/*
 * CoordinadorDiplomadosAction.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.action;

import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.service.CoordinadorDiplomadosService;
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
@Namespaces({ @Namespace(value = "diplomados/coordinacion") })
public class CoordinadorDiplomadosAction extends AbstractBaseAction {

	private int menu = 1;
	private static final String[] op = { "DIPLOMADOS", "ENCUESTAS", "PyMEs",
			"REPORTES" };
	private static final String[] fr = {
			"coordinadorDiplomadosDiplomadosShow.do",
			"coordinadorDiplomadosEncuestasShow.do",
			"coordinadorDiplomadosPyMEsShow.do",
			"coordinadorDiplomadosReportesShow.do", "", "" };

	private CoordinadorDiplomadosService coordinadorDiplomadosService;

	public void setCoordinadorDiplomadosService(
			CoordinadorDiplomadosService coordinadorDiplomadosService) {
		this.coordinadorDiplomadosService = coordinadorDiplomadosService;
	}

	@Action(value = "/coordinadorDiplomadosDiplomadosShow", results = { @Result(name = "success", location = "coordinacion.diplomados.diplomados.show", type = "tiles") })
	public String coordinadorDiplomadosDiplomadosShow()
			throws BaseBusinessException {
		log.debug("coordinadorDiplomadosDiplomadosShow()");
		setMenu(1);

		return SUCCESS;
	}

	@Action(value = "/coordinadorDiplomadosEncuestasShow", results = { @Result(name = "success", location = "coordinacion.diplomados.encuestas.show", type = "tiles") })
	public String coordinadorDiplomadosEncuestasShow()
			throws BaseBusinessException {
		log.debug("coordinadorDiplomadosEncuestasShow()");
		setMenu(2);

		return SUCCESS;
	}

	@Action(value = "/coordinadorDiplomadosPyMEsShow", results = { @Result(name = "success", location = "coordinacion.diplomados.pymes.show", type = "tiles") })
	public String coordinadorDiplomadosPyMEsShow() throws BaseBusinessException {
		log.debug("coordinadorDiplomadosPyMEsShow()");
		setMenu(3);

		return SUCCESS;
	}

	@Action(value = "/coordinadorDiplomadosReportesShow", results = { @Result(name = "success", location = "coordinacion.diplomados.reportes.show", type = "tiles") })
	public String coordinadorDiplomadosReportesShow()
			throws BaseBusinessException {
		log.debug("coordinadorDiplomadosReportesShow()");
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
