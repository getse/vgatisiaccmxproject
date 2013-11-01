/*
 * SesionesNoObtenidasException.java        28/06/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception;

import mx.com.vgati.framework.exception.BaseBusinessException;
import mx.com.vgati.framework.exception.ExceptionMessage;

/**
 * 
 * 
 * @author Sergio Olivos
 * 
 */
@SuppressWarnings("serial")
public class SesionesNoObtenidasException extends BaseBusinessException {
	public SesionesNoObtenidasException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public SesionesNoObtenidasException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}
}
