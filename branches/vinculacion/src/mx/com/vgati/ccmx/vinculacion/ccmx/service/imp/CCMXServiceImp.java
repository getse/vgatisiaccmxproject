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
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.ConsultoraNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.ConsultorasNoObtenidasExceprion;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.service.CCMXService;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMENoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
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
	public Mensaje saveUsuarioTractora(Tractoras tractoras)
			throws TractorasNoAlmacenadasException {
		try {
			return ccmxDao.saveUsuarioTractora(tractoras);
		} catch (DaoException e) {
			throw new TractorasNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al guradar El Usuario de la Tractora."),
					e);
		}
	}

	@Override
	public Mensaje saveRolTractora(Tractoras tractoras)
			throws TractorasNoAlmacenadasException {
		try {
			return ccmxDao.saveRolTractora(tractoras);
		} catch (DaoException e) {
			throw new TractorasNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al guradar El Rol de la Tractora."), e);
		}
	}

	@Override
	public Mensaje saveTractora(Tractoras tractoras)
			throws TractorasNoAlmacenadasException {
		try {
			return ccmxDao.saveTractora(tractoras);
		} catch (DaoException e) {
			throw new TractorasNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al guradar la Tractora."), e);
		}
	}

	@Override
	public Mensaje updateTractora(Tractoras tractoras, String credenciales)
			throws TractorasNoAlmacenadasException {
		try {
			return ccmxDao.updateTractora(tractoras, credenciales);
		} catch (DaoException e) {
			throw new TractorasNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al actualizar la Tractora."), e);
		}
	}

	public List<PyMEs> getPyME(int id) throws PyMEsNoObtenidasException {
		try {
			return ccmxDao.getPyMEs(id);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al consultar las PyMEs."), e);
		}
	}

	@Override
	public Mensaje saveUsuarioPyME(PyMEs pyMEs)
			throws PyMENoAlmacenadaException {
		try {
			return ccmxDao.saveUsuarioPyME(pyMEs);
		} catch (DaoException e) {
			throw new PyMENoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al guradar El Usuario PyME."), e);
		}
	}

	@Override
	public Mensaje saveRolPyME(PyMEs pyMEs) throws PyMENoAlmacenadaException {
		try {
			return ccmxDao.saveRolPyME(pyMEs);
		} catch (DaoException e) {
			throw new PyMENoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al guradar El Rol del usuario PyME."), e);
		}
	}

	@Override
	public Mensaje savePyME(PyMEs pyMEs) throws PyMENoAlmacenadaException {
		try {
			return ccmxDao.savePyME(pyMEs);
		} catch (DaoException e) {
			throw new PyMENoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al guradar el usario PyME."), e);
		}
	}

	@Override
	public List<Consultoras> getConsultoras(int id)
			throws ConsultorasNoObtenidasExceprion {
		try {
			return ccmxDao.getConsultoras(id);
		} catch (DaoException e) {
			throw new ConsultorasNoObtenidasExceprion(new ExceptionMessage(
					"Ocurrio un error al obtener las Consultoras."), e);
		}
	}

	@Override
	public Mensaje saveUsuarioConsultora(Consultoras consultoras)
			throws ConsultoraNoAlmacenadaException {
		try {
			return ccmxDao.saveUsuarioConsultora(consultoras);
		} catch (DaoException e) {
			throw new ConsultoraNoAlmacenadaException(
					new ExceptionMessage(
							"Ocurrio un error al guardar el Usuario de la Consultora."),
					e);
		}
	}

	@Override
	public Mensaje saveRolConsultora(Consultoras consultoras)
			throws ConsultoraNoAlmacenadaException {
		try {
			return ccmxDao.saveRolConsultora(consultoras);
		} catch (DaoException e) {
			throw new ConsultoraNoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al guardar el Rol de la Consultora."), e);
		}
	}

	@Override
	public Mensaje saveConsultora(Consultoras consultoras)
			throws ConsultoraNoAlmacenadaException {
		try {
			return ccmxDao.saveConsultora(consultoras);
		} catch (DaoException e) {
			throw new ConsultoraNoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al guardar la Consultora."), e);
		}
	}

	@Override
	public Mensaje updateConsultora(Consultoras consultoras, String credenciales)
			throws ConsultoraNoAlmacenadaException {
		try {
			return ccmxDao.updateConsultora(consultoras, credenciales);
		} catch (DaoException e) {
			throw new ConsultoraNoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al actualizar la Consultora."), e);
		}
	}

}
