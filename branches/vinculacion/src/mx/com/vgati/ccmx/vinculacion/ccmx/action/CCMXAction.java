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

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMeNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMesNoObtenidasException;
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
@Namespaces({ @Namespace(value = "ccmx/administracion") })
public class CCMXAction extends AbstractBaseAction {

	private static final long serialVersionUID = -6132513079633432961L;
	private int menu = 1;
	private static final String[] op = { "TRACTORAS", "CONSULTOR&Iacute;AS",
			"PyMEs", "DIPLOMADOS", "REPORTES" };
	private static final String[] fr = { "tractorasShow.do",
			"consultoriasShow.do", "PyMEsShow.do", "diplomadosShow.do",
			"reportesShow.do" };

	private CCMXService ccmxService;
	private InitService initService;
	private Tractoras tractoras;
	private List<Tractoras> listTractoras;
	private Mensaje mensaje;
	private PyMEs pyMes;
	private List<PyMEs> listPyMes;

	public void setCcmxService(CCMXService ccmxService) {
		this.ccmxService = ccmxService;
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
		if (tractoras != null) {
			tractoras.setPassword(ValidationUtils.getNext(4));
			log.debug("guardando el usuario, tractora:" + tractoras);
			ccmxService.saveUsuarioTra(tractoras);
			log.debug("guardando rol");
			ccmxService.saveRolTra(tractoras);
			log.debug("guardando la Tractora:" + tractoras);
			Usuario u = initService
					.getUsuario(tractoras.getCorreoElectronico());
			tractoras.setIdUsuario(u.getIdUsuario());
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
		}
		return SUCCESS;
	}

	@Action(value = "/tractoraAdd", results = { @Result(name = "success", location = "ccmx.administracion.tractoras.add", type = "tiles") })
	public String tractoraAdd() throws TractorasNoObtenidasException {
		log.debug("tractoraAdd()");
		setMenu(1);
		return SUCCESS;
	}

	@Action(value = "/consultoriasShow", results = { @Result(name = "success", location = "ccmx.administracion.consultorias.list", type = "tiles") })
	public String consultoriasShow() {
		log.debug("consultoriasShow()");
		setMenu(2);
		return SUCCESS;
	}

	@Action(value = "/consultoriaAdd", results = { @Result(name = "success", location = "ccmx.administracion.consultorias.add", type = "tiles") })
	public String consultoriaAdd() {
		log.debug("consultoriaAdd()");
		setMenu(2);
		return SUCCESS;
	}

	@Action(value = "/PyMEsShow", results = {
			@Result(name = "success", location = "ccmx.administracion.pymes.list", type = "tiles"),
			@Result(name = "input", location = "ccmx.administracion.pymes.list", type = "tiles"),
			@Result(name = "error", location = "ccmx.administracion.pymes.list", type = "tiles") })
	public String PyMEsShow() throws PyMeNoAlmacenadaException,
			UsuarioNoObtenidoException {
		log.debug("PyMEsShow()");
		setMenu(3);
		if (pyMes != null) {
			pyMes.setPassword(ValidationUtils.getNext(4));
			log.debug("guardando el usuario, pyme:" + pyMes);
			ccmxService.saveUsuarioPyMe(pyMes);
			log.debug("guardando rol");
			ccmxService.saveRolPyMe(pyMes);
			log.debug("guardando PyMe:" + pyMes);
			Usuario u = initService.getUsuario(pyMes.getCorreoElectronico());
			Usuario up = (Usuario) sessionMap.get("Usuario");
			pyMes.setIdUsuario(u.getIdUsuario());
			pyMes.setIdUsuarioPadre(up.getIdUsuario());
			setMensaje(ccmxService.savePyMe(pyMes));
			// TODO cambiar el texto del correo
			SendEmail envia = new SendEmail(
					pyMes.getCorreoElectronico(),
					"SIA CCMX Registro de usuario Tractora",
					"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado Administrador de "
							+ pyMes.getCorreoElectronico()
							+ ",<br /><br />El Centro de Competitividad de México (CCMX) ha generado tu perfil como Comprador-Administrador de "
							+ pyMes.getCorreoElectronico()
							+ " en el Sistema de Vinculación del CCMX. Recuerda que en este sistema podrás dar de alta a los compradores que podrán buscar productos y servicios que ofrecen las Pequeñas y Medianas Empresas (PYMES) de México. Además, podrán ver sus datos de contacto, sus principales productos, sus principales clientes; así como indicadores sobre su desempeño en experiencias de compra con otras grandes empresas.<br /><br />En este sistema también podrán dar de alta sus requerimientos para que las PYMES con registro en este sistema puedan enviarles cotizaciones o presupuestos.<br /><br />Los accesos para el Sistema de Vinculación son los siguientes:<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><a href='http://50.56.213.202:8080/vinculacion/inicio.do'>http://50.56.213.202:8080/vinculacion/inicio.do</a><br />Usuario: "
							+ pyMes.getCorreoElectronico()
							+ "<br />Contraseña: "
							+ pyMes.getPassword()
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

	public PyMEs getPyMes() {
		return pyMes;
	}

	public void setPyMes(PyMEs pyMes) {
		this.pyMes = pyMes;
	}

	public List<PyMEs> getListPyMes() throws PyMesNoObtenidasException {
		Usuario u = (Usuario) sessionMap.get("Usuario");
		setListPyMes(ccmxService.getPyMe(u.getIdUsuario()));
		return listPyMes;
	}

	public void setListPyMes(List<PyMEs> listPyMes) {
		this.listPyMes = listPyMes;
	}
}