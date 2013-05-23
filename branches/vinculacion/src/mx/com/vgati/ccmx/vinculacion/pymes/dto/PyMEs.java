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
package mx.com.vgati.ccmx.vinculacion.pymes.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import mx.com.vgati.framework.dto.AbstractBaseDTO;
import mx.com.vgati.framework.util.Null;

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
	public boolean bPrimerNivel;
	public boolean bSegundoNivel;
	public boolean bTercerNivel;
	private String ventasAnuales;
	private int idProducto1;
	private int idProducto2;
	private int idProducto3;
	private int idProducto4;
	private int idProducto5;
	private int idProducto6;
	private int idProducto7;
	private int idProducto8;
	private int idProducto9;
	private int idProducto10;
	private int idProducto11;
	private int idProducto12;
	private int idProducto13;
	private int idProducto14;
	private int idProducto15;
	private int idProducto16;
	private int idProducto17;
	private int idProducto18;
	private int idProducto19;
	private int idProducto20;
	private String producto1;
	private String producto2;
	private String producto3;
	private String producto4;
	private String producto5;
	private String producto6;
	private String producto7;
	private String producto8;
	private String producto9;
	private String producto10;
	private String producto11;
	private String producto12;
	private String producto13;
	private String producto14;
	private String producto15;
	private String producto16;
	private String producto17;
	private String producto18;
	private String producto19;
	private String producto20;
	private int idContacto1;
	private String tipoContacto1;
	private String nombreContacto1;
	private String appPaterno1;
	private String appMaterno1;
	private String correoElectronicoContacto1;
	private String telefonoContacto1;
	private int idContacto2;
	private String tipoContacto2;
	private String nombreContacto2;
	private String appPaterno2;
	private String appMaterno2;
	private String correoElectronicoContacto2;
	private String telefonoContacto2;
	private int idCliente1;
	private int idCliente2;
	private int idCliente3;
	private int idCliente4;
	private int idCliente5;
	private String cliente1;
	private String cliente2;
	private String cliente3;
	private String cliente4;
	private String cliente5;
	private String productosCompra1;
	private String productosCompra2;
	private String productosCompra3;
	private String productosCompra4;
	private String productosCompra5;
	private String aniosProveedor1;
	private String aniosProveedor2;
	private String aniosProveedor3;
	private String aniosProveedor4;
	private String aniosProveedor5;
	private String mesesProveedor1;
	private String mesesProveedor2;
	private String mesesProveedor3;
	private String mesesProveedor4;
	private String mesesProveedor5;
	public boolean bDiplomadoCcmxUno;
	public boolean bDiplomadoCcmxDos;
	public boolean bDiplomadoCcmxTres;
	public boolean bDiplomadoCcmxCuatro;
	private String descArchivo1;
	private String descArchivo2;
	private String descArchivo3;
	private String descArchivo4;
	private String descArchivo5;
	private String descArchivo6;
	private String descArchivo7;
	private String descArchivo8;
	private String descArchivo9;
	private String descArchivo10;
	private File archivo;
	public InputStream archivo1;
	public InputStream archivo2;
	public InputStream archivo3;
	public InputStream archivo4;
	public InputStream archivo5;
	public InputStream archivo6;
	public InputStream archivo7;
	public InputStream archivo8;
	public InputStream archivo9;
	public InputStream archivo10;
	public int idArchivo1;
	public int idArchivo2;
	public int idArchivo3;
	public int idArchivo4;
	public int idArchivo5;
	public int idArchivo6;
	public int idArchivo7;
	public int idArchivo8;
	public int idArchivo9;
	public int idArchivo10;
	public String archivo1ContentType;
	public String archivo2ContentType;
	public String archivo3ContentType;
	public String archivo4ContentType;
	public String archivo5ContentType;
	public String archivo6ContentType;
	public String archivo7ContentType;
	public String archivo8ContentType;
	public String archivo9ContentType;
	public String archivo10ContentType;
	public String archivo1FileName;
	public String archivo2FileName;
	public String archivo3FileName;
	public String archivo4FileName;
	public String archivo5FileName;
	public String archivo6FileName;
	public String archivo7FileName;
	public String archivo8FileName;
	public String archivo9FileName;
	public String archivo10FileName;
	public boolean bRecibeRequerimientosCompra;

	private int cveScian;
	private int cveScianRequerimientosCompra;
	public boolean bServiciosCcmxDiplomados;
	public boolean bServiciosCcmxConsultoria;
	private String password;
	private String estado;
	private int idCertificacion1;
	private String certificacion1;
	private Date fechaCertificacion1;
	private String institutoCertificador1;
	private int idCertificacion2;
	private String certificacion2;
	private Date fechaCertificacion2;
	private String institutoCertificador2;
	private int idCertificacion3;
	private String certificacion3;
	private Date fechaCertificacion3;
	private String institutoCertificador3;
	private int idCertificacion4;
	private String certificacion4;
	private Date fechaCertificacion4;
	private String institutoCertificador4;
	private int idCertificacion5;
	private String certificacion5;
	private Date fechaCertificacion5;
	private String institutoCertificador5;
	private String cedula;
	private boolean cedulaModificable;
	private int idTractora;

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

	public boolean isbPrimerNivel() {
		return bPrimerNivel;
	}

	public void setbPrimerNivel(String bPrimerNivel) {
		this.bPrimerNivel = Null.free(bPrimerNivel).equals("true") ? true
				: false;
	}

	public void setbPrimerNivel(boolean bPrimerNivel) {
		this.bPrimerNivel = bPrimerNivel;
	}

	public boolean isbSegundoNivel() {
		return bSegundoNivel;
	}

	public void setbSegundoNivel(String bSegundoNivel) {
		this.bSegundoNivel = Null.free(bSegundoNivel).equals("true") ? true
				: false;
	}

	public void setbSegundoNivel(boolean bSegundoNivel) {
		this.bSegundoNivel = bSegundoNivel;
	}

	public boolean isbTercerNivel() {
		return bTercerNivel;
	}

	public void setbTercerNivel(String bTercerNivel) {
		this.bTercerNivel = Null.free(bTercerNivel).equals("true") ? true
				: false;
	}

	public void setbTercerNivel(boolean bTercerNivel) {
		this.bTercerNivel = bTercerNivel;
	}

	public String getVentasAnuales() {
		return ventasAnuales;
	}

	public void setVentasAnuales(String ventasAnuales) {
		this.ventasAnuales = ventasAnuales;
	}

	public int getIdProducto1() {
		return idProducto1;
	}

	public void setIdProducto1(int idProducto1) {
		this.idProducto1 = idProducto1;
	}

	public int getIdProducto2() {
		return idProducto2;
	}

	public void setIdProducto2(int idProducto2) {
		this.idProducto2 = idProducto2;
	}

	public int getIdProducto3() {
		return idProducto3;
	}

	public void setIdProducto3(int idProducto3) {
		this.idProducto3 = idProducto3;
	}

	public int getIdProducto4() {
		return idProducto4;
	}

	public void setIdProducto4(int idProducto4) {
		this.idProducto4 = idProducto4;
	}

	public int getIdProducto5() {
		return idProducto5;
	}

	public void setIdProducto5(int idProducto5) {
		this.idProducto5 = idProducto5;
	}

	public int getIdProducto6() {
		return idProducto6;
	}

	public void setIdProducto6(int idProducto6) {
		this.idProducto6 = idProducto6;
	}

	public int getIdProducto7() {
		return idProducto7;
	}

	public void setIdProducto7(int idProducto7) {
		this.idProducto7 = idProducto7;
	}

	public int getIdProducto8() {
		return idProducto8;
	}

	public void setIdProducto8(int idProducto8) {
		this.idProducto8 = idProducto8;
	}

	public int getIdProducto9() {
		return idProducto9;
	}

	public void setIdProducto9(int idProducto9) {
		this.idProducto9 = idProducto9;
	}

	public int getIdProducto10() {
		return idProducto10;
	}

	public void setIdProducto10(int idProducto10) {
		this.idProducto10 = idProducto10;
	}

	public int getIdProducto11() {
		return idProducto11;
	}

	public void setIdProducto11(int idProducto11) {
		this.idProducto11 = idProducto11;
	}

	public int getIdProducto12() {
		return idProducto12;
	}

	public void setIdProducto12(int idProducto12) {
		this.idProducto12 = idProducto12;
	}

	public int getIdProducto13() {
		return idProducto13;
	}

	public void setIdProducto13(int idProducto13) {
		this.idProducto13 = idProducto13;
	}

	public int getIdProducto14() {
		return idProducto14;
	}

	public void setIdProducto14(int idProducto14) {
		this.idProducto14 = idProducto14;
	}

	public int getIdProducto15() {
		return idProducto15;
	}

	public void setIdProducto15(int idProducto15) {
		this.idProducto15 = idProducto15;
	}

	public int getIdProducto16() {
		return idProducto16;
	}

	public void setIdProducto16(int idProducto16) {
		this.idProducto16 = idProducto16;
	}

	public int getIdProducto17() {
		return idProducto17;
	}

	public void setIdProducto17(int idProducto17) {
		this.idProducto17 = idProducto17;
	}

	public int getIdProducto18() {
		return idProducto18;
	}

	public void setIdProducto18(int idProducto18) {
		this.idProducto18 = idProducto18;
	}

	public int getIdProducto19() {
		return idProducto19;
	}

	public void setIdProducto19(int idProducto19) {
		this.idProducto19 = idProducto19;
	}

	public int getIdProducto20() {
		return idProducto20;
	}

	public void setIdProducto20(int idProducto20) {
		this.idProducto20 = idProducto20;
	}

	public String getProducto1() {
		return producto1;
	}

	public void setProducto1(String producto1) {
		this.producto1 = producto1;
	}

	public String getProducto2() {
		return producto2;
	}

	public void setProducto2(String producto2) {
		this.producto2 = producto2;
	}

	public String getProducto3() {
		return producto3;
	}

	public void setProducto3(String producto3) {
		this.producto3 = producto3;
	}

	public String getProducto4() {
		return producto4;
	}

	public void setProducto4(String producto4) {
		this.producto4 = producto4;
	}

	public String getProducto5() {
		return producto5;
	}

	public void setProducto5(String producto5) {
		this.producto5 = producto5;
	}

	public String getProducto6() {
		return producto6;
	}

	public void setProducto6(String producto6) {
		this.producto6 = producto6;
	}

	public String getProducto7() {
		return producto7;
	}

	public void setProducto7(String producto7) {
		this.producto7 = producto7;
	}

	public String getProducto8() {
		return producto8;
	}

	public void setProducto8(String producto8) {
		this.producto8 = producto8;
	}

	public String getProducto9() {
		return producto9;
	}

	public void setProducto9(String producto9) {
		this.producto9 = producto9;
	}

	public String getProducto10() {
		return producto10;
	}

	public void setProducto10(String producto10) {
		this.producto10 = producto10;
	}

	public String getProducto11() {
		return producto11;
	}

	public void setProducto11(String producto11) {
		this.producto11 = producto11;
	}

	public String getProducto12() {
		return producto12;
	}

	public void setProducto12(String producto12) {
		this.producto12 = producto12;
	}

	public String getProducto13() {
		return producto13;
	}

	public void setProducto13(String producto13) {
		this.producto13 = producto13;
	}

	public String getProducto14() {
		return producto14;
	}

	public void setProducto14(String producto14) {
		this.producto14 = producto14;
	}

	public String getProducto15() {
		return producto15;
	}

	public void setProducto15(String producto15) {
		this.producto15 = producto15;
	}

	public String getProducto16() {
		return producto16;
	}

	public void setProducto16(String producto16) {
		this.producto16 = producto16;
	}

	public String getProducto17() {
		return producto17;
	}

	public void setProducto17(String producto17) {
		this.producto17 = producto17;
	}

	public String getProducto18() {
		return producto18;
	}

	public void setProducto18(String producto18) {
		this.producto18 = producto18;
	}

	public String getProducto19() {
		return producto19;
	}

	public void setProducto19(String producto19) {
		this.producto19 = producto19;
	}

	public String getProducto20() {
		return producto20;
	}

	public void setProducto20(String producto20) {
		this.producto20 = producto20;
	}

	public int getIdContacto1() {
		return idContacto1;
	}

	public void setIdContacto1(int idContacto1) {
		this.idContacto1 = idContacto1;
	}

	public String getTipoContacto1() {
		return tipoContacto1;
	}

	public void setTipoContacto1(String tipoContacto1) {
		this.tipoContacto1 = tipoContacto1;
	}

	public String getNombreContacto1() {
		return nombreContacto1;
	}

	public void setNombreContacto1(String nombreContacto1) {
		this.nombreContacto1 = nombreContacto1;
	}

	public String getAppPaterno1() {
		return appPaterno1;
	}

	public void setAppPaterno1(String appPaterno1) {
		this.appPaterno1 = appPaterno1;
	}

	public String getAppMaterno1() {
		return appMaterno1;
	}

	public void setAppMaterno1(String appMaterno1) {
		this.appMaterno1 = appMaterno1;
	}

	public String getCorreoElectronicoContacto1() {
		return correoElectronicoContacto1;
	}

	public void setCorreoElectronicoContacto1(String correoElectronicoContacto1) {
		this.correoElectronicoContacto1 = correoElectronicoContacto1;
	}

	public String getTelefonoContacto1() {
		return telefonoContacto1;
	}

	public void setTelefonoContacto1(String telefonoContacto1) {
		this.telefonoContacto1 = telefonoContacto1;
	}

	public int getIdContacto2() {
		return idContacto2;
	}

	public void setIdContacto2(int idContacto2) {
		this.idContacto2 = idContacto2;
	}

	public String getTipoContacto2() {
		return tipoContacto2;
	}

	public void setTipoContacto2(String tipoContacto2) {
		this.tipoContacto2 = tipoContacto2;
	}

	public String getNombreContacto2() {
		return nombreContacto2;
	}

	public void setNombreContacto2(String nombreContacto2) {
		this.nombreContacto2 = nombreContacto2;
	}

	public String getAppPaterno2() {
		return appPaterno2;
	}

	public void setAppPaterno2(String appPaterno2) {
		this.appPaterno2 = appPaterno2;
	}

	public String getAppMaterno2() {
		return appMaterno2;
	}

	public void setAppMaterno2(String appMaterno2) {
		this.appMaterno2 = appMaterno2;
	}

	public String getCorreoElectronicoContacto2() {
		return correoElectronicoContacto2;
	}

	public void setCorreoElectronicoContacto2(String correoElectronicoContacto2) {
		this.correoElectronicoContacto2 = correoElectronicoContacto2;
	}

	public String getTelefonoContacto2() {
		return telefonoContacto2;
	}

	public void setTelefonoContacto2(String telefonoContacto2) {
		this.telefonoContacto2 = telefonoContacto2;
	}

	public int getIdCliente1() {
		return idCliente1;
	}

	public void setIdCliente1(int idCliente1) {
		this.idCliente1 = idCliente1;
	}

	public int getIdCliente2() {
		return idCliente2;
	}

	public void setIdCliente2(int idCliente2) {
		this.idCliente2 = idCliente2;
	}

	public int getIdCliente3() {
		return idCliente3;
	}

	public void setIdCliente3(int idCliente3) {
		this.idCliente3 = idCliente3;
	}

	public int getIdCliente4() {
		return idCliente4;
	}

	public void setIdCliente4(int idCliente4) {
		this.idCliente4 = idCliente4;
	}

	public int getIdCliente5() {
		return idCliente5;
	}

	public void setIdCliente5(int idCliente5) {
		this.idCliente5 = idCliente5;
	}

	public String getCliente1() {
		return cliente1;
	}

	public void setCliente1(String cliente1) {
		this.cliente1 = cliente1;
	}

	public String getCliente2() {
		return cliente2;
	}

	public void setCliente2(String cliente2) {
		this.cliente2 = cliente2;
	}

	public String getCliente3() {
		return cliente3;
	}

	public void setCliente3(String cliente3) {
		this.cliente3 = cliente3;
	}

	public String getCliente4() {
		return cliente4;
	}

	public void setCliente4(String cliente4) {
		this.cliente4 = cliente4;
	}

	public String getCliente5() {
		return cliente5;
	}

	public void setCliente5(String cliente5) {
		this.cliente5 = cliente5;
	}

	public String getProductosCompra1() {
		return productosCompra1;
	}

	public void setProductosCompra1(String productosCompra1) {
		this.productosCompra1 = productosCompra1;
	}

	public String getProductosCompra2() {
		return productosCompra2;
	}

	public void setProductosCompra2(String productosCompra2) {
		this.productosCompra2 = productosCompra2;
	}

	public String getProductosCompra3() {
		return productosCompra3;
	}

	public void setProductosCompra3(String productosCompra3) {
		this.productosCompra3 = productosCompra3;
	}

	public String getProductosCompra4() {
		return productosCompra4;
	}

	public void setProductosCompra4(String productosCompra4) {
		this.productosCompra4 = productosCompra4;
	}

	public String getProductosCompra5() {
		return productosCompra5;
	}

	public void setProductosCompra5(String productosCompra5) {
		this.productosCompra5 = productosCompra5;
	}

	public String getAniosProveedor1() {
		return aniosProveedor1;
	}

	public void setAniosProveedor1(String aniosProveedor1) {
		this.aniosProveedor1 = aniosProveedor1;
	}

	public String getAniosProveedor2() {
		return aniosProveedor2;
	}

	public void setAniosProveedor2(String aniosProveedor2) {
		this.aniosProveedor2 = aniosProveedor2;
	}

	public String getAniosProveedor3() {
		return aniosProveedor3;
	}

	public void setAniosProveedor3(String aniosProveedor3) {
		this.aniosProveedor3 = aniosProveedor3;
	}

	public String getAniosProveedor4() {
		return aniosProveedor4;
	}

	public void setAniosProveedor4(String aniosProveedor4) {
		this.aniosProveedor4 = aniosProveedor4;
	}

	public String getAniosProveedor5() {
		return aniosProveedor5;
	}

	public void setAniosProveedor5(String aniosProveedor5) {
		this.aniosProveedor5 = aniosProveedor5;
	}

	public String getMesesProveedor1() {
		return mesesProveedor1;
	}

	public void setMesesProveedor1(String mesesProveedor1) {
		this.mesesProveedor1 = mesesProveedor1;
	}

	public String getMesesProveedor2() {
		return mesesProveedor2;
	}

	public void setMesesProveedor2(String mesesProveedor2) {
		this.mesesProveedor2 = mesesProveedor2;
	}

	public String getMesesProveedor3() {
		return mesesProveedor3;
	}

	public void setMesesProveedor3(String mesesProveedor3) {
		this.mesesProveedor3 = mesesProveedor3;
	}

	public String getMesesProveedor4() {
		return mesesProveedor4;
	}

	public void setMesesProveedor4(String mesesProveedor4) {
		this.mesesProveedor4 = mesesProveedor4;
	}

	public String getMesesProveedor5() {
		return mesesProveedor5;
	}

	public void setMesesProveedor5(String mesesProveedor5) {
		this.mesesProveedor5 = mesesProveedor5;
	}

	public boolean isbDiplomadoCcmxUno() {
		return bDiplomadoCcmxUno;
	}

	public void setbDiplomadoCcmxUno(String bDiplomadoCcmxUno) {
		this.bDiplomadoCcmxUno = Null.free(bDiplomadoCcmxUno).equals("true") ? true
				: false;
	}

	public void setbDiplomadoCcmxUno(boolean bDiplomadoCcmxUno) {
		this.bDiplomadoCcmxUno = bDiplomadoCcmxUno;
	}

	public boolean isbDiplomadoCcmxDos() {
		return bDiplomadoCcmxDos;
	}

	public void setbDiplomadoCcmxDos(String bDiplomadoCcmxDos) {
		this.bDiplomadoCcmxDos = Null.free(bDiplomadoCcmxDos).equals("true") ? true
				: false;
	}

	public void setbDiplomadoCcmxDos(boolean bDiplomadoCcmxDos) {
		this.bDiplomadoCcmxDos = bDiplomadoCcmxDos;
	}

	public boolean isbDiplomadoCcmxTres() {
		return bDiplomadoCcmxTres;
	}

	public void setbDiplomadoCcmxTres(String bDiplomadoCcmxTres) {
		this.bDiplomadoCcmxTres = Null.free(bDiplomadoCcmxTres).equals("true") ? true
				: false;
	}

	public void setbDiplomadoCcmxTres(boolean bDiplomadoCcmxTres) {
		this.bDiplomadoCcmxTres = bDiplomadoCcmxTres;
	}

	public boolean isbDiplomadoCcmxCuatro() {
		return bDiplomadoCcmxCuatro;
	}

	public void setbDiplomadoCcmxCuatro(String bDiplomadoCcmxCuatro) {
		this.bDiplomadoCcmxCuatro = Null.free(bDiplomadoCcmxCuatro).equals(
				"true") ? true : false;
	}

	public void setbDiplomadoCcmxCuatro(boolean bDiplomadoCcmxCuatro) {
		this.bDiplomadoCcmxCuatro = bDiplomadoCcmxCuatro;
	}

	public String getDescArchivo1() {
		return descArchivo1;
	}

	public void setDescArchivo1(String descArchivo1) {
		this.descArchivo1 = descArchivo1;
	}

	public String getDescArchivo2() {
		return descArchivo2;
	}

	public void setDescArchivo2(String descArchivo2) {
		this.descArchivo2 = descArchivo2;
	}

	public String getDescArchivo3() {
		return descArchivo3;
	}

	public void setDescArchivo3(String descArchivo3) {
		this.descArchivo3 = descArchivo3;
	}

	public String getDescArchivo4() {
		return descArchivo4;
	}

	public void setDescArchivo4(String descArchivo4) {
		this.descArchivo4 = descArchivo4;
	}

	public String getDescArchivo5() {
		return descArchivo5;
	}

	public void setDescArchivo5(String descArchivo5) {
		this.descArchivo5 = descArchivo5;
	}

	public String getDescArchivo6() {
		return descArchivo6;
	}

	public void setDescArchivo6(String descArchivo6) {
		this.descArchivo6 = descArchivo6;
	}

	public String getDescArchivo7() {
		return descArchivo7;
	}

	public void setDescArchivo7(String descArchivo7) {
		this.descArchivo7 = descArchivo7;
	}

	public String getDescArchivo8() {
		return descArchivo8;
	}

	public void setDescArchivo8(String descArchivo8) {
		this.descArchivo8 = descArchivo8;
	}

	public String getDescArchivo9() {
		return descArchivo9;
	}

	public void setDescArchivo9(String descArchivo9) {
		this.descArchivo9 = descArchivo9;
	}

	public String getDescArchivo10() {
		return descArchivo10;
	}

	public void setDescArchivo10(String descArchivo10) {
		this.descArchivo10 = descArchivo10;
	}

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
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

	public InputStream getArchivo2() {
		return archivo2;
	}

	public void setArchivo2(InputStream archivo2) {
		this.archivo2 = archivo2;
	}

	public void setArchivo2(File archivo2) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo2.getCanonicalPath());
			this.archivo2 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo3() {
		return archivo3;
	}

	public void setArchivo3(InputStream archivo3) {
		this.archivo3 = archivo3;
	}

	public void setArchivo3(File archivo3) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo3.getCanonicalPath());
			this.archivo3 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo4() {
		return archivo4;
	}

	public void setArchivo4(InputStream archivo4) {
		this.archivo4 = archivo4;
	}

	public void setArchivo4(File archivo4) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo4.getCanonicalPath());
			this.archivo4 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo5() {
		return archivo5;
	}

	public void setArchivo5(InputStream archivo5) {
		this.archivo5 = archivo5;
	}

	public void setArchivo5(File archivo5) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo5.getCanonicalPath());
			this.archivo5 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo6() {
		return archivo6;
	}

	public void setArchivo6(InputStream archivo6) {
		this.archivo6 = archivo6;
	}

	public void setArchivo6(File archivo6) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo6.getCanonicalPath());
			this.archivo6 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo7() {
		return archivo7;
	}

	public void setArchivo7(InputStream archivo7) {
		this.archivo7 = archivo7;
	}

	public void setArchivo7(File archivo7) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo7.getCanonicalPath());
			this.archivo7 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo8() {
		return archivo8;
	}

	public void setArchivo8(InputStream archivo8) {
		this.archivo8 = archivo8;
	}

	public void setArchivo8(File archivo8) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo8.getCanonicalPath());
			this.archivo8 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo9() {
		return archivo9;
	}

	public void setArchivo9(InputStream archivo9) {
		this.archivo9 = archivo9;
	}

	public void setArchivo9(File archivo9) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo9.getCanonicalPath());
			this.archivo9 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo10() {
		return archivo10;
	}

	public void setArchivo10(InputStream archivo10) {
		this.archivo10 = archivo10;
	}

	public void setArchivo10(File archivo10) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo10.getCanonicalPath());
			this.archivo10 = fis;
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

	public int getIdArchivo2() {
		return idArchivo2;
	}

	public void setIdArchivo2(int idArchivo2) {
		this.idArchivo2 = idArchivo2;
	}

	public int getIdArchivo3() {
		return idArchivo3;
	}

	public void setIdArchivo3(int idArchivo3) {
		this.idArchivo3 = idArchivo3;
	}

	public int getIdArchivo4() {
		return idArchivo4;
	}

	public void setIdArchivo4(int idArchivo4) {
		this.idArchivo4 = idArchivo4;
	}

	public int getIdArchivo5() {
		return idArchivo5;
	}

	public void setIdArchivo5(int idArchivo5) {
		this.idArchivo5 = idArchivo5;
	}

	public int getIdArchivo6() {
		return idArchivo6;
	}

	public void setIdArchivo6(int idArchivo6) {
		this.idArchivo6 = idArchivo6;
	}

	public int getIdArchivo7() {
		return idArchivo7;
	}

	public void setIdArchivo7(int idArchivo7) {
		this.idArchivo7 = idArchivo7;
	}

	public int getIdArchivo8() {
		return idArchivo8;
	}

	public void setIdArchivo8(int idArchivo8) {
		this.idArchivo8 = idArchivo8;
	}

	public int getIdArchivo9() {
		return idArchivo9;
	}

	public void setIdArchivo9(int idArchivo9) {
		this.idArchivo9 = idArchivo9;
	}

	public int getIdArchivo10() {
		return idArchivo10;
	}

	public void setIdArchivo10(int idArchivo10) {
		this.idArchivo10 = idArchivo10;
	}

	public String getArchivo1ContentType() {
		return archivo1ContentType;
	}

	public void setArchivo1ContentType(String archivo1ContentType) {
		this.archivo1ContentType = archivo1ContentType;
	}

	public String getArchivo2ContentType() {
		return archivo2ContentType;
	}

	public void setArchivo2ContentType(String archivo2ContentType) {
		this.archivo2ContentType = archivo2ContentType;
	}

	public String getArchivo3ContentType() {
		return archivo3ContentType;
	}

	public void setArchivo3ContentType(String archivo3ContentType) {
		this.archivo3ContentType = archivo3ContentType;
	}

	public String getArchivo4ContentType() {
		return archivo4ContentType;
	}

	public void setArchivo4ContentType(String archivo4ContentType) {
		this.archivo4ContentType = archivo4ContentType;
	}

	public String getArchivo5ContentType() {
		return archivo5ContentType;
	}

	public void setArchivo5ContentType(String archivo5ContentType) {
		this.archivo5ContentType = archivo5ContentType;
	}

	public String getArchivo6ContentType() {
		return archivo6ContentType;
	}

	public void setArchivo6ContentType(String archivo6ContentType) {
		this.archivo6ContentType = archivo6ContentType;
	}

	public String getArchivo7ContentType() {
		return archivo7ContentType;
	}

	public void setArchivo7ContentType(String archivo7ContentType) {
		this.archivo7ContentType = archivo7ContentType;
	}

	public String getArchivo8ContentType() {
		return archivo8ContentType;
	}

	public void setArchivo8ContentType(String archivo8ContentType) {
		this.archivo8ContentType = archivo8ContentType;
	}

	public String getArchivo9ContentType() {
		return archivo9ContentType;
	}

	public void setArchivo9ContentType(String archivo9ContentType) {
		this.archivo9ContentType = archivo9ContentType;
	}

	public String getArchivo10ContentType() {
		return archivo10ContentType;
	}

	public void setArchivo10ContentType(String archivo10ContentType) {
		this.archivo10ContentType = archivo10ContentType;
	}

	public String getArchivo1FileName() {
		return archivo1FileName;
	}

	public void setArchivo1FileName(String archivo1FileName) {
		this.archivo1FileName = archivo1FileName;
	}

	public String getArchivo2FileName() {
		return archivo2FileName;
	}

	public void setArchivo2FileName(String archivo2FileName) {
		this.archivo2FileName = archivo2FileName;
	}

	public String getArchivo3FileName() {
		return archivo3FileName;
	}

	public void setArchivo3FileName(String archivo3FileName) {
		this.archivo3FileName = archivo3FileName;
	}

	public String getArchivo4FileName() {
		return archivo4FileName;
	}

	public void setArchivo4FileName(String archivo4FileName) {
		this.archivo4FileName = archivo4FileName;
	}

	public String getArchivo5FileName() {
		return archivo5FileName;
	}

	public void setArchivo5FileName(String archivo5FileName) {
		this.archivo5FileName = archivo5FileName;
	}

	public String getArchivo6FileName() {
		return archivo6FileName;
	}

	public void setArchivo6FileName(String archivo6FileName) {
		this.archivo6FileName = archivo6FileName;
	}

	public String getArchivo7FileName() {
		return archivo7FileName;
	}

	public void setArchivo7FileName(String archivo7FileName) {
		this.archivo7FileName = archivo7FileName;
	}

	public String getArchivo8FileName() {
		return archivo8FileName;
	}

	public void setArchivo8FileName(String archivo8FileName) {
		this.archivo8FileName = archivo8FileName;
	}

	public String getArchivo9FileName() {
		return archivo9FileName;
	}

	public void setArchivo9FileName(String archivo9FileName) {
		this.archivo9FileName = archivo9FileName;
	}

	public String getArchivo10FileName() {
		return archivo10FileName;
	}

	public void setArchivo10FileName(String archivo10FileName) {
		this.archivo10FileName = archivo10FileName;
	}

	public boolean isbRecibeRequerimientosCompra() {
		return bRecibeRequerimientosCompra;
	}

	public void setbRecibeRequerimientosCompra(
			String bRecibeRequerimientosCompra) {
		this.bRecibeRequerimientosCompra = Null.free(
				bRecibeRequerimientosCompra).equals("true") ? true : false;
	}

	public void setbRecibeRequerimientosCompra(
			boolean bRecibeRequerimientosCompra) {
		this.bRecibeRequerimientosCompra = bRecibeRequerimientosCompra;
	}

	public int getCveScian() {
		return cveScian;
	}

	public void setCveScian(int cveScian) {
		this.cveScian = cveScian;
	}

	public int getCveScianRequerimientosCompra() {
		return cveScianRequerimientosCompra;
	}

	public void setCveScianRequerimientosCompra(int cveScianRequerimientosCompra) {
		this.cveScianRequerimientosCompra = cveScianRequerimientosCompra;
	}

	public boolean isbServiciosCcmxDiplomados() {
		return bServiciosCcmxDiplomados;
	}

	public void setbServiciosCcmxDiplomados(String bServiciosCcmxDiplomados) {
		this.bServiciosCcmxDiplomados = Null.free(bServiciosCcmxDiplomados)
				.equals("true") ? true : false;
	}

	public void setbServiciosCcmxDiplomados(boolean bServiciosCcmxDiplomados) {
		this.bServiciosCcmxDiplomados = bServiciosCcmxDiplomados;
	}

	public boolean isbServiciosCcmxConsultoria() {
		return bServiciosCcmxConsultoria;
	}

	public void setbServiciosCcmxConsultoria(String bServiciosCcmxConsultoria) {
		this.bServiciosCcmxConsultoria = Null.free(bServiciosCcmxConsultoria)
				.equals("true") ? true : false;
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

	public int getIdCertificacion1() {
		return idCertificacion1;
	}

	public void setIdCertificacion1(int idCertificacion1) {
		this.idCertificacion1 = idCertificacion1;
	}

	public String getCertificacion1() {
		return certificacion1;
	}

	public void setCertificacion1(String certificacion1) {
		this.certificacion1 = certificacion1;
	}

	public Date getFechaCertificacion1() {
		return fechaCertificacion1;
	}

	public void setFechaCertificacion1(Date fechaCertificacion1) {
		this.fechaCertificacion1 = fechaCertificacion1;
	}

	public String getInstitutoCertificador1() {
		return institutoCertificador1;
	}

	public void setInstitutoCertificador1(String institutoCertificador1) {
		this.institutoCertificador1 = institutoCertificador1;
	}

	public int getIdCertificacion2() {
		return idCertificacion2;
	}

	public void setIdCertificacion2(int idCertificacion2) {
		this.idCertificacion2 = idCertificacion2;
	}

	public String getCertificacion2() {
		return certificacion2;
	}

	public void setCertificacion2(String certificacion2) {
		this.certificacion2 = certificacion2;
	}

	public Date getFechaCertificacion2() {
		return fechaCertificacion2;
	}

	public void setFechaCertificacion2(Date fechaCertificacion2) {
		this.fechaCertificacion2 = fechaCertificacion2;
	}

	public String getInstitutoCertificador2() {
		return institutoCertificador2;
	}

	public void setInstitutoCertificador2(String institutoCertificador2) {
		this.institutoCertificador2 = institutoCertificador2;
	}

	public int getIdCertificacion3() {
		return idCertificacion3;
	}

	public void setIdCertificacion3(int idCertificacion3) {
		this.idCertificacion3 = idCertificacion3;
	}

	public String getCertificacion3() {
		return certificacion3;
	}

	public void setCertificacion3(String certificacion3) {
		this.certificacion3 = certificacion3;
	}

	public Date getFechaCertificacion3() {
		return fechaCertificacion3;
	}

	public void setFechaCertificacion3(Date fechaCertificacion3) {
		this.fechaCertificacion3 = fechaCertificacion3;
	}

	public String getInstitutoCertificador3() {
		return institutoCertificador3;
	}

	public void setInstitutoCertificador3(String institutoCertificador3) {
		this.institutoCertificador3 = institutoCertificador3;
	}

	public int getIdCertificacion4() {
		return idCertificacion4;
	}

	public void setIdCertificacion4(int idCertificacion4) {
		this.idCertificacion4 = idCertificacion4;
	}

	public String getCertificacion4() {
		return certificacion4;
	}

	public void setCertificacion4(String certificacion4) {
		this.certificacion4 = certificacion4;
	}

	public Date getFechaCertificacion4() {
		return fechaCertificacion4;
	}

	public void setFechaCertificacion4(Date fechaCertificacion4) {
		this.fechaCertificacion4 = fechaCertificacion4;
	}

	public String getInstitutoCertificador4() {
		return institutoCertificador4;
	}

	public void setInstitutoCertificador4(String institutoCertificador4) {
		this.institutoCertificador4 = institutoCertificador4;
	}

	public int getIdCertificacion5() {
		return idCertificacion5;
	}

	public void setIdCertificacion5(int idCertificacion5) {
		this.idCertificacion5 = idCertificacion5;
	}

	public String getCertificacion5() {
		return certificacion5;
	}

	public void setCertificacion5(String certificacion5) {
		this.certificacion5 = certificacion5;
	}

	public Date getFechaCertificacion5() {
		return fechaCertificacion5;
	}

	public void setFechaCertificacion5(Date fechaCertificacion5) {
		this.fechaCertificacion5 = fechaCertificacion5;
	}

	public String getInstitutoCertificador5() {
		return institutoCertificador5;
	}

	public void setInstitutoCertificador5(String institutoCertificador5) {
		this.institutoCertificador5 = institutoCertificador5;
	}

	public boolean isCedulaModificable() {
		return cedulaModificable;
	}

	public void setCedulaModificable(boolean cedulaModificable) {
		this.cedulaModificable = cedulaModificable;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public int getIdTractora() {
		return idTractora;
	}

	public void setIdTractora(int idTractora) {
		this.idTractora = idTractora;
	}
}