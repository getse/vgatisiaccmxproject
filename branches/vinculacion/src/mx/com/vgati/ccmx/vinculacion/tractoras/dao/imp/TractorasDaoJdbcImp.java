/*
 * TractorasDaoJdbcImp.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.tractoras.dao.imp;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.tractoras.dao.TractorasDao;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;
import mx.com.vgati.framework.dto.Respuesta;





import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class TractorasDaoJdbcImp extends VinculacionBaseJdbcDao implements
		TractorasDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Requerimientos> getRequerimientos(String id)
			throws JdbcDaoException {
		log.debug("getRequerimientos()");
		
		List<Requerimientos> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_REQUERIMIENTO, ");
		query.append("ID_TRACTORA, ");
		query.append("REQUERIMIENTO, ");
		query.append("DESC_REQUERIMIENTO, ");
		query.append("FECHA_INICIO, ");
		query.append("FECHA_FIN ");
		query.append("FROM REQUERIMIENTOS ");
		query.append("WHERE ID_TRACTORA = ? ");
		query.append("ORDER BY ID_REQUERIMIENTO DESC ");
		log.debug("query=" + query);
		
		Object[] o = {id};
		result = (List<Requerimientos>)getJdbcTemplate().query(
				query.toString(), o, new RequerimientosRowMapper());
		
		log.debug("result=" + result);
		return result;
	}
	
	public class RequerimientosRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			RequerimientosResultSetExtractor extractor = new RequerimientosResultSetExtractor();
			return extractor.extractData(rs);
		}
		
	}
	
	public class RequerimientosResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Requerimientos requerimientos = new Requerimientos();
			Respuesta respuesta = new Respuesta();
//			respuesta.setIdRequerimiento(rs.getInt(""));
//			respuesta.setInformacion(rs.getString(""));
//			respuesta.setMensajeEnvio(rs.getString(""));
			requerimientos.setIdTractora(rs.getInt("ID_TRACTORA"));
			requerimientos.setIdRequerimiento(rs.getInt("ID_REQUERIMIENTO"));
			requerimientos.setRequerimiento(rs.getString("REQUERIMIENTO"));
			requerimientos.setDescRequerimiento(rs.getString("DESC_REQUERIMIENTO"));
			requerimientos.setFechaFin(rs.getDate("FECHA_INICIO"));
			requerimientos.setFechaInicio(rs.getDate("FECHA_FIN"));
			requerimientos.setRespuesta(respuesta);
			return requerimientos;
		}
		
	}

	@Override
	public Requerimientos getRequerimiento(String id) throws JdbcDaoException {
		log.debug("getRequerimiento()");
		return null;
	}

	@Override
	public Mensaje saveRequerimiento(Requerimientos requerimientos)
			throws JdbcDaoException {
		log.debug("saveRequerimiento()");
		return null;
	}

}