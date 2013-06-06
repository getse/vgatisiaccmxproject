/*
 * CoordinadorConsultoriasAction.java        01/04/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.action;

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
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.service.CoordinadorConsultoriasService;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXFinanzas;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXParticipantes;
import mx.com.vgati.ccmx.vinculacion.report.dto.Filtros;
import mx.com.vgati.ccmx.vinculacion.report.dto.FiltrosGenerales;
import mx.com.vgati.ccmx.vinculacion.report.dto.IndicadoresPymes;
import mx.com.vgati.ccmx.vinculacion.report.dto.PYMESReporte;
import mx.com.vgati.ccmx.vinculacion.report.dto.TotalEmpresas;
import mx.com.vgati.ccmx.vinculacion.report.service.ReportService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.action.AbstractBaseAction;
import mx.com.vgati.framework.exception.BaseBusinessException;
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
@SuppressWarnings("serial")
@Namespaces({ @Namespace(value = "consultorias/coordinacion") })
public class CoordinadorConsultoriasAction extends AbstractBaseAction {

	private int menu = 1;
	private static final String[] op = { "PyMEs", "SOLICITUDES", "REPORTES" };
	private static final String[] fr = { "coordinadorConsultoriasPyMEsShow.do",
			"coordinadorConsultoriasSolicitudesShow.do",
			"coordinadorConsultoriasReportesShow.do", "", "", "" };
	private InputStream archivo;

	private CoordinadorConsultoriasService coordinadorConsultoriasService;
	private List<Consultoras> consultorasList;
	private List<Tractoras> tractorasList;
	private List<CCMXParticipantes> serviciosList;
	private String opcion;
	private Filtros filtros;
	private String salida;
	private ReportService reportService;
	private List<FiltrosGenerales> menuAnticipo;
	private List<FiltrosGenerales> menuAnticipoFiniquito;
	private List<FiltrosGenerales> menuCedula;
	private List<FiltrosGenerales> menuEstatus;
	private List<FiltrosGenerales> menuFiniquito;

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public List<Consultoras> getConsultorasList() {
		return consultorasList;
	}

	public void setConsultorasList(
			List<Consultoras> consultorasList) {
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

	public void setCoordinadorConsultoriasService(
			CoordinadorConsultoriasService coordinadorConsultoriasService) {
		this.coordinadorConsultoriasService = coordinadorConsultoriasService;
	}

	@Action(value = "/coordinadorConsultoriasPyMEsShow", results = { @Result(name = "success", location = "coordinacion.consultorias.pymes.show", type = "tiles") })
	public String coordinadorConsultoriasPyMEsShow()
			throws BaseBusinessException {
		log.debug("coordinadorConsultoriasPyMEsShow()");
		setMenu(1);

		return SUCCESS;
	}

	@Action(value = "/coordinadorConsultoriasSolicitudesShow", results = { @Result(name = "success", location = "coordinacion.consultorias.solicitudes.show", type = "tiles") })
	public String coordinadorConsultoriasSolicitudesShow()
			throws BaseBusinessException {
		log.debug("coordinadorConsultoriasSolicitudesShow()");
		setMenu(2);

		return SUCCESS;
	}
	
	

	@SuppressWarnings("unchecked")
	@Action(value = "/coordinadorConsultoriasReportesShow", results = {
			@Result(name = "success", location = "coordinacion.consultorias.reportes.show", type = "tiles"),
			@Result(name = "input", location = "coordinacion.consultorias.reportes.show", type = "tiles"),
			@Result(name = "error", location = "coordinacion.consultorias.reportes.show", type = "tiles") })
	public String coordinadorConsultoriasReportesShow()
			throws BaseBusinessException {
		log.debug("coordinadorConsultoriasReportesShow()");

		setMenu(3);
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
			setMenuAnticipo(reportService.getMenuFacturaAnticipo());
			setMenuFiniquito(reportService.getMenuFacturaFiniquito());
			setMenuAnticipoFiniquito(reportService.getMenuFacturaAnticipoFiniquito());
			return SUCCESS;
		} else if (opcion != null && opcion.equals("pymes")) {
			setOpcion(opcion);
			setConsultorasList(reportService.getConsultores(0));
			setMenuEstatus(reportService.getMenuEstatus());
			setMenuCedula(reportService.getMenuCedulas());	
			return SUCCESS;
		} else if (opcion != null && opcion.equals("indicadores")) {
			setOpcion(opcion);
			setConsultorasList(reportService.getConsultores(0));
			setMenuAnticipo(reportService.getMenuFacturaAnticipo());
			setMenuAnticipoFiniquito(reportService.getMenuFacturaAnticipoFiniquito());
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
							reportService.getParticipantesEmpresas(filtros,1));
					parameters.put("tManufactura",
							reportService.getParticipantesEmpresas(filtros,2));
					parameters.put("tEstrategia",
							reportService.getParticipantesEmpresas(filtros,3));
					parameters.put("tPlaneacion",
							reportService.getParticipantesEmpresas(filtros,4));
					parameters.put("tCulturaEmpres",
							reportService.getParticipantes(filtros,1));
					parameters.put("tManufacturaEmpres",
							reportService.getParticipantes(filtros,2));
					parameters.put("tEstrategiaEmpres",
							reportService.getParticipantes(filtros,3));
					parameters.put("tPlaneacionEmpres",
							reportService.getParticipantes(filtros,4));
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
					log.debug(e.getCause()+"\n"+e);
				} catch (JRException e) {
					e.printStackTrace();
					log.debug(e.getCause()+"\n"+e);
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
				setOpcion("descarga");
			}
			return SUCCESS;
		} else if (opcion != null && opcion.trim().equals("inRepor")) {
			log.debug("Generando reporte de indicadores de los indicadores");
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
									+ "/jasper/indicadores.jrxml")));/* "\jasper\\reporte.jrxml" */
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
		} 
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

	@Action(value = "/downDoc", results = {
			@Result(name = "success", type = "stream", params = { "inputName",
					"archivo", "contentType", "mimeArchivo",
					"contentDisposition",
					"attachment;filename=\"Reporte.xlsx\"" }),
			@Result(name = "input", location = "reportes.general.reportes.list", type = "tiles"),
			@Result(name = "error", location = "reportes.general.reportes.list", type = "tiles") })
	public String downDoc() throws BaseBusinessException {
		log.debug("downDoc()");
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

	public ReportService getReportService() {
		return reportService;
	}

	public InputStream getArchivo() {
		return archivo;
	}

	public void setArchivo(InputStream archivo) {
		this.archivo = archivo;
	}

	public List<FiltrosGenerales> getMenuFiniquito() {
		return menuFiniquito;
	}

	public void setMenuFiniquito(List<FiltrosGenerales> menuFiniquito) {
		this.menuFiniquito = menuFiniquito;
	}
	
}
