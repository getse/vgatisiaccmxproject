package mx.com.vgati.ccmx.vinculacion.coordinacion.diplomados.dto;

import mx.com.vgati.framework.dto.AbstractBaseDTO;

@SuppressWarnings("serial")
public class Encuestas extends AbstractBaseDTO{
	private int idAsistente;
	private int idSesion;
	private float respuesta1;
	private float respuesta2;
	private String respuesta3;
	private String respuesta4;
	private float respuesta5;
	private float respuesta6;
	private String respuesta7;
	private String respuesta8;
	private String respuesta9;
	private String retroalimenacion;
	public int getIdAsistente() {
		return idAsistente;
	}
	public void setIdAsistente(int idAsistente) {
		this.idAsistente = idAsistente;
	}
	public float getRespuesta1() {
		return respuesta1;
	}
	public void setRespuesta1(float respuesta1) {
		this.respuesta1 = respuesta1;
	}
	public float getRespuesta2() {
		return respuesta2;
	}
	public void setRespuesta2(float respuesta2) {
		this.respuesta2 = respuesta2;
	}
	public String getRespuesta3() {
		return respuesta3;
	}
	public void setRespuesta3(String respuesta3) {
		this.respuesta3 = respuesta3;
	}
	public String getRespuesta4() {
		return respuesta4;
	}
	public void setRespuesta4(String respuesta4) {
		this.respuesta4 = respuesta4;
	}
	public float getRespuesta5() {
		return respuesta5;
	}
	public void setRespuesta5(float respuesta5) {
		this.respuesta5 = respuesta5;
	}
	public float getRespuesta6() {
		return respuesta6;
	}
	public void setRespuesta6(float respuesta6) {
		this.respuesta6 = respuesta6;
	}
	public String getRespuesta7() {
		return respuesta7;
	}
	public void setRespuesta7(String respuesta7) {
		this.respuesta7 = respuesta7;
	}
	public String getRespuesta8() {
		return respuesta8;
	}
	public void setRespuesta8(String respuesta8) {
		this.respuesta8 = respuesta8;
	}
	public String getRespuesta9() {
		return respuesta9;
	}
	public void setRespuesta9(String respuesta9) {
		this.respuesta9 = respuesta9;
	}
	public String getRetroalimenacion() {
		return retroalimenacion;
	}
	public void setRetroalimenacion(String retroalimenacion) {
		this.retroalimenacion = retroalimenacion;
	}
	public int getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}
}
