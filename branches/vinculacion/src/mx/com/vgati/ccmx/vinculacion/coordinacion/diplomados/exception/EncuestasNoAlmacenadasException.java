/*
 * EncuestasNoAlmacenadasException.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception;

import mx.com.vgati.framework.exception.ExceptionMessage;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class EncuestasNoAlmacenadasException extends
		CoordinadorDiplomadosException {

	public EncuestasNoAlmacenadasException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public EncuestasNoAlmacenadasException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}

}