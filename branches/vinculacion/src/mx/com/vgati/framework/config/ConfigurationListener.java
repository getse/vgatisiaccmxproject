package mx.com.vgati.framework.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import mx.com.vgati.framework.util.ApplicationContextUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * Clase que configura los componentes del framework.<br>
 * Si se utiliza el servicio de transformación de OpenOffice, en el web.xml debe
 * configurarse el parámetro del contexto:<br>
 * <b>ooConnectionPort</b> el valor debe ser entero.<br>
 * Ej:<br>
 * <context-param><br>
 * <param-name>ooConnectionPort</param-name><br>
 * <param-value>8100</param-value><br>
 * </context-param><br>
 * <context-param><br>
 * <param-name>ooConnectionHost</param-name><br>
 * <param-value>127.0.0.1</param-value><br>
 * </context-param>
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
public class ConfigurationListener implements ServletContextListener {
	private Log logger = LogFactory.getLog(getClass());

	public void contextInitialized(ServletContextEvent event) {
		logger.debug("ConfigurationListener.contextInitialized");

		// Se configura el contexto de la aplicación
		ApplicationContextUtils.setServletContext(event.getServletContext());

	}

	public void contextDestroyed(ServletContextEvent event) {
		logger.debug("ConfigurationListener.contextDestroyed");

	}
}
