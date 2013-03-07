/*
 * DaoException.java        01/03/2013
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

import mx.com.vgati.framework.exception.BaseFrameworkException;

/**
 * Clase base para las excepciones de tipo DAO.
 * 
 * @author Getsemani Correa
 * 
 */
public class DaoException extends BaseFrameworkException {

	private static final long serialVersionUID = -5337056938534258597L;

	public DaoException(String message, Map<String, Object> params) {
		super(message, params);
	}

	public DaoException(String message, Throwable cause,
			Map<String, Object> params) {
		super(message, cause, params);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

}
