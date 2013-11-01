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
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Facturas;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Pagos;
import mx.com.vgati.ccmx.vinculacion.consultoras.exception.ConsultoraNoObtenidaException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.exception.ConsultoriasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.DiplomadosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ConsultoriasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMENoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.report.exception.FacturasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.report.exception.FacturasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface ConsultorasService {

	/**
	 * @param id
	 * @return
	 * @throws ConsultoraNoObtenidaException
	 */
	public Consultoras getConsultora(int id)
			throws ConsultoraNoObtenidaException;

	/**
	 * @param consultoras
	 * @return
	 * @throws ConsultoraNoAlmacenadaException
	 */
	public Mensaje saveRolConsultora(Consultoras consultoras)
			throws ConsultoraNoAlmacenadaException;

	/**
	 * @param idPadre
	 * @return
	 * @throws ConsultoraNoObtenidaException
	 */
	public List<Consultoras> getConsultorasAdmin(int idPadre)
			throws ConsultoraNoObtenidaException;

	/**
	 * @param idUsuarioAdmin
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getPymesAdmin(int idUsuarioAdmin)
			throws PyMEsNoObtenidasException;

	/**
	 * @param idConsultor
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getPymes(int idConsultor)
			throws PyMEsNoObtenidasException;

	/**
	 * @param uPymes
	 * @param uConsultor
	 * @return
	 * @throws PyMENoAlmacenadaException
	 */
	public Mensaje saveRelPymesConsultora(int uPymes, int uConsultor)
			throws PyMENoAlmacenadaException;

	/**
	 * @param idServicio
	 * @param cedula
	 * @return
	 * @throws PyMENoAlmacenadaException
	 */
	public Mensaje saveCedula(List<Integer> idServicio, String cedula)
			throws PyMENoAlmacenadaException;

	/**
	 * @param idServicio
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public String getPymeByServicio(int idServicio)
			throws PyMEsNoObtenidasException;

	/**
	 * @param idConsultora
	 * @param filtro
	 * @return
	 * @throws RequerimientosNoObtenidosException
	 */
	public List<Pagos> getPagos(int idConsultora, int filtro)
			throws RequerimientosNoObtenidosException;

	/**
	 * @param idServicio
	 * @return
	 * @throws RequerimientosNoObtenidosException
	 */
	public Pagos getPagos(int idServicio)
			throws RequerimientosNoObtenidosException;

	/**
	 * @param busqueda
	 * @param estado
	 * @param cveScian
	 * @param idConsultora
	 * @param idUsuario
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getBusquedaPyME(String busqueda, String estado,
			String cveScian, int idConsultora, int idUsuario)
			throws PyMEsNoObtenidasException;

	/**
	 * @param consultor
	 * @return
	 * @throws ConsultoraNoAlmacenadaException
	 */
	public Mensaje saveConsultor(Consultoras consultor)
			throws ConsultoraNoAlmacenadaException;

	/**
	 * @param consultor
	 * @return
	 * @throws ConsultoraNoAlmacenadaException
	 */
	public Mensaje updateConsultor(Consultoras consultor)
			throws ConsultoraNoAlmacenadaException;

	/**
	 * @param idConsultor
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getPyMEsCedula(int idConsultor)
			throws PyMEsNoObtenidasException;

	/**
	 * @param idConsultora
	 * @return
	 * @throws ConsultoriasNoObtenidasException
	 */
	public ServiciosConsultoria getServiciosConsultoria(int idConsultora)
			throws ConsultoriasNoObtenidasException;

	/**
	 * @param serviciosConsultoria
	 * @return
	 * @throws ConsultoriasNoAlmacenadasException
	 */
	public Mensaje saveServiciosConsultoria(
			ServiciosConsultoria serviciosConsultoria)
			throws ConsultoriasNoAlmacenadasException;

	/**
	 * @param idConsultora
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getPyMEsConsultor(int idConsultora)
			throws PyMEsNoObtenidasException;

	/**
	 * @return
	 * @throws ConsultoriasNoObtenidasException
	 */
	public String getCorreoCordCons() throws ConsultoriasNoObtenidasException;

	/**
	 * @param idUsuario
	 * @return
	 * @throws FacturasNoObtenidasException
	 */
	public List<Facturas> getFacturasPorAdmin(int idUsuario)
			throws FacturasNoObtenidasException;

	/**
	 * @param anticipo
	 * @param abono1
	 * @param abono2
	 * @param finiquito
	 * @param idFactura
	 * @return
	 * @throws FacturasNoAlmacenadasException
	 */
	public Mensaje saveFacturas(List<Pagos> anticipo, List<Pagos> abono1,
			List<Pagos> abono2, List<Pagos> finiquito, String idFactura)
			throws FacturasNoAlmacenadasException;

	/**
	 * @param idFactura
	 * @param idUsuario
	 * @return
	 * @throws FacturasNoAlmacenadasException
	 */
	public Mensaje saveFactura(String idFactura, int idUsuario)
			throws FacturasNoAlmacenadasException;

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
	public List<PyMEs> getPymesLiberar(int id) throws PyMEsNoObtenidasException;

	/**
	 * @param id
	 * @return
	 * @throws PyMENoAlmacenadaException
	 */
	public boolean saveLiberarPymes(int id) throws PyMENoAlmacenadaException;

	/**
	 * @return
	 * @throws DiplomadosNoObtenidosException
	 */
	public List<Diplomados> getTemaDiplomado()
			throws DiplomadosNoObtenidosException;

}