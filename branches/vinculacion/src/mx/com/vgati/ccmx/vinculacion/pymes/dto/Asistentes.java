/*
 * Asistentes.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class Asistentes extends AbstractBaseDTO{
	
	private int idAsistente;
	private int idDiplomado;
	private String nombre;
	private String appPaterno;
	private String appMaterno;
	private String telefono;
	private String correoElectronico;
	
	public int getIdAsistente() {
		return idAsistente;
	}
	public void setIdAsistente(int idAsistente) {
		this.idAsistente = idAsistente;
	}
	public int getIdDiplomado() {
		return idDiplomado;
	}
	public void setIdDiplomado(int idDiplomado) {
		this.idDiplomado = idDiplomado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
}
