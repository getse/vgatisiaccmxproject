package mx.com.vgati.ccmx.vinculacion.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

/**
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
public class Usuario extends AbstractBaseDTO {

	private static final long serialVersionUID = -4173854220915643234L;
	private String id;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
