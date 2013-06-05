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

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.ConsultoraNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.ConsultorasNoObtenidasExceprion;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ClientesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMENoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dto.Mensaje;

public interface CCMXService {

	public List<Tractoras> getTractoras(int id)
			throws TractorasNoObtenidasException;

	public Mensaje saveUsuarioTractora(Tractoras tractoras)
			throws TractorasNoAlmacenadasException;

	public Mensaje saveRolTractora(Tractoras tractoras)
			throws TractorasNoAlmacenadasException;

	public Mensaje saveTractora(Tractoras tractoras)
			throws TractorasNoAlmacenadasException;

	public Mensaje updateTractora(Tractoras tractoras, String credenciales)
			throws TractorasNoAlmacenadasException;

	public List<PyMEs> getPyME() throws PyMEsNoObtenidasException;

	public Mensaje saveUsuarioPyME(PyMEs pyMEs)
			throws PyMENoAlmacenadaException;

	public Mensaje saveRolPyME(PyMEs pyMEs) throws PyMENoAlmacenadaException;

	public Mensaje savePyME(PyMEs pyMEs) throws PyMENoAlmacenadaException;

	public List<Consultoras> getConsultoras(int id)
			throws ConsultorasNoObtenidasExceprion;

	public Mensaje saveUsuarioConsultora(Consultoras consultoras)
			throws ConsultoraNoAlmacenadaException;

	public Mensaje saveRolConsultora(Consultoras consultoras)
			throws ConsultoraNoAlmacenadaException;

	public Mensaje saveConsultora(Consultoras consultoras)
			throws ConsultoraNoAlmacenadaException;

	public Mensaje updateConsultora(Consultoras consultoras, String credenciales)
			throws ConsultoraNoAlmacenadaException;

	public Mensaje saveRelPyMETrac(PyMEs pyMEs)throws PyMENoAlmacenadaException;

	public String getNombreTractora(int id)throws TractorasNoObtenidasException;

	public Mensaje saveCliente(String nomTractora, int idPyME)throws ClientesNoAlmacenadosException;

}
