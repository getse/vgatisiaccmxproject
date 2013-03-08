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

import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface TractorasService {

	public List<Requerimientos> getRequerimientos(String id)
			throws RequerimientosNoObtenidosException;

	public Requerimientos getRequerimiento(String id)
			throws RequerimientosNoObtenidosException;

	public Mensaje insertRequerimiento(Requerimientos requerimientos)
			throws RequerimientosNoAlmacenadosException;

	public Mensaje updateRequerimiento(Requerimientos requerimientos)
			throws RequerimientosNoAlmacenadosException;
}
