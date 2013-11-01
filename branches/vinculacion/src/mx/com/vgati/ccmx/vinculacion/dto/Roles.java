/*
 * Roles.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.dto;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public enum Roles {

	AdministradorCCMX("AdministradorCCMX"), Comprador("Comprador"), Tractora(
			"Tractora"), CoordinadorConsultoras("CoordinadorConsultoras"), CoordinadorDiplomados(
			"CoordinadorDiplomados"), PyME("PyME"), AdministradorConsultores(
			"AdministradorConsultores"), Consultor("Consultor");

	private final String rol;

	private Roles(String rol) {
		this.rol = rol;
	}

	public String getRol() {
		return rol;
	}
}
