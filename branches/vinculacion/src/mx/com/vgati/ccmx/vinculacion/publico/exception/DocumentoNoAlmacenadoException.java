/*
 * DocumentoNoAlmacenadoException.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.publico.exception;

import mx.com.vgati.framework.exception.ExceptionMessage;

/**
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class DocumentoNoAlmacenadoException extends InitException {

	public DocumentoNoAlmacenadoException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public DocumentoNoAlmacenadoException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}
}
