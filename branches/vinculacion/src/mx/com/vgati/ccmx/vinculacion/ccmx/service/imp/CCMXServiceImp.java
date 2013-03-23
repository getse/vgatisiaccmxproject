/*
 * CCMXServiceImp.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.ccmx.service.imp;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.dao.CCMXDao;
import mx.com.vgati.ccmx.vinculacion.ccmx.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMeNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMesNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.service.CCMXService;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.exception.ExceptionMessage;
import mx.com.vgati.framework.service.AbstractBaseService;

public class CCMXServiceImp extends AbstractBaseService implements CCMXService {

	private CCMXDao ccmxDao;

	public void setCcmxDao(CCMXDao ccmxDao) {
		this.ccmxDao = ccmxDao;
	}

	public List<Tractoras> getTractoras(int id)
			throws TractorasNoObtenidasException {
		try {
			return ccmxDao.getTractoras(id);
		} catch (DaoException e) {
			throw new TractorasNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al consultar las Tractoras."), e);
		}
	}

	@Override
	public Mensaje saveUsuarioTra(Tractoras tractoras)
			throws TractorasNoAlmacenadasException {
		try {
			return ccmxDao.saveUsuarioTra(tractoras);
		} catch (DaoException e) {
			throw new TractorasNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al guradar El Usuario de la Tractora."),
					e);
		}
	}

	@Override
	public Mensaje saveRolTra(Tractoras tractoras)
			throws TractorasNoAlmacenadasException {
		try {
			return ccmxDao.saveRolTra(tractoras);
		} catch (DaoException e) {
			throw new TractorasNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al guradar El Rol de la Tractora."), e);
		}
	}

	@Override
	public Mensaje saveTractora(Tractoras tractoras)
			throws TractorasNoAlmacenadasException {
		try {
			return ccmxDao.saveTractoras(tractoras);
		} catch (DaoException e) {
			throw new TractorasNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al guradar la Tractora."), e);
		}
	}
	
	public List<PyMEs> getPyMe(int id)
			throws PyMesNoObtenidasException {
		try {
			return ccmxDao.getPyMes(id);
		} catch (DaoException e) {
			throw new PyMesNoObtenidasException(new ExceptionMessage(
				"Ocurrio un error al consultar las PyMEs."), e);
		}
	}
	
	@Override
	public Mensaje saveUsuarioPyMe(PyMEs pyMes)
			throws PyMeNoAlmacenadaException {
		try {
			return ccmxDao.saveUsuarioPyMes(pyMes);
		} catch (DaoException e) {
			throw new PyMeNoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al guradar El Usuario PyME."), e);
		}
	}

	@Override
	public Mensaje saveRolPyMe(PyMEs pyMes)
			throws PyMeNoAlmacenadaException {
		try {
			return ccmxDao.saveRolPyMes(pyMes);
		} catch (DaoException e) {
			throw new PyMeNoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al guradar El Rol del usuario PyME."), e);
		}
	}

	@Override
	public Mensaje savePyMe(PyMEs pyMes)
			throws PyMeNoAlmacenadaException {
		try {
			return ccmxDao.savePyMes(pyMes);
		} catch (DaoException e) {
			throw new PyMeNoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al guradar el usario PyME."), e);
		}
	}
}
