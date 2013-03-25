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

import java.util.List;
import java.util.StringTokenizer;

import mx.com.vgati.ccmx.vinculacion.ccmx.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.ccmx.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.dto.Usuario;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Certificaciones;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Clientes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.AsistentesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.CertificacionesNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.CertificacionesNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ClienteNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ClientesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ConsultoriasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.DiplomadosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMeNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMesNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.service.PyMEsService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.framework.action.AbstractBaseAction;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.dto.Requerimientos;

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
	
	private PyMEsService pyMesService;
	private PyMEs pyMes;
	private List<PyMEs> listPyMEs;
	private Domicilios domicilios;
	private Clientes clientes;
	private Certificaciones certificaciones;
	private Mensaje mensaje;
	private String busqueda;
	private String estado;
	private String codigoPostal;
	private String sector;
	private String subSector;
	private String tractoraReq;
	private int idRequerimiento;
	private int idDiplomado;
	private String fechaDesde;
	private String fechaHasta;
	private ServiciosDiplomado serviciosDiplomado;
	private ServiciosConsultoria serviciosConsultoria;
	private List<ServiciosDiplomado> listServiciosDiplomado;
	private Asistentes asistentes;
	private Requerimientos requerimientos;
	private List<Requerimientos> listRequerimientos;
	private List<Requerimientos> listFechas;
	private List<Tractoras> listTractoras;
	private String tituloDiplomado;
	private String nombresAsistentes;
	private String appPatAsistentes;
	private String appMatAsistentes;

	public void setPyMesService(PyMEsService pyMesService) {
		this.pyMesService = pyMesService;
	}

	@Action(value = "/pymeInformacionShow", results = { 
			@Result(name = "success", location = "pyme.datos.show", type = "tiles"),
			@Result (name = "input", location = "pyme.datos.show", type = "tiles"),
			@Result (name = "error", location = "pyme.datos.show", type = "tiles") })
	public String pymeInformacionShow() throws PyMesNoObtenidasException, DomiciliosNoObtenidosException, 
			ClienteNoObtenidoException, CertificacionesNoObtenidasException{
		log.debug("pymeInformacionShow()");
		setMenu(1);

		Usuario u = (Usuario) sessionMap.get("Usuario");
		log.debug("Usuario=" + u);
		setPyMes(pyMesService.getPyME(u.getIdUsuario()));
		String idDom = pyMesService.getIdDomicilio(u.getIdUsuario());
		log.debug("idDomicilio=" + idDom);
		setDomicilios(pyMesService.getDomicilio(Integer.parseInt(idDom)));
		log.debug("domicilio=" + domicilios);
		String idClient = pyMesService.getIdCliente(u.getIdUsuario());
		log.debug("idCliente=" + idClient);
		setClientes(pyMesService.getCliente(Integer.parseInt(idClient)));
		log.debug("clientes=" + clientes);
		String idCert = pyMesService.getIdCertificacion(u.getIdUsuario());
		log.debug("idCertificacion=" + idCert);
		setCertificaciones(pyMesService.getCertificacion(Integer.parseInt(idCert)));
		log.debug("certificaciones=" + certificaciones);
		return SUCCESS;
	}
	
	@Action(value = "/pymeInformacionSave", results = { @Result(name = "success", location = "pyme.datos.show", type = "tiles") })
	public String pymeInformacionSave() throws PyMeNoAlmacenadaException, 
			DomiciliosNoAlmacenadosException, ClientesNoAlmacenadosException, CertificacionesNoAlmacenadasException{
		log.debug("pymeInformacionSave()");
		setMenu(1);
		
		if(pyMes != null){
			log.debug("Actualizando los datos de la PyME" + pyMes);
			pyMes.setIdUsuario(((Usuario) sessionMap.get("Usuario"))
					.getIdUsuario());
			setMensaje(pyMesService.updatePyME(pyMes));
		}
		if (domicilios != null && domicilios.getIdDomicilio() == 0) {
			log.debug("Insertando el domicilio" + domicilios);
			setMensaje(pyMesService.saveDomicilio(domicilios));
			log.debug("Insertando id's");
			log.debug("mensaje=" + mensaje);
			domicilios.setIdDomicilio(Integer.parseInt(mensaje != null ? mensaje.getId() : "0"));
			pyMes.setIdUsuario(((Usuario) sessionMap.get("Usuario")).getIdUsuario());
			setMensaje(pyMesService.saveRelDomicilio(domicilios, pyMes));
		}else if (domicilios != null) {
			log.debug("Actualizando el domicilio" + domicilios);
			setMensaje(pyMesService.updateDomicilio(domicilios));
		}
		if(clientes != null && clientes.getIdCliente() == 0){
			log.debug("Insertando el cliente" + clientes);
			clientes.setIdUsuario(((Usuario) sessionMap.get("Usuario")).getIdUsuario());
			setMensaje(pyMesService.saveCliente(clientes));
		}else if(clientes != null){
			log.debug("Actualizando el cliente" + clientes);
			setMensaje(pyMesService.updateCliente(clientes));
		}
		if(certificaciones != null && certificaciones.getIdCertificado() == 0){
			log.debug("Insertando la certificación" + certificaciones);
			certificaciones.setIdUsuario(((Usuario) sessionMap.get("Usuario")).getIdUsuario());
			setMensaje(pyMesService.saveCertificacion(certificaciones));
		}else if(certificaciones != null){
			log.debug("Actualizando la Certificacion" + certificaciones);
			setMensaje(pyMesService.updateCertificacion(certificaciones));
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
		log.debug("Aqui está el ID fuera del if=" + idRequerimiento);
		if(idRequerimiento != 0){
			log.debug("Aqui está el ID=" + idRequerimiento);
			setRequerimientos(pyMesService.getShowRequerimiento(idRequerimiento));
			
		}
		
		
		return SUCCESS;
	}

	@Action(value = "/pymeServiciosShow", results = { 
			@Result(name = "success", location = "pyme.servicios.show", type = "tiles"),
			@Result(name = "input", location = "pyme.servicios.show", type = "tiles"),
			@Result(name = "error", location = "pyme.servicios.show", type = "tiles")})
	public String pymeServiciosShow() throws AsistentesNoAlmacenadosException {
		log.debug("pymeServiciosShow()");
		setMenu(3);
		
		log.debug("Salvando el asistente=" + asistentes);
		if(asistentes != null && asistentes.getNombre() != null){
			log.debug("Salvando los asistentes...");
			StringTokenizer nombres = new StringTokenizer(nombresAsistentes, ",");
			StringTokenizer appPaternos = new StringTokenizer(appPatAsistentes, ",");
			StringTokenizer appMaternos = new StringTokenizer(appMatAsistentes, ",");
			while(nombres.hasMoreTokens()){
				asistentes = new Asistentes();
				asistentes.setIdDiplomado(idDiplomado); // constante idDiplomado
				asistentes.setNombre(nombres.nextToken());
				asistentes.setAppPaterno(appPaternos.nextToken());
				asistentes.setAppMaterno(appMaternos.nextToken());
				log.debug("asistente a insertar: " + asistentes);
				setMensaje(pyMesService.saveAsistente(asistentes));			 
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
			log.debug("Salvando la consultoria=" + serviciosConsultoria);
			Usuario u = (Usuario) sessionMap.get("Usuario");
			serviciosConsultoria.setIdUsuario(u.getIdUsuario());
			log.debug("Usuario sessionMap=" + u);
			setMensaje(pyMesService.saveConsultoria(serviciosConsultoria));
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
	
	public PyMEs getPyMes() {
		return pyMes;
	}

	public void setPyMes(PyMEs pyMes) {
		this.pyMes = pyMes;
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
	
	public List<PyMEs> getListPyMEs() throws PyMesNoObtenidasException {
		log.debug("Contenido de estado:" + estado);
		log.debug("Contenido de Codigo Postal:" + codigoPostal);
		setListPyMEs(pyMesService.getBusquedaPyME(busqueda, estado, codigoPostal, sector, subSector));
		return listPyMEs;
	}
	
	public List<Requerimientos> getListRequerimientos() throws RequerimientosNoObtenidosException {
		log.debug("getListRequerimientos()");
		log.debug("Aqui está fechaDesde" + fechaDesde );
		log.debug("Aqui está fechaHasta" + fechaHasta );
		setListRequerimientos(pyMesService.getRequerimiento(busqueda, tractoraReq, fechaDesde, fechaHasta));
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

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
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
	
	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
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
	
	public List<ServiciosDiplomado> getListServiciosDiplomado() throws DiplomadosNoObtenidosException {
		setListServiciosDiplomado(pyMesService.getServiciosDiplomado(serviciosDiplomado));
		return listServiciosDiplomado;
	}

	public void setListServiciosDiplomado(List<ServiciosDiplomado> listServiciosDiplomado) {
		this.listServiciosDiplomado = listServiciosDiplomado;
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
		setListFechas(pyMesService.getFecha());
		return listFechas;
	}

	public void setListFechas(List<Requerimientos> listFechas) {
		this.listFechas = listFechas;
	}
	
	public List<Tractoras> getListTractoras() throws TractorasNoObtenidasException {
		log.debug("getListTractoras()");
		setListTractoras(pyMesService.getTractora());
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
}