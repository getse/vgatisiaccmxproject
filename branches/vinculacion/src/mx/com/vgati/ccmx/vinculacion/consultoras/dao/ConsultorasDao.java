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

import java.util.List;


import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.exception.ConsultoraNoObtenidaException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface ConsultorasDao {
	public Mensaje saveRolConsultora(Consultoras consultoras)
	throws DaoException ;
	public Consultoras getConsultora(int id) throws DaoException;
	public List<Consultoras> getConsultorasAdmin(int idPadre) throws DaoException;
	public List<PyMEs> getPymes() throws DaoException;
	public List<PyMEs> getPymes(int idConsultora) throws DaoException;
	public Mensaje saveRelPymesConsultora(int uPymes,int uConsultor) throws DaoException;
}