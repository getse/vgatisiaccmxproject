/*
 * RelPyMEsTractoras.java        01/03/2013
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

@SuppressWarnings("serial")
public class RelPyMEsTractoras extends AbstractBaseDTO {
	
	private int idPyMETractora;
	private int idUsuarioTractora;
	private int idUsuarioPyME;
	private int calificacion;
	private String comentario;
	
	public int getIdPyMETractora() {
		return idPyMETractora;
	}
	public void setIdPyMETractora(int idPyMETractora) {
		this.idPyMETractora = idPyMETractora;
	}
	public int getIdUsuarioTractora() {
		return idUsuarioTractora;
	}
	public void setIdUsuarioTractora(int idUsuarioTractora) {
		this.idUsuarioTractora = idUsuarioTractora;
	}
	public int getIdUsuarioPyME() {
		return idUsuarioPyME;
	}
	public void setIdUsuarioPyME(int idUsuarioPyME) {
		this.idUsuarioPyME = idUsuarioPyME;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}