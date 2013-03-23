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

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMeNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMesNoObtenidasException;
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
	
	public List<PyMEs> getPyMe(int id)
			throws PyMesNoObtenidasException;
	
	public Mensaje saveUsuarioPyMe(PyMEs pyMes)
			throws PyMeNoAlmacenadaException;

	public Mensaje saveRolPyMe(PyMEs pyMes)
			throws PyMeNoAlmacenadaException;

	public Mensaje savePyMe(PyMEs pyMes)
			throws PyMeNoAlmacenadaException;

}
