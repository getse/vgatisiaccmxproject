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
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Participantes;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Sesiones;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.DiplomadosNoObtenidosException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.ParticipantesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.ParticipantesNoObtenidoException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.SesionesNoAlmacenadasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.SesionesNoObtenidasException;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.service.CoordinadorDiplomadosService;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
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
	public List<Diplomados> getMenuDiplomados() throws DiplomadosNoObtenidosException{
		try{
			return coordinadorDiplomadosDao.getMenuDiplomados();
		} catch (DaoException e) {
			throw new DiplomadosNoObtenidosException(
					new ExceptionMessage(
							"Ocurrio un error al consultar los diplomados"),
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
	public Mensaje saveParticipantes(List<Participantes> participantes, int idPyme, int idDiplomado)
				throws ParticipantesNoAlmacenadosException{
		try{
			return coordinadorDiplomadosDao.saveParticipantes(participantes, idPyme, idDiplomado);
		} catch (DaoException e) {
			throw new ParticipantesNoAlmacenadosException(
					new ExceptionMessage(
							"Ocurrio un error al guardar los datos del participante."),
					e);
		}
	}
}
