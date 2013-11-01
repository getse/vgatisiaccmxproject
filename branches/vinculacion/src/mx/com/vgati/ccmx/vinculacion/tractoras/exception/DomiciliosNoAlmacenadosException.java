/*
 * DomiciliosNoAlmacenadosException.java        12/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.tractoras.exception;

import mx.com.vgati.framework.exception.ExceptionMessage;

/**
 * 
 * 
 * @author Omar Hernandez
 * 
 */
@SuppressWarnings("serial")
public class DomiciliosNoAlmacenadosException extends TractorasException {

	public DomiciliosNoAlmacenadosException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public DomiciliosNoAlmacenadosException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}
}
