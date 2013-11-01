/*
 * ServiciosConsultoria.java        23/03/2013
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
import java.util.Date;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

/**
 * 
 * 
 * @author Omar Hernandez
 * 
 */
@SuppressWarnings("serial")
public class ServiciosConsultoria extends AbstractBaseDTO {

	private int idConsultoria;
	private int idUsuario;
	public boolean bConsultoriaVeinte;
	public boolean bConsultoriaCuarenta;
	public boolean bConsultoriaSesenta;
	public boolean bConsultoriaOchenta;
	private File archivo1;
	public int idArchivo1;
	public String archivo1ContentType;
	public String archivo1FileName;

	private float recursosHumanosAntes;
	private float mercadeoAntes;
	private float finanzasAntes;
	private float administracionAntes;
	private float procesosAntes;

	private float recursosHumanosDespues;
	private float mercadeoDespues;
	private float finanzasDespues;
	private float administracionDespues;
	private float procesosDespues;

	private int diplomadoRecomendado1;
	private int diplomadoRecomendado2;

	private Date inicio;
	private Date termino;

	private String estatus;

	public int getIdConsultoria() {
		return idConsultoria;
	}

	public void setIdConsultoria(int idConsultoria) {
		this.idConsultoria = idConsultoria;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean isbConsultoriaVeinte() {
		return bConsultoriaVeinte;
	}

	public void setbConsultoriaVeinte(boolean bConsultoriaVeinte) {
		this.bConsultoriaVeinte = bConsultoriaVeinte;
	}

	public boolean isbConsultoriaCuarenta() {
		return bConsultoriaCuarenta;
	}

	public void setbConsultoriaCuarenta(boolean bConsultoriaCuarenta) {
		this.bConsultoriaCuarenta = bConsultoriaCuarenta;
	}

	public boolean isbConsultoriaSesenta() {
		return bConsultoriaSesenta;
	}

	public void setbConsultoriaSesenta(boolean bConsultoriaSesenta) {
		this.bConsultoriaSesenta = bConsultoriaSesenta;
	}

	public boolean isbConsultoriaOchenta() {
		return bConsultoriaOchenta;
	}

	public void setbConsultoriaOchenta(boolean bConsultoriaOchenta) {
		this.bConsultoriaOchenta = bConsultoriaOchenta;
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

	public float getRecursosHumanosAntes() {
		return recursosHumanosAntes;
	}

	public void setRecursosHumanosAntes(float recursosHumanosAntes) {
		this.recursosHumanosAntes = recursosHumanosAntes;
	}

	public float getMercadeoAntes() {
		return mercadeoAntes;
	}

	public void setMercadeoAntes(float mercadeoAntes) {
		this.mercadeoAntes = mercadeoAntes;
	}

	public float getFinanzasAntes() {
		return finanzasAntes;
	}

	public void setFinanzasAntes(float finanzasAntes) {
		this.finanzasAntes = finanzasAntes;
	}

	public float getAdministracionAntes() {
		return administracionAntes;
	}

	public void setAdministracionAntes(float administracionAntes) {
		this.administracionAntes = administracionAntes;
	}

	public float getProcesosAntes() {
		return procesosAntes;
	}

	public void setProcesosAntes(float procesosAntes) {
		this.procesosAntes = procesosAntes;
	}

	public float getRecursosHumanosDespues() {
		return recursosHumanosDespues;
	}

	public void setRecursosHumanosDespues(float recursosHumanosDespues) {
		this.recursosHumanosDespues = recursosHumanosDespues;
	}

	public float getMercadeoDespues() {
		return mercadeoDespues;
	}

	public void setMercadeoDespues(float mercadeoDespues) {
		this.mercadeoDespues = mercadeoDespues;
	}

	public float getFinanzasDespues() {
		return finanzasDespues;
	}

	public void setFinanzasDespues(float finanzasDespues) {
		this.finanzasDespues = finanzasDespues;
	}

	public float getAdministracionDespues() {
		return administracionDespues;
	}

	public void setAdministracionDespues(float administracionDespues) {
		this.administracionDespues = administracionDespues;
	}

	public float getProcesosDespues() {
		return procesosDespues;
	}

	public void setProcesosDespues(float procesosDespues) {
		this.procesosDespues = procesosDespues;
	}

	public int getDiplomadoRecomendado1() {
		return diplomadoRecomendado1;
	}

	public void setDiplomadoRecomendado1(int diplomadoRecomendado1) {
		this.diplomadoRecomendado1 = diplomadoRecomendado1;
	}

	public int getDiplomadoRecomendado2() {
		return diplomadoRecomendado2;
	}

	public void setDiplomadoRecomendado2(int diplomadoRecomendado2) {
		this.diplomadoRecomendado2 = diplomadoRecomendado2;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
}