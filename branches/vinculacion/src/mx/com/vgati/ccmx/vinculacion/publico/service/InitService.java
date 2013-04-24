/*
 * InitService.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.publico.service;

import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.exception.UsuarioNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.exception.UsuarioNoValidadoException;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface InitService {

	public Usuario getUsuario(String id) throws UsuarioNoObtenidoException;

	public Usuario getCredenciales(int id) throws UsuarioNoObtenidoException;

	public boolean validateUsuario(String cve, int id)
			throws UsuarioNoValidadoException;

}
