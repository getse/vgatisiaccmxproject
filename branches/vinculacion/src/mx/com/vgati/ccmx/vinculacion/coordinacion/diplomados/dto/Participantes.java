package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class Participantes extends AbstractBaseDTO {
	private int id;
	private int idUsuario;
	private String telefono;
	private String nombre;
	private String correoElectronico;
	private boolean asistencia1;
	private boolean asistencia2;
	private boolean asistencia3;
	private boolean asistencia4;
	private boolean pago;
	private boolean factura;
	private String cargo;
	private String pyme;
	private String tractora;
	private String numPago;
	private boolean editable1;
	private boolean editable2;
	private boolean editable3;
	private boolean editable4;
	private int sesion;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public boolean isAsistencia1() {
		return asistencia1;
	}
	public void setAsistencia1(boolean asistencia1) {
		this.asistencia1 = asistencia1;
	}
	public boolean isAsistencia2() {
		return asistencia2;
	}
	public void setAsistencia2(boolean asistencia2) {
		this.asistencia2 = asistencia2;
	}
	public boolean isAsistencia3() {
		return asistencia3;
	}
	public void setAsistencia3(boolean asistencia3) {
		this.asistencia3 = asistencia3;
	}
	public boolean isAsistencia4() {
		return asistencia4;
	}
	public void setAsistencia4(boolean asistencia4) {
		this.asistencia4 = asistencia4;
	}
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	public boolean isFactura() {
		return factura;
	}
	public void setFactura(boolean factura) {
		this.factura = factura;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getPyme() {
		return pyme;
	}
	public void setPyme(String pyme) {
		this.pyme = pyme;
	}
	public String getTractora() {
		return tractora;
	}
	public void setTractora(String tractora) {
		this.tractora = tractora;
	}
	public String getNumPago() {
		return numPago;
	}
	public void setNumPago(String numPago) {
		this.numPago = numPago;
	}
	public boolean isEditable1() {
		return editable1;
	}
	public void setEditable1(boolean editable1) {
		this.editable1 = editable1;
	}
	public boolean isEditable2() {
		return editable2;
	}
	public void setEditable2(boolean editable2) {
		this.editable2 = editable2;
	}
	public boolean isEditable3() {
		return editable3;
	}
	public void setEditable3(boolean editable3) {
		this.editable3 = editable3;
	}
	public boolean isEditable4() {
		return editable4;
	}
	public void setEditable4(boolean editable4) {
		this.editable4 = editable4;
	}
	public int getSesion() {
		return sesion;
	}
	public void setSesion(int sesion) {
		this.sesion = sesion;
	}
	
}
