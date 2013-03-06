package mx.com.vgati.framework.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase que loggea la excepción en el siguiente formato:<br>
 * <br>
 * Ticket ID:[#Identificador &Uacute;nico#]<br>
 * Cause:[#origen de la excepci&oacute;n#]<br>
 * >#&Iacute;ndice# [#Stack Trace#]
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
public class ExceptionLogger {
	private Log logger = LogFactory.getLog(getClass());
	private Exception exception;
	private ExceptionMessage exceptionMessage;

	public ExceptionLogger(ExceptionMessage exceptionMessage,
			Exception exception) {
		setException(exception);
		setExceptionMessage(exceptionMessage);
	}

	public void log() {
		StackTraceElement[] traceElement = exception.getStackTrace();
		logger.error("Ticket ID:[" + exceptionMessage.getUuid() + "]");
		logger.error("Cause:[" + exception + "]");

		for (int index = 0; index < traceElement.length; index++) {
			logger.error(String.format("  > %1$3d [%2$s]", index,
					traceElement[index]));
		}
	}

	private void setException(final Exception exception) {
		this.exception = exception;
	}

	private void setExceptionMessage(final ExceptionMessage exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
