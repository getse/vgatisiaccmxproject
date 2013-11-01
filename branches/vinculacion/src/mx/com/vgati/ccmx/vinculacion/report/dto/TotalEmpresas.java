/*
 * TotalEmpresas.java        08/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.report.dto;

/**
 * 
 * 
 * @author Sergio Olivos
 * 
 */
public class TotalEmpresas {
	private int empresas;
	private String consultoraTotal;

	public int getEmpresas() {
		return empresas;
	}

	public void setEmpresas(int empresas) {
		this.empresas = empresas;
	}

	public String getConsultoraTotal() {
		return consultoraTotal;
	}

	public void setConsultoraTotal(String consultoraTotal) {
		this.consultoraTotal = consultoraTotal;
	}

}
