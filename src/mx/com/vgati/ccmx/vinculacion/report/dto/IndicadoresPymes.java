package mx.com.vgati.ccmx.vinculacion.report.dto;


import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class IndicadoresPymes extends AbstractBaseDTO{

	private int no;
	private String pyme;				
	private String anoAtencion;
	private String cedula;			
	private String consultor;
	private String estatus;	
	private String indicadorPpt;	
	private String admonAntes;	
	private String mdoAntes;
	private String finanzasAntes;
	private String operacionAntes;
	private String rhAntes;
	private String promedioAntes;	
	private String admonDespues;
	private String mdoDespues;	
	private String finanzasDespues;
	private String operacionDespues;
	private String rhDespues;
	private String promedioDespues;
	private int ingresosAntes;
	private int ingresosDespues;	
	private int clientesAntes;
	private int clientesDespues;
	private int empleadosAntes;
	private int empleadosDespues;
	private int ventasAntes;
	private int ventasDespues;
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
	public int getClientesAntes() {
		return clientesAntes;
	}
	public void setClientesAntes(int clientesAntes) {
		this.clientesAntes = clientesAntes;
	}
	public int getClientesDespues() {
		return clientesDespues;
	}
	public void setClientesDespues(int clientesDespues) {
		this.clientesDespues = clientesDespues;
	}
	public int getEmpleadosAntes() {
		return empleadosAntes;
	}
	public void setEmpleadosAntes(int empleadosAntes) {
		this.empleadosAntes = empleadosAntes;
	}
	public int getEmpleadosDespues() {
		return empleadosDespues;
	}
	public void setEmpleadosDespues(int empleadosDespues) {
		this.empleadosDespues = empleadosDespues;
	}
	public int getVentasAntes() {
		return ventasAntes;
	}
	public void setVentasAntes(int ventasAntes) {
		this.ventasAntes = ventasAntes;
	}
	public int getVentasDespues() {
		return ventasDespues;
	}
	public void setVentasDespues(int ventasDespues) {
		this.ventasDespues = ventasDespues;
	}
	public String getAdmonAntes() {
		return admonAntes;
	}
	public void setAdmonAntes(String admonAntes) {
		this.admonAntes = admonAntes;
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
	public String getFinanzasDespues() {
		return finanzasDespues;
	}
	public void setFinanzasDespues(String finanzasDespues) {
		this.finanzasDespues = finanzasDespues;
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
	public String getIndicadorPpt() {
		return indicadorPpt;
	}
	public void setIndicadorPpt(String indicadorPpt) {
		this.indicadorPpt = indicadorPpt;
	}
	public String getPromedioAntes() {
		return promedioAntes;
	}
	public void setPromedioAntes(String promedioAntes) {
		this.promedioAntes = promedioAntes;
	}
	public String getRhDespues() {
		return rhDespues;
	}
	public void setRhDespues(String rhDespues) {
		this.rhDespues = rhDespues;
	}
	public String getPromedioDespues() {
		return promedioDespues;
	}
	public void setPromedioDespues(String promedioDespues) {
		this.promedioDespues = promedioDespues;
	}
	public int getIngresosAntes() {
		return ingresosAntes;
	}
	public void setIngresosAntes(int ingresosAntes) {
		this.ingresosAntes = ingresosAntes;
	}
	public int getIngresosDespues() {
		return ingresosDespues;
	}
	public void setIngresosDespues(int ingresosDespues) {
		this.ingresosDespues = ingresosDespues;
	}
}
