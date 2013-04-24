/*
 * Consultoras.java        07/04/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.ccmx.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

/**
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class Consultoras extends AbstractBaseDTO {

	private int idUsuario;
	private int idUsuarioPadre;
	private String empresa;
	private String nombreContacto;
	private String appPaternoContacto;
	private String appMaternoContacto;
	private String correoElectronico;
	private String password;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdUsuarioPadre() {
		return idUsuarioPadre;
	}

	public void setIdUsuarioPadre(int idUsuarioPadre) {
		this.idUsuarioPadre = idUsuarioPadre;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getAppPaternoContacto() {
		return appPaternoContacto;
	}

	public void setAppPaternoContacto(String appPaternoContacto) {
		this.appPaternoContacto = appPaternoContacto;
	}

	public String getAppMaternoContacto() {
		return appMaternoContacto;
	}

	public void setAppMaternoContacto(String appMaternoContacto) {
		this.appMaternoContacto = appMaternoContacto;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}