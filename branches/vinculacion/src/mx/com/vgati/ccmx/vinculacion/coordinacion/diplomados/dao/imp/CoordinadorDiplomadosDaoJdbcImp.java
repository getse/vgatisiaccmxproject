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
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dao.CoordinadorDiplomadosDao;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Participantes;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Sesiones;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class CoordinadorDiplomadosDaoJdbcImp extends VinculacionBaseJdbcDao
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
	public List<Diplomados> getMenuDiplomados() throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT DISTINCT(TEMA) FROM  INFRA.DIPLOMADOS ;");
		log.debug("getMenuDiplomados()"+query);
		return getJdbcTemplate().query(query.toString(),
				new MenuDiplomadosRowMapper());
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
			d.setTema(rs.getString("TEMA"));
			return d;
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
 		query.append(", CONCAT(CONCAT(CONCAT(CONCAT(A.NOMBRE,' '),A.APP_PATERNO),' '),A.APP_MATERNO)  AS NOMBRE_ASISTENTE");
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
			ses.setDomicilios(getDomicilios(rs.getInt("ID_DOMICILIO")));
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
				query.append(",SESION) VALUES (");
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
				query.append(")");
				log.debug("saveSesiones() Inserta query=" + query);
				try {
					getJdbcTemplate().update(query.toString());
				} catch (Exception e) {
					log.fatal("ERROR al salvar los estados de ventas, " + e);
					return new Mensaje(1,
							"No es posible registrar los datos de las sesiones, intentelo más tarde.");
				}
			}
		}
		for(int i = numeroSesiones ; i<sesiones.size(); i++){
			if(sesiones.get(i).getIdSesion()>0){
				StringBuffer query = new StringBuffer();
				query.append("DELETE FROM INFRA.SESIONES WHERE ID_SESION=");
				query.append(sesiones.get(i).getIdSesion());
				try {
					getJdbcTemplate().update(query.toString());
				} catch (Exception e) {
					log.fatal("ERROR al salvar los estados de ventas, " + e);
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
					log.fatal("ERROR al salvar los estados de ventas, " + e);
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
						log.fatal("ERROR al salvar los estados de ventas, " + e);
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
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("ERROR al salvar los estados de ventas, " + e);
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
						log.fatal("ERROR al salvar los estados de ventas, " + e);
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
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("ERROR al salvar los estados de ventas, " + e);
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
						log.fatal("ERROR al salvar los estados de ventas, " + e);
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
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("ERROR al salvar los estados de ventas, " + e);
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
						log.fatal("ERROR al salvar los estados de ventas, " + e);
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
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("ERROR al salvar los estados de ventas, " + e);
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
						log.fatal("ERROR al salvar los estados de ventas, " + e);
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
					log.debug("Actualizando Asistencia " + query);
					try {
						getJdbcTemplate().update(query.toString());
					} catch (Exception e) {
						log.fatal("ERROR al salvar los estados de ventas, " + e);
						return new Mensaje(1,
								"No es posible guardar los cambios realizados," +
								" intentelo más tarde.");
					}
				}
			}
			
			query = new StringBuffer();
			query.append("UPDATE INFRA.ASISTENTES SET ");
			query.append(" FACTURA =");
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
				log.fatal("ERROR al salvar los estados de ventas, " + e);
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
		query.append("SELECT TRUE FROM INFRA.ASISTENCIAS WHERE ID_ASISTENTE =");
		query.append(idAsistente);
		query.append(" AND ID_SESION =");
		query.append(idSesion);		
		return getJdbcTemplate().query(query.toString(),
				new AsistenteRowMapper()) != null;
	}
	@SuppressWarnings("rawtypes")
	public class AsistenteRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs1, int arg1) throws SQLException {
			if(rs1 != null){
				return true;
			}
			return false;
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
	
}