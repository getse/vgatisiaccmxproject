/*
 * InitServiceUmp.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.publico.service.imp;

import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.publico.dao.InitDao;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.exception.UsuarioNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.exception.UsuarioNoValidadoException;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Usuario;
import mx.com.vgati.framework.exception.ExceptionMessage;
import mx.com.vgati.framework.service.AbstractBaseService;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class InitServiceImp extends AbstractBaseService implements InitService {

	private InitDao initDao;

	public void setInitDao(InitDao initDao) {
		this.initDao = initDao;
	}

	@Override
	public Usuario getUsuario(String id) throws UsuarioNoObtenidoException {
		try {
			return initDao.getUsuario(id);
		} catch (DaoException e) {
			throw new UsuarioNoObtenidoException(new ExceptionMessage(
					"Ocurrio un error al consultar el Usuario."), e);
		}
	}

	@Override
	public Usuario getCredenciales(int id) throws UsuarioNoObtenidoException {
		try {
			return initDao.getCredenciales(id);
		} catch (DaoException e) {
			throw new UsuarioNoObtenidoException(new ExceptionMessage(
					"Ocurrio un error al consultar el Usuario."), e);
		}
	}

	@Override
	public boolean validateUsuario(String cve, int id)
			throws UsuarioNoValidadoException {
		try {
			return initDao.validateUsuario(cve, id);
		} catch (DaoException e) {
			throw new UsuarioNoValidadoException(new ExceptionMessage(
					"Ocurrio un error al validar el Usuario."), e);
		}
	}

	@Override
	public Documento getArchivo(String id) throws DocumentoNoObtenidoException {
		try {
			return initDao.getArchivo(id);
		} catch (DaoException e) {
			throw new DocumentoNoObtenidoException(new ExceptionMessage(
					"Ocurrio un error al consultar el Documento."), e);
		}
	}

}
