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

import java.io.File;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class RelPyMEsTractoras extends AbstractBaseDTO {
	
	private int idPyMETractora;
	private int idUsuarioTractora;
	private int idUsuarioPyME;
	private int calificacion;
	private String comentario;
	
	private File archivo1;
	public int idArchivo1;
	public String archivo1ContentType;
	public String archivo1FileName;
	
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
	public File getArchivo1() {
		return archivo1;
	}
	public void setArchivo1(File archivo1) {
		this.archivo1 = archivo1;
	}
	public int getIdArchivo1() {
		return idArchivo1;
	}
	public void setIdArchivo1(int idArchivo1) {
		this.idArchivo1 = idArchivo1;
	}
	public String getArchivo1ContentType() {
		return archivo1ContentType;
	}
	public void setArchivo1ContentType(String archivo1ContentType) {
		this.archivo1ContentType = archivo1ContentType;
	}
	public String getArchivo1FileName() {
		return archivo1FileName;
	}
	public void setArchivo1FileName(String archivo1FileName) {
		this.archivo1FileName = archivo1FileName;
	}
}