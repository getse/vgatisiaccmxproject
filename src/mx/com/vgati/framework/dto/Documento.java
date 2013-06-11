package mx.com.vgati.framework.dto;

import java.io.InputStream;

import mx.com.vgati.framework.util.Null;

@SuppressWarnings("serial")
public class Documento extends AbstractBaseDTO {

	private int idArchivo;
	private int idUsuario;
	private int idIndicador;
	private int idReferencia;
	private String nombre;
	private String descripcionArchivo;
	private String ruta;
	private InputStream is;

	public int getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(int idArchivo) {
		this.idArchivo = idArchivo;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdIndicador() {
		return idIndicador;
	}

	public void setIdIndicador(int idIndicador) {
		this.idIndicador = idIndicador;
	}

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
		String result = "txt";
		try {
			result = Null.free(nombre).substring(
					Null.free(nombre).lastIndexOf("."));
		} catch (Exception e) {
		}
		return result;
	}

	public String getDescripcionArchivo() {
		return descripcionArchivo;
	}

	public void setDescripcionArchivo(String descripcionArchivo) {
		this.descripcionArchivo = descripcionArchivo;
	}

	public String getMimeType(String nombre) {
		String type = null;
		if (Null.free(nombre).equalsIgnoreCase("txt")) {
			type = "text/plain";
		} else if (Null.free(nombre).equalsIgnoreCase("doc")
				|| Null.free(nombre).equalsIgnoreCase("docx")) {
			type = "application/msword";
		} else if (Null.free(nombre).equalsIgnoreCase("xls")
				|| Null.free(nombre).equalsIgnoreCase("xlsx")) {
			type = "application/excel";
		} else if (Null.free(nombre).equalsIgnoreCase("pps")
				|| Null.free(nombre).equalsIgnoreCase("ppt")) {
			type = "application/mspowerpoint";
		} else if (Null.free(nombre).equalsIgnoreCase("jpg")) {
			type = "image/jpeg";
		} else if (Null.free(nombre).equalsIgnoreCase("jpeg")) {
			type = "image/jpeg";
		} else if (Null.free(nombre).equalsIgnoreCase("png")) {
			type = "image/png";
		} else if (Null.free(nombre).equalsIgnoreCase("bmp")) {
			type = "image/bmp";
		} else if (Null.free(nombre).equalsIgnoreCase("gif")) {
			type = "image/gif";
		} else if (Null.free(nombre).equalsIgnoreCase("pdf")) {
			type = "application/pdf";
		} else if (Null.free(nombre).equalsIgnoreCase("raw")) {
			type = "application/";
		} else if (Null.free(nombre).equalsIgnoreCase("zip")) {
			type = "application/zip";
		} else if (Null.free(nombre).equalsIgnoreCase("dwg")) {
			type = "application/";
		}

		return type;
	}

}
