/*
 * CoordinadorDiplomadosDao.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dao;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Encuestas;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Participantes;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Sesiones;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.exception.ParticipantesNoAlmacenadosException;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface CoordinadorDiplomadosDao {

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public List<Diplomados> getDiplomados(int id) throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<PyMEs> getPymes() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<PyMEs> getPymesNoDiplomado(int idDiplomado) throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public Mensaje inscribirPymes(int idDiplomado, String PyMes) throws DaoException;

	/**
	 * @param year
	 * @return
	 * @throws DaoException
	 */
	public int getGeneraciones(int year) throws DaoException;

	/**
	 * @param year
	 * @param generaciones
	 * @return
	 * @throws DaoException
	 */
	public List<List<Diplomados>> getMenuDiplomados(int year, int generaciones)
			throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<Integer> getMenuAnios() throws DaoException;

	/**
	 * @param idDiplomado
	 * @return
	 * @throws DaoException
	 */
	public List<Participantes> getParticipantes(int idDiplomado)
			throws DaoException;

	/**
	 * @param idDiplomado
	 * @param conf1
	 * @param conf2
	 * @param conf3
	 * @param conf4
	 * @return
	 * @throws DaoException
	 */
	public List<Participantes> getParticipantesPorSesion(int idDiplomado,
			boolean conf1, boolean conf2, boolean conf3, boolean conf4)
			throws DaoException;

	/**
	 * @param idParticipante
	 * @return
	 * @throws DaoException
	 */
	public Participantes getParticipante(int idParticipante)
			throws DaoException;

	/**
	 * @param idDiplomado
	 * @return
	 * @throws DaoException
	 */
	public List<Sesiones> getSesiones(int idDiplomado) throws DaoException;

	/**
	 * @param tema
	 * @param generacion
	 * @return
	 * @throws DaoException
	 */
	public Diplomados getDiplomado(String tema, int generacion)
			throws DaoException;

	/**
	 * @param sesiones
	 * @param numeroSesiones
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveSesiones(List<Sesiones> sesiones, int numeroSesiones)
			throws DaoException;

	/**
	 * @param participantes
	 * @return
	 * @throws DaoException
	 */
	public Mensaje savePagos(List<Participantes> participantes)
			throws DaoException;

	/**
	 * @param participantes
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveAsistencias(List<Participantes> participantes)
			throws DaoException;

	/**
	 * @param participantes
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveFacturas(List<Participantes> participantes)
			throws DaoException;

	/**
	 * @param idDiplomado
	 * @param idPyme
	 * @return
	 * @throws DaoException
	 */
	public List<Participantes> getParticipantes(int idDiplomado, int idPyme)
			throws DaoException;

	/**
	 * @param idDiplomado
	 * @param idPyme
	 * @return
	 * @throws DaoException
	 */
	public List<Participantes> getParticipantesDiploma(int idDiplomado,
			int idPyme) throws DaoException;

	/**
	 * @param idDiplomado
	 * @return
	 * @throws DaoException
	 */
	public List<Participantes> getInasistentes(int idDiplomado)
			throws DaoException;

	/**
	 * @param idAsistente
	 * @param idSesion
	 * @return
	 * @throws DaoException
	 */
	public Encuestas getEncuestas(int idAsistente, int idSesion)
			throws DaoException;

	/**
	 * @param encuestas
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveEncuestas(Encuestas encuestas) throws DaoException;

	/**
	 * @param p
	 * @param idDiplomado
	 * @return
	 * @throws DaoException
	 */
	public Mensaje saveInasistententes(Participantes p, int idDiplomado)
			throws DaoException;

	/**
	 * @param idDiplomado
	 * @return
	 * @throws DaoException
	 */
	public String getTema(int idDiplomado) throws DaoException;

	/**
	 * @param idPyme
	 * @return
	 * @throws DaoException
	 */
	public PyMEs getPyme(int idPyme) throws DaoException;

	/**
	 * @param idDiplomado
	 * @return
	 * @throws DaoException
	 */
	public List<PyMEs> getPymes(int idDiplomado) throws DaoException;

	/**
	 * @param idSesion
	 * @return
	 * @throws DaoException
	 */
	public List<Sesiones> getSesion(int idSesion) throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<PyMEs> getLiberarPymes() throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public boolean saveLiberarPymes(int id) throws DaoException;

}