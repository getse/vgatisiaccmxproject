/*
 * ProductosNoAlmacenadosException.java        23/04/2013
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
public class ProductosNoAlmacenadosException extends PyMEsException {

	public ProductosNoAlmacenadosException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public ProductosNoAlmacenadosException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}
}
