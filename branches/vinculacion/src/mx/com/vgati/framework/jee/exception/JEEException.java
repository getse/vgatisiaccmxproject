/*
 * JEEException.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.jee.exception;

import java.util.Map;

import mx.com.vgati.framework.exception.BaseFrameworkException;

/**
 * Excepción base de los patrones JEE dentro del framework.
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class JEEException extends BaseFrameworkException {

	/**
	 * @param message
	 */
	public JEEException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public JEEException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JEEException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param params
	 */
	public JEEException(String message, Throwable cause,
			Map<String, Object> params) {
		super(message, cause, params);
	}

	/**
	 * @param message
	 * @param params
	 */
	public JEEException(String message, Map<String, Object> params) {
		super(message, params);
	}

}
