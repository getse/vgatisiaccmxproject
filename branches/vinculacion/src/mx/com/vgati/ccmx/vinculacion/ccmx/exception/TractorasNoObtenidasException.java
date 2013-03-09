package mx.com.vgati.ccmx.vinculacion.ccmx.exception;

import mx.com.vgati.framework.exception.ExceptionMessage;

@SuppressWarnings("serial")
public class TractorasNoObtenidasException extends CCMXException {
	
	public TractorasNoObtenidasException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public TractorasNoObtenidasException(
			ExceptionMessage exceptionMessage, Throwable cause) {
		super(exceptionMessage, cause);
	}

}
