/*
 * CopyPropertiesException.java        01/03/2013
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
 * Lanzada cuando no ha sido posible copiar las propiedades del objeto origen al
 * destino.
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class CopyPropertiesException extends BeanConverterException {

	/**
	 * @param message
	 */
	public CopyPropertiesException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CopyPropertiesException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CopyPropertiesException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param params
	 */
	public CopyPropertiesException(String message, Throwable cause,
			Map<String, Object> params) {
		super(message, cause, params);
	}

	/**
	 * @param message
	 * @param params
	 */
	public CopyPropertiesException(String message, Map<String, Object> params) {
		super(message, params);
	}

}
