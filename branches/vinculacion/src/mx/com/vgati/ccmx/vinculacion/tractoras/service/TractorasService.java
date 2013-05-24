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
import mx.com.vgati.framework.dto.Contacto;
import mx.com.vgati.framework.dto.Documento;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface TractorasService {

	public List<Requerimientos> getRequerimientos(int id)
			throws RequerimientosNoObtenidosException;

	public Requerimientos getRequerimiento(String id)
			throws RequerimientosNoObtenidosException;

	public Mensaje insertRequerimiento(Requerimientos requerimientos)
			throws RequerimientosNoAlmacenadosException;

	public Mensaje updateRequerimiento(Requerimientos requerimientos)
			throws RequerimientosNoAlmacenadosException;

	public Mensaje insertDocumento(Documento documento)
			throws DocumentoNoAlmacenadoException;

	public Mensaje updateDocumento(Documento documento, String idArchivo)
			throws DocumentoNoAlmacenadoException;

	public Documento getArchivo(int id) throws DocumentoNoObtenidoException;

	public List<Productos> getProductos(String busqueda)
			throws ProductosNoObtenidosException;

	public String getTercerNivelScian(int cve)
			throws ProductosNoObtenidosException;

	public List<CatScianCcmx> getNivelScian(int cve)
			throws ProductosNoObtenidosException;

	public Mensaje deleteRequerimiento(Requerimientos requerimientos)
			throws RequerimientosNoEliminadosException;

	public Mensaje deleteDocumentos(Requerimientos requerimientos)
			throws RequerimientosNoEliminadosException;

	public List<Tractoras> getCompradores(int id)
			throws CompradoresNoObtenidosException;

	public Mensaje saveUsuarioComp(Tractoras tractoras)
			throws CompradoresNoAlmacenadosException;

	public Mensaje saveRolComp(Tractoras tractoras)
			throws CompradoresNoAlmacenadosException;

	public Mensaje saveComprador(Tractoras tractoras)
			throws CompradoresNoAlmacenadosException;

	public Mensaje asignaPyMEs(int idComprador, String idPyMEs)
			throws CompradoresNoAlmacenadosException;

	public Tractoras getTractora(int id) throws CompradoresNoObtenidosException;

	public Mensaje updateTractoras(Tractoras tractoras)
			throws TractorasNoAlmacenadasException;

	public Mensaje insertDomicilio(Domicilios domicilios)
			throws DomiciliosNoAlmacenadosException;

	public Mensaje updateDomicilio(Domicilios domicilios)
			throws DomiciliosNoAlmacenadosException;

	public Mensaje insertRelDomicilio(Domicilios domicilios, Tractoras tractoras)
			throws DomiciliosNoAlmacenadosException;

	public Domicilios getDomicilio(int id)
			throws DomiciliosNoAlmacenadosException;

	public String getIdDomicilio(int id)
			throws DomiciliosNoAlmacenadosException;

	public List<PyMEs> getPymeTractora(int idUsuario)
			throws PyMEsNoObtenidasException;

	public List<PyMEs> getPymeVinculacion(int idUsuario)
			throws PyMEsNoObtenidasException;

	public List<Contacto> getCorreosByProducto(String cveScian)
			throws ProductosNoObtenidosException;

	public Mensaje insertIndicador(Indicadores indicadores)
			throws IndicadoresNoAlmacenadosException;
	
	public Mensaje updateIndicador(Indicadores indicadores)
			throws IndicadoresNoAlmacenadosException;

	public List<CatIndicadoresTractora> getCatIndicador()
			throws IndicadoresNoObtenidosException;

	public Mensaje insertCalificacion(RelPyMEsTractoras relPyMEsTractoras, Indicadores indicadores)
			throws IndicadoresNoAlmacenadosException;

	public RelPyMEsTractoras getCalificacion(int id) 
			throws IndicadoresNoObtenidosException;

	public String getIdIndicador(Indicadores indicadores) 
			throws IndicadoresNoObtenidosException;

	public String getIdPyMETractora(int idUsuario)
			throws TractorasNoObtenidasException;

}