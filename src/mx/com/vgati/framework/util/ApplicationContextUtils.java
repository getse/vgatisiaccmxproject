package mx.com.vgati.framework.util;

import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * Utilerias para el manejo del ServletContext de la aplicación.
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
public class ApplicationContextUtils {
	private static ServletContext servletContext;
	private static Log logger = LogFactory
			.getLog(ApplicationContextUtils.class);

	public static void setServletContext(ServletContext applicationContext) {
		servletContext = applicationContext;

	}

	public static InputStream getStream(String uri) {
		logger.debug("Obteniendo el recurso:[" + uri + "]");
		if (!ValidationUtils.isEmpty(uri)) {
			return servletContext.getResourceAsStream(uri);
		} else {
			return null;
		}

	}

	public static String getParameter(String parameter) {
		logger.debug("Obteniendo el parámetro:[" + parameter + "]");
		if (!ValidationUtils.isEmpty(parameter)) {
			return servletContext.getInitParameter(parameter);
		} else {
			return null;
		}

	}
}
