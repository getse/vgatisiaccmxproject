package mx.com.vgati.ccmx.vinculacion.publico.action;

import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.framework.action.AbstractBaseAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;

/**
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
public class InitAction extends AbstractBaseAction {

	// TODO : Implementar la inyección de dependencias con Spring

	private static final long serialVersionUID = -4126012772376778539L;

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

	@SuppressWarnings("unused")
	@Action(value = "/inicio", results = {
			@Result(name = "admccmx", type = "redirectAction", params = {
					"actionName", "showDatTra", "namespace", "/ccmx/administracion/tractoras" }),
			@Result(name = "admtract", type = "redirectAction", params = {
					"actionName", "showDatAdm", "namespace", "/tractoras/administracion/datos" }),
			@Result(name = "tract", type = "redirectAction", params = {
					"actionName", "showDat", "namespace", "/tractoras/datos" }),
			@Result(name = "coordconsul", type = "redirectAction", params = {
					"actionName", "showLis", "namespace", "/coordinacion/consultoras/listado" }),
			@Result(name = "coorddip", type = "redirectAction", params = {
					"actionName", "showLis", "namespace", "/coordinacion/diplomados/listado" }),
			@Result(name = "pyme", type = "redirectAction", params = {
					"actionName", "showDat", "namespace", "/pyme/datos" }),
			@Result(name = "admconsult", type = "redirectAction", params = {
					"actionName", "showDatAdms", "namespace", "/consultoria/administracion/datos" }),
			@Result(name = "consult", type = "redirectAction", params = {
					"actionName", "showDat", "namespace", "/consultoria/datos" }),
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "logout", "namespace", "/" }) })
	public String begin() {
		log.info("principal=" + principal.getUserPrincipal().toString());
		Usuario usuario = new Usuario();
		usuario.setId(principal.getUserPrincipal().toString());
		sessionMap.put("Usuario", usuario);

		if (true)
			return "admtract";
		else
			return SUCCESS;
	}

}
