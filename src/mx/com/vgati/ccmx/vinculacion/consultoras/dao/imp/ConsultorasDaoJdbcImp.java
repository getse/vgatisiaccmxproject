/*
 * ConsultorasDaoJdbcImp.java        01/04/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import mx.com.vgati.ccmx.vinculacion.consultoras.dao.ConsultorasDao;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Pagos;
import mx.com.vgati.ccmx.vinculacion.dto.Roles;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Mensaje;
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
public class ConsultorasDaoJdbcImp extends VinculacionBaseJdbcDao implements
		ConsultorasDao {

	@Override
	public Consultoras getConsultora(int id) throws JdbcDaoException {
		log.debug("getConsultora()");

		Consultoras result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_CONSULTORA,");
		query.append("ID_USUARIO, ");
		query.append("ID_USUARIO_PADRE, ");
		query.append("ID_CONSULTORA_PADRE, ");
		query.append("EMPRESA, ");
		query.append("NOMBRE_CONTACTO, ");
		query.append("APP_PATERNO_CONTACTO, ");
		query.append("APP_MATERNO_CONTACTO, ");
		query.append("CORREO_ELECTRONICO_CONTACTO ");
		query.append("FROM INFRA.CONSULTORAS ");
		query.append("WHERE ID_USUARIO = ? ");
		log.debug("query=" + query);
		log.debug(id);

		Object[] o = { id };
		result = (Consultoras) getJdbcTemplate().queryForObject(
				query.toString(), o, new ConsultoraRowMapper());

		log.debug("result=" + result);
		return result;
	}
	@Override
	public Mensaje saveRolConsultora(Consultoras consultoras)
			throws DaoException {
		log.debug("saveRolConsultora()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.REL_ROLES ( ");
		query.append("CVE_ROL, ");
		query.append("CVE_USUARIO) ");
		query.append("VALUES( '");
		query.append(Roles.Consultor.name());
		query.append("', '");
		query.append(consultoras.getCorreoElectronico());
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
	public class ConsultoraRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			Consultoras consultoras = new Consultoras();
			consultoras.setIdUsuario(rs.getInt("ID_USUARIO"));
			consultoras.setIdUsuarioPadre(rs.getInt("ID_USUARIO_PADRE"));
			consultoras.setIdConsultoraPadre(rs.getInt("ID_CONSULTORA_PADRE"));
			consultoras.setIdConsultora(rs.getInt("ID_CONSULTORA"));
			consultoras.setEmpresa(rs.getString("EMPRESA"));
			consultoras.setNombreContacto(rs.getString("NOMBRE_CONTACTO"));
			consultoras.setAppPaternoContacto(rs
					.getString("APP_PATERNO_CONTACTO"));
			consultoras.setAppMaternoContacto(rs
					.getString("APP_MATERNO_CONTACTO"));
			consultoras.setCorreoElectronico(rs
					.getString("CORREO_ELECTRONICO_CONTACTO"));

			return consultoras;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Consultoras> getConsultorasAdmin(int idPadre) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_CONSULTORA");
		query.append(",ID_USUARIO");
		query.append(",ID_USUARIO_PADRE");
		query.append(",ID_CONSULTORA_PADRE");
		query.append(",EMPRESA,NOMBRE_CONTACTO");
		query.append(",APP_PATERNO_CONTACTO");
		query.append(",APP_MATERNO_CONTACTO");
		query.append(",CORREO_ELECTRONICO_CONTACTO");
		query.append(" FROM INFRA.CONSULTORAS ");
		query.append("WHERE ID_CONSULTORA_PADRE="+ idPadre);		
		log.debug("getConsultoras query* "+query);
		return getJdbcTemplate().query(query.toString(), new ConsultorasRowMapper());
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
			Consultoras consultoras = new Consultoras();
			consultoras.setIdUsuario(rs.getInt("ID_USUARIO"));
			consultoras.setIdUsuarioPadre(rs.getInt("ID_USUARIO_PADRE"));
			consultoras.setIdConsultora(rs.getInt("ID_CONSULTORA"));
			consultoras.setIdConsultoraPadre(rs.getInt("ID_CONSULTORA_PADRE"));
			consultoras.setEmpresa(rs.getString("EMPRESA"));
			consultoras.setNombreContacto(rs.getString("NOMBRE_CONTACTO"));
			consultoras.setAppPaternoContacto(rs
					.getString("APP_PATERNO_CONTACTO"));
			consultoras.setAppMaternoContacto(rs
					.getString("APP_MATERNO_CONTACTO"));
			consultoras.setCorreoElectronico(rs
					.getString("CORREO_ELECTRONICO_CONTACTO"));
			return consultoras;
		}
				
	}
	@Override
	public Mensaje saveConsultor(Consultoras consultor) throws DaoException {
		log.debug("saveTractora()");

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ");
		query.append("INFRA.CONSULTORAS ( ");
		query.append("ID_USUARIO, ");
		query.append("ID_USUARIO_PADRE, ");
		query.append("ID_CONSULTORA_PADRE, ");
		query.append("EMPRESA, ");
		query.append("NOMBRE_CONTACTO, ");
		query.append("APP_PATERNO_CONTACTO, ");
		query.append("APP_MATERNO_CONTACTO, ");
		query.append("CORREO_ELECTRONICO_CONTACTO) ");
		query.append("VALUES( ");
		query.append(consultor.getIdUsuario());
		query.append(", ");
		query.append(consultor.getIdUsuarioPadre());
		query.append(", ");
		query.append(consultor.getIdConsultoraPadre());
		query.append(", '");
		query.append(consultor.getEmpresa());
		query.append("', '");
		query.append(consultor.getNombreContacto());
		query.append("', '");
		query.append(consultor.getAppPaternoContacto());
		query.append("', '");
		query.append(consultor.getAppMaternoContacto());
		query.append("', '");
		query.append(consultor.getCorreoElectronico());
		query.append("' )");
		log.debug("query=" + query);

		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(
					0,
					"El usuario consultor se dio de alta satisfactoriamente. En breve recibirá un correo electrónico con la información requerida y la liga para acceder al sistema.");
		} catch (Exception e) {
			log.fatal("ERROR al insertar la Consultora, " + e);
			return new Mensaje(1, "No es posible dar de alta la Consultora.");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PyMEs> getPymesAdmin(int idUsuarioAdmin) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT PY.ID_USUARIO");
		query.append(",PY.CORREO_ELECTRONICO");
		query.append(",PY.NOMBRE_COMERCIAL");
		query.append(" FROM INFRA.PYMES AS PY JOIN INFRA.REL_CONSULTORAS_PYME as REL ");
		query.append(" ON PY.ID_USUARIO=REL.ID_USUARIO_PYME ");
		query.append(" WHERE PY.ID_USUARIO ");
		query.append("NOT IN (SELECT ID_USUARIO_PYME FROM INFRA.REL_CONSULTORAS_PYME");
		query.append(" WHERE ID_USURIO_CONSULTOR !="+idUsuarioAdmin);		
		query.append(") ORDER BY NOMBRE_COMERCIAL ASC;");		
		log.debug("getPymesAdmin query ="+query);
		return getJdbcTemplate().query(query.toString(), new getPymesRowMapper());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PyMEs> getPymes(int idConsultora) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT PY.ID_USUARIO");
		query.append(",PY.CORREO_ELECTRONICO");
		query.append(",PY.NOMBRE_COMERCIAL");
		query.append(" FROM INFRA.PYMES AS PY"); 
		query.append(" JOIN INFRA.REL_CONSULTORAS_PYME AS RP  ");
		query.append(" ON PY.ID_USUARIO=RP.ID_USUARIO_PYME ");
		query.append(" JOIN INFRA.CONSULTORAS AS C ON RP.ID_USURIO_CONSULTOR=C.ID_USUARIO ");
		query.append(" WHERE C.ID_CONSULTORA_PADRE = "+idConsultora+";");	
		log.debug("getPymes "+ query);
		return getJdbcTemplate().query(query.toString(), new getPymesRowMapper());
	}
	@SuppressWarnings("rawtypes")
	public class getPymesRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			PymesResultExtractor pymes = new PymesResultExtractor();
			return pymes.extractData(arg0);
		}
		
	}
	@SuppressWarnings("rawtypes")
	public class PymesResultExtractor implements ResultSetExtractor{

		@Override
		public Object extractData(ResultSet arg0) throws SQLException,
				DataAccessException {
			PyMEs pyMEs= new PyMEs();
			pyMEs.setIdUsuario(arg0.getInt("ID_USUARIO"));
			pyMEs.setCorreoElectronico(arg0.getString("CORREO_ELECTRONICO"));
			pyMEs.setNombreComercial(arg0.getString("NOMBRE_COMERCIAL"));
			return pyMEs;
		}
	}

	@Override
	public Mensaje saveRelPymesConsultora(int uPymes,int uConsultor) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO INFRA.REL_CONSULTORAS_PYME(ID_USUARIO_PYME,ID_USURIO_CONSULTOR)");
		query.append("VALUES(");
		query.append(uPymes);
		query.append(",");
		query.append(uConsultor);
		query.append(");");
		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"La asignado la PYME exitosamente.");
		} catch (Exception e) {
			log.fatal("ERROR al salvar el contacto, " + e);
			return new Mensaje(1,
					"No es posible asignar la PYME, intentelo más tarde.");
		}
	}
	@Override
	public Mensaje saveCedula(int idPyme,String cedula) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE INFRA.PYMES SET CEDULA='");
		query.append(cedula);
		query.append("' WHERE ID_USUARIO=");		
		query.append(idPyme);
		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,
					"La cedula "+cedula+" fue asignada a ");
		} catch (Exception e) {
			log.fatal("ERROR al salvar el contacto, " + e);
			return new Mensaje(1,
					"Intentelo más tarde. No es posible asignar la cedula "+cedula+", a ");
		}
	}
	@Override
	public String saveFacturaAnticipo(String numeroFactura,String idServicios) throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO INFRA.PAGOS(ID_SERVICO_CONSULTORIA,TIPO,NUMERO)");
		query.append("VALUES(");
		query.append(idServicios);
		query.append(",'Anticipo','");
		query.append(numeroFactura);
		query.append("');");
		log.debug("Save anticipo query"+query);
		try {
			getJdbcTemplate().update(query.toString());
			return "Se asigno factura Anticipo "+numeroFactura+" a la PYME ";
		} catch (Exception e) {
			log.fatal("ERROR al salvar el contacto, " + e);
			return null;
		}
	}
	@Override
	public String getPymeByServicio(int idServicio) throws DaoException {
		StringBuffer query= new StringBuffer();
		query.append("SELECT PY.NOMBRE_COMERCIAL  ");
		query.append("FROM INFRA.PYMES AS PY");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA AS SC ");
		query.append("ON SC.ID_USUARIO =PY.ID_USUARIO ");
		query.append("WHERE SC.ID_CONSULTORIA ="+idServicio);
		String result = (String) getJdbcTemplate().queryForObject(
				query.toString(), null, String.class);
		log.debug("Resulto "+result);
		return result;
	}
	
	@Override
	public String saveFacturaAbono1(String numeroFactura,String idServicios) throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO INFRA.PAGOS(ID_SERVICO_CONSULTORIA,TIPO,NUMERO)");
		query.append("VALUES(");
		query.append(idServicios);
		query.append(",'Abono1','");
		query.append(numeroFactura);
		query.append("');");
		try {
			getJdbcTemplate().update(query.toString());
			return "Se asigno factura Abono1 "+numeroFactura+" a la PYME ";
		} catch (Exception e) {
			log.fatal("ERROR al salvar el contacto, " + e);
			return null;
		}
	}
	@Override
	public String saveFacturaAbono2(String numeroFactura,String idServicios) throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO INFRA.PAGOS(ID_SERVICO_CONSULTORIA,TIPO,NUMERO)");
		query.append("VALUES(");
		query.append(idServicios);
		query.append(",'Abono2','");
		query.append(numeroFactura);
		query.append("');");
		try {
			getJdbcTemplate().update(query.toString());
			return "Se asigno abono2 "+numeroFactura + " a la PYME ";
		} catch (Exception e) {
			log.fatal("ERROR al salvar el contacto, " + e);
			return null;
		}
	}
	@Override
	public String saveFacturaFiniquito(String numeroFactura,String idServicios) throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO INFRA.PAGOS(ID_SERVICO_CONSULTORIA,TIPO,NUMERO)");
		query.append("VALUES(");
		query.append(idServicios);
		query.append(",'Finiquito','");
		query.append(numeroFactura);
		query.append("');");
		try {
			getJdbcTemplate().update(query.toString());
			return "Se asigno factura finiquito "+numeroFactura+" a la PYME ";
		} catch (Exception e) {
			log.fatal("ERROR al salvar el contacto, " + e);
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Pagos> getPagos(int idConsultora,int filtro) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT SC.ID_CONSULTORIA");
		query.append(" ,PY.NOMBRE_COMERCIAL  AS NOMBRE ");
		query.append(" ,C.NOMBRE_CONTACTO ");
		query.append(" ,SC.ESTATUS ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Anticipo%') AS ANTICIPO ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Abono1%') AS ABONO1 ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Abono2%') AS ABONO2 ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Finiquito%') AS FINIQUITO ");
		query.append(" FROM INFRA.CONSULTORAS as C ");
		query.append(" JOIN INFRA.REL_CONSULTORIAS_CONSULTORA as RC ON C.ID_CONSULTORA=RC.ID_CONSULTORA ");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON RC.ID_CONSULTORIA=SC.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON SC.ID_USUARIO=PY.ID_USUARIO ");
		query.append(" WHERE "+ idConsultora +"=ID_CONSULTORA_PADRE ");
		if(filtro==1){
			query.append(" AND SC.ESTATUS='DIAGNOSTICO' OR SC.ESTATUS='PLAN' OR SC.ESTATUS='IMPEMENTACION' OR SC.ESTATUS='EVALUACION'");
		}else if (filtro==2) {
			query.append(" AND SC.ESTATUS='CONCLUIDA' ");		
		}
		query.append(" ORDER BY PY.NOMBRE_COMERCIAL;");
		log.debug("getPagos() \nQuery "+query);
		return getJdbcTemplate().query(query.toString(),new getPagosRowMapper());
	}
	@SuppressWarnings("rawtypes")
	public class getPagosRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			PymesPagosExtractor pymes = new PymesPagosExtractor();
			return pymes.extractData(arg0);
		}
		
	}
	@SuppressWarnings("rawtypes")
	public class PymesPagosExtractor implements ResultSetExtractor{

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Pagos pagos=new Pagos();
			pagos.setIdServicios(rs.getInt("ID_CONSULTORIA"));
			pagos.setNombreComercial(rs.getString("NOMBRE"));
			pagos.setNombreContacto(rs.getString("NOMBRE_CONTACTO"));
			pagos.setEstatus(rs.getString("ESTATUS"));
			pagos.setAnticipo(rs.getString("ANTICIPO"));
			pagos.setAbono1(rs.getString("ABONO1"));
			pagos.setAbono2(rs.getString("ABONO2"));
			pagos.setFiniquito(rs.getString("FINIQUITO"));		
			return pagos;
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagos getPagos(int idServicio) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT SC.ID_CONSULTORIA");
		query.append(" ,PY.NOMBRE_COMERCIAL  AS NOMBRE ");
		query.append(" ,C.NOMBRE_CONTACTO ");
		query.append(" ,SC.ESTATUS ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Anticipo%') AS ANTICIPO ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Abono1%') AS ABONO1 ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Abono2%') AS ABONO2 ");
		query.append(" ,(SELECT P.NUMERO FROM INFRA.PAGOS AS P  WHERE P.ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND P.TIPO LIKE'%Finiquito%') AS FINIQUITO ");
		query.append(" FROM INFRA.CONSULTORAS as C ");
		query.append(" JOIN INFRA.REL_CONSULTORIAS_CONSULTORA as RC ON C.ID_CONSULTORA=RC.ID_CONSULTORA ");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON RC.ID_CONSULTORIA=SC.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON SC.ID_USUARIO=PY.ID_USUARIO ");
		query.append(" WHERE "+ idServicio +"=SC.ID_CONSULTORIA ");
		log.debug("Obteniendo Servicio con id"+idServicio+" query = "+query);
		List<Pagos> p =getJdbcTemplate().query(query.toString(),new getPagosRowMapper());
		if(p!=null && !p.isEmpty()){
			return p.get(0);
		} else {
			return null;
		}
		 
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<PyMEs> getBusquedaPyMEs(String busqueda, String estado,
			String cveScian, String nombreComercial,int idConsultora) throws DaoException {
		log.debug("getBusquedaPyMEs()");
		List<String> l = null;
		if(busqueda!=null && !	busqueda.trim().equals("")){
			String cadenaBusqueda = busqueda.toUpperCase().trim().replace('Á', 'A')
					.replace('É', 'E').replace('Í', 'I').replace('Ó', 'O')
					.replace('Ú', 'U').replace('Ü', 'U');
			StringTokenizer st = new StringTokenizer(cadenaBusqueda, " ");
			l = new ArrayList<String>();
			while (st.hasMoreElements()) {
				l.add((String) st.nextElement());
			}
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
		query.append(", (SELECT CASE COUNT(*) WHEN 0 THEN 'false' ELSE 'true' END ");
		query.append(" FROM INFRA.REL_CONSULTORAS_PYME AS REL ");
		query.append(" JOIN INFRA.PYMES AS PY on PY.ID_USUARIO=REL.ID_USUARIO_PYME ");
		query.append(" JOIN INFRA.CONSULTORAS AS CONS ON CONS.ID_USUARIO =  REL.ID_USURIO_CONSULTOR ");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA  AS  SVC ON SVC.ID_USUARIO=PY.ID_USUARIO");
		query.append(" WHERE ID_USURIO_CONSULTOR!=10 ");
		query.append(" AND PY.ID_USUARIO=P.ID_USUARIO");
		query.append(" AND SVC.ESTATUS LIKE'%DIAGNOSTICO%') as CEDULA_MODIFIC");				
		query.append(" FROM INFRA.PYMES P");
		query.append(", INFRA.CONTACTOS C");
		query.append(", INFRA.PRODUCTOS PP");
		query.append(", INFRA.REL_DOMICILIOS_USUARIO RDU");
		query.append(", INFRA.REL_CONSULTORAS_PYME as REL  ");
		query.append(", INFRA.CONSULTORAS as CO");
		query.append(", INFRA.DOMICILIOS D ");
		query.append("WHERE P.ID_USUARIO = C.ID_USUARIO ");
		query.append("AND P.ID_USUARIO = PP.ID_USUARIO(+) ");
		query.append("AND  P.ID_USUARIO = RDU.ID_USUARIO(+) ");
		query.append("AND RDU.ID_DOMICILIO = D.ID_DOMICILIO(+) ");
		query.append("AND P.ID_USUARIO = REL.ID_USUARIO_PYME ");
		query.append("AND ID_USURIO_CONSULTOR=CO.ID_USUARIO ");
		if(idConsultora>0){
			query.append("AND  CO.ID_USUARIO= "+idConsultora);
		}		
		query.append("AND C.B_PRINCIPAL = true ");
		if (busqueda!=null && !busqueda.isEmpty())
			query.append(" AND ( ( ");
		if(l!=null){
			for (String valor : l) {
				query.append(" UPPER(PP.PRODUCTO) LIKE '%".concat(Null.free(valor))
						.concat("%' "));
				if (l.indexOf(valor) != l.size() - 1)
					query.append(" OR ");
			}
		}
		if (busqueda!=null && !busqueda.isEmpty())
			query.append(" ) ");
		if ((nombreComercial!=null && estado!=null && cveScian!=null)&&( !nombreComercial.isEmpty() || !estado.isEmpty()
				|| !cveScian.isEmpty()))
			query.append(" OR ( ");

		
		if (nombreComercial!=null && !nombreComercial.isEmpty())
			query.append(" UPPER(P.NOMBRE_COMERCIAL) LIKE '%".concat(
					nombreComercial.toUpperCase()).concat("%' "));
		if (estado!=null && !estado.isEmpty()) {
			if (nombreComercial!=null && !nombreComercial.isEmpty())
				query.append(" OR ");
			query.append(" D.ESTADO LIKE '%".concat(estado).concat("%' "));
		}
		
		if (cveScian !=null && !cveScian.isEmpty()) {
			if (!nombreComercial.isEmpty())
				query.append(" OR ");
			else if (!estado.isEmpty())
				query.append(" OR ");
			query.append(" P.CVE_SCIAN LIKE '"
					.concat(cveScian.length() > 3 ? cveScian.substring(0, 3)
							: cveScian).concat("%' "));
		}
		if ((nombreComercial!=null && estado!=null )&&( !nombreComercial.isEmpty() || !estado.isEmpty()
				|| !cveScian.isEmpty()))
			query.append(" ) ");
		if (busqueda !=null && !busqueda.isEmpty())
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
			pymes.setCedulaModificable(rs.getBoolean("CEDULA_MODIFIC"));
			return pymes;

		}
	}
}