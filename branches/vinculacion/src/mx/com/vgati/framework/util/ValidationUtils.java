/*
 * ValidationUtils.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.util;

/**
 * 
 * Utileria con las validaciones más comúnes.
 * 
 * @author Getsemani Correa
 * 
 */
public class ValidationUtils {

	public static final int MIN_LENGTH = 10;
	protected static java.util.Random r = new java.util.Random();

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

	public String getPasswd() {
		return getNext(12);
	}

	protected static char[] goodChar = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
			'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '@', '_', '#', '.', ',' };

	public static String getNext() {
		return getNext(MIN_LENGTH);
	}

	public static String getNext(int length) {
		if (length < 1) {
			throw new IllegalArgumentException("Minimo 10 de longitud: "
					+ length);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(goodChar[r.nextInt(goodChar.length)]);
		}
		return sb.toString();
	}

}
