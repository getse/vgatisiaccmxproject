/*
 * TractorasService.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.tractoras.service;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.dto.Contacto;
import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.dto.Requerimientos;
import mx.com.vgati.ccmx.vinculacion.dto.Respuesta;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoAlmacenadoException;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.IndicadoresNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.IndicadoresNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.CatIndicadoresTractora;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.CatScianCcmx;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Productos;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.RelPyMEsTractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.CompradoresNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.CompradoresNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.ProductosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoEliminadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RespuestasNoObtenidasException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface TractorasService {

	/**
	 * @param id
	 * @return
	 * @throws RequerimientosNoObtenidosException
	 */
	public List<Requerimientos> getRequerimientos(int id)
			throws RequerimientosNoObtenidosException;

	/**
	 * @param id
	 * @return
	 * @throws RequerimientosNoObtenidosException
	 */
	public Requerimientos getRequerimiento(String id)
			throws RequerimientosNoObtenidosException;

	/**
	 * @param requerimientos
	 * @return
	 * @throws RequerimientosNoAlmacenadosException
	 */
	public Mensaje insertRequerimiento(Requerimientos requerimientos)
			throws RequerimientosNoAlmacenadosException;

	/**
	 * @param requerimientos
	 * @return
	 * @throws RequerimientosNoAlmacenadosException
	 */
	public Mensaje updateRequerimiento(Requerimientos requerimientos)
			throws RequerimientosNoAlmacenadosException;

	/**
	 * @param documento
	 * @return
	 * @throws DocumentoNoAlmacenadoException
	 */
	public Mensaje insertDocumento(Documento documento)
			throws DocumentoNoAlmacenadoException;

	/**
	 * @param documento
	 * @param idArchivo
	 * @return
	 * @throws DocumentoNoAlmacenadoException
	 */
	public Mensaje updateDocumento(Documento documento, String idArchivo)
			throws DocumentoNoAlmacenadoException;

	/**
	 * @param id
	 * @return
	 * @throws DocumentoNoObtenidoException
	 */
	public Documento getArchivo(int id) throws DocumentoNoObtenidoException;

	/**
	 * @param busqueda
	 * @return
	 * @throws ProductosNoObtenidosException
	 */
	public List<Productos> getProductos(String busqueda)
			throws ProductosNoObtenidosException;

	/**
	 * @param cve
	 * @return
	 * @throws ProductosNoObtenidosException
	 */
	public String getTercerNivelScian(int cve)
			throws ProductosNoObtenidosException;

	/**
	 * @param cve
	 * @return
	 * @throws ProductosNoObtenidosException
	 */
	public List<CatScianCcmx> getNivelScian(int cve)
			throws ProductosNoObtenidosException;

	/**
	 * @param requerimientos
	 * @return
	 * @throws RequerimientosNoEliminadosException
	 */
	public Mensaje deleteRequerimiento(Requerimientos requerimientos)
			throws RequerimientosNoEliminadosException;

	/**
	 * @param requerimientos
	 * @return
	 * @throws RequerimientosNoEliminadosException
	 */
	public Mensaje deleteDocumentos(Requerimientos requerimientos)
			throws RequerimientosNoEliminadosException;

	/**
	 * @param id
	 * @return
	 * @throws CompradoresNoObtenidosException
	 */
	public List<Tractoras> getCompradores(int id)
			throws CompradoresNoObtenidosException;

	/**
	 * @param tractoras
	 * @return
	 * @throws CompradoresNoAlmacenadosException
	 */
	public Mensaje saveUsuarioComp(Tractoras tractoras)
			throws CompradoresNoAlmacenadosException;

	/**
	 * @param tractoras
	 * @return
	 * @throws CompradoresNoAlmacenadosException
	 */
	public Mensaje saveRolComp(Tractoras tractoras)
			throws CompradoresNoAlmacenadosException;

	/**
	 * @param tractoras
	 * @return
	 * @throws CompradoresNoAlmacenadosException
	 */
	public Mensaje saveComprador(Tractoras tractoras)
			throws CompradoresNoAlmacenadosException;

	/**
	 * @param idComprador
	 * @param idPyMEs
	 * @return
	 * @throws CompradoresNoAlmacenadosException
	 */
	public Mensaje asignaPyMEs(int idComprador, String idPyMEs)
			throws CompradoresNoAlmacenadosException;

	/**
	 * @param id
	 * @return
	 * @throws CompradoresNoObtenidosException
	 */
	public Tractoras getTractora(int id) throws CompradoresNoObtenidosException;

	/**
	 * @param tractoras
	 * @return
	 * @throws TractorasNoAlmacenadasException
	 */
	public Mensaje updateTractoras(Tractoras tractoras)
			throws TractorasNoAlmacenadasException;

	/**
	 * @param domicilios
	 * @return
	 * @throws DomiciliosNoAlmacenadosException
	 */
	public Mensaje insertDomicilio(Domicilios domicilios)
			throws DomiciliosNoAlmacenadosException;

	/**
	 * @param domicilios
	 * @return
	 * @throws DomiciliosNoAlmacenadosException
	 */
	public Mensaje updateDomicilio(Domicilios domicilios)
			throws DomiciliosNoAlmacenadosException;

	/**
	 * @param domicilios
	 * @param tractoras
	 * @return
	 * @throws DomiciliosNoAlmacenadosException
	 */
	public Mensaje insertRelDomicilio(Domicilios domicilios, Tractoras tractoras)
			throws DomiciliosNoAlmacenadosException;

	/**
	 * @param id
	 * @return
	 * @throws DomiciliosNoAlmacenadosException
	 */
	public Domicilios getDomicilio(int id)
			throws DomiciliosNoAlmacenadosException;

	/**
	 * @param id
	 * @return
	 * @throws DomiciliosNoAlmacenadosException
	 */
	public String getIdDomicilio(int id)
			throws DomiciliosNoAlmacenadosException;

	/**
	 * @param idUsuario
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getPymeTractora(int idUsuario)
			throws PyMEsNoObtenidasException;

	/**
	 * @param idUsuario
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getPymeVinculacion(int idUsuario)
			throws PyMEsNoObtenidasException;

	/**
	 * @param cveScian
	 * @return
	 * @throws ProductosNoObtenidosException
	 */
	public List<Contacto> getCorreosByProducto(String cveScian)
			throws ProductosNoObtenidosException;

	/**
	 * @param indicadores
	 * @return
	 * @throws IndicadoresNoAlmacenadosException
	 */
	public Mensaje insertIndicador(Indicadores indicadores)
			throws IndicadoresNoAlmacenadosException;

	/**
	 * @param indicadores
	 * @return
	 * @throws IndicadoresNoAlmacenadosException
	 */
	public Mensaje updateIndicador(Indicadores indicadores)
			throws IndicadoresNoAlmacenadosException;

	/**
	 * @return
	 * @throws IndicadoresNoObtenidosException
	 */
	public List<CatIndicadoresTractora> getCatIndicador()
			throws IndicadoresNoObtenidosException;

	/**
	 * @param relPyMEsTractoras
	 * @return
	 * @throws IndicadoresNoAlmacenadosException
	 */
	public Mensaje insertCalificacion(RelPyMEsTractoras relPyMEsTractoras)
			throws IndicadoresNoAlmacenadosException;

	/**
	 * @param id
	 * @return
	 * @throws IndicadoresNoObtenidosException
	 */
	public RelPyMEsTractoras getCalificacion(int id)
			throws IndicadoresNoObtenidosException;

	/**
	 * @param indicadores
	 * @return
	 * @throws IndicadoresNoObtenidosException
	 */
	public String getIdIndicador(Indicadores indicadores)
			throws IndicadoresNoObtenidosException;

	/**
	 * @param idUsuario
	 * @param rel
	 * @return
	 * @throws TractorasNoObtenidasException
	 */
	public String getIdPyMETractora(int idUsuario, int rel)
			throws TractorasNoObtenidasException;

	/**
	 * @param id
	 * @return
	 * @throws RespuestasNoObtenidasException
	 */
	public List<Respuesta> getRespuestas(int id)
			throws RespuestasNoObtenidasException;

	/**
	 * @param id
	 * @return
	 * @throws RespuestasNoObtenidasException
	 */
	public Respuesta getRespuesta(int id) throws RespuestasNoObtenidasException;

	/**
	 * @param idTractoraActual
	 * @param busqueda
	 * @param estado
	 * @param cveScian
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getBusquedaPyME(int idTractoraActual, String busqueda,
			String estado, String cveScian) throws PyMEsNoObtenidasException;

	/**
	 * @param idTractoraActual
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getPyME(int idTractoraActual)
			throws PyMEsNoObtenidasException;

	/**
	 * @param idTractora
	 * @return
	 * @throws TractorasNoObtenidasException
	 */
	public List<Tractoras> getDetalleRequerimientosTractora(int idTractora)
			throws TractorasNoObtenidasException;

	/**
	 * @param idTractora
	 * @return
	 * @throws TractorasNoObtenidasException
	 */
	public Tractoras getDetalleRequerimientosComprador(int idTractora)
			throws TractorasNoObtenidasException;

}
