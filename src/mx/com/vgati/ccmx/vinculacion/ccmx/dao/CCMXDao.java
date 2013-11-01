/*
 * CCMXDao.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.ccmx.dao;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Usuario;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface CCMXDao {

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<Tractoras> getTractoras() throws DaoException;

	/**
	 * @param tractoras
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveUsuarioTractora(Tractoras tractoras) throws DaoException;

	/**
	 * @param tractoras
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveRolTractora(Tractoras tractoras) throws DaoException;

	/**
	 * @param tractoras
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveTractora(Tractoras tractoras) throws DaoException;

	/**
	 * @param tractoras
	 * @param credenciales
	 * @param rol
	 * @return
	 * @throws DaoException
	 */
	public Mensaje updateTractora(Tractoras tractoras, String credenciales,
			String rol) throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<PyMEs> getPyMEs() throws DaoException;

	/**
	 * @param pyMEs
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveUsuarioPyME(PyMEs pyMEs) throws DaoException;

	/**
	 * @param pyMEs
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveRolPyME(PyMEs pyMEs) throws DaoException;

	/**
	 * @param pyMEs
	 * @return
	 * @throws DaoException
	 */
	public Mensaje savePyME(PyMEs pyMEs) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public List<Consultoras> getConsultoras(int id) throws DaoException;

	/**
	 * @param consultoras
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveUsuarioConsultora(Consultoras consultoras)
			throws DaoException;

	/**
	 * @param consultoras
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveRolConsultora(Consultoras consultoras)
			throws DaoException;

	/**
	 * @param consultoras
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveConsultora(Consultoras consultoras) throws DaoException;

	/**
	 * @param consultoras
	 * @param credenciales
	 * @return
	 * @throws DaoException
	 */
	public Mensaje updateConsultora(Consultoras consultoras, String credenciales)
			throws DaoException;

	/**
	 * @param pyMEs
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveRelPyMETractora(PyMEs pyMEs) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public String getNombreTractoras(int id) throws DaoException;

	/**
	 * @param nomTractora
	 * @param idPyME
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveClientes(String nomTractora, int idPyME)
			throws DaoException;

	/**
	 * @param estatus
	 * @param libera
	 * @return
	 * @throws DaoException
	 */
	public Mensaje deshabilitaPyMEs(int estatus, boolean libera)
			throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<Tractoras> getDetallesTractoras() throws DaoException;

	/**
	 * @param diplomado
	 * @param generacion
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveDiplomados(Diplomados diplomado, int generacion)
			throws DaoException;

	/**
	 * @param id
	 * @param tema
	 * @return
	 * @throws DaoException
	 */
	public Mensaje updateDiplomado(int id, String tema) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public String getIdServicios(int id) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public List<Integer> getListaIds(int id) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Mensaje deleteDomicilios(int id) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Mensaje deleteSesiones(int id) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Mensaje deleteDiplomados(int id) throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<Usuario> getUsuarios() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public String getPyMEsTotal() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public String getPyMEsActivas() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public String getPyMEsExpediente() throws DaoException;

	/**
	 * @param archivo
	 * @param rol
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveDocumento(Documento archivo, int rol)
			throws DaoException;

}
