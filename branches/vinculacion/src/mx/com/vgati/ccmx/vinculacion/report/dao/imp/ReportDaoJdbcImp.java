package mx.com.vgati.ccmx.vinculacion.report.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;


import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.report.dao.ReportDao;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXFinanzas;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXParticipantes;
import mx.com.vgati.ccmx.vinculacion.report.dto.Filtros;
import mx.com.vgati.ccmx.vinculacion.report.dto.FiltrosGenerales;
import mx.com.vgati.ccmx.vinculacion.report.dto.IndicadoresPymes;
import mx.com.vgati.ccmx.vinculacion.report.dto.PYMESReporte;
import mx.com.vgati.ccmx.vinculacion.report.dto.TotalEmpresas;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;

public class ReportDaoJdbcImp extends VinculacionBaseJdbcDao implements ReportDao{

	@Override
	public List<CCMXParticipantes> getDatos() throws DaoException{
		CCMXParticipantes a1 = new CCMXParticipantes();
		CCMXParticipantes a2 = new CCMXParticipantes();
        a1.setAnoAtencion("2089");
        a2.setEstatus("Equivocado");
        // Store people in our dataSource list (normally would come from database).
        ArrayList<CCMXParticipantes> myList = new ArrayList<CCMXParticipantes>();
        myList.add(a1);
        myList.add(a2);
        myList.add(a2);
        myList.add(a2);
        myList.add(a2);
		
		return myList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Consultoras> getConsultoras() throws JdbcDaoException {
		log.debug("getConsultoras()");

		List<Consultoras> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_USUARIO, ");
		query.append("ID_USUARIO_PADRE, ");
		query.append("ID_CONSULTORA, ");
		query.append("EMPRESA, ");
		query.append("NOMBRE_CONTACTO, ");
		query.append("APP_PATERNO_CONTACTO, ");
		query.append("APP_MATERNO_CONTACTO, ");
		query.append("CORREO_ELECTRONICO_CONTACTO ");
		query.append("FROM INFRA.CONSULTORAS WHERE ID_CONSULTORA_PADRE=0;");
		log.debug("query=" + query);
		result = getJdbcTemplate().query(query.toString(), new ConsultoraRowMapper());

		log.debug("result=" + result);
		return result;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Consultoras> getConsultores(int idConsultoraPadre) throws DaoException {
		log.debug("getConsultores()");

		List<Consultoras> result = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_USUARIO, ");
		query.append("ID_USUARIO_PADRE, ");
		query.append("ID_CONSULTORA, ");
		query.append("EMPRESA, ");
		query.append("NOMBRE_CONTACTO, ");
		query.append("APP_PATERNO_CONTACTO, ");
		query.append("APP_MATERNO_CONTACTO, ");
		query.append("CORREO_ELECTRONICO_CONTACTO ");
		query.append("FROM INFRA.CONSULTORAS ");
		if(idConsultoraPadre>0){
			query.append(" WHERE ID_CONSULTORA_PADRE=");
			query.append(idConsultoraPadre);
		}
		else{
			query.append(" WHERE ID_CONSULTORA_PADRE>0");
		}
		query.append(";");
		log.debug("query=" + query);
		result = getJdbcTemplate().query(query.toString(), new ConsultoraRowMapper());

		log.debug("result=" + result);
		return result;
	}

	
	@SuppressWarnings("rawtypes")
	public class ConsultoraRowMapper implements RowMapper {
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
	public List<ServiciosConsultoria> getServiciosConsultoria() throws DaoException {
		log.debug("ServiciosConsultoria()");

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_CONSULTORIA ");
		query.append(",ID_USUARIO");
		query.append(",B_CONSULTORIA_20 ");
		query.append(",B_CONSULTORIA_40 ");
		query.append(",B_CONSULTORIA_60 ");
		query.append(",B_CONSULTORIA_80");
		query.append(",MENSAJE");
		query.append("FROM  INFRA.SERVICIOS_CONSULTORIA");
		log.debug("query=" + query);

		List<ServiciosConsultoria> trac = getJdbcTemplate().query(query.toString(),
				new TractorasRowMapper());
		return trac;

	}

	@SuppressWarnings("rawtypes")
	public class ServiciosConsultoriaMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			ServiciosConsultoriaResultSetExtractor extractor = new ServiciosConsultoriaResultSetExtractor();
			return extractor.extractData(rs);
		}

	}
	@SuppressWarnings("rawtypes")
	public class ServiciosConsultoriaResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			ServiciosConsultoria serviciosCons = new ServiciosConsultoria();
			serviciosCons.setIdConsultoria(rs.getInt("ID_CONSULTORIA"));
			serviciosCons.setIdUsuario(rs.getInt("ID_USUARIO"));
			serviciosCons.setbConsultoriaVeinte(rs.getBoolean("B_CONSULTORIA_20"));
			serviciosCons.setbConsultoriaCuarenta(rs.getBoolean("B_CONSULTORIA_40"));
			serviciosCons.setbConsultoriaSesenta(rs.getBoolean("B_CONSULTORIA_60"));
			serviciosCons.setbConsultoriaOchenta(rs.getBoolean("B_CONSULTORIA_80"));
			serviciosCons.setMensaje(rs.getString(""));		
			return serviciosCons;
		}
				
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tractoras> getTractoras() throws DaoException {
		log.debug("getTractoras()");

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
		query.append("WHERE ID_TRACTORA_PADRE=0 ");
		query.append("ORDER BY ID_USUARIO DESC ");
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
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CCMXParticipantes> getCCMXServicios(Filtros filtros) throws DaoException{
		log.debug("getCCMXServicios");
		StringBuffer query = new StringBuffer();
		query.append("");
		query.append("SELECT (rownum) AS NUMERO");
		query.append(",PY.NOMBRE_COMERCIAL ");
		query.append(", CASE WHEN PY.B_PRIMER_NIVEL THEN 'Sector de servicios'");
		query.append("  WHEN PY. B_SEGUNDO_NIVEL  THEN 'Sector comercial'");
		query.append("  WHEN PY.B_TERCER_NIVEL   THEN 'Sector manufacturero'");
		query.append("  ELSE 'No definido'  END CASE as GIRO ");
		query.append(", SC.ESTATUS");
		query.append(", SC.FECHA_INICIO");
		query.append(", SC.FECHA_TERMINO");
		query.append(", YEAR(SC.FECHA_INICIO) as ANO_ATENCION");
		query.append(", (SELECT COUNT(*) ");
		query.append(" FROM INFRA.SERVICIOS_DIPLOMADO AS SVD ");
		query.append(" JOIN INFRA.ASISTENTES AS ASI ON ASI.ID_DIPLOMADO=SVD.ID_DIPLOMADO ");
		query.append(" JOIN INFRA.DIPLOMADOS AS DIP ON DIP.ID_DIPLOMADO=SVD.ID_DIPLOMADO ");
		query.append(" WHERE SVD.ID_USUARIO=PY.ID_USUARIO AND DIP.TEMA LIKE ('%Cultura Organizacional%')");
		query.append(" ) AS B_DIPLOMADO_CCMX_UNO ");		
		query.append(", (SELECT COUNT(*) ");
		query.append(" FROM INFRA.SERVICIOS_DIPLOMADO AS SVD ");
		query.append(" JOIN INFRA.ASISTENTES AS ASI ON ASI.ID_DIPLOMADO=SVD.ID_DIPLOMADO ");
		query.append(" JOIN INFRA.DIPLOMADOS AS DIP ON DIP.ID_DIPLOMADO=SVD.ID_DIPLOMADO ");
		query.append(" WHERE SVD.ID_USUARIO=PY.ID_USUARIO AND DIP.TEMA LIKE ('%Estrategia Comercial, Imágen y Cadena de Distribución%')");
		query.append("  ) AS B_DIPLOMADO_CCMX_DOS");
		query.append(", (SELECT COUNT(*) ");
		query.append(" FROM INFRA.SERVICIOS_DIPLOMADO AS SVD ");
		query.append(" JOIN INFRA.ASISTENTES AS ASI ON ASI.ID_DIPLOMADO=SVD.ID_DIPLOMADO ");
		query.append(" JOIN INFRA.DIPLOMADOS AS DIP ON DIP.ID_DIPLOMADO=SVD.ID_DIPLOMADO ");
		query.append(" WHERE SVD.ID_USUARIO=PY.ID_USUARIO AND DIP.TEMA LIKE ('%Estrategia, Planeación e Innovación%')");
		query.append("  ) AS B_DIPLOMADO_CCMX_CUATRO");
		query.append(", (SELECT COUNT(*) ");
		query.append(" FROM INFRA.SERVICIOS_DIPLOMADO AS SVD ");
		query.append(" JOIN INFRA.ASISTENTES AS ASI ON ASI.ID_DIPLOMADO=SVD.ID_DIPLOMADO ");
		query.append(" JOIN INFRA.DIPLOMADOS AS DIP ON DIP.ID_DIPLOMADO=SVD.ID_DIPLOMADO ");
		query.append(" WHERE SVD.ID_USUARIO=PY.ID_USUARIO AND DIP.TEMA LIKE ('%Manufactura Esbelta%')");
		query.append("  ) AS B_DIPLOMADO_CCMX_TRES");
		query.append(", null as SESION_INFORMATIVA");
		query.append(",(SC.ADMINISTRACION_ANTES + SC.MERCADEO_ANTES + SC.FINANZAS_ANTES +");
		query.append(" SC.PROCESOS_ANTES + SC.RECURSOS_HUMANOS_ANTES)*1.0/5  AS RADAR_PROMEDIO_ANT");
		query.append(",(SC.ADMINISTRACION_DESPUES + SC.MERCADEO_DESPUES + SC.FINANZAS_DESPUES + ");
		query.append(" SC.PROCESOS_DESPUES + SC.RECURSOS_HUMANOS_DESPUES)*1.0/5  AS RADAR_PROMEDIO_DES ");
		query.append("	FROM  ");
		query.append(" INFRA.CONSULTORAS as C JOIN INFRA.REL_CONSULTORIAS_CONSULTORA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_CONSULTORIA=CI.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO=SC.ID_USUARIO ");
		query.append(" JOIN INFRA.REL_PYMES_TRACTORAS RTR on PY.ID_USUARIO = RTR.ID_USUARIO_PYME "); 
		query.append(" JOIN INFRA.TRACTORAS as TR on RTR.ID_USUARIO_TRACTORA=TR.ID_USUARIO ");
		if(filtros.getFiltro4()>0 || filtros.getFiltro5()>0){
			query.append(" JOIN INFRA.PAGOS AS P ON SC.ID_CONSULTORIA=P.ID_SERVICO_CONSULTORIA WHERE (");
			if(filtros.getFiltro4()>0){
				query.append("P.ID_PAGO="+filtros.getFiltro4());
			}
			if(filtros.getFiltro5()>0){
				if(filtros.getFiltro4()>0){query.append(" or ");}
				query.append(" P.ID_PAGO="+filtros.getFiltro5());
			}
			query.append(") ");
			if(filtros.getId()>0 || filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
				query.append(" and ");
			}	
		}else if(filtros.getId()>0 || filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
			query.append(" WHERE ");
		}
		if(filtros.getId()>0){
			query.append(" ("+filtros.getId()+" IN (TR.ID_USUARIO");
			query.append(",TR.ID_TRACTORA_PADRE,C.ID_USUARIO) ");
			query.append(" or " + " C.ID_CONSULTORA IN (SELECT ID_CONSULTORA_PADRE");
			query.append(" FROM INFRA.CONSULTORAS WHERE ID_USUARIO = " + filtros.getId() + ")) ");
			if(filtros.getFiltro1()>0 || filtros.getFiltro2()>0){
				query.append(" and ");
			}
		}
		if(filtros.getFiltro1() > 0){
			query.append(" TR.ID_USUARIO ="+filtros.getFiltro1()+" ");
			if(filtros.getFiltro2() >0){
				query.append(" and ");
			}
		}			
		if(filtros.getFiltro2() > 0){
			query.append(" C.ID_CONSULTORA_PADRE IN (SELECT ID_CONSULTORA FROM CONSULTORAS ");
			query.append(" WHERE ID_USUARIO=" + filtros.getFiltro2() + ")");
		}
		
		log.debug(query);
		return getJdbcTemplate().query(query.toString(),
				new CCMXPartReportRowMapper());
	}
	@SuppressWarnings("unchecked")
	@Override
	public int getCCMXServiciosTotal(Filtros filtros) throws DaoException{
		log.debug("getCCMXServicios");
		StringBuffer query = new StringBuffer();
		query.append("");
		query.append("SELECT count(*) as TOTAL ");
		query.append("	FROM  ");
		query.append(" INFRA.CONSULTORAS as C JOIN INFRA.REL_CONSULTORIAS_CONSULTORA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_CONSULTORIA=CI.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO=SC.ID_USUARIO ");
		query.append(" JOIN INFRA.REL_PYMES_TRACTORAS RTR on PY.ID_USUARIO = RTR.ID_USUARIO_PYME "); 
		query.append(" JOIN INFRA.TRACTORAS as TR on RTR.ID_USUARIO_TRACTORA=TR.ID_USUARIO ");
		if(filtros.getFiltro4()>0 || filtros.getFiltro5()>0){
			query.append(" JOIN INFRA.PAGOS AS P ON SC.ID_CONSULTORIA=P.ID_SERVICO_CONSULTORIA WHERE (");
			if(filtros.getFiltro4()>0){
				query.append("P.ID_PAGO="+filtros.getFiltro4());
			}
			if(filtros.getFiltro5()>0){
				if(filtros.getFiltro4()>0){query.append(" or ");}
				query.append(" P.ID_PAGO="+filtros.getFiltro5());
			}
			query.append(") ");
			if(filtros.getId()>0 || filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
				query.append(" and ");
			}	
		}else if(filtros.getId()>0 || filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
			query.append(" WHERE ");
		}
		if(filtros.getId()>0){
			query.append(" ("+filtros.getId()+" IN (TR.ID_USUARIO");
			query.append(",TR.ID_TRACTORA_PADRE,C.ID_USUARIO) ");
			query.append(" or " + " C.ID_CONSULTORA IN (SELECT ID_CONSULTORA_PADRE");
			query.append(" FROM INFRA.CONSULTORAS WHERE ID_USUARIO = " + filtros.getId() + ")) ");
			if(filtros.getFiltro1()>0 || filtros.getFiltro2()>0){
				query.append(" and ");
			}	
		}
		if(filtros.getFiltro1() > 0){
			query.append(" TR.ID_USUARIO ="+filtros.getFiltro1()+" ");
			if(filtros.getFiltro2() >0){
				query.append(" and ");
			}
		}			
		if(filtros.getFiltro2() > 0){
			query.append(" C.ID_CONSULTORA_PADRE IN (SELECT ID_CONSULTORA FROM CONSULTORAS ");
			query.append(" WHERE ID_USUARIO="+filtros.getFiltro2()+")");
		}
		log.debug(query);
		log.debug(query);
		List<Integer> x = getJdbcTemplate().query(query.toString(),
				new getInt());
		if(x.isEmpty()){
			return 0;
		}else {
			return  x.get(0);
		}
	}
	@SuppressWarnings("rawtypes")
	public class CCMXPartReportRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			CCMXPartReportResultSetExtractor extractor = new CCMXPartReportResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class CCMXPartReportResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			CCMXParticipantes ccmxParticipantes = new CCMXParticipantes();
			ccmxParticipantes.setNo(rs.getInt("NUMERO"));
			ccmxParticipantes.setPyme(rs.getString("NOMBRE_COMERCIAL"));
			ccmxParticipantes.setGiro(rs.getString("GIRO"));
			ccmxParticipantes.setEstatus(rs.getString("ESTATUS"));
			ccmxParticipantes.setFechaInicio(rs.getString("FECHA_INICIO"));
			ccmxParticipantes.setFechaTermino(rs.getString("FECHA_INICIO"));
			ccmxParticipantes.setAnoAtencion(rs.getString("ANO_ATENCION"));
			ccmxParticipantes.setParticipantesDiplomadoCultOrg(rs.getInt("B_DIPLOMADO_CCMX_UNO"));
			ccmxParticipantes.setParticipantesDiplomadoEstrCom(rs.getInt("B_DIPLOMADO_CCMX_DOS"));
			ccmxParticipantes.setParticipantesDiplomadoReduCos(rs.getInt("B_DIPLOMADO_CCMX_TRES"));
			ccmxParticipantes.setParticipantesDiplomadoPlanIno(rs.getInt("B_DIPLOMADO_CCMX_CUATRO"));
			ccmxParticipantes.setSesionInformativa(rs.getString("SESION_INFORMATIVA"));
			ccmxParticipantes.setRadarPromAnt(rs.getString("RADAR_PROMEDIO_ANT"));
			ccmxParticipantes.setRadarPromDes(rs.getString("RADAR_PROMEDIO_DES"));
			return ccmxParticipantes;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public int getParticipantes(Filtros filtros,int indice) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT count(*) as TOTAL  ");
		query.append("FROM  ");
		query.append(" INFRA.CONSULTORAS as C JOIN INFRA.REL_CONSULTORIAS_CONSULTORA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_CONSULTORIA=CI.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO=SC.ID_USUARIO ");
		query.append(" JOIN INFRA.REL_PYMES_TRACTORAS RTR on PY.ID_USUARIO = RTR.ID_USUARIO_PYME "); 
		query.append(" JOIN INFRA.TRACTORAS as TR on RTR.ID_USUARIO_TRACTORA=TR.ID_USUARIO ");
		if(filtros.getFiltro4()>0 || filtros.getFiltro5()>0){
			query.append(" JOIN INFRA.PAGOS AS P ON SC.ID_CONSULTORIA=P.ID_SERVICO_CONSULTORIA ");
		}
		query.append(" JOIN INFRA.ASISTENTES AS ASI ON ASI.ID_DIPLOMADO=SVD.ID_DIPLOMADO ");
		query.append(" JOIN INFRA.SERVICIOS_DIPLOMADO AS SVD ON SVD.ID_USUARIO=PY.ID_USUARIO ");
		query.append(" JOIN INFRA.DIPLOMADOS AS DIP ON DIP.ID_DIPLOMADO=SVD.ID_DIPLOMADO ");
		switch (indice) {
		case 1:
				query.append(" WHERE DIP.TEMA LIKE ('%Cultura Organizacional%')");
			break;
		case 3:
			query.append(" WHERE DIP.TEMA LIKE ('%Estrategia Comercial, Imágen y Cadena de Distribución%')");
			break;
		case 4:
			query.append(" WHERE DIP.TEMA LIKE ('%Estrategia, Planeación e Innovación%')");
			break;
		case 2:
			query.append(" WHERE DIP.TEMA LIKE ('%Manufactura Esbelta%')");
			break;

		default:
			return 0;
		}
		if(filtros.getFiltro4()>0 || filtros.getFiltro5()>0 ||
			filtros.getId()>0 || filtros.getFiltro2() > 0 || filtros.getFiltro1() > 0){
			query.append(" and ");
		}
		if(filtros.getFiltro4()>0 || filtros.getFiltro5()>0){
			query.append("(");
			if(filtros.getFiltro4()>0){
				query.append("P.ID_PAGO="+filtros.getFiltro4());
				if(filtros.getFiltro5()>0){
					query.append(" or ");
				}
			}
			if(filtros.getFiltro5()>0){
				query.append(" P.ID_PAGO="+filtros.getFiltro5());
			}
			query.append(") ");
			if(filtros.getId()>0 || filtros.getFiltro1()>0
					|| filtros.getFiltro2()>0){
				query.append(" and ");
			}
		}
		if(filtros.getId()>0){
			query.append(" ("+filtros.getId()+" IN (TR.ID_USUARIO");
			query.append(",TR.ID_TRACTORA_PADRE,C.ID_USUARIO) ");
			query.append(" or " + " C.ID_CONSULTORA IN (SELECT ID_CONSULTORA_PADRE");
			query.append(" FROM INFRA.CONSULTORAS WHERE ID_USUARIO = " + filtros.getId() + ")) ");
			if(filtros.getFiltro1()>0 || filtros.getFiltro2()>0){
				query.append(" and ");
			}	
		}
		if(filtros.getFiltro1() > 0){
			query.append(" TR.ID_USUARIO ="+filtros.getFiltro1()+" ");
			if(filtros.getFiltro2() >0){
				query.append(" and ");
			}
		}			
		if(filtros.getFiltro2() > 0){
			query.append(" C.ID_CONSULTORA_PADRE IN (SELECT ID_CONSULTORA FROM CONSULTORAS ");
			query.append(" WHERE ID_USUARIO="+filtros.getFiltro2()+")");
		}
		log.debug(query);
		List<Integer> x = getJdbcTemplate().query(query.toString(),
				new getInt());
		if(x.isEmpty()){
			return 0;
		}else {
			return  x.get(0);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public int getParticipantesEmpresas(Filtros filtros,int indice) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT count(*) as TOTAL  ");
		query.append("FROM  ");
		query.append(" INFRA.CONSULTORAS as C JOIN INFRA.REL_CONSULTORIAS_CONSULTORA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_CONSULTORIA=CI.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO=SC.ID_USUARIO ");
		query.append(" JOIN INFRA.REL_PYMES_TRACTORAS RTR on PY.ID_USUARIO = RTR.ID_USUARIO_PYME "); 
		query.append(" JOIN INFRA.TRACTORAS as TR on RTR.ID_USUARIO_TRACTORA=TR.ID_USUARIO ");
		if(filtros.getFiltro4()>0 || filtros.getFiltro5()>0){
			query.append(" JOIN INFRA.PAGOS AS P ON SC.ID_CONSULTORIA=P.ID_SERVICO_CONSULTORIA ");
		}
		query.append(" JOIN INFRA.SERVICIOS_DIPLOMADO AS SVD ON SVD.ID_USUARIO=PY.ID_USUARIO ");
		query.append(" JOIN INFRA.DIPLOMADOS AS DIP ON DIP.ID_DIPLOMADO=SVD.ID_DIPLOMADO ");
		switch (indice) {
		case 1:
				query.append(" WHERE DIP.TEMA LIKE ('%Cultura Organizacional%')");
			break;
		case 3:
			query.append(" WHERE DIP.TEMA LIKE ('%Estrategia Comercial, Imágen y Cadena de Distribución%')");
			break;
		case 4: 
			query.append(" WHERE DIP.TEMA LIKE ('%Estrategia, Planeación e Innovación%')");
			break;
		case 2:
			query.append(" WHERE DIP.TEMA LIKE ('%Manufactura Esbelta%')");
			break;

		default:
			return 0;
		}
		if(filtros.getFiltro4()>0 || filtros.getFiltro5()>0 ||
			filtros.getId()>0 || filtros.getFiltro2() > 0 || filtros.getFiltro1() > 0){
			query.append(" and ");
		}
		if(filtros.getFiltro4()>0 || filtros.getFiltro5()>0){
			query.append("(");
			if(filtros.getFiltro4()>0){
				query.append("P.ID_PAGO="+filtros.getFiltro4());
				if(filtros.getFiltro5()>0){
					query.append(" or ");
				}
			}
			if(filtros.getFiltro5()>0){
				query.append(" P.ID_PAGO="+filtros.getFiltro5());
			}
			query.append(") ");
			if(filtros.getId()>0 || filtros.getFiltro1()>0
					|| filtros.getFiltro2()>0){
				query.append(" and ");
			}
		}
		if(filtros.getId()>0){
			query.append(" ("+filtros.getId()+" IN (TR.ID_USUARIO");
			query.append(",TR.ID_TRACTORA_PADRE,C.ID_USUARIO) ");
			query.append(" or " + " C.ID_CONSULTORA IN (SELECT ID_CONSULTORA_PADRE");
			query.append(" FROM INFRA.CONSULTORAS WHERE ID_USUARIO = " + filtros.getId() + ")) ");
			if(filtros.getFiltro1()>0 || filtros.getFiltro2()>0){
				query.append(" and ");
			}	
		}
		if(filtros.getFiltro1() > 0){
			query.append(" TR.ID_USUARIO ="+filtros.getFiltro1()+" ");
			if(filtros.getFiltro2() >0){
				query.append(" and ");
			}
		}			
		if(filtros.getFiltro2() > 0){
			query.append(" C.ID_CONSULTORA_PADRE IN (SELECT ID_CONSULTORA FROM CONSULTORAS ");
			query.append(" WHERE ID_USUARIO="+filtros.getFiltro2()+")");
		}
		log.debug(query);
		List<Integer> x = getJdbcTemplate().query(query.toString(),
				new getInt());
		if(x.isEmpty()){
			return 0;
		}else {
			return  x.get(0);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public int getPorEstatus(Filtros filtros) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT count(*) as TOTAL ");
		query.append("FROM  ");
		query.append(" INFRA.CONSULTORAS as C JOIN INFRA.REL_CONSULTORIAS_CONSULTORA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_CONSULTORIA=CI.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO=SC.ID_USUARIO ");
		query.append(" JOIN INFRA.REL_PYMES_TRACTORAS RTR on PY.ID_USUARIO = RTR.ID_USUARIO_PYME "); 
		query.append(" JOIN INFRA.TRACTORAS as TR on RTR.ID_USUARIO_TRACTORA=TR.ID_USUARIO ");
		query.append(" JOIN  INFRA.SERVICIOS_DIPLOMADO AS SVD ON SVD.ID_USUARIO=PY.ID_USUARIO");
		query.append(" JOIN INFRA.ASISTENTES AS ASI ON ASI.ID_DIPLOMADO=SVD.ID_DIPLOMADO ");
		if(filtros.getFiltro4()>0 || filtros.getFiltro5()>0){
			query.append(" JOIN INFRA.PAGOS AS P ON SC.ID_CONSULTORIA=P.ID_SERVICO_CONSULTORIA ");
		}
		query.append(" WHERE SC.ESTATUS LIKE('%"+filtros.getEstatus()+"%') ");
		if(filtros.getFiltro4()>0 || filtros.getFiltro5()>0 ||
				filtros.getId()>0 || filtros.getFiltro2() > 0 || filtros.getFiltro1() > 0){
				query.append(" and ");
		}
		if(filtros.getFiltro4()>0 || filtros.getFiltro5()>0){
			query.append("(");
			if(filtros.getFiltro4()>0){
				query.append("P.ID_PAGO="+filtros.getFiltro4());
				if(filtros.getFiltro5()>0){
					query.append(" or ");
				}
			}
			if(filtros.getFiltro5()>0){
				query.append(" P.ID_PAGO="+filtros.getFiltro5());
			}
			query.append(") ");
			if(filtros.getId()>0 || filtros.getFiltro1()>0
					|| filtros.getFiltro2()>0){
				query.append(" and ");
			}
		}	
		if(filtros.getId()>0){
			query.append(" ("+filtros.getId()+" IN (TR.ID_USUARIO");
			query.append(",TR.ID_TRACTORA_PADRE,C.ID_USUARIO) ");
			query.append(" or " + " C.ID_CONSULTORA IN (SELECT ID_CONSULTORA_PADRE");
			query.append(" FROM INFRA.CONSULTORAS WHERE ID_USUARIO = " + filtros.getId() + ")) ");
			if(filtros.getFiltro1()>0 || filtros.getFiltro2()>0){
				query.append(" and ");
			}	
		}
		if(filtros.getFiltro1() > 0){
			query.append(" TR.ID_USUARIO ="+filtros.getFiltro1()+" ");
			if(filtros.getFiltro2() >0){
				query.append(" and ");
			}
		}			
		if(filtros.getFiltro2() > 0){
			query.append(" C.ID_CONSULTORA_PADRE IN (SELECT ID_CONSULTORA FROM CONSULTORAS ");
				query.append(" WHERE ID_USUARIO="+filtros.getFiltro2()+")");
		}
		log.debug(query);
		List<Integer> x = getJdbcTemplate().query(query.toString(),
				new getInt());
		if(x.isEmpty()){
			return 0;
		}else {
			return  x.get(0);
		}
	}
	@SuppressWarnings("rawtypes")
	public class getInt implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			getIntExtractor extractor = new getIntExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class getIntExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			return rs.getInt("TOTAL");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CCMXFinanzas> getCCMXFiannzas(Filtros filtros) throws DaoException {
		StringBuffer query = new StringBuffer();
		log.debug("getFinanzas");
		query.append("SELECT (rownum) AS NUMERO,");
		query.append("PY.NOMBRE_COMERCIAL, C.EMPRESA");
		query.append(",YEAR(SC.FECHA_INICIO) as ANO_ATENCION");
		query.append(",PY.CEDULA");
		query.append(",(SELECT NUMERO FROM INFRA.PAGOS WHERE ");
		query.append(" ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND TIPO LIKE '%Anticipo%') as ANTICIPO ");
		query.append(",(SELECT NUMERO FROM INFRA.PAGOS WHERE ");
		query.append(" ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND TIPO LIKE '%Abono1%') as ABONO1 ");
		query.append(",(SELECT NUMERO FROM INFRA.PAGOS WHERE ");
		query.append(" ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND TIPO LIKE '%Abono2%') as ABONO2 ");
		query.append(",(SELECT NUMERO FROM INFRA.PAGOS WHERE ");
		query.append(" ID_SERVICO_CONSULTORIA=SC.ID_CONSULTORIA AND TIPO LIKE '%Finiquito%') as FINIQUITO ");
		query.append(",CASE ");
		query.append("WHEN SC.B_CONSULTORIA_20 THEN '20' ");
		query.append("WHEN SC.B_CONSULTORIA_40 THEN '40' ");
		query.append("WHEN SC.B_CONSULTORIA_60 THEN '60' ");
		query.append("WHEN SC.B_CONSULTORIA_80 THEN '80' ");
		query.append("ELSE '0' END AS HORAS_CONSULTORIA");
		query.append(",null as TOTAL ");
		query.append("FROM ");
		query.append(" INFRA.CONSULTORAS as C JOIN INFRA.REL_CONSULTORIAS_CONSULTORA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_CONSULTORIA=CI.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO=SC.ID_USUARIO ");
		if(filtros.getFiltro2()>0 ||filtros.getFiltro3()>0 || filtros.getFiltro4()>0){
			query.append(" JOIN INFRA.PAGOS AS P ON SC.ID_CONSULTORIA=P.ID_SERVICO_CONSULTORIA WHERE (");
			if(filtros.getFiltro2()>0){
				query.append(" P.ID_PAGO="+filtros.getFiltro2());
				if(filtros.getFiltro3()>0 || filtros.getFiltro4()>0){
					query.append(" or ");
				}
			}
			if(filtros.getFiltro3()>0){
				query.append(" P.ID_PAGO="+filtros.getFiltro3());
				if(filtros.getFiltro4()>0){
					query.append(" or ");
				}
			}
			if(filtros.getFiltro4()>0){
				query.append(" P.ID_PAGO="+filtros.getFiltro4());
			}
			query.append(") ");
			if(filtros.getId()>0 || filtros.getFiltro1() > 0 || filtros.getFiltro5() > 0){
				query.append(" and ");
			}
		}else if(filtros.getId()>0 || filtros.getFiltro1() > 0 || filtros.getFiltro5() > 0){
			query.append(" WHERE ");
		}
		if(filtros.getId() > 0){			
			//Administrador consultor
			query.append(" ("+filtros.getId()+" IN (TR.ID_USUARIO");
			query.append(",TR.ID_TRACTORA_PADRE,C.ID_USUARIO) ");
			query.append(" or " + " C.ID_CONSULTORA IN (SELECT ID_CONSULTORA_PADRE");
			query.append(" FROM INFRA.CONSULTORAS WHERE ID_USUARIO = " + filtros.getId() + ")) ");
			if(filtros.getFiltro1()>0 || filtros.getFiltro5()>0){
				query.append(" and ");
			}
			
		}	
		
		if(filtros.getFiltro5()>0){
			switch (filtros.getFiltro5()) {
			case 20:
				query.append(" SC.B_CONSULTORIA_20=TRUE ");
				break;
			case 40:
				query.append(" SC.B_CONSULTORIA_40=TRUE ");
				break;
			case 60:
				query.append(" SC.B_CONSULTORIA_60=TRUE ");
				break;
			case 80:
				query.append(" SC.B_CONSULTORIA_80=TRUE ");
				break;
			default:
				break;
			}
			if( filtros.getFiltro1() > 0 ){
				query.append(" and ");
			}
		}
		if(filtros.getFiltro1() > 0){
			query.append(" C.ID_CONSULTORA_PADRE IN (SELECT ID_CONSULTORA FROM CONSULTORAS ");
			query.append(" WHERE ID_CONSULTORA=" + filtros.getFiltro1() + ")");
		}
		query.append(";");
		log.debug("quey Finanzas= " + query);
		return getJdbcTemplate().query(query.toString(),
				new CCMXFiannzasRowMapper());
	}
	@SuppressWarnings("rawtypes")
	public class CCMXFiannzasRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			CCMXFiannzasResultSetExtractor extractor = new CCMXFiannzasResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class CCMXFiannzasResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			CCMXFinanzas cFinanzas = new CCMXFinanzas();
			cFinanzas.setNo(rs.getInt("NUMERO"));
			cFinanzas.setPyme(rs.getString("NOMBRE_COMERCIAL"));
			cFinanzas.setConsultora(rs.getString("EMPRESA"));
			cFinanzas.setAnoAtencion(rs.getInt("ANO_ATENCION"));
			cFinanzas.setCedula(rs.getString("CEDULA"));
			cFinanzas.setAnoAtencion(rs.getInt("ANO_ATENCION"));
			cFinanzas.setFaturaAnticipo(rs.getString("ANTICIPO"));
			cFinanzas.setFaturaAbono1(rs.getString("ABONO1"));
			cFinanzas.setFaturaAbono2(rs.getString("ABONO2"));
			cFinanzas.setFacturaFiniquito(rs.getString("FINIQUITO"));
			cFinanzas.setHorasConsultoria(rs.getInt("HORAS_CONSULTORIA"));
			cFinanzas.setTotal(rs.getString("TOTAL"));
			return cFinanzas;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PYMESReporte> getPymesReporte(Filtros filtros) throws DaoException{
		StringBuffer query = new StringBuffer();
		log.debug("En jdbc Pymes");
		query.append("SELECT (rownum) AS NUMERO,");
				query.append("PY.NOMBRE_COMERCIAL "); 
				query.append(",YEAR(SC.FECHA_INICIO) as ANO_ATENCION ");
				query.append(",PY.CEDULA ");
				query.append(",C.EMPRESA as CONSULTOR");
				query.append(",SC.ESTATUS ");
				query.append(",CASE ");
				query.append("WHEN SC.B_CONSULTORIA_20 THEN '20' ");
				query.append("WHEN SC.B_CONSULTORIA_40 THEN '40' ");
				query.append("WHEN SC.B_CONSULTORIA_60 THEN '60' ");
				query.append("WHEN SC.B_CONSULTORIA_80 THEN '80' ");
				query.append("ELSE 'NO DEFINIDO' END AS HORAS_CONSULTORIA ");
				query.append(",SC.FINANZAS_ANTES");
				query.append(",SC.FINANZAS_DESPUES");
				query.append(",SC.ADMINISTRACION_ANTES AS ADMON_ANTES");
				query.append(",SC.ADMINISTRACION_DESPUES AS ADMON_DESPUES");
				query.append(",SC.MERCADEO_ANTES AS MDO_ANTES");
				query.append(",SC.MERCADEO_DESPUES AS MDO_DESPUES");
				query.append(",SC.PROCESOS_ANTES AS OPERACION_ANTES");
				query.append(",SC.PROCESOS_DESPUES AS OPERACION_DESPUES");
				query.append(",SC.RECURSOS_HUMANOS_ANTES as RH_ANTES");
				query.append(",SC.RECURSOS_HUMANOS_DESPUES as RH_DESPUES");
				query.append(",(SC.ADMINISTRACION_ANTES + SC.MERCADEO_ANTES + SC.FINANZAS_ANTES +");
				query.append(" SC.PROCESOS_ANTES + SC.RECURSOS_HUMANOS_ANTES)*1.0/5  AS PROMEDIO_ANTES");
				query.append(",(SC.ADMINISTRACION_DESPUES + SC.MERCADEO_DESPUES + SC.FINANZAS_DESPUES + ");
				query.append(" SC.PROCESOS_DESPUES + SC.RECURSOS_HUMANOS_DESPUES)*1.0/5  AS PROMEDIO_DESPUES ");
				query.append(",PY.CALIFICACION ");
				query.append("FROM ");							
				query.append(" INFRA.CONSULTORAS as C JOIN INFRA.REL_CONSULTORIAS_CONSULTORA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
				query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_CONSULTORIA=CI.ID_CONSULTORIA ");
				query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO=SC.ID_USUARIO ");
				query.append(" JOIN INFRA.INDICADORES as I ON I.ID_PYME=PY.ID_USUARIO ");
				
				if(filtros.getId()>0){		
					if(filtros.getPermisos()==1){
						//Comprador administrador
						query.append(" JOIN INFRA.REL_PYMES_TRACTORAS RTR  on PY.ID_USUARIO = RTR.ID_USUARIO_PYME "); 
						query.append(" JOIN INFRA.TRACTORAS as TR on RTR.ID_USUARIO_TRACTORA=TR.ID_USUARIO ");
						query.append(" WHERE "+filtros.getId()+" IN(TR.ID_USUARIO,TR.ID_USUARIO_PADRE)");
						
					}else if(filtros.getPermisos()==2) {
						//Comprador
						query.append(" JOIN INFRA.REL_PYMES_TRACTORAS RTR  on PY.ID_USUARIO = RTR.ID_USUARIO_PYME "); 
						query.append(" JOIN INFRA.TRACTORAS as TR on RTR.ID_USUARIO_TRACTORA=TR.ID_USUARIO ");
						query.append(" WHERE "+filtros.getId()+"=TR.ID_USUARIO ");
						
					}else if(filtros.getPermisos()==3){
						//Administrador consultor
						query.append(" WHERE ");
						query.append(" "+filtros.getId()+" IN (C.ID_USUARIO_PADRE,C.ID_CONSULTORA_PADRE) ");
					}else{
						//Consultor
						query.append(" WHERE");
						query.append(" C.ID_CONSULTORA_PADRE="+filtros.getId()+" ");
					}
					if(filtros.getFiltro2() > 0){
						query.append(" and ");
					}
				}
				else {
					if(filtros.getFiltro2() >0){
						query.append(" WHERE ");
					}
				}
				if(filtros.getFiltro2() > 0){
					query.append("  C.ID_CONSULTORA = ");
					query.append(filtros.getFiltro2() +" ");
				}
				
		query.append(";");				
		log.debug(query);
		return getJdbcTemplate().query(query.toString(),
				new PymesReporteRowMapper());
	}
	@SuppressWarnings("rawtypes")
	public class PymesReporteRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			PymesReporteResultSetExtractor extractor = new PymesReporteResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class PymesReporteResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			PYMESReporte pReporte = new PYMESReporte();
			pReporte.setNo(rs.getInt("NUMERO"));
			pReporte.setPyme(rs.getString("NOMBRE_COMERCIAL"));
			pReporte.setAnoAtencion(rs.getString("ANO_ATENCION"));
			pReporte.setCedula(rs.getString("CEDULA"));
			pReporte.setEstatus(rs.getString("ESTATUS"));
			pReporte.setConsultor(rs.getString("CONSULTOR"));
			pReporte.setHorasConsultoria(rs.getString("HORAS_CONSULTORIA"));
			pReporte.setAdmonantes(rs.getString("ADMON_ANTES"));
			pReporte.setAdmonDespues(rs.getString("ADMON_DESPUES"));
			pReporte.setMdoAntes(rs.getString("MDO_ANTES"));
			pReporte.setMdoDespues(rs.getString("MDO_DESPUES"));
			pReporte.setFinanzasAntes(rs.getString("FINANZAS_ANTES"));
			pReporte.setFinanzasdespues(rs.getString("FINANZAS_DESPUES"));
			pReporte.setOperacionAntes(rs.getString("OPERACION_ANTES"));
			pReporte.setOperacionDespues(rs.getString("OPERACION_DESPUES"));
			pReporte.setRhAntes(rs.getString("RH_ANTES"));
			pReporte.setRhDespues(rs.getString("RH_DESPUES"));
			pReporte.setPromedioAntes(rs.getString("PROMEDIO_ANTES"));
			pReporte.setPromedioDespues(rs.getString("PROMEDIO_DESPUES"));		
			pReporte.setEstrellas(rs.getInt("CALIFICACION"));
			return pReporte;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TotalEmpresas> getEmpresasByConsultora(Filtros filtros) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT C.EMPRESA ,count(*) as TOTAL ");
		query.append("FROM ");							
		query.append(" INFRA.CONSULTORAS as C JOIN INFRA.REL_CONSULTORIAS_CONSULTORA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_CONSULTORIA=CI.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO=SC.ID_USUARIO ");
		if(filtros.getId()>0){		
			
			if(filtros.getPermisos()==1){
				//Comprador administrador
				query.append(" JOIN INFRA.REL_PYMES_TRACTORAS RTR  on PY.ID_USUARIO = RTR.ID_USUARIO_PYME "); 
				query.append(" JOIN INFRA.TRACTORAS as TR on RTR.ID_USUARIO_TRACTORA=TR.ID_USUARIO ");
				query.append(" WHERE "+filtros.getId()+" IN(TR.ID_USUARIO,TR.ID_USUARIO_PADRE)");
				
			}else if(filtros.getPermisos()==2) {
				//Comprador
				query.append(" JOIN INFRA.REL_PYMES_TRACTORAS RTR  on PY.ID_USUARIO = RTR.ID_USUARIO_PYME "); 
				query.append(" JOIN INFRA.TRACTORAS as TR on RTR.ID_USUARIO_TRACTORA=TR.ID_USUARIO ");
				query.append(" WHERE "+filtros.getId()+"=TR.ID_USUARIO ");
				
			}else if(filtros.getPermisos()==3){
				//Administrador consultor
				query.append(" WHERE ");
				query.append(" "+filtros.getId()+" IN (C.ID_USUARIO_PADRE,C.ID_CONSULTORA_PADRE) ");
			}else{
				//Consultor
				query.append(" WHERE");
				query.append(" C.ID_CONSULTORA_PADRE="+filtros.getId()+" ");
			}
			if(filtros.getFiltro2() > 0){
				query.append(" and ");
			}
		}
		else {
			if(filtros.getFiltro2() >0){
				query.append(" WHERE ");
			}
		}
		if(filtros.getFiltro2() > 0){
			query.append(" C.ID_CONSULTORA = ");
			query.append(filtros.getFiltro2() +" ");
		}
		
		query.append(" GROUP BY C.EMPRESA;");
		log.debug("modificar query::::" + query);
		return getJdbcTemplate().query(query.toString(),
				new getTotalEmpresas());
	}
	@SuppressWarnings("rawtypes")
	public class getTotalEmpresas implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			getTotalEmpresasExtractor extractor = new getTotalEmpresasExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class getTotalEmpresasExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			TotalEmpresas totalEmpresas = new TotalEmpresas();
			totalEmpresas.setConsultoraTotal(rs.getString("EMPRESA"));
			totalEmpresas.setEmpresas(rs.getInt("TOTAL"));
			return totalEmpresas;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<IndicadoresPymes> getIndicadoresReporte(Filtros filtros)
			throws DaoException {
		StringBuffer query= new StringBuffer();
		query.append("SELECT (rownum) AS NUMERO");
		query.append(",PY.NOMBRE_COMERCIAL");
		query.append(",NULL AS ANO_ATENCION");
		query.append(",PY.CEDULA");
		query.append(",CONCAT(CONCAT(CONCAT(CONCAT(C.NOMBRE_CONTACTO,' ')");
		query.append(",C.APP_PATERNO_CONTACTO),' '),C.APP_MATERNO_CONTACTO ) AS CONSULTOR");
		query.append(",SC.ESTATUS");
		query.append(",CASE ");
		query.append("WHEN SC.B_CONSULTORIA_20 THEN '20' ");
		query.append("WHEN SC.B_CONSULTORIA_40 THEN '40' ");
		query.append("WHEN SC.B_CONSULTORIA_60 THEN '60' ");
		query.append("WHEN SC.B_CONSULTORIA_80 THEN '80' ");
		query.append("ELSE 'NO DEFINIDO' END AS HORAS_CONSULTORIA ");
		query.append(",SC.ADMINISTRACION_ANTES AS ADMON_ANTES");
		query.append(",SC.MERCADEO_ANTES AS MDO_ANTES");
		query.append(",SC.FINANZAS_ANTES");
		query.append(",SC.PROCESOS_ANTES AS OPERACIONES_ANTES");
		query.append(",SC.RECURSOS_HUMANOS_ANTES AS RH_ANTES");
		query.append(",(SC.ADMINISTRACION_ANTES + SC.MERCADEO_ANTES + SC.FINANZAS_ANTES +");
		query.append(" SC.PROCESOS_ANTES + SC.RECURSOS_HUMANOS_ANTES)*1.0/5  AS PROMEDIO_ANTES");
		query.append(",SC.ADMINISTRACION_DESPUES ADMON_DESPUES");
		query.append(",SC.MERCADEO_DESPUES MDO_DESPUES");
		query.append(",SC.FINANZAS_DESPUES AS FINANZAS_DESPUES");
		query.append(",SC.PROCESOS_DESPUES AS OPERACION_DESPUES");
		query.append(",SC.RECURSOS_HUMANOS_DESPUES AS RH_DESPUES");
		query.append(",(SC.ADMINISTRACION_DESPUES + SC.MERCADEO_DESPUES + SC.FINANZAS_DESPUES + ");
		query.append(" SC.PROCESOS_DESPUES + SC.RECURSOS_HUMANOS_DESPUES)*1.0/5  AS PROMEDIO_DESPUES");
		query.append(",I.INGRESOS_ANTES");
		query.append(",I.INGRESOS_DESPUES");
		query.append(",I.CLIENTES_ANTES");
		query.append(",I.CLIENTES_DESPUES");
		query.append(",I.EMPLEADOS_ANTES");
		query.append(",I.EMPLEADOS_DESPUES");
		query.append(",I.EGRESOS_ANTES");
		query.append(",I.EGRESOS_DESPUES ");
		//TODO Ver de donde se van a optener los siguiente indicadores
		query.append(",null as AHORROS1");
		query.append(",null as AHORROS2");
		query.append(",null as AHORROS3");
		query.append(",null as AHORROS4");
		query.append(",null as DEFECTOS1");
		query.append(",null as DEFECTOS2");
		query.append(",null as DEFECTOS3");
		query.append(",null as DEFECTOS4");
		query.append(",null as AHORRO1");
		query.append(",null as AHORRO2");
		query.append(",null as AHORRO3");
		query.append(",null as AHORRO4");
		query.append(",null as POSTVENTA1");
		query.append(",null as POSTVENTA2");
		query.append(",null as POSTVENTA3");
		query.append(",null as POSTVENTA4");
		query.append(",null as CAPACIDADPYME1");
		query.append(",null as CAPACIDADPYME2");
		query.append(",null as CAPACIDADPYME3");
		query.append(",null as CAPACIDADPYME4");	
		query.append(" FROM ");
		query.append("INFRA.PYMES AS PY ");
		query.append("JOIN  INFRA.REL_CONSULTORAS_PYME AS REL ON REL.ID_USUARIO_PYME= PY.ID_USUARIO ");
		query.append("JOIN INFRA.CONSULTORAS AS C ON REL.ID_USUARIO_CONSULTOR=C.ID_USUARIO ");
		query.append("JOIN INFRA.SERVICIOS_CONSULTORIA AS SC ON PY.ID_USUARIO=SC.ID_USUARIO ");
		query.append("LEFT JOIN INFRA.INDICADORES AS I ON I.ID_PYME=PY.ID_USUARIO ");
		if(filtros.getFiltro5()>0 || filtros.getFiltro4()>0){
			query.append("JOIN INFRA.PAGOS AS PA ON PA.ID_SERVICO_CONSULTORIA = SC.ID_CONSULTORIA ");
		}		
		query.append("WHERE C.ID_CONSULTORA_PADRE>0 ");
		if(filtros.getFiltro5()>0){
			query.append(" AND PA.ID_PAGO = " + filtros.getFiltro5() );
		}
		if(filtros.getFiltro4()>0){
			query.append(" AND PA.ID_PAGO = " + filtros.getFiltro4() );
		}
		if(filtros.getId()>0){
			query.append(" AND C.ID_USUARIO =" + filtros.getId());
		}
		if(filtros.getCedula()!=null && filtros.getCedula().trim().equals("")){
			query.append(" AND PY.CEDULA LIKE(%"+filtros.getCedula()+"%) ");
		}
		if(filtros.getEstatus()!=null && filtros.getEstatus().trim().equals("")){
			query.append(" AND SC.ESTATUS LIKE(%"+filtros.getCedula()+"%) ");
		}
		if(filtros.getFiltro2()>0){
			query.append(" AND C.ID_CONSULTORA=" +filtros.getFiltro2());
		}
		query.append(";");
		log.debug("ReporteIndicadoresPymes()"+query.toString());
		
		return getJdbcTemplate().query(query.toString(),
				new getIndicadoresPymes());
	}
	@SuppressWarnings("rawtypes")
	public class getIndicadoresPymes implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			getIndicadoresPymesExtractor extractor = new getIndicadoresPymesExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class getIndicadoresPymesExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			IndicadoresPymes in=new IndicadoresPymes();
			in.setNo(rs.getInt("NUMERO"));
			in.setPyme(rs.getString("NOMBRE_COMERCIAL"));
			in.setAnoAtencion(rs.getString("ANO_ATENCION"));
			in.setCedula(rs.getString("CEDULA"));
			in.setConsultor(rs.getString("CONSULTOR"));
			in.setEstatus(rs.getString("ESTATUS"));
			in.setHorasConsultoria(rs.getString("HORAS_CONSULTORIA"));
			in.setAdmonAntes(rs.getInt("ADMON_ANTES"));
			in.setMdoAntes(rs.getInt("MDO_ANTES"));
			in.setFinanzasAntes(rs.getInt("FINANZAS_ANTES"));
			in.setOperacionAntes(rs.getInt("OPERACIONES_ANTES"));
			in.setRhAntes(rs.getInt("RH_ANTES"));
			in.setPromedioAntes(rs.getFloat("PROMEDIO_ANTES"));
			in.setAdmonDespues(rs.getInt("ADMON_DESPUES"));
			in.setMdoDespues(rs.getInt("MDO_DESPUES"));
			in.setFinanzasDespues(rs.getInt("FINANZAS_DESPUES"));
			in.setOperacionDespues(rs.getInt("OPERACION_DESPUES"));
			in.setRhDespues(rs.getInt("RH_DESPUES"));
			in.setPromedioDespues(rs.getFloat("PROMEDIO_DESPUES"));
			in.setIngresosAntes(rs.getInt("INGRESOS_ANTES"));
			in.setIngresosDespues(rs.getInt("INGRESOS_DESPUES"));
			in.setClientesAntes(rs.getInt("CLIENTES_ANTES"));
			in.setClientesDespues(rs.getInt("CLIENTES_DESPUES"));
			in.setEmpleadosAntes(rs.getInt("EMPLEADOS_ANTES"));
			in.setEmpleadosDespues(rs.getInt("EMPLEADOS_DESPUES"));
			in.setVentasAntes(rs.getInt("EGRESOS_ANTES"));
			in.setVentasDespues(rs.getInt("EGRESOS_DESPUES"));
			in.setMonetarios1(rs.getInt("AHORROS1"));
			in.setMonetarios2(rs.getInt("AHORROS2"));
			in.setMonetarios3(rs.getInt("AHORROS3"));
			in.setMonetarios4(rs.getInt("AHORROS4"));
			in.setDefectos1(rs.getInt("DEFECTOS1"));
			in.setDefectos2(rs.getInt("DEFECTOS2"));
			in.setDefectos3(rs.getInt("DEFECTOS3"));
			in.setDefectos4(rs.getInt("DEFECTOS4"));
			in.setAhorro1(rs.getInt("AHORRO1"));
			in.setAhorro2(rs.getInt("AHORRO2"));
			in.setAhorro3(rs.getInt("AHORRO3"));
			in.setAhorro4(rs.getInt("AHORRO4"));
			in.setPostVenta1(rs.getInt("POSTVENTA1"));
			in.setPostVenta2(rs.getInt("POSTVENTA2"));
			in.setPostVenta3(rs.getInt("POSTVENTA3"));
			in.setPostVenta4(rs.getInt("POSTVENTA4"));
			in.setCapacidadPyme1(rs.getInt("CAPACIDADPYME1"));
			in.setCapacidadPyme2(rs.getInt("CAPACIDADPYME2"));
			in.setCapacidadPyme3(rs.getInt("CAPACIDADPYME3"));
			in.setCapacidadPyme4(rs.getInt("CAPACIDADPYME4"));			
			return in;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<FiltrosGenerales> getMenuFacturaAnticipo() throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_PAGO as ID ");
		query.append(",NUMERO AS STRING ");
		query.append("FROM INFRA.PAGOS WHERE TIPO ");
		query.append("LIKE ('%Anticipo%')");
		query.append(";");
		log.debug("getMenuFacturaAnticipo() query=" + query);
		return getJdbcTemplate().query(query.toString(),
				new getGenerales());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<FiltrosGenerales> getMenuFacturaFiniquito() throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_PAGO as ID ");
		query.append(",NUMERO AS STRING ");
		query.append("FROM INFRA.PAGOS WHERE TIPO ");
		query.append("LIKE ('%Finiquito%')");
		query.append(";");
		log.debug("getMenuFacturaFiniquito() query=" + query);
		return getJdbcTemplate().query(query.toString(),
				new getGenerales());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<FiltrosGenerales> getMenuFacturaAnticipoFiniquito() throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_PAGO as ID ");
		query.append(",NUMERO AS STRING ");
		query.append("FROM INFRA.PAGOS WHERE TIPO ");
		query.append("LIKE ('%Anticipo%') ");
		query.append(" or TIPO LIKE('Finiquito');");
		log.debug("getMenuFacturaAnticipoFiniquito() query=" + query);
		return getJdbcTemplate().query(query.toString(),
				new getGenerales());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<FiltrosGenerales> getMenuCedulas() throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("SELECT CEDULA AS STRING,NULL AS ID FROM INFRA.PYMES ");
		query.append("WHERE CEDULA != '' GROUP BY CEDULA ;");
		log.debug("getMenuCedulas() query=" + query);
		return getJdbcTemplate().query(query.toString(),
				new getGenerales());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<FiltrosGenerales> getMenuEstatus() throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("SELECT ESTATUS AS STRING, NULL AS ID ");
		query.append("FROM INFRA.SERVICIOS_CONSULTORIA  GROUP BY ESTATUS;");
		log.debug("getMenuEstatus() query=" + query);
		return getJdbcTemplate().query(query.toString(),
				new getGenerales());
	}
	@SuppressWarnings("rawtypes")
	public class getGenerales implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			getGeneralesExtractor extractor = new getGeneralesExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class getGeneralesExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			FiltrosGenerales fi = new FiltrosGenerales();
			fi.setId(rs.getInt("ID"));
			fi.setCampoString(rs.getString("STRING"));
			return fi;
		}
	}
}
