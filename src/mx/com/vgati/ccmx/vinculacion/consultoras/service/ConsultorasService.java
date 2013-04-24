/*
 * ConsultorasService.java        01/04/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.service;

import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.exception.ConsultoraNoObtenidaException;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface ConsultorasService {

	public Consultoras getConsultora(int id)
			throws ConsultoraNoObtenidaException;

}