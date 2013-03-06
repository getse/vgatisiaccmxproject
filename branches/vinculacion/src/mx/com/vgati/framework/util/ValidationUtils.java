package mx.com.vgati.framework.util;

/**
 * 
 * Utileria con las validaciones más comúnes.
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
public class ValidationUtils {
	/**
	 * Verifica que la cadena pasada sea nula.
	 * 
	 * @param string
	 *            Cadena a verificar.
	 * @return verdadero si es nula<br>
	 */
	public static boolean isNull(final String string) {
		return string == null;
	}

	/**
	 * Verifica que la cadena pasada este vacia.
	 * 
	 * @param string
	 *            Cadena a verificar.
	 * @return verdadero si está vacia.
	 */
	public static boolean isEmpty(final String string) {
		if (!isNull(string)) {
			return string.trim().length() <= 0;
		} else {
			return true;
		}

	}

	/**
	 * 
	 * Lanza una IllegalArgumentException si el objeto pasado es nulo.
	 * 
	 * @param paramName
	 *            Nombre del parámetro.
	 * @param object
	 *            Objeto a verificar que no sea nulo.
	 */
	public static void notNull(final String paramName, final Object object) {

		if (object == null) {
			throw new IllegalArgumentException("El parámetro [" + paramName
					+ "] es nulo.");
		}

	}
}
