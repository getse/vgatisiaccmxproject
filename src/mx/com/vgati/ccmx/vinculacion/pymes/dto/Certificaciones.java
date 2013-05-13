/*
 * Certificaciones.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.dto;

import java.sql.Date;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

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

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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

	public void setFechaCertificacion(String fechaCertificacion) {
		this.fechaCertificacion = Date.valueOf(fechaCertificacion);
	}

}
