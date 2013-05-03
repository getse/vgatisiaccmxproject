/*
 * CoordinadorDiplomadosServiceImp.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.service.imp;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dao.CoordinadorDiplomadosDao;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.DiplomadosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.service.CoordinadorDiplomadosService;
import mx.com.vgati.framework.service.AbstractBaseService;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class CoordinadorDiplomadosServiceImp extends AbstractBaseService
		implements CoordinadorDiplomadosService {

	private CoordinadorDiplomadosDao coordinadorDiplomadosDao;

	public void setCoordinadorDiplomadosDao(
			CoordinadorDiplomadosDao coordinadorDiplomadosDao) {
		this.coordinadorDiplomadosDao = coordinadorDiplomadosDao;
	}

	@Override
	public List<Diplomados> getDiplomados(int id)
			throws DiplomadosNoObtenidosException {
		return null;
	}

}
