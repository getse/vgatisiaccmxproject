/*
 * FiltrosGenerales.java        23/05/2013
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
public class FiltrosGenerales extends AbstractBaseDTO {

	private int id;
	private String campoString;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCampoString() {
		return campoString;
	}

	public void setCampoString(String campoString) {
		this.campoString = campoString;
	}

}
