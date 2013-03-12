/*
 * ExceptionServlet.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.common.exception.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.vgati.framework.action.AbstractBaseServlet;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class ExceptionServlet extends AbstractBaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		log.debug("doGet");
		String stringCode = (String) req.getParameter("code");
		int code = Integer.parseInt(stringCode);
		log.debug("stringCode: " + stringCode);
		log.info("code: " + code);

		String path = req.getContextPath() + "/httpErrors/error.do?code="
				+ code;

		// redirect
		String url = resp.encodeRedirectURL(path);
		log.debug("url: " + url);
		resp.sendRedirect(url);

		return;
	}

}
