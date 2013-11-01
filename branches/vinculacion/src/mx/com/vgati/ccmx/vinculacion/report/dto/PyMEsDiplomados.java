/*
 * PyMEsDiplomados.java        28/06/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.report.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

/**
 * 
 * 
 * @author Sergio Olivos
 * 
 */
@SuppressWarnings("serial")
public class PyMEsDiplomados extends AbstractBaseDTO {
	private int no;
	private String nombre;
	private String pyme;
	private String tractora;
	private String diplomado;
	private int generacion;
	private int participantes;
	private String tractoraTotal;
	private String generacionToltal;
	private String total;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPyme() {
		return pyme;
	}

	public void setPyme(String pyme) {
		this.pyme = pyme;
	}

	public String getTractora() {
		return tractora;
	}

	public void setTractora(String tractora) {
		this.tractora = tractora;
	}

	public String getDiplomado() {
		return diplomado;
	}

	public void setDiplomado(String diplomado) {
		this.diplomado = diplomado;
	}

	public int getGeneracion() {
		return generacion;
	}

	public void setGeneracion(int generacion) {
		this.generacion = generacion;
	}

	public int getParticipantes() {
		return participantes;
	}

	public void setParticipantes(int participantes) {
		this.participantes = participantes;
	}

	public String getTractoraTotal() {
		return tractoraTotal;
	}

	public void setTractoraTotal(String tractoraTotal) {
		this.tractoraTotal = tractoraTotal;
	}

	public String getGeneracionToltal() {
		return generacionToltal;
	}

	public void setGeneracionToltal(String generacionToltal) {
		this.generacionToltal = generacionToltal;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
}
