/*
 * ConsultorasService.java        01/04/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.service;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.ConsultoraNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Pagos;
import mx.com.vgati.ccmx.vinculacion.consultoras.exception.ConsultoraNoObtenidaException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface ConsultorasService {

	public Consultoras getConsultora(int id)
			throws ConsultoraNoObtenidaException;
	public Mensaje saveRolConsultora(Consultoras consultoras)
	throws ConsultoraNoAlmacenadaException;
	public List<Consultoras> getConsultorasAdmin(int idPadre) throws DaoException;
	public List<PyMEs> getPymesAdmin(int idUsuarioAdmin) throws DaoException;
	public List<PyMEs> getPymes(int idConsultor) throws DaoException;
	public Mensaje saveRelPymesConsultora(int uPymes,int uConsultor);
	public Mensaje saveCedula(int idServicio,String cedula);
	public String saveFacturaAnticipo(String numeroFactura,String idServicios);
	public String saveFacturaAbono1(String numeroFactura,String idServicios);
	public String saveFacturaAbono2(String numeroFactura,String idServicios);
	public String saveFacturaFiniquito(String numeroFactura,String idServicios);
	public String getPymeByServicio(int idServicio);
	public List<Pagos> getPagos(int idConsultora,int filtro);
	public Pagos getPagos(int idServicio);
	public List<PyMEs> getBusquedaPyME(String busqueda, String estado,
			String cveScian, String nombreComercial, int idConsultora)
			throws PyMEsNoObtenidasException;
	public Mensaje saveConsultor(Consultoras consultor)
			throws ConsultoraNoAlmacenadaException;
	Mensaje updateConsultor(Consultoras consultor)
			throws ConsultoraNoAlmacenadaException;

}