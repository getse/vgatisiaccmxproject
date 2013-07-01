/*
 * CoordinadorDiplomadosAction.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.action;

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
import java.util.StringTokenizer;

import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Participantes;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Sesiones;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.service.CoordinadorDiplomadosService;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.service.PyMEsService;
import mx.com.vgati.ccmx.vinculacion.report.dto.FinanzasDiplomados;
import mx.com.vgati.ccmx.vinculacion.report.dto.PymesDiplomados;
import mx.com.vgati.ccmx.vinculacion.report.service.ReportService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.CatScianCcmx;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.RelPyMEsTractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.ProductosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.service.TractorasService;
import mx.com.vgati.framework.action.AbstractBaseAction;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.exception.BaseBusinessException;
import mx.com.vgati.framework.util.Null;
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
@Namespaces({ @Namespace(value = "diplomados/coordinacion") })
public class CoordinadorDiplomadosAction extends AbstractBaseAction {

	private int menu = 1;
	private static final String[] op = { "DIPLOMADOS", "ENCUESTAS", "PyMEs",
			"REPORTES" };
	private static final String[] fr = {
			"coordinadorDiplomadosDiplomadosShow.do",
			"coordinadorDiplomadosEncuestasShow.do",
			"coordinadorDiplomadosPyMEsShow.do",
			"coordinadorDiplomadosReportesShow.do", "", "" };

	private CoordinadorDiplomadosService coordinadorDiplomadosService;
	private TractorasService tractorasService;
	private PyMEsService pyMEsService;
	private ReportService reportService;
	private int idUsuario;
	private List<CatScianCcmx> listCat2;
	private List<CatScianCcmx> listCat3;
	private List<CatScianCcmx> listCat4;
	private List<CatScianCcmx> listCat5;
	private int cat1;
	private int cat2;
	private int cat3;
	private int cat4;
	private int cat5;
	private Mensaje mensaje;
	private String busqueda;
	private String estado;
	private String cveScian;
	private String producto;
	private List<CatScianCcmx> listCatProductos;
	private ServiciosConsultoria serviciosConsultoria;
	private Indicadores indicadoresMes;
	private RelPyMEsTractoras relPyMEsTractoras;
	private Indicadores indicadores;
	private List<PyMEs> listPyMEs;
	private List<PyMEs> listPymes;
	private PyMEs pyMEs;
	private EstadosVenta estadosVentas;
	private String opcion;
	private String salida;
	private InputStream archivo;
	private List<Tractoras> listTractoras;
	private int idTractora;
	private int generacion;
	private List<Diplomados> listDiplomados;
	private String tema;
	private List<Participantes> listParticipantes;
	private List<Sesiones> listSesiones;
	private int numeroSesiones;
	private int idPyme;
	private int idDiplomado;
	private String nombresAsistentes;
	private String appPatAsistentes;
	private String appMatAsistentes;
	private String telAsistentes;
	private String correoAsistentes;
	private List<Participantes> listInacistencias;
	
	
	public void setCoordinadorDiplomadosService(
			CoordinadorDiplomadosService coordinadorDiplomadosService) {
		this.coordinadorDiplomadosService = coordinadorDiplomadosService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public void setTractorasService(TractorasService tractorasService) {
		this.tractorasService = tractorasService;
	}

	public void setPyMEsService(PyMEsService pyMEsService) {
		this.pyMEsService = pyMEsService;
	}

	@Action(value = "/coordinadorDiplomadosDiplomadosShow", results = { @Result
			(name = "success", location = "coordinacion.diplomados.diplomados.show", type = "tiles") })
	public String coordinadorDiplomadosDiplomadosShow()
			throws BaseBusinessException {
		log.debug("coordinadorDiplomadosDiplomadosShow()");
	//TODO quitar al final
		setMenu(1);
		if (nombresAsistentes != null && appPatAsistentes != null && appMatAsistentes != null) {
			
			//TODO Revisar cuando Omar suba cambios
			log.debug("Salvando los asistentes..." + idDiplomado);
			Asistentes asistentes;
			StringTokenizer nombres = new StringTokenizer(nombresAsistentes, ", ");
			StringTokenizer appPaternos = new StringTokenizer(appPatAsistentes, ", ");
			StringTokenizer appMaternos = new StringTokenizer(appMatAsistentes, ", ");
			StringTokenizer telefonos = new StringTokenizer(telAsistentes, ", ");
			StringTokenizer correos = new StringTokenizer(correoAsistentes, ", ");
			while (nombres.hasMoreTokens()) {
				asistentes = new Asistentes();
				asistentes.setIdDiplomado(idDiplomado);
				asistentes.setNombre(nombres.nextToken());
				asistentes.setAppPaterno(appPaternos.nextToken());
				asistentes.setAppMaterno(appMaternos.nextToken());
				asistentes.setTelefono(telefonos.nextToken());
				asistentes.setCorreoElectronico(correos.nextToken());
				log.debug("asistente a insertar: " + asistentes);
				setMensaje(pyMEsService.saveAsistente(asistentes));
			}
			setListParticipantes(coordinadorDiplomadosService.getParticipantes(idDiplomado, idPyme));
			log.debug(listParticipantes);
			setIdPyme(idPyme);
		} else	if(numeroSesiones>0 && getListSesiones() != null){
			log.debug("Guardando sesiones de diplomado = " + idDiplomado + listSesiones.get(0).getIdSesion());
			setMensaje(coordinadorDiplomadosService.saveSesiones(getListSesiones(), numeroSesiones));
			setListSesiones(null);
			setListDiplomados(coordinadorDiplomadosService.getMenuDiplomados());
		} else if(listParticipantes!= null && idPyme >0){
			log.debug("Guradando datos de participantes por pyme = " + idPyme +"::::"+listParticipantes);
			setMensaje(coordinadorDiplomadosService.saveParticipantes(listParticipantes, idPyme, idDiplomado));
			setIdPyme(0);
			setListParticipantes(null);
			setListDiplomados(coordinadorDiplomadosService.getMenuDiplomados());
		}else if(idDiplomado>0 && idPyme == -1){
			log.debug("Llenando sesiones de diplomado = " + idDiplomado);
			setListSesiones(coordinadorDiplomadosService.getSesiones(idDiplomado));
			if(listSesiones==null || listSesiones.isEmpty()){
				setListSesiones(new ArrayList<Sesiones>());
			}
			setIdPyme(0);
			setIdDiplomado(idDiplomado);
		} else if(tema!=null && generacion>0 && idPyme == 0){
			log.debug("Llenanda lista General de participantes");
			setIdDiplomado((coordinadorDiplomadosService.getDiplomado(tema, generacion)).getIdDiplomado());
			if(idDiplomado>0){
				setListParticipantes(coordinadorDiplomadosService.getParticipantes(idDiplomado));
			} else {
				setListParticipantes(new ArrayList<Participantes>());
			}
		} else if(idDiplomado>0 && idPyme > 0){
			log.debug("Llenanda lista de participantes de la pyme " + idPyme);
			setListParticipantes(coordinadorDiplomadosService.getParticipantes(idDiplomado, idPyme));
			log.debug(listParticipantes);
			setIdPyme(idPyme);
		} else if(listSesiones == null){
			setIdPyme(0);
			setListDiplomados(coordinadorDiplomadosService.getMenuDiplomados());
		}		
		return SUCCESS;
	}
	@Action(value = "/coordinadorDiplomadosInasistencias", results = { @Result
			(name = "success", location = "coordinacion.diplomados.diplomados.show", type = "tiles") })
	public String coordinadorDiplomadosInasistencias()
			throws BaseBusinessException {
		log.debug("coordinadorDiplomadosInasistencias()");
		setMenu(1);
		setListInacistencias(coordinadorDiplomadosService.getInasistentes());
		return SUCCESS;
	}

	@Action(value = "/coordinadorDiplomadosEncuestasShow", results = { @Result(name = "success", location = "coordinacion.diplomados.encuestas.show", type = "tiles") })
	public String coordinadorDiplomadosEncuestasShow()
			throws BaseBusinessException {
		log.debug("coordinadorDiplomadosEncuestasShow()");
		setMenu(2);
		return SUCCESS;
	}

	@Action(value = "/coordinadorDiplomadosPyMEsShow", results = { @Result(name = "success", location = "coordinacion.diplomados.pymes.show", type = "tiles") })
	public String coordinadorDiplomadosPyMEsShow() throws BaseBusinessException {
		log.debug("coordinadorDiplomadosPyMEsShow()");
		setMenu(3);
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

	@SuppressWarnings("unchecked")
	@Action(value = "/coordinadorDiplomadosReportesShow", results = { @Result(name = "success", location = "coordinacion.diplomados.reportes.show", type = "tiles") })
	public String coordinadorDiplomadosReportesShow()
			throws BaseBusinessException {
		log.debug("coordinadorDiplomadosReportesShow()");
		setMenu(4);
		if(opcion!=null && opcion.equals("pymes")){
			setListPymes(coordinadorDiplomadosService.getPymes());
			setListTractoras(pyMEsService.getTractora());
			log.debug(listPymes);
			return SUCCESS;
		} else if (opcion != null && opcion.equals("finanzas")) {
			setOpcion("descarga");
			String direccion = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("/");
			Usuario usuario = getUsuario();
			List<FinanzasDiplomados> finanzasList = reportService.getFinanzasDiplomado();
			if (finanzasList.isEmpty()) {
				setSalida("No se encontraron resultados que coincidan con su busqueda");
				return SUCCESS;
			} else {
				setSalida(null);
				try {
					JasperDesign design = JRXmlLoader
							.load((new FileInputStream(direccion
									+ "/jasper/financieroDiplomados.jrxml")));/* "WEB-INF\\jasper\\reporte.jrxml" */
					JasperCompileManager.compileReportToFile(design, direccion
							+ "/jasper/reporte" + usuario.getIdUsuario()
							+ ".jasper");
					@SuppressWarnings({ "rawtypes" })
					Map parameters = new HashMap();
					parameters.put("SUBREPORT_DIR", direccion
							+ "/jasper/Reportes\\");
					parameters.put("participantes1",
							reportService.getParticipante1());
					parameters.put("participantes2",
							reportService.getParticipante2());
					parameters.put("participantes3",
							reportService.getParticipante3());
					parameters.put("participantes4",
							reportService.getParticipante4());
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
		} else if (opcion != null && opcion.equals("pyRepor")) {
			setOpcion("descarga");
			String direccion = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("/");
			Usuario usuario = getUsuario();
			List<PymesDiplomados> finanzasList = reportService.
					getPymesDiplomado(idUsuario,idTractora,generacion);
			if (finanzasList.isEmpty()) {
				setSalida("No se encontraron resultados que coincidan con su busqueda");
				return SUCCESS;
			} else {
				setSalida(null);
				try {
					JasperDesign design = JRXmlLoader
							.load((new FileInputStream(direccion
									+ "/jasper/pymesDiplomados.jrxml")));/* "WEB-INF\\jasper\\reporte.jrxml" */
					JasperCompileManager.compileReportToFile(design, direccion
							+ "/jasper/reporte" + usuario.getIdUsuario()
							+ ".jasper");
					@SuppressWarnings({ "rawtypes" })
					Map parameters = new HashMap();
					parameters.put("SUBREPORT_DIR", direccion
							+ "/jasper/Reportes\\");
					parameters.put("participantes1",
							reportService.getParticipante1(
									idUsuario,idTractora,generacion));
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

	public List<PyMEs> getListPyMEs() throws PyMEsNoObtenidasException {
		log.debug("getListPyMEs()");

		List<PyMEs> list = new ArrayList<PyMEs>();
		log.debug(busqueda);
		log.debug(estado);
		log.debug(cveScian);
		list = pyMEsService.getBusquedaPyME(Null.free(busqueda),
				Null.free(estado).equals("-1") ? "" : estado,
				Null.free(cveScian));
		setListPyMEs(list);
		return listPyMEs;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
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

	public RelPyMEsTractoras getRelPyMEsTractoras() {
		return relPyMEsTractoras;
	}

	public void setRelPyMEsTractoras(RelPyMEsTractoras relPyMEsTractoras) {
		this.relPyMEsTractoras = relPyMEsTractoras;
	}

	public Indicadores getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(Indicadores indicadores) {
		this.indicadores = indicadores;
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

	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getSalida() {
		return salida;
	}
	public void setSalida(String salida) {
		this.salida = salida;
	}
	public InputStream getArchivo() {
		return archivo;
	}
	public void setArchivo(InputStream archivo) {
		this.archivo = archivo;
	}
	public List<Tractoras> getListTractoras() {
		return listTractoras;
	}

	public void setListTractoras(List<Tractoras> listTractoras) {
		this.listTractoras = listTractoras;
	}
	public int getIdTractora() {
		return idTractora;
	}

	public void setIdTractora(int idTractora) {
		this.idTractora = idTractora;
	}
	public List<PyMEs> getListPymes() {
		return listPymes;
	}

	public void setListPymes(List<PyMEs> listPymes) {
		this.listPymes = listPymes;
	}
	public int getGeneracion() {
		return generacion;
	}

	public void setGeneracion(int generacion) {
		this.generacion = generacion;
	}

	public List<Diplomados> getListDiplomados() {
		return listDiplomados;
	}

	public void setListDiplomados(List<Diplomados> listDiplomados) {
		this.listDiplomados = listDiplomados;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
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

	public int getIdPyme() {
		return idPyme;
	}

	public void setIdPyme(int idPyme) {
		this.idPyme = idPyme;
	}
	public int getIdDiplomado() {
		return idDiplomado;
	}

	public void setIdDiplomado(int idDiplomado) {
		this.idDiplomado = idDiplomado;
	}
	public String getNombresAsistentes() {
		return nombresAsistentes;
	}

	public void setNombresAsistentes(String nombresAsistentes) {
		this.nombresAsistentes = nombresAsistentes;
	}

	public String getAppPatAsistentes() {
		return appPatAsistentes;
	}

	public void setAppPatAsistentes(String appPatAsistentes) {
		this.appPatAsistentes = appPatAsistentes;
	}

	public String getAppMatAsistentes() {
		return appMatAsistentes;
	}

	public void setAppMatAsistentes(String appMatAsistentes) {
		this.appMatAsistentes = appMatAsistentes;
	}

	public String getTelAsistentes() {
		return telAsistentes;
	}

	public void setTelAsistentes(String telAsistentes) {
		this.telAsistentes = telAsistentes;
	}

	public String getCorreoAsistentes() {
		return correoAsistentes;
	}

	public void setCorreoAsistentes(String correoAsistentes) {
		this.correoAsistentes = correoAsistentes;
	}


	public List<Participantes> getListInacistencias() {
		return listInacistencias;
	}

	public void setListInacistencias(List<Participantes> listInacistencias) {
		this.listInacistencias = listInacistencias;
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
}
