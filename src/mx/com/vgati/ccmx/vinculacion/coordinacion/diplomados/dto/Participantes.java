/*
 * Participantes.java        28/06/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

/**
 * 
 * 
 * @author Sergio Olivos
 * 
 */
@SuppressWarnings("serial")
public class Participantes extends AbstractBaseDTO {

	private int id;
	private int idUsuario;
	private String telefono;
	private String nombre;
	private String correoElectronico;
	private boolean asistencia1;
	private boolean asistencia2;
	private boolean asistencia3;
	private boolean asistencia4;
	private boolean pago;
	private boolean factura;
	private String cargo;
	private String pyme;
	private String tractora;
	private String numPago;
	private boolean editable1;
	private boolean editable2;
	private boolean editable3;
	private boolean editable4;
	private int sesion;
	private String tema;
	private int generacion;
	private boolean invitacion;
	private boolean activaInvitacion;
	private boolean confirmado1;
	private boolean confirmado2;
	private boolean confirmado3;
	private boolean confirmado4;
	private int idSesion;
	private int idServiciosDiplomado;
	private boolean diploma;
	private boolean seleccion;
	private int idSesion1;
	private int idSesion2;
	private int idSesion3;
	private int idSesion4;
	private String numFile;
	private boolean resagado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public boolean isAsistencia1() {
		return asistencia1;
	}

	public void setAsistencia1(boolean asistencia1) {
		this.asistencia1 = asistencia1;
	}

	public boolean isAsistencia2() {
		return asistencia2;
	}

	public void setAsistencia2(boolean asistencia2) {
		this.asistencia2 = asistencia2;
	}

	public boolean isAsistencia3() {
		return asistencia3;
	}

	public void setAsistencia3(boolean asistencia3) {
		this.asistencia3 = asistencia3;
	}

	public boolean isAsistencia4() {
		return asistencia4;
	}

	public void setAsistencia4(boolean asistencia4) {
		this.asistencia4 = asistencia4;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public boolean isFactura() {
		return factura;
	}

	public void setFactura(boolean factura) {
		this.factura = factura;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getPyme() {
		return pyme;
	}

	public void setPyme(String pyme) {
		this.pyme = pyme;
	}

	public String getTractora() {
		return tractora;
	}

	public void setTractora(String tractora) {
		this.tractora = tractora;
	}

	public String getNumPago() {
		return numPago;
	}

	public void setNumPago(String numPago) {
		this.numPago = numPago;
	}

	public boolean isEditable1() {
		return editable1;
	}

	public void setEditable1(boolean editable1) {
		this.editable1 = editable1;
	}

	public boolean isEditable2() {
		return editable2;
	}

	public void setEditable2(boolean editable2) {
		this.editable2 = editable2;
	}

	public boolean isEditable3() {
		return editable3;
	}

	public void setEditable3(boolean editable3) {
		this.editable3 = editable3;
	}

	public boolean isEditable4() {
		return editable4;
	}

	public void setEditable4(boolean editable4) {
		this.editable4 = editable4;
	}

	public int getSesion() {
		return sesion;
	}

	public void setSesion(int sesion) {
		this.sesion = sesion;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public int getGeneracion() {
		return generacion;
	}

	public void setGeneracion(int generacion) {
		this.generacion = generacion;
	}

	public boolean isInvitacion() {
		return invitacion;
	}

	public void setInvitacion(boolean invitacion) {
		this.invitacion = invitacion;
	}

	public boolean isActivaInvitacion() {
		return activaInvitacion;
	}

	public void setActivaInvitacion(boolean activaInvitacion) {
		this.activaInvitacion = activaInvitacion;
	}

	public boolean isConfirmado1() {
		return confirmado1;
	}

	public void setConfirmado1(boolean confirmado1) {
		this.confirmado1 = confirmado1;
	}

	public boolean isConfirmado2() {
		return confirmado2;
	}

	public void setConfirmado2(boolean confirmado2) {
		this.confirmado2 = confirmado2;
	}

	public boolean isConfirmado3() {
		return confirmado3;
	}

	public void setConfirmado3(boolean confirmado3) {
		this.confirmado3 = confirmado3;
	}

	public boolean isConfirmado4() {
		return confirmado4;
	}

	public void setConfirmado4(boolean confirmado4) {
		this.confirmado4 = confirmado4;
	}

	public int getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}

	public int getIdServiciosDiplomado() {
		return idServiciosDiplomado;
	}

	public void setIdServiciosDiplomado(int idServiciosDiplomado) {
		this.idServiciosDiplomado = idServiciosDiplomado;
	}

	public boolean isDiploma() {
		return diploma;
	}

	public void setDiploma(boolean diploma) {
		this.diploma = diploma;
	}

	public boolean isSeleccion() {
		return seleccion;
	}

	public void setSeleccion(boolean seleccion) {
		this.seleccion = seleccion;
	}

	public int getIdSesion1() {
		return idSesion1;
	}

	public void setIdSesion1(int idSesion1) {
		this.idSesion1 = idSesion1;
	}

	public int getIdSesion2() {
		return idSesion2;
	}

	public void setIdSesion2(int idSesion2) {
		this.idSesion2 = idSesion2;
	}

	public int getIdSesion3() {
		return idSesion3;
	}

	public void setIdSesion3(int idSesion3) {
		this.idSesion3 = idSesion3;
	}

	public int getIdSesion4() {
		return idSesion4;
	}

	public void setIdSesion4(int idSesion4) {
		this.idSesion4 = idSesion4;
	}

	public String getNumFile() {
		return numFile;
	}

	public void setNumFile(String numFile) {
		this.numFile = numFile;
	}

	public boolean isResagado() {
		return resagado;
	}

	public void setResagado(boolean resagado) {
		this.resagado = resagado;
	}
}
