/*
 * Pagos.java        16/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

/**
 * 
 * 
 * @author Sergio Olivos
 * 
 */
@SuppressWarnings("serial")
public class Pagos extends AbstractBaseDTO {

	private int idServicios;
	private String nombreComercial;
	private String nombreContacto;
	private String estatus;
	private String anticipo;
	private String finiquito;
	private String abono1;
	private String abono2;
	private String correoPyme;

	public int getIdServicios() {
		return idServicios;
	}

	public void setIdServicios(int idServicios) {
		this.idServicios = idServicios;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getAnticipo() {
		return anticipo;
	}

	public void setAnticipo(String anticipo) {
		this.anticipo = anticipo;
	}

	public String getFiniquito() {
		return finiquito;
	}

	public void setFiniquito(String finiquito) {
		this.finiquito = finiquito;
	}

	public String getAbono1() {
		return abono1;
	}

	public void setAbono1(String abono1) {
		this.abono1 = abono1;
	}

	public String getAbono2() {
		return abono2;
	}

	public void setAbono2(String abono2) {
		this.abono2 = abono2;
	}

	public String getCorreoPyme() {
		return correoPyme;
	}

	public void setCorreoPyme(String correoPyme) {
		this.correoPyme = correoPyme;
	}
}
