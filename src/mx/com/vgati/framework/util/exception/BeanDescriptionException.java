package mx.com.vgati.framework.util.exception;

import java.util.Map;

/**
 * Lanzada si no ha sido posible describir las propiedades de un objeto.
 * 
 * @version 0.1
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
