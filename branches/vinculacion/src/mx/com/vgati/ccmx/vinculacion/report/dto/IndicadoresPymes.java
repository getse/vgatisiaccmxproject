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
	private String horasConsultoria;
	private float promedioAntes;
	private float promedioDespues;
	private int mdoAntes;
	private int mdoDespues;
	private int operacionAntes;
	private int operacionDespues;
	private int finanzasAntes;
	private int finanzasDespues;
	private int rhAntes;
	private int rhDespues;
	private int admonAntes;			
	private int admonDespues;
	private int ingresosAntes;
	private int ingresosDespues;	
	private int clientesAntes;
	private int clientesDespues;
	private int empleadosAntes;
	private int empleadosDespues;
	private int ventasAntes;
	private int ventasDespues;
	private int monetarios1;
	private int monetarios2;
	private int monetarios3;
	private int monetarios4;
	private int defectos1;
	private int defectos2;
	private int defectos3;
	private int defectos4;
	private int ahorro1;
	private int ahorro2;
	private int ahorro3;
	private int ahorro4;
	private int postVenta1;
	private int postVenta2;
	private int postVenta3;
	private int postVenta4;
	private int capacidadPyme1;
	private int capacidadPyme2;
	private int capacidadPyme3;
	private int capacidadPyme4;
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
	public int getAdmonAntes() {
		return admonAntes;
	}
	public void setAdmonAntes(int admonAntes) {
		this.admonAntes = admonAntes;
	}
	public int getMdoAntes() {
		return mdoAntes;
	}
	public void setMdoAntes(int mdoAntes) {
		this.mdoAntes = mdoAntes;
	}
	public int getFinanzasAntes() {
		return finanzasAntes;
	}
	public void setFinanzasAntes(int finanzasAntes) {
		this.finanzasAntes = finanzasAntes;
	}
	public int getOperacionAntes() {
		return operacionAntes;
	}
	public void setOperacionAntes(int operacionAntes) {
		this.operacionAntes = operacionAntes;
	}
	public int getRhAntes() {
		return rhAntes;
	}
	public void setRhAntes(int rhAntes) {
		this.rhAntes = rhAntes;
	}
	public float getPromedioAntes() {
		return promedioAntes;
	}
	public void setPromedioAntes(float promedioAntes) {
		this.promedioAntes = promedioAntes;
	}
	public int getAdmonDespues() {
		return admonDespues;
	}
	public void setAdmonDespues(int admonDespues) {
		this.admonDespues = admonDespues;
	}
	public int getMdoDespues() {
		return mdoDespues;
	}
	public void setMdoDespues(int mdoDespues) {
		this.mdoDespues = mdoDespues;
	}
	public int getFinanzasDespues() {
		return finanzasDespues;
	}
	public void setFinanzasDespues(int finanzasDespues) {
		this.finanzasDespues = finanzasDespues;
	}
	public int getOperacionDespues() {
		return operacionDespues;
	}
	public void setOperacionDespues(int operacionDespues) {
		this.operacionDespues = operacionDespues;
	}
	public int getRhDespues() {
		return rhDespues;
	}
	public void setRhDespues(int rhDespues) {
		this.rhDespues = rhDespues;
	}
	public float getPromedioDespues() {
		return promedioDespues;
	}
	public void setPromedioDespues(float promedioDespues) {
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
	public int getMonetarios1() {
		return monetarios1;
	}
	public void setMonetarios1(int monetarios1) {
		this.monetarios1 = monetarios1;
	}
	public int getMonetarios2() {
		return monetarios2;
	}
	public void setMonetarios2(int monetarios2) {
		this.monetarios2 = monetarios2;
	}
	public int getMonetarios3() {
		return monetarios3;
	}
	public void setMonetarios3(int monetarios3) {
		this.monetarios3 = monetarios3;
	}
	public int getMonetarios4() {
		return monetarios4;
	}
	public void setMonetarios4(int monetarios4) {
		this.monetarios4 = monetarios4;
	}
	public int getDefectos1() {
		return defectos1;
	}
	public void setDefectos1(int defectos1) {
		this.defectos1 = defectos1;
	}
	public int getDefectos2() {
		return defectos2;
	}
	public void setDefectos2(int defectos2) {
		this.defectos2 = defectos2;
	}
	public int getDefectos3() {
		return defectos3;
	}
	public void setDefectos3(int defectos3) {
		this.defectos3 = defectos3;
	}
	public int getDefectos4() {
		return defectos4;
	}
	public void setDefectos4(int defectos4) {
		this.defectos4 = defectos4;
	}
	public int getAhorro1() {
		return ahorro1;
	}
	public void setAhorro1(int ahorro1) {
		this.ahorro1 = ahorro1;
	}
	public int getAhorro2() {
		return ahorro2;
	}
	public void setAhorro2(int ahorro2) {
		this.ahorro2 = ahorro2;
	}
	public int getAhorro3() {
		return ahorro3;
	}
	public void setAhorro3(int ahorro3) {
		this.ahorro3 = ahorro3;
	}
	public int getAhorro4() {
		return ahorro4;
	}
	public void setAhorro4(int ahorro4) {
		this.ahorro4 = ahorro4;
	}
	public int getPostVenta1() {
		return postVenta1;
	}
	public void setPostVenta1(int postVenta1) {
		this.postVenta1 = postVenta1;
	}
	public int getPostVenta2() {
		return postVenta2;
	}
	public void setPostVenta2(int postVenta2) {
		this.postVenta2 = postVenta2;
	}
	public int getPostVenta3() {
		return postVenta3;
	}
	public void setPostVenta3(int postVenta3) {
		this.postVenta3 = postVenta3;
	}
	public int getPostVenta4() {
		return postVenta4;
	}
	public void setPostVenta4(int postVenta4) {
		this.postVenta4 = postVenta4;
	}
	public int getCapacidadPyme1() {
		return capacidadPyme1;
	}
	public void setCapacidadPyme1(int capacidadPyme1) {
		this.capacidadPyme1 = capacidadPyme1;
	}
	public int getCapacidadPyme2() {
		return capacidadPyme2;
	}
	public void setCapacidadPyme2(int capacidadPyme2) {
		this.capacidadPyme2 = capacidadPyme2;
	}
	public int getCapacidadPyme3() {
		return capacidadPyme3;
	}
	public void setCapacidadPyme3(int capacidadPyme3) {
		this.capacidadPyme3 = capacidadPyme3;
	}
	public int getCapacidadPyme4() {
		return capacidadPyme4;
	}
	public void setCapacidadPyme4(int capacidadPyme4) {
		this.capacidadPyme4 = capacidadPyme4;
	}
}
