/*
 * ConsultorasAction.java        01/04/2013
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
@Namespaces({ @Namespace(value = "consultor") })
public class ConsultorasAction extends AbstractBaseAction {

	private int menu = 1;
	private static final String[] op = { "MI INFORMACI&Oacute;N",
			"PyMEs ASIGNADAS", "INDICADORES" };
	private static final String[] fr = { "consultorInformacionShow.do",
			"consultorPyMEsShow.do", "consultorIndicadoresShow.do" };

	private ConsultorasService consultorasService;
	private InitService initService;
	private Consultoras consultoras;

	public void setConsultorasService(ConsultorasService consultorasService) {
		this.consultorasService = consultorasService;
	}

	public void setInitService(InitService initService) {
		this.initService = initService;
	}

	@Action(value = "/consultorInformacionShow", results = { @Result(name = "success", location = "consultora.datos.show", type = "tiles") })
	public String consultorInformacionShow() {
		log.debug("consultorInformacionShow()");
		setMenu(1);

		Usuario u = (Usuario) sessionMap.get("Usuario");
		log.debug("Usuario=" + u);

		return SUCCESS;
	}

	@Action(value = "/consultorInformacionAdd", results = { @Result(name = "success", location = "consultora.datos.show", type = "tiles") })
	public String consultorInformacionAdd() {
		log.debug("consultorInformacionAdd()");
		setMenu(1);

		return SUCCESS;
	}

	@Action(value = "/consultorPyMEsShow", results = { @Result(name = "success", location = "consultora.pymes.list", type = "tiles") })
	public String consultorPyMEsShow() {
		log.debug("consultorPyMEsShow");
		setMenu(2);
		return SUCCESS;
	}

	@Action(value = "/consultorIndicadoresShow", results = { @Result(name = "success", location = "consultora.indicadores.show", type = "tiles") })
	public String consultorIndicadoresShow() {
		log.debug("consultorIndicadoresShow");
		setMenu(3);
		return SUCCESS;
	}

	@Action(value = "/indicadorAdd", results = { @Result(name = "success", location = "consultora.indicadores.add", type = "tiles") })
	public String indicadorAdd() {
		log.debug("indicadorAdd");
		setMenu(3);
		return SUCCESS;
	}

	@Action(value = "/indicadorShow", results = { @Result(name = "success", location = "consultora.indicadores.show", type = "tiles") })
	public String indicadorShow() {
		log.debug("indicadorShow");
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

	public Consultoras getConsultoras() {
		return consultoras;
	}

	public void setConsultoras(Consultoras consultoras) {
		this.consultoras = consultoras;
	}

}
