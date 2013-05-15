package mx.com.vgati.ccmx.vinculacion.dto;

/**
 * 
 * @author Getsemani Correa
 * 
 */
public enum Diplomados {

	CulturaOrg("Cultura organizacional y la competitividad de las empresas"), EstrategiaCom(
			"Estrategia Comercial, Imagen y Cadena de Distribución"), DesrrolloMet(
			"Desarrollo de Métodos de Producción Esbeltos y Cadena de Valor"), EstrategiaPlan(
			"Estrategia, Planeación e Innovación");

	private final String diplomado;

	private Diplomados(String diplomado) {
		this.diplomado = diplomado;
	}

	public String getDiplomado() {
		return diplomado;
	}
}
