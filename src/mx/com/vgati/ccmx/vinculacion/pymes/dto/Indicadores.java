/*
 * Indicadores.java        01/03/2013
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

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
	public InputStream archivo1;
	public int idArchivo1;
	public String archivo1ContentType;
	public String archivo1FileName;
	
	private String ahorrosMonetariosEnero;
	private String ahorrosMonetariosAbril;
	private String ahorrosMonetariosJulio;
	private String ahorrosMonetariosOctubre;
	private String defectosEnero;
	private String defectosAbril;
	private String defectosJulio;
	private String defectosOctubre;
	private String ahorroTiempoEnero;
	private String ahorroTiempoAbril;
	private String ahorroTiempoJulio;
	private String ahorroTiempoOctubre;
	private String servicioEnero;
	private String servicioAbril;
	private String servicioJulio;
	private String servicioOctubre;
	private String capacidadEnero;
	private String capacidadAbril;
	private String capacidadJulio;
	private String capacidadOctubre;
	
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
	public InputStream getArchivo1() {
		return archivo1;
	}

	public void setArchivo1(InputStream archivo1) {
		this.archivo1 = archivo1;
	}

	public void setArchivo1(File archivo1) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo1.getCanonicalPath());
			this.archivo1 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public String getAhorrosMonetariosEnero() {
		return ahorrosMonetariosEnero;
	}
	public void setAhorrosMonetariosEnero(String ahorrosMonetariosEnero) {
		this.ahorrosMonetariosEnero = ahorrosMonetariosEnero;
	}
	public String getAhorrosMonetariosAbril() {
		return ahorrosMonetariosAbril;
	}
	public void setAhorrosMonetariosAbril(String ahorrosMonetariosAbril) {
		this.ahorrosMonetariosAbril = ahorrosMonetariosAbril;
	}
	public String getAhorrosMonetariosJulio() {
		return ahorrosMonetariosJulio;
	}
	public void setAhorrosMonetariosJulio(String ahorrosMonetariosJulio) {
		this.ahorrosMonetariosJulio = ahorrosMonetariosJulio;
	}
	public String getAhorrosMonetariosOctubre() {
		return ahorrosMonetariosOctubre;
	}
	public void setAhorrosMonetariosOctubre(String ahorrosMonetariosOctubre) {
		this.ahorrosMonetariosOctubre = ahorrosMonetariosOctubre;
	}
	public String getDefectosEnero() {
		return defectosEnero;
	}
	public void setDefectosEnero(String defectosEnero) {
		this.defectosEnero = defectosEnero;
	}
	public String getDefectosAbril() {
		return defectosAbril;
	}
	public void setDefectosAbril(String defectosAbril) {
		this.defectosAbril = defectosAbril;
	}
	public String getDefectosJulio() {
		return defectosJulio;
	}
	public void setDefectosJulio(String defectosJulio) {
		this.defectosJulio = defectosJulio;
	}
	public String getDefectosOctubre() {
		return defectosOctubre;
	}
	public void setDefectosOctubre(String defectosOctubre) {
		this.defectosOctubre = defectosOctubre;
	}
	public String getAhorroTiempoEnero() {
		return ahorroTiempoEnero;
	}
	public void setAhorroTiempoEnero(String ahorroTiempoEnero) {
		this.ahorroTiempoEnero = ahorroTiempoEnero;
	}
	public String getAhorroTiempoAbril() {
		return ahorroTiempoAbril;
	}
	public void setAhorroTiempoAbril(String ahorroTiempoAbril) {
		this.ahorroTiempoAbril = ahorroTiempoAbril;
	}
	public String getAhorroTiempoJulio() {
		return ahorroTiempoJulio;
	}
	public void setAhorroTiempoJulio(String ahorroTiempoJulio) {
		this.ahorroTiempoJulio = ahorroTiempoJulio;
	}
	public String getAhorroTiempoOctubre() {
		return ahorroTiempoOctubre;
	}
	public void setAhorroTiempoOctubre(String ahorroTiempoOctubre) {
		this.ahorroTiempoOctubre = ahorroTiempoOctubre;
	}
	public String getServicioEnero() {
		return servicioEnero;
	}
	public void setServicioEnero(String servicioEnero) {
		this.servicioEnero = servicioEnero;
	}
	public String getServicioAbril() {
		return servicioAbril;
	}
	public void setServicioAbril(String servicioAbril) {
		this.servicioAbril = servicioAbril;
	}
	public String getServicioJulio() {
		return servicioJulio;
	}
	public void setServicioJulio(String servicioJulio) {
		this.servicioJulio = servicioJulio;
	}
	public String getServicioOctubre() {
		return servicioOctubre;
	}
	public void setServicioOctubre(String servicioOctubre) {
		this.servicioOctubre = servicioOctubre;
	}
	public String getCapacidadEnero() {
		return capacidadEnero;
	}
	public void setCapacidadEnero(String capacidadEnero) {
		this.capacidadEnero = capacidadEnero;
	}
	public String getCapacidadAbril() {
		return capacidadAbril;
	}
	public void setCapacidadAbril(String capacidadAbril) {
		this.capacidadAbril = capacidadAbril;
	}
	public String getCapacidadJulio() {
		return capacidadJulio;
	}
	public void setCapacidadJulio(String capacidadJulio) {
		this.capacidadJulio = capacidadJulio;
	}
	public String getCapacidadOctubre() {
		return capacidadOctubre;
	}
	public void setCapacidadOctubre(String capacidadOctubre) {
		this.capacidadOctubre = capacidadOctubre;
	}
}