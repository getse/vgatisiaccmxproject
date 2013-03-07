/*
 * UUIDProvider.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.uuid;

import java.util.UUID;

/**
 * Proveedor de "Universal Unique IDentifiers"
 * 
 * @author Getsemani Correa
 * 
 */
public final class UUIDProvider {
	private static UUIDProvider instance = new UUIDProvider();

	private UUIDProvider() {

	}

	public static UUIDProvider getInstance() {
		return instance;
	}

	public String getUUID() {
		return UUID.randomUUID().toString();
	}
}
