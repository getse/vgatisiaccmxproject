/*
 * CoordinadorDiplomadosDao.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
 *
 */
package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dao;

import java.util.List;

import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Diplomados;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Participantes;
import mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto.Sesiones;
import mx.com.vgati.ccmx.vinculacion.pymes.dto.PyMEs;
import mx.com.vgati.framework.dao.exception.DaoException;
import mx.com.vgati.framework.dao.exception.JdbcDaoException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface CoordinadorDiplomadosDao {

	public List<Diplomados> getDiplomados(int id) throws DaoException;
	public List<PyMEs> getPymes() throws DaoException;
	public List<Diplomados> getMenuDiplomados() throws DaoException;
	public List<Participantes> getParticipantes(int idDiplomado) throws DaoException;
	public List<Sesiones> getSesiones(int idDiplomado) throws DaoException;
	public Diplomados getDiplomado(String tema, int generacion) throws DaoException;
	public Mensaje saveSesiones(List<Sesiones> sesiones, int numeroSesiones) throws DaoException;
	public Mensaje saveParticipantes(List<Participantes> participantes, int idPyme, int idDiplomado)
			throws DaoException;
	public List<Participantes> getParticipantes(int idDiplomado, int idPyme)
			throws DaoException;
	public List<Participantes> getInasistentes() throws JdbcDaoException;
}