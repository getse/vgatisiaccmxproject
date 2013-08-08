/*
 * TractorasAction.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.tractoras.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.exception.ConsultoriasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.dto.Contacto;
import mx.com.vgati.ccmx.vinculacion.dto.Requerimientos;
import mx.com.vgati.ccmx.vinculacion.dto.Respuesta;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.publico.service.InitService;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.IndicadoresNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.service.PyMEsService;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXParticipantes;
import mx.com.vgati.ccmx.vinculacion.report.dto.Filtros;
import mx.com.vgati.ccmx.vinculacion.report.dto.FiltrosGenerales;
import mx.com.vgati.ccmx.vinculacion.report.dto.PYMESReporte;
import mx.com.vgati.ccmx.vinculacion.report.dto.TotalEmpresas;
import mx.com.vgati.ccmx.vinculacion.report.service.ReportService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.CatIndicadoresTractora;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.CatScianCcmx;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Productos;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.RelPyMEsTractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.ProductosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.service.TractorasService;
import mx.com.vgati.framework.action.AbstractBaseAction;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Usuario;
import mx.com.vgati.framework.exception.BaseBusinessException;
import mx.com.vgati.framework.util.Null;
import mx.com.vgati.framework.util.SendEmail;
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
@Namespaces({ @Namespace(value = "comprador") })
public class TractorasAction extends AbstractBaseAction {

	private int menu = 1;
	private static final String[] op = { "MI INFORMACI&Oacute;N",
			"REQUERIMIENTOS", "B&Uacute;SQUEDA PyMEs",
			"VINCULACI&Oacute;N PyMEs", "REPORTES", "INDICADORES" };
	private static final String[] fr = { "compradorInformacionShow.do",
			"compradorRequerimientosShow.do", "compradorBusquedaShow.do",
			"compradorPyMEsShow.do", "compradorReportesShow.do",
			"compradorIndicadoresShow.do" };

	private TractorasService tractorasService;
	private InitService initService;
	private PyMEsService pyMEsService;
	private List<Requerimientos> listRequerimientos;
	private List<Respuesta> listRespuestas;
	private Requerimientos requerimientos;
	private Respuesta respuesta;
	private List<CatScianCcmx> listCatProductos;
	private List<CatScianCcmx> listCat2;
	private List<CatScianCcmx> listCat3;
	private List<CatScianCcmx> listCat4;
	private List<CatScianCcmx> listCat5;
	private List<Productos> productos;
	private List<PyMEs> listPyMEs;
	private PyMEs pyMEs;
	private EstadosVenta estadosVentas;
	private List<PyMEs> listPyMEsIndicadores;
	private String busqueda;
	private String chain;
	private String estado;
	private String cveScian;
	private String producto;
	private int idUsuario;
	private int idRespuesta;
	private Tractoras tractoras;
	private Domicilios domicilios;
	private Mensaje mensaje;
	private String lugares;
	private String cve;
	private Date fechaSuministro;
	private Date fechaExpira;
	private int idArchivo;
	private String nameArchivo;
	private String mimeArchivo;
	private InputStream archivo;
	private String init;
	private int cat1;
	private int cat2;
	private int cat3;
	private int cat4;
	private int cat5;
	private List<Consultoras> consultorasList;
	private List<CCMXParticipantes> serviciosList;
	private String opcion;
	private Filtros filtros;
	private String salida;
	private ReportService reportService;
	private int indicador;
	private int rel;
	private String empresa;
	private int calificaPyME;
	private Indicadores indicadores;
	private Indicadores indicadoresMes;
	private CatIndicadoresTractora catIndicadoresTractora;
	private RelPyMEsTractoras relPyMEsTractoras;
	private List<CatIndicadoresTractora> listCatIndicadoresTractora;
	private List<FiltrosGenerales> menuCedula;
	private List<FiltrosGenerales> menuEstatus;
	private ServiciosConsultoria serviciosConsultoria;

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
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

	public void setTractorasService(TractorasService tractorasService) {
		this.tractorasService = tractorasService;
	}

	public void setInitService(InitService initService) {
		this.initService = initService;
	}

	public void setPyMEsService(PyMEsService pyMEsService) {
		this.pyMEsService = pyMEsService;
	}

	@Action(value = "/compradorInformacionShow", results = {
			@Result(name = "success", location = "tractora.datos.show", type = "tiles"),
			@Result(name = "busqueda", location = "tractora.busqueda.show", type = "tiles") })
	public String compradorInformacionShow() throws NumberFormatException,
			BaseBusinessException {
		log.debug("compradorInformacionShow()");

		Usuario u = getUsuario();
		log.debug("Usuario=" + u);
		setTractoras(tractorasService.getTractora(u.getIdUsuario()));
		String idDom = tractorasService.getIdDomicilio(u.getIdUsuario());
		log.debug("idDomicilio=" + idDom);
		setDomicilios(tractorasService.getDomicilio(Integer.parseInt(idDom)));
		log.debug("domicilio=" + domicilios);
		if (Null.free(init).equals("1")
				&& !Null.free(tractoras.getPuesto()).trim().isEmpty()) {
			setMenu(3);
			return "busqueda";
		}
		setMenu(1);

		return SUCCESS;
	}

	@Action(value = "/compradorInformacionAdd", results = { @Result(name = "success", location = "tractora.datos.show", type = "tiles") })
	public String compradorInformacionAdd() throws BaseBusinessException {
		log.debug("compradorInformacionAdd()");
		setMenu(1);

		if (tractoras != null) {
			log.debug("Actualizando los datos de la tractora" + tractoras);
			tractoras.setIdUsuario(getUsuario().getIdUsuario());
			setMensaje(tractorasService.updateTractoras(tractoras));
		}
		if (domicilios != null && domicilios.getIdDomicilio() == 0) {
			log.debug("Insertando el domicilio" + domicilios);
			setMensaje(tractorasService.insertDomicilio(domicilios));
			log.debug("Insertando id's");
			log.debug("mensaje=" + mensaje);
			domicilios.setIdDomicilio(Integer
					.parseInt(mensaje != null ? mensaje.getId() : "0"));
			tractoras.setIdUsuario(getUsuario().getIdUsuario());
			setMensaje(tractorasService.insertRelDomicilio(domicilios,
					tractoras));
		} else if (domicilios != null) {
			log.debug("Actualizando el domicilio" + domicilios);
			setMensaje(tractorasService.updateDomicilio(domicilios));
		}

		Usuario u = getUsuario();
		log.debug("Usuario=" + u);
		setTractoras(tractorasService.getTractora(u.getIdUsuario()));

		return SUCCESS;
	}

	@Action(value = "/compradorRequerimientosShow", results = {
			@Result(name = "success", location = "tractora.requerimientos.list", type = "tiles"),
			@Result(name = "input", location = "tractora.requerimientos.add", type = "tiles") })
	public String compradorRequerimientosShow() {
		log.debug("compradorRequerimientosShow()");
		setMenu(2);
		return SUCCESS;
	}

	@Action(value = "/compradorRequerimientoSave", results = {
			@Result(name = "success", location = "tractora.requerimientos.add", type = "tiles"),
			@Result(name = "input", location = "tractora.requerimientos.add", type = "tiles"),
			@Result(name = "error", location = "tractora.requerimientos.add", type = "tiles") })
	public String compradorRequerimientoSave() throws BaseBusinessException {
		log.debug("compradorRequerimientoSave()");
		setMenu(2);

		log.debug("requerimientos=" + requerimientos);
		log.debug("fechaSuministro=" + fechaSuministro);
		log.debug("fechaExpira=" + fechaExpira);
		requerimientos.setFechaSuministro(fechaSuministro);
		requerimientos.setFechaExpira(fechaExpira);

		if (requerimientos != null && requerimientos.getIdRequerimiento() == 0) {
			log.debug("guardando el requerimiento:" + requerimientos);
			requerimientos.setIdTractora(getUsuario().getIdUsuario());
			setMensaje(tractorasService.insertRequerimiento(requerimientos));
		} else if (requerimientos != null) {
			log.debug("actualizando el requerimiento:" + requerimientos);
			setMensaje(tractorasService.updateRequerimiento(requerimientos));
		}

		if (mensaje.getRespuesta() == 0) {
			log.debug("Enviando correos electronicos");
			Tractoras t = tractorasService.getTractora(getUsuario()
					.getIdUsuario());
			String cve = String.valueOf(requerimientos.getCveScian());
			List<Contacto> correos = tractorasService.getCorreosByProducto(cve);
			if (correos.size() > 0) {
				StringBuffer suministro = new StringBuffer();
				List<EstadosVenta> ev = requerimientos.getLugarSuministro();
				for (EstadosVenta edo : ev) {
					if (!Null.free(edo.getEstadoVenta()).isEmpty()) {
						suministro.append(edo.getEstadoVenta());
						suministro.append(", ");
					}
				}
				if (suministro.length() > 0)
					suministro.deleteCharAt(suministro.lastIndexOf(","));
				SendEmail envia = new SendEmail(
						null,
						"SIA CCMX Aviso de Requerimiento",
						"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimada Micro, pequeña o mediana empresa,"
								.concat("<br /><br />Nos complace informante que la empresa ")
								.concat(Null.free(t.getEmpresa()))
								.concat(" ha subido un requerimiento relacionado con ")
								.concat(Null.free(tractorasService
										.getTercerNivelScian(requerimientos
												.getCveScian())))
								.concat(". Se espera que el suministro del producto o la provisión del servicio sea en ")
								.concat(suministro.toString().trim())
								.concat(", el ")
								.concat(requerimientos.getFechaSuministro() == null ? (requerimientos
										.isbIndefinido() ? "Indefinido"
										: requerimientos
												.isbContinuoSuministro() ? "Continuo"
												: requerimientos
														.isbVariasFechas() ? ("Varias fechas ".concat(Null.free(requerimientos
														.getVariasFechas())))
														: "")
										: requerimientos.getFechaSuministro()
												.toString())
								.concat(".")
								.concat("<br /><br />Dicho requerimiento tiene como fecha de vencimiento para que puedas establecer contacto con ")
								.concat(Null.free(t.getEmpresa()))
								.concat(", a través de nuestro sistema el ")
								.concat(requerimientos.getFechaExpira() == null ? (requerimientos
										.isbContinuoExpira() ? "Continuo" : "")
										: requerimientos.getFechaExpira()
												.toString())
								.concat(".<br /><br />No olvides ingresar a tu cuenta en el Sistema de Vinculación para que puedas ver los detalles ")
								.concat("del requerimiento y las condiciones específicas del mismo.")
								.concat("<br /><br />Con el objetivo de que el sistema sea eficiente y confiable tanto para las PyMEs como para las ")
								.concat("empresas compradoras, te solicitamos que solamente establezcas contacto con las empresas compradoras, ")
								.concat("en respuesta a un requerimiento sobre el cual tienes completa capacidad de atender.")
								.concat("<br /><br />Por otro lado, no olvides actualizar tu perfil si tus datos de contacto han ")
								.concat("cambiado o si tienes nuevos productos o servicios que ofrecer.")
								.concat("<br /><br />En caso de cualquier duda sobre la operación y funcionamiento del sistema, no ")
								.concat("dudes en ponerte en contacto con andres.blancas@ccmx.org.mx.")
								.concat("<br /><br />Muchas gracias por utilizar el sistema de vinculación del CCMX.</h5>"),
						correos);
				log.debug("Enviando correo electrónico:" + envia);
			} else {
				log.info("no se envio correo de requerimiento");
			}
		}

		setRequerimientos(tractorasService.getRequerimiento(getMensaje()
				.getId()));

		return SUCCESS;
	}

	@Action(value = "/compradorRequerimientoAdd", results = {
			@Result(name = "success", location = "tractora.requerimientos.add", type = "tiles"),
			@Result(name = "input", location = "tractora.requerimientos.add", type = "tiles") })
	public String compradorRequerimientoAdd()
			throws RequerimientosNoObtenidosException,
			ProductosNoObtenidosException {
		log.debug("compradorRequerimientoAdd()");
		setMenu(2);

		log.debug("requerimientos=" + requerimientos);
		if (requerimientos != null && requerimientos.getIdRequerimiento() != 0) {
			log.debug("requerimientos=" + requerimientos);
			setRequerimientos(tractorasService.getRequerimiento(String
					.valueOf(getRequerimientos().getIdRequerimiento())));
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
			log.debug("consultando Cat 4 = " + cat4);
			setListCat5(tractorasService.getNivelScian(cat4));
		}

		return SUCCESS;
	}

	@Action(value = "/compradorRequerimientoBusqueda", results = { @Result(name = "success", location = "tractora.requerimientos.busqueda", type = "tiles") })
	public String compradorRequerimientoBusqueda() throws BaseBusinessException {
		log.debug("compradorRequerimientoBusqueda()");

		if (!Null.free(chain).isEmpty()) {
			log.debug("buscando..." + chain);
			setProductos(tractorasService.getProductos(chain));
			log.debug("resultado: " + productos);
		}

		return SUCCESS;
	}

	@Action(value = "/compradorRequerimientoDelete", results = {
			@Result(name = "success", location = "tractora.requerimientos.list", type = "tiles"),
			@Result(name = "input", location = "tractora.requerimientos.list", type = "tiles"),
			@Result(name = "error", location = "tractora.requerimientos.list", type = "tiles"),
			@Result(name = "invalid", location = "tractora.requerimientos.list", type = "tiles") })
	public String compradorRequerimientoDelete() throws BaseBusinessException {
		log.debug("compradorRequerimientoDelete()");
		setMenu(2);

		Usuario u = getUsuario();
		log.debug("Usuario=" + u);
		if (!initService.validateUsuario(cve, u.getIdUsuario())) {
			Mensaje mensaje = new Mensaje(1,
					"La contraseña no es correcta, intente de nuevo");
			setMensaje(mensaje);
			return "invalid";
		}
		log.debug("requerimientos=" + requerimientos);
		setMensaje(tractorasService.deleteRequerimiento(requerimientos));

		return SUCCESS;
	}

	@Action(value = "/compradorRequerimientoPyME", results = { @Result(name = "success", location = "tractora.requerimientos.pyme", type = "tiles") })
	public String compradorRequerimientoPyME() throws BaseBusinessException {
		log.debug("compradorRequerimientoPyME()");
		setMenu(2);

		if (idUsuario != 0) {
			log.debug("Consultando la PyME" + idUsuario);
			setPyMEs(pyMEsService.getPyME(idUsuario));
			setEstadosVentas(pyMEsService.getEstadoVenta(idUsuario));

			String idInd = pyMEsService.getIdIndicador(idUsuario);
			log.debug("idIndicador=" + idInd);
			setIndicadores(pyMEsService.getIndicador(Integer.parseInt(idInd)));
			setRelPyMEsTractoras(pyMEsService.getCalificacion(idUsuario));
			setIndicadoresMes(pyMEsService.getIndicadorMes(idUsuario));
			setServiciosConsultoria(pyMEsService.getServConsultorias(idUsuario));
		}

		return SUCCESS;
	}

	@Action(value = "/compradorRequerimientoRespuesta", results = { @Result(name = "success", location = "tractora.requerimientos.respuesta", type = "tiles") })
	public String compradorRequerimientoRespuesta()
			throws BaseBusinessException {
		log.debug("compradorRequerimientoRespuesta()");
		setMenu(2);

		if (idRespuesta != 0) {
			log.debug("Consultando Respuesta: " + idRespuesta);
			setRespuesta(tractorasService.getRespuesta(idRespuesta));
		}

		return SUCCESS;
	}

	@Action(value = "/compradorBusquedaShow", results = { @Result(name = "success", location = "tractora.busqueda.show", type = "tiles") })
	public String compradorBusquedaShow() throws PyMEsNoObtenidasException,
			ProductosNoObtenidosException, IndicadoresNoObtenidosException,
			ConsultoriasNoObtenidasException {
		log.debug("compradorBusquedaShow()");
		setMenu(3);

		// TODO aquí no se llena la lista de PyMEs cuando no se hace una búsqueda como en AdminTractoras, está bien así?
		if (!Null.free(busqueda).trim().isEmpty()) {
			List<PyMEs> list = new ArrayList<PyMEs>();
			log.debug(busqueda);
			log.debug(estado);
			log.debug(cveScian);
			list = pyMEsService.getBusquedaPyME(Null.free(busqueda),
					Null.free(estado).equals("-1") ? "" : Null.free(estado),
					Null.free(cveScian));
			setListPyMEs(list);
		}

		if (idUsuario != 0) {
			log.debug("Consultando la PyME" + idUsuario);
			setPyMEs(pyMEsService.getPyME(idUsuario));
			setEstadosVentas(pyMEsService.getEstadoVenta(idUsuario));

			String idInd = pyMEsService.getIdIndicador(idUsuario);
			log.debug("idIndicador=" + idInd);
			setIndicadores(pyMEsService.getIndicador(Integer.parseInt(idInd)));
			setRelPyMEsTractoras(pyMEsService.getCalificacion(idUsuario));
			setIndicadoresMes(pyMEsService.getIndicadorMes(idUsuario));
			setServiciosConsultoria(pyMEsService.getServConsultorias(idUsuario));
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

	@Action(value = "/compradorPyMEsShow", results = { @Result(name = "success", location = "tractora.pymes.list", type = "tiles") })
	public String compradorPyMEsShow() throws BaseBusinessException {
		log.debug("compradorPyMEsShow");
		setMenu(4);

		if (idUsuario != 0) {
			log.debug("Consultando la PyME" + idUsuario);
			setPyMEs(pyMEsService.getPyME(idUsuario));
			setEstadosVentas(pyMEsService.getEstadoVenta(idUsuario));

			String idInd = pyMEsService.getIdIndicador(idUsuario);
			log.debug("idIndicador=" + idInd);
			setIndicadores(pyMEsService.getIndicador(Integer.parseInt(idInd)));
			setRelPyMEsTractoras(pyMEsService.getCalificacion(idUsuario));
			setIndicadoresMes(pyMEsService.getIndicadorMes(idUsuario));
			setServiciosConsultoria(pyMEsService.getServConsultorias(idUsuario));
		}

		setListPyMEs(tractorasService.getPymeVinculacion(getUsuario()
				.getIdUsuario()));

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Action(value = "/compradorReportesShow", results = {
			@Result(name = "success", location = "tractora.reportes.show", type = "tiles"),
			@Result(name = "input", location = "tractora.reportes.show", type = "tiles"),
			@Result(name = "error", location = "tractora.reportes.show", type = "tiles") })
	public String compradorReportesShow() throws BaseBusinessException {
		log.debug("compradorReportesShow()");
		setMenu(5);
		if (opcion != null && opcion.equals("servicios")) {
			setOpcion(opcion);
			setConsultorasList(reportService.getConsultoras());
			return SUCCESS;

		} else if (opcion != null && opcion.equals("pymes")) {
			setOpcion(opcion);
			setConsultorasList(reportService.getConsultores(0));
			setMenuEstatus(reportService.getMenuEstatus());
			setMenuCedula(reportService.getMenuCedulas());
			return SUCCESS;
		} else if (opcion != null && opcion.equals("servRepor")) {
			setOpcion("descarga");
			log.debug("Reporte servicios");
			String direccion = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("/");
			Usuario usuario = getUsuario();
			filtros.setId(usuario.getIdUsuario());
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
					parameters.put("radarAntesControl", reportService.getPromedioRadarAntes(filtros)*1.0);
					parameters.put("radarDespuesControl", reportService.getPromedioRadarDespues(filtros)*1.0);

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
					return ERROR;
				} catch (JRException e) {
					e.printStackTrace();
					return ERROR;
				}
				return SUCCESS;
			}

		} else if (opcion != null && opcion.trim().equals("pyRepor")) {
			log.debug("Generando reporte de pymes");
			String direccion = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("/");
			Usuario usuario = getUsuario();
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

	@Action(value = "/compradorIndicadoresShow", results = { @Result(name = "success", location = "tractora.indicadores.show", type = "tiles") })
	public String compradorIndicadoresShow() throws BaseBusinessException {
		log.debug("compradorIndicadoresShow()");
		setMenu(6);

		if (indicador == 0) {
			log.debug("ConsultandoPyMEs Vinculadas a comprador...");
			setListPyMEsIndicadores(tractorasService
					.getPymeTractora(getUsuario().getIdUsuario()));
		}

		if (indicador != 0) {
			log.debug("Llenando combo de indicadores...");
			setListCatIndicadoresTractora(tractorasService.getCatIndicador());
		}

		log.debug("Consultamos el id REL_PYMES_TRACTORAS...");
		String idPT = tractorasService.getIdPyMETractora(getUsuario()
				.getIdUsuario(), rel);

		if (indicadores != null) {
			indicadores.setIdPyMETractora(Integer.parseInt(idPT));
			log.debug("Consultando si existe el indicador...");
			String idInd = tractorasService.getIdIndicador(indicadores);
			log.debug("Resultado del id indicador..." + idInd);
			indicadores.setIdIndicador(Integer.parseInt(idInd));

			if (indicadores.getIdIndicador() != 0) {
				log.debug("Actualizando el indicador...");
				setMensaje(tractorasService.updateIndicador(indicadores));
			} else {
				log.debug("Insertando el indicador...");
				setMensaje(tractorasService.insertIndicador(indicadores));
			}
		}

		if (relPyMEsTractoras != null) {
			relPyMEsTractoras.setIdPyMETractora(Integer.parseInt(idPT));

			log.debug("Insertando la calificación..." + relPyMEsTractoras);
			setMensaje(tractorasService.insertCalificacion(relPyMEsTractoras));
		}

		if (calificaPyME != 0) {
			setRelPyMEsTractoras(tractorasService.getCalificacion(rel));
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

	public List<Requerimientos> getListRequerimientos()
			throws BaseBusinessException {
		Usuario u = getUsuario();
		log.debug("Usuario=" + u);
		setListRequerimientos(tractorasService.getRequerimientos(u
				.getIdUsuario()));
		return listRequerimientos;
	}

	public void setListRequerimientos(List<Requerimientos> listRequerimientos) {
		this.listRequerimientos = listRequerimientos;
	}

	public List<Respuesta> getListRespuestas() throws BaseBusinessException {
		setListRespuestas(tractorasService.getRespuestas(requerimientos
				.getIdRequerimiento()));
		return listRespuestas;
	}

	public void setListRespuestas(List<Respuesta> listRespuestas) {
		this.listRespuestas = listRespuestas;
	}

	public Requerimientos getRequerimientos() {
		return requerimientos;
	}

	public void setRequerimientos(Requerimientos requerimientos) {
		this.requerimientos = requerimientos;
	}

	public Respuesta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	public List<CatScianCcmx> getListCatProductos()
			throws ProductosNoObtenidosException {

		setListCatProductos(tractorasService.getNivelScian(0));

		return listCatProductos;
	}

	public void setListCatProductos(List<CatScianCcmx> listCatProductos) {
		this.listCatProductos = listCatProductos;
	}

	public List<PyMEs> getListPyMEs() throws PyMEsNoObtenidasException {
		return listPyMEs;
	}

	public void setListPyMEs(List<PyMEs> listPyMEs) {
		this.listPyMEs = listPyMEs;
	}

	public PyMEs getPyMEs() {
		return pyMEs;
	}

	public void setPyMEs(PyMEs pyMEs) {
		this.pyMEs = pyMEs;
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

	public String getChain() {
		return chain;
	}

	public void setChain(String chain) {
		this.chain = chain;
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

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public void setTractoras(Tractoras tractoras) {
		this.tractoras = tractoras;
	}

	public Tractoras getTractoras() {
		return tractoras;
	}

	public Domicilios getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(Domicilios domicilios) {
		this.domicilios = domicilios;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setLugares(String lugares) {
		this.lugares = lugares;
	}

	public String getLugares() {
		return lugares;
	}

	public String getCve() {
		return cve;
	}

	public void setCve(String cve) {
		this.cve = cve;
	}

	public void setFechaSuministro(Date fechaSuministro) {
		this.fechaSuministro = fechaSuministro;
	}

	public Date getFechaSuministro() {
		return fechaSuministro;
	}

	public void setFechaExpira(Date fechaExpira) {
		this.fechaExpira = fechaExpira;
	}

	public Date getFechaExpira() {
		return fechaExpira;
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

	public String getInit() {
		return init;
	}

	public void setInit(String init) {
		this.init = init;
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
			@Result(name = "input", location = "tractora.requerimientos.add", type = "tiles"),
			@Result(name = "error", location = "tractora.requerimientos.add", type = "tiles") })
	public String showDoc() throws DocumentoNoObtenidoException,
			RequerimientosNoObtenidosException {
		log.debug("showDoc()");
		setArchivo(tractorasService.getArchivo(idArchivo).getIs());

		log.debug("archivo=" + archivo);
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		return SUCCESS;
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

	public List<Productos> getProductos() throws ProductosNoObtenidosException {
		return productos;
	}

	public void setProductos(List<Productos> productos) {
		this.productos = productos;
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

	public List<PyMEs> getListPyMEsIndicadores() {
		return listPyMEsIndicadores;
	}

	public void setListPyMEsIndicadores(List<PyMEs> listPyMEsIndicadores) {
		this.listPyMEsIndicadores = listPyMEsIndicadores;
	}

	public int getIndicador() {
		return indicador;
	}

	public void setIndicador(int indicador) {
		this.indicador = indicador;
	}

	public int getRel() {
		return rel;
	}

	public void setRel(int rel) {
		this.rel = rel;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public int getCalificaPyME() {
		return calificaPyME;
	}

	public void setCalificaPyME(int calificaPyME) {
		this.calificaPyME = calificaPyME;
	}

	public Indicadores getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(Indicadores indicadores) {
		this.indicadores = indicadores;
	}

	public CatIndicadoresTractora getCatIndicadoresTractora() {
		return catIndicadoresTractora;
	}

	public void setCatIndicadoresTractora(
			CatIndicadoresTractora catIndicadoresTractora) {
		this.catIndicadoresTractora = catIndicadoresTractora;
	}

	public RelPyMEsTractoras getRelPyMEsTractoras() {
		return relPyMEsTractoras;
	}

	public void setRelPyMEsTractoras(RelPyMEsTractoras relPyMEsTractoras) {
		this.relPyMEsTractoras = relPyMEsTractoras;
	}

	public List<CatIndicadoresTractora> getListCatIndicadoresTractora() {
		return listCatIndicadoresTractora;
	}

	public void setListCatIndicadoresTractora(
			List<CatIndicadoresTractora> listCatIndicadoresTractora) {
		this.listCatIndicadoresTractora = listCatIndicadoresTractora;
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

	public Indicadores getIndicadoresMes() {
		return indicadoresMes;
	}

	public void setIndicadoresMes(Indicadores indicadoresMes) {
		this.indicadoresMes = indicadoresMes;
	}
}
