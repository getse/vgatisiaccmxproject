/*
 * CoordinadorDiplomadosAction.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
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

import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Encuestas;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Participantes;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Sesiones;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.service.CoordinadorDiplomadosService;
import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.service.PyMEsService;
import mx.com.vgati.ccmx.vinculacion.report.dto.FinanzasDiplomados;
import mx.com.vgati.ccmx.vinculacion.report.dto.PymesDiplomados;
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
	private List<List<Diplomados>> listDiplomados;
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
	private String nameArchivo;
	private String mimeArchivo;
	private List<Participantes> listInacistencias;
	private List<Participantes> listDiplomas;
	private Encuestas encuesta;
	private int idAsistente;
	private int year;
	private List<Integer> menuAnios;
	private ServiciosDiplomado serviciosDiplomado;
	private String idArchivos;
	private int idArchivo;
	private List<Documento> listDocumentos;
	private int menuSeleccionado;
	private String idsSolitante;
	private String numSolicitud;
	private int solicitanteFact;
	private int generaciones;
	private boolean sesion1;
	private boolean sesion2;
	private boolean sesion3;
	private boolean sesion4;
	
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

	@SuppressWarnings("unchecked")
	@Action(value = "/coordinadorDiplomadosDiplomadosShow", results = {
			@Result(name = "success", location = "coordinacion.diplomados.diplomados.show", type = "tiles") })
	public String coordinadorDiplomadosDiplomadosShow()
			throws BaseBusinessException, FileNotFoundException {
		log.debug("coordinadorDiplomadosDiplomadosShow()");
		setMenu(1);	
		if (serviciosDiplomado!=null && serviciosDiplomado.getAsistentes() != null) {
			ServiciosDiplomado sd = getServiciosDiplomado();
			sd =pyMEsService.getServicioDiplomado(idDiplomado, idPyme);
				for (Asistentes as:serviciosDiplomado.getAsistentes()) {
					if (as != null && as.getIdAsistente() == 0 && !Null.free(as.getNombre()).isEmpty()) {
						log.debug("Insertando Asistente... " + as.getNombre());
						as.setIdServiciosDiplomado(sd.getIdServiciosDiplomado());
						setMensaje(pyMEsService.saveAsistentes(as));
					} else if (as != null && as.getIdAsistente() != 0 && !Null.free(as.getNombre()).isEmpty()) {
						log.debug("Actualizando Asistente... " + as.getIdAsistente());
						setMensaje(pyMEsService.updateAsistentes(as));
					}
				}
			if(getMensaje().getRespuesta()==0){
				setMensaje(new Mensaje(0,"Los asistentes fueron almacenados correctamente."));
			} else{
				setMensaje(new Mensaje(1,"Error al guardar los asistentes, intentelo mas tarde."));
			}
			setTema(coordinadorDiplomadosService.getTema(idDiplomado));
			setListParticipantes(coordinadorDiplomadosService.getParticipantes(idDiplomado));
			setOpcion(null);
		} else if(opcion!=null && opcion.equals("selecPyme")){
			//Seleccion de pyme
			setTema(coordinadorDiplomadosService.getTema(idDiplomado));
			setListPymes(coordinadorDiplomadosService.getPymes(idDiplomado));
			setOpcion("InPyme");
		} else if(opcion!=null && opcion.equals("selecPyme2")){
			//Seleccion de pyme
			setTema(coordinadorDiplomadosService.getTema(idDiplomado));
			setListPymes(coordinadorDiplomadosService.getPymes(idDiplomado));
			setOpcion("InPyme2");
		} else if(opcion!=null && opcion.equals("SesionesAdmin")){
			//Administrar sesiones
			setTema(coordinadorDiplomadosService.getTema(idDiplomado));
			setListParticipantes(coordinadorDiplomadosService.getParticipantes(idDiplomado));
			setOpcion("SesionesAdmin");
			setSalida("No se ha generado archivo");
		} else if(opcion!=null && opcion.equals("InPyme")){
			//Pagos y facturas
			setOpcion("Pagos");
			setTema(coordinadorDiplomadosService.getTema(idDiplomado));
			setServiciosDiplomado(pyMEsService.getServicioDiplomado(idDiplomado, idPyme));
			ServiciosDiplomado sd= new ServiciosDiplomado();
			sd.setAsistentes(pyMEsService.getAsistentes(serviciosDiplomado.getIdServiciosDiplomado()));
			if(serviciosDiplomado != null){
				setListDocumentos(pyMEsService.getArchivosDiplomado(serviciosDiplomado.getIdServiciosDiplomado()));
			}
			setServiciosDiplomado(sd);
			setListParticipantes(coordinadorDiplomadosService.getParticipantes(idDiplomado, idPyme));
			setIdPyme(idPyme);
		} else if(opcion!=null && opcion.equals("InPyme2")){
			//Participantes
			setTema(coordinadorDiplomadosService.getTema(idDiplomado));
			setServiciosDiplomado(pyMEsService.getServicioDiplomado(idDiplomado, idPyme));
			ServiciosDiplomado sd= new ServiciosDiplomado();
			sd.setAsistentes(pyMEsService.getAsistentes(serviciosDiplomado.getIdServiciosDiplomado()));
			if(serviciosDiplomado != null){
				setListDocumentos(pyMEsService.getArchivosDiplomado(serviciosDiplomado.getIdServiciosDiplomado()));
			}
			setOpcion("Participantes");
			setServiciosDiplomado(sd);
			setListParticipantes(coordinadorDiplomadosService.getParticipantes(idDiplomado, idPyme));
			setIdPyme(idPyme);
		} else if(opcion!=null && opcion.equals("AdminSesiones")){
			//Pagina de administrar sesiones
			setSalida("No se ha generado archivo");
			if(menuSeleccionado==1){
				//Modificaciones de asistencias y conirmacion
				setMensaje(coordinadorDiplomadosService.saveAsistencias(listParticipantes));
			} else if(menuSeleccionado==2){
				//Generar diploma
				log.debug("Agregando a lista de diplomas");
				boolean todosParticpantes=true;
				if(listParticipantes!=null){
					for(int i =0 ; i<listParticipantes.size();i++){
						if(listParticipantes.get(i).isSeleccion()){
							todosParticpantes=false;
							break;
						}
					}
					listDiplomas = new ArrayList<Participantes>();
					if(todosParticpantes){
						for(int i =0 ; i<listParticipantes.size();i++){
							if(listParticipantes.get(i).isResagado()){
								setMensaje(new Mensaje(1,"Algunos participantes fueron omitidos debido a que estan rezagados."));
							}else{
								listDiplomas.add(listParticipantes.get(i));
							}
						}
					} else {
						for(int i =0 ; i<listParticipantes.size();i++){
							if(listParticipantes.get(i).isSeleccion()){
								if(listParticipantes.get(i).isResagado()){
									setMensaje(new Mensaje(1,"Algunos participantes fueron omitidos debido a que estan rezagados."));
								} else{
									listDiplomas.add(listParticipantes.get(i));
								}
							}
						}
					}
				}
				log.debug(listDiplomas);
				setOpcion(null);
				setListParticipantes(null);
				return SUCCESS;
			} else if(menuSeleccionado==3){
				//Enviar invitación
				boolean todosParticpantes=true;
				if(listParticipantes!=null){
					for(int i =0 ; i<listParticipantes.size();i++){
						if(listParticipantes.get(i).isSeleccion()){
							todosParticpantes=false;
							break;
						}
					}
					if(todosParticpantes){
						for(int i = 0 ; i < listParticipantes.size() ; i++){
							Participantes part = listParticipantes.get(i);
							listSesiones = coordinadorDiplomadosService.getSesion(part.getIdSesion1());
							if(listSesiones!=null && listSesiones.size()>0){
							Sesiones s = listSesiones.get(0);
									 log.debug("Enviando correo electrónico:"+part.getCorreoElectronico());
									 String direccion=" No dada de alta.";
									 if(s.getDomicilios()!=null){
										 direccion= "Núm." + s.getDomicilios().getNumExt();
										 if(s.getDomicilios().getNumInt()!=null){
											 direccion = direccion + " Interior "+s.getDomicilios().getNumInt();
										 }if(s.getDomicilios().getPiso()!=null){
											 direccion= direccion + " Piso "+ s.getDomicilios().getPiso();
										 }
										 direccion = direccion + " Colonia "+ s.getDomicilios().getColonia();
										 direccion = direccion + " Delegacion o Municipio "+ s.getDomicilios().getDelegacion();
										 direccion = direccion + " Estado "+ s.getDomicilios().getEstado();
										 direccion = direccion + " Codigo postal "+ s.getDomicilios().getCodigoPostal();
									 }
									 String entrada = "";
									 if(s.getMinuto()<=15){
										 int min = 60-(15-s.getMinuto());
										 int hora = s.getHora()-1;
										 if(s.getHora()==0){
											 hora=23;
										 }
										 entrada = hora + ":" + min;
									 }
									 else{
										 entrada=  s.getHora() + ":" + s.getMinuto();
									 }
									 String horarios=s.getHora() + ":" + s.getMinuto() + " hrs. a "+s.getHoraFin() + ":" + s.getMinutoFin()+" hrs.";
										SendEmail envia = new SendEmail(
												Null.free(part.getCorreoElectronico()),
												"SIA CCMX Invitación a nueva sesión.",
												"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>"
														.concat("Estimado(a) Empresario (a):</h5> ")
														.concat("<br /><br />Nos permitimos recordarle de su próxima sesión al Diplomado de '")
														.concat(Null.free(part.getTema()))
														.concat("' , y le damos la más cordial bienvenida a esta  ")
														.concat(Null.free(""+part.getSesion()))
														.concat(" la cual será impartida en las instalaciones de la empresa ")
														.concat(s.getExpositor())
														.concat(".<br /><br />Es un gusto poder contar con su presencia en este evento, estamos seguros que podrá adquirir excelentes ")
														.concat(" prácticas para implementación en beneficio de su empresa.<br />")
														.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Fecha de inicio: </h5>")
														.concat(Null.free(s.getFecha()+""))
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
														.concat(Null.free(s.getHora()+":"))
														.concat(Null.free(s.getMinuto()+""))
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
										log.debug("Enviando correo electrónico a:" + envia);
							}
							else {
								setMensaje(new Mensaje(0,"Por el momento no es posible enviar invitacioes."));
							}
						}
					} else {
						for(int i = 0 ; i < listParticipantes.size() ; i++){
							Participantes part = listParticipantes.get(i);
							if(part.isSeleccion()){
								listSesiones = coordinadorDiplomadosService.getSesion(part.getIdSesion1());
								if(listSesiones!=null && listSesiones.size()>0){
									Sesiones s = listSesiones.get(0);
										 log.debug("Enviando correo electrónico:"+part.getCorreoElectronico());
										 String direccion=" No dada de alta.";
										 if(s.getDomicilios()!=null){
											 direccion= "Núm." + s.getDomicilios().getNumExt();
											 if(s.getDomicilios().getNumInt()!=null){
												 direccion = direccion + " Interior "+s.getDomicilios().getNumInt();
											 }if(s.getDomicilios().getPiso()!=null){
												 direccion= direccion + " Piso "+ s.getDomicilios().getPiso();
											 }
											 direccion = direccion + " Colonia "+ s.getDomicilios().getColonia();
											 direccion = direccion + " Delegacion o Municipio "+ s.getDomicilios().getDelegacion();
											 direccion = direccion + " Estado "+ s.getDomicilios().getEstado();
											 direccion = direccion + " Codigo postal "+ s.getDomicilios().getCodigoPostal();
										 }
										 String entrada = "";
										 if(s.getMinuto()<=15){
											 int min = 60-(15-s.getMinuto());
											 int hora = s.getHora()-1;
											 if(s.getHora()==0){
												 hora=23;
											 }
											 entrada = hora + ":" + min;
										 }
										 else{
											 entrada=  s.getHora() + ":" + s.getMinuto();
										 }
										 String horarios=s.getHora() + ":" + s.getMinuto() + " hrs. a "+s.getHoraFin() + ":" + s.getMinutoFin()+" hrs.";
											SendEmail envia = new SendEmail(
													Null.free(part.getCorreoElectronico()),
													"SIA CCMX Invitación a nueva sesión.",
													"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>"
															.concat("Estimado(a) Empresario (a):</h5> ")
															.concat("<br /><br />Nos permitimos recordarle de su próxima sesión al Diplomado de '")
															.concat(Null.free(part.getTema()))
															.concat("' , y le damos la más cordial bienvenida a esta  ")
															.concat(Null.free(""+part.getSesion()))
															.concat(" la cual será impartida en las instalaciones de la empresa ")
															.concat(s.getExpositor())
															.concat(".<br /><br />Es un gusto poder contar con su presencia en este evento, estamos seguros que podrá adquirir excelentes ")
															.concat(" prácticas para implementación en beneficio de su empresa.<br />")
															.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Fecha de inicio: </h5>")
															.concat(Null.free(s.getFecha()+""))
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
															.concat(Null.free(s.getHora()+":"))
															.concat(Null.free(s.getMinuto()+""))
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
											log.debug("Enviando correo electrónico a:" + envia);
											setMensaje(new Mensaje(0,"Invitaciones enviadas correctamente"));
								}
								else {
									setMensaje(new Mensaje(0,"Por el momento no es posible enviar invitacioes."));
								}
							}
						}
					}
				} 
			} else {
				List<Participantes> parts=null;
				if(sesion1||sesion2||sesion3||sesion4){
					parts = coordinadorDiplomadosService.getParticipantesPorSesion(idDiplomado, sesion1,sesion2,sesion3,sesion4);
				}
				if(parts!=null && parts.size()>0){
					Usuario usuario = getUsuario();
					String direccion = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("/");
					try {
						JasperDesign design = JRXmlLoader
								.load((new FileInputStream(direccion
										+ "/jasper/participantes.jrxml")));/* "WEB-INF\\jasper\\reporte.jrxml" */
						JasperCompileManager.compileReportToFile(design, direccion
								+ "/jasper/participantes" + usuario.getIdUsuario()
								+ ".jasper");
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
						setSalida("No");
					}
					if(salida.equals("No")){
						setSalida("No se pudo generar el arhivo, intentelo mas tarde.");
					}else{
						setSalida(null);
					}
				}
			}
			setListSesiones(null);
			setTema(coordinadorDiplomadosService.getTema(idDiplomado));
			setListParticipantes(coordinadorDiplomadosService.getParticipantes(idDiplomado));
			setOpcion("SesionesAdmin");
			
		} else if(opcion!=null && opcion.equals("PAgosFacturasa")){
			//Pantalla de paagos y facturas
			if(menuSeleccionado==1){
				//Solicitar factura
				boolean isPorPyme=true;
				if(listParticipantes!=null){
					for(int i =0 ; i<listParticipantes.size();i++){
						if(listParticipantes.get(i).isSeleccion()){
							isPorPyme=false;
							break;
						}
					}
				}
				setMensaje(coordinadorDiplomadosService.saveFacturas(listParticipantes));
				if(getMensaje().getRespuesta()==0){
					PyMEs py = coordinadorDiplomadosService.getPyme(idPyme);
					Domicilios dom = pyMEsService.getDomicilio(Integer.parseInt(pyMEsService.getIdDomicilio(idPyme)));
					if(dom==null){
						dom = new Domicilios();
					}
					if(isPorPyme){
						String text = "<table border=1 cellspacing=0 cellpadding=2 bordercolor='666633'>" +
						"<tr><td>Factura</td><td>Razon social</td><td>Domicilio fiscal</td>" +
						"<td>Participante</td><td>RFC</td><td>Forma de pago</td>" +
						"<td>No.CTA(4 Ilt. Dig.)</td><td>Partc a Facturar</td></tr>" +
						" <tr><td>" + Null.free("")+
						"</td><td>" + Null.free(py.getNombreComercial())+
						"</td><td>" + "Calle: "+Null.free(dom.getCalle()) +""+
						"Número: "+Null.free(dom.getNumExt()) +
						" Interior: "+Null.free(dom.getNumInt())+"<br/>" +
						"Colonia: " + Null.free(dom.getColonia())+" " +
						"Delegación/Municipio: "+Null.free(dom.getDelegacion())+" "+
						"Estado: "+Null.free(dom.getEstado())+ "<br/>C.P."+ Null.free(dom.getCodigoPostal())+
						"</td><td>" + Null.free("")+
						"</td><td>" + Null.free(py.getRfc())+
						"</td><td>" +"Transferencia"+
						"</td><td>" +"0"+
						"</td><td>1</td></tr>";
						SendEmail envia = new SendEmail(
								"nayla.martinez@caintra.org.mx",//TODO Cambiar correo para pruebas
								"SIA CCMX Solicitud de factura",
								text
								,null);
						log.debug("Enviando correo electrónico a:" + envia);
					}else{
						String participantes="";
						String text = "<table border=1 cellspacing=0 cellpadding=2 bordercolor='666633'>";
						text = text +
						"<tr><td>Factura</td><td>Razon social</td><td>Domicilio fiscal</td>" +
						"<td>Participante</td><td>RFC</td><td>Forma de pago</td>" +
						"<td>No.CTA(4 Ult. Dig.)</td><td>Partc a Facturar</td></tr>" ;
						for(int i =0 ; i<listParticipantes.size();i++){
							Participantes p = listParticipantes.get(i);
							if(p.isSeleccion()){
								Participantes par = coordinadorDiplomadosService.getParticipante(p.getId());
								participantes=participantes+"Nombre: "+Null.free(par.getNombre())+"<br/>";
								text = text+
								" <tr><td>" + Null.free(p.getNumPago())+
								"</td><td>" + Null.free(py.getNombreComercial())+
								"</td><td>" + "Calle: "+Null.free(dom.getCalle()) +""+
								"Número: "+Null.free(dom.getNumExt()) +
								" Interior: "+Null.free(dom.getNumInt())+"<br/>" +
								"Colonia: " + Null.free(dom.getColonia())+" " +
								"Delegación/Municipio: "+Null.free(dom.getDelegacion())+" "+
								"Estado: "+Null.free(dom.getEstado())+ "<br/>C.P."+ Null.free(dom.getCodigoPostal())+
								"</td><td>" + Null.free(par.getNombre())+
								"</td><td>" + Null.free(py.getRfc())+
								"</td><td>" +"Transferecia"+
								"</td><td>" +"0"+
								"</td><td>1</td></tr>";
							}
						}
						text = text + "</table>";
						SendEmail envia = new SendEmail(
								"nayla.martinez@caintra.org.mx",//TODO  Cambiar correo para pruebas nayla.martinez@caintra.org.mx
								"SIA CCMX Solicitud de factura",
								text
								,null);
						log.debug("Enviando correo electrónico a:" + envia);
					}
				}
			}else {
				//Guardar datos de pago y arcchivos
				ServiciosDiplomado sd = pyMEsService.getServicioDiplomado(idDiplomado, idPyme);
				Documento d = null;
				if (serviciosDiplomado!= null && serviciosDiplomado.getArchivos() != null) {
					log.debug(serviciosDiplomado.getArchivos().getUpload().size());
					for (int i = 0; i < serviciosDiplomado.getArchivos().getUpload().size(); i++) {
						log.debug("Insertando Archivo... " + i);
						d = new Documento();
						d.setIs(new FileInputStream(serviciosDiplomado.getArchivos().getUpload().get(i)));
						d.setIdServiciosDiplomado(sd.getIdServiciosDiplomado());
						d.setNombre(serviciosDiplomado.getArchivos().getUploadFileName().get(i));
						d.setDescripcionArchivo(serviciosDiplomado.getArchivos().getDescripcionArchivos().get(i));
						setMensaje(pyMEsService.saveArchivoServicio(d));
					}
				}
				if(idArchivos != null && !idArchivos.trim().equals("")){
					log.debug("Eliminando archivos..." + idArchivos);
					setMensaje(pyMEsService.deleteArchivoPago(idArchivos));
				}
				setMensaje(coordinadorDiplomadosService.savePagos(listParticipantes));
			}
			setOpcion("Pagos");
			setTema(coordinadorDiplomadosService.getTema(idDiplomado));
			setServiciosDiplomado(pyMEsService.getServicioDiplomado(idDiplomado, idPyme));
			ServiciosDiplomado sd= new ServiciosDiplomado();
			sd.setAsistentes(pyMEsService.getAsistentes(serviciosDiplomado.getIdServiciosDiplomado()));
			if(serviciosDiplomado != null){
				setListDocumentos(pyMEsService.getArchivosDiplomado(serviciosDiplomado.getIdServiciosDiplomado()));
			}
			setServiciosDiplomado(sd);
			setListParticipantes(coordinadorDiplomadosService.getParticipantes(idDiplomado, idPyme));
			setIdPyme(idPyme);
		} else if(numeroSesiones>0 && getListSesiones() != null){
			log.debug("Guardando sesiones de diplomado = " + idDiplomado + listSesiones.get(0).getIdSesion());
			setMensaje(coordinadorDiplomadosService.saveSesiones(getListSesiones(), numeroSesiones));
			setListSesiones(null);
			setMenuAnios(coordinadorDiplomadosService.getMenuAnios());
			setGeneraciones(coordinadorDiplomadosService.getGeneraciones(year));
			setListDiplomados(coordinadorDiplomadosService.getMenuDiplomados(0, generaciones));
		} else if(idDiplomado>0 && idPyme == -1){
			log.debug("Llenando sesiones de diplomado = " + idDiplomado);
			setListSesiones(coordinadorDiplomadosService.getSesiones(idDiplomado));
			if(listSesiones==null || listSesiones.isEmpty()){
				setListSesiones(new ArrayList<Sesiones>());
			}
			setIdPyme(0);
			setIdDiplomado(idDiplomado);
		} else if(idDiplomado > 0 ){
			log.debug("Llenanda lista General de participantes");
			setTema(coordinadorDiplomadosService.getTema(idDiplomado));
			setListParticipantes(coordinadorDiplomadosService.getParticipantes(idDiplomado));
		} else {
			setIdPyme(0);
			setMenuAnios(coordinadorDiplomadosService.getMenuAnios());
			setGeneraciones(coordinadorDiplomadosService.getGeneraciones(year));
			setListDiplomados(coordinadorDiplomadosService.getMenuDiplomados(year, generaciones));
			if(listDiplomados!=null && listDiplomados.size()>0){
				List<Diplomados> dip = listDiplomados.get(0);
				if(dip!=null && dip.size()>0){
					setYear(dip.get(0).getYear());
				}
			}
		}
		return SUCCESS;
	}
	@Action(value = "/coordinadorDiplomadosInasistencias", results = { @Result
			(name = "success", location = "coordinacion.diplomados.diplomados.show", type = "tiles") })
	public String coordinadorDiplomadosInasistencias()
			throws BaseBusinessException {
		log.debug("coordinadorDiplomadosInasistencias()");
		setMenu(1);
		if(listInacistencias!=null){
			log.debug(listInacistencias);
			listSesiones = coordinadorDiplomadosService.getSesiones(idDiplomado);
			if(listSesiones != null){
				for(int i = 0 ; i<listInacistencias.size();i++){
					Participantes part = listInacistencias.get(i);
					 if(part.isInvitacion()){							 
						 for(int j =0 ;j<listSesiones.size();j++){
							 Sesiones s = listSesiones.get(j);
							 if(s.getSesion() == part.getSesion()){
								 setMensaje(coordinadorDiplomadosService.saveInasistententes(part,idDiplomado));
								 if(mensaje!=null && mensaje.getRespuesta()==0){
									 log.debug("Enviando correo electrónico:"+part.getCorreoElectronico());
									 String direccion=" No dada de alta.";
									 if(s.getDomicilios()!=null){
										 direccion= "Núm." + s.getDomicilios().getNumExt();
										 if(s.getDomicilios().getNumInt()!=null){
											 direccion = direccion + " Interior "+s.getDomicilios().getNumInt();
										 }if(s.getDomicilios().getPiso()!=null){
											 direccion= direccion + " Piso "+ s.getDomicilios().getPiso();
										 }
										 direccion = direccion + " Colonia "+ s.getDomicilios().getColonia();
										 direccion = direccion + " Delegacion o Municipio "+ s.getDomicilios().getDelegacion();
										 direccion = direccion + " Estado "+ s.getDomicilios().getEstado();
										 direccion = direccion + " Codigo postal "+ s.getDomicilios().getCodigoPostal();
									 }
									 String entrada = "";
									 if(s.getMinuto()<=15){
										 int min = 60-(15-s.getMinuto());
										 int hora = s.getHora()-1;
										 if(s.getHora()==0){
											 hora=23;
										 }
										 entrada = hora + ":" + min;
									 }
									 else{
										 entrada=  s.getHora() + ":" + s.getMinuto();
									 }
									 String horarios=s.getHora() + ":" + s.getMinuto() + " hrs. a "+s.getHoraFin() + ":" + s.getMinutoFin()+" hrs.";
										SendEmail envia = new SendEmail(
												Null.free(part.getCorreoElectronico()),
												"SIA CCMX Invitación a nueva sesión.",
												"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>"
														.concat("Estimado(a) Empresario (a):</h5> ")
														.concat("<br /><br />Nos permitimos recordarle de su próxima sesión al Diplomado de '")
														.concat(Null.free(part.getTema()))
														.concat("' , y le damos la más cordial bienvenida a esta  ")
														.concat(Null.free(""+part.getSesion()))
														.concat(" la cual será impartida en las instalaciones de la empresa ")
														.concat(s.getExpositor())
														.concat(".<br /><br />Es un gusto poder contar con su presencia en este evento, estamos seguros que podrá adquirir excelentes ")
														.concat(" prácticas para implementación en beneficio de su empresa.<br />")
														.concat("<br /><h5 style='font-family: Verdana; font-size: 12px; color: #336699;'>•Fecha de inicio: </h5>")
														.concat(Null.free(s.getFecha()+""))
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
														.concat(Null.free(s.getHora()+":"))
														.concat(Null.free(s.getMinuto()+""))
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
										log.debug("Enviando correo electrónico a:" + envia);
									 break;
								 }
							 }
						 }
					 }
				}
			}
			setListSesiones(null);
		}
		setIdPyme(idPyme);
		setIdDiplomado(idDiplomado);
		setListInacistencias(coordinadorDiplomadosService.getInasistentes(idDiplomado));		
		return SUCCESS;
	}
	@Action(value = "/coordinadorDiplomadosEncuestasShow", results = { @Result(name = "success", location = "coordinacion.diplomados.encuestas.show", type = "tiles") })
	public String coordinadorDiplomadosEncuestasShow()
			throws BaseBusinessException {
		log.debug("coordinadorDiplomadosEncuestasShow()");
		setMenu(2);
		if(getEncuesta()!=null){
			log.debug("Guardando Encuesta de idAsistente = " + idAsistente);
			setMensaje(coordinadorDiplomadosService.saveEncuestas(encuesta));
			setEncuesta(null);
			setIdPyme(0);
			setMenuAnios(coordinadorDiplomadosService.getMenuAnios());
			setGeneraciones(coordinadorDiplomadosService.getGeneraciones(year));
			setListDiplomados(coordinadorDiplomadosService.getMenuDiplomados(0, generaciones));
		} else if(numeroSesiones>0){
			log.debug(numeroSesiones+"-"+idAsistente);
			setEncuesta(coordinadorDiplomadosService.getEncuestas(idAsistente,numeroSesiones));
			if(getEncuesta()==null){
				log.debug("vacio");
				Encuestas temp = new Encuestas();
				temp.setIdAsistente(idAsistente);
				temp.setIdSesion(numeroSesiones);
				setEncuesta(temp);
			}
		}	else if(idAsistente>0 && idDiplomado>0){
			setListSesiones(coordinadorDiplomadosService.getSesiones(idDiplomado));
			if(listSesiones==null || listSesiones.isEmpty()){
				setListSesiones(new ArrayList<Sesiones>());
			}
		} else if(idDiplomado>0){
			log.debug("Llenanda lista General de participantes");
			setIdDiplomado(idDiplomado);
			if(idDiplomado>0){
				setListParticipantes(coordinadorDiplomadosService.getParticipantes(idDiplomado));
			} else {
				setListParticipantes(new ArrayList<Participantes>());
			}
		} else if(year > 0){
			log.debug("Inicializando en el year " + year);
			setIdPyme(0);
			setMenuAnios(coordinadorDiplomadosService.getMenuAnios());
			setGeneraciones(coordinadorDiplomadosService.getGeneraciones(year));
			setListDiplomados(coordinadorDiplomadosService.getMenuDiplomados(year, generaciones));
		} else {
			setIdPyme(0);
			setMenuAnios(coordinadorDiplomadosService.getMenuAnios());
			setGeneraciones(coordinadorDiplomadosService.getGeneraciones(year));
			setListDiplomados(coordinadorDiplomadosService.getMenuDiplomados(0, generaciones));
		}		
		return SUCCESS;
	}
	@Action(value = "/coordinadorDiplomadosDiplomaShow", results = { @Result(name = "success", location = "coordinacion.diplomados.diplomados.show", type = "tiles") })
	public String coordinadorDiplomadosDiplomaShow() throws BaseBusinessException{
		log.debug("coordinadorDiplomadosDiplomasShow()");
		setMenu(1);
		if(tema!=null){
			setTema(tema);
			setNombresAsistentes(nombresAsistentes);
		}
		return SUCCESS;
	}
	@Action(value = "/coordinadorDiplomadosDiplomasShow", results = { @Result(name = "success", location = "coordinacion.diplomados.diplomados.show", type = "tiles") })
	public String coordinadorDiplomadosDiplomasShow() throws BaseBusinessException{
		log.debug("coordinadorDiplomadosDiplomasShow()");
		setMenu(1);
		setListDiplomas(coordinadorDiplomadosService.getParticipantesDiploma(idDiplomado, idPyme));
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


	public List<List<Diplomados>> getListDiplomados() {
		return listDiplomados;
	}

	public void setListDiplomados(List<List<Diplomados>> listDiplomados) {
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

	public Encuestas getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(Encuestas encuesta) {
		this.encuesta = encuesta;
	}

	public int getIdAsistente() {
		return idAsistente;
	}

	public void setIdAsistente(int idAsistente) {
		this.idAsistente = idAsistente;
	}

	public List<Integer> getMenuAnios() {
		return menuAnios;
	}

	public void setMenuAnios(List<Integer> menuAnios) {
		this.menuAnios = menuAnios;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public ServiciosDiplomado getServiciosDiplomado() {
		return serviciosDiplomado;
	}

	public void setServiciosDiplomado(ServiciosDiplomado serviciosDiplomado) {
		this.serviciosDiplomado = serviciosDiplomado;
	}

	public List<Participantes> getListDiplomas() {
		return listDiplomas;
	}

	public void setListDiplomas(List<Participantes> listDiplomas) {
		this.listDiplomas = listDiplomas;
	}

	public String getIdArchivos() {
		return idArchivos;
	}

	public void setIdArchivos(String idArchivos) {
		this.idArchivos = idArchivos;
	}

	public List<Documento> getListDocumentos() {
		return listDocumentos;
	}

	public void setListDocumentos(List<Documento> listDocumentos) {
		this.listDocumentos = listDocumentos;
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

	public int getMenuSeleccionado() {
		return menuSeleccionado;
	}

	public void setMenuSeleccionado(int menuSeleccionado) {
		this.menuSeleccionado = menuSeleccionado;
	}


	public String getIdsSolitante() {
		return idsSolitante;
	}

	public void setIdsSolitante(String idsSolitante) {
		this.idsSolitante = idsSolitante;
	}


	public int getSolicitanteFact() {
		return solicitanteFact;
	}

	public void setSolicitanteFact(int solicitanteFact) {
		this.solicitanteFact = solicitanteFact;
	}

	public String getNumSolicitud() {
		return numSolicitud;
	}

	public void setNumSolicitud(String numSolicitud) {
		this.numSolicitud = numSolicitud;
	}

	public int getGeneraciones() {
		return generaciones;
	}

	public void setGeneraciones(int generaciones) {
		this.generaciones = generaciones;
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
	@Action(value = "/downDocs", results = {
			@Result(name = "success", type = "stream", params = { "inputName",
					"archivo", "contentType", "mimeArchivo",
					"contentDisposition",
					"attachment;filename=\"Participantes.xlsx\"" }),
			@Result(name = "input", location = "reportes.general.reportes.list", type = "tiles"),
			@Result(name = "error", location = "reportes.general.reportes.list", type = "tiles") })
	public String downDocs() throws BaseBusinessException {
		log.debug("downDoc()");
		Usuario usuario = getUsuario();
		String direccion = ServletActionContext.getRequest().getSession()
				.getServletContext().getRealPath("/");
		File file = new File(direccion + "/jasper/participantes"
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

}
