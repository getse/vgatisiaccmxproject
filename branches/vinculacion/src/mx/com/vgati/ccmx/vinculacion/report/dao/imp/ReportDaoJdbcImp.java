package mx.com.vgati.ccmx.vinculacion.report.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;


import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.report.dao.ReportDao;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXFinanzas;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXParticipantes;
import mx.com.vgati.ccmx.vinculacion.report.dto.Filtros;
import mx.com.vgati.ccmx.vinculacion.report.dto.PYMESReporte;
import mx.com.vgati.ccmx.vinculacion.report.dto.ServiciosConsultoria;
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
		log.debug("getConsultora()");

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
		query.append(",CASE WHEN PY.B_PRIMER_NIVEL THEN 'Sector de servicios'");
		query.append(" WHEN PY. B_SEGUNDO_NIVEL  THEN 'Sector comercial'");
		query.append(" WHEN PY.B_TERCER_NIVEL   THEN 'Sector manufacturero'");
		query.append(" ELSE 'No definido'  END CASE as GIRO ");
		query.append(" ,CI.ESTATUS");
		query.append(" ,null as FECHA_INICIO");
		query.append(" ,null as FECHA_TERMINO");
		query.append(" ,null as ANO_ATENCION");
		query.append(" ,CASE PY.B_DIPLOMADO_CCMX_UNO");
		query.append(" WHEN PY.B_DIPLOMADO_CCMX_UNO THEN 'Inscrito' ");
		query.append(" ELSE '' END AS B_DIPLOMADO_CCMX_UNO");
		query.append(" ,CASE PY.B_DIPLOMADO_CCMX_DOS");
		query.append(" WHEN PY.B_DIPLOMADO_CCMX_DOS  THEN 'Inscrito'");
		query.append(" ELSE '  ' END AS B_DIPLOMADO_CCMX_DOS");
		query.append(",CASE PY.B_DIPLOMADO_CCMX_TRES"); 
		query.append(" WHEN PY.B_DIPLOMADO_CCMX_TRES");
		query.append(" THEN 'Inscrito'");
		query.append(" ELSE '' END AS B_DIPLOMADO_CCMX_TRES");
		query.append(" ,CASE PY.B_DIPLOMADO_CCMX_CUATRO "); 
		query.append(" WHEN PY.B_DIPLOMADO_CCMX_CUATRO ");
		query.append(" THEN 'Inscrito'");
		query.append(" ELSE '' END AS B_DIPLOMADO_CCMX_CUATRO");
		query.append(" ,null as SESION_INFORMATIVA");
		query.append(" ,null as RADAR_PROMEDIO_ANT");
		query.append(" ,null as RADAR_PROMEDIO_DES ");
		query.append(" FROM  ");
		query.append(" INFRA.CONSULTORAS as C JOIN INFRA.CONSULTORIA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_CONSULTORIA=CI.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO=SC.ID_USUARIO ");
		query.append(" JOIN INFRA.REL_PYMES_TRACTORAS RTR on PY.ID_USUARIO = RTR.ID_USUARIO_PYME "); 
		query.append(" JOIN INFRA.TRACTORAS as TR on RTR.ID_USUARIO_TRACTORA=TR.ID_USUARIO ");
		if(filtros.getId()>-1){			
			query.append(" WHERE ");
			query.append(" "+filtros.getId()+" IN (TR.ID_USUARIO_PADRE,TR.ID_TRACTORA_PADRE,C.ID_CONSULTORA_PADRE,C.ID_USUARIO_PADRE) ");
			if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
				query.append(" and ");
			}
		}
		else {
			if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
				query.append(" WHERE ");
			}
		}
		if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
			if(filtros.getFiltro1() > 0){
				query.append(" TR.ID_USUARIO ="+filtros.getFiltro1()+" ");
				if(filtros.getFiltro2() != -1){
					query.append(" and ");
				}
			}			
			if(filtros.getFiltro2() > 0){
				query.append(" C.ID_USUARIO= "+filtros.getFiltro2()+" ");
			}
		}
		log.debug(query);
		return getJdbcTemplate().query(query.toString(),
				new CCMXPartReportRowMapper());
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
			ccmxParticipantes.setAnoAtencion(rs.getString("FECHA_INICIO"));
			ccmxParticipantes.setParticipantesDiplomadoCultOrg(rs.getString("B_DIPLOMADO_CCMX_UNO"));
			ccmxParticipantes.setParticipantesDiplomadoEstrCom(rs.getString("B_DIPLOMADO_CCMX_DOS"));
			ccmxParticipantes.setParticipantesDiplomadoReduCos(rs.getString("B_DIPLOMADO_CCMX_TRES"));
			ccmxParticipantes.setParticipantesDiplomadoPlanIno(rs.getString("B_DIPLOMADO_CCMX_CUATRO"));
			ccmxParticipantes.setSesionInformativa(rs.getString("SESION_INFORMATIVA"));
			ccmxParticipantes.setRadarPromAnt(rs.getString("RADAR_PROMEDIO_ANT"));
			ccmxParticipantes.setRadarPromDes(rs.getString("RADAR_PROMEDIO_DES"));
			return ccmxParticipantes;
		}

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PYMESReporte> getPymesReporte(Filtros filtros) throws DaoException{
		StringBuffer query = new StringBuffer();
		log.debug("En jdbc Pymes");
		query.append("SELECT (rownum) AS NUMERO,");
				query.append("PY.NOMBRE_COMERCIAL "); 
				query.append(",null as ANO_ATENCION ");
				query.append(",null as CEDULA ");
				query.append(",C.EMPRESA as CONSULTOR");
				query.append(",CI.ESTATUS ");
				query.append(",CASE ");
				query.append("WHEN SC.B_CONSULTORIA_20 THEN '20' ");
				query.append("WHEN SC.B_CONSULTORIA_40 THEN '40' ");
				query.append("WHEN SC.B_CONSULTORIA_60 THEN '60' ");
				query.append("WHEN SC.B_CONSULTORIA_80 THEN '80' ");
				query.append("ELSE 'NO DEFINIDO' END AS HORAS_CONSULTORIA ");
				query.append(",null as CLIENTES_ANTES");
				query.append(",null as CLIENTES_DESPUES ");
				query.append(",null as EMPLEADOS_ANTES");
				query.append(",null as EMPLEADOS_DESPUES");
				query.append(",null as VENTAS_ANTES");
				query.append(",null as VENTAS_DESPUES");
				query.append(",null as ADMON_ANTES");
				query.append(",null as ADMON_DESPUES");
				query.append(",null as MDO_ANTES");
				query.append(",null as MDO_DESPUES");
				query.append(",null as FINANZAS_ANTES");
				query.append(",null as FINANZAS_DESPUES");
				query.append(",null as OPERACION_ANTES");
				query.append(",null as OPERACION_DESPUES");
				query.append(",null as RH_ANTES");
				query.append(",null as RH_DESPUES");
				query.append(",null as PROMEDIO_ANTES");
				query.append(",null as PROMEDIO_DESPUES ");
				query.append("FROM ");							
				query.append(" INFRA.CONSULTORAS as C JOIN INFRA.CONSULTORIA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
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
			pReporte.setClientesAntes(rs.getInt("CLIENTES_ANTES"));
			pReporte.setClientesDespues(rs.getInt("CLIENTES_DESPUES"));
			pReporte.setEmpleadosAntes(rs.getInt("EMPLEADOS_ANTES"));
			pReporte.setVentasAntes(rs.getInt("EMPLEADOS_DESPUES"));
			pReporte.setVentasDespues(rs.getInt("VENTAS_ANTES"));
			pReporte.setVentasDespues(rs.getInt("VENTAS_DESPUES"));
			pReporte.setAdmonantes(rs.getString("ADMON_ANTES"));
			pReporte.setAdmonDespues(rs.getString("ADMON_DESPUES"));
			pReporte.setMdoAntes(rs.getString("MDO_ANTES"));
			pReporte.setMdoDespues(rs.getString("MDO_DESPUES"));
			pReporte.setFinanzasAntes(rs.getString("FINANZAS_ANTES"));
			pReporte.setFinanzasdespues(rs.getString("FINANZAS_DESPUES"));
			pReporte.setOperacionAntes(rs.getString("OPERACION_ANTES"));
			pReporte.setOperacionDespues(rs.getString("OPERACION_DESPUES"));
			pReporte.setRhAntes(rs.getString("RH_ANTES"));
			pReporte.setRgDespues(rs.getString("RH_DESPUES"));
			pReporte.setPromedioAntes(rs.getString("PROMEDIO_ANTES"));
			pReporte.setPromedioDespues(rs.getString("PROMEDIO_DESPUES"));			
			return pReporte;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CCMXFinanzas> getCCMXFiannzas(Filtros filtros) throws DaoException {
		StringBuffer query = new StringBuffer();
		log.debug("getFinanzas");
		query.append("SELECT (rownum) AS NUMERO,");
		query.append("PY.NOMBRE_COMERCIAL, C.EMPRESA");
		query.append(",null as ANO_ATENCION");
		query.append(",null as CEDULA");
		query.append(",null as ANTICIPO");
		query.append(",null as ABONO1");
		query.append(",null as ABONO2");
		query.append(",null as FINIQUITO");
		query.append(",CASE ");
		query.append("WHEN SC.B_CONSULTORIA_20 THEN '20' ");
		query.append("WHEN SC.B_CONSULTORIA_40 THEN '40' ");
		query.append("WHEN SC.B_CONSULTORIA_60 THEN '60' ");
		query.append("WHEN SC.B_CONSULTORIA_80 THEN '80' ");
		query.append("ELSE 'NO DEFINIDO' END AS HORAS_CONSULTORIA");
		query.append(",null as TOTAL ");
		query.append("FROM ");
		query.append(" INFRA.CONSULTORAS as C JOIN INFRA.CONSULTORIA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_CONSULTORIA=CI.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO=SC.ID_USUARIO ");
		if(filtros.getId() > 0){			
			//Administrador consultor
			query.append(" WHERE ");
			query.append(" "+filtros.getId()+" IN (C.ID_USUARIO_PADRE,C.ID_CONSULTORA_PADRE) ");
			if(filtros.getFiltro1() > 0 || filtros.getFiltro5() > 0 ){
				query.append(" AND ");
			}
			
		}
		else {
			if(filtros.getFiltro1() > 0 || filtros.getFiltro5() > 0 ){
				query.append(" WHERE ");
			}
		}		
		if(filtros.getFiltro1()>0 || filtros.getFiltro5() >0){
			
			if(filtros.getFiltro1()>0){
				query.append(" C.ID_CONSULTORA="+filtros.getFiltro1());
				if(filtros.getFiltro5() >0){
					query.append(" AND ");
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
			}
			
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
			cFinanzas.setFaturaAnticipo(rs.getDouble("ANTICIPO"));
			cFinanzas.setFaturaAbono1(rs.getDouble("ABONO1"));
			cFinanzas.setFaturaAbono2(rs.getDouble("ABONO2"));
			cFinanzas.setFacturaFiniquito(rs.getDouble("FINIQUITO"));
			cFinanzas.setHorasConsultoria(rs.getString("HORAS_CONSULTORIA"));
			cFinanzas.setTotal(rs.getDouble("TOTAL"));
			return cFinanzas;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public int getTCultura(Filtros filtros) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT count(*) as TOTAL  ");
		query.append(" FROM  ");
		query.append(" INFRA.CONSULTORAS as C JOIN INFRA.CONSULTORIA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_CONSULTORIA=CI.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO=SC.ID_USUARIO ");
		query.append(" JOIN INFRA.REL_PYMES_TRACTORAS RTR on PY.ID_USUARIO = RTR.ID_USUARIO_PYME "); 
		query.append(" JOIN INFRA.TRACTORAS as TR on RTR.ID_USUARIO_TRACTORA=TR.ID_USUARIO ");
		query.append(" WHERE B_DIPLOMADO_CCMX_UNO=TRUE  ");
		if(filtros.getId()>-1){			
			query.append(" AND ");
			query.append(" "+filtros.getId()+" IN (TR.ID_USUARIO_PADRE,TR.ID_TRACTORA_PADRE,C.ID_CONSULTORA_PADRE,C.ID_USUARIO_PADRE) ");
			if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
				query.append(" and ");
			}
		}
		else {
			if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
				query.append(" AND ");
			}
		}
		if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
			if(filtros.getFiltro1() > 0){
				query.append(" TR.ID_USUARIO ="+filtros.getFiltro1()+" ");
				if(filtros.getFiltro2() != -1){
					query.append(" and ");
				}
			}			
			if(filtros.getFiltro2() > 0){
				query.append(" C.ID_USUARIO= "+filtros.getFiltro2()+" ");
			}
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
	public int getTPlaneacion(Filtros filtros) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT count(*) as TOTAL  ");
		query.append(" FROM  ");
		query.append(" INFRA.CONSULTORAS as C JOIN INFRA.CONSULTORIA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_CONSULTORIA=CI.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO=SC.ID_USUARIO ");
		query.append(" JOIN INFRA.REL_PYMES_TRACTORAS RTR on PY.ID_USUARIO = RTR.ID_USUARIO_PYME "); 
		query.append(" JOIN INFRA.TRACTORAS as TR on RTR.ID_USUARIO_TRACTORA=TR.ID_USUARIO ");
		query.append(" WHERE B_DIPLOMADO_CCMX_CUATRO=TRUE ");
		if(filtros.getId()>-1){			
			query.append(" AND ");
			query.append(" "+filtros.getId()+" IN (TR.ID_USUARIO_PADRE,TR.ID_TRACTORA_PADRE,C.ID_CONSULTORA_PADRE,C.ID_USUARIO_PADRE) ");
			if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
				query.append(" and ");
			}
		}
		else {
			if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
				query.append(" AND ");
			}
		}
		if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
			if(filtros.getFiltro1() > 0){
				query.append(" TR.ID_USUARIO ="+filtros.getFiltro1()+" ");
				if(filtros.getFiltro2() != -1){
					query.append(" and ");
				}
			}			
			if(filtros.getFiltro2() > 0){
				query.append(" C.ID_USUARIO= "+filtros.getFiltro2()+" ");
			}
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
	public int getTManufactura(Filtros filtros) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT count(*) as TOTAL  ");
		query.append(" FROM  ");
		query.append(" INFRA.CONSULTORAS as C JOIN INFRA.CONSULTORIA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_CONSULTORIA=CI.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO=SC.ID_USUARIO ");
		query.append(" JOIN INFRA.REL_PYMES_TRACTORAS RTR on PY.ID_USUARIO = RTR.ID_USUARIO_PYME "); 
		query.append(" JOIN INFRA.TRACTORAS as TR on RTR.ID_USUARIO_TRACTORA=TR.ID_USUARIO ");
		query.append(" WHERE B_DIPLOMADO_CCMX_DOS=TRUE ");
		if(filtros.getId()>-1){			
			query.append(" AND ");
			query.append(" "+filtros.getId()+" IN (TR.ID_USUARIO_PADRE,TR.ID_TRACTORA_PADRE,C.ID_CONSULTORA_PADRE,C.ID_USUARIO_PADRE) ");
			if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
				query.append(" and ");
			}
		}
		else {
			if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
				query.append(" AND ");
			}
		}
		if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
			if(filtros.getFiltro1() > 0){
				query.append(" TR.ID_USUARIO ="+filtros.getFiltro1()+" ");
				if(filtros.getFiltro2() != -1){
					query.append(" and ");
				}
			}			
			if(filtros.getFiltro2() > 0){
				query.append(" C.ID_USUARIO= "+filtros.getFiltro2()+" ");
			}
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
	public int getTEstrategia(Filtros filtros) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT count(*) as TOTAL ");
		query.append(" FROM  ");
		query.append(" INFRA.CONSULTORAS as C JOIN INFRA.CONSULTORIA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
		query.append(" JOIN INFRA.SERVICIOS_CONSULTORIA as SC ON SC.ID_CONSULTORIA=CI.ID_CONSULTORIA ");
		query.append(" JOIN INFRA.PYMES as PY ON PY.ID_USUARIO=SC.ID_USUARIO ");
		query.append(" JOIN INFRA.REL_PYMES_TRACTORAS RTR on PY.ID_USUARIO = RTR.ID_USUARIO_PYME "); 
		query.append(" JOIN INFRA.TRACTORAS as TR on RTR.ID_USUARIO_TRACTORA=TR.ID_USUARIO ");
		query.append(" WHERE B_DIPLOMADO_CCMX_TRES=TRUE ");
		if(filtros.getId()>-1){			
			query.append(" AND ");
			query.append(" "+filtros.getId()+" IN (TR.ID_USUARIO_PADRE,TR.ID_TRACTORA_PADRE,C.ID_CONSULTORA_PADRE,C.ID_USUARIO_PADRE) ");
			if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
				query.append(" and ");
			}
		}
		else {
			if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
				query.append(" AND ");
			}
		}
		if(filtros.getFiltro1() > 0 || filtros.getFiltro2() > 0){
			if(filtros.getFiltro1() > 0){
				query.append(" TR.ID_USUARIO ="+filtros.getFiltro1()+" ");
				if(filtros.getFiltro2() != -1){
					query.append(" and ");
				}
			}			
			if(filtros.getFiltro2() > 0){
				query.append(" C.ID_USUARIO= "+filtros.getFiltro2()+" ");
			}
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
	public List<TotalEmpresas> getEmpresasByConsultora(Filtros filtros) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT C.EMPRESA ,count(*) as TOTAL ");
		query.append("FROM ");							
		query.append(" INFRA.CONSULTORAS as C JOIN INFRA.CONSULTORIA as CI on C.ID_CONSULTORA=CI.ID_CONSULTORA");
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
}
