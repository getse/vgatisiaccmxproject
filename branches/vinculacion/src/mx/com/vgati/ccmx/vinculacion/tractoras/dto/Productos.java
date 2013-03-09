/*
 * Productos.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.tractoras.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class Productos extends AbstractBaseDTO {

	private int idClase;
	private int idSectorEconomico;
	private int idSubsectorEconomico;
	private int idRama;
	private int idSubrama;
	private String sectorEconomico;
	private String subsectorEconomico;
	private String rama;
	private String subRama;
	private String clase;
	private String sectorSubsector;
	private int porcentaje;
	private String observacion;
	private String tieneComentario;
	private String comentario;
	private String usoRestringido;
	private String idScian;
	private boolean seleccionada;
	private String idRegistroAe;

	public int getIdClase() {
		return idClase;
	}

	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}

	public int getIdSectorEconomico() {
		return idSectorEconomico;
	}

	public void setIdSectorEconomico(int idSectorEconomico) {
		this.idSectorEconomico = idSectorEconomico;
	}

	public int getIdSubsectorEconomico() {
		return idSubsectorEconomico;
	}

	public void setIdSubsectorEconomico(int idSubsectorEconomico) {
		this.idSubsectorEconomico = idSubsectorEconomico;
	}

	public int getIdRama() {
		return idRama;
	}

	public void setIdRama(int idRama) {
		this.idRama = idRama;
	}

	public int getIdSubrama() {
		return idSubrama;
	}

	public void setIdSubrama(int idSubrama) {
		this.idSubrama = idSubrama;
	}

	public String getSectorEconomico() {
		return sectorEconomico;
	}

	public void setSectorEconomico(String sectorEconomico) {
		this.sectorEconomico = sectorEconomico;
	}

	public String getSubsectorEconomico() {
		return subsectorEconomico;
	}

	public void setSubsectorEconomico(String subsectorEconomico) {
		this.subsectorEconomico = subsectorEconomico;
	}

	public String getRama() {
		return rama;
	}

	public void setRama(String rama) {
		this.rama = rama;
	}

	public String getSubRama() {
		return subRama;
	}

	public void setSubRama(String subRama) {
		this.subRama = subRama;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getSectorSubsector() {
		return sectorSubsector;
	}

	public void setSectorSubsector(String sectorSubsector) {
		this.sectorSubsector = sectorSubsector;
	}

	public int getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTieneComentario() {
		return tieneComentario;
	}

	public void setTieneComentario(String tieneComentario) {
		this.tieneComentario = tieneComentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getUsoRestringido() {
		return usoRestringido;
	}

	public void setUsoRestringido(String usoRestringido) {
		this.usoRestringido = usoRestringido;
	}

	public String getIdScian() {
		return idScian;
	}

	public void setIdScian(String idScian) {
		this.idScian = idScian;
	}

	public boolean isSeleccionada() {
		return seleccionada;
	}

	public void setSeleccionada(boolean seleccionada) {
		this.seleccionada = seleccionada;
	}

	public String getIdRegistroAe() {
		return idRegistroAe;
	}

	public void setIdRegistroAe(String idRegistroAe) {
		this.idRegistroAe = idRegistroAe;
	}

}
