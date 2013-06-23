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
package mx.com.vgati.ccmx.vinculacion.pymes.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;
import mx.com.vgati.framework.util.Null;

@SuppressWarnings("serial")
public class Productos extends AbstractBaseDTO {
	
	private int idProducto;
	private int idUsuario;
	private String producto;
	
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public void setIdProducto(String idProducto) {
		String id = Null.free(idProducto);
		this.idProducto = Integer.parseInt(id.isEmpty() ? "0" : id);
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		String id = Null.free(idUsuario);
		this.idUsuario = Integer.parseInt(id.isEmpty() ? "0" : id);
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
}