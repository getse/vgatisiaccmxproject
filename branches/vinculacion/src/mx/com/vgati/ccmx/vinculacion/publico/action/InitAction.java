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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import mx.com.vgati.ccmx.vinculacion.dto.Roles;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.exception.UsuarioNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.framework.action.AbstractBaseAction;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;

/**
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class InitAction extends AbstractBaseAction {

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
		Cookie ccmxCookie = searchCookie("ccmx");
		if (ccmxCookie != null) {
			ccmxCookie.setMaxAge(0);
			ccmxCookie.setValue(null);
			response.addCookie(ccmxCookie);
			log.info("cookie 'ccmx' eliminada: " + ccmxCookie);
		}
		((SessionMap<String, Object>) sessionMap).invalidate();
		log.info("Sesion invalidada");
		return SUCCESS;
	}

	@Action(value = "/inicio", results = {
			@Result(name = "admccmx", type = "redirectAction", params = {
					"actionName", "tractorasShow", "namespace",
					"/ccmx/administracion" }),
			@Result(name = "tract", type = "redirectAction", params = {
					"actionName", "tractoraInformacionShow", "namespace",
					"/tractora/administracion", "init", "1" }),
			@Result(name = "compr", type = "redirectAction", params = {
					"actionName", "compradorInformacionShow", "namespace",
					"/comprador", "init", "1" }),
			@Result(name = "coordconsul", type = "redirectAction", params = {
					"actionName", "showLis", "namespace",
					"/consultoras/coordinacion" }),
			@Result(name = "coorddip", type = "redirectAction", params = {
					"actionName", "showLis", "namespace",
					"/diplomados/coordinacion" }),
			@Result(name = "pyme", type = "redirectAction", params = {
					"actionName", "pymeInformacionShow", "namespace", "/pyme" }),
			@Result(name = "admconsult", type = "redirectAction", params = {
					"actionName", "consultoraInformacionShow", "namespace",
					"/consultor/administracion" }),
			@Result(name = "consult", type = "redirectAction", params = {
					"actionName", "consultorInformacionShow", "namespace",
					"/consultor" }),
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "logout", "namespace", "/" }) })
	public String begin() throws UsuarioNoObtenidoException {
		log.info("principal=" + principal.getUserPrincipal().toString());
		Usuario usuario = initService.getUsuario(principal.getUserPrincipal()
				.toString());
		sessionMap.put("Usuario", usuario);

		Cookie myCookie = new Cookie("ccmx", "true");
		myCookie.setMaxAge(-1);
		response.addCookie(myCookie);

		if (principal.isUserInRole(Roles.AdministradorCCMX.name()))
			return "admccmx";
		else if (principal.isUserInRole(Roles.Tractora.name()))
			return "tract";
		else if (principal.isUserInRole(Roles.Comprador.name()))
			return "compr";
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

	private Cookie searchCookie(String cookie) {
		log.debug("cookiee: " + cookie);

		HttpServletRequest request = ServletActionContext.getRequest();
		log.debug("cookies: " + request.getCookies());

		for (Cookie c : request.getCookies()) {
			log.debug("cookie: " + c.getName());
			if (cookie.equals(c.getName())) {
				log.debug("result: " + c.getName());
				return c;
			}
		}

		return null;
	}

}
