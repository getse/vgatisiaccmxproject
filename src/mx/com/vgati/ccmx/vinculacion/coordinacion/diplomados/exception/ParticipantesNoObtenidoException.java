package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception;

import mx.com.vgati.framework.exception.BaseBusinessException;
import mx.com.vgati.framework.exception.ExceptionMessage;

@SuppressWarnings("serial")
public class ParticipantesNoObtenidoException extends BaseBusinessException {
	public ParticipantesNoObtenidoException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}

	public ParticipantesNoObtenidoException(ExceptionMessage exceptionMessage,
			Throwable cause) {
		super(exceptionMessage, cause);
	}
}
