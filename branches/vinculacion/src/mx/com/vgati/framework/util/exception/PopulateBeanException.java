/*
 * PopulateBeanException.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.util.exception;

import java.util.Map;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class PopulateBeanException extends BeanConverterException {

	/**
	 * @param message
	 */
	public PopulateBeanException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PopulateBeanException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PopulateBeanException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param params
	 */
	public PopulateBeanException(String message, Throwable cause,
			Map<String, Object> params) {
		super(message, cause, params);
	}

	/**
	 * @param message
	 * @param params
	 */
	public PopulateBeanException(String message, Map<String, Object> params) {
		super(message, params);
	}

}
