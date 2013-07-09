/*
 * TractorasDao.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.tractoras.dao;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.dto.Contacto;
import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.dto.Requerimientos;
import mx.com.vgati.ccmx.vinculacion.dto.Respuesta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.CatIndicadoresTractora;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.CatScianCcmx;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Productos;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.RelPyMEsTractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface TractorasDao {

	public List<Requerimientos> getRequerimientos(int id) throws DaoException;

	public Requerimientos getRequerimiento(String id) throws DaoException;

	public Mensaje insertRequerimiento(Requerimientos requerimientos)
			throws DaoException;

	public Mensaje updateRequerimiento(Requerimientos requerimientos)
			throws DaoException;

	public Mensaje insertDocumento(Documento documento) throws DaoException;

	public Mensaje updateDocumento(Documento documento, String idArchivo)
			throws DaoException;

	public Documento getArchivo(int id) throws DaoException;

	public List<Productos> getProductos(String busqueda) throws DaoException;

	public String getTercerNivelScian(int cve) throws DaoException;

	public List<CatScianCcmx> getNivelScian(int cve) throws DaoException;

	public Mensaje deleteRequerimiento(Requerimientos requerimientos)
			throws DaoException;

	public Mensaje deleteDocumentos(Requerimientos requerimientos)
			throws DaoException;

	public List<Tractoras> getCompradores(int id) throws DaoException;

	public Mensaje saveUsuarioComp(Tractoras tractoras) throws DaoException;

	public Mensaje saveRolComp(Tractoras tractoras) throws DaoException;

	public Mensaje saveCompradores(Tractoras tractoras) throws DaoException;

	public Mensaje asignaPyMEs(int idComprador, String idPyMEs)
			throws DaoException;

	public Tractoras getTractora(int id) throws DaoException;

	public Mensaje updateTractora(Tractoras tractoras) throws DaoException;

	public Mensaje insertDomicilios(Domicilios domicilios) throws DaoException;

	public Mensaje updateDomicilios(Domicilios domicilios) throws DaoException;

	public Mensaje insertRelDomicilios(Domicilios domicilios,
			Tractoras tractoras) throws DaoException;

	public Domicilios getDomicilios(int id) throws DaoException;

	public String getIdDomicilio(int id) throws DaoException;

	public List<PyMEs> getPymesTractoras(int id) throws DaoException;

	public List<PyMEs> getPymesVinculacion(int id) throws DaoException;

	public List<Contacto> getCorreosByProducto(String cveScian)
			throws DaoException;

	public Mensaje insertIndicadores(Indicadores indicadores)
			throws DaoException;

	public Mensaje updateIndicadores(Indicadores indicadores)
			throws DaoException;

	public List<CatIndicadoresTractora> getCatIndicadores() throws DaoException;

	public Mensaje insertCalificaciones(RelPyMEsTractoras relPyMEsTractoras) throws DaoException;

	public RelPyMEsTractoras getCalificaciones(int id) throws DaoException;

	public String getIdIndicadores(Indicadores indicadores) throws DaoException;

	public String getIdPyMETractoras(int id, int rel) throws DaoException;

	public List<Respuesta> getRespuestas(int id) throws DaoException;

	public Respuesta getRespuesta(int id) throws DaoException;

}