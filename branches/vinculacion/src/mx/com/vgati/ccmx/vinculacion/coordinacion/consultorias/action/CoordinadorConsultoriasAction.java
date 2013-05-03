/*
 * CoordinadorConsultoriasAction.java        01/04/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.action;

import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.service.CoordinadorConsultoriasService;
import mx.com.vgati.framework.action.AbstractBaseAction;
import mx.com.vgati.framework.exception.BaseBusinessException;

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
@Namespaces({ @Namespace(value = "consultorias/coordinacion") })
public class CoordinadorConsultoriasAction extends AbstractBaseAction {

	private int menu = 1;
	private static final String[] op = { "PyMEs", "SOLICITUDES", "REPORTES" };
	private static final String[] fr = { "coordinadorConsultoriasPyMEsShow.do",
			"coordinadorConsultoriasSolicitudesShow.do",
			"coordinadorConsultoriasReportesShow.do", "", "", "" };

	private CoordinadorConsultoriasService coordinadorConsultoriasService;

	public void setCoordinadorConsultoriasService(
			CoordinadorConsultoriasService coordinadorConsultoriasService) {
		this.coordinadorConsultoriasService = coordinadorConsultoriasService;
	}

	@Action(value = "/coordinadorConsultoriasPyMEsShow", results = { @Result(name = "success", location = "coordinacion.consultorias.pymes.show", type = "tiles") })
	public String coordinadorConsultoriasPyMEsShow()
			throws BaseBusinessException {
		log.debug("coordinadorConsultoriasPyMEsShow()");
		setMenu(1);

		return SUCCESS;
	}

	@Action(value = "/coordinadorConsultoriasSolicitudesShow", results = { @Result(name = "success", location = "coordinacion.consultorias.solicitudes.show", type = "tiles") })
	public String coordinadorConsultoriasSolicitudesShow()
			throws BaseBusinessException {
		log.debug("coordinadorConsultoriasSolicitudesShow()");
		setMenu(2);

		return SUCCESS;
	}

	@Action(value = "/coordinadorConsultoriasReportesShow", results = { @Result(name = "success", location = "coordinacion.consultorias.reportes.show", type = "tiles") })
	public String coordinadorConsultoriasReportesShow()
			throws BaseBusinessException {
		log.debug("coordinadorConsultoriasReportesShow()");
		setMenu(3);

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
