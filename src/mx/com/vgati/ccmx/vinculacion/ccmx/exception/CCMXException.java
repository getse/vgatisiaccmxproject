/*
 * CCMXException.java        07/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.ccmx.exception;

import mx.com.vgati.framework.exception.BaseBusinessException;
import mx.com.vgati.framework.exception.ExceptionMessage;

public class CCMXException extends BaseBusinessException {

	private static final long serialVersionUID = 5666239817282728544L;

	public CCMXException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public CCMXException(ExceptionMessage exceptionMessage, Throwable cause) {
		super(exceptionMessage, cause);
	}
}
