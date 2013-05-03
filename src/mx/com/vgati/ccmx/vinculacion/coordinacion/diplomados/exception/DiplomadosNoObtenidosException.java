/*
 * DiplomadosNoObtenidosException.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception;

import mx.com.vgati.framework.exception.ExceptionMessage;

/**
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class DiplomadosNoObtenidosException extends
		CoordinadorDiplomadosException {

	public DiplomadosNoObtenidosException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public DiplomadosNoObtenidosException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}

}
