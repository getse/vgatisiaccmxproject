/*
 * ConsultorasAction.java        01/04/2013
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

import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.service.ConsultorasService;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.service.PyMEsService;
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
@Namespaces({ @Namespace(value = "consultor") })
public class ConsultorasAction extends AbstractBaseAction {

	private int menu = 1;
	private static final String[] op = { "MI INFORMACI&Oacute;N",
			"PyMEs ASIGNADAS", "INDICADORES", "REPORTES" };
	private static final String[] fr = { "consultorInformacionShow.do",
			"consultorPyMEsShow.do", "consultorIndicadoresShow.do",
			"consultorReportesShow.do" };

	private ConsultorasService consultorasService;
	private TractorasService tractorasService;
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
	private List<FiltrosGenerales> menuAnticipo;
	private List<FiltrosGenerales> menuAnticipoFiniquito;
	private List<FiltrosGenerales> menuCedula;
	private List<FiltrosGenerales> menuEstatus;
	private PyMEs pyMEs;
	private List<CatScianCcmx> listCatProductos;
	private List<CatScianCcmx> listCat1;
	private List<CatScianCcmx> listCat2;
	private List<CatScianCcmx> listCat3;
	private List<CatScianCcmx> listCat4;
	private List<CatScianCcmx> listCat5;
	private EstadosVenta estadosVentas;
	private String busqueda;
	private String estado;
	private String cveScian;
	private List<PyMEs> listPyMEs;
	private List<PyMEs> pymesList;
	private int cat1;
	private int cat2;
	private int cat3;
	private int cat4;
	private int cat5;
	public int idUsuario;
	private Domicilios domicilios;
	private int idConsultor;
	private Indicadores indicadores;
	private Mensaje mensaje;
	private int seguimiento;
	private ServiciosConsultoria servConsultoria;
	private List<Diplomados> diplomados;
	private String init;
	private ServiciosConsultoria serviciosConsultoria;
	private Indicadores indicadoresMes;
	private RelPyMEsTractoras relPyMEsTractoras;
	private int idArchivo;

	public void setTractorasService(TractorasService tractorasService) {
		this.tractorasService = tractorasService;
	}

	public void setPyMEsService(PyMEsService pyMEsService) {
		this.pyMEsService = pyMEsService;
	}

	public void setConsultorasService(ConsultorasService consultorasService) {
		this.consultorasService = consultorasService;
	}

	@Action(value = "/consultorInformacionShow", results = {
			@Result(name = "success", location = "consultora.datos.show", type = "tiles"),
			@Result(name = "indicadores", location = "consultora.indicadores.show", type = "tiles") })
	public String consultorInformacionShow() throws BaseBusinessException {
		log.debug("consultorInformacionShow()");
		setMenu(1);

		if (getConsultoras() != null && getConsultoras().getIdUsuario() != 0) {
			log.debug(consultoras.getTelefonos());
			setMensaje(consultorasService.updateConsultor(consultoras));

		}
		Usuario u = getUsuario();
		log.debug("Usuario=" + u);
		Consultoras d = consultorasService.getConsultora(u.getIdUsuario());
		d.setIdUsuario(0);
		setConsultoras(d);
		if (init != null && init.trim().equals("1")
				&& !consultoras.getTelefonos().isEmpty()) {
			log.debug("consultorIndicadoresShow()");
			setMenu(3);
			log.debug(servConsultoria);
			if (servConsultoria != null
					&& servConsultoria.getIdConsultoria() != 0) {
				log.debug("Salvando cambios en el sericio de consultoria : "
						+ servConsultoria);
				setMensaje(consultorasService
						.saveServiciosConsultoria(servConsultoria));
			}
			Usuario t = getUsuario();
			setIdUsuario(t.getIdUsuario());
			setPymesList(consultorasService
					.getPyMEsConsultor(consultorasService.getConsultora(
							idUsuario).getIdConsultora()));
			return "indicadores";
		}
		return SUCCESS;
	}

	@Action(value = "/consultorInformacionAdd", results = { @Result(name = "success", location = "consultora.datos.show", type = "tiles") })
	public String consultorInformacionAdd() throws BaseBusinessException {
		log.debug("consultorInformacionAdd()");
		setMenu(1);
		Usuario u = getUsuario();
		setConsultoras(consultorasService.getConsultora(u.getIdUsuario()));
		return SUCCESS;
	}

	@Action(value = "/consultorPyMEsShow", results = { @Result(name = "success", location = "consultora.pymes.list", type = "tiles") })
	public String consultorPyMEsShow() throws NumberFormatException,
			BaseBusinessException {
		log.debug("consultorPyMEsShow"+idUsuario);
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
		}
		Usuario t = getUsuario();
		setIdUsuario(t.getIdUsuario());
		if (idConsultor != 0) {
			log.debug("Consultando la PyME" + idUsuario);
			setPyMEs(pyMEsService.getPyME(idUsuario));
			setEstadosVentas(pyMEsService.getEstadoVenta(idUsuario));

			String idInd = pyMEsService.getIdIndicador(idUsuario);
			log.debug("idIndicador=" + idInd);
			setIndicadores(pyMEsService.getIndicador(Integer.parseInt(idInd)));
			setRelPyMEsTractoras(pyMEsService.getCalificacion(idUsuario));
			setIndicadoresMes(pyMEsService.getIndicadorMes(idUsuario));
			setServiciosConsultoria(pyMEsService.getServConsultorias(idUsuario));
			setIdConsultor(0);
		} else {
			setIdConsultor(consultorasService.getConsultora(idUsuario)
					.getIdConsultora());
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

		return SUCCESS;
	}

	@Action(value = "/consultorIndicadoresShow", results = { @Result(name = "success", location = "consultora.indicadores.show", type = "tiles") })
	public String consultorIndicadoresShow() throws BaseBusinessException {
		log.debug("consultorIndicadoresShow()");
		setMenu(3);
		log.debug(servConsultoria);
		if (servConsultoria != null && servConsultoria.getIdConsultoria() != 0) {
			log.debug("Salvando cambios en el sericio de consultoria : "
					+ servConsultoria);
			setMensaje(consultorasService
					.saveServiciosConsultoria(servConsultoria));
			ServiciosConsultoria temp = consultorasService.getServiciosConsultoria(servConsultoria.getIdConsultoria());
			if(temp!=null && temp.getIdUsuario()>0 && temp.getEstatus().equals("CONCLUIDA") && 
						(temp.isbConsultoriaVeinte()|| temp.isbConsultoriaCuarenta() || temp.isbConsultoriaSesenta() || temp.isbConsultoriaSesenta() || temp.isbConsultoriaOchenta())){
				List<PyMEs> temps = consultorasService.getPymesLiberar(temp.getIdUsuario());
				if(temps!=null){
					for (int i = 0; i < temps.size(); i++) {
						PyMEs de=temps.get(i);
						if(de!=null ){
							if(consultorasService.saveLiberarPymes(de.getIdUsuario())){
							SendEmail envia = new SendEmail(
									de.getCorreoElectronico(),
									"SIA CCMX Registro de usuario PyME",
									"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimada "
											.concat(Null.free(de.getNombreComercial()))
											.concat(",<br /><br />Nos complace informarte que el Centro de Competitividad de México (CCMX) ha dado de alta a tu empresa en ")
											.concat("el Sistema de Vinculación del CCMX. En este sistema podrás consultar los requerimientos de las grandes empresas de México")
											.concat(" y podrás enviar cotizaciones.<br /><br />")
											.concat("Además, tu información de contacto, así como de los productos o los servicios que ofreces, estarán disponibles para que las ")
											.concat("grandes empresas u otras PyMEs que buscan oportunidades de negocio puedan identificarte.<br /><br />")
											.concat("Es muy importante que para aprovechar todas las ventajas que tiene este sistema, ingreses con la siguiente cuenta y password ")
											.concat("para actualizar y completar tu información.<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>Usuario: ")
											.concat(Null.free(de.getCorreoElectronico()))
											.concat("<br />Contraseña: ")
											.concat(Null.free(de.getPassword()))
											.concat("<br /></h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>El vínculo del Sistema de Vinculación es:</h5>")
											.concat("<h5 style='font-family: Verdana; font-size: 12px; color: #336699;'><br /><a href='http://www.ccmx.mx/vinculacion/inicio.do'>")
											.concat("http://www.ccmx.mx/vinculacion/inicio.do</a><br /><br />")
											.concat("</h5><h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>No olvides actualizar tu perfil si tus datos de contacto")
											.concat(" han cambiado o si tienes nuevos productos o servicios que ofrecer.<br /><br />")
											.concat("En caso de cualquier duda sobre la operación y funcionamiento del sistema, no dudes en ponerte en contacto con ")
											.concat("sistemadevinculacion@ccmx.org.mx.<br /><br />")
											.concat("Muchas gracias por utilizar el sistema de vinculación del CCMX.</h5>"),
									null);
							log.debug("Enviando correo electrónico:" + envia);
							}
						}
					}
				}
			}
			
		}
		Usuario t = getUsuario();
		setIdUsuario(t.getIdUsuario());
		setPymesList(consultorasService.getPyMEsConsultor(consultorasService
				.getConsultora(idUsuario).getIdConsultora()));
		return SUCCESS;
	}

	@Action(value = "/consultorIndicadorShow", results = { @Result(name = "success", location = "consultora.indicadores.list", type = "tiles") })
	public String consultorIndicadorShow() throws BaseBusinessException {
		log.debug("consultorIndicadorShow");
		setMenu(3);
		log.debug(getSeguimiento());
		if (getSeguimiento() != 0) {
			setServConsultoria(consultorasService
					.getServiciosConsultoria(getSeguimiento()));
			setDiplomados(consultorasService.getTemaDiplomado());
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Action(value = "/consultorReportesShow", results = {
			@Result(name = "success", location = "consultora.reportes.show", type = "tiles"),
			@Result(name = "input", location = "consultora.reportes.show", type = "tiles"),
			@Result(name = "error", location = "consultora.reportes.show", type = "tiles") })
	public String consultorReportesShow() throws BaseBusinessException {
		log.debug("consultorReportesShow()");
		setMenu(4);
		if (opcion != null && opcion.equals("pymes")) {
			setOpcion(opcion);
			setConsultorasList(reportService.getConsultoras());
			setMenuAnticipo(reportService.getMenuFacturaAnticipo());
			setMenuAnticipoFiniquito(reportService
					.getMenuFacturaAnticipoFiniquito());
			setMenuEstatus(reportService.getMenuEstatus());
			setMenuCedula(reportService.getMenuCedulas());
			return SUCCESS;
		} else if (opcion != null && opcion.equals("indicadores")) {
			setOpcion(opcion);
			setMenuAnticipo(reportService.getMenuFacturaAnticipo());
			setMenuAnticipoFiniquito(reportService
					.getMenuFacturaAnticipoFiniquito());
			setMenuEstatus(reportService.getMenuEstatus());
			setMenuCedula(reportService.getMenuCedulas());
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
			filtros.setId(usuario.getIdUsuario());
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
			return SUCCESS;
		}
	}

	@Action(value = "/consultorReportesAdd", results = { @Result(name = "success", location = "consultora.reportes.add", type = "tiles") })
	public String consultorReportesAdd() {
		log.debug("consultorReportesAdd()");
		setMenu(4);
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

	public Consultoras getConsultoras() {
		return consultoras;
	}

	public void setConsultoras(Consultoras consultoras) {
		this.consultoras = consultoras;
	}

	public List<Tractoras> getTractorasList() {
		return tractorasList;
	}

	public void setTractorasList(List<Tractoras> tractorasList) {
		this.tractorasList = tractorasList;
	}

	public List<Consultoras> getConsultorasList() {
		return consultorasList;
	}

	public void setConsultorasList(List<Consultoras> consultorasList) {
		this.consultorasList = consultorasList;
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

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public InputStream getArchivo() {
		return archivo;
	}

	public void setArchivo(InputStream archivo) {
		this.archivo = archivo;
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

	public List<CatScianCcmx> getListCat1() {
		return listCat1;
	}

	public void setListCat1(List<CatScianCcmx> listCat1) {
		this.listCat1 = listCat1;
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

	public List<PyMEs> getListPyMEs() throws PyMEsNoObtenidasException {
		log.debug("getListPyMEs()");
		List<PyMEs> list = new ArrayList<PyMEs>();
		log.debug(busqueda);
		log.debug(estado);
		log.debug(cveScian);
		log.debug(idConsultor);
		list = consultorasService.getBusquedaPyME(Null.free(busqueda), Null
				.free(estado).equals("-1") ? "" : estado, Null.free(cveScian),
				idConsultor, idUsuario);
		setListPyMEs(list);
		return listPyMEs;
	}

	public void setListPyMEs(List<PyMEs> listPyMEs) {
		this.listPyMEs = listPyMEs;
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

	public int getIdConsultor() {
		return idConsultor;
	}

	public void setIdConsultor(int idConsultor) {
		this.idConsultor = idConsultor;
	}

	public Indicadores getIndicadores() {
		return indicadores;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public void setIndicadores(Indicadores indicadores) {
		this.indicadores = indicadores;
	}

	public int getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento(int seguimiento) {
		this.seguimiento = seguimiento;
	}

	public ServiciosConsultoria getServConsultoria() {
		return servConsultoria;
	}

	public void setServConsultoria(ServiciosConsultoria servConsultoria) {
		this.servConsultoria = servConsultoria;
	}

	public List<PyMEs> getPymesList() {
		return pymesList;
	}

	public void setPymesList(List<PyMEs> pymesList) {
		this.pymesList = pymesList;
	}

	public List<Diplomados> getDiplomados() {
		return diplomados;
	}

	public void setDiplomados(List<Diplomados> diplomados) {
		this.diplomados = diplomados;
	}

	public String getInit() {
		return init;
	}

	public void setInit(String init) {
		this.init = init;
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

	@Action(value = "/downDoc", results = {
			@Result(name = "success", type = "stream"),
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

	@Action(value = "/showDoc", results = {
			@Result(name = "success", type = "stream", params = { "inputName",
					"archivo", "contentType", "mimeArchivo",
					"contentDisposition",
					"attachment;filename=\"${nameArchivo}\"" }),
			@Result(name = "input", location = "pyme.datos.show", type = "tiles"),
			@Result(name = "error", location = "pyme.datos.show", type = "tiles") })
	public String showDoc() throws DocumentoNoObtenidoException {
		log.debug("showDoc()");
		setArchivo(consultorasService.getArchivo(idArchivo).getIs());

		log.debug("archivo=" + archivo);
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		return SUCCESS;
	}

}
