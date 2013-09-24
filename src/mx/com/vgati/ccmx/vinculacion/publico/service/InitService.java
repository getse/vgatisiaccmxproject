/*
 * InitService.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.publico.service;

import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.exception.UsuarioNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.exception.UsuarioNoValidadoException;
import mx.com.vgati.framework.dto.Usuario;

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

	public Documento getArchivo(String id) throws DocumentoNoObtenidoException;

}
