/*
 * CCMXParticipantes.java        08/05/2013
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
public class CCMXParticipantes extends AbstractBaseDTO {

	private int no;
	private String pyme;
	private String giro;
	private String estatus;
	private String fechaInicio;
	private String fechaTermino;
	private String anoAtencion;
	private int participantesDiplomadoCultOrg;
	private int participantesDiplomadoReduCos;
	private int participantesDiplomadoPlanIno;
	private int participantesDiplomadoEstrCom;
	private String sesionInformativa;
	private String radarPromAnt;
	private String radarPromDes;

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

	public String getGiro() {
		return giro;
	}

	public void setGiro(String giro) {
		this.giro = giro;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(String fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public String getAnoAtencion() {
		return anoAtencion;
	}

	public void setAnoAtencion(String anoAtencion) {
		this.anoAtencion = anoAtencion;
	}

	public int getParticipantesDiplomadoCultOrg() {
		return participantesDiplomadoCultOrg;
	}

	public void setParticipantesDiplomadoCultOrg(
			int participantesDiplomadoCultOrg) {
		this.participantesDiplomadoCultOrg = participantesDiplomadoCultOrg;
	}

	public int getParticipantesDiplomadoReduCos() {
		return participantesDiplomadoReduCos;
	}

	public void setParticipantesDiplomadoReduCos(
			int participantesDiplomadoReduCos) {
		this.participantesDiplomadoReduCos = participantesDiplomadoReduCos;
	}

	public int getParticipantesDiplomadoPlanIno() {
		return participantesDiplomadoPlanIno;
	}

	public void setParticipantesDiplomadoPlanIno(
			int participantesDiplomadoPlanIno) {
		this.participantesDiplomadoPlanIno = participantesDiplomadoPlanIno;
	}

	public int getParticipantesDiplomadoEstrCom() {
		return participantesDiplomadoEstrCom;
	}

	public void setParticipantesDiplomadoEstrCom(
			int participantesDiplomadoEstrCom) {
		this.participantesDiplomadoEstrCom = participantesDiplomadoEstrCom;
	}

	public String getSesionInformativa() {
		return sesionInformativa;
	}

	public void setSesionInformativa(String sesionInformativa) {
		this.sesionInformativa = sesionInformativa;
	}

	public String getRadarPromAnt() {
		return radarPromAnt;
	}

	public void setRadarPromAnt(String radarPromAnt) {
		this.radarPromAnt = radarPromAnt;
	}

	public String getRadarPromDes() {
		return radarPromDes;
	}

	public void setRadarPromDes(String radarPromDes) {
		this.radarPromDes = radarPromDes;
	}

}
