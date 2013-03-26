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

		String nameSpace = "";
		String path = req.getContextPath() + nameSpace;

		switch (code) {
		case 400:
		case 408:
		case 404:
		case 406:
		case 414:
			path += "/badRequestHandler.do";
			break;
		case 407:
		case 403:
			path += "/authorizationHandler.do";
			break;
		case 500:
		case 501:
		case 503:
			path += "/serverErrorHandler.do";
			break;
		case 502:
		case 504:
			path += "/gatewayServerHandler.do";
			break;
		default:
			path += "/badRequestHandler.do";
			break;
		}

		log.debug("stringCode: " + stringCode);
		log.info("code: " + code);

		String url = resp.encodeRedirectURL(path);
		log.debug("url: " + url);
		resp.sendRedirect(url);

		return;
	}

}
