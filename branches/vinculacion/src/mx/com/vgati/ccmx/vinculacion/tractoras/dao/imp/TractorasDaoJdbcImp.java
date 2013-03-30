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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.dto.Roles;
import mx.com.vgati.ccmx.vinculacion.tractoras.dao.TractorasDao;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.CatScianCcmx;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Productos;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Telefonos;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Documento;
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
	public List<Requerimientos> getRequerimientos(int id)
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
		query.append("B_INDEFINIDO, ");
		query.append("B_VARIAS_FECHAS, ");
		query.append("B_CONTINUO_F_SUMINISTRO, ");
		query.append("FECHA_EXPIRA, ");
		query.append("B_CONTINUO_F_EXPIRA ");
		query.append("FROM INFRA.REQUERIMIENTOS ");
		query.append("WHERE ID_TRACTORA = ? ");
		query.append("ORDER BY ID_REQUERIMIENTO DESC ");
		log.debug("query=" + query);
		log.debug(id);

		try {
			Object[] o = { id };
			result = (List<Requerimientos>) getJdbcTemplate().query(
					query.toString(), o, new RequerimientosRowMapper());
		} catch (Exception e) {
			throw new JdbcDaoException(e);
		}

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
			requerimientos.setbIndefinido(rs.getBoolean("B_INDEFINIDO"));
			requerimientos.setbVariasFechas(rs.getBoolean("B_VARIAS_FECHAS"));
			requerimientos.setbContinuoSuministro(rs
					.getBoolean("B_CONTINUO_F_EXPIRA"));
			requerimientos.setFechaExpira(rs.getDate("FECHA_EXPIRA"));
			requerimientos.setbContinuoExpira(rs
					.getBoolean("B_CONTINUO_F_EXPIRA"));
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
		query.append("CVE_SCIAN, ");
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
		query.append("B_CONTINUO_F_EXPIRA, ");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 1, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 0 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO), '0') ");
		query.append("AS ID_DOC1,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 2, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 1 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO), '0') ");
		query.append("AS ID_DOC2,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 3, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 2 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO), '0') ");
		query.append("AS ID_DOC3,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 4, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 3 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO), '0') ");
		query.append("AS ID_DOC4,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 5, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 4 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO), '0') ");
		query.append("AS ID_DOC5,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 6, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 5 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO), '0') ");
		query.append("AS ID_DOC6,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 7, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 6 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO), '0') ");
		query.append("AS ID_DOC7,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 8, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 7 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO), '0') ");
		query.append("AS ID_DOC8,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 9, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 8 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO), '0') ");
		query.append("AS ID_DOC9,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 10, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 9 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO), '0') ");
		query.append("AS ID_DOC10,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 1, ");
		query.append("( SELECT NOMBRE FROM ");
		query.append("INFRA.ARCHIVOS WHERE ");
		query.append("ID_ARCHIVO = ( SELECT ");
		query.append("MIN(ID_ARCHIVO) + 0 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO)), NULL) ");
		query.append("AS NAME_DOC1,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 2, ");
		query.append("( SELECT NOMBRE FROM ");
		query.append("INFRA.ARCHIVOS WHERE ");
		query.append("ID_ARCHIVO = ( SELECT ");
		query.append("MIN(ID_ARCHIVO) + 1 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO)), NULL) ");
		query.append("AS NAME_DOC2,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 3, ");
		query.append("( SELECT NOMBRE FROM ");
		query.append("INFRA.ARCHIVOS WHERE ");
		query.append("ID_ARCHIVO = ( SELECT ");
		query.append("MIN(ID_ARCHIVO) + 2 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO)), NULL) ");
		query.append("AS NAME_DOC3,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 4, ");
		query.append("( SELECT NOMBRE FROM ");
		query.append("INFRA.ARCHIVOS WHERE ");
		query.append("ID_ARCHIVO = ( SELECT ");
		query.append("MIN(ID_ARCHIVO) + 3 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO)), NULL) ");
		query.append("AS NAME_DOC4,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 5, ");
		query.append("( SELECT NOMBRE FROM ");
		query.append("INFRA.ARCHIVOS WHERE ");
		query.append("ID_ARCHIVO = ( SELECT ");
		query.append("MIN(ID_ARCHIVO) + 4 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO)), NULL) ");
		query.append("AS NAME_DOC5,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 6, ");
		query.append("( SELECT NOMBRE FROM ");
		query.append("INFRA.ARCHIVOS WHERE ");
		query.append("ID_ARCHIVO = ( SELECT ");
		query.append("MIN(ID_ARCHIVO) + 5 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO)), NULL) ");
		query.append("AS NAME_DOC6,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 7, ");
		query.append("( SELECT NOMBRE FROM ");
		query.append("INFRA.ARCHIVOS WHERE ");
		query.append("ID_ARCHIVO = ( SELECT ");
		query.append("MIN(ID_ARCHIVO) + 6 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO)), NULL) ");
		query.append("AS NAME_DOC7,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 8, ");
		query.append("( SELECT NOMBRE FROM ");
		query.append("INFRA.ARCHIVOS WHERE ");
		query.append("ID_ARCHIVO = ( SELECT ");
		query.append("MIN(ID_ARCHIVO) + 7 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO)), NULL) ");
		query.append("AS NAME_DOC8,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 9, ");
		query.append("( SELECT NOMBRE FROM ");
		query.append("INFRA.ARCHIVOS WHERE ");
		query.append("ID_ARCHIVO = ( SELECT ");
		query.append("MIN(ID_ARCHIVO) + 8 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO)), NULL) ");
		query.append("AS NAME_DOC9,");
		query.append("CASEWHEN((SELECT ");
		query.append("COUNT(ID_ARCHIVO) ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO) >= 10, ");
		query.append("( SELECT NOMBRE FROM ");
		query.append("INFRA.ARCHIVOS WHERE ");
		query.append("ID_ARCHIVO = ( SELECT ");
		query.append("MIN(ID_ARCHIVO) + 9 ");
		query.append("FROM INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO ");
		query.append("= A.ID_REQUERIMIENTO)), NULL) ");
		query.append("AS NAME_DOC10 ");
		query.append("FROM INFRA.REQUERIMIENTOS A ");
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
			requerimientos.setCveScian(rs.getInt("CVE_SCIAN"));
			requerimientos.setDescripcion(rs.getString("DESCRIPCION"));
			requerimientos.setFechaSuministro(rs.getDate("FECHA_SUMINISTRO"));
			requerimientos.setbIndefinido(rs.getBoolean("B_INDEFINIDO"));
			requerimientos.setbVariasFechas(rs.getBoolean("B_VARIAS_FECHAS"));
			requerimientos.setbContinuoSuministro(rs
					.getBoolean("B_CONTINUO_F_SUMINISTRO"));
			requerimientos.setLugarSuministro(rs.getString("LUGAR_SUMINISTRO"));
			requerimientos.setbContado(rs.getBoolean("B_CONTADO"));
			requerimientos.setbCredito(rs.getBoolean("B_CREDITO"));
			requerimientos.setbQuince(rs.getBoolean("B_QUINCE"));
			requerimientos.setbTreinta(rs.getBoolean("B_TREINTA"));
			requerimientos.setbSesenta(rs.getBoolean("B_SESENTA"));
			requerimientos.setbNoventa(rs.getBoolean("B_NOVENTA"));
			requerimientos.setbOtro(rs.getBoolean("B_OTRO"));
			requerimientos.setOtrasCondiciones(rs
					.getString("OTRAS_CONDICIONES"));
			requerimientos.setRequisitosAdicionales(rs
					.getString("REQUISITOS_ADICIONALES"));
			requerimientos.setFechaExpira(rs.getDate("FECHA_EXPIRA"));
			requerimientos.setbContinuoExpira(rs
					.getBoolean("B_CONTINUO_F_EXPIRA"));
			requerimientos.setIdArchivo1(rs.getInt("ID_DOC1"));
			requerimientos.setArchivo1FileName(rs.getString("NAME_DOC1"));
			requerimientos.setIdArchivo2(rs.getInt("ID_DOC2"));
			requerimientos.setArchivo2FileName(rs.getString("NAME_DOC2"));
			requerimientos.setIdArchivo3(rs.getInt("ID_DOC3"));
			requerimientos.setArchivo3FileName(rs.getString("NAME_DOC3"));
			requerimientos.setIdArchivo4(rs.getInt("ID_DOC4"));
			requerimientos.setArchivo4FileName(rs.getString("NAME_DOC4"));
			requerimientos.setIdArchivo5(rs.getInt("ID_DOC5"));
			requerimientos.setArchivo5FileName(rs.getString("NAME_DOC5"));
			requerimientos.setIdArchivo6(rs.getInt("ID_DOC6"));
			requerimientos.setArchivo6FileName(rs.getString("NAME_DOC6"));
			requerimientos.setIdArchivo7(rs.getInt("ID_DOC7"));
			requerimientos.setArchivo7FileName(rs.getString("NAME_DOC7"));
			requerimientos.setIdArchivo8(rs.getInt("ID_DOC8"));
			requerimientos.setArchivo8FileName(rs.getString("NAME_DOC8"));
			requerimientos.setIdArchivo9(rs.getInt("ID_DOC9"));
			requerimientos.setArchivo9FileName(rs.getString("NAME_DOC9"));
			requerimientos.setIdArchivo10(rs.getInt("ID_DOC10"));
			requerimientos.setArchivo10FileName(rs.getString("NAME_DOC10"));

			return requerimientos;
		}

	}

	@SuppressWarnings("unchecked")
	public Requerimientos getIdRequerimiento() throws DaoException {
		log.debug("getIdRequerimiento()");

		Requerimientos result = null;
		StringBuffer query = new StringBuffer();

		query.append("SELECT MAX(ID_REQUERIMIENTO) AS MAX_REQUERIMIENTO ");
		query.append("FROM INFRA.REQUERIMIENTOS ");
		log.debug("query=" + query);

		result = (Requerimientos) getJdbcTemplate().queryForObject(
				query.toString(), new IdMaxRequerimientosRowMapper());

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class IdMaxRequerimientosRowMapper implements RowMapper {

		@Override
		public Requerimientos mapRow(ResultSet rs, int ln) throws SQLException {
			Requerimientos requerimientos = new Requerimientos();
			requerimientos.setIdRequerimiento(rs.getInt("MAX_REQUERIMIENTO"));
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
		query.append("CVE_SCIAN, ");
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
		query.append("B_CONTINUO_F_EXPIRA) ");
		query.append("VALUES( '");
		query.append(requerimientos.getIdTractora());
		query.append("', '");
		query.append(requerimientos.getProducto());
		query.append("', '");
		query.append(requerimientos.getTipoProducto());
		query.append("', '");
		query.append(requerimientos.getCveScian());
		query.append("', '");
		query.append(requerimientos.getDescripcion());
		query.append("', ");
		query.append(requerimientos.getFechaSuministro() == null ? "null" : "'"
				+ requerimientos.getFechaSuministro() + "'");
		query.append(", ");
		query.append(requerimientos.isbIndefinido());
		query.append(", ");
		query.append(requerimientos.isbVariasFechas());
		query.append(", ");
		query.append(requerimientos.isbContinuoSuministro());
		query.append(", '");
		query.append(requerimientos.getLugarSuministro());
		query.append("', ");
		query.append(requerimientos.isbContado());
		query.append(", ");
		query.append(requerimientos.isbCredito());
		query.append(", ");
		query.append(requerimientos.isbQuince());
		query.append(", ");
		query.append(requerimientos.isbTreinta());
		query.append(", ");
		query.append(requerimientos.isbSesenta());
		query.append(", ");
		query.append(requerimientos.isbNoventa());
		query.append(", ");
		query.append(requerimientos.isbOtro());
		query.append(", '");
		query.append(requerimientos.getOtrasCondiciones());
		query.append("', '");
		query.append(requerimientos.getRequisitosAdicionales());
		query.append("', ");
		query.append(requerimientos.getFechaExpira() == null ? "null" : "'"
				+ requerimientos.getFechaExpira() + "'");
		query.append(", ");
		query.append(requerimientos.isbContinuoExpira());
		query.append(" )");
		log.debug("query=" + query);

		try {

			Documento d = null;
			boolean result = true;

			getJdbcTemplate().update(query.toString());

			int idR = getIdRequerimiento().getIdRequerimiento();
			if (requerimientos.getArchivo1() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo1());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo1FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo2() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo2());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo2FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo3() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo3());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo3FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo4() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo4());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo4FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo5() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo5());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo5FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo6() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo6());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo6FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo7() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo7());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo7FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo8() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo8());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo8FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo9() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo9());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo9FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo10() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo10());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo10FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}

			if (result) {
				Mensaje m = new Mensaje();
				m.setRespuesta(0);
				m.setMensaje("El Requerimiento se dio de alta satisfactoriamente.");
				m.setId(String.valueOf(idR));
				return m;
			} else {
				return new Mensaje(1,
						"El Requerimiento se dio de alta con errores al guardar el o los documentos.");
			}
		} catch (Exception e) {
			log.fatal("ERROR al insertar el Requerimiento, " + e);
			return new Mensaje(1, "No es posible dar de alta el Requerimiento.");
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
		query.append(requerimientos.getIdTractora());
		query.append("', ");
		query.append("PRODUCTO = '");
		query.append(requerimientos.getProducto());
		query.append("', ");
		query.append("TIPO_PRODUCTO = '");
		query.append(requerimientos.getTipoProducto());
		query.append("', ");
		query.append("CVE_SCIAN = '");
		query.append(requerimientos.getCveScian());
		query.append("', ");
		query.append("DESCRIPCION = '");
		query.append(requerimientos.getDescripcion());
		query.append("', ");
		query.append("FECHA_SUMINISTRO = ");
		query.append(requerimientos.getFechaSuministro() == null ? "null" : "'"
				+ requerimientos.getFechaSuministro() + "'");
		query.append(", ");
		query.append("B_INDEFINIDO = ");
		query.append(requerimientos.isbIndefinido());
		query.append(", ");
		query.append("B_VARIAS_FECHAS = ");
		query.append(requerimientos.isbVariasFechas());
		query.append(", ");
		query.append("B_CONTINUO_F_SUMINISTRO = ");
		query.append(requerimientos.isbContinuoSuministro());
		query.append(", ");
		query.append("LUGAR_SUMINISTRO = '");
		query.append(requerimientos.getLugarSuministro());
		query.append("', ");
		query.append("B_CONTADO = ");
		query.append(requerimientos.isbContado());
		query.append(", ");
		query.append("B_CREDITO = ");
		query.append(requerimientos.isbCredito());
		query.append(", ");
		query.append("B_QUINCE = ");
		query.append(requerimientos.isbQuince());
		query.append(", ");
		query.append("B_TREINTA = ");
		query.append(requerimientos.isbTreinta());
		query.append(", ");
		query.append("B_SESENTA = ");
		query.append(requerimientos.isbSesenta());
		query.append(", ");
		query.append("B_NOVENTA = ");
		query.append(requerimientos.isbNoventa());
		query.append(", ");
		query.append("B_OTRO = ");
		query.append(requerimientos.isbOtro());
		query.append(", ");
		query.append("OTRAS_CONDICIONES = '");
		query.append(requerimientos.getOtrasCondiciones());
		query.append("', ");
		query.append("REQUISITOS_ADICIONALES = '");
		query.append(requerimientos.getRequisitosAdicionales());
		query.append("', ");
		query.append("FECHA_EXPIRA = ");
		query.append(requerimientos.getFechaExpira() == null ? "null" : "'"
				+ requerimientos.getFechaExpira() + "'");
		query.append(", ");
		query.append("B_CONTINUO_F_EXPIRA = ");
		query.append(requerimientos.isbContinuoExpira());
		query.append(" WHERE ID_REQUERIMIENTO = ");
		query.append(requerimientos.getIdRequerimiento());
		query.append(" ");
		log.debug("query=" + query);

		try {

			Documento d = null;
			boolean result = true;

			getJdbcTemplate().update(query.toString());

			int idR = requerimientos.getIdRequerimiento();
			if (requerimientos.getArchivo1() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo1());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo1FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo2() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo2());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo2FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo3() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo3());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo3FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo4() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo4());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo4FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo5() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo5());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo5FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo6() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo6());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo6FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo7() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo7());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo7FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo8() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo8());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo8FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo9() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo9());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo9FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo10() != null) {
				d = new Documento();
				d.setIs(requerimientos.getArchivo10());
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo10FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}

			if (result) {
				Mensaje m = new Mensaje();
				m.setRespuesta(0);
				m.setMensaje("El Requerimiento se actualizó satisfactoriamente.");
				m.setId(String.valueOf(idR));
				return m;
			} else {
				return new Mensaje(1,
						"El Requerimiento se actualizó con errores al guardar el o los documentos.");
			}

		} catch (Exception e) {
			log.fatal("ERROR al actualizar el Requerimiento, " + e);
			return new Mensaje(1, "No es posible actualizar el Requerimiento.");
		}

	}

	@Override
	public Mensaje deleteRequerimiento(Requerimientos requerimientos)
			throws DaoException {
		log.debug("deleteRequerimiento()");

		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM ");
		query.append("INFRA.REQUERIMIENTOS ");
		query.append("WHERE ID_REQUERIMIENTO = ");
		query.append(requerimientos.getIdRequerimiento());
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			deleteDocumentos(requerimientos);
			return new Mensaje(0,
					"El requerimiento se eliminó satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al eliminar el Requerimiento, " + e);
			return new Mensaje(1, "No es posible eliminar el Requerimiento.");
		}

	}

	@Override
	public Mensaje deleteDocumentos(Requerimientos requerimientos)
			throws DaoException {
		log.debug("deleteDocumentos()");

		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM ");
		query.append("INFRA.ARCHIVOS ");
		query.append("WHERE ID_REQUERIMIENTO = ");
		query.append(requerimientos.getIdRequerimiento());
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los Documentos se eliminaron satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al eliminar los Documentos, " + e);
			return new Mensaje(1, "No es posible eliminar los Documentos.");
		}

	}

	@Override
	public Mensaje insertDocumento(Documento documento) throws DaoException {
		log.debug("insertArchivo()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.ARCHIVOS( ");
		query.append("ID_REQUERIMIENTO, ");
		query.append("MIME, ");
		query.append("NOMBRE, ");
		query.append("TIPO, ");
		query.append("CONTENIDO) ");
		query.append("VALUES( ?, ?, ?, ?, ? )");
		log.debug("query=" + query);
		log.debug("documento: " + documento);

		PreparedStatement ps = null;
		try {
			getConnection().setAutoCommit(false);
			ps = getConnection().prepareStatement(query.toString());
			ps.setInt(1, documento.getIdReferencia());
			ps.setString(2, documento.getMimeType(documento.getNombre()));
			ps.setString(3, documento.getNombre());
			ps.setString(4, documento.getFileType(documento.getNombre()));
			ps.setBlob(5, documento.getIs());
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

	@Override
	public Mensaje updateDocumento(Documento documento, String idArchivo)
			throws JdbcDaoException {
		log.debug("updateDocumento()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.ARCHIVOS SET ");
		query.append("ID_REFERENCIA = ");
		query.append(documento.getIdReferencia());
		query.append(", NOMBRE = '");
		query.append(documento.getNombre());
		query.append("', TIPO = '");
		query.append(documento.getFileType(documento.getNombre()));
		query.append("', CONTENIDO = ");
		query.append(documento.getIs());
		query.append(" WHERE ID_ARCHIVO = ");
		query.append(idArchivo);
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El Documento se actualizó satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar el Documento, " + e);
			return new Mensaje(1, "No es posible actualizar el Documento.");
		}

	}

	@Override
	public Documento getArchivo(int id) throws JdbcDaoException {
		log.debug("getArchivo()");

		Documento result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_ARCHIVO, ");
		query.append("ID_REQUERIMIENTO, ");
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
			doc.setIdReferencia(rs.getInt("ID_REQUERIMIENTO"));
			doc.setNombre(rs.getString("NOMBRE"));
			doc.setIs(rs.getBinaryStream("CONTENIDO"));

			return doc;
		}

	}

	@Override
	public List<Productos> getProductos(String busqueda)
			throws JdbcDaoException {
		log.debug("getProductos()");

		List<Productos> result = null;
		String cadenaBusqueda = busqueda.toUpperCase().trim().replace('Á', 'A')
				.replace('É', 'E').replace('Í', 'I').replace('Ó', 'O')
				.replace('Ú', 'U');
		StringTokenizer st = new StringTokenizer(cadenaBusqueda, " ");
		List<String> l = new ArrayList<String>();
		while (st.hasMoreElements()) {
			l.add((String) st.nextElement());
		}
		StringBuffer query = new StringBuffer();
		query.append(" SELECT ID_INDUSTRIA AS CVE_CLASE,");
		query.append(" ACTIVIDAD AS DESC_CLASE, ");
		query.append(" SUBRAMO AS DESC_SUBRAMO, ");
		query.append(" RAMO AS DESC_RAMO, ");
		query.append(" SUBSECTOR AS DESC_SUBSECTOR, ");
		query.append(" SECTOR AS DESC_SECTOR, ");
		query.append(" SECTOR_SUBSECTOR, ");
		query.append(" OBSERVACION, ");
		query.append(" B_COMENTARIO, ");
		query.append(" USO_RESTRINGIDO ");
		query.append(" FROM INFRA.V_ACT_ECONOMICAS_SAT ");
		query.append(" WHERE (");
		for (String valor : l) {
			query.append("ACTIVIDAD LIKE '%".concat(valor)
					.concat("%' OR BUSQUEDA LIKE '%")
					.concat(valor.concat("%'  ")));
			if (l.indexOf(valor) != l.size() - 1)
				query.append(" OR ");
		}
		query.append(") ORDER BY DESC_CLASE ");
		log.debug("query=" + query);
		log.debug(busqueda);

		result = (List<Productos>) getJdbcTemplate().query(query.toString(),
				new ProductosRowMapper());

		log.debug("result.size=" + result.size());
		return result;
	}

	public class ProductosRowMapper implements RowMapper<Productos> {

		@Override
		public Productos mapRow(ResultSet rs, int ln) throws SQLException {
			ProductosResultSetExtractor extractor = new ProductosResultSetExtractor();
			return (Productos) extractor.extractData(rs);
		}

	}

	public class ProductosResultSetExtractor implements
			ResultSetExtractor<Productos> {

		@Override
		public Productos extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Productos productos = new Productos();
			productos.setIdClase(rs.getInt("CVE_CLASE"));
			productos.setClase(rs.getString("DESC_CLASE"));
			productos.setSubRama(rs.getString("DESC_SUBRAMO"));
			productos.setRama(rs.getString("DESC_RAMO"));
			productos.setSubsectorEconomico(rs.getString("DESC_SUBSECTOR"));
			productos.setSectorEconomico(rs.getString("DESC_SECTOR"));
			productos.setSectorSubsector(rs.getString("SECTOR_SUBSECTOR"));
			productos.setObservacion(rs.getString("OBSERVACION"));
			productos.setTieneComentario(rs.getString("B_COMENTARIO"));
			productos.setUsoRestringido(rs.getString("USO_RESTRINGIDO"));
			return productos;
		}

	}

	@Override
	public List<CatScianCcmx> getCatProductos(String cve_scian)
			throws JdbcDaoException {
		log.debug("getCatProductos()");

		List<CatScianCcmx> result = null;
		StringBuffer query = new StringBuffer();
		query.append(" SELECT CVE_SCIAN,");
		query.append(" DESC_SCIAN, ");
		query.append(" CVE_NIVEL ");
		query.append(" FROM INFRA.CAT_SCIAN_CCMX ");
		query.append(cve_scian == null ? "ORDER BY CVE_SCIAN, DESC_SCIAN, CVE_NIVEL "
				: "WHERE CVE_SCIAN LIKE '".concat(cve_scian)
						.concat("%' AND LENGTH(CVE_SCIAN) = ".concat(cve_scian
								.length() == 1 ? "2"
								: cve_scian.length() == 2 ? "3" : cve_scian
										.length() == 3 ? "5" : "0")));
		log.debug("query=" + query);

		result = (List<CatScianCcmx>) getJdbcTemplate().query(query.toString(),
				new CatProductosRowMapper());

		log.debug("result.size=" + result.size());
		return result;
	}

	public class CatProductosRowMapper implements RowMapper<CatScianCcmx> {

		@Override
		public CatScianCcmx mapRow(ResultSet rs, int ln) throws SQLException {
			CatProductosResultSetExtractor extractor = new CatProductosResultSetExtractor();
			return (CatScianCcmx) extractor.extractData(rs);
		}

	}

	public class CatProductosResultSetExtractor implements
			ResultSetExtractor<CatScianCcmx> {

		@Override
		public CatScianCcmx extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			CatScianCcmx cat = new CatScianCcmx();
			cat.setCveScian(rs.getString("CVE_SCIAN"));
			cat.setDescScian(rs.getString("DESC_SCIAN"));
			cat.setCveNivel(rs.getInt("CVE_NIVEL"));
			return cat;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tractoras> getCompradores(int id) throws DaoException {
		log.debug("getTractoras()");

		List<Tractoras> result = null;
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
		query.append("WHERE ID_TRACTORA_PADRE = ? ");
		query.append("ORDER BY ID_USUARIO DESC ");
		log.debug("query=" + query);
		log.debug(id);

		log.debug(id);

		Object[] o = { id };
		result = (List<Tractoras>) getJdbcTemplate().query(query.toString(), o,
				new CompradoresRowMapper());

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class CompradoresRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			CompradoresResultSetExtractor extractor = new CompradoresResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class CompradoresResultSetExtractor implements ResultSetExtractor {

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
	public Mensaje saveUsuarioComp(Tractoras tractoras) throws DaoException {

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
					"No es posible dar de alta al Comprador, revise que el Usuario no exista.");
		}

	}

	@Override
	public Mensaje saveRolComp(Tractoras tractoras) throws DaoException {

		log.debug("saveRolComp()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.REL_ROLES ( ");
		query.append("CVE_ROL, ");
		query.append("CVE_USUARIO) ");
		query.append("VALUES( '");
		query.append(Roles.Comprador.name());
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

	public Mensaje saveCompradores(Tractoras tractoras) throws DaoException {

		log.debug("saveCompradores()");

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
		query.append(tractoras.getIdTractoraPadre());
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
			if (tractoras.getTelefonos() != null) {
				int id = getIdTractora().getIdUsuario();
				deleteTelefonos(id, 0);
				Iterator<Telefonos> i = tractoras.getTelefonos().iterator();
				Telefonos tel = null;
				while (i.hasNext()) {
					if (tel != null && !Null.free(tel.getTelefono()).isEmpty()) {
						saveTelefonos(id, tel.getTelefono());
					}
				}
			}
			return new Mensaje(
					0,
					"El Comprador se dio de alta satisfactoriamente. En breve recibirá un correo electrónico el nuevo Comprador con la información requerida y la liga para acceder al sistema.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar el Comprador, " + e);
			return new Mensaje(1, "No es posible dar de alta al Comprador.");
		}

	}

	public List<Telefonos> getTelefonos(int id) throws JdbcDaoException {
		log.debug("getTelefonos()");

		List<Telefonos> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_TELEFONO, ");
		query.append("ID_USUARIO, ");
		query.append("TELEFONO ");
		query.append("FROM INFRA.TELEFONOS ");
		query.append("WHERE ID_USUARIO = " + id);
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

	public Mensaje deleteTelefonos(int idUsuario, int idTelefono)
			throws DaoException {

		log.debug("deleteTelefonos()");

		StringBuffer query = new StringBuffer();
		query.append("DELETE INFRA.TELEFONOS ");
		if (idUsuario != 0) {
			query.append("WHERE ID_USUARIO =  ");
			query.append(idUsuario);
		} else {
			query.append("WHERE ID_TELEFONO =  ");
			query.append(idTelefono);
		}
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los Telefonos se eliminaron satisfactoriamente");
		} catch (Exception e) {
			log.fatal("ERROR al eiminar Telefonos, " + e);
			return new Mensaje(1, "No es posible eliminar Telefonos.");
		}

	}

	public Mensaje saveTelefonos(int id, String telefono) throws DaoException {

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
			return new Mensaje(0,
					"El Telefono se dio de alta satisfactoriamente");
		} catch (Exception e) {
			log.fatal("ERROR al insertar el Telefono, " + e);
			return new Mensaje(1, "No es posible dar de alta el Telefono.");
		}

	}

	@Override
	public Tractoras getTractora(int id) throws JdbcDaoException {
		log.debug("getTractora()");

		Tractoras result = null;
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
		query.append("FROM INFRA.TRACTORAS ");
		query.append("WHERE ID_USUARIO = ? ");
		log.debug("query=" + query);
		log.debug(id);

		Object[] o = { id };
		result = (Tractoras) getJdbcTemplate().queryForObject(query.toString(),
				o, new TractoraRowMapper());
		List<Telefonos> l = getTelefonos(id);
		result.setTelefonos(l);

		log.debug("result=" + result);
		return result;
	}

	public class TractoraRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
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
	public Mensaje updateTractora(Tractoras tractoras) throws JdbcDaoException {
		log.debug("updateTractora()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.TRACTORAS SET ");
		query.append("EMPRESA = '");
		query.append(tractoras.getEmpresa());
		query.append("', ");
		query.append("NOMBRE_CONTACTO = '");
		query.append(tractoras.getNombreContacto());
		query.append("', ");
		query.append("APP_PATERNO = '");
		query.append(tractoras.getAppPaterno());
		query.append("', ");
		query.append("APP_MATERNO = '");
		query.append(tractoras.getAppMaterno());
		query.append("', ");
		query.append("CORREO_ELECTRONICO = '");
		query.append(tractoras.getCorreoElectronico());
		query.append("', ");
		query.append("PUESTO = '");
		query.append(tractoras.getPuesto());
		query.append("'");
		query.append(" WHERE ID_USUARIO = ");
		query.append(tractoras.getIdUsuario());
		query.append(" ");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());

			if (tractoras.getTelefonos() != null) {
				int id = tractoras.getIdUsuario();
				deleteTelefonos(id, 0);
				Iterator<Telefonos> i = tractoras.getTelefonos().iterator();
				Telefonos tel = null;
				while (i.hasNext()) {
					tel = i.next();
					if (tel != null && !Null.free(tel.getTelefono()).isEmpty()) {
						saveTelefonos(id, tel.getTelefono());
					}
				}
			}
			return new Mensaje(0,
					"Los datos de la Tractora se actualizaron satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar los datos de la Tractora, " + e);
			return new Mensaje(1,
					"No es posible actualizar los datos de la Tractora, intentelo más tarde.");
		}

	}

	@SuppressWarnings("unchecked")
	public Tractoras getIdTractora() throws DaoException {
		log.debug("getIdTractora()");

		Tractoras result = null;
		StringBuffer query = new StringBuffer();

		query.append("SELECT MAX(ID_USUARIO) AS MAX_TRACTORA ");
		query.append("FROM INFRA.TRACTORAS ");
		log.debug("query=" + query);

		result = (Tractoras) getJdbcTemplate().queryForObject(query.toString(),
				new IdMaxTractorasRowMapper());

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class IdMaxTractorasRowMapper implements RowMapper {

		@Override
		public Tractoras mapRow(ResultSet rs, int ln) throws SQLException {
			Tractoras tractoras = new Tractoras();
			tractoras.setIdUsuario(rs.getInt("MAX_TRACTORA"));
			return tractoras;
		}

	}

	public Mensaje insertDomicilios(Domicilios domicilios) throws DaoException {
		log.debug("insertDomicilios()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.DOMICILIOS (");
		query.append("CALLE, ");
		query.append("NUM_EXT, ");
		query.append("NUM_INT, ");
		query.append("PISO, ");
		query.append("COLONIA, ");
		query.append("DELEGACION, ");
		query.append("ESTADO, ");
		query.append("CODIGO_POSTAL) ");
		query.append("VALUES ('");
		query.append(domicilios.getCalle());
		query.append("', '");
		query.append(domicilios.getNumExt());
		query.append("', '");
		query.append(domicilios.getNumInt());
		query.append("', '");
		query.append(domicilios.getPiso());
		query.append("', '");
		query.append(domicilios.getColonia());
		query.append("', '");
		query.append(domicilios.getDelegacion());
		query.append("', '");
		query.append(domicilios.getEstado());
		query.append("', '");
		query.append(domicilios.getCodigoPostal());
		query.append("') ");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			Mensaje m = new Mensaje();
			m.setRespuesta(0);
			m.setMensaje("Los datos de Domicilio han sido registrados exitosamente.");
			m.setId(String.valueOf(getIdDomicilio().getIdDomicilio()));
			return m;
		} catch (Exception e) {
			log.fatal("ERROR al insertar los datos de Domicilio, " + e);
			return new Mensaje(1,
					"No es posible registrar los datos de domicilio.");
		}

	}

	@SuppressWarnings("unchecked")
	public Domicilios getIdDomicilio() throws DaoException {
		log.debug("getIdDomicilio()");

		Domicilios result = null;
		StringBuffer query = new StringBuffer();

		query.append("SELECT MAX(ID_DOMICILIO) AS MAX_DOMICILIO ");
		query.append("FROM INFRA.DOMICILIOS ");
		log.debug("query=" + query);

		result = (Domicilios) getJdbcTemplate().queryForObject(
				query.toString(), new IdMaxDomiciliosRowMapper());

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class IdMaxDomiciliosRowMapper implements RowMapper {

		@Override
		public Domicilios mapRow(ResultSet rs, int ln) throws SQLException {
			Domicilios domicilios = new Domicilios();
			domicilios.setIdDomicilio(rs.getInt("MAX_DOMICILIO"));
			return domicilios;
		}

	}

	@SuppressWarnings("unchecked")
	public String getIdDomicilio(int id) throws DaoException {
		log.debug("getIdDomicilio()");

		String result;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("ID_DOMICILIO ");
		query.append("FROM INFRA.REL_DOMICILIOS_USUARIO ");
		query.append("WHERE ID_USUARIO = " + id);
		log.debug("query=" + query);

		try {
			result = (String) getJdbcTemplate().queryForObject(
					query.toString(), new IdDomicilioRowMapper());
		} catch (Exception e) {
			result = "0";
		}

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class IdDomicilioRowMapper implements RowMapper {

		@Override
		public String mapRow(ResultSet rs, int ln) throws SQLException {
			return rs.getString("ID_DOMICILIO");
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Domicilios getDomicilios(int id) throws DaoException {
		log.debug("getDomicilio()");

		Domicilios result = null;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("ID_DOMICILIO,");
		query.append("CALLE, ");
		query.append("NUM_EXT, ");
		query.append("NUM_INT, ");
		query.append("PISO, ");
		query.append("COLONIA, ");
		query.append("DELEGACION, ");
		query.append("ESTADO, ");
		query.append("CODIGO_POSTAL ");
		query.append("FROM INFRA.DOMICILIOS ");
		query.append("WHERE ID_DOMICILIO = " + id);
		log.debug("query=" + query);

		if (id == 0)
			return null;
		result = (Domicilios) getJdbcTemplate().queryForObject(
				query.toString(), new IdDomiciliosRowMapper());

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class IdDomiciliosRowMapper implements RowMapper {

		@Override
		public Domicilios mapRow(ResultSet rs, int ln) throws SQLException {
			Domicilios domicilios = new Domicilios();
			domicilios.setIdDomicilio(rs.getInt("ID_DOMICILIO"));
			domicilios.setCalle(rs.getString("CALLE"));
			domicilios.setNumExt(rs.getString("NUM_EXT"));
			domicilios.setNumInt(rs.getString("NUM_INT"));
			domicilios.setPiso(rs.getString("PISO"));
			domicilios.setColonia(rs.getString("COLONIA"));
			domicilios.setDelegacion(rs.getString("DELEGACION"));
			domicilios.setEstado(rs.getString("ESTADO"));
			domicilios.setCodigoPostal(rs.getString("CODIGO_POSTAL"));

			return domicilios;
		}

	}

	@Override
	public Mensaje updateDomicilios(Domicilios domicilios)
			throws JdbcDaoException {
		log.debug("updateDomicilio()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.DOMICILIOS SET ");
		query.append("CALLE = '");
		query.append(domicilios.getCalle());
		query.append("', ");
		query.append("NUM_EXT = '");
		query.append(domicilios.getNumExt());
		query.append("', ");
		query.append("NUM_INT = '");
		query.append(domicilios.getNumInt());
		query.append("', ");
		query.append("PISO = '");
		query.append(domicilios.getPiso());
		query.append("', ");
		query.append("COLONIA = '");
		query.append(domicilios.getColonia());
		query.append("', ");
		query.append("DELEGACION = '");
		query.append(domicilios.getDelegacion());
		query.append("', ");
		query.append("ESTADO = '");
		query.append(domicilios.getEstado());
		query.append("', ");
		query.append("CODIGO_POSTAL = '");
		query.append(domicilios.getCodigoPostal());
		query.append("'");
		query.append(" WHERE ID_DOMICILIO = ");
		query.append(domicilios.getIdDomicilio());
		query.append(" ");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos de la Tractora se actualizaron satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar los datos de la Tractora, " + e);
			return new Mensaje(1,
					"No es posible actualizar los datos de la Tractora, intentelo más tarde.");
		}

	}

	public Mensaje insertRelDomicilios(Domicilios domicilios,
			Tractoras tractoras) throws DaoException {

		log.debug("insertDomicilio()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.REL_DOMICILIOS_USUARIO (");
		query.append("ID_USUARIO, ");
		query.append("ID_DOMICILIO) ");
		query.append("VALUES ('");
		query.append(tractoras.getIdUsuario());
		query.append("', '");
		query.append(domicilios.getIdDomicilio());
		query.append("')");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos han sido registrados exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar los datos RER_DOMICILIOS, " + e);
			return new Mensaje(1, "No es posible registrar los datos.");
		}

	}

}