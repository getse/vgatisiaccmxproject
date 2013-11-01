/*
 * IndicadoresNoObtenidosException.java        09/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
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
public class IndicadoresNoObtenidosException extends PyMEsException {

	public IndicadoresNoObtenidosException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public IndicadoresNoObtenidosException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}
}
