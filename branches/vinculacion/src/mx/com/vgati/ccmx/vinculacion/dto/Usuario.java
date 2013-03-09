/*
 * Usuario.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

/**
 * 
 * @author Getsemani Correa
 * 
 */
public class Usuario extends AbstractBaseDTO {

	private static final long serialVersionUID = -4173854220915643234L;
	private String id;
	private String idUsuario;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

}
