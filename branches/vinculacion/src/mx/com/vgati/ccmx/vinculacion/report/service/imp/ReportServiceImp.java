package mx.com.vgati.ccmx.vinculacion.report.service.imp;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.ConsultorasNoObtenidasExceprion;
import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.consultoras.exception.ConsultoraNoObtenidaException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.report.dao.ReportDao;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXFinanzas;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXParticipantes;
import mx.com.vgati.ccmx.vinculacion.report.dto.Filtros;
import mx.com.vgati.ccmx.vinculacion.report.dto.FiltrosGenerales;
import mx.com.vgati.ccmx.vinculacion.report.dto.IndicadoresPymes;
import mx.com.vgati.ccmx.vinculacion.report.dto.PYMESReporte;

import mx.com.vgati.ccmx.vinculacion.report.dto.TotalEmpresas;
import mx.com.vgati.ccmx.vinculacion.report.exception.FiltrosExcception;
import mx.com.vgati.ccmx.vinculacion.report.exception.ReporteException;
import mx.com.vgati.ccmx.vinculacion.report.service.ReportService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.exception.ExceptionMessage;

public class ReportServiceImp implements ReportService {
	private ReportDao reportDao;	

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	@Override
	public List<CCMXParticipantes> getDatos() throws PyMEsNoObtenidasException {
		try {
			return reportDao.getDatos();
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(
					new ExceptionMessage("Ocurrio un error obteniendo lista de Reporte CCMX Participantes"), e);
		}
	}

	@Override
	public List<Consultoras> getConsultoras() throws ConsultoraNoObtenidaException{
		try {
			return reportDao.getConsultoras();
		} catch (DaoException e) {
			new  ConsultoraNoObtenidaException(new  
					ExceptionMessage("Ocurrio un error al traer las consultoras."),e);
		}
		return null;
	}
	@Override
	public List<Consultoras> getConsultores(int idConsultoraPadre) throws ConsultorasNoObtenidasExceprion {
		try {
			return reportDao.getConsultores(idConsultoraPadre);
		} catch (Exception e) {
				throw new ConsultorasNoObtenidasExceprion(
						new ExceptionMessage("Ocurrio un error obteniendo lista de filtros de Factura Anticipo."), e);
		}
	}
	@Override
	public List<Tractoras> getTractoras() throws TractorasNoObtenidasException {
		try {
			return reportDao.getTractoras();
		} catch (DaoException e) {
			throw new TractorasNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al consultar las Tractoras."), e);
		}
	}

	@Override
	public List<ServiciosConsultoria> getServiciosConsultoria() throws ConsultoraNoObtenidaException {
		try {
			return reportDao.getServiciosConsultoria();
		}catch (DaoException e) {
			throw new ConsultoraNoObtenidaException(
					new ExceptionMessage("Ocurrio un error obteniendo lista de Reporte Servicios Consultoria"), e);
		}
	}

	@Override
	public List<CCMXParticipantes> getCCMXServicios(Filtros filtros) throws PyMEsNoObtenidasException {
		try {
			return reportDao.getCCMXServicios(filtros);
		}catch (DaoException e) {
			throw new PyMEsNoObtenidasException(
					new ExceptionMessage("Ocurrio un error obteniendo lista de Reporte CCMX Servicios."), e);
		}		
	}
	@Override
	public int getCCMXServiciosTotal(Filtros filtros) throws PyMEsNoObtenidasException {
		try {
			return reportDao.getCCMXServiciosTotal(filtros);
		}catch (DaoException e) {
			throw new PyMEsNoObtenidasException(
					new ExceptionMessage("Ocurrio un error obteniendo total de Reporte CCMX Servicios."), e);
		}		
	}
	@Override
	public List<CCMXFinanzas> getCCMXFiannzas(Filtros filtros) throws ReporteException {
		try {
			return reportDao.getCCMXFiannzas(filtros);
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo lista de Reporte CCMX Finanzas."), e);
		}
	}

	@Override
	public List<PYMESReporte> getPymesReporte(Filtros filtros) throws ReporteException  {
		try {
			return reportDao.getPymesReporte(filtros);
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo lista de reporte de PYMES."), e);
		}
	}

	@Override
	public int getParticipantes(Filtros filtros,int indice) throws FiltrosExcception {
		try {
			return reportDao.getParticipantes(filtros,indice);
		}catch (DaoException e) {
			throw new FiltrosExcception(
					new ExceptionMessage("Ocurrio un error obteniendo Totales de cultura"), e);
		}
	}

	@Override
	public int getParticipantesEmpresas(Filtros filtros,int indice) throws FiltrosExcception {
		try {
			return reportDao.getParticipantesEmpresas(filtros,indice);
		}catch (DaoException e) {
			throw new FiltrosExcception(
					new ExceptionMessage("Ocurrio un error obteniendo Totales de cultura"), e);
		}
	}

	@Override
	public int getPorEstatus(Filtros filtros) throws FiltrosExcception {
		try {
			return reportDao.getPorEstatus(filtros);
		}catch (DaoException e) {
			throw new FiltrosExcception(
					new ExceptionMessage("Ocurrio un error obteniendo Totales de cultura"), e);
		}
	}
	@Override
	public List<TotalEmpresas> getEmpresasByConsultora(Filtros filtros) throws ReporteException {
		try {
			return reportDao.getEmpresasByConsultora(filtros);
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo Totales de Empresas por consultora."), e);
		}
	}

	@Override
	public List<IndicadoresPymes> getIndicadoresReporte(Filtros filtros) throws ReporteException {
		try {
			return reportDao.getIndicadoresReporte(filtros);
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo lista de reporte de Indicadores."), e);
		}
	}

	@Override
	public List<FiltrosGenerales> getMenuFacturaAnticipo() throws FiltrosExcception {
		try {
			return reportDao.getMenuFacturaAnticipo();
		} catch (Exception e) {
			throw new FiltrosExcception(
					new ExceptionMessage("Ocurrio un error obteniendo lista de filtros de Factura Anticipo."), e);
		}
	}
	
	@Override
	public List<FiltrosGenerales> getMenuFacturaFiniquito() throws FiltrosExcception {
		try {
			return reportDao.getMenuFacturaFiniquito();
		} catch (Exception e) {
			throw new FiltrosExcception(
					new ExceptionMessage("Ocurrio un error obteniendo lista de filtros de Factura Anticipo."), e);
		}
	}

	@Override
	public List<FiltrosGenerales> getMenuFacturaAnticipoFiniquito() throws FiltrosExcception {
		try {
			return reportDao.getMenuFacturaAnticipoFiniquito();
		} catch (Exception e) {
			throw new FiltrosExcception(
					new ExceptionMessage("Ocurrio un error obteniendo lista de filtros de Factura Anticipo y Finiquito."), e);
		}
	}

	@Override
	public List<FiltrosGenerales> getMenuCedulas() throws FiltrosExcception {
		try {
			return reportDao.getMenuCedulas();
		} catch (Exception e) {
			throw new FiltrosExcception(
					new ExceptionMessage("Ocurrio un error obteniendo lista de filtros de Cedulas."), e);
		}
	}

	@Override
	public List<FiltrosGenerales> getMenuEstatus() throws FiltrosExcception {
		try {
			return reportDao.getMenuEstatus();
		} catch (Exception e) {
			throw new FiltrosExcception(
					new ExceptionMessage("Ocurrio un error obteniendo lista de filtros de Estatus."), e);
		}
	}

	
}
