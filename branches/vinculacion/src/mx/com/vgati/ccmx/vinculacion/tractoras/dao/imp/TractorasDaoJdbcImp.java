/*
 * TractorasDaoJdbcImp.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.tractoras.dao.imp;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import mx.com.vgati.ccmx.vinculacion.dto.Contacto;
import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.dto.Requerimientos;
import mx.com.vgati.ccmx.vinculacion.dto.Respuesta;
import mx.com.vgati.ccmx.vinculacion.dto.Roles;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.tractoras.dao.TractorasDao;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.CatIndicadoresTractora;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.CatScianCcmx;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Productos;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.RelPyMEsTractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Telefonos;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dao.AbstractBaseJdbcDao;
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
public class TractorasDaoJdbcImp extends AbstractBaseJdbcDao implements
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
		query.append("ORDER BY ID_REQUERIMIENTO ASC ");
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
					.getBoolean("B_CONTINUO_F_SUMINISTRO"));
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
		query.append("DETALLE_VARIAS_FECHAS, ");
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
		query.append("B_CONTINUO_F_EXPIRA ");
		query.append("FROM INFRA.REQUERIMIENTOS ");
		query.append("WHERE ID_REQUERIMIENTO = ? ");
		log.debug("query=" + query);
		log.debug(id);

		Object[] o = { id };
		result = (Requerimientos) getJdbcTemplate().queryForObject(
				query.toString(), o, new RequerimientoRowMapper());

		List<EstadosVenta> l = getLugarSuministro(id);
		result.setLugarSuministro(l);

		List<Documento> d = getDocumentos(id);
		try {
			result.setIdArchivo1(d.get(0).getIdArchivo());
			result.setArchivo1FileName(d.get(0).getNombre());
			result.setDescArchivo1(d.get(0).getDescripcionArchivo());
		} catch (Exception e) {
		}
		try {
			result.setIdArchivo2(d.get(1).getIdArchivo());
			result.setArchivo2FileName(d.get(1).getNombre());
			result.setDescArchivo2(d.get(1).getDescripcionArchivo());
		} catch (Exception e) {
		}
		try {
			result.setIdArchivo3(d.get(2).getIdArchivo());
			result.setArchivo3FileName(d.get(2).getNombre());
			result.setDescArchivo3(d.get(2).getDescripcionArchivo());
		} catch (Exception e) {
		}
		try {
			result.setIdArchivo4(d.get(3).getIdArchivo());
			result.setArchivo4FileName(d.get(3).getNombre());
			result.setDescArchivo4(d.get(3).getDescripcionArchivo());
		} catch (Exception e) {
		}
		try {
			result.setIdArchivo5(d.get(4).getIdArchivo());
			result.setArchivo5FileName(d.get(4).getNombre());
			result.setDescArchivo5(d.get(4).getDescripcionArchivo());
		} catch (Exception e) {
		}
		try {
			result.setIdArchivo6(d.get(5).getIdArchivo());
			result.setArchivo6FileName(d.get(5).getNombre());
			result.setDescArchivo6(d.get(5).getDescripcionArchivo());
		} catch (Exception e) {
		}
		try {
			result.setIdArchivo7(d.get(6).getIdArchivo());
			result.setArchivo7FileName(d.get(6).getNombre());
			result.setDescArchivo7(d.get(6).getDescripcionArchivo());
		} catch (Exception e) {
		}
		try {
			result.setIdArchivo8(d.get(7).getIdArchivo());
			result.setArchivo8FileName(d.get(7).getNombre());
			result.setDescArchivo8(d.get(7).getDescripcionArchivo());
		} catch (Exception e) {
		}
		try {
			result.setIdArchivo9(d.get(8).getIdArchivo());
			result.setArchivo9FileName(d.get(8).getNombre());
			result.setDescArchivo9(d.get(8).getDescripcionArchivo());
		} catch (Exception e) {
		}
		try {
			result.setIdArchivo10(d.get(9).getIdArchivo());
			result.setArchivo10FileName(d.get(9).getNombre());
			result.setDescArchivo10(d.get(9).getDescripcionArchivo());
		} catch (Exception e) {
		}

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
			requerimientos.setVariasFechas(rs
					.getString("DETALLE_VARIAS_FECHAS"));
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

			return requerimientos;
		}

	}

	@SuppressWarnings("unchecked")
	public Requerimientos getIdRequerimiento() throws JdbcDaoException {
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
			throws JdbcDaoException {
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
		query.append("DETALLE_VARIAS_FECHAS, ");
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
		query.append(requerimientos.getVariasFechas());
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
				d.setIs(new FileInputStream(requerimientos.getArchivo1()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo1FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo1());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo2() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo2()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo2FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo2());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo3() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo3()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo3FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo3());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo4() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo4()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo4FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo4());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo5() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo5()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo5FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo5());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo6() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo6()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo6FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo6());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo7() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo7()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo7FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo7());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo8() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo8()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo8FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo8());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo9() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo9()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo9FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo9());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo10() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo10()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo10FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo10());
				result = insertDocumento(d).getRespuesta() == 0;
			}

			deleteLugarSuministro(idR, 0);
			List<EstadosVenta> ev = requerimientos.getLugarSuministro();
			for (EstadosVenta edo : ev) {
				if (edo != null && !Null.free(edo.getEstadoVenta()).isEmpty()) {
					edo.setIdUsuario(requerimientos.getIdTractora());
					edo.setIdRequerimiento(idR);
					result = saveLugarSuministro(edo).getRespuesta() == 0;
				}
			}

			if (result) {
				Mensaje m = new Mensaje();
				m.setRespuesta(0);
				m.setMensaje("El Requerimiento se dio de alta satisfactoriamente.");
				m.setId(String.valueOf(idR));
				return m;
			} else {
				return new Mensaje(
						1,
						"El Requerimiento se dio de alta de forma incompleta, revise su informaci�n de ser necesario.");
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
		query.append("DETALLE_VARIAS_FECHAS = '");
		query.append(requerimientos.getVariasFechas());
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
				d.setIs(new FileInputStream(requerimientos.getArchivo1()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo1FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo1());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo2() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo2()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo2FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo2());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo3() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo3()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo3FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo3());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo4() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo4()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo4FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo4());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo5() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo5()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo5FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo5());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo6() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo6()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo6FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo6());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo7() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo7()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo7FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo7());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo8() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo8()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo8FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo8());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo9() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo9()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo9FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo9());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (requerimientos.getArchivo10() != null) {
				d = new Documento();
				d.setIs(new FileInputStream(requerimientos.getArchivo10()));
				d.setIdReferencia(idR);
				d.setNombre(requerimientos.getArchivo10FileName());
				d.setDescripcionArchivo(requerimientos.getDescArchivo10());
				result = insertDocumento(d).getRespuesta() == 0;
			}

			deleteLugarSuministro(idR, 0);
			List<EstadosVenta> ev = requerimientos.getLugarSuministro();
			for (EstadosVenta edo : ev) {
				if (edo != null && !Null.free(edo.getEstadoVenta()).isEmpty()) {
					edo.setIdUsuario(requerimientos.getIdTractora());
					edo.setIdRequerimiento(idR);
					result = saveLugarSuministro(edo).getRespuesta() == 0;
				}
			}

			if (result) {
				Mensaje m = new Mensaje();
				m.setRespuesta(0);
				m.setMensaje("El Requerimiento se actualiz� satisfactoriamente.");
				m.setId(String.valueOf(idR));
				return m;
			} else {
				return new Mensaje(
						1,
						"El Requerimiento se actualiz� de forma incorrecta, revise su informaci�n de ser necesario.");
			}

		} catch (Exception e) {
			log.fatal("ERROR al actualizar el Requerimiento, " + e);
			return new Mensaje(1, "No es posible actualizar el Requerimiento.");
		}

	}

	@Override
	public Mensaje deleteRequerimiento(Requerimientos requerimientos)
			throws JdbcDaoException {
		log.debug("deleteRequerimiento()");

		StringBuffer query = new StringBuffer();

		try {
			query.append("DELETE FROM ");
			query.append("INFRA.ESTADOS_VENTA ");
			query.append("WHERE ID_REQUERIMIENTO = ");
			query.append(requerimientos.getIdRequerimiento());
			log.debug("query=" + query);

			getJdbcTemplate().update(query.toString());

			query = new StringBuffer();
			query.append("DELETE FROM ");
			query.append("INFRA.REQUERIMIENTOS ");
			query.append("WHERE ID_REQUERIMIENTO = ");
			query.append(requerimientos.getIdRequerimiento());
			log.debug("query=" + query);

			getJdbcTemplate().update(query.toString());
			deleteDocumentos(requerimientos);
			return new Mensaje(0,
					"El requerimiento se elimin� satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al eliminar el Requerimiento, " + e);
			return new Mensaje(1, "No es posible eliminar el Requerimiento.");
		}

	}

	public List<EstadosVenta> getLugarSuministro(String idRequerimiento)
			throws JdbcDaoException {
		log.debug("getLugarSuministro()");

		List<EstadosVenta> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_ESTADO_VENTA, ");
		query.append("ID_USUARIO, ");
		query.append("ID_REQUERIMIENTO, ");
		query.append("ESTADO_VENTA, ");
		query.append("DESCRIPCION ");
		query.append("FROM INFRA.ESTADOS_VENTA ");
		query.append("WHERE ID_REQUERIMIENTO = " + idRequerimiento);
		query.append("ORDER BY ID_ESTADO_VENTA ");
		log.debug("query=" + query);

		try {
			result = (List<EstadosVenta>) getJdbcTemplate().query(
					query.toString(), new LugarSuministroRowMapper());
		} catch (EmptyResultDataAccessException erdae) {
			log.warn("No se obtubieron estados de lugar de suministro");
		} catch (Exception e) {
			throw new JdbcDaoException(e);
		}

		log.debug("result=" + result);
		return result;
	}

	public class LugarSuministroRowMapper implements RowMapper<EstadosVenta> {

		@Override
		public EstadosVenta mapRow(ResultSet rs, int ln) throws SQLException {
			LugarSuministroResultSetExtractor extractor = new LugarSuministroResultSetExtractor();
			return (EstadosVenta) extractor.extractData(rs);
		}

	}

	public class LugarSuministroResultSetExtractor implements
			ResultSetExtractor<EstadosVenta> {

		@Override
		public EstadosVenta extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			EstadosVenta edos = new EstadosVenta();
			edos.setIdEstadoVenta(rs.getInt("ID_ESTADO_VENTA"));
			edos.setIdUsuario(rs.getInt("ID_USUARIO"));
			edos.setIdRequerimiento(rs.getInt("ID_REQUERIMIENTO"));
			edos.setEstadoVenta(rs.getString("ESTADO_VENTA"));
			edos.setDescripcion(rs.getString("DESCRIPCION"));
			return edos;
		}

	}

	public Mensaje deleteLugarSuministro(int idRequerimiento, int idEstado)
			throws JdbcDaoException {

		log.debug("deleteEstadosVenta()");

		StringBuffer query = new StringBuffer();
		query.append("DELETE INFRA.ESTADOS_VENTA ");
		if (idRequerimiento != 0) {
			query.append("WHERE ID_REQUERIMIENTO =  ");
			query.append(idRequerimiento);
		} else {
			query.append("WHERE ID_ESTADO_VENTA =  ");
			query.append(idEstado);
		}
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los lugares de suministro se eliminaron satisfactoriamente");
		} catch (Exception e) {
			log.fatal("ERROR al eiminar el lugar de suministro, " + e);
			return new Mensaje(1,
					"No es posible eliminar el lugar de suministro.");
		}

	}

	public Mensaje saveLugarSuministro(EstadosVenta edo)
			throws JdbcDaoException {

		log.debug("saveLugarSuministro()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.ESTADOS_VENTA ( ");
		query.append("ID_USUARIO, ");
		query.append("ID_REQUERIMIENTO, ");
		query.append("ESTADO_VENTA, ");
		query.append("DESCRIPCION) ");
		query.append("VALUES( ");
		query.append(edo.getIdUsuario());
		query.append(", ");
		query.append(edo.getIdRequerimiento());
		query.append(", '");
		query.append(edo.getEstadoVenta());
		query.append("', ");
		query.append(Null.free(edo.getDescripcion()).trim().isEmpty() ? "null"
				: "'".concat(edo.getDescripcion()).concat("'"));
		query.append(" )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El lugar de suministro se dio de alta satisfactoriamente");
		} catch (Exception e) {
			log.fatal("ERROR al insertar el lugar de suministro, " + e);
			return new Mensaje(1,
					"No es posible dar de alta el lugar de suministro.");
		}

	}

	@Override
	public Mensaje deleteDocumentos(Requerimientos requerimientos)
			throws JdbcDaoException {
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
	public Mensaje insertDocumento(Documento documento) throws JdbcDaoException {
		log.debug("insertDocumento()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.ARCHIVOS( ");
		query.append("ID_USUARIO, ");
		query.append("ID_REQUERIMIENTO, ");
		query.append("ID_INDICADOR, ");
		query.append("MIME, ");
		query.append("NOMBRE, ");
		query.append("DESCRIPCION_ARCHIVO, ");
		query.append("TIPO, ");
		query.append("CONTENIDO) ");
		query.append("VALUES( ?, ?, ?, ?, ?, ?, ?, ? )");
		log.debug("query=" + query);
		log.debug("documento: " + documento);

		PreparedStatement ps = null;
		try {
			getConnection().setAutoCommit(false);
			ps = getConnection().prepareStatement(query.toString());
			ps.setInt(1, documento.getIdUsuario());
			ps.setInt(2, documento.getIdReferencia());
			ps.setInt(3, documento.getIdIndicador());
			ps.setString(4, documento.getMimeType(documento.getNombre()));
			ps.setString(5, documento.getNombre());
			ps.setString(6, documento.getDescripcionArchivo());
			ps.setString(7, documento.getFileType(documento.getNombre()));
			ps.setBlob(8, documento.getIs());
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
					"El Documento se actualiz� satisfactoriamente.");
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
		String cadenaBusqueda = busqueda.toUpperCase().trim().replace('�', 'A')
				.replace('�', 'E').replace('�', 'I').replace('�', 'O')
				.replace('�', 'U').replace('�', 'U');
		StringTokenizer st = new StringTokenizer(cadenaBusqueda, " ");
		List<String> x = new ArrayList<String>();
		x.add("A");
		x.add("E");
		x.add("I");
		x.add("O");
		x.add("U");
		x.add("ANTE");
		x.add("BAJO");
		x.add("CABE");
		x.add("CON");
		x.add("CONTRA");
		x.add("DE");
		x.add("DESDE");
		x.add("DURANTE");
		x.add("EN");
		x.add("ENTRE");
		x.add("HACIA");
		x.add("HASTA");
		x.add("MEDIANTE");
		x.add("PARA");
		x.add("POR");
		x.add("SEGUN");
		x.add("SIN");
		x.add("SO");
		x.add("SOBRE");
		x.add("TRAS");
		x.add("EL");
		x.add("LA");
		x.add("LOS");
		x.add("LO");
		x.add("LAS");
		x.add("UN");
		x.add("UNO");
		x.add("UNA");
		x.add("UNOS");
		x.add("UNAS");
		x.add("YO");
		x.add("TU");
		x.add("ELLA");
		x.add("MI");
		x.add("Y");
		x.add("EN");
		x.add("NI");
		x.add("QUE");
		List<String> l = new ArrayList<String>();
		while (st.hasMoreElements()) {
			String p = (String) st.nextElement();
			if (!x.contains(p))
				l.add(p);
		}

		StringBuffer query = new StringBuffer();
		query.append(" SELECT CVE_SCIAN, CASEWHEN( ( ");
		query.append(" SELECT LEFT( A.CVE_SCIAN, 2 ) ");
		query.append(" FROM DUAL ) <> 33 AND ( SELECT ");
		query.append(" LEFT( A.CVE_SCIAN, 2 ) FROM DUAL ");
		query.append(" ) <> 32, ( CASEWHEN( ( SELECT LEFT( ");
		query.append(" A.CVE_SCIAN, 2 ) FROM DUAL ) ");
		query.append(" <> 49, ( SELECT DESC_SCIAN ");
		query.append(" FROM INFRA.CAT_SCIAN_CCMX ");
		query.append(" WHERE CVE_SCIAN = LEFT( ");
		query.append(" A.CVE_SCIAN, 2) ");
		query.append(" ), ( SELECT DESC_SCIAN ");
		query.append(" FROM INFRA.CAT_SCIAN_CCMX ");
		query.append(" WHERE CVE_SCIAN = ");
		query.append(" LEFT(48, 2) ) ) ");
		query.append(" ), ( SELECT DESC_SCIAN ");
		query.append(" FROM INFRA.CAT_SCIAN_CCMX ");
		query.append(" WHERE CVE_SCIAN = ");
		query.append(" LEFT(31, 2) ) ) AS NIVEL_1, ");
		query.append(" ( SELECT DESC_SCIAN ");
		query.append(" FROM INFRA.CAT_SCIAN_CCMX ");
		query.append(" WHERE CVE_SCIAN = ");
		query.append(" LEFT(A.CVE_SCIAN, 3) ) AS NIVEL_2, ");
		query.append(" ( SELECT DESC_SCIAN ");
		query.append(" FROM INFRA.CAT_SCIAN_CCMX ");
		query.append(" WHERE CVE_SCIAN = ");
		query.append(" LEFT(A.CVE_SCIAN, 4) ) AS NIVEL_3, ");
		query.append(" ( SELECT DESC_SCIAN ");
		query.append(" FROM INFRA.CAT_SCIAN_CCMX ");
		query.append(" WHERE CVE_SCIAN = ");
		query.append(" LEFT(A.CVE_SCIAN, 5) ) AS NIVEL_4, ");
		query.append(" DESC_SCIAN,  ");
		query.append(" BUSQUEDA,  ");
		query.append(" CVE_NIVEL  ");
		query.append(" FROM INFRA.CAT_SCIAN_CCMX A ");
		query.append(" WHERE (  ");
		for (String valor : l) {
			query.append(" BUSQUEDA LIKE '%".concat(valor).concat("%' "));
			if (l.indexOf(valor) != l.size() - 1)
				query.append(" OR ");
		}
		query.append(" ) AND CVE_NIVEL = 5");
		query.append(" ORDER BY DESC_SCIAN ASC ");
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
			productos.setCveScian(rs.getInt("CVE_SCIAN"));
			productos.setDescScian(rs.getString("DESC_SCIAN"));
			productos.setNivel1(rs.getString("NIVEL_1"));
			productos.setNivel2(rs.getString("NIVEL_2"));
			productos.setNivel3(rs.getString("NIVEL_3"));
			productos.setNivel4(rs.getString("NIVEL_4"));
			productos.setBusqueda(rs.getString("BUSQUEDA"));
			productos.setCveNivel(rs.getInt("CVE_NIVEL"));
			return productos;
		}

	}

	@SuppressWarnings("unchecked")
	public String getTercerNivelScian(int cve) throws JdbcDaoException {
		log.debug("getNivelScian()");

		String result;
		StringBuffer query = new StringBuffer();
		query.append(" SELECT DESC_SCIAN ");
		query.append(" FROM INFRA.CAT_SCIAN_CCMX ");
		query.append(" WHERE CVE_NIVEL = 3 ");
		query.append(" AND CVE_SCIAN = ");
		query.append(String.valueOf(cve).substring(0, 4));
		log.debug("query=" + query);

		result = (String) getJdbcTemplate().queryForObject(query.toString(),
				new TercerNivelScianRowMapper());

		log.debug("tercer nivel=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class TercerNivelScianRowMapper implements RowMapper {

		@Override
		public String mapRow(ResultSet rs, int ln) throws SQLException {
			return rs.getString("DESC_SCIAN");
		}

	}

	public List<CatScianCcmx> getNivelScian(int cve) throws JdbcDaoException {
		log.debug("getNivelScian()");

		int size = String.valueOf(cve).length();
		StringBuffer query = new StringBuffer();
		query.append(" SELECT CVE_SCIAN,");
		query.append(" DESC_SCIAN, ");
		query.append(" CVE_NIVEL ");
		query.append(" FROM INFRA.CAT_SCIAN_CCMX");
		if (cve == 0) {
			log.debug("es CERO");
			query.append(" WHERE CVE_NIVEL = 1");
		} else {
			log.debug("NO es CERO");
			query.append(" WHERE ( LEFT(CVE_SCIAN, " + size + ") = " + cve);
			if (cve == 31) {
				log.debug("es 31");
				query.append(" OR LEFT(CVE_SCIAN, " + size + ") = " + (cve + 1));
				query.append(" OR LEFT(CVE_SCIAN, " + size + ") = " + (cve + 2));
			} else if (cve == 48) {
				log.debug("es 48");
				query.append(" OR LEFT(CVE_SCIAN, " + size + ") = " + (cve + 1));
			}
			query.append(" ) AND CVE_NIVEL = " + size);
		}
		log.debug("query=" + query);

		List<CatScianCcmx> catScian = getJdbcTemplate().query(query.toString(),
				new CatProd1RowMapper());

		log.debug("catalogo=" + catScian);
		return catScian;
	}

	public class CatProd1RowMapper implements RowMapper<CatScianCcmx> {

		@Override
		public CatScianCcmx mapRow(ResultSet rs, int ln) throws SQLException {
			CatProd1ResultSetExtractor extractor = new CatProd1ResultSetExtractor();
			return (CatScianCcmx) extractor.extractData(rs);
		}

	}

	public class CatProd1ResultSetExtractor implements
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
	public List<Tractoras> getCompradores(int id) throws JdbcDaoException {
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
		query.append("ORDER BY ID_USUARIO ASC ");
		log.debug("query=" + query);
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
	public Mensaje saveUsuarioComp(Tractoras tractoras) throws JdbcDaoException {

		log.debug("saveUsuarioTra()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.USUARIOS ( ");
		query.append("CVE_USUARIO, ");
		query.append("PASSWORD) ");
		query.append("VALUES( '");
		query.append(tractoras.getCorreoElectronico().toLowerCase());
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
	public Mensaje saveRolComp(Tractoras tractoras) throws JdbcDaoException {
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

	public Mensaje saveCompradores(Tractoras tractoras) throws JdbcDaoException {

		log.debug("saveCompradores()");

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
		query.append(tractoras.getIdTractoraPadre());
		query.append("', ' ', '");
		query.append(tractoras.getNombreContacto());
		query.append("', '");
		query.append(tractoras.getAppPaterno());
		query.append("', '");
		query.append(tractoras.getAppMaterno());
		query.append("', '");
		query.append(tractoras.getCorreoElectronico());
		query.append("', ' ' )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			if (tractoras.getTelefonos() != null) {
				int id = getIdTractora().getIdUsuario();
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
			return new Mensaje(
					0,
					"El Comprador se dio de alta satisfactoriamente. En breve recibir� un correo electr�nico el nuevo Comprador con la informaci�n requerida y la liga para acceder al sistema.");
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
		query.append(" ORDER BY ID_TELEFONO ");
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
			throws JdbcDaoException {

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

	public Mensaje saveTelefonos(int id, String telefono)
			throws JdbcDaoException {

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
	public Mensaje asignaPyMEs(int idComprador, String idPyMEs)
			throws JdbcDaoException {
		log.debug("asignaPyMEs()");

		StringBuffer query = new StringBuffer();

		StringTokenizer st = new StringTokenizer(idPyMEs, ",");
		while (st.hasMoreElements()) {
			query = new StringBuffer();
			query.append("INSERT INTO ");
			query.append("INFRA.REL_PYMES_TRACTORAS");
			query.append("( ID_USUARIO_TRACTORA");
			query.append(", ID_USUARIO_PYME ) ");
			query.append("VALUES ( ");
			query.append(idComprador);
			query.append(", ");
			query.append(st.nextElement());
			query.append(" )");
			log.debug("query=" + query);
			try {
				getJdbcTemplate().update(query.toString());
			} catch (Exception e) {
				log.fatal("ERROR al insertar el Rol, " + e);
				return new Mensaje(1,
						"Ocurri� un error al intentar asignar las PyMEs al Comprador seleccionado.");
			}
		}

		return new Mensaje(0,
				"Las PyMEs se asignaron satisfactoriamente al Comprador seleccionado.");

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
		log.debug("querys=" + query);

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
					"Los datos se actualizaron satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar los datos de la Tractora, " + e);
			return new Mensaje(1,
					"No es posible actualizar los datos, intentelo m�s tarde.");
		}

	}

	@SuppressWarnings("unchecked")
	public Tractoras getIdTractora() throws JdbcDaoException {
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

	public Mensaje insertDomicilios(Domicilios domicilios)
			throws JdbcDaoException {
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
	public Domicilios getIdDomicilio() throws JdbcDaoException {
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
	public String getIdDomicilio(int id) throws JdbcDaoException {
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
	public Domicilios getDomicilios(int id) throws JdbcDaoException {
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
					"Los datos se actualizaron satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar los datos de la Tractora, " + e);
			return new Mensaje(1,
					"No es posible actualizar los datos, intentelo m�s tarde.");
		}

	}

	public Mensaje insertRelDomicilios(Domicilios domicilios,
			Tractoras tractoras) throws JdbcDaoException {

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

	@Override
	public List<PyMEs> getPymesTractoras(int id) throws JdbcDaoException {
		log.debug("getPymesTractoras()");

		List<PyMEs> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT RPT.ID_PYME_TRACTORA, ");
		query.append("P.ID_USUARIO, ");
		query.append("P.PERSONALIDAD_JURIDICA, ");
		query.append("P.NOMBRE_COMERCIAL, ");
		query.append("P.B_INHIBIR_VINCULACION, ");
		query.append("P.LIBERA_EXPEDIENTE, ");
		query.append("CO.NOMBRE, ");
		query.append("P.CORREO_ELECTRONICO ");
		query.append("FROM INFRA.PYMES AS P ");
		query.append("LEFT JOIN INFRA.CONTACTOS AS CO ");
		query.append("ON P.ID_USUARIO = CO.ID_USUARIO ");
		query.append("LEFT JOIN INFRA.REL_PYMES_TRACTORAS AS RPT ");
		query.append("ON P.ID_USUARIO = RPT.ID_USUARIO_PYME ");
		query.append("LEFT JOIN INFRA.TRACTORAS AS T ");
		query.append("ON RPT.ID_USUARIO_TRACTORA = T.ID_USUARIO ");
		query.append("WHERE RPT.ID_USUARIO_TRACTORA = ? ");
		query.append("AND CO.TIPO = 'Ventas'");
		query.append("ORDER BY P.NOMBRE_COMERCIAL ASC ");
		log.debug("query=" + query);
		log.debug(id);

		try {
			Object[] o = { id };
			result = (List<PyMEs>) getJdbcTemplate().query(query.toString(), o,
					new PyMEsTractorasRowMapper());
		} catch (Exception e) {
			throw new JdbcDaoException(e);
		}

		log.debug("result=" + result);
		return result;
	}

	public class PyMEsTractorasRowMapper implements RowMapper<PyMEs> {

		@Override
		public PyMEs mapRow(ResultSet rs, int ln) throws SQLException {
			PyMEsTractorasResultSetExtractor extractor = new PyMEsTractorasResultSetExtractor();
			return (PyMEs) extractor.extractData(rs);
		}
	}

	public class PyMEsTractorasResultSetExtractor implements
			ResultSetExtractor<PyMEs> {

		@Override
		public PyMEs extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			PyMEs pym = new PyMEs();
			pym.setIdTractora(rs.getInt("ID_PYME_TRACTORA"));
			pym.setIdUsuario(rs.getInt("ID_USUARIO"));
			pym.setPersonalidadJuridica(rs.getString("PERSONALIDAD_JURIDICA"));
			pym.setNombreComercial(rs.getString("NOMBRE_COMERCIAL"));
			pym.setbInhibirVinculacion(rs.getBoolean("B_INHIBIR_VINCULACION"));
			pym.setEstatus(rs.getBoolean("LIBERA_EXPEDIENTE"));
			pym.setNombreContacto1(rs.getString("NOMBRE"));
			pym.setCorreoElectronicoContacto1(rs
					.getString("CORREO_ELECTRONICO"));
			return pym;
		}
	}

	@Override
	public List<PyMEs> getPymesVinculacion(int id) throws JdbcDaoException {
		log.debug("getPymesVinculacion()");

		List<PyMEs> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT DISTINCT P.ID_USUARIO");
		query.append(", ( SELECT COUNT(*) FROM ");
		query.append("INFRA.REL_PYMES_TRACTORAS TRPT ");
		query.append("WHERE TRPT.ID_USUARIO_TRACTORA ");
		query.append("IN ( SELECT TT.ID_USUARIO ");
		query.append("FROM INFRA.TRACTORAS AS TT ");
		query.append("WHERE TT.ID_TRACTORA_PADRE = ");
		query.append("RPT.ID_USUARIO_TRACTORA ) ");
		query.append("AND TRPT.ID_USUARIO_PYME =");
		query.append(" P.ID_USUARIO ) ");
		query.append("AS ASIGNADA");
		query.append(", P.ID_USUARIO_PADRE");
		query.append(", P.NOMBRE_COMERCIAL");
		query.append(", D.ESTADO");
		query.append(", C.TELEFONO");
		query.append(", C.NOMBRE");
		query.append(", C.APELLIDO_PATERNO");
		query.append(", C.APELLIDO_MATERNO");
		query.append(", C.CORREO_ELECTRONICO ");
		query.append("FROM INFRA.PYMES P");
		query.append(", INFRA.CONTACTOS C");
		query.append(", INFRA.PRODUCTOS PP");
		query.append(", INFRA.REL_DOMICILIOS_USUARIO RDU ");
		query.append(", INFRA.DOMICILIOS D");
		query.append(", INFRA.REL_PYMES_TRACTORAS RPT ");
		query.append("WHERE RPT.ID_USUARIO_TRACTORA = ? ");
		query.append("AND P.ID_USUARIO = RPT.ID_USUARIO_PYME ");
		query.append("AND P.ID_USUARIO = C.ID_USUARIO ");
		query.append("AND P.ID_USUARIO = PP.ID_USUARIO(+) ");
		query.append("AND  P.ID_USUARIO = RDU.ID_USUARIO(+) ");
		query.append("AND RDU.ID_DOMICILIO = D.ID_DOMICILIO(+) ");
		query.append("AND C.B_PRINCIPAL = true ");
		query.append("AND ( SELECT COUNT(*) ");
		query.append("FROM INFRA.REL_PYMES_TRACTORAS TRPT ");
		query.append("WHERE TRPT.ID_USUARIO_TRACTORA IN ");
		query.append("( SELECT TT.ID_USUARIO ");
		query.append("FROM INFRA.TRACTORAS AS TT ");
		query.append("WHERE TT.ID_TRACTORA_PADRE = ");
		query.append("RPT.ID_USUARIO_TRACTORA) ");
		query.append("AND TRPT.ID_USUARIO_PYME = ");
		query.append("P.ID_USUARIO ) = 0");
		query.append(" ORDER BY P.ID_USUARIO ASC");
		log.debug("query=" + query);
		log.debug(id);

		try {
			Object[] o = { id };
			result = (List<PyMEs>) getJdbcTemplate().query(query.toString(), o,
					new PyMEsVinculacionRowMapper());
		} catch (Exception e) {
			throw new JdbcDaoException(e);
		}

		log.debug("result="
				+ (result != null && result.size() > 20 ? result.size()
						: result));
		return result;
	}

	public class PyMEsVinculacionRowMapper implements RowMapper<PyMEs> {

		@Override
		public PyMEs mapRow(ResultSet rs, int ln) throws SQLException {
			PyMEsVinculacionResultSetExtractor extractor = new PyMEsVinculacionResultSetExtractor();
			return (PyMEs) extractor.extractData(rs);
		}
	}

	public class PyMEsVinculacionResultSetExtractor implements
			ResultSetExtractor<PyMEs> {

		@Override
		public PyMEs extractData(ResultSet rs) throws SQLException,
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
			return pymes;
		}
	}

	@Override
	public List<Contacto> getCorreosByProducto(String cveScian)
			throws JdbcDaoException {
		log.debug("getRequerimientos()");

		List<Contacto> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT DISTINCT U.CVE_USUARIO");
		query.append(" AS CORREO ");
		query.append("FROM INFRA.PYMES P");
		query.append(", INFRA.USUARIOS U");
		query.append(", INFRA.CATEGORIAS C");
		query.append(" WHERE P.ID_USUARIO = U.ID_USUARIO");
		query.append(" AND P.ID_USUARIO = C.ID_USUARIO(+)");
		query.append(" AND C.CVE_SCIAN LIKE '");
		query.append(cveScian.substring(0, 3).concat("%'"));
		log.debug("query=" + query);

		try {
			result = (List<Contacto>) getJdbcTemplate().query(query.toString(),
					new ContactoRowMapper());
		} catch (Exception e) {
			throw new JdbcDaoException(e);
		}

		log.debug("result=" + result);
		return result;
	}

	public class ContactoRowMapper implements RowMapper<Contacto> {

		@Override
		public Contacto mapRow(ResultSet rs, int ln) throws SQLException {
			ContactoResultSetExtractor extractor = new ContactoResultSetExtractor();
			return (Contacto) extractor.extractData(rs);
		}
	}

	public class ContactoResultSetExtractor implements
			ResultSetExtractor<Contacto> {

		@Override
		public Contacto extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Contacto contacto = new Contacto();
			contacto.setCorreoElectronico(rs.getString("CORREO"));
			return contacto;
		}
	}

	@Override
	public Mensaje insertIndicadores(Indicadores indicadores)
			throws JdbcDaoException {
		log.debug("insertIndicadores()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.INDICADORES (");
		query.append("ID_PYME_TRACTORA, ");
		query.append("ID_INDICADOR_TRACTORA, ");
		query.append("RESULTADO_CALCULO, ");
		query.append("PERIODO_REF_MES, ");
		query.append("PERIODO_REF_ANIO) ");
		query.append("VALUES (");
		query.append(indicadores.getIdPyMETractora());
		query.append(", ");
		query.append(indicadores.getIdIndicadorTractora());
		query.append(", '");
		query.append(indicadores.getResultadoCalculo());
		query.append("', '");
		query.append(indicadores.getPeriodoRefMes());
		query.append("', ");
		query.append(indicadores.getPeriodoRefAnio());
		query.append(")");

		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos han sido registrados exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar los datos de INDICACORES, " + e);
			return new Mensaje(1,
					"No es posible realizar el registro, intentelo m�s tarde.");
		}
	}

	@Override
	public Mensaje updateIndicadores(Indicadores indicadores)
			throws JdbcDaoException {
		log.debug("updateIndicadores()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE INFRA.INDICADORES SET ");
		query.append("ID_INDICADOR_TRACTORA = ");
		query.append(indicadores.getIdIndicadorTractora());
		query.append(", RESULTADO_CALCULO = '");
		query.append(indicadores.getResultadoCalculo());
		query.append("', PERIODO_REF_MES  = '");
		query.append(indicadores.getPeriodoRefMes());
		query.append("', PERIODO_REF_ANIO = ");
		query.append(indicadores.getPeriodoRefAnio());
		query.append(" WHERE ID_INDICADOR = ");
		query.append(indicadores.getIdIndicador());
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos han sido registrados exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar los datos de INDICACORES, " + e);
			return new Mensaje(1,
					"No es posible realizar el registro, intentelo m�s tarde.");
		}
	}

	@SuppressWarnings("unchecked")
	public Indicadores getIdIndicador() throws JdbcDaoException {
		log.debug("getIdIndicador()");

		Indicadores result = null;
		StringBuffer query = new StringBuffer();

		query.append("SELECT MAX(ID_INDICADOR) AS MAX_INDICADOR ");
		query.append("FROM INFRA.INDICADORES ");
		log.debug("query=" + query);

		result = (Indicadores) getJdbcTemplate().queryForObject(
				query.toString(), new IdMaxIndicadoresRowMapper());

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class IdMaxIndicadoresRowMapper implements RowMapper {

		@Override
		public Indicadores mapRow(ResultSet rs, int ln) throws SQLException {
			Indicadores ind = new Indicadores();
			ind.setIdIndicador(rs.getInt("MAX_INDICADOR"));
			return ind;
		}
	}

	@Override
	public List<CatIndicadoresTractora> getCatIndicadores()
			throws JdbcDaoException {
		log.debug("getCatIndicadores()");

		List<CatIndicadoresTractora> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_INDICADOR, ");
		query.append("INDICADOR ");
		query.append("FROM INFRA.CAT_INDICADORES_TRACTORA ");
		query.append("ORDER BY ID_INDICADOR ASC");
		log.debug("query=" + query);

		try {
			result = (List<CatIndicadoresTractora>) getJdbcTemplate().query(
					query.toString(), new CatalogoIndicadoresRowMapper());
		} catch (Exception e) {
			throw new JdbcDaoException(e);
		}

		log.debug("result=" + result);
		return result;
	}

	public class CatalogoIndicadoresRowMapper implements
			RowMapper<CatIndicadoresTractora> {

		@Override
		public CatIndicadoresTractora mapRow(ResultSet rs, int ln)
				throws SQLException {
			CatalogoIndicadoresResultSetExtractor extractor = new CatalogoIndicadoresResultSetExtractor();
			return (CatIndicadoresTractora) extractor.extractData(rs);
		}
	}

	public class CatalogoIndicadoresResultSetExtractor implements
			ResultSetExtractor<CatIndicadoresTractora> {

		@Override
		public CatIndicadoresTractora extractData(ResultSet rs)
				throws SQLException, DataAccessException {
			CatIndicadoresTractora cat = new CatIndicadoresTractora();
			cat.setIdIndicador(rs.getInt("ID_INDICADOR"));
			cat.setIndicador(rs.getString("INDICADOR"));
			return cat;
		}
	}

	@Override
	public Mensaje insertCalificaciones(RelPyMEsTractoras relPyMEsTractoras)
			throws JdbcDaoException {
		log.debug("insertCalificaciones()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.REL_PYMES_TRACTORAS SET ");
		query.append("CALIFICACION = ");
		query.append(relPyMEsTractoras.getCalificacion());
		query.append(", COMENTARIO = '");
		query.append(relPyMEsTractoras.getComentario());
		query.append("', RECOMENDACION = ");
		query.append(relPyMEsTractoras.isRecomendacion());
		query.append(" WHERE ID_PYME_TRACTORA = ");
		query.append(relPyMEsTractoras.getIdPyMETractora());
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos han sido actualizados exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar los datos de la Calificac�n, " + e);
			return new Mensaje(1,
					"No es posible registrar los datos, intentelo m�s tarde.");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public RelPyMEsTractoras getCalificaciones(int id) throws JdbcDaoException {
		log.debug("getCalificaciones()");

		RelPyMEsTractoras result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT TOP 1 ID_PYME_TRACTORA, ");
		query.append("ID_USUARIO_PYME, ");
		query.append("CALIFICACION, ");
		query.append("COMENTARIO, ");
		query.append("RECOMENDACION ");
		query.append("FROM INFRA.REL_PYMES_TRACTORAS ");
		query.append("WHERE ID_USUARIO_PYME = " + id);
		query.append(" ORDER BY ID_USUARIO_TRACTORA ASC");
		log.debug("query=" + query);
		log.debug(id);

		if (id == 0)
			return null;
		result = (RelPyMEsTractoras) getJdbcTemplate().queryForObject(
				query.toString(), new CalificacionesRowMapper());

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class CalificacionesRowMapper implements RowMapper {

		@Override
		public RelPyMEsTractoras mapRow(ResultSet rs, int ln)
				throws SQLException {
			RelPyMEsTractoras rc = new RelPyMEsTractoras();
			rc.setIdPyMETractora(rs.getInt("ID_PYME_TRACTORA"));
			rc.setIdUsuarioPyME(rs.getInt("ID_USUARIO_PYME"));
			rc.setCalificacion(rs.getInt("CALIFICACION"));
			rc.setComentario(rs.getString("COMENTARIO"));
			rc.setRecomendacion(rs.getBoolean("RECOMENDACION"));
			return rc;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getIdIndicadores(Indicadores indicadores)
			throws JdbcDaoException {
		log.debug("getIdIndicadores()");

		String result;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ID_INDICADOR ");
		query.append("FROM INFRA.INDICADORES ");
		query.append("WHERE ID_PYME_TRACTORA = ");
		query.append(indicadores.getIdPyMETractora());
		query.append(" AND ID_INDICADOR_TRACTORA = ");
		query.append(indicadores.getIdIndicadorTractora());
		query.append(" AND PERIODO_REF_MES  = '");
		query.append(indicadores.getPeriodoRefMes());
		query.append("' AND PERIODO_REF_ANIO = ");
		query.append(indicadores.getPeriodoRefAnio());
		log.debug("query=" + query);

		try {
			result = (String) getJdbcTemplate().queryForObject(
					query.toString(), new IdIndicadorRowMapper());
		} catch (Exception e) {
			result = "0";
		}

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class IdIndicadorRowMapper implements RowMapper {

		@Override
		public String mapRow(ResultSet rs, int ln) throws SQLException {
			return rs.getString("ID_INDICADOR");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getIdPyMETractoras(int id, int rel) throws JdbcDaoException {
		log.debug("getIdPyMETractoras()");

		String result;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ID_PYME_TRACTORA ");
		query.append("FROM INFRA.REL_PYMES_TRACTORAS AS REL ");
		query.append("JOIN INFRA.TRACTORAS AS T ");
		query.append("ON REL.ID_USUARIO_TRACTORA = T.ID_USUARIO ");
		query.append("WHERE REL.ID_USUARIO_PYME = " + rel);
		query.append(" ORDER BY ID_PYME_TRACTORA ASC LIMIT 1");
		log.debug("query=" + query);

		try {
			result = (String) getJdbcTemplate().queryForObject(
					query.toString(), new IdPyMETractoraRowMapper());
		} catch (Exception e) {
			result = "0";
		}

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class IdPyMETractoraRowMapper implements RowMapper {

		@Override
		public String mapRow(ResultSet rs, int ln) throws SQLException {
			return rs.getString("ID_PYME_TRACTORA");
		}
	}

	@Override
	public List<Respuesta> getRespuestas(int id) throws JdbcDaoException {
		log.debug("getRespuestas()");

		List<Respuesta> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT RES.ID_RESPUESTA AS ID_RESPUESTA");
		query.append(", RES.ID_REQUERIMIENTO AS ID_REQUERIMIENTO");
		query.append(", RES.INFORMACION AS INFORMACION");
		query.append(", RES.ID_PYME AS ID_PYME");
		query.append(", PYM.NOMBRE_COMERCIAL AS PYME");
		query.append(" FROM INFRA.RESPUESTAS AS RES");
		query.append(", INFRA.PYMES AS PYM");
		query.append(", INFRA.REQUERIMIENTOS AS REQ");
		query.append(" WHERE RES.ID_REQUERIMIENTO = REQ.ID_REQUERIMIENTO");
		query.append(" AND RES.ID_PYME = PYM.ID_USUARIO");
		query.append(" AND RES.ID_REQUERIMIENTO = ?");
		query.append(" ORDER BY ID_RESPUESTA DESC");
		log.debug("query=" + query);
		log.debug(id);

		try {
			Object[] o = { id };
			result = (List<Respuesta>) getJdbcTemplate().query(
					query.toString(), o, new RespuestasRowMapper());
		} catch (Exception e) {
			throw new JdbcDaoException(e);
		}

		log.debug("result=" + result);
		return result;
	}

	public class RespuestasRowMapper implements RowMapper<Respuesta> {

		@Override
		public Respuesta mapRow(ResultSet rs, int ln) throws SQLException {
			RespuestasResultSetExtractor extractor = new RespuestasResultSetExtractor();
			return (Respuesta) extractor.extractData(rs);
		}

	}

	public class RespuestasResultSetExtractor implements
			ResultSetExtractor<Respuesta> {

		@Override
		public Respuesta extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Respuesta respuesta = new Respuesta();
			respuesta.setIdRespuesta(rs.getInt("ID_RESPUESTA"));
			respuesta.setIdRequerimiento(rs.getInt("ID_REQUERIMIENTO"));
			respuesta.setInformacion(rs.getString("INFORMACION"));
			respuesta.setIdPyME(rs.getInt("ID_PYME"));
			respuesta.setNombrePyME(rs.getString("PYME"));
			return respuesta;
		}

	}

	@Override
	public Respuesta getRespuesta(int id) throws JdbcDaoException {
		log.debug("getRespuesta()");

		Respuesta result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_RESPUESTA");
		query.append(", ID_REQUERIMIENTO");
		query.append(", INFORMACION");
		query.append(", MENSAJE_ENVIO");
		query.append(" FROM INFRA.RESPUESTAS");
		query.append(" WHERE ID_RESPUESTA = ?");
		log.debug("query=" + query);
		log.debug(id);

		Object[] o = { id };
		result = (Respuesta) getJdbcTemplate().queryForObject(query.toString(),
				o, new RespuestaRowMapper());

		try {
			query = new StringBuffer();
			query.append("SELECT ID_ARCHIVO");
			query.append(", NOMBRE");
			query.append(" FROM INFRA.ARCHIVOS");
			query.append(" WHERE ID_RESPUESTA = ?");
			log.debug("query=" + query);
			log.debug(id);

			Object[] u = { id };
			List<Documento> l = (List<Documento>) getJdbcTemplate().query(
					query.toString(), u, new ArchivosRowMapper());

			if (l.size() > 0) {
				result.setIdArchivo1(l.get(0).getIdArchivo());
				result.setArchivo1FileName(l.get(0).getNombre());
			}
			if (l.size() > 1) {
				result.setIdArchivo2(l.get(1).getIdArchivo());
				result.setArchivo2FileName(l.get(1).getNombre());
			}
			if (l.size() > 2) {
				result.setIdArchivo3(l.get(2).getIdArchivo());
				result.setArchivo3FileName(l.get(2).getNombre());
			}
			if (l.size() > 3) {
				result.setIdArchivo4(l.get(3).getIdArchivo());
				result.setArchivo4FileName(l.get(3).getNombre());
			}
			if (l.size() > 4) {
				result.setIdArchivo5(l.get(4).getIdArchivo());
				result.setArchivo5FileName(l.get(4).getNombre());
			}
			if (l.size() > 5) {
				result.setIdArchivo6(l.get(5).getIdArchivo());
				result.setArchivo6FileName(l.get(5).getNombre());
			}
			if (l.size() > 6) {
				result.setIdArchivo7(l.get(6).getIdArchivo());
				result.setArchivo7FileName(l.get(6).getNombre());
			}
			if (l.size() > 7) {
				result.setIdArchivo8(l.get(7).getIdArchivo());
				result.setArchivo8FileName(l.get(7).getNombre());
			}
			if (l.size() > 8) {
				result.setIdArchivo9(l.get(8).getIdArchivo());
				result.setArchivo9FileName(l.get(8).getNombre());
			}
			if (l.size() > 9) {
				result.setIdArchivo10(l.get(9).getIdArchivo());
				result.setArchivo10FileName(l.get(9).getNombre());
			}

		} catch (Exception e) {
			log.warn("La Respuesta al Requerimiento no contine archivos.");
		}

		log.debug("result=" + result);
		return result;
	}

	public class RespuestaRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			Respuesta respuesta = new Respuesta();
			respuesta.setIdRespuesta(rs.getInt("ID_RESPUESTA"));
			respuesta.setIdRequerimiento(rs.getInt("ID_REQUERIMIENTO"));
			respuesta.setInformacion(rs.getString("INFORMACION"));
			respuesta.setMensajeEnvio(rs.getString("MENSAJE_ENVIO"));

			return respuesta;
		}

	}

	public class ArchivosRowMapper implements RowMapper<Documento> {

		@Override
		public Documento mapRow(ResultSet rs, int ln) throws SQLException {
			ArchivosResultSetExtractor extractor = new ArchivosResultSetExtractor();
			return (Documento) extractor.extractData(rs);
		}

	}

	public class ArchivosResultSetExtractor implements
			ResultSetExtractor<Documento> {

		@Override
		public Documento extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Documento doc = new Documento();
			doc.setIdArchivo(rs.getInt("ID_ARCHIVO"));
			doc.setNombre(rs.getString("NOMBRE"));
			return doc;
		}

	}

	public List<Documento> getDocumentos(String id) throws JdbcDaoException {
		log.debug("getDocumentos()");

		List<Documento> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_ARCHIVO");
		query.append(", NOMBRE");
		query.append(", DESCRIPCION_ARCHIVO");
		query.append(" FROM INFRA.ARCHIVOS");
		query.append(" WHERE ID_REQUERIMIENTO = " + id);
		query.append(" ORDER BY ID_ARCHIVO");
		log.debug("query=" + query);

		try {
			result = (List<Documento>) getJdbcTemplate().query(
					query.toString(), new DocumentosRowMapper());
		} catch (EmptyResultDataAccessException erdae) {
			log.warn("No se obtubieron documentos");
		} catch (Exception e) {
			throw new JdbcDaoException(e);
		}

		log.debug("result=" + result);
		return result;
	}

	public class DocumentosRowMapper implements RowMapper<Documento> {

		@Override
		public Documento mapRow(ResultSet rs, int ln) throws SQLException {
			DocumentosResultSetExtractor extractor = new DocumentosResultSetExtractor();
			return (Documento) extractor.extractData(rs);
		}

	}

	public class DocumentosResultSetExtractor implements
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