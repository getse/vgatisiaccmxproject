package mx.com.vgati.ccmx.vinculacion.report.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;



@SuppressWarnings("serial")
public class ServiciosConsultoria extends AbstractBaseDTO{
	private int idConsultoria;
	private int idUsuario;
	public boolean bConsultoriaVeinte;
	public boolean bConsultoriaCuarenta;
	public boolean bConsultoriaSesenta;
	public boolean bConsultoriaOchenta;
	private String mensaje;
	public int getIdConsultoria() {
		return idConsultoria;
	}
	public void setIdConsultoria(int idConsultoria) {
		this.idConsultoria = idConsultoria;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public boolean isbConsultoriaVeinte() {
		return bConsultoriaVeinte;
	}
	public void setbConsultoriaVeinte(boolean bConsultoriaVeinte) {
		this.bConsultoriaVeinte = bConsultoriaVeinte;
	}
	public boolean isbConsultoriaCuarenta() {
		return bConsultoriaCuarenta;
	}
	public void setbConsultoriaCuarenta(boolean bConsultoriaCuarenta) {
		this.bConsultoriaCuarenta = bConsultoriaCuarenta;
	}
	public boolean isbConsultoriaSesenta() {
		return bConsultoriaSesenta;
	}
	public void setbConsultoriaSesenta(boolean bConsultoriaSesenta) {
		this.bConsultoriaSesenta = bConsultoriaSesenta;
	}
	public boolean isbConsultoriaOchenta() {
		return bConsultoriaOchenta;
	}
	public void setbConsultoriaOchenta(boolean bConsultoriaOchenta) {
		this.bConsultoriaOchenta = bConsultoriaOchenta;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
