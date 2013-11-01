/*
 * Indicadores.java        09/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.dto;

import java.io.File;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

/**
 * 
 * 
 * @author Omar Hernandez
 * 
 */
@SuppressWarnings("serial")
public class Indicadores extends AbstractBaseDTO {
	private int idIndicador;
	private int idPyME;
	private int idPyMETractora;
	private int ingresosAntes;
	private int ingresosDespues;
	private int clientesAntes;
	private int clientesDespues;
	private int empleadosAntes;
	private int empleadosDespues;
	private int egresosAntes;
	private int egresosDespues;
	private int idIndicadorTractora;
	private String resultadoCalculo;
	private String periodoRefMes;
	private int periodoRefAnio;
	private File archivo1;
	public int idArchivo1;
	public String archivo1ContentType;
	public String archivo1FileName;

	private String ahorrosMonetariosUnoEnero2012;
	private String ahorrosMonetariosUnoAbril2012;
	private String ahorrosMonetariosUnoJulio2012;
	private String ahorrosMonetariosUnoOctubre2012;
	private String ahorrosMonetariosDosEnero2012;
	private String ahorrosMonetariosDosAbril2012;
	private String ahorrosMonetariosDosJulio2012;
	private String ahorrosMonetariosDosOctubre2012;
	private String defectosUnoEnero2012;
	private String defectosUnoAbril2012;
	private String defectosUnoJulio2012;
	private String defectosUnoOctubre2012;
	private String defectosDosEnero2012;
	private String defectosDosAbril2012;
	private String defectosDosJulio2012;
	private String defectosDosOctubre2012;
	private String ahorroTiempoUnoEnero2012;
	private String ahorroTiempoUnoAbril2012;
	private String ahorroTiempoUnoJulio2012;
	private String ahorroTiempoUnoOctubre2012;
	private String ahorroTiempoDosEnero2012;
	private String ahorroTiempoDosAbril2012;
	private String ahorroTiempoDosJulio2012;
	private String ahorroTiempoDosOctubre2012;
	private String servicioUnoEnero2012;
	private String servicioUnoAbril2012;
	private String servicioUnoJulio2012;
	private String servicioUnoOctubre2012;
	private String servicioDosEnero2012;
	private String servicioDosAbril2012;
	private String servicioDosJulio2012;
	private String servicioDosOctubre2012;
	private String capacidadEnero2012;
	private String capacidadAbril2012;
	private String capacidadJulio2012;
	private String capacidadOctubre2012;

	private String ahorrosMonetariosUnoEnero2013;
	private String ahorrosMonetariosUnoAbril2013;
	private String ahorrosMonetariosUnoJulio2013;
	private String ahorrosMonetariosUnoOctubre2013;
	private String ahorrosMonetariosDosEnero2013;
	private String ahorrosMonetariosDosAbril2013;
	private String ahorrosMonetariosDosJulio2013;
	private String ahorrosMonetariosDosOctubre2013;
	private String defectosUnoEnero2013;
	private String defectosUnoAbril2013;
	private String defectosUnoJulio2013;
	private String defectosUnoOctubre2013;
	private String defectosDosEnero2013;
	private String defectosDosAbril2013;
	private String defectosDosJulio2013;
	private String defectosDosOctubre2013;
	private String ahorroTiempoUnoEnero2013;
	private String ahorroTiempoUnoAbril2013;
	private String ahorroTiempoUnoJulio2013;
	private String ahorroTiempoUnoOctubre2013;
	private String ahorroTiempoDosEnero2013;
	private String ahorroTiempoDosAbril2013;
	private String ahorroTiempoDosJulio2013;
	private String ahorroTiempoDosOctubre2013;
	private String servicioUnoEnero2013;
	private String servicioUnoAbril2013;
	private String servicioUnoJulio2013;
	private String servicioUnoOctubre2013;
	private String servicioDosEnero2013;
	private String servicioDosAbril2013;
	private String servicioDosJulio2013;
	private String servicioDosOctubre2013;
	private String capacidadEnero2013;
	private String capacidadAbril2013;
	private String capacidadJulio2013;
	private String capacidadOctubre2013;

	public int getIdIndicador() {
		return idIndicador;
	}

	public void setIdIndicador(int idIndicador) {
		this.idIndicador = idIndicador;
	}

	public int getIdPyME() {
		return idPyME;
	}

	public void setIdPyME(int idPyME) {
		this.idPyME = idPyME;
	}

	public int getIdPyMETractora() {
		return idPyMETractora;
	}

	public void setIdPyMETractora(int idPyMETractora) {
		this.idPyMETractora = idPyMETractora;
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

	public int getEgresosAntes() {
		return egresosAntes;
	}

	public void setEgresosAntes(int egresosAntes) {
		this.egresosAntes = egresosAntes;
	}

	public int getEgresosDespues() {
		return egresosDespues;
	}

	public void setEgresosDespues(int egresosDespues) {
		this.egresosDespues = egresosDespues;
	}

	public int getIdIndicadorTractora() {
		return idIndicadorTractora;
	}

	public void setIdIndicadorTractora(int idIndicadorTractora) {
		this.idIndicadorTractora = idIndicadorTractora;
	}

	public String getResultadoCalculo() {
		return resultadoCalculo;
	}

	public void setResultadoCalculo(String resultadoCalculo) {
		this.resultadoCalculo = resultadoCalculo;
	}

	public String getPeriodoRefMes() {
		return periodoRefMes;
	}

	public void setPeriodoRefMes(String periodoRefMes) {
		this.periodoRefMes = periodoRefMes;
	}

	public int getPeriodoRefAnio() {
		return periodoRefAnio;
	}

	public void setPeriodoRefAnio(int periodoRefAnio) {
		this.periodoRefAnio = periodoRefAnio;
	}

	public File getArchivo1() {
		return archivo1;
	}

	public void setArchivo1(File archivo1) {
		this.archivo1 = archivo1;
	}

	public int getIdArchivo1() {
		return idArchivo1;
	}

	public void setIdArchivo1(int idArchivo1) {
		this.idArchivo1 = idArchivo1;
	}

	public String getArchivo1ContentType() {
		return archivo1ContentType;
	}

	public void setArchivo1ContentType(String archivo1ContentType) {
		this.archivo1ContentType = archivo1ContentType;
	}

	public String getArchivo1FileName() {
		return archivo1FileName;
	}

	public void setArchivo1FileName(String archivo1FileName) {
		this.archivo1FileName = archivo1FileName;
	}

	public String getAhorrosMonetariosUnoEnero2012() {
		return ahorrosMonetariosUnoEnero2012;
	}

	public void setAhorrosMonetariosUnoEnero2012(
			String ahorrosMonetariosUnoEnero2012) {
		this.ahorrosMonetariosUnoEnero2012 = ahorrosMonetariosUnoEnero2012;
	}

	public String getAhorrosMonetariosUnoAbril2012() {
		return ahorrosMonetariosUnoAbril2012;
	}

	public void setAhorrosMonetariosUnoAbril2012(
			String ahorrosMonetariosUnoAbril2012) {
		this.ahorrosMonetariosUnoAbril2012 = ahorrosMonetariosUnoAbril2012;
	}

	public String getAhorrosMonetariosUnoJulio2012() {
		return ahorrosMonetariosUnoJulio2012;
	}

	public void setAhorrosMonetariosUnoJulio2012(
			String ahorrosMonetariosUnoJulio2012) {
		this.ahorrosMonetariosUnoJulio2012 = ahorrosMonetariosUnoJulio2012;
	}

	public String getAhorrosMonetariosUnoOctubre2012() {
		return ahorrosMonetariosUnoOctubre2012;
	}

	public void setAhorrosMonetariosUnoOctubre2012(
			String ahorrosMonetariosUnoOctubre2012) {
		this.ahorrosMonetariosUnoOctubre2012 = ahorrosMonetariosUnoOctubre2012;
	}

	public String getAhorrosMonetariosDosEnero2012() {
		return ahorrosMonetariosDosEnero2012;
	}

	public void setAhorrosMonetariosDosEnero2012(
			String ahorrosMonetariosDosEnero2012) {
		this.ahorrosMonetariosDosEnero2012 = ahorrosMonetariosDosEnero2012;
	}

	public String getAhorrosMonetariosDosAbril2012() {
		return ahorrosMonetariosDosAbril2012;
	}

	public void setAhorrosMonetariosDosAbril2012(
			String ahorrosMonetariosDosAbril2012) {
		this.ahorrosMonetariosDosAbril2012 = ahorrosMonetariosDosAbril2012;
	}

	public String getAhorrosMonetariosDosJulio2012() {
		return ahorrosMonetariosDosJulio2012;
	}

	public void setAhorrosMonetariosDosJulio2012(
			String ahorrosMonetariosDosJulio2012) {
		this.ahorrosMonetariosDosJulio2012 = ahorrosMonetariosDosJulio2012;
	}

	public String getAhorrosMonetariosDosOctubre2012() {
		return ahorrosMonetariosDosOctubre2012;
	}

	public void setAhorrosMonetariosDosOctubre2012(
			String ahorrosMonetariosDosOctubre2012) {
		this.ahorrosMonetariosDosOctubre2012 = ahorrosMonetariosDosOctubre2012;
	}

	public String getDefectosUnoEnero2012() {
		return defectosUnoEnero2012;
	}

	public void setDefectosUnoEnero2012(String defectosUnoEnero2012) {
		this.defectosUnoEnero2012 = defectosUnoEnero2012;
	}

	public String getDefectosUnoAbril2012() {
		return defectosUnoAbril2012;
	}

	public void setDefectosUnoAbril2012(String defectosUnoAbril2012) {
		this.defectosUnoAbril2012 = defectosUnoAbril2012;
	}

	public String getDefectosUnoJulio2012() {
		return defectosUnoJulio2012;
	}

	public void setDefectosUnoJulio2012(String defectosUnoJulio2012) {
		this.defectosUnoJulio2012 = defectosUnoJulio2012;
	}

	public String getDefectosUnoOctubre2012() {
		return defectosUnoOctubre2012;
	}

	public void setDefectosUnoOctubre2012(String defectosUnoOctubre2012) {
		this.defectosUnoOctubre2012 = defectosUnoOctubre2012;
	}

	public String getDefectosDosEnero2012() {
		return defectosDosEnero2012;
	}

	public void setDefectosDosEnero2012(String defectosDosEnero2012) {
		this.defectosDosEnero2012 = defectosDosEnero2012;
	}

	public String getDefectosDosAbril2012() {
		return defectosDosAbril2012;
	}

	public void setDefectosDosAbril2012(String defectosDosAbril2012) {
		this.defectosDosAbril2012 = defectosDosAbril2012;
	}

	public String getDefectosDosJulio2012() {
		return defectosDosJulio2012;
	}

	public void setDefectosDosJulio2012(String defectosDosJulio2012) {
		this.defectosDosJulio2012 = defectosDosJulio2012;
	}

	public String getDefectosDosOctubre2012() {
		return defectosDosOctubre2012;
	}

	public void setDefectosDosOctubre2012(String defectosDosOctubre2012) {
		this.defectosDosOctubre2012 = defectosDosOctubre2012;
	}

	public String getAhorroTiempoUnoEnero2012() {
		return ahorroTiempoUnoEnero2012;
	}

	public void setAhorroTiempoUnoEnero2012(String ahorroTiempoUnoEnero2012) {
		this.ahorroTiempoUnoEnero2012 = ahorroTiempoUnoEnero2012;
	}

	public String getAhorroTiempoUnoAbril2012() {
		return ahorroTiempoUnoAbril2012;
	}

	public void setAhorroTiempoUnoAbril2012(String ahorroTiempoUnoAbril2012) {
		this.ahorroTiempoUnoAbril2012 = ahorroTiempoUnoAbril2012;
	}

	public String getAhorroTiempoUnoJulio2012() {
		return ahorroTiempoUnoJulio2012;
	}

	public void setAhorroTiempoUnoJulio2012(String ahorroTiempoUnoJulio2012) {
		this.ahorroTiempoUnoJulio2012 = ahorroTiempoUnoJulio2012;
	}

	public String getAhorroTiempoUnoOctubre2012() {
		return ahorroTiempoUnoOctubre2012;
	}

	public void setAhorroTiempoUnoOctubre2012(String ahorroTiempoUnoOctubre2012) {
		this.ahorroTiempoUnoOctubre2012 = ahorroTiempoUnoOctubre2012;
	}

	public String getAhorroTiempoDosEnero2012() {
		return ahorroTiempoDosEnero2012;
	}

	public void setAhorroTiempoDosEnero2012(String ahorroTiempoDosEnero2012) {
		this.ahorroTiempoDosEnero2012 = ahorroTiempoDosEnero2012;
	}

	public String getAhorroTiempoDosAbril2012() {
		return ahorroTiempoDosAbril2012;
	}

	public void setAhorroTiempoDosAbril2012(String ahorroTiempoDosAbril2012) {
		this.ahorroTiempoDosAbril2012 = ahorroTiempoDosAbril2012;
	}

	public String getAhorroTiempoDosJulio2012() {
		return ahorroTiempoDosJulio2012;
	}

	public void setAhorroTiempoDosJulio2012(String ahorroTiempoDosJulio2012) {
		this.ahorroTiempoDosJulio2012 = ahorroTiempoDosJulio2012;
	}

	public String getAhorroTiempoDosOctubre2012() {
		return ahorroTiempoDosOctubre2012;
	}

	public void setAhorroTiempoDosOctubre2012(String ahorroTiempoDosOctubre2012) {
		this.ahorroTiempoDosOctubre2012 = ahorroTiempoDosOctubre2012;
	}

	public String getServicioUnoEnero2012() {
		return servicioUnoEnero2012;
	}

	public void setServicioUnoEnero2012(String servicioUnoEnero2012) {
		this.servicioUnoEnero2012 = servicioUnoEnero2012;
	}

	public String getServicioUnoAbril2012() {
		return servicioUnoAbril2012;
	}

	public void setServicioUnoAbril2012(String servicioUnoAbril2012) {
		this.servicioUnoAbril2012 = servicioUnoAbril2012;
	}

	public String getServicioUnoJulio2012() {
		return servicioUnoJulio2012;
	}

	public void setServicioUnoJulio2012(String servicioUnoJulio2012) {
		this.servicioUnoJulio2012 = servicioUnoJulio2012;
	}

	public String getServicioUnoOctubre2012() {
		return servicioUnoOctubre2012;
	}

	public void setServicioUnoOctubre2012(String servicioUnoOctubre2012) {
		this.servicioUnoOctubre2012 = servicioUnoOctubre2012;
	}

	public String getServicioDosEnero2012() {
		return servicioDosEnero2012;
	}

	public void setServicioDosEnero2012(String servicioDosEnero2012) {
		this.servicioDosEnero2012 = servicioDosEnero2012;
	}

	public String getServicioDosAbril2012() {
		return servicioDosAbril2012;
	}

	public void setServicioDosAbril2012(String servicioDosAbril2012) {
		this.servicioDosAbril2012 = servicioDosAbril2012;
	}

	public String getServicioDosJulio2012() {
		return servicioDosJulio2012;
	}

	public void setServicioDosJulio2012(String servicioDosJulio2012) {
		this.servicioDosJulio2012 = servicioDosJulio2012;
	}

	public String getServicioDosOctubre2012() {
		return servicioDosOctubre2012;
	}

	public void setServicioDosOctubre2012(String servicioDosOctubre2012) {
		this.servicioDosOctubre2012 = servicioDosOctubre2012;
	}

	public String getCapacidadEnero2012() {
		return capacidadEnero2012;
	}

	public void setCapacidadEnero2012(String capacidadEnero2012) {
		this.capacidadEnero2012 = capacidadEnero2012;
	}

	public String getCapacidadAbril2012() {
		return capacidadAbril2012;
	}

	public void setCapacidadAbril2012(String capacidadAbril2012) {
		this.capacidadAbril2012 = capacidadAbril2012;
	}

	public String getCapacidadJulio2012() {
		return capacidadJulio2012;
	}

	public void setCapacidadJulio2012(String capacidadJulio2012) {
		this.capacidadJulio2012 = capacidadJulio2012;
	}

	public String getCapacidadOctubre2012() {
		return capacidadOctubre2012;
	}

	public void setCapacidadOctubre2012(String capacidadOctubre2012) {
		this.capacidadOctubre2012 = capacidadOctubre2012;
	}

	public String getAhorrosMonetariosUnoEnero2013() {
		return ahorrosMonetariosUnoEnero2013;
	}

	public void setAhorrosMonetariosUnoEnero2013(
			String ahorrosMonetariosUnoEnero2013) {
		this.ahorrosMonetariosUnoEnero2013 = ahorrosMonetariosUnoEnero2013;
	}

	public String getAhorrosMonetariosUnoAbril2013() {
		return ahorrosMonetariosUnoAbril2013;
	}

	public void setAhorrosMonetariosUnoAbril2013(
			String ahorrosMonetariosUnoAbril2013) {
		this.ahorrosMonetariosUnoAbril2013 = ahorrosMonetariosUnoAbril2013;
	}

	public String getAhorrosMonetariosUnoJulio2013() {
		return ahorrosMonetariosUnoJulio2013;
	}

	public void setAhorrosMonetariosUnoJulio2013(
			String ahorrosMonetariosUnoJulio2013) {
		this.ahorrosMonetariosUnoJulio2013 = ahorrosMonetariosUnoJulio2013;
	}

	public String getAhorrosMonetariosUnoOctubre2013() {
		return ahorrosMonetariosUnoOctubre2013;
	}

	public void setAhorrosMonetariosUnoOctubre2013(
			String ahorrosMonetariosUnoOctubre2013) {
		this.ahorrosMonetariosUnoOctubre2013 = ahorrosMonetariosUnoOctubre2013;
	}

	public String getAhorrosMonetariosDosEnero2013() {
		return ahorrosMonetariosDosEnero2013;
	}

	public void setAhorrosMonetariosDosEnero2013(
			String ahorrosMonetariosDosEnero2013) {
		this.ahorrosMonetariosDosEnero2013 = ahorrosMonetariosDosEnero2013;
	}

	public String getAhorrosMonetariosDosAbril2013() {
		return ahorrosMonetariosDosAbril2013;
	}

	public void setAhorrosMonetariosDosAbril2013(
			String ahorrosMonetariosDosAbril2013) {
		this.ahorrosMonetariosDosAbril2013 = ahorrosMonetariosDosAbril2013;
	}

	public String getAhorrosMonetariosDosJulio2013() {
		return ahorrosMonetariosDosJulio2013;
	}

	public void setAhorrosMonetariosDosJulio2013(
			String ahorrosMonetariosDosJulio2013) {
		this.ahorrosMonetariosDosJulio2013 = ahorrosMonetariosDosJulio2013;
	}

	public String getAhorrosMonetariosDosOctubre2013() {
		return ahorrosMonetariosDosOctubre2013;
	}

	public void setAhorrosMonetariosDosOctubre2013(
			String ahorrosMonetariosDosOctubre2013) {
		this.ahorrosMonetariosDosOctubre2013 = ahorrosMonetariosDosOctubre2013;
	}

	public String getDefectosUnoEnero2013() {
		return defectosUnoEnero2013;
	}

	public void setDefectosUnoEnero2013(String defectosUnoEnero2013) {
		this.defectosUnoEnero2013 = defectosUnoEnero2013;
	}

	public String getDefectosUnoAbril2013() {
		return defectosUnoAbril2013;
	}

	public void setDefectosUnoAbril2013(String defectosUnoAbril2013) {
		this.defectosUnoAbril2013 = defectosUnoAbril2013;
	}

	public String getDefectosUnoJulio2013() {
		return defectosUnoJulio2013;
	}

	public void setDefectosUnoJulio2013(String defectosUnoJulio2013) {
		this.defectosUnoJulio2013 = defectosUnoJulio2013;
	}

	public String getDefectosUnoOctubre2013() {
		return defectosUnoOctubre2013;
	}

	public void setDefectosUnoOctubre2013(String defectosUnoOctubre2013) {
		this.defectosUnoOctubre2013 = defectosUnoOctubre2013;
	}

	public String getDefectosDosEnero2013() {
		return defectosDosEnero2013;
	}

	public void setDefectosDosEnero2013(String defectosDosEnero2013) {
		this.defectosDosEnero2013 = defectosDosEnero2013;
	}

	public String getDefectosDosAbril2013() {
		return defectosDosAbril2013;
	}

	public void setDefectosDosAbril2013(String defectosDosAbril2013) {
		this.defectosDosAbril2013 = defectosDosAbril2013;
	}

	public String getDefectosDosJulio2013() {
		return defectosDosJulio2013;
	}

	public void setDefectosDosJulio2013(String defectosDosJulio2013) {
		this.defectosDosJulio2013 = defectosDosJulio2013;
	}

	public String getDefectosDosOctubre2013() {
		return defectosDosOctubre2013;
	}

	public void setDefectosDosOctubre2013(String defectosDosOctubre2013) {
		this.defectosDosOctubre2013 = defectosDosOctubre2013;
	}

	public String getAhorroTiempoUnoEnero2013() {
		return ahorroTiempoUnoEnero2013;
	}

	public void setAhorroTiempoUnoEnero2013(String ahorroTiempoUnoEnero2013) {
		this.ahorroTiempoUnoEnero2013 = ahorroTiempoUnoEnero2013;
	}

	public String getAhorroTiempoUnoAbril2013() {
		return ahorroTiempoUnoAbril2013;
	}

	public void setAhorroTiempoUnoAbril2013(String ahorroTiempoUnoAbril2013) {
		this.ahorroTiempoUnoAbril2013 = ahorroTiempoUnoAbril2013;
	}

	public String getAhorroTiempoUnoJulio2013() {
		return ahorroTiempoUnoJulio2013;
	}

	public void setAhorroTiempoUnoJulio2013(String ahorroTiempoUnoJulio2013) {
		this.ahorroTiempoUnoJulio2013 = ahorroTiempoUnoJulio2013;
	}

	public String getAhorroTiempoUnoOctubre2013() {
		return ahorroTiempoUnoOctubre2013;
	}

	public void setAhorroTiempoUnoOctubre2013(String ahorroTiempoUnoOctubre2013) {
		this.ahorroTiempoUnoOctubre2013 = ahorroTiempoUnoOctubre2013;
	}

	public String getAhorroTiempoDosEnero2013() {
		return ahorroTiempoDosEnero2013;
	}

	public void setAhorroTiempoDosEnero2013(String ahorroTiempoDosEnero2013) {
		this.ahorroTiempoDosEnero2013 = ahorroTiempoDosEnero2013;
	}

	public String getAhorroTiempoDosAbril2013() {
		return ahorroTiempoDosAbril2013;
	}

	public void setAhorroTiempoDosAbril2013(String ahorroTiempoDosAbril2013) {
		this.ahorroTiempoDosAbril2013 = ahorroTiempoDosAbril2013;
	}

	public String getAhorroTiempoDosJulio2013() {
		return ahorroTiempoDosJulio2013;
	}

	public void setAhorroTiempoDosJulio2013(String ahorroTiempoDosJulio2013) {
		this.ahorroTiempoDosJulio2013 = ahorroTiempoDosJulio2013;
	}

	public String getAhorroTiempoDosOctubre2013() {
		return ahorroTiempoDosOctubre2013;
	}

	public void setAhorroTiempoDosOctubre2013(String ahorroTiempoDosOctubre2013) {
		this.ahorroTiempoDosOctubre2013 = ahorroTiempoDosOctubre2013;
	}

	public String getServicioUnoEnero2013() {
		return servicioUnoEnero2013;
	}

	public void setServicioUnoEnero2013(String servicioUnoEnero2013) {
		this.servicioUnoEnero2013 = servicioUnoEnero2013;
	}

	public String getServicioUnoAbril2013() {
		return servicioUnoAbril2013;
	}

	public void setServicioUnoAbril2013(String servicioUnoAbril2013) {
		this.servicioUnoAbril2013 = servicioUnoAbril2013;
	}

	public String getServicioUnoJulio2013() {
		return servicioUnoJulio2013;
	}

	public void setServicioUnoJulio2013(String servicioUnoJulio2013) {
		this.servicioUnoJulio2013 = servicioUnoJulio2013;
	}

	public String getServicioUnoOctubre2013() {
		return servicioUnoOctubre2013;
	}

	public void setServicioUnoOctubre2013(String servicioUnoOctubre2013) {
		this.servicioUnoOctubre2013 = servicioUnoOctubre2013;
	}

	public String getServicioDosEnero2013() {
		return servicioDosEnero2013;
	}

	public void setServicioDosEnero2013(String servicioDosEnero2013) {
		this.servicioDosEnero2013 = servicioDosEnero2013;
	}

	public String getServicioDosAbril2013() {
		return servicioDosAbril2013;
	}

	public void setServicioDosAbril2013(String servicioDosAbril2013) {
		this.servicioDosAbril2013 = servicioDosAbril2013;
	}

	public String getServicioDosJulio2013() {
		return servicioDosJulio2013;
	}

	public void setServicioDosJulio2013(String servicioDosJulio2013) {
		this.servicioDosJulio2013 = servicioDosJulio2013;
	}

	public String getServicioDosOctubre2013() {
		return servicioDosOctubre2013;
	}

	public void setServicioDosOctubre2013(String servicioDosOctubre2013) {
		this.servicioDosOctubre2013 = servicioDosOctubre2013;
	}

	public String getCapacidadEnero2013() {
		return capacidadEnero2013;
	}

	public void setCapacidadEnero2013(String capacidadEnero2013) {
		this.capacidadEnero2013 = capacidadEnero2013;
	}

	public String getCapacidadAbril2013() {
		return capacidadAbril2013;
	}

	public void setCapacidadAbril2013(String capacidadAbril2013) {
		this.capacidadAbril2013 = capacidadAbril2013;
	}

	public String getCapacidadJulio2013() {
		return capacidadJulio2013;
	}

	public void setCapacidadJulio2013(String capacidadJulio2013) {
		this.capacidadJulio2013 = capacidadJulio2013;
	}

	public String getCapacidadOctubre2013() {
		return capacidadOctubre2013;
	}

	public void setCapacidadOctubre2013(String capacidadOctubre2013) {
		this.capacidadOctubre2013 = capacidadOctubre2013;
	}
}