/*
 * Filtros.java        08/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.report.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

/**
 * 
 * 
 * @author Sergio Olivos
 * 
 */
@SuppressWarnings("serial")
public class Filtros extends AbstractBaseDTO {

	private int id;
	private int filtro1;
	private int filtro2;
	private int filtro3;
	private int filtro4;
	private int filtro5;
	private int permisos;
	private String cedula;
	private String estatus;
	private Date sesionInformativa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFiltro1() {
		return filtro1;
	}

	public void setFiltro1(int filtro1) {
		this.filtro1 = filtro1;
	}

	public int getFiltro2() {
		return filtro2;
	}

	public void setFiltro2(int filtro2) {
		this.filtro2 = filtro2;
	}

	public int getFiltro3() {
		return filtro3;
	}

	public void setFiltro3(int filtro3) {
		this.filtro3 = filtro3;
	}

	public int getFiltro4() {
		return filtro4;
	}

	public void setFiltro4(int filtro4) {
		this.filtro4 = filtro4;
	}

	public int getFiltro5() {
		return filtro5;
	}

	public void setFiltro5(int filtro5) {
		this.filtro5 = filtro5;
	}

	public int getPermisos() {
		return permisos;
	}

	public void setPermisos(int permisos) {
		this.permisos = permisos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getSesionInformativa() {
		return sesionInformativa;
	}

	public void setSesionInformativa(Date sesionInformativa) {
		this.sesionInformativa = sesionInformativa;
	}

	public void setSesionInformativa(String sesionInformativa) {
		try {
			SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd",
					new Locale("es", "ES"));
			this.sesionInformativa = new java.sql.Date(sdf.parse(
					sesionInformativa).getTime());
		} catch (Exception ex) {
			this.sesionInformativa = null;
		}
	}
}
