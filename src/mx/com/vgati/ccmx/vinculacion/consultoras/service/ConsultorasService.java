/*
 * ConsultorasService.java        01/04/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.service;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.ConsultoraNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Pagos;
import mx.com.vgati.ccmx.vinculacion.consultoras.exception.ConsultoraNoObtenidaException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.exception.ConsultoriasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ConsultoriasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMENoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.report.exception.FacturasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
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
	public List<Consultoras> getConsultorasAdmin(int idPadre) throws ConsultoraNoObtenidaException;
	public List<PyMEs> getPymesAdmin(int idUsuarioAdmin) throws PyMEsNoObtenidasException;
	public List<PyMEs> getPymes(int idConsultor) throws PyMEsNoObtenidasException;
	public Mensaje saveRelPymesConsultora(int uPymes,int uConsultor) throws PyMENoAlmacenadaException;
	public Mensaje saveCedula(int idServicio,String cedula) throws PyMENoAlmacenadaException;
	public String saveFacturaAnticipo(String numeroFactura,String idServicios) throws FacturasNoAlmacenadasException;
	public String saveFacturaAbono1(String numeroFactura,String idServicios) throws FacturasNoAlmacenadasException;
	public String saveFacturaAbono2(String numeroFactura,String idServicios) throws FacturasNoAlmacenadasException;
	public String saveFacturaFiniquito(String numeroFactura,String idServicios) throws FacturasNoAlmacenadasException;
	public String getPymeByServicio(int idServicio) throws PyMEsNoObtenidasException;
	public List<Pagos> getPagos(int idConsultora,int filtro) throws RequerimientosNoObtenidosException;
	public Pagos getPagos(int idServicio) throws RequerimientosNoObtenidosException;
	public List<PyMEs> getBusquedaPyME(String busqueda, String estado,
			String cveScian, String nombreComercial, int idConsultora,int idUsuario)
			throws PyMEsNoObtenidasException;
	public Mensaje saveConsultor(Consultoras consultor)
			throws ConsultoraNoAlmacenadaException;
	public Mensaje updateConsultor(Consultoras consultor)
			throws ConsultoraNoAlmacenadaException;
	public List<PyMEs> getPyMEsCedula(int idConsultor)
			throws PyMEsNoObtenidasException;
	public ServiciosConsultoria getServiciosConsultoria(int idConsultora)
			throws ConsultoriasNoObtenidasException;
	public Mensaje saveServiciosConsultoria(ServiciosConsultoria serviciosConsultoria)
			throws ConsultoriasNoAlmacenadasException;
	public List<PyMEs> getPyMEsConsultor(int idConsultora)
			throws PyMEsNoObtenidasException;
}