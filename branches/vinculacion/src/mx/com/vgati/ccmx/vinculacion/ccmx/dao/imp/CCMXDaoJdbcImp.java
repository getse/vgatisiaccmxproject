/*
 * CCMXDaoJdbcImp.java        07/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.ccmx.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.dao.CCMXDao;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.dto.Contacto;
import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.dto.Roles;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dao.AbstractBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Usuario;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class CCMXDaoJdbcImp extends AbstractBaseJdbcDao implements CCMXDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Tractoras> getTractoras() throws DaoException {
		log.debug("getTractoras()");

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_USUARIO, ");
		query.append("ID_TRACTORA_PADRE, ");
		query.append("EMPRESA, ");
		query.append("NOMBRE_CONTACTO, ");
		query.append("APP_PATERNO, ");
		query.append("APP_MATERNO, ");
		query.append("CORREO_ELECTRONICO, ");
		query.append("PUESTO ");
		query.append("FROM TRACTORAS ");
		query.append("WHERE ID_TRACTORA_PADRE = 0 ");
		query.append("ORDER BY ID_USUARIO ASC ");
		log.debug("query=" + query);

		List<Tractoras> trac = getJdbcTemplate().query(query.toString(),
				new TractorasRowMapper());
		return trac;

	}

	@SuppressWarnings("rawtypes")
	public class TractorasRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			TractorasResultSetExtractor extractor = new TractorasResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class TractorasResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Tractoras tractoras = new Tractoras();
			tractoras.setIdUsuario(rs.getInt("ID_USUARIO"));
			tractoras.setIdTractoraPadre(rs.getInt("ID_TRACTORA_PADRE"));
			tractoras.setEmpresa(rs.getString("EMPRESA"));
			tractoras.setNombreContacto(rs.getString("NOMBRE_CONTACTO"));
			tractoras.setAppPaterno(rs.getString("APP_PATERNO"));
			tractoras.setAppMaterno(rs.getString("APP_MATERNO"));
			tractoras.setCorreoElectronico(rs.getString("CORREO_ELECTRONICO"));
			tractoras.setPuesto(rs.getString("PUESTO"));
			return tractoras;
		}

	}

	@Override
	public Mensaje saveUsuarioTractora(Tractoras tractoras) throws DaoException {
		log.debug("saveUsuarioTractora()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.USUARIOS ( ");
		query.append("CVE_USUARIO, ");
		query.append("PASSWORD) ");
		query.append("VALUES( '");
		query.append(tractoras.getCorreoElectronico());
		query.append("', '");
		query.append(tractoras.getPassword());
		query.append("' )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El Usuario se dio de alta satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar el Usuario, " + e);
			return new Mensaje(1,
					"No es posible dar de alta la Tractora, revise que el Usuario no exista.");
		}

	}

	@Override
	public Mensaje saveRolTractora(Tractoras tractoras) throws DaoException {
		log.debug("saveRolTractora()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.REL_ROLES ( ");
		query.append("CVE_ROL, ");
		query.append("CVE_USUARIO) ");
		query.append("VALUES( '");
		query.append(Roles.Tractora.name());
		query.append("', '");
		query.append(tractoras.getCorreoElectronico());
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

	@Override
	public Mensaje saveTractora(Tractoras tractoras) throws DaoException {
		log.debug("saveTractora()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.TRACTORAS ( ");
		query.append("ID_USUARIO, ");
		query.append("ID_USUARIO_PADRE, ");
		query.append("ID_TRACTORA_PADRE, ");
		query.append("EMPRESA, ");
		query.append("NOMBRE_CONTACTO, ");
		query.append("APP_PATERNO, ");
		query.append("APP_MATERNO, ");
		query.append("CORREO_ELECTRONICO, ");
		query.append("PUESTO) ");
		query.append("VALUES( '");
		query.append(tractoras.getIdUsuario());
		query.append("', '");
		query.append(tractoras.getIdUsuarioPadre());
		query.append("', '");
		query.append(0);
		query.append("', '");
		query.append(tractoras.getEmpresa());
		query.append("', '");
		query.append(tractoras.getNombreContacto());
		query.append("', '");
		query.append(tractoras.getAppPaterno());
		query.append("', '");
		query.append(tractoras.getAppMaterno());
		query.append("', '");
		query.append(tractoras.getCorreoElectronico());
		query.append(tractoras.getPuesto() == null ? "', " : "', '");
		query.append(tractoras.getPuesto());
		query.append(tractoras.getPuesto() == null ? " )" : "' )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(
					0,
					"Los datos se dieron de alta satisfactoriamente. En breve recibirá un correo electrónico con la información requerida y la liga para acceder al sistema.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar la Tractora, " + e);
			return new Mensaje(1,
					"No es posible dar de alta la los datos ingresados.");
		}

	}

	@Override
	public Mensaje updateTractora(Tractoras tractoras, String credenciales,
			String rol) throws DaoException {
		log.debug("updateTractora()");

		StringBuffer query = new StringBuffer();
		try {
			if (!tractoras.getCorreoElectronico()
					.equalsIgnoreCase(credenciales)) {
				query = new StringBuffer();
				query.append("DELETE ");
				query.append("INFRA.REL_ROLES ");
				query.append("WHERE CVE_USUARIO = '");
				query.append(credenciales);
				query.append("'");
				log.debug("query=" + query);

				getJdbcTemplate().update(query.toString());

				query = new StringBuffer();
				query.append("UPDATE ");
				query.append("INFRA.USUARIOS SET ");
				query.append("CVE_USUARIO = '");
				query.append(tractoras.getCorreoElectronico());
				query.append("' WHERE ID_USUARIO = ");
				query.append(tractoras.getIdUsuario());
				log.debug("query=" + query);

				getJdbcTemplate().update(query.toString());

				query = new StringBuffer();
				query.append("INSERT INTO ");
				query.append("INFRA.REL_ROLES (CVE_ROL, ");
				query.append("CVE_USUARIO) VALUES ('");
				query.append(rol);
				query.append("', '");
				query.append(tractoras.getCorreoElectronico());
				query.append("')");
				log.debug("query=" + query);

				getJdbcTemplate().update(query.toString());
			}
		} catch (DataIntegrityViolationException e) {
			return new Mensaje(
					1,
					"No es posible modificar la Tractora con ese correo electrónico debido a que ya existe en el sistema.");
		}

		try {
			query = new StringBuffer();
			query.append("UPDATE ");
			query.append("INFRA.TRACTORAS SET ");
			query.append("EMPRESA = '");
			query.append(tractoras.getEmpresa());
			query.append("', NOMBRE_CONTACTO = '");
			query.append(tractoras.getNombreContacto());
			query.append("', APP_PATERNO = '");
			query.append(tractoras.getAppPaterno());
			query.append("', APP_MATERNO = '");
			query.append(tractoras.getAppMaterno());
			query.append("', CORREO_ELECTRONICO = '");
			query.append(tractoras.getCorreoElectronico());
			query.append("' WHERE ID_USUARIO = ");
			query.append(tractoras.getIdUsuario());
			log.debug("query=" + query);

			getJdbcTemplate().update(query.toString());

			return new Mensaje(
					0,
					"Sus datos se acualizaron satisfactoriamente. En breve recibirá un correo electrónico con la liga para acceder al sistema.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar la Tractora, " + e);
			return new Mensaje(1, "No es posible actualizar sus datos.");
		}

	}

	public List<PyMEs> getPyMEs() throws DaoException {
		log.debug("getPyMEs()");
		StringBuffer query = new StringBuffer();
		query.append(" SELECT ");
		query.append(" P.ID_USUARIO, ");
		query.append(" P.ID_USUARIO_PADRE, ");
		query.append(" P.NOMBRE_COMERCIAL, ");
		query.append(" P.B_INHIBIR_VINCULACION, ");
		query.append(" P.LIBERA_EXPEDIENTE, ");
		query.append(" C.NOMBRE, ");
		query.append(" C.APELLIDO_PATERNO, ");
		query.append(" C.APELLIDO_MATERNO, ");
		query.append(" C.CORREO_ELECTRONICO, ");
		query.append(" C.TELEFONO, ");
		query.append(" D.ESTADO ");
		query.append(" FROM INFRA.PYMES AS P ");
		query.append(" JOIN INFRA.CONTACTOS AS C ");
		query.append(" ON P.ID_USUARIO = C.ID_USUARIO ");
		query.append(" LEFT JOIN INFRA.REL_DOMICILIOS_USUARIO AS RDOM ");
		query.append(" ON P.ID_USUARIO = RDOM.ID_USUARIO ");
		query.append(" LEFT JOIN INFRA.DOMICILIOS AS D ");
		query.append(" ON RDOM.ID_DOMICILIO = D.ID_DOMICILIO ");
		query.append(" WHERE C.B_PRINCIPAL = true ");
		query.append(" ORDER BY ID_USUARIO ASC");
		log.debug("query=" + query);

		@SuppressWarnings("unchecked")
		List<PyMEs> pymes = getJdbcTemplate().query(query.toString(),
				new PyMEsRowMapper());
		return pymes;

	}

	@SuppressWarnings("rawtypes")
	public class PyMEsRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			PyMEsResultSetExtractor extractor = new PyMEsResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class PyMEsResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			PyMEs pymes = new PyMEs();
			pymes.setIdUsuario(rs.getInt("ID_USUARIO"));
			pymes.setIdUsuarioPadre(rs.getInt("ID_USUARIO_PADRE"));
			pymes.setNombreComercial(rs.getString("NOMBRE_COMERCIAL"));
			pymes.setbInhibirVinculacion(rs.getBoolean("B_INHIBIR_VINCULACION"));
			pymes.setEstatus(rs.getBoolean("LIBERA_EXPEDIENTE"));
			pymes.setNombreContacto1(rs.getString("NOMBRE"));
			pymes.setAppPaterno1(rs.getString("APELLIDO_PATERNO"));
			pymes.setAppMaterno1(rs.getString("APELLIDO_MATERNO"));
			pymes.setCorreoElectronicoContacto1(rs
					.getString("CORREO_ELECTRONICO"));
			pymes.setTelefonoContacto1(rs.getString("TELEFONO"));
			pymes.setEstado(rs.getString("ESTADO"));
			return pymes;
		}
	}

	@Override
	public Mensaje saveUsuarioPyME(PyMEs pyMEs) throws DaoException {
		log.debug("saveUsuarioPyME()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.USUARIOS ( ");
		query.append("CVE_USUARIO, ");
		query.append("PASSWORD) ");
		query.append("VALUES( '");
		query.append(pyMEs.getCorreoElectronico());
		query.append("', '");
		query.append(pyMEs.getPassword());
		query.append("' )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El Usuario PyME se dio de alta satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar el Usuario PyME, " + e);
			return new Mensaje(1,
					"No es posible dar de alta al usuario PyME, revise que el Usuario no exista.");
		}

	}

	@Override
	public Mensaje saveRolPyME(PyMEs pyMEs) throws DaoException {
		log.debug("saveRolPyME()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.REL_ROLES ( ");
		query.append("CVE_ROL, ");
		query.append("CVE_USUARIO) ");
		query.append("VALUES( '");
		query.append(Roles.PyME.name());
		query.append("', '");
		query.append(pyMEs.getCorreoElectronico());
		query.append("' )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El Rol PyME se dio de alta satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar el Rol PyME, " + e);
			return new Mensaje(1, "No es posible dar de alta el Rol PyME.");
		}

	}

	@Override
	public Mensaje savePyME(PyMEs pyMEs) throws DaoException {
		log.debug("savePyME()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.PYMES ( ");
		query.append("ID_USUARIO, ");
		query.append("ID_USUARIO_PADRE, ");
		query.append("CORREO_ELECTRONICO, ");
		query.append("NOMBRE_COMERCIAL) ");
		query.append("VALUES( '");
		query.append(pyMEs.getIdUsuario());
		query.append("', '");
		query.append(pyMEs.getIdUsuarioPadre());
		query.append("', '");
		query.append(pyMEs.getCorreoElectronico());
		query.append("', '");
		query.append(pyMEs.getNombreComercial());
		query.append("' )");
		log.debug("query=" + query);

		try {

			Contacto co = null;
			boolean result = true;

			getJdbcTemplate().update(query.toString());

			int id = pyMEs.getIdUsuario();
			/* Inserta Contacto */
			if (pyMEs.getNombreContacto1() != null) {
				log.debug("Insertando el Contacto 1 = "
						+ pyMEs.getNombreContacto1());
				co = new Contacto();
				co.setIdUsuario(id);
				co.setTipo(pyMEs.getTipoContacto1());
				co.setNombre(pyMEs.getNombreContacto1());
				co.setApellidoPaterno(pyMEs.getAppPaterno1());
				co.setApellidoMaterno(pyMEs.getAppMaterno1());
				co.setCorreoElectronico(pyMEs.getCorreoElectronicoContacto1());
				result = saveContacto(co).getRespuesta() == 0;
			}
			if (result) {
				Mensaje m = new Mensaje();
				m.setRespuesta(0);
				m.setMensaje("El Usuario PyME se dio de alta satisfactoriamente. En breve recibirá un correo electrónico con la información requerida y la liga para acceder al sistema.");
				m.setId(String.valueOf(id));
				return m;
			} else {
				return new Mensaje(1,
						"El usuario PyME se dio de alta con errores al guardar el contacto.");
			}

		} catch (Exception e) {
			log.fatal("ERROR al insertar el usuario PyME, " + e);
			return new Mensaje(1, "No es posible dar de alta al usuario PyME.");
		}
	}

	@Override
	public List<Consultoras> getConsultoras(int id) throws DaoException {
		log.debug("getConsultoras()");
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_USUARIO, ");
		query.append("ID_USUARIO_PADRE, ");
		query.append("EMPRESA, ");
		query.append("NOMBRE_CONTACTO, ");
		query.append("APP_PATERNO_CONTACTO, ");
		query.append("APP_MATERNO_CONTACTO, ");
		query.append("CORREO_ELECTRONICO_CONTACTO ");
		query.append("FROM CONSULTORAS ");
		query.append("WHERE ID_USUARIO_PADRE =  " + id);
		query.append(" AND ID_CONSULTORA_PADRE=0 ORDER BY ID_CONSULTORA ASC ");
		log.debug("query=" + query);
		log.debug(id);
		List<Consultoras> cons = getJdbcTemplate().query(query.toString(),
				new ConsultorasRowMapper());
		return cons;
	}

	public class ConsultorasRowMapper implements RowMapper<Consultoras> {
		@Override
		public Consultoras mapRow(ResultSet rs, int ln) throws SQLException {
			ConsultorasResultSetExtractor extractor = new ConsultorasResultSetExtractor();
			return extractor.extractData(rs);
		}
	}

	public class ConsultorasResultSetExtractor implements
			ResultSetExtractor<Consultoras> {

		@Override
		public Consultoras extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Consultoras consultoras = new Consultoras();
			consultoras.setIdUsuario(rs.getInt("ID_USUARIO"));
			consultoras.setIdUsuarioPadre(rs.getInt("ID_USUARIO_PADRE"));
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
	public Mensaje saveUsuarioConsultora(Consultoras consultoras)
			throws DaoException {
		log.debug("saveUsuarioConsultora()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.USUARIOS ( ");
		query.append("CVE_USUARIO, ");
		query.append("PASSWORD) ");
		query.append("VALUES( '");
		query.append(consultoras.getCorreoElectronico());
		query.append("', '");
		query.append(consultoras.getPassword());
		query.append("' )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El Usuario se dio de alta satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar el Usuario, " + e);
			return new Mensaje(1,
					"No es posible dar de alta la Consultora, revise que el Usuario no exista.");
		}
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
		query.append(Roles.AdministradorConsultores.name());
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

	@Override
	public Mensaje saveConsultora(Consultoras consultoras) throws DaoException {
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
		query.append("COSTO_ANTICIPO, ");
		query.append("COSTO_ABONO1, ");
		query.append("COSTO_ABONO2, ");
		query.append("COSTO_FINIQUITO, ");
		query.append("CORREO_ELECTRONICO_CONTACTO) ");
		query.append("VALUES( ");
		query.append(consultoras.getIdUsuario());
		query.append(", ");
		query.append(consultoras.getIdUsuarioPadre());
		query.append(", ");
		query.append(consultoras.getIdConsultoraPadre());
		query.append(", '");
		query.append(consultoras.getEmpresa());
		query.append("', '");
		query.append(consultoras.getNombreContacto());
		query.append("', '");
		query.append(consultoras.getAppPaternoContacto());
		query.append("', '");
		query.append(consultoras.getAppMaternoContacto());
		query.append("', ");
		query.append(consultoras.getCostoAnticipo());
		query.append(", ");
		query.append(consultoras.getCostoAbono1());
		query.append(", ");
		query.append(consultoras.getCostoAbono2());
		query.append(", ");
		query.append(consultoras.getCostoFiniquito());
		query.append(", '");
		query.append(consultoras.getCorreoElectronico());
		query.append("' )");
		log.debug("query=" + query);
		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(
					0,
					"La Empresa Consultora se dio de alta satisfactoriamente. En breve recibirá un correo electrónico con la información requerida y la liga para acceder al sistema.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar la Consultora, " + e);
			return new Mensaje(1, "No es posible dar de alta la Consultora.");
		}
	}

	@Override
	public Mensaje updateConsultora(Consultoras consultoras, String credenciales)
			throws JdbcDaoException {
		log.debug("updateConsultora()");

		StringBuffer query = new StringBuffer();
		try {
			if (!consultoras.getCorreoElectronico().equalsIgnoreCase(
					credenciales)) {
				query = new StringBuffer();
				query.append("DELETE ");
				query.append("INFRA.REL_ROLES ");
				query.append("WHERE CVE_USUARIO = '");
				query.append(credenciales);
				query.append("'");
				log.debug("query=" + query);

				getJdbcTemplate().update(query.toString());

				query = new StringBuffer();
				query.append("UPDATE ");
				query.append("INFRA.USUARIOS SET ");
				query.append("CVE_USUARIO = '");
				query.append(consultoras.getCorreoElectronico());
				query.append("' WHERE ID_USUARIO = ");
				query.append(consultoras.getIdUsuario());
				log.debug("query=" + query);

				getJdbcTemplate().update(query.toString());

				query = new StringBuffer();
				query.append("INSERT INTO ");
				query.append("INFRA.REL_ROLES (CVE_ROL, ");
				query.append("CVE_USUARIO) VALUES ('");
				query.append(Roles.AdministradorConsultores.name());
				query.append("', '");
				query.append(consultoras.getCorreoElectronico());
				query.append("')");
				log.debug("query=" + query);

				getJdbcTemplate().update(query.toString());
			}
		} catch (DataIntegrityViolationException e) {
			return new Mensaje(
					1,
					"No es posible modificar la Consultora con ese correo electrónico debido a que ya existe en el sistema.");
		}

		try {
			query = new StringBuffer();
			query.append("UPDATE ");
			query.append("INFRA.CONSULTORAS SET ");
			query.append("EMPRESA = '");
			query.append(consultoras.getEmpresa());
			query.append("', NOMBRE_CONTACTO = '");
			query.append(consultoras.getNombreContacto());
			query.append("', APP_PATERNO_CONTACTO = '");
			query.append(consultoras.getAppPaternoContacto());
			query.append("', APP_MATERNO_CONTACTO = '");
			query.append(consultoras.getAppMaternoContacto());
			query.append("', CORREO_ELECTRONICO_CONTACTO = '");
			query.append(consultoras.getCorreoElectronico());
			query.append("', COSTO_ANTICIPO = ");
			query.append(consultoras.getCostoAnticipo());
			query.append(", COSTO_ABONO1 = ");
			query.append(consultoras.getCostoAbono1());
			query.append(", COSTO_ABONO2 = ");
			query.append(consultoras.getCostoAbono2());
			query.append(", COSTO_FINIQUITO = ");
			query.append(consultoras.getCostoFiniquito());
			query.append(" WHERE ID_USUARIO = ");
			query.append(consultoras.getIdUsuario());
			log.debug("query=" + query);

			getJdbcTemplate().update(query.toString());

			return new Mensaje(
					0,
					"La Consultora se acualizó satisfactoriamente. En breve recibirá un correo electrónico con la liga para acceder al sistema.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar la Consultora, " + e);
			return new Mensaje(1, "No es posible actualizar la Consultora.");
		}

	}

	public Mensaje saveContacto(Contacto contacto) throws DaoException {
		log.debug("saveContacto()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.CONTACTOS (");
		query.append("ID_USUARIO, ");
		query.append("TIPO, ");
		query.append("NOMBRE, ");
		query.append("APELLIDO_PATERNO, ");
		query.append("APELLIDO_MATERNO, ");
		query.append("CORREO_ELECTRONICO, ");
		query.append("B_PRINCIPAL, ");
		query.append("TELEFONO) ");
		query.append("VALUES ('");
		query.append(contacto.getIdUsuario());
		query.append("', '");
		query.append(contacto.getTipo());
		query.append("', '");
		query.append(contacto.getNombre());
		query.append("', '");
		query.append(contacto.getApellidoPaterno());
		query.append("', '");
		query.append(contacto.getApellidoMaterno());
		query.append("', '");
		query.append(contacto.getCorreoElectronico());
		query.append("', ");
		query.append(true);
		query.append(", '");
		query.append(contacto.getTelefono());
		query.append("')");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos del Contacto han sido registrados correctamente.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar el contacto, " + e);
			return new Mensaje(1,
					"No es posible registrar el contacto, intentelo más tarde.");
		}
	}

	@Override
	public Mensaje saveRelPyMETractora(PyMEs pyMEs) throws DaoException {
		log.debug("saveRelPyMETractora()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.REL_PYMES_TRACTORAS ( ");
		query.append("ID_USUARIO_TRACTORA, ");
		query.append("ID_USUARIO_PYME) ");
		query.append("VALUES( ");
		query.append(pyMEs.getIdTractora());
		query.append(", ");
		query.append(pyMEs.getIdUsuario());
		query.append(" )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El Usuario PyME se dio de alta satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar el Usuario PyME, " + e);
			return new Mensaje(1,
					"No es posible dar de alta al usuario PyME, revise que el Usuario no exista.");
		}
	}

	@SuppressWarnings("unchecked")
	public String getNombreTractoras(int id) throws DaoException {
		log.debug("getNombreTractoras()");

		String result;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("EMPRESA ");
		query.append("FROM INFRA.TRACTORAS ");
		query.append("WHERE ID_USUARIO = " + id);
		log.debug("query=" + query);

		try {
			result = (String) getJdbcTemplate().queryForObject(
					query.toString(), new NombreTractoraRowMapper());
		} catch (Exception e) {
			result = "0";
		}

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class NombreTractoraRowMapper implements RowMapper {

		@Override
		public String mapRow(ResultSet rs, int ln) throws SQLException {
			return rs.getString("EMPRESA");
		}
	}

	@Override
	public Mensaje saveClientes(String nomTractora, int idPyME)
			throws DaoException {
		log.debug("saveClientes()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.CLIENTES ( ");
		query.append("ID_USUARIO, ");
		query.append("CLIENTE) ");
		query.append("VALUES( ");
		query.append(idPyME);
		query.append(", '");
		query.append(nomTractora);
		query.append("' )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El Usuario PyME se dio de alta satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar el Usuario PyME, " + e);
			return new Mensaje(1,
					"No es posible dar de alta al usuario PyME, revise que el Usuario no exista.");
		}
	}

	@Override
	public Mensaje deshabilitaPyMEs(int estatus, boolean libera)
			throws DaoException {
		log.debug("deshabilitaPyMEs/habilitaPyMEs()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.PYMES SET ");
		query.append("LIBERA_EXPEDIENTE = ");
		query.append(libera);
		query.append(" WHERE ID_USUARIO = ");
		query.append(estatus);
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"La PyME seleccionada se ha deshabilitado correctamente.");
		} catch (Exception e) {
			log.fatal("ERROR al deshabilitar la PyME seleccionada, " + e);
			return new Mensaje(1,
					"No es posible deshabilitar la PyME, intentelo más tarde.");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tractoras> getDetallesTractoras() throws DaoException {
		log.debug("getDetallesTractoras()");

		StringBuffer query = new StringBuffer();
		query.append("SELECT T.ID_USUARIO, ");
		query.append("T.EMPRESA, ");
		query.append("( SELECT COUNT(ID_USUARIO) ");
		query.append("FROM INFRA.TRACTORAS ");
		query.append("WHERE ID_TRACTORA_PADRE = ");
		query.append("T.ID_USUARIO) AS COMPRADORES, ");
		query.append("( SELECT COUNT(ID_TRACTORA) ");
		query.append("FROM INFRA.REQUERIMIENTOS ");
		query.append("WHERE ID_TRACTORA IN ");
		query.append("( SELECT ID_USUARIO ");
		query.append("FROM INFRA.TRACTORAS ");
		query.append("WHERE ID_TRACTORA_PADRE = ");
		query.append("T.ID_USUARIO ) + ");
		query.append("SELECT COUNT(ID_TRACTORA) ");
		query.append("FROM INFRA.REQUERIMIENTOS ");
		query.append("WHERE ID_TRACTORA IN (");
		query.append("T.ID_USUARIO) ) AS REQUERIMIENTOS");
		query.append(", ( ( SELECT COUNT(ID_TRACTORA) ");
		query.append("FROM INFRA.REQUERIMIENTOS ");
		query.append("WHERE ID_TRACTORA IN ( ");
		query.append("SELECT ID_USUARIO ");
		query.append("FROM INFRA.TRACTORAS ");
		query.append("WHERE ID_TRACTORA_PADRE = ");
		query.append("T.ID_USUARIO");
		query.append(" ) AND ( FECHA_EXPIRA >= ");
		query.append(" CURRENT_DATE OR FECHA_EXPIRA ");
		query.append(" IS NULL ) ) + ");
		query.append("( SELECT COUNT(ID_TRACTORA) ");
		query.append("FROM INFRA.REQUERIMIENTOS ");
		query.append("WHERE ID_TRACTORA IN (T.ID_USUARIO)");
		query.append(" AND ( FECHA_EXPIRA >= CURRENT_DATE ");
		query.append("OR FECHA_EXPIRA IS NULL ) ) ) AS ");
		query.append("ACTIVOS FROM INFRA.TRACTORAS T ");
		query.append("WHERE T.ID_TRACTORA_PADRE = 0 ");
		query.append("ORDER BY T.EMPRESA ASC ");
		log.debug("query=" + query);

		List<Tractoras> trac = getJdbcTemplate().query(query.toString(),
				new DetallesTractorasRowMapper());
		return trac;

	}

	@SuppressWarnings("rawtypes")
	public class DetallesTractorasRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			DetallesTractorasResultSetExtractor extractor = new DetallesTractorasResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class DetallesTractorasResultSetExtractor implements
			ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Tractoras tractoras = new Tractoras();
			tractoras.setEmpresa(rs.getString("EMPRESA"));
			tractoras.setCompradores(rs.getInt("COMPRADORES"));
			tractoras.setRequerimientos(rs.getInt("REQUERIMIENTOS"));
			tractoras.setRequerimientosActivos(rs.getInt("ACTIVOS"));
			return tractoras;
		}

	}

	@Override
	public Mensaje saveDiplomados(Diplomados diplomado, int generacion)
			throws DaoException {
		log.debug("saveDiplomados()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.DIPLOMADOS ( ");
		query.append("TEMA, ");
		query.append("GENERACION, ");
		query.append("YEAR) ");
		query.append("VALUES ( '");
		query.append(diplomado.getTema());
		query.append("', ");
		query.append(generacion);
		query.append(", ");
		query.append(diplomado.getYear());
		query.append(" )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El Diplomado se dio de alta satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al registrar el Diplomado, " + e);
			return new Mensaje(1,
					"No es posible dar de alta el diplomado, intentelo más tarde.");
		}
	}

	@Override
	public Mensaje updateDiplomado(int id, String tema) throws DaoException {
		log.debug("updateDiplomado()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.DIPLOMADOS SET ");
		query.append("TEMA = '");
		query.append(tema);
		query.append("' ");
		query.append("WHERE ID_DIPLOMADO = ");
		query.append(id);
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El Diplomado se ha actualizado correctamente.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar el Diplomado, " + e);
			return new Mensaje(1,
					"No es posible actualizar el Diplomado, intentelo más tarde.");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getIdServicios(int id) throws DaoException {
		log.debug("getIdServicios()");

		String result;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("COUNT(ID_SERVICIOS_DIPLOMADO) AS TOTAL ");
		query.append("FROM INFRA.SERVICIOS_DIPLOMADO ");
		query.append("WHERE ID_DIPLOMADO = " + id);
		log.debug("query=" + query);

		try {
			result = (String) getJdbcTemplate().queryForObject(
					query.toString(), new IdServiciosRowMapper());
		} catch (Exception e) {
			log.debug("EXCEPTION... " + e);
			result = "0";
		}

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class IdServiciosRowMapper implements RowMapper {

		@Override
		public String mapRow(ResultSet rs, int ln) throws SQLException {
			return rs.getString("TOTAL");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getListaIds(int id) throws DaoException {
		log.debug("getListaIds()");

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_DOMICILIO ");
		query.append("FROM INFRA.SESIONES ");
		query.append("WHERE ID_DIPLOMADO = " + id);
		log.debug("query=" + query);

		List<Integer> l = getJdbcTemplate().query(query.toString(),
				new ListaIdsRowMapper());
		return l;
	}

	@SuppressWarnings("rawtypes")
	public class ListaIdsRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			return rs.getInt("ID_DOMICILIO");
		}
	}

	@Override
	public Mensaje deleteDomicilios(int id) throws DaoException {
		log.debug("deleteDomicilios()");

		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM ");
		query.append("INFRA.DOMICILIOS ");
		query.append("WHERE ID_DOMICILIO = ");
		query.append(id);
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0, "El Domicilio se eliminó satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al eliminar el Domicilio, " + e);
			return new Mensaje(1, "No es posible eliminar el Domicilio.");
		}
	}

	@Override
	public Mensaje deleteSesiones(int id) throws DaoException {
		log.debug("deleteSesiones()");

		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM ");
		query.append("INFRA.SESIONES ");
		query.append("WHERE ID_DIPLOMADO = ");
		query.append(id);
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0, "La sesión se eliminó satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al eliminar la sesión, " + e);
			return new Mensaje(1, "No es posible eliminar la sesión.");
		}
	}

	@Override
	public Mensaje deleteDiplomados(int id) throws DaoException {
		log.debug("deleteDiplomados()");

		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM ");
		query.append("INFRA.DIPLOMADOS ");
		query.append("WHERE ID_DIPLOMADO = ");
		query.append(id);
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0, "El diplomado se eliminó satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al eliminar el diplomado, " + e);
			return new Mensaje(1, "No es posible eliminar el diplomado.");
		}
	}

	@Override
	public List<Usuario> getUsuarios() throws DaoException {
		log.debug("getUsuarios()");

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("U.ID_USUARIO AS ID");
		query.append(", R.CVE_ROL AS ROL");
		query.append(", U.CVE_USUARIO AS CORREO");
		query.append(", U.PASSWORD AS PASSWORD");
		query.append(" FROM  ");
		query.append("INFRA.USUARIOS U");
		query.append(", INFRA.REL_ROLES R ");
		query.append("WHERE U.CVE_USUARIO = R.CVE_USUARIO");
		query.append(" ORDER BY R.CVE_ROL, U.CVE_USUARIO");
		log.debug("query=" + query);

		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = getJdbcTemplate().query(query.toString(),
				new UsuariosRowMapper());
		return usuarios;

	}

	@SuppressWarnings("rawtypes")
	public class UsuariosRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			UsuariosResultSetExtractor extractor = new UsuariosResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class UsuariosResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(rs.getInt("ID"));
			usuario.setRol(rs.getString("ROL"));
			usuario.setId(rs.getString("CORREO"));
			usuario.setCredenciales(rs.getString("PASSWORD"));
			return usuario;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public String getPyMEsTotal() throws DaoException {
		log.debug("getPyMEsTotal()");

		String result;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("COUNT(*) AS TOTAL ");
		query.append("FROM INFRA.PYMES ");
		log.debug("query=" + query);

		try {
			result = (String) getJdbcTemplate().queryForObject(
					query.toString(), new PyMEsTotalRowMapper());
		} catch (Exception e) {
			result = "0";
		}

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class PyMEsTotalRowMapper implements RowMapper {

		@Override
		public String mapRow(ResultSet rs, int ln) throws SQLException {
			return rs.getString("TOTAL");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getPyMEsActivas() throws DaoException {
		log.debug("getPyMEsActivas()");

		String result;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("COUNT(*) AS ACTIVAS ");
		query.append("FROM INFRA.PYMES ");
		query.append("WHERE LIBERA_EXPEDIENTE = true");
		log.debug("query=" + query);

		try {
			result = (String) getJdbcTemplate().queryForObject(
					query.toString(), new PyMEsActivasRowMapper());
		} catch (Exception e) {
			result = "0";
		}

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class PyMEsActivasRowMapper implements RowMapper {

		@Override
		public String mapRow(ResultSet rs, int ln) throws SQLException {
			return rs.getString("ACTIVAS");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getPyMEsExpediente() throws DaoException {
		log.debug("getPyMEsExpediente()");

		String result;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("COUNT(*) AS EXPEDIENTE ");
		query.append("FROM INFRA.PYMES ");
		query.append("WHERE F_AVISO_PRIVACIDAD IS NOT NULL");
		log.debug("query=" + query);

		try {
			result = (String) getJdbcTemplate().queryForObject(
					query.toString(), new PyMEsExpedienteRowMapper());
		} catch (Exception e) {
			result = "0";
		}

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class PyMEsExpedienteRowMapper implements RowMapper {

		@Override
		public String mapRow(ResultSet rs, int ln) throws SQLException {
			return rs.getString("EXPEDIENTE");
		}
	}

	@Override
	public Mensaje saveDocumento(Documento archivo, int rol)
			throws DaoException {
		log.debug("saveDocumento()");

		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM ");
		query.append("INFRA.ARCHIVOS ");
		query.append("WHERE ID_MANUAL = ");
		query.append(rol);
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
		} catch (Exception e) {
			log.fatal("ERROR al eliminar el Documento, " + e);
		}

		query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.ARCHIVOS( ");
		query.append("ID_USUARIO, ");
		query.append("ID_MANUAL, ");
		query.append("MIME, ");
		query.append("NOMBRE, ");
		query.append("DESCRIPCION_ARCHIVO, ");
		query.append("TIPO, ");
		query.append("CONTENIDO) ");
		query.append("VALUES( ?, ?, ?, ?, ?, ?, ? )");
		log.debug("query=" + query);

		PreparedStatement ps = null;
		try {
			getConnection().setAutoCommit(false);
			ps = getConnection().prepareStatement(query.toString());
			ps.setInt(1, 0);
			ps.setInt(2, rol);
			ps.setString(3, archivo.getMimeType(archivo.getNombre()));
			ps.setString(4, archivo.getNombre());
			ps.setString(5, archivo.getDescripcionArchivo());
			ps.setString(6, archivo.getFileType(archivo.getNombre()));
			ps.setBlob(7, archivo.getIs());
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

		return new Mensaje(1, "No es posible guradar el Documento.");

	}

}