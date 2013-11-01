/*
 * CoordinadorDiplomadosService.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.service;

import java.util.List;

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
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.AsistentesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMENoAlmacenadaException;
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface CoordinadorDiplomadosService {

	/**
	 * @param id
	 * @return
	 * @throws DiplomadosNoObtenidosException
	 */
	public List<Diplomados> getDiplomados(int id)
			throws DiplomadosNoObtenidosException;

	/**
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getPymes() throws PyMEsNoObtenidasException;

	/**
	 * @param year
	 * @return
	 * @throws DiplomadosNoObtenidosException
	 */
	public int getGeneraciones(int year) throws DiplomadosNoObtenidosException;

	/**
	 * @param year
	 * @param generaciones
	 * @return
	 * @throws DiplomadosNoObtenidosException
	 */
	public List<List<Diplomados>> getMenuDiplomados(int year, int generaciones)
			throws DiplomadosNoObtenidosException;

	/**
	 * @return
	 * @throws DiplomadosNoObtenidosException
	 */
	public List<Integer> getMenuAnios() throws DiplomadosNoObtenidosException;

	/**
	 * @param tema
	 * @param generacion
	 * @return
	 * @throws DiplomadosNoObtenidosException
	 */
	public Diplomados getDiplomado(String tema, int generacion)
			throws DiplomadosNoObtenidosException;

	/**
	 * @param idParticipante
	 * @return
	 * @throws ParticipantesNoObtenidoException
	 */
	public Participantes getParticipante(int idParticipante)
			throws ParticipantesNoObtenidoException;

	/**
	 * @param idDiplomado
	 * @return
	 * @throws SesionesNoObtenidasException
	 */
	public List<Sesiones> getSesiones(int idDiplomado)
			throws SesionesNoObtenidasException;

	/**
	 * @param idDiplomado
	 * @return
	 * @throws ParticipantesNoObtenidoException
	 */
	public List<Participantes> getParticipantes(int idDiplomado)
			throws ParticipantesNoObtenidoException;

	/**
	 * @param idDiplomado
	 * @param conf1
	 * @param conf2
	 * @param conf3
	 * @param conf4
	 * @return
	 * @throws ParticipantesNoObtenidoException
	 */
	public List<Participantes> getParticipantesPorSesion(int idDiplomado,
			boolean conf1, boolean conf2, boolean conf3, boolean conf4)
			throws ParticipantesNoObtenidoException;

	/**
	 * @param sesiones
	 * @param numeroSesiones
	 * @return
	 * @throws SesionesNoAlmacenadasException
	 */
	public Mensaje saveSesiones(List<Sesiones> sesiones, int numeroSesiones)
			throws SesionesNoAlmacenadasException;

	/**
	 * @param participantes
	 * @return
	 * @throws ParticipantesNoAlmacenadosException
	 */
	public Mensaje savePagos(List<Participantes> participantes)
			throws ParticipantesNoAlmacenadosException;

	/**
	 * @param participantes
	 * @return
	 * @throws ParticipantesNoAlmacenadosException
	 */
	public Mensaje saveAsistencias(List<Participantes> participantes)
			throws ParticipantesNoAlmacenadosException;

	/**
	 * @param participantes
	 * @return
	 * @throws ParticipantesNoAlmacenadosException
	 */
	public Mensaje saveFacturas(List<Participantes> participantes)
			throws ParticipantesNoAlmacenadosException;

	/**
	 * @param idPyme
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public PyMEs getPyme(int idPyme) throws PyMEsNoObtenidasException;

	/**
	 * @param idDiplomado
	 * @param idPyme
	 * @return
	 * @throws ParticipantesNoObtenidoException
	 */
	public List<Participantes> getParticipantes(int idDiplomado, int idPyme)
			throws ParticipantesNoObtenidoException;

	/**
	 * @param idDiplomado
	 * @param idPyme
	 * @return
	 * @throws ParticipantesNoObtenidoException
	 */
	public List<Participantes> getParticipantesDiploma(int idDiplomado,
			int idPyme) throws ParticipantesNoObtenidoException;

	/**
	 * @param idDiplomado
	 * @return
	 * @throws ParticipantesNoObtenidoException
	 */
	public List<Participantes> getInasistentes(int idDiplomado)
			throws ParticipantesNoObtenidoException;

	/**
	 * @param idAsistente
	 * @param idSesion
	 * @return
	 * @throws EncuestasNoObtenidasException
	 */
	public Encuestas getEncuestas(int idAsistente, int idSesion)
			throws EncuestasNoObtenidasException;

	/**
	 * @param encuestas
	 * @return
	 * @throws EncuestasNoAlmacenadasException
	 */
	public Mensaje saveEncuestas(Encuestas encuestas)
			throws EncuestasNoAlmacenadasException;

	/**
	 * @param p
	 * @param idDiplomado
	 * @return
	 * @throws AsistentesNoAlmacenadosException
	 */
	public Mensaje saveInasistententes(Participantes p, int idDiplomado)
			throws AsistentesNoAlmacenadosException;

	/**
	 * @param idDiplomado
	 * @return
	 * @throws DiplomadosNoObtenidosException
	 */
	public String getTema(int idDiplomado)
			throws DiplomadosNoObtenidosException;

	/**
	 * @param idDiplomado
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getPymes(int idDiplomado)
			throws PyMEsNoObtenidasException;

	/**
	 * @param idSesion
	 * @return
	 * @throws SesionesNoObtenidasException
	 */
	public List<Sesiones> getSesion(int idSesion)
			throws SesionesNoObtenidasException;

	/**
	 * @return
	 * @throws PyMEsNoObtenidasException
	 */
	public List<PyMEs> getLiberarPymes() throws PyMEsNoObtenidasException;

	/**
	 * @param id
	 * @return
	 * @throws PyMENoAlmacenadaException
	 */
	public boolean saveLiberarPymes(int id) throws PyMENoAlmacenadaException;

}