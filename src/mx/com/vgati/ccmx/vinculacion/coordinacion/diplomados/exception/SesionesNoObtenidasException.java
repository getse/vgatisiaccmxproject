package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception;

import mx.com.vgati.framework.exception.BaseBusinessException;
import mx.com.vgati.framework.exception.ExceptionMessage;

@SuppressWarnings("serial")
public class SesionesNoObtenidasException extends BaseBusinessException {
	public SesionesNoObtenidasException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public SesionesNoObtenidasException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}
}
