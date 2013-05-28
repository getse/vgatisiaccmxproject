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
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.exception.ConsultoriasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ConsultoriasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMENoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.report.exception.FacturasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
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
			throws ConsultoraNoObtenidaException {
		try {
			return consultorasDao.getConsultorasAdmin(idPadre);
		} catch (DaoException e) {
			throw new ConsultoraNoObtenidaException(new ExceptionMessage(
			"Ocurrio un error al consultar las Consultoras del administrador de consultoras"), e);
		}
	}

	@Override
	public List<PyMEs> getPymesAdmin(int idUsuarioAdmin) throws PyMEsNoObtenidasException {
		try {
			return consultorasDao.getPymesAdmin(idUsuarioAdmin);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error obteniendo lista de Pymes"),e);
		}
	}
	
	@Override
	public List<PyMEs> getPymes(int idConsultoras) throws PyMEsNoObtenidasException {
		try {
			return consultorasDao.getPymes(idConsultoras);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
			"Ocurrio un error obteniendo lista de Pymes"),e);
		}
	}
	@Override
	public List<PyMEs> getPyMEsConsultor(int idConsultor) throws PyMEsNoObtenidasException {
		try {
			return consultorasDao.getPyMEsConsultor(idConsultor);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
			"Ocurrio un error obteniendo lista de Pymes"),e);
		}
	}

	@Override
	public Mensaje saveRelPymesConsultora(int uPymes, int uConsultor)
			throws  PyMENoAlmacenadaException{
		try {
			return consultorasDao.saveRelPymesConsultora(uPymes, uConsultor);
		} catch (DaoException e) {
			throw new PyMENoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error guardando la asignando la Pyme a Consultora"),e);
		}
	}
	@Override
	public Mensaje saveCedula(int idServicio, String cedula)
			throws PyMENoAlmacenadaException {
		try {
			return consultorasDao.saveCedula(idServicio, cedula);
		} catch (DaoException e) {
			throw new PyMENoAlmacenadaException(new ExceptionMessage(
			"Ocurrio un error guardando la Cedula del consultor"),e);
		}
	}
	@Override
	public List<Pagos> getPagos(int idConsultora,int filtro) 
		throws RequerimientosNoObtenidosException {
		try {
			return consultorasDao.getPagos(idConsultora,filtro);
		} catch (DaoException e) {
			throw new RequerimientosNoObtenidosException(new ExceptionMessage(
					"Error obteniendo Facturas"), e);
		}
	}

	@Override
	public ServiciosConsultoria getServiciosConsultoria(int idConsultora) 
		throws ConsultoriasNoObtenidasException {
		try {
			return consultorasDao.getServiciosConsultoria(idConsultora);
		} catch (DaoException e) {
			throw new ConsultoriasNoObtenidasException(new ExceptionMessage(
					"Error obteniendo Facturas"), e);
		}
	}
	
	@Override
	public Pagos getPagos(int idServicio) throws RequerimientosNoObtenidosException {
		try {
			return consultorasDao.getPagos(idServicio);
		} catch (DaoException e) {
			throw new RequerimientosNoObtenidosException(new ExceptionMessage(
			"Error obteniendo Factura. "), e);
		}
	}

	@Override
	public String saveFacturaAnticipo(String numeroFactura, String idServicios)
	throws FacturasNoAlmacenadasException {
		try {
			return consultorasDao.saveFacturaAnticipo(numeroFactura, idServicios);
		} catch (DaoException e) {
			throw new FacturasNoAlmacenadasException(new ExceptionMessage(
			"Ocurrio un error guardando la Factura anticipo"),e);
		}
	}

	@Override
	public String saveFacturaAbono1(String numeroFactura, String idServicios) 
	throws FacturasNoAlmacenadasException {
		try {
			return consultorasDao.saveFacturaAbono1(numeroFactura, idServicios);
		} catch (DaoException e) {
			throw new FacturasNoAlmacenadasException(new ExceptionMessage(
			"Ocurrio un error guardando la Factura abono1"),e);
		}
	}

	@Override
	public String saveFacturaAbono2(String numeroFactura, String idServicios) 
	throws FacturasNoAlmacenadasException {
		try {
			return consultorasDao.saveFacturaAbono2(numeroFactura, idServicios);
		} catch (DaoException e) {
			throw new FacturasNoAlmacenadasException(new ExceptionMessage(
			"Ocurrio un error guardando la Factura abono2"),e);
		}
	}

	@Override
	public String saveFacturaFiniquito(String numeroFactura, String idServicios) 
	throws FacturasNoAlmacenadasException {
		try {
			return consultorasDao.saveFacturaFiniquito(numeroFactura, idServicios);
		} catch (DaoException e) {
			throw new FacturasNoAlmacenadasException(new ExceptionMessage(
			"Ocurrio un error guardando la Factura finiquito"),e);
		}
	}
	@Override
	public List<PyMEs> getBusquedaPyME(String busqueda, String estado,
			String cveScian, String nombreComercial,int idConsultora,int idUsuario)
			throws PyMEsNoObtenidasException {
		try {
			return consultorasDao.getBusquedaPyMEs(busqueda, estado, cveScian,
					nombreComercial,idConsultora,idUsuario);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al consultar los datos de las PyMEs."), e);
		}
	}
	@Override
	public List<PyMEs> getPyMEsCedula(int idConsultor)
			throws PyMEsNoObtenidasException {
		try {
			return consultorasDao.getPyMEsCedula(idConsultor);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al consultar los datos de las PyMEs."), e);
		}
	}

	@Override
	public String getPymeByServicio(int idServicio) throws PyMEsNoObtenidasException {
		try {
			return consultorasDao.getPymeByServicio(idServicio);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
			"Error obteniendo Pyme. "), e);
		}
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
	@Override
	public Mensaje updateConsultor(Consultoras consultor)
			throws ConsultoraNoAlmacenadaException {
		try {
			return consultorasDao.updateConsultor(consultor);
		} catch (DaoException e) {
			throw new ConsultoraNoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al guardar la Consultora."), e);
		}
	}

	@Override
	public Mensaje saveServiciosConsultoria(
			ServiciosConsultoria serviciosConsultoria)
			throws ConsultoriasNoAlmacenadasException {
		try {
			return consultorasDao.saveServiciosConsultoria(serviciosConsultoria);
		} catch (DaoException e) {
			throw new ConsultoriasNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al guardar la Consultora."), e);
		}
	}
}
