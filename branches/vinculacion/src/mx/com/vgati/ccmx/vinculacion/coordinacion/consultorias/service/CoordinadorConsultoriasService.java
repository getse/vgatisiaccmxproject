/*
 * CoordinadorConsultoriasService.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.service;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.dto.Consultorias;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.exception.ConsultoriasNoObtenidasException;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface CoordinadorConsultoriasService {

	public List<Consultorias> getConsultorias(int id)
			throws ConsultoriasNoObtenidasException;

}