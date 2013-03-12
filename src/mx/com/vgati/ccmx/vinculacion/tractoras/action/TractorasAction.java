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

import java.sql.Date;
import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.exception.UsuarioNoValidadoException;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Productos;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.CompradoresNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.ProductosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoEliminadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.service.TractorasService;
import mx.com.vgati.framework.action.AbstractBaseAction;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;
import mx.com.vgati.framework.util.Null;

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
	private InitService initService;
	private List<Requerimientos> listRequerimientos;
	private Requerimientos requerimientos;
	private List<Productos> listProductos;
	private Tractoras tractoras;
	private Domicilios domicilios;
	private Mensaje mensaje;
	private String lugares;
	private String busqueda;
	private String resultados;
	private String cve;
	private Date fechaSuministro;

	public void setTractorasService(TractorasService tractorasService) {
		this.tractorasService = tractorasService;
	}

	public void setInitService(InitService initService) {
		this.initService = initService;
	}

	@Action(value = "/addDat", results = { @Result(name = "success", location = "tractora.datos.add", type = "tiles") })
	public String addDat() {
		return SUCCESS;
	}

	@Action(value = "/showDat", results = { @Result(name = "success", location = "tractora.datos.show", type = "tiles") })
	public String showDat() throws DomiciliosNoAlmacenadosException,
			NumberFormatException, CompradoresNoObtenidosException {
		log.debug("showDatAdm()");
		setMenu(1);

		Usuario u = (Usuario) sessionMap.get("Usuario");
		log.debug("Usuario=" + u);
		setTractoras(tractorasService.getTractora(u.getIdUsuario()));
		String idDom = tractorasService.getIdDomicilio(u.getIdUsuario());
		log.debug("idDomicilio=" + idDom);
		setDomicilios(tractorasService.getDomicilio(Integer.parseInt(idDom)));
		log.debug("domicilio=" + domicilios);

		return SUCCESS;
	}
	
	@Action(value = "/saveDat", results = { @Result(name = "success", location = "tractora.datos.show", type = "tiles") })
	public String saveDat() throws TractorasNoAlmacenadasException,
			DomiciliosNoAlmacenadosException, CompradoresNoObtenidosException {
		log.debug("saveDat()");
		setMenu(1);

		if (tractoras != null) {
			log.debug("Actualizando los datos de la tractora" + tractoras);
			tractoras.setIdUsuario(((Usuario) sessionMap.get("Usuario"))
					.getIdUsuario());
			setMensaje(tractorasService.updateTractoras(tractoras));
		}
		if (domicilios != null && domicilios.getIdDomicilio() == 0) {
			log.debug("Insertando el domicilio" + domicilios);
			setMensaje(tractorasService.insertDomicilio(domicilios));
			log.debug("Insertando id's");
			log.debug("mensaje=" + mensaje);
			domicilios.setIdDomicilio(Integer
					.parseInt(mensaje != null ? mensaje.getId() : "0"));
			tractoras.setIdUsuario(((Usuario) sessionMap.get("Usuario"))
					.getIdUsuario());
			setMensaje(tractorasService.insertRelDomicilio(domicilios,
					tractoras));
		} else if (domicilios != null) {
			log.debug("Actualizando el domicilio" + domicilios);
			setMensaje(tractorasService.updateDomicilio(domicilios));
		}

		return SUCCESS;
	}

	@Action(value = "/listReq", results = {
			@Result(name = "success", location = "tractora.requerimientos.list", type = "tiles"),
			@Result(name = "input", location = "tractora.requerimientos.add", type = "tiles") })
	public String listReq() {
		log.debug("listReq()");
		setMenu(2);
		return SUCCESS;
	}

	@Action(value = "/save", results = {
			@Result(name = "success", location = "tractora.requerimientos.list", type = "tiles"),
			@Result(name = "input", location = "tractora.requerimientos.add", type = "tiles"),
			@Result(name = "error", location = "tractora.requerimientos.add", type = "tiles") })
	public String save() throws RequerimientosNoObtenidosException,
			RequerimientosNoAlmacenadosException {
		log.debug("save()");
		setMenu(2);
		log.debug("requerimientos=" + requerimientos);
		log.debug("busqueda=" + busqueda);
		log.debug("fechaSuministro=" + fechaSuministro);
		requerimientos.setFechaSuministro(fechaSuministro);
		if (requerimientos != null && requerimientos.getIdRequerimiento() == 0) {
			log.debug("guardando el requerimiento:" + requerimientos);
			requerimientos.setIdTractora(((Usuario) sessionMap.get("Usuario"))
					.getIdUsuario());
			setMensaje(tractorasService.insertRequerimiento(requerimientos));
		} else if (requerimientos != null) {
			log.debug("actualizando el requerimiento:" + requerimientos);
			setMensaje(tractorasService.updateRequerimiento(requerimientos));
		}
		return SUCCESS;
	}

	@Action(value = "/addReq", results = {
			@Result(name = "success", location = "tractora.requerimientos.add", type = "tiles"),
			@Result(name = "input", location = "tractora.requerimientos.add", type = "tiles") })
	public String addReq() throws RequerimientosNoObtenidosException {
		log.debug("addReq()");
		setMenu(2);
		log.debug("requerimientos=" + requerimientos);
		log.debug("busqueda=" + busqueda);
		if (requerimientos != null && requerimientos.getIdRequerimiento() != 0
				&& Null.free(busqueda).isEmpty()) {
			log.debug("requerimientos=" + requerimientos);
			setRequerimientos(tractorasService.getRequerimiento(String
					.valueOf(getRequerimientos().getIdRequerimiento())));
		}
		return SUCCESS;
	}

	@Action(value = "/showReq", results = {
			@Result(name = "success", location = "tractora.requerimientos.show", type = "tiles"),
			@Result(name = "input", location = "tractora.requerimientos.show", type = "tiles") })
	public String showReq() throws RequerimientosNoObtenidosException {
		log.debug("showReq()");
		setMenu(2);
		log.debug("requerimientos=" + requerimientos);
		return SUCCESS;
	}

	@Action(value = "/deleteReq", results = {
			@Result(name = "success", location = "tractora.requerimientos.list", type = "tiles"),
			@Result(name = "input", location = "tractora.requerimientos.list", type = "tiles"),
			@Result(name = "error", location = "tractora.requerimientos.list", type = "tiles"),
			@Result(name = "invalid", location = "tractora.requerimientos.list", type = "tiles") })
	public String deleteReq() throws RequerimientosNoObtenidosException,
			RequerimientosNoEliminadosException, UsuarioNoValidadoException {
		log.debug("deleteReq()");
		setMenu(2);
		Usuario u = (Usuario) sessionMap.get("Usuario");
		log.debug("Usuario=" + u);
		if (!initService.validateUsuario(cve, u.getIdUsuario())) {
			Mensaje mensaje = new Mensaje(1,
					"La contraseña no es correcta, intente de nuevo");
			setMensaje(mensaje);
			return "invalid";
		}
		log.debug("requerimientos=" + requerimientos);
		setMensaje(tractorasService.deleteRequerimiento(requerimientos));
		return SUCCESS;
	}

	@Action(value = "/showPro", results = {
			@Result(name = "success", location = "tractora.requerimientos.productos.show", type = "tiles"),
			@Result(name = "input", location = "tractora.requerimientos.productos.show", type = "tiles") })
	public String showPro() throws ProductosNoObtenidosException {
		log.debug("showPro()");
		setMenu(2);
		log.debug("resultados=" + resultados);
		if (requerimientos != null && Null.free(resultados).equals("false")) {
			log.debug("requerimientos=" + requerimientos);
			setListProductos(tractorasService.getProductos(requerimientos
					.getBusqueda()));
			log.debug("requerimientos=" + requerimientos);
		}
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
		log.debug("Usuario=" + u);
		setListRequerimientos(tractorasService.getRequerimientos(u
				.getIdUsuario()));
		log.debug("");
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

	public void setTractoras(Tractoras tractoras) {
		this.tractoras = tractoras;
	}

	public Tractoras getTractoras() {
		return tractoras;
	}

	public Domicilios getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(Domicilios domicilios) {
		this.domicilios = domicilios;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setLugares(String lugares) {
		this.lugares = lugares;
	}

	public String getLugares() {
		return lugares;
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

	public void setResultados(String resultados) {
		this.resultados = resultados;
	}

	public String getResultados() {
		return resultados;
	}

	public String getCve() {
		return cve;
	}

	public void setCve(String cve) {
		this.cve = cve;
	}

	public void setFechaSuministro(Date fechaSuministro) {
		this.fechaSuministro = fechaSuministro;
	}

	public Date getFechaSuministro() {
		return fechaSuministro;
	}

}
