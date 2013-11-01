/*
 * ParticipantesNoAlmacenadosException.java        28/06/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
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
public class ParticipantesNoAlmacenadosException extends BaseBusinessException {
	public ParticipantesNoAlmacenadosException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public ParticipantesNoAlmacenadosException(
			ExceptionMessage exceptionMessage, Throwable cause) {
		super(exceptionMessage, cause);
	}
}
