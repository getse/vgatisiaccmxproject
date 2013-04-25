/*
 * PyMEsDao.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.dao;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Certificaciones;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Diplomado;
import mx.com.vgati.framework.dto.Documento;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;
import mx.com.vgati.framework.dto.Respuesta;

public interface PyMEsDao {

	public PyMEs getPyMEs(int id) throws DaoException;

	public String getIdDomicilio(int id) throws DaoException;

	public Domicilios getDomicilios(int id) throws DaoException;

	public String getIdCertificacion(int id) throws DaoException;

	public Certificaciones getCertificaciones(int id) throws DaoException;

	public Mensaje updatePyMEs(PyMEs pyMEs) throws DaoException;

	public Mensaje saveDomicilios(Domicilios domicilios) throws DaoException;

	public Mensaje saveRelDomicilios(Domicilios domicilios, PyMEs pyMEs)
			throws DaoException;

	public Mensaje updateDomicilios(Domicilios domicilios) throws DaoException;

	public Mensaje saveCertificaciones(Certificaciones certificaciones)
			throws DaoException;

	public Mensaje updateCertificaciones(Certificaciones certificaciones)
			throws DaoException;

	public Requerimientos getShowRequerimientos(int idRequerimiento)
			throws DaoException;

	public Mensaje saveRespuestas(Respuesta respuesta) throws DaoException;

	public Mensaje saveServDiplomados(ServiciosDiplomado serviciosDiplomado)
			throws DaoException;

	public Mensaje saveAsistentes(Asistentes asistentes) throws DaoException;

	public Mensaje saveConsultorias(ServiciosConsultoria serviciosConsultoria)
			throws DaoException;

	public List<PyMEs> getBusquedaPyMEs(String busqueda, String estado,
			String sector, String subSector) throws DaoException;

	public List<Requerimientos> getRequerimientos(String busqueda,
			String tractoraReq, java.sql.Date fechaDesde,
			java.sql.Date fechaHasta) throws DaoException;

	public List<Requerimientos> getFechas() throws DaoException;

	public List<Tractoras> getTractoras() throws DaoException;
	
	public List<Diplomado> getDiplomados() throws DaoException;

	public Documento getArchivo(int id) throws DaoException;

}