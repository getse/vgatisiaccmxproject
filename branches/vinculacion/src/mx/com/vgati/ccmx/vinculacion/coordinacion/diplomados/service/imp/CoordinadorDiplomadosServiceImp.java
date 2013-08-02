/*
 * CoordinadorDiplomadosServiceImp.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.service.imp;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dao.CoordinadorDiplomadosDao;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Encuestas;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Participantes;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Sesiones;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.DiplomadosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.EncuestasNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.EncuestasNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.ParticipantesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.ParticipantesNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.SesionesNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.SesionesNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.service.CoordinadorDiplomadosService;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.AsistentesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;
import mx.com.vgati.framework.exception.ExceptionMessage;
import mx.com.vgati.framework.service.AbstractBaseService;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public class CoordinadorDiplomadosServiceImp extends AbstractBaseService
		implements CoordinadorDiplomadosService {

	private CoordinadorDiplomadosDao coordinadorDiplomadosDao;

	public void setCoordinadorDiplomadosDao(
			CoordinadorDiplomadosDao coordinadorDiplomadosDao) {
		this.coordinadorDiplomadosDao = coordinadorDiplomadosDao;
	}

	@Override
	public List<Diplomados> getDiplomados(int id)
			throws DiplomadosNoObtenidosException {
		return null;
	}
	@Override
	public List<PyMEs> getPymes() throws PyMEsNoObtenidasException{
		try{
			return coordinadorDiplomadosDao.getPymes();
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(
					new ExceptionMessage(
							"Ocurrio un error al consultar las Consultoras Pymes"),
					e);
		}
	}
	@Override
	public List<PyMEs> getPymes(int idDiplomado) throws PyMEsNoObtenidasException{
		try{
			return coordinadorDiplomadosDao.getPymes(idDiplomado);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(
					new ExceptionMessage(
							"Ocurrio un error al consultar las Consultoras Pymes"),
					e);
		}
	}
	@Override
	public int getGeneraciones(int year) throws DiplomadosNoObtenidosException {
		try {
			return coordinadorDiplomadosDao.getGeneraciones(year);
		} catch (DaoException e) {
			throw new DiplomadosNoObtenidosException(new ExceptionMessage(
					"Ocurrio un error al obtener las Generaciones de los catálogos."), e);
		}
	}
	@Override
	public List<List<Diplomados>> getMenuDiplomados(int year, int generaciones) throws DiplomadosNoObtenidosException{
		try{
			return coordinadorDiplomadosDao.getMenuDiplomados(year, generaciones);
		} catch (DaoException e) {
			throw new DiplomadosNoObtenidosException(
					new ExceptionMessage(
							"Ocurrio un error al consultar los diplomados"),
					e);
		}
		
	}
	
	@Override
	public List<Integer> getMenuAnios() throws DiplomadosNoObtenidosException{
		try{
			return coordinadorDiplomadosDao.getMenuAnios();
		} catch (DaoException e) {
			throw new DiplomadosNoObtenidosException(
					new ExceptionMessage(
							"Ocurrio un error al consultar años de diplomados"),
					e);
		}
		
	}
	@Override
	public Diplomados getDiplomado(String tema, int generacion)throws DiplomadosNoObtenidosException{
		try{
			return coordinadorDiplomadosDao.getDiplomado(tema,generacion);
		} catch (DaoException e) {
			throw new DiplomadosNoObtenidosException(
					new ExceptionMessage(
							"Ocurrio un error al consultar el diplomado"),
					e);
		}
		
	}
	@Override
	public List<Participantes> getParticipantes(int idDiplomado) throws ParticipantesNoObtenidoException{
		try{
			return coordinadorDiplomadosDao.getParticipantes(idDiplomado);
		} catch (DaoException e) {
			throw new ParticipantesNoObtenidoException(
					new ExceptionMessage(
							"Ocurrio un error al consultar los participantes"),
					e);
		}
		
	}
	@Override
	public List<Participantes> getParticipantesPorSesion(int idDiplomado,boolean conf1,boolean conf2,boolean conf3,boolean conf4) throws ParticipantesNoObtenidoException{
		try{
			return coordinadorDiplomadosDao.getParticipantesPorSesion(idDiplomado,conf1,conf2,conf3,conf4);
		} catch (DaoException e) {
			throw new ParticipantesNoObtenidoException(
					new ExceptionMessage(
							"Ocurrio un error al consultar los participantes"),
					e);
		}
		
	}
	@Override
	public Participantes getParticipante(int idParticipante) throws ParticipantesNoObtenidoException{
		try{
			return coordinadorDiplomadosDao.getParticipante(idParticipante);
		} catch (DaoException e) {
			throw new ParticipantesNoObtenidoException(
					new ExceptionMessage(
							"Ocurrio un error al consultar los participantes"),
					e);
		}
		
	}
	@Override
	public List<Participantes> getParticipantes(int idDiplomado,int idPyme) throws ParticipantesNoObtenidoException{
		try{
			return coordinadorDiplomadosDao.getParticipantes(idDiplomado,idPyme);
		} catch (DaoException e) {
			throw new ParticipantesNoObtenidoException(
					new ExceptionMessage(
							"Ocurrio un error al consultar los participantes"),
					e);
		}
		
	}
	@Override
	public List<Participantes> getParticipantesDiploma(int idDiplomado, int idPyme)throws ParticipantesNoObtenidoException{
		try{
			return coordinadorDiplomadosDao.getParticipantesDiploma(idDiplomado,idPyme);
		} catch (DaoException e) {
			throw new ParticipantesNoObtenidoException(
					new ExceptionMessage(
							"Ocurrio un error al consultar los participantes"),
					e);
		}
		
	}
	@Override
	public List<Sesiones> getSesiones(int idDiplomado) throws SesionesNoObtenidasException{
		try{
			return coordinadorDiplomadosDao.getSesiones(idDiplomado);
		} catch (DaoException e) {
			throw new SesionesNoObtenidasException(
					new ExceptionMessage(
							"Ocurrio un error al consultar las sesiones del diplomado"),
					e);
		}
		
	}
	@Override
	public List<Sesiones> getSesion(int idSesion)  throws SesionesNoObtenidasException{
		try{
			return coordinadorDiplomadosDao.getSesion(idSesion);
		} catch (DaoException e) {
			throw new SesionesNoObtenidasException(
					new ExceptionMessage(
							"Ocurrio un error al consultar las sesiones del diplomado"),
					e);
		}
		
	}
	public Mensaje saveSesiones(List<Sesiones> sesiones, int numeroSesiones) throws SesionesNoAlmacenadasException{
		try{
			return coordinadorDiplomadosDao.saveSesiones(sesiones, numeroSesiones);
		} catch (DaoException e) {
			throw new SesionesNoAlmacenadasException(
					new ExceptionMessage(
							"Ocurrio un error al guardar las sesiones."),
					e);
		}
	}
	public Mensaje savePagos(List<Participantes> participantes)
			throws ParticipantesNoAlmacenadosException{
		try{
		return coordinadorDiplomadosDao.savePagos(participantes);
		} catch (DaoException e) {
		throw new ParticipantesNoAlmacenadosException(
				new ExceptionMessage(
						"Ocurrio un error al guardar los datos del participante(confirmacion)."),
				e);
		}
		}
	public Mensaje saveAsistencias(List<Participantes> participantes)
			throws ParticipantesNoAlmacenadosException{
		try{
		return coordinadorDiplomadosDao.saveAsistencias(participantes);
		} catch (DaoException e) {
		throw new ParticipantesNoAlmacenadosException(
				new ExceptionMessage(
						"Ocurrio un error al guardar los datos del participante(asistencia)."),
				e);
		}
		}
	public Mensaje saveFacturas(List<Participantes> participantes)
				throws ParticipantesNoAlmacenadosException{
		try{
			return coordinadorDiplomadosDao.saveFacturas(participantes);
		} catch (DaoException e) {
			throw new ParticipantesNoAlmacenadosException(
					new ExceptionMessage(
							"Ocurrio un error al guardar los datos del participante(Facturación)."),
					e);
		}
	}
	public PyMEs getPyme(int idPyme) throws PyMEsNoObtenidasException{
		try{
			return coordinadorDiplomadosDao.getPyme(idPyme);
		} catch (DaoException e) {
			throw new PyMEsNoObtenidasException(
					new ExceptionMessage(
							"Ocurrio un error al traer la PYME."),
					e);
		}
	}
	public List<Participantes> getInasistentes(int idDiplomado) 
		throws ParticipantesNoObtenidoException{
		try{
		return coordinadorDiplomadosDao.getInasistentes(idDiplomado);
		} catch (DaoException e) {
		throw new ParticipantesNoObtenidoException(
				new ExceptionMessage(
						"Ocurrio un error al obtener las inasistencias."),
				e);
		}
	}
	public Encuestas getEncuestas(int idAsistente,int idSesion) 
	throws EncuestasNoObtenidasException{
		try{
			return coordinadorDiplomadosDao.getEncuestas(idAsistente,idSesion);
		} catch (DaoException e) {
		throw new EncuestasNoObtenidasException(
				new ExceptionMessage(
						"Ocurrio un error al obtener encuesta."),
				e);
		}
	}
	public Mensaje saveEncuestas(Encuestas encuestas) throws EncuestasNoAlmacenadasException{
		try{
			return coordinadorDiplomadosDao.saveEncuestas(encuestas);
		} catch (DaoException e) {
		throw new EncuestasNoAlmacenadasException(
				new ExceptionMessage(
						"Ocurrio un error al almacenar encuesta."),
				e);
		}
	}
	public Mensaje saveInasistententes(Participantes  p,int idDiplomado) throws AsistentesNoAlmacenadosException{
		try{
			return coordinadorDiplomadosDao.saveInasistententes(p,idDiplomado);
		} catch (DaoException e) {
		throw new AsistentesNoAlmacenadosException(
				new ExceptionMessage(
						"Ocurrio un error al almacenar la reasigacion del asitente."),
				e);
		}
	}
	public String getTema(int idDiplomado) throws DiplomadosNoObtenidosException{
		try{
			return coordinadorDiplomadosDao.getTema(idDiplomado);
		} catch (DaoException e) {
		throw new DiplomadosNoObtenidosException(
				new ExceptionMessage(
						"Ocurrio un error al obtener Tema de diplomado."),
				e);
		}
	}
}
