/*
 * JdbcDaoException.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.dao.exception;

import java.util.Map;

/**
 * Clase de excepción para DAO's de implementación en JDBC.
 * 
 * @author Getsemani Correa
 * 
 */
public class JdbcDaoException extends DaoException {

	private static final long serialVersionUID = -4988507923701202507L;

	/**
	 * @param message
	 * @param params
	 */
	public JdbcDaoException(String message, Map<String, Object> params) {
		super(message, params);
	}

	/**
	 * @param message
	 * @param cause
	 * @param params
	 */
	public JdbcDaoException(String message, Throwable cause,
			Map<String, Object> params) {
		super(message, cause, params);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JdbcDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public JdbcDaoException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public JdbcDaoException(Throwable cause) {
		super(cause);
	}

}
