package mx.com.vgati.framework.exception;

import java.util.Map;

/**
 * Clase base abstracta para el manejo de excepciones de infraestructura.
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
public class BaseFrameworkException extends RuntimeException {

	private static final long serialVersionUID = -2402709702208799108L;
	private Map<String, Object> params;

	public BaseFrameworkException(String message) {
		super(message);
	}

	public BaseFrameworkException(Throwable cause) {
		super(cause);
	}

	public BaseFrameworkException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseFrameworkException(String message, Throwable cause,
			Map<String, Object> params) {
		this(message, cause);
		setParams(params);
	}

	public BaseFrameworkException(String message, Map<String, Object> params) {
		this(message);
		setParams(params);
	}

	public Map<String, Object> getParams() {
		return params;
	}

	private void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
