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
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.dto.Roles;
import mx.com.vgati.ccmx.vinculacion.tractoras.dao.TractorasDao;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Productos;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;

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
		query.append("B_CONTINUO_F_EXPIRA ");
		query.append("FROM INFRA.REQUERIMIENTOS ");
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
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El Requerimiento se dio de alta satisfactoriamente.");
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
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El Requerimiento se actualizó satisfactoriamente.");
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
			return new Mensaje(0,
					"El requerimiento se eliminó satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al eliminar el Requerimiento, " + e);
			return new Mensaje(1, "No es posible eliminar el Requerimiento.");
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
		query.append("PUESTO, ");
		query.append("TELEFONOS ");
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
			tractoras.setTelefonos(rs.getString("TELEFONOS"));
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

		log.debug("saveRolTra()");

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
		query.append("PUESTO, ");
		query.append("TELEFONOS) ");
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
		query.append("', '");
		query.append(tractoras.getTelefonos());
		query.append("' )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(
					0,
					"El Comprador se dio de alta satisfactoriamente. En breve recibirá un correo electrónico el nuevo Comprador con la información requerida y la liga para acceder al sistema.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar el Comprador, " + e);
			return new Mensaje(1, "No es posible dar de alta al Comprador.");
		}

	}

	public class InsertaCompradorRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			return rs;
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
		query.append("PUESTO, TELEFONOS ");
		query.append("FROM INFRA.TRACTORAS ");
		query.append("WHERE ID_USUARIO = ? ");
		log.debug("query=" + query);
		log.debug(id);

		Object[] o = { id };
		result = (Tractoras) getJdbcTemplate().queryForObject(query.toString(),
				o, new TractoraRowMapper());

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
			tractoras.setTelefonos(rs.getString("TELEFONOS"));

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
		query.append("', ");
		query.append("TELEFONOS = '");
		query.append(tractoras.getTelefonos());
		query.append("'");
		query.append(" WHERE ID_USUARIO = ");
		query.append(tractoras.getIdUsuario());
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

	public Mensaje insertDomicilios(Domicilios domicilios) throws DaoException {

		log.debug("insertDomicilio()");

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

	public Domicilios getIdDomicilio() throws DaoException {

		Domicilios result = null;
		StringBuffer query = new StringBuffer();

		log.debug("insertDomicilio()");
		query.append("SELECT MAX(");
		query.append("ID_DOMICILIO) ");
		query.append("FROM INFRA.DOMICILIOS ");
		log.debug("query=" + query);

		result = (Domicilios) getJdbcTemplate().queryForObject(
				query.toString(), new IdDomiciliosRowMapper());

		log.debug("result=" + result);
		return result;
	}

	public class IdDomiciliosRowMapper implements RowMapper<Domicilios> {

		@Override
		public Domicilios mapRow(ResultSet rs, int ln) throws SQLException {
			IdDomiciliosResultSetExtractor extractor = new IdDomiciliosResultSetExtractor();
			return (Domicilios) extractor.extractData(rs);
		}

	}

	public class IdDomiciliosResultSetExtractor implements
			ResultSetExtractor<Domicilios> {

		@Override
		public Domicilios extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Domicilios domicilios = new Domicilios();
			domicilios.setIdDomicilio(rs.getInt("ID_DOMICILIO"));
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
		query.append(domicilios.getIdDomicilio());
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
		query.append(domicilios.getIdDomicilio());
		query.append("', '");
		query.append(tractoras.getIdUsuario());
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