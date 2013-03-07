/*
 * BaseBusinessException.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.exception;

/**
 * Clase abstracta para el manejo de las excepciones de negocio. <br>
 * Todas las excepciones de negocio deben ser una especialización de esta clase.
 * 
 * @author Getsemani Correa
 * 
 */
public class BaseBusinessException extends Exception {

	private static final long serialVersionUID = 7551451198604279256L;
	private ExceptionMessage exceptionMessage;

	public BaseBusinessException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(cause);
		setExceptionMessage(exceptionMessage);
	}

	public BaseBusinessException(ExceptionMessage exceptionMessage) {
		super();
		setExceptionMessage(exceptionMessage);
	}

	public ExceptionMessage getExceptionMessage() {
		return exceptionMessage;
	}

	private void setExceptionMessage(ExceptionMessage exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
