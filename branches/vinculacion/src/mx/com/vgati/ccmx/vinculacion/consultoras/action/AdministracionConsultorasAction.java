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
import java.util.List;
import java.util.Map;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.service.CCMXService;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.exception.ConsultoraNoObtenidaException;
import mx.com.vgati.ccmx.vinculacion.consultoras.service.ConsultorasService;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXFinanzas;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXParticipantes;
import mx.com.vgati.ccmx.vinculacion.report.dto.Filtros;
import mx.com.vgati.ccmx.vinculacion.report.dto.PYMESReporte;
import mx.com.vgati.ccmx.vinculacion.report.dto.TotalEmpresas;
import mx.com.vgati.ccmx.vinculacion.report.service.ReportService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
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
	private InitService initService;
	private CCMXService ccmxService;
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
	private List<Consultoras> consultorList;
	private String credenciales;
	private Mensaje mensaje;
	public int idUsuario;
	public List<String> pymesSelected;

	public void setCcmxService(CCMXService ccmxService) {
		this.ccmxService = ccmxService;

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
		if (pymesSelected != null && consultoras != null) {
			log.debug("Asignando PYMEs");
			for (String temp : pymesSelected) {
				consultorasService.saveRelPymesConsultora(
						Integer.valueOf(temp.trim()),
						consultoras.getIdUsuario());
				log.debug(Integer.valueOf(temp.trim()));
			}
		} else if (consultoras != null && consultoras.getIdUsuario() == 0) {
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
			Consultoras consult = consultorasService.getConsultora(up
					.getIdUsuario());
			consultoras.setIdUsuarioPadre(consult.getIdUsuarioPadre());
			consultoras.setIdConsultoraPadre(consult.getIdConsultora());
			log.debug("guardando el usuario, consultora:" + consultoras);
			ccmxService.saveUsuarioConsultora(consultoras);
			log.debug("guardando rol");
			consultorasService.saveRolConsultora(consultoras);
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
							.concat("seguimiento a las PYMES que se te han asignado para ofrecerles el servicio de consultoría especializada.")
							.concat("<br /><br />Además de registrar el avance de las PYMES en el proceso de consultoría, será posible solicitar el pago por tus servicios.")
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
								.concat("seguimiento a las PYMES que se te han asignado para ofrecerles el servicio de consultoría especializada.")
								.concat("<br /><br />Además de registrar el avance de las PYMES en el proceso de consultoría, será posible solicitar el pago por tus servicios.")
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
	public String consultoraPymes() throws ConsultoraNoObtenidaException {
		log.debug("consultoraConsultoresAdd()");
		setMenu(1);
		if (consultoras != null && consultoras.getIdUsuario() != 0)
			setPymesList(consultorasService.getPymes());
		setConsultoras(consultorasService.getConsultora(consultoras
				.getIdUsuario()));
		return SUCCESS;
	}

	@Action(value = "/consultoraPyMEsShow", results = { @Result(name = "success", location = "consultoras.administracion.pymes.list", type = "tiles") })
	public String asignarPymes() throws BaseBusinessException {
		log.debug("Lista de Pymes");
		Usuario u = getUsuario();
		pymesList = consultorasService.getPymes(consultorasService
				.getConsultora(u.getIdUsuario()).getIdConsultora());
		log.debug("" + u.getIdUsuario() + "\n" + pymesList);
		setMenu(2);
		return SUCCESS;
	}

	@Action(value = "/consultoraFacturacionShow", results = { @Result(name = "success", location = "consultoras.administracion.contabilidad.show", type = "tiles") })
	public String consultoraFacturacionShow() {
		log.debug("consultoraFacturacionShow()");
		setMenu(3);

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
		if (opcion != null && opcion.equals("servicios")) {
			setOpcion(opcion);
			try {
				setTractorasList(reportService.getTractoras());
				setConsultorasList(reportService.getConsultoras());
			} catch (TractorasNoObtenidasException e) {
				e.printStackTrace();
				log.debug("" + e.toString() + "\n" + e);
			}
			return SUCCESS;

		} else if (opcion != null && opcion.equals("finanzas")) {
			setOpcion(opcion);
			setConsultorasList(reportService.getConsultoras());
			return SUCCESS;
		} else if (opcion != null && opcion.equals("pymes")) {
			setOpcion(opcion);
			setConsultorasList(reportService.getConsultoras());
			return SUCCESS;
		} else if (opcion != null && opcion.equals("servRepor")) {
			setOpcion("descarga");
			log.debug("Reporte servicios");
			String direccion = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("/");
			Usuario usuario = getUsuario();
			if (usuario.getRol().equals("Comprador")
					|| usuario.getRol().endsWith("CompradorAdministrador")) {
				filtros.setId(usuario.getIdUsuario());
			} else {
				filtros.setId(-1);
			}
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
					parameters.put("tCultura",
							reportService.getTCultura(filtros));
					parameters.put("tPlaneacion",
							reportService.getTPlaneacion(filtros));
					parameters.put("tManufactura",
							reportService.getTManufactura(filtros));
					parameters.put("tEstrategia",
							reportService.getTEstrategia(filtros));
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
				} catch (JRException e) {
					e.printStackTrace();
				}/* "WEB-INF\\jasper\\reporte.jrxml" */
				return SUCCESS;
			}

		} else if (opcion != null && opcion.equals("finRepor")) {
			setOpcion("descarga");
			String direccion = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("/");
			Usuario usuario = getUsuario();
			if (usuario.getRol().equals("AdministradorConsultor")) {
				filtros.setId(usuario.getIdUsuario());
			}
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
									+ "/jasper/pymes.jrxml")));/* "WEB-INF\\jasper\\reporte.jrxml" */
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
		} else if (opcion != null && opcion.equals("descarga")) {
			setOpcion("descarga");
			return SUCCESS;
		} else {
			log.debug("aca ando");
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

	@Action(value = "/downDoc", results = {
			@Result(name = "success", type = "stream", params = { "inputName",
					"archivo", "contentType", "mimeArchivo",
					"contentDisposition",
					"attachment;filename=\"Reporte.xlsx\"" }),
			@Result(name = "input", location = "reportes.general.reportes.list", type = "tiles"),
			@Result(name = "error", location = "reportes.general.reportes.list", type = "tiles") })
	public String downDoc() throws BaseBusinessException {
		log.debug("showDoc()");
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
