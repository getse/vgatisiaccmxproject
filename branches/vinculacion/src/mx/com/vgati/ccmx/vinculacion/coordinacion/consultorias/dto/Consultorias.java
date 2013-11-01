/*
 * Consultorias.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class Consultorias extends AbstractBaseDTO {

	private int idConsultoria;
	private int idUsuario;

	public int getIdConsultoria() {
		return idConsultoria;
	}

	public void setIdConsultoria(int idConsultoria) {
		this.idConsultoria = idConsultoria;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}