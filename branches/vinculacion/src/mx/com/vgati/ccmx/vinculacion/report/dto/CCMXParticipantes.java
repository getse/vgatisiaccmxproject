package mx.com.vgati.ccmx.vinculacion.report.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class CCMXParticipantes extends AbstractBaseDTO {
	private int no;
	private String pyme;
	private String giro;
	private String estatus;
	private String fechaInicio;
	private String fechaTermino;
	private String anoAtencion;
	private String participantesDiplomadoCultOrg;
	private String participantesDiplomadoReduCos;
	private String participantesDiplomadoPlanIno;
	private String participantesDiplomadoEstrCom;
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
	public String getParticipantesDiplomadoCultOrg() {
		return participantesDiplomadoCultOrg;
	}
	public void setParticipantesDiplomadoCultOrg(
			String participantesDiplomadoCultOrg) {
		this.participantesDiplomadoCultOrg = participantesDiplomadoCultOrg;
	}
	public String getParticipantesDiplomadoReduCos() {
		return participantesDiplomadoReduCos;
	}
	public void setParticipantesDiplomadoReduCos(
			String participantesDiplomadoReduCos) {
		this.participantesDiplomadoReduCos = participantesDiplomadoReduCos;
	}
	public String getParticipantesDiplomadoPlanIno() {
		return participantesDiplomadoPlanIno;
	}
	public void setParticipantesDiplomadoPlanIno(
			String participantesDiplomadoPlanIno) {
		this.participantesDiplomadoPlanIno = participantesDiplomadoPlanIno;
	}
	public String getParticipantesDiplomadoEstrCom() {
		return participantesDiplomadoEstrCom;
	}
	public void setParticipantesDiplomadoEstrCom(
			String participantesDiplomadoEstrCom) {
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
