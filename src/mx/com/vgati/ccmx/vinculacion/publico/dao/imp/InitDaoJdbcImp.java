/*
 * InitDaoJdbcImp.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.publico.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.publico.dao.InitDao;
import mx.com.vgati.framework.dao.AbstractBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Usuario;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class InitDaoJdbcImp extends AbstractBaseJdbcDao implements InitDao {

	@Override
	public Usuario getUsuario(String id) throws JdbcDaoException {
		log.debug("getUsuario()");

		Usuario result = null;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("A.CVE_USUARIO, ");
		query.append("A.ID_USUARIO, ");
		query.append("(SELECT ");
		query.append("CVE_ROL FROM ");
		query.append("INFRA.REL_ROLES ");
		query.append("WHERE CVE_USUARIO = ");
		query.append("A.CVE_USUARIO) ROL ");
		query.append("FROM INFRA.USUARIOS A ");
		query.append("WHERE CVE_USUARIO = ? ");
		log.debug("query=" + query);
		log.debug(id);

		try {
			Object[] o = { id };
			result = (Usuario) getJdbcTemplate().queryForObject(
					query.toString(), o, new UsuarioRowMapper());
		} catch (EmptyResultDataAccessException e) {
			result = null;
		}
		log.debug("result=" + result);
		return result;
	}

	public class UsuarioRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			Usuario usuario = new Usuario();
			usuario.setId(rs.getString("CVE_USUARIO"));
			usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
			usuario.setRol(rs.getString("ROL"));
			return usuario;
		}

	}

	@Override
	public Usuario getCredenciales(int id) throws JdbcDaoException {
		log.debug("getCredenciales()");

		Usuario result = null;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("CVE_USUARIO, ");
		query.append("ID_USUARIO, ");
		query.append("PASSWORD ");
		query.append("FROM INFRA.USUARIOS ");
		query.append("WHERE ID_USUARIO = ? ");
		log.debug("query=" + query);
		log.debug(id);

		Object[] o = { id };
		result = (Usuario) getJdbcTemplate().queryForObject(query.toString(),
				o, new CredencialesRowMapper());

		log.debug("result=" + result);
		return result;
	}

	public class CredencialesRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			Usuario usuario = new Usuario();
			usuario.setId(rs.getString("CVE_USUARIO"));
			usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
			usuario.setCredenciales(rs.getString("PASSWORD"));
			return usuario;
		}

	}

	@Override
	public boolean validateUsuario(String cve, int id) throws DaoException {
		boolean result;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("PASSWORD ");
		query.append("FROM INFRA.USUARIOS ");
		query.append("WHERE ID_USUARIO = " + id);
		log.debug("query=" + query);

		result = ((String) getJdbcTemplate().queryForObject(query.toString(),
				new ValidateRowMapper())).equalsIgnoreCase(cve);

		log.debug("result=" + result);
		return result;
	}

	public class ValidateRowMapper implements RowMapper<String> {

		@Override
		public String mapRow(ResultSet rs, int ln) throws SQLException {
			return rs.getString("PASSWORD");
		}

	}

	@Override
	public Documento getArchivo(String id) throws JdbcDaoException {
		log.debug("getArchivo()");

		Documento result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_ARCHIVO, ");
		query.append("ID_REQUERIMIENTO, ");
		query.append("NOMBRE, ");
		query.append("CONTENIDO ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_MANUAL = ? ");
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
			doc.setIdReferencia(rs.getInt("ID_REQUERIMIENTO"));
			doc.setNombre(rs.getString("NOMBRE"));
			doc.setIs(rs.getBinaryStream("CONTENIDO"));

			return doc;
		}
	}

}