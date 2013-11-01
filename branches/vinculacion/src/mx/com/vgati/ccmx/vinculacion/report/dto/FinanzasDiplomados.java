/*
 * FinanzasDiplomados.java        28/06/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.report.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

/**
 * 
 * 
 * @author Sergio Olivos
 * 
 */
@SuppressWarnings("serial")
public class FinanzasDiplomados extends AbstractBaseDTO {
	private int no;
	private String pyme;
	private String tractora;
	private int participantesCultura;
	private int participantesManufactura;
	private int particpantesPlaneacion;
	private int participantesComercio;
	private int generacion;
	private String pago;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getPyme() {
		return pyme;
	}

	public void setPyme(String pyme) {
		this.pyme = pyme;
	}

	public String getTractora() {
		return tractora;
	}

	public void setTractora(String tractora) {
		this.tractora = tractora;
	}

	public int getParticipantesCultura() {
		return participantesCultura;
	}

	public void setParticipantesCultura(int participantesCultura) {
		this.participantesCultura = participantesCultura;
	}

	public int getParticipantesManufactura() {
		return participantesManufactura;
	}

	public void setParticipantesManufactura(int participantesManufactura) {
		this.participantesManufactura = participantesManufactura;
	}

	public int getParticpantesPlaneacion() {
		return particpantesPlaneacion;
	}

	public void setParticpantesPlaneacion(int particpantesPlaneacion) {
		this.particpantesPlaneacion = particpantesPlaneacion;
	}

	public int getParticipantesComercio() {
		return participantesComercio;
	}

	public void setParticipantesComercio(int participantesComercio) {
		this.participantesComercio = participantesComercio;
	}

	public int getGeneracion() {
		return generacion;
	}

	public void setGeneracion(int generacion) {
		this.generacion = generacion;
	}

	public String getPago() {
		return pago;
	}

	public void setPago(String pago) {
		this.pago = pago;
	}
}
