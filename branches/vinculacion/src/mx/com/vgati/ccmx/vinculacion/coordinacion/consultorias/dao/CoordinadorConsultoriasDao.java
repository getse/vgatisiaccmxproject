/*
 * CoordinadorConsultoriasDao.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dao;

import java.util.Date;
import java.util.List;

import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Facturas;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dto.Consultorias;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface CoordinadorConsultoriasDao {

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public List<Consultorias> getConsultorias(int id) throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<PyMEs> getPyMEs() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<Consultoras> getConsultoras() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<PyMEs> getDiplomasPyMEs() throws DaoException;

	/**
	 * @param idConsultora
	 * @param idPyMEs
	 * @return
	 * @throws DaoException
	 */
	public Mensaje asignaPyMEs(int idConsultora, String idPyMEs)
			throws DaoException;

	/**
	 * @param idPyMEs
	 * @return
	 * @throws DaoException
	 */
	public Mensaje regDiplomas(String idPyMEs) throws DaoException;

	/**
	 * @param idUsuario
	 * @return
	 * @throws DaoException
	 */
	public List<Facturas> getFacturas(int idUsuario) throws DaoException;

	/**
	 * @param numeroFactura
	 * @return
	 * @throws DaoException
	 */
	public List<PyMEs> getDetalleFacturas(String numeroFactura)
			throws DaoException;

	/**
	 * @param factura
	 * @param monto
	 * @return
	 * @throws DaoException
	 */
	public Mensaje liberaFacturas(String factura, String monto)
			throws DaoException;

	/**
	 * @param idPagosFacturas
	 * @return
	 * @throws DaoException
	 */
	public Mensaje quitarRelFacturas(String idPagosFacturas)
			throws DaoException;

	/**
	 * @param busqueda
	 * @param tractora
	 * @param cveScian
	 * @return
	 * @throws DaoException
	 */
	public List<PyMEs> getReasignaPyME(String busqueda, String tractora,
			String cveScian) throws DaoException;

	/**
	 * @param idConsultora
	 * @param idPyMEs
	 * @return
	 * @throws DaoException
	 */
	public Mensaje reAsignaPyMEs(int idConsultora, String idPyMEs)
			throws DaoException;

	/**
	 * @param idUsuario
	 * @return
	 * @throws DaoException
	 */
	public List<Facturas> getInfoFacturas(int idUsuario) throws DaoException;

	/**
	 * @param idFacturas
	 * @param fechaPago
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveFechaFacturas(String idFacturas, Date fechaPago)
			throws DaoException;

}