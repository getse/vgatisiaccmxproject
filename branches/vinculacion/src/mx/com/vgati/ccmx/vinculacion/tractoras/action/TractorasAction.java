/*
 * TractorasAction.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.tractoras.action;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Productos;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.ProductosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.service.TractorasService;
import mx.com.vgati.framework.action.AbstractBaseAction;
import mx.com.vgati.framework.dto.Requerimientos;

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
@Namespaces({ @Namespace(value = "tractora/datos"),
		@Namespace(value = "tractora/compradores"),
		@Namespace(value = "tractora/requerimientos"),
		@Namespace(value = "tractora/busquedas"),
		@Namespace(value = "tractora/reportes") })
public class TractorasAction extends AbstractBaseAction {

	private static final long serialVersionUID = 3043240220537679175L;
	private int menu = 1;
	private static final String[] op = { "MI INFORMACI&Oacute;N",
			"REQUERIMIENTOS", "B&Uacute;SQUEDAS", "PyMEs", "INDICADORES" };
	private static final String[] fr = { "showDat.do", "listReq.do",
			"showBus.do", "listPyM.do", "listInd.do" };

	private TractorasService tractorasService;
	private List<Requerimientos> listRequerimientos;
	private Requerimientos requerimientos;
	private List<Productos> listProductos;
	private String busqueda;

	public void setTractorasService(TractorasService tractorasService) {
		this.tractorasService = tractorasService;
	}

	@Action(value = "/addDat", results = { @Result(name = "success", location = "tractora.datos.add", type = "tiles") })
	public String addDat() {
		return SUCCESS;
	}

	@Action(value = "/showDat", results = { @Result(name = "success", location = "tractora.datos.show", type = "tiles") })
	public String showDat() {
		return SUCCESS;
	}

	@Action(value = "/listReq", results = { @Result(name = "success", location = "tractora.requerimientos.list", type = "tiles") })
	public String listReq() throws RequerimientosNoObtenidosException,
			RequerimientosNoAlmacenadosException {
		log.debug("listReq()");
		setMenu(2);
		if (requerimientos != null && requerimientos.getIdRequerimiento() == 0) {
			log.debug("guardando el requerimiento:" + requerimientos);
			tractorasService.insertRequerimiento(requerimientos);
		} else if (requerimientos != null) {
			log.debug("actualizando el requerimiento:" + requerimientos);
			tractorasService.updateRequerimiento(requerimientos);
		}
		return SUCCESS;
	}

	@Action(value = "/addReq", results = { @Result(name = "success", location = "tractora.requerimientos.add", type = "tiles") })
	public String addReq() throws RequerimientosNoObtenidosException {
		log.debug("addReq()");
		setMenu(2);
		if (requerimientos != null && requerimientos.getIdRequerimiento() != 0) {
			String busqueda = requerimientos.getTipoProducto();
			log.debug("requerimientos=" + requerimientos);
			setRequerimientos(tractorasService.getRequerimiento(String
					.valueOf(getRequerimientos().getIdRequerimiento())));
			requerimientos.setTipoProducto(busqueda);
		}
		return SUCCESS;
	}

	@Action(value = "/showProd", results = { @Result(name = "success", location = "tractora.requerimientos.productos.show", type = "tiles") })
	public String showProd() throws ProductosNoObtenidosException {
		log.debug("showProd()");
		setMenu(2);
		String busqueda = requerimientos.getTipoProducto();
		if (requerimientos != null) {
			requerimientos.setTipoProducto(busqueda);
			log.debug("tipoProducto=" + requerimientos.getTipoProducto());
			setListProductos(tractorasService.getProductos(busqueda));
		}
		return SUCCESS;
	}

	@Action(value = "/showReq", results = { @Result(name = "success", location = "tractora.requerimientos.show", type = "tiles") })
	public String showReq() {
		return SUCCESS;
	}

	@Action(value = "/showPro", results = { @Result(name = "success", location = "tractora.producto.show", type = "tiles") })
	public String showPro() {
		return SUCCESS;
	}

	@Action(value = "/showBus", results = { @Result(name = "success", location = "tractora.busqueda.show", type = "tiles") })
	public String showBus() {
		log.debug("showBus()");
		return SUCCESS;
	}

	@Action(value = "/listPyM", results = { @Result(name = "success", location = "tractora.pymes.list", type = "tiles") })
	public String listPyM() {
		return SUCCESS;
	}

	@Action(value = "/listInd", results = { @Result(name = "success", location = "tractora.indicadores.list", type = "tiles") })
	public String listInd() {
		return SUCCESS;
	}

	@Action(value = "/addInd", results = { @Result(name = "success", location = "tractora.indicadores.add", type = "tiles") })
	public String addInd() {
		return SUCCESS;
	}

	@Action(value = "/showInd", results = { @Result(name = "success", location = "tractora.indicadores.show", type = "tiles") })
	public String showInd() {
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

	public List<Requerimientos> getListRequerimientos()
			throws RequerimientosNoObtenidosException {
		Usuario u = (Usuario) sessionMap.get("Usuario");
		setListRequerimientos(tractorasService.getRequerimientos(u
				.getIdUsuario()));
		return listRequerimientos;
	}

	public void setListRequerimientos(List<Requerimientos> listRequerimientos) {
		this.listRequerimientos = listRequerimientos;
	}

	public Requerimientos getRequerimientos() {
		return requerimientos;
	}

	public void setRequerimientos(Requerimientos requerimientos) {
		this.requerimientos = requerimientos;
	}

	public void setListProductos(List<Productos> listProductos) {
		this.listProductos = listProductos;
	}

	public List<Productos> getListProductos() {
		return listProductos;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public String getBusqueda() {
		return busqueda;
	}

}
