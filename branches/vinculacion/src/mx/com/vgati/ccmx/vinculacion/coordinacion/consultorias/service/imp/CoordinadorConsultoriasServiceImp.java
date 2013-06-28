/*
 * CoordinadorConsultoriasServiceImp.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.service.imp;

import java.util.Date;
import java.util.List;

import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Facturas;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dao.CoordinadorConsultoriasDao;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dto.Consultorias;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.exception.ConsultoriasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.service.CoordinadorConsultoriasService;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ConsultoriasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMENoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.report.exception.FacturasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.report.exception.FacturasNoObtenidasException;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.exception.ExceptionMessage;
import mx.com.vgati.framework.service.AbstractBaseService;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class CoordinadorConsultoriasServiceImp extends AbstractBaseService
		implements CoordinadorConsultoriasService {

	private CoordinadorConsultoriasDao coordinadorConsultoriasDao;

	public void setCoordinadorConsultoriasDao(
			CoordinadorConsultoriasDao coordinadorConsultoriasDao) {
		this.coordinadorConsultoriasDao = coordinadorConsultoriasDao;
	}

	@Override
	public List<Consultorias> getConsultorias(int id)
			throws ConsultoriasNoObtenidasException {
		return null;
	}

	@Override
	public List<PyMEs> getPyME() throws PyMEsNoObtenidasException {
		try {
			return coordinadorConsultoriasDao.getPyMEs();
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al obtener las PyMEs."), e);
		}
	}

	public List<Consultoras> getConsultora()
			throws ConsultoriasNoObtenidasException {
		try {
			return coordinadorConsultoriasDao.getConsultoras();
		} catch (DaoException e) {
			throw new ConsultoriasNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al obtener las Consultoras."), e);
		}
	}

	@Override
	public List<PyMEs> getDiplomaPyME() throws PyMEsNoObtenidasException {
		try {
			return coordinadorConsultoriasDao.getDiplomasPyMEs();
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al obtener las PyMEs."), e);
		}
	}

	@Override
	public Mensaje asignaPyMEs(int idConsultora, String idPyMEs)
			throws PyMENoAlmacenadaException {
		try {
			return coordinadorConsultoriasDao.asignaPyMEs(idConsultora, idPyMEs);
		} catch (DaoException e) {
			throw new PyMENoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al asignar las PyMEs."), e);
		}
	}

	@Override
	public Mensaje regDiploma(String idPyMEs)
			throws ConsultoriasNoAlmacenadasException {
		try {
			return coordinadorConsultoriasDao.regDiplomas(idPyMEs);
		} catch (DaoException e) {
			throw new ConsultoriasNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al registrar los diplomas de las PyMEs."), e);
		}
	}

	@Override
	public List<Facturas> getFactura(int idUsuario)
			throws FacturasNoObtenidasException {
		try {
			return coordinadorConsultoriasDao.getFacturas(idUsuario);
		} catch (DaoException e) {
			throw new FacturasNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al consultar las facturas."), e);
		}
	}

	@Override
	public List<PyMEs> getDetalleFactura(String numeroFactura)
			throws PyMEsNoObtenidasException {
		try {
			return coordinadorConsultoriasDao.getDetalleFacturas(numeroFactura);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al obtener las PyMEs."), e);
		}
	}

	@Override
	public Mensaje liberaFactura(String idFacturas)
			throws FacturasNoAlmacenadasException {
		try {
			return coordinadorConsultoriasDao.liberaFacturas(idFacturas);
		} catch (DaoException e) {
			throw new FacturasNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al liberar las facturas."), e);
		}
	}

	@Override
	public Mensaje quitarRelFactura(String idPagosFacturas)
			throws FacturasNoAlmacenadasException {
		try {
			return coordinadorConsultoriasDao.quitarRelFacturas(idPagosFacturas);
		} catch (DaoException e) {
			throw new FacturasNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al eliminar las PyMEs de las facturas."), e);
		}
	}

	@Override
	public List<PyMEs> getReasignaPyME(String busqueda, String tractora, String cveScian)
			throws PyMEsNoObtenidasException {
		try {
			return coordinadorConsultoriasDao.getReasignaPyME(busqueda, tractora, cveScian);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al obtener las PyMEs."), e);
		}
	}

	@Override
	public Mensaje reAsignaPyME(int idConsultora, String idPyMEs)
			throws PyMENoAlmacenadaException {
		try {
			return coordinadorConsultoriasDao.reAsignaPyMEs(idConsultora, idPyMEs);
		} catch (DaoException e) {
			throw new PyMENoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al reasignar las PyMEs."), e);
		}
	}

	@Override
	public List<Facturas> getInfoFactura(int idUsuario)
			throws FacturasNoObtenidasException {
		try {
			return coordinadorConsultoriasDao.getInfoFacturas(idUsuario);
		} catch (DaoException e) {
			throw new FacturasNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al consultar las facturas."), e);
		}
	}

	@Override
	public Mensaje saveFechaFactura(String idFacturas, Date fechaPago)
			throws FacturasNoAlmacenadasException {
		try {
			return coordinadorConsultoriasDao.saveFechaFacturas(idFacturas, fechaPago);
		} catch (DaoException e) {
			throw new FacturasNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al almacenar la fecha de las facturas."), e);
		}
	}

}
