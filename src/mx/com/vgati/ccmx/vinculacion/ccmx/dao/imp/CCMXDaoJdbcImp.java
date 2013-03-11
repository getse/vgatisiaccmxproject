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
import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.dto.Roles;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.util.ValidationUtils;

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
		query.append("ID_USUARIO_PADRE, ");
		query.append("ID_TRACTORA_PADRE, ");
		query.append("EMPRESA, ");
		query.append("NOMBRE_CONTACTO, ");
		query.append("APP_PATERNO, ");
		query.append("APP_MATERNO, ");
		query.append("CORREO_ELECTRONICO, ");
		query.append("PUESTO, ");
		query.append("TELEFONOS ");
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
			tractoras.setIdUsuarioPadre(rs.getInt("ID_USUARIO_PADRE"));
			tractoras.setIdTractoraPadre(rs.getInt("ID_TRACTORA_PADRE"));
			tractoras.setEmpresa(rs.getString("EMPRESA"));
			tractoras.setNombreContacto(rs.getString("NOMBRE_CONTACTO"));
			tractoras.setAppPaterno(rs.getString("APP_PATERNO"));
			tractoras.setAppMaterno(rs.getString("APP_MATERNO"));
			tractoras.setCorreoElectronico(rs.getString("CORREO_ELECTRONICO"));
			tractoras.setPuesto(rs.getString("PUESTO"));
			tractoras.setTelefonos(rs.getString("TELEFONOS"));
			return tractoras;
		}

	}

	@Override
	public Mensaje saveUsuarioTra(Tractoras tractoras) throws DaoException {

		log.debug("saveUsuarioTra()");

		ValidationUtils v = new ValidationUtils();
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.USUARIOS ( ");
		query.append("CVE_USUARIO, ");
		query.append("PASSWORD) ");
		query.append("VALUES( '");
		query.append(tractoras.getCorreoElectronico());
		query.append("', '");
		query.append(v.getPasswd());
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
		query.append("ID_USUARIO_PADRE, ");
		query.append("ID_TRACTORA_PADRE, ");
		query.append("EMPRESA, ");
		query.append("NOMBRE_CONTACTO, ");
		query.append("APP_PATERNO, ");
		query.append("APP_MATERNO, ");
		query.append("CORREO_ELECTRONICO, ");
		query.append("PUESTO, ");
		query.append("TELEFONOS) ");
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
		query.append("', '");
		query.append(tractoras.getPuesto());
		query.append("', '");
		query.append(tractoras.getTelefonos());
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
}
