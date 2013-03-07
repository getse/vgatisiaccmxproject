/*
 * AbstractBaseJdbcDao.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Clase abstracta para las implementaciones de los DAO's de tipo JDBC.
 * 
 * @author Getsemani Correa
 * 
 */
public abstract class AbstractBaseJdbcDao extends JdbcDaoSupport {

	protected Log log = LogFactory.getLog(getClass());

}
