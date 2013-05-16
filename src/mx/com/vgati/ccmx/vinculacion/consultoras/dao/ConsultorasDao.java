/*
 * ConsultorasDao.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.dao;

import java.util.List;


import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Pagos;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
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
	throws DaoException ;
	public Consultoras getConsultora(int id) throws DaoException;
	public List<Consultoras> getConsultorasAdmin(int idPadre) throws DaoException;
	public List<PyMEs> getPymesAdmin(int idUsuarioAdmin) throws DaoException;
	public List<PyMEs> getPymes(int idConsultora) throws DaoException;
	public Mensaje saveRelPymesConsultora(int uPymes,int uConsultor) throws DaoException;
	public Mensaje saveCedula(int idPyme,String cedula) throws DaoException;
	public String saveFacturaAnticipo(String numeroFactura,String idServicios) throws DaoException;
	public String saveFacturaAbono1(String numeroFactura,String idServicios) throws DaoException;
	public String saveFacturaAbono2(String numeroFactura,String idServicios) throws DaoException;
	public String saveFacturaFiniquito(String numeroFactura,String idServicios) throws DaoException;
	public List<Pagos> getPagos(int idConsultora,int filtro) throws DaoException;
	public Pagos getPagos(int idServicio) throws DaoException;	
	public List<PyMEs> getBusquedaPyMEs(String busqueda, String estado,
			String cveScian, String nombreComercial,int idConsultora) throws DaoException;
}