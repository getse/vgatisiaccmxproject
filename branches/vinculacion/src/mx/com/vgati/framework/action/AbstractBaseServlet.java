package mx.com.vgati.framework.action;

import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * Todos los servlets deben ser una especialización de esta clase.
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
public abstract class AbstractBaseServlet extends HttpServlet {

	private static final long serialVersionUID = 2439178860093265794L;
	protected Log logger = LogFactory.getLog(getClass());

}
