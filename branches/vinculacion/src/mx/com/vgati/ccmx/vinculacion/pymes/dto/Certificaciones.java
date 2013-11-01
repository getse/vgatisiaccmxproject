/*
 * Certificaciones.java        23/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.dto;

import java.util.Date;

import mx.com.vgati.framework.dto.AbstractBaseDTO;
import mx.com.vgati.framework.util.Null;

/**
 * 
 * 
 * @author Omar Hernandez
 * 
 */
@SuppressWarnings("serial")
public class Certificaciones extends AbstractBaseDTO {

	private int idCertificado;
	private int idUsuario;
	private String certificacion;
	private String institutoCertificador;
	private Date fechaCertificacion;

	public int getIdCertificado() {
		return idCertificado;
	}

	public void setIdCertificado(int idCertificado) {
		this.idCertificado = idCertificado;
	}

	public void setIdCertificado(String idCertificado) {
		String id = Null.free(idCertificado);
		this.idCertificado = Integer.parseInt(id.isEmpty() ? "0" : id);
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		String id = Null.free(idUsuario);
		this.idUsuario = Integer.parseInt(id.isEmpty() ? "0" : id);
	}

	public String getCertificacion() {
		return certificacion;
	}

	public void setCertificacion(String certificacion) {
		this.certificacion = certificacion;
	}

	public String getInstitutoCertificador() {
		return institutoCertificador;
	}

	public void setInstitutoCertificador(String institutoCertificador) {
		this.institutoCertificador = institutoCertificador;
	}

	public Date getFechaCertificacion() {
		return fechaCertificacion;
	}

	public void setFechaCertificacion(Date fechaCertificacion) {
		this.fechaCertificacion = fechaCertificacion;
	}
}
