package mx.com.vgati.framework.util.exception;

import java.util.Map;

import mx.com.vgati.framework.exception.BaseFrameworkException;

/**
 * 
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class BeanConverterException extends BaseFrameworkException {

	/**
	 * @param message
	 */
	public BeanConverterException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BeanConverterException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BeanConverterException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param params
	 */
	public BeanConverterException(String message, Throwable cause,
			Map<String, Object> params) {
		super(message, cause, params);
	}

	/**
	 * @param message
	 * @param params
	 */
	public BeanConverterException(String message, Map<String, Object> params) {
		super(message, params);
	}

}
