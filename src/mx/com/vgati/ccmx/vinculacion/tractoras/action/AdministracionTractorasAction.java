/*
 * AdministracionTractorasAction.java        01/03/2013
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

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.exception.UsuarioNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.CompradoresNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.CompradoresNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.service.TractorasService;
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
 * @author Getsemani Correa
 * 
 */
@Namespaces({ @Namespace(value = "tractoras/administracion/datos"),
		@Namespace(value = "tractoras/administracion/compradores"),
		@Namespace(value = "tractoras/administracion/requerimientos"),
		@Namespace(value = "tractoras/administracion/busquedas"),
		@Namespace(value = "tractoras/administracion/reportes") })
public class AdministracionTractorasAction extends AbstractBaseAction {

	private static final long serialVersionUID = 6076350949482670437L;
	private int menu = 1;
	private static final String[] op = { "MI INFORMACI&Oacute;N",
			"COMPRADORES", "REQUERIMIENTOS", "B&Uacute;SQUEDAS", "REPORTES" };
	private static final String[] fr = { "showDatAdm.do", "showComAdm.do",
			"showReqAdm.do", "showBusAdm.do", "showRepAdm.do" };

	private TractorasService tractorasService;
	private InitService initService;
	private Tractoras tractoras;
	private Domicilios domicilios;
	private List<Tractoras> listCompradores;
	private Mensaje mensaje;

	public void setTractorasService(TractorasService tractorasService) {
		this.tractorasService = tractorasService;
	}

	public void setInitService(InitService initService) {
		this.initService = initService;
	}

	@Action(value = "/addDatAdm", results = { @Result(name = "success", location = "tractoras.administracion.datos.add", type = "tiles") })
	public String addDatAdm() {
		return SUCCESS;
	}

	@Action(value = "/showDatAdm", results = { @Result(name = "success", location = "tractoras.administracion.datos.show", type = "tiles") })
	public String showDatAdm() throws TractorasNoAlmacenadasException, DomiciliosNoAlmacenadosException {
		log.debug("showDatAdm");
		if (tractoras != null) {
			log.debug("Actualizando los datos de la tractora" + tractoras );
			tractoras.setIdUsuario(((Usuario) sessionMap.get("Usuario")).getIdUsuario());
			setMensaje(tractorasService.updateTractoras(tractoras));
		}
		if (domicilios != null && domicilios.getIdDomicilio() == 0) {
			log.debug("Insertando el domicilio" + domicilios);
			setMensaje(tractorasService.insertDomicilio(domicilios));
			log.debug("Insertando id's");
			setMensaje(tractorasService.insertRelDomicilio(domicilios, tractoras));
		}else if(domicilios != null){
			log.debug("Actualizando el domicilio" + domicilios);
			setMensaje(tractorasService.updateDomicilio(domicilios));
		}
		return SUCCESS;
	}

	@Action(value = "/addComAdm", results = { @Result(name = "success", location = "tractoras.administracion.compradores.add", type = "tiles") })
	public String addComAdm() {
		log.debug("addComAdm()");
		setMenu(2);
		return SUCCESS;
	}

	@Action(value = "/showComAdm", results = {
			@Result(name = "success", location = "tractoras.administracion.compradores.show", type = "tiles"),
			@Result(name = "input", location = "tractoras.administracion.compradores.show", type = "tiles"),
			@Result(name = "error", location = "tractoras.administracion.compradores.show", type = "tiles") })
	public String showComAdm() throws CompradoresNoAlmacenadosException,
			UsuarioNoObtenidoException, CompradoresNoObtenidosException {
		log.debug("showComAdm()");
		setMenu(2);
		if (tractoras != null) {
			log.debug("guardando el usuario, comprador:" + tractoras);
			tractorasService.saveUsuarioComp(tractoras);
			log.debug("guardando rol");
			tractorasService.saveRolComp(tractoras);
			log.debug("guardando la Tractora:" + tractoras);
			Usuario uP = (Usuario) sessionMap.get("Usuario");
			Usuario u = initService
					.getUsuario(tractoras.getCorreoElectronico());
			tractoras.setIdUsuario(u.getIdUsuario());
			tractoras.setIdTractoraPadre(uP.getIdUsuario());
			setMensaje(tractorasService.saveComprador(tractoras));
			log.debug("Enviando correo electrónico:"
					+ tractoras.getCorreoElectronico());

			ValidationUtils v = new ValidationUtils();
			Usuario usuario = (Usuario) sessionMap.get("Usuario");
			Tractoras t = tractorasService.getTractora(usuario.getIdUsuario());
			SendEmail envia = new SendEmail(
					tractoras.getCorreoElectronico(),
					"SIA CCMX Registro de usuario Comprador",
					"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado "
							+ tractoras.getNombreContacto()
							+ ",<br /><br />El administrador de compras de "
							+ t.getEmpresa()
							+ " en el Sistema de Vinculación, "
							+ t.getNombreContacto()
							+ " ,te ha dado de alta como comprador con accesos para utilizar dicho sistema. Para ingresar da click en el siguiente vínculo y confirma tus datos:<br /><br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><a href='https://50.56.213.202:8181/vinculacion/inicio.do'>https://50.56.213.202:8181/vinculacion/inicio.do</a><br /><br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Recuerda que en dicha plataforma podrás realizar búsquedas de proveedores, así como subir requerimientos y recibir cotizaciones. Para que sea más sencillo de utilizar, el sistema cuenta con alertas de forma que su monitoreo se reduzca al mínimo.<br />Los accesos al sistema son los siguientes:<br /><br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Usuario: "
							+ tractoras.getCorreoElectronico()
							+ "<br />Contraseña: "
							+ v.getPasswd()
							+ "</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br /><br />En caso de cualquier duda sobre la operación y funcionamiento del sistema, no dudes en ponerte en contacto con sistemadevinculacion@ccmx.org.mx.<br /><br />Muchas gracias por utilizar el sistema de vinculación del CCMX.<br />Centro de Competitividad de México</h5>");
			log.debug("Enviando correo electrónico:" + envia);
		}
		return SUCCESS;
	}

	@Action(value = "/addReqAdm", results = { @Result(name = "success", location = "tractoras.administracion.requerimientos.add", type = "tiles") })
	public String addReqAdm() {
		return SUCCESS;
	}

	@Action(value = "/showReqAdm", results = { @Result(name = "success", location = "tractoras.administracion.requerimientos.show", type = "tiles") })
	public String showReqAdm() {
		return SUCCESS;
	}

	@Action(value = "/addBusAdm", results = { @Result(name = "success", location = "tractoras.administracion.busquedas.add", type = "tiles") })
	public String addBusAdm() {
		return SUCCESS;
	}

	@Action(value = "/showBusAdm", results = { @Result(name = "success", location = "tractoras.administracion.busquedas.show", type = "tiles") })
	public String showBusAdm() {
		return SUCCESS;
	}

	@Action(value = "/addRepAdm", results = { @Result(name = "success", location = "tractoras.administracion.reportes.add", type = "tiles") })
	public String addRepAdm() {
		return SUCCESS;
	}

	@Action(value = "/showRepAdm", results = { @Result(name = "success", location = "tractoras.administracion.reportes.show", type = "tiles") })
	public String showRepAdm() {
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

	public List<Tractoras> getListCompradores()
			throws CompradoresNoObtenidosException {
		Usuario u = (Usuario) sessionMap.get("Usuario");
		setListCompradores(tractorasService.getCompradores(u.getIdUsuario()));
		return listCompradores;
	}

	public void setListCompradores(List<Tractoras> listCompradores) {
		this.listCompradores = listCompradores;
	}

	public Tractoras getTractoras() {
		return tractoras;
	}

	public void setTractoras(Tractoras tractoras) {
		this.tractoras = tractoras;
	}
	
	public Domicilios getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(Domicilios domicilios) {
		this.domicilios = domicilios;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

}
