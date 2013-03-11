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
import java.sql.Date;

import mx.com.vgati.framework.util.Null;

/**
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class Requerimientos extends AbstractBaseDTO {

	public int idRequerimiento;
	public int idTractora;
	public String producto;
	public String tipoProducto;
	public String busqueda;
	public int cveScian;
	public String descripcion;
	public Date fechaSuministro;
	public boolean bIndefinido;
	public boolean bVariasFechas;
	public boolean bContinuoSuministro;
	public String lugarSuministro;
	public boolean bContado;
	public boolean bCredito;
	public boolean bQuince;
	public boolean bTreinta;
	public boolean bSesenta;
	public boolean bNoventa;
	public boolean bOtro;
	public String otrasCondiciones;
	public String requisitosAdicionales;
	public Date fechaExpira;
	public boolean bContinuoExpira;
	public File archivo;

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

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public int getCveScian() {
		return cveScian;
	}

	public void setCveScian(int cveScian) {
		this.cveScian = cveScian;
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

	public void setFechaSuministro(String fechaSuministro) {
		this.fechaSuministro = Date.valueOf(fechaSuministro);
	}

	public void setFechaSuministro(Date fechaSuministro) {
		this.fechaSuministro = fechaSuministro;
	}

	public boolean isbIndefinido() {
		return bIndefinido;
	}

	public void setbIndefinido(String bIndefinido) {
		this.bIndefinido = Null.free(bIndefinido).equals("true") ? true : false;
	}

	public void setbIndefinido(boolean bIndefinido) {
		this.bIndefinido = bIndefinido;
	}

	public boolean isbVariasFechas() {
		return bVariasFechas;
	}

	public void setbVariasFechas(String bVariasFechas) {
		this.bVariasFechas = Null.free(bVariasFechas).equals("true") ? true
				: false;
	}

	public void setbVariasFechas(boolean bVariasFechas) {
		this.bVariasFechas = bVariasFechas;
	}

	public boolean isbContinuoSuministro() {
		return bContinuoSuministro;
	}

	public void setbContinuoSuministro(String bContinuoSuministro) {
		this.bContinuoSuministro = Null.free(bContinuoSuministro)
				.equals("true") ? true : false;
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

	public void setbContado(String bContado) {
		this.bContado = Null.free(bContado).equals("true") ? true : false;
	}

	public void setbContado(boolean bContado) {
		this.bContado = bContado;
	}

	public boolean isbCredito() {
		return bCredito;
	}

	public void setbCredito(String bCredito) {
		this.bCredito = Null.free(bCredito).equals("true") ? true : false;
	}

	public void setbCredito(boolean bCredito) {
		this.bCredito = bCredito;
	}

	public boolean isbQuince() {
		return bQuince;
	}

	public void setbQuince(String bQuince) {
		this.bQuince = Null.free(bQuince).equals("true") ? true : false;
	}

	public void setbQuince(boolean bQuince) {
		this.bQuince = bQuince;
	}

	public boolean isbTreinta() {
		return bTreinta;
	}

	public void setbTreinta(String bTreinta) {
		this.bTreinta = Null.free(bTreinta).equals("true") ? true : false;
	}

	public void setbTreinta(boolean bTreinta) {
		this.bTreinta = bTreinta;
	}

	public boolean isbSesenta() {
		return bSesenta;
	}

	public void setbSesenta(String bSesenta) {
		this.bSesenta = Null.free(bSesenta).equals("true") ? true : false;
	}

	public void setbSesenta(boolean bSesenta) {
		this.bSesenta = bSesenta;
	}

	public boolean isbNoventa() {
		return bNoventa;
	}

	public void setbNoventa(String bNoventa) {
		this.bNoventa = Null.free(bNoventa).equals("true") ? true : false;
	}

	public void setbNoventa(boolean bNoventa) {
		this.bNoventa = bNoventa;
	}

	public boolean isbOtro() {
		return bOtro;
	}

	public void setbOtro(String bOtro) {
		this.bOtro = Null.free(bOtro).equals("true") ? true : false;
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

	public void setFechaExpira(String fechaExpira) {
		this.fechaExpira = Date.valueOf(fechaExpira);
	}

	public void setFechaExpira(Date fechaExpira) {
		this.fechaExpira = fechaExpira;
	}

	public boolean isbContinuoExpira() {
		return bContinuoExpira;
	}

	public void setbContinuoExpira(String bContinuoExpira) {
		this.bContinuoExpira = Null.free(bContinuoExpira).equals("true") ? true
				: false;
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
