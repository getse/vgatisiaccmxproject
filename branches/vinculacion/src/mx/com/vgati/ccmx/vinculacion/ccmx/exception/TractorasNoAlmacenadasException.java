package mx.com.vgati.ccmx.vinculacion.ccmx.exception;

import mx.com.vgati.framework.exception.ExceptionMessage;

public class TractorasNoAlmacenadasException extends CCMXException {
	
	private static final long serialVersionUID = -939722706416274906L;

	public TractorasNoAlmacenadasException(
			ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public TractorasNoAlmacenadasException(
			ExceptionMessage exceptionMessage, Throwable cause) {
		super(exceptionMessage, cause);
	}

}
