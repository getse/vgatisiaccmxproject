package mx.com.vgati.ccmx.vinculacion.report.service.imp;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.consultoras.dto.Consultoras;
import mx.com.vgati.ccmx.vinculacion.report.dao.ReportDao;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXFinanzas;
import mx.com.vgati.ccmx.vinculacion.report.dto.CCMXParticipantes;
import mx.com.vgati.ccmx.vinculacion.report.dto.Filtros;
import mx.com.vgati.ccmx.vinculacion.report.dto.PYMESReporte;
import mx.com.vgati.ccmx.vinculacion.report.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.report.dto.TotalEmpresas;
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
	public List<CCMXParticipantes> getDatos() {
		try {
			return reportDao.getDatos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Consultoras> getConsultoras() {
		try {
			return reportDao.getConsultoras();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	public List<ServiciosConsultoria> getServiciosConsultoria() {
		try {
			return reportDao.getServiciosConsultoria();
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CCMXParticipantes> getCCMXServicios(Filtros filtros) {
		try {
			return reportDao.getCCMXServicios(	filtros);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return null;			
	}
	@Override
	public List<CCMXFinanzas> getCCMXFiannzas(Filtros filtros) {
		try {
			return reportDao.getCCMXFiannzas(filtros);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PYMESReporte> getPymesReporte(Filtros filtros) {
		try {
			return reportDao.getPymesReporte(filtros);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getTCultura(Filtros filtros) {
		try {
			return reportDao.getTCultura(filtros);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getTPlaneacion(Filtros filtros) {
		try {
			return reportDao.getTPlaneacion(filtros);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getTManufactura(Filtros filtros) {
		try {
			return reportDao.getTManufactura(filtros);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getTEstrategia(Filtros filtros) {
		try {
			return reportDao.getTEstrategia(filtros);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<TotalEmpresas> getEmpresasByConsultora(Filtros filtros) {
		try {
			return reportDao.getEmpresasByConsultora(filtros);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	
}
