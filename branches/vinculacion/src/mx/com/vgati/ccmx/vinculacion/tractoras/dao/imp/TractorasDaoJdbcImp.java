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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mx.com.vgati.ccmx.vinculacion.tractoras.dao.TractorasDao;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;
import mx.com.vgati.framework.util.Null;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class TractorasDaoJdbcImp extends VinculacionBaseJdbcDao implements
		TractorasDao {

	@Override
	public List<Requerimientos> getRequerimientos(String id)
			throws JdbcDaoException {
		log.debug("getRequerimientos()");

		List<Requerimientos> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_REQUERIMIENTO, ");
		query.append("ID_TRACTORA, ");
		query.append("PRODUCTO, ");
		query.append("DESCRIPCION, ");
		query.append("FECHA_SUMINISTRO, ");
		query.append("FECHA_EXPIRA ");
		query.append("FROM  REQUERIMIENTOS ");
		query.append("WHERE ID_TRACTORA = ? ");
		query.append("ORDER BY ID_REQUERIMIENTO DESC ");
		log.debug("query=" + query);
		log.debug(id);

		Object[] o = { id };
		result = (List<Requerimientos>) getJdbcTemplate().query(
				query.toString(), o, new RequerimientosRowMapper());

		log.debug("result=" + result);
		return result;
	}

	public class RequerimientosRowMapper implements RowMapper<Requerimientos> {

		@Override
		public Requerimientos mapRow(ResultSet rs, int ln) throws SQLException {
			RequerimientosResultSetExtractor extractor = new RequerimientosResultSetExtractor();
			return (Requerimientos) extractor.extractData(rs);
		}

	}

	public class RequerimientosResultSetExtractor implements
			ResultSetExtractor<Requerimientos> {

		@Override
		public Requerimientos extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Requerimientos requerimientos = new Requerimientos();
			requerimientos.setIdRequerimiento(rs.getInt("ID_REQUERIMIENTO"));
			requerimientos.setIdTractora(rs.getInt("ID_TRACTORA"));
			requerimientos.setProducto(rs.getString("PRODUCTO"));
			requerimientos.setDescripcion(rs.getString("DESCRIPCION"));
			requerimientos.setFechaSuministro(rs.getDate("FECHA_SUMINISTRO"));
			requerimientos.setFechaExpira(rs.getDate("FECHA_EXPIRA"));
			return requerimientos;
		}

	}

	@Override
	public Requerimientos getRequerimiento(String id) throws JdbcDaoException {
		log.debug("getRequerimiento()");

		Requerimientos result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_REQUERIMIENTO, ");
		query.append("ID_TRACTORA, ");
		query.append("PRODUCTO, ");
		query.append("TIPO_PRODUCTO, ");
		query.append("DESCRIPCION, ");
		query.append("FECHA_SUMINISTRO, ");
		query.append("B_INDEFINIDO, ");
		query.append("B_VARIAS_FECHAS, ");
		query.append("B_CONTINUO_F_SUMINISTRO, ");
		query.append("LUGAR_SUMINISTRO, ");
		query.append("B_CONTADO, ");
		query.append("B_CREDITO, ");
		query.append("B_QUINCE, ");
		query.append("B_TREINTA, ");
		query.append("B_SESENTA, ");
		query.append("B_NOVENTA, ");
		query.append("B_OTRO, ");
		query.append("OTRAS_CONDICIONES, ");
		query.append("REQUISITOS_ADICIONALES, ");
		query.append("FECHA_EXPIRA, ");
		query.append("B_CONTINUO_F_EXP ");
		query.append("FROM  REQUERIMIENTOS ");
		query.append("WHERE ID_REQUERIMIENTO = ? ");
		log.debug("query=" + query);
		log.debug(id);

		Object[] o = { id };
		result = (Requerimientos) getJdbcTemplate().queryForObject(
				query.toString(), o, new RequerimientoRowMapper());

		log.debug("result=" + result);
		return result;
	}

	public class RequerimientoRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			Requerimientos requerimientos = new Requerimientos();
			requerimientos.setIdRequerimiento(rs.getInt("ID_REQUERIMIENTO"));
			requerimientos.setIdTractora(rs.getInt("ID_TRACTORA"));
			requerimientos.setProducto(rs.getString("PRODUCTO"));
			requerimientos.setTipoProducto(rs.getString("TIPO_PRODUCTO"));
			requerimientos.setDescripcion(rs.getString("DESCRIPCION"));
			requerimientos.setFechaSuministro(rs.getDate("FECHA_SUMINISTRO"));
			requerimientos.setbIndefinido(Null.free(
					rs.getString("B_INDEFINIDO")).equals("V") ? true : false);
			requerimientos
					.setbVariasFechas(Null
							.free(rs.getString("B_VARIAS_FECHAS")).equals("V") ? true
							: false);
			requerimientos.setbContinuoSuministro(Null.free(
					rs.getString("B_CONTINUO_F_SUMINISTRO")).equals("V") ? true
					: false);
			requerimientos.setLugarSuministro(rs.getString("LUGAR_SUMINISTRO"));
			requerimientos.setbContado(Null.free(rs.getString("B_CONTADO"))
					.equals("V") ? true : false);
			requerimientos.setbCredito(Null.free(rs.getString("B_CREDITO"))
					.equals("V") ? true : false);
			requerimientos.setbQuince(Null.free(rs.getString("B_QUINCE"))
					.equals("V") ? true : false);
			requerimientos.setbTreinta(Null.free(rs.getString("B_TREINTA"))
					.equals("V") ? true : false);
			requerimientos.setbSesenta(Null.free(rs.getString("B_SESENTA"))
					.equals("V") ? true : false);
			requerimientos.setbNoventa(Null.free(rs.getString("B_NOVENTA"))
					.equals("V") ? true : false);
			requerimientos.setbOtro(Null.free(rs.getString("B_OTRO")).equals(
					"V") ? true : false);
			requerimientos.setOtrasCondiciones(rs
					.getString("OTRAS_CONDICIONES"));
			requerimientos.setRequisitosAdicionales(rs
					.getString("REQUISITOS_ADICIONALES"));
			requerimientos.setFechaExpira(rs.getDate("FECHA_EXPIRA"));
			requerimientos.setbContinuoExpira(Null.free(
					rs.getString("B_CONTINUO_F_EXP")).equals("V") ? true
					: false);
			return requerimientos;
		}

	}

	@Override
	public Mensaje insertRequerimiento(Requerimientos requerimientos)
			throws DaoException {
		log.debug("insertRequerimiento()");
		
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.REQUERIMIENTOS ( ");
		query.append("ID_TRACTORA, ");
		query.append("PRODUCTO, ");
		query.append("TIPO_PRODUCTO, ");
		query.append("DESCRIPCION, ");
		query.append("FECHA_SUMINISTRO, ");
		query.append("B_INDEFINIDO, ");
		query.append("B_VARIAS_FECHAS, ");
		query.append("B_CONTINUO_F_SUMINISTRO, ");
		query.append("LUGAR_SUMINISTRO, ");
		query.append("B_CONTADO, ");
		query.append("B_CREDITO, ");
		query.append("B_QUINCE, ");
		query.append("B_TREINTA, ");
		query.append("B_SESENTA, ");
		query.append("B_NOVENTA, ");
		query.append("B_OTRO, ");
		query.append("OTRAS_CONDICIONES, ");
		query.append("REQUISITOS_ADICIONALES, ");
		query.append("FECHA_EXPIRA, ");
		query.append("B_CONTINUO_F_EXP) ");
		query.append("VALUES( '");
		query.append("3"/*requerimientos.getIdTractora()*/);
		query.append("', '");
		query.append(requerimientos.getProducto());
		query.append("', '");
		query.append(requerimientos.getTipoProducto());
		query.append("', '");
		query.append(requerimientos.getDescripcion());
		query.append("', ");
		query.append(requerimientos.getFechaSuministro()==null?"null":"'" + requerimientos.getFechaSuministro().getYear()+"-"+requerimientos.getFechaSuministro().getMonth()+"-"+requerimientos.getFechaSuministro().getDay() + "'");
		query.append(", '");
		query.append(requerimientos.isbIndefinido() ? "V" : "F");
		query.append("', '");
		query.append(requerimientos.isbVariasFechas() ? "V" : "F");
		query.append("', '");
		query.append(requerimientos.isbContinuoSuministro() ? "V" : "F");
		query.append("', '");
		query.append(requerimientos.getLugarSuministro());
		query.append("', '");
		query.append(requerimientos.isbContado() ? "V" : "F");
		query.append("', '");
		query.append(requerimientos.isbCredito() ? "V" : "F");
		query.append("', '");
		query.append(requerimientos.isbQuince() ? "V" : "F");
		query.append("', '");
		query.append(requerimientos.isbTreinta() ? "V" : "F");
		query.append("', '");
		query.append(requerimientos.isbSesenta() ? "V" : "F");
		query.append("', '");
		query.append(requerimientos.isbNoventa() ? "V" : "F");
		query.append("', '");
		query.append(requerimientos.isbOtro() ? "V" : "F");
		query.append("', '");
		query.append(requerimientos.getOtrasCondiciones());
		query.append("', '");
		query.append(requerimientos.getRequisitosAdicionales());
		query.append("', ");
		query.append(requerimientos.getFechaExpira()==null?"null":"'"+ requerimientos.getFechaExpira().getYear()+"-"+requerimientos.getFechaExpira().getMonth()+"-"+requerimientos.getFechaExpira().getDay()+"'");
		query.append(", '");
		query.append(requerimientos.isbContinuoExpira() ? "V" : "F");
		query.append("' )");
		log.debug("query=" + query);

		getJdbcTemplate().update(query.toString());

		return null;
	}

	public class InsertaRequerimientoRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			return rs;
		}
	}

	@Override
	public Mensaje updateRequerimiento(Requerimientos requerimientos)
			throws JdbcDaoException {
		log.debug("updateRequerimiento()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.REQUERIMIENTOS SET ");
		query.append("ID_TRACTORA = '");
		query.append("3"/*requerimientos.getIdTractora()*/);
		query.append("', ");
		query.append("PRODUCTO = '");
		query.append(requerimientos.getProducto());
		query.append("', ");
		query.append("TIPO_PRODUCTO = '");
		query.append(requerimientos.getTipoProducto());
		query.append("', ");
		query.append("DESCRIPCION = '");
		query.append(requerimientos.getDescripcion());
		query.append("', ");
		query.append("FECHA_SUMINISTRO = ");
		query.append(requerimientos.getFechaSuministro()==null?"null":"'" + requerimientos.getFechaSuministro().getYear()+"-"+requerimientos.getFechaSuministro().getMonth()+"-"+requerimientos.getFechaSuministro().getDay() + "'");
		query.append(", ");
		query.append("B_INDEFINIDO = '");
		query.append(requerimientos.isbIndefinido() ? "V" : "F");
		query.append("', ");
		query.append("B_VARIAS_FECHAS = '");
		query.append(requerimientos.isbVariasFechas() ? "V" : "F");
		query.append("', ");
		query.append("B_CONTINUO_F_SUMINISTRO = '");
		query.append(requerimientos.isbContinuoSuministro() ? "V" : "F");
		query.append("', ");
		query.append("LUGAR_SUMINISTRO = '");
		query.append(requerimientos.getLugarSuministro());
		query.append("', ");
		query.append("B_CONTADO = '");
		query.append(requerimientos.isbContado() ? "V" : "F");
		query.append("', ");
		query.append("B_CREDITO = '");
		query.append(requerimientos.isbCredito() ? "V" : "F");
		query.append("', ");
		query.append("B_QUINCE = '");
		query.append(requerimientos.isbQuince() ? "V" : "F");
		query.append("', ");
		query.append("B_TREINTA = '");
		query.append(requerimientos.isbTreinta() ? "V" : "F");
		query.append("', ");
		query.append("B_SESENTA = '");
		query.append(requerimientos.isbSesenta() ? "V" : "F");
		query.append("', ");
		query.append("B_NOVENTA = '");
		query.append(requerimientos.isbNoventa() ? "V" : "F");
		query.append("', ");
		query.append("B_OTRO = '");
		query.append(requerimientos.isbOtro() ? "V" : "F");
		query.append("', ");
		query.append("OTRAS_CONDICIONES = '");
		query.append(requerimientos.getOtrasCondiciones());
		query.append("', ");
		query.append("REQUISITOS_ADICIONALES = '");
		query.append(requerimientos.getRequisitosAdicionales());
		query.append("', ");
		query.append("FECHA_EXPIRA = ");
		query.append(requerimientos.getFechaExpira()==null?"null":"'"+ requerimientos.getFechaExpira().getYear()+"-"+requerimientos.getFechaExpira().getMonth()+"-"+requerimientos.getFechaExpira().getDay()+"'");
		query.append(", ");
		query.append("B_CONTINUO_F_EXP = '");
		query.append(requerimientos.isbContinuoExpira() ? "V" : "F");
		query.append("' ");
		query.append("WHERE ID_REQUERIMIENTO = ");
		query.append(requerimientos.getIdRequerimiento());
		query.append(" ");
		log.debug("query=" + query);

		getJdbcTemplate().update(query.toString());
		return null;

	}

	public class ActualizaRequerimientoRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			return rs;
		}
	}

}