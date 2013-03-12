package mx.com.vgati.ccmx.vinculacion.tractoras.exception;

import mx.com.vgati.framework.exception.ExceptionMessage;

@SuppressWarnings("serial")
public class DomiciliosNoAlmacenadosException extends TractorasException {

	public DomiciliosNoAlmacenadosException(
			ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public DomiciliosNoAlmacenadosException(
			ExceptionMessage exceptionMessage, Throwable cause) {
		super(exceptionMessage, cause);
	}
}
