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

import mx.com.vgati.ccmx.vinculacion.tractoras.dao.TractorasDao;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Productos;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.ProductosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoAlmacenadosException;
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
	public List<Requerimientos> getRequerimientos(String id)
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

}
