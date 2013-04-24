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
import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Certificaciones;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.AsistentesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.CertificacionesNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.CertificacionesNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ConsultoriasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.DiplomadosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMeNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMesNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.RespuestaNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.framework.dto.Documento;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;
import mx.com.vgati.framework.dto.Respuesta;


public interface PyMEsService {
	
	public PyMEs getPyME(int id) throws PyMesNoObtenidasException;
	
	public String getIdDomicilio(int id) throws DomiciliosNoObtenidosException;
	
	public Domicilios getDomicilio(int id) throws DomiciliosNoObtenidosException;
	
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
	
	public Mensaje saveCertificacion(Certificaciones certificaciones) 
			throws CertificacionesNoAlmacenadasException;
	
	public Mensaje updateCertificacion(Certificaciones certificaciones) 
			throws CertificacionesNoAlmacenadasException;
	
	public Requerimientos getShowRequerimiento(int idRequerimiento) throws RequerimientosNoObtenidosException;
	
	public Mensaje saveRespuesta(Respuesta respuesta) throws RespuestaNoAlmacenadaException;
	
	public Mensaje saveServDiplomado(ServiciosDiplomado serviciosDiplomado) throws DiplomadosNoAlmacenadosException;
	
	public Mensaje saveAsistente(Asistentes asistentes) throws AsistentesNoAlmacenadosException;
	
	public Mensaje saveConsultoria(ServiciosConsultoria serviciosConsultoria) 
			throws ConsultoriasNoAlmacenadasException;
	
	public List<PyMEs> getBusquedaPyME(String busqueda, String estado, String sector, String subSector) 
			throws PyMesNoObtenidasException;
	
	public List<Requerimientos> getRequerimiento(String busqueda, String tractoraReq, java.sql.Date fechaDesde, java.sql.Date fechaHasta) 
			throws RequerimientosNoObtenidosException;
	
	public List<Requerimientos> getFecha() throws RequerimientosNoObtenidosException;
	
	public List<Tractoras> getTractora() throws TractorasNoObtenidasException;
	
	public Documento getArchivo(int id) throws DocumentoNoObtenidoException;

}