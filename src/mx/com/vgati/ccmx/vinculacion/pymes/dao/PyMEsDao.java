/*
 * PyMEsDao.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.dao;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Sesiones;
import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.dto.Requerimientos;
import mx.com.vgati.ccmx.vinculacion.dto.Respuesta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.RelPyMEsTractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface PyMEsDao {

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public PyMEs getPyMEs(int id) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public String getIdDomicilio(int id) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Domicilios getDomicilios(int id) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public EstadosVenta getEstadosVentas(int id) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public String getIdIndicadores(int id) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Indicadores getIndicadores(int id) throws DaoException;

	/**
	 * @param pyMEs
	 * @param estadosVenta
	 * @return
	 * @throws DaoException
	 */
	public Mensaje updatePyMEs(PyMEs pyMEs, EstadosVenta estadosVenta)
			throws DaoException;

	/**
	 * @param domicilios
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveDomicilios(Domicilios domicilios) throws DaoException;

	/**
	 * @param domicilios
	 * @param pyMEs
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveRelDomicilios(Domicilios domicilios, PyMEs pyMEs)
			throws DaoException;

	/**
	 * @param domicilios
	 * @return
	 * @throws DaoException
	 */
	public Mensaje updateDomicilios(Domicilios domicilios) throws DaoException;

	/**
	 * @param indicadores
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveIndicadores(Indicadores indicadores) throws DaoException;

	/**
	 * @param indicadores
	 * @return
	 * @throws DaoException
	 */
	public Mensaje updateIndicadores(Indicadores indicadores)
			throws DaoException;

	/**
	 * @param respuesta
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveRespuestas(Respuesta respuesta) throws DaoException;

	/**
	 * @param serviciosDiplomado
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveServDiplomados(ServiciosDiplomado serviciosDiplomado)
			throws DaoException;

	/**
	 * @param serviciosConsultoria
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveConsultorias(ServiciosConsultoria serviciosConsultoria)
			throws DaoException;

	/**
	 * @param busqueda
	 * @param estado
	 * @param cveScian
	 * @return
	 * @throws DaoException
	 */
	public List<PyMEs> getBusquedaPyMEs(String busqueda, String estado,
			String cveScian) throws DaoException;

	/**
	 * @param busqueda
	 * @param tractoraReq
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param idUsuario
	 * @return
	 * @throws DaoException
	 */
	public List<Requerimientos> getRequerimientos(String busqueda,
			String tractoraReq, java.sql.Date fechaDesde,
			java.sql.Date fechaHasta, int idUsuario) throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<Requerimientos> getFechas() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<Tractoras> getTractoras() throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Documento getArchivo(int id) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public RelPyMEsTractoras getCalificaciones(int id) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Indicadores getIndicadoresMes(int id) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public ServiciosConsultoria getConsultorias(int id) throws DaoException;

	/**
	 * @param cveCat
	 * @return
	 * @throws DaoException
	 */
	public String getNombresCveScian(int cveCat) throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public int getGeneraciones() throws DaoException;

	/**
	 * @param generaciones
	 * @return
	 * @throws DaoException
	 */
	public List<List<Diplomados>> getTemaDiplomados(int generaciones)
			throws DaoException;

	/**
	 * @param id
	 * @param servicio
	 * @return
	 * @throws DaoException
	 */
	public Object getServicioConsultorias(int id, String servicio)
			throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public List<Sesiones> getSesiones(int id) throws DaoException;

	/**
	 * @param idDiplomado
	 * @param idUsuario
	 * @return
	 * @throws DaoException
	 */
	public ServiciosDiplomado getServicioDiplomados(int idDiplomado,
			int idUsuario) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public List<Asistentes> getAsistentes(int id) throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public ServiciosDiplomado getIdServicioDiplomado() throws DaoException;

	/**
	 * @param asistentes
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveAsistentes(Asistentes asistentes) throws DaoException;

	/**
	 * @param asistentes
	 * @return
	 * @throws DaoException
	 */
	public Mensaje updateAsistentes(Asistentes asistentes) throws DaoException;

	/**
	 * @param idServicio
	 * @return
	 * @throws DaoException
	 */
	public List<Documento> getArchivosDiplomados(int idServicio)
			throws DaoException;

	/**
	 * @param d
	 * @return
	 * @throws DaoException
	 */
	public Mensaje insertDocServicio(Documento d) throws DaoException;

	/**
	 * @param idArchivos
	 * @return
	 * @throws DaoException
	 */
	public Mensaje deleteArchivoPagos(String idArchivos) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Documento getRfc(int id) throws DaoException;

	/**
	 * @param documento
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveRFCPyMEs(Documento documento) throws DaoException;

	/**
	 * @param original
	 * @param nuevo
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Mensaje changeCorreo(String original, String nuevo, int id)
			throws DaoException;

}