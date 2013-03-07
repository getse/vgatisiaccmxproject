/*
 * CloneableBeanException.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.framework.util.exception;

import java.util.Map;

/**
 * Lanzada si no ha sido posible clonar un objeto (el objeto no necesariamente
 * debe implementar Cloneable)
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class CloneableBeanException extends BeanConverterException {

	/**
	 * @param message
	 */
	public CloneableBeanException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CloneableBeanException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CloneableBeanException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param params
	 */
	public CloneableBeanException(String message, Throwable cause,
			Map<String, Object> params) {
		super(message, cause, params);
	}

	/**
	 * @param message
	 * @param params
	 */
	public CloneableBeanException(String message, Map<String, Object> params) {
		super(message, params);
	}

}
