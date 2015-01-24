/*
 * InitAction.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.publico.action;

import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import mx.com.vgati.ccmx.vinculacion.dto.Roles;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
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
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class InitAction extends AbstractBaseAction {

	private InitService initService;
	private Mensaje mensaje;
	private String recover;
	private String idArchivo;
	private String nameArchivo;
	private InputStream archivo;

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

	public String getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(String idArchivo) {
		this.idArchivo = idArchivo;
	}

	public String getNameArchivo() {
		return nameArchivo;
	}

	public void setNameArchivo(String nameArchivo) {
		this.nameArchivo = nameArchivo;
	}

	public InputStream getArchivo() {
		return archivo;
	}

	public void setArchivo(InputStream archivo) {
		this.archivo = archivo;
	}

	@Action(value = "/login", results = { @Result(name = "success", location = "login", type = "tiles") })
	public String login() throws UsuarioNoObtenidoException {
		log.info("login()");
		if (!Null.free(recover).isEmpty()) {
			log.debug("recuperacion de contrase�a para: " + recover);
			Usuario u = initService.getUsuario(recover);
			u = u == null ? null : initService
					.getCredenciales(u.getIdUsuario());
			if (u != null && !Null.free(u.getCredenciales()).isEmpty()) {
				SendEmail envia = new SendEmail(
						recover,
						"SIA CCMX Recuperaci�n de contrase�a",
						"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado usuario, "
								.concat("gracias por utilizar la herramienta de recuperaci�n de contrase�a del sistema CCMX. Para ingresar da clic en el siguiente ")
								.concat("v�nculo y confirma tus datos:<br /><br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>")
								.concat("<a href='http://www.ccmx.mx/vinculacion/inicio.do'>http://www.ccmx.mx/vinculacion/inicio.do</a><br /><br />")
								.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>La contrase�a de acceso al sistema ")
								.concat("es la siguiente:<br /><br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Contrase�a: ")
								.concat(Null.free(u.getCredenciales()))
								.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br /><br />En caso de cualquier duda sobre")
								.concat(" la operaci�n y funcionamiento del sistema, no dudes en ponerte en contacto con soporte@ccmx.org.mx.")
								.concat("<br /><br />Muchas gracias por utilizar el sistema de vinculaci�n del CCMX.<br />Centro de Competitividad de M�xico</h5>"),
						null);
				log.debug("Enviando correo electr�nico:" + envia);
				setMensaje(new Mensaje(0,
						"Le hemos enviando un correo electr�nico con su nueva contrase�a."));
			} else {
				log.error("ERROR al recuperar contrase�a para: " + recover);
				setMensaje(new Mensaje(1, "No se encontr� el usuario '"
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
	public String logout() throws ServletException, UsuarioNoObtenidoException {
		log.info("logout()");
		if (principal != null && principal.getUserPrincipal() != null) {
			log.info("logout... principal: "
					+ principal.getUserPrincipal().toString());
		}
		Cookie ccmxCookie = searchCookie("ccmx");
		if (ccmxCookie != null) {
			ccmxCookie.setMaxAge(0);
			ccmxCookie.setValue(null);
			response.addCookie(ccmxCookie);
			log.info("cookie 'ccmx' eliminada: " + ccmxCookie);
		}
		boolean superUsuario = sessionMap.get("super") == null ? false
				: (Boolean) sessionMap.get("super");
		((SessionMap<String, Object>) sessionMap).invalidate();
		log.info("Sesion invalidada");

		if (superUsuario) {
			log.info("regresando a la sesion de super-usuario...");
			principal = null;

			HttpServletRequest request = ServletActionContext.getRequest();
			request.logout();
			Usuario sU = initService.getCredenciales(1);
			request.login(sU.getId(), sU.getCredenciales());
		}

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

	@Action(value = "/showMan", results = {
			@Result(name = "success", type = "stream", params = { "inputName",
					"archivo", "contentType", "mimeArchivo",
					"contentDisposition",
					"attachment;filename=\"${nameArchivo}\"" }),
			@Result(name = "input", location = "terminosUso", type = "tiles"),
			@Result(name = "error", location = "terminosUso", type = "tiles") })
	public String showMan() throws DocumentoNoObtenidoException {
		log.debug("showMan()");
		setArchivo(initService.getArchivo(idArchivo).getIs());

		log.debug("manual=" + archivo);
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		return SUCCESS;
	}

	private Cookie searchCookie(String cookie) {
		log.debug("cookiee: " + cookie);

		HttpServletRequest request = ServletActionContext.getRequest();
		log.debug("cookies: " + request.getCookies());

		if (request.getCookies() != null)
			for (Cookie c : request.getCookies()) {
				log.debug("cookie: " + c.getName());
				if (Null.free(cookie).equals(c.getName())) {
					log.debug("result: " + c.getName());
					return c;
				}
			}

		return null;
	}

}
