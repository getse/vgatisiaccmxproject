/*
 * PyMEs.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.ccmx.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class PyMEs extends AbstractBaseDTO {
	
	private int idUsuario;
	private int idUsuarioPadre;
	private String personalidadJuridica;
	private String rfc;
	private String correoElectronico;
	private String nombreComercial;
	private String nombreFiscal;
	private int numeroEmpleados;
	private String mensajeVentas;
	private String paginaWeb;
	private String productosPrincipales;
	private int ventasAnuales;
	private int cveScian;
	private String nombreContacto;
	private String appPaterno;
	private String appMaterno;
	private String correoElectronicoContacto;
	private String telefonoContacto;
	private String institutoCertificador;
	private boolean bDiplomadoCcmxUno;
	private boolean bDiplomadoCcmxDos;
	private boolean bDiplomadoCcmxTres;
	private boolean bDiplomadoCcmxCuatro;
	private boolean	bRecibeRequerimientosCompra;
	private int cveScianRequerimientosCompra;
	private String calificacion;
	private boolean bServiciosCcmxDiplomados;
	private boolean bServiciosCcmxConsultoria;
	private String password;
	private String estado;
	
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
	public String getPersonalidadJuridica() {
		return personalidadJuridica;
	}
	public void setPersonalidadJuridica(String personalidadJuridica) {
		this.personalidadJuridica = personalidadJuridica;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public String getNombreFiscal() {
		return nombreFiscal;
	}
	public void setNombreFiscal(String nombreFiscal) {
		this.nombreFiscal = nombreFiscal;
	}
	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}
	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}
	public String getMensajeVentas() {
		return mensajeVentas;
	}
	public void setMensajeVentas(String mensajeVentas) {
		this.mensajeVentas = mensajeVentas;
	}
	public String getPaginaWeb() {
		return paginaWeb;
	}
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}
	public String getProductosPrincipales() {
		return productosPrincipales;
	}
	public void setProductosPrincipales(String productosPrincipales) {
		this.productosPrincipales = productosPrincipales;
	}
	public int getVentasAnuales() {
		return ventasAnuales;
	}
	public void setVentasAnuales(int ventasAnuales) {
		this.ventasAnuales = ventasAnuales;
	}
	public int getCveScian() {
		return cveScian;
	}
	public void setCveScian(int cveScian) {
		this.cveScian = cveScian;
	}
	public String getNombreContacto() {
		return nombreContacto;
	}
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	public String getAppPaterno() {
		return appPaterno;
	}
	public void setAppPaterno(String appPaterno) {
		this.appPaterno = appPaterno;
	}
	public String getAppMaterno() {
		return appMaterno;
	}
	public void setAppMaterno(String appMaterno) {
		this.appMaterno = appMaterno;
	}
	public String getCorreoElectronicoContacto() {
		return correoElectronicoContacto;
	}
	public void setCorreoElectronicoContacto(String correoElectronicoContacto) {
		this.correoElectronicoContacto = correoElectronicoContacto;
	}
	public String getTelefonoContacto() {
		return telefonoContacto;
	}
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	public String getInstitutoCertificador() {
		return institutoCertificador;
	}
	public void setInstitutoCertificador(String institutoCertificador) {
		this.institutoCertificador = institutoCertificador;
	}
	public boolean isbDiplomadoCcmxUno() {
		return bDiplomadoCcmxUno;
	}
	public void setbDiplomadoCcmxUno(boolean bDiplomadoCcmxUno) {
		this.bDiplomadoCcmxUno = bDiplomadoCcmxUno;
	}
	public boolean isbDiplomadoCcmxDos() {
		return bDiplomadoCcmxDos;
	}
	public void setbDiplomadoCcmxDos(boolean bDiplomadoCcmxDos) {
		this.bDiplomadoCcmxDos = bDiplomadoCcmxDos;
	}
	public boolean isbDiplomadoCcmxTres() {
		return bDiplomadoCcmxTres;
	}
	public void setbDiplomadoCcmxTres(boolean bDiplomadoCcmxTres) {
		this.bDiplomadoCcmxTres = bDiplomadoCcmxTres;
	}
	public boolean isbDiplomadoCcmxCuatro() {
		return bDiplomadoCcmxCuatro;
	}
	public void setbDiplomadoCcmxCuatro(boolean bDiplomadoCcmxCuatro) {
		this.bDiplomadoCcmxCuatro = bDiplomadoCcmxCuatro;
	}
	public boolean isbRecibeRequerimientosCompra() {
		return bRecibeRequerimientosCompra;
	}
	public void setbRecibeRequerimientosCompra(boolean bRecibeRequerimientosCompra) {
		this.bRecibeRequerimientosCompra = bRecibeRequerimientosCompra;
	}
	public int getCveScianRequerimientosCompra() {
		return cveScianRequerimientosCompra;
	}
	public void setCveScianRequerimientosCompra(int cveScianRequerimientosCompra) {
		this.cveScianRequerimientosCompra = cveScianRequerimientosCompra;
	}
	public String getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}
	public boolean isbServiciosCcmxDiplomados() {
		return bServiciosCcmxDiplomados;
	}
	public void setbServiciosCcmxDiplomados(boolean bServiciosCcmxDiplomados) {
		this.bServiciosCcmxDiplomados = bServiciosCcmxDiplomados;
	}
	public boolean isbServiciosCcmxConsultoria() {
		return bServiciosCcmxConsultoria;
	}
	public void setbServiciosCcmxConsultoria(boolean bServiciosCcmxConsultoria) {
		this.bServiciosCcmxConsultoria = bServiciosCcmxConsultoria;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}