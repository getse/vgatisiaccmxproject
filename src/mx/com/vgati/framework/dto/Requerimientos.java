/*
 * Requerimientos.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.dto;

import java.util.Date;

/**
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class Requerimientos extends AbstractBaseDTO {

	private int idRequerimiento;
	private int idTractora;
	private String requerimiento;
	private String descRequerimiento;
	private Date fechaInicio;
	private Date fechaFin;
	private Respuesta respuesta;
	private String tipoProducto;

	public int getIdRequerimiento() {
		return idRequerimiento;
	}

	public void setIdRequerimiento(int idRequerimiento) {
		this.idRequerimiento = idRequerimiento;
	}

	public int getIdTractora() {
		return idTractora;
	}

	public void setIdTractora(int idTractora) {
		this.idTractora = idTractora;
	}

	public String getRequerimiento() {
		return requerimiento;
	}

	public void setRequerimiento(String requerimiento) {
		this.requerimiento = requerimiento;
	}

	public String getDescRequerimiento() {
		return descRequerimiento;
	}

	public void setDescRequerimiento(String descRequerimiento) {
		this.descRequerimiento = descRequerimiento;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	public Respuesta getRespuesta() {
		return respuesta;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

}
