/*
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.pymes.dao.PyMEsDao;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Certificaciones;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Clientes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Productos;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.RelPyMEsTractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Contacto;
import mx.com.vgati.framework.dto.Documento;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;
import mx.com.vgati.framework.dto.Respuesta;
import mx.com.vgati.framework.util.Null;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class PyMEsDaoJdbcImp extends VinculacionBaseJdbcDao implements PyMEsDao {

	@SuppressWarnings("unchecked")
	@Override
	public PyMEs getPyMEs(int id) throws DaoException {
		log.debug("getPyMEs()");

		PyMEs result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_USUARIO, ");
		query.append("ID_USUARIO_PADRE, ");
		query.append("PERSONALIDAD_JURIDICA, ");
		query.append("CASE RFC WHEN 'null' THEN '' ELSE RFC END AS RFC, ");
		query.append("CORREO_ELECTRONICO, ");
		query.append("NOMBRE_COMERCIAL, ");
		query.append("CASE NOMBRE_FISCAL WHEN 'null' THEN '' ELSE NOMBRE_FISCAL END AS NOMBRE_FISCAL, ");
		query.append("NUMERO_EMPLEADOS, ");
		query.append("CASE MENSAJE_VENTAS WHEN 'null' THEN '' ELSE MENSAJE_VENTAS END AS MENSAJE_VENTAS, ");
		query.append("CASE PAGINA_WEB WHEN 'null' THEN ' ' ELSE PAGINA_WEB END AS PAGINA_WEB, ");
		query.append("VENTAS_ANUALES, ");
		query.append("CVE_SCIAN, ");
		query.append("B_PRIMER_NIVEL, ");
		query.append("B_SEGUNDO_NIVEL, ");
		query.append("B_TERCER_NIVEL, ");
		query.append("B_DIPLOMADO_CCMX_UNO, ");
		query.append("B_DIPLOMADO_CCMX_DOS, ");
		query.append("B_DIPLOMADO_CCMX_TRES, ");
		query.append("B_DIPLOMADO_CCMX_CUATRO, ");
		query.append("B_RECIBE_REQUERIMIENTOS_COMPRA, ");
		query.append("CVE_SCIAN_REQUERIMIENTOS_COMPRA, ");
		query.append("B_SERVICIOS_CCMX_DIPLOMADOS, ");
		query.append("B_SERVICIOS_CCMX_CONSULTORIA, ");
		query.append("CASE WHEN  CEDULA IS null THEN 'Sin asignar' ELSE CEDULA END AS CEDULA, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 1, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 0 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO), '0') AS ID_DOC1, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 2, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 1 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO), '0') AS ID_DOC2, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 3, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 2 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO), '0') AS ID_DOC3, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 4, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 3 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO), '0') AS ID_DOC4, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 5, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 4 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO), '0') AS ID_DOC5, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 6, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 5 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO), '0') AS ID_DOC6, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 7, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 6 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO), '0') AS ID_DOC7, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 8, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 7 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO), '0') AS ID_DOC8, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 9, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 8 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO), '0') AS ID_DOC9, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 10, ");
		query.append("(SELECT MIN(ID_ARCHIVO) + 9 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO), '0') AS ID_DOC10, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 1, ");
		query.append("( SELECT NOMBRE FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 0 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS NAME_DOC1, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 2, ");
		query.append("( SELECT NOMBRE FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 1 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS NAME_DOC2, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 3, ");
		query.append("( SELECT NOMBRE FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 2 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS NAME_DOC3, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 4, ");
		query.append("( SELECT NOMBRE FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 3 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS NAME_DOC4, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 5, ");
		query.append("( SELECT NOMBRE FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 4 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS NAME_DOC5, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 6, ");
		query.append("( SELECT NOMBRE FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 5 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS NAME_DOC6, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 7, ");
		query.append("( SELECT NOMBRE FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 6 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS NAME_DOC7, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 8, ");
		query.append("( SELECT NOMBRE FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 7 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS NAME_DOC8, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 9, ");
		query.append("( SELECT NOMBRE FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 8 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS NAME_DOC9, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 10, ");
		query.append("( SELECT NOMBRE FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 9 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS NAME_DOC10, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 1, ");
		query.append("( SELECT DESCRIPCION_ARCHIVO FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 0 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS DESCRIPCION_ARCHIVO1, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 2, ");
		query.append("( SELECT DESCRIPCION_ARCHIVO FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 1 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS DESCRIPCION_ARCHIVO2, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 3, ");
		query.append("( SELECT DESCRIPCION_ARCHIVO FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 2 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS DESCRIPCION_ARCHIVO3, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 4, ");
		query.append("( SELECT DESCRIPCION_ARCHIVO FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 3 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS DESCRIPCION_ARCHIVO4, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 5, ");
		query.append("( SELECT DESCRIPCION_ARCHIVO FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 4 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS DESCRIPCION_ARCHIVO5, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 6, ");
		query.append("( SELECT DESCRIPCION_ARCHIVO FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 5 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS DESCRIPCION_ARCHIVO6, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 7, ");
		query.append("( SELECT DESCRIPCION_ARCHIVO FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 6 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS DESCRIPCION_ARCHIVO7, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 8, ");
		query.append("( SELECT DESCRIPCION_ARCHIVO FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 7 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS DESCRIPCION_ARCHIVO8, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 9, ");
		query.append("( SELECT DESCRIPCION_ARCHIVO FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 8 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS DESCRIPCION_ARCHIVO9, ");
		query.append("CASEWHEN((SELECT COUNT(ID_ARCHIVO) FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO) >= 10, ");
		query.append("( SELECT DESCRIPCION_ARCHIVO FROM INFRA.ARCHIVOS WHERE ID_ARCHIVO = ( SELECT MIN(ID_ARCHIVO) + 9 FROM INFRA.ARCHIVOS WHERE ID_USUARIO = P.ID_USUARIO)), NULL) AS DESCRIPCION_ARCHIVO10 ");
		query.append("FROM INFRA.PYMES AS P ");
		query.append("WHERE ID_USUARIO = ? ");
		log.debug("query=" + query);
		log.debug(id);

		Object[] o = { id };
		result = (PyMEs) getJdbcTemplate().queryForObject(query.toString(), o, new PyMEsRowMapper());
		
		List<Productos> lp = getProductos(id);
			result.setProductos(lp);
		
		List<Contacto> lcont = getContactos(id);
			result.setContactos(lcont);
		
		List<Clientes> lclient = getClientes(id);
			result.setClientes(lclient);
		
		List<Certificaciones> lcert = getCertificaciones(id);
			result.setCertificaciones(lcert);

		log.debug("result=" + result);
		return result;
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
			pymes.setPersonalidadJuridica(rs.getString("PERSONALIDAD_JURIDICA"));
			pymes.setRfc(rs.getString("RFC"));
			pymes.setCorreoElectronico(rs.getString("CORREO_ELECTRONICO"));
			pymes.setNombreComercial(rs.getString("NOMBRE_COMERCIAL"));
			pymes.setNombreFiscal(rs.getString("NOMBRE_FISCAL"));
			pymes.setNumeroEmpleados(rs.getInt("NUMERO_EMPLEADOS"));
			pymes.setMensajeVentas(rs.getString("MENSAJE_VENTAS"));
			pymes.setPaginaWeb(rs.getString("PAGINA_WEB"));
			pymes.setVentasAnuales(rs.getString("VENTAS_ANUALES"));
			pymes.setCveScian(rs.getInt("CVE_SCIAN"));
			pymes.setbPrimerNivel(rs.getBoolean("B_PRIMER_NIVEL"));
			pymes.setbSegundoNivel(rs.getBoolean("B_SEGUNDO_NIVEL"));
			pymes.setbTercerNivel(rs.getBoolean("B_TERCER_NIVEL"));
			pymes.setbDiplomadoCcmxUno(rs.getBoolean("B_DIPLOMADO_CCMX_UNO"));
			pymes.setbDiplomadoCcmxDos(rs.getBoolean("B_DIPLOMADO_CCMX_DOS"));
			pymes.setbDiplomadoCcmxTres(rs.getBoolean("B_DIPLOMADO_CCMX_TRES"));
			pymes.setbDiplomadoCcmxCuatro(rs
					.getBoolean("B_DIPLOMADO_CCMX_CUATRO"));
			pymes.setbRecibeRequerimientosCompra(rs
					.getBoolean("B_RECIBE_REQUERIMIENTOS_COMPRA"));
			pymes.setCveScianRequerimientosCompra(rs
					.getInt("CVE_SCIAN_REQUERIMIENTOS_COMPRA"));
			pymes.setbServiciosCcmxDiplomados(rs
					.getBoolean("B_SERVICIOS_CCMX_DIPLOMADOS"));
			pymes.setbServiciosCcmxConsultoria(rs
					.getBoolean("B_SERVICIOS_CCMX_CONSULTORIA"));
			pymes.setIdArchivo1(rs.getInt("ID_DOC1"));
			pymes.setIdArchivo2(rs.getInt("ID_DOC2"));
			pymes.setIdArchivo3(rs.getInt("ID_DOC3"));
			pymes.setIdArchivo4(rs.getInt("ID_DOC4"));
			pymes.setIdArchivo5(rs.getInt("ID_DOC5"));
			pymes.setIdArchivo6(rs.getInt("ID_DOC6"));
			pymes.setIdArchivo7(rs.getInt("ID_DOC7"));
			pymes.setIdArchivo8(rs.getInt("ID_DOC8"));
			pymes.setIdArchivo9(rs.getInt("ID_DOC9"));
			pymes.setIdArchivo10(rs.getInt("ID_DOC10"));
			pymes.setArchivo1FileName(rs.getString("NAME_DOC1"));
			pymes.setArchivo2FileName(rs.getString("NAME_DOC2"));
			pymes.setArchivo3FileName(rs.getString("NAME_DOC3"));
			pymes.setArchivo4FileName(rs.getString("NAME_DOC4"));
			pymes.setArchivo5FileName(rs.getString("NAME_DOC5"));
			pymes.setArchivo6FileName(rs.getString("NAME_DOC6"));
			pymes.setArchivo7FileName(rs.getString("NAME_DOC7"));
			pymes.setArchivo8FileName(rs.getString("NAME_DOC8"));
			pymes.setArchivo9FileName(rs.getString("NAME_DOC9"));
			pymes.setArchivo10FileName(rs.getString("NAME_DOC10"));
			pymes.setDescArchivo1(rs.getString("DESCRIPCION_ARCHIVO1"));
			pymes.setDescArchivo2(rs.getString("DESCRIPCION_ARCHIVO2"));
			pymes.setDescArchivo3(rs.getString("DESCRIPCION_ARCHIVO3"));
			pymes.setDescArchivo4(rs.getString("DESCRIPCION_ARCHIVO4"));
			pymes.setDescArchivo5(rs.getString("DESCRIPCION_ARCHIVO5"));
			pymes.setDescArchivo6(rs.getString("DESCRIPCION_ARCHIVO6"));
			pymes.setDescArchivo7(rs.getString("DESCRIPCION_ARCHIVO7"));
			pymes.setDescArchivo8(rs.getString("DESCRIPCION_ARCHIVO8"));
			pymes.setDescArchivo9(rs.getString("DESCRIPCION_ARCHIVO9"));
			pymes.setDescArchivo10(rs.getString("DESCRIPCION_ARCHIVO10"));
			return pymes;
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
		log.debug(id);

		if (id == 0)
			return null;
		result = (Domicilios) getJdbcTemplate().queryForObject(
				query.toString(), new DomiciliosRowMapper());

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class DomiciliosRowMapper implements RowMapper {

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

	@SuppressWarnings("unchecked")
	@Override
	public EstadosVenta getEstadosVentas(int id) throws DaoException {
		log.debug("getEstadosVentas()");

		EstadosVenta result = null;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Nacional') AS ID_NACIONAL, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Nacional' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Nacional' AND ID_USUARIO = EV.ID_USUARIO) AS NACIONAL, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Aguascalientes') AS ID_AGUASCALIENTES, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Aguascalientes' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Aguascalientes' AND ID_USUARIO = EV.ID_USUARIO) AS AGUASCALIENTES, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Baja California Norte') AS ID_BAJA_CALIFORNIA_NORTE, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Baja California Norte' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Baja California Norte' AND ID_USUARIO = EV.ID_USUARIO) AS BAJA_CALIFORNIA_NORTE, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Baja California Sur') AS ID_BAJA_CALIFORNIA_SUR, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Baja California Sur' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Baja California Sur' AND ID_USUARIO = EV.ID_USUARIO) AS BAJA_CALIFORNIA_SUR, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Campeche') AS ID_CAMPECHE, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Campeche' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Campeche' AND ID_USUARIO = EV.ID_USUARIO) AS CAMPECHE, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Chiapas') AS ID_CHIAPAS, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Chiapas' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Chiapas' AND ID_USUARIO = EV.ID_USUARIO) AS CHIAPAS, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Chihuahua') AS ID_CHIHUAHUA, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Chihuahua' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Chihuahua' AND ID_USUARIO = EV.ID_USUARIO) AS CHIHUAHUA, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Coahuila') AS ID_COAHUILA, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Coahuila' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Coahuila' AND ID_USUARIO = EV.ID_USUARIO) AS COAHUILA, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Colima') AS ID_COLIMA, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Colima' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Colima' AND ID_USUARIO = EV.ID_USUARIO) AS COLIMA, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Distrito Federal') AS ID_DISTRITO_FEDERAL, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Distrito Federal' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Distrito Federal' AND ID_USUARIO = EV.ID_USUARIO) AS DISTRITO_FEDERAL, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Durango') AS ID_DURANGO, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Durango' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Durango' AND ID_USUARIO = EV.ID_USUARIO) AS DURANGO, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Guanajuato') AS ID_GUANAJUATO, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Guanajuato' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Guanajuato' AND ID_USUARIO = EV.ID_USUARIO) AS GUANAJUATO, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Guerrero') AS ID_GUERRERO, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Guerrero' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Guerrero' AND ID_USUARIO = EV.ID_USUARIO) AS GUERRERO, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Hidalgo') AS ID_HIDALGO, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Hidalgo' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Hidalgo' AND ID_USUARIO = EV.ID_USUARIO) AS HIDALGO, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Jalisco') AS ID_JALISCO, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Jalisco' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Jalisco' AND ID_USUARIO = EV.ID_USUARIO) AS JALISCO, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Estado de México') AS ID_ESTADO_DE_MEXICO, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Estado de México' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Estado de México' AND ID_USUARIO = EV.ID_USUARIO) AS ESTADO_DE_MEXICO, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Michoacán') AS ID_MICHOACAN, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Michoacán' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Michoacán' AND ID_USUARIO = EV.ID_USUARIO) AS MICHOACAN, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Morelos') AS ID_MORELOS, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Morelos' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Morelos' AND ID_USUARIO = EV.ID_USUARIO) AS MORELOS, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Nayarit') AS ID_NAYARIT, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Nayarit' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Nayarit' AND ID_USUARIO = EV.ID_USUARIO) AS NAYARIT, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Nuevo León') AS ID_NUEVO_LEON, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Nuevo León' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Nuevo León' AND ID_USUARIO = EV.ID_USUARIO) AS NUEVO_LEON, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Oaxaca') AS ID_OAXACA, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Oaxaca' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Oaxaca' AND ID_USUARIO = EV.ID_USUARIO) AS OAXACA, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Puebla') AS ID_PUEBLA, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Puebla' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Puebla' AND ID_USUARIO = EV.ID_USUARIO) AS PUEBLA, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Querétaro') AS ID_QUERETARO, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Querétaro' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Querétaro' AND ID_USUARIO = EV.ID_USUARIO) AS QUERETARO, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Quintana Roo') AS ID_QUINTANA_ROO, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Quintana Roo' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Quintana Roo' AND ID_USUARIO = EV.ID_USUARIO) AS QUINTANA_ROO, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'San Luís Potosí') AS ID_SAN_LUIS_POTOSI,	");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'San Luís Potosí' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'San Luís Potosí' AND ID_USUARIO = EV.ID_USUARIO) AS SAN_LUIS_POTOSI, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Sinaloa') AS ID_SINALOA, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Sinaloa' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Sinaloa' AND ID_USUARIO = EV.ID_USUARIO) AS SINALOA, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Sonora') AS ID_SONORA, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Sonora' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Sonora' AND ID_USUARIO = EV.ID_USUARIO) AS SONORA, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Tabasco') AS ID_TABASCO, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Tabasco' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Tabasco' AND ID_USUARIO = EV.ID_USUARIO) AS TABASCO, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Tamaulipas') AS ID_TAMAULIPAS, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Tamaulipas' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Tamaulipas' AND ID_USUARIO = EV.ID_USUARIO) AS TAMAULIPAS, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Tlaxcala') AS ID_TLAXCALA, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Tlaxcala' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Tlaxcala' AND ID_USUARIO = EV.ID_USUARIO) AS TLAXCALA, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Veracruz') AS ID_VERACRUZ, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Veracruz' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Veracruz' AND ID_USUARIO = EV.ID_USUARIO) AS VERACRUZ, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Yucatán') AS ID_YUCATAN, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Yucatán' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Yucatán' AND ID_USUARIO = EV.ID_USUARIO) AS YUCATAN, ");
		query.append("(SELECT CASE ID_ESTADO_VENTA WHEN null THEN 0 ELSE ID_ESTADO_VENTA END FROM INFRA.ESTADOS_VENTA WHERE ID_USUARIO = EV.ID_USUARIO AND ESTADO_VENTA = 'Zacatecas') AS ID_ZACATECAS, ");
		query.append("(SELECT CASE COUNT(*) WHEN 1 THEN 'Zacatecas' ELSE null END FROM INFRA.ESTADOS_VENTA WHERE ESTADO_VENTA = 'Zacatecas' AND ID_USUARIO = EV.ID_USUARIO) AS ZACATECAS ");
		query.append("FROM INFRA.ESTADOS_VENTA AS EV ");
		query.append("WHERE EV.ID_USUARIO = " + id);
		query.append("GROUP BY EV.ID_USUARIO");
		log.debug("query=" + query);
		log.debug(id);

		if (id == 0)
			return null;
		try {
			result = (EstadosVenta) getJdbcTemplate().queryForObject(
					query.toString(), new EstadosVentasRowMapper());
		} catch (EmptyResultDataAccessException e) {
			log.warn("Sin resultado al ejecutar la consulta.");
		}

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class EstadosVentasRowMapper implements RowMapper {

		@Override
		public EstadosVenta mapRow(ResultSet rs, int ln) throws SQLException {
			EstadosVenta est = new EstadosVenta();
			est.setIdNacional(rs.getInt("ID_NACIONAL"));
			est.setNacional(rs.getString("NACIONAL"));
			est.setIdAguascalientes(rs.getInt("ID_AGUASCALIENTES"));
			est.setAguascalientes(rs.getString("AGUASCALIENTES"));
			est.setIdBajaCaliforniaNorte(rs.getInt("ID_BAJA_CALIFORNIA_NORTE"));
			est.setBajaCaliforniaNorte(rs.getString("BAJA_CALIFORNIA_NORTE"));
			est.setIdBajaCaliforniaSur(rs.getInt("ID_BAJA_CALIFORNIA_SUR"));
			est.setBajaCaliforniaSur(rs.getString("BAJA_CALIFORNIA_SUR"));
			est.setIdCampeche(rs.getInt("ID_CAMPECHE"));
			est.setCampeche(rs.getString("CAMPECHE"));
			est.setIdChiapas(rs.getInt("ID_CHIAPAS"));
			est.setChiapas(rs.getString("CHIAPAS"));
			est.setIdChihuahua(rs.getInt("ID_CHIHUAHUA"));
			est.setChihuahua(rs.getString("CHIHUAHUA"));
			est.setIdCoahuila(rs.getInt("ID_COAHUILA"));
			est.setCoahuila(rs.getString("COAHUILA"));
			est.setIdColima(rs.getInt("ID_COLIMA"));
			est.setColima(rs.getString("COLIMA"));
			est.setIdDistritoFederal(rs.getInt("ID_DISTRITO_FEDERAL"));
			est.setDistritoFederal(rs.getString("DISTRITO_FEDERAL"));
			est.setIdDurango(rs.getInt("ID_DURANGO"));
			est.setDurango(rs.getString("DURANGO"));
			est.setIdGuanajuato(rs.getInt("ID_GUANAJUATO"));
			est.setGuanajuato(rs.getString("GUANAJUATO"));
			est.setIdGuerrero(rs.getInt("ID_GUERRERO"));
			est.setGuerrero(rs.getString("GUERRERO"));
			est.setIdHidalgo(rs.getInt("ID_HIDALGO"));
			est.setHidalgo(rs.getString("HIDALGO"));
			est.setIdJalisco(rs.getInt("ID_JALISCO"));
			est.setJalisco(rs.getString("JALISCO"));
			est.setIdEstadoDeMexico(rs.getInt("ID_ESTADO_DE_MEXICO"));
			est.setEstadoDeMexico(rs.getString("ESTADO_DE_MEXICO"));
			est.setIdMichoacan(rs.getInt("ID_MICHOACAN"));
			est.setMichoacan(rs.getString("MICHOACAN"));
			est.setIdMorelos(rs.getInt("ID_MORELOS"));
			est.setMorelos(rs.getString("MORELOS"));
			est.setIdNayarit(rs.getInt("ID_NAYARIT"));
			est.setNayarit(rs.getString("NAYARIT"));
			est.setIdNuevoLeon(rs.getInt("ID_NUEVO_LEON"));
			est.setNuevoLeon(rs.getString("NUEVO_LEON"));
			est.setIdOaxaca(rs.getInt("ID_OAXACA"));
			est.setOaxaca(rs.getString("OAXACA"));
			est.setIdPuebla(rs.getInt("ID_PUEBLA"));
			est.setPuebla(rs.getString("PUEBLA"));
			est.setIdQueretaro(rs.getInt("ID_QUERETARO"));
			est.setQueretaro(rs.getString("QUERETARO"));
			est.setIdQuintanaRoo(rs.getInt("ID_QUINTANA_ROO"));
			est.setQuintanaRoo(rs.getString("QUINTANA_ROO"));
			est.setIdSanLuisPotosi(rs.getInt("ID_SAN_LUIS_POTOSI"));
			est.setSanLuisPotosi(rs.getString("SAN_LUIS_POTOSI"));
			est.setIdSinaloa(rs.getInt("ID_SINALOA"));
			est.setSinaloa(rs.getString("SINALOA"));
			est.setIdSonora(rs.getInt("ID_SONORA"));
			est.setSonora(rs.getString("SONORA"));
			est.setIdTabasco(rs.getInt("ID_TABASCO"));
			est.setTabasco(rs.getString("TABASCO"));
			est.setIdTamaulipas(rs.getInt("ID_TAMAULIPAS"));
			est.setTamaulipas(rs.getString("TAMAULIPAS"));
			est.setIdTlaxcala(rs.getInt("ID_TLAXCALA"));
			est.setTlaxcala(rs.getString("TLAXCALA"));
			est.setIdVeracruz(rs.getInt("ID_VERACRUZ"));
			est.setVeracruz(rs.getString("VERACRUZ"));
			est.setIdYucatan(rs.getInt("ID_YUCATAN"));
			est.setYucatan(rs.getString("YUCATAN"));
			est.setIdZacatecas(rs.getInt("ID_ZACATECAS"));
			est.setZacatecas(rs.getString("ZACATECAS"));

			return est;
		}
	}

	@SuppressWarnings("unchecked")
	public String getIdIndicadores(int id) throws DaoException {
		log.debug("getIdIndicadores()");

		String result;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("ID_INDICADOR ");
		query.append("FROM INFRA.INDICADORES ");
		query.append("WHERE ID_PYME = " + id);
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
	public Indicadores getIndicadores(int id) throws DaoException {
		log.debug("getIndicadores()");

		Indicadores result = null;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("ID_INDICADOR, ");
		query.append("ID_PYME, ");
		query.append("INGRESOS_ANTES, ");
		query.append("INGRESOS_DESPUES, ");
		query.append("CLIENTES_ANTES, ");
		query.append("CLIENTES_DESPUES, ");
		query.append("EMPLEADOS_ANTES, ");
		query.append("EMPLEADOS_DESPUES, ");
		query.append("EGRESOS_ANTES, ");
		query.append("EGRESOS_DESPUES ");
		query.append("FROM INFRA.INDICADORES ");
		query.append("WHERE ID_INDICADOR = " + id);
		log.debug("query=" + query);
		log.debug("ID=" + id);

		if (id == 0) {
			return null;
		}
		result = (Indicadores) getJdbcTemplate().queryForObject(
				query.toString(), new IndicadoresRowMapper());

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class IndicadoresRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			IndicadoresResultSetExtractor extractor = new IndicadoresResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class IndicadoresResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Indicadores ind = new Indicadores();
			ind.setIdIndicador(rs.getInt("ID_INDICADOR"));
			ind.setIdPyME(rs.getInt("ID_PYME"));
			ind.setIngresosAntes(rs.getInt("INGRESOS_ANTES"));
			ind.setIngresosDespues(rs.getInt("INGRESOS_DESPUES"));
			ind.setClientesAntes(rs.getInt("CLIENTES_ANTES"));
			ind.setClientesDespues(rs.getInt("CLIENTES_DESPUES"));
			ind.setEmpleadosAntes(rs.getInt("EMPLEADOS_ANTES"));
			ind.setEmpleadosDespues(rs.getInt("EMPLEADOS_DESPUES"));
			ind.setEgresosAntes(rs.getInt("EGRESOS_ANTES"));
			ind.setEgresosDespues(rs.getInt("EGRESOS_DESPUES"));
			return ind;
		}
	}

	public Mensaje updatePyMEs(PyMEs pyMEs, EstadosVenta estadosVenta)
			throws JdbcDaoException {
		log.debug("updatePyMEs()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.PYMES SET ");
		query.append("PERSONALIDAD_JURIDICA = '");
		query.append(pyMEs.getPersonalidadJuridica());
		query.append("', ");
		query.append("RFC = '");
		query.append(pyMEs.getRfc());
		query.append("', ");
		query.append("CORREO_ELECTRONICO = '");
		query.append(pyMEs.getCorreoElectronico());
		query.append("', ");
		query.append("NOMBRE_COMERCIAL = '");
		query.append(pyMEs.getNombreComercial());
		query.append("', ");
		query.append("NOMBRE_FISCAL = '");
		query.append(pyMEs.getNombreFiscal());
		query.append("', ");
		query.append("NUMERO_EMPLEADOS = '");
		query.append(pyMEs.getNumeroEmpleados());
		query.append("', ");
		query.append("MENSAJE_VENTAS = '");
		query.append(pyMEs.getMensajeVentas());
		query.append("', ");
		query.append("PAGINA_WEB = '");
		query.append(pyMEs.getPaginaWeb());
		query.append("', ");
		query.append("VENTAS_ANUALES = '");
		query.append(pyMEs.getVentasAnuales());
		query.append("', ");
		query.append("CVE_SCIAN = '");
		query.append(pyMEs.getCveScian());
		query.append("', ");
		query.append("B_PRIMER_NIVEL = '");
		query.append(pyMEs.isbPrimerNivel());
		query.append("', ");
		query.append("B_SEGUNDO_NIVEL = '");
		query.append(pyMEs.isbSegundoNivel());
		query.append("', ");
		query.append("B_TERCER_NIVEL = '");
		query.append(pyMEs.isbTercerNivel());
		query.append("', ");
		query.append("B_DIPLOMADO_CCMX_UNO = '");
		query.append(pyMEs.isbDiplomadoCcmxUno());
		query.append("', ");
		query.append("B_DIPLOMADO_CCMX_DOS = '");
		query.append(pyMEs.isbDiplomadoCcmxDos());
		query.append("', ");
		query.append("B_DIPLOMADO_CCMX_TRES = '");
		query.append(pyMEs.isbDiplomadoCcmxTres());
		query.append("', ");
		query.append("B_DIPLOMADO_CCMX_CUATRO = '");
		query.append(pyMEs.isbDiplomadoCcmxCuatro());
		query.append("', ");
		query.append("B_RECIBE_REQUERIMIENTOS_COMPRA = '");
		query.append(pyMEs.isbRecibeRequerimientosCompra());
		query.append("', ");
		query.append("CVE_SCIAN_REQUERIMIENTOS_COMPRA = '");
		query.append(pyMEs.getCveScianRequerimientosCompra());
		query.append("', ");
		query.append("B_SERVICIOS_CCMX_DIPLOMADOS = '");
		query.append(pyMEs.isbServiciosCcmxDiplomados());
		query.append("', ");
		query.append("B_SERVICIOS_CCMX_CONSULTORIA = '");
		query.append(pyMEs.isbServiciosCcmxConsultoria());
		query.append("'");
		query.append(" WHERE ID_USUARIO = ");
		query.append(pyMEs.getIdUsuario());
		query.append(" ");
		log.debug("query=" + query);

		try {
			Documento d = null;
			EstadosVenta est = null;
			boolean result = true;

			getJdbcTemplate().update(query.toString());

			int idPyME = pyMEs.getIdUsuario();
			
			/* Sección de Estados de Ventas */
			if (estadosVenta.getIdNacional() == 0 && estadosVenta.getNacional().length() > 2) {
				log.debug("Insertando ID_NACIONAL = " + estadosVenta.getNacional());
				est = new EstadosVenta();
				est.setIdUsuario(idPyME);
				est.setEstadoVenta(estadosVenta.getNacional());
				result = saveEstadoVenta(est).getRespuesta() == 0;
			} else if (estadosVenta.getIdNacional() != 0 && estadosVenta.getNacional().length() == 0) {
				log.debug("Eliminando ID_NACIONAL = " + estadosVenta.getNacional());
				est = new EstadosVenta();
				est.setIdEstadoVenta(estadosVenta.getIdNacional());
				result = deleteEstadoVenta(est).getRespuesta() == 0;
			}

			if (result) {
				if (estadosVenta.getIdAguascalientes() == 0 && estadosVenta.getAguascalientes().length() > 0) {
					log.debug("Insertando ID_AGUASCALIENTES = " + estadosVenta.getAguascalientes());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getAguascalientes());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdAguascalientes() != 0 && estadosVenta.getAguascalientes().length() == 0) {
					log.debug("Eliminando ID_AGUASCALIENTES = " + estadosVenta.getAguascalientes());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdAguascalientes());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdBajaCaliforniaNorte() == 0 && estadosVenta.getBajaCaliforniaNorte().length() > 0) {
					log.debug("Insertando ID_BAJA_CALIFORNIA_NORTE = " + estadosVenta.getBajaCaliforniaNorte());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getBajaCaliforniaNorte());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdBajaCaliforniaNorte() != 0 && estadosVenta.getBajaCaliforniaNorte().length() == 0) {
					log.debug("Eliminando ID_BAJA_CALIFORNIA_NORTE = " + estadosVenta.getBajaCaliforniaNorte());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdBajaCaliforniaNorte());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdBajaCaliforniaSur() == 0 && estadosVenta.getBajaCaliforniaSur().length() > 0) {
					log.debug("Insertando ID_BAJA_CALIFORNIA_SUR = " + estadosVenta.getBajaCaliforniaSur());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getBajaCaliforniaSur());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdBajaCaliforniaSur() != 0 && estadosVenta.getBajaCaliforniaSur().length() == 0) {
					log.debug("Eliminando ID_BAJA_CALIFORNIA_SUR = " + estadosVenta.getBajaCaliforniaSur());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdBajaCaliforniaSur());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdCampeche() == 0 && estadosVenta.getCampeche().length() > 0) {
					log.debug("Insertando ID_CAMPECHE = " + estadosVenta.getCampeche());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getCampeche());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdCampeche() != 0 && estadosVenta.getCampeche().length() == 0) {
					log.debug("Eliminando ID_CAMPECHE = " + estadosVenta.getCampeche());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdCampeche());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdChiapas() == 0 && estadosVenta.getChiapas().length() > 0) {
					log.debug("Insertando ID_CHIAPAS = " + estadosVenta.getChiapas());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getChiapas());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdChiapas() != 0 && estadosVenta.getChiapas().length() == 0) {
					log.debug("Eliminando ID_CHIAPAS = " + estadosVenta.getBajaCaliforniaNorte());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdChiapas());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdChihuahua() == 0 && estadosVenta.getChihuahua().length() > 0) {
					log.debug("Insertando ID_CHIHUAHUA = " + estadosVenta.getChihuahua());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getChihuahua());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdChihuahua() != 0 && estadosVenta.getChihuahua().length() == 0) {
					log.debug("Eliminando ID_CHIHUAHUA = " + estadosVenta.getChihuahua());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdChihuahua());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdCoahuila() == 0 && estadosVenta.getCoahuila().length() > 0) {
					log.debug("Insertando ID_COAHUILA = " + estadosVenta.getCoahuila());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getCoahuila());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdCoahuila() != 0 && estadosVenta.getCoahuila().length() == 0) {
					log.debug("Eliminando ID_COAHUILA = " + estadosVenta.getCoahuila());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdCoahuila());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdColima() == 0 && estadosVenta.getColima().length() > 0) {
					log.debug("Insertando ID_COLIMA = " + estadosVenta.getColima());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getColima());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdColima() != 0 && estadosVenta.getColima().length() == 0) {
					log.debug("Eliminando ID_COLIMA = " + estadosVenta.getColima());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdColima());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdDistritoFederal() == 0 && estadosVenta.getDistritoFederal().length() > 0) {
					log.debug("Insertando ID_DISTRITO_FEDERAL = " + estadosVenta.getDistritoFederal());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getDistritoFederal());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdDistritoFederal() != 0 && estadosVenta.getDistritoFederal().length() == 0) {
					log.debug("Eliminando ID_DISTRITO_FEDERAL = " + estadosVenta.getDistritoFederal());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdDistritoFederal());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdDurango() == 0
						&& estadosVenta.getDurango().length() > 0) {
					log.debug("Insertando ID_DURANGO = "
							+ estadosVenta.getDurango());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getDurango());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdDurango() != 0
						&& estadosVenta.getDurango().length() == 0) {
					log.debug("Eliminando ID_DURANGO = "
							+ estadosVenta.getDurango());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdDurango());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdGuanajuato() == 0
						&& estadosVenta.getGuanajuato().length() > 0) {
					log.debug("Insertando ID_GUANAJUATO = "
							+ estadosVenta.getGuanajuato());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getGuanajuato());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdGuanajuato() != 0
						&& estadosVenta.getGuanajuato().length() == 0) {
					log.debug("Eliminando ID_GUANAJUATO = "
							+ estadosVenta.getGuanajuato());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdGuanajuato());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdGuerrero() == 0
						&& estadosVenta.getGuerrero().length() > 0) {
					log.debug("Insertando ID_GUERRERO = "
							+ estadosVenta.getGuerrero());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getGuerrero());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdGuerrero() != 0
						&& estadosVenta.getGuerrero().length() == 0) {
					log.debug("Eliminando ID_GUERRERO = "
							+ estadosVenta.getGuerrero());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdGuerrero());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdHidalgo() == 0
						&& estadosVenta.getHidalgo().length() > 0) {
					log.debug("Insertando ID_HIDALGO = "
							+ estadosVenta.getHidalgo());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getHidalgo());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdHidalgo() != 0
						&& estadosVenta.getHidalgo().length() == 0) {
					log.debug("Eliminando ID_HIDALGO = "
							+ estadosVenta.getHidalgo());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdHidalgo());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdJalisco() == 0
						&& estadosVenta.getJalisco().length() > 0) {
					log.debug("Insertando ID_JALISCO = "
							+ estadosVenta.getJalisco());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getJalisco());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdJalisco() != 0
						&& estadosVenta.getJalisco().length() == 0) {
					log.debug("Eliminando ID_JALISCO = "
							+ estadosVenta.getJalisco());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdJalisco());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdEstadoDeMexico() == 0
						&& estadosVenta.getEstadoDeMexico().length() > 0) {
					log.debug("Insertando ID_ESTADO_DE_MEXICO = "
							+ estadosVenta.getEstadoDeMexico());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getEstadoDeMexico());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdEstadoDeMexico() != 0
						&& estadosVenta.getEstadoDeMexico().length() == 0) {
					log.debug("Eliminando ID_ESTADO_DE_MEXICO = "
							+ estadosVenta.getEstadoDeMexico());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdEstadoDeMexico());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdMichoacan() == 0
						&& estadosVenta.getMichoacan().length() > 0) {
					log.debug("Insertando ID_MICHOACAN = "
							+ estadosVenta.getMichoacan());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getMichoacan());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdMichoacan() != 0
						&& estadosVenta.getMichoacan().length() == 0) {
					log.debug("Eliminando ID_MICHOACAN = "
							+ estadosVenta.getMichoacan());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdMichoacan());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdMorelos() == 0
						&& estadosVenta.getMorelos().length() > 0) {
					log.debug("Insertando ID_MORELOS = "
							+ estadosVenta.getMorelos());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getMorelos());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdMorelos() != 0
						&& estadosVenta.getMorelos().length() == 0) {
					log.debug("Eliminando ID_MORELOS = "
							+ estadosVenta.getMorelos());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdMorelos());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdNayarit() == 0
						&& estadosVenta.getNayarit().length() > 0) {
					log.debug("Insertando ID_NAYARIT = "
							+ estadosVenta.getNayarit());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getNayarit());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdNayarit() != 0
						&& estadosVenta.getNayarit().length() == 0) {
					log.debug("Eliminando ID_NAYARIT = "
							+ estadosVenta.getNayarit());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdNayarit());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdNuevoLeon() == 0
						&& estadosVenta.getNuevoLeon().length() > 0) {
					log.debug("Insertando ID_NUEVO_LEON = "
							+ estadosVenta.getNuevoLeon());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getNuevoLeon());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdNuevoLeon() != 0
						&& estadosVenta.getNuevoLeon().length() == 0) {
					log.debug("Eliminando ID_NUEVO_LEON = "
							+ estadosVenta.getNuevoLeon());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdNuevoLeon());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdOaxaca() == 0
						&& estadosVenta.getOaxaca().length() > 0) {
					log.debug("Insertando ID_OAXACA = " + estadosVenta.getOaxaca());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getOaxaca());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdOaxaca() != 0
						&& estadosVenta.getOaxaca().length() == 0) {
					log.debug("Eliminando ID_OAXACA = " + estadosVenta.getOaxaca());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdOaxaca());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdPuebla() == 0
						&& estadosVenta.getPuebla().length() > 0) {
					log.debug("Insertando ID_PUEBLA = " + estadosVenta.getPuebla());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getPuebla());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdPuebla() != 0
						&& estadosVenta.getPuebla().length() == 0) {
					log.debug("Eliminando ID_PUEBLA = " + estadosVenta.getPuebla());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdPuebla());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdQueretaro() == 0
						&& estadosVenta.getQueretaro().length() > 0) {
					log.debug("Insertando ID_QUERETARO = "
							+ estadosVenta.getQueretaro());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getQueretaro());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdQueretaro() != 0
						&& estadosVenta.getQueretaro().length() == 0) {
					log.debug("Eliminando ID_QUERETARO = "
							+ estadosVenta.getQueretaro());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdQueretaro());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdQuintanaRoo() == 0
						&& estadosVenta.getQuintanaRoo().length() > 0) {
					log.debug("Insertando ID_QUINTANA_ROO = "
							+ estadosVenta.getQuintanaRoo());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getQuintanaRoo());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdQuintanaRoo() != 0
						&& estadosVenta.getQuintanaRoo().length() == 0) {
					log.debug("Eliminando ID_QUINTANA_ROO = "
							+ estadosVenta.getQuintanaRoo());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdQuintanaRoo());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdSanLuisPotosi() == 0
						&& estadosVenta.getSanLuisPotosi().length() > 0) {
					log.debug("Insertando ID_SAN_LUIS_POTOSI = "
							+ estadosVenta.getSanLuisPotosi());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getSanLuisPotosi());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdSanLuisPotosi() != 0
						&& estadosVenta.getSanLuisPotosi().length() == 0) {
					log.debug("Eliminando ID_SAN_LUIS_POTOSI = "
							+ estadosVenta.getSanLuisPotosi());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdSanLuisPotosi());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdSinaloa() == 0
						&& estadosVenta.getSinaloa().length() > 0) {
					log.debug("Insertando ID_SINALOA = "
							+ estadosVenta.getSinaloa());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getSinaloa());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdSinaloa() != 0
						&& estadosVenta.getSinaloa().length() == 0) {
					log.debug("Eliminando ID_SINALOA = "
							+ estadosVenta.getSinaloa());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdSinaloa());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdSonora() == 0
						&& estadosVenta.getSonora().length() > 0) {
					log.debug("Insertando ID_SONORA = " + estadosVenta.getSonora());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getSonora());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdSonora() != 0
						&& estadosVenta.getSonora().length() == 0) {
					log.debug("Eliminando ID_SONORA = " + estadosVenta.getSonora());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdSonora());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdTabasco() == 0
						&& estadosVenta.getTabasco().length() > 0) {
					log.debug("Insertando ID_TABASCO = "
							+ estadosVenta.getTabasco());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getTabasco());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdTabasco() != 0
						&& estadosVenta.getTabasco().length() == 0) {
					log.debug("Eliminando ID_TABASCO = "
							+ estadosVenta.getTabasco());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdTabasco());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdTamaulipas() == 0
						&& estadosVenta.getTamaulipas().length() > 0) {
					log.debug("Insertando ID_TAMAULIPAS = "
							+ estadosVenta.getTamaulipas());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getTamaulipas());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdTamaulipas() != 0
						&& estadosVenta.getTamaulipas().length() == 0) {
					log.debug("Eliminando ID_TAMAULIPAS = "
							+ estadosVenta.getTamaulipas());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdTamaulipas());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdTlaxcala() == 0
						&& estadosVenta.getTlaxcala().length() > 0) {
					log.debug("Insertando ID_TLAXCALA = "
							+ estadosVenta.getTlaxcala());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getTlaxcala());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdTlaxcala() != 0
						&& estadosVenta.getTlaxcala().length() == 0) {
					log.debug("Eliminando ID_TLAXCALA = "
							+ estadosVenta.getTlaxcala());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdTlaxcala());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdVeracruz() == 0
						&& estadosVenta.getVeracruz().length() > 0) {
					log.debug("Insertando ID_VERACRUZ = "
							+ estadosVenta.getVeracruz());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getVeracruz());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdVeracruz() != 0
						&& estadosVenta.getVeracruz().length() == 0) {
					log.debug("Eliminando ID_VERACRUZ = "
							+ estadosVenta.getVeracruz());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdVeracruz());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			if (result) {
				if (estadosVenta.getIdYucatan() == 0
						&& estadosVenta.getYucatan().length() > 0) {
					log.debug("Insertando ID_YUCATAN = "
							+ estadosVenta.getYucatan());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getYucatan());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdYucatan() != 0
						&& estadosVenta.getYucatan().length() == 0) {
					log.debug("Eliminando ID_YUCATAN = "
							+ estadosVenta.getYucatan());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdYucatan());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}
			
			if (result) {
				if (estadosVenta.getIdZacatecas() == 0
						&& estadosVenta.getZacatecas().length() > 0) {
					log.debug("Insertando ID_ZACATECAS = "
							+ estadosVenta.getZacatecas());
					est = new EstadosVenta();
					est.setIdUsuario(idPyME);
					est.setEstadoVenta(estadosVenta.getZacatecas());
					result = saveEstadoVenta(est).getRespuesta() == 0;
				} else if (estadosVenta.getIdZacatecas() != 0
						&& estadosVenta.getZacatecas().length() == 0) {
					log.debug("Eliminando ID_ZACATECAS = "
							+ estadosVenta.getZacatecas());
					est = new EstadosVenta();
					est.setIdEstadoVenta(estadosVenta.getIdZacatecas());
					result = deleteEstadoVenta(est).getRespuesta() == 0;
				}
			}

			
			
			if (result) {
				/* Sección de Productos */
				if (pyMEs.getProductos() != null) {
					Iterator<Productos> i = pyMEs.getProductos().iterator();
					Productos prod = null;
					while (i.hasNext()) {
						prod = i.next();
						if (prod != null && prod.getIdProducto() == 0 && !Null.free(prod.getProducto()).isEmpty()) {
							prod.setIdUsuario(idPyME);
							log.debug("Insertando Producto... " + prod.getProducto());
							saveProductos(prod);
						}else if(prod != null && prod.getIdProducto() != 0 && !Null.free(prod.getProducto()).isEmpty()){
							log.debug("Actualizando Producto... " + prod.getIdProducto());
							updateProducto(prod);
						}else if(prod.getIdProducto() != 0 && Null.free(prod.getProducto()).isEmpty()){
							log.debug("Eliminando Producto... " + prod.getProducto());
							deleteProducto(prod);
						}
					}
				}
			}
			
			if (result) {
				/* Sección de contactos */
				if (pyMEs.getContactos() != null) {
					Iterator<Contacto> ic = pyMEs.getContactos().iterator();
					Contacto cont = null;
					while (ic.hasNext()) {
						cont = ic.next();
						if (cont != null && cont.getIdContacto() == 0 && !Null.free(cont.getNombre()).isEmpty()) {
							log.debug("Insertando Contacto... " + cont.getNombre());
							cont.setIdUsuario(idPyME);
							result = saveContacto(cont).getRespuesta() == 0;
						}else if(cont != null && cont.getIdContacto() != 0 && !Null.free(cont.getNombre()).isEmpty()){
							log.debug("Actualizando Contacto... " + cont.getNombre());
							result = updateContacto(cont).getRespuesta() == 0;
						}else if(cont.getIdContacto() != 0 && Null.free(cont.getNombre()).isEmpty()){
							log.debug("Eliminando Contacto... " + cont.getIdContacto());
							result = deleteContacto(cont).getRespuesta() == 0;
						}
					}
				}
			}
			
			if (result) {
				/* Sección de clientes */
				if (pyMEs.getClientes() != null) {
					Iterator<Clientes> icli = pyMEs.getClientes().iterator();
					Clientes client = null;
					while (icli.hasNext()) {
						client = icli.next();
						if (client != null && client.getIdCliente() == 0 && !Null.free(client.getCliente()).isEmpty()) {
							log.debug("Insertando Cliente... " + client.getCliente());
							client.setIdUsuario(idPyME);
							result = saveClientes(client).getRespuesta() == 0;
						}else if(client != null && client.getIdCliente() != 0 && !Null.free(client.getCliente()).isEmpty()){
							log.debug("Actualizando Cliente... " + client.getCliente());
							result = updateCliente(client).getRespuesta() == 0;
						}else if(client.getIdCliente() != 0 && Null.free(client.getCliente()).isEmpty()){
							log.debug("Eliminando Cliente... " + client.getIdCliente());
							result = deleteCliente(client).getRespuesta() == 0;
						}
					}
				}
			}
			
			if (result) {
				/* Sección de Certificaciones */
				if (pyMEs.getCertificaciones() != null) {
					Iterator<Certificaciones> icer = pyMEs.getCertificaciones().iterator();
					Certificaciones cert = null;
					while (icer.hasNext()) {
						cert = icer.next();
						if (cert != null && cert.getIdCertificado() == 0 && !Null.free(cert.getCertificacion()).isEmpty()) {
							log.debug("Insertando Certificacion... " + cert);
							cert.setIdUsuario(idPyME);
							result = saveCertificaciones(cert).getRespuesta() == 0;
						}else if(cert != null && cert.getIdCertificado() != 0 && !Null.free(cert.getCertificacion()).isEmpty()){
							log.debug("Actualizando Certificacion... " + cert);
							result = updateCertificaciones(cert).getRespuesta() == 0;
						}else if(cert.getIdCertificado() != 0 && Null.free(cert.getCertificacion()).isEmpty()){
							log.debug("Eliminando Certificacion... " + cert);
							result = deleteCertificacion(cert).getRespuesta() == 0;
						}
					}
				}
				
			}
			
			if (result) {
				/* Sección de Archivos */
				if (pyMEs.getArchivo1() != null) {
					log.debug("Insertando el Archivo 1 = " + pyMEs.getArchivo1());
					d = new Documento();
					d.setIs(pyMEs.getArchivo1());
					d.setIdUsuario(idPyME);
					d.setNombre(pyMEs.getArchivo1FileName());
					d.setDescripcionArchivo(pyMEs.getDescArchivo1());
					result = insertDocumento(d).getRespuesta() == 0;
				}
				if (pyMEs.getArchivo2() != null) {
					log.debug("Insertando el Archivo 2 = " + pyMEs.getArchivo2());
					d = new Documento();
					d.setIs(pyMEs.getArchivo2());
					d.setIdUsuario(idPyME);
					d.setNombre(pyMEs.getArchivo2FileName());
					d.setDescripcionArchivo(pyMEs.getDescArchivo2());
					result = insertDocumento(d).getRespuesta() == 0;
				}
				if (pyMEs.getArchivo3() != null) {
					log.debug("Insertando el Archivo 3 = " + pyMEs.getArchivo3());
					d = new Documento();
					d.setIs(pyMEs.getArchivo3());
					d.setIdUsuario(idPyME);
					d.setNombre(pyMEs.getArchivo3FileName());
					d.setDescripcionArchivo(pyMEs.getDescArchivo3());
					result = insertDocumento(d).getRespuesta() == 0;
				}
				if (pyMEs.getArchivo4() != null) {
					log.debug("Insertando el Archivo 4 = " + pyMEs.getArchivo4());
					d = new Documento();
					d.setIs(pyMEs.getArchivo4());
					d.setIdUsuario(idPyME);
					d.setNombre(pyMEs.getArchivo4FileName());
					d.setDescripcionArchivo(pyMEs.getDescArchivo4());
					result = insertDocumento(d).getRespuesta() == 0;
				}
				if (pyMEs.getArchivo5() != null) {
					log.debug("Insertando el Archivo 5 = " + pyMEs.getArchivo5());
					d = new Documento();
					d.setIs(pyMEs.getArchivo5());
					d.setIdUsuario(idPyME);
					d.setNombre(pyMEs.getArchivo5FileName());
					d.setDescripcionArchivo(pyMEs.getDescArchivo5());
					result = insertDocumento(d).getRespuesta() == 0;
				}
				if (pyMEs.getArchivo6() != null) {
					log.debug("Insertando el Archivo 6 = " + pyMEs.getArchivo6());
					d = new Documento();
					d.setIs(pyMEs.getArchivo6());
					d.setIdUsuario(idPyME);
					d.setNombre(pyMEs.getArchivo6FileName());
					d.setDescripcionArchivo(pyMEs.getDescArchivo6());
					result = insertDocumento(d).getRespuesta() == 0;
				}
				if (pyMEs.getArchivo7() != null) {
					log.debug("Insertando el Archivo 7 = " + pyMEs.getArchivo7());
					d = new Documento();
					d.setIs(pyMEs.getArchivo7());
					d.setIdUsuario(idPyME);
					d.setNombre(pyMEs.getArchivo7FileName());
					d.setDescripcionArchivo(pyMEs.getDescArchivo7());
					result = insertDocumento(d).getRespuesta() == 0;
				}
				if (pyMEs.getArchivo8() != null) {
					log.debug("Insertando el Archivo 8 = " + pyMEs.getArchivo8());
					d = new Documento();
					d.setIs(pyMEs.getArchivo8());
					d.setIdUsuario(idPyME);
					d.setNombre(pyMEs.getArchivo8FileName());
					d.setDescripcionArchivo(pyMEs.getDescArchivo8());
					result = insertDocumento(d).getRespuesta() == 0;
				}
				if (pyMEs.getArchivo9() != null) {
					log.debug("Insertando el Archivo 9 = " + pyMEs.getArchivo9());
					d = new Documento();
					d.setIs(pyMEs.getArchivo9());
					d.setIdUsuario(idPyME);
					d.setNombre(pyMEs.getArchivo9FileName());
					d.setDescripcionArchivo(pyMEs.getDescArchivo9());
					result = insertDocumento(d).getRespuesta() == 0;
				}
				if (pyMEs.getArchivo10() != null) {
					log.debug("Insertando el Archivo 10 = " + pyMEs.getArchivo10());
					d = new Documento();
					d.setIs(pyMEs.getArchivo10());
					d.setIdUsuario(idPyME);
					d.setNombre(pyMEs.getArchivo10FileName());
					d.setDescripcionArchivo(pyMEs.getDescArchivo10());
					result = insertDocumento(d).getRespuesta() == 0;
				}
			}

			if (result) {
				Mensaje m = new Mensaje();
				m.setRespuesta(0);
				m.setMensaje("Los datos de la PyME se actualizaron satisfactoriamente.");
				m.setId(String.valueOf(idPyME));
				return m;
			} else {
				return new Mensaje(
						1,
						"Ocurrio un error al intentar actualizar Los datos de la PyME, intentelo más tarde.");
			}

		} catch (Exception e) {
			log.fatal("ERROR al actualizar los datos de la PyME, " + e);
			return new Mensaje(1,
					"No es posible actualizar los datos de la PyME, intentelo más tarde.");
		}
	}

	public Mensaje saveDomicilios(Domicilios domicilios) throws DaoException {
		log.debug("SaveDomicilios()");

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

	public Mensaje saveRelDomicilios(Domicilios domicilios, PyMEs pyMEs)
			throws DaoException {

		log.debug("saveRelDomicilios()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.REL_DOMICILIOS_USUARIO (");
		query.append("ID_USUARIO, ");
		query.append("ID_DOMICILIO) ");
		query.append("VALUES ('");
		query.append(pyMEs.getIdUsuario());
		query.append("', '");
		query.append(domicilios.getIdDomicilio());
		query.append("')");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos han sido registrados exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar los datos REL_DOMICILIOS, " + e);
			return new Mensaje(1, "No es posible registrar los datos.");
		}
	}

	@Override
	public Mensaje updateDomicilios(Domicilios domicilios)
			throws JdbcDaoException {
		log.debug("updateDomicilios()");

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
					"Los datos de la PyME se actualizaron satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar los datos de la PyME, " + e);
			return new Mensaje(1,
					"No es posible actualizar los datos de la Tractora, intentelo más tarde.");
		}
	}

	public Mensaje saveIndicadores(Indicadores indicadores) throws DaoException {
		log.debug("saveIndicadores()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.INDICADORES (");
		query.append("ID_PYME, ");
		query.append("INGRESOS_ANTES, ");
		query.append("INGRESOS_DESPUES, ");
		query.append("CLIENTES_ANTES, ");
		query.append("CLIENTES_DESPUES, ");
		query.append("EMPLEADOS_ANTES, ");
		query.append("EMPLEADOS_DESPUES, ");
		query.append("EGRESOS_ANTES, ");
		query.append("EGRESOS_DESPUES) ");
		query.append("VALUES ('");
		query.append(indicadores.getIdPyME());
		query.append("', '");
		query.append(indicadores.getIngresosAntes());
		query.append("', '");
		query.append(indicadores.getIngresosDespues());
		query.append("', '");
		query.append(indicadores.getClientesAntes());
		query.append("', '");
		query.append(indicadores.getClientesDespues());
		query.append("', '");
		query.append(indicadores.getEmpleadosAntes());
		query.append("', '");
		query.append(indicadores.getEmpleadosDespues());
		query.append("', '");
		query.append(indicadores.getEgresosAntes());
		query.append("', '");
		query.append(indicadores.getEgresosDespues());
		query.append("')");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos han sido registrados exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar los datos INDICADORES, " + e);
			return new Mensaje(1, "No es posible registrar los datos.");
		}
	}

	@Override
	public Mensaje updateIndicadores(Indicadores indicadores)
			throws JdbcDaoException {
		log.debug("updateIndicadores()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.INDICADORES SET ");
		query.append("INGRESOS_ANTES = '");
		query.append(indicadores.getIngresosAntes());
		query.append("', ");
		query.append("INGRESOS_DESPUES = '");
		query.append(indicadores.getIngresosDespues());
		query.append("', ");
		query.append("CLIENTES_ANTES = '");
		query.append(indicadores.getClientesAntes());
		query.append("', ");
		query.append("CLIENTES_DESPUES = '");
		query.append(indicadores.getClientesDespues());
		query.append("', ");
		query.append("EMPLEADOS_ANTES = '");
		query.append(indicadores.getEmpleadosAntes());
		query.append("', ");
		query.append("EMPLEADOS_DESPUES = '");
		query.append(indicadores.getEmpleadosDespues());
		query.append("', ");
		query.append("EGRESOS_ANTES = '");
		query.append(indicadores.getEgresosAntes());
		query.append("', ");
		query.append("EGRESOS_DESPUES = '");
		query.append(indicadores.getEgresosDespues());
		query.append("' ");
		query.append("WHERE ID_INDICADOR = ");
		query.append(indicadores.getIdIndicador());
		query.append(" ");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos han sido actualizados exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar los datos de los INDICADORES, " + e);
			return new Mensaje(1,
					"No es posible registrar los datos, intentelo más tarde.");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Requerimientos getShowRequerimientos(int idRequerimiento)
			throws DaoException {
		log.debug("getShowRequerimientos()");
		// TODO corregir para traerse lo de la PyME solamente, ya que
		// requerimientos los traemos ahora de tractorasService ;)
		Requerimientos result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("R.ID_REQUERIMIENTO, ");
		query.append("T.EMPRESA, ");
		query.append("R.DESCRIPCION ");
		query.append("FROM INFRA.TRACTORAS AS T ");
		query.append("LEFT JOIN INFRA.REQUERIMIENTOS AS R ");
		query.append("ON T.ID_USUARIO=R.ID_TRACTORA ");
		query.append("WHERE R.ID_REQUERIMIENTO = " + idRequerimiento);
		log.debug("query=" + query);

		if (idRequerimiento == 0)
			return null;
		result = (Requerimientos) getJdbcTemplate().queryForObject(
				query.toString(), new ShowRequerimientosRowMapper());

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class ShowRequerimientosRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			ShowRequerimientosPyMEsResultSetExtractor extractor = new ShowRequerimientosPyMEsResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class ShowRequerimientosPyMEsResultSetExtractor implements
			ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Requerimientos req = new Requerimientos();
			req.setIdRequerimiento(rs.getInt("ID_REQUERIMIENTO"));
			// req.setNombreTractora(rs.getString("EMPRESA"));
			req.setDescripcion(rs.getString("DESCRIPCION"));
			return req;

		}
	}

	@Override
	public Mensaje saveRespuestas(Respuesta respuesta) throws DaoException {
		log.debug("saveRespuestas()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.RESPUESTAS (");
		query.append("ID_REQUERIMIENTO, ");
		query.append("INFORMACION, ");
		query.append("MENSAJE_ENVIO) ");
		query.append("VALUES ('");
		query.append(respuesta.getIdRequerimiento());
		query.append("', '");
		query.append(respuesta.getInformacion());
		query.append("', '");
		query.append(respuesta.getMensajeEnvio());
		query.append("')");
		log.debug("query=" + query);

		try {

			Documento d = null;
			boolean result = true;

			getJdbcTemplate().update(query.toString());

			int id = getIdRespuesta().getIdRespuesta();
			if (respuesta.getArchivo1() != null) {
				d = new Documento();
				d.setIs(respuesta.getArchivo1());
				d.setIdReferencia(id);
				d.setNombre(respuesta.getArchivo1FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (respuesta.getArchivo2() != null) {
				d = new Documento();
				d.setIs(respuesta.getArchivo2());
				d.setIdReferencia(id);
				d.setNombre(respuesta.getArchivo2FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (respuesta.getArchivo3() != null) {
				d = new Documento();
				d.setIs(respuesta.getArchivo3());
				d.setIdReferencia(id);
				d.setNombre(respuesta.getArchivo3FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (respuesta.getArchivo4() != null) {
				d = new Documento();
				d.setIs(respuesta.getArchivo4());
				d.setIdReferencia(id);
				d.setNombre(respuesta.getArchivo4FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (respuesta.getArchivo5() != null) {
				d = new Documento();
				d.setIs(respuesta.getArchivo5());
				d.setIdReferencia(id);
				d.setNombre(respuesta.getArchivo5FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (respuesta.getArchivo6() != null) {
				d = new Documento();
				d.setIs(respuesta.getArchivo6());
				d.setIdReferencia(id);
				d.setNombre(respuesta.getArchivo6FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (respuesta.getArchivo7() != null) {
				d = new Documento();
				d.setIs(respuesta.getArchivo7());
				d.setIdReferencia(id);
				d.setNombre(respuesta.getArchivo7FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (respuesta.getArchivo8() != null) {
				d = new Documento();
				d.setIs(respuesta.getArchivo8());
				d.setIdReferencia(id);
				d.setNombre(respuesta.getArchivo8FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (respuesta.getArchivo9() != null) {
				d = new Documento();
				d.setIs(respuesta.getArchivo9());
				d.setIdReferencia(id);
				d.setNombre(respuesta.getArchivo9FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}
			if (respuesta.getArchivo10() != null) {
				d = new Documento();
				d.setIs(respuesta.getArchivo10());
				d.setIdReferencia(id);
				d.setNombre(respuesta.getArchivo10FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}

			if (result) {
				Mensaje m = new Mensaje();
				m.setRespuesta(0);
				m.setMensaje("Su cotización se envió satisfactoriamente.");
				m.setId(String.valueOf(id));
				return m;
			} else {
				return new Mensaje(1,
						"La respuesta se insertó con errores al guardar el o los documentos.");
			}

		} catch (Exception e) {
			log.fatal("ERROR al salvar la respuesta del requerimiento, " + e);
			return new Mensaje(1,
					"No es posible enviar la respuesta del requerimiento, intentelo más tarde.");
		}
	}

	public Mensaje saveServDiplomados(ServiciosDiplomado serviciosDiplomado)
			throws DaoException {
		log.debug("saveServDiplomados()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.SERVICIOS_DIPLOMADO (");
		query.append("ID_DIPLOMADO, ");
		query.append("ID_USUARIO, ");
		query.append("TITULO, ");
		query.append("FECHA, ");
		query.append("MENSAJE) ");
		query.append("VALUES ('");
		query.append(serviciosDiplomado.getIdDiplomado());
		query.append("', '");
		query.append(serviciosDiplomado.getIdUsuario());
		query.append("', '");
		query.append(serviciosDiplomado.getTitulo());
		query.append("', '");
		query.append(new java.sql.Date(serviciosDiplomado.getFecha().getTime()));
		query.append("', '");
		query.append(serviciosDiplomado.getMensaje());
		query.append("')");
		log.debug("query=" + query);

		try {

			Documento d = null;
			boolean result = true;

			getJdbcTemplate().update(query.toString());

			if (serviciosDiplomado.getArchivo1() != null) {
				log.debug("Insertando el Archivo 1 = "
						+ serviciosDiplomado.getArchivo1());
				d = new Documento();
				d.setIs(serviciosDiplomado.getArchivo1());
				d.setIdReferencia(serviciosDiplomado.getIdDiplomado());
				d.setNombre(serviciosDiplomado.getArchivo1FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}

			if (result) {
				Mensaje m = new Mensaje();
				m.setRespuesta(0);
				m.setMensaje("Estimada PYME ha quedado inscrita en el diplomado seleccionado, en breve nos comunicaremos con ustedes para confirmar su asistencia.");
				m.setId(String.valueOf(serviciosDiplomado.getIdDiplomado()));
				return m;
			} else {
				return new Mensaje(
						1,
						"Estimada PYME ha quedado inscrita en el diplomado seleccionado con errores al guardar el o los documentos.");
			}

		} catch (Exception e) {
			log.fatal("ERROR al salvar la inscripción del diplomado, " + e);
			return new Mensaje(1,
					"No es posible registrar el servicio, intentelo más tarde.");
		}
	}

	public Mensaje saveAsistentes(Asistentes asistentes) throws DaoException {
		log.debug("saveAsistentes()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.ASISTENTES (");
		query.append("ID_DIPLOMADO, ");
		query.append("NOMBRE, ");
		query.append("APP_PATERNO, ");
		query.append("APP_MATERNO) ");
		query.append("VALUES ('");
		query.append(asistentes.getIdDiplomado());
		query.append("', '");
		query.append(asistentes.getNombre());
		query.append("', '");
		query.append(asistentes.getAppPaterno());
		query.append("', '");
		query.append(asistentes.getAppMaterno());
		query.append("')");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(
					0,
					"Estimada PYME ha quedado inscrita en los diplomados seleccionados. En breve nos comunicaremos con ustedes para confirmar su asistencia.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar al asistente, " + e);
			return new Mensaje(1,
					"No es posible realizar el registro, intentelo más tarde.");
		}
	}

	public Mensaje saveConsultorias(ServiciosConsultoria seviciosConsultoria)
			throws DaoException {
		log.debug("saveConsultorias()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.SERVICIOS_CONSULTORIA (");
		query.append("ID_USUARIO, ");
		query.append("B_CONSULTORIA_20, ");
		query.append("B_CONSULTORIA_40, ");
		query.append("B_CONSULTORIA_60, ");
		query.append("B_CONSULTORIA_80, ");
		query.append("MENSAJE) ");
		query.append("VALUES ('");
		query.append(seviciosConsultoria.getIdUsuario());
		query.append("', ");
		query.append(seviciosConsultoria.isbConsultoriaVeinte());
		query.append(", ");
		query.append(seviciosConsultoria.isbConsultoriaCuarenta());
		query.append(", ");
		query.append(seviciosConsultoria.isbConsultoriaSesenta());
		query.append(", ");
		query.append(seviciosConsultoria.isbConsultoriaOchenta());
		query.append(", '");
		query.append(seviciosConsultoria.getMensaje());
		query.append("')");
		log.debug("query=" + query);

		try {

			Documento d = null;
			boolean result = true;

			getJdbcTemplate().update(query.toString());

			int id = getIdConsultoria().getIdConsultoria();
			if (seviciosConsultoria.getArchivo1() != null) {
				log.debug("Insertando el Archivo 1 = "
						+ seviciosConsultoria.getArchivo1());
				d = new Documento();
				d.setIs(seviciosConsultoria.getArchivo1());
				d.setIdReferencia(id);
				d.setNombre(seviciosConsultoria.getArchivo1FileName());
				result = insertDocumento(d).getRespuesta() == 0;
			}

			if (result) {
				Mensaje m = new Mensaje();
				m.setRespuesta(0);
				m.setMensaje("Estimada PYME, en breve un consultor se pondrá en contacto con ustedes, a nombre del CCMX.");
				m.setId(String.valueOf(id));
				return m;
			} else {
				return new Mensaje(
						1,
						"Estimada PYME ha quedado inscrita en la consultoría seleccionada con errores al guardar el o los documentos.");
			}

		} catch (Exception e) {
			log.fatal("ERROR al salvar la consultoria, " + e);
			return new Mensaje(1,
					"No es posible realizar el registro, intentelo más tarde.");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PyMEs> getBusquedaPyMEs(String busqueda, String estado,
			String cveScian) throws DaoException {
		log.debug("getBusquedaPyMEs()");

		String cadenaBusqueda = busqueda.toUpperCase().trim().replace('Á', 'A')
				.replace('É', 'E').replace('Í', 'I').replace('Ó', 'O')
				.replace('Ú', 'U').replace('Ü', 'U');
		StringTokenizer st = new StringTokenizer(cadenaBusqueda, " ");
		List<String> l = new ArrayList<String>();
		while (st.hasMoreElements()) {
			l.add((String) st.nextElement());
		}

		StringBuffer query = new StringBuffer();
		query.append("SELECT DISTINCT P.ID_USUARIO");
		query.append(", P.ID_USUARIO_PADRE");
		query.append(", P.NOMBRE_COMERCIAL");
		query.append(", D.ESTADO");
		query.append(", C.TELEFONO");
		query.append(", C.NOMBRE");
		query.append(", C.APELLIDO_PATERNO");
		query.append(", C.APELLIDO_MATERNO");
		query.append(", C.CORREO_ELECTRONICO ");
		query.append(", U.ESTATUS ");
		query.append("FROM INFRA.PYMES P");
		query.append(", INFRA.CONTACTOS C");
		query.append(", INFRA.PRODUCTOS PP");
		query.append(", INFRA.REL_DOMICILIOS_USUARIO RDU");
		query.append(", INFRA.DOMICILIOS D ");
		query.append(", INFRA.USUARIOS U ");
		query.append("WHERE P.ID_USUARIO = C.ID_USUARIO ");
		query.append("AND P.ID_USUARIO = PP.ID_USUARIO(+) ");
		query.append("AND  P.ID_USUARIO = RDU.ID_USUARIO(+) ");
		query.append("AND RDU.ID_DOMICILIO = D.ID_DOMICILIO(+) ");
		query.append("AND P.CORREO_ELECTRONICO = U.CVE_USUARIO(+) ");
		query.append("AND C.B_PRINCIPAL = true ");
		query.append(" AND ( ( ( ");
		for (String valor : l) {
			query.append(" UPPER(PP.PRODUCTO) LIKE '%".concat(Null.free(valor))
					.concat("%' "));
			if (l.indexOf(valor) != l.size() - 1)
				query.append(" OR ");
		}
		query.append(" ) OR ( ");
		for (String valor : l) {
			query.append(" UPPER(P.NOMBRE_COMERCIAL) LIKE '%".concat(
					Null.free(valor)).concat("%' "));
			if (l.indexOf(valor) != l.size() - 1)
				query.append(" OR ");
		}
		query.append(" ) ) ");
		if (!estado.isEmpty())
			query.append(" AND D.ESTADO LIKE '%".concat(estado).concat("%' "));

		if (!cveScian.isEmpty())
			query.append(" AND P.CVE_SCIAN LIKE '"
					.concat(cveScian.length() > 3 ? cveScian.substring(0, 3)
							: cveScian).concat("%' "));
		query.append(" ) ");
		log.debug("query=" + query);

		try {
			List<PyMEs> listPyME = getJdbcTemplate().query(query.toString(),
					new BusquedaPyMEsRowMapper());
			log.debug("result=" + listPyME);
			return listPyME;

		} catch (Exception e) {
			log.debug("Error: " + e);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public class BusquedaPyMEsRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			BusquedaPyMEsResultSetExtractor extractor = new BusquedaPyMEsResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class BusquedaPyMEsResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
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
			pymes.setEstatus(rs.getBoolean("ESTATUS"));
			return pymes;

		}
	}

	@SuppressWarnings("unchecked")
	public List<Requerimientos> getRequerimientos(String busqueda,
			String tractoraReq, java.sql.Date fechaDesde,
			java.sql.Date fechaHasta) throws DaoException {
		log.debug("getRequerimientos()");

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("R.ID_REQUERIMIENTO, ");
		query.append("T.EMPRESA, ");
		query.append("R.DESCRIPCION, ");
		query.append("R.FECHA_SUMINISTRO, ");
		query.append("R.FECHA_EXPIRA, ");
		query.append("R.PRODUCTO ");
		query.append("FROM INFRA.TRACTORAS AS T ");
		query.append("LEFT JOIN INFRA.REQUERIMIENTOS AS R ");
		query.append("ON T.ID_USUARIO=R.ID_TRACTORA WHERE ( ");
		StringTokenizer st = new StringTokenizer(busqueda, " ");
		int i = 1;
		while (st.hasMoreElements()) {
			if (i != 1)
				query.append(" OR ");
			query.append("R.PRODUCTO LIKE '%" + st.nextElement() + "%' ");
			i++;
		}
		query.append(" ) AND (T.EMPRESA LIKE '%"
				+ (Null.free(tractoraReq).equals("-1") ? "" : tractoraReq)
				+ "%' ");
		// TODO arreglar sentencia para que despliegue resultados sin fechas
		// (por banderas)
		if (fechaDesde != null) {
			query.append(" AND R.FECHA_SUMINISTRO >= ");
			query.append("'" + fechaDesde + "'");
		}
		if (fechaHasta != null) {
			query.append(" AND R.FECHA_EXPIRA <= ");
			query.append("'" + fechaHasta + "'");
		}
		query.append(" ) ORDER BY T.EMPRESA DESC ");

		log.debug("query=" + query);

		try {
			List<Requerimientos> listReq = getJdbcTemplate().query(
					query.toString(), new RequerimientosPyMEsRowMapper());
			log.debug("result=" + listReq);
			return listReq;

		} catch (Exception e) {
			log.debug("Aquí está e: " + e);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public class RequerimientosPyMEsRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			RequerimientosPyMEsResultSetExtractor extractor = new RequerimientosPyMEsResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class RequerimientosPyMEsResultSetExtractor implements
			ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Requerimientos req = new Requerimientos();
			Tractoras tra = new Tractoras();
			req.setIdRequerimiento(rs.getInt("ID_REQUERIMIENTO"));
			tra.setEmpresa(rs.getString("EMPRESA"));
			req.setTractora(tra);
			req.setDescripcion(rs.getString("DESCRIPCION"));
			req.setFechaExpira(rs.getDate("FECHA_SUMINISTRO"));
			req.setFechaExpira(rs.getDate("FECHA_EXPIRA"));
			req.setProducto(rs.getString("PRODUCTO"));
			return req;

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Requerimientos> getFechas() throws DaoException {
		log.debug("getFechas()");

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("FECHA_SUMINISTRO, ");
		query.append("FECHA_EXPIRA ");
		query.append("FROM INFRA.REQUERIMIENTOS ");
		query.append("ORDER BY 'FECHA_SUMINISTRO' ASC, ");
		query.append("'FECHA_EXPIRA' DESC ");
		log.debug("query=" + query);

		List<Requerimientos> trac = getJdbcTemplate().query(query.toString(),
				new FechasRowMapper());
		return trac;
	}

	@SuppressWarnings("rawtypes")
	public class FechasRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			FechasResultSetExtractor extractor = new FechasResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class FechasResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Requerimientos req = new Requerimientos();
			req.setFechaSuministro(rs.getDate("FECHA_SUMINISTRO"));
			req.setFechaExpira(rs.getDate("FECHA_EXPIRA"));
			return req;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tractoras> getTractoras() throws DaoException {
		log.debug("getTractoras()");

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("EMPRESA ");
		query.append("FROM INFRA.TRACTORAS ");
		query.append("ORDER BY EMPRESA ASC ");
		log.debug("query=" + query);

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
			tractoras.setEmpresa(rs.getString("EMPRESA"));
			return tractoras;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Diplomados> getDiplomados() throws DaoException {
		log.debug("getDiplomados()");

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_DIPLOMADO, ");
		query.append("TEMA, ");
		query.append("GENERACION, ");
		query.append("UBICACION, ");
		query.append("FECHA, ");
		query.append("URL ");
		query.append("FROM INFRA.DIPLOMADOS ");
		query.append("ORDER BY GENERACION ASC ");
		log.debug("query=" + query);

		List<Diplomados> dip = getJdbcTemplate().query(query.toString(),
				new DiplomadosRowMapper());
		return dip;
	}

	@SuppressWarnings("rawtypes")
	public class DiplomadosRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			DiplomadosResultSetExtractor extractor = new DiplomadosResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class DiplomadosResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Diplomados diplomados = new Diplomados();
			diplomados.setIdDiplomado(rs.getInt("ID_DIPLOMADO"));
			diplomados.setTema(rs.getString("TEMA"));
			diplomados.setGeneracion(rs.getInt("GENERACION"));
			diplomados.setUbicacion(rs.getString("UBICACION"));
			diplomados.setFecha(rs.getDate("FECHA"));
			diplomados.setUrl(rs.getString("URL"));
			return diplomados;
		}
	}

	@Override
	public Documento getArchivo(int id) throws JdbcDaoException {
		log.debug("getArchivo()");

		Documento result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_ARCHIVO, ");
		// query.append("ID_USUARIO, ");
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
	public ServiciosConsultoria getIdConsultoria() throws DaoException {
		log.debug("getIdConsultoria()");

		ServiciosConsultoria result = null;
		StringBuffer query = new StringBuffer();

		query.append("SELECT MAX(ID_CONSULTORIA) AS MAX_CONSULTORIA ");
		query.append("FROM INFRA.SERVICIOS_CONSULTORIA ");
		log.debug("query=" + query);

		result = (ServiciosConsultoria) getJdbcTemplate().queryForObject(
				query.toString(), new IdMaxServConsultoriaRowMapper());

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class IdMaxServConsultoriaRowMapper implements RowMapper {

		@Override
		public ServiciosConsultoria mapRow(ResultSet rs, int ln)
				throws SQLException {
			ServiciosConsultoria serv = new ServiciosConsultoria();
			serv.setIdConsultoria(rs.getInt("MAX_CONSULTORIA"));
			return serv;
		}
	}

	@SuppressWarnings("unchecked")
	public Respuesta getIdRespuesta() throws DaoException {
		log.debug("getIdRespuesta()");

		Respuesta result = null;
		StringBuffer query = new StringBuffer();

		query.append("SELECT MAX(ID_RESPUESTA) AS MAX_RESPUESTA ");
		query.append("FROM INFRA.RESPUESTAS ");
		log.debug("query=" + query);

		result = (Respuesta) getJdbcTemplate().queryForObject(query.toString(),
				new IdMaxRespuestaRowMapper());

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class IdMaxRespuestaRowMapper implements RowMapper {

		@Override
		public Respuesta mapRow(ResultSet rs, int ln) throws SQLException {
			Respuesta respuesta = new Respuesta();
			respuesta.setIdRespuesta(rs.getInt("MAX_RESPUESTA"));
			return respuesta;
		}
	}

	public Mensaje saveProductos(Productos productos) throws DaoException {
		log.debug("saveProductos()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.PRODUCTOS (");
		query.append("ID_USUARIO, ");
		query.append("PRODUCTO) ");
		query.append("VALUES ('");
		query.append(productos.getIdUsuario());
		query.append("', '");
		query.append(productos.getProducto());
		query.append("')");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos de Productos han sido registrados exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar el producto, " + e);
			return new Mensaje(1,
					"No es posible registrar el producto, intentelo más tarde.");
		}
	}

	public Mensaje updateProducto(Productos productos) throws JdbcDaoException {
		log.debug("updateProducto()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.PRODUCTOS SET ");
		query.append("PRODUCTO = '");
		query.append(productos.getProducto());
		query.append("'");
		query.append(" WHERE ID_PRODUCTO = ");
		query.append(productos.getIdProducto());
		query.append(" ");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El producto se actualizó satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar el producto, " + e);
			return new Mensaje(1,
					"No es posible actualizar el producto, intentelo más tarde.");
		}
	}

	public Mensaje deleteProducto(Productos productos) throws DaoException {
		log.debug("deleteProducto()");

		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM ");
		query.append("INFRA.PRODUCTOS ");
		query.append("WHERE ID_PRODUCTO = ");
		query.append(productos.getIdProducto());
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0, "El producto se eliminó satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al eliminar el producto, " + e);
			return new Mensaje(1, "No es posible eliminar el Producto.");
		}

	}

	public Mensaje saveEstadoVenta(EstadosVenta estadosVenta)
			throws DaoException {
		log.debug("saveEstadosVentas()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.ESTADOS_VENTA (");
		query.append("ID_USUARIO, ");
		query.append("ESTADO_VENTA) ");
		query.append("VALUES ('");
		query.append(estadosVenta.getIdUsuario());
		query.append("', '");
		query.append(estadosVenta.getEstadoVenta());
		query.append("')");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos de Estados de ventas han sido registrados exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar los estados de ventas, " + e);
			return new Mensaje(1,
					"No es posible registrar el estado de ventas, intentelo más tarde.");
		}
	}

	public Mensaje deleteEstadoVenta(EstadosVenta estadosVenta)
			throws DaoException {
		log.debug("deleteEstadoVenta()");

		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM ");
		query.append("INFRA.ESTADOS_VENTA ");
		query.append("WHERE ID_ESTADO_VENTA = ");
		query.append(estadosVenta.getIdEstadoVenta());
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El estado de venta se eliminó satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al eliminar el estado de venta, " + e);
			return new Mensaje(1, "No es posible eliminar el estado de venta.");
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
			return new Mensaje(1,
					"No es posible registrar el contacto, intentelo más tarde.");
		}
	}

	public Mensaje updateContacto(Contacto contacto) throws JdbcDaoException {
		log.debug("updateContactos()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.CONTACTOS SET ");
		query.append("TIPO = '");
		query.append(contacto.getTipo());
		query.append("', ");
		query.append("NOMBRE = '");
		query.append(contacto.getNombre());
		query.append("', ");
		query.append("APELLIDO_PATERNO = '");
		query.append(contacto.getApellidoPaterno());
		query.append("', ");
		query.append("APELLIDO_MATERNO = '");
		query.append(contacto.getApellidoMaterno());
		query.append("', ");
		query.append("CORREO_ELECTRONICO = '");
		query.append(contacto.getCorreoElectronico());
		query.append("', ");
		query.append("TELEFONO = '");
		query.append(contacto.getTelefono());
		query.append("'");
		query.append(" WHERE ID_CONTACTO = ");
		query.append(contacto.getIdContacto());
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"El producto se actualizó satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar el producto, " + e);
			return new Mensaje(1,
					"No es posible actualizar el producto, intentelo más tarde.");
		}
	}

	public Mensaje deleteContacto(Contacto contacto) throws DaoException {
		log.debug("deleteContacto()");

		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM ");
		query.append("INFRA.CONTACTOS ");
		query.append("WHERE ID_CONTACTO = ");
		query.append(contacto.getIdContacto());
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0, "El contacto se eliminó satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al eliminar el contacto, " + e);
			return new Mensaje(1,
					"No es posible eliminar el contacto, intentelo más tarde.");
		}

	}

	public Mensaje saveClientes(Clientes clientes) throws DaoException {
		log.debug("saveClientes()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.CLIENTES  (");
		query.append("ID_USUARIO, ");
		query.append("CLIENTE, ");
		query.append("PRODUCTOS_COMPRA, ");
		query.append("ANIOS_PROVEEDOR, ");
		query.append("MESES_PROVEEDOR) ");
		query.append("VALUES ('");
		query.append(clientes.getIdUsuario());
		query.append("', '");
		query.append(clientes.getCliente());
		query.append("', '");
		query.append(clientes.getProductosCompra());
		query.append("', '");
		query.append(clientes.getAniosProveedor());
		query.append("', '");
		query.append(clientes.getMesesProveedor());
		query.append("')");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos han sido registrados exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar los datos CLIENTES, " + e);
			return new Mensaje(1, "No es posible registrar los datos.");
		}
	}

	public Mensaje updateCliente(Clientes clientes) throws JdbcDaoException {
		log.debug("updateCliente()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.CLIENTES SET ");
		query.append("CLIENTE = '");
		query.append(clientes.getCliente());
		query.append("', ");
		query.append("PRODUCTOS_COMPRA = '");
		query.append(clientes.getProductosCompra());
		query.append("', ");
		query.append("ANIOS_PROVEEDOR = '");
		query.append(clientes.getAniosProveedor());
		query.append("', ");
		query.append("MESES_PROVEEDOR = '");
		query.append(clientes.getMesesProveedor());
		query.append("'");
		query.append(" WHERE ID_CLIENTE = ");
		query.append(clientes.getIdCliente());
		query.append(" ");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos del clientes se actualizaron satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar los datos del CLIENTE, " + e);
			return new Mensaje(1,
					"No es posible actualizar los datos del cliente, intentelo más tarde.");
		}
	}

	public Mensaje deleteCliente(Clientes clientes) throws DaoException {
		log.debug("deleteCliente()");

		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM ");
		query.append("INFRA.CLIENTES ");
		query.append("WHERE ID_CLIENTE = ");
		query.append(clientes.getIdCliente());
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0, "El cliente se eliminó satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al eliminar el cliente, " + e);
			return new Mensaje(1, "No es posible eliminar el cliente.");
		}

	}

	public Mensaje saveCertificaciones(Certificaciones certificaciones)
			throws DaoException {
		log.debug("saveCertificaciones()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.CERTIFICACIONES (");
		query.append("ID_USUARIO, ");
		query.append("CERTIFICACION, ");
		query.append("INSTITUTO_CERTIFICADOR , ");
		query.append("FECHA_CERTIFICACION) ");
		query.append("VALUES ('");
		query.append(certificaciones.getIdUsuario());
		query.append("', '");
		query.append(certificaciones.getCertificacion());
		query.append("', '");
		query.append(certificaciones.getInstitutoCertificador());
		query.append("', '");
		query.append(new java.sql.Date(certificaciones.getFechaCertificacion()
				.getTime()));
		query.append("')");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos han sido registrados exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar los datos CERTIFICACIONES, " + e);
			return new Mensaje(1, "No es posible registrar los datos.");
		}
	}

	public Mensaje updateCertificaciones(Certificaciones certificaciones)
			throws JdbcDaoException {
		log.debug("updateCertificaciones()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.CERTIFICACIONES SET ");
		query.append("CERTIFICACION = '");
		query.append(certificaciones.getCertificacion());
		query.append("', ");
		query.append("INSTITUTO_CERTIFICADOR = '");
		query.append(certificaciones.getInstitutoCertificador());
		query.append("', ");
		query.append("FECHA_CERTIFICACION = '");
		query.append(new java.sql.Date(certificaciones.getFechaCertificacion()
				.getTime()));
		query.append("' ");
		query.append("WHERE ID_CERTIFICADO = ");
		query.append(certificaciones.getIdCertificado());
		query.append(" ");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Los datos han sido actualizados exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar los datos de los CERTIFICACIONES, "
					+ e);
			return new Mensaje(1,
					"No es posible registrar los datos, intentelo más tarde.");
		}
	}

	public Mensaje deleteCertificacion(Certificaciones certificaciones)
			throws DaoException {
		log.debug("deleteCertificacion()");

		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM ");
		query.append("INFRA.CERTIFICACIONES ");
		query.append("WHERE ID_CERTIFICADO = ");
		query.append(certificaciones.getIdCertificado());
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"La Certificación se eliminó satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al eliminar la Certificación, " + e);
			return new Mensaje(1, "No es posible eliminar la Certificación.");
		}

	}

	public Mensaje insertDocumento(Documento documento) throws DaoException {
		log.debug("insertDocumento()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.ARCHIVOS( ");
		query.append("ID_USUARIO, ");
		query.append("ID_RESPUESTA, ");
		query.append("NOMBRE, ");
		query.append("DESCRIPCION_ARCHIVO, ");
		query.append("MIME, ");
		query.append("TIPO, ");
		query.append("CONTENIDO) ");
		query.append("VALUES( ?, ?, ?, ?, ?, ?, ? )");
		log.debug("query=" + query);
		log.debug("documento: " + documento);

		PreparedStatement ps = null;
		try {
			getConnection().setAutoCommit(false);
			ps = getConnection().prepareStatement(query.toString());
			ps.setInt(1, documento.getIdUsuario());
			ps.setInt(2, documento.getIdReferencia());
			ps.setString(3, documento.getNombre());
			ps.setString(4, documento.getDescripcionArchivo());
			ps.setString(5, documento.getMimeType(documento.getNombre()));
			ps.setString(6, documento.getFileType(documento.getNombre()));
			ps.setBlob(7, documento.getIs());

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

	@SuppressWarnings("unchecked")
	@Override
	public RelPyMEsTractoras getCalificaciones(int id) throws DaoException {
		log.debug("getCalificaciones()");

		List<RelPyMEsTractoras> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT REL.CALIFICACION, ");
		query.append("REL.COMENTARIO ");
		query.append("FROM INFRA.REL_PYMES_TRACTORAS AS REL ");
		query.append("JOIN INFRA.PYMES AS P ");
		query.append("ON P.ID_USUARIO=REL.ID_USUARIO_PYME ");
		query.append("JOIN INFRA.TRACTORAS AS T ");
		query.append("ON T.ID_USUARIO=REL.ID_USUARIO_TRACTORA ");
		query.append("WHERE P.ID_USUARIO = " + id);
		query.append(" AND NOT (REL.CALIFICACION) IS NULL");
		log.debug("query=" + query);
		log.debug(id);

		result = getJdbcTemplate().query(query.toString(),
				new CalificacionRowMapper());

		log.debug("result=" + result);
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}

		return null;
	}

	@SuppressWarnings("rawtypes")
	public class CalificacionRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			CalificacionResultSetExtractor extractor = new CalificacionResultSetExtractor();
			return extractor.extractData(rs);
		}
	}

	@SuppressWarnings("rawtypes")
	public class CalificacionResultSetExtractor implements ResultSetExtractor {
		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			RelPyMEsTractoras rel = new RelPyMEsTractoras();
			rel.setCalificacion(rs.getInt("CALIFICACION"));
			rel.setComentario(rs.getString("COMENTARIO"));
			return rel;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Indicadores getIndicadoresMes(int id) throws DaoException {
		log.debug("getIndicadoresMes()");

		List<Indicadores> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES WHERE ID_INDICADOR_TRACTORA = 1 AND PERIODO_REF_MES = 'Enero - Marzo' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS AHORROS_MONETARIOS_ENERO, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES WHERE ID_INDICADOR_TRACTORA = 1 AND PERIODO_REF_MES = 'Abril - Junio' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS AHORROS_MONETARIOS_ABRIL, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 1 AND PERIODO_REF_MES = 'Julio - Septiembre' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS AHORROS_MONETARIOS_JULIO, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 1 AND PERIODO_REF_MES = 'Octubre - Diciembre' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS AHORROS_MONETARIOS_OCTUBRE, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 3 AND PERIODO_REF_MES = 'Enero - Marzo' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS DEFECTOS_ENERO, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 3 AND PERIODO_REF_MES = 'Abril - Junio' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS DEFECTOS_ABRIL, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 3 AND PERIODO_REF_MES = 'Julio - Septiembre' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS DEFECTOS_JULIO, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 3 AND PERIODO_REF_MES = 'Octubre - Diciembre' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS DEFECTOS_OCTUBRE, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 6 AND PERIODO_REF_MES = 'Enero - Marzo' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS AHORRO_TIEMPO_ENERO, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 6 AND PERIODO_REF_MES = 'Abril - Junio' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS AHORRO_TIEMPO_ABRIL, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 6 AND PERIODO_REF_MES = 'Julio - Septiembre' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS AHORRO_TIEMPO_JULIO, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 6 AND PERIODO_REF_MES = 'Octubre - Diciembre' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS AHORRO_TIEMPO_OCTUBRE, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 7 AND PERIODO_REF_MES = 'Enero - Marzo' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS SERVICIO_ENERO, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 7 AND PERIODO_REF_MES = 'Abril - Junio' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS SERVICIO_ABRIL, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 7 AND PERIODO_REF_MES = 'Julio - Septiembre' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS SERVICIO_JULIO, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 7 AND PERIODO_REF_MES = 'Octubre - Diciembre' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS SERVICIO_OCTUBRE, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 9 AND PERIODO_REF_MES = 'Enero - Marzo' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS CAPACIDAD_ENERO, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 9 AND PERIODO_REF_MES = 'Abril - Junio' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS CAPACIDAD_ABRIL, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 9 AND PERIODO_REF_MES = 'Julio - Septiembre' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS CAPACIDAD_JULIO, ");
		query.append("(SELECT RESULTADO_CALCULO FROM INFRA.INDICADORES AS IN WHERE ID_INDICADOR_TRACTORA = 9 AND PERIODO_REF_MES = 'Octubre - Diciembre' AND PERIODO_REF_ANIO = 2012 AND REL.ID_PYME_TRACTORA=ID_PYME_TRACTORA) AS CAPACIDAD_OCTUBRE ");
		query.append("FROM INFRA.REL_PYMES_TRACTORAS AS REL ");
		query.append("JOIN INFRA.PYMES AS P ");
		query.append("ON REL.ID_USUARIO_PYME=P.ID_USUARIO ");
		query.append("WHERE P.ID_USUARIO = " + id);
		query.append(" AND NOT (REL.CALIFICACION) IS NULL");
		log.debug("query=" + query);
		log.debug(id);

		result = getJdbcTemplate().query(query.toString(),
				new IndicadoresMesRowMapper());

		log.debug("result=" + result);
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}

		return null;
	}

	@SuppressWarnings("rawtypes")
	public class IndicadoresMesRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			IndicadoresMesResultSetExtractor extractor = new IndicadoresMesResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class IndicadoresMesResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Indicadores ind = new Indicadores();
			ind.setAhorrosMonetariosEnero(rs
					.getString("AHORROS_MONETARIOS_ENERO"));
			ind.setAhorrosMonetariosAbril(rs
					.getString("AHORROS_MONETARIOS_ABRIL"));
			ind.setAhorrosMonetariosJulio(rs
					.getString("AHORROS_MONETARIOS_JULIO"));
			ind.setAhorrosMonetariosOctubre(rs
					.getString("AHORROS_MONETARIOS_OCTUBRE"));
			ind.setDefectosEnero(rs.getString("DEFECTOS_ENERO"));
			ind.setDefectosAbril(rs.getString("DEFECTOS_ABRIL"));
			ind.setDefectosJulio(rs.getString("DEFECTOS_JULIO"));
			ind.setDefectosOctubre(rs.getString("DEFECTOS_OCTUBRE"));
			ind.setAhorroTiempoEnero(rs.getString("AHORRO_TIEMPO_ENERO"));
			ind.setAhorroTiempoAbril(rs.getString("AHORRO_TIEMPO_ABRIL"));
			ind.setAhorroTiempoJulio(rs.getString("AHORRO_TIEMPO_JULIO"));
			ind.setAhorroTiempoOctubre(rs.getString("AHORRO_TIEMPO_OCTUBRE"));
			ind.setServicioEnero(rs.getString("SERVICIO_ENERO"));
			ind.setServicioAbril(rs.getString("SERVICIO_ABRIL"));
			ind.setServicioJulio(rs.getString("SERVICIO_JULIO"));
			ind.setServicioOctubre(rs.getString("SERVICIO_OCTUBRE"));
			ind.setCapacidadEnero(rs.getString("CAPACIDAD_ENERO"));
			ind.setCapacidadAbril(rs.getString("CAPACIDAD_ABRIL"));
			ind.setCapacidadJulio(rs.getString("CAPACIDAD_JULIO"));
			ind.setCapacidadOctubre(rs.getString("CAPACIDAD_OCTUBRE"));
			return ind;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiciosConsultoria getConsultorias(int id) throws DaoException {
		log.debug("getConsultorias()");

		List<ServiciosConsultoria> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("B_CONSULTORIA_20, ");
		query.append("B_CONSULTORIA_40, ");
		query.append("B_CONSULTORIA_60, ");
		query.append("B_CONSULTORIA_80, ");
		query.append("RECURSOS_HUMANOS_ANTES, ");
		query.append("MERCADEO_ANTES, ");
		query.append("FINANZAS_ANTES, ");
		query.append("ADMINISTRACION_ANTES, ");
		query.append("PROCESOS_ANTES, ");
		query.append("RECURSOS_HUMANOS_DESPUES, ");
		query.append("MERCADEO_DESPUES, ");
		query.append("FINANZAS_DESPUES, ");
		query.append("ADMINISTRACION_DESPUES, ");
		query.append("PROCESOS_DESPUES, ");
		query.append("FECHA_INICIO, ");
		query.append("FECHA_TERMINO ");
		query.append("FROM INFRA.SERVICIOS_CONSULTORIA ");
		query.append("WHERE ID_USUARIO  = " + id);
		log.debug("query=" + query);
		log.debug(id);

		result = getJdbcTemplate().query(query.toString(),
				new RadaresRowMapper());

		log.debug("result=" + result);
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public class RadaresRowMapper implements RowMapper {

		@Override
		public ServiciosConsultoria mapRow(ResultSet rs, int ln)
				throws SQLException {
			ServiciosConsultoria radar = new ServiciosConsultoria();
			radar.setbConsultoriaVeinte(rs.getBoolean("B_CONSULTORIA_20"));
			radar.setbConsultoriaCuarenta(rs.getBoolean("B_CONSULTORIA_40"));
			radar.setbConsultoriaSesenta(rs.getBoolean("B_CONSULTORIA_60"));
			radar.setbConsultoriaOchenta(rs.getBoolean("B_CONSULTORIA_80"));
			radar.setRecursosHumanosAntes(rs.getInt("RECURSOS_HUMANOS_ANTES"));
			radar.setMercadeoAntes(rs.getInt("MERCADEO_ANTES"));
			radar.setFinanzasAntes(rs.getInt("FINANZAS_ANTES"));
			radar.setAdministracionAntes(rs.getInt("ADMINISTRACION_ANTES"));
			radar.setProcesosAntes(rs.getInt("PROCESOS_ANTES"));
			radar.setRecursosHumanosDespues(rs
					.getInt("RECURSOS_HUMANOS_DESPUES"));
			radar.setMercadeoDespues(rs.getInt("MERCADEO_DESPUES"));
			radar.setFinanzasDespues(rs.getInt("FINANZAS_DESPUES"));
			radar.setAdministracionDespues(rs.getInt("ADMINISTRACION_DESPUES"));
			radar.setProcesosDespues(rs.getInt("PROCESOS_DESPUES"));
			radar.setInicio(rs.getDate("FECHA_INICIO"));
			radar.setTermino(rs.getDate("FECHA_TERMINO"));

			return radar;
		}
	}
	
	public List<Productos> getProductos(int id) throws JdbcDaoException {
		log.debug("getProductos()");

		List<Productos> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_PRODUCTO, ");
		query.append("ID_USUARIO, ");
		query.append("PRODUCTO ");
		query.append("FROM INFRA.PRODUCTOS ");
		query.append("WHERE ID_USUARIO = " + id);
		query.append(" ORDER BY ID_PRODUCTO ");
		log.debug("query=" + query);

		try {
			result = (List<Productos>) getJdbcTemplate().query(
					query.toString(), new ProductosRowMapper());
		} catch (Exception e) {
			throw new JdbcDaoException(e);
		}

		log.debug("result=" + result);
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
			Productos pr = new Productos();
			pr.setIdProducto(rs.getInt("ID_PRODUCTO"));
			pr.setIdUsuario(rs.getInt("ID_USUARIO"));
			pr.setProducto(rs.getString("PRODUCTO"));
			return pr;
		}

	}
	
	public List<Contacto> getContactos(int id) throws JdbcDaoException {
		log.debug("getContactos()");

		List<Contacto> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_CONTACTO, ");
		query.append("ID_USUARIO, ");
		query.append("TIPO, ");
		query.append("NOMBRE, ");
		query.append("APELLIDO_PATERNO, ");
		query.append("APELLIDO_MATERNO, ");
		query.append("CORREO_ELECTRONICO, ");
		query.append("B_PRINCIPAL, ");
		query.append("TELEFONO ");
		query.append("FROM INFRA.CONTACTOS ");
		query.append("WHERE ID_USUARIO = " + id);
		query.append(" ORDER BY ID_CONTACTO ASC");
		log.debug("query=" + query);

		try {
			result = (List<Contacto>) getJdbcTemplate().query(
					query.toString(), new ContactosRowMapper());
		} catch (Exception e) {
			throw new JdbcDaoException(e);
		}

		log.debug("result=" + result);
		return result;
	}

	public class ContactosRowMapper implements RowMapper<Contacto> {

		@Override
		public Contacto mapRow(ResultSet rs, int ln) throws SQLException {
			ContactosResultSetExtractor extractor = new ContactosResultSetExtractor();
			return (Contacto) extractor.extractData(rs);
		}
	}

	public class ContactosResultSetExtractor implements
			ResultSetExtractor<Contacto> {

		@Override
		public Contacto extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Contacto contacto = new Contacto();
			contacto.setIdContacto(rs.getInt("ID_CONTACTO"));
			contacto.setIdUsuario(rs.getInt("ID_USUARIO"));
			contacto.setTipo(rs.getString("TIPO"));
			contacto.setNombre(rs.getString("NOMBRE"));
			contacto.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
			contacto.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
			contacto.setCorreoElectronico(rs.getString("CORREO_ELECTRONICO"));
			contacto.setTelefono(rs.getString("TELEFONO"));
			return contacto;
		}
	}

	public List<Clientes> getClientes(int id) throws JdbcDaoException {
		log.debug("getClientes()");

		List<Clientes> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_CLIENTE, ");
		query.append("ID_USUARIO, ");
		query.append("CLIENTE, ");
		query.append("PRODUCTOS_COMPRA, ");
		query.append("ANIOS_PROVEEDOR, ");
		query.append("MESES_PROVEEDOR ");
		query.append("FROM INFRA.CLIENTES ");
		query.append("WHERE ID_USUARIO = " + id);
		query.append(" ORDER BY ID_CLIENTE ASC");
		log.debug("query=" + query);

		try {
			result = (List<Clientes>) getJdbcTemplate().query(
					query.toString(), new ClientesRowMapper());
		} catch (Exception e) {
			throw new JdbcDaoException(e);
		}

		log.debug("result=" + result);
		return result;
	}

	public class ClientesRowMapper implements RowMapper<Clientes> {

		@Override
		public Clientes mapRow(ResultSet rs, int ln) throws SQLException {
			ClientesResultSetExtractor extractor = new ClientesResultSetExtractor();
			return (Clientes) extractor.extractData(rs);
		}
	}

	public class ClientesResultSetExtractor implements
			ResultSetExtractor<Clientes> {

		@Override
		public Clientes extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Clientes client = new Clientes();
			client.setIdCliente(rs.getInt("ID_CLIENTE"));
			client.setIdUsuario(rs.getInt("ID_USUARIO"));
			client.setCliente(rs.getString("CLIENTE"));
			client.setProductosCompra(rs.getString("PRODUCTOS_COMPRA"));
			client.setAniosProveedor(rs.getString("ANIOS_PROVEEDOR"));
			client.setMesesProveedor(rs.getString("MESES_PROVEEDOR"));
			return client;
		}
	}
	
	public List<Certificaciones> getCertificaciones(int id) throws JdbcDaoException {
		log.debug("getCertificaciones()");

		List<Certificaciones> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_CERTIFICADO, ");
		query.append("ID_USUARIO, ");
		query.append("CERTIFICACION, ");
		query.append("INSTITUTO_CERTIFICADOR, ");
		query.append("FECHA_CERTIFICACION ");
		query.append("FROM INFRA.CERTIFICACIONES ");
		query.append("WHERE ID_USUARIO = " + id);
		query.append(" ORDER BY ID_CERTIFICADO ASC");
		log.debug("query=" + query);

		try {
			result = (List<Certificaciones>) getJdbcTemplate().query(
					query.toString(), new CertificacionesRowMapper());
		} catch (Exception e) {
			throw new JdbcDaoException(e);
		}

		log.debug("result=" + result);
		return result;
	}

	public class CertificacionesRowMapper implements RowMapper<Certificaciones> {

		@Override
		public Certificaciones mapRow(ResultSet rs, int ln) throws SQLException {
			CertificacionesResultSetExtractor extractor = new CertificacionesResultSetExtractor();
			return (Certificaciones) extractor.extractData(rs);
		}
	}

	public class CertificacionesResultSetExtractor implements
			ResultSetExtractor<Certificaciones> {

		@Override
		public Certificaciones extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Certificaciones cert = new Certificaciones();
			cert.setIdCertificado(rs.getInt("ID_CERTIFICADO"));
			cert.setIdUsuario(rs.getInt("ID_USUARIO"));
			cert.setCertificacion(rs.getString("CERTIFICACION"));
			cert.setInstitutoCertificador(rs.getString("INSTITUTO_CERTIFICADOR"));
			cert.setFechaCertificacion(rs.getDate("FECHA_CERTIFICACION"));
			return cert;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getNombresCveScian(int cveCat) throws DaoException {
		log.debug("getNombresCveScian()");

		String result;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("DESC_SCIAN ");
		query.append("FROM INFRA.CAT_SCIAN_CCMX ");
		query.append("WHERE CVE_SCIAN = " + cveCat);
		log.debug("query=" + query);

		try {
			result = (String) getJdbcTemplate().queryForObject(query.toString(), new NombreCatRowMapper());
		} catch (Exception e) {
			result = "0";
		}
		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class NombreCatRowMapper implements RowMapper {

		@Override
		public String mapRow(ResultSet rs, int ln) throws SQLException {
			return rs.getString("DESC_SCIAN");
		}
	}
}