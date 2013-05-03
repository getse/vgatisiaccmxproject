/*
 * CoordinadorConsultoriasServiceImp.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.service.imp;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dao.CoordinadorConsultoriasDao;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dto.Consultorias;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.exception.ConsultoriasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.service.CoordinadorConsultoriasService;
import mx.com.vgati.framework.service.AbstractBaseService;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class CoordinadorConsultoriasServiceImp extends AbstractBaseService
		implements CoordinadorConsultoriasService {

	private CoordinadorConsultoriasDao coordinadorConsultoriasDao;

	public void setCoordinadorConsultoriasDao(
			CoordinadorConsultoriasDao coordinadorConsultoriasDao) {
		this.coordinadorConsultoriasDao = coordinadorConsultoriasDao;
	}

	@Override
	public List<Consultorias> getConsultorias(int id)
			throws ConsultoriasNoObtenidasException {
		return null;
	}

}
