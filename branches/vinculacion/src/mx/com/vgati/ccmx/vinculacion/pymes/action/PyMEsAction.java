/*
 * PyMEsAction.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.action;

import mx.com.vgati.framework.action.AbstractBaseAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.Result;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
@Namespaces({ @Namespace(value = "pymes/datos"),
		@Namespace(value = "pymes/requerimientos"),
		@Namespace(value = "pymes/servicios"),
		@Namespace(value = "pymes/busquedas") })
public class PyMEsAction extends AbstractBaseAction {

	private int menu = 1;
	private static final String[] op = { "MI INFORMACI&Oacute;N",
			"REQUERIMIENTOS", "SERVICIOS", "B&Uacute;SQUEDAS" };
	private static final String[] fr = { "showDat.do", "listReq.do",
			"showSer.do", "showBus.do" };

	@Action(value = "/showDat", results = { @Result(name = "success", location = "pyme.datos.show", type = "tiles") })
	public String showDat() {
		log.debug("showDat()");
		setMenu(1);
		return SUCCESS;
	}

	@Action(value = "/listReq", results = { @Result(name = "success", location = "pyme.requerimientos.list", type = "tiles") })
	public String listReq() {
		log.debug("listReq()");
		setMenu(2);
		return SUCCESS;
	}

	@Action(value = "/showSer", results = { @Result(name = "success", location = "pyme.servicios.show", type = "tiles") })
	public String showSer() {
		log.debug("showSer()");
		setMenu(3);
		return SUCCESS;
	}

	@Action(value = "/showBus", results = { @Result(name = "success", location = "pyme.busqueda.show", type = "tiles") })
	public String showBus() {
		log.debug("showBus()");
		setMenu(4);
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
