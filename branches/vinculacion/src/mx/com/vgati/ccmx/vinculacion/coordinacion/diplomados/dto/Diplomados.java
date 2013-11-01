/*
 * Diplomados.java        24/04/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;
import mx.com.vgati.framework.util.Null;

/**
 * 
 * 
 * @author Omar Hernandez
 * 
 */
@SuppressWarnings("serial")
public class Diplomados extends AbstractBaseDTO {

	private int idDiplomado;
	private String tema;
	private int generacion;
	private int year;

	public int getIdDiplomado() {
		return idDiplomado;
	}

	public void setIdDiplomado(int idDiplomado) {
		this.idDiplomado = idDiplomado;
	}

	public void setIdDiplomado(String idDiplomado) {
		String id = Null.free(idDiplomado);
		this.idDiplomado = Integer.parseInt(id.isEmpty() ? "0" : id);
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public int getGeneracion() {
		return generacion;
	}

	public void setGeneracion(int generacion) {
		this.generacion = generacion;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}