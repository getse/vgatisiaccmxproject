package mx.com.vgati.framework.uuid;

import java.util.UUID;

/**
 * Proveedor de "Universal Unique IDentifiers"
 * 
 * @version 0.1
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
