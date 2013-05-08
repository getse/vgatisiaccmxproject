/*
 * CCMXAction.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.ccmx.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.service.CCMXService;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.exception.ConsultoraNoObtenidaException;
import mx.com.vgati.ccmx.vinculacion.consultoras.service.ConsultorasService;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Certificaciones;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.DiplomadosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.service.PyMEsService;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXFinanzas;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXParticipantes;
import mx.com.vgati.ccmx.vinculacion.report.dto.Filtros;
import mx.com.vgati.ccmx.vinculacion.report.dto.PYMESReporte;
import mx.com.vgati.ccmx.vinculacion.report.dto.TotalEmpresas;
import mx.com.vgati.ccmx.vinculacion.report.service.ReportService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.CompradoresNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.service.TractorasService;
import mx.com.vgati.framework.action.AbstractBaseAction;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.exception.BaseBusinessException;
import mx.com.vgati.framework.util.SendEmail;
import mx.com.vgati.framework.util.ValidationUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


import org.apache.struts2.ServletActionContext;
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
	private ConsultorasService consultorasService;
	private InitService initService;
	private PyMEsService pyMEsService;
	private ReportService reportService;
	private Tractoras tractoras;
	private List<Tractoras> listTractoras;
	private List<Diplomados> listDiplomados;
	private Mensaje mensaje;
	private PyMEs pyMEs;
	private List<PyMEs> listPyMEs;
	private Consultoras consultoras;
	private List<Consultoras> listConsultoras;
	private String credenciales;
	private int idUsuario;
	private Certificaciones certificaciones;
	private Domicilios domicilios;
	private int idArchivo;
	private String nameArchivo;
	private String mimeArchivo;
	private InputStream archivo;
	private List<mx.com.vgati.ccmx.vinculacion.report.dto.Consultoras> consultorasList;
	private List<Tractoras> tractorasList;
	private List<CCMXParticipantes > serviciosList;
	private String opcion;
	private Filtros filtros;
	private String salida;
	
	

	public void setCcmxService(CCMXService ccmxService) {
		this.ccmxService = ccmxService;
	}

	public void setTractorasService(TractorasService tractorasService) {
		this.tractorasService = tractorasService;
	}

	public void setConsultorasService(ConsultorasService consultorasService) {
		this.consultorasService = consultorasService;
	}

	public void setInitService(InitService initService) {
		this.initService = initService;
	}

	public void setPyMEsService(PyMEsService pyMEsService) {
		this.pyMEsService = pyMEsService;
	}

	@Action(value = "/tractorasShow", results = {
			@Result(name = "success", location = "ccmx.administracion.tractoras.list", type = "tiles"),
			@Result(name = "input", location = "ccmx.administracion.tractoras.list", type = "tiles"),
			@Result(name = "error", location = "ccmx.administracion.tractoras.list", type = "tiles") })
	public String tractorasShow() throws BaseBusinessException {
		log.debug("tractorasShow()");
		setMenu(1);
		Usuario up = getUsuario();
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
			tractoras.setIdUsuarioPadre(up.getIdUsuario());
			setMensaje(ccmxService.saveTractora(tractoras));
			if (mensaje.getRespuesta() == 0) {
				log.debug("Enviando correo electr�nico:"
						+ tractoras.getCorreoElectronico());
				SendEmail envia = new SendEmail(
						tractoras.getCorreoElectronico(),
						"SIA CCMX Registro de usuario Tractora",
						"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado Administrador de "
								+ tractoras.getEmpresa()
								+ ",<br /><br />El Centro de Competitividad de M�xico (CCMX) ha generado tu perfil como Comprador-Administrador de "
								+ tractoras.getEmpresa()
								+ " en el Sistema de Vinculaci�n del CCMX. Recuerda que en este sistema podr�s dar de alta a los compradores que podr�n buscar productos y servicios que ofrecen las Peque�as y Medianas Empresas (PYMES) de M�xico. Adem�s, podr�n ver sus datos de contacto, sus principales productos, sus principales clientes; as� como indicadores sobre su desempe�o en experiencias de compra con otras grandes empresas.<br /><br />En este sistema tambi�n podr�n dar de alta sus requerimientos para que las PYMES con registro en este sistema puedan enviarles cotizaciones o presupuestos.<br /><br />Los accesos para el Sistema de Vinculaci�n son los siguientes:<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><a href='http://50.56.213.202:8080/vinculacion/inicio.do'>http://50.56.213.202:8080/vinculacion/inicio.do</a><br />Usuario: "
								+ tractoras.getCorreoElectronico()
								+ "<br />Contrase�a: "
								+ tractoras.getPassword()
								+ "</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br />Esperamos que tu experiencia con el Sistema de Vinculaci�n sea excelente y en caso de cualquier duda sobre la operaci�n y funcionamiento del sistema, no dudes en ponerte en contacto con andres.blancas@ccmx.org.mx.<br /><br />Muchas gracias por utilizar el sistema de vinculaci�n del CCMX.<br />Centro de Competitividad de M�xico</h5>");
				log.debug("Enviando correo electr�nico:" + envia);
			}
		} else if (tractoras != null && tractoras.getIdUsuario() != 0) {
			log.debug("actualizando tractora:" + tractoras);
			Usuario u = initService.getUsuario(credenciales);
			tractoras.setPassword(initService.getCredenciales(u.getIdUsuario())
					.getCredenciales());
			tractoras.setIdUsuario(u.getIdUsuario());
			setMensaje(ccmxService.updateTractora(tractoras, credenciales));
			if (mensaje.getRespuesta() == 0) {
				log.debug("Enviando correo electr�nico:"
						+ tractoras.getCorreoElectronico());
				SendEmail envia = new SendEmail(
						tractoras.getCorreoElectronico(),
						"SIA CCMX Registro de usuario Tractora",
						"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado Administrador de "
								+ tractoras.getEmpresa()
								+ ",<br /><br />El Centro de Competitividad de M�xico (CCMX) ha generado tu perfil como Comprador-Administrador de "
								+ tractoras.getEmpresa()
								+ " en el Sistema de Vinculaci�n del CCMX. Recuerda que en este sistema podr�s dar de alta a los compradores que podr�n buscar productos y servicios que ofrecen las Peque�as y Medianas Empresas (PYMES) de M�xico. Adem�s, podr�n ver sus datos de contacto, sus principales productos, sus principales clientes; as� como indicadores sobre su desempe�o en experiencias de compra con otras grandes empresas.<br /><br />En este sistema tambi�n podr�n dar de alta sus requerimientos para que las PYMES con registro en este sistema puedan enviarles cotizaciones o presupuestos.<br /><br />Los accesos para el Sistema de Vinculaci�n son los siguientes:<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><a href='http://50.56.213.202:8080/vinculacion/inicio.do'>http://50.56.213.202:8080/vinculacion/inicio.do</a><br />Usuario: "
								+ tractoras.getCorreoElectronico()
								+ "<br />Contrase�a: "
								+ tractoras.getPassword()
								+ "</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br />Esperamos que tu experiencia con el Sistema de Vinculaci�n sea excelente y en caso de cualquier duda sobre la operaci�n y funcionamiento del sistema, no dudes en ponerte en contacto con andres.blancas@ccmx.org.mx.<br /><br />Muchas gracias por utilizar el sistema de vinculaci�n del CCMX.<br />Centro de Competitividad de M�xico</h5>");
				log.debug("Enviando correo electr�nico:" + envia);
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
	public String consultorasShow() throws BaseBusinessException {
		log.debug("consultorasShow()");
		setMenu(2);

		if (consultoras != null && consultoras.getIdUsuario() == 0) {
			consultoras.setPassword(ValidationUtils.getNext(4));
			Usuario up = getUsuario();
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
					"SIA CCMX Registro de usuario CONSULTORA",
					"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado Administrador de "
							+ consultoras.getCorreoElectronico()
							+ ",<br /><br />El Centro de Competitividad de M�xico (CCMX) ha generado tu perfil como Comprador-Administrador de "
							+ consultoras.getCorreoElectronico()
							+ " en el Sistema de Vinculaci�n del CCMX. Recuerda que en este sistema podr�s dar de alta a los compradores que podr�n buscar productos y servicios que ofrecen las Peque�as y Medianas Empresas (PYMES) de M�xico. Adem�s, podr�n ver sus datos de contacto, sus principales productos, sus principales clientes; as� como indicadores sobre su desempe�o en experiencias de compra con otras grandes empresas.<br /><br />En este sistema tambi�n podr�n dar de alta sus requerimientos para que las PYMES con registro en este sistema puedan enviarles cotizaciones o presupuestos.<br /><br />Los accesos para el Sistema de Vinculaci�n son los siguientes:<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><a href='http://50.56.213.202:8080/vinculacion/inicio.do'>http://50.56.213.202:8080/vinculacion/inicio.do</a><br />Usuario: "
							+ consultoras.getCorreoElectronico()
							+ "<br />Contrase�a: "
							+ consultoras.getPassword()
							+ "</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br />Esperamos que tu experiencia con el Sistema de Vinculaci�n sea excelente y en caso de cualquier duda sobre la operaci�n y funcionamiento del sistema, no dudes en ponerte en contacto con andres.blancas@ccmx.org.mx.<br /><br />Muchas gracias por utilizar el sistema de vinculaci�n del CCMX.<br />Centro de Competitividad de M�xico</h5>");
			log.debug("Enviando correo electr�nico:" + envia);

		} else if (consultoras != null && consultoras.getIdUsuario() != 0) {
			log.debug("actualizando consultora:" + consultoras);
			Usuario u = initService.getUsuario(credenciales);
			consultoras.setPassword(initService.getCredenciales(
					u.getIdUsuario()).getCredenciales());
			consultoras.setIdUsuario(u.getIdUsuario());
			setMensaje(ccmxService.updateConsultora(consultoras, credenciales));
			if (mensaje.getRespuesta() == 0) {
				log.debug("Enviando correo electr�nico:"
						+ consultoras.getCorreoElectronico());
				// TODO cambiar el texto del correo
				SendEmail envia = new SendEmail(
						consultoras.getCorreoElectronico(),
						"SIA CCMX Registro de usuario CONSULTORA",
						"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado Administrador de "
								+ consultoras.getEmpresa()
								+ ",<br /><br />El Centro de Competitividad de M�xico (CCMX) ha generado tu perfil como Comprador-Administrador de "
								+ consultoras.getEmpresa()
								+ " en el Sistema de Vinculaci�n del CCMX. Recuerda que en este sistema podr�s dar de alta a los compradores que podr�n buscar productos y servicios que ofrecen las Peque�as y Medianas Empresas (PYMES) de M�xico. Adem�s, podr�n ver sus datos de contacto, sus principales productos, sus principales clientes; as� como indicadores sobre su desempe�o en experiencias de compra con otras grandes empresas.<br /><br />En este sistema tambi�n podr�n dar de alta sus requerimientos para que las PYMES con registro en este sistema puedan enviarles cotizaciones o presupuestos.<br /><br />Los accesos para el Sistema de Vinculaci�n son los siguientes:<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><a href='http://50.56.213.202:8080/vinculacion/inicio.do'>http://50.56.213.202:8080/vinculacion/inicio.do</a><br />Usuario: "
								+ consultoras.getCorreoElectronico()
								+ "<br />Contrase�a: "
								+ consultoras.getPassword()
								+ "</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br />Esperamos que tu experiencia con el Sistema de Vinculaci�n sea excelente y en caso de cualquier duda sobre la operaci�n y funcionamiento del sistema, no dudes en ponerte en contacto con andres.blancas@ccmx.org.mx.<br /><br />Muchas gracias por utilizar el sistema de vinculaci�n del CCMX.<br />Centro de Competitividad de M�xico</h5>");
				log.debug("Enviando correo electr�nico:" + envia);
			}
		}

		return SUCCESS;
	}

	@Action(value = "/consultoraAdd", results = { @Result(name = "success", location = "ccmx.administracion.consultoras.add", type = "tiles") })
	public String consultoraAdd() throws ConsultoraNoObtenidaException {
		log.debug("consultoraAdd()");
		setMenu(2);

		if (consultoras != null && consultoras.getIdUsuario() != 0)
			setConsultoras(consultorasService.getConsultora(consultoras
					.getIdUsuario()));
		return SUCCESS;
	}

	@Action(value = "/PyMEsShow", results = {
			@Result(name = "success", location = "ccmx.administracion.pymes.list", type = "tiles"),
			@Result(name = "input", location = "ccmx.administracion.pymes.list", type = "tiles"),
			@Result(name = "error", location = "ccmx.administracion.pymes.list", type = "tiles") })
	public String PyMEsShow() throws BaseBusinessException {
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
			Usuario up = getUsuario();
			pyMEs.setIdUsuario(u.getIdUsuario());
			pyMEs.setIdUsuarioPadre(up.getIdUsuario());
			setMensaje(ccmxService.savePyME(pyMEs));
			SendEmail envia = new SendEmail(
					pyMEs.getCorreoElectronico(),
					"SIA CCMX Registro de usuario PyME",
					"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimada "
							+ pyMEs.getNombreComercial()
							+ ",<br /><br />Nos complace informarte que el Centro de Competitividad de M�xico (CCMX) ha dado de alta a tu empresa en el Sistema de Vinculaci�n del CCMX. En este sistema podr�s consultar los requerimientos de las grandes empresas de M�xico y podr�s enviar cotizaciones.<br /><br />"
							+ "Adem�s, tu informaci�n de contacto, as� como de los productos o los servicios que ofreces, estar�n disponibles para que las grandes empresas u otras PYMES que buscan oportunidades de negocio puedan identificarte.<br /><br />"
							+ "Es muy importante que para aprovechar todas las ventajas que tiene este sistema, ingreses con la siguiente cuenta y password para actualizar y completar tu informaci�n.<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Usuario: "
							+ pyMEs.getCorreoElectronico()
							+ "<br />Contrase�a: "
							+ pyMEs.getPassword()
							+ "<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>El v�nculo del Sistema de Vinculaci�n es:</h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><br /><a href='http://50.56.213.202:8080/vinculacion/inicio.do'>http://50.56.213.202:8080/vinculacion/inicio.do</a><br /><br />"
							+ "</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>No olvides actualizar tu perfil si tus datos de contacto han cambiado o si tienes nuevos productos o servicios que ofrecer.<br /><br />"
							+ "En caso de cualquier duda sobre la operaci�n y funcionamiento del sistema, no dudes en ponerte en contacto con sistemadevinculacion@ccmx.org.mx.<br /><br />"
							+ "Muchas gracias por utilizar el sistema de vinculaci�n del CCMX.</h5>");
			log.debug("Enviando correo electr�nico:" + envia);

		}

		if (idUsuario != 0) {
			log.debug("Consultando la PyME");
			setPyMEs(pyMEsService.getPyME(idUsuario));
			log.debug("Usuario=" + idUsuario);
			String idDom = pyMEsService.getIdDomicilio(idUsuario);
			log.debug("idDomicilio=" + idDom);
			setDomicilios(pyMEsService.getDomicilio(Integer.parseInt(idDom)));
			String idCert = pyMEsService.getIdCertificacion(idUsuario);
			log.debug("idCertificacion=" + idCert);
			setCertificaciones(pyMEsService.getCertificacion(Integer
					.parseInt(idCert)));
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

	@SuppressWarnings("unchecked")
	@Action(value = "/reportesShow", results = { @Result(name = "success", location = "ccmx.administracion.reportes.list", type = "tiles"),
			@Result(name = "input", location = "ccmx.administracion.reportes.list", type = "tiles"),
			@Result(name = "error", location = "ccmx.administracion.reportes.list", type = "tiles") })
	public String reportesShow() {
		setMenu(5);		
		if(opcion!= null && opcion.equals("servicios")){
			setOpcion(opcion);
			try {
				setTractorasList(reportService.getTractoras());
				setConsultorasList(reportService.getConsultoras());
			} catch (TractorasNoObtenidasException e) {
				e.printStackTrace();
				log.debug(""+ e.toString()+"\n"+e);
			}
			return SUCCESS;
					
		}else if(opcion!=null && opcion.equals("finanzas")) {
			setOpcion(opcion);
			setConsultorasList(reportService.getConsultoras());
			return SUCCESS;
		}else if (opcion!=null && opcion.equals("pymes")) {
			setOpcion(opcion);
			setConsultorasList(reportService.getConsultoras());	
			return SUCCESS;
		}else if(opcion!=null && opcion.equals("servRepor")){
			setOpcion("descarga");
		    log.debug("Reporte servicios");
			String direccion = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
			Usuario usuario = (Usuario) sessionMap.get("Usuario");
			if(usuario.getRol().equals("Comprador")|| usuario.getRol().endsWith("CompradorAdministrador")){
				filtros.setId(usuario.getIdUsuario());
			}else{
				filtros.setId(-1);
			}
			List<CCMXParticipantes> serviciosList = reportService.getCCMXServicios(filtros);
			if(serviciosList.isEmpty()){
				setSalida("No se encontraron resultados que coincidan con su busqueda");
				return SUCCESS;
			}else{
				setSalida(null);
				JasperDesign design;
				try {
					design = JRXmlLoader.load(
					        (new FileInputStream(direccion +"/jasper/servicios.jrxml")));
					JasperCompileManager.compileReportToFile(design,direccion +"/jasper/reporte"+usuario.getIdUsuario()+".jasper");
		            @SuppressWarnings({ "rawtypes" })
		            Map parameters = new HashMap();
		            parameters.put("SUBREPORT_DIR", direccion +"/jasper/Reportes\\");
		            parameters.put("tCultura", reportService.getTCultura(filtros));
		            parameters.put("tPlaneacion", reportService.getTPlaneacion(filtros));
		            parameters.put("tManufactura", reportService.getTManufactura(filtros));
		            parameters.put("tEstrategia", reportService.getTEstrategia(filtros));
		            parameters.put("empresaControl", 0);
		            parameters.put("radarAntesControl", 0);
		            parameters.put("radarDespuesControl", 0);
		            parameters.put("estatusControl", 0);
		            JasperPrint jasperPrint = JasperFillManager.fillReport(direccion +"/jasper/reporte"+usuario.getIdUsuario()+".jasper", parameters,new JRBeanCollectionDataSource(serviciosList));
		            OutputStream output = new FileOutputStream(new File(direccion +"/jasper/Reporte"+usuario.getIdUsuario()+".xlsx")); 
		            JRXlsxExporter exporterXLS = new JRXlsxExporter();
		            List<JasperPrint> objetos = new ArrayList<JasperPrint>();
		            objetos.add(jasperPrint);
		            log.debug(jasperPrint);
		            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT_LIST, objetos); 
		            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output); 
		            exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
		            exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE); 
		            exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
		            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
		            exporterXLS.exportReport(); 
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (JRException e) {
					e.printStackTrace();
				}/*"WEB-INF\\jasper\\reporte.jrxml"*/
				return SUCCESS;
			}
			
			
		}
		else if(opcion!=null && opcion.equals("finRepor")){
			setOpcion("descarga");
			String direccion = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
			Usuario usuario = (Usuario) sessionMap.get("Usuario");
			if(usuario.getRol().equals("AdministradorConsultor")){
				filtros.setId(usuario.getIdUsuario());
			}
			List<CCMXFinanzas> finanzasList = reportService.getCCMXFiannzas(filtros);
			if (finanzasList.isEmpty()) {
				setSalida("No se encontraron resultados que coincidan con su busqueda");
				return SUCCESS;
			}else {
				setSalida(null);
				try {            
					JasperDesign design = JRXmlLoader.load(
		                    (new FileInputStream(direccion +"/jasper/financiero.jrxml")));/*"WEB-INF\\jasper\\reporte.jrxml"*/
		            JasperCompileManager.compileReportToFile(design,direccion +"/jasper/reporte"+usuario.getIdUsuario()+".jasper");
		            @SuppressWarnings({ "rawtypes" })
		            Map parameters = new HashMap();
		            parameters.put("SUBREPORT_DIR", direccion +"/jasper/Reportes\\");
		            parameters.put("abono1Total", 0);
		            parameters.put("abono2Total", 0);
		            parameters.put("anticipoTotal", 0);
		            parameters.put("finiquitoTotal", 0);
		            parameters.put("empresaPagada", 0);
		            parameters.put("empresaSinPago", 0);
		            parameters.put("facturaTotal", 0);
		            parameters.put("facturaPendiente", 0);
		            JasperPrint jasperPrint = JasperFillManager.fillReport(direccion +"/jasper/reporte"+usuario.getIdUsuario()+".jasper", parameters,new JRBeanCollectionDataSource(finanzasList));
		            OutputStream output = new FileOutputStream(new File(direccion +"/jasper/Reporte"+usuario.getIdUsuario()+".xlsx")); 
		            JRXlsxExporter exporterXLS = new JRXlsxExporter();
		            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint); 
		            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output); 
		            exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
		            exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE); 
		            exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
		            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
		            exporterXLS.exportReport();   
		        } catch (Exception e) {
		            e.printStackTrace();
		            log.debug(e.getCause()+"\n"+e.getMessage()+"\n"+e.toString());
		            return ERROR;
		        }
			}
			return SUCCESS;
		}else if(opcion!=null && opcion.trim().equals("pyRepor")){	
			log.debug("Generando reporte de pymes");
			String direccion = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
			Usuario usuario = (Usuario) sessionMap.get("Usuario");
				if(usuario.getRol().equals("AdmnistradorConsultor")||usuario.getRol().equals("CompradorAdministrador")||usuario.getRol().equals("Comprador")||usuario.getRol().equals("Consultor")){
					filtros.setId(usuario.getIdUsuario());
				if(usuario.getRol().equals("AdmnistradorConsultor")){
					filtros.setPermisos(3);
				}else if (usuario.getRol().equals("CompradorAdministrador")) {
						filtros.setPermisos(1);
				}else if (usuario.getRol().equals("Comprador")) {
					filtros.setPermisos(2);
				}else {
					filtros.setPermisos(4);
				}
			}	
			log.debug(""+filtros);
			List<PYMESReporte> pymesLists= new ArrayList<PYMESReporte>();
			PYMESReporte temp;
			List<PYMESReporte> pymesList = reportService.getPymesReporte(filtros);
			List<TotalEmpresas> totalEmpresas= reportService.getEmpresasByConsultora(filtros);
			for(int i=0;i<pymesList.size();i++){
				log.debug(totalEmpresas.size()>i );
				if(totalEmpresas.size()>i){
					temp=pymesList.get(i);
					log.debug(totalEmpresas.get(i).getConsultoraTotal());
					temp.setEmpresa(totalEmpresas.get(i).getConsultoraTotal());
					temp.setTotales(""+totalEmpresas.get(i).getEmpresas());
					pymesLists.add(temp);
				}
				else {
					pymesLists.add(pymesList.get(i));
				}
			}
			if (pymesList.isEmpty()) {
					setSalida("No se encontraron resultados que coincidan con su busqueda");
					setOpcion("descarga");
					return SUCCESS;
			}else {
					setSalida(null);
			        try {            
						JasperDesign design = JRXmlLoader.load(
			                    (new FileInputStream(direccion +"/jasper/pymes.jrxml")));/*"WEB-INF\\jasper\\reporte.jrxml"*/
			            JasperCompileManager.compileReportToFile(design,direccion +"/jasper/reporte"+usuario.getIdUsuario()+".jasper");
			            @SuppressWarnings({ "rawtypes" })
			            Map parameters = new HashMap();
			            parameters.put("SUBREPORT_DIR", direccion +"/jasper/Reportes\\");
			            JasperPrint jasperPrint = JasperFillManager.fillReport(direccion +"/jasper/reporte"+usuario.getIdUsuario()+".jasper", parameters,new JRBeanCollectionDataSource(pymesLists));
			            OutputStream output = new FileOutputStream(new File(direccion +"/jasper/Reporte"+usuario.getIdUsuario()+".xlsx")); 
			            JRXlsxExporter exporterXLS = new JRXlsxExporter();
			            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint); 
			            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output); 
			            exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
			            exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE); 
			            exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
			            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
			            exporterXLS.exportReport();   
			       } catch (Exception e) {
			            e.printStackTrace();
			            log.debug(e.getCause()+"\n"+e.getMessage()+"\n"+e.toString());
			            return ERROR;
			       }
			}
			setOpcion("descarga");
			return SUCCESS;
		}else if(opcion!=null && opcion.equals("descarga")){
			setOpcion("descarga");
			return SUCCESS;
		}else {
			log.debug("aca ando");
			return SUCCESS;
		}
	}

	@Action(value = "/reporteShow", results = { @Result(name = "success", location = "ccmx.administracion.reportes.show", type = "tiles") })
	public String reporteShow() {
		log.debug("reporteShow()");
		setMenu(5);
		return SUCCESS;
	}

	
	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public Filtros getFiltros() {
		return filtros;
	}

	public void setFiltros(Filtros filtros) {
		this.filtros = filtros;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public List<mx.com.vgati.ccmx.vinculacion.report.dto.Consultoras> getConsultorasList() {
		return consultorasList;
	}

	public void setConsultorasList(List<mx.com.vgati.ccmx.vinculacion.report.dto.Consultoras> consultorasList) {
		this.consultorasList = consultorasList;
	}

	public List<Tractoras> getTractorasList() {
		return tractorasList;
	}

	public void setTractorasList(List<Tractoras> tractorasList) {
		this.tractorasList = tractorasList;
	}

	public List<CCMXParticipantes> getServiciosList() {
		return serviciosList;
	}

	public void setServiciosList(List<CCMXParticipantes> serviciosList) {
		this.serviciosList = serviciosList;
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

	public List<Tractoras> getListTractoras() throws BaseBusinessException {
		Usuario u = getUsuario();
		setListTractoras(ccmxService.getTractoras(u.getIdUsuario()));
		return listTractoras;
	}

	public void setListTractoras(List<Tractoras> listTractoras) {
		this.listTractoras = listTractoras;
	}

	public List<Diplomados> getListDiplomados()
			throws DiplomadosNoObtenidosException {
		log.debug("getListDiplomados()");
		setListDiplomados(pyMEsService.getDiplomado());
		return listDiplomados;
	}

	public void setListDiplomados(List<Diplomados> listDiplomados) {
		this.listDiplomados = listDiplomados;
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
		// Usuario u = getUsuario();
		setListPyMEs(ccmxService.getPyME(/* u.getIdUsuario() */));
		return listPyMEs;
	}

	public void setListPyMEs(List<PyMEs> listPyMEs) {
		this.listPyMEs = listPyMEs;
	}

	public List<Consultoras> getListConsultoras() throws BaseBusinessException {
		Usuario u = getUsuario();
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

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Certificaciones getCertificaciones() {
		return certificaciones;
	}

	public void setCertificaciones(Certificaciones certificaciones) {
		this.certificaciones = certificaciones;
	}

	public Domicilios getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(Domicilios domicilios) {
		this.domicilios = domicilios;
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

	@Action(value = "/showDoc", results = {
			@Result(name = "success", type = "stream", params = { "inputName",
					"archivo", "contentType", "mimeArchivo",
					"contentDisposition",
					"attachment;filename=\"${nameArchivo}\"" }),
			@Result(name = "input", location = "pyme.datos.show", type = "tiles"),
			@Result(name = "error", location = "pyme.datos.show", type = "tiles") })
	public String showDoc() throws DocumentoNoObtenidoException {
		log.debug("showDoc()");
		setArchivo(pyMEsService.getArchivo(idArchivo).getIs());
		log.debug("archivo=" + archivo);
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		return SUCCESS;
	}
	@Action(value = "/downDoc", results = {
			@Result(name = "success", type = "stream", params = { "inputName", "archivo", "contentType", "mimeArchivo", "contentDisposition",
					"attachment;filename=\"Reporte.xlsx\"" }),
			@Result(name = "input", location = "reportes.general.reportes.list", type = "tiles"),
			@Result(name = "error", location = "reportes.general.reportes.list", type = "tiles") })
	public String downDoc() throws DocumentoNoObtenidoException {
		log.debug("showDoc()");
		Usuario usuario = (Usuario) sessionMap.get("Usuario");
		String direccion = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
		File file= new File(direccion +"/jasper/Reporte"+usuario.getIdUsuario()+".xlsx");
        try {
			setArchivo(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		log.debug("archivo=" + archivo);
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		return SUCCESS;
	}

}
