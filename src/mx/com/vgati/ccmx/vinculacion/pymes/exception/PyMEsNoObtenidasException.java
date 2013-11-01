/*
 * PyMEsNoObtenidasException.java        23/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.exception;

import mx.com.vgati.framework.exception.ExceptionMessage;

/**
 * 
 * 
 * @author Omar Hernandez
 * 
 */
@SuppressWarnings("serial")
public class PyMEsNoObtenidasException extends PyMEsException {

	public PyMEsNoObtenidasException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public PyMEsNoObtenidasException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}
}
