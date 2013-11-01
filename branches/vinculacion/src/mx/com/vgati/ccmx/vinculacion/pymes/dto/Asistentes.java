/*
 * Asistentes.java        23/03/2013
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

/**
 * 
 * 
 * @author Omar Hernandez
 * 
 */
@SuppressWarnings("serial")
public class Asistentes extends AbstractBaseDTO {

	private int idAsistente;
	private int idServiciosDiplomado;
	private String nombre;
	private String appPaterno;
	private String appMaterno;
	private String telefono;
	private String correoElectronico;
	private Boolean factura;
	private Boolean pago;
	private String cargo;
	private String numeroPago;

	public int getIdAsistente() {
		return idAsistente;
	}

	public void setIdAsistente(int idAsistente) {
		this.idAsistente = idAsistente;
	}

	public int getIdServiciosDiplomado() {
		return idServiciosDiplomado;
	}

	public void setIdServiciosDiplomado(int idServiciosDiplomado) {
		this.idServiciosDiplomado = idServiciosDiplomado;
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

	public Boolean getFactura() {
		return factura;
	}

	public void setFactura(Boolean factura) {
		this.factura = factura;
	}

	public Boolean getPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getNumeroPago() {
		return numeroPago;
	}

	public void setNumeroPago(String numeroPago) {
		this.numeroPago = numeroPago;
	}
}
