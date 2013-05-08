package mx.com.vgati.ccmx.vinculacion.report.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class CCMXFinanzas extends AbstractBaseDTO {
	private int no;
	private String pyme;	
	private String consultora;
	private int anoAtencion;
	private String cedula;	
	private double faturaAnticipo;
	private double faturaAbono1;
	private double faturaAbono2;
	private double facturaFiniquito;
	private String horasConsultoria;
	private double total;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getPyme() {
		return pyme;
	}
	public void setPyme(String pyme) {
		this.pyme = pyme;
	}
	public String getConsultora() {
		return consultora;
	}
	public void setConsultora(String consultora) {
		this.consultora = consultora;
	}
	public int getAnoAtencion() {
		return anoAtencion;
	}
	public void setAnoAtencion(int anoAatencion) {
		this.anoAtencion = anoAatencion;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public double getFaturaAnticipo() {
		return faturaAnticipo;
	}
	public void setFaturaAnticipo(double faturaAnticipo) {
		this.faturaAnticipo = faturaAnticipo;
	}
	public double getFaturaAbono1() {
		return faturaAbono1;
	}
	public void setFaturaAbono1(double faturaAbono1) {
		this.faturaAbono1 = faturaAbono1;
	}
	public double getFaturaAbono2() {
		return faturaAbono2;
	}
	public void setFaturaAbono2(double faturaAbono2) {
		this.faturaAbono2 = faturaAbono2;
	}
	public double getFacturaFiniquito() {
		return facturaFiniquito;
	}
	public void setFacturaFiniquito(double facturaFiniquito) {
		this.facturaFiniquito = facturaFiniquito;
	}
	public String getHorasConsultoria() {
		return horasConsultoria;
	}
	public void setHorasConsultoria(String horasConsultoria) {
		this.horasConsultoria = horasConsultoria;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
