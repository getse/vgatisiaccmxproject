package mx.com.vgati.framework.util.exception;

import java.util.Map;

/**
 * 
 * 
 * @version 0.1
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
