/*
 * IndicadoresPyMEs.java        23/05/2013
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
public class IndicadoresPyMEs extends AbstractBaseDTO {

	private int no;
	private String pyme;
	private String anoAtencion;
	private String cedula;
	private String consultor;
	private String estatus;
	private String horasConsultoria;
	private float promedioAntes;
	private float promedioDespues;
	private int mdoAntes;
	private int mdoDespues;
	private int operacionAntes;
	private int operacionDespues;
	private int finanzasAntes;
	private int finanzasDespues;
	private int rhAntes;
	private int rhDespues;
	private int admonAntes;
	private int admonDespues;
	private int ingresosAntes;
	private int ingresosDespues;
	private int clientesAntes;
	private int clientesDespues;
	private int empleadosAntes;
	private int empleadosDespues;
	private int ventasAntes;
	private int ventasDespues;
	private String monetarios1;
	private String monetarios2;
	private String monetarios3;
	private String monetarios4;
	private String monetarios21;
	private String monetarios22;
	private String monetarios23;
	private String monetarios24;
	private String defectos1;
	private String defectos2;
	private String defectos3;
	private String defectos4;
	private String defectos21;
	private String defectos22;
	private String defectos23;
	private String defectos24;
	private String ahorro1;
	private String ahorro2;
	private String ahorro3;
	private String ahorro4;
	private String ahorro21;
	private String ahorro22;
	private String ahorro23;
	private String ahorro24;
	private String postVenta1;
	private String postVenta2;
	private String postVenta3;
	private String postVenta4;
	private String postVenta21;
	private String postVenta22;
	private String postVenta23;
	private String postVenta24;
	private String capacidadPyme1;
	private String capacidadPyme2;
	private String capacidadPyme3;
	private String capacidadPyme4;

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

	public String getAnoAtencion() {
		return anoAtencion;
	}

	public void setAnoAtencion(String anoAtencion) {
		this.anoAtencion = anoAtencion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getConsultor() {
		return consultor;
	}

	public void setConsultor(String consultor) {
		this.consultor = consultor;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getHorasConsultoria() {
		return horasConsultoria;
	}

	public void setHorasConsultoria(String horasConsultoria) {
		this.horasConsultoria = horasConsultoria;
	}

	public int getClientesAntes() {
		return clientesAntes;
	}

	public void setClientesAntes(int clientesAntes) {
		this.clientesAntes = clientesAntes;
	}

	public int getClientesDespues() {
		return clientesDespues;
	}

	public void setClientesDespues(int clientesDespues) {
		this.clientesDespues = clientesDespues;
	}

	public int getEmpleadosAntes() {
		return empleadosAntes;
	}

	public void setEmpleadosAntes(int empleadosAntes) {
		this.empleadosAntes = empleadosAntes;
	}

	public int getEmpleadosDespues() {
		return empleadosDespues;
	}

	public void setEmpleadosDespues(int empleadosDespues) {
		this.empleadosDespues = empleadosDespues;
	}

	public int getVentasAntes() {
		return ventasAntes;
	}

	public void setVentasAntes(int ventasAntes) {
		this.ventasAntes = ventasAntes;
	}

	public int getVentasDespues() {
		return ventasDespues;
	}

	public void setVentasDespues(int ventasDespues) {
		this.ventasDespues = ventasDespues;
	}

	public int getAdmonAntes() {
		return admonAntes;
	}

	public void setAdmonAntes(int admonAntes) {
		this.admonAntes = admonAntes;
	}

	public int getMdoAntes() {
		return mdoAntes;
	}

	public void setMdoAntes(int mdoAntes) {
		this.mdoAntes = mdoAntes;
	}

	public int getFinanzasAntes() {
		return finanzasAntes;
	}

	public void setFinanzasAntes(int finanzasAntes) {
		this.finanzasAntes = finanzasAntes;
	}

	public int getOperacionAntes() {
		return operacionAntes;
	}

	public void setOperacionAntes(int operacionAntes) {
		this.operacionAntes = operacionAntes;
	}

	public int getRhAntes() {
		return rhAntes;
	}

	public void setRhAntes(int rhAntes) {
		this.rhAntes = rhAntes;
	}

	public float getPromedioAntes() {
		return promedioAntes;
	}

	public void setPromedioAntes(float promedioAntes) {
		this.promedioAntes = promedioAntes;
	}

	public int getAdmonDespues() {
		return admonDespues;
	}

	public void setAdmonDespues(int admonDespues) {
		this.admonDespues = admonDespues;
	}

	public int getMdoDespues() {
		return mdoDespues;
	}

	public void setMdoDespues(int mdoDespues) {
		this.mdoDespues = mdoDespues;
	}

	public int getFinanzasDespues() {
		return finanzasDespues;
	}

	public void setFinanzasDespues(int finanzasDespues) {
		this.finanzasDespues = finanzasDespues;
	}

	public int getOperacionDespues() {
		return operacionDespues;
	}

	public void setOperacionDespues(int operacionDespues) {
		this.operacionDespues = operacionDespues;
	}

	public int getRhDespues() {
		return rhDespues;
	}

	public void setRhDespues(int rhDespues) {
		this.rhDespues = rhDespues;
	}

	public float getPromedioDespues() {
		return promedioDespues;
	}

	public void setPromedioDespues(float promedioDespues) {
		this.promedioDespues = promedioDespues;
	}

	public int getIngresosAntes() {
		return ingresosAntes;
	}

	public void setIngresosAntes(int ingresosAntes) {
		this.ingresosAntes = ingresosAntes;
	}

	public int getIngresosDespues() {
		return ingresosDespues;
	}

	public void setIngresosDespues(int ingresosDespues) {
		this.ingresosDespues = ingresosDespues;
	}

	public String getMonetarios1() {
		return monetarios1;
	}

	public void setMonetarios1(String monetarios1) {
		this.monetarios1 = monetarios1;
	}

	public String getMonetarios2() {
		return monetarios2;
	}

	public void setMonetarios2(String monetarios2) {
		this.monetarios2 = monetarios2;
	}

	public String getMonetarios3() {
		return monetarios3;
	}

	public void setMonetarios3(String monetarios3) {
		this.monetarios3 = monetarios3;
	}

	public String getMonetarios4() {
		return monetarios4;
	}

	public void setMonetarios4(String monetarios4) {
		this.monetarios4 = monetarios4;
	}

	public String getMonetarios21() {
		return monetarios21;
	}

	public void setMonetarios21(String monetarios21) {
		this.monetarios21 = monetarios21;
	}

	public String getMonetarios22() {
		return monetarios22;
	}

	public void setMonetarios22(String monetarios22) {
		this.monetarios22 = monetarios22;
	}

	public String getMonetarios23() {
		return monetarios23;
	}

	public void setMonetarios23(String monetarios23) {
		this.monetarios23 = monetarios23;
	}

	public String getMonetarios24() {
		return monetarios24;
	}

	public void setMonetarios24(String monetarios24) {
		this.monetarios24 = monetarios24;
	}

	public String getDefectos1() {
		return defectos1;
	}

	public void setDefectos1(String defectos1) {
		this.defectos1 = defectos1;
	}

	public String getDefectos2() {
		return defectos2;
	}

	public void setDefectos2(String defectos2) {
		this.defectos2 = defectos2;
	}

	public String getDefectos3() {
		return defectos3;
	}

	public void setDefectos3(String defectos3) {
		this.defectos3 = defectos3;
	}

	public String getDefectos4() {
		return defectos4;
	}

	public void setDefectos4(String defectos4) {
		this.defectos4 = defectos4;
	}

	public String getDefectos21() {
		return defectos21;
	}

	public void setDefectos21(String defectos21) {
		this.defectos21 = defectos21;
	}

	public String getDefectos22() {
		return defectos22;
	}

	public void setDefectos22(String defectos22) {
		this.defectos22 = defectos22;
	}

	public String getDefectos23() {
		return defectos23;
	}

	public void setDefectos23(String defectos23) {
		this.defectos23 = defectos23;
	}

	public String getDefectos24() {
		return defectos24;
	}

	public void setDefectos24(String defectos24) {
		this.defectos24 = defectos24;
	}

	public String getAhorro1() {
		return ahorro1;
	}

	public void setAhorro1(String ahorro1) {
		this.ahorro1 = ahorro1;
	}

	public String getAhorro2() {
		return ahorro2;
	}

	public void setAhorro2(String ahorro2) {
		this.ahorro2 = ahorro2;
	}

	public String getAhorro3() {
		return ahorro3;
	}

	public void setAhorro3(String ahorro3) {
		this.ahorro3 = ahorro3;
	}

	public String getAhorro4() {
		return ahorro4;
	}

	public void setAhorro4(String ahorro4) {
		this.ahorro4 = ahorro4;
	}

	public String getAhorro21() {
		return ahorro21;
	}

	public void setAhorro21(String ahorro21) {
		this.ahorro21 = ahorro21;
	}

	public String getAhorro22() {
		return ahorro22;
	}

	public void setAhorro22(String ahorro22) {
		this.ahorro22 = ahorro22;
	}

	public String getAhorro23() {
		return ahorro23;
	}

	public void setAhorro23(String ahorro23) {
		this.ahorro23 = ahorro23;
	}

	public String getAhorro24() {
		return ahorro24;
	}

	public void setAhorro24(String ahorro24) {
		this.ahorro24 = ahorro24;
	}

	public String getPostVenta1() {
		return postVenta1;
	}

	public void setPostVenta1(String postVenta1) {
		this.postVenta1 = postVenta1;
	}

	public String getPostVenta2() {
		return postVenta2;
	}

	public void setPostVenta2(String postVenta2) {
		this.postVenta2 = postVenta2;
	}

	public String getPostVenta3() {
		return postVenta3;
	}

	public void setPostVenta3(String postVenta3) {
		this.postVenta3 = postVenta3;
	}

	public String getPostVenta4() {
		return postVenta4;
	}

	public void setPostVenta4(String postVenta4) {
		this.postVenta4 = postVenta4;
	}

	public String getPostVenta21() {
		return postVenta21;
	}

	public void setPostVenta21(String postVenta21) {
		this.postVenta21 = postVenta21;
	}

	public String getPostVenta22() {
		return postVenta22;
	}

	public void setPostVenta22(String postVenta22) {
		this.postVenta22 = postVenta22;
	}

	public String getPostVenta23() {
		return postVenta23;
	}

	public void setPostVenta23(String postVenta23) {
		this.postVenta23 = postVenta23;
	}

	public String getPostVenta24() {
		return postVenta24;
	}

	public void setPostVenta24(String postVenta24) {
		this.postVenta24 = postVenta24;
	}

	public String getCapacidadPyme1() {
		return capacidadPyme1;
	}

	public void setCapacidadPyme1(String capacidadPyme1) {
		this.capacidadPyme1 = capacidadPyme1;
	}

	public String getCapacidadPyme2() {
		return capacidadPyme2;
	}

	public void setCapacidadPyme2(String capacidadPyme2) {
		this.capacidadPyme2 = capacidadPyme2;
	}

	public String getCapacidadPyme3() {
		return capacidadPyme3;
	}

	public void setCapacidadPyme3(String capacidadPyme3) {
		this.capacidadPyme3 = capacidadPyme3;
	}

	public String getCapacidadPyme4() {
		return capacidadPyme4;
	}

	public void setCapacidadPyme4(String capacidadPyme4) {
		this.capacidadPyme4 = capacidadPyme4;
	}
}
