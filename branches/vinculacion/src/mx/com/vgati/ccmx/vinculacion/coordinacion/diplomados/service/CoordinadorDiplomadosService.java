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
import mx.com.vgati.ccmx.vinculacion.pymes.exception.PyMEsNoObtenidasException;
import mx.com.vgati.framework.dto.Mensaje;

/**
 * 
 * 
 * @author Getsemani Correa
 * 
 */
public interface CoordinadorDiplomadosService {

	public List<Diplomados> getDiplomados(int id)
			throws DiplomadosNoObtenidosException;
	public List<PyMEs> getPymes() throws PyMEsNoObtenidasException;
	public List<List<Diplomados>> getMenuDiplomados(int year) throws DiplomadosNoObtenidosException;
	public List<Integer> getMenuAnios() throws DiplomadosNoObtenidosException;
	public Diplomados getDiplomado(String tema, int generacion)
			throws DiplomadosNoObtenidosException;
	public List<Sesiones> getSesiones(int idDiplomado) 
			throws SesionesNoObtenidasException;
	public List<Participantes> getParticipantes(int idDiplomado)
			throws ParticipantesNoObtenidoException;
	public Mensaje saveSesiones(List<Sesiones> sesiones, int numeroSesiones)
			throws SesionesNoAlmacenadasException;
	public Mensaje saveParticipantes(List<Participantes> participantes, int idPyme, int idDiplomado)
			throws ParticipantesNoAlmacenadosException;
	public List<Participantes> getParticipantes(int idDiplomado, int idPyme)
			throws ParticipantesNoObtenidoException;
	public List<Participantes> getInasistentes(int idDiplomado) throws ParticipantesNoObtenidoException;
	public Encuestas getEncuestas(int idAsistente) throws EncuestasNoObtenidasException;
	public Mensaje saveEncuestas(Encuestas encuestas) throws EncuestasNoAlmacenadasException;
}