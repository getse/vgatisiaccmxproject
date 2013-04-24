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

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Certificaciones;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Clientes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Productos;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.AsistentesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.CertificacionesNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.CertificacionesNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ConsultoriasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.DiplomadosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMENoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.RespuestaNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.service.PyMEsService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.framework.action.AbstractBaseAction;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;
import mx.com.vgati.framework.dto.Respuesta;

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
			"REQUERIMIENTOS", "SERVICIOS", "B&Uacute;SQUEDAS" };
	private static final String[] fr = { "pymeInformacionShow.do", "pymeRequerimientosShow.do",
			"pymeServiciosShow.do", "pymeBusquedaShow.do" };
	
	private PyMEsService pyMEsService;
	private PyMEs pyMEs;
	private List<PyMEs> listPyMEs;
	private Domicilios domicilios;
	private Clientes clientes;
	private Certificaciones certificaciones;
	private Mensaje mensaje;
	private String busqueda;
	private String estado;
	private String sector;
	private String subSector;
	private String tractoraReq;
	private int idRequerimiento;
	private int idDiplomado;
	private java.sql.Date fechaDesde;
	private java.sql.Date fechaHasta;
	private ServiciosDiplomado serviciosDiplomado;
	private ServiciosConsultoria serviciosConsultoria;
	private Asistentes asistentes;
	private Requerimientos requerimientos;
	private List<Requerimientos> listRequerimientos;
	private List<Requerimientos> listFechas;
	private List<Tractoras> listTractoras;
	private String tituloDiplomado;
	private Date fechaDip;
	private String nombresAsistentes;
	private String appPatAsistentes;
	private String appMatAsistentes;
	private Respuesta respuesta;
	private Productos productos;
	private String prodPrincipales;
	private int idArchivo;
	private String nameArchivo;
	private String mimeArchivo;
	private InputStream archivo;

	public void setPyMEsService(PyMEsService pyMEsService) {
		this.pyMEsService = pyMEsService;
	}

	@Action(value = "/pymeInformacionShow", results = { 
			@Result(name = "success", location = "pyme.datos.show", type = "tiles"),
			@Result (name = "input", location = "pyme.datos.show", type = "tiles"),
			@Result (name = "error", location = "pyme.datos.show", type = "tiles") })
	public String pymeInformacionShow() throws PyMEsNoObtenidasException, DomiciliosNoObtenidosException, 
			CertificacionesNoObtenidasException{
		log.debug("pymeInformacionShow()");
		setMenu(1);

		Usuario u = (Usuario) sessionMap.get("Usuario");
		log.debug("Usuario=" + u);
		setPyMEs(pyMEsService.getPyME(u.getIdUsuario()));
		String idDom = pyMEsService.getIdDomicilio(u.getIdUsuario());
		log.debug("idDomicilio=" + idDom);
		setDomicilios(pyMEsService.getDomicilio(Integer.parseInt(idDom)));
		String idCert = pyMEsService.getIdCertificacion(u.getIdUsuario());
		log.debug("idCertificacion=" + idCert);
		setCertificaciones(pyMEsService.getCertificacion(Integer.parseInt(idCert)));
		return SUCCESS;
	}
	
	@Action(value = "/pymeInformacionSave", results = { 
			@Result(name = "success", location = "pyme.datos.show", type = "tiles"),
			@Result(name = "input", location = "pyme.datos.show", type = "tiles"),
			@Result(name = "error", location = "pyme.datos.show", type = "tiles") })
	public String pymeInformacionSave() throws PyMENoAlmacenadaException, DomiciliosNoAlmacenadosException,
			CertificacionesNoAlmacenadasException{
		log.debug("pymeInformacionSave()");
		setMenu(1);
		
		if(pyMEs != null){
			log.debug("Actualizando los datos de la PyME" + pyMEs);
			pyMEs.setIdUsuario(((Usuario) sessionMap.get("Usuario"))
					.getIdUsuario());
			setMensaje(pyMEsService.updatePyME(pyMEs));
		}
		if (domicilios != null && domicilios.getIdDomicilio() == 0) {
			log.debug("Insertando el domicilio" + domicilios);
			setMensaje(pyMEsService.saveDomicilio(domicilios));
			log.debug("Insertando id's");
			log.debug("mensaje=" + mensaje);
			domicilios.setIdDomicilio(Integer.parseInt(mensaje != null ? mensaje.getId() : "0"));
			pyMEs.setIdUsuario(((Usuario) sessionMap.get("Usuario")).getIdUsuario());
			setMensaje(pyMEsService.saveRelDomicilio(domicilios, pyMEs));
		}else if (domicilios != null) {
			log.debug("Actualizando el domicilio" + domicilios);
			setMensaje(pyMEsService.updateDomicilio(domicilios));
		}
		if(certificaciones != null && certificaciones.getIdCertificado() == 0){
			log.debug("Insertando la certificación" + certificaciones);
			certificaciones.setIdUsuario(((Usuario) sessionMap.get("Usuario")).getIdUsuario());
			setMensaje(pyMEsService.saveCertificacion(certificaciones));
		}else if(certificaciones != null){
			log.debug("Actualizando la Certificacion" + certificaciones);
			setMensaje(pyMEsService.updateCertificacion(certificaciones));
		}
		return SUCCESS;
	}

	@Action(value = "/pymeRequerimientosShow", results = { 
			@Result(name = "success", location = "pyme.requerimientos.list", type = "tiles"),
			@Result(name = "input", location = "pyme.requerimientos.list", type = "tiles"),
			@Result(name = "error", location = "pyme.requerimientos.list", type = "tiles") })
	public String pymeRequerimientosShow() throws RequerimientosNoObtenidosException {
		log.debug("pymeRequerimientosShow()");
		setMenu(2);
		log.debug("tractoraReq: " + tractoraReq);
		if(idRequerimiento != 0){
			log.debug("Aqui está el ID=" + idRequerimiento);
			setRequerimientos(pyMEsService.getShowRequerimiento(idRequerimiento));
		}
		return SUCCESS;
	}
	
	@Action(value = "/pymeRequerimientosSave", results = { 
			@Result(name = "success", location = "pyme.requerimientos.list", type = "tiles"),
			@Result(name = "input", location = "pyme.requerimientos.list", type = "tiles"),
			@Result(name = "error", location = "pyme.requerimientos.list", type = "tiles") })
	public String pymeRequerimientosSave() throws RespuestaNoAlmacenadaException{
		log.debug("pymeRequerimientosSave()");
		setMenu(2);
		log.debug("Enviando respuesta de requerimiento=" + idRequerimiento);
		log.debug("respuesta=" + respuesta);
		Usuario u = (Usuario) sessionMap.get("Usuario");
		log.debug("Id Usuario=" + u.getIdUsuario());
		setMensaje(pyMEsService.saveRespuesta(respuesta));
		return SUCCESS;
	}

	@Action(value = "/pymeServiciosShow", results = { 
			@Result(name = "success", location = "pyme.servicios.show", type = "tiles"),
			@Result(name = "input", location = "pyme.servicios.show", type = "tiles"),
			@Result(name = "error", location = "pyme.servicios.show", type = "tiles")})
	public String pymeServiciosShow() throws AsistentesNoAlmacenadosException, DiplomadosNoAlmacenadosException {
		log.debug("pymeServiciosShow()");
		setMenu(3);
		
		if(idDiplomado != 0 && asistentes != null){
			log.debug("Salvando servicio Diplomado...");
			Usuario u = (Usuario) sessionMap.get("Usuario");
			serviciosDiplomado = new ServiciosDiplomado();
			log.debug("Id Usuario=" + u.getIdUsuario());
			serviciosDiplomado.setIdUsuario(u.getIdUsuario());
			log.debug("Id Diplomado=" + idDiplomado);
			serviciosDiplomado.setIdDiplomado(idDiplomado);
			log.debug("Titulo diplomado =" + tituloDiplomado);
			serviciosDiplomado.setTitulo(tituloDiplomado);
			//serviciosDiplomado.setFecha(fechaDip);
			serviciosDiplomado.setMensaje("Servicio registrado correctamente");
			setMensaje(pyMEsService.saveServDiplomado(serviciosDiplomado));
		}

		if(asistentes != null && asistentes.getNombre() != null){
			log.debug("Salvando los asistentes...");
			StringTokenizer nombres = new StringTokenizer(nombresAsistentes, ",");
			StringTokenizer appPaternos = new StringTokenizer(appPatAsistentes, ",");
			StringTokenizer appMaternos = new StringTokenizer(appMatAsistentes, ",");
			while(nombres.hasMoreTokens()){
				asistentes = new Asistentes();
				asistentes.setNombre(nombres.nextToken());
				asistentes.setAppPaterno(appPaternos.nextToken());
				asistentes.setAppMaterno(appMaternos.nextToken());
				log.debug("asistente a insertar: " + asistentes);
				setMensaje(pyMEsService.saveAsistente(asistentes));			 
			}
		}
		
		return SUCCESS;
	}
	
	@Action(value = "/pymeServiciosSave", results = { 
			@Result(name = "success", location = "pyme.servicios.show", type = "tiles"),
			@Result(name = "input", location = "pyme.servicios.show", type = "tiles"),
			@Result(name = "error", location = "pyme.servicios.show", type = "tiles") })
	public String pymeServiciosSave() throws ConsultoriasNoAlmacenadasException {
		log.debug("pymeServiciosSave()");
		setMenu(3);
		if(serviciosConsultoria != null){
			log.debug("Salvando el servicio de consultoria=" + serviciosConsultoria);
			Usuario u = (Usuario) sessionMap.get("Usuario");
			serviciosConsultoria.setIdUsuario(u.getIdUsuario());
			log.debug("Usuario sessionMap=" + u);
			setMensaje(pyMEsService.saveConsultoria(serviciosConsultoria));
		}
		
		return SUCCESS;
	}

	@Action(value = "/pymeBusquedaShow", results = { @Result(name = "success", location = "pyme.busqueda.show", type = "tiles") })
	public String pymeBusquedaShow() {
		log.debug("pymeBusquedaShow()");
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
	
	public PyMEs getPyMEs() {
		return pyMEs;
	}

	public void setPyMEs(PyMEs pyMEs) {
		this.pyMEs = pyMEs;
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
		log.debug("Contenido de estado:" + estado);
		setListPyMEs(pyMEsService.getBusquedaPyME(busqueda, estado, sector, subSector));
		return listPyMEs;
	}
	
	public List<Requerimientos> getListRequerimientos() throws RequerimientosNoObtenidosException {
		log.debug("getListRequerimientos()");
		log.debug("Aqui está fechaDesde" + fechaDesde );
		log.debug("Aqui está fechaHasta" + fechaHasta );
		setListRequerimientos(pyMEsService.getRequerimiento(busqueda, tractoraReq, fechaDesde, fechaHasta));
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

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSubSector() {
		return subSector;
	}

	public void setSubSector(String subSector) {
		this.subSector = subSector;
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

	public void setServiciosConsultoria(ServiciosConsultoria serviciosConsultoria) {
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

	public List<Requerimientos> getListFechas() throws RequerimientosNoObtenidosException {
		log.debug("getListFechas()");
		setListFechas(pyMEsService.getFecha());
		return listFechas;
	}

	public void setListFechas(List<Requerimientos> listFechas) {
		this.listFechas = listFechas;
	}
	
	public List<Tractoras> getListTractoras() throws TractorasNoObtenidasException {
		log.debug("getListTractoras()");
		setListTractoras(pyMEsService.getTractora());
		return listTractoras;
	}

	public void setListTractoras(List<Tractoras> listTractoras) {
		this.listTractoras = listTractoras;
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
	
	public Date getFechaDip() {
		return fechaDip;
	}

	public void setFechaDip(Date fechaDip) {
		this.fechaDip = fechaDip;
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
			@Result(name = "success", type = "stream", params = { "inputName", "archivo", "contentType", "mimeArchivo", "contentDisposition",
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