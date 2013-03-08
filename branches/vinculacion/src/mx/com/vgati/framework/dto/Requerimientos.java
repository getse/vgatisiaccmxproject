/*
 * Requerimientos.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.dto;

import java.io.File;
import java.util.Date;

/**
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class Requerimientos extends AbstractBaseDTO {

	private int idRequerimiento;
	private int idTractora;
	private String producto;
	private String tipoProducto;
	private String descripcion;
	private Date fechaSuministro;
	private boolean bIndefinido;
	private boolean bVariasFechas;
	private boolean bContinuoSuministro;
	private String lugarSuministro;
	private boolean bContado;
	private boolean bCredito;
	private boolean bQuince;
	private boolean bTreinta;
	private boolean bSesenta;
	private boolean bNoventa;
	private boolean bOtro;
	private String otrasCondiciones;
	private String requisitosAdicionales;
	private Date fechaExpira;
	private boolean bContinuoExpira;
	private File archivo;

	public int getIdRequerimiento() {
		return idRequerimiento;
	}

	public void setIdRequerimiento(int idRequerimiento) {
		this.idRequerimiento = idRequerimiento;
	}

	public int getIdTractora() {
		return idTractora;
	}

	public void setIdTractora(int idTractora) {
		this.idTractora = idTractora;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaSuministro() {
		return fechaSuministro;
	}

	public void setFechaSuministro(Date fechaSuministro) {
		this.fechaSuministro = fechaSuministro;
	}

	public boolean isbIndefinido() {
		return bIndefinido;
	}

	public void setbIndefinido(boolean bIndefinido) {
		this.bIndefinido = bIndefinido;
	}

	public boolean isbVariasFechas() {
		return bVariasFechas;
	}

	public void setbVariasFechas(boolean bVariasFechas) {
		this.bVariasFechas = bVariasFechas;
	}

	public boolean isbContinuoSuministro() {
		return bContinuoSuministro;
	}

	public void setbContinuoSuministro(boolean bContinuoSuministro) {
		this.bContinuoSuministro = bContinuoSuministro;
	}

	public String getLugarSuministro() {
		return lugarSuministro;
	}

	public void setLugarSuministro(String lugarSuministro) {
		this.lugarSuministro = lugarSuministro;
	}

	public boolean isbContado() {
		return bContado;
	}

	public void setbContado(boolean bContado) {
		this.bContado = bContado;
	}

	public boolean isbCredito() {
		return bCredito;
	}

	public void setbCredito(boolean bCredito) {
		this.bCredito = bCredito;
	}

	public boolean isbQuince() {
		return bQuince;
	}

	public void setbQuince(boolean bQuince) {
		this.bQuince = bQuince;
	}

	public boolean isbTreinta() {
		return bTreinta;
	}

	public void setbTreinta(boolean bTreinta) {
		this.bTreinta = bTreinta;
	}

	public boolean isbSesenta() {
		return bSesenta;
	}

	public void setbSesenta(boolean bSesenta) {
		this.bSesenta = bSesenta;
	}

	public boolean isbNoventa() {
		return bNoventa;
	}

	public void setbNoventa(boolean bNoventa) {
		this.bNoventa = bNoventa;
	}

	public boolean isbOtro() {
		return bOtro;
	}

	public void setbOtro(boolean bOtro) {
		this.bOtro = bOtro;
	}

	public String getOtrasCondiciones() {
		return otrasCondiciones;
	}

	public void setOtrasCondiciones(String otrasCondiciones) {
		this.otrasCondiciones = otrasCondiciones;
	}

	public String getRequisitosAdicionales() {
		return requisitosAdicionales;
	}

	public void setRequisitosAdicionales(String requisitosAdicionales) {
		this.requisitosAdicionales = requisitosAdicionales;
	}

	public Date getFechaExpira() {
		return fechaExpira;
	}

	public void setFechaExpira(Date fechaExpira) {
		this.fechaExpira = fechaExpira;
	}

	public boolean isbContinuoExpira() {
		return bContinuoExpira;
	}

	public void setbContinuoExpira(boolean bContinuoExpira) {
		this.bContinuoExpira = bContinuoExpira;
	}

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

}
