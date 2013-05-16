package mx.com.vgati.ccmx.vinculacion.consultoras.dto;

public enum Factura {
	Anticipo("Anticipo"), Finiquito("Finiquito"), Abono1(
	"Abono1"), Abono2("Abono2");

	private final String factura;
	
	private Factura(String factura) {
	this.factura= factura;
	}
	
	public String geteFactura() {
		return factura;
	}
}
