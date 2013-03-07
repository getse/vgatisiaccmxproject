/*
 * AbstractBaseServlet.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
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
	protected Log log = LogFactory.getLog(getClass());

}
