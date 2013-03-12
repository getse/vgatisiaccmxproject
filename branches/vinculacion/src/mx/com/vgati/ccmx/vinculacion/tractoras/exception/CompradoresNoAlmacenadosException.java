package mx.com.vgati.ccmx.vinculacion.tractoras.exception;

import mx.com.vgati.framework.exception.ExceptionMessage;

@SuppressWarnings("serial")
public class CompradoresNoAlmacenadosException extends TractorasException {

	public CompradoresNoAlmacenadosException(
			ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public CompradoresNoAlmacenadosException(
			ExceptionMessage exceptionMessage, Throwable cause) {
		super(exceptionMessage, cause);
	}
}