/*
 * ReportServiceImp.java        08/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
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
import mx.com.vgati.ccmx.vinculacion.report.dto.IndicadoresPyMEs;
import mx.com.vgati.ccmx.vinculacion.report.dto.PyMEsReporte;
import mx.com.vgati.ccmx.vinculacion.report.dto.FinanzasDiplomados;
import mx.com.vgati.ccmx.vinculacion.report.dto.PyMEsDiplomados;

import mx.com.vgati.ccmx.vinculacion.report.dto.TotalEmpresas;
import mx.com.vgati.ccmx.vinculacion.report.exception.FiltrosExcception;
import mx.com.vgati.ccmx.vinculacion.report.exception.ReporteException;
import mx.com.vgati.ccmx.vinculacion.report.service.ReportService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.exception.ExceptionMessage;

/**
 * 
 * 
 * @author Sergio Olivos
 * 
 */
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
	public float getPromedioRadarAntes(Filtros filtros) throws PyMEsNoObtenidasException {
		try {
			return reportDao.getPromedioRadarAntes(filtros);
		}catch (DaoException e) {
			throw new PyMEsNoObtenidasException(
					new ExceptionMessage("Ocurrio un error obteniendo total de Reporte CCMX Servicios."), e);
		}		
	}
	@Override
	public float getPromedioRadarDespues(Filtros filtros) throws PyMEsNoObtenidasException {
		try {
			return reportDao.getPromedioRadarDespues(filtros);
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
	public List<PyMEsReporte> getPymesReporte(Filtros filtros) throws ReporteException  {
		try {
			return reportDao.getPymesReporte(filtros);
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo lista de reporte de PyMEs."), e);
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
	public List<IndicadoresPyMEs> getIndicadoresReporte(Filtros filtros) throws ReporteException {
		try {
			return reportDao.getIndicadoresReporte(filtros);
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo lista de reporte de Indicadores."), e);
		}
	}
	@Override
	public String getIndicePeriodo(int periodo) throws ReporteException {
		try {
			return reportDao.getIndicePeriodo(periodo);
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo lista de reporte de Indicadores."), e);
		}
	}

	@Override
	public  List<FinanzasDiplomados> getFinanzasDiplomado() throws ReporteException {
		try {
			return reportDao.getFinanzasDiplomado();
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo reporte Financiero pymes en diplomados ."), e);
		}
	}

	@Override
	public  List<PyMEsDiplomados> getPymesDiplomado(int idPyme, int idTracto, int generacion) throws ReporteException {
		try {
			return reportDao.getPymesDiplomado(idPyme, idTracto, generacion);
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo reporte de pymes en diplomados ."), e);
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
	public List<FiltrosGenerales> getMenuSesionInformativa() throws FiltrosExcception {
		try {
			return reportDao.getMenuSesionInformativa();
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

	@Override
	public int getParticipante1() throws ReporteException {
		try {
			return reportDao.getParticipante1();
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo participantes 1 ."), e);
		}
	}

	@Override
	public int getParticipante1(int idPyme, int idTracto, int generacion) throws ReporteException {
		try {
			return reportDao.getParticipante1(idPyme,idTracto,generacion);
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo participantes 1 ."), e);
		}
	}

	@Override
	public int getParticipante2() throws ReporteException {
		try {
			return reportDao.getParticipante2();
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo participantes 1 ."), e);
		}
	}

	@Override
	public int getParticipante3() throws ReporteException {
		try {
			return reportDao.getParticipante3();
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo participantes 1 ."), e);
		}
	}

	@Override
	public int getParticipante4() throws ReporteException {
		try {
			return reportDao.getParticipante4();
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo participantes 1 ."), e);
		}
	}

	@Override
	public int getTotalFacturas(String tipo,Filtros filtros) throws ReporteException {
		try {
			return reportDao.getTotalFacturas(tipo,filtros);
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo facturas en Reporte CCMX Finanzas."), e);
		}
	}
	public int getEmpresasPagadas(boolean pagada,Filtros filtros) throws ReporteException {
		try {
			return reportDao.getEmpresasPagadas(pagada,filtros);
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo empresas pagadas en Reporte CCMX Finanzas."), e);
		}
	}
	public float getCantidadPagadas(boolean pagada,Filtros filtros) throws ReporteException {
		try {
			return reportDao.getCantidadesPagadas(pagada , filtros);
		}catch (DaoException e) {
			throw new ReporteException(
					new ExceptionMessage("Ocurrio un error obteniendo catidad pagadas en Reporte CCMX Finanzas."), e);
		}
	}
}
