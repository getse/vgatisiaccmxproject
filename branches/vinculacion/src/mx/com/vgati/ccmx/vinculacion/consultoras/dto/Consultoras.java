/*
 * Consultoras.java        07/04/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.dto;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Telefonos;
import mx.com.vgati.framework.dto.AbstractBaseDTO;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class Consultoras extends AbstractBaseDTO {

	private int idConsultora;
	private int idUsuario;
	private int idUsuarioPadre;
	private int idConsultoraPadre;
	private String empresa;
	private String nombreContacto;
	private String appPaternoContacto;
	private String appMaternoContacto;
	private String correoElectronico;
	private String password;
	private double costoAnticipo;
	private double costoAbono1;
	private double costoAbono2;
	private double costoFiniquito;
	private List<Telefonos> telefonos;

	public int getIdConsultora() {
		return idConsultora;
	}

	public void setIdConsultora(int idConsultora) {
		this.idConsultora = idConsultora;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdUsuarioPadre() {
		return idUsuarioPadre;
	}

	public void setIdUsuarioPadre(int idUsuarioPadre) {
		this.idUsuarioPadre = idUsuarioPadre;
	}

	public int getIdConsultoraPadre() {
		return idConsultoraPadre;
	}

	public void setIdConsultoraPadre(int idConsultoraPadre) {
		this.idConsultoraPadre = idConsultoraPadre;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getAppPaternoContacto() {
		return appPaternoContacto;
	}

	public void setAppPaternoContacto(String appPaternoContacto) {
		this.appPaternoContacto = appPaternoContacto;
	}

	public String getAppMaternoContacto() {
		return appMaternoContacto;
	}

	public void setAppMaternoContacto(String appMaternoContacto) {
		this.appMaternoContacto = appMaternoContacto;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Telefonos> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<Telefonos> telefonos) {
		this.telefonos = telefonos;
	}

	public double getCostoAnticipo() {
		return costoAnticipo;
	}

	public void setCostoAnticipo(double costoAnticipo) {
		this.costoAnticipo = costoAnticipo;
	}

	public double getCostoAbono1() {
		return costoAbono1;
	}

	public void setCostoAbono1(double costoAbono1) {
		this.costoAbono1 = costoAbono1;
	}

	public double getCostoAbono2() {
		return costoAbono2;
	}

	public void setCostoAbono2(double costoAbono2) {
		this.costoAbono2 = costoAbono2;
	}

	public double getCostoFiniquito() {
		return costoFiniquito;
	}

	public void setCostoFiniquito(double costoFiniquito) {
		this.costoFiniquito = costoFiniquito;
	}
}