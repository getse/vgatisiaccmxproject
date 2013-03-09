/*
 * TractorasDao.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.tractoras.dao;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Productos;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface TractorasDao {

	public List<Requerimientos> getRequerimientos(String id)
			throws DaoException;

	public Requerimientos getRequerimiento(String id) throws DaoException;

	public Mensaje insertRequerimiento(Requerimientos requerimientos)
			throws DaoException;

	public Mensaje updateRequerimiento(Requerimientos requerimientos)
			throws DaoException;

	public List<Productos> getProductos(String busqueda) throws DaoException;

}
