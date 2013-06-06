package mx.com.vgati.ccmx.vinculacion.report.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class PYMESReporte extends AbstractBaseDTO{

	private int no;
	private String pyme;				
	private String anoAtencion;
	private String cedula;			
	private String consultor;
	private String estatus;	
	private String horasConsultoria;
	private String admonantes;	
	private String admonDespues;
	private String mdoAntes;
	private String mdoDespues;
	private String finanzasAntes;
	private String finanzasdespues;
	private String operacionAntes;
	private String operacionDespues;
	private String rhAntes;
	private String rhDespues;
	private String promedioAntes;
	private String promedioDespues;
	private String empresa;
	private String totales;
	private int estrellas;
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
	public String getAnoAtencion() {
		return anoAtencion;
	}
	public void setAnoAtencion(String anoAtencion) {
		this.anoAtencion = anoAtencion;
	}
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getConsultor() {
		return consultor;
	}
	public void setConsultor(String consultor) {
		this.consultor = consultor;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getHorasConsultoria() {
		return horasConsultoria;
	}
	public void setHorasConsultoria(String horasConsultoria) {
		this.horasConsultoria = horasConsultoria;
	}
	public String getAdmonantes() {
		return admonantes;
	}
	public void setAdmonantes(String admonantes) {
		this.admonantes = admonantes;
	}
	public String getAdmonDespues() {
		return admonDespues;
	}
	public void setAdmonDespues(String admonDespues) {
		this.admonDespues = admonDespues;
	}
	public String getMdoAntes() {
		return mdoAntes;
	}
	public void setMdoAntes(String mdoAntes) {
		this.mdoAntes = mdoAntes;
	}
	public String getMdoDespues() {
		return mdoDespues;
	}
	public void setMdoDespues(String mdoDespues) {
		this.mdoDespues = mdoDespues;
	}
	public String getFinanzasAntes() {
		return finanzasAntes;
	}
	public void setFinanzasAntes(String finanzasAntes) {
		this.finanzasAntes = finanzasAntes;
	}
	public String getFinanzasdespues() {
		return finanzasdespues;
	}
	public void setFinanzasdespues(String finanzasdespues) {
		this.finanzasdespues = finanzasdespues;
	}
	public String getOperacionAntes() {
		return operacionAntes;
	}
	public void setOperacionAntes(String operacionAntes) {
		this.operacionAntes = operacionAntes;
	}
	public String getOperacionDespues() {
		return operacionDespues;
	}
	public void setOperacionDespues(String operacionDespues) {
		this.operacionDespues = operacionDespues;
	}
	public String getRhAntes() {
		return rhAntes;
	}
	public void setRhAntes(String rhAntes) {
		this.rhAntes = rhAntes;
	}
	public String getRhDespues() {
		return rhDespues;
	}
	public void setRhDespues(String rhDespues) {
		this.rhDespues = rhDespues;
	}
	public String getPromedioAntes() {
		return promedioAntes;
	}
	public void setPromedioAntes(String promedioAntes) {
		this.promedioAntes = promedioAntes;
	}
	public String getPromedioDespues() {
		return promedioDespues;
	}
	public void setPromedioDespues(String promedioDespues) {
		this.promedioDespues = promedioDespues;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getTotales() {
		return totales;
	}
	public void setTotales(String totales) {
		this.totales = totales;
	}
	public int getEstrellas() {
		return estrellas;
	}
	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}
}
