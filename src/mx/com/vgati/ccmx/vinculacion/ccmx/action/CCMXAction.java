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

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.ccmx.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.ConsultoraNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.ConsultorasNoObtenidasExceprion;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.service.CCMXService;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.exception.UsuarioNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMENoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.CompradoresNoObtenidosException;
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
 * 
 * @author Getsemani Correa
 * 
 */
@Namespaces({ @Namespace(value = "ccmx/administracion") })
public class CCMXAction extends AbstractBaseAction {

	private static final long serialVersionUID = -6132513079633432961L;
	private int menu = 1;
	private static final String[] op = { "TRACTORAS", "EMPRESAS CONSULTORAS",
			"PyMEs", "DIPLOMADOS", "REPORTES" };
	private static final String[] fr = { "tractorasShow.do",
			"consultorasShow.do", "PyMEsShow.do", "diplomadosShow.do",
			"reportesShow.do" };

	private CCMXService ccmxService;
	private TractorasService tractorasService;
	private InitService initService;
	private Tractoras tractoras;
	private List<Tractoras> listTractoras;
	private Mensaje mensaje;
	private PyMEs pyMEs;
	private List<PyMEs> listPyMEs;
	private Consultoras consultoras;
	private List<Consultoras> listConsultoras;
	private String credenciales;

	public void setCcmxService(CCMXService ccmxService) {
		this.ccmxService = ccmxService;
	}

	public void setTractorasService(TractorasService tractorasService) {
		this.tractorasService = tractorasService;
	}

	public void setInitService(InitService initService) {
		this.initService = initService;
	}

	@Action(value = "/tractorasShow", results = {
			@Result(name = "success", location = "ccmx.administracion.tractoras.list", type = "tiles"),
			@Result(name = "input", location = "ccmx.administracion.tractoras.list", type = "tiles"),
			@Result(name = "error", location = "ccmx.administracion.tractoras.list", type = "tiles") })
	public String tractorasShow() throws UsuarioNoObtenidoException,
			TractorasNoAlmacenadasException {
		log.debug("tractorasShow()");
		setMenu(1);
		if (tractoras != null && tractoras.getIdUsuario() == 0) {
			tractoras.setPassword(ValidationUtils.getNext(4));
			log.debug("guardando el usuario, tractora:" + tractoras);
			ccmxService.saveUsuarioTractora(tractoras);
			log.debug("guardando rol");
			ccmxService.saveRolTractora(tractoras);
			log.debug("guardando la Tractora:" + tractoras);
			Usuario u = initService
					.getUsuario(tractoras.getCorreoElectronico());
			tractoras.setIdUsuario(u.getIdUsuario());
			Usuario up = (Usuario) sessionMap.get("Usuario");
			tractoras.setIdUsuarioPadre(up.getIdUsuario());
			setMensaje(ccmxService.saveTractora(tractoras));
			if (mensaje.getRespuesta() == 0) {
				log.debug("Enviando correo electrónico:"
						+ tractoras.getCorreoElectronico());
				SendEmail envia = new SendEmail(
						tractoras.getCorreoElectronico(),
						"SIA CCMX Registro de usuario Tractora",
						"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado Administrador de "
								+ tractoras.getEmpresa()
								+ ",<br /><br />El Centro de Competitividad de México (CCMX) ha generado tu perfil como Comprador-Administrador de "
								+ tractoras.getEmpresa()
								+ " en el Sistema de Vinculación del CCMX. Recuerda que en este sistema podrás dar de alta a los compradores que podrán buscar productos y servicios que ofrecen las Pequeñas y Medianas Empresas (PYMES) de México. Además, podrán ver sus datos de contacto, sus principales productos, sus principales clientes; así como indicadores sobre su desempeño en experiencias de compra con otras grandes empresas.<br /><br />En este sistema también podrán dar de alta sus requerimientos para que las PYMES con registro en este sistema puedan enviarles cotizaciones o presupuestos.<br /><br />Los accesos para el Sistema de Vinculación son los siguientes:<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><a href='http://50.56.213.202:8080/vinculacion/inicio.do'>http://50.56.213.202:8080/vinculacion/inicio.do</a><br />Usuario: "
								+ tractoras.getCorreoElectronico()
								+ "<br />Contraseña: "
								+ tractoras.getPassword()
								+ "</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br />Esperamos que tu experiencia con el Sistema de Vinculación sea excelente y en caso de cualquier duda sobre la operación y funcionamiento del sistema, no dudes en ponerte en contacto con andres.blancas@ccmx.org.mx.<br /><br />Muchas gracias por utilizar el sistema de vinculación del CCMX.<br />Centro de Competitividad de México</h5>");
				log.debug("Enviando correo electrónico:" + envia);
			}
		} else if (tractoras != null && tractoras.getIdUsuario() != 0) {
			log.debug("actualizando tractora:" + tractoras);
			Usuario u = initService.getUsuario(credenciales);
			tractoras.setPassword(initService.getCredenciales(u.getIdUsuario())
					.getCredenciales());
			tractoras.setIdUsuario(u.getIdUsuario());
			setMensaje(ccmxService.updateTractora(tractoras, credenciales));
			if (mensaje.getRespuesta() == 0) {
				log.debug("Enviando correo electrónico:"
						+ tractoras.getCorreoElectronico());
				SendEmail envia = new SendEmail(
						tractoras.getCorreoElectronico(),
						"SIA CCMX Registro de usuario Tractora",
						"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado Administrador de "
								+ tractoras.getEmpresa()
								+ ",<br /><br />El Centro de Competitividad de México (CCMX) ha generado tu perfil como Comprador-Administrador de "
								+ tractoras.getEmpresa()
								+ " en el Sistema de Vinculación del CCMX. Recuerda que en este sistema podrás dar de alta a los compradores que podrán buscar productos y servicios que ofrecen las Pequeñas y Medianas Empresas (PYMES) de México. Además, podrán ver sus datos de contacto, sus principales productos, sus principales clientes; así como indicadores sobre su desempeño en experiencias de compra con otras grandes empresas.<br /><br />En este sistema también podrán dar de alta sus requerimientos para que las PYMES con registro en este sistema puedan enviarles cotizaciones o presupuestos.<br /><br />Los accesos para el Sistema de Vinculación son los siguientes:<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><a href='http://50.56.213.202:8080/vinculacion/inicio.do'>http://50.56.213.202:8080/vinculacion/inicio.do</a><br />Usuario: "
								+ tractoras.getCorreoElectronico()
								+ "<br />Contraseña: "
								+ tractoras.getPassword()
								+ "</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br />Esperamos que tu experiencia con el Sistema de Vinculación sea excelente y en caso de cualquier duda sobre la operación y funcionamiento del sistema, no dudes en ponerte en contacto con andres.blancas@ccmx.org.mx.<br /><br />Muchas gracias por utilizar el sistema de vinculación del CCMX.<br />Centro de Competitividad de México</h5>");
				log.debug("Enviando correo electrónico:" + envia);
			}
		}
		return SUCCESS;
	}

	@Action(value = "/tractoraAdd", results = { @Result(name = "success", location = "ccmx.administracion.tractoras.add", type = "tiles") })
	public String tractoraAdd() throws TractorasNoObtenidasException,
			CompradoresNoObtenidosException {
		log.debug("tractoraAdd()");
		setMenu(1);

		if (tractoras != null && tractoras.getIdUsuario() != 0)
			setTractoras(tractorasService.getTractora(tractoras.getIdUsuario()));
		return SUCCESS;
	}

	@Action(value = "/consultorasShow", results = { @Result(name = "success", location = "ccmx.administracion.consultoras.list", type = "tiles") })
	public String consultorasShow() throws ConsultoraNoAlmacenadaException,
			UsuarioNoObtenidoException {
		log.debug("consultorasShow()");
		setMenu(2);

		if (consultoras != null) {
			consultoras.setPassword(ValidationUtils.getNext(4));
			Usuario up = (Usuario) sessionMap.get("Usuario");
			consultoras.setIdUsuarioPadre(up.getIdUsuario());
			log.debug("guardando el usuario, consultora:" + consultoras);
			ccmxService.saveUsuarioConsultora(consultoras);
			log.debug("guardando rol");
			ccmxService.saveRolConsultora(consultoras);
			Usuario u = initService.getUsuario(consultoras
					.getCorreoElectronico());
			consultoras.setIdUsuario(u.getIdUsuario());
			log.debug("guardando Consultora:" + consultoras);
			setMensaje(ccmxService.saveConsultora(consultoras));
			// TODO cambiar el texto del correo
			SendEmail envia = new SendEmail(
					consultoras.getCorreoElectronico(),
					"SIA CCMX Registro de usuario TEMP",
					"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado Administrador de "
							+ consultoras.getCorreoElectronico()
							+ ",<br /><br />El Centro de Competitividad de México (CCMX) ha generado tu perfil como Comprador-Administrador de "
							+ consultoras.getCorreoElectronico()
							+ " en el Sistema de Vinculación del CCMX. Recuerda que en este sistema podrás dar de alta a los compradores que podrán buscar productos y servicios que ofrecen las Pequeñas y Medianas Empresas (PYMES) de México. Además, podrán ver sus datos de contacto, sus principales productos, sus principales clientes; así como indicadores sobre su desempeño en experiencias de compra con otras grandes empresas.<br /><br />En este sistema también podrán dar de alta sus requerimientos para que las PYMES con registro en este sistema puedan enviarles cotizaciones o presupuestos.<br /><br />Los accesos para el Sistema de Vinculación son los siguientes:<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><a href='http://50.56.213.202:8080/vinculacion/inicio.do'>http://50.56.213.202:8080/vinculacion/inicio.do</a><br />Usuario: "
							+ consultoras.getCorreoElectronico()
							+ "<br />Contraseña: "
							+ consultoras.getPassword()
							+ "</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br />Esperamos que tu experiencia con el Sistema de Vinculación sea excelente y en caso de cualquier duda sobre la operación y funcionamiento del sistema, no dudes en ponerte en contacto con andres.blancas@ccmx.org.mx.<br /><br />Muchas gracias por utilizar el sistema de vinculación del CCMX.<br />Centro de Competitividad de México</h5>");
			log.debug("Enviando correo electrónico:" + envia);

		}

		return SUCCESS;
	}

	@Action(value = "/consultoraAdd", results = { @Result(name = "success", location = "ccmx.administracion.consultoras.add", type = "tiles") })
	public String consultoraAdd() {
		log.debug("consultoraAdd()");
		setMenu(2);
		return SUCCESS;
	}

	@Action(value = "/PyMEsShow", results = {
			@Result(name = "success", location = "ccmx.administracion.pymes.list", type = "tiles"),
			@Result(name = "input", location = "ccmx.administracion.pymes.list", type = "tiles"),
			@Result(name = "error", location = "ccmx.administracion.pymes.list", type = "tiles") })
	public String PyMEsShow() throws PyMENoAlmacenadaException,
			UsuarioNoObtenidoException {
		log.debug("PyMEsShow()");
		setMenu(3);
		if (pyMEs != null) {
			pyMEs.setPassword(ValidationUtils.getNext(4));
			log.debug("guardando el usuario, pyme:" + pyMEs);
			ccmxService.saveUsuarioPyME(pyMEs);
			log.debug("guardando rol");
			ccmxService.saveRolPyME(pyMEs);
			log.debug("guardando PyME:" + pyMEs);
			Usuario u = initService.getUsuario(pyMEs.getCorreoElectronico());
			Usuario up = (Usuario) sessionMap.get("Usuario");
			pyMEs.setIdUsuario(u.getIdUsuario());
			pyMEs.setIdUsuarioPadre(up.getIdUsuario());
			setMensaje(ccmxService.savePyME(pyMEs));
			// TODO cambiar el texto del correo
			SendEmail envia = new SendEmail(
					pyMEs.getCorreoElectronico(),
					"SIA CCMX Registro de usuario Tractora",
					"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado Administrador de "
							+ pyMEs.getCorreoElectronico()
							+ ",<br /><br />El Centro de Competitividad de México (CCMX) ha generado tu perfil como Comprador-Administrador de "
							+ pyMEs.getCorreoElectronico()
							+ " en el Sistema de Vinculación del CCMX. Recuerda que en este sistema podrás dar de alta a los compradores que podrán buscar productos y servicios que ofrecen las Pequeñas y Medianas Empresas (PYMES) de México. Además, podrán ver sus datos de contacto, sus principales productos, sus principales clientes; así como indicadores sobre su desempeño en experiencias de compra con otras grandes empresas.<br /><br />En este sistema también podrán dar de alta sus requerimientos para que las PYMES con registro en este sistema puedan enviarles cotizaciones o presupuestos.<br /><br />Los accesos para el Sistema de Vinculación son los siguientes:<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><a href='http://50.56.213.202:8080/vinculacion/inicio.do'>http://50.56.213.202:8080/vinculacion/inicio.do</a><br />Usuario: "
							+ pyMEs.getCorreoElectronico()
							+ "<br />Contraseña: "
							+ pyMEs.getPassword()
							+ "</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br />Esperamos que tu experiencia con el Sistema de Vinculación sea excelente y en caso de cualquier duda sobre la operación y funcionamiento del sistema, no dudes en ponerte en contacto con andres.blancas@ccmx.org.mx.<br /><br />Muchas gracias por utilizar el sistema de vinculación del CCMX.<br />Centro de Competitividad de México</h5>");
			log.debug("Enviando correo electrónico:" + envia);

		}
		return SUCCESS;
	}

	@Action(value = "/PyMEAdd", results = { @Result(name = "success", location = "ccmx.administracion.pymes.add", type = "tiles") })
	public String PyMEAdd() {
		log.debug("PyMEAdd()");
		setMenu(3);
		return SUCCESS;
	}

	@Action(value = "/PyMEShow", results = { @Result(name = "success", location = "ccmx.administracion.pymes.show", type = "tiles") })
	public String PyMEShow() {
		log.debug("PyMEShow()");
		setMenu(3);
		return SUCCESS;
	}

	@Action(value = "/diplomadosShow", results = { @Result(name = "success", location = "ccmx.administracion.diplomados.list", type = "tiles") })
	public String diplomadosShow() {
		log.debug("diplomadosShow()");
		setMenu(4);
		return SUCCESS;
	}

	@Action(value = "/diplomadoAdd", results = { @Result(name = "success", location = "ccmx.administracion.diplomados.add", type = "tiles") })
	public String diplomadoAdd() {
		log.debug("diplomadoAdd()");
		setMenu(4);
		return SUCCESS;
	}

	@Action(value = "/diplomadoShow", results = { @Result(name = "success", location = "ccmx.administracion.diplomados.show", type = "tiles") })
	public String diplomadoShow() {
		log.debug("diplomadoShow");
		setMenu(4);
		return SUCCESS;
	}

	@Action(value = "/reportesShow", results = { @Result(name = "success", location = "ccmx.administracion.reportes.list", type = "tiles") })
	public String reportesShow() {
		log.debug("reportesShow()");
		setMenu(5);
		return SUCCESS;
	}

	@Action(value = "/reporteShow", results = { @Result(name = "success", location = "ccmx.administracion.reportes.show", type = "tiles") })
	public String reporteShow() {
		log.debug("reporteShow()");
		setMenu(5);
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

	public PyMEs getPyMEs() {
		return pyMEs;
	}

	public void setPyMEs(PyMEs pyMEs) {
		this.pyMEs = pyMEs;
	}

	public Consultoras getConsultoras() {
		return consultoras;
	}

	public void setConsultoras(Consultoras consultoras) {
		this.consultoras = consultoras;
	}

	public List<PyMEs> getListPyMEs() throws PyMEsNoObtenidasException {
		Usuario u = (Usuario) sessionMap.get("Usuario");
		setListPyMEs(ccmxService.getPyME(u.getIdUsuario()));
		return listPyMEs;
	}

	public void setListPyMEs(List<PyMEs> listPyMEs) {
		this.listPyMEs = listPyMEs;
	}

	public List<Consultoras> getListConsultoras()
			throws ConsultorasNoObtenidasExceprion {
		Usuario u = (Usuario) sessionMap.get("Usuario");
		setListConsultoras(ccmxService.getConsultoras(u.getIdUsuario()));
		return listConsultoras;
	}

	public void setListConsultoras(List<Consultoras> listConsultoras) {
		this.listConsultoras = listConsultoras;
	}

	public String getCredenciales() {
		return credenciales;
	}

	public void setCredenciales(String credenciales) {
		this.credenciales = credenciales;
	}

}