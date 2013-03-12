/*
 * Tractoras.java        07/03/2013
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

@SuppressWarnings("serial")
public class Tractoras extends AbstractBaseDTO {
	private int idUsuario;
	private int idTractoraPadre;
	private String empresa;
	private String nombreContacto;
	private String appPaterno;
	private String appMaterno;
	private String correoElectronico;
	private String puesto;
	private String telefonos;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdTractoraPadre() {
		return idTractoraPadre;
	}

	public void setIdTractoraPadre(int idTractoraPadre) {
		this.idTractoraPadre = idTractoraPadre;
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

	public String getAppPaterno() {
		return appPaterno;
	}

	public void setAppPaterno(String appPaterno) {
		this.appPaterno = appPaterno;
	}

	public String getAppMaterno() {
		return appMaterno;
	}

	public void setAppMaterno(String appMaterno) {
		this.appMaterno = appMaterno;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}
}
