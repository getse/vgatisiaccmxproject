package mx.com.vgati.framework.dto;

import java.io.Serializable;
import java.util.Map;

import mx.com.vgati.framework.util.BeanUtils;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Clase base para los data transfer objects.<br>
 * Sin excepci�n todos los objetos de tipo 'DTO' deben ser una especializaci�n
 * de �sta clase.
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
public abstract class AbstractBaseDTO implements Serializable {

	private static final long serialVersionUID = -7451248569930286434L;

	/**
	 * Genera una cadena con todos los atributos del objeto y sus valores.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * 
	 * @return Mapa con las propiedades del bean.
	 */
	public Map<String, Object> describe() {
		return BeanUtils.describe(this);
	}
}
