/*
 * ConsultorasDao.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.dao;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Facturas;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Pagos;
import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface ConsultorasDao {
	public Mensaje saveRolConsultora(Consultoras consultoras)
			throws DaoException;

	public Consultoras getConsultora(int id) throws DaoException;

	public List<Consultoras> getConsultorasAdmin(int idPadre)
			throws DaoException;

	public List<PyMEs> getPymesAdmin(int idUsuarioAdmin) throws DaoException;

	public List<PyMEs> getPymes(int idConsultora) throws DaoException;

	public Mensaje saveRelPymesConsultora(int uPymes, int uConsultor)
			throws DaoException;

	public Mensaje saveCedula(List<Integer> idPyme, String cedula)
			throws DaoException;

	public Mensaje saveFacturas(List<Pagos> anticipo, List<Pagos> abono1,
			List<Pagos> abono2, List<Pagos> finiquito, String idFactura)
			throws DaoException;

	public List<Pagos> getPagos(int idConsultora, int filtro)
			throws DaoException;

	public String getPymeByServicio(int idServicio) throws DaoException;

	public Pagos getPagos(int idServicio) throws DaoException;

	public List<PyMEs> getBusquedaPyMEs(String busqueda, String estado,
			String cveScian, int idConsultora, int idUsuario)
			throws DaoException;

	public Mensaje saveConsultor(Consultoras consultor) throws DaoException;

	public Mensaje updateConsultor(Consultoras consultor) throws DaoException;

	public List<PyMEs> getPyMEsCedula(int idConsultor) throws DaoException;

	public ServiciosConsultoria getServiciosConsultoria(int idConsultora)
			throws DaoException;

	public Mensaje saveServiciosConsultoria(
			ServiciosConsultoria serviciosConsultoria) throws DaoException;

	public List<PyMEs> getPyMEsConsultor(int idConsultor) throws DaoException;

	public String getCorreoCordCons() throws DaoException;

	public List<Facturas> getFacturasPorAdmin(int idUsuario)
			throws DaoException;

	public Mensaje saveFactura(String idFactura, int idUsuario)
			throws DaoException;

	public Documento getArchivo(int id) throws DaoException;

}