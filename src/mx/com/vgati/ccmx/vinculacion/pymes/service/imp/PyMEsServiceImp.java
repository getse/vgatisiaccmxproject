/*
 * PyMEsServiceImp.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.service.imp;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.ccmx.exception.TractorasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.consultorias.exception.ConsultoriasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Sesiones;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.ParticipantesNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.SesionesNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.dto.Documento;
import mx.com.vgati.ccmx.vinculacion.dto.Requerimientos;
import mx.com.vgati.ccmx.vinculacion.dto.Respuesta;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoAlmacenadoException;
import mx.com.vgati.ccmx.vinculacion.publico.exception.DocumentoNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.pymes.dao.PyMEsDao;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Asistentes;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.Indicadores;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosConsultoria;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.ServiciosDiplomado;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.AsistentesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.ConsultoriasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.DiplomadosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.DiplomadosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.IndicadoresNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.IndicadoresNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMENoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.RespuestaNoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.service.PyMEsService;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Domicilios;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.RelPyMEsTractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.DomiciliosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.tractoras.exception.RequerimientosNoObtenidosException;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.exception.ExceptionMessage;
import mx.com.vgati.framework.service.AbstractBaseService;

public class PyMEsServiceImp extends AbstractBaseService implements
		PyMEsService {

	private PyMEsDao pyMEsDao;

	public void setPyMEsDao(PyMEsDao pyMEsDao) {
		this.pyMEsDao = pyMEsDao;
	}

	@Override
	public PyMEs getPyME(int id) throws PyMEsNoObtenidasException {
		try {
			return pyMEsDao.getPyMEs(id);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al obtener la PyME."), e);
		}
	}

	@Override
	public String getIdDomicilio(int id) throws DomiciliosNoObtenidosException {
		try {
			return pyMEsDao.getIdDomicilio(id);
		} catch (DaoException e) {
			throw new DomiciliosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al obtener el id domicilio."), e);
		}
	}

	@Override
	public Domicilios getDomicilio(int id)
			throws DomiciliosNoObtenidosException {
		try {
			return pyMEsDao.getDomicilios(id);
		} catch (DaoException e) {
			throw new DomiciliosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al obtener el domicilio."), e);
		}
	}

	@Override
	public EstadosVenta getEstadoVenta(int id) throws PyMEsNoObtenidasException {
		try {
			return pyMEsDao.getEstadosVentas(id);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al obtener el estado de venta."), e);
		}
	}

	@Override
	public String getIdIndicador(int id) throws IndicadoresNoObtenidosException {
		try {
			return pyMEsDao.getIdIndicadores(id);
		} catch (DaoException e) {
			throw new IndicadoresNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al obtener el id del Indicador."), e);
		}
	}

	@Override
	public Indicadores getIndicador(int id)
			throws IndicadoresNoObtenidosException {
		try {
			return pyMEsDao.getIndicadores(id);
		} catch (DaoException e) {
			throw new IndicadoresNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al obtener el indicador."), e);
		}
	}

	@Override
	public Mensaje updatePyME(PyMEs pyMEs, EstadosVenta estadosVenta)
			throws PyMENoAlmacenadaException {
		try {
			return pyMEsDao.updatePyMEs(pyMEs, estadosVenta);
		} catch (DaoException e) {
			throw new PyMENoAlmacenadaException(new ExceptionMessage(
					"Ocurrio un error al obtener la PyME."), e);
		}
	}

	@Override
	public Mensaje saveDomicilio(Domicilios domicilios)
			throws DomiciliosNoAlmacenadosException {
		try {
			return pyMEsDao.saveDomicilios(domicilios);
		} catch (DaoException e) {
			throw new DomiciliosNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al guradar el domicilio."), e);
		}
	}

	@Override
	public Mensaje saveRelDomicilio(Domicilios domicilios, PyMEs pyMEs)
			throws DomiciliosNoAlmacenadosException {
		try {
			return pyMEsDao.saveRelDomicilios(domicilios, pyMEs);
		} catch (DaoException e) {
			throw new DomiciliosNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al guradar la relacion Domicilio."), e);
		}
	}

	@Override
	public Mensaje updateDomicilio(Domicilios domicilios)
			throws DomiciliosNoAlmacenadosException {
		try {
			return pyMEsDao.updateDomicilios(domicilios);
		} catch (DaoException e) {
			throw new DomiciliosNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al actualizar los datos del Domicilio."),
					e);
		}
	}

	@Override
	public Mensaje saveIndicador(Indicadores indicadores)
			throws IndicadoresNoAlmacenadosException {
		try {
			return pyMEsDao.saveIndicadores(indicadores);
		} catch (DaoException e) {
			throw new IndicadoresNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al guradar el indicador."), e);
		}
	}

	@Override
	public Mensaje updateIndicador(Indicadores indicadores)
			throws IndicadoresNoAlmacenadosException {
		try {
			return pyMEsDao.updateIndicadores(indicadores);
		} catch (DaoException e) {
			throw new IndicadoresNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al actualizar los datos del indicador."),
					e);
		}
	}

	@Override
	public Mensaje saveRespuesta(Respuesta respuesta)
			throws RespuestaNoAlmacenadaException {
		try {
			return pyMEsDao.saveRespuestas(respuesta);
		} catch (DaoException e) {
			throw new RespuestaNoAlmacenadaException(
					new ExceptionMessage(
							"Ocurrio un error al guardar la respuesta al requerimiento."),
					e);
		}
	}

	@Override
	public Mensaje saveServDiplomado(ServiciosDiplomado serviciosDiplomado)
			throws DiplomadosNoAlmacenadosException {
		try {
			return pyMEsDao.saveServDiplomados(serviciosDiplomado);
		} catch (DaoException e) {
			throw new DiplomadosNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al guardar el servicio del diplomado."), e);
		}
	}

	@Override
	public Mensaje saveAsistente(Asistentes asistentes)
			throws AsistentesNoAlmacenadosException {
		try {
			return pyMEsDao.saveAsistentes(asistentes);
		} catch (DaoException e) {
			throw new AsistentesNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al guardar el asistente."), e);
		}
	}

	@Override
	public Mensaje saveConsultoria(ServiciosConsultoria serviciosConsultoria)
			throws ConsultoriasNoAlmacenadasException {
		try {
			return pyMEsDao.saveConsultorias(serviciosConsultoria);
		} catch (DaoException e) {
			throw new ConsultoriasNoAlmacenadasException(new ExceptionMessage(
					"Ocurrio un error al guardar el servicio."), e);
		}
	}

	@Override
	public List<PyMEs> getBusquedaPyME(String busqueda, String estado,
			String cveScian) throws PyMEsNoObtenidasException {
		try {
			return pyMEsDao.getBusquedaPyMEs(busqueda, estado, cveScian);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al consultar los datos de las PyMEs."), e);
		}
	}

	@Override
	public List<Requerimientos> getRequerimiento(String busqueda,
			String tractoraReq, java.sql.Date fechaDesde,
			java.sql.Date fechaHasta, int idUsuario)
			throws RequerimientosNoObtenidosException {
		try {
			return pyMEsDao.getRequerimientos(busqueda, tractoraReq,
					fechaDesde, fechaHasta, idUsuario);
		} catch (DaoException e) {
			throw new RequerimientosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al consultar los Requerimientos."), e);
		}
	}

	@Override
	public List<Requerimientos> getFecha()
			throws RequerimientosNoObtenidosException {
		try {
			return pyMEsDao.getFechas();
		} catch (DaoException e) {
			throw new RequerimientosNoObtenidosException(
					new ExceptionMessage(
							"Ocurrio un error al consultar las fechas del Requerimiento."),
					e);
		}
	}

	@Override
	public List<Tractoras> getTractora() throws TractorasNoObtenidasException {
		try {
			return pyMEsDao.getTractoras();
		} catch (DaoException e) {
			throw new TractorasNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al consultar las tractoras."), e);
		}
	}

	@Override
	public Documento getArchivo(int id) throws DocumentoNoObtenidoException {
		try {
			return pyMEsDao.getArchivo(id);
		} catch (DaoException e) {
			throw new DocumentoNoObtenidoException(new ExceptionMessage(
					"Ocurrio un error al obtener el Documento."), e);
		}
	}

	@Override
	public RelPyMEsTractoras getCalificacion(int id)
			throws PyMEsNoObtenidasException {
		try {
			return pyMEsDao.getCalificaciones(id);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al obtener la calificacion."), e);
		}
	}

	@Override
	public Indicadores getIndicadorMes(int id)
			throws IndicadoresNoObtenidosException {
		try {
			return pyMEsDao.getIndicadoresMes(id);
		} catch (DaoException e) {
			throw new IndicadoresNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al obtener los Indicadores."), e);
		}
	}

	@Override
	public ServiciosConsultoria getServConsultorias(int id)
			throws ConsultoriasNoObtenidasException {
		try {
			return pyMEsDao.getConsultorias(id);
		} catch (DaoException e) {
			throw new ConsultoriasNoObtenidasException(
					new ExceptionMessage(
							"Ocurrio un error al obtener los datos del servicio de consultorias."),
					e);
		}
	}

	@Override
	public String getNombreCveScian(int cveCat)
			throws PyMEsNoObtenidasException {
		try {
			return pyMEsDao.getNombresCveScian(cveCat);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al obtener el nombre del Catálogo."), e);
		}
	}

	@Override
	public int getGeneracion() throws DiplomadosNoObtenidosException {
		try {
			return pyMEsDao.getGeneraciones();
		} catch (DaoException e) {
			throw new DiplomadosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al obtener las Generaciones de los catálogos."), e);
		}
	}
	
	@Override
	public List<Diplomados> getTemaDiplomado()
			throws DiplomadosNoObtenidosException {
		try {
			return pyMEsDao.getTemaDiplomados();
		} catch (DaoException e) {
			throw new DiplomadosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al consultar los diplomados."), e);
		}
	}
	
	@Override
	public Diplomados getDiplomado(int generacion, String tema)
			throws DiplomadosNoObtenidosException {
		try {
			return pyMEsDao.getDiplomados(generacion, tema);
		} catch (DaoException e) {
			throw new DiplomadosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al consultar los diplomados."), e);
		}
	}

	@Override
	public Object getServicioConsultoria(int id, String servicio)
			throws ConsultoriasNoObtenidasException {
		try {
			return pyMEsDao.getServicioConsultorias(id, servicio);
		} catch (DaoException e) {
			throw new ConsultoriasNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al consultar el servicio de Consultoría."), e);
		}
	}

	@Override
	public List<Sesiones> getSesion(int id) throws SesionesNoObtenidasException {
		try {
			return pyMEsDao.getSesiones(id);
		} catch (DaoException e) {
			throw new SesionesNoObtenidasException(new ExceptionMessage(
					"Ocurrio un error al consultar las sesiones del diplomado."), e);
		}
	}

	@Override
	public ServiciosDiplomado getServicioDiplomado(int idDiplomado,
			int idUsuario) throws DiplomadosNoObtenidosException {
		try {
			return pyMEsDao.getServicioDiplomados(idDiplomado, idUsuario);
		} catch (DaoException e) {
			throw new DiplomadosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al consultar el servicio de diplomado."), e);
		}
	}

	@Override
	public List<Asistentes> getAsistentes(int id)
			throws ParticipantesNoObtenidoException {
		try {
			return pyMEsDao.getAsistentes(id);
		} catch (DaoException e) {
			throw new ParticipantesNoObtenidoException(new ExceptionMessage(
					"Ocurrio un error al consultar los asistentes inscritos en el servicio de diplomado."), e);
		}
	}

	@Override
	public ServiciosDiplomado getIdServicioDiplomado()
			throws DiplomadosNoObtenidosException {
		try {
			return pyMEsDao.getIdServicioDiplomado();
		} catch (DaoException e) {
			throw new DiplomadosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al consultar el servicio de diplomado."), e);
		}
	}

	@Override
	public Mensaje saveAsistentes(Asistentes asistentes)
			throws AsistentesNoAlmacenadosException {
		try {
			return pyMEsDao.saveAsistentes(asistentes);
		} catch (DaoException e) {
			throw new AsistentesNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al guardar los asistentes en el servicio de diplomado."), e);
		}
	}

	@Override
	public Mensaje updateAsistentes(Asistentes asistentes)
			throws AsistentesNoAlmacenadosException {
		try {
			return pyMEsDao.updateAsistentes(asistentes);
		} catch (DaoException e) {
			throw new AsistentesNoAlmacenadosException(new ExceptionMessage(
					"Ocurrio un error al actualizar los datos de los asistentes en el servicio de diplomado."), e);
		}
	}

	@Override
	public List<Documento> getArchivosDiplomado(int idServicio)
			throws DocumentoNoObtenidoException {
		try {
			return pyMEsDao.getArchivosDiplomados(idServicio);
		} catch (DaoException e) {
			throw new DocumentoNoObtenidoException(new ExceptionMessage(
					"Ocurrio un error al consultar los documentos de pago del servicio de diplomado."), e);
		}
	}

	@Override
	public Mensaje saveArchivoServicio(Documento d)
			throws DocumentoNoAlmacenadoException {
		try {
			return pyMEsDao.insertDocServicio(d);
		} catch (DaoException e) {
			throw new DocumentoNoAlmacenadoException(new ExceptionMessage(
					"Ocurrio un error al almacenar el documento de pago del servicio de diplomado."), e);
		}
	}

	@Override
	public Mensaje deleteArchivoPago(String idArchivos)
			throws DocumentoNoAlmacenadoException {
		try {
			return pyMEsDao.deleteArchivoPagos(idArchivos);
		} catch (DaoException e) {
			throw new DocumentoNoAlmacenadoException(new ExceptionMessage(
					"Ocurrio un error al almacenar el documento de pago del servicio de diplomado."), e);
		}
	}

	@Override
	public Documento getRfc(int id) throws DocumentoNoObtenidoException {
		try {
			return pyMEsDao.getRfc(id);
		} catch (DaoException e) {
			throw new DocumentoNoObtenidoException(new ExceptionMessage(
					"Ocurrio un error al consultar el RFC de la PyME."), e);
		}
	}

	@Override
	public Mensaje saveRFCPyME(Documento documento)
			throws DocumentoNoAlmacenadoException {
		try {
			return pyMEsDao.saveRFCPyMEs(documento);
		} catch (DaoException e) {
			throw new DocumentoNoAlmacenadoException(new ExceptionMessage(
					"Ocurrio un error al almacenar el documento del RFC de la PyME."), e);
		}
	}
}