/*
 * ConsultorasDao.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.dao;

import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.framework.dao.exception.DaoException;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface ConsultorasDao {

	public Consultoras getConsultora(int id) throws DaoException;

}