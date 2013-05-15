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
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.dto.Roles;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Contacto;
import mx.com.vgati.framework.dto.Mensaje;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
					"La Tractora se dio de alta satisfactoriamente. En breve recibirá un correo electrónico con la información requerida y la liga para acceder al sistema.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar la Tractora, " + e);
			return new Mensaje(1, "No es posible dar de alta la Tractora.");
		}

	}

	@Override
	public Mensaje updateTractora(Tractoras tractoras, String credenciales)
			throws DaoException {
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
				query.append(Roles.Tractora.name());
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
					"La Tractora se acualizó satisfactoriamente. En breve la Tractora recibirá un correo electrónico con la liga para acceder al sistema.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar la Tractora, " + e);
			return new Mensaje(1, "No es posible actualizar la Tractora.");
		}

	}

	public List<PyMEs> getPyMEs() throws DaoException {
		log.debug("getPyMEs()");
		StringBuffer query = new StringBuffer();
		query.append(" SELECT ");
		query.append(" P.ID_USUARIO, ");
		query.append(" P.ID_USUARIO_PADRE, ");
		query.append(" P.NOMBRE_COMERCIAL, ");
		query.append(" C.NOMBRE, ");
		query.append(" C.APELLIDO_PATERNO, ");
		query.append(" C.APELLIDO_MATERNO, ");
		query.append(" C.CORREO_ELECTRONICO ");
		query.append(" FROM INFRA.PYMES AS P ");
		query.append(" JOIN INFRA.CONTACTOS AS C ");
		query.append(" ON P.ID_USUARIO = C.ID_USUARIO ");
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
			pymes.setNombreContacto1(rs.getString("NOMBRE"));
			pymes.setAppPaterno1(rs.getString("APELLIDO_PATERNO"));
			pymes.setAppMaterno1(rs.getString("APELLIDO_MATERNO"));
			pymes.setCorreoElectronicoContacto1(rs
					.getString("CORREO_ELECTRONICO"));
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
		query.append(" ORDER BY ID_CONSULTORA DESC ");
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
		query.append("', '");
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
			query.append("' WHERE ID_USUARIO = ");
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
					"Los datos del Contacto han sido registrados exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar el contacto, " + e);
			return new Mensaje(1,
					"No es posible registrar el contacto, intentelo más tarde.");
		}
	}
}