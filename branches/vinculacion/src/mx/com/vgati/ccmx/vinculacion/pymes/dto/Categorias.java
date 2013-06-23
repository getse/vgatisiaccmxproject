/*
 * Categorias.java        01/06/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.ccmx.vinculacion.pymes.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class Categorias extends AbstractBaseDTO {

	private int idCategoria;
	private int idUsuario;
	private int cveScian;
	private String descScian;

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		int i;
		try {
			i = Integer.parseInt(idCategoria.trim());
		} catch (Exception e) {
			i = 0;
		}
		this.idCategoria = i;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		int i;
		try {
			i = Integer.parseInt(idUsuario.trim());
		} catch (Exception e) {
			i = 0;
		}
		this.idUsuario = i;
	}

	public int getCveScian() {
		return cveScian;
	}

	public void setCveScian(int cveScian) {
		this.cveScian = cveScian;
	}

	public void setCveScian(String cveScian) {
		int i;
		try {
			i = Integer.parseInt(cveScian.trim());
		} catch (Exception e) {
			i = 0;
		}
		this.cveScian = i;
	}

	public String getDescScian() {
		return descScian;
	}

	public void setDescScian(String descScian) {
		this.descScian = descScian;
	}

}