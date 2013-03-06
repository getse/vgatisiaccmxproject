package mx.com.vgati.framework.exception;

/**
 * Clase abstracta para el manejo de las excepciones de negocio. <br>
 * Todas las excepciones de negocio deben ser una especializaci�n de esta clase.
 * 
 * @version 0.1
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
