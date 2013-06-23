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
import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.DiplomadosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.service.PyMEsService;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXFinanzas;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXParticipantes;
import mx.com.vgati.ccmx.vinculacion.report.dto.Filtros;
import mx.com.vgati.ccmx.vinculacion.report.dto.FiltrosGenerales;
import mx.com.vgati.ccmx.vinculacion.report.dto.IndicadoresPymes;
import mx.com.vgati.ccmx.vinculacion.report.dto.PYMESReporte;
import mx.com.vgati.ccmx.vinculacion.report.dto.TotalEmpresas;
import mx.com.vgati.ccmx.vinculacion.report.service.ReportService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.CatScianCcmx;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.RelPyMEsTractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.CompradoresNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.ProductosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.service.TractorasService;
import mx.com.vgati.framework.action.AbstractBaseAction;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.exception.BaseBusinessException;
import mx.com.vgati.framework.util.Null;
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
	private String busqueda;
	private String estado;
	private String cveScian;
	private String producto;
	private List<CatScianCcmx> listCatProductos;
	private List<CatScianCcmx> listCat2;
	private List<CatScianCcmx> listCat3;
	private List<CatScianCcmx> listCat4;
	private List<CatScianCcmx> listCat5;
	private int cat1;
	private int cat2;
	private int cat3;
	private int cat4;
	private int cat5;
	private Consultoras consultoras;
	private List<Consultoras> listConsultoras;
	private String credenciales;
	private int idUsuario;
	private Domicilios domicilios;
	private int idArchivo;
	private String nameArchivo;
	private String mimeArchivo;
	private InputStream archivo;
	private List<Consultoras> consultorasList;
	private List<Tractoras> tractorasList;
	private List<CCMXParticipantes> serviciosList;
	private String opcion;
	private Filtros filtros;
	private String salida;
	private EstadosVenta estadosVentas;
	private Indicadores indicadores;
	private Indicadores indicadoresMes;
	private RelPyMEsTractoras relPymesTractoras;
	private List<FiltrosGenerales> menuAnticipo;
	private List<FiltrosGenerales> menuFiniquito;
	private List<FiltrosGenerales> menuAnticipoFiniquito;
	private List<FiltrosGenerales> menuCedula;
	private List<FiltrosGenerales> menuEstatus;
	private ServiciosConsultoria serviciosConsultoria;
	private List<Tractoras> listDetallesTractoras;
	private int estatus;

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
			if (initService.getUsuario(tractoras.getCorreoElectronico()) != null) {
				setMensaje(new Mensaje(
						1,
						"Imposible realizar la operación, la cuenta de correo '"
								.concat(tractoras.getCorreoElectronico())
								.concat("' ya ha sido utilizada en el sistema, intente con otra cuenta de correo electrónico por favor.")));
				return SUCCESS;
			}
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
				log.debug("Enviando correo electrónico:"
						+ tractoras.getCorreoElectronico());
				SendEmail envia = new SendEmail(
						Null.free(tractoras.getCorreoElectronico()),
						"SIA CCMX Registro de usuario Tractora",
						"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>"
								.concat("Estimado Administrador de ")
								.concat(Null.free(tractoras.getEmpresa()))
								.concat(",<br /><br />El Centro de Competitividad de México (CCMX) ha ")
								.concat("generado tu perfil como Comprador-Administrador de ")
								.concat(Null.free(tractoras.getEmpresa()))
								.concat(" en el Sistema de Vinculación del CCMX. Recuerda que en este sistema podrás dar de alta a ")
								.concat("los compradores que podrán buscar productos y servicios que ofrecen las Pequeñas y Medianas")
								.concat(" Empresas (PyMEs) de México. Además, podrán ver sus datos de contacto, sus principales ")
								.concat("productos, sus principales clientes; así como indicadores sobre su desempeño en experiencias ")
								.concat("de compra con otras grandes empresas.<br /><br />En este sistema también podrán dar de alta ")
								.concat("sus requerimientos para que las PyMEs con registro en este sistema puedan enviarles cotizaciones")
								.concat(" o presupuestos.<br /><br />Los accesos para el Sistema de Vinculación son los siguientes:<br />")
								.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><a href='")
								.concat("http://200.76.23.155:8080/vinculacion/inicio.do'>http://200.76.23.155:8080/vinculacion/inicio.do</a><br />Usuario: ")
								.concat(Null.free(tractoras
										.getCorreoElectronico()))
								.concat("<br />Contraseña: ")
								.concat(Null.free(tractoras.getPassword()))
								.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br />Esperamos que tu experiencia")
								.concat(" con el Sistema de Vinculación sea excelente y en caso de cualquier duda sobre la operación y funcionamiento ")
								.concat("del sistema, no dudes en ponerte en contacto con andres.blancas@ccmx.org.mx.<br /><br />Muchas gracias ")
								.concat("por utilizar el sistema de vinculación del CCMX.<br />Centro de Competitividad de México</h5>"),
						null);
				log.debug("Enviando correo electrónico:" + envia);
			}
		} else if (tractoras != null && tractoras.getIdUsuario() != 0) {
			String original = initService.getCredenciales(
					tractoras.getIdUsuario()).getId();
			String nuevo = tractoras.getCorreoElectronico();
			if (!original.equals(nuevo)
					&& initService.getUsuario(nuevo) != null) {
				setMensaje(new Mensaje(
						1,
						"Imposible realizar la operación, la cuenta de correo '"
								.concat(nuevo)
								.concat("' ya ha sido utilizada en el sistema, intente con otra cuenta de correo electrónico por favor.")));
				return SUCCESS;
			}
			log.debug("actualizando tractora:" + tractoras);
			Usuario u = initService.getUsuario(credenciales);
			tractoras.setPassword(initService.getCredenciales(u.getIdUsuario())
					.getCredenciales());
			tractoras.setIdUsuario(u.getIdUsuario());
			setMensaje(ccmxService.updateTractora(tractoras, credenciales));
			if (mensaje.getRespuesta() == 0 && !original.equals(nuevo)) {
				log.debug("Enviando correo electrónico:"
						+ tractoras.getCorreoElectronico());
				SendEmail envia = new SendEmail(
						Null.free(tractoras.getCorreoElectronico()),
						"SIA CCMX Actualización de usuario Tractora",
						"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>"
								.concat("Estimada ")
								.concat(Null.free(tractoras.getEmpresa()))
								.concat(",<br /><br />Se ha solicitado la modificación del correo ")
								.concat("electrónico en el perfil del administrador de ")
								.concat(Null.free(tractoras.getEmpresa()))
								.concat(" en el Sistema de Vinculación del Centro de Competitividad de México.")
								.concat("<br /><br />De tal forma, si es necesario editar alguna información ")
								.concat("adicional de su perfil, recuerde que lo puede hacer en la siguiente ")
								.concat("dirección electrónica:<br />")
								.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><a href='")
								.concat("http://200.76.23.155:8080/vinculacion/inicio.do'>http://200.76.23.155:8080/")
								.concat("vinculacion/inicio.do</a><br /><br />Su usuario es:<br /><br />")
								.concat(Null.free(tractoras
										.getCorreoElectronico()))
								.concat("<br /><br />Su contraseña es:<br /><br />")
								.concat(Null.free(tractoras.getPassword()))
								.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Le recordamos que como ")
								.concat("administrador del Sistema de Vinculación por parte de ")
								.concat(Null.free(tractoras.getEmpresa()))
								.concat(" podrá dar seguimiento al desempeño de las PyMEs que ")
								.concat("forman parte de dicho sistema y son o han sido sus proveedores.<br /><br />")
								.concat("En caso de que no se hubiera solicitado la modificación del correo")
								.concat(" electrónico o tenga alguna duda sobre la operación y funcionamiento del")
								.concat(" Sistema de Vinculación, no dude en comunicarse con el Centro de Competitividad")
								.concat(" de México al siguiente correo electrónico:<br /><br />sistemadevinculacion@ccmx.org.mx")
								.concat("<br /><br />Muchas gracias ")
								.concat("por utilizar el sistema de vinculación del CCMX.</h5>"),
						null);
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

		// TODO revisar si es necesario filtrar por idUsuario...
		if (tractoras != null && tractoras.getIdUsuario() != 0)
			setTractoras(tractorasService.getTractora(tractoras.getIdUsuario()));
		return SUCCESS;
	}

	@Action(value = "/consultorasShow", results = { @Result(name = "success", location = "ccmx.administracion.consultoras.list", type = "tiles") })
	public String consultorasShow() throws BaseBusinessException {
		log.debug("consultorasShow()");
		setMenu(2);

		if (consultoras != null && consultoras.getIdUsuario() == 0) {
			if (initService.getUsuario(consultoras.getCorreoElectronico()) != null) {
				setMensaje(new Mensaje(
						1,
						"Imposible realizar la operación, la cuenta de correo '"
								.concat(consultoras.getCorreoElectronico())
								.concat("' ya ha sido utilizada en el sistema, intente con otra cuenta de correo electrónico por favor.")));
				return SUCCESS;
			}
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
			SendEmail envia = new SendEmail(
					consultoras.getCorreoElectronico(),
					"SIA CCMX Registro de usuario Consultora",
					"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimada "
							.concat(Null.free(consultoras.getEmpresa()))
							.concat(",<br /><br />Nos complace informante que el Centro de Competitividad de México (CCMX) te ha dado de alta como empresa")
							.concat(" consultora en el Sistema de Vinculación del CCMX. En este sistema podrás dar de alta a tus consultores para que sea posible el ")
							.concat("seguimiento a las PyMEs que se te han asignado para ofrecerles el servicio de consultoría especializada.")
							.concat("<br /><br />Además de registrar el avance de las PyMEs en el proceso de consultoría, será posible solicitar el pago por tus servicios.")
							.concat("<br /><br />Es muy importante para el CCMX que como empresas consultoras utilicen este sistema de información para hacer")
							.concat(" más eficiente la administración y seguimiento de los servicios que ofrecemos. Los accesos del sistema son los siguientes.")
							.concat("<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Usuario: ")
							.concat(Null.free(consultoras
									.getCorreoElectronico()))
							.concat("<br />Contraseña: ")
							.concat(Null.free(consultoras.getPassword()))
							.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br />En caso de cualquier duda sobre la operación y ")
							.concat("funcionamiento del sistema, no dudes en ponerte en contacto con sistemadevinculacion@ccmx.org.mx.")
							.concat("<br /><br />Muchas gracias por utilizar el sistema de vinculación del CCMX.</h5>"),
					null);
			log.debug("Enviando correo electrónico:" + envia);

		} else if (consultoras != null && consultoras.getIdUsuario() != 0) {
			String original = initService.getCredenciales(
					consultoras.getIdUsuario()).getId();
			String nuevo = consultoras.getCorreoElectronico();
			if (!original.equals(nuevo)
					&& initService.getUsuario(nuevo) != null) {
				setMensaje(new Mensaje(
						1,
						"Imposible realizar la operación, la cuenta de correo '"
								.concat(nuevo)
								.concat("' ya ha sido utilizada en el sistema, intente con otra cuenta de correo electrónico por favor.")));
				return SUCCESS;
			}
			log.debug("actualizando consultora:" + consultoras);
			Usuario u = initService.getUsuario(credenciales);
			consultoras.setPassword(initService.getCredenciales(
					u.getIdUsuario()).getCredenciales());
			consultoras.setIdUsuario(u.getIdUsuario());
			setMensaje(ccmxService.updateConsultora(consultoras, credenciales));
			if (mensaje.getRespuesta() == 0) {
				log.debug("Enviando correo electrónico:"
						+ consultoras.getCorreoElectronico());
				SendEmail envia = new SendEmail(
						consultoras.getCorreoElectronico(),
						"SIA CCMX Registro de usuario Consultora",
						"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimada "
								.concat(Null.free(consultoras.getEmpresa()))
								.concat(",<br /><br />Nos complace informante que el Centro de Competitividad de México (CCMX) te ha dado de alta como empresa")
								.concat(" consultora en el Sistema de Vinculación del CCMX. En este sistema podrás dar de alta a tus consultores para que sea posible el ")
								.concat("seguimiento a las PyMEs que se te han asignado para ofrecerles el servicio de consultoría especializada.")
								.concat("<br /><br />Además de registrar el avance de las PyMEs en el proceso de consultoría, será posible solicitar el pago por tus servicios.")
								.concat("<br /><br />Es muy importante para el CCMX que como empresas consultoras utilicen este sistema de información para hacer")
								.concat(" más eficiente la administración y seguimiento de los servicios que ofrecemos. Los accesos del sistema son los siguientes.")
								.concat("<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Usuario: ")
								.concat(Null.free(consultoras
										.getCorreoElectronico()))
								.concat("<br />Contraseña: ")
								.concat(Null.free(consultoras.getPassword()))
								.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br />En caso de cualquier duda sobre la operación y ")
								.concat("funcionamiento del sistema, no dudes en ponerte en contacto con sistemadevinculacion@ccmx.org.mx.")
								.concat("<br /><br />Muchas gracias por utilizar el sistema de vinculación del CCMX.</h5>"),
						null);
				log.debug("Enviando correo electrónico:" + envia);
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
			if (initService.getUsuario(pyMEs.getCorreoElectronico()) != null) {
				setMensaje(new Mensaje(
						1,
						"Imposible realizar la operación, la cuenta de correo '"
								.concat(pyMEs.getCorreoElectronico())
								.concat("' ya ha sido utilizada en el sistema, intente con otra cuenta de correo electrónico por favor.")));
				return SUCCESS;
			}
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
			log.debug("guardando la asignación de tractora a PyME:"
					+ pyMEs.getIdTractora());
			setMensaje(ccmxService.saveRelPyMETrac(pyMEs));
			log.debug("Guardando la tractora como cliente de la PyME:"
					+ pyMEs.getIdTractora());
			String nomTractora = ccmxService.getNombreTractora(pyMEs
					.getIdTractora());
			log.debug("Nombre Cliente Tractora=" + nomTractora);
			setMensaje(ccmxService.saveCliente(nomTractora,
					pyMEs.getIdUsuario()));

			SendEmail envia = new SendEmail(
					pyMEs.getCorreoElectronico(),
					"SIA CCMX Registro de usuario PyME",
					"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimada "
							.concat(Null.free(pyMEs.getNombreComercial()))
							.concat(",<br /><br />Nos complace informarte que el Centro de Competitividad de México (CCMX) ha dado de alta a tu empresa en ")
							.concat("el Sistema de Vinculación del CCMX. En este sistema podrás consultar los requerimientos de las grandes empresas de México")
							.concat(" y podrás enviar cotizaciones.<br /><br />")
							.concat("Además, tu información de contacto, así como de los productos o los servicios que ofreces, estarán disponibles para que las ")
							.concat("grandes empresas u otras PyMEs que buscan oportunidades de negocio puedan identificarte.<br /><br />")
							.concat("Es muy importante que para aprovechar todas las ventajas que tiene este sistema, ingreses con la siguiente cuenta y password ")
							.concat("para actualizar y completar tu información.<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Usuario: ")
							.concat(Null.free(pyMEs.getCorreoElectronico()))
							.concat("<br />Contraseña: ")
							.concat(Null.free(pyMEs.getPassword()))
							.concat("<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>El vínculo del Sistema de Vinculación es:</h5>")
							.concat("<h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><br /><a href='http://200.76.23.155:8080/vinculacion/inicio.do'>")
							.concat("http://200.76.23.155:8080/vinculacion/inicio.do</a><br /><br />")
							.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>No olvides actualizar tu perfil si tus datos de contacto")
							.concat(" han cambiado o si tienes nuevos productos o servicios que ofrecer.<br /><br />")
							.concat("En caso de cualquier duda sobre la operación y funcionamiento del sistema, no dudes en ponerte en contacto con ")
							.concat("sistemadevinculacion@ccmx.org.mx.<br /><br />")
							.concat("Muchas gracias por utilizar el sistema de vinculación del CCMX.</h5>"),
					null);
			log.debug("Enviando correo electrónico:" + envia);

		}

		if (idUsuario != 0) {
			log.debug("Consultando la PyME");
			setPyMEs(pyMEsService.getPyME(idUsuario));
			log.debug("Usuario=" + idUsuario);
			String idDom = pyMEsService.getIdDomicilio(idUsuario);
			log.debug("idDomicilio=" + idDom);
			setDomicilios(pyMEsService.getDomicilio(Integer.parseInt(idDom)));

			setEstadosVentas(pyMEsService.getEstadoVenta(idUsuario));

			String idInd = pyMEsService.getIdIndicador(idUsuario);
			log.debug("idIndicador=" + idInd);
			setIndicadores(pyMEsService.getIndicador(Integer.parseInt(idInd)));

			setRelPymesTractoras(pyMEsService.getCalificacion(idUsuario));
			setIndicadoresMes(pyMEsService.getIndicadorMes(idUsuario));
			setServiciosConsultoria(pyMEsService.getServConsultorias(idUsuario));
		}

		if (estatus != 0) {
			log.debug("Deshabilitando PyME" + estatus);
			setMensaje(ccmxService.deshabilitaPyME(estatus));
		}

		if (pyMEs == null) {
			log.debug("cat1=" + cat1);
			if (cat1 != 0) {
				log.debug("consultando Cat 2 = " + cat1);
				setListCat2(tractorasService.getNivelScian(cat1));
			}

			log.debug("cat2=" + cat2);
			if (cat2 != 0) {
				log.debug("consultando Cat 3 = " + cat2);
				setListCat3(tractorasService.getNivelScian(cat2));
			}

			log.debug("cat3=" + cat3);
			if (cat3 != 0) {
				log.debug("consultando Cat 4 = " + cat3);
				setListCat4(tractorasService.getNivelScian(cat3));
			}

			log.debug("cat4=" + cat4);
			if (cat4 != 0) {
				log.debug("consultando Cat 5 = " + cat4);
				setListCat5(tractorasService.getNivelScian(cat4));
			}
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
	public String PyMEShow() throws ProductosNoObtenidosException,
			PyMEsNoObtenidasException {
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
	@Action(value = "/reportesShow", results = {
			@Result(name = "success", location = "ccmx.administracion.reportes.list", type = "tiles"),
			@Result(name = "input", location = "ccmx.administracion.reportes.list", type = "tiles"),
			@Result(name = "error", location = "ccmx.administracion.reportes.list", type = "tiles") })
	public String reportesShow() throws BaseBusinessException {
		setMenu(5);
		if (opcion != null && opcion.equals("servicios")) {
			setOpcion(opcion);
			setTractorasList(reportService.getTractoras());
			setConsultorasList(reportService.getConsultoras());
			setMenuAnticipo(reportService.getMenuFacturaAnticipo());
			setMenuAnticipoFiniquito(reportService
					.getMenuFacturaAnticipoFiniquito());
			return SUCCESS;

		} else if (opcion != null && opcion.equals("finanzas")) {
			setOpcion(opcion);
			setConsultorasList(reportService.getConsultoras());
			setMenuAnticipo(reportService.getMenuFacturaAnticipo());
			setMenuFiniquito(reportService.getMenuFacturaFiniquito());
			setMenuAnticipoFiniquito(reportService
					.getMenuFacturaAnticipoFiniquito());
			return SUCCESS;
		} else if (opcion != null && opcion.equals("pymes")) {
			setOpcion(opcion);
			setConsultorasList(reportService.getConsultores(0));
			setMenuAnticipo(reportService.getMenuFacturaAnticipo());
			setMenuAnticipoFiniquito(reportService
					.getMenuFacturaAnticipoFiniquito());
			setMenuEstatus(reportService.getMenuEstatus());
			setMenuCedula(reportService.getMenuCedulas());
			return SUCCESS;
		} else if (opcion != null && opcion.equals("indicadores")) {
			setOpcion(opcion);
			setConsultorasList(reportService.getConsultores(0));
			setMenuAnticipo(reportService.getMenuFacturaAnticipo());
			setMenuAnticipoFiniquito(reportService
					.getMenuFacturaAnticipoFiniquito());
			setMenuEstatus(reportService.getMenuEstatus());
			setMenuCedula(reportService.getMenuCedulas());
			return SUCCESS;
		} else if (opcion != null && opcion.equals("servRepor")) {
			setOpcion("descarga");
			log.debug("Reporte servicios");
			String direccion = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("/");
			Usuario usuario = getUsuario();
			filtros.setId(-1);
			log.debug(filtros);
			List<CCMXParticipantes> serviciosList = reportService
					.getCCMXServicios(filtros);
			if (serviciosList.isEmpty()) {
				setSalida("No se encontraron resultados que coincidan con su busqueda");
				return SUCCESS;
			} else {
				setSalida(null);
				JasperDesign design;
				try {
					design = JRXmlLoader.load((new FileInputStream(direccion
							+ "/jasper/servicios.jrxml")));
					JasperCompileManager.compileReportToFile(design, direccion
							+ "/jasper/reporte" + usuario.getIdUsuario()
							+ ".jasper");
					@SuppressWarnings({ "rawtypes" })
					Map parameters = new HashMap();
					parameters.put("SUBREPORT_DIR", direccion
							+ "/jasper/Reportes\\");
					parameters.put("total",
							reportService.getCCMXServiciosTotal(filtros));
					parameters.put("tCultura",
							reportService.getParticipantesEmpresas(filtros, 1));
					parameters.put("tManufactura",
							reportService.getParticipantesEmpresas(filtros, 2));
					parameters.put("tEstrategia",
							reportService.getParticipantesEmpresas(filtros, 3));
					parameters.put("tPlaneacion",
							reportService.getParticipantesEmpresas(filtros, 4));
					parameters.put("tCulturaEmpres",
							reportService.getParticipantes(filtros, 1));
					parameters.put("tManufacturaEmpres",
							reportService.getParticipantes(filtros, 2));
					parameters.put("tEstrategiaEmpres",
							reportService.getParticipantes(filtros, 3));
					parameters.put("tPlaneacionEmpres",
							reportService.getParticipantes(filtros, 4));
					filtros.setEstatus("DIAGNOSTICO");
					parameters.put("diagnostico",
							reportService.getPorEstatus(filtros));
					filtros.setEstatus("PLAN DE MEJORA");
					parameters.put("planMejora",
							reportService.getPorEstatus(filtros));
					filtros.setEstatus("IMPLEMENTACION");
					parameters.put("implementacion",
							reportService.getPorEstatus(filtros));
					filtros.setEstatus("EVALUACION");
					parameters.put("evaluacion",
							reportService.getPorEstatus(filtros));
					filtros.setEstatus("CIERRE");
					parameters.put("cierre",
							reportService.getPorEstatus(filtros));
					filtros.setEstatus("CANCELADA");
					parameters.put("cancelada",
							reportService.getPorEstatus(filtros));
					filtros.setEstatus("NO ACEPTO");
					parameters.put("noAcepto",
							reportService.getPorEstatus(filtros));
					filtros.setEstatus("PENDIENTE");
					parameters.put("pendiente",
							reportService.getPorEstatus(filtros));
					filtros.setEstatus("DIFERIDA");
					parameters.put("diferida",
							reportService.getPorEstatus(filtros));

					parameters.put("empresaControl", 0);
					parameters.put("radarAntesControl", 0);
					parameters.put("radarDespuesControl", 0);

					parameters.put("estatusControl", 0);
					JasperPrint jasperPrint = JasperFillManager.fillReport(
							direccion + "/jasper/reporte"
									+ usuario.getIdUsuario() + ".jasper",
							parameters, new JRBeanCollectionDataSource(
									serviciosList));
					OutputStream output = new FileOutputStream(new File(
							direccion + "/jasper/Reporte"
									+ usuario.getIdUsuario() + ".xlsx"));
					JRXlsxExporter exporterXLS = new JRXlsxExporter();
					List<JasperPrint> objetos = new ArrayList<JasperPrint>();
					objetos.add(jasperPrint);
					log.debug(jasperPrint);
					exporterXLS.setParameter(
							JRXlsExporterParameter.JASPER_PRINT_LIST, objetos);
					exporterXLS.setParameter(
							JRXlsExporterParameter.OUTPUT_STREAM, output);
					exporterXLS.setParameter(
							JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
							Boolean.TRUE);
					exporterXLS.setParameter(
							JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
							Boolean.TRUE);
					exporterXLS.setParameter(
							JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
							Boolean.FALSE);
					exporterXLS
							.setParameter(
									JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
									Boolean.TRUE);
					exporterXLS.exportReport();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					log.debug(e.getCause() + "\n" + e);
				} catch (JRException e) {
					e.printStackTrace();
					log.debug(e.getCause() + "\n" + e);
				}
				return SUCCESS;
			}

		} else if (opcion != null && opcion.equals("finRepor")) {
			setOpcion("descarga");
			String direccion = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("/");
			Usuario usuario = getUsuario();
			List<CCMXFinanzas> finanzasList = reportService
					.getCCMXFiannzas(filtros);
			log.debug(filtros);
			if (finanzasList.isEmpty()) {
				setSalida("No se encontraron resultados que coincidan con su busqueda");
				return SUCCESS;
			} else {
				setSalida(null);
				try {
					JasperDesign design = JRXmlLoader
							.load((new FileInputStream(direccion
									+ "/jasper/financiero.jrxml")));/* "WEB-INF\\jasper\\reporte.jrxml" */
					JasperCompileManager.compileReportToFile(design, direccion
							+ "/jasper/reporte" + usuario.getIdUsuario()
							+ ".jasper");
					@SuppressWarnings({ "rawtypes" })
					Map parameters = new HashMap();
					parameters.put("SUBREPORT_DIR", direccion
							+ "/jasper/Reportes\\");
					parameters.put("abono1Total", 0);
					parameters.put("abono2Total", 0);
					parameters.put("anticipoTotal", 0);
					parameters.put("finiquitoTotal", 0);
					parameters.put("empresaPagada", 0);
					parameters.put("empresaSinPago", 0);
					parameters.put("facturaTotal", 0);
					parameters.put("facturaPendiente", 0);
					JasperPrint jasperPrint = JasperFillManager.fillReport(
							direccion + "/jasper/reporte"
									+ usuario.getIdUsuario() + ".jasper",
							parameters, new JRBeanCollectionDataSource(
									finanzasList));
					OutputStream output = new FileOutputStream(new File(
							direccion + "/jasper/Reporte"
									+ usuario.getIdUsuario() + ".xlsx"));
					JRXlsxExporter exporterXLS = new JRXlsxExporter();
					exporterXLS.setParameter(
							JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
					exporterXLS.setParameter(
							JRXlsExporterParameter.OUTPUT_STREAM, output);
					exporterXLS.setParameter(
							JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
							Boolean.TRUE);
					exporterXLS.setParameter(
							JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
							Boolean.TRUE);
					exporterXLS.setParameter(
							JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
							Boolean.FALSE);
					exporterXLS
							.setParameter(
									JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
									Boolean.TRUE);
					exporterXLS.exportReport();
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.getCause() + "\n" + e.getMessage() + "\n"
							+ e.toString());
					return ERROR;
				}
			}
			return SUCCESS;
		} else if (opcion != null && opcion.trim().equals("pyRepor")) {
			log.debug("Generando reporte de pymes");
			String direccion = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("/");
			Usuario usuario = getUsuario();
			if (usuario.getRol().equals("AdmnistradorConsultor")
					|| usuario.getRol().equals("CompradorAdministrador")
					|| usuario.getRol().equals("Comprador")
					|| usuario.getRol().equals("Consultor")) {
				filtros.setId(usuario.getIdUsuario());
				if (usuario.getRol().equals("AdmnistradorConsultor")) {
					filtros.setPermisos(3);
				} else if (usuario.getRol().equals("CompradorAdministrador")) {
					filtros.setPermisos(1);
				} else if (usuario.getRol().equals("Comprador")) {
					filtros.setPermisos(2);
				} else {
					filtros.setPermisos(4);
				}
			}
			log.debug("" + filtros);
			List<PYMESReporte> pymesLists = new ArrayList<PYMESReporte>();
			PYMESReporte temp;
			List<PYMESReporte> pymesList = reportService
					.getPymesReporte(filtros);
			List<TotalEmpresas> totalEmpresas = reportService
					.getEmpresasByConsultora(filtros);
			for (int i = 0; i < pymesList.size(); i++) {
				log.debug(totalEmpresas.size() > i);
				if (totalEmpresas.size() > i) {
					temp = pymesList.get(i);
					log.debug(totalEmpresas.get(i).getConsultoraTotal());
					temp.setEmpresa(totalEmpresas.get(i).getConsultoraTotal());
					temp.setTotales("" + totalEmpresas.get(i).getEmpresas());
					pymesLists.add(temp);
				} else {
					pymesLists.add(pymesList.get(i));
				}
			}
			if (pymesList.isEmpty()) {
				setSalida("No se encontraron resultados que coincidan con su busqueda");
				setOpcion("descarga");
				return SUCCESS;
			} else {
				setSalida(null);
				try {
					JasperDesign design = JRXmlLoader
							.load((new FileInputStream(direccion
									+ "/jasper/indicadorpublico.jrxml")));/* "WEB-INF\\jasper\\reporte.jrxml" */
					JasperCompileManager.compileReportToFile(design, direccion
							+ "/jasper/reporte" + usuario.getIdUsuario()
							+ ".jasper");
					@SuppressWarnings({ "rawtypes" })
					Map parameters = new HashMap();
					parameters.put("SUBREPORT_DIR", direccion
							+ "/jasper/Reportes\\");
					JasperPrint jasperPrint = JasperFillManager.fillReport(
							direccion + "/jasper/reporte"
									+ usuario.getIdUsuario() + ".jasper",
							parameters, new JRBeanCollectionDataSource(
									pymesLists));
					OutputStream output = new FileOutputStream(new File(
							direccion + "/jasper/Reporte"
									+ usuario.getIdUsuario() + ".xlsx"));
					JRXlsxExporter exporterXLS = new JRXlsxExporter();
					exporterXLS.setParameter(
							JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
					exporterXLS.setParameter(
							JRXlsExporterParameter.OUTPUT_STREAM, output);
					exporterXLS.setParameter(
							JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
							Boolean.TRUE);
					exporterXLS.setParameter(
							JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
							Boolean.TRUE);
					exporterXLS.setParameter(
							JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
							Boolean.FALSE);
					exporterXLS
							.setParameter(
									JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
									Boolean.TRUE);
					exporterXLS.exportReport();
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.getCause() + "\n" + e.getMessage() + "\n"
							+ e.toString());
					return ERROR;
				}
			}
			setOpcion("descarga");
			return SUCCESS;
		} else if (opcion != null && opcion.trim().equals("inRepor")) {
			log.debug("Generando reporte de indicadores de las indicadores");
			String direccion = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("/");
			Usuario usuario = getUsuario();
			log.debug("" + filtros);
			List<IndicadoresPymes> indicadoresList = new ArrayList<IndicadoresPymes>();
			indicadoresList = reportService.getIndicadoresReporte(filtros);
			if (indicadoresList.isEmpty()) {
				setSalida("No se encontraron resultados que coincidan con su busqueda");
				setOpcion("descarga");
				return SUCCESS;
			} else {
				setSalida(null);
				try {
					JasperDesign design = JRXmlLoader
							.load((new FileInputStream(direccion
									+ "/jasper/indicadorprivado.jrxml")));/* "\jasper\\reporte.jrxml" */
					JasperCompileManager.compileReportToFile(design, direccion
							+ "/jasper/reporte" + usuario.getIdUsuario()
							+ ".jasper");
					@SuppressWarnings({ "rawtypes" })
					Map parameters = new HashMap();
					parameters.put("SUBREPORT_DIR", direccion
							+ "/jasper/Reportes\\");
					parameters.put("t1", "T4 2012");
					parameters.put("t2", "T1 2013");
					parameters.put("t3", "T2 2013");
					parameters.put("t4", "T3 2014");
					JasperPrint jasperPrint = JasperFillManager.fillReport(
							direccion + "/jasper/reporte"
									+ usuario.getIdUsuario() + ".jasper",
							parameters, new JRBeanCollectionDataSource(
									indicadoresList));
					OutputStream output = new FileOutputStream(new File(
							direccion + "/jasper/Reporte"
									+ usuario.getIdUsuario() + ".xlsx"));
					JRXlsxExporter exporterXLS = new JRXlsxExporter();
					exporterXLS.setParameter(
							JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
					exporterXLS.setParameter(
							JRXlsExporterParameter.OUTPUT_STREAM, output);
					exporterXLS.setParameter(
							JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
							Boolean.TRUE);
					exporterXLS.setParameter(
							JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
							Boolean.TRUE);
					exporterXLS.setParameter(
							JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
							Boolean.FALSE);
					exporterXLS
							.setParameter(
									JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
									Boolean.TRUE);
					exporterXLS.exportReport();
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.getCause() + "\n" + e.getMessage() + "\n"
							+ e.toString());
					return ERROR;
				}
			}
			setOpcion("descarga");
			return SUCCESS;
		} else if (opcion != null && opcion.equals("descarga")) {
			setOpcion("descarga");
			return SUCCESS;
		} else {
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

	public List<Consultoras> getConsultorasList() {
		return consultorasList;
	}

	public void setConsultorasList(List<Consultoras> consultorasList) {
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
		List<PyMEs> list = new ArrayList<PyMEs>();
		log.debug(busqueda);
		log.debug(estado);
		log.debug(cveScian);
		if (Null.free(busqueda).trim().isEmpty()) {
			setListPyMEs(ccmxService.getPyME());
		} else {
			list = pyMEsService.getBusquedaPyME(Null.free(busqueda),
					Null.free(estado).equals("-1") ? "" : estado,
					Null.free(cveScian));
			setListPyMEs(list);
		}

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

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCveScian() {
		return cveScian;
	}

	public void setCveScian(String cveScian) {
		this.cveScian = cveScian;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public List<CatScianCcmx> getListCatProductos()
			throws ProductosNoObtenidosException {

		setListCatProductos(tractorasService.getNivelScian(0));

		return listCatProductos;
	}

	public void setListCatProductos(List<CatScianCcmx> listCatProductos) {
		this.listCatProductos = listCatProductos;
	}

	public List<CatScianCcmx> getListCat2()
			throws ProductosNoObtenidosException {
		return listCat2;
	}

	public void setListCat2(List<CatScianCcmx> listCat2) {
		this.listCat2 = listCat2;
	}

	public List<CatScianCcmx> getListCat3() {
		return listCat3;
	}

	public void setListCat3(List<CatScianCcmx> listCat3) {
		this.listCat3 = listCat3;
	}

	public List<CatScianCcmx> getListCat4()
			throws ProductosNoObtenidosException {
		return listCat4;
	}

	public void setListCat4(List<CatScianCcmx> listCat4) {
		this.listCat4 = listCat4;
	}

	public List<CatScianCcmx> getListCat5()
			throws ProductosNoObtenidosException {
		return listCat5;
	}

	public void setListCat5(List<CatScianCcmx> listCat5) {
		this.listCat5 = listCat5;
	}

	public int getCat1() {
		return cat1;
	}

	public void setCat1(int cat1) {
		this.cat1 = cat1;
	}

	public int getCat2() {
		return cat2;
	}

	public void setCat2(int cat2) {
		this.cat2 = cat2;
	}

	public int getCat3() {
		return cat3;
	}

	public void setCat3(int cat3) {
		this.cat3 = cat3;
	}

	public int getCat4() {
		return cat4;
	}

	public void setCat4(int cat4) {
		this.cat4 = cat4;
	}

	public int getCat5() {
		return cat5;
	}

	public void setCat5(int cat5) {
		this.cat5 = cat5;
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

	public EstadosVenta getEstadosVentas() {
		return estadosVentas;
	}

	public void setEstadosVentas(EstadosVenta estadosVentas) {
		this.estadosVentas = estadosVentas;
	}

	public Indicadores getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(Indicadores indicadores) {
		this.indicadores = indicadores;
	}

	public Indicadores getIndicadoresMes() {
		return indicadoresMes;
	}

	public void setIndicadoresMes(Indicadores indicadoresMes) {
		this.indicadoresMes = indicadoresMes;
	}

	public RelPyMEsTractoras getRelPymesTractoras() {
		return relPymesTractoras;
	}

	public void setRelPymesTractoras(RelPyMEsTractoras relPymesTractoras) {
		this.relPymesTractoras = relPymesTractoras;
	}

	public List<FiltrosGenerales> getMenuAnticipo() {
		return menuAnticipo;
	}

	public void setMenuAnticipo(List<FiltrosGenerales> menuAnticipo) {
		this.menuAnticipo = menuAnticipo;
	}

	public List<FiltrosGenerales> getMenuAnticipoFiniquito() {
		return menuAnticipoFiniquito;
	}

	public void setMenuAnticipoFiniquito(
			List<FiltrosGenerales> menuAnticipoFiniquito) {
		this.menuAnticipoFiniquito = menuAnticipoFiniquito;
	}

	public List<FiltrosGenerales> getMenuCedula() {
		return menuCedula;
	}

	public void setMenuCedula(List<FiltrosGenerales> menuCedula) {
		this.menuCedula = menuCedula;
	}

	public List<FiltrosGenerales> getMenuEstatus() {
		return menuEstatus;
	}

	public void setMenuEstatus(List<FiltrosGenerales> menuEstatus) {
		this.menuEstatus = menuEstatus;
	}

	public ServiciosConsultoria getServiciosConsultoria() {
		return serviciosConsultoria;
	}

	public void setServiciosConsultoria(
			ServiciosConsultoria serviciosConsultoria) {
		this.serviciosConsultoria = serviciosConsultoria;
	}

	public List<FiltrosGenerales> getMenuFiniquito() {
		return menuFiniquito;
	}

	public void setMenuFiniquito(List<FiltrosGenerales> menuFiniquito) {
		this.menuFiniquito = menuFiniquito;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public List<Tractoras> getListDetallesTractoras()
			throws TractorasNoObtenidasException {
		setListDetallesTractoras(ccmxService.getDetalleTractora());
		return listDetallesTractoras;
	}

	public void setListDetallesTractoras(List<Tractoras> listDetallesTractoras) {
		this.listDetallesTractoras = listDetallesTractoras;
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
			@Result(name = "success", type = "stream", params = { "inputName",
					"archivo", "contentType", "mimeArchivo",
					"contentDisposition",
					"attachment;filename=\"Reporte.xlsx\"" }),
			@Result(name = "input", location = "reportes.general.reportes.list", type = "tiles"),
			@Result(name = "error", location = "reportes.general.reportes.list", type = "tiles") })
	public String downDoc() throws BaseBusinessException {
		log.debug("downDocAdmin()");
		Usuario usuario = getUsuario();
		String direccion = ServletActionContext.getRequest().getSession()
				.getServletContext().getRealPath("/");
		File file = new File(direccion + "/jasper/Reporte"
				+ usuario.getIdUsuario() + ".xlsx");
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
