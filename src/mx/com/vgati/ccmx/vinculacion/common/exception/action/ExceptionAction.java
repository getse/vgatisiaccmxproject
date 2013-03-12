/*
 * ExceptionAction.java        01/03/2013
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
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class ExceptionAction extends AbstractBaseAction {

	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Action(value = "/httpErrors/error", results = { @Result(name = "success", location = "errorCodes", type = "tiles") })
	public String procesaError() {
		log.debug("-- procesaError --");
		log.debug("code=" + code);

		return SUCCESS;
	}

}
