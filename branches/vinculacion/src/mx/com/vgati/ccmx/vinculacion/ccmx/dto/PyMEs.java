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
	private boolean bPrimerNivel;
	private boolean bSegundoNivel;
	private boolean bTercerNivel;
	private boolean bAguascalientes;
	private boolean bBajaCaliforniaSur;
	private boolean bBajaCaliforniaNorte;
	private boolean bCampeche;
	private boolean bChiapas;
	private boolean bChihuahua;
	private boolean bCoahuila;
	private boolean bColima;
	private boolean bDistritoFederal;
	private boolean bDurango;
	private boolean bGuanajuato;
	private boolean bGuerrero;
	private boolean bHidalgo;
	private boolean bJalisco;
	private boolean bMexico;
	private boolean bMichoacan;
	private boolean bMorelos;
	private boolean bNayarit;
	private boolean bNuevoLeon;
	private boolean bOaxaca;
	private boolean bPuebla;
	private boolean bQueretaro;
	private boolean bQuintanaRoo;
	private boolean bSanLuisPotosi;
	private boolean bSinaloa;
	private boolean bSonora;
	private boolean bTabasco;
	private boolean bTamaulipas;
	private boolean bTlaxcala;
	private boolean bVeracruz;
	private boolean bYucatan;
	private boolean bZacatecas;
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
	public boolean isbPrimerNivel() {
		return bPrimerNivel;
	}
	public void setbPrimerNivel(boolean bPrimerNivel) {
		this.bPrimerNivel = bPrimerNivel;
	}
	public boolean isbSegundoNivel() {
		return bSegundoNivel;
	}
	public void setbSegundoNivel(boolean bSegundoNivel) {
		this.bSegundoNivel = bSegundoNivel;
	}
	public boolean isbTercerNivel() {
		return bTercerNivel;
	}
	public void setbTercerNivel(boolean bTercerNivel) {
		this.bTercerNivel = bTercerNivel;
	}
	public boolean isbAguascalientes() {
		return bAguascalientes;
	}
	public void setbAguascalientes(boolean bAguascalientes) {
		this.bAguascalientes = bAguascalientes;
	}
	public boolean isbBajaCaliforniaSur() {
		return bBajaCaliforniaSur;
	}
	public void setbBajaCaliforniaSur(boolean bBajaCaliforniaSur) {
		this.bBajaCaliforniaSur = bBajaCaliforniaSur;
	}
	public boolean isbBajaCaliforniaNorte() {
		return bBajaCaliforniaNorte;
	}
	public void setbBajaCaliforniaNorte(boolean bBajaCaliforniaNorte) {
		this.bBajaCaliforniaNorte = bBajaCaliforniaNorte;
	}
	public boolean isbCampeche() {
		return bCampeche;
	}
	public void setbCampeche(boolean bCampeche) {
		this.bCampeche = bCampeche;
	}
	public boolean isbChiapas() {
		return bChiapas;
	}
	public void setbChiapas(boolean bChiapas) {
		this.bChiapas = bChiapas;
	}
	public boolean isbChihuahua() {
		return bChihuahua;
	}
	public void setbChihuahua(boolean bChihuahua) {
		this.bChihuahua = bChihuahua;
	}
	public boolean isbCoahuila() {
		return bCoahuila;
	}
	public void setbCoahuila(boolean bCoahuila) {
		this.bCoahuila = bCoahuila;
	}
	public boolean isbColima() {
		return bColima;
	}
	public void setbColima(boolean bColima) {
		this.bColima = bColima;
	}
	public boolean isbDistritoFederal() {
		return bDistritoFederal;
	}
	public void setbDistritoFederal(boolean bDistritoFederal) {
		this.bDistritoFederal = bDistritoFederal;
	}
	public boolean isbDurango() {
		return bDurango;
	}
	public void setbDurango(boolean bDurango) {
		this.bDurango = bDurango;
	}
	public boolean isbGuanajuato() {
		return bGuanajuato;
	}
	public void setbGuanajuato(boolean bGuanajuato) {
		this.bGuanajuato = bGuanajuato;
	}
	public boolean isbGuerrero() {
		return bGuerrero;
	}
	public void setbGuerrero(boolean bGuerrero) {
		this.bGuerrero = bGuerrero;
	}
	public boolean isbHidalgo() {
		return bHidalgo;
	}
	public void setbHidalgo(boolean bHidalgo) {
		this.bHidalgo = bHidalgo;
	}
	public boolean isbJalisco() {
		return bJalisco;
	}
	public void setbJalisco(boolean bJalisco) {
		this.bJalisco = bJalisco;
	}
	public boolean isbMexico() {
		return bMexico;
	}
	public void setbMexico(boolean bMexico) {
		this.bMexico = bMexico;
	}
	public boolean isbMichoacan() {
		return bMichoacan;
	}
	public void setbMichoacan(boolean bMichoacan) {
		this.bMichoacan = bMichoacan;
	}
	public boolean isbMorelos() {
		return bMorelos;
	}
	public void setbMorelos(boolean bMorelos) {
		this.bMorelos = bMorelos;
	}
	public boolean isbNayarit() {
		return bNayarit;
	}
	public void setbNayarit(boolean bNayarit) {
		this.bNayarit = bNayarit;
	}
	public boolean isbNuevoLeon() {
		return bNuevoLeon;
	}
	public void setbNuevoLeon(boolean bNuevoLeon) {
		this.bNuevoLeon = bNuevoLeon;
	}
	public boolean isbOaxaca() {
		return bOaxaca;
	}
	public void setbOaxaca(boolean bOaxaca) {
		this.bOaxaca = bOaxaca;
	}
	public boolean isbPuebla() {
		return bPuebla;
	}
	public void setbPuebla(boolean bPuebla) {
		this.bPuebla = bPuebla;
	}
	public boolean isbQueretaro() {
		return bQueretaro;
	}
	public void setbQueretaro(boolean bQueretaro) {
		this.bQueretaro = bQueretaro;
	}
	public boolean isbQuintanaRoo() {
		return bQuintanaRoo;
	}
	public void setbQuintanaRoo(boolean bQuintanaRoo) {
		this.bQuintanaRoo = bQuintanaRoo;
	}
	public boolean isbSanLuisPotosi() {
		return bSanLuisPotosi;
	}
	public void setbSanLuisPotosi(boolean bSanLuisPotosi) {
		this.bSanLuisPotosi = bSanLuisPotosi;
	}
	public boolean isbSinaloa() {
		return bSinaloa;
	}
	public void setbSinaloa(boolean bSinaloa) {
		this.bSinaloa = bSinaloa;
	}
	public boolean isbSonora() {
		return bSonora;
	}
	public void setbSonora(boolean bSonora) {
		this.bSonora = bSonora;
	}
	public boolean isbTabasco() {
		return bTabasco;
	}
	public void setbTabasco(boolean bTabasco) {
		this.bTabasco = bTabasco;
	}
	public boolean isbTamaulipas() {
		return bTamaulipas;
	}
	public void setbTamaulipas(boolean bTamaulipas) {
		this.bTamaulipas = bTamaulipas;
	}
	public boolean isbTlaxcala() {
		return bTlaxcala;
	}
	public void setbTlaxcala(boolean bTlaxcala) {
		this.bTlaxcala = bTlaxcala;
	}
	public boolean isbVeracruz() {
		return bVeracruz;
	}
	public void setbVeracruz(boolean bVeracruz) {
		this.bVeracruz = bVeracruz;
	}
	public boolean isbYucatan() {
		return bYucatan;
	}
	public void setbYucatan(boolean bYucatan) {
		this.bYucatan = bYucatan;
	}
	public boolean isbZacatecas() {
		return bZacatecas;
	}
	public void setbZacatecas(boolean bZacatecas) {
		this.bZacatecas = bZacatecas;
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