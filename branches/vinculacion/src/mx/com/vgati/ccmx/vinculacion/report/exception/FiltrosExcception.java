package mx.com.vgati.ccmx.vinculacion.report.exception;

import mx.com.vgati.framework.exception.BaseBusinessException;
import mx.com.vgati.framework.exception.ExceptionMessage;

@SuppressWarnings("serial")
public class FiltrosExcception extends BaseBusinessException {

	public FiltrosExcception(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public FiltrosExcception(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}

}
