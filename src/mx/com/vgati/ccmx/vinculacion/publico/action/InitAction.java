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
import mx.com.vgati.ccmx.vinculacion.publico.exception.UsuarioNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.framework.action.AbstractBaseAction;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Usuario;
import mx.com.vgati.framework.util.Null;
import mx.com.vgati.framework.util.SendEmail;

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
	private Mensaje mensaje;
	private String recover;

	public void setInitService(InitService initService) {
		this.initService = initService;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public String getRecover() {
		return recover;
	}

	public void setRecover(String recover) {
		this.recover = recover;
	}

	@Action(value = "/login", results = { @Result(name = "success", location = "login", type = "tiles") })
	public String login() throws UsuarioNoObtenidoException {
		log.info("login()");
		if (!Null.free(recover).isEmpty()) {
			log.debug("recuperacion de contraseña para: " + recover);
			Usuario u = initService.getUsuario(recover);
			u = u == null ? null : initService
					.getCredenciales(u.getIdUsuario());
			if (u != null && !Null.free(u.getCredenciales()).isEmpty()) {
				SendEmail envia = new SendEmail(
						recover,
						"SIA CCMX Recuperación de contraseña",
						"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado usuario, "
								.concat("gracias por utilizar la herramienta de recuperación de contraseña del sistema CCMX. Para ingresar da clic en el siguiente ")
								.concat("vínculo y confirma tus datos:<br /><br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>")
								.concat("<a href='http://200.76.23.155:8080/vinculacion/inicio.do'>http://200.76.23.155:8080/vinculacion/inicio.do</a><br /><br />")
								.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>La contraseña de acceso al sistema ")
								.concat("es la siguiente:<br /><br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Contraseña: ")
								.concat(Null.free(u.getCredenciales()))
								.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br /><br />En caso de cualquier duda sobre")
								.concat(" la operación y funcionamiento del sistema, no dudes en ponerte en contacto con sistemadevinculacion@ccmx.org.mx.")
								.concat("<br /><br />Muchas gracias por utilizar el sistema de vinculación del CCMX.<br />Centro de Competitividad de México</h5>"),
						null);
				log.debug("Enviando correo electrónico:" + envia);
				setMensaje(new Mensaje(0,
						"Le hemos enviando un correo electrónico con su nueva contraseña."));
			} else {
				log.error("ERROR al recuperar contraseña para: " + recover);
				setMensaje(new Mensaje(1, "No se encontró el usuario '"
						+ recover + "', intente nuevamente."));
			}
		}
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
					"actionName", "coordinadorConsultoriasPyMEsShow",
					"namespace", "/consultorias/coordinacion" }),
			@Result(name = "coorddip", type = "redirectAction", params = {
					"actionName", "coordinadorDiplomadosDiplomadosShow",
					"namespace", "/diplomados/coordinacion" }),
			@Result(name = "pymeDes", type = "redirectAction", params = {
					"actionName", "pymeErrorLogin", "namespace", "/pyme" }),
			@Result(name = "pyme", type = "redirectAction", params = {
					"actionName", "pymeInformacionShow", "namespace", "/pyme" }),
			@Result(name = "admconsult", type = "redirectAction", params = {
					"actionName", "consultoraConsultoresShow", "namespace",
					"/consultor/administracion" }),
			@Result(name = "consult", type = "redirectAction", params = {
					"actionName", "consultorInformacionShow", "namespace",
					"/consultor", "init", "1" }),
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
		else if (principal.isUserInRole(Roles.PyME.name())
				&& usuario.isEstatus() == true)
			return "pymeDes";
		else if (principal.isUserInRole(Roles.PyME.name())
				&& usuario.isEstatus() != true)
			return "pyme";
		else if (principal.isUserInRole(Roles.AdministradorConsultores.name()))
			return "admconsult";
		else if (principal.isUserInRole(Roles.Consultor.name()))
			return "consult";
		else
			return SUCCESS;
	}

	@Action(value = "/terminos", results = { @Result(name = "success", location = "terminosUso", type = "tiles") })
	public String terminos() {
		log.debug("terminos()");

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
