package mx.com.vgati.ccmx.vinculacion.report.dao;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXFinanzas;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXParticipantes;
import mx.com.vgati.ccmx.vinculacion.report.dto.Filtros;
import mx.com.vgati.ccmx.vinculacion.report.dto.FiltrosGenerales;
import mx.com.vgati.ccmx.vinculacion.report.dto.IndicadoresPymes;
import mx.com.vgati.ccmx.vinculacion.report.dto.PYMESReporte;
import mx.com.vgati.ccmx.vinculacion.report.dto.FinanzasDiplomados;
import mx.com.vgati.ccmx.vinculacion.report.dto.PymesDiplomados;
import mx.com.vgati.ccmx.vinculacion.report.dto.TotalEmpresas;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dao.exception.DaoException;

public interface ReportDao {
	public List<CCMXParticipantes> getDatos();
	public List<Consultoras> getConsultoras() throws DaoException;
	public List<Tractoras> getTractoras() throws DaoException;
	public List<ServiciosConsultoria> getServiciosConsultoria() throws DaoException;
	public List<CCMXParticipantes> getCCMXServicios(Filtros filtros)throws DaoException;
	public int getCCMXServiciosTotal(Filtros filtros)throws DaoException;
	public int getParticipantes(Filtros filtros,int indice)throws DaoException;
	public int getParticipantesEmpresas(Filtros filtros,int indice)throws DaoException;
	public int getPorEstatus(Filtros filtros)throws DaoException;
	public List<TotalEmpresas> getEmpresasByConsultora(Filtros filtros)throws DaoException;
	public List<FinanzasDiplomados> getFinanzasDiplomado()throws DaoException;
	public List<PymesDiplomados> getPymesDiplomado(int idPyme, int idTracto, int generacion)throws DaoException;
	public List<CCMXFinanzas> getCCMXFiannzas(Filtros filtros) throws DaoException;
	public List<PYMESReporte> getPymesReporte(Filtros filtros) throws DaoException;
	List<Consultoras> getConsultores(int idConsultoraPadre)
			throws DaoException;
	public List<IndicadoresPymes> getIndicadoresReporte(Filtros filtros) throws DaoException;
	public String getIndicePeriodo(int periodo) throws DaoException;
	public List<FiltrosGenerales> getMenuFacturaAnticipo() throws DaoException;
	public List<FiltrosGenerales> getMenuFacturaFiniquito() throws DaoException;
	public List<FiltrosGenerales> getMenuFacturaAnticipoFiniquito() throws DaoException;
	public List<FiltrosGenerales> getMenuCedulas() throws DaoException;
	public List<FiltrosGenerales> getMenuEstatus() throws DaoException;
	public int getParticipante1() throws DaoException;
	public int getParticipante1(int idPyme, int idTracto, int Generacion) throws DaoException;
	public int getParticipante2() throws DaoException;
	public int getParticipante3() throws DaoException;
	public int getParticipante4() throws DaoException;
	public int getTotalFacturas(String tipo, Filtros filtros) throws DaoException;
	public int getEmpresasPagadas(boolean pagada,Filtros filtros) throws DaoException;
	public float getCantidadesPagadas(boolean pagada,Filtros filtros) throws DaoException;
	public float getPromedioRadarAntes(Filtros filtros) throws DaoException;
	public float getPromedioRadarDespues(Filtros filtros) throws DaoException;
}
