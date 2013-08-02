/*
 * CoordinadorDiplomadosService.java        01/05/2013
 *
 * Copyright (c) 2013 Centro de Competitividad M�xico
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad M�xico.
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
	public int getGeneraciones(int year) throws DiplomadosNoObtenidosException;
	public List<List<Diplomados>> getMenuDiplomados(int year, int generaciones) 
			throws DiplomadosNoObtenidosException;
	public List<Integer> getMenuAnios() throws DiplomadosNoObtenidosException;
	public Diplomados getDiplomado(String tema, int generacion)
			throws DiplomadosNoObtenidosException;
	public Participantes getParticipante(int idParticipante)
			throws ParticipantesNoObtenidoException;
	public List<Sesiones> getSesiones(int idDiplomado) 
			throws SesionesNoObtenidasException;
	public List<Participantes> getParticipantes(int idDiplomado)
			throws ParticipantesNoObtenidoException;
	public List<Participantes> getParticipantesPorSesion(int idDiplomado,boolean conf1
			,boolean conf2,boolean conf3,boolean conf4)
	throws ParticipantesNoObtenidoException;
	public Mensaje saveSesiones(List<Sesiones> sesiones, int numeroSesiones)
			throws SesionesNoAlmacenadasException;
	public Mensaje savePagos(List<Participantes> participantes)
			throws ParticipantesNoAlmacenadosException;
	public Mensaje saveAsistencias(List<Participantes> participantes)
		throws ParticipantesNoAlmacenadosException;
	public Mensaje saveFacturas(List<Participantes> participantes)
		throws ParticipantesNoAlmacenadosException;
	public PyMEs getPyme(int idPyme) throws PyMEsNoObtenidasException;
	public List<Participantes> getParticipantes(int idDiplomado, int idPyme)
			throws ParticipantesNoObtenidoException;
	public List<Participantes> getParticipantesDiploma(int idDiplomado, int idPyme)
			throws ParticipantesNoObtenidoException;
	public List<Participantes> getInasistentes(int idDiplomado) throws ParticipantesNoObtenidoException;
	public Encuestas getEncuestas(int idAsistente,int idSesion) throws EncuestasNoObtenidasException;
	public Mensaje saveEncuestas(Encuestas encuestas) throws EncuestasNoAlmacenadasException;
	public Mensaje saveInasistententes(Participantes  p,int idDiplomado) throws 
			AsistentesNoAlmacenadosException;
	public String getTema(int idDiplomado) throws DiplomadosNoObtenidosException;
	public List<PyMEs> getPymes(int idDiplomado) throws PyMEsNoObtenidasException;
	public List<Sesiones> getSesion(int idSesion) throws SesionesNoObtenidasException;
}