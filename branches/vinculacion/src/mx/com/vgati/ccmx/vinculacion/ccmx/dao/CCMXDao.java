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
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;

public interface CCMXDao {

	public List<Tractoras> getTractoras(int id) throws DaoException;

	public Mensaje saveUsuarioTractora(Tractoras tractoras) throws DaoException;

	public Mensaje saveRolTractora(Tractoras tractoras) throws DaoException;

	public Mensaje saveTractora(Tractoras tractoras) throws DaoException;

	public Mensaje updateTractora(Tractoras tractoras, String credenciales)
			throws DaoException;

	public List<PyMEs> getPyMEs() throws DaoException;

	public Mensaje saveUsuarioPyME(PyMEs pyMEs) throws DaoException;

	public Mensaje saveRolPyME(PyMEs pyMEs) throws DaoException;

	public Mensaje savePyME(PyMEs pyMEs) throws DaoException;

	public List<Consultoras> getConsultoras(int id) throws DaoException;

	public Mensaje saveUsuarioConsultora(Consultoras consultoras)
			throws DaoException;

	public Mensaje saveRolConsultora(Consultoras consultoras)
			throws DaoException;

	public Mensaje saveConsultora(Consultoras consultoras) throws DaoException;

	public Mensaje updateConsultora(Consultoras consultoras, String credenciales)
			throws DaoException;

	public Mensaje saveRelPyMETractora(PyMEs pyMEs) throws DaoException;

	public String getNombreTractoras(int id)throws DaoException;

	public Mensaje saveClientes(String nomTractora, int idPyME)throws DaoException;

	public Mensaje deshabilitaPyMEs(int estatus) throws DaoException;

}
