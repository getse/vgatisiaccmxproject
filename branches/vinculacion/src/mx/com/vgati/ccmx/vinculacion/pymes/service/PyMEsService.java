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

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.exception.ConsultoriasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Sesiones;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.ParticipantesNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.SesionesNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.dto.Requerimientos;
import mx.com.vgati.ccmx.vinculacion.dto.Respuesta;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoAlmacenadoException;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.AsistentesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ConsultoriasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.DiplomadosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.DiplomadosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.IndicadoresNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.IndicadoresNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMENoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.RespuestaNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.RelPyMEsTractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.framework.dto.Mensaje;

public interface PyMEsService {

	public PyMEs getPyME(int id) throws PyMEsNoObtenidasException;

	public String getIdDomicilio(int id) throws DomiciliosNoObtenidosException;

	public Domicilios getDomicilio(int id)
			throws DomiciliosNoObtenidosException;

	public EstadosVenta getEstadoVenta(int id) throws PyMEsNoObtenidasException;

	public String getIdIndicador(int id) throws IndicadoresNoObtenidosException;

	public Indicadores getIndicador(int id)
			throws IndicadoresNoObtenidosException;

	public Mensaje updatePyME(PyMEs pyMEs, EstadosVenta estadosVenta)
			throws PyMENoAlmacenadaException;

	public Mensaje saveDomicilio(Domicilios domicilios)
			throws DomiciliosNoAlmacenadosException;

	public Mensaje saveRelDomicilio(Domicilios domicilios, PyMEs pyMEs)
			throws DomiciliosNoAlmacenadosException;

	public Mensaje updateDomicilio(Domicilios domicilios)
			throws DomiciliosNoAlmacenadosException;

	public Mensaje saveIndicador(Indicadores indicadores)
			throws IndicadoresNoAlmacenadosException;

	public Mensaje updateIndicador(Indicadores indicadores)
			throws IndicadoresNoAlmacenadosException;

	public Mensaje saveRespuesta(Respuesta respuesta)
			throws RespuestaNoAlmacenadaException;

	public Mensaje saveServDiplomado(ServiciosDiplomado serviciosDiplomado)
			throws DiplomadosNoAlmacenadosException;

	public Mensaje saveAsistente(Asistentes asistentes)
			throws AsistentesNoAlmacenadosException;

	public Mensaje saveConsultoria(ServiciosConsultoria serviciosConsultoria)
			throws ConsultoriasNoAlmacenadasException;

	public List<PyMEs> getBusquedaPyME(String busqueda, String estado,
			String cveScian) throws PyMEsNoObtenidasException;

	public List<Requerimientos> getRequerimiento(String busqueda,
			String tractoraReq, java.sql.Date fechaDesde,
			java.sql.Date fechaHasta, int idUsuario)
			throws RequerimientosNoObtenidosException;

	public List<Requerimientos> getFecha()
			throws RequerimientosNoObtenidosException;

	public List<Tractoras> getTractora() throws TractorasNoObtenidasException;

	public Documento getArchivo(int id) throws DocumentoNoObtenidoException;

	public RelPyMEsTractoras getCalificacion(int id)
			throws PyMEsNoObtenidasException;

	public Indicadores getIndicadorMes(int id)
			throws IndicadoresNoObtenidosException;

	public ServiciosConsultoria getServConsultorias(int id)
			throws ConsultoriasNoObtenidasException;

	public String getNombreCveScian(int cveCat)
			throws PyMEsNoObtenidasException;

	public int getGeneracion() throws DiplomadosNoObtenidosException;
	
	public List<List<Diplomados>> getTemaDiplomado(int generaciones)
			throws DiplomadosNoObtenidosException;

	public Object getServicioConsultoria(int id, String servicio)
			throws ConsultoriasNoObtenidasException;

	public List<Sesiones> getSesion(int id)
			throws SesionesNoObtenidasException;

	public ServiciosDiplomado getServicioDiplomado(int idDiplomado,
			int idUsuario) throws DiplomadosNoObtenidosException;

	public List<Asistentes> getAsistentes(int id)
			throws ParticipantesNoObtenidoException;

	public ServiciosDiplomado getIdServicioDiplomado()
			throws DiplomadosNoObtenidosException;

	public Mensaje saveAsistentes(Asistentes as)
			throws AsistentesNoAlmacenadosException;

	public Mensaje updateAsistentes(Asistentes as)
			throws AsistentesNoAlmacenadosException;

	public List<Documento> getArchivosDiplomado(int idServiciosDiplomado)
			throws DocumentoNoObtenidoException;

	public Mensaje saveArchivoServicio(Documento d)
			throws DocumentoNoAlmacenadoException;

	public Mensaje deleteArchivoPago(String idArchivos)
			throws DocumentoNoAlmacenadoException;

	public Documento getRfc(int id) throws DocumentoNoObtenidoException;

	public Mensaje saveRFCPyME(Documento documento)
			throws DocumentoNoAlmacenadoException;

}