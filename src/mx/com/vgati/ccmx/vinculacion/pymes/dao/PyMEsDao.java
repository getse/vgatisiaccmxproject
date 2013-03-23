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

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Certificaciones;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Clientes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;

public interface PyMEsDao {

	public PyMEs getPyMEs(int id) throws DaoException;

	public String getIdDomicilio(int id) throws DaoException;

	public Domicilios getDomicilios(int id) throws DaoException;

	public String getIdCliente(int id) throws DaoException;

	public Clientes getClientes(int id) throws DaoException;

	public String getIdCertificacion(int id) throws DaoException;

	public Certificaciones getCertificaciones(int id) throws DaoException;

	public Mensaje updatePyMEs(PyMEs pyMes) throws DaoException;

	public Mensaje saveDomicilios(Domicilios domicilios) throws DaoException;

	public Mensaje saveRelDomicilios(Domicilios domicilios, PyMEs pyMes) 
	throws DaoException;

	public Mensaje updateDomicilios(Domicilios domicilios) throws DaoException;

	public Mensaje saveClientes(Clientes clientes) throws DaoException;

	public Mensaje updateClientes(Clientes clientes) throws DaoException;

	public Mensaje saveCertificaciones(Certificaciones certificaciones) throws DaoException;

	public Mensaje updateCertificaciones(Certificaciones certificaciones) throws DaoException;

	public List<PyMEs> getBusquedaPyMEs(String busqueda,String estado, String codigoPostal, String sector, String subSector) 
	throws DaoException;

	public List<ServiciosDiplomado> getServiciosDiplomados(ServiciosDiplomado serviciosDiplomado) throws DaoException;

	public Mensaje saveAsistentes(Asistentes asistentes) throws DaoException;

	public List<Requerimientos> getRequerimientos(String busqueda, String tractoraReq) throws DaoException;

	public Mensaje saveConsultorias(ServiciosConsultoria serviciosConsultoria) throws DaoException;

}
