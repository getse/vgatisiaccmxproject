/*
 * CoordinadorDiplomadosService.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.service;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.DiplomadosNoObtenidosException;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface CoordinadorDiplomadosService {

	public List<Diplomados> getDiplomados(int id)
			throws DiplomadosNoObtenidosException;

}