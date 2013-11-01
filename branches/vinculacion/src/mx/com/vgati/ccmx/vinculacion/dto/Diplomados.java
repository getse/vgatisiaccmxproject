/*
 * Diplomados.java        15/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.dto;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public enum Diplomados {

	CulturaOrg("Cultura organizacional y la competitividad de las empresas"), EstrategiaCom(
			"Estrategia Comercial, Imagen y Cadena de Distribuci�n"), DesrrolloMet(
			"Desarrollo de M�todos de Producci�n Esbeltos y Cadena de Valor"), EstrategiaPlan(
			"Estrategia, Planeaci�n e Innovaci�n");

	private final String diplomado;

	private Diplomados(String diplomado) {
		this.diplomado = diplomado;
	}

	public String getDiplomado() {
		return diplomado;
	}
}
