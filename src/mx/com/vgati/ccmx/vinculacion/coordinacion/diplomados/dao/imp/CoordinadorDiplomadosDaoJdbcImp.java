/*
 * CoordinadorDiplomadosDaoJdbcImp.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dao.imp;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dao.CoordinadorDiplomadosDao;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Encuestas;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Participantes;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Sesiones;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.framework.dao.AbstractBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class CoordinadorDiplomadosDaoJdbcImp extends AbstractBaseJdbcDao
		implements CoordinadorDiplomadosDao {

	@Override
	public List<Diplomados> getDiplomados(int id) throws DaoException {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PyMEs> getPymes() throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_USUARIO,NOMBRE_COMERCIAL  FROM INFRA.PYMES ;");
		log.debug("GetPymes()"+query);
		return getJdbcTemplate().query(query.toString(),
				new PymesRowMapper());
	}
	@SuppressWarnings("rawtypes")
	public class PymesRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			PymesResultSetExtractor extractor = new PymesResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class PymesResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			PyMEs py = new  PyMEs();
			py.setIdUsuario(rs.getInt("ID_USUARIO"));
			py.setNombreComercial(rs.getString("NOMBRE_COMERCIAL"));
			return py;
		}

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<List<Diplomados>> getMenuDiplomados(int year) throws DaoException {
		StringBuffer query;
		List<List<Diplomados>>  temp = new ArrayList<List<Diplomados>>();
		for (int i = 1; i < 5; i++) {
			query = new StringBuffer();
			query.append("SELECT ID_DIPLOMADO,TEMA,YEAR,GENERACION FROM  INFRA.DIPLOMADOS WHERE YEAR = ");
			if(year == 0){
				query.append("(SELECT YEAR FROM INFRA.DIPLOMADOS GROUP BY YEAR ORDER BY YEAR DESC LIMIT 1)");
			} else {
				query.append(year);
			}
			query.append(" AND GENERACION=");
			query.append(i);
			log.debug("getMenuDiplomados()" + query);
			temp.add(getJdbcTemplate().query(query.toString(),
					new MenuDiplomadosRowMapper()));
		}
		return temp;
	}
	@SuppressWarnings("rawtypes")
	public class MenuDiplomadosRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			MenuDiploamdosResultSetExtractor extractor = new MenuDiploamdosResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class MenuDiploamdosResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Diplomados d = new Diplomados();
			d.setIdDiplomado(rs.getInt("ID_DIPLOMADO"));
			d.setGeneracion(rs.getInt("GENERACION"));
			d.setYear(rs.getInt("YEAR"));
			d.setTema(rs.getString("TEMA"));
			return d;
		}

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getMenuAnios() throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT YEAR FROM INFRA.DIPLOMADOS GROUP BY YEAR ORDER BY YEAR DESC");
		log.debug("getMenuAnios()" + query);
		return getJdbcTemplate().query(query.toString(),
				new MenuAniosDiplomadosRowMapper());
	}
	@SuppressWarnings("rawtypes")
	public class MenuAniosDiplomadosRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			MenuAniosDiploamdosResultSetExtractor extractor = new MenuAniosDiploamdosResultSetExtractor();
			return extractor.extractData(rs);
		}

	}

	@SuppressWarnings("rawtypes")
	public class MenuAniosDiploamdosResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			return rs.getInt("YEAR");
		}

	}
	@SuppressWarnings("unchecked")
	@Override
	public Diplomados getDiplomado(String tema, int generacion) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_DIPLOMADO,GENERACION ");
		query.append(",TEMA FROM INFRA.DIPLOMADOS ");
		query.append("WHERE TEMA LIKE('%");
		query.append(tema);
		query.append("%') AND GENERACION =");
		query.append(generacion);
		log.debug("getDiplomado()"+query);
		List<Diplomados> diplomados = getJdbcTemplate().query(query.toString(),
				new DiplomadoRowMapper());
		if(diplomados!=null && !diplomados.isEmpty())
			return diplomados.get(0);
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public class DiplomadoRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			DiploamdoResultSetExtractor extractor = new  DiploamdoResultSetExtractor();
			return extractor.extractData(rs);
		}

	}
	@SuppressWarnings("rawtypes")
	public class DiploamdoResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Diplomados d = new Diplomados();
			d.setTema(rs.getString("TEMA"));
			d.setGeneracion(rs.getInt("GENERACION"));
			d.setIdDiplomado(rs.getInt("ID_DIPLOMADO"));
			return d;
		}

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Participantes> getParticipantes(int idDiplomado) throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_ASISTENTE");
 		query.append(",PY.ID_USUARIO");
 		query.append(",PY.NOMBRE_COMERCIAL ");
 		query.append(",A.TELEFONO");
 		query.append(", CONCAT(CONCAT(CONCAT(CONCAT(A.NOMBRE,' '),A.APP_PATERNO),' '),A.APP_MATERNO)  AS NOMBRE_ASISTENTE");
 		query.append(",A.CORREO_ELECTRONICO");
 		query.append(",A.CARGO");
 		query.append(",TR.EMPRESA");
 		query.append(",(SELECT ASISTENCIA FROM INFRA.SESIONES AS SES JOIN INFRA.ASISTENCIAS AS ASI ON SES.ID_SESION = ASI.ID_SESION WHERE  SES.ID_DIPLOMADO=D.ID_DIPLOMADO AND SESION = 1 AND ASI.ID_ASISTENTE=A.ID_ASISTENTE)  AS ASISTENCIA1");
 		query.append(",(SELECT ASISTENCIA FROM INFRA.SESIONES AS SES JOIN INFRA.ASISTENCIAS AS ASI ON SES.ID_SESION = ASI.ID_SESION WHERE  SES.ID_DIPLOMADO=D.ID_DIPLOMADO AND SESION = 2 AND ASI.ID_ASISTENTE=A.ID_ASISTENTE)  AS ASISTENCIA2");
 		query.append(",(SELECT ASISTENCIA FROM INFRA.SESIONES AS SES JOIN INFRA.ASISTENCIAS AS ASI ON SES.ID_SESION = ASI.ID_SESION WHERE  SES.ID_DIPLOMADO=D.ID_DIPLOMADO AND SESION = 3 AND ASI.ID_ASISTENTE=A.ID_ASISTENTE)  AS ASISTENCIA3");
 		query.append(",(SELECT ASISTENCIA FROM INFRA.SESIONES AS SES JOIN INFRA.ASISTENCIAS AS ASI ON SES.ID_SESION = ASI.ID_SESION WHERE  SES.ID_DIPLOMADO=D.ID_DIPLOMADO AND SESION = 4 AND ASI.ID_ASISTENTE=A.ID_ASISTENTE)  AS ASISTENCIA4");
 		query.append(",A.PAGO");
 		query.append(",A.FACTURA");
 		query.append(",A.NUMERO_PAGO");
 		query.append(" FROM");
 		query.append(" INFRA.PYMES PY");
 		query.append(" JOIN INFRA.SERVICIOS_DIPLOMADO AS SD ON SD.ID_USUARIO = PY.ID_USUARIO");
 		query.append(" JOIN INFRA.ASISTENTES AS A ON A.ID_SERVICIOS_DIPLOMADO  = SD.ID_SERVICIOS_DIPLOMADO ");
 		query.append(" JOIN INFRA.CONTACTOS AS CO ON CO.ID_USUARIO = PY.ID_USUARIO");
 		query.append(" JOIN  INFRA.REL_PYMES_TRACTORAS AS RPT ON PY.ID_USUARIO = RPT.ID_USUARIO_PYME");
 		query.append(" JOIN INFRA.TRACTORAS AS TR ON TR.ID_USUARIO = RPT.ID_USUARIO_TRACTORA");
 		query.append(" JOIN INFRA.DIPLOMADOS AS D ON D.ID_DIPLOMADO = SD.ID_DIPLOMADO");
 		query.append(" WHERE CO.TIPO LIKE('%Ventas%')  AND ID_TRACTORA_PADRE=0");
 		query.append(" AND D.ID_DIPLOMADO =");
 		query.append(idDiplomado);
 		query.append(" ORDER BY PY.ID_USUARIO");
 		log.debug("getParticipantes()" + query);
 		return getJdbcTemplate().query(query.toString(),
				new DiplomadosRowMapper());
	}
	@SuppressWarnings("rawtypes")
	public class DiplomadosRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			DiploamdosResultSetExtractor extractor = new DiploamdosResultSetExtractor();
			return extractor.extractData(rs);
		}

	}
	@SuppressWarnings("rawtypes")
	public class DiploamdosResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Participantes p = new Participantes();
			p.setId(rs.getInt("ID_ASISTENTE"));
			p.setIdUsuario(rs.getInt("ID_USUARIO"));
			p.setPyme(rs.getString("NOMBRE_COMERCIAL"));
			p.setTelefono(rs.getString("TELEFONO"));
			p.setNombre(rs.getString("NOMBRE_ASISTENTE"));
			p.setCorreoElectronico(rs.getString("CORREO_ELECTRONICO"));
			p.setCargo(rs.getString("CARGO"));
			p.setAsistencia1(rs.getBoolean("ASISTENCIA1"));
			p.setAsistencia2(rs.getBoolean("ASISTENCIA2"));
			p.setAsistencia3(rs.getBoolean("ASISTENCIA3"));
			p.setAsistencia4(rs.getBoolean("ASISTENCIA4"));
			p.setPago(rs.getBoolean("PAGO"));
			p.setFactura(rs.getBoolean("FACTURA"));
			p.setTractora(rs.getString("EMPRESA"));
			p.setNumPago(rs.getString("NUMERO_PAGO"));
			return p;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Participantes> getParticipantes(int idDiplomado,int idPyme) throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_ASISTENTE");
 		query.append(",PY.ID_USUARIO");
 		query.append(",PY.NOMBRE_COMERCIAL ");
 		query.append(",A.TELEFONO");
 		query.append(",CONCAT(CONCAT(CONCAT(CONCAT(A.NOMBRE,' '),A.APP_PATERNO),' '),A.APP_MATERNO)  AS NOMBRE_ASISTENTE");
 		query.append(",A.CORREO_ELECTRONICO");
 		query.append(",A.CARGO");
 		query.append(",TR.EMPRESA");
 		query.append(",(SELECT ASISTENCIA FROM INFRA.SESIONES AS SES JOIN INFRA.ASISTENCIAS AS ASI ON SES.ID_SESION = ASI.ID_SESION WHERE  SES.ID_DIPLOMADO=D.ID_DIPLOMADO AND SESION = 1 AND ASI.ID_ASISTENTE=A.ID_ASISTENTE)  AS ASISTENCIA1");
 		query.append(",(SELECT ASISTENCIA FROM INFRA.SESIONES AS SES JOIN INFRA.ASISTENCIAS AS ASI ON SES.ID_SESION = ASI.ID_SESION WHERE  SES.ID_DIPLOMADO=D.ID_DIPLOMADO AND SESION = 2 AND ASI.ID_ASISTENTE=A.ID_ASISTENTE)  AS ASISTENCIA2");
 		query.append(",(SELECT ASISTENCIA FROM INFRA.SESIONES AS SES JOIN INFRA.ASISTENCIAS AS ASI ON SES.ID_SESION = ASI.ID_SESION WHERE  SES.ID_DIPLOMADO=D.ID_DIPLOMADO AND SESION = 3 AND ASI.ID_ASISTENTE=A.ID_ASISTENTE)  AS ASISTENCIA3");
 		query.append(",(SELECT ASISTENCIA FROM INFRA.SESIONES AS SES JOIN INFRA.ASISTENCIAS AS ASI ON SES.ID_SESION = ASI.ID_SESION WHERE  SES.ID_DIPLOMADO=D.ID_DIPLOMADO AND SESION = 4 AND ASI.ID_ASISTENTE=A.ID_ASISTENTE)  AS ASISTENCIA4");
 		query.append(",CASE WHEN (SELECT ID_DIPLOMADO FROM INFRA.SESIONES WHERE ID_DIPLOMADO=D.ID_DIPLOMADO AND SESION=1)>0 THEN 'TRUE' ELSE 'FALSE' END AS EDITABLE1");
 		query.append(",CASE WHEN (SELECT ID_DIPLOMADO FROM INFRA.SESIONES WHERE ID_DIPLOMADO=D.ID_DIPLOMADO AND SESION=2)>0 THEN 'TRUE' ELSE 'FALSE' END AS EDITABLE2");
 		query.append(",CASE WHEN (SELECT ID_DIPLOMADO FROM INFRA.SESIONES WHERE ID_DIPLOMADO=D.ID_DIPLOMADO AND SESION=3)>0 THEN 'TRUE' ELSE 'FALSE' END AS EDITABLE3");
 		query.append(",CASE WHEN (SELECT ID_DIPLOMADO FROM INFRA.SESIONES WHERE ID_DIPLOMADO=D.ID_DIPLOMADO AND SESION=4)>0 THEN 'TRUE' ELSE 'FALSE' END AS EDITABLE4");
 		query.append(",A.PAGO");
 		query.append(",A.FACTURA");
 		query.append(",A.NUMERO_PAGO");
		query.append(",A.C1");
		query.append(",A.C2");
		query.append(",A.C3");
		query.append(",A.C4");
 		query.append(" FROM");
 		query.append(" INFRA.PYMES PY");
 		query.append(" JOIN INFRA.SERVICIOS_DIPLOMADO AS SD ON SD.ID_USUARIO = PY.ID_USUARIO");
 		query.append(" JOIN INFRA.ASISTENTES AS A ON A.ID_SERVICIOS_DIPLOMADO  = SD.ID_SERVICIOS_DIPLOMADO ");
 		query.append(" JOIN INFRA.CONTACTOS AS CO ON CO.ID_USUARIO = PY.ID_USUARIO");
 		query.append(" JOIN  INFRA.REL_PYMES_TRACTORAS AS RPT ON PY.ID_USUARIO = RPT.ID_USUARIO_PYME");
 		query.append(" JOIN INFRA.TRACTORAS AS TR ON TR.ID_USUARIO = RPT.ID_USUARIO_TRACTORA");
 		query.append(" JOIN INFRA.DIPLOMADOS AS D ON D.ID_DIPLOMADO = SD.ID_DIPLOMADO");
 		query.append(" WHERE CO.TIPO LIKE('%Ventas%')  AND ID_TRACTORA_PADRE=0");
 		query.append(" AND D.ID_DIPLOMADO =");
 		query.append(idDiplomado);
 		query.append(" AND PY.ID_USUARIO =");
 		query.append(idPyme);
 		query.append(" ORDER BY PY.ID_USUARIO");
 		log.debug("getParticipantes() por Pyme" + query);
 		return getJdbcTemplate().query(query.toString(),
				new DiplomadosPymeRowMapper());
	}
	@SuppressWarnings("rawtypes")
	public class DiplomadosPymeRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			DiploamdosPymeResultSetExtractor extractor = new DiploamdosPymeResultSetExtractor();
			return extractor.extractData(rs);
		}

	}
	@SuppressWarnings("rawtypes")
	public class DiploamdosPymeResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Participantes p = new Participantes();
			p.setId(rs.getInt("ID_ASISTENTE"));
			p.setIdUsuario(rs.getInt("ID_USUARIO"));
			p.setPyme(rs.getString("NOMBRE_COMERCIAL"));
			p.setTelefono(rs.getString("TELEFONO"));
			p.setNombre(rs.getString("NOMBRE_ASISTENTE"));
			p.setCorreoElectronico(rs.getString("CORREO_ELECTRONICO"));
			p.setCargo(rs.getString("CARGO"));
			p.setAsistencia1(rs.getBoolean("ASISTENCIA1"));
			p.setAsistencia2(rs.getBoolean("ASISTENCIA2"));
			p.setAsistencia3(rs.getBoolean("ASISTENCIA3"));
			p.setAsistencia4(rs.getBoolean("ASISTENCIA4"));
			p.setPago(rs.getBoolean("PAGO"));
			p.setFactura(rs.getBoolean("FACTURA"));
			p.setTractora(rs.getString("EMPRESA"));
			p.setNumPago(rs.getString("NUMERO_PAGO"));
			p.setEditable1(rs.getBoolean("EDITABLE1"));
			p.setEditable2(rs.getBoolean("EDITABLE2"));
			p.setEditable3(rs.getBoolean("EDITABLE3"));
			p.setEditable4(rs.getBoolean("EDITABLE4"));
			p.setConfirmado1(rs.getBoolean("C1"));
			p.setConfirmado2(rs.getBoolean("C2"));
			p.setConfirmado3(rs.getBoolean("C3"));
			p.setConfirmado4(rs.getBoolean("C4"));
			return p;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Participantes> getParticipantesDiploma(int idDiplomado, int idPyme)throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("SELECT D.TEMA");
		query.append(",CONCAT(CONCAT(CONCAT(CONCAT(A.NOMBRE,' '),A.APP_PATERNO),' '),A.APP_MATERNO)  AS NOMBRE_ASISTENTE");
		query.append(" FROM INFRA.SERVICIOS_DIPLOMADO SD ");
 		query.append(" JOIN INFRA.ASISTENTES AS A ON A.ID_SERVICIOS_DIPLOMADO  = SD.ID_SERVICIOS_DIPLOMADO ");
 		query.append(" JOIN INFRA.DIPLOMADOS AS D ON D.ID_DIPLOMADO = SD.ID_DIPLOMADO");
 		query.append(" WHERE SD.ID_DIPLOMADO=");
 		query.append(idDiplomado);
 		query.append(" AND SD.ID_USUARIO=");
 		query.append(idPyme);
 		query.append(" AND EXISTS(SELECT ID_DIPLOMADO FROM INFRA.SESIONES WHERE ID_DIPLOMADO=D.ID_DIPLOMADO AND SESION=2 AND CAST(FECHA AS DATE)<CURRENT_DATE)");
		log.debug("getParticipantes() por Pyme" + query);
 		return getJdbcTemplate().query(query.toString(),
				new DiplomasRowMapper());
	}
	@SuppressWarnings("rawtypes")
	public class DiplomasRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			DiplomasResultSetExtractor extractor = new DiplomasResultSetExtractor();
			return extractor.extractData(rs);
		}

	}
	@SuppressWarnings("rawtypes")
	public class DiplomasResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Participantes p = new Participantes();
			p.setTema(rs.getString("TEMA"));
			p.setNombre(rs.getString("NOMBRE_ASISTENTE"));
			return p;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Sesiones> getSesiones(int idDiplomado) throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_SESION ");
		query.append(",ID_DIPLOMADO");
		query.append(",EXPOSITOR");
		query.append(",SALA");
		query.append(",FECHA");
		query.append(",INSTRUCTOR");
		query.append(",INFORMACION");
		query.append(",ID_DOMICILIO");
		query.append(",SESION");
		query.append(",HOUR(FECHA) as HORA"); 
		query.append(",HORA_FIN");
		query.append(",MINUTO_FIN");
		query.append(",MINUTE(FECHA) as MINUTO"); 
		query.append(",CAST(FECHA AS DATE) as FECHA_INI "); 
		query.append(" FROM INFRA.SESIONES ");
		query.append(" WHERE ID_DIPLOMADO =");
		query.append(idDiplomado);
		query.append(" ORDER BY SESION");
		log.debug("getSesiones() " + query);
		List<Sesiones> sesiones = getJdbcTemplate().query(query.toString(),
				new SesionesRowMapper());
		return sesiones;
	}
	@SuppressWarnings("rawtypes")
	public class SesionesRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int ln) throws SQLException {
			SesionesResultSetExtractor extractor = new SesionesResultSetExtractor();
			return extractor.extractData(rs);
		}

	}
	@SuppressWarnings("rawtypes")
	public class SesionesResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Sesiones ses = new Sesiones();
			ses.setIdSesion(rs.getInt("ID_SESION"));
			ses.setIdDiplomado(rs.getInt("ID_DIPLOMADO"));
			ses.setExpositor(rs.getString("EXPOSITOR"));
			ses.setSala(rs.getString("SALA"));
			ses.setFecha(rs.getDate("FECHA_INI"));
			ses.setHora(rs.getInt("HORA"));
			ses.setMinuto(rs.getInt("MINUTO"));
			ses.setInstructor(rs.getString("INSTRUCTOR"));
			ses.setInfo(rs.getString("INFORMACION"));
			ses.setSesion(rs.getInt("SESION"));
			ses.setHoraFin(rs.getInt("HORA_FIN"));
			ses.setMinuto(rs.getInt("MINUTO_FIN"));
			if(rs.getInt("ID_DOMICILIO")>0){
				ses.setDomicilios(getDomicilios(rs.getInt("ID_DOMICILIO")));
			}else {
				ses.setDomicilios(null);
			}
			
			return ses;
		}

	}
	
	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings("deprecation")
	@Override
	public Mensaje saveSesiones(List<Sesiones> sesiones, int numeroSesiones)
		throws DaoException{
		for(int i = 0; i< numeroSesiones ; i++){
			int idDireccion = 0;
			if(sesiones.get(i).getDomicilios()!=null &&
					sesiones.get(i).getDomicilios().getIdDomicilio()>0){
				if(! updateDomicilios(sesiones.get(i).getDomicilios())){
					return new Mensaje(1,"Error al guardar sesiones, intentelo mas tarde.");
				}
				idDireccion = sesiones.get(i).getDomicilios().getIdDomicilio();
			} else if(sesiones.get(i).getDomicilios()!=null &&
					sesiones.get(i).getDomicilios().getCalle()!=null){
				idDireccion= insertDomicilios(sesiones.get(i).getDomicilios());
				if(idDireccion == 0){
					return new Mensaje(1,"Error al guardar sesiones, intentelo mas tarde.");
				}
			}
			StringBuffer query = new StringBuffer();
			Timestamp fecha = new Timestamp(sesiones.get(i).getFecha().getYear(), sesiones.get(i).getFecha().getMonth(), 
					sesiones.get(i).getFecha().getDay(), sesiones.get(i).getHora(), sesiones.get(i).getMinuto(), 0, 0);
			if(sesiones!= null && sesiones.get(i).getIdSesion()>0){
				query.append("UPDATE INFRA.SESIONES ");
				query.append(" SET ID_DIPLOMADO = ");
				query.append(sesiones.get(i).getIdDiplomado());
				query.append(" ,EXPOSITOR = '");
				query.append(sesiones.get(i).getExpositor());
				query.append("' ");
				query.append(" ,SALA = '");
				query.append(sesiones.get(i).getSala());
				query.append("' ");
				query.append(" ,FECHA = '");
				query.append(fecha);
				query.append("' ");
				query.append(", HORA_FIN = ");
				query.append(sesiones.get(i).getHoraFin());
				query.append(", MINUTO_FIN = ");
				query.append(sesiones.get(i).getMinutoFin());
				query.append(" ,INSTRUCTOR = '");
				query.append(sesiones.get(i).getInstructor());
				query.append("' ");
				query.append(" ,INFORMACION = '");
				query.append(sesiones.get(i).getInfo());
				query.append("' ");
				query.append(" ,ID_DOMICILIO = ");
				query.append(idDireccion);
				query.append(" ,SESION = ");
				query.append( i+1 );
				query.append(" WHERE ID_SESION = ");
				query.append( sesiones.get(i).getIdSesion());
				log.debug("saveSesiones() Actualiza" + query);
				try {
					getJdbcTemplate().update(query.toString());
				} catch (Exception e) {
					log.fatal("ERROR al actualizar el producto, " + e);
					return new Mensaje(1,
							"No es posible actualizar el producto, intentelo más tarde.");
				}
			} else if(sesiones!= null){
				query.append("INSERT INTO INFRA.SESIONES (");
				query.append(" ID_DIPLOMADO");
				query.append(",EXPOSITOR ,SALA");
				query.append(",FECHA ,INSTRUCTOR");
				query.append(",INFORMACION ,ID_DOMICILIO");
				query.append(",SESION,HORA_FIN,MINUTO_FIN) VALUES (");
				query.append(sesiones.get(i).getIdDiplomado());
				query.append(",'");
				query.append(sesiones.get(i).getExpositor());
				query.append("','");
				query.append(sesiones.get(i).getSala());
				query.append("','");
				query.append(fecha);
				query.append("','");
				query.append(sesiones.get(i).getInstructor());
				query.append("','");
				query.append(sesiones.get(i).getInfo());
				query.append("',");
				query.append(idDireccion);
				query.append(",");
				query.append(i+1);
				query.append(",");
				query.append(sesiones.get(i).getHoraFin());
				query.append(",");
				query.append(sesiones.get(i).getMinutoFin());
				query.append(")");
				log.debug("saveSesiones() Inserta query=" + query);
				try {
					getJdbcTemplate().update(query.toString());
				} catch (Exception e) {
					log.fatal("Error al guardar los cambios, " + e);
					return new Mensaje(1,
							"No es posible registrar los datos de las sesiones, intentelo más tarde.");
				}
			}
		}
		for(int i = numeroSesiones ; i<sesiones.size(); i++){
			if(sesiones.get(i).getIdSesion()>0){
				StringBuffer query = new StringBuffer();
				int idSesion = sesiones.get(i).getIdSesion();
				if(idSesion>0){
					query.append("DELETE FROM INFRA.ASISTENCIAS WHERE ID_SESION =");
					query.append(idSesion);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("Error al guardar los cambios, " + e);
						return new Mensaje(1,
								"No es posible borrar los datos de las sesiones, intentelo más tarde.");
					}
				}
				query = new StringBuffer();
				query.append("DELETE FROM INFRA.SESIONES WHERE ID_SESION=");
				query.append(sesiones.get(i).getIdSesion());
				try {
					getJdbcTemplate().update(query.toString());
				} catch (Exception e) {
					log.fatal("Error al guardar los cambios, " + e);
					return new Mensaje(1,
							"No es posible borrar los datos de las sesiones, intentelo más tarde.");
				}
			}
			if(sesiones.get(i).getDomicilios().getIdDomicilio()>0){
				StringBuffer query = new StringBuffer();
				query.append("DELETE FROM INFRA.DOMICILIOS WHERE ID_DOMICILIO=");
				query.append(sesiones.get(i).getDomicilios().getIdDomicilio());
				try {
					getJdbcTemplate().update(query.toString());
				} catch (Exception e) {
					log.fatal("Error al guardar los cambios, " + e);
					return new Mensaje(1,
							"No es posible borrar los datos de las sesiones, intentelo más tarde.");
				}
			}
		}
		return new Mensaje(0,
		"Los datos de las sesiones han sido guardados exitosamente.");
	}
	
	
	public boolean updateDomicilios(Domicilios domicilios)
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
			return true;
		} catch (Exception e) {
			log.fatal("ERROR al actualizar los datos de la Tractora, " + e);
			return false;
		}

	}
	public int insertDomicilios(Domicilios domicilios)
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
			return getIdDomicilio().getIdDomicilio();
		} catch (Exception e) {
			log.fatal("ERROR al insertar los datos de Domicilio, " + e);
			return 0;
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
	@Override
	public Mensaje saveParticipantes(List<Participantes> participantes, int idPyme, int idDiplomado)
			throws DaoException{
		StringBuffer query;
		for(int i=0;i<participantes.size();i++){
			int idSesion = getIdSesion(1, idDiplomado);
			if(idSesion>0){
				if(hasRegistroSesion(participantes.get(i).getId(), idSesion)){
					query = new StringBuffer(); 
					query.append("UPDATE INFRA.ASISTENCIAS SET ASISTENCIA=");
					query.append(participantes.get(i).isAsistencia1());
					query.append(" WHERE ID_ASISTENTE=");
					query.append(participantes.get(i).getId());
					query.append(" AND ID_SESION=");
					query.append(idSesion);
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("Error al guardar los cambios, " + e);
						return new Mensaje(1,
								"No es posible guardar los cambios realizados," +
								" intentelo más tarde.");
					}
				}
				else {
					query = new StringBuffer(); 
					query.append("INSERT INTO INFRA.ASISTENCIAS(ID_SESION,ID_ASISTENTE,ASISTENCIA)VALUES(");
					query.append(idSesion);
					query.append(",");
					query.append(participantes.get(i).getId());
					query.append(",");
					query.append(participantes.get(i).isAsistencia1());
					query.append(")");
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("Error al guardar los cambios, " + e);
						return new Mensaje(1,
								"No es posible guardar los cambios realizados," +
								" intentelo más tarde.");
					}
				}
			}
			idSesion = getIdSesion(2, idDiplomado);
			if(idSesion>0){
				if(hasRegistroSesion(participantes.get(i).getId(), idSesion)){
					query = new StringBuffer(); 
					query.append("UPDATE INFRA.ASISTENCIAS SET ASISTENCIA=");
					query.append(participantes.get(i).isAsistencia2());
					query.append(" WHERE ID_ASISTENTE=");
					query.append(participantes.get(i).getId());
					query.append(" AND ID_SESION=");
					query.append(idSesion);
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("Error al guardar los cambios, " + e);
						return new Mensaje(1,
								"No es posible guardar los cambios realizados," +
								" intentelo más tarde.");
					}
				}
				else {
					query = new StringBuffer(); 
					query.append("INSERT INTO INFRA.ASISTENCIAS(ID_SESION,ID_ASISTENTE,ASISTENCIA)VALUES(");
					query.append(idSesion);
					query.append(",");
					query.append(participantes.get(i).getId());
					query.append(",");
					query.append(participantes.get(i).isAsistencia2());
					query.append(")");
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("Error al guardar los cambios, " + e);
						return new Mensaje(1,
								"No es posible guardar los cambios realizados," +
								" intentelo más tarde.");
					}
				}
			}
			idSesion = getIdSesion(2, idDiplomado);
			if(idSesion>0){
				if(hasRegistroSesion(participantes.get(i).getId(), idSesion)){
					query = new StringBuffer(); 
					query.append("UPDATE INFRA.ASISTENCIAS SET ASISTENCIA=");
					query.append(participantes.get(i).isAsistencia2());
					query.append(" WHERE ID_ASISTENTE=");
					query.append(participantes.get(i).getId());
					query.append(" AND ID_SESION=");
					query.append(idSesion);
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("Error al guardar los cambios, " + e);
						return new Mensaje(1,
								"No es posible guardar los cambios realizados," +
								" intentelo más tarde.");
					}
				}
				else {
					query = new StringBuffer(); 
					query.append("INSERT INTO INFRA.ASISTENCIAS(ID_SESION,ID_ASISTENTE,ASISTENCIA)VALUES(");
					query.append(idSesion);
					query.append(",");
					query.append(participantes.get(i).getId());
					query.append(",");
					query.append(participantes.get(i).isAsistencia2());
					query.append(")");
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("Error al guardar los cambios, " + e);
						return new Mensaje(1,
								"No es posible guardar los cambios realizados," +
								" intentelo más tarde.");
					}
				}
			}
			idSesion = getIdSesion(3, idDiplomado);
			if(idSesion>0){
				if(hasRegistroSesion(participantes.get(i).getId(), idSesion)){
					query = new StringBuffer(); 
					query.append("UPDATE INFRA.ASISTENCIAS SET ASISTENCIA=");
					query.append(participantes.get(i).isAsistencia3());
					query.append(" WHERE ID_ASISTENTE=");
					query.append(participantes.get(i).getId());
					query.append(" AND ID_SESION=");
					query.append(idSesion);
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("Error al guardar los cambios, " + e);
						return new Mensaje(1,
								"No es posible guardar los cambios realizados," +
								" intentelo más tarde.");
					}
				}
				else {
					query = new StringBuffer(); 
					query.append("INSERT INTO INFRA.ASISTENCIAS(ID_SESION,ID_ASISTENTE,ASISTENCIA)VALUES(");
					query.append(idSesion);
					query.append(",");
					query.append(participantes.get(i).getId());
					query.append(",");
					query.append(participantes.get(i).isAsistencia3());
					query.append(")");
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("Error al guardar los cambios, " + e);
						return new Mensaje(1,
								"No es posible guardar los cambios realizados," +
								" intentelo más tarde.");
					}
				}
			}
			idSesion = getIdSesion(4, idDiplomado);
			if(idSesion>0){
				if(hasRegistroSesion(participantes.get(i).getId(), idSesion)){
					query = new StringBuffer(); 
					query.append("UPDATE INFRA.ASISTENCIAS SET ASISTENCIA=");
					query.append(participantes.get(i).isAsistencia4());
					query.append(" WHERE ID_ASISTENTE=");
					query.append(participantes.get(i).getId());
					query.append(" AND ID_SESION=");
					query.append(idSesion);
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("Error al guardar los cambios, " + e);
						return new Mensaje(1,
								"No es posible guardar los cambios realizados," +
								" intentelo más tarde.");
					}
				}
				else {
					query = new StringBuffer(); 
					query.append("INSERT INTO INFRA.ASISTENCIAS(ID_SESION,ID_ASISTENTE,ASISTENCIA)VALUES(");
					query.append(idSesion);
					query.append(",");
					query.append(participantes.get(i).getId());
					query.append(",");
					query.append(participantes.get(i).isAsistencia4());
					query.append(")");
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("Error al guardar los cambios, " + e);
						return new Mensaje(1,
								"No es posible guardar los cambios realizados," +
								" intentelo más tarde.");
					}
				}
			}
			
			query = new StringBuffer();
			query.append("UPDATE INFRA.ASISTENTES SET ");
			query.append(" C1 =");
			query.append(participantes.get(i).isConfirmado1());
			query.append(", C2 =");
			query.append(participantes.get(i).isConfirmado2());
			query.append(", C3 =");
			query.append(participantes.get(i).isConfirmado3());
			query.append(", C4 =");
			query.append(participantes.get(i).isConfirmado4());
			query.append(", FACTURA =");
			query.append(participantes.get(i).isFactura());
			query.append(" ,PAGO = ");
			query.append(participantes.get(i).isPago());
			query.append(" ,NUMERO_PAGO='");
			query.append(participantes.get(i).getNumPago());
			query.append("' WHERE ID_ASISTENTE = ");
			query.append(participantes.get(i).getId());
			log.debug("Actualizando asistente " + query);
			try {
				getJdbcTemplate().update(query.toString());
			} catch (Exception e) {
				log.fatal("Error al guardar los cambios, " + e);
				return new Mensaje(1,
						"No es posible guardar los cambios realizados," +
						" intentelo más tarde.");
			}
		}
		return new Mensaje(0,"Datos de la PYME guardados correctamente");
	}
	@SuppressWarnings({ "unchecked"})
	private boolean hasRegistroSesion(int idAsistente,int idSesion){
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_ASISTENTE  FROM INFRA.ASISTENCIAS WHERE ID_ASISTENTE =");
		query.append(idAsistente);
		query.append(" AND ID_SESION =");
		query.append(idSesion);		
		List<Integer> list = getJdbcTemplate().query(query.toString(),
				new AsistenteRowMapper());
		if(list!=null && list.size()>0){
			return true;
		}
		else{
			return false;
		}
	}
	@SuppressWarnings("rawtypes")
	public class AsistenteRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			AsistenteExtractor extractor = new AsistenteExtractor();
			return extractor.extractData(rs);
		}
	}
	@SuppressWarnings("rawtypes")
	public class AsistenteExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			return rs.getInt("ID_ASISTENTE");
		}
		
	}
	@SuppressWarnings({ "unchecked"})
	private int getIdSesion(int numSesion,int idDiplomado){
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID_SESION FROM INFRA.SESIONES WHERE SESION =");
		query.append(numSesion);
		query.append(" AND ID_DIPLOMADO =");
		query.append(idDiplomado);		
		List<Integer> list = getJdbcTemplate().query(query.toString(),
				new IdSesionRowMapper());
		if(list!=null && list.size()>0){
			return list.get(list.size()-1);
		}
		else{
			return 0;
		}
	}
	@SuppressWarnings("rawtypes")
	public class IdSesionRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IdSesionExtractor extractor = new IdSesionExtractor();
			return extractor.extractData(rs);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public class IdSesionExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			return rs.getInt("ID_SESION");
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Participantes> getInasistentes(int idDiplomado,int idPyme) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT DISTINCT(A.ID_ASISTENTE)");
		query.append(",PY.NOMBRE_COMERCIAL");
		query.append(",SD.ID_USUARIO");
		query.append(",S.ID_SESION");
		query.append(",SESION");
		query.append(",FECHA");
		query.append(", CONCAT(CONCAT(CONCAT(CONCAT(A.NOMBRE,' '),A.APP_PATERNO),' '),A.APP_MATERNO)  AS"); 
		query.append(" NOMBRE_ASISTENTE ");
		query.append(",A.CORREO_ELECTRONICO");
		query.append(",A.TELEFONO");
		query.append(",S.SESION");
		query.append(",D.TEMA");
		query.append(",D.GENERACION");
		query.append(",D.TEMA");
		query.append(" FROM INFRA.ASISTENTES A"); 
		query.append(" JOIN INFRA.SERVICIOS_DIPLOMADO SD ON SD.ID_SERVICIOS_DIPLOMADO=A.ID_SERVICIOS_DIPLOMADO"); 
		query.append(" JOIN INFRA.SESIONES S ON SD.ID_DIPLOMADO = S.ID_DIPLOMADO");  
		query.append(" JOIN INFRA.DIPLOMADOS D ON SD.ID_DIPLOMADO = D.ID_DIPLOMADO ");
		query.append(" JOIN INFRA.PYMES PY ON PY.ID_USUARIO=SD.ID_USUARIO");
		query.append(" WHERE SD.ID_USUARIO=");
		query.append(idPyme);
		query.append(" AND D.ID_DIPLOMADO=");
		query.append(" (SELECT ID_DIPLOMADO  FROM INFRA.DIPLOMADOS WHERE ID_DIPLOMADO"); 
		query.append(" IN  (SELECT ID_DIPLOMADO  FROM INFRA.DIPLOMADOS WHERE GENERACION = ");
		query.append(" CASE  WHEN  (SELECT GENERACION FROM INFRA.DIPLOMADOS WHERE ID_DIPLOMADO=");
		query.append(idDiplomado);
		query.append(")=1  THEN 4 ELSE  (SELECT GENERACION FROM INFRA.DIPLOMADOS WHERE ID_DIPLOMADO=");
		query.append(idDiplomado);
		query.append(")-1 END");
		query.append(" AND  YEAR =  ");
		query.append(" CASE  WHEN  (SELECT GENERACION FROM INFRA.DIPLOMADOS WHERE ID_DIPLOMADO=");
		query.append(idDiplomado);
		query.append(")=1 ");
		query.append(" THEN   (SELECT YEAR FROM INFRA.DIPLOMADOS WHERE ID_DIPLOMADO=");
		query.append(idDiplomado);
		query.append(")-1 ");
		query.append(" ELSE  (SELECT GENERACION FROM INFRA.DIPLOMADOS  WHERE ID_DIPLOMADO=");
		query.append(idDiplomado);
		query.append(") END)"); 
		query.append(" AND TEMA = SELECT TEMA FROM INFRA.DIPLOMADOS WHERE ID_DIPLOMADO=");
		query.append(idDiplomado);
		query.append(" LIMIT 1)");
		query.append(" AND  NOT EXISTS(");
		query.append(" SELECT us.id_sesion FROM INFRA.DIPLOMADOS UD");
		query.append(" JOIN INFRA.SERVICIOS_DIPLOMADO USD ON UD.ID_DIPLOMADO=USD.ID_DIPLOMADO");
		query.append(" JOIN INFRA.SESIONES US ON US.ID_DIPLOMADO=USD.ID_DIPLOMADO");
		query.append(" LEFT JOIN INFRA.ASISTENCIAS USI ON USI.ID_SESION=US.ID_SESION");
		query.append(" WHERE UD.TEMA = D.TEMA");
		query.append(" AND US.SESION=S.SESION");
		query.append(" AND USI.ASISTENCIA=TRUE");
		query.append(" AND USI.ID_ASISTENTE=A.ID_ASISTENTE");
		query.append(" LIMIT 1");
		query.append(")");
		query.append("ORDER BY A.ID_ASISTENTE,S.ID_SESION,S.SESION");
		log.debug("getInasistentes() " + query);
		
		return getJdbcTemplate().query(query.toString(),
				new InasistentesnRowMapper());
	}
	@SuppressWarnings("rawtypes")
	public class InasistentesnRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			InasistentesExtractor extractor = new InasistentesExtractor();
			return extractor.extractData(rs);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public class InasistentesExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Participantes p = new Participantes();
			p.setId(rs.getInt("ID_ASISTENTE"));
			p.setNombre(rs.getString("NOMBRE_ASISTENTE"));
			p.setIdUsuario(rs.getInt("ID_USUARIO"));
			p.setCorreoElectronico(rs.getString("CORREO_ELECTRONICO"));
			p.setTelefono(rs.getString("TELEFONO"));
			p.setSesion(rs.getInt("SESION"));
			p.setTema(rs.getString("TEMA"));
			p.setGeneracion(rs.getInt("GENERACION"));
			p.setPyme(rs.getString("NOMBRE_COMERCIAL"));
			return p;
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public Encuestas getEncuestas(int idAsistente) throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("ID_ASISTENTE");
		query.append(",RESPUESTA1");
		query.append(",RESPUESTA2");
		query.append(",RESPUESTA3");
		query.append(",RESPUESTA4");
		query.append(",RESPUESTA5");
		query.append(",RESPUESTA6");
		query.append(",RESPUESTA7");
		query.append(",RESPUESTA8");
		query.append(",RESPUESTA8");
		query.append(",RETROALIMENTACION");
		query.append(" FROM INFRA.ENCUESTAS");
		query.append(" WHERE ID_ASISTENTE=");
		query.append(idAsistente);
		log.debug("getEncuestas() "+ query);
		List<Encuestas> en=getJdbcTemplate().query(query.toString(),
				new EncuestasRowMapper());
		if(en!=null && en.size()>0){
			return en.get(0);
		} else {
			return null;
		}
	}
	@SuppressWarnings("rawtypes")
	public class EncuestasRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			EncuestasExtractor extractor = new EncuestasExtractor();
			return extractor.extractData(rs);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public class EncuestasExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			Encuestas en = new Encuestas();
			en.setIdAsistente(rs.getInt("ID_ASISTENTE"));
			en.setRespuesta1(rs.getFloat("RESPUESTA1"));
			en.setRespuesta2(rs.getFloat("RESPUESTA2"));
			en.setRespuesta3(rs.getString("RESPUESTA3"));
			en.setRespuesta4(rs.getString("RESPUESTA5"));
			en.setRespuesta6(rs.getFloat("RESPUESTA6"));
			en.setRespuesta5(rs.getFloat("RESPUESTA5"));
			en.setRespuesta7(rs.getString("RESPUESTA3"));
			en.setRespuesta8(rs.getString("RESPUESTA5"));
			en.setRespuesta9(rs.getString("RESPUESTA3"));
			en.setRetroalimenacion(rs.getString("RETROALIMENTACION"));
			return en;
		}
		
	}
	public Mensaje saveEncuestas(Encuestas encuestas) throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO INFRA.ENCUESTAS(ID_ASISTENTE)");
		query.append(" SELECT * FROM (SELECT ");
		query.append(encuestas.getIdAsistente());
		query.append(" WHERE NOT EXISTS (SELECT ID_ASISTENTE FROM ");
		query.append("INFRA.ENCUESTAS WHERE ID_ASISTENTE=");
		query.append(encuestas.getIdAsistente());
		query.append(" LIMIT 1))");
		log.debug("saveEncuestas() Insertando asistente " + query);
		try {
			getJdbcTemplate().update(query.toString());
		} catch (Exception e) {
			log.fatal("Error al guardar los cambios, " + e);
			return new Mensaje(1,
					"No es posible guardar los cambios realizados," +
					" intentelo más tarde.");
		}
		query = new StringBuffer();
		query.append("UPDATE INFRA.ENCUESTAS SET ");
		query.append("RESPUESTA1=");
		query.append(encuestas.getRespuesta1());
		query.append(",RESPUESTA2=");
		query.append(encuestas.getRespuesta2());
		query.append(",RESPUESTA3='");
		query.append(encuestas.getRespuesta3());
		query.append("',RESPUESTA4='");
		query.append(encuestas.getRespuesta4());
		query.append("',RESPUESTA5=");
		query.append(encuestas.getRespuesta5());
		query.append(",RESPUESTA6=");
		query.append(encuestas.getRespuesta6());
		query.append(",RESPUESTA7='");
		query.append(encuestas.getRespuesta7());
		query.append("',RESPUESTA8='");
		query.append(encuestas.getRespuesta8());
		query.append("',RESPUESTA9='");
		query.append(encuestas.getRespuesta9());
		query.append("',RETROALIMENTACION='");
		query.append(encuestas.getRetroalimenacion());
		query.append("' WHERE ID_ASISTENTE=");		
		query.append(encuestas.getIdAsistente());
		log.debug("saveEncuestas() Actualizando asistente " + query);
		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,"Encuesta almacenada correctamente.");
		} catch (Exception e) {
			log.fatal("Error al guardar los cambios, " + e);
			return new Mensaje(1,
					"No es posible guardar los cambios realizados," +
					" intentelo más tarde.");
		}		
	}
	@Override
	public Mensaje saveInasistententes(Participantes  p) throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("UPDATE INFRA.ASISTENTES");
		query.append(" SET ID_SERVICIOS_DIPLOMADO=");
		query.append(p.getIdServiciosDiplomado());
		query.append(" ,RESAGADO = TRUE ");
		query.append(" WHERE ID_ASISTENTE=");
		query.append(p.getId());
		log.debug("saveInasistententes()"+query);
		try {
			getJdbcTemplate().update(query.toString());
			return new Mensaje(0,"Se han guardado las modificaciones correctamente, en seguida se enviaran las notificaciones correspondientes.");
		} catch (Exception e) {
			log.fatal("Error al guardar los cambios, " + e);
			return new Mensaje(1,
					"No es posible reasignar los Asistentes seleccionados," +
					" intentelo más tarde.");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getTema(int idDiplomado) throws DaoException{
		StringBuffer query = new StringBuffer();
		query.append("SELECT TEMA FROM");
		query.append(" INFRA.DIPLOMADOS WHERE ID_DIPLOMADO=");
		query.append(idDiplomado);
		log.debug("getTema() "+ query);
		List<String> en=getJdbcTemplate().query(query.toString(),
				new TemaRowMapper());
		if(en!=null && en.size()>0){
			return en.get(0);
		} else {
			return null;
		}
	}
	@SuppressWarnings("rawtypes")
	public class TemaRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			TemaExtractor extractor = new TemaExtractor();
			return extractor.extractData(rs);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public class TemaExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			return rs.getString("TEMA");
		}
		
	}
}