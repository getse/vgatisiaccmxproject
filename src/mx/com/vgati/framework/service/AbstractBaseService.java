/*
 * AbstractBaseService.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.framework.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase base abstracta de los servicios del sistema.
 * 
 * @author Getsemani Correa
 * 
 */
public abstract class AbstractBaseService {
	protected Log log = LogFactory.getLog(getClass());
}
