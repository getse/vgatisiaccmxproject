/*
 * InitAction.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.publico.action;

import mx.com.vgati.ccmx.vinculacion.dto.Roles;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.exception.UsuarioNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.framework.action.AbstractBaseAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;

/**
 * 
 * @author Getsemani Correa
 * 
 */
public class InitAction extends AbstractBaseAction {

	// TODO : Implementar la inyección de dependencias con Spring

	private static final long serialVersionUID = -4126012772376778539L;

	private InitService initService;

	public void setInitService(InitService initService) {
		this.initService = initService;
	}

	@Action(value = "/login", results = { @Result(name = "success", location = "login", type = "tiles") })
	public String login() {
		log.info("login()");
		return SUCCESS;
	}

	@Action(value = "/errorLogin", results = { @Result(name = "success", location = "errorLogin", type = "tiles") })
	public String errorLogin() {
		log.info("errorLogin()");
		return SUCCESS;
	}

	@Action(value = "/logout", results = { @Result(name = "success", type = "redirectAction", params = {
			"actionName", "inicio", "namespace", "/" }) })
	public String logout() {
		log.info("logout()");
		if (principal != null && principal.getUserPrincipal() != null) {
			log.info("principal=" + principal.getUserPrincipal().toString());
		}
		((SessionMap<String, Object>) sessionMap).invalidate();
		log.info("Sesion invalidada");
		return SUCCESS;
	}

	@Action(value = "/inicio", results = {
			@Result(name = "admccmx", type = "redirectAction", params = {
					"actionName", "showLisTra", "namespace",
					"/ccmx/administracion/tractoras" }),
			@Result(name = "admtract", type = "redirectAction", params = {
					"actionName", "showDatAdm", "namespace",
					"/tractoras/administracion/datos" }),
			@Result(name = "tract", type = "redirectAction", params = {
					"actionName", "showDat", "namespace", "/tractora/datos" }),
			@Result(name = "coordconsul", type = "redirectAction", params = {
					"actionName", "showLis", "namespace",
					"/consultoras/coordinacion/listado" }),
			@Result(name = "coorddip", type = "redirectAction", params = {
					"actionName", "showLis", "namespace",
					"/diplomados/coordinacion/listado" }),
			@Result(name = "pyme", type = "redirectAction", params = {
					"actionName", "showDat", "namespace", "/pyme/datos" }),
			@Result(name = "admconsult", type = "redirectAction", params = {
					"actionName", "showDatAdms", "namespace",
					"/consultores/administracion/datos" }),
			@Result(name = "consult", type = "redirectAction", params = {
					"actionName", "showDat", "namespace", "/consultor/datos" }),
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "logout", "namespace", "/" }) })
	public String begin() throws UsuarioNoObtenidoException {
		log.info("principal=" + principal.getUserPrincipal().toString());
		Usuario usuario = new Usuario();
		usuario.setId(principal.getUserPrincipal().toString());
		usuario.setIdUsuario(initService.getUsuario(
				principal.getUserPrincipal().toString()).getIdUsuario());
		sessionMap.put("Usuario", usuario);

		if (principal.isUserInRole(Roles.AdministradorCCMX.name()))
			return "admccmx";
		else if (principal.isUserInRole(Roles.AdministradorTractora.name()))
			return "admtract";
		else if (principal.isUserInRole(Roles.Tractora.name()))
			return "tract";
		else if (principal.isUserInRole(Roles.CoordinadorConsultoras.name()))
			return "coordconsul";
		else if (principal.isUserInRole(Roles.CoordinadorDiplomados.name()))
			return "coorddip";
		else if (principal.isUserInRole(Roles.PyME.name()))
			return "pyme";
		else if (principal.isUserInRole(Roles.AdministradorConsultores.name()))
			return "admconsult";
		else if (principal.isUserInRole(Roles.Consultor.name()))
			return "consult";
		else
			return SUCCESS;
	}

}
