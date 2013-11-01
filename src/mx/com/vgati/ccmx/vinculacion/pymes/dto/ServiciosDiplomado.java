/*
 * ServiciosDiplomado.java        23/03/2013
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

import mx.com.vgati.ccmx.vinculacion.dto.Archivos;
import mx.com.vgati.framework.dto.AbstractBaseDTO;
import mx.com.vgati.framework.util.Null;

/**
 * 
 * 
 * @author Omar Hernandez
 * 
 */
@SuppressWarnings("serial")
public class ServiciosDiplomado extends AbstractBaseDTO {

	private int idServiciosDiplomado;
	private int idDiplomado;
	private int idUsuario;
	private List<Asistentes> asistentes;
	private Archivos archivos;
	private File rfc;
	public int idRfc;
	public String rfcContentType;
	public String rfcFileName;

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

	public Archivos getArchivos() {
		return archivos;
	}

	public void setArchivos(Archivos archivos) {
		this.archivos = archivos;
	}

	public File getRfc() {
		return rfc;
	}

	public void setRfc(File rfc) {
		this.rfc = rfc;
	}

	public int getIdRfc() {
		return idRfc;
	}

	public void setIdRfc(int idRfc) {
		this.idRfc = idRfc;
	}

	public String getRfcContentType() {
		return rfcContentType;
	}

	public void setRfcContentType(String rfcContentType) {
		this.rfcContentType = rfcContentType;
	}

	public String getRfcFileName() {
		return rfcFileName;
	}

	public void setRfcFileName(String rfcFileName) {
		this.rfcFileName = rfcFileName;
	}
}