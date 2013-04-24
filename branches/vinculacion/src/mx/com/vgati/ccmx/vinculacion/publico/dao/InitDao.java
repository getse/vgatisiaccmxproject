/*
 * InitDao.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.publico.dao;

import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.framework.dao.exception.DaoException;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface InitDao {

	public Usuario getUsuario(String id) throws DaoException;

	public Usuario getCredenciales(int id) throws DaoException;

	public boolean validateUsuario(String cve, int id) throws DaoException;

}
