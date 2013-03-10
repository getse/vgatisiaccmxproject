/*
 * CCMXAction.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.ccmx.action;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.service.CCMXService;
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
@Namespaces({ @Namespace(value = "ccmx/administracion/tractoras"),
	@Namespace(value = "ccmx/administracion/consultorias"),
	@Namespace(value = "ccmx/administracion/pymes"),
	@Namespace(value = "ccmx/administracion/diplomados"),
	@Namespace(value = "ccmx/administracion/reportes") })
	public class CCMXAction extends AbstractBaseAction {

	private static final long serialVersionUID = -6132513079633432961L;
	private int menu = 1;
	private static final String[] op = { "TRACTORAS", "CONSULTOR&Iacute;AS",
		"PyMEs", "DIPLOMADOS", "REPORTES" };
	private static final String[] fr = { "showLisTra.do", "showLisCon.do",
		"showLisPym.do", "showLisDip.do", "showLisRep.do" };
	private CCMXService ccmxService;
	private Tractoras tractoras;
	private List<Tractoras> listTractoras;



	public void setCcmxService(CCMXService ccmxService) {
		this.ccmxService = ccmxService;
	}

	@Action(value = "/showLisTra", results = { @Result(name = "success", location = "ccmx.administracion.tractoras.list", type = "tiles") })
	public String showLisTra() throws TractorasNoObtenidasException, TractorasNoAlmacenadasException{
		log.debug("showLisTra()");
		log.debug("tractoras");
		if (tractoras != null){
			log.debug("guardando la Tractora:" + tractoras);
			ccmxService.saveTractora(tractoras);
		}

		return SUCCESS;
	}

	@Action(value = "/addTra", results = { @Result(name = "success", location = "ccmx.administracion.tractoras.add", type = "tiles") })
	public String addTra() throws TractorasNoObtenidasException{
		log.debug("addTra()");
		if (tractoras != null){
			log.debug("Tractoras=" + tractoras);
			setListTractoras(ccmxService.getTractoras());
		}
		return SUCCESS;
	}

	@Action(value = "/showTra", results = { @Result(name = "success", location = "ccmx.administracion.tractoras.show", type = "tiles") })
	public String showTra() {
		return SUCCESS;
	}

	@Action(value = "/showLisCon", results = { @Result(name = "success", location = "ccmx.administracion.consultorias.list", type = "tiles") })
	public String showLisCon() {
		return SUCCESS;
	}

	@Action(value = "/addCon", results = { @Result(name = "success", location = "ccmx.administracion.consultorias.add", type = "tiles") })
	public String addCon() {
		return SUCCESS;
	}

	@Action(value = "/showCon", results = { @Result(name = "success", location = "ccmx.administracion.consultorias.show", type = "tiles") })
	public String showCon() {
		return SUCCESS;
	}

	@Action(value = "/showLisPym", results = { @Result(name = "success", location = "ccmx.administracion.pymes.list", type = "tiles") })
	public String showLisPym() {
		return SUCCESS;
	}

	@Action(value = "/addPym", results = { @Result(name = "success", location = "ccmx.administracion.pymes.add", type = "tiles") })
	public String addPym() {
		return SUCCESS;
	}

	@Action(value = "/showPym", results = { @Result(name = "success", location = "ccmx.administracion.pymes.show", type = "tiles") })
	public String showPym() {
		return SUCCESS;
	}

	@Action(value = "/showLisDip", results = { @Result(name = "success", location = "ccmx.administracion.diplomados.list", type = "tiles") })
	public String showLisDip() {
		return SUCCESS;
	}

	@Action(value = "/addDip", results = { @Result(name = "success", location = "ccmx.administracion.diplomados.add", type = "tiles") })
	public String addDip() {
		return SUCCESS;
	}

	@Action(value = "/showDip", results = { @Result(name = "success", location = "ccmx.administracion.diplomados.show", type = "tiles") })
	public String showDip() {
		return SUCCESS;
	}

	@Action(value = "/showLisRep", results = { @Result(name = "success", location = "ccmx.administracion.reportes.list", type = "tiles") })
	public String showLisRep() {
		return SUCCESS;
	}

	@Action(value = "/showRep", results = { @Result(name = "success", location = "ccmx.administracion.reportes.show", type = "tiles") })
	public String showRep() {
		return SUCCESS;
	}

	public String[] getOp() {
		return op;
	}

	public String[] getFr() {
		return fr;
	}

	public void setMenu(int menu) {
		this.menu = menu;
	}

	public int getMenu() {
		return menu;
	}

	public Tractoras getTractoras() {
		return tractoras;
	}

	public void setTractoras(Tractoras tractoras) {
		this.tractoras = tractoras;
	}

	public List<Tractoras> getListTractoras() throws TractorasNoObtenidasException{
		setListTractoras(ccmxService.getTractoras());
		return listTractoras;
	}

	public void setListTractoras(List<Tractoras> listTractoras){
		this.listTractoras = listTractoras;
	}

}
