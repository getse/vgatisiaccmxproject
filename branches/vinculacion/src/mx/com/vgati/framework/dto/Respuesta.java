/*
 * Respuesta.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class Respuesta extends AbstractBaseDTO {

	private int idRespuesta;
	private int idRequerimiento;
	private int idPyME;
	private String nombrePyME;
	private String informacion;
	private File archivo;
	private String mensajeEnvio;
	public InputStream archivo1;
	public InputStream archivo2;
	public InputStream archivo3;
	public InputStream archivo4;
	public InputStream archivo5;
	public InputStream archivo6;
	public InputStream archivo7;
	public InputStream archivo8;
	public InputStream archivo9;
	public InputStream archivo10;
	public int idArchivo1;
	public int idArchivo2;
	public int idArchivo3;
	public int idArchivo4;
	public int idArchivo5;
	public int idArchivo6;
	public int idArchivo7;
	public int idArchivo8;
	public int idArchivo9;
	public int idArchivo10;
	public String archivo1ContentType;
	public String archivo2ContentType;
	public String archivo3ContentType;
	public String archivo4ContentType;
	public String archivo5ContentType;
	public String archivo6ContentType;
	public String archivo7ContentType;
	public String archivo8ContentType;
	public String archivo9ContentType;
	public String archivo10ContentType;
	public String archivo1FileName;
	public String archivo2FileName;
	public String archivo3FileName;
	public String archivo4FileName;
	public String archivo5FileName;
	public String archivo6FileName;
	public String archivo7FileName;
	public String archivo8FileName;
	public String archivo9FileName;
	public String archivo10FileName;

	public int getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public int getIdRequerimiento() {
		return idRequerimiento;
	}

	public void setIdRequerimiento(int idRequerimiento) {
		this.idRequerimiento = idRequerimiento;
	}

	public int getIdPyME() {
		return idPyME;
	}

	public void setIdPyME(int idPyME) {
		this.idPyME = idPyME;
	}

	public String getNombrePyME() {
		return nombrePyME;
	}

	public void setNombrePyME(String nombrePyME) {
		this.nombrePyME = nombrePyME;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	public String getMensajeEnvio() {
		return mensajeEnvio;
	}

	public void setMensajeEnvio(String mensajeEnvio) {
		this.mensajeEnvio = mensajeEnvio;
	}

	public InputStream getArchivo1() {
		return archivo1;
	}

	public void setArchivo1(InputStream archivo1) {
		this.archivo1 = archivo1;
	}

	public void setArchivo1(File archivo1) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo1.getCanonicalPath());
			this.archivo1 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo2() {
		return archivo2;
	}

	public void setArchivo2(InputStream archivo2) {
		this.archivo2 = archivo2;
	}

	public void setArchivo2(File archivo2) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo2.getCanonicalPath());
			this.archivo2 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo3() {
		return archivo3;
	}

	public void setArchivo3(InputStream archivo3) {
		this.archivo3 = archivo3;
	}

	public void setArchivo3(File archivo3) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo3.getCanonicalPath());
			this.archivo3 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo4() {
		return archivo4;
	}

	public void setArchivo4(InputStream archivo4) {
		this.archivo4 = archivo4;
	}

	public void setArchivo4(File archivo4) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo4.getCanonicalPath());
			this.archivo4 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo5() {
		return archivo5;
	}

	public void setArchivo5(InputStream archivo5) {
		this.archivo5 = archivo5;
	}

	public void setArchivo5(File archivo5) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo5.getCanonicalPath());
			this.archivo5 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo6() {
		return archivo6;
	}

	public void setArchivo6(InputStream archivo6) {
		this.archivo6 = archivo6;
	}

	public void setArchivo6(File archivo6) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo6.getCanonicalPath());
			this.archivo6 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo7() {
		return archivo7;
	}

	public void setArchivo7(InputStream archivo7) {
		this.archivo7 = archivo7;
	}

	public void setArchivo7(File archivo7) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo7.getCanonicalPath());
			this.archivo7 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo8() {
		return archivo8;
	}

	public void setArchivo8(InputStream archivo8) {
		this.archivo8 = archivo8;
	}

	public void setArchivo8(File archivo8) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo8.getCanonicalPath());
			this.archivo8 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo9() {
		return archivo9;
	}

	public void setArchivo9(InputStream archivo9) {
		this.archivo9 = archivo9;
	}

	public void setArchivo9(File archivo9) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo9.getCanonicalPath());
			this.archivo9 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getArchivo10() {
		return archivo10;
	}

	public void setArchivo10(InputStream archivo10) {
		this.archivo10 = archivo10;
	}

	public void setArchivo10(File archivo10) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(archivo10.getCanonicalPath());
			this.archivo10 = fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getIdArchivo1() {
		return idArchivo1;
	}

	public void setIdArchivo1(int idArchivo1) {
		this.idArchivo1 = idArchivo1;
	}

	public int getIdArchivo2() {
		return idArchivo2;
	}

	public void setIdArchivo2(int idArchivo2) {
		this.idArchivo2 = idArchivo2;
	}

	public int getIdArchivo3() {
		return idArchivo3;
	}

	public void setIdArchivo3(int idArchivo3) {
		this.idArchivo3 = idArchivo3;
	}

	public int getIdArchivo4() {
		return idArchivo4;
	}

	public void setIdArchivo4(int idArchivo4) {
		this.idArchivo4 = idArchivo4;
	}

	public int getIdArchivo5() {
		return idArchivo5;
	}

	public void setIdArchivo5(int idArchivo5) {
		this.idArchivo5 = idArchivo5;
	}

	public int getIdArchivo6() {
		return idArchivo6;
	}

	public void setIdArchivo6(int idArchivo6) {
		this.idArchivo6 = idArchivo6;
	}

	public int getIdArchivo7() {
		return idArchivo7;
	}

	public void setIdArchivo7(int idArchivo7) {
		this.idArchivo7 = idArchivo7;
	}

	public int getIdArchivo8() {
		return idArchivo8;
	}

	public void setIdArchivo8(int idArchivo8) {
		this.idArchivo8 = idArchivo8;
	}

	public int getIdArchivo9() {
		return idArchivo9;
	}

	public void setIdArchivo9(int idArchivo9) {
		this.idArchivo9 = idArchivo9;
	}

	public int getIdArchivo10() {
		return idArchivo10;
	}

	public void setIdArchivo10(int idArchivo10) {
		this.idArchivo10 = idArchivo10;
	}

	public String getArchivo1ContentType() {
		return archivo1ContentType;
	}

	public void setArchivo1ContentType(String archivo1ContentType) {
		this.archivo1ContentType = archivo1ContentType;
	}

	public String getArchivo2ContentType() {
		return archivo2ContentType;
	}

	public void setArchivo2ContentType(String archivo2ContentType) {
		this.archivo2ContentType = archivo2ContentType;
	}

	public String getArchivo3ContentType() {
		return archivo3ContentType;
	}

	public void setArchivo3ContentType(String archivo3ContentType) {
		this.archivo3ContentType = archivo3ContentType;
	}

	public String getArchivo4ContentType() {
		return archivo4ContentType;
	}

	public void setArchivo4ContentType(String archivo4ContentType) {
		this.archivo4ContentType = archivo4ContentType;
	}

	public String getArchivo5ContentType() {
		return archivo5ContentType;
	}

	public void setArchivo5ContentType(String archivo5ContentType) {
		this.archivo5ContentType = archivo5ContentType;
	}

	public String getArchivo6ContentType() {
		return archivo6ContentType;
	}

	public void setArchivo6ContentType(String archivo6ContentType) {
		this.archivo6ContentType = archivo6ContentType;
	}

	public String getArchivo7ContentType() {
		return archivo7ContentType;
	}

	public void setArchivo7ContentType(String archivo7ContentType) {
		this.archivo7ContentType = archivo7ContentType;
	}

	public String getArchivo8ContentType() {
		return archivo8ContentType;
	}

	public void setArchivo8ContentType(String archivo8ContentType) {
		this.archivo8ContentType = archivo8ContentType;
	}

	public String getArchivo9ContentType() {
		return archivo9ContentType;
	}

	public void setArchivo9ContentType(String archivo9ContentType) {
		this.archivo9ContentType = archivo9ContentType;
	}

	public String getArchivo10ContentType() {
		return archivo10ContentType;
	}

	public void setArchivo10ContentType(String archivo10ContentType) {
		this.archivo10ContentType = archivo10ContentType;
	}

	public String getArchivo1FileName() {
		return archivo1FileName;
	}

	public void setArchivo1FileName(String archivo1FileName) {
		this.archivo1FileName = archivo1FileName;
	}

	public String getArchivo2FileName() {
		return archivo2FileName;
	}

	public void setArchivo2FileName(String archivo2FileName) {
		this.archivo2FileName = archivo2FileName;
	}

	public String getArchivo3FileName() {
		return archivo3FileName;
	}

	public void setArchivo3FileName(String archivo3FileName) {
		this.archivo3FileName = archivo3FileName;
	}

	public String getArchivo4FileName() {
		return archivo4FileName;
	}

	public void setArchivo4FileName(String archivo4FileName) {
		this.archivo4FileName = archivo4FileName;
	}

	public String getArchivo5FileName() {
		return archivo5FileName;
	}

	public void setArchivo5FileName(String archivo5FileName) {
		this.archivo5FileName = archivo5FileName;
	}

	public String getArchivo6FileName() {
		return archivo6FileName;
	}

	public void setArchivo6FileName(String archivo6FileName) {
		this.archivo6FileName = archivo6FileName;
	}

	public String getArchivo7FileName() {
		return archivo7FileName;
	}

	public void setArchivo7FileName(String archivo7FileName) {
		this.archivo7FileName = archivo7FileName;
	}

	public String getArchivo8FileName() {
		return archivo8FileName;
	}

	public void setArchivo8FileName(String archivo8FileName) {
		this.archivo8FileName = archivo8FileName;
	}

	public String getArchivo9FileName() {
		return archivo9FileName;
	}

	public void setArchivo9FileName(String archivo9FileName) {
		this.archivo9FileName = archivo9FileName;
	}

	public String getArchivo10FileName() {
		return archivo10FileName;
	}

	public void setArchivo10FileName(String archivo10FileName) {
		this.archivo10FileName = archivo10FileName;
	}

}
