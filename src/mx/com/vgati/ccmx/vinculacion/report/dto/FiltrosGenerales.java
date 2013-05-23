package mx.com.vgati.ccmx.vinculacion.report.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class FiltrosGenerales extends AbstractBaseDTO{
	private int id;
	private String campoString;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCampoString() {
		return campoString;
	}
	public void setCampoString(String campoString) {
		this.campoString = campoString;
	}
	

}
