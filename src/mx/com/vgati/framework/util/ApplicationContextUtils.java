/*
 * ApplicationContextUtils.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.framework.util;

import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * Utilerias para el manejo del ServletContext de la aplicaci�n.
 * 
 * @author Getsemani Correa
 * 
 */
public class ApplicationContextUtils {
	private static ServletContext servletContext;
	private static Log log = LogFactory
			.getLog(ApplicationContextUtils.class);

	public static void setServletContext(ServletContext applicationContext) {
		servletContext = applicationContext;

	}

	public static InputStream getStream(String uri) {
		log.debug("Obteniendo el recurso: [" + uri + "]");
		if (!ValidationUtils.isEmpty(uri)) {
			return servletContext.getResourceAsStream(uri);
		} else {
			return null;
		}

	}

	public static String getParameter(String parameter) {
		log.debug("Obteniendo el par�metro: [" + parameter + "]");
		if (!ValidationUtils.isEmpty(parameter)) {
			return servletContext.getInitParameter(parameter);
		} else {
			return null;
		}

	}
}
