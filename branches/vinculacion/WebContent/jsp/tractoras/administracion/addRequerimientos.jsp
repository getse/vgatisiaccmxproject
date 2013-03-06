<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="<%=request.getContextPath()%>/css/calendario.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/calendar.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/calendar-es.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/calendar-setup.js"></script>
</head>

<body>
<fieldset id="requerimientos"><legend>Captura de
Requerimientos<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio."  /></legend><br />

<s:form action="respuesta" namespace="/" theme="simple">
<table>
<tr>
<td>
<s:label cssClass="etiquetaCaptura" value="* Producto solicitado:"  />
<s:textfield size="25"></s:textfield><br />
<s:label cssClass="etiquetaAyuda" value="Defina el producto solicitado en tres palabras."  /><br /><br />
<s:label cssClass="etiquetaCaptura" value="* Tipo de producto:"  />
<select id="sector" name="sector" >
		<option selected="selected">Seleccione el sector</option>
		<option>Agricultura, cr&iacute;a y explotaci&oacute;n de animales, aprovechamiento...</option>
		<option>Miner&iacute;a</option>
		<option>Generaci&oacute;n, transmisi&oacute;n y distribuci&oacute;n de energ&iacute;a el&eacute;ctrica...</option>
		<option>Construcci&oacute;n</option>
		<option>Industrias manufactureras</option>
		<option>Comercio al por mayor</option>
		<option>Comercio al por menor</option>
		<option>Transportes, correos y almacenamiento</option>
		<option>Informaci&oacute;n en medios masivos</option>
		<option>Servicios financieros y de seguros</option>
		<option>Servicios inmobiliarios y de alquiler de bienes muebles...</option>
		<option>Servicios profesionales, cient&iacute;ficos y t&eacute;cnicos</option>
		<option>Corporativos</option>
		<option>Servicios de apoyo a los negocios y manejo de desechos...</option>
		<option>Servicios educativos</option>
		<option>Servicios de salud y de asistencia social</option>
		<option>Servicios de esparcimiento culturales y deportivos...</option>
		<option>Servicios de alojamiento temporal y de preparaci&oacute;n...</option>
		<option>Otros servicios excepto actividades gubernamentales</option>
</select><br />
<s:label cssClass="etiquetaAyuda" value="Seleccione la categoría en la cual se encuentra su producto." /><br />
<s:label cssClass="etiquetaAyuda" value="Si requiere incluir información adicional puede hacer una descripción " /><br />
<s:label cssClass="etiquetaAyuda" value="del mismo o adjuntar archivos como complemento." /><br /><br />
<s:label cssClass="etiquetaCaptura" value="Descripción:"  />
<s:textarea cols="27" rows="1"></s:textarea><br />
<s:label cssClass="etiquetaAyuda" value="Describa el producto con mayor detalle en caso de requerirlo."  /><br /><br />
<s:label cssClass="etiquetaCaptura" value="Incluir archivo(s):"  />
<s:file ><label class="agregar">+ agregar otro</label></s:file> <br />
<s:label cssClass="etiquetaAyuda" value="Indique el o los archivos que serán incluidos. Máximo 2MB (.pdf .doc .png)"  /><br /><br />
<s:label cssClass="etiquetaCaptura" value="* Fecha de suministro:"  />
<input class="calendario" id="ingreso" name="igreso" value="dd-mm-yyyy" size="18"  />
<img src="<%=request.getContextPath()%>/img/calendario.png" width="16" height="16" border="0" title="Seleccione una fecha" id="lanzador" style="cursor:hand" ></img>
<br />
<input class="checkbox" id="persiste" name="persiste" type="checkbox" ><s:label cssClass="etiquetaCaptura" value="Indefinido"  /></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="checkbox" id="persiste" name="persiste" type="checkbox" ><s:label cssClass="etiquetaCaptura" value="Varias fechas"  /></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="checkbox" id="continuo" name="continuo" type="checkbox" ><s:label cssClass="etiquetaCaptura" value="Continuo"  /></input>
<br />
<s:label cssClass="etiquetaAyuda" value="Indique la fecha de suministro o seleccione una opción."  /><br />
</td>
<td>
<s:label cssClass="etiquetaCaptura" value="* Lugar de suministro:"  /><label class="agregar">+ agregar otro</label>
<select id="lugarsuministro" name="lugarsuministro"  >
				<option selected="selected" >Nacional</option>
				<option>Aguascalientes</option>
				<option>Baja California</option>
				<option>Baja California Sur</option>
				<option>Campeche</option>
				<option>Chiapas</option>
				<option>Chihuahua</option>
				<option>Coahuila</option>
				<option>Colima</option>
				<option>Distrito Federal</option>
				<option>Durango</option>
				<option>Guanajuato</option>
				<option>Guerrero</option>
				<option>Hidalgo</option>
				<option>Jalisco</option>
				<option>M&eacute;xico</option>
				<option>Michoac&oacute;n</option>
				<option>Morelos</option>
				<option>Nayarit</option>
				<option>Nuevo Le&oacute;n</option>
				<option>Oaxaca</option>
				<option>Puebla</option>
				<option>Quer&eacute;taro</option>
				<option>Quintana Roo</option>
				<option>San Luis Potos&iacute;</option>
				<option>Sinaloa</option>
				<option>Sonora</option>
				<option>Tabasco</option>
				<option>Tamaulipas</option>
				<option>Tlaxcala</option>
				<option>Veracruz</option>
				<option>Yucat&aacute;n</option>
				<option>Zacatecas</option>
			</select><br />
<s:label cssClass="etiquetaAyuda" value="Seleccione el o los lugares de suministro."  /><br /><br />
<s:label cssClass="etiquetaCaptura" value="Condiciones de pago:"  />
<input class="checkbox" id="checkcontado" name="checkcontado" type="checkbox" onclick="contado();" ><s:label cssClass="etiquetaCaptura" value="Contado"  /></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="checkbox" id="checkcredito" name="checkcredito" type="checkbox" onclick="credito()"><s:label cssClass="etiquetaCaptura" value="Crédito"  /></input><br />
<div id="plazo" style="display: none;"><input class="checkbox" id="checkquince" name="checkquince" type="checkbox" ><s:label cssClass="etiquetaCaptura" value="15 días"  /></input>&nbsp;&nbsp;&nbsp;<input class="checkbox" id="checktreinta" name="checktreinta" type="checkbox" ><s:label cssClass="etiquetaCaptura" value="30 días"  /></input>&nbsp;&nbsp;&nbsp;<input class="checkbox" id="checksesenta" name="checksesenta" type="checkbox" ><s:label cssClass="etiquetaCaptura" value="60 días"  /></input>&nbsp;&nbsp;&nbsp;<input class="checkbox" id="checknoventa" name="checknoventa" type="checkbox" ><s:label cssClass="etiquetaCaptura" value="90 días"  /></input>&nbsp;&nbsp;&nbsp;<input class="checkbox" id="checkotro" name="checkotro" type="checkbox" onclick="otro();"><s:label cssClass="etiquetaCaptura" value="otro"  /></input>&nbsp;&nbsp;&nbsp;</div>
<s:label cssClass="etiquetaAyuda" value="Seleccione una opción."  /><br /><br />
<div id="otrasCondicionesPago" style="display: none;"><s:label cssClass="etiquetaCaptura" value="Otras condiciones:"  />
<s:textarea cols="30" rows="1"></s:textarea><br />
<s:label cssClass="etiquetaAyuda" value="Especifique si existen otras condiciones de pago."  /><br /><br /></div>
<s:label cssClass="etiquetaCaptura" value="Requisitos adicionales:"  />
<s:textarea cols="27" rows="1"></s:textarea><br />
<s:label cssClass="etiquetaAyuda" value="Describa los requisitos adicionales; tales como certificaciones, criterios de calidad, condiciones de entrega."  /><br /><br />
<s:label cssClass="etiquetaCaptura" value="* Fecha en la que expira el requerimiento:"  />
<input class="calendario" id="ingreso2" name="igreso" value="dd-mm-yyyy" size="18"  />
<img src="<%=request.getContextPath()%>/img/calendario.png" width="16" height="16" border="0" title="Seleccione una fecha" id="lanzador2" style="cursor:hand" ></img>
<br />
<input class="checkbox" id="expiracontinuo" name="expiracontinuo" type="checkbox" ><s:label cssClass="etiquetaCaptura" value="Continuo"  /></input>
<br />
<s:label cssClass="etiquetaAyuda" value="Indique la fecha en que expira el requerimiento o si el requerimiento es continuo."  /><br />
</td>
</tr>
</table>
<table align="center">
<tr>
<td><s:submit cssClass="botonenviar" value="Cancelar" /></td>
<td><s:submit cssClass="botonenviar" value="Subir" /></td>
<td><s:submit cssClass="botonenviar" value="Guardar" /></td>
</tr>
</table>

</s:form>

</fieldset>
<script type="text/javascript">
function contado() {
	document.getElementById('checkcredito').checked=false;
	document.getElementById('checkquince').checked=false;
	document.getElementById('checktreinta').checked=false;
	document.getElementById('checksesenta').checked=false;
	document.getElementById('checknoventa').checked=false;
	document.getElementById('checkotro').checked=false;
	document.getElementById('plazo').style.display='none';
	document.getElementById('otrasCondicionesPago').style.display='none';
}

function credito() {
	document.getElementById('checkcontado').checked=false;
	if (document.getElementById('checkcredito').checked==false) {
		document.getElementById('plazo').style.display='none';
		document.getElementById('otrasCondicionesPago').style.display='none';
	} else {
		if (document.getElementById('checkotro').checked==true) {
			document.getElementById('otrasCondicionesPago').style.display='block';
		}
		document.getElementById('plazo').style.display='block';
	}
}

function otro() {
	if (document.getElementById('checkotro').checked==false) {
		document.getElementById('otrasCondicionesPago').style.display='none';
	} else {
		document.getElementById('otrasCondicionesPago').style.display='block';
	}
	document.getElementById('checkquince').checked=false;
	document.getElementById('checktreinta').checked=false;
	document.getElementById('checksesenta').checked=false;
	document.getElementById('checknoventa').checked=false;
}

	Calendar.setup({
		inputField : "ingreso", // id del campo de texto 
		ifFormat : "%d-%m-%Y", // formato de la fecha que se escriba en el campo de texto 
		button : "lanzador" // el id del botón que lanzará el calendario 
	});
	Calendar.setup({
		inputField : "ingreso2", // id del campo de texto 
		ifFormat : "%d-%m-%Y", // formato de la fecha que se escriba en el campo de texto 
		button : "lanzador2" // el id del botón que lanzará el calendario 
	});
</script>
</body>

</html>