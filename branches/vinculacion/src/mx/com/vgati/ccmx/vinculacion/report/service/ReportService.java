package mx.com.vgati.ccmx.vinculacion.report.service;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXFinanzas;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXParticipantes;
import mx.com.vgati.ccmx.vinculacion.report.dto.Filtros;
import mx.com.vgati.ccmx.vinculacion.report.dto.FiltrosGenerales;
import mx.com.vgati.ccmx.vinculacion.report.dto.IndicadoresPymes;
import mx.com.vgati.ccmx.vinculacion.report.dto.PYMESReporte;
import mx.com.vgati.ccmx.vinculacion.report.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.report.dto.TotalEmpresas;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;

public interface ReportService {
	public List<CCMXParticipantes> getDatos();
	public List<Consultoras> getConsultoras();
	public List<FiltrosGenerales> getMenuFacturaAnticipo();
	public List<FiltrosGenerales> getMenuFacturaAnticipoFiniquito();
	public List<FiltrosGenerales> getMenuCedulas();
	public List<FiltrosGenerales> getMenuEstatus();
	public List<Consultoras> getConsultores(int idConsultoraPadre);
	public List<Tractoras> getTractoras()throws TractorasNoObtenidasException ;
	public List<ServiciosConsultoria> getServiciosConsultoria();
	public List<CCMXParticipantes> getCCMXServicios(Filtros filtros);
	public int getTCultura(Filtros filtros);
	public int getTPlaneacion(Filtros filtros);
	public int getTManufactura(Filtros filtros);
	public int getTEstrategia(Filtros filtros);
	public List<CCMXFinanzas> getCCMXFiannzas(Filtros filtros);
	public List<PYMESReporte> getPymesReporte(Filtros filtros);
	public List<TotalEmpresas> getEmpresasByConsultora(Filtros filtros);
	public List<IndicadoresPymes> getIndicadoresReporte(Filtros filtros);
}
