/*
 * ServiceLocator.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.jee;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mx.com.vgati.framework.jee.exception.ServiceLocatorException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase que implementa el patrón Service Locator de JEE. <br>
 * <p>
 * http://java.sun.com/blueprints/corej2eepatterns/Patterns/ServiceLocator.html
 * 
 * @author Getsemai Correa
 * 
 */
public class ServiceLocator {

	private static ServiceLocator serviceLocator;
	private InitialContext context = null;
	private Log logger = LogFactory.getLog(getClass());

	private ServiceLocator() throws ServiceLocatorException {
		try {
			context = new InitialContext();
		} catch (NamingException ne) {
			throw new ServiceLocatorException(ne);
		}
	}

	/**
	 * Singleton que provee la instancia.
	 * 
	 * @return Instancia del ServiceLocator.
	 * @throws ServiceLocatorException
	 */
	public static ServiceLocator getSingletonServices()
			throws ServiceLocatorException {
		if (serviceLocator == null) {
			serviceLocator = new ServiceLocator();
		}
		return serviceLocator;
	}

	/**
	 * Obtiene el DataSource del directorio.
	 * 
	 * @param jndiName
	 *            Nombre del data source.
	 * @return Data source.
	 */
	public DataSource getDataSource(String jndiName) {
		logger.debug("getDataSource.jndiName:[" + jndiName + "]");
		DataSource dataSource = null;
		try {
			dataSource = (DataSource) context.lookup(jndiName);
		} catch (NamingException e) {
			throw new ServiceLocatorException(e);
		}

		return dataSource;
	}

}
