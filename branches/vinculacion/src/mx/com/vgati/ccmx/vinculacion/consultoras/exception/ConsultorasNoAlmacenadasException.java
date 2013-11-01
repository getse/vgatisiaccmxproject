/*
 * ConsultorasNoAlmacenadasException.java        01/04/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.exception;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.CCMXException;
import mx.com.vgati.framework.exception.ExceptionMessage;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class ConsultorasNoAlmacenadasException extends CCMXException {

	public ConsultorasNoAlmacenadasException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public ConsultorasNoAlmacenadasException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}

}
