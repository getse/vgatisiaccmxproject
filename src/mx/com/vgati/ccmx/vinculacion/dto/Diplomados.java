/*
 * Diplomados.java        15/05/2013
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
public enum Diplomados {

	CulturaOrg("Cultura organizacional y la competitividad de las empresas"), EstrategiaCom(
			"Estrategia Comercial, Imagen y Cadena de Distribución"), DesrrolloMet(
			"Desarrollo de Métodos de Producción Esbeltos y Cadena de Valor"), EstrategiaPlan(
			"Estrategia, Planeación e Innovación");

	private final String diplomado;

	private Diplomados(String diplomado) {
		this.diplomado = diplomado;
	}

	public String getDiplomado() {
		return diplomado;
	}
}
