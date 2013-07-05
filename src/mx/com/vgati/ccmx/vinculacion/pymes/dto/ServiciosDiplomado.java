/*
 * ServiciosDiplomado.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.dto;

import java.io.File;
import java.util.List;

import mx.com.vgati.framework.dto.AbstractBaseDTO;
import mx.com.vgati.framework.util.Null;

@SuppressWarnings("serial")
public class ServiciosDiplomado extends AbstractBaseDTO{
	
	private int idServiciosDiplomado;
	private int idDiplomado;
	private int idUsuario;
	private List<Asistentes> asistentes;
	private File archivo1;
	public int idArchivo1;
	public String archivo1ContentType;
	public String archivo1FileName;

	public int getIdServiciosDiplomado() {
		return idServiciosDiplomado;
	}
	public void setIdServiciosDiplomado(int idServiciosDiplomado) {
		this.idServiciosDiplomado = idServiciosDiplomado;
	}
	public void setIdServiciosDiplomado(String idServiciosDiplomado) {
		String id = Null.free(idServiciosDiplomado);
		this.idServiciosDiplomado = Integer.parseInt(id.isEmpty() ? "0" : id);
	}	
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
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public List<Asistentes> getAsistentes() {
		return asistentes;
	}
	public void setAsistentes(List<Asistentes> asistentes) {
		this.asistentes = asistentes;
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