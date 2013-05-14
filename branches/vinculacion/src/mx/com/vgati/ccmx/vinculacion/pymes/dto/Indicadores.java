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
	private int idTractora;
	private int idPyMETractora;
	private int ingresosAntes;
	private int ingresosDespues;
	private int clientesAntes;
	private int clientesDespues;
	private int empleadosAntes;
	private int empleadosDespues;
	private int egresosAntes;
	private int egresosDespues;
	private String indicador;
	private String descripcion;
	private String frecuencia;
	private String resultadoCalculo;
	private String periodoRefMes;
	private int periodoRefAnio;
	private String comentario;
	private int calificacion;
	public InputStream archivo1;
	public int idArchivo1;
	public String archivo1ContentType;
	public String archivo1FileName;
	
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
	public int getIdTractora() {
		return idTractora;
	}
	public void setIdTractora(int idTractora) {
		this.idTractora = idTractora;
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
	public String getIndicador() {
		return indicador;
	}
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
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
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
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
}