/*
 * TractorasNoObtenidasException.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.ccmx.exception;

import mx.com.vgati.framework.exception.ExceptionMessage;

@SuppressWarnings("serial")
public class TractorasNoObtenidasException extends CCMXException {

	public TractorasNoObtenidasException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public TractorasNoObtenidasException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}

}
