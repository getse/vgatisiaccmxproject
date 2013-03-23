/*
 * Clientes.java        01/03/2013
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
public class Clientes extends AbstractBaseDTO {
	private int idCliente;
	private int idUsuario;
	private String cliente;
	private String productosCompra;
	private String aniosProveedor;
	private String mesesProveedor;
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getProductosCompra() {
		return productosCompra;
	}
	public void setProductosCompra(String productosCompra) {
		this.productosCompra = productosCompra;
	}
	public String getAniosProveedor() {
		return aniosProveedor;
	}
	public void setAniosProveedor(String aniosProveedor) {
		this.aniosProveedor = aniosProveedor;
	}
	public String getMesesProveedor() {
		return mesesProveedor;
	}
	public void setMesesProveedor(String mesesProveedor) {
		this.mesesProveedor = mesesProveedor;
	}
}
