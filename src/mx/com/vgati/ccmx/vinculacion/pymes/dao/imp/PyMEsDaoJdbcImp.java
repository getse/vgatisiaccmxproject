/*
 * PyMEsDaoJdbcImp.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.pymes.dao.PyMEsDao;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Certificaciones;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Clientes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;
import mx.com.vgati.framework.dto.Respuesta;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class PyMEsDaoJdbcImp extends VinculacionBaseJdbcDao 
implements PyMEsDao {

	@SuppressWarnings("unchecked")
	@Override
	public PyMEs getPyMEs(int id) throws DaoException {
		log.debug("getPyMes()");

		PyMEs result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_USUARIO, ");
		query.append("ID_USUARIO_PADRE, ");
		query.append("PERSONALIDAD_JURIDICA, ");
		query.append("RFC, ");
		query.append("CORREO_ELECTRONICO, ");
		query.append("NOMBRE_COMERCIAL, ");
		query.append("NOMBRE_FISCAL, ");
		query.append("NUMERO_EMPLEADOS, ");
		query.append("MENSAJE_VENTAS, ");
		query.append("PAGINA_WEB, ");
		query.append("PRODUCTOS_PRINCIPALES, ");
		query.append("VENTAS_ANUALES, ");
		query.append("CVE_SCIAN, ");
		query.append("NOMBRE_CONTACTO, ");
		query.append("APP_PATERNO, ");
		query.append("APP_MATERNO, ");
		query.append("CORREO_ELECTRONICO_CONTACTO, ");
		query.append("TELEFONO_CONTACTO, ");
		query.append("INSTITUTO_CERTIFICADOR, ");
		query.append("B_DIPLOMADO_CCMX_UNO, ");
		query.append("B_DIPLOMADO_CCMX_DOS, ");
		query.append("B_DIPLOMADO_CCMX_TRES, ");
		query.append("B_DIPLOMADO_CCMX_CUATRO, ");
		query.append("B_RECIBE_REQUERIMIENTOS_COMPRA, ");
		query.append("CVE_SCIAN_REQUERIMIENTOS_COMPRA, ");
		query.append("CALIFICACION, ");
		query.append("B_SERVICIOS_CCMX_DIPLOMADOS, ");
		query.append("B_SERVICIOS_CCMX_CONSULTORIA, ");
		query.append("B_PRIMER_NIVEL, ");
		query.append("B_SEGUNDO_NIVEL, ");
		query.append("B_TERCER_NIVEL, ");
		query.append("B_AGUASCALIENTES, ");
		query.append("B_BAJA_CALIFORNIA_SUR, ");
		query.append("B_BAJA_CALIFORNIA_NORTE, ");
		query.append("B_CAMPECHE, ");
		query.append("B_CHIAPAS, ");
		query.append("B_CHIHUAHUA, ");
		query.append("B_COAHUILA, ");
		query.append("B_COLIMA, ");
		query.append("B_DISTRITO_FEDERAL, ");
		query.append("B_DURANGO, ");
		query.append("B_GUANAJUATO, ");
		query.append("B_GUERRERO, ");
		query.append("B_HIDALGO, ");
		query.append("B_JALISCO, ");
		query.append("B_MEXICO, ");
		query.append("B_MICHOACAN, ");
		query.append("B_MORELOS, ");
		query.append("B_NAYARIT, ");
		query.append("B_NUEVO_LEON, ");
		query.append("B_OAXACA, ");
		query.append("B_PUEBLA, ");
		query.append("B_QUERETARO, ");
		query.append("B_QUINTANA_ROO, ");
		query.append("B_SAN_LUIS_POTOSI, ");
		query.append("B_SINALOA, ");
		query.append("B_SONORA, ");
		query.append("B_TABASCO, ");
		query.append("B_TAMAULIPAS, ");
		query.append("B_TLAXCALA, ");
		query.append("B_VERACRUZ, ");
		query.append("B_YUCATAN, ");
		query.append("B_ZACATECAS ");
		query.append("FROM INFRA.PYMES ");
		query.append("WHERE ID_USUARIO = ? ");
		log.debug("query=" + query);
		log.debug(id);

		Object[] o = { id };
		result = (PyMEs) getJdbcTemplate().queryForObject(query.toString(),
				o, new PyMesRowMapper());

		log.debug("result=" + result);
		return result;
	}
	@SuppressWarnings("rawtypes")
	public class PyMesRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			PyMesResultSetExtractor extractor = new PyMesResultSetExtractor();
			return extractor.extractData(rs);
		}

	}
	@SuppressWarnings("rawtypes")
	public class PyMesResultSetExtractor implements ResultSetExtractor {

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
			pymes.setProductosPrincipales(rs.getString("PRODUCTOS_PRINCIPALES"));
			pymes.setVentasAnuales(rs.getInt("VENTAS_ANUALES"));
			pymes.setCveScian(rs.getInt("CVE_SCIAN"));
			pymes.setNombreContacto(rs.getString("NOMBRE_CONTACTO"));
			pymes.setAppPaterno(rs.getString("APP_PATERNO"));
			pymes.setAppMaterno(rs.getString("APP_MATERNO"));
			pymes.setCorreoElectronicoContacto(rs.getString("CORREO_ELECTRONICO_CONTACTO"));
			pymes.setTelefonoContacto(rs.getString("TELEFONO_CONTACTO"));
			pymes.setInstitutoCertificador(rs.getString("INSTITUTO_CERTIFICADOR"));
			pymes.setbDiplomadoCcmxUno(rs.getBoolean("B_DIPLOMADO_CCMX_UNO"));
			pymes.setbDiplomadoCcmxDos(rs.getBoolean("B_DIPLOMADO_CCMX_DOS"));
			pymes.setbDiplomadoCcmxTres(rs.getBoolean("B_DIPLOMADO_CCMX_TRES"));
			pymes.setbDiplomadoCcmxCuatro(rs.getBoolean("B_DIPLOMADO_CCMX_CUATRO"));
			pymes.setbRecibeRequerimientosCompra(rs.getBoolean("B_RECIBE_REQUERIMIENTOS_COMPRA"));
			pymes.setCveScianRequerimientosCompra(rs.getInt("CVE_SCIAN_REQUERIMIENTOS_COMPRA"));
			pymes.setCalificacion(rs.getString("CALIFICACION"));
			pymes.setbServiciosCcmxDiplomados(rs.getBoolean("B_SERVICIOS_CCMX_DIPLOMADOS"));
			pymes.setbServiciosCcmxConsultoria(rs.getBoolean("B_SERVICIOS_CCMX_CONSULTORIA"));
			pymes.setbPrimerNivel(rs.getBoolean("B_PRIMER_NIVEL"));
			pymes.setbSegundoNivel(rs.getBoolean("B_SEGUNDO_NIVEL"));
			pymes.setbTercerNivel(rs.getBoolean("B_TERCER_NIVEL"));
			pymes.setbAguascalientes(rs.getBoolean("B_AGUASCALIENTES"));
			pymes.setbBajaCaliforniaSur(rs.getBoolean("B_BAJA_CALIFORNIA_SUR"));
			pymes.setbBajaCaliforniaNorte(rs.getBoolean("B_BAJA_CALIFORNIA_NORTE"));
			pymes.setbCampeche(rs.getBoolean("B_CAMPECHE"));
			pymes.setbChiapas(rs.getBoolean("B_CHIAPAS"));
			pymes.setbChihuahua(rs.getBoolean("B_CHIHUAHUA"));
			pymes.setbCoahuila(rs.getBoolean("B_COAHUILA"));
			pymes.setbColima(rs.getBoolean("B_COLIMA"));
			pymes.setbDistritoFederal(rs.getBoolean("B_DISTRITO_FEDERAL"));
			pymes.setbDurango(rs.getBoolean("B_DURANGO"));
			pymes.setbGuanajuato(rs.getBoolean("B_GUANAJUATO"));
			pymes.setbGuerrero(rs.getBoolean("B_GUERRERO"));
			pymes.setbHidalgo(rs.getBoolean("B_HIDALGO"));
			pymes.setbJalisco(rs.getBoolean("B_JALISCO"));
			pymes.setbMexico(rs.getBoolean("B_MEXICO"));
			pymes.setbMichoacan(rs.getBoolean("B_MICHOACAN"));
			pymes.setbMorelos(rs.getBoolean("B_MORELOS"));
			pymes.setbMorelos(rs.getBoolean("B_NAYARIT"));
			pymes.setbMorelos(rs.getBoolean("B_NUEVO_LEON"));
			pymes.setbMorelos(rs.getBoolean("B_OAXACA"));
			pymes.setbMorelos(rs.getBoolean("B_PUEBLA"));
			pymes.setbMorelos(rs.getBoolean("B_QUERETARO"));
			pymes.setbMorelos(rs.getBoolean("B_QUINTANA_ROO"));
			pymes.setbMorelos(rs.getBoolean("B_SAN_LUIS_POTOSI"));
			pymes.setbMorelos(rs.getBoolean("B_SINALOA"));
			pymes.setbMorelos(rs.getBoolean("B_SONORA"));
			pymes.setbMorelos(rs.getBoolean("B_TABASCO"));
			pymes.setbMorelos(rs.getBoolean("B_TAMAULIPAS"));
			pymes.setbMorelos(rs.getBoolean("B_TLAXCALA"));
			pymes.setbMorelos(rs.getBoolean("B_VERACRUZ"));
			pymes.setbMorelos(rs.getBoolean("B_YUCATAN"));
			pymes.setbMorelos(rs.getBoolean("B_ZACATECAS"));
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
	public String getIdCliente(int id) throws DaoException {
		log.debug("getIdCliente()");

		String result;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("ID_CLIENTE ");
		query.append("FROM INFRA.CLIENTES ");
		query.append("WHERE ID_USUARIO = " + id);
		log.debug("query=" + query);

		try {
			result = (String) getJdbcTemplate().queryForObject(
					query.toString(), new IdClienteRowMapper());
		} catch (Exception e) {
			result = "0";
		}

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class IdClienteRowMapper implements RowMapper {

		@Override
		public String mapRow(ResultSet rs, int ln) throws SQLException {
			return rs.getString("ID_CLIENTE");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Clientes getClientes(int id) throws DaoException {
		log.debug("getClientes()");
		log.debug("id en getClientes:" +id);

		Clientes result = null;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("ID_CLIENTE, ");
		query.append("ID_USUARIO, ");
		query.append("CLIENTE, ");
		query.append("PRODUCTOS_COMPRA, ");
		query.append("ANIOS_PROVEEDOR, ");
		query.append("MESES_PROVEEDOR ");
		query.append("FROM INFRA.CLIENTES ");
		query.append("WHERE ID_CLIENTE = " + id);
		log.debug("query=" + query);
		log.debug(id);
		
		if (id == 0)
			return null;
		result = (Clientes) getJdbcTemplate().queryForObject(
				query.toString(), new ClientesRowMapper());

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class ClientesRowMapper implements RowMapper {

		@Override
		public Clientes mapRow(ResultSet rs, int ln) throws SQLException {
			Clientes clientes = new Clientes();
			clientes.setIdCliente(rs.getInt("ID_CLIENTE"));
			clientes.setIdUsuario(rs.getInt("ID_USUARIO"));
			clientes.setCliente(rs.getString("CLIENTE"));
			clientes.setProductosCompra(rs.getString("PRODUCTOS_COMPRA"));
			clientes.setAniosProveedor(rs.getString("ANIOS_PROVEEDOR"));
			clientes.setMesesProveedor(rs.getString("MESES_PROVEEDOR"));

			return clientes;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String getIdCertificacion(int id) throws DaoException {
		log.debug("getIdCertificacion()");

		String result;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("ID_CERTIFICADO ");
		query.append("FROM INFRA.CERTIFICACIONES ");
		query.append("WHERE ID_USUARIO = " + id);
		log.debug("query=" + query);

		try {
			result = (String) getJdbcTemplate().queryForObject(
					query.toString(), new IdCertificacionRowMapper());
		} catch (Exception e) {
			result = "0";
		}

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class IdCertificacionRowMapper implements RowMapper {

		@Override
		public String mapRow(ResultSet rs, int ln) throws SQLException {
			return rs.getString("ID_CERTIFICADO");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Certificaciones getCertificaciones(int id) throws DaoException {
		log.debug("getClientes()");

		Certificaciones result = null;
		StringBuffer query = new StringBuffer();

		query.append("SELECT ");
		query.append("ID_CERTIFICADO, ");
		query.append("ID_USUARIO, ");
		query.append("CERTIFICACION, ");
		query.append("FECHA_CERTIFICACION ");
		query.append("FROM INFRA.CERTIFICACIONES ");
		query.append("WHERE ID_CERTIFICADO = " + id);
		log.debug("query=" + query);

		if (id == 0)
			return null;
		result = (Certificaciones) getJdbcTemplate().queryForObject(
				query.toString(), new CertificacionesRowMapper());

		log.debug("result=" + result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public class CertificacionesRowMapper implements RowMapper {

		@Override
		public Certificaciones mapRow(ResultSet rs, int ln) throws SQLException {
			Certificaciones certificaciones = new Certificaciones();
			certificaciones.setIdCertificado(rs.getInt("ID_CERTIFICADO"));
			certificaciones.setIdUsuario(rs.getInt("ID_USUARIO"));
			certificaciones.setCertificacion(rs.getString("CERTIFICACION"));
			certificaciones.setFechaCertificacion(rs.getDate("FECHA_CERTIFICACION"));

			return certificaciones;
		}
	}

	public Mensaje updatePyMEs(PyMEs pyMes) throws JdbcDaoException {
		log.debug("updatePyMEs()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.PYMES SET ");
		query.append("PERSONALIDAD_JURIDICA = '");
		query.append(pyMes.getPersonalidadJuridica());
		query.append("', ");
		query.append("RFC = '");
		query.append(pyMes.getRfc());
		query.append("', ");
		query.append("CORREO_ELECTRONICO = '");
		query.append(pyMes.getCorreoElectronico());
		query.append("', ");
		query.append("NOMBRE_COMERCIAL = '");
		query.append(pyMes.getNombreComercial());
		query.append("', ");
		query.append("NOMBRE_FISCAL = '");
		query.append(pyMes.getNombreFiscal());
		query.append("', ");
		query.append("NUMERO_EMPLEADOS = '");
		query.append(pyMes.getNumeroEmpleados());
		query.append("', ");
		query.append("MENSAJE_VENTAS = '");
		query.append(pyMes.getMensajeVentas());
		query.append("', ");
		query.append("PAGINA_WEB = '");
		query.append(pyMes.getPaginaWeb());
		query.append("', ");
		query.append("PRODUCTOS_PRINCIPALES = '");
		query.append(pyMes.getProductosPrincipales());
		query.append("', ");
		query.append("VENTAS_ANUALES = '");
		query.append(pyMes.getVentasAnuales());
		query.append("', ");
		query.append("CVE_SCIAN = '");
		query.append(pyMes.getCveScian());
		query.append("', ");
		query.append("NOMBRE_CONTACTO = '");
		query.append(pyMes.getNombreContacto());
		query.append("', ");
		query.append("APP_PATERNO = '");
		query.append(pyMes.getAppPaterno());
		query.append("', ");
		query.append("APP_MATERNO = '");
		query.append(pyMes.getAppMaterno());
		query.append("', ");
		query.append("CORREO_ELECTRONICO_CONTACTO = '");
		query.append(pyMes.getCorreoElectronicoContacto());
		query.append("', ");
		query.append("TELEFONO_CONTACTO = '");
		query.append(pyMes.getTelefonoContacto());
		query.append("', ");
		query.append("INSTITUTO_CERTIFICADOR = '");
		query.append(pyMes.getInstitutoCertificador());
		query.append("', ");
		query.append("B_DIPLOMADO_CCMX_UNO = '");
		query.append(pyMes.isbDiplomadoCcmxUno());
		query.append("', ");
		query.append("B_DIPLOMADO_CCMX_DOS = '");
		query.append(pyMes.isbDiplomadoCcmxDos());
		query.append("', ");
		query.append("B_DIPLOMADO_CCMX_TRES = '");
		query.append(pyMes.isbDiplomadoCcmxTres());
		query.append("', ");
		query.append("B_DIPLOMADO_CCMX_CUATRO = '");
		query.append(pyMes.isbDiplomadoCcmxCuatro());
		query.append("', ");
		query.append("B_RECIBE_REQUERIMIENTOS_COMPRA = '");
		query.append(pyMes.isbRecibeRequerimientosCompra());
		query.append("', ");
		query.append("CVE_SCIAN_REQUERIMIENTOS_COMPRA = '");
		query.append(pyMes.getCveScianRequerimientosCompra());
		query.append("', ");
		query.append("CALIFICACION = '");
		query.append(pyMes.getCalificacion());
		query.append("', ");
		query.append("B_SERVICIOS_CCMX_DIPLOMADOS = '");
		query.append(pyMes.isbServiciosCcmxDiplomados());
		query.append("', ");
		query.append("B_SERVICIOS_CCMX_CONSULTORIA = '");
		query.append(pyMes.isbServiciosCcmxConsultoria());
		query.append("', ");
		query.append("B_PRIMER_NIVEL = '");
		query.append(pyMes.isbPrimerNivel());
		query.append("', ");
		query.append("B_SEGUNDO_NIVEL = '");
		query.append(pyMes.isbSegundoNivel());
		query.append("', ");
		query.append("B_TERCER_NIVEL = '");
		query.append(pyMes.isbTercerNivel());
		query.append("', ");
		query.append("B_AGUASCALIENTES = '");
		query.append(pyMes.isbAguascalientes());
		query.append("', ");
		query.append("B_BAJA_CALIFORNIA_SUR = '");
		query.append(pyMes.isbBajaCaliforniaSur());
		query.append("', ");
		query.append("B_BAJA_CALIFORNIA_NORTE = '");
		query.append(pyMes.isbBajaCaliforniaNorte());
		query.append("', ");
		query.append("B_CAMPECHE = '");
		query.append(pyMes.isbCampeche());
		query.append("', ");
		query.append("B_CHIAPAS = '");
		query.append(pyMes.isbChiapas());
		query.append("', ");
		query.append("B_CHIHUAHUA = '");
		query.append(pyMes.isbChihuahua());
		query.append("', ");
		query.append("B_COAHUILA = '");
		query.append(pyMes.isbCoahuila());
		query.append("', ");
		query.append("B_COLIMA = '");
		query.append(pyMes.isbColima());
		query.append("', ");
		query.append("B_DISTRITO_FEDERAL = '");
		query.append(pyMes.isbDistritoFederal());
		query.append("', ");
		query.append("B_DURANGO = '");
		query.append(pyMes.isbDurango());
		query.append("', ");
		query.append("B_GUANAJUATO = '");
		query.append(pyMes.isbGuanajuato());
		query.append("', ");
		query.append("B_GUERRERO = '");
		query.append(pyMes.isbGuerrero());
		query.append("', ");
		query.append("B_HIDALGO = '");
		query.append(pyMes.isbHidalgo());
		query.append("', ");
		query.append("B_JALISCO = '");
		query.append(pyMes.isbJalisco());
		query.append("', ");
		query.append("B_MEXICO = '");
		query.append(pyMes.isbMexico());
		query.append("', ");
		query.append("B_MICHOACAN = '");
		query.append(pyMes.isbMichoacan());
		query.append("', ");
		query.append("B_MORELOS = '");
		query.append(pyMes.isbMorelos());
		query.append("', ");
		query.append("B_NAYARIT = '");
		query.append(pyMes.isbNayarit());
		query.append("', ");
		query.append("B_NUEVO_LEON = '");
		query.append(pyMes.isbNuevoLeon());
		query.append("', ");
		query.append("B_OAXACA = '");
		query.append(pyMes.isbOaxaca());
		query.append("', ");
		query.append("B_PUEBLA = '");
		query.append(pyMes.isbPuebla());
		query.append("', ");
		query.append("B_QUERETARO = '");
		query.append(pyMes.isbQueretaro());
		query.append("', ");
		query.append("B_QUINTANA_ROO = '");
		query.append(pyMes.isbQuintanaRoo());
		query.append("', ");
		query.append("B_SAN_LUIS_POTOSI = '");
		query.append(pyMes.isbSanLuisPotosi());
		query.append("', ");
		query.append("B_SINALOA = '");
		query.append(pyMes.isbSinaloa());
		query.append("', ");
		query.append("B_SONORA = '");
		query.append(pyMes.isbSonora());
		query.append("', ");
		query.append("B_TABASCO = '");
		query.append(pyMes.isbTabasco());
		query.append("', ");
		query.append("B_TAMAULIPAS = '");
		query.append(pyMes.isbTamaulipas());
		query.append("', ");
		query.append("B_TLAXCALA = '");
		query.append(pyMes.isbTlaxcala());
		query.append("', ");
		query.append("B_VERACRUZ = '");
		query.append(pyMes.isbVeracruz());
		query.append("', ");
		query.append("B_YUCATAN = '");
		query.append(pyMes.isbYucatan());
		query.append("', ");
		query.append("B_ZACATECAS = '");
		query.append(pyMes.isbZacatecas());
		query.append("'");
		query.append(" WHERE ID_USUARIO = ");
		query.append(pyMes.getIdUsuario());
		query.append(" ");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
			"Los datos de la PyME se actualizaron satisfactoriamente.");
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
	
	public Mensaje saveRelDomicilios(Domicilios domicilios,
			PyMEs pyMes) throws DaoException {

		log.debug("saveRelDomicilios()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.REL_DOMICILIOS_USUARIO (");
		query.append("ID_USUARIO, ");
		query.append("ID_DOMICILIO) ");
		query.append("VALUES ('");
		query.append(pyMes.getIdUsuario());
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
	
	@Override
	public Mensaje updateClientes(Clientes clientes)
			throws JdbcDaoException {
		log.debug("updateClientes()");

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
					"Los datos de los clientes se actualizaron satisfactoriamente.");
		} catch (Exception e) {
			log.fatal("ERROR al actualizar los datos de los CLIENTES, " + e);
			return new Mensaje(1,
					"No es posible actualizar los datos de la PyME, intentelo más tarde.");
		}
	}
	
	public Mensaje saveCertificaciones(Certificaciones certificaciones) throws DaoException {
		log.debug("saveCertificaciones()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.CERTIFICACIONES (");
		query.append("ID_USUARIO, ");
		query.append("CERTIFICACION, ");
		query.append("FECHA_CERTIFICACION) ");
		query.append("VALUES ('");
		query.append(certificaciones.getIdUsuario());
		query.append("', '");
		query.append(certificaciones.getCertificacion());
		query.append("', ");
		query.append("sysdate");
		query.append(")");
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
	
	@Override
	public Mensaje updateCertificaciones(Certificaciones certificaciones)
			throws JdbcDaoException {
		log.debug("updateCertificaciones()");

		StringBuffer query = new StringBuffer();
		query.append("UPDATE ");
		query.append("INFRA.CERTIFICACIONES SET ");
		query.append("CERTIFICACION = '");
		query.append(certificaciones.getCertificacion());
		query.append("', ");
		query.append("FECHA_CERTIFICACION = '");
		query.append(certificaciones.getFechaCertificacion());		
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
			log.fatal("ERROR al actualizar los datos de los CERTIFICACIONES, " + e);
			return new Mensaje(1,
					"No es posible registrar los datos, intentelo más tarde.");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PyMEs> getBusquedaPyMEs(String busqueda, String estado, String codigoPostal, String sector, String subSector)
			throws DaoException {
		log.debug("getBusquedaPyMEs()");

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("P.NOMBRE_COMERCIAL, ");
		query.append("DO.ESTADO, ");
		query.append("P.TELEFONO_CONTACTO, ");
		query.append("P.NOMBRE_CONTACTO, ");
		query.append("P.APP_PATERNO, ");
		query.append("P.APP_MATERNO, ");
		query.append("P.CORREO_ELECTRONICO_CONTACTO ");
		query.append("FROM INFRA.PYMES AS P ");
		query.append("LEFT JOIN INFRA.REL_DOMICILIOS_USUARIO AS RDU ");
		query.append("ON P.ID_USUARIO=RDU.ID_USUARIO ");
		query.append("LEFT JOIN INFRA.DOMICILIOS AS DO ");
		query.append("ON RDU.ID_DOMICILIO=DO.ID_DOMICILIO ");
		query.append("WHERE (P.NOMBRE_COMERCIAL LIKE '%" + busqueda + "%' ");
		query.append("OR DO.ESTADO LIKE '%" + busqueda + "%' ");
		query.append("OR P.TELEFONO_CONTACTO LIKE '%" + busqueda + "%' ");
		query.append("OR P.NOMBRE_CONTACTO LIKE '%" + busqueda + "%' ");
		query.append("OR P.APP_PATERNO LIKE '%" + busqueda + "%' ");
		query.append("OR P.APP_MATERNO LIKE '%" + busqueda + "%' ");
		query.append("OR P.CORREO_ELECTRONICO_CONTACTO LIKE '%" + busqueda + "%') ");
		query.append("OR DO.ESTADO LIKE '%"+ estado +"%' ");
		query.append("OR DO.CODIGO_POSTAL LIKE '%" + codigoPostal + "%' ");
		log.debug("query=" + query);
		log.debug("estado = " + estado);
		log.debug("CP = " + codigoPostal);
	
		try{
			List<PyMEs> listPyME =  getJdbcTemplate().query(query.toString(), new BusquedaPyMEsRowMapper()) ;
			log.debug("result=" + listPyME);
			return listPyME;
			
		}catch(Exception e){
			log.debug("Aquíe está el error: " + e);
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
		public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
			PyMEs pymes = new PyMEs();
			pymes.setNombreComercial(rs.getString("NOMBRE_COMERCIAL"));
			pymes.setEstado(rs.getString("ESTADO"));
			pymes.setTelefonoContacto(rs.getString("TELEFONO_CONTACTO"));
			pymes.setNombreContacto(rs.getString("NOMBRE_CONTACTO"));
			pymes.setAppPaterno(rs.getString("APP_PATERNO"));
			pymes.setAppMaterno(rs.getString("APP_MATERNO"));
			pymes.setCorreoElectronicoContacto(rs.getString("CORREO_ELECTRONICO_CONTACTO"));
			return pymes;
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiciosDiplomado> getServiciosDiplomados(ServiciosDiplomado serviciosDiplomado)
			throws DaoException {
		log.debug("getServiciosDiplomados()");

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_DIPLOMADO, ");
		query.append("ID_USUARIO, ");
		query.append("TITULO, ");
		query.append("FECHA, ");
		query.append("MENSAJE ");
		query.append("FROM INFRA.SERVICIOS_DIPLOMADO ");
		log.debug("query=" + query);

		List<ServiciosDiplomado> servDiplomados = getJdbcTemplate().query(query.toString(),
				new ServiciosDiplomadoRowMapper());
		log.debug("Lista de serv" + servDiplomados);
		return servDiplomados;
	}
	
	@SuppressWarnings("rawtypes")
	public class ServiciosDiplomadoRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			ServicionDiplomadosResultSetExtractor extractor = new ServicionDiplomadosResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class ServicionDiplomadosResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			ServiciosDiplomado servDiplomados = new ServiciosDiplomado();
			servDiplomados.setIdDiplomado(rs.getInt("ID_DIPLOMADO"));
			servDiplomados.setIdUsuario(rs.getInt("ID_USUARIO"));
			servDiplomados.setTitulo(rs.getString("TITULO"));
			servDiplomados.setFecha(rs.getDate("FECHA"));
			servDiplomados.setMensaje(rs.getString("MENSAJE"));
			return servDiplomados;
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
			return new Mensaje(0,
					"Estimada PYME ha quedado inscrita en los diplomados seleccionados. En breve nos comunicaremos con ustedes para confirmar su asistencia.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar al asistente, " + e);
			return new Mensaje(1, "No es posible realizar el registro, intentelo más tarde.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Requerimientos> getRequerimientos(String busqueda, String tractoraReq, String fechaDesde, String fechaHasta)
			throws DaoException {
		log.debug("getRequerimientos()");

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("R.ID_REQUERIMIENTO, ");
		query.append("T.EMPRESA, ");
		query.append("R.DESCRIPCION, ");
		query.append("R.FECHA_SUMINISTRO, ");
		query.append("R.FECHA_EXPIRA ");
		query.append("FROM INFRA.TRACTORAS AS T ");
		query.append("LEFT JOIN INFRA.REQUERIMIENTOS AS R ");
		query.append("ON T.ID_USUARIO=R.ID_TRACTORA ");
		query.append("WHERE (T.EMPRESA LIKE '%" + busqueda + "%' ");
		query.append("OR R.DESCRIPCION LIKE '%" + busqueda + "%') ");
		query.append("OR T.EMPRESA LIKE '%"+ tractoraReq +"%' ");
		query.append("OR R.FECHA_SUMINISTRO LIKE '%" + fechaDesde +"%' ");
		query.append("OR R.FECHA_EXPIRA LIKE '%" + fechaHasta +"%' ");
		log.debug("query=" + query);
	
		try{
			List<Requerimientos> listReq =  getJdbcTemplate().query(query.toString(), new RequerimientosPyMEsRowMapper()) ;
			log.debug("result=" + listReq);
			return listReq;
			
		}catch(Exception e){
			log.debug("Aquíe está e: " + e);
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
	public class RequerimientosPyMEsResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
			Requerimientos req = new Requerimientos();
			req.setIdRequerimiento(rs.getInt("ID_REQUERIMIENTO"));
			req.setNombreTractora(rs.getString("EMPRESA"));
			req.setDescripcion(rs.getString("DESCRIPCION"));
			req.setFechaExpira(rs.getDate("FECHA_SUMINISTRO"));
			req.setFechaExpira(rs.getDate("FECHA_EXPIRA"));
			return req;
			
		}
	}
	
	public Mensaje saveConsultorias(ServiciosConsultoria seviciosConsultoria) throws DaoException {
		log.debug("saveConsultorias()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.SERVICIOS_CONSULTORIA (");
		query.append("ID_USUARIO, ");
		query.append("B_CONSULTORIA_40, ");
		query.append("B_CONSULTORIA_60, ");
		query.append("B_CONSULTORIA_80, ");
		query.append("MENSAJE) ");
		query.append("VALUES ('");
		query.append(seviciosConsultoria.getIdUsuario());
		query.append("', ");
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
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Estimada PYME, en breve un consultor se pondrá en contacto con ustedes, a nombre del CCMX.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar la consultoria, " + e);
			return new Mensaje(1, "No es posible realizar el registro, intentelo más tarde.");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tractoras> getTractoras()
			throws DaoException {
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
	public Requerimientos getShowRequerimientos(int idRequerimiento)
			throws DaoException {
		log.debug("getShowRequerimientos()");

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
	public class ShowRequerimientosPyMEsResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
			Requerimientos req = new Requerimientos();
			req.setIdRequerimiento(rs.getInt("ID_REQUERIMIENTO"));
			req.setNombreTractora(rs.getString("EMPRESA"));
			req.setDescripcion(rs.getString("DESCRIPCION"));
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
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Envío exitoso de su cotización.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar la respuesta del requerimiento, " + e);
			return new Mensaje(1, "No es posible enviar la respuesta l requerimiento, intentelo más tarde.");
		}
	}

	@Override
	public Mensaje saveServDiplomados(ServiciosDiplomado serviciosDiplomado)
			throws DaoException {
		log.debug("saveRespuestas()");

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
		query.append("', ");
		query.append("sysdate");
		query.append(", '");
		query.append(serviciosDiplomado.getMensaje());
		query.append("')");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"Estimada PYME ha quedado inscrita en el diplomado seleccionado. Registre el nombre de los asistentes y en breve nos comunicaremos con ustedes para confirmar su asistencia.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar el servicio, " + e);
			return new Mensaje(1, "No es posible registrar el servicio, intentelo más tarde.");
		}
	}
}