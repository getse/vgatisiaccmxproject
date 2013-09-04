package mx.com.vgati.ccmx.vinculacion.report.service;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.ConsultorasNoObtenidasExceprion;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.exception.ConsultoraNoObtenidaException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXFinanzas;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXParticipantes;
import mx.com.vgati.ccmx.vinculacion.report.dto.Filtros;
import mx.com.vgati.ccmx.vinculacion.report.dto.FiltrosGenerales;
import mx.com.vgati.ccmx.vinculacion.report.dto.IndicadoresPymes;
import mx.com.vgati.ccmx.vinculacion.report.dto.PYMESReporte;
import mx.com.vgati.ccmx.vinculacion.report.dto.FinanzasDiplomados;
import mx.com.vgati.ccmx.vinculacion.report.dto.PymesDiplomados;
import mx.com.vgati.ccmx.vinculacion.report.dto.TotalEmpresas;
import mx.com.vgati.ccmx.vinculacion.report.exception.FiltrosExcception;
import mx.com.vgati.ccmx.vinculacion.report.exception.ReporteException;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;

public interface ReportService {
	public List<CCMXParticipantes> getDatos() throws PyMEsNoObtenidasException;
	public List<Consultoras> getConsultoras() throws ConsultoraNoObtenidaException;
	public List<FiltrosGenerales> getMenuFacturaAnticipo() throws FiltrosExcception;
	public List<FiltrosGenerales> getMenuFacturaFiniquito() throws FiltrosExcception;
	public List<FiltrosGenerales> getMenuFacturaAnticipoFiniquito() throws FiltrosExcception;
	public List<FiltrosGenerales> getMenuCedulas() throws FiltrosExcception;
	public List<FiltrosGenerales> getMenuEstatus() throws FiltrosExcception;
	public List<Consultoras> getConsultores(int idConsultoraPadre) throws ConsultorasNoObtenidasExceprion;
	public List<Tractoras> getTractoras()throws TractorasNoObtenidasException ;
	public List<ServiciosConsultoria> getServiciosConsultoria() throws ConsultoraNoObtenidaException;
	public List<CCMXParticipantes> getCCMXServicios(Filtros filtros) throws PyMEsNoObtenidasException;
	public int getCCMXServiciosTotal(Filtros filtros) throws PyMEsNoObtenidasException;
	public int getParticipantes(Filtros filtros, int indice) throws FiltrosExcception;
	public int getParticipantesEmpresas(Filtros filtros, int indice) throws FiltrosExcception;
	public int getPorEstatus(Filtros filtros) throws FiltrosExcception;
	public List<CCMXFinanzas> getCCMXFiannzas(Filtros filtros) throws ReporteException;
	public List<PYMESReporte> getPymesReporte(Filtros filtros) throws ReporteException;
	public List<TotalEmpresas> getEmpresasByConsultora(Filtros filtros) throws ReporteException;
	public List<FinanzasDiplomados> getFinanzasDiplomado() throws ReporteException;
	public List<PymesDiplomados> getPymesDiplomado(int idPyme, int idTracto, int Generacion) throws ReporteException;
	public List<IndicadoresPymes> getIndicadoresReporte(Filtros filtros) throws ReporteException;
	public String getIndicePeriodo(int periodo) throws ReporteException;
	public int getParticipante1()throws ReporteException;
	public int getParticipante1(int idPyme, int idTracto, int Generacion)throws ReporteException;
	public int getParticipante2()throws ReporteException;
	public int getParticipante3()throws ReporteException;
	public int getParticipante4()throws ReporteException;
	public int getTotalFacturas(String tipo,Filtros filtros) throws ReporteException;
	public int getEmpresasPagadas(boolean pagada,Filtros filtros) throws ReporteException;
	public float getCantidadPagadas(boolean pagada,Filtros filtros) throws ReporteException;
	public float getPromedioRadarAntes(Filtros filtros)
			throws PyMEsNoObtenidasException;
	public float getPromedioRadarDespues(Filtros filtros)
			throws PyMEsNoObtenidasException;
	public List<FiltrosGenerales> getMenuSesionInformativa() throws FiltrosExcception;
}
