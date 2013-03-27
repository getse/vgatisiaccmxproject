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

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class ServiciosConsultoria extends AbstractBaseDTO{
	
	private int idConsultoria;
	private int idUsuario;
	private boolean bConsultoriaCuarenta;
	private boolean bConsultoriaSesenta;
	private boolean bConsultoriaOchenta;
	private String mensaje;
	
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
	public boolean isbConsultoriaCuarenta() {
		return bConsultoriaCuarenta;
	}
	public void setbConsultoriaCuarenta(boolean bConsultoriaCuarenta) {
		this.bConsultoriaCuarenta = bConsultoriaCuarenta;
	}
	public boolean isbConsultoriaSesenta() {
		return bConsultoriaSesenta;
	}
	public void setbConsultoriaSesenta(boolean bConsultoriaSesenta) {
		this.bConsultoriaSesenta = bConsultoriaSesenta;
	}
	public boolean isbConsultoriaOchenta() {
		return bConsultoriaOchenta;
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
}