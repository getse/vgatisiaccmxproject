package mx.com.vgati.ccmx.vinculacion.report.exception;

import mx.com.vgati.framework.exception.BaseBusinessException;
import mx.com.vgati.framework.exception.ExceptionMessage;

@SuppressWarnings("serial")
public class ReporteException extends BaseBusinessException{

	public ReporteException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public ReporteException(ExceptionMessage exceptionMessage, Throwable cause) {
		super(exceptionMessage, cause);
	}

}
