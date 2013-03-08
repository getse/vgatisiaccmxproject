/*
 * Null.java        01/03/2013
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
 * @author Getsemani Correa
 * 
 */
public class Null {

	public static String free(String value) {
		return value == null ? "" : value;
	}

}
