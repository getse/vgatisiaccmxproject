/*
 * Requerimientos.java        01/03/2013
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
import java.sql.Date;
import java.util.List;

import mx.com.vgati.ccmx.vinculacion.pymes.dto.EstadosVenta;
import mx.com.vgati.ccmx.vinculacion.tractoras.dto.Tractoras;
import mx.com.vgati.framework.util.Null;

/**
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class Requerimientos extends AbstractBaseDTO {

	public int idRequerimiento;
	public int idTractora;
	public String producto;
	public String tipoProducto;
	public String busqueda;
	public int cveScian;
	public String descripcion;
	public Date fechaSuministro;
	public boolean bIndefinido;
	public boolean bVariasFechas;
	public boolean bContinuoSuministro;
	public String variasFechas;
	public List<EstadosVenta> lugarSuministro;
	public boolean bContado;
	public boolean bCredito;
	public boolean bQuince;
	public boolean bTreinta;
	public boolean bSesenta;
	public boolean bNoventa;
	public boolean bOtro;
	public String otrasCondiciones;
	public String requisitosAdicionales;
	public Date fechaExpira;
	public boolean bContinuoExpira;
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
	private String descArchivo1;
	private String descArchivo2;
	private String descArchivo3;
	private String descArchivo4;
	private String descArchivo5;
	private String descArchivo6;
	private String descArchivo7;
	private String descArchivo8;
	private String descArchivo9;
	private String descArchivo10;
	public Tractoras tractora;

	public int getIdRequerimiento() {
		return idRequerimiento;
	}

	public void setIdRequerimiento(int idRequerimiento) {
		this.idRequerimiento = idRequerimiento;
	}

	public void setIdRequerimiento(String idRequerimiento) {
		String id = Null.free(idRequerimiento);
		this.idRequerimiento = Integer.parseInt(id.isEmpty() ? "0" : id);
	}

	public int getIdTractora() {
		return idTractora;
	}

	public void setIdTractora(int idTractora) {
		this.idTractora = idTractora;
	}

	public void setIdTractora(String idTractora) {
		String id = Null.free(idTractora);
		this.idTractora = Integer.parseInt(id.isEmpty() ? "0" : id);
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public int getCveScian() {
		return cveScian;
	}

	public void setCveScian(int cveScian) {
		this.cveScian = cveScian;
	}

	public void setCveScian(String cveScian) {
		String id = Null.free(cveScian);
		this.cveScian = Integer.parseInt(id.isEmpty() ? "0" : id);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaSuministro() {
		return fechaSuministro;
	}

	public void setFechaSuministro(String fechaSuministro) {
		this.fechaSuministro = Date.valueOf(fechaSuministro);
	}

	public void setFechaSuministro(Date fechaSuministro) {
		this.fechaSuministro = fechaSuministro;
	}

	public boolean isbIndefinido() {
		return bIndefinido;
	}

	public void setbIndefinido(String bIndefinido) {
		this.bIndefinido = Null.free(bIndefinido).equals("true") ? true : false;
	}

	public void setbIndefinido(boolean bIndefinido) {
		this.bIndefinido = bIndefinido;
	}

	public boolean isbVariasFechas() {
		return bVariasFechas;
	}

	public void setbVariasFechas(String bVariasFechas) {
		this.bVariasFechas = Null.free(bVariasFechas).equals("true") ? true
				: false;
	}

	public void setbVariasFechas(boolean bVariasFechas) {
		this.bVariasFechas = bVariasFechas;
	}

	public boolean isbContinuoSuministro() {
		return bContinuoSuministro;
	}

	public void setbContinuoSuministro(String bContinuoSuministro) {
		this.bContinuoSuministro = Null.free(bContinuoSuministro)
				.equals("true") ? true : false;
	}

	public void setbContinuoSuministro(boolean bContinuoSuministro) {
		this.bContinuoSuministro = bContinuoSuministro;
	}

	public String getVariasFechas() {
		return variasFechas;
	}

	public void setVariasFechas(String variasFechas) {
		this.variasFechas = variasFechas;
	}

	public List<EstadosVenta> getLugarSuministro() {
		return lugarSuministro;
	}

	public void setLugarSuministro(List<EstadosVenta> lugarSuministro) {
		this.lugarSuministro = lugarSuministro;
	}

	public boolean isbContado() {
		return bContado;
	}

	public void setbContado(String bContado) {
		this.bContado = Null.free(bContado).equals("true") ? true : false;
	}

	public void setbContado(boolean bContado) {
		this.bContado = bContado;
	}

	public boolean isbCredito() {
		return bCredito;
	}

	public void setbCredito(String bCredito) {
		this.bCredito = Null.free(bCredito).equals("true") ? true : false;
	}

	public void setbCredito(boolean bCredito) {
		this.bCredito = bCredito;
	}

	public boolean isbQuince() {
		return bQuince;
	}

	public void setbQuince(String bQuince) {
		this.bQuince = Null.free(bQuince).equals("true") ? true : false;
	}

	public void setbQuince(boolean bQuince) {
		this.bQuince = bQuince;
	}

	public boolean isbTreinta() {
		return bTreinta;
	}

	public void setbTreinta(String bTreinta) {
		this.bTreinta = Null.free(bTreinta).equals("true") ? true : false;
	}

	public void setbTreinta(boolean bTreinta) {
		this.bTreinta = bTreinta;
	}

	public boolean isbSesenta() {
		return bSesenta;
	}

	public void setbSesenta(String bSesenta) {
		this.bSesenta = Null.free(bSesenta).equals("true") ? true : false;
	}

	public void setbSesenta(boolean bSesenta) {
		this.bSesenta = bSesenta;
	}

	public boolean isbNoventa() {
		return bNoventa;
	}

	public void setbNoventa(String bNoventa) {
		this.bNoventa = Null.free(bNoventa).equals("true") ? true : false;
	}

	public void setbNoventa(boolean bNoventa) {
		this.bNoventa = bNoventa;
	}

	public boolean isbOtro() {
		return bOtro;
	}

	public void setbOtro(String bOtro) {
		this.bOtro = Null.free(bOtro).equals("true") ? true : false;
	}

	public void setbOtro(boolean bOtro) {
		this.bOtro = bOtro;
	}

	public String getOtrasCondiciones() {
		return otrasCondiciones;
	}

	public void setOtrasCondiciones(String otrasCondiciones) {
		this.otrasCondiciones = otrasCondiciones;
	}

	public String getRequisitosAdicionales() {
		return requisitosAdicionales;
	}

	public void setRequisitosAdicionales(String requisitosAdicionales) {
		this.requisitosAdicionales = requisitosAdicionales;
	}

	public Date getFechaExpira() {
		return fechaExpira;
	}

	public void setFechaExpira(String fechaExpira) {
		this.fechaExpira = Date.valueOf(fechaExpira);
	}

	public void setFechaExpira(Date fechaExpira) {
		this.fechaExpira = fechaExpira;
	}

	public boolean isbContinuoExpira() {
		return bContinuoExpira;
	}

	public void setbContinuoExpira(String bContinuoExpira) {
		this.bContinuoExpira = Null.free(bContinuoExpira).equals("true") ? true
				: false;
	}

	public void setbContinuoExpira(boolean bContinuoExpira) {
		this.bContinuoExpira = bContinuoExpira;
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

	public String getDescArchivo1() {
		return descArchivo1;
	}

	public void setDescArchivo1(String descArchivo1) {
		this.descArchivo1 = descArchivo1;
	}

	public String getDescArchivo2() {
		return descArchivo2;
	}

	public void setDescArchivo2(String descArchivo2) {
		this.descArchivo2 = descArchivo2;
	}

	public String getDescArchivo3() {
		return descArchivo3;
	}

	public void setDescArchivo3(String descArchivo3) {
		this.descArchivo3 = descArchivo3;
	}

	public String getDescArchivo4() {
		return descArchivo4;
	}

	public void setDescArchivo4(String descArchivo4) {
		this.descArchivo4 = descArchivo4;
	}

	public String getDescArchivo5() {
		return descArchivo5;
	}

	public void setDescArchivo5(String descArchivo5) {
		this.descArchivo5 = descArchivo5;
	}

	public String getDescArchivo6() {
		return descArchivo6;
	}

	public void setDescArchivo6(String descArchivo6) {
		this.descArchivo6 = descArchivo6;
	}

	public String getDescArchivo7() {
		return descArchivo7;
	}

	public void setDescArchivo7(String descArchivo7) {
		this.descArchivo7 = descArchivo7;
	}

	public String getDescArchivo8() {
		return descArchivo8;
	}

	public void setDescArchivo8(String descArchivo8) {
		this.descArchivo8 = descArchivo8;
	}

	public String getDescArchivo9() {
		return descArchivo9;
	}

	public void setDescArchivo9(String descArchivo9) {
		this.descArchivo9 = descArchivo9;
	}

	public String getDescArchivo10() {
		return descArchivo10;
	}

	public void setDescArchivo10(String descArchivo10) {
		this.descArchivo10 = descArchivo10;
	}

	public Tractoras getTractora() {
		return tractora;
	}

	public void setTractora(Tractoras tractora) {
		this.tractora = tractora;
	}

}
