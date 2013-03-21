package mx.com.vgati.ccmx.vinculacion.tractoras.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class CatScianCcmx extends AbstractBaseDTO {

	private String cveScian;
	private String descScian;
	private int cveNivel;

	public String getCveScian() {
		return cveScian;
	}

	public void setCveScian(String cveScian) {
		this.cveScian = cveScian;
	}

	public String getDescScian() {
		return descScian;
	}

	public void setDescScian(String descScian) {
		this.descScian = descScian;
	}

	public int getCveNivel() {
		return cveNivel;
	}

	public void setCveNivel(int cveNivel) {
		this.cveNivel = cveNivel;
	}

}
