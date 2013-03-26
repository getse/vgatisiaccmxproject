/*
 * HttpErrorHandlerAction.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.common.exception.action;

import mx.com.vgati.framework.action.AbstractBaseAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 * 
 * Clase que maneja los errores HTTP mas comunes.<br>
 * Se agrupan en:<br>
 * <ul>
 * <li>Errores de peticion
 * <ul>
 * <li>400</li>
 * <li>404</li>
 * <li>406</li>
 * <li>408</li>
 * <li>414</li>
 * </ul>
 * </li>
 * <li>Errores de autorizacion
 * <ul>
 * <li>403</li>
 * <li>407</li>
 * </ul>
 * </li>
 * <li>Errores de servidor
 * <ul>
 * <li>500</li>
 * <li>501</li>
 * <li>503</li>
 * </ul>
 * </li>
 * <li>Errores de gateway
 * <ul>
 * <li>502</li>
 * <li>504</li>
 * </ul>
 * </li>
 * </ul>
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class HttpErrorHandlerAction extends AbstractBaseAction {

	private String messageKey;

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	@Action(value = "/badRequestHandler", results = { @Result(name = "success", location = "http.error.action", type = "tiles") })
	public String badRequestHandler() {
		log.debug("badRequestHandler()");
		log.debug("messageKey: " + messageKey);
		setMessageKey(getText("bad.request"));
		return SUCCESS;
	}

	@Action(value = "/authorizationHandler", results = { @Result(name = "success", location = "http.error.action", type = "tiles") })
	public String authorizationHandler() {
		log.debug("authorizationHandler()");
		log.debug("messageKey: " + messageKey);
		setMessageKey(getText("not.authorized"));
		return SUCCESS;
	}

	@Action(value = "/serverErrorHandler", results = { @Result(name = "success", location = "http.error.action", type = "tiles") })
	public String serverErrorHandler() {
		log.debug("serverErrorHandler()");
		log.debug("messageKey: " + messageKey);
		setMessageKey(getText("server.error"));
		return SUCCESS;
	}

	@Action(value = "/gatewayServerHandler", results = { @Result(name = "success", location = "http.error.action", type = "tiles") })
	public String gatewayServerHandler() {
		log.debug("gatewayServerHandler()");
		log.debug("messageKey: " + messageKey);
		setMessageKey(getText("gateway.server"));
		return SUCCESS;
	}

}
