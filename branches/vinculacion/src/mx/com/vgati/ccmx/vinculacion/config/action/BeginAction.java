package mx.com.vgati.ccmx.vinculacion.config.action;

import mx.com.vgati.framework.action.AbstractBaseAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class BeginAction extends AbstractBaseAction {
	private String mensaje;
	private String nombre;
	private String numero;

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	@Action(value = "/inicios", results = { @Result(name = "success", location = "inicios", type = "tiles") })
	public String captura() {
		log.info("captura()");
		return SUCCESS;
	}

	// @Action(value = "/respuesta", results = { @Result(name = "success",
	// location = "respuesta", type = "tiles") })
	// public String respuesta() {
	// setMensaje("datos " + nombre + " - " + numero);
	// ActionContext.getContext().getSession()
	// .put("GTS", "datos de sesion");
	// return SUCCESS;
	// }

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

}
