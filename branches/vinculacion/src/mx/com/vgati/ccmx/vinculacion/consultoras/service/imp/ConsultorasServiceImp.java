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

import mx.com.vgati.ccmx.vinculacion.consultoras.dao.ConsultorasDao;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.exception.ConsultoraNoObtenidaException;
import mx.com.vgati.ccmx.vinculacion.consultoras.service.ConsultorasService;
import mx.com.vgati.framework.dao.exception.DaoException;
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

}
