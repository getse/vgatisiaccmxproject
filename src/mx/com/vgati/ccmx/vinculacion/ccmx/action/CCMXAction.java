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
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.exception.UsuarioNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.framework.action.AbstractBaseAction;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.util.SendEmail;
import mx.com.vgati.framework.util.ValidationUtils;

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
	private InitService initService;
	private Tractoras tractoras;
	private List<Tractoras> listTractoras;
	private Mensaje mensaje;

	public void setCcmxService(CCMXService ccmxService) {
		this.ccmxService = ccmxService;
	}

	public void setInitService(InitService initService) {
		this.initService = initService;
	}

	@Action(value = "/showLisTra", results = { @Result(name = "success", location = "ccmx.administracion.tractoras.list", type = "tiles") })
	public String showLisTra() {
		log.debug("showLisTra()");
		log.debug("Aquí está la tractora inicial" + tractoras);
		return SUCCESS;
	}

	@Action(value = "/addTra", results = { @Result(name = "success", location = "ccmx.administracion.tractoras.add", type = "tiles") })
	public String addTra() throws TractorasNoObtenidasException {
		log.debug("addTra()");
		return SUCCESS;
	}

	@Action(value = "/showTra", results = {
			@Result(name = "success", location = "ccmx.administracion.tractoras.list", type = "tiles"),
			@Result(name = "input", location = "ccmx.administracion.tractoras.list", type = "tiles"),
			@Result(name = "error", location = "ccmx.administracion.tractoras.list", type = "tiles") })
	public String showTra() throws TractorasNoAlmacenadasException,
			UsuarioNoObtenidoException {
		if (tractoras != null) {
			log.debug("guardando el usuario, tractora:"
					+ tractoras);
			ccmxService.saveUsuarioTra(tractoras);
			log.debug("guardando rol");
			ccmxService.saveRolTra(tractoras);
			log.debug("guardando la Tractora:" + tractoras);
			Usuario u = initService
					.getUsuario(tractoras.getCorreoElectronico());
			tractoras.setIdUsuario(u.getIdUsuario());
			setMensaje(ccmxService.saveTractora(tractoras));
			log.debug("Enviando correo electrónico:"
					+ tractoras.getCorreoElectronico());

			ValidationUtils v = new ValidationUtils();
			SendEmail envia = new SendEmail(
					tractoras.getCorreoElectronico(),
					"Alta de Usuario Tractora",
					"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado Administrador de "
							+ tractoras.getEmpresa()
							+ ",<br /><br />El Centro de Competitividad de México (CCMX) ha generado tu perfil como Comprador-Administrador de "
							+ tractoras.getEmpresa()
							+ " en el Sistema de Vinculación del CCMX. Recuerda que en este sistema podrás dar de alta a los compradores que podrán buscar productos y servicios que ofrecen las Pequeñas y Medianas Empresas (PYMES) de México. Además, podrán ver sus datos de contacto, sus principales productos, sus principales clientes; así como indicadores sobre su desempeño en experiencias de compra con otras grandes empresas.<br /><br />En este sistema también podrán dar de alta sus requerimientos para que las PYMES con registro en este sistema puedan enviarles cotizaciones o presupuestos.<br /><br />Los accesos para el Sistema de Vinculación son los siguientes:<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><a href='https://50.56.213.202:8181/vinculacion/inicio.do'>https://50.56.213.202:8181/vinculacion/inicio.do</a><br />Usuario: "
							+ tractoras.getCorreoElectronico()
							+ "<br />Contraseña: "
							+ v.getPasswd()
							+ "</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br />Esperamos que tu experiencia con el Sistema de Vinculación sea excelente y en caso de cualquier duda sobre la operación y funcionamiento del sistema, no dudes en ponerte en contacto con andres.blancas@ccmx.org.mx.<br /><br />Muchas gracias por utilizar el sistema de vinculación del CCMX.<br />Centro de Competitividad de México</h5>");
			log.debug("Enviando correo electrónico:" + envia);
		}

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

	public List<Tractoras> getListTractoras()
			throws TractorasNoObtenidasException {
		Usuario u = (Usuario) sessionMap.get("Usuario");
		setListTractoras(ccmxService.getTractoras(u.getIdUsuario()));
		return listTractoras;
	}

	public void setListTractoras(List<Tractoras> listTractoras) {
		this.listTractoras = listTractoras;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

}
