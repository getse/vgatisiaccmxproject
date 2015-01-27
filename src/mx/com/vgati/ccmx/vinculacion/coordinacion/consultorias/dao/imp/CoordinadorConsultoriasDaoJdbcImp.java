/*
 * CoordinadorConsultoriasDaoJdbcImp.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Facturas;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dao.CoordinadorConsultoriasDao;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dto.Consultorias;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.framework.dao.AbstractBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.util.Null;
import mx.com.vgati.framework.util.Util;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class CoordinadorConsultoriasDaoJdbcImp extends AbstractBaseJdbcDao
		implements CoordinadorConsultoriasDao {

	@Override
	public List<Consultorias> getConsultorias(int id) throws DaoException {
		return null;
	}

	@Override
	public List<PyMEs> getPyMEs() throws DaoException {
		log.debug("getPyMEs()");
		StringBuffer query = new StringBuffer();
		query.append("SELECT T.EMPRESA, ");
		query.append("P.ID_USUARIO, ");
		query.append("P.PERSONALIDAD_JURIDICA, ");
		query.append("P.NOMBRE_COMERCIAL, ");
		query.append("P.B_INHIBIR_VINCULACION, ");
		query.append("P.LIBERA_EXPEDIENTE, ");
		query.append("C.NOMBRE, ");
		query.append("C.APELLIDO_PATERNO, ");
		query.append("C.APELLIDO_MATERNO, ");
		query.append("C.CORREO_ELECTRONICO ");
		query.append("FROM INFRA.PYMES AS P ");
		query.append("JOIN INFRA.REL_PYMES_TRACTORAS AS REL ");
		query.append("ON P.ID_USUARIO = REL.ID_USUARIO_PYME ");
		query.append("JOIN INFRA.TRACTORAS AS T ");
		query.append("ON REL.ID_USUARIO_TRACTORA = T.ID_USUARIO ");
		query.append("JOIN INFRA.CONTACTOS AS C ");
		query.append("ON P.ID_USUARIO = C.ID_USUARIO ");
		query.append("WHERE P.ID_USUARIO NOT IN (SELECT ID_USUARIO_PYME FROM INFRA.REL_CONSULTORAS_PYME) ");
		query.append("AND T.ID_TRACTORA_PADRE = 0 ");
		query.append("AND C.B_PRINCIPAL = true ");
		query.append("ORDER BY ID_USUARIO ASC");
		log.debug("query=" + query);

		@SuppressWarnings("unchecked")
		List<PyMEs> pymes = getJdbcTemplate().query(query.toString(), new PyMEsRowMapper());
		return pymes;

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
			pymes.setTractora(rs.getString("EMPRESA"));
			pymes.setIdUsuario(rs.getInt("ID_USUARIO"));
			pymes.setPersonalidadJuridica(rs.getString("PERSONALIDAD_JURIDICA"));
			pymes.setNombreComercial(rs.getString("NOMBRE_COMERCIAL"));
			pymes.setbInhibirVinculacion(rs.getBoolean("B_INHIBIR_VINCULACION"));
			pymes.setEstatus(rs.getBoolean("LIBERA_EXPEDIENTE"));
			pymes.setNombreContacto1(rs.getString("NOMBRE"));
			pymes.setAppPaterno1(rs.getString("APELLIDO_PATERNO"));
			pymes.setAppMaterno1(rs.getString("APELLIDO_MATERNO"));
			pymes.setCorreoElectronicoContacto1(rs.getString("CORREO_ELECTRONICO"));
			return pymes;
		}
	}
	
	@Override
	public List<Consultoras> getConsultoras() throws DaoException {
		log.debug("getConsultoras()");
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_CONSULTORA, ");
		query.append("ID_USUARIO, ");
		query.append("EMPRESA ");
		query.append("FROM INFRA.CONSULTORAS ");
		query.append("WHERE ID_CONSULTORA_PADRE = 0 ");
		query.append("ORDER BY EMPRESA ASC");
		log.debug("query=" + query);

		@SuppressWarnings("unchecked")
		List<Consultoras> consultoras = getJdbcTemplate().query(query.toString(), new ConsultorasRowMapper());
		return consultoras;
	}
	
	@SuppressWarnings("rawtypes")
	public class ConsultorasRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			ConsultorasResultSetExtractor extractor = new ConsultorasResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class ConsultorasResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Consultoras c = new Consultoras();
			c.setIdConsultora(rs.getInt("ID_CONSULTORA"));
			c.setIdUsuario(rs.getInt("ID_USUARIO"));
			c.setEmpresa(rs.getString("EMPRESA"));
			return c;
		}
	}

	@Override
	public List<PyMEs> getDiplomasPyMEs() throws DaoException {
		log.debug("getDiplomasPyMEs()");
		StringBuffer query = new StringBuffer();
		query.append("SELECT SERV.ID_CONSULTORIA, ");
		query.append("P.ID_USUARIO, ");
		query.append("P.NOMBRE_COMERCIAL, ");
		query.append("SERV.FECHA_TERMINO, ");
		query.append("C.EMPRESA, ");
		query.append("CASE WHEN SERV.B_CONSULTORIA_20 THEN '20' ");
		query.append("WHEN SERV.B_CONSULTORIA_40 THEN '40' ");
		query.append("WHEN SERV.B_CONSULTORIA_60 THEN '60' ");
		query.append("WHEN SERV.B_CONSULTORIA_80 THEN '80' ");
		query.append("ELSE '0' END AS SERVICIO ");
		query.append("FROM INFRA.PYMES AS P ");
		query.append("JOIN INFRA.SERVICIOS_CONSULTORIA AS SERV ");
		query.append("ON P.ID_USUARIO = SERV.ID_USUARIO ");
		query.append("JOIN INFRA.REL_CONSULTORAS_PYME AS REL ");
		query.append("ON P.ID_USUARIO = REL.ID_USUARIO_PYME ");
		query.append("JOIN INFRA.CONSULTORAS AS C ");
		query.append("ON REL.ID_USUARIO_CONSULTOR = C.ID_USUARIO ");
		query.append("WHERE C.ID_CONSULTORA_PADRE = 0 AND ");
		query.append("SERV.FECHA_TERMINO < CURRENT_DATE ");
		query.append("AND B_DIPLOMA IS NULL");
		log.debug("query=" + query);

		@SuppressWarnings("unchecked")
		List<PyMEs> pymes = getJdbcTemplate().query(query.toString(), new DiplomasPyMEsRowMapper());
		return pymes;
	}
	
	@SuppressWarnings("rawtypes")
	public class DiplomasPyMEsRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			DiplomasPyMEsResultSetExtractor extractor = new DiplomasPyMEsResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class DiplomasPyMEsResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			PyMEs pymes = new PyMEs();
			pymes.setIdServicioConsultoria(rs.getInt("ID_CONSULTORIA"));
			pymes.setIdUsuario(rs.getInt("ID_USUARIO"));
			pymes.setNombreComercial(rs.getString("NOMBRE_COMERCIAL"));
			pymes.setFecha(rs.getDate("FECHA_TERMINO"));
			pymes.setTractora(rs.getString("EMPRESA"));
			pymes.setHoras(rs.getInt("SERVICIO"));
			return pymes;
		}
	}

	@Override
	public Mensaje asignaPyMEs(int idConsultora, String idPyMEs) throws DaoException {
		log.debug("asignaPyMEs()");
		StringBuffer query = new StringBuffer();

		String tempIdUser = "";
		StringTokenizer st = new StringTokenizer(idPyMEs, ",");
		while (st.hasMoreElements()) {
			tempIdUser = (String) st.nextElement();
			query = new StringBuffer();
			query.append("INSERT INTO ");
			query.append("INFRA.REL_CONSULTORAS_PYME");
			query.append("( ID_USUARIO_PYME");
			query.append(", ID_USUARIO_CONSULTOR ) ");
			query.append("VALUES ( ");
			query.append(tempIdUser);
			query.append(", ");
			query.append(idConsultora);
			query.append(" )");
			log.debug("query=" + query);
			try {
				getJdbcTemplate().update(query.toString());
				query = new StringBuffer();
				query.append("INSERT INTO  INFRA.SERVICIOS_CONSULTORIA( ");
				query.append("ID_USUARIO ");
				query.append(",B_CONSULTORIA_20 ) "); 
				query.append("SELECT "); 
				query.append(tempIdUser);
				query.append(",TRUE ");
				query.append("WHERE NOT EXISTS (SELECT ID_USUARIO ");
				query.append("FROM INFRA.SERVICIOS_CONSULTORIA ");
				query.append("WHERE  ID_USUARIO =  ");
				query.append(tempIdUser);
				query.append(" AND B_CONSULTORIA_20)");
				log.debug("Insertando servicio de consultoria 20 horas, en caso de no  tener " + query);
				getJdbcTemplate().update(query.toString());
			} catch (Exception e) {
				log.fatal("ERROR al asignar PyMEs con Consultora. " + e);
				return new Mensaje(1, "Ocurrió un error al intentar asignar las PyMEs a la Consultora seleccionada.");
			}
		}
		return new Mensaje(0,
				"Las PyMEs se asignaron satisfactoriamente a la Consultora seleccionada.");
	}
	
	@Override
	public Mensaje regDiplomas( String idPyMEs) throws DaoException {
		log.debug("regDiplomas()");
		StringBuffer query = new StringBuffer();

		StringTokenizer st = new StringTokenizer(idPyMEs, ",");
		while (st.hasMoreElements()) {
			query = new StringBuffer();
			query.append("UPDATE ");
			query.append("INFRA.SERVICIOS_CONSULTORIA SET ");
			query.append("B_DIPLOMA = ");
			query.append(true);
			query.append(" WHERE ID_CONSULTORIA = ");
			query.append(st.nextElement());
			log.debug("query=" + query);
			try {
				getJdbcTemplate().update(query.toString());
			} catch (Exception e) {
				log.fatal("ERROR al registrar los Diplomas de las PyMEs. " + e);
				return new Mensaje(1, "Ocurrió un error al intentar registrar los diplomas de las PyMEs seleccionadas, intentelo más tarde.");
			}
		}
		return new Mensaje(0,
				"Los Diplomas generados se registraron satisfactoriamente.");
	}

	@Override
	public List<Facturas> getFacturas(int idUsuario) throws DaoException {
		log.debug("getFacturas()");
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_FACTURA, ");
		query.append("ID_USUARIO, ");
		query.append("(SELECT ");
		query.append("SUM(CASE ");
		query.append("WHEN P.TIPO = 'Anticipo' THEN SELECT COSTO_ANTICIPO FROM INFRA.CONSULTORAS WHERE ID_USUARIO = F.ID_USUARIO ");
		query.append("WHEN P.TIPO = 'Abono1' THEN SELECT COSTO_ABONO1 FROM INFRA.CONSULTORAS WHERE ID_USUARIO = F.ID_USUARIO ");
		query.append("WHEN P.TIPO = 'Abono2' THEN SELECT COSTO_ABONO2 FROM INFRA.CONSULTORAS WHERE ID_USUARIO = F.ID_USUARIO ");
		query.append("WHEN P.TIPO = 'Finiquito' THEN SELECT COSTO_FINIQUITO FROM INFRA.CONSULTORAS WHERE ID_USUARIO = F.ID_USUARIO ");
		query.append("ELSE '0' END ) ");
		query.append("FROM INFRA.FACTURAS AS F ");
		query.append("LEFT JOIN INFRA.PAGOS AS P ");
		query.append("ON F.ID_FACTURA = P.NUMERO ");
		query.append("LEFT JOIN INFRA.CONSULTORAS AS C ");
		query.append("ON F.ID_USUARIO = C.ID_USUARIO ");
		query.append("WHERE F.ID_USUARIO = FAC.ID_USUARIO ");
		query.append("AND F.ID_FACTURA = FAC.ID_FACTURA ) ");
		query.append("AS IMPORTE_TOTAL ");
		query.append("FROM INFRA.FACTURAS AS FAC ");
		query.append("WHERE FAC.ID_USUARIO = ");
		query.append(idUsuario);
		query.append(" AND ESTATUS = 'Pendiente' ");
		query.append("ORDER BY ID_FACTURA ASC");
		log.debug("query=" + query);

		@SuppressWarnings("unchecked")
		List<Facturas> facturas = getJdbcTemplate().query(query.toString(), new FacturasRowMapper());
		return facturas;

	}

	@SuppressWarnings("rawtypes")
	public class FacturasRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			FacturasResultSetExtractor extractor = new FacturasResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class FacturasResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Facturas fac = new Facturas();
			fac.setIdFactura(rs.getString("ID_FACTURA"));
			fac.setIdUsuario(rs.getInt("ID_USUARIO"));
			fac.setImporteTotal(rs.getString("IMPORTE_TOTAL"));
			return fac;
		}
	}
	
	@Override
	public List<PyMEs> getDetalleFacturas(String numeroFactura) throws DaoException {
		log.debug("getDetalleFacturas()");
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT P.NOMBRE_COMERCIAL, ");
		query.append("CASE WHEN SERV.B_CONSULTORIA_20 THEN '20' ");
		query.append("WHEN SERV.B_CONSULTORIA_40 THEN '40' ");
		query.append("WHEN SERV.B_CONSULTORIA_60 THEN '60' ");
		query.append("WHEN SERV.B_CONSULTORIA_80 THEN '80' ");
		query.append("ELSE '0' END AS HORAS, ");
		query.append("C.EMPRESA, ");
		query.append("SERV.ESTATUS, ");
		query.append("PA.TIPO, ");
		query.append("PA.ID_PAGO, ");
		query.append("CASE WHEN PA.TIPO = 'Anticipo' THEN SELECT COSTO_ANTICIPO FROM INFRA.CONSULTORAS WHERE ID_USUARIO = C.ID_USUARIO ");
		query.append("WHEN PA.TIPO = 'Abono1' THEN SELECT COSTO_ABONO1 FROM INFRA.CONSULTORAS WHERE ID_USUARIO = C.ID_USUARIO ");
		query.append("WHEN PA.TIPO = 'Abono2' THEN SELECT COSTO_ABONO2 FROM INFRA.CONSULTORAS WHERE ID_USUARIO = C.ID_USUARIO ");
		query.append("WHEN PA.TIPO = 'Finiquito' THEN SELECT COSTO_FINIQUITO FROM INFRA.CONSULTORAS WHERE ID_USUARIO = C.ID_USUARIO ");
		query.append("ELSE '0' END AS MONTO ");
		query.append("FROM INFRA.PYMES AS P ");
		query.append("JOIN INFRA.SERVICIOS_CONSULTORIA AS SERV ");
		query.append("ON P.ID_USUARIO = SERV.ID_USUARIO ");
		query.append("JOIN INFRA.REL_CONSULTORAS_PYME AS RCP ");
		query.append("ON P.ID_USUARIO = RCP.ID_USUARIO_PYME ");
		query.append("JOIN INFRA.CONSULTORAS AS C ");
		query.append("ON RCP.ID_USUARIO_CONSULTOR = C.ID_USUARIO ");
		query.append("JOIN INFRA.PAGOS AS PA ");
		query.append("ON SERV.ID_CONSULTORIA = PA.ID_SERVICO_CONSULTORIA ");
		query.append("WHERE C.ID_CONSULTORA_PADRE = 0 ");
		query.append("AND PA.NUMERO = '");
		query.append(numeroFactura);
		query.append("'");
		log.debug("query=" + query);

		@SuppressWarnings("unchecked")
		List<PyMEs> pymes = getJdbcTemplate().query(query.toString(), new DetalleFacturasRowMapper());
		return pymes;

	}

	@SuppressWarnings("rawtypes")
	public class DetalleFacturasRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			DetalleFacturasResultSetExtractor extractor = new DetalleFacturasResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class DetalleFacturasResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			PyMEs p = new PyMEs();
			p.setNombreComercial(rs.getString("NOMBRE_COMERCIAL"));
			p.setHoras(rs.getInt("HORAS"));
			p.setTractora(rs.getString("EMPRESA"));
			p.setEstatusConsultoria(rs.getString("ESTATUS"));
			p.setTipo(rs.getString("TIPO"));
			p.setIdPago(rs.getInt("ID_PAGO"));
			p.setMonto(rs.getString("MONTO"));
			return p;
		}
	}

	@Override
	public Mensaje liberaFacturas(String factura, String monto) throws DaoException {
		log.debug("liberaFacturas()");
		StringBuffer query = new StringBuffer();

		StringTokenizer st = new StringTokenizer(factura, ",");
		StringTokenizer m = new StringTokenizer(monto, ",");
		while (st.hasMoreElements()) {
			query = new StringBuffer();
			query.append("UPDATE ");
			query.append("INFRA.FACTURAS SET ");
			query.append("ESTATUS = 'Pagada', ");
			query.append("IMPORTE_TOTAL = '");
			query.append(m.nextElement());
			query.append("' ");
			query.append("WHERE ID_FACTURA = '");
			query.append(st.nextElement());
			query.append("'");
			log.debug("query=" + query);
			try {
				getJdbcTemplate().update(query.toString());
			} catch (Exception e) {
				log.fatal("ERROR al liberar las facturas de las PyMEs. " + e);
				return new Mensaje(1, "Ocurrió un error al intentar liberar las facturas de las PyMEs seleccionadas, intentelo más tarde.");
			}
		}
		return new Mensaje(0,
				"Las Facturas se han liberado satisfactoriamente.");
	}

	@Override
	public Mensaje quitarRelFacturas(String idPagosFacturas)
			throws DaoException {
		log.debug("quitarRelFacturas()");
		StringBuffer query = new StringBuffer();

		StringTokenizer st = new StringTokenizer(idPagosFacturas, ",");
		while (st.hasMoreElements()) {
			query = new StringBuffer();
			query.append("DELETE ");
			query.append("INFRA.PAGOS ");
			query.append("WHERE ID_PAGO = ");
			query.append(st.nextElement());
			log.debug("query=" + query);
			try {
				getJdbcTemplate().update(query.toString());
			} catch (Exception e) {
				log.fatal("ERROR al eliminar las PyMEs de la factura. " + e);
				return new Mensaje(1, "Ocurrió un error al intentar eliminar las PyMEs de la factura seleccionada, intentelo más tarde.");
			}
		}
		return new Mensaje(0,
				"Las PyMEs se han eliminado satisfactoriamente de la Factura seleccionada.");
	}
	
	@Override
	public List<PyMEs> getReasignaPyME(String busqueda, String tractora, String cveScian) throws DaoException {
		log.debug("getReasignaPyME()");

		List<String> l = new Util().filtro(busqueda);

		StringBuffer query = new StringBuffer();		
		query.append("SELECT DISTINCT(P.ID_USUARIO), ");
		query.append("P.NOMBRE_COMERCIAL, ");
		query.append("T.EMPRESA AS TRACTORA, ");
		query.append("D.ESTADO, ");
		query.append("CO.EMPRESA AS CONSULTORA ");
		query.append("FROM INFRA.PYMES AS P ");
		query.append("JOIN INFRA.REL_PYMES_TRACTORAS AS RPT ");
		query.append("ON P.ID_USUARIO = RPT.ID_USUARIO_PYME ");
		query.append("JOIN INFRA.TRACTORAS AS T ");
		query.append("ON RPT.ID_USUARIO_TRACTORA = T.ID_USUARIO ");
		query.append("LEFT JOIN INFRA.REL_DOMICILIOS_USUARIO AS RDU ");
		query.append("ON P.ID_USUARIO = RDU.ID_USUARIO ");
		query.append("LEFT JOIN INFRA.DOMICILIOS AS D ");
		query.append("ON RDU.ID_DOMICILIO = D.ID_DOMICILIO ");
		query.append("LEFT JOIN INFRA.REL_CONSULTORAS_PYME AS RCP ");
		query.append("ON P.ID_USUARIO = RCP.ID_USUARIO_PYME ");
		query.append("LEFT JOIN INFRA.CONSULTORAS AS CO ");
		query.append("ON RCP.ID_USUARIO_CONSULTOR = CO.ID_USUARIO ");
		query.append("LEFT JOIN INFRA.PRODUCTOS AS PP ");
		query.append("ON P.ID_USUARIO = PP.ID_USUARIO ");
		query.append("LEFT JOIN INFRA.CATEGORIAS AS CAT ");
		query.append("ON P.ID_USUARIO = CAT.ID_USUARIO ");
		query.append("WHERE T.ID_TRACTORA_PADRE = 0 ");
		query.append("AND CO.ID_CONSULTORA_PADRE = 0 ");
		query.append("AND P.ID_USUARIO NOT IN( ");
		query.append("SELECT REL.ID_USUARIO_PYME ");
		query.append("FROM INFRA.CONSULTORAS CONS ");
		query.append("JOIN INFRA.REL_CONSULTORAS_PYME REL ");
		query.append("ON REL.ID_USUARIO_CONSULTOR = CONS.ID_USUARIO ");
		query.append("WHERE ID_CONSULTORA_PADRE > 0 ) ");
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
		if (!tractora.isEmpty())
			query.append(" AND T.EMPRESA LIKE '%".concat(tractora).concat("%' "));

		if (!cveScian.isEmpty())
			query.append(" AND CAT.CVE_SCIAN LIKE '"
					.concat(cveScian.length() > 3 ? cveScian.substring(0, 3)
							: cveScian).concat("%' "));
		query.append(" ) ");
		query.append("ORDER BY P.ID_USUARIO ");
		log.debug("query=" + query);

		@SuppressWarnings("unchecked")
		List<PyMEs> pymes = getJdbcTemplate().query(query.toString(), new ReasignaPyMERowMapper());
		return pymes;

	}

	@SuppressWarnings("rawtypes")
	public class ReasignaPyMERowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			ReasignaPyMEResultSetExtractor extractor = new ReasignaPyMEResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class ReasignaPyMEResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			PyMEs pymes = new PyMEs();
			pymes.setIdUsuario(rs.getInt("ID_USUARIO"));
			pymes.setNombreComercial(rs.getString("NOMBRE_COMERCIAL"));
			pymes.setEstado(rs.getString("ESTADO"));
			pymes.setTractora(rs.getString("TRACTORA"));
			pymes.setEmpresa(rs.getString("CONSULTORA"));
			return pymes;
		}
	}
	
	@Override
	public Mensaje reAsignaPyMEs(int idConsultora, String idPyMEs) throws DaoException {
		log.debug("reAsignaPyMEs()");
		StringBuffer query = new StringBuffer();
		String tempIdUser = "";
		StringTokenizer st = new StringTokenizer(idPyMEs, ",");
		while (st.hasMoreElements()) {
			tempIdUser = (String) st.nextElement();
			query = new StringBuffer();
			query.append("UPDATE ");
			query.append("INFRA.REL_CONSULTORAS_PYME SET ");
			query.append("ID_USUARIO_CONSULTOR = "+ idConsultora);
			query.append(" WHERE ID_USUARIO_PYME = ");
			query.append(tempIdUser);
			log.debug("query=" + query);
			try {
				getJdbcTemplate().update(query.toString());
				query = new StringBuffer();
				query.append("INSERT INTO  INFRA.SERVICIOS_CONSULTORIA( ");
				query.append("ID_USUARIO ");
				query.append(",B_CONSULTORIA_20 ) "); 
				query.append("SELECT "); 
				query.append(tempIdUser);
				query.append(",TRUE ");
				query.append("WHERE NOT EXISTS (SELECT ID_USUARIO ");
				query.append("FROM INFRA.SERVICIOS_CONSULTORIA ");
				query.append("WHERE  ID_USUARIO =  ");
				query.append(tempIdUser);
				query.append(" AND B_CONSULTORIA_20)");
				log.debug("Insertando servicio de consultoria 20 horas, en caso de no  tener " + query);
				getJdbcTemplate().update(query.toString());
			} catch (Exception e) {
				log.fatal("ERROR al Reasignar la empresa Consultora a las PyMEs seleccionadas. " + e);
				return new Mensaje(1, "Ocurrió un error al intentar reasignar la empresa Consultora de las PyMEs seleccionadas, intentelo más tarde.");
			}
		}
		return new Mensaje(0,
				"Las PyMEs seleccionadas han sido reasignadas satisfactoriamente.");
	}
	
	@Override
	public List<Facturas> getInfoFacturas(int idUsuario) throws DaoException {
		log.debug("getInfoFacturas()");
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT ");
		query.append("ID_FACTURA ");
		query.append("FROM INFRA.FACTURAS ");
		query.append("WHERE ID_USUARIO = " + idUsuario);
		query.append(" AND ESTATUS = 'Pagada' ");
		query.append("AND FECHA_PAGO IS NULL ");
		query.append("ORDER BY ID_FACTURA ASC");
		
		log.debug("query=" + query);

		@SuppressWarnings("unchecked")
		List<Facturas> facturas = getJdbcTemplate().query(query.toString(), new InfoFacturasRowMapper());
		return facturas;

	}

	@SuppressWarnings("rawtypes")
	public class InfoFacturasRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			InfoFacturasResultSetExtractor extractor = new InfoFacturasResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class InfoFacturasResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Facturas f = new Facturas();
			f.setIdFactura(rs.getString("ID_FACTURA"));
			return f;
		}
	}

	@Override
	public Mensaje saveFechaFacturas(String idFacturas, Date fechaPago)
			throws DaoException {
		log.debug("saveFechaFacturas()");
		StringBuffer query = new StringBuffer();

		StringTokenizer st = new StringTokenizer(idFacturas, ",");
		while (st.hasMoreElements()) {
			query = new StringBuffer();
			query.append("UPDATE ");
			query.append("INFRA.FACTURAS SET ");
			query.append("	FECHA_PAGO = '");
			query.append(new java.sql.Date(fechaPago.getTime()));
			query.append("' WHERE ID_FACTURA = ");
			query.append(st.nextElement());
			log.debug("query=" + query);
			try {
				getJdbcTemplate().update(query.toString());
			} catch (Exception e) {
				log.fatal("ERROR al salvar la fecha de pago de las Facturas. " + e);
				return new Mensaje(1, "Ocurrió un error al intentar guardar la fecha de las facturas seleccionadas, intentelo más tarde.");
			}
		}
		return new Mensaje(0,
				"La fecha ha sido asignada satisfactoriamente a las facturas seleccionadas.");
	}
}