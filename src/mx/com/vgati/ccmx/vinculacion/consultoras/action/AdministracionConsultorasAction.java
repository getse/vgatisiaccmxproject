/*
 * AdministracionConsultorasAction.java        01/04/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.consultoras.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mx.com.vgati.ccmx.vinculacion.ccmx.service.CCMXService;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Facturas;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Pagos;
import mx.com.vgati.ccmx.vinculacion.consultoras.exception.ConsultoraNoObtenidaException;
import mx.com.vgati.ccmx.vinculacion.consultoras.service.ConsultorasService;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
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
@SuppressWarnings("serial")
@Namespaces({ @Namespace(value = "consultor/administracion") })
public class AdministracionConsultorasAction extends AbstractBaseAction {

	private int menu = 1;
	private static final String[] op = { "CONSULTORES", "PyMEs", "FACTURACIÓN",
			"REPORTES" };
	private static final String[] fr = { "consultoraConsultoresShow.do",
			"consultoraPyMEsShow.do", "consultoraFacturacionShow.do",
			"consultoraReportesShow.do" };

	private ConsultorasService consultorasService;
	private TractorasService tractorasService;
	private InitService initService;
	private CCMXService ccmxService;
	private PyMEsService pyMEsService;
	private Consultoras consultoras;
	private ReportService reportService;
	private List<Tractoras> tractorasList;
	private List<Consultoras> consultorasList;
	private List<CCMXParticipantes> serviciosList;
	private String opcion;
	private Filtros filtros;
	private String salida;
	private InputStream archivo;
	private List<PyMEs> pymesList;
	private List<PyMEs> listPyMEs;
	private List<Consultoras> consultorList;
	private String credenciales;
	private Mensaje mensaje;
	public int idUsuario;
	public List<String> pymesSelected;
	private int cat1;
	private int cat2;
	private int cat3;
	private int cat4;
	private int cat5;
	private String ant1;
	private PyMEs pyMEs;
	private List<CatScianCcmx> listCatProductos;
	private List<CatScianCcmx> listCat2;
	private List<CatScianCcmx> listCat3;
	private List<CatScianCcmx> listCat4;
	private List<CatScianCcmx> listCat5;
	private EstadosVenta estadosVentas;
	private String busqueda;
	private String estado;
	private String cveScian;
	private List<Pagos> pagosList;
	private String seleccion;
	private List<String> anticipoList;
	private List<String> abono1List;
	private List<String> abono2List;
	private List<String> finiquitoList;
	private String idFactura;
	private int idConsultor;
	private Domicilios domicilios;
	private Indicadores indicadores;
	private List<FiltrosGenerales> menuAnticipo;
	private List<FiltrosGenerales> menuFiniquito;
	private List<FiltrosGenerales> menuAnticipoFiniquito;
	private List<FiltrosGenerales> menuCedula;
	private List<FiltrosGenerales> menuEstatus;
	private List<Facturas> facturasList;
	private ServiciosConsultoria serviciosConsultoria;
	private Indicadores indicadoresMes;
	private RelPyMEsTractoras relPyMEsTractoras;
	private int idArchivo;

	public void setTractorasService(TractorasService tractorasService) {
		this.tractorasService = tractorasService;
	}

	public void setCcmxService(CCMXService ccmxService) {
		this.ccmxService = ccmxService;

	}

	public void setPyMEsService(PyMEsService pyMEsService) {
		this.pyMEsService = pyMEsService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public void setConsultorasService(ConsultorasService consultorasService) {
		this.consultorasService = consultorasService;
	}

	public void setInitService(InitService initService) {
		this.initService = initService;
	}

	@Action(value = "/consultoraConsultoresShow", results = {
			@Result(name = "success", location = "consultoras.administracion.consultores.show", type = "tiles"),
			@Result(name = "input", location = "consultoras.administracion.consultores.add", type = "tiles"),
			@Result(name = "error", location = "consultoras.administracion.consultores.add", type = "tiles") })
	public String consultoraConsultoresShow() throws BaseBusinessException {
		log.debug("consultoraConsultoresShow()");
		boolean existeUsuario = false;
		if (pymesSelected != null && consultoras != null) {
			consultoras = consultorasService.getConsultora(consultoras
					.getIdUsuario());
			String texto = "";
			log.debug("Asignando PyMEs");
			texto = texto
					+ "<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado "
					+ consultoras.getNombreContacto()
					+ " "
					+ consultoras.getAppMaternoContacto()
					+ " "
					+ consultoras.getAppMaternoContacto()
					+ "<br/><br/>"
					+ "Nos complace informarte que el Centro de Competitividad de México (CCMX), a través de "
					+ consultoras.getEmpresa()
					+ "te ha asignado las siguientes PYMES para inicien los servicios de consultoría: ";
			for (String temp : pymesSelected) {
				PyMEs pyme = pyMEsService
						.getPyME(Integer.parseInt(temp.trim()));
				String x = pyMEsService.getIdDomicilio(Integer.parseInt(temp
						.trim()));
				Domicilios dom = pyMEsService.getDomicilio(Integer.parseInt(x));
				if (dom == null) {
					dom = new Domicilios();
				}
				setMensaje(consultorasService.saveRelPymesConsultora(
						Integer.parseInt(temp.trim()),
						consultoras.getIdUsuario()));
				if (mensaje.getRespuesta() == 0) {
					consultoras = consultorasService.getConsultora(consultoras
							.getIdUsuario());
					if (consultoras == null) {
						consultoras = new Consultoras();
					}
					texto = texto + "<br/>&nbsp;&nbsp;&nbsp;&bull; "
							+ pyme.getNombreComercial() + " "
							+ Null.free(pyme.getNombreContacto1()) + " "
							+ Null.free(pyme.getCorreoElectronico()) + " "
							+ "Calle: " + Null.free(dom.getCalle()) + " "
							+ "Número: " + Null.free(dom.getNumExt())
							+ " Interior: " + Null.free(dom.getNumInt())
							+ "<br/>" + "Colonia: "
							+ Null.free(dom.getColonia()) + " "
							+ "Delegación/Municipio: "
							+ Null.free(dom.getDelegacion()) + " " + "Estado: "
							+ Null.free(dom.getEstado()) + "<br/>C.P."
							+ Null.free(dom.getCodigoPostal());
				}
			}
			Usuario us = getUsuario();
			Consultoras cons = consultorasService.getConsultora(us
					.getIdUsuario());
			if (cons == null) {
				cons = new Consultoras();
			}
			texto = texto
					+ "<br/><br/>"
					+ "Te pedimos que te pongas en contacto con los representantes de las empresas para inicial la consultoría lo antes posible."
					+ "<br/><br/>"
					+ "Recuerda que ahora le podrás dar seguimiento a las empresas que atiendes, a través del Sistema de Vinculación del CCMX. Te pedimos por favor, que registres la información solicitada en dicho sistema."
					+ "<br/><br/>"
					+ "En caso de cualquier duda sobre la operación y funcionamiento del sistema, no dudes en ponerte en contacto con sistemadevinculacion@ccmx.org.mx. "
					+ "<br/><br/>"
					+ "En caso de cualquier duda sobre las empresas asignadas, no dudes en contractar a "
					+ cons.getEmpresa() + " al siguiente correo electrónico "
					+ cons.getCorreoElectronico()
					+ "<br/><br/>Muchas gracias <br/><br/> CCMX.";
			log.debug(texto);
			SendEmail envia = new SendEmail(consultoras.getCorreoElectronico(),
					"SIA CCMX asignacion de PyME a Consultor ", texto, null);
			log.debug("Enviando a " + consultoras.getCorreoElectronico()
					+ " correo electrónico:" + envia);
			setMensaje(new Mensaje(
					0,
					"Las PyMEs han sido asignadas satisfactoriamente, en breve se notificará al consultor vía correo electrónico."));
		} else if (consultoras != null && consultoras.getIdUsuario() == 0) {
			if (initService.getUsuario(consultoras.getCorreoElectronico()) != null) {
				setMensaje(new Mensaje(
						1,
						"Imposible realizar la operación, la cuenta de correo '"
								.concat(consultoras.getCorreoElectronico())
								.concat("' ya ha sido utilizada en el sistema, intente con otra cuenta de correo electrónico por favor.")));
				existeUsuario = true;
			}
			if (!existeUsuario) {
				consultoras.setPassword(ValidationUtils.getNext(4));
				Usuario up = getUsuario();
				Consultoras consult = consultorasService.getConsultora(up
						.getIdUsuario());
				consultoras.setIdUsuarioPadre(consult.getIdUsuarioPadre());
				consultoras.setIdConsultoraPadre(consult.getIdConsultora());
				consultoras.setEmpresa(consult.getEmpresa());
				log.debug("guardando el usuario, consultora:" + consultoras);
				ccmxService.saveUsuarioConsultora(consultoras);
				log.debug("guardando rol");
				consultorasService.saveRolConsultora(consultoras);
				Usuario u = initService.getUsuario(consultoras
						.getCorreoElectronico());
				consultoras.setIdUsuario(u.getIdUsuario());
				log.debug("guardando Consultora:" + consultoras);
				setMensaje(consultorasService.saveConsultor(consultoras));
				SendEmail envia = new SendEmail(
						consultoras.getCorreoElectronico(),
						"SIA CCMX Registro de usuario Consultora",
						"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado(a) "
								.concat(Null.free(consultoras
										.getNombreContacto()))
								.concat(",<br /><br />Nos complace informarte que el Centro de Competitividad de México (CCMX) te ha dado de alta como consultor ")
								.concat(" en el Sistema de Vinculación del CCMX. En este sistema podrás dar ")
								.concat("seguimiento a las PyMEs que se te han asignado para ofrecerles el servicio de consultoría especializada.")
								.concat("<br /><br />Además de registrar el avance de las PyMEs en el proceso de consultoría.")
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
				existeUsuario = true;
			}
			if (!existeUsuario) {
				log.debug("actualizando consultora:" + consultoras);
				Usuario u = initService.getUsuario(credenciales);
				consultoras.setPassword(initService.getCredenciales(
						u.getIdUsuario()).getCredenciales());
				consultoras.setIdUsuario(u.getIdUsuario());
				setMensaje(ccmxService.updateConsultora(consultoras,
						credenciales));
				if (mensaje.getRespuesta() == 0) {
					log.debug("Enviando correo electrónico:"
							+ consultoras.getCorreoElectronico());
					SendEmail envia = new SendEmail(
							consultoras.getCorreoElectronico(),
							"SIA CCMX Registro de usuario Consultora",
							"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimada "
									.concat(Null.free(consultoras.getEmpresa()))
									.concat(",<br /><br />Nos complace informarte que el Centro de Competitividad de México (CCMX) te ha dado de alta como empresa")
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
		}
		Usuario up = getUsuario();
		setConsultorasList(consultorasService
				.getConsultorasAdmin(consultorasService.getConsultora(
						up.getIdUsuario()).getIdConsultora()));
		setMenu(1);
		if (getConsultorasList().isEmpty()) {
			return INPUT;
		} else {
			return SUCCESS;
		}
	}

	@Action(value = "/consultoraConsultoresAdd", results = { @Result(name = "success", location = "consultoras.administracion.consultores.add", type = "tiles") })
	public String consultoraConsultoresAdd()
			throws ConsultoraNoObtenidaException {
		log.debug("consultoraConsultoresAdd()");
		setMenu(1);
		setPymesList(null);
		if (consultoras != null && consultoras.getIdUsuario() != 0)
			setConsultoras(consultorasService.getConsultora(consultoras
					.getIdUsuario()));
		return SUCCESS;
	}

	@Action(value = "/consultoraPymes", results = { @Result(name = "success", location = "consultoras.administracion.consultores.add", type = "tiles") })
	public String consultoraPymes() throws BaseBusinessException {
		log.debug("consultoraPymes()");
		setMenu(1);
		Usuario us = getUsuario();

		if (consultoras != null && consultoras.getIdUsuario() != 0)
			setPymesList(consultorasService.getPymesAdmin(us.getIdUsuario()));
		setConsultoras(consultorasService.getConsultora(consultoras
				.getIdUsuario()));
		return SUCCESS;
	}

	@Action(value = "/consultoraPyMEsShow", results = { @Result(name = "success", location = "consultoras.administracion.pymes.list", type = "tiles") })
	public String pymeBusquedaShow() throws BaseBusinessException {
		log.debug("pymeBusquedaShow()" + idUsuario);
		setMenu(2);
		if (idUsuario != 0) {
			log.debug("Consultando la PyME " + idUsuario);
			setPyMEs(pyMEsService.getPyME(idUsuario));
			setEstadosVentas(pyMEsService.getEstadoVenta(idUsuario));

			String idInd = pyMEsService.getIdIndicador(idUsuario);
			log.debug("idIndicador=" + idInd);
			setIndicadores(pyMEsService.getIndicador(Integer.parseInt(idInd)));
			setRelPyMEsTractoras(pyMEsService.getCalificacion(idUsuario));
			setIndicadoresMes(pyMEsService.getIndicadorMes(idUsuario));
			setServiciosConsultoria(pyMEsService.getServConsultorias(idUsuario));
			return SUCCESS;
		} else {
			Usuario t = getUsuario();
			Consultoras cons = consultorasService.getConsultora(t
					.getIdUsuario());
			setIdConsultor(cons.getIdConsultora());
			setPymesList(null);
			if (salida != null && salida.trim().equals("asignar")) {
				log.debug("Entrando a set de asignación de cedula");
				setPymesList(new ArrayList<PyMEs>());
				setPymesList(consultorasService.getPyMEsCedula(idConsultor));
			} else if (ant1 != null && pymesSelected != null) {
				String mensajs = "";
				List<Integer> id = new ArrayList<Integer>();
				for (String ids : pymesSelected) {
					// ids id
					// ant1 anho
					id.add(Integer.parseInt(ids.trim()));
					mensajs = mensajs
							+ " - "
							+ pyMEsService
									.getPyME(Integer.parseInt(ids.trim()))
									.getNombreComercial() + "";
				}
				Mensaje m = consultorasService.saveCedula(id, ant1);
				setMensaje(new Mensaje(m.getRespuesta(), m.getMensaje()
						+ mensajs));
			}

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

	@Action(value = "/addFacturacionShow", results = { @Result(name = "success", location = "consultoras.administracion.contabilidad.show", type = "tiles") })
	public String addFacturacionShow() throws BaseBusinessException {
		log.debug("addFacturacionShow()");
		setMenu(3);
		setIdFactura("");
		return SUCCESS;
	}

	@Action(value = "/consultoraFacturacionShow", results = { @Result(name = "success", location = "consultoras.administracion.contabilidad.show", type = "tiles") })
	public String consultoraFacturacionShow() throws BaseBusinessException {
		log.debug("consultoraFacturacionShow()");
		setMenu(3);
		Usuario user = getUsuario();
		Consultoras c = consultorasService.getConsultora(user.getIdUsuario());
		String mailCordinador = consultorasService.getCorreoCordCons();
		if (idFactura != null
				&& (abono1List == null && abono2List == null
						&& anticipoList == null && finiquitoList == null)) {
			setMensaje(consultorasService.saveFactura(idFactura,
					user.getIdUsuario()));
		}
		if (abono1List == null && abono2List == null && anticipoList == null
				&& finiquitoList == null) {
			setFacturasList(consultorasService.getFacturasPorAdmin(user
					.getIdUsuario()));
			if (getOpcion() != null && getOpcion().equals("1")) {
				setPagosList(consultorasService
						.getPagos(c.getIdConsultora(), 1));
			} else if (getOpcion() != null && getOpcion().equals("2")) {
				setPagosList(consultorasService
						.getPagos(c.getIdConsultora(), 2));
			} else {
				setPagosList(consultorasService
						.getPagos(c.getIdConsultora(), 0));
			}
		} else {
			Map<Integer, String> correos = new HashMap<Integer, String>();
			if (mailCordinador != null && mailCordinador.trim().equals("")) {
				setMensaje(new Mensaje(1,
						"Ocurrio un error al enviar correo electronico, "
								+ "consulte al administrador;"));
				return SUCCESS;
			}
			List<Pagos> pAnticipoList = null;
			List<Pagos> pAbono1List = null;
			List<Pagos> pAbono2List = null;
			List<Pagos> pFiniquitoList = null;
			if (anticipoList != null) {
				pAnticipoList = new ArrayList<Pagos>();
				for (String s : anticipoList) {
					Pagos p = consultorasService.getPagos(Integer.parseInt(s));
					if (!correos.containsKey(p.getIdServicios())) {
						correos.put(p.getIdServicios(), " Anticipo ");
					}
					pAnticipoList.add(p);
				}
			}
			if (abono1List != null) {
				pAbono1List = new ArrayList<Pagos>();
				for (String s : abono1List) {
					Pagos p = consultorasService.getPagos(Integer.parseInt(s));
					if (!correos.containsKey(p.getIdServicios())) {
						correos.put(p.getIdServicios(), " Abono 1 ");
					} else {
						String t = correos.get(p.getIdServicios());
						correos.remove(p.getIdServicios());
						correos.put(p.getIdServicios(), t + ", Abono1");
					}
					pAbono1List.add(p);
				}
			}
			if (abono2List != null) {
				pAbono2List = new ArrayList<Pagos>();
				for (String s : abono2List) {
					Pagos p = consultorasService.getPagos(Integer.parseInt(s));
					if (!correos.containsKey(p.getIdServicios())) {
						correos.put(p.getIdServicios(), " Abono2 ");
					} else {
						String t = correos.get(p.getIdServicios());
						correos.remove(p.getIdServicios());
						correos.put(p.getIdServicios(), t + ", Abono2");
					}
					pAbono2List.add(p);
				}
			}
			if (finiquitoList != null) {
				pFiniquitoList = new ArrayList<Pagos>();
				for (String s : finiquitoList) {
					Pagos p = consultorasService.getPagos(Integer.parseInt(s));
					if (!correos.containsKey(p.getIdServicios())) {
						correos.put(p.getIdServicios(), " Finiquito ");
					} else {
						String t = correos.get(p.getIdServicios());
						correos.remove(p.getIdServicios());
						correos.put(p.getIdServicios(), t + ", Finiquito");
					}
					pFiniquitoList.add(p);
				}
			}
			setMensaje(consultorasService.saveFacturas(pAnticipoList,
					pAbono1List, pAbono2List, pFiniquitoList, idFactura));
			if (mensaje.getRespuesta() == 0) {
				Iterator<Entry<Integer, String>> it = correos.entrySet()
						.iterator();
				while (it.hasNext()) {
					Map.Entry<Integer, String> e = (Entry<Integer, String>) it
							.next();
					SendEmail envia = new SendEmail(
							mailCordinador,
							"SIA CCMX Solicitud de factura",
							"<p style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado cordinador: "
									.concat("<br /><br />La empresa ")
									.concat(Null.free(consultorasService
											.getPymeByServicio(e.getKey())))
									.concat(", ha solicitado las siguientes facturas: </p>")
									.concat("<br /><br /> <ul><li> ")
									.concat(Null.free(idFactura))
									.concat(": ")
									.concat(Null.free(e.getValue()))
									.concat("</li></ul>")
									.concat("<br /><p style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>En caso de cualquier duda sobre la operación y funcionamiento del sistema,</p>")
									.concat("<p>no dudes en ponerte en contacto con consultoria@ccmx.org.mx")
									.concat("</p><p>Muchas gracias por utilizar el sistema de vinculación del CCMX.</p>"),
							null);
					log.debug("Enviando correo electrónico:" + envia);
				}
			}
			setFacturasList(consultorasService.getFacturasPorAdmin(user
					.getIdUsuario()));
			if (getOpcion() != null && getOpcion().equals("1")) {
				setPagosList(consultorasService
						.getPagos(c.getIdConsultora(), 1));
			} else if (getOpcion() != null && getOpcion().equals("2")) {
				setPagosList(consultorasService
						.getPagos(c.getIdConsultora(), 2));
			} else {
				setPagosList(consultorasService
						.getPagos(c.getIdConsultora(), 0));
			}
		}
		setIdFactura(null);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Action(value = "/consultoraReportesShow", results = {
			@Result(name = "success", location = "consultoras.administracion.reportes.show", type = "tiles"),
			@Result(name = "input", location = "consultoras.administracion.reportes.show", type = "tiles"),
			@Result(name = "error", location = "consultoras.administracion.reportes.show", type = "tiles") })
	public String consultoraReportesShow() throws BaseBusinessException {
		log.debug("consultoraReportesShow()");
		setMenu(4);
		if (opcion != null && opcion.equals("finanzas")) {
			setOpcion(opcion);
			setMenuAnticipo(reportService.getMenuFacturaAnticipo());
			setMenuFiniquito(reportService.getMenuFacturaFiniquito());
			setMenuAnticipoFiniquito(reportService
					.getMenuFacturaAnticipoFiniquito());
			return SUCCESS;
		} else if (opcion != null && opcion.equals("pymes")) {
			setOpcion(opcion);
			Usuario user = getUsuario();
			Consultoras cons = consultorasService.getConsultora(user
					.getIdUsuario());
			setConsultorasList(reportService.getConsultores(cons
					.getIdConsultora()));
			setMenuAnticipo(reportService.getMenuFacturaAnticipo());
			setMenuAnticipoFiniquito(reportService
					.getMenuFacturaAnticipoFiniquito());
			setMenuEstatus(reportService.getMenuEstatus());
			setMenuCedula(reportService.getMenuCedulas());
			return SUCCESS;
		} else if (opcion != null && opcion.equals("indicadores")) {
			setOpcion(opcion);
			Usuario user = getUsuario();
			Consultoras temp = consultorasService.getConsultora(user
					.getIdUsuario());
			setConsultorasList(reportService.getConsultores(temp
					.getIdConsultora()));
			setMenuAnticipo(reportService.getMenuFacturaAnticipo());
			setMenuAnticipoFiniquito(reportService
					.getMenuFacturaAnticipoFiniquito());
			setMenuEstatus(reportService.getMenuEstatus());
			setMenuCedula(reportService.getMenuCedulas());
			return SUCCESS;
		} else if (opcion != null && opcion.equals("finRepor")) {
			setOpcion("descarga");
			String direccion = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("/");
			Usuario usuario = getUsuario();
			if (filtros == null) {
				filtros = new Filtros();
			}
			filtros.setId(usuario.getIdUsuario());
			List<CCMXFinanzas> finanzasList = reportService
					.getCCMXFiannzas(filtros);
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
			log.debug("No se inicializo opcion Reportes ");
			return SUCCESS;
		}
	}

	@Action(value = "/consultoraReportesAdd", results = { @Result(name = "success", location = "consultoras.administracion.reportes.add", type = "tiles") })
	public String consultoraReportesAdd() {
		log.debug("consultoraReportesAdd()");
		setMenu(4);
		return SUCCESS;
	}

	public String getCredenciales() {
		return credenciales;
	}

	public void setCredenciales(String credenciales) {
		this.credenciales = credenciales;
	}

	public InputStream getArchivo() {
		return archivo;
	}

	public void setArchivo(InputStream archivo) {
		this.archivo = archivo;
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

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public Filtros getFiltros() {
		return filtros;
	}

	public void setFiltros(Filtros filtros) {
		this.filtros = filtros;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
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

	public List<PyMEs> getPymesList() {
		return pymesList;
	}

	public void setPymesList(List<PyMEs> pymesList) {
		this.pymesList = pymesList;
	}

	public List<Consultoras> getConsultorList() {
		return consultorList;
	}

	public void setConsultorList(List<Consultoras> consultorList) {
		this.consultorList = consultorList;
	}

	public Consultoras getConsultoras() {
		return consultoras;
	}

	public void setConsultoras(Consultoras consultoras) {
		this.consultoras = consultoras;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<String> getPymesSelected() {
		return pymesSelected;
	}

	public void setPymesSelected(List<String> pymesSelected) {
		this.pymesSelected = pymesSelected;
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

	public PyMEs getPyMEs() {
		return pyMEs;
	}

	public void setPyMEs(PyMEs pyMEs) {
		this.pyMEs = pyMEs;
	}

	public List<CatScianCcmx> getListCatProductos()
			throws ProductosNoObtenidosException {
		setListCatProductos(tractorasService.getNivelScian(0));
		return listCatProductos;
	}

	public void setListCatProductos(List<CatScianCcmx> listCatProductos) {
		this.listCatProductos = listCatProductos;
	}

	public List<CatScianCcmx> getListCat2() {
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

	public List<CatScianCcmx> getListCat4() {
		return listCat4;
	}

	public void setListCat4(List<CatScianCcmx> listCat4) {
		this.listCat4 = listCat4;
	}

	public List<CatScianCcmx> getListCat5() {
		return listCat5;
	}

	public void setListCat5(List<CatScianCcmx> listCat5) {
		this.listCat5 = listCat5;
	}

	public EstadosVenta getEstadosVentas() {
		return estadosVentas;
	}

	public void setEstadosVentas(EstadosVenta estadosVentas) {
		this.estadosVentas = estadosVentas;
	}

	public List<PyMEs> getListPyMEs() throws PyMEsNoObtenidasException {
		log.debug("getListPyMEs()");
		List<PyMEs> list = new ArrayList<PyMEs>();
		log.debug(busqueda);
		log.debug(estado);
		log.debug(cveScian);
		log.debug(idConsultor);
		list = consultorasService.getBusquedaPyME(Null.free(busqueda), Null
				.free(estado).equals("-1") ? "" : estado, Null.free(cveScian),
				idConsultor, 0);
		setListPyMEs(list);
		return listPyMEs;

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

	public void setListPyMEs(List<PyMEs> listPyMEs) {
		this.listPyMEs = listPyMEs;
	}

	public List<Pagos> getPagosList() {
		return pagosList;
	}

	public void setPagosList(List<Pagos> pagosList) {
		this.pagosList = pagosList;
	}

	public String getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}

	public List<String> getAnticipoList() {
		return anticipoList;
	}

	public void setAnticipoList(List<String> anticipoList) {
		this.anticipoList = anticipoList;
	}

	public List<String> getAbono1List() {
		return abono1List;
	}

	public void setAbono1List(List<String> abono1List) {
		this.abono1List = abono1List;
	}

	public List<String> getAbono2List() {
		return abono2List;
	}

	public void setAbono2List(List<String> abono2List) {
		this.abono2List = abono2List;
	}

	public List<String> getFiniquitoList() {
		return finiquitoList;
	}

	public void setFiniquitoList(List<String> finiquitoList) {
		this.finiquitoList = finiquitoList;
	}

	public int getIdConsultor() {
		return idConsultor;
	}

	public void setIdConsultor(int idConsultor) {
		this.idConsultor = idConsultor;
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

	public List<FiltrosGenerales> getMenuFiniquito() {
		return menuFiniquito;
	}

	public void setMenuFiniquito(List<FiltrosGenerales> menuFiniquito) {
		this.menuFiniquito = menuFiniquito;
	}

	@Action(value = "/downDoc", results = {
			@Result(name = "success", type = "stream"),
			@Result(name = "input", location = "reportes.general.reportes.list", type = "tiles"),
			@Result(name = "error", location = "reportes.general.reportes.list", type = "tiles") })
	public String downDoc() throws BaseBusinessException {
		log.debug("downDoc");
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

	@Action(value = "/consultoraShowDoc", results = {
			@Result(name = "success", type = "stream", params = { "inputName",
					"archivo", "contentType", "mimeArchivo",
					"contentDisposition",
					"attachment;filename=\"${nameArchivo}\"" }),
			@Result(name = "input", location = "pyme.datos.show", type = "tiles"),
			@Result(name = "error", location = "pyme.datos.show", type = "tiles") })
	public String consultoraShowDoc() throws DocumentoNoObtenidoException {
		log.debug("consultoraShowDoc()");
		setArchivo(consultorasService.getArchivo(idArchivo).getIs());

		log.debug("archivo=" + archivo);
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		return SUCCESS;
	}

	public Indicadores getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(Indicadores indicadores) {
		this.indicadores = indicadores;
	}

	public Domicilios getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(Domicilios domicilios) {
		this.domicilios = domicilios;
	}

	public String getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}

	public String getAnt1() {
		return ant1;
	}

	public void setAnt1(String ant1) {
		this.ant1 = ant1;
	}

	public List<Facturas> getFacturasList() {
		return facturasList;
	}

	public void setFacturasList(List<Facturas> facturasList) {
		this.facturasList = facturasList;
	}

	public ServiciosConsultoria getServiciosConsultoria() {
		return serviciosConsultoria;
	}

	public void setServiciosConsultoria(
			ServiciosConsultoria serviciosConsultoria) {
		this.serviciosConsultoria = serviciosConsultoria;
	}

	public Indicadores getIndicadoresMes() {
		return indicadoresMes;
	}

	public void setIndicadoresMes(Indicadores indicadoresMes) {
		this.indicadoresMes = indicadoresMes;
	}

	public RelPyMEsTractoras getRelPyMEsTractoras() {
		return relPyMEsTractoras;
	}

	public void setRelPyMEsTractoras(RelPyMEsTractoras relPyMEsTractoras) {
		this.relPyMEsTractoras = relPyMEsTractoras;
	}

	public int getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(int idArchivo) {
		this.idArchivo = idArchivo;
	}

}
