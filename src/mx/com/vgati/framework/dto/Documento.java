package mx.com.vgati.framework.dto;

import java.io.InputStream;

import mx.com.vgati.framework.util.Null;

@SuppressWarnings("serial")
public class Documento extends AbstractBaseDTO {

	private int idReferencia;
	private String nombre;
	private String ruta;
	private InputStream is;

	public int getIdReferencia() {
		return idReferencia;
	}

	public void setIdReferencia(int idReferencia) {
		this.idReferencia = idReferencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public String getFileType(String nombre) {
		return Null.free(nombre).substring(Null.free(nombre).lastIndexOf("."));
	}

	public String getMimeType(String nombre) {
		String type = null;
		if (Null.free(nombre).equals("txt")) {

		} else if (Null.free(nombre).equals("doc")
				|| Null.free(nombre).equals("docx")) {

		} else if (Null.free(nombre).equals("xls")
				|| Null.free(nombre).equals("xlsx")) {

		} else if (Null.free(nombre).equalsIgnoreCase("jpg")) {

		} else if (Null.free(nombre).equalsIgnoreCase("jpeg")) {

		} else if (Null.free(nombre).equalsIgnoreCase("png")) {

		} else if (Null.free(nombre).equalsIgnoreCase("bmp")) {

		}

		return type;
	}

}
