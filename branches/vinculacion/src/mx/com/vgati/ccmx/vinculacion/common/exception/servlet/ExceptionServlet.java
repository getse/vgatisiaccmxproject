package mx.com.vgati.ccmx.vinculacion.common.exception.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.vgati.framework.action.AbstractBaseServlet;

/**
 * 
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
public class ExceptionServlet extends AbstractBaseServlet {

	private static final long serialVersionUID = -7058645839392118140L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		logger.debug("doGet");
		// obtengo parametro del codigo de error
		String stringCode = (String) req.getParameter("code");
		int code = Integer.parseInt(stringCode);
		logger.debug("stringCode: " + stringCode);
		logger.info("code: " + code);

		String path = req.getContextPath() + "/httpErrors/error.do?code=" + code;

		// redirect
		String url = resp.encodeRedirectURL(path);
		logger.debug("url: " + url);
		resp.sendRedirect(url);

		logger.debug("termino doGet");
		return;
	}

}
