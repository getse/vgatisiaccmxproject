/*
 * Factura.java        16/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.dto;

/**
 * 
 * 
 * @author Sergio Olivos
 * 
 */
public enum Factura {

	Anticipo("Anticipo"), Finiquito("Finiquito"), Abono1("Abono1"), Abono2(
			"Abono2");

	private final String factura;

	private Factura(String factura) {
		this.factura = factura;
	}

	public String geteFactura() {
		return factura;
	}
}
