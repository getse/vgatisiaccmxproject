package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto;

import java.util.Date;

import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;

public class Sesiones {
	private int idSesion;
	private int idDiplomado;
	private String expositor;
	private String sala;
	private Domicilios domicilios;
	private Date fecha;
	private int hora;
	private int minuto;
	private String instructor;
	private String info;
	private int sesion;
	public int getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}
	public int getIdDiplomado() {
		return idDiplomado;
	}
	public void setIdDiplomado(int idDiplomado) {
		this.idDiplomado = idDiplomado;
	}
	public String getExpositor() {
		return expositor;
	}
	public void setExpositor(String expositor) {
		this.expositor = expositor;
	}
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public Domicilios getDomicilios() {
		return domicilios;
	}
	public void setDomicilios(Domicilios domicilios) {
		this.domicilios = domicilios;
	}
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		this.hora = hora;
	}
	public int getMinuto() {
		return minuto;
	}
	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getSesion() {
		return sesion;
	}
	public void setSesion(int sesion) {
		this.sesion = sesion;
	}
}
