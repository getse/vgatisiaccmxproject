/*
 * PyMEsAction.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.exception.ConsultoriasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Sesiones;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Certificaciones;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Clientes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Productos;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.IndicadoresNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.service.PyMEsService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.CatScianCcmx;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.RelPyMEsTractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.ProductosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.service.TractorasService;
import mx.com.vgati.framework.action.AbstractBaseAction;
import mx.com.vgati.framework.dto.Documento;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;
import mx.com.vgati.framework.dto.Respuesta;
import mx.com.vgati.framework.exception.BaseBusinessException;
import mx.com.vgati.framework.util.Null;
import mx.com.vgati.framework.util.SendEmail;

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
@Namespaces({ @Namespace(value = "pymes") })
public class PyMEsAction extends AbstractBaseAction {

	private int menu = 1;
	private static final String[] op = { "MI INFORMACI&Oacute;N",
			"REQUERIMIENTOS", "SERVICIOS CCMX", "B&Uacute;SQUEDA DE PyMEs" };
	private static final String[] fr = { "pymeInformacionShow.do",
			"pymeRequerimientosShow.do", "pymeServiciosShow.do",
			"pymeBusquedaShow.do" };

	private PyMEsService pyMEsService;
	private TractorasService tractorasService;
	private PyMEs pyMEs;
	private EstadosVenta estadosVentas;
	private List<PyMEs> listPyMEs;
	private Domicilios domicilios;
	private Clientes clientes;
	private Indicadores indicadores;
	private Certificaciones certificaciones;
	private Mensaje mensaje;
	private String busqueda;
	private String estado;
	private String cveScian;
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
	private String tractoraReq;
	private int idRequerimiento;
	private int idDiplomado;
	private int idUsuario;
	private java.sql.Date fechaDesde;
	private java.sql.Date fechaHasta;
	private ServiciosDiplomado serviciosDiplomado;
	private ServiciosConsultoria serviciosConsultoria;
	private Asistentes asistentes;
	private Requerimientos requerimientos;
	private List<Requerimientos> listRequerimientos;
	private List<Requerimientos> listFechas;
	private List<Tractoras> listTractoras;
	private List<Diplomados> listDiplomados;
	private String tituloDiplomado;
	private String fechaDip;
	private String pago;
	private Respuesta respuesta;
	private Productos productos;
	private String prodPrincipales;
	private int idArchivo;
	private String idArchivos;
	private String nameArchivo;
	private String mimeArchivo;
	private InputStream archivo;
	private Tractoras tractoras;
	private RelPyMEsTractoras relPymesTractoras;
	private int generaciones;
	private int generacion;
	private String ubicacion;
	private Diplomados diplomados;
	private List<Sesiones> listSesiones;
	private List<Documento> listDocumentos;
	private Documento documentoRfc;

	public void setPyMEsService(PyMEsService pyMEsService) {
		this.pyMEsService = pyMEsService;
	}

	public void setTractorasService(TractorasService tractorasService) {
		this.tractorasService = tractorasService;
	}

	@Action(value = "/pymeInformacionShow", results = {
			@Result(name = "success", location = "pyme.datos.show", type = "tiles"),
			@Result(name = "input", location = "pyme.datos.show", type = "tiles"),
			@Result(name = "error", location = "pyme.datos.show", type = "tiles") })
	public String pymeInformacionShow() throws BaseBusinessException {
		log.debug("pymeInformacionShow()");
		setMenu(1);

		Usuario u = getUsuario();
		log.debug("Usuario=" + u);
		setPyMEs(pyMEsService.getPyME(u.getIdUsuario()));

		String idDom = pyMEsService.getIdDomicilio(u.getIdUsuario());
		log.debug("idDomicilio=" + idDom);
		setDomicilios(pyMEsService.getDomicilio(Integer.parseInt(idDom)));

		setEstadosVentas(pyMEsService.getEstadoVenta(u.getIdUsuario()));

		String idInd = pyMEsService.getIdIndicador(u.getIdUsuario());
		log.debug("idIndicador=" + idInd);
		setIndicadores(pyMEsService.getIndicador(Integer.parseInt(idInd)));

		return SUCCESS;
	}

	@Action(value = "/pymeInformacionSave", results = {
			@Result(name = "success", location = "pyme.datos.show", type = "tiles"),
			@Result(name = "input", location = "pyme.datos.show", type = "tiles"),
			@Result(name = "error", location = "pyme.datos.show", type = "tiles") })
	public String pymeInformacionSave() throws BaseBusinessException {
		log.debug("pymeInformacionSave()");
		setMenu(1);

		if (pyMEs != null) {
			log.debug("Actualizando los datos de la PyME" + pyMEs);
			pyMEs.setIdUsuario(getUsuario().getIdUsuario());
			setMensaje(pyMEsService.updatePyME(pyMEs, estadosVentas));
		}

		if (mensaje.getRespuesta() == 0) {
			log.debug("DENTRO DEL MENSAJE MENSAJE ===" + mensaje);
			if (domicilios != null && domicilios.getIdDomicilio() == 0) {
				log.debug("Insertando el domicilio" + domicilios);
				setMensaje(pyMEsService.saveDomicilio(domicilios));
				log.debug("Insertando id's");
				log.debug("mensaje=" + mensaje);
				domicilios.setIdDomicilio(Integer
						.parseInt(mensaje != null ? mensaje.getId() : "0"));
				pyMEs.setIdUsuario(getUsuario().getIdUsuario());
				setMensaje(pyMEsService.saveRelDomicilio(domicilios, pyMEs));
			} else if (domicilios != null) {
				log.debug("Actualizando el domicilio" + domicilios);
				setMensaje(pyMEsService.updateDomicilio(domicilios));
			}

			if (indicadores != null && indicadores.getIdIndicador() == 0) {
				log.debug("Insertando el indicador" + indicadores);
				indicadores.setIdPyME(getUsuario().getIdUsuario());
				setMensaje(pyMEsService.saveIndicador(indicadores));
			} else if (indicadores != null && indicadores.getIdIndicador() != 0) {
				log.debug("Actualizando Indicadores" + indicadores);
				setMensaje(pyMEsService.updateIndicador(indicadores));
			}

			log.debug("Sección de refresh");

			Usuario u = getUsuario();
			log.debug("Usuario=" + u);
			setPyMEs(pyMEsService.getPyME(u.getIdUsuario()));

			String idDom = pyMEsService.getIdDomicilio(u.getIdUsuario());
			log.debug("idDomicilio=" + idDom);
			setDomicilios(pyMEsService.getDomicilio(Integer.parseInt(idDom)));

			setEstadosVentas(pyMEsService.getEstadoVenta(u.getIdUsuario()));

			String idInd = pyMEsService.getIdIndicador(u.getIdUsuario());
			log.debug("idIndicador=" + idInd);
			setIndicadores(pyMEsService.getIndicador(Integer.parseInt(idInd)));

			log.debug("Termina Sección de refresh");
		} else {
			log.debug("Sección de refresh");

			Usuario u = getUsuario();
			log.debug("Usuario=" + u);
			setPyMEs(pyMEsService.getPyME(u.getIdUsuario()));

			String idDom = pyMEsService.getIdDomicilio(u.getIdUsuario());
			log.debug("idDomicilio=" + idDom);
			setDomicilios(pyMEsService.getDomicilio(Integer.parseInt(idDom)));

			setEstadosVentas(pyMEsService.getEstadoVenta(u.getIdUsuario()));

			String idInd = pyMEsService.getIdIndicador(u.getIdUsuario());
			log.debug("idIndicador=" + idInd);
			setIndicadores(pyMEsService.getIndicador(Integer.parseInt(idInd)));

			log.debug("Termina Sección de refresh");
		}

		return SUCCESS;
	}

	@Action(value = "/pymeRequerimientosShow", results = {
			@Result(name = "success", location = "pyme.requerimientos.list", type = "tiles"),
			@Result(name = "input", location = "pyme.requerimientos.list", type = "tiles"),
			@Result(name = "error", location = "pyme.requerimientos.list", type = "tiles") })
	public String pymeRequerimientosShow() throws BaseBusinessException {
		log.debug("pymeRequerimientosShow()");
		setMenu(2);

		log.debug("tractoraReq: " + tractoraReq);
		Tractoras tractora = null;
		Requerimientos requerimiento = null;
		if (idRequerimiento != 0) {
			log.debug("Aqui está el ID=" + idRequerimiento);
			requerimiento = tractorasService.getRequerimiento(String
					.valueOf(idRequerimiento));
			tractora = tractorasService.getTractora(requerimiento
					.getIdTractora());
			requerimiento.setTractora(tractora);
			setRequerimientos(requerimiento);
			setBusqueda(null);
		} else {
			setIdUsuario(getUsuario().getIdUsuario());
			if (busqueda == null) {
				busqueda = " ";
			}
		}

		return SUCCESS;
	}

	@Action(value = "/pymeRequerimientosSave", results = {
			@Result(name = "success", location = "pyme.requerimientos.list", type = "tiles"),
			@Result(name = "input", location = "pyme.requerimientos.list", type = "tiles"),
			@Result(name = "error", location = "pyme.requerimientos.list", type = "tiles") })
	public String pymeRequerimientosSave() throws BaseBusinessException {
		log.debug("pymeRequerimientosSave()");
		setMenu(2);

		log.debug("Enviando respuesta de requerimiento=" + idRequerimiento);
		Usuario u = getUsuario();
		log.debug("Id Usuario=" + u.getIdUsuario());
		respuesta.setIdPyME(u.getIdUsuario());
		log.debug("respuesta=" + respuesta);
		setMensaje(pyMEsService.saveRespuesta(respuesta));
		Tractoras tractora = null;
		Requerimientos requerimiento = null;
		StringBuffer diplomados = new StringBuffer();
		if (idRequerimiento != 0) {
			log.debug("idRequerimiento=" + idRequerimiento);
			requerimiento = tractorasService.getRequerimiento(String
					.valueOf(idRequerimiento));
			tractora = tractorasService.getTractora(requerimiento
					.getIdTractora());
			requerimiento.setTractora(tractora);
			setRequerimientos(requerimiento);
			setPyMEs(pyMEsService.getPyME(u.getIdUsuario()));
		}
		if (pyMEs.bDiplomadoCcmxUno)
			diplomados
					.append(mx.com.vgati.ccmx.vinculacion.dto.Diplomados.CulturaOrg
							.getDiplomado());
		if (pyMEs.bDiplomadoCcmxDos)
			diplomados
					.append((pyMEs.bDiplomadoCcmxUno ? ", " : "")
							.concat(mx.com.vgati.ccmx.vinculacion.dto.Diplomados.EstrategiaCom
									.getDiplomado()));

		if (pyMEs.bDiplomadoCcmxTres)
			diplomados
					.append((pyMEs.bDiplomadoCcmxUno || pyMEs.bDiplomadoCcmxDos ? ", "
							: "")
							.concat(mx.com.vgati.ccmx.vinculacion.dto.Diplomados.DesrrolloMet
									.getDiplomado()));
		if (pyMEs.bDiplomadoCcmxCuatro)
			diplomados
					.append((pyMEs.bDiplomadoCcmxUno || pyMEs.bDiplomadoCcmxDos
							|| pyMEs.bDiplomadoCcmxTres ? ", " : "")
							.concat(mx.com.vgati.ccmx.vinculacion.dto.Diplomados.EstrategiaPlan
									.getDiplomado()));
		if (mensaje.getRespuesta() == 0) {
			log.debug("Enviando correo electronico de respuesta");
			String cliente = "";
			String producto = "<br /><br />";
			String telefono = "";
			if (pyMEs.getContactos() != null && !pyMEs.getContactos().isEmpty()
					&& pyMEs.getContactos().get(0).getTelefono() != null) {
				telefono = " su teléfono es "
						+ pyMEs.getContactos().get(0).getTelefono() + " y";
			}
			if (pyMEs.getClientes() != null && !pyMEs.getClientes().isEmpty()) {
				cliente = pyMEs.getClientes().get(0).getCliente();
			}
			for (int i = 0; i < pyMEs.getProductos().size(); i++) {
				producto = producto + (i + 1) + " "
						+ pyMEs.getProductos().get(i).getProducto() + ".<br />";
			}
			SendEmail envia = new SendEmail(
					requerimientos.getTractora().getCorreoElectronico(),
					"SIA CCMX Aviso de Vinculación",
					"<h5 style='font-family: Verdana; font-size: 12px; color: #5A5A5A;'>Estimado(a) "
							.concat(Null.free(requerimientos.getTractora()
									.getNombreContacto()))
							.concat("<br /><br />La empresa ")
							.concat(Null.free(pyMEs.getNombreComercial()))
							.concat(" ha subido una cotización en respuesta al requerimiento No. ")
							.concat(String.valueOf(requerimiento
									.getIdRequerimiento()))
							.concat(" sobre ")
							.concat(Null.free(requerimiento.getProducto()))
							.concat(". La PyME es una empresa que se especializa en los siguientes productos: ")
							.concat(Null.free(producto))
							.concat("<br /><br />")
							.concat("El contacto de ventas de la empresa es ")
							.concat(Null.free(pyMEs.getContactos().get(0)
									.getNombre()))
							.concat(" ")
							.concat(Null.free(pyMEs.getContactos().get(0)
									.getApellidoPaterno()))
							.concat(" ")
							.concat(Null.free(pyMEs.getContactos().get(0)
									.getApellidoMaterno()))
							.concat(", ")
							.concat(Null.free(telefono))
							.concat(" la cuenta de correo electrónico es ")
							.concat(Null.free(pyMEs.getContactos().get(0)
									.getCorreoElectronico()))
							.concat(". La empresa es proveedora actualmente de ")
							.concat(Null.free(cliente))
							.concat(".<br /><br />")
							.concat(Null.free(pyMEs.getNombreComercial()))
							.concat(" ha tomado la Consultoría Básica en el CCMX y sus empleados han cursado los siguientes diplomados ")
							.concat(Null.free(diplomados.toString()))
							.concat(".<br /><br />")
							.concat("Recuerda que podrás ver el perfil completo de la PyME a través del Sistema de Vinculación.<br /><br />")
							.concat("En caso de cualquier duda sobre la operación y funcionamiento del sistema, ")
							.concat("no dudes en ponerte en contacto con andres.blancas@ccmx.org.mx.<br /><br />")
							.concat("Muchas gracias por utilizar el sistema de vinculación del CCMX.<br /></h5>"),
					null);
			log.debug("Enviando correo electrónico:" + envia);
		}
		requerimiento = tractorasService.getRequerimiento(String
				.valueOf(idRequerimiento));
		return SUCCESS;
	}

	@Action(value = "/pymeServiciosShow", results = {
			@Result(name = "success", location = "pyme.servicios.show", type = "tiles"),
			@Result(name = "input", location = "pyme.servicios.show", type = "tiles"),
			@Result(name = "error", location = "pyme.servicios.show", type = "tiles") })
	public String pymeServiciosShow() throws BaseBusinessException {
		log.debug("pymeServiciosShow()");
		setMenu(3);
		Usuario u = getUsuario();
		setDocumentoRfc(pyMEsService.getRfc(u.getIdUsuario()));

		if(generacion == 0){
			log.debug("Consultando Tema y Generación de Diplomados...");
			setGeneraciones(pyMEsService.getGeneracion());
			setListDiplomados(pyMEsService.getTemaDiplomado());
		}else{
			log.debug("Consulta de Diplomados por generación..." + generacion + " y " + tituloDiplomado);
			Diplomados idD = pyMEsService.getDiplomado(generacion, tituloDiplomado);
			log.debug("idDiplomado... " + idD.getIdDiplomado());
			setServiciosDiplomado(pyMEsService.getServicioDiplomado(idD.getIdDiplomado(), u.getIdUsuario()));
			setListSesiones(pyMEsService.getSesion(idD.getIdDiplomado()));
			if(serviciosDiplomado != null){
				setListDocumentos(pyMEsService.getArchivosDiplomado(serviciosDiplomado.getIdServiciosDiplomado()));
			}
			setIdDiplomado(idD.getIdDiplomado());
		}
		return SUCCESS;
	}

	@Action(value = "/pymeServiciosSave", results = {
			@Result(name = "success", location = "pyme.servicios.show", type = "tiles"),
			@Result(name = "input", location = "pyme.servicios.show", type = "tiles"),
			@Result(name = "error", location = "pyme.servicios.show", type = "tiles") })
	public String pymeServiciosSave() throws BaseBusinessException, FileNotFoundException {
		log.debug("pymeServiciosSave()");
		setMenu(3);
		
		if (serviciosDiplomado != null && serviciosConsultoria == null) {
			ServiciosDiplomado sd = getServiciosDiplomado();
			sd.setIdServiciosDiplomado(serviciosDiplomado.getIdServiciosDiplomado());
			Documento d = null;
			Usuario u = getUsuario();

			if(serviciosDiplomado.getIdServiciosDiplomado() == 0){
				log.debug("Salvando servicio Diplomados...");
				log.debug("Id Usuario=" + u.getIdUsuario());
				serviciosDiplomado.setIdUsuario(u.getIdUsuario());
				serviciosDiplomado.setIdDiplomado(idDiplomado);
				log.debug("Id DIPLOMADO... " + idDiplomado);
				setMensaje(pyMEsService.saveServDiplomado(serviciosDiplomado));
				sd = pyMEsService.getIdServicioDiplomado();
				log.debug("Id Servicio Diplomado=" + sd);
			}

			if(documentoRfc.getIdArchivo() == 0){
				log.debug("Guardando el RFC de la PyME...");
				d = new Documento();
				d.setIs(new FileInputStream(serviciosDiplomado.getRfc()));
				d.setIdUsuario(u.getIdUsuario());
				d.setNombre(serviciosDiplomado.getRfcFileName());
				d.setbRfc(true);
				setMensaje(pyMEsService.saveRFCPyME(d));
			}

			if (serviciosDiplomado.getArchivos() != null) {
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

			if(idArchivos != null){
				log.debug("Eliminando archivos..." + idArchivos);
				setMensaje(pyMEsService.deleteArchivoPago(idArchivos));
			}

			if (serviciosDiplomado.getAsistentes() != null) {
				Iterator<Asistentes> i = serviciosDiplomado.getAsistentes().iterator();
				Asistentes as = null;
				while (i.hasNext()) {
					as = i.next();
					if (as != null && as.getIdAsistente() == 0 && !Null.free(as.getNombre()).isEmpty()) {
						log.debug("Insertando Asistente... " + as.getNombre());
						as.setIdServiciosDiplomado(sd.getIdServiciosDiplomado());
						setMensaje(pyMEsService.saveAsistentes(as));
					} else if (as != null && as.getIdAsistente() != 0 && !Null.free(as.getNombre()).isEmpty()) {
						log.debug("Actualizando Asistente... " + as.getIdAsistente());
						setMensaje(pyMEsService.updateAsistentes(as));
					}
				}
			}
		}

		if (serviciosConsultoria != null) {
			String serv = null; 
			Usuario u = getUsuario();
			Documento d = null;
			
			if( serviciosConsultoria.isbConsultoriaVeinte() == true ){
				serv = "B_CONSULTORIA_20 = true";
			}else if( serviciosConsultoria.isbConsultoriaCuarenta() == true ){
				serv = "B_CONSULTORIA_40 = true";
			}else if( serviciosConsultoria.isbConsultoriaSesenta() == true ){
				serv = "B_CONSULTORIA_60 = true";
			}else if( serviciosConsultoria.isbConsultoriaOchenta() == true ){
				serv = "B_CONSULTORIA_80 = true";
			}

			log.debug("Consultando el servicio consultoría");
			if (pyMEsService.getServicioConsultoria(u.getIdUsuario(), serv) != null) {
				setMensaje(new Mensaje( 1,
						"Imposible realizar la operación, el servicio que solicita ya ha sido registrado, seleccione otro servicio."));
				return SUCCESS;
			}
			
			if(serviciosDiplomado.getIdRfc() == 0){
				log.debug("Guardando el RFC de la PyME...");
				d = new Documento();
				d.setIs(new FileInputStream(serviciosDiplomado.getRfc()));
				d.setIdUsuario(u.getIdUsuario());
				d.setNombre(serviciosDiplomado.getRfcFileName());
				d.setbRfc(true);
				setMensaje(pyMEsService.saveRFCPyME(d));
			}
			
			log.debug("Salvando el servicio de consultoria=" + serviciosConsultoria);
			serviciosConsultoria.setIdUsuario(u.getIdUsuario());
			log.debug("Usuario sessionMap=" + u);
			setMensaje(pyMEsService.saveConsultoria(serviciosConsultoria));
		}

		return SUCCESS;
	}

	@Action(value = "/pymeBusquedaShow", results = { @Result(name = "success", location = "pyme.busqueda.show", type = "tiles") })
	public String pymeBusquedaShow() throws PyMEsNoObtenidasException,
			ProductosNoObtenidosException, IndicadoresNoObtenidosException,
			ConsultoriasNoObtenidasException {
		log.debug("pymeBusquedaShow()");
		setMenu(4);

		if (idUsuario != 0) {
			log.debug("Consultando la PyME" + idUsuario);
			setPyMEs(pyMEsService.getPyME(idUsuario));

			setEstadosVentas(pyMEsService.getEstadoVenta(idUsuario));
			setRelPymesTractoras(pyMEsService.getCalificacion(idUsuario));
			setIndicadores(pyMEsService.getIndicadorMes(idUsuario));
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

	@Action(value = "/pymeErrorLogin", results = { @Result(name = "success", location = "pyme.error.login", type = "tiles") })
	public String pymeErrorLogin() {
		log.debug("pymeErrorLogin()");

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

	public Domicilios getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(Domicilios domicilios) {
		this.domicilios = domicilios;
	}

	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public Indicadores getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(Indicadores indicadores) {
		this.indicadores = indicadores;
	}

	public Certificaciones getCertificaciones() {
		return certificaciones;
	}

	public void setCertificaciones(Certificaciones certificaciones) {
		this.certificaciones = certificaciones;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
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

	public List<Requerimientos> getListRequerimientos()
			throws RequerimientosNoObtenidosException {
		log.debug("getListRequerimientos()");
		log.debug("Aqui está fechaDesde" + fechaDesde);
		log.debug("Aqui está fechaHasta" + fechaHasta);
		setListRequerimientos(pyMEsService.getRequerimiento(busqueda,
				tractoraReq, fechaDesde, fechaHasta, idUsuario));
		return listRequerimientos;
	}

	public void setListRequerimientos(List<Requerimientos> listRequerimientos) {
		this.listRequerimientos = listRequerimientos;
	}

	public void setListPyMEs(List<PyMEs> listPyMEs) {
		this.listPyMEs = listPyMEs;
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

	public String getTractoraReq() {
		return tractoraReq;
	}

	public int getIdRequerimiento() {
		return idRequerimiento;
	}

	public void setIdRequerimiento(int idRequerimiento) {
		this.idRequerimiento = idRequerimiento;
	}

	public java.sql.Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(java.sql.Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public java.sql.Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(java.sql.Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public void setTractoraReq(String tractoraReq) {
		this.tractoraReq = tractoraReq;
	}

	public ServiciosDiplomado getServiciosDiplomado() {
		return serviciosDiplomado;
	}

	public void setServiciosDiplomado(ServiciosDiplomado serviciosDiplomado) {
		this.serviciosDiplomado = serviciosDiplomado;
	}

	public ServiciosConsultoria getServiciosConsultoria() {
		return serviciosConsultoria;
	}

	public void setServiciosConsultoria(
			ServiciosConsultoria serviciosConsultoria) {
		this.serviciosConsultoria = serviciosConsultoria;
	}

	public Asistentes getAsistentes() {
		return asistentes;
	}

	public void setAsistentes(Asistentes asistentes) {
		this.asistentes = asistentes;
	}

	public Requerimientos getRequerimientos() {
		return requerimientos;
	}

	public void setRequerimientos(Requerimientos requerimientos) {
		this.requerimientos = requerimientos;
	}

	public List<Requerimientos> getListFechas()
			throws RequerimientosNoObtenidosException {
		log.debug("getListFechas()");
		setListFechas(pyMEsService.getFecha());
		return listFechas;
	}

	public void setListFechas(List<Requerimientos> listFechas) {
		this.listFechas = listFechas;
	}

	public List<Tractoras> getListTractoras()
			throws TractorasNoObtenidasException {
		log.debug("getListTractoras()");
		setListTractoras(pyMEsService.getTractora());
		return listTractoras;
	}

	public void setListTractoras(List<Tractoras> listTractoras) {
		this.listTractoras = listTractoras;
	}

	public List<Diplomados> getListDiplomados() {
		return listDiplomados;
	}

	public void setListDiplomados(List<Diplomados> listDiplomados) {
		this.listDiplomados = listDiplomados;
	}

	public int getIdDiplomado() {
		return idDiplomado;
	}

	public void setIdDiplomado(int idDiplomado) {
		this.idDiplomado = idDiplomado;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getTituloDiplomado() {
		return tituloDiplomado;
	}

	public void setTituloDiplomado(String tituloDiplomado) {
		this.tituloDiplomado = tituloDiplomado;
	}

	public String getFechaDip() {
		return fechaDip;
	}

	public void setFechaDip(String fechaDip) {
		this.fechaDip = fechaDip;
	}
	
	public String getPago() {
		return pago;
	}

	public void setPago(String pago) {
		this.pago = pago;
	}

	public Respuesta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	public Productos getProductos() {
		return productos;
	}

	public void setProductos(Productos productos) {
		this.productos = productos;
	}

	public String getProdPrincipales() {
		return prodPrincipales;
	}

	public void setProdPrincipales(String prodPrincipales) {
		this.prodPrincipales = prodPrincipales;
	}

	public int getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(int idArchivo) {
		this.idArchivo = idArchivo;
	}

	public String getIdArchivos() {
		return idArchivos;
	}

	public void setIdArchivos(String idArchivos) {
		this.idArchivos = idArchivos;
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

	public Tractoras getTractoras() {
		return tractoras;
	}

	public void setTractoras(Tractoras tractoras) {
		this.tractoras = tractoras;
	}

	public RelPyMEsTractoras getRelPymesTractoras() {
		return relPymesTractoras;
	}

	public void setRelPymesTractoras(RelPyMEsTractoras relPymesTractoras) {
		this.relPymesTractoras = relPymesTractoras;
	}

	public int getGeneraciones() {
		return generaciones;
	}

	public void setGeneraciones(int generaciones) {
		this.generaciones = generaciones;
	}

	public int getGeneracion() {
		return generacion;
	}

	public void setGeneracion(int generacion) {
		this.generacion = generacion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Diplomados getDiplomados() {
		return diplomados;
	}

	public void setDiplomados(Diplomados diplomados) {
		this.diplomados = diplomados;
	}

	public List<Sesiones> getListSesiones() {
		return listSesiones;
	}

	public void setListSesiones(List<Sesiones> listSesiones) {
		this.listSesiones = listSesiones;
	}

	public List<Documento> getListDocumentos() {
		return listDocumentos;
	}

	public void setListDocumentos(List<Documento> listDocumentos) {
		this.listDocumentos = listDocumentos;
	}

	public Documento getDocumentoRfc() {
		return documentoRfc;
	}

	public void setDocumentoRfc(Documento documentoRfc) {
		this.documentoRfc = documentoRfc;
	}
}