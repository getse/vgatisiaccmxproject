/*
 * CCMXFinanzas.java        08/05/2013
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
public class CCMXFinanzas extends AbstractBaseDTO {

	private int no;
	private String pyme;
	private String consultora;
	private int anoAtencion;
	private String cedula;
	private String faturaAnticipo;
	private String faturaAbono1;
	private String faturaAbono2;
	private String facturaFiniquito;
	private int horasConsultoria;
	private String total;

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

	public String getConsultora() {
		return consultora;
	}

	public void setConsultora(String consultora) {
		this.consultora = consultora;
	}

	public int getAnoAtencion() {
		return anoAtencion;
	}

	public void setAnoAtencion(int anoAatencion) {
		this.anoAtencion = anoAatencion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getFaturaAnticipo() {
		return faturaAnticipo;
	}

	public void setFaturaAnticipo(String faturaAnticipo) {
		this.faturaAnticipo = faturaAnticipo;
	}

	public String getFaturaAbono1() {
		return faturaAbono1;
	}

	public void setFaturaAbono1(String faturaAbono1) {
		this.faturaAbono1 = faturaAbono1;
	}

	public String getFaturaAbono2() {
		return faturaAbono2;
	}

	public void setFaturaAbono2(String faturaAbono2) {
		this.faturaAbono2 = faturaAbono2;
	}

	public String getFacturaFiniquito() {
		return facturaFiniquito;
	}

	public void setFacturaFiniquito(String facturaFiniquito) {
		this.facturaFiniquito = facturaFiniquito;
	}

	public int getHorasConsultoria() {
		return horasConsultoria;
	}

	public void setHorasConsultoria(int horasConsultoria) {
		this.horasConsultoria = horasConsultoria;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
}
