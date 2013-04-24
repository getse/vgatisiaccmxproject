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

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoAlmacenadoException;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.exception.UsuarioNoValidadoException;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.CatScianCcmx;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
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
@SuppressWarnings("serial")
@Namespaces({ @Namespace(value = "comprador") })
public class TractorasAction extends AbstractBaseAction {

	private int menu = 1;
	private static final String[] op = { "MI INFORMACI&Oacute;N",
			"REQUERIMIENTOS", "B&Uacute;SQUEDAS", "PyMEs", "INDICADORES" };
	private static final String[] fr = { "compradorInformacionShow.do",
			"compradorRequerimientosShow.do", "compradorBusquedaShow.do",
			"compradorPyMEsShow.do", "compradorIndicadoresShow.do" };

	private TractorasService tractorasService;
	private InitService initService;
	private List<Requerimientos> listRequerimientos;
	private Requerimientos requerimientos;
	private List<CatScianCcmx> listCatProductos;
	private Tractoras tractoras;
	private Domicilios domicilios;
	private Mensaje mensaje;
	private String lugares;
	private String cve;
	private Date fechaSuministro;
	private Date fechaExpira;
	private int idArchivo;
	private String nameArchivo;
	private String mimeArchivo;
	private InputStream archivo;
	private String init;

	public void setTractorasService(TractorasService tractorasService) {
		this.tractorasService = tractorasService;
	}

	public void setInitService(InitService initService) {
		this.initService = initService;
	}

	@Action(value = "/compradorInformacionShow", results = {
			@Result(name = "success", location = "tractora.datos.show", type = "tiles"),
			@Result(name = "busqueda", location = "tractora.busqueda.show", type = "tiles") })
	public String compradorInformacionShow()
			throws DomiciliosNoAlmacenadosException, NumberFormatException,
			CompradoresNoObtenidosException {
		log.debug("compradorInformacionShow()");

		Usuario u = (Usuario) sessionMap.get("Usuario");
		log.debug("Usuario=" + u);
		setTractoras(tractorasService.getTractora(u.getIdUsuario()));
		String idDom = tractorasService.getIdDomicilio(u.getIdUsuario());
		log.debug("idDomicilio=" + idDom);
		setDomicilios(tractorasService.getDomicilio(Integer.parseInt(idDom)));
		log.debug("domicilio=" + domicilios);
		if (Null.free(init).equals("1")
				&& !Null.free(tractoras.getPuesto()).isEmpty()) {
			setMenu(3);
			return "busqueda";
		}

		setMenu(1);
		return SUCCESS;
	}

	@Action(value = "/compradorInformacionAdd", results = { @Result(name = "success", location = "tractora.datos.show", type = "tiles") })
	public String compradorInformacionAdd()
			throws TractorasNoAlmacenadasException,
			DomiciliosNoAlmacenadosException, CompradoresNoObtenidosException {
		log.debug("compradorInformacionAdd()");
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

		Usuario u = (Usuario) sessionMap.get("Usuario");
		log.debug("Usuario=" + u);
		setTractoras(tractorasService.getTractora(u.getIdUsuario()));

		return SUCCESS;
	}

	@Action(value = "/compradorRequerimientosShow", results = {
			@Result(name = "success", location = "tractora.requerimientos.list", type = "tiles"),
			@Result(name = "input", location = "tractora.requerimientos.add", type = "tiles") })
	public String compradorRequerimientosShow() {
		log.debug("compradorRequerimientosShow()");
		setMenu(2);
		return SUCCESS;
	}

	@Action(value = "/compradorRequerimientoSave", results = {
			@Result(name = "success", location = "tractora.requerimientos.add", type = "tiles"),
			@Result(name = "input", location = "tractora.requerimientos.add", type = "tiles"),
			@Result(name = "error", location = "tractora.requerimientos.add", type = "tiles") })
	public String compradorRequerimientoSave()
			throws RequerimientosNoObtenidosException,
			RequerimientosNoAlmacenadosException,
			DocumentoNoAlmacenadoException {
		log.debug("compradorRequerimientoSave()");
		setMenu(2);

		log.debug("requerimientos=" + requerimientos);
		log.debug("fechaSuministro=" + fechaSuministro);
		log.debug("fechaExpira=" + fechaExpira);
		requerimientos.setFechaSuministro(fechaSuministro);
		requerimientos.setFechaExpira(fechaExpira);
		if (requerimientos != null && requerimientos.getIdRequerimiento() == 0) {
			log.debug("guardando el requerimiento:" + requerimientos);
			requerimientos.setIdTractora(((Usuario) sessionMap.get("Usuario"))
					.getIdUsuario());
			setMensaje(tractorasService.insertRequerimiento(requerimientos));
		} else if (requerimientos != null) {
			log.debug("actualizando el requerimiento:" + requerimientos);
			setMensaje(tractorasService.updateRequerimiento(requerimientos));
		}
		setRequerimientos(tractorasService.getRequerimiento(getMensaje()
				.getId()));
		return SUCCESS;
	}

	@Action(value = "/compradorRequerimientoAdd", results = {
			@Result(name = "success", location = "tractora.requerimientos.add", type = "tiles"),
			@Result(name = "input", location = "tractora.requerimientos.add", type = "tiles") })
	public String compradorRequerimientoAdd()
			throws RequerimientosNoObtenidosException {
		log.debug("compradorRequerimientoAdd()");
		setMenu(2);
		log.debug("requerimientos=" + requerimientos);
		if (requerimientos != null && requerimientos.getIdRequerimiento() != 0) {
			log.debug("requerimientos=" + requerimientos);
			setRequerimientos(tractorasService.getRequerimiento(String
					.valueOf(getRequerimientos().getIdRequerimiento())));
		}
		return SUCCESS;
	}

	@Action(value = "/compradorRequerimientoDelete", results = {
			@Result(name = "success", location = "tractora.requerimientos.list", type = "tiles"),
			@Result(name = "input", location = "tractora.requerimientos.list", type = "tiles"),
			@Result(name = "error", location = "tractora.requerimientos.list", type = "tiles"),
			@Result(name = "invalid", location = "tractora.requerimientos.list", type = "tiles") })
	public String compradorRequerimientoDelete()
			throws RequerimientosNoObtenidosException,
			RequerimientosNoEliminadosException, UsuarioNoValidadoException {
		log.debug("compradorRequerimientoDelete()");
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

	@Action(value = "/compradorBusquedaShow", results = { @Result(name = "success", location = "tractora.busqueda.show", type = "tiles") })
	public String compradorBusquedaShow() {
		log.debug("compradorBusquedaShow()");
		setMenu(3);
		return SUCCESS;
	}

	@Action(value = "/compradorPyMEsShow", results = { @Result(name = "success", location = "tractora.pymes.list", type = "tiles") })
	public String compradorPyMEsShow() {
		log.debug("compradorPyMEsShow");
		setMenu(4);
		return SUCCESS;
	}

	@Action(value = "/compradorIndicadoresShow", results = { @Result(name = "success", location = "tractora.indicadores.list", type = "tiles") })
	public String compradorIndicadoresShow() {
		log.debug("compradorIndicadoresShow");
		setMenu(5);
		return SUCCESS;
	}

	@Action(value = "/indicadorAdd", results = { @Result(name = "success", location = "tractora.indicadores.add", type = "tiles") })
	public String addindicadorAddInd() {
		log.debug("indicadorAdd");
		setMenu(5);
		return SUCCESS;
	}

	@Action(value = "/indicadorShow", results = { @Result(name = "success", location = "tractora.indicadores.show", type = "tiles") })
	public String indicadorShow() {
		log.debug("indicadorShow");
		setMenu(5);
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

	public List<CatScianCcmx> getListCatProductos()
			throws ProductosNoObtenidosException {
		if (listCatProductos == null) {
			setListCatProductos(tractorasService.getCatProductos(null));
		}
		return listCatProductos;
	}

	public void setListCatProductos(List<CatScianCcmx> listCatProductos) {
		this.listCatProductos = listCatProductos;
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

	public void setFechaExpira(Date fechaExpira) {
		this.fechaExpira = fechaExpira;
	}

	public Date getFechaExpira() {
		return fechaExpira;
	}

	public int getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(int idArchivo) {
		this.idArchivo = idArchivo;
	}

	public String getNameArchivo() {
		return nameArchivo;
	}

	public void setNameArchivo(String nameArchivo) {
		this.nameArchivo = nameArchivo;
	}

	public String getMimeArchivo() {
		return mimeArchivo;
	}

	public void setMimeArchivo(String mimeArchivo) {
		this.mimeArchivo = mimeArchivo;
	}

	public InputStream getArchivo() {
		return archivo;
	}

	public void setArchivo(InputStream archivo) {
		this.archivo = archivo;
	}

	public String getInit() {
		return init;
	}

	public void setInit(String init) {
		this.init = init;
	}

	@Action(value = "/showDoc", results = {
			@Result(name = "success", type = "stream", params = { "inputName",
					"archivo", "contentType", "mimeArchivo",
					"contentDisposition",
					"attachment;filename=\"${nameArchivo}\"" }),
			@Result(name = "input", location = "tractora.requerimientos.add", type = "tiles"),
			@Result(name = "error", location = "tractora.requerimientos.add", type = "tiles") })
	public String showDoc() throws DocumentoNoObtenidoException,
			RequerimientosNoObtenidosException {
		log.debug("showDoc()");
		setArchivo(tractorasService.getArchivo(idArchivo).getIs());

		log.debug("archivo=" + archivo);
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		return SUCCESS;
	}

}
