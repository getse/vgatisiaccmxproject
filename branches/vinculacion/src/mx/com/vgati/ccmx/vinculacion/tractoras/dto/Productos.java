/*
 * Productos.java        07/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.tractoras.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class Productos extends AbstractBaseDTO {

	private int cveScian;
	private String nivel1;
	private String nivel2;
	private String nivel3;
	private String nivel4;
	private String descScian;
	private String busqueda;
	private int cveNivel;

	public int getCveScian() {
		return cveScian;
	}

	public void setCveScian(int cveScian) {
		this.cveScian = cveScian;
	}

	public String getNivel1() {
		return nivel1;
	}

	public void setNivel1(String nivel1) {
		this.nivel1 = nivel1;
	}

	public String getNivel2() {
		return nivel2;
	}

	public void setNivel2(String nivel2) {
		this.nivel2 = nivel2;
	}

	public String getNivel3() {
		return nivel3;
	}

	public void setNivel3(String nivel3) {
		this.nivel3 = nivel3;
	}

	public String getNivel4() {
		return nivel4;
	}

	public void setNivel4(String nivel4) {
		this.nivel4 = nivel4;
	}

	public String getDescScian() {
		return descScian;
	}

	public void setDescScian(String descScian) {
		this.descScian = descScian;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public int getCveNivel() {
		return cveNivel;
	}

	public void setCveNivel(int cveNivel) {
		this.cveNivel = cveNivel;
	}

}
