/*
 * CCMXService.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.ccmx.service;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.framework.dto.Mensaje;

public interface CCMXService {

	public List<Tractoras> getTractoras(int id)
			throws TractorasNoObtenidasException;

	public Mensaje saveUsuarioTra(Tractoras tractoras)
			throws TractorasNoAlmacenadasException;

	public Mensaje saveRolTra(Tractoras tractoras)
			throws TractorasNoAlmacenadasException;

	public Mensaje saveTractora(Tractoras tractoras)
			throws TractorasNoAlmacenadasException;

}
