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
import java.sql.Date;
import java.util.List;

import mx.com.vgati.ccmx.vinculacion.dto.Contacto;
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
	private List<Productos> productos;
	private List<Contacto> contactos;
	private List<Clientes> clientes;
	private List<Certificaciones> certificaciones;
	private List<Categorias> categorias;

	private int idContacto1;
	private String tipoContacto1;
	private String nombreContacto1;
	private String appPaterno1;
	private String appMaterno1;
	private String correoElectronicoContacto1;
	private String telefonoContacto1;

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
	private File archivo1;
	private File archivo2;
	private File archivo3;
	private File archivo4;
	private File archivo5;
	private File archivo6;
	private File archivo7;
	private File archivo8;
	private File archivo9;
	private File archivo10;
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

	public boolean bServiciosCcmxDiplomados;
	public boolean bServiciosCcmxConsultoria;
	private String password;
	private String estado;

	private String institutoCertificador5;
	private String cedula;
	private boolean cedulaModificable;
	private int idTractora;
	private String tractora;
	private Date fecha;
	private int horas;
	private String Tipo;
	private int idPago;
	private String estatusConsultoria;
	private String empresa;
	private String monto;

	private boolean desactivar;
	private String nombreAcepta;
	private String apellidoPaternoAcepta;
	private String apellidoMaternoAcepta;

	private int idServicioConsultoria;
	public boolean estatus;

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

	public File getArchivo1() {
		return archivo1;
	}

	public void setArchivo1(File archivo1) {
		this.archivo1 = archivo1;
	}

	public File getArchivo2() {
		return archivo2;
	}

	public void setArchivo2(File archivo2) {
		this.archivo2 = archivo2;
	}

	public File getArchivo3() {
		return archivo3;
	}

	public void setArchivo3(File archivo3) {
		this.archivo3 = archivo3;
	}

	public File getArchivo4() {
		return archivo4;
	}

	public void setArchivo4(File archivo4) {
		this.archivo4 = archivo4;
	}

	public File getArchivo5() {
		return archivo5;
	}

	public void setArchivo5(File archivo5) {
		this.archivo5 = archivo5;
	}

	public File getArchivo6() {
		return archivo6;
	}

	public void setArchivo6(File archivo6) {
		this.archivo6 = archivo6;
	}

	public File getArchivo7() {
		return archivo7;
	}

	public void setArchivo7(File archivo7) {
		this.archivo7 = archivo7;
	}

	public File getArchivo8() {
		return archivo8;
	}

	public void setArchivo8(File archivo8) {
		this.archivo8 = archivo8;
	}

	public File getArchivo9() {
		return archivo9;
	}

	public void setArchivo9(File archivo9) {
		this.archivo9 = archivo9;
	}

	public File getArchivo10() {
		return archivo10;
	}

	public void setArchivo10(File archivo10) {
		this.archivo10 = archivo10;
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

	public String getTractora() {
		return tractora;
	}

	public void setTractora(String tractora) {
		this.tractora = tractora;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getIdServicioConsultoria() {
		return idServicioConsultoria;
	}

	public void setIdServicioConsultoria(int idServicioConsultoria) {
		this.idServicioConsultoria = idServicioConsultoria;
	}

	public List<Productos> getProductos() {
		return productos;
	}

	public void setProductos(List<Productos> productos) {
		this.productos = productos;
	}

	public List<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

	public List<Clientes> getClientes() {
		return clientes;
	}

	public void setClientes(List<Clientes> clientes) {
		this.clientes = clientes;
	}

	public List<Certificaciones> getCertificaciones() {
		return certificaciones;
	}

	public void setCertificaciones(List<Certificaciones> certificaciones) {
		this.certificaciones = certificaciones;
	}

	public List<Categorias> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categorias> categorias) {
		this.categorias = categorias;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = Null.free(estatus).equals("true") ? true : false;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public String getEstatusConsultoria() {
		return estatusConsultoria;
	}

	public void setEstatusConsultoria(String estatusConsultoria) {
		this.estatusConsultoria = estatusConsultoria;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public boolean getDesactivar() {
		return desactivar;
	}

	public void setDesactivar(boolean desactivar) {
		this.desactivar = desactivar;
	}

	public void setDesactivar(String desactivar) {
		this.desactivar = Null.free(desactivar).equalsIgnoreCase("true") ? true
				: false;
	}

	public String getNombreAcepta() {
		return nombreAcepta;
	}

	public void setNombreAcepta(String nombreAcepta) {
		this.nombreAcepta = nombreAcepta;
	}

	public String getApellidoPaternoAcepta() {
		return apellidoPaternoAcepta;
	}

	public void setApellidoPaternoAcepta(String apellidoPaternoAcepta) {
		this.apellidoPaternoAcepta = apellidoPaternoAcepta;
	}

	public String getApellidoMaternoAcepta() {
		return apellidoMaternoAcepta;
	}

	public void setApellidoMaternoAcepta(String apellidoMaternoAcepta) {
		this.apellidoMaternoAcepta = apellidoMaternoAcepta;
	}

}