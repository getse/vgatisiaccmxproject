/*
 * ConsultorasDaoJdbcImp.java        01/04/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import mx.com.vgati.ccmx.vinculacion.consultoras.dao.ConsultorasDao;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Facturas;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Pagos;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.dto.Roles;
import mx.com.vgati.ccmx.vinculacion.pymes.dao.imp.PyMEsDaoJdbcImp.DocumentoServicioResultSetExtractor;
import mx.com.vgati.ccmx.vinculacion.pymes.dao.imp.PyMEsDaoJdbcImp.DocumentoServicioRowMapper;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Telefonos;
import mx.com.vgati.framework.dao.AbstractBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.util.Null;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class ConsultorasDaoJdbcImp extends AbstractBaseJdbcDao implements
		ConsultorasDao {

	@Override
	public Consultoras getConsultora(int id) throws JdbcDaoException {
		log.debug("getConsultora()");
		Consultoras result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_CONSULTORA,");
		query.append("ID_USUARIO, ");
		query.append("ID_USUARIO_PADRE, ");
		query.append("ID_CONSULTORA_PADRE, ");
		query.append("EMPRESA, ");
		query.append("NOMBRE_CONTACTO, ");
		query.append("APP_PATERNO_CONTACTO, ");
		query.append("APP_MATERNO_CONTACTO, ");
		query.append("CORREO_ELECTRONICO_CONTACTO, ");
		query.append("COSTO_ANTICIPO,");
		query.append("COSTO_ABONO1,");
		query.append("COSTO_ABONO2,");
		query.append("COSTO_FINIQUITO ");
		query.append("FROM INFRA.CONSULTORAS ");
		query.append("WHERE ID_USUARIO = ? ");
		log.debug("query=" + query);
		log.debug(id);

		Object[] o = { id };
		result = (Consultoras) getJdbcTemplate().queryForObject(
				query.toString(), o, new ConsultoraRowMapper());
		result.setTelefonos(getTelefonos(id));
		log.debug("result=" + result);
		return result;
	}

	@Override
	public Mensaje saveRolConsultora(Consultoras consultoras)
			throws DaoException {
		log.debug("saveRolConsultora()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.REL_ROLES ( ");
		query.append("CVE_ROL, ");
		query.append("CVE_USUARIO) ");
		query.append("VALUES( '");
		query.append(Roles.Consultor.name());
		query.append("', '");
		query.append(consultoras.getCorreoElectronico());
		query.append("' )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0, "El Rol se dio de alta satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar el Rol, " + e);
			return new Mensaje(1, "No es posible dar de alta el Rol.");
		}
	}

	public class ConsultoraRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			Consultoras consultoras = new Consultoras();
			consultoras.setIdUsuario(rs.getInt("ID_USUARIO"));
			consultoras.setIdUsuarioPadre(rs.getInt("ID_USUARIO_PADRE"));
			consultoras.setIdConsultoraPadre(rs.getInt("ID_CONSULTORA_PADRE"));
			consultoras.setIdConsultora(rs.getInt("ID_CONSULTORA"));
			consultoras.setEmpresa(rs.getString("EMPRESA"));
			consultoras.setNombreContacto(rs.getString("NOMBRE_CONTACTO"));
			consultoras.setAppPaternoContacto(rs
					.getString("APP_PATERNO_CONTACTO"));
			consultoras.setAppMaternoContacto(rs
					.getString("APP_MATERNO_CONTACTO"));
			consultoras.setCorreoElectronico(rs
					.getString("CORREO_ELECTRONICO_CONTACTO"));
			consultoras.setCostoAnticipo(rs.getDouble("COSTO_ANTICIPO"));
			consultoras.setCostoAbono1(rs.getDouble("COSTO_ABONO1"));
			consultoras.setCostoAbono2(rs.getDouble("COSTO_ABONO2"));
			consultoras.setCostoFiniquito(rs.getDouble("COSTO_FINIQUITO"));
			return consultoras;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Consultoras> getConsultorasAdmin(int idPadre)
			throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_CONSULTORA");
		query.append(",ID_USUARIO");
		query.append(",ID_USUARIO_PADRE");
		query.append(",ID_CONSULTORA_PADRE");
		query.append(",EMPRESA,NOMBRE_CONTACTO");
		query.append(",APP_PATERNO_CONTACTO");
		query.append(",APP_MATERNO_CONTACTO");
		query.append(",CORREO_ELECTRONICO_CONTACTO");
		query.append(" FROM INFRA.CONSULTORAS ");
		query.append("WHERE ID_CONSULTORA_PADRE=" + idPadre);
		log.debug("getConsultoras query* " + query);
		return getJdbcTemplate().query(query.toString(),
				new ConsultorasRowMapper());
	}

	@SuppressWarnings("rawtypes")
	public class ConsultorasRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			ConsultorasResultSetExtractor extractor = new ConsultorasResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class ConsultorasResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Consultoras consultoras = new Consultoras();
			consultoras.setIdUsuario(rs.getInt("ID_USUARIO"));
			consultoras.setIdUsuarioPadre(rs.getInt("ID_USUARIO_PADRE"));
			consultoras.setIdConsultora(rs.getInt("ID_CONSULTORA"));
			consultoras.setIdConsultoraPadre(rs.getInt("ID_CONSULTORA_PADRE"));
			consultoras.setEmpresa(rs.getString("EMPRESA"));
			consultoras.setNombreContacto(rs.getString("NOMBRE_CONTACTO"));
			consultoras.setAppPaternoContacto(rs
					.getString("APP_PATERNO_CONTACTO"));
			consultoras.setAppMaternoContacto(rs
					.getString("APP_MATERNO_CONTACTO"));
			consultoras.setCorreoElectronico(rs
					.getString("CORREO_ELECTRONICO_CONTACTO"));
			return consultoras;
		}

	}

	@Override
	public Mensaje saveConsultor(Consultoras consultor) throws DaoException {
		log.debug("saveTractora()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.CONSULTORAS ( ");
		query.append("ID_USUARIO, ");
		query.append("ID_USUARIO_PADRE, ");
		query.append("ID_CONSULTORA_PADRE, ");
		query.append("EMPRESA, ");
		query.append("NOMBRE_CONTACTO, ");
		query.append("APP_PATERNO_CONTACTO, ");
		query.append("APP_MATERNO_CONTACTO, ");
		query.append("CORREO_ELECTRONICO_CONTACTO) ");
		query.append("VALUES( ");
		query.append(consultor.getIdUsuario());
		query.append(", ");
		query.append(consultor.getIdUsuarioPadre());
		query.append(", ");
		query.append(consultor.getIdConsultoraPadre());
		query.append(", '");
		query.append(consultor.getEmpresa());
		query.append("', '");
		query.append(consultor.getNombreContacto());
		query.append("', '");
		query.append(consultor.getAppPaternoContacto());
		query.append("', '");
		query.append(consultor.getAppMaternoContacto());
		query.append("', '");
		query.append(consultor.getCorreoElectronico());
		query.append("' )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(
					0,
					"El usuario consultor se dio de alta satisfactoriamente. En breve recibirá un correo electrónico con la información requerida y la liga para acceder al sistema.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar la Consultora, " + e);
			return new Mensaje(1, "No es posible dar de alta la Consultora.");
		}
	}

	@Override
	public Mensaje updateConsultor(Consultoras consultor) throws DaoException {
		log.debug("UpdateTractora()");
		Boolean error = false;
		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.CONSULTORAS SET ");
		query.append("NOMBRE_CONTACTO = '");
		query.append(consultor.getNombreContacto());
		query.append("', APP_PATERNO_CONTACTO = '");
		query.append(consultor.getAppPaternoContacto());
		query.append("', APP_MATERNO_CONTACTO = '");
		query.append(consultor.getAppMaternoContacto());
		query.append("' WHERE ID_USUARIO=");
		query.append(consultor.getIdUsuario());
		log.debug("query=" + query);

		for (Telefonos tels : consultor.getTelefonos()) {
			if (tels.getIdTelefono() == 0) {
				error = saveTelefonos(consultor.getIdUsuario(),
						tels.getTelefono());
				if (error) {
					break;
				}
			} else if (tels.getIdTelefono() != 0) {
				error = updateTelefonos(tels.getIdTelefono(),
						tels.getTelefono());
				if (error) {
					break;
				}
			}
		}
		if (!error) {
			try {
				getJdbcTemplate().update(query.toString());
				return new Mensaje(0,
						"Se guardaron correctamente los cambios en su información.");
			} catch (Exception e) {
				log.fatal("ERROR al actualizando datos , " + e);
				return new Mensaje(
						1,
						"No es posible actualizar sus datos, intentelo de nuevo y reportelo al administrador.");
			}
		} else {
			return new Mensaje(
					1,
					"No es posible actualizar sus datos, intentelo de nuevo y reportelo al administrador.");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PyMEs> getPymesAdmin(int idUsuarioAdmin) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT PY.ID_USUARIO");
		query.append(",PY.CORREO_ELECTRONICO");
		query.append(",PY.NOMBRE_COMERCIAL");
		query.append(",(SELECT CONCAT(C.NOMBRE,CONCAT");
		query.append("(' ',CONCAT(C.APELLIDO_PATERNO,CONCAT(' ',APELLIDO_MATERNO)))) FROM ");
		query.append(" INFRA.PYMES AS PM  JOIN INFRA.CONTACTOS AS C ON C.ID_USUARIO ");
		query.append("= PM.ID_USUARIO WHERE C.ID_USUARIO = PM.ID_USUARIO ");
		query.append(" AND PM.ID_USUARIO=PY.ID_USUARIO AND C.B_PRINCIPAL) AS CONTACTO ");
		query.append(" FROM INFRA.PYMES AS PY JOIN INFRA.REL_CONSULTORAS_PYME as REL ");
		query.append(" ON PY.ID_USUARIO=REL.ID_USUARIO_PYME ");
		query.append(" WHERE PY.ID_USUARIO ");
		query.append("NOT IN (SELECT ID_USUARIO_PYME FROM INFRA.REL_CONSULTORAS_PYME");
		query.append(" WHERE ID_USUARIO_CONSULTOR !=" + idUsuarioAdmin);
		query.append(") ORDER BY NOMBRE_COMERCIAL ASC;");
		log.debug("getPymesAdmin() query =" + query);
		return getJdbcTemplate().query(query.toString(),
				new getPymesRowMapper());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PyMEs> getPymes(int idConsultora) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT PY.ID_USUARIO");
		query.append(",PY.CORREO_ELECTRONICO");
		query.append(",PY.NOMBRE_COMERCIAL");
		query.append(",null AS CONTACTO ");
		query.append(" FROM INFRA.PYMES AS PY");
		query.append(" JOIN INFRA.REL_CONSULTORAS_PYME AS RP  ");
		query.append(" ON PY.ID_USUARIO=RP.ID_USUARIO_PYME ");
		query.append(" JOIN INFRA.CONSULTORAS AS C ON RP.ID_USUARIO_CONSULTOR=C.ID_USUARIO ");
		query.append(" WHERE C.ID_CONSULTORA_PADRE = " + idConsultora + ";");
		log.debug("getPymes " + query);
		return getJdbcTemplate().query(query.toString(),
				new getPymesRowMapper());
	}

	@SuppressWarnings("rawtypes")
	public class getPymesRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			PymesResultExtractor pymes = new PymesResultExtractor();
			return pymes.extractData(arg0);
		}

	}

	@SuppressWarnings("rawtypes")
	public class PymesResultExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet arg0) throws SQLException,
				DataAccessException {
			PyMEs pyMEs = new PyMEs();
			pyMEs.setIdUsuario(arg0.getInt("ID_USUARIO"));
			pyMEs.setCorreoElectronico(arg0.getString("CORREO_ELECTRONICO"));
			pyMEs.setNombreComercial(arg0.getString("NOMBRE_COMERCIAL"));
			pyMEs.setNombreContacto1(arg0.getString("CONTACTO"));
			return pyMEs;
		}
	}

	@Override
	public Mensaje saveRelPymesConsultora(int uPymes, int uConsultor)
			throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO INFRA.REL_CONSULTORAS_PYME(ID_USUARIO_PYME,ID_USUARIO_CONSULTOR)");
		query.append("VALUES(");
		query.append(uPymes);
		query.append(",");
		query.append(uConsultor);
		query.append(");");
		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0, "Se ha asignado la PyME correctamente.");
		} catch (Exception e) {
			return new Mensaje(1,
					"No es posible asignar la PyME, la PyME ya habia sido asignada.");
		}
	}

	@Override
	public Mensaje saveCedula(List<Integer> idPyme, String cedula)
			throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE INFRA.PYMES SET CEDULA='");
		query.append(cedula);
		query.append("' WHERE ");
		for (int i = 0; i < idPyme.size(); i++) {
			query.append("ID_USUARIO=");
			query.append(idPyme.get(i));
			if ((i + 1) < idPyme.size()) {
				query.append(" or ");
			}
		}

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0, "La cedula " + cedula
					+ " fue asignada a consultor(as):");
		} catch (Exception e) {
			log.fatal("ERROR al salvar el contacto, " + e);
			return new Mensaje(1, "La cedula " + cedula
					+ " no pudo ser asignada a consultor(as):");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Facturas> getFacturasPorAdmin(int idUsuario)
			throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_USUARIO");
		query.append(", ID_FACTURA");
		query.append(", ESTATUS FROM INFRA.FACTURAS");
		query.append(" WHERE ID_USUARIO  = ");
		query.append(idUsuario);
		query.append(" AND ESTATUS  LIKE('%Pendiente%');");
		log.debug("getFacturasPorAdmin() query: " + query);
		return getJdbcTemplate().query(query.toString(),
				new FacturasPorAdminMapper());
	}

	@SuppressWarnings("rawtypes")
	public class FacturasPorAdminMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			FacturasPorAdminExtractor pymes = new FacturasPorAdminExtractor();
			return pymes.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class FacturasPorAdminExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Facturas fac = new Facturas();
			fac.setIdFactura(rs.getString("ID_FACTURA"));
			fac.setEstatus(rs.getString("ESTATUS"));
			fac.setIdUsuario(rs.getInt("ID_USUARIO"));
			return fac;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getCorreoCordCons() throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT CVE_USUARIO  FROM INFRA.USUARIOS");
		query.append(" WHERE ID_USUARIO = SELECT ID_USUARIO");
		query.append("  FROM INFRA.COORDINADORES_CONSULTORA ;");
		String list = getJdbcTemplate().queryForObject(query.toString(),
				new getCorreoCordConsMapper());

		return list;

	}

	@SuppressWarnings("rawtypes")
	public class getCorreoCordConsMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			getCorreoCordConsExtractor res = new getCorreoCordConsExtractor();
			return res.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class getCorreoCordConsExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			String r = rs.getString("CVE_USUARIO");
			return r;
		}

	}

	@Override
	public String getPymeByServicio(int idServicio) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT PY.NOMBRE_COMERCIAL");
		query.append(" FROM INFRA.PYMES AS PY");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA AS SC ");
		query.append(" ON SC.ID_USUARIO = PY.ID_USUARIO ");
		query.append(" WHERE SC.ID_CONSULTORIA = " + idServicio);
		String result = (String) getJdbcTemplate().queryForObject(
				query.toString(), null, String.class);
		log.debug("Resulto " + result);
		return result;
	}

	@Override
	public Mensaje saveFacturas(List<Pagos> anticipo, List<Pagos> abono1,
			List<Pagos> abono2, List<Pagos> finiquito, String idFactura)
			throws DaoException {
		log.debug("saveFacturas()");
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO INFRA.PAGOS(ID_SERVICO_CONSULTORIA,TIPO,NUMERO)");
		query.append("VALUES(");
		if (anticipo != null) {
			for (int i = 0; i < anticipo.size(); i++) {
				if (i > 0) {
					query.append(",(");
				}
				query.append(anticipo.get(i).getIdServicios());
				query.append(",'Anticipo','");
				query.append(idFactura);
				query.append("')");
			}
		}
		if (abono1 != null && abono1.size() > 0 && anticipo != null
				&& anticipo.size() > 0) {
			query.append(",(");
		}
		if (abono1 != null) {
			for (int i = 0; i < abono1.size(); i++) {
				if (i > 0) {
					query.append(",(");
				}
				query.append(abono1.get(i).getIdServicios());
				query.append(",'Abono1','");
				query.append(idFactura);
				query.append("')");
			}
		}
		if (abono2 != null
				&& abono2.size() > 0
				&& ((anticipo != null && anticipo.size() > 0) || (abono1 != null && abono1
						.size() > 0))) {
			query.append(",(");
		}
		if (abono2 != null) {
			for (int i = 0; i < abono2.size(); i++) {
				if (i > 0) {
					query.append(",(");
				}
				query.append(abono2.get(i).getIdServicios());
				query.append(",'Abono2','");
				query.append(idFactura);
				query.append("')");
			}
		}
		if (finiquito != null
				&& finiquito.size() > 0
				&& (((abono2 != null && abono2.size() > 0))
						|| (abono1 != null && abono1.size() > 0) || (anticipo != null && finiquito
						.size() > 0))) {
			query.append(",(");
		}
		if (finiquito != null) {
			for (int i = 0; i < finiquito.size(); i++) {
				if (i > 0) {
					query.append(",(");
				}
				query.append(finiquito.get(i).getIdServicios());
				query.append(",'Finiquito','");
				query.append(idFactura);
				query.append("')");
			}
		}
		log.debug("query: " + query);
		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Se ha solicitado correctamente el pago de las factura "
							+ idFactura);
		} catch (Exception e) {
			log.fatal("ERROR al salvar el contacto, " + e);
			return new Mensaje(1,
					"Ocurrio un error al solicitar el pago de las factura "
							+ idFactura + " consulte al dministrador");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pagos> getPagos(int idConsultora, int filtro)
			throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT SC.ID_CONSULTORIA");
		query.append(" ,PY.NOMBRE_COMERCIAL  AS NOMBRE ");
		query.append(" ,C.NOMBRE_CONTACTO ");
		query.append(" ,SC.ESTATUS ");
		query.append(" ,(SELECT CVE_USUARIO  FROM INFRA.USUARIOS  WHERE ID_USUARIO = PY.ID_USUARIO) AS CORREO ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Anticipo%') AS ANTICIPO ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Abono1%') AS ABONO1 ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Abono2%') AS ABONO2 ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Finiquito%') AS FINIQUITO ");
		query.append(" FROM INFRA.CONSULTORAS as C ");
		query.append(" JOIN INFRA.REL_CONSULTORAS_PYME as RC ON C.ID_USUARIO=RC.ID_USUARIO_CONSULTOR ");
		query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO = RC.ID_USUARIO_PYME ");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_USUARIO=PY.ID_USUARIO ");
		query.append(" WHERE " + idConsultora + "=ID_CONSULTORA_PADRE ");
		if (filtro == 1) {
			query.append(" AND SC.ESTATUS='DIAGNOSTICO' OR SC.ESTATUS='PLAN' OR SC.ESTATUS='IMPLEMENTACION' OR SC.ESTATUS='EVALUACION'");
		} else if (filtro == 2) {
			query.append(" AND SC.ESTATUS='CONCLUIDA' ");
		}
		query.append(" ORDER BY PY.NOMBRE_COMERCIAL;");
		log.debug("getPagos() \nQuery " + query);
		return getJdbcTemplate().query(query.toString(),
				new getPagosRowMapper());
	}

	@SuppressWarnings("rawtypes")
	public class getPagosRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			PymesPagosExtractor pymes = new PymesPagosExtractor();
			return pymes.extractData(arg0);
		}

	}

	@SuppressWarnings("rawtypes")
	public class PymesPagosExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Pagos pagos = new Pagos();
			pagos.setIdServicios(rs.getInt("ID_CONSULTORIA"));
			pagos.setNombreComercial(rs.getString("NOMBRE"));
			pagos.setNombreContacto(rs.getString("NOMBRE_CONTACTO"));
			pagos.setEstatus(rs.getString("ESTATUS"));
			pagos.setAnticipo(rs.getString("ANTICIPO"));
			pagos.setAbono1(rs.getString("ABONO1"));
			pagos.setAbono2(rs.getString("ABONO2"));
			pagos.setFiniquito(rs.getString("FINIQUITO"));
			pagos.setCorreoPyme(rs.getString("CORREO"));
			return pagos;

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagos getPagos(int idServicio) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT SC.ID_CONSULTORIA");
		query.append(" ,PY.NOMBRE_COMERCIAL  AS NOMBRE ");
		query.append(" ,C.NOMBRE_CONTACTO ");
		query.append(" ,SC.ESTATUS ");
		query.append(" ,(SELECT CVE_USUARIO  FROM INFRA.USUARIOS  WHERE ID_USUARIO = PY.ID_USUARIO) AS CORREO ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Anticipo%') AS ANTICIPO ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Abono1%') AS ABONO1 ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Abono2%') AS ABONO2 ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Finiquito%') AS FINIQUITO ");
		query.append(" FROM INFRA.CONSULTORAS as C ");
		query.append(" JOIN INFRA.REL_CONSULTORAS_PYME as RC ON C.ID_USUARIO = RC.ID_USUARIO_CONSULTOR ");
		query.append(" JOIN INFRA.PYMES as PY ON RC.ID_USUARIO_PYME = PY.ID_USUARIO ");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON PY.ID_USUARIO=SC.ID_USUARIO ");
		query.append(" WHERE " + idServicio
				+ "=SC.ID_CONSULTORIA AND C.ID_CONSULTORA_PADRE=0");
		log.debug("Obteniendo Servicio con id" + idServicio + " query = "
				+ query);
		List<Pagos> p = getJdbcTemplate().query(query.toString(),
				new getPagosRowMapper());
		if (p != null && !p.isEmpty()) {
			return p.get(0);
		} else {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PyMEs> getBusquedaPyMEs(String busqueda, String estado,
			String cveScian, int idConsultora, int idUsuario)
			throws DaoException {
		log.debug("getBusquedaPyMEs()");
		List<String> l = null;
		if (busqueda != null && !busqueda.trim().equals("")) {
			String cadenaBusqueda = busqueda.toUpperCase().trim()
					.replace('Á', 'A').replace('É', 'E').replace('Í', 'I')
					.replace('Ó', 'O').replace('Ú', 'U').replace('Ü', 'U');
			StringTokenizer st = new StringTokenizer(cadenaBusqueda, " ");
			l = new ArrayList<String>();
			while (st.hasMoreElements()) {
				l.add((String) st.nextElement());
			}
		}
		StringBuffer query = new StringBuffer();
		query.append("SELECT DISTINCT P.ID_USUARIO");
		query.append(", P.ID_USUARIO_PADRE");
		query.append(", P.NOMBRE_COMERCIAL");
		query.append(", D.ESTADO");
		query.append(", C.TELEFONO");
		query.append(", C.NOMBRE");
		query.append(", C.APELLIDO_PATERNO");
		query.append(", C.APELLIDO_MATERNO");
		query.append(", C.CORREO_ELECTRONICO ");
		query.append(", P.LIBERA_EXPEDIENTE");
		query.append(", NULL as CEDULA_MODIFIC");
		query.append(", NULL as CEDULA");
		query.append(", NULL as ID_CONSULTORIA");
		query.append(" FROM INFRA.PYMES P");
		query.append(", INFRA.CONTACTOS C");
		query.append(", INFRA.PRODUCTOS PP");
		query.append(", INFRA.REL_DOMICILIOS_USUARIO RDU");
		query.append(", INFRA.REL_CONSULTORAS_PYME REL  ");
		query.append(", INFRA.CONSULTORAS CO");
		query.append(", INFRA.DOMICILIOS D");
		query.append(", INFRA.USUARIOS U");
		query.append(", INFRA.CATEGORIAS CAT ");
		query.append("WHERE P.ID_USUARIO = C.ID_USUARIO ");
		query.append("AND P.PERSONALIDAD_JURIDICA IS NOT NULL ");
		query.append("AND P.ID_USUARIO = PP.ID_USUARIO(+) ");
		query.append("AND P.ID_USUARIO = RDU.ID_USUARIO(+) ");
		query.append("AND RDU.ID_DOMICILIO = D.ID_DOMICILIO(+) ");
		query.append("AND P.CORREO_ELECTRONICO = U.CVE_USUARIO(+) ");
		query.append("AND P.ID_USUARIO = REL.ID_USUARIO_PYME ");
		query.append("AND REL.ID_USUARIO_CONSULTOR=CO.ID_USUARIO ");
		query.append("AND P.ID_USUARIO = CAT.ID_USUARIO(+) ");
		query.append("AND C.B_PRINCIPAL = true ");
		if (idConsultora > 0 && idUsuario < 1) {
			query.append(" AND (CO.ID_CONSULTORA_PADRE = " + idConsultora + " ");
			query.append(" OR CO.ID_CONSULTORA = " + idConsultora + " ) ");
		}
		if (idUsuario > 0) {
			query.append(" AND CO.ID_USUARIO = " + idUsuario + " ");
		}
		if (l != null && !l.isEmpty()) {
			query.append(" AND ( ( ( ");
			for (String valor : l) {
				query.append(" UPPER(PP.PRODUCTO) LIKE '%".concat(
						Null.free(valor)).concat("%' "));
				if (l.indexOf(valor) != l.size() - 1)
					query.append(" OR ");
			}
			query.append(" ) OR ( ");
			log.debug("idConsultora2=" + idConsultora);
			for (String valor : l) {
				query.append(" UPPER(P.NOMBRE_COMERCIAL) LIKE '%".concat(
						Null.free(valor)).concat("%' "));
				if (l.indexOf(valor) != l.size() - 1)
					query.append(" OR ");
			}
			query.append(" ) ) ");
			if (!estado.isEmpty())
				query.append(" AND D.ESTADO LIKE '%".concat(estado).concat(
						"%' "));

			if (!cveScian.isEmpty())
				query.append(" AND CAT.CVE_SCIAN LIKE '".concat(
						cveScian.length() > 3 ? cveScian.substring(0, 3)
								: cveScian).concat("%' "));
			query.append(" ) ");
		}
		log.debug("query=" + query);

		try {
			List<PyMEs> listPyME = getJdbcTemplate().query(query.toString(),
					new BusquedaPyMEsRowMapper());
			log.debug("result="
					+ (listPyME != null && listPyME.size() > 20 ? listPyME
							.size() : listPyME));
			return listPyME;
		} catch (Exception e) {
			log.debug("Error: " + e);
		}
		return null;
	}

	@Override
	public List<PyMEs> getPyMEsCedula(int idConsultorPadre) throws DaoException {
		log.debug("getPyMEsCedula()");
		StringBuffer query = new StringBuffer();
		query.append("SELECT DISTINCT P.ID_USUARIO");
		query.append(", P.ID_USUARIO_PADRE");
		query.append(", SVC.ID_CONSULTORIA");
		query.append(", P.NOMBRE_COMERCIAL");
		query.append(", D.ESTADO");
		query.append(", C.TELEFONO");
		query.append(", C.NOMBRE");
		query.append(", C.APELLIDO_PATERNO");
		query.append(", C.APELLIDO_MATERNO");
		query.append(", C.CORREO_ELECTRONICO ");
		query.append(", P.LIBERA_EXPEDIENTE");
		query.append(", 'TRUE' AS CEDULA_MODIFIC");
		query.append(", P.CEDULA ");
		query.append(" FROM INFRA.PYMES P");
		query.append(", INFRA.CONTACTOS C");
		query.append(", INFRA.PRODUCTOS PP");
		query.append(", INFRA.REL_DOMICILIOS_USUARIO RDU");
		query.append(", INFRA.REL_CONSULTORAS_PYME as REL  ");
		query.append(", INFRA.CONSULTORAS as CO");
		query.append(", INFRA.DOMICILIOS D ");
		query.append(", INFRA.SERVICIOS_CONSULTORIA SVC ");
		query.append("WHERE P.ID_USUARIO = C.ID_USUARIO ");
		query.append("AND P.ID_USUARIO = PP.ID_USUARIO(+) ");
		query.append("AND  P.ID_USUARIO = RDU.ID_USUARIO(+) ");
		query.append("AND RDU.ID_DOMICILIO = D.ID_DOMICILIO(+) ");
		query.append("AND P.ID_USUARIO = REL.ID_USUARIO_PYME ");
		query.append("AND ID_USUARIO_CONSULTOR=CO.ID_USUARIO ");
		query.append("AND SVC.ID_USUARIO = P.ID_USUARIO ");
		query.append("AND  CO.ID_CONSULTORA_PADRE = " + idConsultorPadre + " ");
		query.append(" AND SVC.ESTATUS LIKE'%DIAGNOSTICO%';");

		log.debug("query = " + query);
		try {
			@SuppressWarnings("unchecked")
			List<PyMEs> listPyME = getJdbcTemplate().query(query.toString(),
					new BusquedaPyMEsRowMapper());
			log.debug("result="
					+ (listPyME != null && listPyME.size() > 20 ? listPyME
							.size() : listPyME));
			return listPyME;

		} catch (Exception e) {
			log.debug("Error: " + e);
		}
		return null;
	}

	@Override
	public List<PyMEs> getPyMEsConsultor(int idConsultor) throws DaoException {
		log.debug("getPyMEsConsultor()");
		StringBuffer query = new StringBuffer();
		query.append("SELECT DISTINCT P.ID_USUARIO");
		query.append(", P.ID_USUARIO_PADRE");
		query.append(", SVC.ID_CONSULTORIA");
		query.append(", P.NOMBRE_COMERCIAL");
		query.append(", C.TELEFONO");
		query.append(", C.NOMBRE");
		query.append(", C.APELLIDO_PATERNO");
		query.append(", C.APELLIDO_MATERNO");
		query.append(", C.CORREO_ELECTRONICO ");
		query.append(", 'TRUE' AS CEDULA_MODIFIC");
		query.append(", P.CEDULA ");
		query.append(", P.LIBERA_EXPEDIENTE");
		query.append(", NULL AS ESTADO ");
		query.append(", NULL AS CEDULA_MODIFIC ");
		query.append(" FROM INFRA.PYMES P");
		query.append(" JOIN INFRA.REL_CONSULTORAS_PYME as REL  ON REL.ID_USUARIO_PYME = P.ID_USUARIO ");
		query.append(" JOIN INFRA.CONSULTORAS as CO ON REL.ID_USUARIO_CONSULTOR = CO.ID_USUARIO ");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA SVC ON SVC.ID_USUARIO  =   P.ID_USUARIO ");
		query.append(" LEFT JOIN INFRA.REL_DOMICILIOS_USUARIO AS RDU ON RDU.ID_USUARIO = P.ID_USUARIO ");
		query.append(" LEFT JOIN INFRA.DOMICILIOS D ON D.ID_DOMICILIO = RDU.ID_DOMICILIO ");
		query.append(" LEFT JOIN INFRA.CONTACTOS C ON P.ID_USUARIO = C.ID_USUARIO ");
		query.append("WHERE  CO.ID_CONSULTORA = " + idConsultor
				+ "  AND C.B_PRINCIPAL");

		log.debug("query = " + query);
		try {
			@SuppressWarnings("unchecked")
			List<PyMEs> listPyME = getJdbcTemplate().query(query.toString(),
					new BusquedaPyMEsRowMapper());
			log.debug("result="
					+ (listPyME != null && listPyME.size() > 20 ? listPyME
							.size() : listPyME));
			return listPyME;

		} catch (Exception e) {
			log.debug("Error: " + e);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public class BusquedaPyMEsRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			BusquedaPyMEsResultSetExtractor extractor = new BusquedaPyMEsResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class BusquedaPyMEsResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			PyMEs pymes = new PyMEs();
			pymes.setIdUsuario(rs.getInt("ID_USUARIO"));
			pymes.setIdUsuarioPadre(rs.getInt("ID_USUARIO_PADRE"));
			pymes.setNombreComercial(rs.getString("NOMBRE_COMERCIAL"));
			pymes.setEstado(rs.getString("ESTADO"));
			pymes.setTelefonoContacto1(rs.getString("TELEFONO"));
			pymes.setNombreContacto1(rs.getString("NOMBRE"));
			pymes.setAppPaterno1(rs.getString("APELLIDO_PATERNO"));
			pymes.setAppMaterno1(rs.getString("APELLIDO_MATERNO"));
			pymes.setCorreoElectronicoContacto1(rs
					.getString("CORREO_ELECTRONICO"));
			pymes.setCedulaModificable(rs.getBoolean("CEDULA_MODIFIC"));
			pymes.setCedula(rs.getString("CEDULA"));
			pymes.setIdServicioConsultoria(rs.getInt("ID_CONSULTORIA"));
			pymes.setEstatus(rs.getBoolean("LIBERA_EXPEDIENTE"));
			return pymes;

		}
	}

	public List<Telefonos> getTelefonos(int idUsuario) throws JdbcDaoException {
		log.debug("getTelefonos()");

		List<Telefonos> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_TELEFONO, ");
		query.append("ID_USUARIO, ");
		query.append("TELEFONO ");
		query.append("FROM INFRA.TELEFONOS ");
		query.append("WHERE ID_USUARIO = " + idUsuario);
		query.append("ORDER BY ID_TELEFONO ");
		log.debug("query=" + query);

		try {
			result = (List<Telefonos>) getJdbcTemplate().query(
					query.toString(), new TelefonosRowMapper());
		} catch (Exception e) {
			throw new JdbcDaoException(e);
		}

		log.debug("result=" + result);
		return result;
	}

	public class TelefonosRowMapper implements RowMapper<Telefonos> {

		@Override
		public Telefonos mapRow(ResultSet rs, int ln) throws SQLException {
			TelefonosResultSetExtractor extractor = new TelefonosResultSetExtractor();
			return (Telefonos) extractor.extractData(rs);
		}

	}

	public class TelefonosResultSetExtractor implements
			ResultSetExtractor<Telefonos> {

		@Override
		public Telefonos extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Telefonos telefonos = new Telefonos();
			telefonos.setIdTelefono(rs.getInt("ID_TELEFONO"));
			telefonos.setIdUsuario(rs.getInt("ID_USUARIO"));
			telefonos.setTelefono(rs.getString("TELEFONO"));
			return telefonos;
		}

	}

	public boolean updateTelefonos(int idTelefono, String telefono)
			throws DaoException {

		log.debug("updateTelefonos()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE INFRA.TELEFONOS ");
		query.append("SET TELEFONO='");
		query.append(telefono);
		query.append("' WHERE ID_TELEFONO=");
		query.append(idTelefono);
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return false;
		} catch (Exception e) {
			log.fatal("ERROR al eiminar Telefonos, " + e);
			return true;
		}

	}

	public boolean saveTelefonos(int id, String telefono) throws DaoException {

		log.debug("saveTelefonos()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.TELEFONOS ( ");
		query.append("ID_USUARIO, ");
		query.append("TELEFONO) ");
		query.append("VALUES( ");
		query.append(id);
		query.append(", '");
		query.append(telefono);
		query.append("' )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return false;
		} catch (Exception e) {
			log.fatal("ERROR al insertar el Telefono, " + e);
			return true;
		}

	}

	@Override
	public ServiciosConsultoria getServiciosConsultoria(int idConsultoria)
			throws DaoException {
		log.debug("getServiciosConsultoria()");
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_CONSULTORIA");
		query.append(",ID_USUARIO");
		query.append(",B_CONSULTORIA_20");
		query.append(",B_CONSULTORIA_40");
		query.append(",B_CONSULTORIA_60");
		query.append(",B_CONSULTORIA_80");
		query.append(",RECURSOS_HUMANOS_ANTES");
		query.append(",MERCADEO_ANTES");
		query.append(",FINANZAS_ANTES");
		query.append(",ADMINISTRACION_ANTES");
		query.append(",PROCESOS_ANTES");
		query.append(",RECURSOS_HUMANOS_DESPUES");
		query.append(",MERCADEO_DESPUES");
		query.append(",FINANZAS_DESPUES");
		query.append(",ADMINISTRACION_DESPUES");
		query.append(",PROCESOS_DESPUES");
		query.append(",DIPLOMADO_RECOMENDADO_1");
		query.append(",DIPLOMADO_RECOMENDADO_2");
		query.append(",FECHA_INICIO");
		query.append(",FECHA_TERMINO");
		query.append(",ESTATUS ");
		query.append(",COMENTARIO ");
		query.append(" FROM INFRA.SERVICIOS_CONSULTORIA ");
		query.append(" WHERE ID_CONSULTORIA = " + idConsultoria);
		log.debug("query " + query);
		ServiciosConsultoria result = (ServiciosConsultoria) getJdbcTemplate()
				.queryForObject(query.toString(),
						new getServiciosConsultoriaRowMapper());
		log.debug("Reultado " + result);
		return result;

	}

	public class getServiciosConsultoriaRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			ServiciosConsultoria sc = new ServiciosConsultoria();
			sc.setIdConsultoria(rs.getInt("ID_CONSULTORIA"));
			sc.setIdUsuario(rs.getInt("ID_USUARIO"));
			sc.setRecursosHumanosAntes(rs.getFloat("RECURSOS_HUMANOS_ANTES"));
			sc.setMercadeoAntes(rs.getFloat("MERCADEO_ANTES"));
			sc.setFinanzasAntes(rs.getFloat("FINANZAS_ANTES"));
			sc.setAdministracionAntes(rs.getFloat("ADMINISTRACION_ANTES"));
			sc.setProcesosAntes(rs.getFloat("PROCESOS_ANTES"));
			sc.setRecursosHumanosDespues(rs
					.getFloat("RECURSOS_HUMANOS_DESPUES"));
			sc.setMercadeoDespues(rs.getFloat("MERCADEO_DESPUES"));
			sc.setFinanzasDespues(rs.getFloat("FINANZAS_DESPUES"));
			sc.setAdministracionDespues(rs.getFloat("ADMINISTRACION_DESPUES"));
			sc.setProcesosDespues(rs.getFloat("PROCESOS_DESPUES"));
			sc.setDiplomadoRecomendado1(rs.getInt("DIPLOMADO_RECOMENDADO_1"));
			sc.setDiplomadoRecomendado2(rs.getInt("DIPLOMADO_RECOMENDADO_2"));
			sc.setInicio(rs.getDate("FECHA_INICIO"));
			sc.setTermino(rs.getDate("FECHA_TERMINO"));
			sc.setEstatus(rs.getString("ESTATUS"));
			sc.setComentario(rs.getString("COMENTARIO"));
			sc.setbConsultoriaVeinte(rs.getBoolean("B_CONSULTORIA_20"));
			sc.setbConsultoriaCuarenta(rs.getBoolean("B_CONSULTORIA_40"));
			sc.setbConsultoriaSesenta(rs.getBoolean("B_CONSULTORIA_60"));
			sc.setbConsultoriaOchenta(rs.getBoolean("B_CONSULTORIA_80"));
			return sc;
		}

	}

	@Override
	public Mensaje saveServiciosConsultoria(ServiciosConsultoria servCo)
			throws DaoException {
		StringBuffer query = new StringBuffer();
		if (servCo.getIdConsultoria() > 0) {
			query.append("UPDATE INFRA.SERVICIOS_CONSULTORIA SET ");
			query.append(" RECURSOS_HUMANOS_ANTES=");
			query.append(servCo.getRecursosHumanosAntes());
			query.append(",MERCADEO_ANTES=");
			query.append(servCo.getMercadeoAntes());
			query.append(",FINANZAS_ANTES=");
			query.append(servCo.getFinanzasAntes());
			query.append(",ADMINISTRACION_ANTES=");
			query.append(servCo.getAdministracionAntes());
			query.append(",PROCESOS_ANTES=");
			query.append(servCo.getProcesosAntes());
			query.append(",RECURSOS_HUMANOS_DESPUES=");
			query.append(servCo.getRecursosHumanosDespues());
			query.append(",MERCADEO_DESPUES=");
			query.append(servCo.getMercadeoDespues());
			query.append(",FINANZAS_DESPUES=");
			query.append(servCo.getFinanzasDespues());
			query.append(",ADMINISTRACION_DESPUES=");
			query.append(servCo.getAdministracionDespues());
			query.append(",PROCESOS_DESPUES=");
			query.append(servCo.getProcesosDespues());
			query.append(",DIPLOMADO_RECOMENDADO_1=");
			query.append(servCo.getDiplomadoRecomendado1());
			query.append(",DIPLOMADO_RECOMENDADO_2=");
			query.append(servCo.getDiplomadoRecomendado2());
			query.append(",ESTATUS='");
			query.append(servCo.getEstatus());
			query.append("',FECHA_INICIO='");
			query.append(new java.sql.Date(servCo.getInicio().getTime()));
			query.append("',FECHA_TERMINO=");
			query.append((servCo.getTermino()!=null)?("'" + new java.sql.Date(servCo.getTermino().getTime())+"'"):null);
			query.append(", COMENTARIO = '");
			query.append(servCo.getComentario());
			query.append("' WHERE ID_CONSULTORIA=" + servCo.getIdConsultoria()
					+ ";");
		} else {
			query.append("INSERT INTO INFRA.SERVICIOS_CONSULTORIA SET(");
			query.append(" RECURSOS_HUMANOS_ANTES");
			query.append(",MERCADEO_ANTES");
			query.append(",FINANZAS_ANTES");
			query.append(",ADMINISTRACION_ANTES");
			query.append(",PROCESOS_ANTES");
			query.append(",RECURSOS_HUMANOS_DESPUES");
			query.append(",MERCADEO_DESPUES");
			query.append(",FINANZAS_DESPUES");
			query.append(",ADMINISTRACION_DESPUES");
			query.append(",PROCESOS_DESPUES");
			query.append(",DIPLOMADO_RECOMENDADO_1");
			query.append(",DIPLOMADO_RECOMENDADO_2");
			query.append(",FECHA_INICIO");
			query.append(",FECHA_TERMINO");
			query.append(",COMENTARIO ");
			query.append(")VALUES(");
			query.append(servCo.getRecursosHumanosAntes());
			query.append(",");
			query.append(servCo.getMercadeoAntes());
			query.append(",");
			query.append(servCo.getFinanzasAntes());
			query.append(",");
			query.append(servCo.getAdministracionAntes());
			query.append(",");
			query.append(servCo.getProcesosAntes());
			query.append(",");
			query.append(servCo.getRecursosHumanosDespues());
			query.append(",");
			query.append(servCo.getMercadeoDespues());
			query.append(",");
			query.append(servCo.getFinanzasDespues());
			query.append(",");
			query.append(servCo.getAdministracionDespues());
			query.append(",");
			query.append(servCo.getProcesosDespues());
			query.append(",");
			query.append(servCo.getDiplomadoRecomendado1());
			query.append(",");
			query.append(servCo.getDiplomadoRecomendado2());
			query.append(",");
			query.append(new java.sql.Date(servCo.getInicio().getTime()));
			query.append(",");
			query.append((servCo.getTermino()!=null)?(new java.sql.Date(servCo.getTermino().getTime())):null);
			query.append(",'");
			query.append(servCo.getComentario());
			query.append("');");
		}
		try {
			log.debug(query);
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Se guardaron correctamente los cambios sobre el seguimiento de la PyME.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar el Rol, " + e);
			return new Mensaje(1,
					"No es posible guardar sus modificaciones, intentelo mas tarde.");
		}
	}

	@Override
	public Mensaje saveFactura(String idFactura, int idUsuario)
			throws DaoException {
		log.debug("saveTractora()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append(" INFRA.FACTURAS");
		query.append("(ID_FACTURA,ESTATUS,ID_USUARIO) ");
		query.append("VALUES('");
		query.append(idFactura);
		query.append("', ");
		query.append("'Pendiente'");
		query.append(", ");
		query.append(idUsuario);
		query.append(");");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0, "Factura ingresada correctamente.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar la Factura, " + e);
			return new Mensaje(1,
					"No es posible dar de alta la Factura, puede encontrarse en existencia.");
		}
	}

	@Override
	public Documento getArchivo(int id) throws JdbcDaoException {
		log.debug("getArchivo()");

		Documento result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_ARCHIVO, ");
		query.append("NOMBRE, ");
		query.append("CONTENIDO ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_ARCHIVO = ? ");
		log.debug("query=" + query);
		log.debug(id);

		Object[] o = { id };
		result = (Documento) getJdbcTemplate().queryForObject(query.toString(),
				o, new DocumentoRowMapper());

		log.debug("result=" + result);
		return result;
	}

	public class DocumentoRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			Documento doc = new Documento();
			doc.setIdArchivo(rs.getInt("ID_ARCHIVO"));
			doc.setNombre(rs.getString("NOMBRE"));
			doc.setIs(rs.getBinaryStream("CONTENIDO"));

			return doc;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PyMEs> getPymesLiberar(int id) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT PY.NOMBRE_COMERCIAL,PY.CORREO_ELECTRONICO ,PY.ID_USUARIO , PASSWORD");
		query.append(" FROM INFRA.PYMES PY ");
		query.append(" JOIN INFRA.USUARIOS U ON U.CVE_USUARIO=PY.CORREO_ELECTRONICO ");
		query.append(" WHERE (PY.LIBERA_EXPEDIENTE= FALSE OR PY.LIBERA_EXPEDIENTE IS NULL) AND PY.ID_USUARIO=");
		query.append(id);
		log.debug("getPymesLiberar()" + query);
		return getJdbcTemplate().query(query.toString(),
				new getPymesLiberarRowMapper());
	}

	@SuppressWarnings("rawtypes")
	public class getPymesLiberarRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			PymesLiberarResultExtractor pymes = new PymesLiberarResultExtractor();
			return pymes.extractData(arg0);
		}

	}

	@SuppressWarnings("rawtypes")
	public class PymesLiberarResultExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			PyMEs py = new PyMEs();
			py.setIdUsuario(rs.getInt("ID_USUARIO"));
			py.setCorreoElectronico(rs.getString("CORREO_ELECTRONICO"));
			py.setNombreComercial(rs.getString("NOMBRE_COMERCIAL"));
			py.setPassword(rs.getString("PASSWORD"));
			return py;
		}
	}

	@Override
	public boolean saveLiberarPymes(int id) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE INFRA.PYMES");
		query.append(" SET LIBERA_EXPEDIENTE = true ");
		query.append("WHERE ID_USUARIO=");
		query.append(id);
		log.debug("saveLiberarPymes()" + query);
		try {
			getJdbcTemplate().update(query.toString());
			return true;
		} catch (Exception e) {
			log.fatal("Error al guardar los cambios, " + e);
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Diplomados> getTemaDiplomado() throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT D.TEMA,(SELECT ID_DIPLOMADO FROM DIPLOMADOS WHERE TEMA=D.TEMA AND ROWNUM=1) ID_DIPLOMADO  FROM INFRA.DIPLOMADOS D WHERE YEAR=YEAR(CURRENT_DATE) GROUP BY TEMA ");
		query.append("ORDER BY TEMA");
		log.debug("getTemaDiplomado() query" + query);
		return getJdbcTemplate().query(query.toString(),
				new getTemaDiplomadoMapper());
	}

	@SuppressWarnings("rawtypes")
	public class getTemaDiplomadoMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			getTemaDiplomadoExtractor res = new getTemaDiplomadoExtractor();
			return res.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class getTemaDiplomadoExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Diplomados d = new Diplomados();
			d.setTema(rs.getString("TEMA"));
			d.setIdDiplomado(rs.getInt("ID_DIPLOMADO"));
			return d;
		}

	}
	
	@Override
	public Mensaje insertDocServicio(Documento documento) throws DaoException {
		log.debug("insertDocServicio()");
		if(getArchivoServiciosConsultoria(documento.getIdConsultoria())!=null){
			StringBuffer query = new StringBuffer();
			query.append("UPDATE ");
			query.append("INFRA.ARCHIVOS SET ");
			query.append(" NOMBRE = ? ");
			query.append(",DESCRIPCION_ARCHIVO = ? ");
			query.append(",MIME = ? ");
			query.append(",TIPO = ? ");
			query.append(",CONTENIDO = ? ");
			query.append(" WHERE ID_SERVICIO_CONSULTORIA = ? ");
			log.debug("query=" + query);
			log.debug("documento: " + documento);
			PreparedStatement ps = null;
			try {
				getConnection().setAutoCommit(false);
				ps = getConnection().prepareStatement(query.toString());
				ps.setString(1, documento.getNombre());
				ps.setString(2, documento.getDescripcionArchivo());
				ps.setString(3, documento.getMimeType(documento.getNombre()));
				ps.setString(4, documento.getFileType(documento.getNombre()));
				ps.setBlob(5, documento.getIs());
				ps.setInt(6, documento.getIdConsultoria());
				ps.executeUpdate();
				getConnection().commit();

				return new Mensaje(0,
						"El Documento se dio de alta satisfactoriamente.");
			} catch (SQLException sqle) {
				try {
					getConnection().rollback();
				} catch (Exception e) {
					log.fatal("Error SQL al hacer rollback en la conexion." + e);
					e.printStackTrace();
				}
				log.fatal("Error SQL al intentar insertar el documento." + sqle);
				sqle.printStackTrace();
			} finally {
				try {
					ps.close();
					getConnection().setAutoCommit(false);
					getConnection().close();
				} catch (SQLException sqle) {
					log.fatal("Error SQL al intentar cerrar la conexion hacia la BD."
							+ sqle);
					sqle.printStackTrace();
				}
			}
		} else {
			StringBuffer query = new StringBuffer();
			query.append("INSERT INTO ");
			query.append("INFRA.ARCHIVOS( ");
			query.append("ID_SERVICIO_CONSULTORIA, ");
			query.append("NOMBRE, ");
			query.append("DESCRIPCION_ARCHIVO, ");
			query.append("MIME, ");
			query.append("TIPO, ");
			query.append("CONTENIDO ) ");
			query.append("VALUES( ?, ?, ?, ?, ?, ? )");
			log.debug("query=" + query);
			log.debug("documento: " + documento);
			PreparedStatement ps = null;
			try {
				getConnection().setAutoCommit(false);
				ps = getConnection().prepareStatement(query.toString());
				ps.setInt(1, documento.getIdConsultoria());
				ps.setString(2, documento.getNombre());
				ps.setString(3, documento.getDescripcionArchivo());
				ps.setString(4, documento.getMimeType(documento.getNombre()));
				ps.setString(5, documento.getFileType(documento.getNombre()));
				ps.setBlob(6, documento.getIs());
				ps.executeUpdate();
				getConnection().commit();

				return new Mensaje(0,
						"El Documento se dio de alta satisfactoriamente.");
			} catch (SQLException sqle) {
				try {
					getConnection().rollback();
				} catch (Exception e) {
					log.fatal("Error SQL al hacer rollback en la conexion." + e);
					e.printStackTrace();
				}
				log.fatal("Error SQL al intentar insertar el documento." + sqle);
				sqle.printStackTrace();
			} finally {
				try {
					ps.close();
					getConnection().setAutoCommit(false);
					getConnection().close();
				} catch (SQLException sqle) {
					log.fatal("Error SQL al intentar cerrar la conexion hacia la BD."
							+ sqle);
					sqle.printStackTrace();
				}
			}
		}



		return new Mensaje(1, "No es posible guradar el Documento.");

	}
	
	@Override
	public Documento getArchivoServiciosConsultoria(int idServicio)
			throws DaoException {
		log.debug("getArchivosDiplomados()");

		List<Documento> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_ARCHIVO, ");
		query.append("NOMBRE, ");
		query.append("DESCRIPCION_ARCHIVO ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_SERVICIO_CONSULTORIA = " + idServicio);
		query.append(" ORDER BY ID_ARCHIVO DESC");
		log.debug("query=" + query);
		log.debug(idServicio);
		try {
			result = (List<Documento>) getJdbcTemplate().query(
					query.toString(), new DocumentoServicioRowMapper());
		} catch (EmptyResultDataAccessException erdae) {
			log.warn("No se obtubieron documentos");
		} catch (Exception e) {
			throw new JdbcDaoException(e);
		}
		log.debug("result=" + result);
		if(result!=null && result.size()>0){
			return result.get(0);
		} else {
			return null;
		}
	}
	public class DocumentoServicioRowMapper implements RowMapper<Documento> {

		@Override
		public Documento mapRow(ResultSet rs, int ln) throws SQLException {
			DocumentoServicioResultSetExtractor extractor = new DocumentoServicioResultSetExtractor();
			return (Documento) extractor.extractData(rs);
		}
	}

	public class DocumentoServicioResultSetExtractor implements
			ResultSetExtractor<Documento> {

		@Override
		public Documento extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Documento doc = new Documento();
			doc.setIdArchivo(rs.getInt("ID_ARCHIVO"));
			doc.setNombre(rs.getString("NOMBRE"));
			doc.setDescripcionArchivo(rs.getString("DESCRIPCION_ARCHIVO"));
			return doc;
		}
	}

}