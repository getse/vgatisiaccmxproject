package mx.com.vgati.ccmx.vinculacion.report.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class Filtros  extends AbstractBaseDTO {
	private int id;
	private int filtro1;
	private int filtro2;
	private int filtro3;
	private int filtro4;
	private int filtro5;
	private int permisos;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFiltro1() {
		return filtro1;
	}
	public void setFiltro1(int filtro1) {
		this.filtro1 = filtro1;
	}
	public int getFiltro2() {
		return filtro2;
	}
	public void setFiltro2(int filtro2) {
		this.filtro2 = filtro2;
	}
	public int getFiltro3() {
		return filtro3;
	}
	public void setFiltro3(int filtro3) {
		this.filtro3 = filtro3;
	}
	public int getFiltro4() {
		return filtro4;
	}
	public void setFiltro4(int filtro4) {
		this.filtro4 = filtro4;
	}
	public int getFiltro5() {
		return filtro5;
	}
	public void setFiltro5(int filtro5) {
		this.filtro5 = filtro5;
	}
	public int getPermisos() {
		return permisos;
	}
	public void setPermisos(int permisos) {
		this.permisos = permisos;
	}
	

}
