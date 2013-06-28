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

	public List<Consultorias> getConsultorias(int id)
	throws ConsultoriasNoObtenidasException;

	public List<PyMEs> getPyME()throws PyMEsNoObtenidasException;

	public List<Consultoras> getConsultora()throws ConsultoriasNoObtenidasException;

	public List<PyMEs> getDiplomaPyME()throws PyMEsNoObtenidasException;

	public Mensaje asignaPyMEs(int idConsultora, String idPyMEs)
			throws PyMENoAlmacenadaException;

	public Mensaje regDiploma(String idPyMEs)throws ConsultoriasNoAlmacenadasException;

	public List<Facturas> getFactura(int idUsuario)throws FacturasNoObtenidasException;

	public List<PyMEs> getDetalleFactura(String numeroFactura)throws PyMEsNoObtenidasException;

	public Mensaje liberaFactura(String idFacturas)throws FacturasNoAlmacenadasException;

	public Mensaje quitarRelFactura(String idPagosFacturas)
			throws FacturasNoAlmacenadasException;

	public List<PyMEs> getReasignaPyME(String busqueda, String tractora, String cveScian) 
			throws PyMEsNoObtenidasException;

	public Mensaje reAsignaPyME(int idConsultora, String idPyMEs)
			throws PyMENoAlmacenadaException;

	public List<Facturas> getInfoFactura(int idUsuario)
			throws FacturasNoObtenidasException;

	public Mensaje saveFechaFactura(String idFacturas, Date fechaPago)
			throws FacturasNoAlmacenadasException;

}