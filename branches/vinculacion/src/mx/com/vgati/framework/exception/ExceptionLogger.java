/*
 * ExceptionLogger.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase que loggea la excepción en el siguiente formato:<br>
 * <br>
 * Ticket ID: [#Identificador Único#]<br>
 * Cause: [#origen de la excepción#]<br>
 * >#Índice# [#Stack Trace#]
 * 
 * @author Getsemani Correa
 * 
 */
public class ExceptionLogger {
	private Log log = LogFactory.getLog(getClass());
	private Exception exception;
	private ExceptionMessage exceptionMessage;

	public ExceptionLogger(ExceptionMessage exceptionMessage,
			Exception exception) {
		setException(exception);
		setExceptionMessage(exceptionMessage);
	}

	public void log() {
		StackTraceElement[] traceElement = exception.getStackTrace();
		log.error("Ticket ID: [" + exceptionMessage.getUuid() + "]");
		log.error("Cause: [" + exception + "]");

		for (int index = 0; index < traceElement.length; index++) {
			log.error(String.format("  > %1$3d [%2$s]", index,
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
