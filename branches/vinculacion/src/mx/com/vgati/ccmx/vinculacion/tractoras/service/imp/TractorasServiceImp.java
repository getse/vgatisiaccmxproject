/*
 * TractorasServiceImp.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.tractoras.service.imp;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.tractoras.dao.TractorasDao;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Productos;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.CompradoresNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.CompradoresNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.ProductosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoEliminadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.service.TractorasService;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;
import mx.com.vgati.framework.exception.ExceptionMessage;
import mx.com.vgati.framework.service.AbstractBaseService;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class TractorasServiceImp extends AbstractBaseService implements
		TractorasService {

	private TractorasDao tractorasDao;

	public void setTractorasDao(TractorasDao tractorasDao) {
		this.tractorasDao = tractorasDao;
	}

	@Override
	public List<Requerimientos> getRequerimientos(int id)
			throws RequerimientosNoObtenidosException {
		try {
			return tractorasDao.getRequerimientos(id);
		} catch (DaoException e) {
			throw new RequerimientosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al consultar los Requerimientos."), e);
		}
	}

	@Override
	public Requerimientos getRequerimiento(String id)
			throws RequerimientosNoObtenidosException {
		try {
			return tractorasDao.getRequerimiento(id);
		} catch (DaoException e) {
			throw new RequerimientosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al consultar un Requerimiento."), e);
		}
	}

	@Override
	public Mensaje insertRequerimiento(Requerimientos requerimientos)
			throws RequerimientosNoAlmacenadosException {
		try {
			return tractorasDao.insertRequerimiento(requerimientos);
		} catch (DaoException e) {
			throw new RequerimientosNoAlmacenadosException(
					new ExceptionMessage(
							"Ocurrio un error al guradar un Requerimiento."), e);
		}

	}

	@Override
	public Mensaje updateRequerimiento(Requerimientos requerimientos)
			throws RequerimientosNoAlmacenadosException {
		try {
			return tractorasDao.updateRequerimiento(requerimientos);
		} catch (DaoException e) {
			throw new RequerimientosNoAlmacenadosException(
					new ExceptionMessage(
							"Ocurrio un error al actualizar un Requerimiento."),
					e);
		}

	}

	@Override
	public List<Productos> getProductos(String busqueda)
			throws ProductosNoObtenidosException {
		try {
			return tractorasDao.getProductos(busqueda);
		} catch (DaoException e) {
			throw new ProductosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al obtener la lista de productos."), e);
		}

	}

	@Override
	public Mensaje deleteRequerimiento(Requerimientos requerimientos)
			throws RequerimientosNoEliminadosException {
		try {
			return tractorasDao.deleteRequerimiento(requerimientos);
		} catch (DaoException e) {
			throw new RequerimientosNoEliminadosException(new ExceptionMessage(
					"Ocurrio un error al intentar eliminar el requerimiento."),
					e);
		}

	}

	public List<Tractoras> getCompradores(int id)
			throws CompradoresNoObtenidosException {
		try {
			return tractorasDao.getCompradores(id);
		} catch (DaoException e) {
			throw new CompradoresNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al consultar los Compradores."), e);
		}
	}

	@Override
	public Mensaje saveUsuarioComp(Tractoras tractoras)
			throws CompradoresNoAlmacenadosException {
		try {
			return tractorasDao.saveUsuarioComp(tractoras);
		} catch (DaoException e) {
			throw new CompradoresNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al guradar El Usuario del Comprador."), e);
		}
	}

	@Override
	public Mensaje saveRolComp(Tractoras tractoras)
			throws CompradoresNoAlmacenadosException {
		try {
			return tractorasDao.saveRolComp(tractoras);
		} catch (DaoException e) {
			throw new CompradoresNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al guradar El Rol del Comprador."), e);
		}
	}

	@Override
	public Mensaje saveComprador(Tractoras tractoras)
			throws CompradoresNoAlmacenadosException {
		try {
			return tractorasDao.saveCompradores(tractoras);
		} catch (DaoException e) {
			throw new CompradoresNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al guradar al Comprador."), e);
		}
	}

	@Override
	public Tractoras getTractora(int id) throws CompradoresNoObtenidosException {
		try {
			return tractorasDao.getTractora(id);
		} catch (DaoException e) {
			throw new CompradoresNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al obtener la Tractora."), e);
		}
	}
	
	@Override
	public Mensaje updateTractoras(Tractoras tractoras)
			throws TractorasNoAlmacenadasException {
		try {
			return tractorasDao.updateTractora(tractoras);
		} catch (DaoException e) {
			throw new TractorasNoAlmacenadasException(
					new ExceptionMessage(
							"Ocurrio un error al actualizar los datos de la Tractora."),
					e);
		}

	}
	
	@Override
	public Mensaje insertDomicilio(Domicilios domicilios)
			throws DomiciliosNoAlmacenadosException {
		try {
			return tractorasDao.insertDomicilios(domicilios);
		} catch (DaoException e) {
			throw new DomiciliosNoAlmacenadosException(
					new ExceptionMessage(
							"Ocurrio un error al guradar un Requerimiento."), e);
		}

	}
	
	@Override
	public Mensaje updateDomicilio(Domicilios domicilios)
			throws DomiciliosNoAlmacenadosException {
		try {
			return tractorasDao.updateDomicilios(domicilios);
		} catch (DaoException e) {
			throw new DomiciliosNoAlmacenadosException(
					new ExceptionMessage(
							"Ocurrio un error al actualizar los datos del Domicilio."),
					e);
		}

	}
	
	@Override
	public Mensaje insertRelDomicilio(Domicilios domicilios, Tractoras tractoras)
			throws DomiciliosNoAlmacenadosException {
		try {
			return tractorasDao.insertRelDomicilios(domicilios, tractoras);
		} catch (DaoException e) {
			throw new DomiciliosNoAlmacenadosException(
					new ExceptionMessage(
							"Ocurrio un error al guradar un Requerimiento."), e);
		}

	}
}
