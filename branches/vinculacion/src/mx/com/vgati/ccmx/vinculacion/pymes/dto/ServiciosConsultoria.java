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

import mx.com.vgati.framework.dto.AbstractBaseDTO;
import mx.com.vgati.framework.util.Null;

@SuppressWarnings("serial")
public class ServiciosConsultoria extends AbstractBaseDTO{
	
	private int idConsultoria;
	private int idUsuario;
	public boolean bConsultoriaVeinte;
	public boolean bConsultoriaCuarenta;
	public boolean bConsultoriaSesenta;
	public boolean bConsultoriaOchenta;
	private String mensaje;
	private File archivo;
	public InputStream archivo1;
	public int idArchivo1;
	public String archivo1ContentType;
	public String archivo1FileName;
	
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
	public boolean isbConsultoriaVeinte() {
		return bConsultoriaVeinte;
	}
	public void setbConsultoriaVeinte(String bConsultoriaVeinte) {
		this.bConsultoriaVeinte = Null.free(bConsultoriaVeinte).equals("true") ? true : false;
	}
	public void setbConsultoriaVeinte(boolean bConsultoriaVeinte) {
		this.bConsultoriaVeinte = bConsultoriaVeinte;
	}
	public boolean isbConsultoriaCuarenta() {
		return bConsultoriaCuarenta;
	}
	public void setbConsultoriaCuarenta(String bConsultoriaCuarenta) {
		this.bConsultoriaCuarenta = Null.free(bConsultoriaCuarenta).equals("true") ? true : false;
	}
	public void setbConsultoriaCuarenta(boolean bConsultoriaCuarenta) {
		this.bConsultoriaCuarenta = bConsultoriaCuarenta;
	}
	public boolean isbConsultoriaSesenta() {
		return bConsultoriaSesenta;
	}
	public void setbConsultoriaSesenta(String bConsultoriaSesenta) {
		this.bConsultoriaSesenta = Null.free(bConsultoriaSesenta).equals("true") ? true : false;
	}
	public void setbConsultoriaSesenta(boolean bConsultoriaSesenta) {
		this.bConsultoriaSesenta = bConsultoriaSesenta;
	}
	public boolean isbConsultoriaOchenta() {
		return bConsultoriaOchenta;
	}
	public void setbConsultoriaOchenta(String bConsultoriaOchenta) {
		this.bConsultoriaOchenta = Null.free(bConsultoriaOchenta).equals("true") ? true : false;
	}
	public void setbConsultoriaOchenta(boolean bConsultoriaOchenta) {
		this.bConsultoriaOchenta = bConsultoriaOchenta;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public File getArchivo() {
		return archivo;
	}
	public void setArchivo(File archivo) {
		this.archivo = archivo;
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