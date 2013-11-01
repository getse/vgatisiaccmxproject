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
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.DiplomadosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.SesionesNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoAlmacenadoException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ClientesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.DiplomadosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMENoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoAlmacenadosException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Usuario;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface CCMXService {

	/**
	 * @return
	 * @throws TractorasNoObtenidasException
	 */
	public List<Tractoras> getTractoras() throws TractorasNoObtenidasException;

	/**
	 * @param tractoras
	 * @return
	 * @throws TractorasNoAlmacenadasException
	 */
	public Mensaje saveUsuarioTractora(Tractoras tractoras)
			throws TractorasNoAlmacenadasException;

	/**
	 * @param tractoras
	 * @return
	 * @throws TractorasNoAlmacenadasException
	 */
	public Mensaje saveRolTractora(Tractoras tractoras)
			throws TractorasNoAlmacenadasException;

	/**
	 * @param tractoras
	 * @return
	 * @throws TractorasNoAlmacenadasException
	 */
	public Mensaje saveTractora(Tractoras tractoras)
			throws TractorasNoAlmacenadasException;

	/**
	 * @param tractoras
	 * @param credenciales
	 * @param rol
	 * @return
	 * @throws TractorasNoAlmacenadasException
	 */
	public Mensaje updateTractora(Tractoras tractoras, String credenciales,
			String rol) throws TractorasNoAlmacenadasException;

	/**
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getPyME() throws PyMEsNoObtenidasException;

	/**
	 * @param pyMEs
	 * @return
	 * @throws PyMENoAlmacenadaException
	 */
	public Mensaje saveUsuarioPyME(PyMEs pyMEs)
			throws PyMENoAlmacenadaException;

	/**
	 * @param pyMEs
	 * @return
	 * @throws PyMENoAlmacenadaException
	 */
	public Mensaje saveRolPyME(PyMEs pyMEs) throws PyMENoAlmacenadaException;

	/**
	 * @param pyMEs
	 * @return
	 * @throws PyMENoAlmacenadaException
	 */
	public Mensaje savePyME(PyMEs pyMEs) throws PyMENoAlmacenadaException;

	/**
	 * @param id
	 * @return
	 * @throws ConsultorasNoObtenidasExceprion
	 */
	public List<Consultoras> getConsultoras(int id)
			throws ConsultorasNoObtenidasExceprion;

	/**
	 * @param consultoras
	 * @return
	 * @throws ConsultoraNoAlmacenadaException
	 */
	public Mensaje saveUsuarioConsultora(Consultoras consultoras)
			throws ConsultoraNoAlmacenadaException;

	/**
	 * @param consultoras
	 * @return
	 * @throws ConsultoraNoAlmacenadaException
	 */
	public Mensaje saveRolConsultora(Consultoras consultoras)
			throws ConsultoraNoAlmacenadaException;

	/**
	 * @param consultoras
	 * @return
	 * @throws ConsultoraNoAlmacenadaException
	 */
	public Mensaje saveConsultora(Consultoras consultoras)
			throws ConsultoraNoAlmacenadaException;

	/**
	 * @param consultoras
	 * @param credenciales
	 * @return
	 * @throws ConsultoraNoAlmacenadaException
	 */
	public Mensaje updateConsultora(Consultoras consultoras, String credenciales)
			throws ConsultoraNoAlmacenadaException;

	/**
	 * @param pyMEs
	 * @return
	 * @throws PyMENoAlmacenadaException
	 */
	public Mensaje saveRelPyMETrac(PyMEs pyMEs)
			throws PyMENoAlmacenadaException;

	/**
	 * @param id
	 * @return
	 * @throws TractorasNoObtenidasException
	 */
	public String getNombreTractora(int id)
			throws TractorasNoObtenidasException;

	/**
	 * @param nomTractora
	 * @param idPyME
	 * @return
	 * @throws ClientesNoAlmacenadosException
	 */
	public Mensaje saveCliente(String nomTractora, int idPyME)
			throws ClientesNoAlmacenadosException;

	/**
	 * @param estatus
	 * @param libera
	 * @return
	 * @throws PyMENoAlmacenadaException
	 */
	public Mensaje deshabilitaPyME(int estatus, boolean libera)
			throws PyMENoAlmacenadaException;

	/**
	 * @return
	 * @throws TractorasNoObtenidasException
	 */
	public List<Tractoras> getDetalleTractora()
			throws TractorasNoObtenidasException;

	/**
	 * @param diplomado
	 * @param generacion
	 * @return
	 * @throws DiplomadosNoAlmacenadosException
	 */
	public Mensaje saveDiplomado(Diplomados diplomado, int generacion)
			throws DiplomadosNoAlmacenadosException;

	/**
	 * @param id
	 * @param tema
	 * @return
	 * @throws DiplomadosNoAlmacenadosException
	 */
	public Mensaje updateDiplomado(int id, String tema)
			throws DiplomadosNoAlmacenadosException;

	/**
	 * @param id
	 * @return
	 * @throws DiplomadosNoObtenidosException
	 */
	public String getIdServicio(int id) throws DiplomadosNoObtenidosException;

	/**
	 * @param id
	 * @return
	 * @throws DiplomadosNoObtenidosException
	 */
	public List<Integer> getListaIds(int id)
			throws DiplomadosNoObtenidosException;

	/**
	 * @param id
	 * @return
	 * @throws DomiciliosNoAlmacenadosException
	 */
	public Mensaje deleteDomicilio(int id)
			throws DomiciliosNoAlmacenadosException;

	/**
	 * @param id
	 * @return
	 * @throws SesionesNoAlmacenadasException
	 */
	public Mensaje deleteSesion(int id) throws SesionesNoAlmacenadasException;

	/**
	 * @param id
	 * @return
	 * @throws DiplomadosNoAlmacenadosException
	 */
	public Mensaje deleteDiplomado(int id)
			throws DiplomadosNoAlmacenadosException;

	/**
	 * @return
	 * @throws TractorasNoObtenidasException
	 */
	public List<Usuario> getUsuarios() throws TractorasNoObtenidasException;

	/**
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public String getPyMEsTotal() throws PyMEsNoObtenidasException;

	/**
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public String getPyMEsActivas() throws PyMEsNoObtenidasException;

	/**
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public String getPyMEsExpediente() throws PyMEsNoObtenidasException;

	/**
	 * @param archivo
	 * @param rol
	 * @return
	 * @throws DocumentoNoAlmacenadoException
	 */
	public Mensaje saveDocumento(Documento archivo, int rol)
			throws DocumentoNoAlmacenadoException;

}
