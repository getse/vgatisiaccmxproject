/*
 * ConsultorasNoObtenidasException.java        01/04/2013
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

/**
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class ConsultorasNoObtenidasExceprion extends CCMXException {

	public ConsultorasNoObtenidasExceprion(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public ConsultorasNoObtenidasExceprion(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}

}
