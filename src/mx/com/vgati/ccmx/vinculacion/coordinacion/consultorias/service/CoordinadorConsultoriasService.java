/*
 * CoordinadorConsultoriasService.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.service;

import java.util.Date;
import java.util.List;

import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Facturas;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dto.Consultorias;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.exception.ConsultoriasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ConsultoriasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMENoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.report.exception.FacturasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.report.exception.FacturasNoObtenidasException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface CoordinadorConsultoriasService {

	/**
	 * @param id
	 * @return
	 * @throws ConsultoriasNoObtenidasException
	 */
	public List<Consultorias> getConsultorias(int id)
			throws ConsultoriasNoObtenidasException;

	/**
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getPyME() throws PyMEsNoObtenidasException;

	/**
	 * @return
	 * @throws ConsultoriasNoObtenidasException
	 */
	public List<Consultoras> getConsultora()
			throws ConsultoriasNoObtenidasException;

	/**
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getDiplomaPyME() throws PyMEsNoObtenidasException;

	/**
	 * @param idConsultora
	 * @param idPyMEs
	 * @return
	 * @throws PyMENoAlmacenadaException
	 */
	public Mensaje asignaPyMEs(int idConsultora, String idPyMEs)
			throws PyMENoAlmacenadaException;

	/**
	 * @param idPyMEs
	 * @return
	 * @throws ConsultoriasNoAlmacenadasException
	 */
	public Mensaje regDiploma(String idPyMEs)
			throws ConsultoriasNoAlmacenadasException;

	/**
	 * @param idUsuario
	 * @return
	 * @throws FacturasNoObtenidasException
	 */
	public List<Facturas> getFactura(int idUsuario)
			throws FacturasNoObtenidasException;

	/**
	 * @param numeroFactura
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getDetalleFactura(String numeroFactura)
			throws PyMEsNoObtenidasException;

	/**
	 * @param factura
	 * @param monto
	 * @return
	 * @throws FacturasNoAlmacenadasException
	 */
	public Mensaje liberaFactura(String factura, String monto)
			throws FacturasNoAlmacenadasException;

	/**
	 * @param idPagosFacturas
	 * @return
	 * @throws FacturasNoAlmacenadasException
	 */
	public Mensaje quitarRelFactura(String idPagosFacturas)
			throws FacturasNoAlmacenadasException;

	/**
	 * @param busqueda
	 * @param tractora
	 * @param cveScian
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getReasignaPyME(String busqueda, String tractora,
			String cveScian) throws PyMEsNoObtenidasException;

	/**
	 * @param idConsultora
	 * @param idPyMEs
	 * @return
	 * @throws PyMENoAlmacenadaException
	 */
	public Mensaje reAsignaPyME(int idConsultora, String idPyMEs)
			throws PyMENoAlmacenadaException;

	/**
	 * @param idUsuario
	 * @return
	 * @throws FacturasNoObtenidasException
	 */
	public List<Facturas> getInfoFactura(int idUsuario)
			throws FacturasNoObtenidasException;

	/**
	 * @param idFacturas
	 * @param fechaPago
	 * @return
	 * @throws FacturasNoAlmacenadasException
	 */
	public Mensaje saveFechaFactura(String idFacturas, Date fechaPago)
			throws FacturasNoAlmacenadasException;

}