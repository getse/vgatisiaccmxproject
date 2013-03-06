package mx.com.vgati.framework.exception;

/**
 * Clase que genera un ExceptionMessage a partir de la excepci&oacute;n.
 * 
 * @version 0.1
 * @author Getseamani Correa
 * 
 */
public class ExceptionMessageHandler {

	/**
	 * 
	 * @return ExceptionMessage
	 */
	public static ExceptionMessage getExceptionMessage(final Exception exception) {
		ExceptionMessage message = null;
		if (exception != null) {
			if (exception instanceof BaseBusinessException) {

				message = ((BaseBusinessException) exception)
						.getExceptionMessage();
			} else {

				message = getMessage(exception);
			}
		}

		return message;
	}

	private static ExceptionMessage getMessage(Exception exception) {
		String cause = exception.getCause() != null ? exception.getCause()
				.getMessage() : exception.getMessage();

		if (cause == null) {
			cause = exception.getClass().toString();
		}

		return new ExceptionMessage(cause);

	}

}
