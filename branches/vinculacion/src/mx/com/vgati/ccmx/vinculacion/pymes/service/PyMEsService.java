/*
 * PyMEsService.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.service;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Certificaciones;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Clientes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.AsistentesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.CertificacionesNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.CertificacionesNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ClienteNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ClientesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ConsultoriasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.DiplomadosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMeNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMesNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;


public interface PyMEsService {

	public PyMEs getPyME(int id) throws PyMesNoObtenidasException;

	public String getIdDomicilio(int id) throws DomiciliosNoObtenidosException;

	public Domicilios getDomicilio(int id) throws DomiciliosNoObtenidosException;
	
	public String getIdCliente(int id) throws ClienteNoObtenidoException;
	
	public Clientes getCliente(int id) throws ClienteNoObtenidoException;
	
	public String getIdCertificacion(int idUsuario) 
			throws CertificacionesNoObtenidasException;
	
	public Certificaciones getCertificacion(int id) 
			throws CertificacionesNoObtenidasException;

	public Mensaje updatePyME(PyMEs pyMes) throws PyMeNoAlmacenadaException;

	public Mensaje saveDomicilio(Domicilios domicilios) 
			throws DomiciliosNoAlmacenadosException;

	public Mensaje saveRelDomicilio(Domicilios domicilios, PyMEs pyMes)
			throws DomiciliosNoAlmacenadosException;

	public Mensaje updateDomicilio(Domicilios domicilios) 
			throws DomiciliosNoAlmacenadosException;

	public Mensaje saveCliente(Clientes clientes) 
			throws ClientesNoAlmacenadosException;
	
	public Mensaje updateCliente(Clientes clientes) 
			throws ClientesNoAlmacenadosException;

	public Mensaje saveCertificacion(Certificaciones certificaciones) 
			throws CertificacionesNoAlmacenadasException;

	public Mensaje updateCertificacion(Certificaciones certificaciones) 
			throws CertificacionesNoAlmacenadasException;

	public List<PyMEs> getBusquedaPyME(String busqueda, String estado, String codigoPostal, String sector, String subSector) 
			throws PyMesNoObtenidasException;

	public List<ServiciosDiplomado> getServiciosDiplomado(ServiciosDiplomado serviciosDiplomado) 
			throws DiplomadosNoObtenidosException;

	public Mensaje saveAsistente(Asistentes asistentes) throws AsistentesNoAlmacenadosException;

	public List<Requerimientos> getRequerimiento(String busqueda, String tractoraReq) 
			throws RequerimientosNoObtenidosException;

	public Mensaje saveConsultoria(ServiciosConsultoria serviciosConsultoria) 
			throws ConsultoriasNoAlmacenadasException;
	
}