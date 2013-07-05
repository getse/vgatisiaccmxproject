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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.ccmx.service.CCMXService;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Facturas;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.service.CoordinadorConsultoriasService;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
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
	private CCMXService ccmxService;
	private TractorasService tractorasService;
	private PyMEsService pyMEsService;
	private InitService initService;
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
	private PyMEs pyMEs;
	private List<PyMEs> listPyMEs;
	private List<PyMEs> listDiplomasPyMEs;
	private List<CatScianCcmx> listCat1;
	private List<CatScianCcmx> listCat2;
	private List<CatScianCcmx> listCat3;
	private List<CatScianCcmx> listCat4;
	private List<CatScianCcmx> listCat5;
	private int cat1;
	private int cat2;
	private int cat3;
	private int cat4;
	private int cat5;
	private String busqueda;
	private String estado;
	private String cveScian;
	private Mensaje mensaje;
	private int idUsuario;
	private Domicilios domicilios;
	private EstadosVenta estadosVenta;
	private Indicadores indicadores;
	private Indicadores indicadoresMes;
	private RelPyMEsTractoras relPymesTractoras;
	private ServiciosConsultoria serviciosConsultoria;
	private int idConsultora;
	private String idPyMEs;
	private List<Facturas> listFacturas;
	private List<Facturas> facturasList;
	private String numeroFactura;
	private String idFacturas;
	private String montoTotal;
	private String idPagosFacturas;
	private Date fechaPago;
	private String tractora;
	private String importe;

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

	public void setCcmxService(CCMXService ccmxService) {
		this.ccmxService = ccmxService;
	}

	public void setTractorasService(TractorasService tractorasService) {
		this.tractorasService = tractorasService;
	}

	public void setPyMEsService(PyMEsService pyMEsService) {
		this.pyMEsService = pyMEsService;
	}

	public void setInitService(InitService initService) {
		this.initService = initService;
	}

	@Action(value = "/coordinadorConsultoriasPyMEsShow", results = { @Result(name = "success", location = "coordinacion.consultorias.pymes.show", type = "tiles") })
	public String coordinadorConsultoriasPyMEsShow() throws BaseBusinessException{
		log.debug("coordinadorConsultoriasPyMEsShow()");
		setMenu(1);

		if (pyMEs != null) {
			log.debug("ADD PyMEs...");
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
		
		if(idPyMEs != null){
			log.debug("Registrando Diplomas");
			log.debug("PyMEs ID's " + idPyMEs);
			setMensaje(coordinadorConsultoriasService.regDiploma(idPyMEs));
		}
		
		if (idConsultora != 0) {
			log.debug("Asignando PyMEs a " + idConsultora);
			log.debug("PyMEs ID's " + idPyMEs);
			setMensaje(coordinadorConsultoriasService.asignaPyMEs(idConsultora, idPyMEs));
		}

		return SUCCESS;
	}

	@Action(value = "/coordinadorConsultoriasPyMEsAdd", results = { @Result(name = "success", location = "coordinacion.consultorias.pymes.add", type = "tiles") })
	public String coordinadorConsultoriasPyMEsAdd() throws BaseBusinessException{
		log.debug("coordinadorConsultoriasPyMEsAdd()");
		setMenu(1);
		
		setTractorasList(ccmxService.getTractoras());
		
		return SUCCESS;
	}
	
	@Action(value = "/coordinadorConsultoriasPyMEsAssign", results = { @Result(name = "success", location = "coordinacion.consultorias.pymes.assign", type = "tiles") })
	public String coordinadorConsultoriasPyMEsAssign() throws BaseBusinessException{
		log.debug("coordinadorConsultoriasPyMEsAssign()");
		setMenu(1);
		
		setListPyMEs(coordinadorConsultoriasService.getPyME());
		setConsultorasList(coordinadorConsultoriasService.getConsultora());
		
		return SUCCESS;
	}
	
	@Action(value = "/coordinadorConsultoriasPyMEsReAssign", results = { @Result(name = "success", location = "coordinacion.consultorias.pymes.reassign", type = "tiles") })
	public String coordinadorConsultoriasPyMEsReAssign() throws BaseBusinessException{
		log.debug("coordinadorConsultoriasPyMEsReAssign()");
		setMenu(1);
		
		if (idConsultora != 0) {
			log.debug("ReAsignando PyMEs a " + idConsultora);
			log.debug("PyMEs ID's " + idPyMEs);
			setMensaje(coordinadorConsultoriasService.reAsignaPyME(idConsultora, idPyMEs));
		}
		
		List<PyMEs> list = new ArrayList<PyMEs>();
		log.debug(busqueda);
		log.debug(tractora);
		log.debug(cveScian);
		if (!Null.free(busqueda).trim().isEmpty()) {
			list = coordinadorConsultoriasService.getReasignaPyME(Null.free(busqueda),
					Null.free(tractora).equals("-1") ? "" : tractora,
					Null.free(cveScian));
			setListPyMEs(list);
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
		setTractorasList(ccmxService.getTractoras());
		setConsultorasList(coordinadorConsultoriasService.getConsultora());
		
		
		return SUCCESS;
	}
	
	@Action(value = "/coordinadorConsultoriasDiplomasPyMEs", results = { @Result(name = "success", location = "coordinacion.consultorias.pymes.diploma", type = "tiles") })
	public String coordinadorConsultoriasDiplomasPyMEs() throws BaseBusinessException{
		log.debug("coordinadorConsultoriasDiplomasPyMEs()");
		setMenu(1);
		
		setListDiplomasPyMEs(coordinadorConsultoriasService.getDiplomaPyME());
		
		return SUCCESS;
	}
	
	@Action(value = "/coordinadorConsultoriasBusquedaPyMEs", results = { @Result(name = "success", location = "coordinacion.consultorias.pymes.busqueda", type = "tiles") })
	public String coordinadorConsultoriasBusquedaPyMEs() throws BaseBusinessException{
		log.debug("coordinadorConsultoriasBusquedaPyMEs()");
		setMenu(1);
		
		if(idUsuario != 0){
			log.debug("Consultando la PyME");
			setPyMEs(pyMEsService.getPyME(idUsuario));
			log.debug("Usuario=" + idUsuario);
			String idDom = pyMEsService.getIdDomicilio(idUsuario);
			log.debug("idDomicilio=" + idDom);
			setDomicilios(pyMEsService.getDomicilio(Integer.parseInt(idDom)));

			setEstadosVenta(pyMEsService.getEstadoVenta(idUsuario));

			String idInd = pyMEsService.getIdIndicador(idUsuario);
			log.debug("idIndicador=" + idInd);
			setIndicadores(pyMEsService.getIndicador(Integer.parseInt(idInd)));

			setRelPymesTractoras(pyMEsService.getCalificacion(idUsuario));
			setIndicadoresMes(pyMEsService.getIndicadorMes(idUsuario));
			setServiciosConsultoria(pyMEsService.getServConsultorias(idUsuario));
		}
		
		List<PyMEs> list = new ArrayList<PyMEs>();
		log.debug(busqueda);
		log.debug(estado);
		log.debug(cveScian);
		if (!Null.free(busqueda).trim().isEmpty()) {
			list = pyMEsService.getBusquedaPyME(Null.free(busqueda),
					Null.free(estado).equals("-1") ? "" : estado,
					Null.free(cveScian));
			setListPyMEs(list);
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
	
	@Action(value = "/coordinadorConsultoriasSolicitudesShow", results = { @Result(name = "success", location = "coordinacion.consultorias.solicitudes.show", type = "tiles") })
	public String coordinadorConsultoriasSolicitudesShow()
			throws BaseBusinessException {
		log.debug("coordinadorConsultoriasSolicitudesShow()");
		setMenu(2);

		if(idUsuario != 0 && numeroFactura ==  null){
			log.debug("Consultando Facturas...");
			setListFacturas(coordinadorConsultoriasService.getFactura(idUsuario));
			setFacturasList(coordinadorConsultoriasService.getInfoFactura(idUsuario));
		}
		
		if(idUsuario != 0 && numeroFactura !=  null){
			log.debug("Consultando Detalle de Factura...");
			setListPyMEs(coordinadorConsultoriasService.getDetalleFactura(numeroFactura));
		}
		
		if(idFacturas != null && fechaPago == null){
			log.debug("Liberando Facturas ..." + idFacturas);
			log.debug("Registrando Montos ..." + montoTotal);
			setMensaje(coordinadorConsultoriasService.liberaFactura(idFacturas, montoTotal));
		}
		
		if(idPagosFacturas != null){
			log.debug("Eliminando Pagos a PyMEs..." + idPagosFacturas);
			setMensaje(coordinadorConsultoriasService.quitarRelFactura(idPagosFacturas));
		}
		
		if(fechaPago != null){
			log.debug("Salvando la Fecha de la factura..." + idFacturas + fechaPago);
			setMensaje(coordinadorConsultoriasService.saveFechaFactura(idFacturas, fechaPago));
		}
		
		setConsultorasList(coordinadorConsultoriasService.getConsultora());
		
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

	public PyMEs getPyMEs() {
		return pyMEs;
	}

	public void setPyMEs(PyMEs pyMEs) {
		this.pyMEs = pyMEs;
	}

	public List<PyMEs> getListPyMEs() {
		return listPyMEs;
	}

	public void setListPyMEs(List<PyMEs> listPyMEs) {
		this.listPyMEs = listPyMEs;
	}

	public List<PyMEs> getListDiplomasPyMEs() {
		return listDiplomasPyMEs;
	}

	public void setListDiplomasPyMEs(List<PyMEs> listDiplomasPyMEs) {
		this.listDiplomasPyMEs = listDiplomasPyMEs;
	}

	public List<CatScianCcmx> getListCat1() throws ProductosNoObtenidosException {
		setListCat1(tractorasService.getNivelScian(0));
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

	public Domicilios getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(Domicilios domicilios) {
		this.domicilios = domicilios;
	}

	public EstadosVenta getEstadosVenta() {
		return estadosVenta;
	}

	public void setEstadosVenta(EstadosVenta estadosVenta) {
		this.estadosVenta = estadosVenta;
	}

	public Indicadores getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(Indicadores indicadores) {
		this.indicadores = indicadores;
	}

	public ServiciosConsultoria getServiciosConsultoria() {
		return serviciosConsultoria;
	}

	public void setServiciosConsultoria(ServiciosConsultoria serviciosConsultoria) {
		this.serviciosConsultoria = serviciosConsultoria;
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

	public int getIdConsultora() {
		return idConsultora;
	}

	public void setIdConsultora(int idConsultora) {
		this.idConsultora = idConsultora;
	}

	public String getIdPyMEs() {
		return idPyMEs;
	}

	public void setIdPyMEs(String idPyMEs) {
		this.idPyMEs = idPyMEs;
	}

	public List<Facturas> getListFacturas() {
		return listFacturas;
	}

	public void setListFacturas(List<Facturas> listFacturas) {
		this.listFacturas = listFacturas;
	}

	public List<Facturas> getFacturasList() {
		return facturasList;
	}

	public void setFacturasList(List<Facturas> facturasList) {
		this.facturasList = facturasList;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getIdFacturas() {
		return idFacturas;
	}

	public void setIdFacturas(String idFacturas) {
		this.idFacturas = idFacturas;
	}

	public String getIdPagosFacturas() {
		return idPagosFacturas;
	}

	public void setIdPagosFacturas(String idPagosFacturas) {
		this.idPagosFacturas = idPagosFacturas;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getTractora() {
		return tractora;
	}

	public void setTractora(String tractora) {
		this.tractora = tractora;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(String montoTotal) {
		this.montoTotal = montoTotal;
	}
	
}
