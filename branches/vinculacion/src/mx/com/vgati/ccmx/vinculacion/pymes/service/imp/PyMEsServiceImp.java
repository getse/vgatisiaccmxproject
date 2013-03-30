/*
 * PyMEsServiceImp.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.service.imp;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.dao.PyMEsDao;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Certificaciones;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Clientes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.AsistentesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.CertificacionesNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.CertificacionesNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ClienteNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ClientesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ConsultoriasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.DiplomadosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.DiplomadosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMeNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMesNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.RespuestaNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.service.PyMEsService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;
import mx.com.vgati.framework.dto.Respuesta;
import mx.com.vgati.framework.exception.ExceptionMessage;
import mx.com.vgati.framework.service.AbstractBaseService;

public class PyMEsServiceImp extends AbstractBaseService implements PyMEsService {

	private PyMEsDao pyMesDao;
	
	public void setPyMesDao(PyMEsDao pyMesDao) {
		this.pyMesDao = pyMesDao;
	}

	@Override
	public PyMEs getPyME(int id) throws PyMesNoObtenidasException {
		try {
			return pyMesDao.getPyMEs(id);
		} catch (DaoException e) {
			throw new PyMesNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al obtener la PyME."), e);
		}
	}
	
	@Override
	public String getIdDomicilio(int id) throws DomiciliosNoObtenidosException {
		try {
			return pyMesDao.getIdDomicilio(id);
		} catch (DaoException e) {
			throw new DomiciliosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al obtener el id domicilio."), e);
		}
	}
	
	@Override
	public Domicilios getDomicilio(int id) throws DomiciliosNoObtenidosException {
		try {
			return pyMesDao.getDomicilios(id);
		} catch (DaoException e) {
			throw new DomiciliosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al obtener el domicilio."), e);
		}
	}
	
	@Override
	public String getIdCliente(int id) throws ClienteNoObtenidoException {
		try {
			return pyMesDao.getIdCliente(id);
		} catch (DaoException e) {
			throw new ClienteNoObtenidoException(new ExceptionMessage(
					"Ocurrio un error al obtener el id cliente."), e);
		}
	}
	
	@Override
	public Clientes getCliente(int id) throws ClienteNoObtenidoException {
		try {
			return pyMesDao.getClientes(id);
		} catch (DaoException e) {
			throw new ClienteNoObtenidoException(new ExceptionMessage(
					"Ocurrio un error al obtener el cliente."), e);
		}
	}
	
	@Override
	public String getIdCertificacion(int id) throws CertificacionesNoObtenidasException {
		try {
			return pyMesDao.getIdCertificacion(id);
		} catch (DaoException e) {
			throw new CertificacionesNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al obtener el id Certificacion."), e);
		}
	}
	
	@Override
	public Certificaciones getCertificacion(int id) throws CertificacionesNoObtenidasException {
		try {
			return pyMesDao.getCertificaciones(id);
		} catch (DaoException e) {
			throw new CertificacionesNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al obtener la certificacion."), e);
		}
	}
	
	@Override
	public Mensaje updatePyME(PyMEs pyMes) throws PyMeNoAlmacenadaException {
		try {
			return pyMesDao.updatePyMEs(pyMes);
		} catch (DaoException e) {
			throw new PyMeNoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al obtener la PyME."), e);
		}
	}
	
	@Override
	public Mensaje saveDomicilio(Domicilios domicilios) throws DomiciliosNoAlmacenadosException {
		try {
			return pyMesDao.saveDomicilios(domicilios);
		} catch (DaoException e) {
			throw new DomiciliosNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al guradar el domicilio."), e);
		}
	}
	
	@Override
	public Mensaje saveRelDomicilio(Domicilios domicilios, PyMEs pyMes) throws DomiciliosNoAlmacenadosException {
		try {
			return pyMesDao.saveRelDomicilios(domicilios, pyMes);
		} catch (DaoException e) {
			throw new DomiciliosNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al guradar la relacion Domicilio."), e);
		}
	}
	
	@Override
	public Mensaje updateDomicilio(Domicilios domicilios)
			throws DomiciliosNoAlmacenadosException {
		try {
			return pyMesDao.updateDomicilios(domicilios);
		} catch (DaoException e) {
			throw new DomiciliosNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al actualizar los datos del Domicilio."), e);
		}
	}
	
	@Override
	public Mensaje saveCliente(Clientes clientes) throws ClientesNoAlmacenadosException {
		try {
			return pyMesDao.saveClientes(clientes);
		} catch (DaoException e) {
			throw new ClientesNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al guradar al cliente."), e);
		}
	}
	
	@Override
	public Mensaje updateCliente(Clientes clientes)
			throws ClientesNoAlmacenadosException {
		try {
			return pyMesDao.updateClientes(clientes);
		} catch (DaoException e) {
			throw new ClientesNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al actualizar los datos del Cliente."), e);
		}
	}
	
	@Override
	public Mensaje saveCertificacion(Certificaciones certificaciones) throws CertificacionesNoAlmacenadasException {
		try {
			return pyMesDao.saveCertificaciones(certificaciones);
		} catch (DaoException e) {
			throw new CertificacionesNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al guradar la certificacion."), e);
		}
	}
	
	@Override
	public Mensaje updateCertificacion(Certificaciones certificaciones)
			throws CertificacionesNoAlmacenadasException {
		try {
			return pyMesDao.updateCertificaciones(certificaciones);
		} catch (DaoException e) {
			throw new CertificacionesNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al actualizar los datos de las Certificaciones."), e);
		}
	}

	@Override
	public List<PyMEs> getBusquedaPyME(String busqueda, String estado, String codigoPostal, String sector, String subSector)
			throws PyMesNoObtenidasException {
		try {
			return pyMesDao.getBusquedaPyMEs(busqueda, estado, codigoPostal, sector, subSector);
		} catch (DaoException e) {
			throw new PyMesNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al consultar los datos de las PyMEs."), e);
		}
	}

	@Override
	public List<ServiciosDiplomado> getServiciosDiplomado(ServiciosDiplomado serviciosDiplomado)
			throws DiplomadosNoObtenidosException {
		try {
			return pyMesDao.getServiciosDiplomados(serviciosDiplomado);
		} catch (DaoException e) {
			throw new DiplomadosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al consultar los diplomados."), e);
		}
	}

	@Override
	public Mensaje saveAsistente(Asistentes asistentes) throws AsistentesNoAlmacenadosException {
		try {
			return pyMesDao.saveAsistentes(asistentes);
		} catch (DaoException e) {
			throw new AsistentesNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al guardar el asistente."), e);
		}
	}
	
	@Override
	public List<Requerimientos> getRequerimiento(String busqueda, String tractoraReq, java.sql.Date fechaDesde, java.sql.Date fechaHasta)
			throws RequerimientosNoObtenidosException {
		try {
			return pyMesDao.getRequerimientos(busqueda, tractoraReq, fechaDesde, fechaHasta);
		} catch (DaoException e) {
			throw new RequerimientosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al consultar los Requerimientos."), e);
		}
	}

	@Override
	public Mensaje saveConsultoria(ServiciosConsultoria serviciosConsultoria)
			throws ConsultoriasNoAlmacenadasException {
		try {
			return pyMesDao.saveConsultorias(serviciosConsultoria);
		} catch (DaoException e) {
			throw new ConsultoriasNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al guardar el servicio."), e);
		}
	}

	@Override
	public List<Tractoras> getTractora()
			throws TractorasNoObtenidasException {
		try {
			return pyMesDao.getTractoras();
		} catch (DaoException e) {
			throw new TractorasNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al consultar las tractoras."), e);
		}
	}
	
	@Override
	public List<Requerimientos> getFecha()
			throws RequerimientosNoObtenidosException {
		try {
			return pyMesDao.getFechas();
		} catch (DaoException e) {
			throw new RequerimientosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al consultar las fechas del Requerimiento."), e);
		}
	}

	@Override
	public Requerimientos getShowRequerimiento(int idRequerimiento)
			throws RequerimientosNoObtenidosException {
		try {
			return pyMesDao.getShowRequerimientos(idRequerimiento);
		} catch (DaoException e) {
			throw new RequerimientosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al consultar el Requerimiento."), e);
		}
	}

	@Override
	public Mensaje saveRespuesta(Respuesta respuesta)
			throws RespuestaNoAlmacenadaException {
		try {
			return pyMesDao.saveRespuestas(respuesta);
		} catch (DaoException e) {
			throw new RespuestaNoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al guardar la respuesta al requerimiento."), e);
		}
	}

	@Override
	public Mensaje saveServDiplomado(ServiciosDiplomado serviciosDiplomado)
			throws DiplomadosNoAlmacenadosException {
		try {
			return pyMesDao.saveServDiplomados(serviciosDiplomado);
		} catch (DaoException e) {
			throw new DiplomadosNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al guardar el servisio diplomado."), e);
		}
	}
}