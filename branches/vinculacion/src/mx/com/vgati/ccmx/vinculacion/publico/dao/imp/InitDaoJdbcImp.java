/*
 * InitDaoJdbcImp.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.publico.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.dao.InitDao;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class InitDaoJdbcImp extends VinculacionBaseJdbcDao implements InitDao {

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

		Object[] o = { id };
		result = (Usuario) getJdbcTemplate().queryForObject(query.toString(),
				o, new UsuarioRowMapper());

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

}