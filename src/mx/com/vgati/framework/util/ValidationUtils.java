/*
 * ValidationUtils.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.framework.util;

/**
 * 
 * Utileria con las validaciones m�s com�nes.
 * 
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
	 * @return verdadero si est� vacia.
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
	 *            Nombre del par�metro.
	 * @param object
	 *            Objeto a verificar que no sea nulo.
	 */
	public static void notNull(final String paramName, final Object object) {

		if (object == null) {
			throw new IllegalArgumentException("El par�metro [" + paramName
					+ "] es nulo.");
		}

	}
}
