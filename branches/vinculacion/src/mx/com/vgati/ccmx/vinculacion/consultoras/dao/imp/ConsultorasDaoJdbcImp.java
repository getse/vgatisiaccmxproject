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
import java.util.List;

import mx.com.vgati.ccmx.vinculacion.consultoras.dao.ConsultorasDao;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.exception.ConsultoraNoObtenidaException;
import mx.com.vgati.ccmx.vinculacion.dto.Roles;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Mensaje;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<PyMEs> getPymes() throws DaoException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT PY.ID_USUARIO");
		query.append(",CORREO_ELECTRONICO");
		query.append(",NOMBRE_COMERCIAL");
		query.append(" FROM INFRA.PYMES AS PY"); 
		query.append(" WHERE ID_USUARIO ");
		query.append("NOT IN (SELECT ID_USUARIO_PYME FROM INFRA.REL_CONSULTORAS_PYME)");
		query.append("ORDER BY NOMBRE_COMERCIAL ASC;");		
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

}