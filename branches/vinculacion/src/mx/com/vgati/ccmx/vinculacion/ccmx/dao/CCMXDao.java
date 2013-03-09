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

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.framework.dao.exception.DaoException;

public interface CCMXDao {

	public List<Tractoras> getTractoras(Tractoras tractoras)
	throws DaoException;

	public Tractoras saveTractoras(Tractoras tractoras)
	throws DaoException;
	
}
