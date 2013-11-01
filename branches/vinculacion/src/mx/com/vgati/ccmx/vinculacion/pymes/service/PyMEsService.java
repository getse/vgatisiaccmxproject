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
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.DiplomadosNoObtenidosException;
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

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface PyMEsService {

	/**
	 * @param id
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public PyMEs getPyME(int id) throws PyMEsNoObtenidasException;

	/**
	 * @param id
	 * @return
	 * @throws DomiciliosNoObtenidosException
	 */
	public String getIdDomicilio(int id) throws DomiciliosNoObtenidosException;

	/**
	 * @param id
	 * @return
	 * @throws DomiciliosNoObtenidosException
	 */
	public Domicilios getDomicilio(int id)
			throws DomiciliosNoObtenidosException;

	/**
	 * @param id
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public EstadosVenta getEstadoVenta(int id) throws PyMEsNoObtenidasException;

	/**
	 * @param id
	 * @return
	 * @throws IndicadoresNoObtenidosException
	 */
	public String getIdIndicador(int id) throws IndicadoresNoObtenidosException;

	/**
	 * @param id
	 * @return
	 * @throws IndicadoresNoObtenidosException
	 */
	public Indicadores getIndicador(int id)
			throws IndicadoresNoObtenidosException;

	/**
	 * @param pyMEs
	 * @param estadosVenta
	 * @return
	 * @throws PyMENoAlmacenadaException
	 */
	public Mensaje updatePyME(PyMEs pyMEs, EstadosVenta estadosVenta)
			throws PyMENoAlmacenadaException;

	/**
	 * @param domicilios
	 * @return
	 * @throws DomiciliosNoAlmacenadosException
	 */
	public Mensaje saveDomicilio(Domicilios domicilios)
			throws DomiciliosNoAlmacenadosException;

	/**
	 * @param domicilios
	 * @param pyMEs
	 * @return
	 * @throws DomiciliosNoAlmacenadosException
	 */
	public Mensaje saveRelDomicilio(Domicilios domicilios, PyMEs pyMEs)
			throws DomiciliosNoAlmacenadosException;

	/**
	 * @param domicilios
	 * @return
	 * @throws DomiciliosNoAlmacenadosException
	 */
	public Mensaje updateDomicilio(Domicilios domicilios)
			throws DomiciliosNoAlmacenadosException;

	/**
	 * @param indicadores
	 * @return
	 * @throws IndicadoresNoAlmacenadosException
	 */
	public Mensaje saveIndicador(Indicadores indicadores)
			throws IndicadoresNoAlmacenadosException;

	/**
	 * @param indicadores
	 * @return
	 * @throws IndicadoresNoAlmacenadosException
	 */
	public Mensaje updateIndicador(Indicadores indicadores)
			throws IndicadoresNoAlmacenadosException;

	/**
	 * @param respuesta
	 * @return
	 * @throws RespuestaNoAlmacenadaException
	 */
	public Mensaje saveRespuesta(Respuesta respuesta)
			throws RespuestaNoAlmacenadaException;

	/**
	 * @param serviciosDiplomado
	 * @return
	 * @throws DiplomadosNoAlmacenadosException
	 */
	public Mensaje saveServDiplomado(ServiciosDiplomado serviciosDiplomado)
			throws DiplomadosNoAlmacenadosException;

	/**
	 * @param asistentes
	 * @return
	 * @throws AsistentesNoAlmacenadosException
	 */
	public Mensaje saveAsistente(Asistentes asistentes)
			throws AsistentesNoAlmacenadosException;

	/**
	 * @param serviciosConsultoria
	 * @return
	 * @throws ConsultoriasNoAlmacenadasException
	 */
	public Mensaje saveConsultoria(ServiciosConsultoria serviciosConsultoria)
			throws ConsultoriasNoAlmacenadasException;

	/**
	 * @param busqueda
	 * @param estado
	 * @param cveScian
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getBusquedaPyME(String busqueda, String estado,
			String cveScian) throws PyMEsNoObtenidasException;

	/**
	 * @param busqueda
	 * @param tractoraReq
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param idUsuario
	 * @return
	 * @throws RequerimientosNoObtenidosException
	 */
	public List<Requerimientos> getRequerimiento(String busqueda,
			String tractoraReq, java.sql.Date fechaDesde,
			java.sql.Date fechaHasta, int idUsuario)
			throws RequerimientosNoObtenidosException;

	/**
	 * @return
	 * @throws RequerimientosNoObtenidosException
	 */
	public List<Requerimientos> getFecha()
			throws RequerimientosNoObtenidosException;

	/**
	 * @return
	 * @throws TractorasNoObtenidasException
	 */
	public List<Tractoras> getTractora() throws TractorasNoObtenidasException;

	/**
	 * @param id
	 * @return
	 * @throws DocumentoNoObtenidoException
	 */
	public Documento getArchivo(int id) throws DocumentoNoObtenidoException;

	/**
	 * @param id
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public RelPyMEsTractoras getCalificacion(int id)
			throws PyMEsNoObtenidasException;

	/**
	 * @param id
	 * @return
	 * @throws IndicadoresNoObtenidosException
	 */
	public Indicadores getIndicadorMes(int id)
			throws IndicadoresNoObtenidosException;

	/**
	 * @param id
	 * @return
	 * @throws ConsultoriasNoObtenidasException
	 */
	public ServiciosConsultoria getServConsultorias(int id)
			throws ConsultoriasNoObtenidasException;

	/**
	 * @param cveCat
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public String getNombreCveScian(int cveCat)
			throws PyMEsNoObtenidasException;

	/**
	 * @return
	 * @throws DiplomadosNoObtenidosException
	 */
	public int getGeneracion() throws DiplomadosNoObtenidosException;

	/**
	 * @param generaciones
	 * @return
	 * @throws DiplomadosNoObtenidosException
	 */
	public List<List<Diplomados>> getTemaDiplomado(int generaciones)
			throws DiplomadosNoObtenidosException;

	/**
	 * @param id
	 * @param servicio
	 * @return
	 * @throws ConsultoriasNoObtenidasException
	 */
	public Object getServicioConsultoria(int id, String servicio)
			throws ConsultoriasNoObtenidasException;

	/**
	 * @param id
	 * @return
	 * @throws SesionesNoObtenidasException
	 */
	public List<Sesiones> getSesion(int id) throws SesionesNoObtenidasException;

	/**
	 * @param idDiplomado
	 * @param idUsuario
	 * @return
	 * @throws DiplomadosNoObtenidosException
	 */
	public ServiciosDiplomado getServicioDiplomado(int idDiplomado,
			int idUsuario) throws DiplomadosNoObtenidosException;

	/**
	 * @param id
	 * @return
	 * @throws ParticipantesNoObtenidoException
	 */
	public List<Asistentes> getAsistentes(int id)
			throws ParticipantesNoObtenidoException;

	/**
	 * @return
	 * @throws DiplomadosNoObtenidosException
	 */
	public ServiciosDiplomado getIdServicioDiplomado()
			throws DiplomadosNoObtenidosException;

	/**
	 * @param as
	 * @return
	 * @throws AsistentesNoAlmacenadosException
	 */
	public Mensaje saveAsistentes(Asistentes as)
			throws AsistentesNoAlmacenadosException;

	/**
	 * @param as
	 * @return
	 * @throws AsistentesNoAlmacenadosException
	 */
	public Mensaje updateAsistentes(Asistentes as)
			throws AsistentesNoAlmacenadosException;

	/**
	 * @param idServiciosDiplomado
	 * @return
	 * @throws DocumentoNoObtenidoException
	 */
	public List<Documento> getArchivosDiplomado(int idServiciosDiplomado)
			throws DocumentoNoObtenidoException;

	/**
	 * @param d
	 * @return
	 * @throws DocumentoNoAlmacenadoException
	 */
	public Mensaje saveArchivoServicio(Documento d)
			throws DocumentoNoAlmacenadoException;

	/**
	 * @param idArchivos
	 * @return
	 * @throws DocumentoNoAlmacenadoException
	 */
	public Mensaje deleteArchivoPago(String idArchivos)
			throws DocumentoNoAlmacenadoException;

	/**
	 * @param id
	 * @return
	 * @throws DocumentoNoObtenidoException
	 */
	public Documento getRfc(int id) throws DocumentoNoObtenidoException;

	/**
	 * @param documento
	 * @return
	 * @throws DocumentoNoAlmacenadoException
	 */
	public Mensaje saveRFCPyME(Documento documento)
			throws DocumentoNoAlmacenadoException;

	/**
	 * @param original
	 * @param nuevo
	 * @param id
	 * @return
	 * @throws PyMENoAlmacenadaException
	 */
	public Mensaje changeCorreo(String original, String nuevo, int id)
			throws PyMENoAlmacenadaException;

}