package mx.com.vgati.ccmx.vinculacion.report.dao;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXFinanzas;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXParticipantes;
import mx.com.vgati.ccmx.vinculacion.report.dto.Filtros;
import mx.com.vgati.ccmx.vinculacion.report.dto.PYMESReporte;
import mx.com.vgati.ccmx.vinculacion.report.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.report.dto.TotalEmpresas;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dao.exception.DaoException;

public interface ReportDao {
	public List<CCMXParticipantes> getDatos();
	public List<Consultoras> getConsultoras() throws DaoException;
	public List<Tractoras> getTractoras() throws DaoException;
	public List<ServiciosConsultoria> getServiciosConsultoria() throws DaoException;
	List<CCMXParticipantes> getCCMXServicios(Filtros filtros)throws DaoException;
	public int getTCultura(Filtros filtros)throws DaoException;
	public int getTPlaneacion(Filtros filtros)throws DaoException;
	public int getTManufactura(Filtros filtros)throws DaoException;
	public int getTEstrategia(Filtros filtros)throws DaoException;
	public List<TotalEmpresas> getEmpresasByConsultora(Filtros filtros)throws DaoException;
	public List<CCMXFinanzas> getCCMXFiannzas(Filtros filtros) throws DaoException;
	public List<PYMESReporte> getPymesReporte(Filtros filtros) throws DaoException;
	
	
}
