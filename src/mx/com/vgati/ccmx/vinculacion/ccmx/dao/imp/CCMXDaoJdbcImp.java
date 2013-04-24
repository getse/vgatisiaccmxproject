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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.dao.CCMXDao;
import mx.com.vgati.ccmx.vinculacion.ccmx.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.dto.Roles;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Contacto;
import mx.com.vgati.framework.dto.Mensaje;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class CCMXDaoJdbcImp extends VinculacionBaseJdbcDao implements CCMXDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Tractoras> getTractoras(int id) throws DaoException {
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
		query.append("ORDER BY ID_USUARIO DESC ");
		log.debug("query=" + query);
		log.debug(id);

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
	public Mensaje saveUsuarioTra(Tractoras tractoras) throws DaoException {

		log.debug("saveUsuarioTra()");

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
	public Mensaje saveRolTra(Tractoras tractoras) throws DaoException {

		log.debug("saveRolTra()");

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
	public Mensaje saveTractoras(Tractoras tractoras) throws DaoException {
		log.debug("insertTractora()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.TRACTORAS ( ");
		query.append("ID_USUARIO, ");
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
		query.append("', '");
		query.append(tractoras.getPuesto());
		query.append("' )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(
					0,
					"La Tractora se dio de alta satisfactoriamente. En breve recibirá un correo electrónico la nueva Tractora con la información requerida y la liga para acceder al sistema.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar la Tractora, " + e);
			return new Mensaje(1, "No es posible dar de alta la Tractora.");
		}

	}

	public class InsertaTractoraRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			return rs;
		}
	}	
	
	public List<PyMEs> getPyMes(int id) throws DaoException {
		log.debug("getPyMes()");

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("P.ID_USUARIO, ");
		query.append("P.ID_USUARIO_PADRE, ");
		query.append("P.CORREO_ELECTRONICO, ");
		query.append("P.NOMBRE_COMERCIAL, ");
		query.append("C.NOMBRE, ");
		query.append("C.APELLIDO_PATERNO, ");
		query.append("C.APELLIDO_MATERNO ");
		query.append("FROM INFRA.PYMES AS P ");
		query.append("JOIN INFRA.CONTACTOS AS C ");
		query.append("ON P.ID_USUARIO = C.ID_USUARIO ");
		query.append("ORDER BY ID_USUARIO DESC");
		log.debug("query=" + query);
		log.debug(id);

		@SuppressWarnings("unchecked")
		List<PyMEs> pymes = getJdbcTemplate().query(query.toString(),
				new PyMesRowMapper());
		return pymes;

	}

	@SuppressWarnings("rawtypes")
	public class PyMesRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			PyMesResultSetExtractor extractor = new PyMesResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class PyMesResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			PyMEs pymes = new PyMEs();
			pymes.setIdUsuario(rs.getInt("ID_USUARIO"));
			pymes.setIdUsuarioPadre(rs.getInt("ID_USUARIO_PADRE"));
			pymes.setCorreoElectronico(rs.getString("CORREO_ELECTRONICO"));
			pymes.setNombreComercial(rs.getString("NOMBRE_COMERCIAL"));
			pymes.setNombreContacto1(rs.getString("NOMBRE"));
			pymes.setAppPaterno1(rs.getString("APELLIDO_PATERNO"));
			pymes.setAppMaterno1(rs.getString("APELLIDO_MATERNO"));
			return pymes;
		}
	}
	
	@Override
	public Mensaje saveUsuarioPyMes(PyMEs pyMes) throws DaoException {

		log.debug("saveUsuarioPyMes()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.USUARIOS ( ");
		query.append("CVE_USUARIO, ");
		query.append("PASSWORD) ");
		query.append("VALUES( '");
		query.append(pyMes.getCorreoElectronico());
		query.append("', '");
		query.append(pyMes.getPassword());
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
	public Mensaje saveRolPyMes(PyMEs pyMes) throws DaoException {

		log.debug("saveRolPyMes()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.REL_ROLES ( ");
		query.append("CVE_ROL, ");
		query.append("CVE_USUARIO) ");
		query.append("VALUES( '");
		query.append(Roles.PyME.name());
		query.append("', '");
		query.append(pyMes.getCorreoElectronico());
		query.append("' )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0, "El Rol PyME se dio de alta satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar el Rol PyME, " + e);
			return new Mensaje(1, "No es posible dar de alta el Rol PyME.");
		}

	}

	@Override
	public Mensaje savePyMes(PyMEs pyMes) throws DaoException {

		log.debug("savePyMes()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.PYMES ( ");
		query.append("ID_USUARIO, ");
		query.append("ID_USUARIO_PADRE, ");
		query.append("PERSONALIDAD_JURIDICA, ");
		query.append("RFC, ");
		query.append("CORREO_ELECTRONICO, ");
		query.append("NOMBRE_COMERCIAL, ");
		query.append("NOMBRE_FISCAL, ");
		query.append("NUMERO_EMPLEADOS, ");
		query.append("MENSAJE_VENTAS, ");
		query.append("PAGINA_WEB, ");
		query.append("VENTAS_ANUALES, ");
		query.append("CVE_SCIAN, ");
		query.append("NOMBRE_CONTACTO, ");
		query.append("APP_PATERNO, ");
		query.append("APP_MATERNO, ");
		query.append("CORREO_ELECTRONICO_CONTACTO, ");
		query.append("TELEFONO_CONTACTO, ");
		query.append("INSTITUTO_CERTIFICADOR, ");
		query.append("B_DIPLOMADO_CCMX_UNO, ");
		query.append("B_DIPLOMADO_CCMX_DOS, ");
		query.append("B_DIPLOMADO_CCMX_TRES, ");
		query.append("B_DIPLOMADO_CCMX_CUATRO, ");
		query.append("B_RECIBE_REQUERIMIENTOS_COMPRA, ");
		query.append("CVE_SCIAN_REQUERIMIENTOS_COMPRA, ");
		query.append("CALIFICACION, ");
		query.append("B_SERVICIOS_CCMX_DIPLOMADOS, ");
		query.append("B_SERVICIOS_CCMX_CONSULTORIA) ");
		query.append("VALUES( '");
		query.append(pyMes.getIdUsuario());
		query.append("', '");
		query.append(pyMes.getIdUsuarioPadre());
		query.append("', '");
		query.append(pyMes.getPersonalidadJuridica());
		query.append("', '");
		query.append(pyMes.getRfc());
		query.append("', '");
		query.append(pyMes.getCorreoElectronico());
		query.append("', '");
		query.append(pyMes.getNombreComercial());
		query.append("', '");
		query.append(pyMes.getNombreFiscal());
		query.append("', '");
		query.append(pyMes.getNumeroEmpleados());
		query.append("', '");
		query.append(pyMes.getMensajeVentas());
		query.append("', '");
		query.append(pyMes.getPaginaWeb());
		query.append("', '");
		query.append(pyMes.getVentasAnuales());
		query.append("', '");
		query.append(pyMes.getCveScian());
		query.append("', '");
		query.append(pyMes.getInstitutoCertificador());
		query.append("', '");
		query.append(pyMes.isbDiplomadoCcmxUno());
		query.append("', '");
		query.append(pyMes.isbDiplomadoCcmxDos());
		query.append("', '");
		query.append(pyMes.isbDiplomadoCcmxTres());
		query.append("', '");
		query.append(pyMes.isbDiplomadoCcmxCuatro());
		query.append("', '");
		query.append(pyMes.isbRecibeRequerimientosCompra());
		query.append("', '");
		query.append(pyMes.getCveScianRequerimientosCompra());
		query.append("', '");
		query.append(pyMes.getCalificacion());
		query.append("', '");
		query.append(pyMes.isbServiciosCcmxDiplomados());
		query.append("', '");
		query.append(pyMes.isbServiciosCcmxConsultoria());
		query.append("' )");
		log.debug("query=" + query);

		try {

			Contacto co = null;
			boolean result = true;

			getJdbcTemplate().update(query.toString());

			int id = pyMes.getIdUsuario();
			/*Inserta Contacto*/
			if( pyMes.getNombreContacto1() != null ){
				log.debug("Insertando el Contacto 1 = " + pyMes.getNombreContacto1());
				co = new Contacto();
				co.setIdUsuario(id);
				co.setNombre(pyMes.getNombreContacto1());
				co.setApellidoPaterno(pyMes.getAppPaterno1());
				co.setApellidoMaterno(pyMes.getAppMaterno1());
				result = saveContacto(co).getRespuesta() == 0;
			}
			if (result) {
				Mensaje m = new Mensaje();
				m.setRespuesta(0);
				m.setMensaje("El Usuario PyME se dio de alta satisfactoriamente. En breve recibirá un correo electrónico el nuevo Usuario PyME con la información requerida y la liga para acceder al sistema.");
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

	public class InsertaPyMeRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			return rs;
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
		query.append("', '");
		query.append(contacto.getTelefono());
		query.append("')");
		log.debug("query=" + query);
		
		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos del Contacto han sido registrados exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar el contacto, " + e);
			return new Mensaje(1, "No es posible registrar el contacto, intentelo más tarde.");
		}
	}
}