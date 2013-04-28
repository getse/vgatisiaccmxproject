/*
 * AbstractBaseAction.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.framework.exception.BaseBusinessException;
import mx.com.vgati.framework.exception.ExceptionMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase abstracta para los objetos de tipo '*Action'. Implementa la base para
 * el manejo de los siguientes objetos:<br>
 * <ul>
 * <li>Atributos de la Sesion: sessionMap</li>
 * <li>Atributos del Request: requestMap</li>
 * <li>Parametros del requestMap: parameters</li>
 * <li>Parametros del contexto: applicationContext</li>
 * <li>Principal: principal</li>
 * <li>Response: response</li>
 * </ul>
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
public abstract class AbstractBaseAction extends ActionSupport implements
		SessionAware, ParameterAware, RequestAware, ApplicationAware,
		PrincipalAware, ServletResponseAware {

	private static final long serialVersionUID = -2631935453303240933L;
	protected Log log = LogFactory.getLog(getClass());
	protected Map<String, Object> sessionMap;
	protected Map<String, Object> parameters;
	protected Map<String, Object> requestMap;
	protected Map<String, Object> applicationContext;
	protected PrincipalProxy principal;
	protected HttpServletResponse response;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setSession(Map session) {
		this.sessionMap = session;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setRequest(Map request) {
		this.requestMap = request;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setApplication(Map applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void setPrincipalProxy(PrincipalProxy principalProxy) {
		this.principal = principalProxy;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Usuario getUsuario() throws BaseBusinessException {
		Usuario u = (Usuario) sessionMap.get("Usuario");
		if (u == null)
			throw new BaseBusinessException(new ExceptionMessage(
					"Su sesión ha expirado, ingrese nuevamente por favor."));
		return u;
	}

}
