/*
 * Diplomado.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.dto;

import java.sql.Date;

@SuppressWarnings("serial")
public class Diplomado extends AbstractBaseDTO {

	private int idDiplomado;
	private String tema;
	private int generacion;
	private String ubicacion;
	private Date fecha;
	private String url;

	public int getIdDiplomado() {
		return idDiplomado;
	}
	public void setIdDiplomado(int idDiplomado) {
		this.idDiplomado = idDiplomado;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public int getGeneracion() {
		return generacion;
	}
	public void setGeneracion(int generacion) {
		this.generacion = generacion;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}