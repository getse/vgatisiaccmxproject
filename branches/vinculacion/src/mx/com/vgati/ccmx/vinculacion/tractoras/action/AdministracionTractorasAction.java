/*
 * AdministracionTractorasAction.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.tractoras.action;

import mx.com.vgati.framework.action.AbstractBaseAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.Result;

/**
 * 
 * @author Getsemani Correa
 * 
 */
@Namespaces({ @Namespace(value = "tractoras/administracion/datos"),
		@Namespace(value = "tractoras/administracion/compradores"),
		@Namespace(value = "tractoras/administracion/requerimientos"),
		@Namespace(value = "tractoras/administracion/busquedas"),
		@Namespace(value = "tractoras/administracion/reportes") })
public class AdministracionTractorasAction extends AbstractBaseAction {

	private static final long serialVersionUID = 6076350949482670437L;
	private int menu = 1;
	private static final String[] op = { "MI INFORMACI&Oacute;N",
			"COMPRADORES", "REQUERIMIENTOS", "B&Uacute;SQUEDAS", "REPORTES" };
	private static final String[] fr = { "showDatAdm.do", "showComAdm.do",
			"showReqAdm.do", "showBusAdm.do", "showRepAdm.do" };

	@Action(value = "/addDatAdm", results = { @Result(name = "success", location = "tractoras.administracion.datos.add", type = "tiles") })
	public String addDatAdm() {
		return SUCCESS;
	}

	@Action(value = "/showDatAdm", results = { @Result(name = "success", location = "tractoras.administracion.datos.show", type = "tiles") })
	public String showDatAdm() {
		return SUCCESS;
	}

	@Action(value = "/addComAdm", results = { @Result(name = "success", location = "tractoras.administracion.compradores.add", type = "tiles") })
	public String addComAdm() {
		return SUCCESS;
	}

	@Action(value = "/showComAdm", results = { @Result(name = "success", location = "tractoras.administracion.compradores.show", type = "tiles") })
	public String showComAdm() {
		return SUCCESS;
	}

	@Action(value = "/addReqAdm", results = { @Result(name = "success", location = "tractoras.administracion.requerimientos.add", type = "tiles") })
	public String addReqAdm() {
		return SUCCESS;
	}

	@Action(value = "/showReqAdm", results = { @Result(name = "success", location = "tractoras.administracion.requerimientos.show", type = "tiles") })
	public String showReqAdm() {
		return SUCCESS;
	}

	@Action(value = "/addBusAdm", results = { @Result(name = "success", location = "tractoras.administracion.busquedas.add", type = "tiles") })
	public String addBusAdm() {
		return SUCCESS;
	}

	@Action(value = "/showBusAdm", results = { @Result(name = "success", location = "tractoras.administracion.busquedas.show", type = "tiles") })
	public String showBusAdm() {
		return SUCCESS;
	}

	@Action(value = "/addRepAdm", results = { @Result(name = "success", location = "tractoras.administracion.reportes.add", type = "tiles") })
	public String addRepAdm() {
		return SUCCESS;
	}

	@Action(value = "/showRepAdm", results = { @Result(name = "success", location = "tractoras.administracion.reportes.show", type = "tiles") })
	public String showRepAdm() {
		return SUCCESS;
	}

	public void setMenu(int menu) {
		this.menu = menu;
	}

	public int getMenu() {
		return menu;
	}

	public String[] getOp() {
		return op;
	}

	public String[] getFr() {
		return fr;
	}

}
