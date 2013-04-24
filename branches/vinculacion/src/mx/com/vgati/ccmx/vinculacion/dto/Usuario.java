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
@SuppressWarnings("serial")
public class Usuario extends AbstractBaseDTO {

	private String id;
	private int idUsuario;
	private String rol;
	private String credenciales;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getRol() {
		return rol;
	}

	public String getCredenciales() {
		return credenciales;
	}

	public void setCredenciales(String credenciales) {
		this.credenciales = credenciales;
	}

}
