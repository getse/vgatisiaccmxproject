/*
 * BeanDescriptionException.java        01/03/2013
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
 * Lanzada si no ha sido posible describir las propiedades de un objeto.
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class BeanDescriptionException extends BeanConverterException {

	/**
	 * @param message
	 * @param params
	 */
	public BeanDescriptionException(String message, Map<String, Object> params) {
		super(message, params);
	}

	/**
	 * @param message
	 * @param cause
	 * @param params
	 */
	public BeanDescriptionException(String message, Throwable cause,
			Map<String, Object> params) {
		super(message, cause, params);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BeanDescriptionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public BeanDescriptionException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BeanDescriptionException(Throwable cause) {
		super(cause);
	}

}
