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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class ServiciosDiplomado extends AbstractBaseDTO{
	
	private int idDiplomado;
	private int idUsuario;
	private String titulo;
	private Date fecha;
	private String mensaje;
	public InputStream archivo1;
	public int idArchivo1;
	public String archivo1ContentType;
	public String archivo1FileName;
	
	public int getIdDiplomado() {
		return idDiplomado;
	}
	public void setIdDiplomado(int idDiplomado) {
		this.idDiplomado = idDiplomado;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public InputStream getArchivo1() {
		return archivo1;
	}
	public void setArchivo1(InputStream archivo1) {
		this.archivo1 = archivo1;
	}
	public void setArchivo1(File archivo1) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo1.getCanonicalPath());
			this.archivo1 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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