/*
 * ConsultorasServiceImp.java        01/04/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.service.imp;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.ConsultoraNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.consultoras.dao.ConsultorasDao;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Pagos;
import mx.com.vgati.ccmx.vinculacion.consultoras.exception.ConsultoraNoObtenidaException;
import mx.com.vgati.ccmx.vinculacion.consultoras.service.ConsultorasService;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
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
public class ConsultorasServiceImp extends AbstractBaseService implements
		ConsultorasService {

	private ConsultorasDao consultorasDao;

	public void setConsultorasDao(ConsultorasDao consultorasDao) {
		this.consultorasDao = consultorasDao;
	}

	@Override
	public Consultoras getConsultora(int id)
			throws ConsultoraNoObtenidaException {
		try {
			return consultorasDao.getConsultora(id);
		} catch (DaoException e) {
			throw new ConsultoraNoObtenidaException(new ExceptionMessage(
					"Ocurrio un error al obtener la Consultora."), e);
		}
	}
	@Override
	public Mensaje saveRolConsultora(Consultoras consultoras)
			throws ConsultoraNoAlmacenadaException {
		try {
			return consultorasDao.saveRolConsultora(consultoras);
		} catch (DaoException e) {
			throw new ConsultoraNoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al guardar el Rol de la Consultora."), e);
		}
	}

	@Override
	public List<Consultoras> getConsultorasAdmin(int idPadre)
			throws DaoException {
		try {
			return consultorasDao.getConsultorasAdmin(idPadre);
		} catch (DaoException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public List<PyMEs> getPymesAdmin(int idUsuarioAdmin) throws DaoException {
		try {
			return consultorasDao.getPymesAdmin(idUsuarioAdmin);
		} catch (DaoException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}
	
	@Override
	public List<PyMEs> getPymes(int idConsultoras) throws DaoException {
		try {
			return consultorasDao.getPymes(idConsultoras);
		} catch (DaoException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public Mensaje saveRelPymesConsultora(int uPymes, int uConsultor)
			throws DaoException {
		try {
			return consultorasDao.saveRelPymesConsultora(uPymes, uConsultor);
		} catch (DaoException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}
	@Override
	public Mensaje saveCedula(int idServicio, String cedula)
			throws DaoException {
		try {
			return consultorasDao.saveCedula(idServicio, cedula);
		} catch (DaoException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}
	@Override
	public List<Pagos> getPagos(int idConsultora,int filtro) {
		try {
			return consultorasDao.getPagos(idConsultora,filtro);
		} catch (DaoException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public Pagos getPagos(int idServicio) {
		try {
			return consultorasDao.getPagos(idServicio);
		} catch (DaoException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public String saveFacturaAnticipo(String numeroFactura, String idServicios) {
		try {
			return consultorasDao.saveFacturaAnticipo(numeroFactura, idServicios);
		} catch (DaoException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public String saveFacturaAbono1(String numeroFactura, String idServicios) {
		try {
			return consultorasDao.saveFacturaAbono1(numeroFactura, idServicios);
		} catch (DaoException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public String saveFacturaAbono2(String numeroFactura, String idServicios) {
		try {
			return consultorasDao.saveFacturaAbono2(numeroFactura, idServicios);
		} catch (DaoException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public String saveFacturaFiniquito(String numeroFactura, String idServicios) {
		try {
			return consultorasDao.saveFacturaFiniquito(numeroFactura, idServicios);
		} catch (DaoException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}
	@Override
	public List<PyMEs> getBusquedaPyME(String busqueda, String estado,
			String cveScian, String nombreComercial,int idConsultora)
			throws PyMEsNoObtenidasException {
		try {
			return consultorasDao.getBusquedaPyMEs(busqueda, estado, cveScian,
					nombreComercial,idConsultora);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al consultar los datos de las PyMEs."), e);
		}
	}

	@Override
	public String getPymeByServicio(int idServicio) {
		try {
			return consultorasDao.getPymeByServicio(idServicio);
		} catch (DaoException e) {
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}
	@Override
	public Mensaje saveConsultor(Consultoras consultor)
			throws ConsultoraNoAlmacenadaException {
		try {
			return consultorasDao.saveConsultor(consultor);
		} catch (DaoException e) {
			throw new ConsultoraNoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al guardar la Consultora."), e);
		}
	}

}
