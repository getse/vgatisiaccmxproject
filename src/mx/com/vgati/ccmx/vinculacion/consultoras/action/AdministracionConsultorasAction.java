/*
 * AdministracionConsultorasAction.java        01/04/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.action;

import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.service.ConsultorasService;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.framework.action.AbstractBaseAction;
import mx.com.vgati.framework.exception.BaseBusinessException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.Result;

/**
 * 
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
@Namespaces({ @Namespace(value = "consultor/administracion") })
public class AdministracionConsultorasAction extends AbstractBaseAction {

	private int menu = 1;
	private static final String[] op = { "MI INFORMACI&Oacute;N",
			"CONSULTORES", "PyMEs", "INDICADORES", "CONTABILIDAD", "REPORTES" };
	private static final String[] fr = { "consultoraInformacionShow.do",
			"consultoraConsultoresShow.do", "consultoraPyMEsShow.do",
			"consultoraIndicadoresShow.do", "consultoraContabilidadShow.do",
			"consultoraReportesShow.do" };

	private ConsultorasService consultorasService;
	private InitService initService;
	private Consultoras consultoras;

	public void setConsultorasService(ConsultorasService consultorasService) {
		this.consultorasService = consultorasService;
	}

	public void setInitService(InitService initService) {
		this.initService = initService;
	}

	@Action(value = "/consultoraInformacionShow", results = { @Result(name = "success", location = "consultoras.administracion.datos.show", type = "tiles") })
	public String consultoraInformacionShow() throws BaseBusinessException {
		log.debug("consultoraInformacionShow()");
		setMenu(1);

		Usuario u = getUsuario();
		log.debug("Usuario=" + u);

		return SUCCESS;
	}

	@Action(value = "/consultoraInformacionAdd", results = { @Result(name = "success", location = "consultoras.administracion.datos.show", type = "tiles") })
	public String consultoraInformacionAdd() {
		log.debug("consultoraInformacionAdd()");
		setMenu(1);

		return SUCCESS;
	}

	@Action(value = "/consultoraConsultoresShow", results = {
			@Result(name = "success", location = "consultoras.administracion.consultores.show", type = "tiles"),
			@Result(name = "input", location = "consultoras.administracion.consultores.show", type = "tiles"),
			@Result(name = "error", location = "consultoras.administracion.consultores.show", type = "tiles") })
	public String consultoraConsultoresShow() {
		log.debug("consultoraConsultoresShow()");
		setMenu(2);

		return SUCCESS;
	}

	@Action(value = "/consultoraConsultoresAdd", results = { @Result(name = "success", location = "consultoras.administracion.consultores.add", type = "tiles") })
	public String consultoraConsultoresAdd() {
		log.debug("consultoraConsultoresAdd()");
		setMenu(2);
		return SUCCESS;
	}

	@Action(value = "/consultoraPyMEsShow", results = { @Result(name = "success", location = "consultoras.administracion.pymes.list", type = "tiles") })
	public String consultoraPyMEsShow() {
		log.debug("consultoraPyMEsShow()");
		setMenu(3);
		return SUCCESS;
	}

	@Action(value = "/consultoraIndicadoresShow", results = { @Result(name = "success", location = "consultoras.administracion.indicadores.show", type = "tiles") })
	public String consultoraIndicadoresShow() {
		log.debug("consultoraIndicadoresShow()");
		setMenu(4);

		return SUCCESS;
	}

	@Action(value = "/consultoraContabilidadShow", results = { @Result(name = "success", location = "consultoras.administracion.contabilidad.show", type = "tiles") })
	public String consultoraContabilidadShow() {
		log.debug("consultoraContabilidadShow()");
		setMenu(5);

		return SUCCESS;
	}

	@Action(value = "/consultoraReportesShow", results = { @Result(name = "success", location = "consultoras.administracion.reportes.show", type = "tiles") })
	public String consultoraReportesShow() {
		log.debug("consultoraReportesShow()");
		setMenu(6);
		return SUCCESS;
	}

	@Action(value = "/consultoraReportesAdd", results = { @Result(name = "success", location = "consultoras.administracion.reportes.add", type = "tiles") })
	public String consultoraReportesAdd() {
		log.debug("consultoraReportesAdd()");
		setMenu(6);
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

	public Consultoras getTractoras() {
		return consultoras;
	}

	public void setConsultoras(Consultoras consultoras) {
		this.consultoras = consultoras;
	}

}
