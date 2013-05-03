/*
 * CoordinadorConsultoriasDaoJdbcImp.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dao.imp;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dao.CoordinadorConsultoriasDao;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dto.Consultorias;
import mx.com.vgati.framework.dao.VinculacionBaseJdbcDao;
import mx.com.vgati.framework.dao.exception.DaoException;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class CoordinadorConsultoriasDaoJdbcImp extends VinculacionBaseJdbcDao
		implements CoordinadorConsultoriasDao {

	@Override
	public List<Consultorias> getConsultorias(int id) throws DaoException {
		return null;
	}

}