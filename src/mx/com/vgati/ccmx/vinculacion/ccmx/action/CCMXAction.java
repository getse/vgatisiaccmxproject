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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.service.CCMXService;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.exception.ConsultoraNoObtenidaException;
import mx.com.vgati.ccmx.vinculacion.consultoras.service.ConsultorasService;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Participantes;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Sesiones;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.service.CoordinadorDiplomadosService;
import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.dto.Roles;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoAlmacenadoException;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.service.PyMEsService;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXFinanzas;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXParticipantes;
import mx.com.vgati.ccmx.vinculacion.report.dto.Filtros;
import mx.com.vgati.ccmx.vinculacion.report.dto.FiltrosGenerales;
import mx.com.vgati.ccmx.vinculacion.report.dto.IndicadoresPyMEs;
import mx.com.vgati.ccmx.vinculacion.report.dto.PyMEsReporte;
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
import mx.com.vgati.framework.dto.Usuario;
import mx.com.vgati.framework.exception.BaseBusinessException;
import mx.com.vgati.framework.util.Null;
import mx.com.vgati.framework.util.SendEmail;
import mx.com.vgati.framework.util.ValidationUtils;
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
			"PyMEs", "DIPLOMADOS", "USUARIOS", "DOCUMENTOS", "REPORTES" };
	private static final String[] fr = { "tractorasShow.do",
			"consultorasShow.do", "PyMEsShow.do", "diplomadosShow.do",
			"usuariosShow.do", "documentosShow.do", "reportesShow.do" };

	private CCMXService ccmxService;
	private TractorasService tractorasService;
	private ConsultorasService consultorasService;
	private InitService initService;
	private PyMEsService pyMEsService;
	private ReportService reportService;
	private CoordinadorDiplomadosService coordinadorDiplomadosService;
	private Tractoras tractoras;
	private List<Tractoras> listTractoras;
	private List<List<Diplomados>> listDiplomados;
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
	private int estatusA;
	private int generaciones;
	private int idDiplomado;
	private String tituloDiplomado;
	private Diplomados diplomado;
	private int year;
	private List<Integer> menuAnios;
	private List<Participantes> listParticipantes;
	private List<Sesiones> listSesiones;
	private int numeroSesiones;
	private String servicios;
	private List<Integer> listIds;
	private int idPyME;
	private ServiciosDiplomado serviciosDiplomado;
	private List<Documento> listDocumentos;
	private List<PyMEs> pyMEsList;
	private int menuSeleccionado;
	private List<Participantes> listDiplomas;
	private List<Participantes> listInacistencias;
	private boolean sesion1;
	private boolean sesion2;
	private boolean sesion3;
	private boolean sesion4;
	private String idArchivos;
	private String participante;
	private List<Usuario> usuarios;
	private String correo;
	private String credencial;
	private List<FiltrosGenerales> sesionInformativa;
	private String total;
	private String activas;
	private String expediente;
	private int rolManual;
	private File archivoManual;
	private String archivoManualFileName;
	private String descArchivoManual;
	private String original;
	private String nuevo;
	private int id;

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

	public void setCoordinadorDiplomadosService(
			CoordinadorDiplomadosService coordinadorDiplomadosService) {
		this.coordinadorDiplomadosService = coordinadorDiplomadosService;
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
			tractoras.setCorreoElectronico(tractoras.getCorreoElectronico()
					.toLowerCase());
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
								.concat("http://www.ccmx.mx/vinculacion/inicio.do'>http://www.ccmx.mx/vinculacion/inicio.do</a><br />Usuario: ")
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
			tractoras.setCorreoElectronico(tractoras.getCorreoElectronico()
					.toLowerCase());
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
			setMensaje(ccmxService.updateTractora(tractoras, credenciales,
					Roles.Tractora.name()));
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
								.concat("http://www.ccmx.mx/vinculacion/inicio.do'>http://www.ccmx.mx/")
								.concat("vinculacion/inicio.do</a><br /><br />Su usuario es:<br /><br />")
								.concat(Null.free(tractoras
										.getCorreoElectronico()))
								.concat("<br /><br />Su contraseña es:<br /><br />")
								.concat(Null.free(tractoras.getPassword()))
								.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br />Le recordamos que como ")
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
			consultoras.setCorreoElectronico(consultoras.getCorreoElectronico()
					.toLowerCase());
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
							.concat("<br /><br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Usuario: ")
							.concat(Null.free(consultoras
									.getCorreoElectronico()))
							.concat("<br />Contraseña: ")
							.concat(Null.free(consultoras.getPassword()))
							.concat("<br />El vínculo del Sistema de Vinculación es:<br /><br/><a href='http://www.ccmx.mx/vinculacion/inicio.do'>http://www.ccmx.mx/vinculacion/inicio.do</a><br />")
							.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'><br />En caso de cualquier duda sobre la operación y ")
							.concat("funcionamiento del sistema, no dudes en ponerte en contacto con sistemadevinculacion@ccmx.org.mx.")
							.concat("<br /><br />Muchas gracias por utilizar el sistema de vinculación del CCMX.</h5>"),
					null);
			log.debug("Enviando correo electrónico:" + envia);

		} else if (consultoras != null && consultoras.getIdUsuario() != 0) {
			consultoras.setCorreoElectronico(consultoras.getCorreoElectronico()
					.toLowerCase());
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
								.concat("<br /><br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Usuario: ")
								.concat(Null.free(consultoras
										.getCorreoElectronico()))
								.concat("<br />Contraseña: ")
								.concat(Null.free(consultoras.getPassword()))
								.concat("<br />El vínculo del Sistema de Vinculación es:<br /><br/><a href='http://www.ccmx.mx/vinculacion/inicio.do'>http://www.ccmx.mx/vinculacion/inicio.do</a><br />")
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
			pyMEs.setCorreoElectronico(pyMEs.getCorreoElectronico()
					.toLowerCase());
			pyMEs.setCorreoElectronicoContacto1(pyMEs
					.getCorreoElectronicoContacto1().toLowerCase());
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

			/*
			 * SendEmail envia = new SendEmail( pyMEs.getCorreoElectronico(),
			 * "SIA CCMX Registro de usuario PyME",
			 * "<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimada "
			 * .concat(Null.free(pyMEs.getNombreComercial())) .concat(
			 * ",<br /><br />Nos complace informarte que el Centro de Competitividad de México (CCMX) ha dado de alta a tu empresa en "
			 * ) .concat(
			 * "el Sistema de Vinculación del CCMX. En este sistema podrás consultar los requerimientos de las grandes empresas de México"
			 * ) .concat(" y podrás enviar cotizaciones.<br /><br />") .concat(
			 * "Además, tu información de contacto, así como de los productos o los servicios que ofreces, estarán disponibles para que las "
			 * ) .concat(
			 * "grandes empresas u otras PyMEs que buscan oportunidades de negocio puedan identificarte.<br /><br />"
			 * ) .concat(
			 * "Es muy importante que para aprovechar todas las ventajas que tiene este sistema, ingreses con la siguiente cuenta y password "
			 * ) .concat(
			 * "para actualizar y completar tu información.<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Usuario: "
			 * ) .concat(Null.free(pyMEs.getCorreoElectronico()))
			 * .concat("<br />Contraseña: ")
			 * .concat(Null.free(pyMEs.getPassword())) .concat(
			 * "<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>El vínculo del Sistema de Vinculación es:</h5>"
			 * ) .concat(
			 * "<h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><br /><a href='http://www.ccmx.mx/vinculacion/inicio.do'>"
			 * )
			 * .concat("http://www.ccmx.mx/vinculacion/inicio.do</a><br /><br />"
			 * ) .concat(
			 * "</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>No olvides actualizar tu perfil si tus datos de contacto"
			 * ) .concat(
			 * " han cambiado o si tienes nuevos productos o servicios que ofrecer.<br /><br />"
			 * ) .concat(
			 * "En caso de cualquier duda sobre la operación y funcionamiento del sistema, no dudes en ponerte en contacto con "
			 * ) .concat("sistemadevinculacion@ccmx.org.mx.<br /><br />")
			 * .concat(
			 * "Muchas gracias por utilizar el sistema de vinculación del CCMX.</h5>"
			 * ), null); log.debug("Enviando correo electrónico:" + envia);
			 */

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
			setMensaje(ccmxService.deshabilitaPyME(estatus, false));
		}

		if (estatusA != 0) {
			log.debug("Habilitando PyME" + estatusA);
			setMensaje(ccmxService.deshabilitaPyME(estatusA, true));
			if (getMensaje().getRespuesta() == 0) {
				setMensaje(new Mensaje(0,
						"La PyME seleccionada ha sido habilitada exitosamente."));
			}
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

		if (original != null && nuevo != null) {
			nuevo = nuevo.trim().toLowerCase();
			if (initService.getUsuario(nuevo) != null) {
				setMensaje(new Mensaje(
						1,
						"Imposible realizar la operación, la cuenta de correo '"
								.concat(nuevo)
								.concat("' ya ha sido utilizada en el sistema, intente con otra cuenta de correo electrónico por favor.")));
			} else {
				setMensaje(pyMEsService.changeCorreo(original, nuevo, id));
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

	@SuppressWarnings("rawtypes")
	@Action(value = "/diplomadosShow", results = { @Result(name = "success", location = "ccmx.administracion.diplomados.list", type = "tiles") })
	public String diplomadosShow() throws BaseBusinessException {
		log.debug("diplomadosShow()");
		setMenu(4);

		if (diplomado != null && numeroSesiones == 0) {
			if (diplomado.getIdDiplomado() == 0) {
				log.debug("Salvando el Diplomado en... " + generaciones
						+ " generaciones");
				for (int i = 1; i <= generaciones; i++) {
					log.debug("Salvando Generación..." + i);
					setMensaje(ccmxService.saveDiplomado(diplomado, i));
				}
			}
		}

		if (numeroSesiones > 0 && getListSesiones() != null) {
			log.debug("Actualizando el Diplomado..." + idDiplomado);
			setMensaje(ccmxService
					.updateDiplomado(idDiplomado, tituloDiplomado));
			log.debug("Guardando sesiones de diplomado = " + idDiplomado
					+ listSesiones.get(0).getIdSesion());
			setMensaje(coordinadorDiplomadosService.saveSesiones(
					getListSesiones(), numeroSesiones));
			setIdDiplomado(0);
		}

		if (opcion != null && opcion.equals("deleteDiplomado")) {
			log.debug("Eliminando Diplomado... " + idDiplomado);
			setListIds(ccmxService.getListaIds(idDiplomado));
			setMensaje(ccmxService.deleteSesion(idDiplomado));
			if (listIds.size() == 0) {
				log.debug("NO EXISTEN DOMICILIOS ASOCIADOS AL DIPLOMADO...");
			} else {
				Iterator iter = listIds.iterator();
				while (iter.hasNext()) {
					setMensaje(ccmxService.deleteDomicilio((Integer) (iter
							.next())));
				}
			}
			setMensaje(ccmxService.deleteDiplomado(idDiplomado));
			setIdDiplomado(0);
		}

		if (opcion != null && opcion.equals("Inasistencias")) {
			log.debug("Opcion... " + opcion);
			if (listInacistencias != null) {
				log.debug(listInacistencias);
				listSesiones = coordinadorDiplomadosService
						.getSesiones(idDiplomado);
				if (listSesiones != null) {
					for (int i = 0; i < listInacistencias.size(); i++) {
						Participantes part = listInacistencias.get(i);
						if (part.isInvitacion()) {
							for (int j = 0; j < listSesiones.size(); j++) {
								Sesiones s = listSesiones.get(j);
								if (s.getSesion() == part.getSesion()) {
									setMensaje(coordinadorDiplomadosService
											.saveInasistententes(part,
													idDiplomado));
									if (mensaje != null
											&& mensaje.getRespuesta() == 0) {
										log.debug("Enviando correo electrónico:"
												+ part.getCorreoElectronico());
										String direccion = " No dada de alta.";
										if (s.getDomicilios() != null) {
											direccion = "Núm."
													+ s.getDomicilios()
															.getNumExt();
											if (s.getDomicilios().getNumInt() != null) {
												direccion = direccion
														+ " Interior "
														+ s.getDomicilios()
																.getNumInt();
											}
											if (s.getDomicilios().getPiso() != null) {
												direccion = direccion
														+ " Piso "
														+ s.getDomicilios()
																.getPiso();
											}
											direccion = direccion
													+ " Colonia "
													+ s.getDomicilios()
															.getColonia();
											direccion = direccion
													+ " Delegacion o Municipio "
													+ s.getDomicilios()
															.getDelegacion();
											direccion = direccion
													+ " Estado "
													+ s.getDomicilios()
															.getEstado();
											direccion = direccion
													+ " Codigo postal "
													+ s.getDomicilios()
															.getCodigoPostal();
										}
										String entrada = "";
										if (s.getMinuto() <= 15) {
											int min = 60 - (15 - s.getMinuto());
											int hora = s.getHora() - 1;
											if (s.getHora() == 0) {
												hora = 23;
											}
											entrada = hora + ":" + min;
										} else {
											entrada = s.getHora() + ":"
													+ s.getMinuto();
										}
										String horarios = s.getHora() + ":"
												+ s.getMinuto() + " hrs. a "
												+ s.getHoraFin() + ":"
												+ s.getMinutoFin() + " hrs.";
										SendEmail envia = new SendEmail(
												Null.free(part
														.getCorreoElectronico()),
												"SIA CCMX Invitación a nueva sesión.",
												"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>"
														.concat("Estimado(a) Empresario (a):</h5> ")
														.concat("<br /><br />Nos permitimos recordarle de su próxima sesión al Diplomado de '")
														.concat(Null.free(part
																.getTema()))
														.concat("' , y le damos la más cordial bienvenida a esta  ")
														.concat(Null.free(""
																+ part.getSesion()))
														.concat(" la cual será impartida en las instalaciones de la empresa ")
														.concat(s
																.getExpositor())
														.concat(".<br /><br />Es un gusto poder contar con su presencia en este evento, estamos seguros que podrá adquirir excelentes ")
														.concat(" prácticas para implementación en beneficio de su empresa.<br />")
														.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Fecha de inicio: </h5>")
														.concat(Null.free(s
																.getFecha()
																+ ""))
														.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Horario: </h5>")
														.concat(horarios)
														.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Dirección: </h5>")
														.concat(direccion)
														.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Sala: </h5>")
														.concat(Null.free(s
																.getSala()))
														.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Vestimenta:  </h5>Casual")
														.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Estacionamiento:</h5> ")
														.concat("Les informamos que en esta ocasión la disponibilidad de espacios es limitada.")
														.concat("<br /><br />Iniciaremos a las ")
														.concat(Null.free(s
																.getHora()
																+ ":"))
														.concat(Null.free(s
																.getMinuto()
																+ ""))
														.concat(" hrs. en punto, por lo que agradeceremos su presencia a las ")
														.concat(entrada)
														.concat(" hrs. necesarios e invitarle un café de bienvenida.<br /><br />")
														.concat("Importante: Le recordamos que en caso de haber confirmado su asistencia y no")
														.concat(" administración señalados en los lineamientos de participación en Diplomados")
														.concat(" presentarse a la sesión correspondiente, se realizará un cargo de $250.00 por gastos de")
														.concat(" administración señalados en los lineamientos de participación en Diplomados<br/>")
														.concat("Atentamente.")
														.concat("<br /><br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Lic. Jose Luis Hernández Carmona<br />")
														.concat("Coordinador de Programa Empresario a Empresario.<br />")
														.concat("Tel.- 5395 3150 y Cel. 5580 7540 </h5><br /><br />"),
												null);
										log.debug("Enviando correo electrónico a:"
												+ envia);
										break;
									}
								}
							}
						}
					}
				}
				setListSesiones(null);
			}
		}

		if (serviciosDiplomado != null
				&& serviciosDiplomado.getAsistentes() != null) {
			ServiciosDiplomado sd = getServiciosDiplomado();
			sd = pyMEsService.getServicioDiplomado(idDiplomado, idPyME);
			for (Asistentes as : serviciosDiplomado.getAsistentes()) {
				if (as != null && as.getIdAsistente() == 0
						&& !Null.free(as.getNombre()).isEmpty()) {
					log.debug("Insertando Asistente... " + as.getNombre());
					as.setIdServiciosDiplomado(sd.getIdServiciosDiplomado());
					setMensaje(pyMEsService.saveAsistentes(as));
				} else if (as != null && as.getIdAsistente() != 0
						&& !Null.free(as.getNombre()).isEmpty()) {
					log.debug("Actualizando Asistente... "
							+ as.getIdAsistente());
					setMensaje(pyMEsService.updateAsistentes(as));
				}
			}
			if (getMensaje().getRespuesta() == 0) {
				setMensaje(new Mensaje(0,
						"Los asistentes fueron almacenados correctamente."));
			} else {
				setMensaje(new Mensaje(1,
						"Error al guardar los asistentes, intentelo mas tarde."));
			}
			setTituloDiplomado(coordinadorDiplomadosService
					.getTema(idDiplomado));
			setIdDiplomado(idDiplomado);
			setOpcion(null);
		}

		if (idDiplomado == 0) {
			log.debug("Consultando Lista de Diplomados...");
			setMenuAnios(coordinadorDiplomadosService.getMenuAnios());
			setGeneraciones(coordinadorDiplomadosService.getGeneraciones(year));
			setListDiplomados(coordinadorDiplomadosService.getMenuDiplomados(
					year, generaciones));
			if (year == 0) {
				Calendar c = new GregorianCalendar();
				setYear(c.get(Calendar.YEAR));
			}
		} else {
			log.debug("Consultando Lista de Asistentes General...");
			setTituloDiplomado(coordinadorDiplomadosService
					.getTema(idDiplomado));
			setListParticipantes(coordinadorDiplomadosService
					.getParticipantes(idDiplomado));
		}

		return SUCCESS;
	}

	@Action(value = "/diplomadoAdd", results = { @Result(name = "success", location = "ccmx.administracion.diplomados.add", type = "tiles") })
	public String diplomadoAdd() throws BaseBusinessException {
		log.debug("diplomadoAdd()");
		setMenu(4);

		if (opcion == null) {
			log.debug("Llenando combo de años...");
			Calendar c = new GregorianCalendar();
			int inicio = c.get(Calendar.YEAR);
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i <= 10; i++) {
				list.add(inicio + i);
			}
			setMenuAnios(list);
		} else {
			log.debug("Obteniendo Diplomado y Sesiones... " + idDiplomado);
			setTituloDiplomado(coordinadorDiplomadosService
					.getTema(idDiplomado));
			setListSesiones(coordinadorDiplomadosService
					.getSesiones(idDiplomado));
			setServicios(ccmxService.getIdServicio(idDiplomado));
		}

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Action(value = "/diplomadoShow", results = { @Result(name = "success", location = "ccmx.administracion.diplomados.show", type = "tiles") })
	public String diplomadoShow() throws BaseBusinessException,
			FileNotFoundException {
		log.debug("diplomadoShow");
		setMenu(4);

		if (opcion != null && opcion.equals("SesionesAdmin")) {
			log.debug("Opcion... " + opcion);
			setTituloDiplomado(coordinadorDiplomadosService
					.getTema(idDiplomado));
			setListParticipantes(coordinadorDiplomadosService
					.getParticipantes(idDiplomado));
			setOpcion("SesionesAdmin");
			setSalida("No se ha generado archivo");
		} else if (opcion != null && opcion.equals("selecPyme")) {
			log.debug("Opcion... " + opcion);
			setTituloDiplomado(coordinadorDiplomadosService
					.getTema(idDiplomado));
			setPyMEsList(coordinadorDiplomadosService.getPymes(idDiplomado));
			setOpcion("InPyme");
		} else if (opcion != null && opcion.equals("InPyme")) {
			log.debug("Opcion... " + opcion);
			setOpcion("Pagos");
			setTituloDiplomado(coordinadorDiplomadosService
					.getTema(idDiplomado));
			setServiciosDiplomado(pyMEsService.getServicioDiplomado(
					idDiplomado, idPyME));
			ServiciosDiplomado sd = new ServiciosDiplomado();
			sd.setAsistentes(pyMEsService.getAsistentes(serviciosDiplomado
					.getIdServiciosDiplomado()));
			if (serviciosDiplomado != null) {
				setListDocumentos(pyMEsService
						.getArchivosDiplomado(serviciosDiplomado
								.getIdServiciosDiplomado()));
			}
			setServiciosDiplomado(sd);
			setListParticipantes(coordinadorDiplomadosService.getParticipantes(
					idDiplomado, idPyME));
			setIdPyME(idPyME);
		} else if (opcion != null && opcion.equals("selecPyme2")) {
			log.debug("Opcion... " + opcion);
			setTituloDiplomado(coordinadorDiplomadosService
					.getTema(idDiplomado));
			setPyMEsList(coordinadorDiplomadosService.getPymes(idDiplomado));
			setOpcion("InPyme2");
		} else if (opcion != null && opcion.equals("InPyme2")) {
			log.debug("Opcion... " + opcion);
			setTituloDiplomado(coordinadorDiplomadosService
					.getTema(idDiplomado));
			setServiciosDiplomado(pyMEsService.getServicioDiplomado(
					idDiplomado, idPyME));
			ServiciosDiplomado sd = new ServiciosDiplomado();
			sd.setAsistentes(pyMEsService.getAsistentes(serviciosDiplomado
					.getIdServiciosDiplomado()));
			if (serviciosDiplomado != null) {
				setListDocumentos(pyMEsService
						.getArchivosDiplomado(serviciosDiplomado
								.getIdServiciosDiplomado()));
			}
			setServiciosDiplomado(sd);
			setListParticipantes(coordinadorDiplomadosService.getParticipantes(
					idDiplomado, idPyME));
			setIdPyME(idPyME);
			setOpcion("Participantes");
		} else if (opcion != null && opcion.equals("AdminSesiones")) {
			log.debug("Opcion... " + opcion);
			if (menuSeleccionado == 0) {
				log.debug("menuSeleccionado 0... ");
				setMensaje(coordinadorDiplomadosService
						.saveAsistencias(listParticipantes));
				List<PyMEs> temp = coordinadorDiplomadosService
						.getLiberarPymes();
				if (temp != null) {
					for (int i = 0; i < temp.size(); i++) {
						PyMEs de = temp.get(i);
						if (de != null && !de.isEstatus()
								&& de.isbPrimerNivel()) {
							if (coordinadorDiplomadosService
									.saveLiberarPymes(de.getIdUsuario())) {
								SendEmail envia = new SendEmail(
										de.getCorreoElectronico(),
										"SIA CCMX Registro de usuario PyME",
										"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimada "
												.concat(Null.free(de
														.getNombreComercial()))
												.concat(",<br /><br />Nos complace informarte que el Centro de Competitividad de México (CCMX) ha dado de alta a tu empresa en ")
												.concat("el Sistema de Vinculación del CCMX. En este sistema podrás consultar los requerimientos de las grandes empresas de México")
												.concat(" y podrás enviar cotizaciones.<br /><br />")
												.concat("Además, tu información de contacto, así como de los productos o los servicios que ofreces, estarán disponibles para que las ")
												.concat("grandes empresas u otras PyMEs que buscan oportunidades de negocio puedan identificarte.<br /><br />")
												.concat("Es muy importante que para aprovechar todas las ventajas que tiene este sistema, ingreses con la siguiente cuenta y password ")
												.concat("para actualizar y completar tu información.<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Usuario: ")
												.concat(Null.free(de
														.getCorreoElectronico()))
												.concat("<br />Contraseña: ")
												.concat(Null.free(de
														.getPassword()))
												.concat("<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>El vínculo del Sistema de Vinculación es:</h5>")
												.concat("<h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><br /><a href='http://www.ccmx.mx/vinculacion/inicio.do'>")
												.concat("http://www.ccmx.mx/vinculacion/inicio.do</a><br /><br />")
												.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>No olvides actualizar tu perfil si tus datos de contacto")
												.concat(" han cambiado o si tienes nuevos productos o servicios que ofrecer.<br /><br />")
												.concat("En caso de cualquier duda sobre la operación y funcionamiento del sistema, no dudes en ponerte en contacto con ")
												.concat("sistemadevinculacion@ccmx.org.mx.<br /><br />")
												.concat("Muchas gracias por utilizar el sistema de vinculación del CCMX.</h5>"),
										null);
								log.debug("Enviando correo electrónico:"
										+ envia);
							}
						}
					}
				}
			} else if (menuSeleccionado == 1) {
				log.debug("menuSeleccionado 1... ");
				boolean todosParticpantes = true;
				if (listParticipantes != null) {
					for (int i = 0; i < listParticipantes.size(); i++) {
						if (listParticipantes.get(i).isSeleccion()) {
							todosParticpantes = false;
							break;
						}
					}
					if (todosParticpantes) {
						for (int i = 0; i < listParticipantes.size(); i++) {
							Participantes part = listParticipantes.get(i);
							listSesiones = coordinadorDiplomadosService
									.getSesion(part.getIdSesion1());
							if (listSesiones != null && listSesiones.size() > 0) {
								Sesiones s = listSesiones.get(0);
								log.debug("Enviando correo electrónico:"
										+ part.getCorreoElectronico());
								String direccion = " No dada de alta.";
								if (s.getDomicilios() != null) {
									direccion = "Núm."
											+ s.getDomicilios().getNumExt();
									if (s.getDomicilios().getNumInt() != null) {
										direccion = direccion + " Interior "
												+ s.getDomicilios().getNumInt();
									}
									if (s.getDomicilios().getPiso() != null) {
										direccion = direccion + " Piso "
												+ s.getDomicilios().getPiso();
									}
									direccion = direccion + " Colonia "
											+ s.getDomicilios().getColonia();
									direccion = direccion
											+ " Delegacion o Municipio "
											+ s.getDomicilios().getDelegacion();
									direccion = direccion + " Estado "
											+ s.getDomicilios().getEstado();
									direccion = direccion
											+ " Codigo postal "
											+ s.getDomicilios()
													.getCodigoPostal();
								}
								String entrada = "";
								if (s.getMinuto() <= 15) {
									int min = 60 - (15 - s.getMinuto());
									int hora = s.getHora() - 1;
									if (s.getHora() == 0) {
										hora = 23;
									}
									entrada = hora + ":" + min;
								} else {
									entrada = s.getHora() + ":" + s.getMinuto();
								}
								String horarios = s.getHora() + ":"
										+ s.getMinuto() + " hrs. a "
										+ s.getHoraFin() + ":"
										+ s.getMinutoFin() + " hrs.";
								SendEmail envia = new SendEmail(
										Null.free(part.getCorreoElectronico()),
										"SIA CCMX Invitación a nueva sesión.",
										"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>"
												.concat("Estimado(a) Empresario (a):</h5> ")
												.concat("<br /><br />Nos permitimos recordarle de su próxima sesión al Diplomado de '")
												.concat(Null.free(part
														.getTema()))
												.concat("', y le damos la más cordial bienvenida a esta  ")
												.concat(Null.free(""
														+ part.getSesion()))
												.concat(" la cual será impartida en las instalaciones de la empresa ")
												.concat(s.getExpositor())
												.concat(".<br /><br />Es un gusto poder contar con su presencia en este evento, estamos seguros que podrá adquirir excelentes ")
												.concat(" prácticas para implementación en beneficio de su empresa.<br />")
												.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Fecha de inicio: </h5>")
												.concat(Null.free(s.getFecha()
														+ ""))
												.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Horario: </h5>")
												.concat(horarios)
												.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Dirección: </h5>")
												.concat(direccion)
												.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Sala: </h5>")
												.concat(Null.free(s.getSala()))
												.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Vestimenta:  </h5>Casual")
												.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Estacionamiento:</h5> ")
												.concat("Les informamos que en esta ocasión la disponibilidad de espacios es limitada.")
												.concat("<br /><br />Iniciaremos a las ")
												.concat(Null.free(s.getHora()
														+ ":"))
												.concat(Null.free(s.getMinuto()
														+ ""))
												.concat(" hrs. en punto, por lo que agradeceremos su presencia a las ")
												.concat(entrada)
												.concat(" hrs. necesarios e invitarle un café de bienvenida.<br /><br />")
												.concat("Importante: Le recordamos que en caso de haber confirmado su asistencia y no")
												.concat(" administración señalados en los lineamientos de participación en Diplomados")
												.concat(" presentarse a la sesión correspondiente, se realizará un cargo de $250.00 por gastos de")
												.concat(" administración señalados en los lineamientos de participación en Diplomados<br/>")
												.concat("Atentamente.")
												.concat("<br /><br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Lic. Jose Luis Hernández Carmona<br />")
												.concat("Coordinador de Programa Empresario a Empresario.<br />")
												.concat("Tel.- 5395 3150 y Cel. 5580 7540 </h5><br /><br />"),
										null);
								log.debug("Enviando correo electrónico a:"
										+ envia);
							} else {
								setMensaje(new Mensaje(0,
										"Por el momento no es posible enviar invitacioes."));
							}
						}
					} else {
						for (int i = 0; i < listParticipantes.size(); i++) {
							Participantes part = listParticipantes.get(i);
							if (part.isSeleccion()) {
								listSesiones = coordinadorDiplomadosService
										.getSesion(part.getIdSesion1());
								if (listSesiones != null
										&& listSesiones.size() > 0) {
									Sesiones s = listSesiones.get(0);
									log.debug("Enviando correo electrónico:"
											+ part.getCorreoElectronico());
									String direccion = " No dada de alta.";
									if (s.getDomicilios() != null) {
										direccion = "Núm."
												+ s.getDomicilios().getNumExt();
										if (s.getDomicilios().getNumInt() != null) {
											direccion = direccion
													+ " Interior "
													+ s.getDomicilios()
															.getNumInt();
										}
										if (s.getDomicilios().getPiso() != null) {
											direccion = direccion
													+ " Piso "
													+ s.getDomicilios()
															.getPiso();
										}
										direccion = direccion
												+ " Colonia "
												+ s.getDomicilios()
														.getColonia();
										direccion = direccion
												+ " Delegacion o Municipio "
												+ s.getDomicilios()
														.getDelegacion();
										direccion = direccion + " Estado "
												+ s.getDomicilios().getEstado();
										direccion = direccion
												+ " Codigo postal "
												+ s.getDomicilios()
														.getCodigoPostal();
									}
									String entrada = "";
									if (s.getMinuto() <= 15) {
										int min = 60 - (15 - s.getMinuto());
										int hora = s.getHora() - 1;
										if (s.getHora() == 0) {
											hora = 23;
										}
										entrada = hora + ":" + min;
									} else {
										entrada = s.getHora() + ":"
												+ s.getMinuto();
									}
									String horarios = s.getHora() + ":"
											+ s.getMinuto() + " hrs. a "
											+ s.getHoraFin() + ":"
											+ s.getMinutoFin() + " hrs.";
									SendEmail envia = new SendEmail(
											Null.free(part
													.getCorreoElectronico()),
											"SIA CCMX Invitación a nueva sesión.",
											"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>"
													.concat("Estimado(a) Empresario (a):</h5> ")
													.concat("<br /><br />Nos permitimos recordarle de su próxima sesión al Diplomado de '")
													.concat(Null.free(part
															.getTema()))
													.concat("', y le damos la más cordial bienvenida a esta  ")
													.concat(Null.free(""
															+ part.getSesion()))
													.concat(" la cual será impartida en las instalaciones de la empresa ")
													.concat(s.getExpositor())
													.concat(".<br /><br />Es un gusto poder contar con su presencia en este evento, estamos seguros que podrá adquirir excelentes ")
													.concat(" prácticas para implementación en beneficio de su empresa.<br />")
													.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Fecha de inicio: </h5>")
													.concat(Null.free(s
															.getFecha() + ""))
													.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Horario: </h5>")
													.concat(horarios)
													.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Dirección: </h5>")
													.concat(direccion)
													.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Sala: </h5>")
													.concat(Null.free(s
															.getSala()))
													.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Vestimenta:  </h5>Casual")
													.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Estacionamiento:</h5> ")
													.concat("Les informamos que en esta ocasión la disponibilidad de espacios es limitada.")
													.concat("<br /><br />Iniciaremos a las ")
													.concat(Null.free(s
															.getHora() + ":"))
													.concat(Null.free(s
															.getMinuto() + ""))
													.concat(" hrs. en punto, por lo que agradeceremos su presencia a las ")
													.concat(entrada)
													.concat(" hrs. necesarios e invitarle un café de bienvenida.<br /><br />")
													.concat("Importante: Le recordamos que en caso de haber confirmado su asistencia y no")
													.concat(" administración señalados en los lineamientos de participación en Diplomados")
													.concat(" presentarse a la sesión correspondiente, se realizará un cargo de $250.00 por gastos de")
													.concat(" administración señalados en los lineamientos de participación en Diplomados<br/>")
													.concat("Atentamente.")
													.concat("<br /><br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Lic. Jose Luis Hernández Carmona<br />")
													.concat("Coordinador de Programa Empresario a Empresario.<br />")
													.concat("Tel.- 5395 3150 y Cel. 5580 7540 </h5><br /><br />"),
											null);
									log.debug("Enviando correo electrónico a:"
											+ envia);
									setMensaje(new Mensaje(0,
											"Invitaciones enviadas correctamente"));
								} else {
									setMensaje(new Mensaje(1,
											"Por el momento no es posible enviar invitacioes."));
								}
							}
						}
					}
				}
				log.debug("Mensaje... " + getMensaje());
				if (getMensaje() != null) {
					if (getMensaje().getRespuesta() == 0) {
						setMensaje(new Mensaje(0,
								"Invitaciones enviadas correctamente"));
					} else {
						setMensaje(new Mensaje(1,
								"Por el momento no es posible enviar invitacioes."));
					}
				} else {
					setMensaje(new Mensaje(1,
							"Seleccione los asistentes a los que se les enviará invitación."));
				}

			} else if (menuSeleccionado == 2) {
				log.debug("menuSeleccionado 2... ");
				log.debug("Agregando a lista de diplomas... ");
				boolean todosParticpantes = true;
				if (listParticipantes != null) {
					for (int i = 0; i < listParticipantes.size(); i++) {
						if (listParticipantes.get(i).isSeleccion()) {
							todosParticpantes = false;
							break;
						}
					}
					listDiplomas = new ArrayList<Participantes>();
					if (todosParticpantes) {
						for (int i = 0; i < listParticipantes.size(); i++) {
							if (listParticipantes.get(i).isResagado()) {
								setMensaje(new Mensaje(1,
										"Algunos participantes fueron omitidos debido a que estan rezagados."));
							} else {
								listDiplomas.add(listParticipantes.get(i));
							}
						}
					} else {
						for (int i = 0; i < listParticipantes.size(); i++) {
							if (listParticipantes.get(i).isSeleccion()) {
								if (listParticipantes.get(i).isResagado()) {
									setMensaje(new Mensaje(1,
											"Algunos participantes fueron omitidos debido a que estan rezagados."));
								} else {
									listDiplomas.add(listParticipantes.get(i));
								}
							}
						}
					}
				}
				log.debug(listDiplomas);
				setOpcion("Diplomas");
				setTituloDiplomado(coordinadorDiplomadosService
						.getTema(idDiplomado));
				setListParticipantes(null);
				return SUCCESS;

			} else if (menuSeleccionado == 3) {
				log.debug("menuSeleccionado 3... ");
				List<Participantes> parts = null;
				if (sesion1 || sesion2 || sesion3 || sesion4) {
					parts = coordinadorDiplomadosService
							.getParticipantesPorSesion(idDiplomado, sesion1,
									sesion2, sesion3, sesion4);
				}
				if (parts != null && parts.size() > 0) {
					Usuario usuario = getUsuario();
					setSalida(null);
					String direccion = ServletActionContext.getRequest()
							.getSession().getServletContext().getRealPath("/");
					try {
						JasperDesign design = JRXmlLoader
								.load((new FileInputStream(direccion
										+ "/jasper/participantes.jrxml")));/* "WEB-INF\\jasper\\reporte.jrxml" */
						JasperCompileManager.compileReportToFile(
								design,
								direccion + "/jasper/participantes"
										+ usuario.getIdUsuario() + ".jasper");
						@SuppressWarnings({ "rawtypes" })
						Map parameters = new HashMap();
						parameters.put("SUBREPORT_DIR", direccion
								+ "/jasper/participantes\\");
						JasperPrint jasperPrint = JasperFillManager.fillReport(
								direccion + "/jasper/participantes"
										+ usuario.getIdUsuario() + ".jasper",
								parameters, new JRBeanCollectionDataSource(
										parts));
						OutputStream output = new FileOutputStream(new File(
								direccion + "/jasper/participantes"
										+ usuario.getIdUsuario() + ".xlsx"));
						JRXlsxExporter exporterXLS = new JRXlsxExporter();
						exporterXLS.setParameter(
								JRXlsExporterParameter.JASPER_PRINT,
								jasperPrint);
						exporterXLS.setParameter(
								JRXlsExporterParameter.OUTPUT_STREAM, output);
						exporterXLS.setParameter(
								JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
								Boolean.TRUE);
						exporterXLS.setParameter(
								JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
								Boolean.TRUE);
						exporterXLS
								.setParameter(
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
						setSalida("No ha generado el arhivo, reportelo al administrador e intentelo mas tarde.");
					}
				}
			}
			setListSesiones(null);
			setTituloDiplomado(coordinadorDiplomadosService
					.getTema(idDiplomado));
			setListParticipantes(coordinadorDiplomadosService
					.getParticipantes(idDiplomado));
			setOpcion("SesionesAdmin");

		} else if (opcion != null && opcion.equals("Inasistencias")) {
			log.debug("Opcion... " + opcion);
			setIdPyME(idPyME);
			setIdDiplomado(idDiplomado);
			setListInacistencias(coordinadorDiplomadosService
					.getInasistentes(idDiplomado));
			setOpcion("Inasistencias");

		} else if (opcion != null && opcion.equals("Facturacion")) {
			log.debug("Opcion... " + opcion);
			if (menuSeleccionado == 1) {
				log.debug("menuSeleccionado 1... ");
				boolean isPorPyme = true;
				if (listParticipantes != null) {
					for (int i = 0; i < listParticipantes.size(); i++) {
						if (listParticipantes.get(i).isSeleccion()) {
							isPorPyme = false;
							break;
						}
					}
				}
				setMensaje(coordinadorDiplomadosService
						.saveFacturas(listParticipantes));
				if (getMensaje().getRespuesta() == 0) {
					PyMEs py = coordinadorDiplomadosService.getPyme(idPyME);
					Domicilios dom = pyMEsService.getDomicilio(Integer
							.parseInt(pyMEsService.getIdDomicilio(idPyME)));
					if (dom == null) {
						dom = new Domicilios();
					}
					if (isPorPyme) {
						String text = "<table border=1 cellspacing=0 cellpadding=2 bordercolor='666633'>"
								+ "<tr><td>Factura</td><td>Razon social</td><td>Domicilio fiscal</td>"
								+ "<td>Participante</td><td>RFC</td><td>Forma de pago</td>"
								+ "<td>No.CTA(4 Ilt. Dig.)</td><td>Partc a Facturar</td></tr>"
								+ " <tr><td>"
								+ Null.free("")
								+ "</td><td>"
								+ Null.free(py.getNombreComercial())
								+ "</td><td>"
								+ "Calle: "
								+ Null.free(dom.getCalle())
								+ ""
								+ "Número: "
								+ Null.free(dom.getNumExt())
								+ " Interior: "
								+ Null.free(dom.getNumInt())
								+ "<br/>"
								+ "Colonia: "
								+ Null.free(dom.getColonia())
								+ " "
								+ "Delegación/Municipio: "
								+ Null.free(dom.getDelegacion())
								+ " "
								+ "Estado: "
								+ Null.free(dom.getEstado())
								+ "<br/>C.P."
								+ Null.free(dom.getCodigoPostal())
								+ "</td><td>"
								+ Null.free("")
								+ "</td><td>"
								+ Null.free(py.getRfc())
								+ "</td><td>"
								+ "Transferencia"
								+ "</td><td>"
								+ "0"
								+ "</td><td>1</td></tr>";
						SendEmail envia = new SendEmail(
								"nayla.martinez@caintra.org.mx",// TODO Cambiar
																// correo para
																// pruebas
								"SIA CCMX Solicitud de factura", text, null);
						log.debug("Enviando correo electrónico a:" + envia);
					} else {
						String participantes = "";
						String text = "<table border=1 cellspacing=0 cellpadding=2 bordercolor='666633'>";
						text = text
								+ "<tr><td>Factura</td><td>Razon social</td><td>Domicilio fiscal</td>"
								+ "<td>Participante</td><td>RFC</td><td>Forma de pago</td>"
								+ "<td>No.CTA(4 Ult. Dig.)</td><td>Partc a Facturar</td></tr>";
						for (int i = 0; i < listParticipantes.size(); i++) {
							Participantes p = listParticipantes.get(i);
							if (p.isSeleccion()) {
								Participantes par = coordinadorDiplomadosService
										.getParticipante(p.getId());
								participantes = participantes + "Nombre: "
										+ Null.free(par.getNombre()) + "<br/>";
								text = text + " <tr><td>"
										+ Null.free(p.getNumPago())
										+ "</td><td>"
										+ Null.free(py.getNombreComercial())
										+ "</td><td>" + "Calle: "
										+ Null.free(dom.getCalle()) + ""
										+ "Número: "
										+ Null.free(dom.getNumExt())
										+ " Interior: "
										+ Null.free(dom.getNumInt()) + "<br/>"
										+ "Colonia: "
										+ Null.free(dom.getColonia()) + " "
										+ "Delegación/Municipio: "
										+ Null.free(dom.getDelegacion()) + " "
										+ "Estado: "
										+ Null.free(dom.getEstado())
										+ "<br/>C.P."
										+ Null.free(dom.getCodigoPostal())
										+ "</td><td>"
										+ Null.free(par.getNombre())
										+ "</td><td>" + Null.free(py.getRfc())
										+ "</td><td>" + "Transferecia"
										+ "</td><td>" + "0"
										+ "</td><td>1</td></tr>";
							}
						}
						text = text + "</table>";
						SendEmail envia = new SendEmail(
								"nayla.martinez@caintra.org.mx",// TODO Cambiar
																// correo para
																// pruebas
																// nayla.martinez@caintra.org.mx
								"SIA CCMX Solicitud de factura", text, null);
						log.debug("Enviando correo electrónico a:" + envia);
					}
				}
			} else {
				log.debug("menuSeleccionado 2... ");
				ServiciosDiplomado sd = pyMEsService.getServicioDiplomado(
						idDiplomado, idPyME);
				Documento d = null;
				if (serviciosDiplomado != null
						&& serviciosDiplomado.getArchivos() != null) {
					log.debug(serviciosDiplomado.getArchivos().getUpload()
							.size());
					for (int i = 0; i < serviciosDiplomado.getArchivos()
							.getUpload().size(); i++) {
						log.debug("Insertando Archivo... " + i);
						d = new Documento();
						d.setIs(new FileInputStream(serviciosDiplomado
								.getArchivos().getUpload().get(i)));
						d.setIdServiciosDiplomado(sd.getIdServiciosDiplomado());
						d.setNombre(serviciosDiplomado.getArchivos()
								.getUploadFileName().get(i));
						d.setDescripcionArchivo(serviciosDiplomado
								.getArchivos().getDescripcionArchivos().get(i));
						setMensaje(pyMEsService.saveArchivoServicio(d));
					}
				}
				if (idArchivos != null && !idArchivos.trim().equals("")) {
					log.debug("Eliminando archivos..." + idArchivos);
					setMensaje(pyMEsService.deleteArchivoPago(idArchivos));
				}
				setMensaje(coordinadorDiplomadosService
						.savePagos(listParticipantes));
			}
			setOpcion("Pagos");
			setTituloDiplomado(coordinadorDiplomadosService
					.getTema(idDiplomado));
			setServiciosDiplomado(pyMEsService.getServicioDiplomado(
					idDiplomado, idPyME));
			ServiciosDiplomado sd = new ServiciosDiplomado();
			sd.setAsistentes(pyMEsService.getAsistentes(serviciosDiplomado
					.getIdServiciosDiplomado()));
			if (serviciosDiplomado != null) {
				setListDocumentos(pyMEsService
						.getArchivosDiplomado(serviciosDiplomado
								.getIdServiciosDiplomado()));
			}
			setServiciosDiplomado(sd);
			setListParticipantes(coordinadorDiplomadosService.getParticipantes(
					idDiplomado, idPyME));
			setIdPyME(idPyME);
		}

		return SUCCESS;
	}

	@Action(value = "/usuariosShow", results = { @Result(name = "success", location = "ccmx.administracion.usuarios.show", type = "tiles") })
	public String usuariosShow() throws BaseBusinessException {
		log.debug("usuariosShow()");
		setMenu(5);
		return SUCCESS;
	}

	@Action(value = "/usuariosSend", results = { @Result(name = "success", location = "ccmx.administracion.usuarios.show", type = "tiles") })
	public String usuariosSend() throws BaseBusinessException {
		log.debug("usuariosSend()");
		setMenu(5);
		log.debug("enviando correo a " + correo);

		SendEmail envia = new SendEmail(
				correo,
				"SIA CCMX Credenciales de acceso",
				"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado usuario,"
						.concat("<br /><br />Nos complace informante que el Centro de Competitividad de México (CCMX) ha dado de alta tu perfil ")
						.concat("en el Sistema de Vinculación del CCMX. Los accesos para dicho sistema ")
						.concat("son los siguientes:<br /><br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Cuenta: ")
						.concat(Null.free(getCorreo()))
						.concat("<br />Contraseña: ")
						.concat(Null.free(getCredencial()))
						.concat("<br />El vínculo del Sistema de Vinculación es: ")
						.concat("<a href='http://www.ccmx.mx/vinculacion/inicio.do'>")
						.concat("http://www.ccmx.mx/vinculacion/inicio.do</a><br /><br />")
						.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>En caso de cualquier duda sobre la operación y ")
						.concat("funcionamiento del sistema, no dudes en ponerte en contacto con Andrés Blancas al correo: andres.blancas@ccmx.org.mx.<br /><br />")
						.concat("Muchas gracias por utilizar el sistema de vinculación del CCMX.</h5>"),
				null);
		log.debug("Enviando correo electrónico:" + envia);

		setMensaje(new Mensaje(0,
				"Se ha enviado un correo electrónico al usuario seleccionado."));
		return SUCCESS;
	}

	@Action(value = "/documentosShow", results = { @Result(name = "success", location = "ccmx.administracion.documentos.show", type = "tiles") })
	public String documentosShow() throws ProductosNoObtenidosException,
			PyMEsNoObtenidasException, DocumentoNoAlmacenadoException {
		log.debug("documentosShow()");
		setMenu(6);
		if (archivoManual != null) {
			log.debug("guardando archivo, rol: " + rolManual);
			Documento d = new Documento();
			try {
				d.setNombre(archivoManualFileName);
				d.setDescripcionArchivo(descArchivoManual);
				d.setIs(new FileInputStream(archivoManual));
			} catch (Exception e) {
				log.error("error al intentar guardar el documento");
				e.printStackTrace();
			}
			setMensaje(ccmxService.saveDocumento(d, rolManual));
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Action(value = "/reportesShow", results = {
			@Result(name = "success", location = "ccmx.administracion.reportes.list", type = "tiles"),
			@Result(name = "input", location = "ccmx.administracion.reportes.list", type = "tiles"),
			@Result(name = "error", location = "ccmx.administracion.reportes.list", type = "tiles") })
	public String reportesShow() throws BaseBusinessException {
		setMenu(7);
		if (opcion != null && opcion.equals("servicios")) {
			setOpcion(opcion);
			setTractorasList(reportService.getTractoras());
			setConsultorasList(reportService.getConsultoras());
			setMenuAnticipo(reportService.getMenuFacturaAnticipo());
			setMenuAnticipoFiniquito(reportService
					.getMenuFacturaAnticipoFiniquito());
			setSesionInformativa(reportService.getMenuSesionInformativa());
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
			log.debug("currentuser" + usuario);
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
				int diplomados = reportService.getParticipantesDiplomado(filtros);
				filtros.setEstatus("DIAGNOSTICO");
				int diagnostico = reportService.getPorEstatus(filtros);
				filtros.setEstatus("PLAN DE MEJORA");
				int planMejora = reportService.getPorEstatus(filtros);
				filtros.setEstatus("IMPLEMENTACION");
				int implementacion = reportService.getPorEstatus(filtros);
				filtros.setEstatus("EVALUACION");
				int evaluacion = reportService.getPorEstatus(filtros);
				filtros.setEstatus("CIERRE");
				int cierre = reportService.getPorEstatus(filtros);
				filtros.setEstatus("CANCELADA");
				int cancelada = reportService.getPorEstatus(filtros);
				filtros.setEstatus("NO ACEPTO");
				int noAcepto = reportService.getPorEstatus(filtros);
				filtros.setEstatus("PENDIENTE");
				int pendiente = reportService.getPorEstatus(filtros);
				filtros.setEstatus("DIFERIDA");
				int diferida = reportService.getPorEstatus(filtros);
				filtros.setEstatus("CONCLUIDA");
				int concluida = reportService.getPorEstatus(filtros);
				int etapa =  diagnostico + planMejora +implementacion
							+ evaluacion + concluida;
				int totalConsultoria = etapa + cancelada + noAcepto +
							 diferida;  
				try {
					setSalida(null);
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
					parameters.put("diagnostico",diagnostico);					
					parameters.put("planMejora",planMejora);					
					parameters.put("implementacion",implementacion);					
					parameters.put("evaluacion",evaluacion);					
					parameters.put("cierre",cierre);					
					parameters.put("cancelada",cancelada);					
					parameters.put("noAcepto",noAcepto);					
					parameters.put("pendiente",pendiente);					
					parameters.put("diferida",diferida);					
					parameters.put("concluida",concluida);		
					parameters.put("capacitacion",totalConsultoria);
					parameters.put("etapaFin",etapa);
					parameters.put("totalDiplomado",diplomados);
					if(totalConsultoria>0){
						parameters.put("diferidaP",(int)(diferida*100) / totalConsultoria);
						parameters.put("diagnosticoP",(int)(diagnostico*100) / totalConsultoria);
						parameters.put("planMejoraP",(int)(planMejora*100) / totalConsultoria);
						parameters.put("implementacionP",(int)(implementacion*100) / totalConsultoria);
						parameters.put("evaluacionP",(int)(evaluacion*100) / totalConsultoria);
						parameters.put("cierreP",(int)(cierre*100) / totalConsultoria);
						parameters.put("canceladaP",(int)(cancelada*100) / totalConsultoria);
						parameters.put("noAceptoP",(int)(noAcepto*100) / totalConsultoria);
						parameters.put("pendienteP",(int)(pendiente*100) / totalConsultoria);
						parameters.put("concluidaP",(int)(concluida*100) / totalConsultoria);
						parameters.put("etapaFinP",(int)(etapa*100) / totalConsultoria);
						parameters.put("totalPDiplomado",(int)((diplomados*100)/totalConsultoria));		
					} else{
						parameters.put("diferidaP",0);
						parameters.put("diagnosticoP",0);
						parameters.put("planMejoraP",0);
						parameters.put("implementacionP",0);
						parameters.put("evaluacionP",0);
						parameters.put("cierreP",0);
						parameters.put("canceladaP",0);
						parameters.put("noAceptoP",0);
						parameters.put("pendienteP",0);
						parameters.put("concluidaP",0);
						parameters.put("etapaFinP",0);
						parameters.put("totalPDiplomado",0);	
					}		
					
					parameters.put("radarAntesControl",
							reportService.getPromedioRadarAntes(filtros) * 1.0);
					parameters
							.put("radarDespuesControl", reportService
									.getPromedioRadarDespues(filtros) * 1.0);
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
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.getCause() + "\n" + e.getMessage() + "\n"
							+ e.toString());
					setSalida("No ha generado el arhivo, reportelo al administrador e intentelo mas tarde.");
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
					setSalida(null);
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
					parameters.put("abono1Total",
							reportService.getTotalFacturas("Abono1", filtros));
					parameters.put("abono2Total",
							reportService.getTotalFacturas("Abono2", filtros));
					parameters
							.put("anticipoTotal", reportService
									.getTotalFacturas("Anticipo", filtros));
					parameters.put("finiquitoTotal", reportService
							.getTotalFacturas("Finiquito", filtros));
					parameters.put("empresaPagada",
							reportService.getEmpresasPagadas(true, filtros));
					parameters.put("empresaSinPago",
							reportService.getEmpresasPagadas(false, filtros));
					parameters.put("facturaTotal",
							reportService.getCantidadPagadas(false, filtros));
					parameters.put("facturaPendiente",
							reportService.getCantidadPagadas(true, filtros));
					parameters.put("IS_IGNORE_PAGINATION", true);
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
					setSalida("No ha generado el arhivo, reportelo al administrador e intentelo mas tarde.");
				}
			}
			return SUCCESS;
		} else if (opcion != null && opcion.trim().equals("pyRepor")) {
			log.debug("Generando reporte de pymes");
			String direccion = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("/");
			Usuario usuario = getUsuario();
			if (filtros == null) {
				filtros = new Filtros();
			}
			if (usuario.getRol().equals("AdmnistradorConsultor")
					|| usuario.getRol().equals("Tractora")
					|| usuario.getRol().equals("Comprador")
					|| usuario.getRol().equals("Consultor")) {
				filtros.setId(usuario.getIdUsuario());
				if (usuario.getRol().equals("AdmnistradorConsultor")) {
					filtros.setPermisos(3);
				} else if (usuario.getRol().equals("Tractora")) {
					filtros.setPermisos(1);
				} else if (usuario.getRol().equals("Comprador")) {
					filtros.setPermisos(2);
				} else {
					filtros.setPermisos(4);
				}
			}
			log.debug("" + filtros);
			List<PyMEsReporte> pymesLists = new ArrayList<PyMEsReporte>();
			PyMEsReporte temp;
			List<PyMEsReporte> pymesList = reportService
					.getPymesReporte(filtros);
			List<TotalEmpresas> totalEmpresas = reportService
					.getEmpresasByConsultora(filtros);
			for (int i = 0; i < pymesList.size(); i++) {
				log.debug(totalEmpresas.size() > i);
				if (totalEmpresas.size() > i) {
					temp = pymesList.get(i);
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
					setSalida(null);
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
					setSalida("No ha generado el arhivo, reportelo al administrador e intentelo mas tarde.");
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
			List<IndicadoresPyMEs> indicadoresList = new ArrayList<IndicadoresPyMEs>();
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
					parameters.put("t1", reportService.getIndicePeriodo(0));
					parameters.put("t2", reportService.getIndicePeriodo(1));
					parameters.put("t3", reportService.getIndicePeriodo(2));
					parameters.put("t4", reportService.getIndicePeriodo(3));
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
					setSalida("No ha generado el arhivo, reportelo al administrador e intentelo mas tarde.");
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
		setMenu(7);
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

	public List<Tractoras> getListTractoras()
			throws TractorasNoObtenidasException {
		setListTractoras(ccmxService.getTractoras());
		return listTractoras;
	}

	public void setListTractoras(List<Tractoras> listTractoras) {
		this.listTractoras = listTractoras;
	}

	public List<List<Diplomados>> getListDiplomados() {
		return listDiplomados;
	}

	public void setListDiplomados(List<List<Diplomados>> listDiplomados) {
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

	public int getEstatusA() {
		return estatusA;
	}

	public void setEstatusA(int estatusA) {
		this.estatusA = estatusA;
	}

	public List<Tractoras> getListDetallesTractoras()
			throws TractorasNoObtenidasException {
		setListDetallesTractoras(ccmxService.getDetalleTractora());
		return listDetallesTractoras;
	}

	public void setListDetallesTractoras(List<Tractoras> listDetallesTractoras) {
		this.listDetallesTractoras = listDetallesTractoras;
	}

	public int getGeneraciones() {
		return generaciones;
	}

	public void setGeneraciones(int generaciones) {
		this.generaciones = generaciones;
	}

	public int getIdDiplomado() {
		return idDiplomado;
	}

	public void setIdDiplomado(int idDiplomado) {
		this.idDiplomado = idDiplomado;
	}

	public String getTituloDiplomado() {
		return tituloDiplomado;
	}

	public void setTituloDiplomado(String tituloDiplomado) {
		this.tituloDiplomado = tituloDiplomado;
	}

	public Diplomados getDiplomado() {
		return diplomado;
	}

	public void setDiplomado(Diplomados diplomado) {
		this.diplomado = diplomado;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Integer> getMenuAnios() {
		return menuAnios;
	}

	public void setMenuAnios(List<Integer> menuAnios) {
		this.menuAnios = menuAnios;
	}

	public List<Participantes> getListParticipantes() {
		return listParticipantes;
	}

	public void setListParticipantes(List<Participantes> listParticipantes) {
		this.listParticipantes = listParticipantes;
	}

	public List<Sesiones> getListSesiones() {
		return listSesiones;
	}

	public void setListSesiones(List<Sesiones> listSesiones) {
		this.listSesiones = listSesiones;
	}

	public int getNumeroSesiones() {
		return numeroSesiones;
	}

	public void setNumeroSesiones(int numeroSesiones) {
		this.numeroSesiones = numeroSesiones;
	}

	public String getServicios() {
		return servicios;
	}

	public void setServicios(String servicios) {
		this.servicios = servicios;
	}

	public List<Integer> getListIds() {
		return listIds;
	}

	public void setListIds(List<Integer> listIds) {
		this.listIds = listIds;
	}

	public int getIdPyME() {
		return idPyME;
	}

	public void setIdPyME(int idPyME) {
		this.idPyME = idPyME;
	}

	public ServiciosDiplomado getServiciosDiplomado() {
		return serviciosDiplomado;
	}

	public void setServiciosDiplomado(ServiciosDiplomado serviciosDiplomado) {
		this.serviciosDiplomado = serviciosDiplomado;
	}

	public List<Documento> getListDocumentos() {
		return listDocumentos;
	}

	public void setListDocumentos(List<Documento> listDocumentos) {
		this.listDocumentos = listDocumentos;
	}

	public List<PyMEs> getPyMEsList() {
		return pyMEsList;
	}

	public void setPyMEsList(List<PyMEs> pyMEsList) {
		this.pyMEsList = pyMEsList;
	}

	public int getMenuSeleccionado() {
		return menuSeleccionado;
	}

	public void setMenuSeleccionado(int menuSeleccionado) {
		this.menuSeleccionado = menuSeleccionado;
	}

	public List<Participantes> getListDiplomas() {
		return listDiplomas;
	}

	public void setListDiplomas(List<Participantes> listDiplomas) {
		this.listDiplomas = listDiplomas;
	}

	public List<Participantes> getListInacistencias() {
		return listInacistencias;
	}

	public void setListInacistencias(List<Participantes> listInacistencias) {
		this.listInacistencias = listInacistencias;
	}

	public boolean isSesion1() {
		return sesion1;
	}

	public void setSesion1(boolean sesion1) {
		this.sesion1 = sesion1;
	}

	public boolean isSesion2() {
		return sesion2;
	}

	public void setSesion2(boolean sesion2) {
		this.sesion2 = sesion2;
	}

	public boolean isSesion3() {
		return sesion3;
	}

	public void setSesion3(boolean sesion3) {
		this.sesion3 = sesion3;
	}

	public boolean isSesion4() {
		return sesion4;
	}

	public void setSesion4(boolean sesion4) {
		this.sesion4 = sesion4;
	}

	public String getIdArchivos() {
		return idArchivos;
	}

	public void setIdArchivos(String idArchivos) {
		this.idArchivos = idArchivos;
	}

	public String getParticipante() {
		return participante;
	}

	public void setParticipante(String participante) {
		this.participante = participante;
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

	public List<Usuario> getUsuarios() throws TractorasNoObtenidasException {
		setUsuarios(ccmxService.getUsuarios());
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCredencial() {
		return credencial;
	}

	public void setCredencial(String credencial) {
		this.credencial = credencial;
	}

	public List<FiltrosGenerales> getSesionInformativa() {
		return sesionInformativa;
	}

	public void setSesionInformativa(List<FiltrosGenerales> sesionInformativa) {
		this.sesionInformativa = sesionInformativa;
	}

	public String getTotal() throws PyMEsNoObtenidasException {
		setTotal(ccmxService.getPyMEsTotal());
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getActivas() throws PyMEsNoObtenidasException {
		setActivas(ccmxService.getPyMEsActivas());
		return activas;
	}

	public void setActivas(String activas) {
		this.activas = activas;
	}

	public String getExpediente() throws PyMEsNoObtenidasException {
		setExpediente(ccmxService.getPyMEsExpediente());
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public int getRolManual() {
		return rolManual;
	}

	public void setRolManual(int rolManual) {
		this.rolManual = rolManual;
	}

	public File getArchivoManual() {
		return archivoManual;
	}

	public void setArchivoManual(File archivoManual) {
		this.archivoManual = archivoManual;
	}

	public String getArchivoManualFileName() {
		return archivoManualFileName;
	}

	public void setArchivoManualFileName(String archivoManualFileName) {
		this.archivoManualFileName = archivoManualFileName;
	}

	public String getDescArchivoManual() {
		return descArchivoManual;
	}

	public void setDescArchivoManual(String descArchivoManual) {
		this.descArchivoManual = descArchivoManual;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getNuevo() {
		return nuevo;
	}

	public void setNuevo(String nuevo) {
		this.nuevo = nuevo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
