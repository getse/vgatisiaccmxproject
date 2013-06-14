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
package mx.com.vgati.ccmx.vinculacion.tractoras.dto;

import java.util.List;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class Tractoras extends AbstractBaseDTO {
	private int idUsuario;
	private int idUsuarioPadre;
	private int idTractoraPadre;
	private String empresa;
	private String nombreContacto;
	private String appPaterno;
	private String appMaterno;
	private String correoElectronico;
	private String puesto;
	private List<Telefonos> telefonos;
	private String password;
	private int compradores;
	private int requerimientos;

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

	public List<Telefonos> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<Telefonos> telefonos) {
		this.telefonos = telefonos;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public int getCompradores() {
		return compradores;
	}

	public void setCompradores(int compradores) {
		this.compradores = compradores;
	}

	public int getRequerimientos() {
		return requerimientos;
	}

	public void setRequerimientos(int requerimientos) {
		this.requerimientos = requerimientos;
	}
}
