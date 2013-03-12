package mx.com.vgati.ccmx.vinculacion.tractoras.exception;

import mx.com.vgati.framework.exception.ExceptionMessage;

@SuppressWarnings("serial")
public class CompradoresNoObtenidosException extends TractorasException {

	public CompradoresNoObtenidosException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public CompradoresNoObtenidosException(
			ExceptionMessage exceptionMessage, Throwable cause) {
		super(exceptionMessage, cause);
	}
}
