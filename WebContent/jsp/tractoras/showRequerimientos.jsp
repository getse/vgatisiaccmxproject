<?xml version="1.0" encoding="UTF-8"?>
<%@taglib
	uri="/struts-tags"
	prefix="s"%>
<%@page
	pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html
	xmlns="http://www.w3.org/1999/xhtml"
	xml:lang="es">
<head>
<meta
	http-equiv="Content-Type"
	content="text/html; charset=UTF-8" />
<link
	href="${pageContext.request.contextPath}/css/calendario.css"
	rel="stylesheet"
	type="text/css" />
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar.js"></script>
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar-es.js"></script>
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar-setup.js"></script>
</head>
<body>
<fieldset id="requerimientos"><legend>Captura de Requerimientos<s:label
	cssClass="camposObligatorios"
	value="Los campos marcados con asterisco(*) son de caracter obligatorio." /></legend><br />
<s:form
	action="listReq"
	namespace="/tractora/requerimientos"
	theme="simple">
	<s:hidden
		name="requerimientos.idRequerimiento"
		id="idIdReq"
		value="%{requerimientos.idRequerimiento}" />
	<s:hidden
		name="requerimientos.idTractora"
		id="idIdTra"
		value="%{requerimientos.idTractora}" />
	<s:hidden
		name="requerimientos.cveScian"
		id="idCveSci"
		value="%{requerimientos.cveScian}" />
	<s:hidden
		name="requerimientos.tipoProducto"
		id="idTipoPro"
		value="%{requerimientos.tipoProducto}" />
	<table>
		<tr>
			<td style="width: 454px"><s:label
				cssClass="etiquetaCaptura"
				value="* Producto solicitado:" /> <s:textfield
				size="25"
				id="idCampoProducto"
				name="requerimientos.producto" /><br />
			<s:label
				cssClass="etiquetaAyuda"
				value="Defina el producto solicitado en tres palabras." /><br />
			<br />
			<s:label
				cssClass="etiquetaCaptura"
				value="* Tipo de producto:" /> <s:textfield
				id="idCampoBusqueda"
				name="requerimientos.busqueda"
				value="%{requerimientos.busqueda}" /> <a href="javascript:buscar();">buscar</a><br />
			<label class="resultado">${requerimientos.tipoProducto}</label><br />
			<s:label
				cssClass="etiquetaAyuda"
				value="Seleccione la categoría en la cual se encuentra su producto." /><br />
			<s:label
				cssClass="etiquetaAyuda"
				value="Si requiere incluir información adicional puede hacer una descripción " /><br />
			<s:label
				cssClass="etiquetaAyuda"
				value="del mismo o adjuntar archivos como complemento." /><br />
			<br />
			<s:label
				cssClass="etiquetaCaptura"
				value="Descripción:" /> <s:textarea
				cols="30"
				id="idCampoDescripcion"
				name="requerimientos.descripcion"
				rows="2"></s:textarea><br />
			<s:label
				cssClass="etiquetaAyuda"
				value="Describa el producto con mayor detalle en caso de requerirlo." /><br />
			<br />
			<s:label
				cssClass="etiquetaCaptura"
				value="Incluir archivo(s):" /> <s:file
				id="idCampoArchivo"
				name="requerimientos.archivo">
				<label class="agregar">+ agregar otro</label>
			</s:file> <br />
			<s:label
				cssClass="etiquetaAyuda"
				value="Indique el o los archivos que serán incluidos. Máximo 2MB (.pdf .doc .png)" /><br />
			<br />
			<s:label
				cssClass="etiquetaCaptura"
				value="* Fecha de suministro:" /> <s:date
				name="requerimientos.fechaSuministro"
				id="fSuministro"
				format="dd/MM/yyyy" /> <s:textfield
				class="calendario"
				id="ingreso"
				name="requerimientos.fechaSuministro"
				value="%{fSuministro}"
				onchange="limpiaCheckSuministro();"
				size="10"
				maxlength="10" /> <img
				src="${pageContext.request.contextPath}/img/calendario.png"
				width="16"
				height="16"
				title="Seleccione una fecha"
				id="lanzador"
				style="cursor: hand"></img><br />
			<s:checkbox
				id="indefinido"
				name="requerimientos.bIndefinido"
				onchange="limpiaFechaExpira(1);"
				value="%{requerimientos.bIndefinido}" /> <s:label
				cssClass="etiquetaCaptura"
				value="Indefinido" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:checkbox
				id="variasfechas"
				name="requerimientos.bVariasFechas"
				onchange="limpiaFechaExpira(2);"
				value="%{requerimientos.bVariasFechas}" /> <s:label
				cssClass="etiquetaCaptura"
				value="Varias fechas" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:checkbox
				id="suministrocontinuo"
				name="requerimientos.bContinuoSuministro"
				onchange="limpiaFechaExpira(3);"
				value="%{requerimientos.bContinuoSuministro}" /> <s:label
				cssClass="etiquetaCaptura"
				value="Continuo" /> <br />
			<s:label
				cssClass="etiquetaAyuda"
				value="Indique la fecha de suministro o seleccione una opción." /><br />
			</td>
			<td style="width: 454px"><s:label
				cssClass="etiquetaCaptura"
				value="* Lugar de suministro:" /><label class="agregar">+ agregar otro</label> <select
				id="idCampoLugarSuministro"
				name="requerimientos.lugarSuministro">
				<option
					selected="selected"
					value="Nacional">Nacional</option>
				<option value="Aguascalientes">Aguascalientes</option>
				<option value="Baja California">Baja California</option>
				<option value="Baja California Sur">Baja California Sur</option>
				<option value="Campeche">Campeche</option>
				<option value="Chiapas">Chiapas</option>
				<option value="Chihuahua">Chihuahua</option>
				<option value="Coahuila">Coahuila</option>
				<option value="Colima">Colima</option>
				<option value="Distrito Federal">Distrito Federal</option>
				<option value="Durango">Durango</option>
				<option value="Guanajuato">Guanajuato</option>
				<option value="Guerrero">Guerrero</option>
				<option value="Hidalgo">Hidalgo</option>
				<option value="Jalisco">Jalisco</option>
				<option value="Mexico">Mexico</option>
				<option value="Michoacan">Michoacan</option>
				<option value="Morelos">Morelos</option>
				<option value="Nayarit">Nayarit</option>
				<option value="Nuevo Leon">Nuevo Leon</option>
				<option value="Oaxaca">Oaxaca</option>
				<option value="Puebla">Puebla</option>
				<option value="Quertaro">Quertaro</option>
				<option value="Quintana Roo">Quintana Roo</option>
				<option value="San Luis Potosi">San Luis Potosi</option>
				<option value="Sinaloa">Sinaloa</option>
				<option value="Sonora">Sonora</option>
				<option value="Tabasco">Tabasco</option>
				<option value="Tamaulipas">Tamaulipas</option>
				<option value="Tlaxcala">Tlaxcala</option>
				<option value="Veracruz">Veracruz</option>
				<option value="Yucatan">Yucatan</option>
				<option value="Zacatecas">Zacatecas</option>
			</select><br />
			<s:label
				cssClass="etiquetaAyuda"
				value="Seleccione el o los lugares de suministro." /><br />
			<br />
			<s:label
				cssClass="etiquetaCaptura"
				value="Condiciones de pago:" /> <s:checkbox
				id="checkcontado"
				name="requerimientos.bContado"
				onclick="contado();"
				value="%{requerimientos.bContado}" /> <s:label
				cssClass="etiquetaCaptura"
				value="Contado" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:checkbox
				id="checkcredito"
				name="requerimientos.bCredito"
				onclick="credito();"
				value="%{requerimientos.bCredito}" /> <s:label
				cssClass="etiquetaCaptura"
				value="Crédito" /><br />
			<div
				id="plazo"
				${requerimientos.bCredito==true?
				' style="display: block;"
				':' style="display: none;"' } ><s:checkbox
				id="checkquince"
				name="requerimientos.bQuince"
				onclick="limpiaCheckCredito(15);"
				value="%{requerimientos.bQuince}" /> <s:label
				cssClass="etiquetaCaptura"
				value="15 días" />&nbsp;&nbsp;&nbsp; <s:checkbox
				id="checktreinta"
				name="requerimientos.bTreinta"
				onclick="limpiaCheckCredito(30);"
				value="%{requerimientos.bTreinta}" /> <s:label
				cssClass="etiquetaCaptura"
				value="30 días" />&nbsp;&nbsp;&nbsp; <s:checkbox
				id="checksesenta"
				name="requerimientos.bSesenta"
				onclick="limpiaCheckCredito(60);"
				value="%{requerimientos.bSesenta}" /> <s:label
				cssClass="etiquetaCaptura"
				value="60 días" />&nbsp;&nbsp;&nbsp; <s:checkbox
				id="checknoventa"
				name="requerimientos.bNoventa"
				onclick="limpiaCheckCredito(90);"
				value="%{requerimientos.bNoventa}" /> <s:label
				cssClass="etiquetaCaptura"
				value="90 días" />&nbsp;&nbsp;&nbsp; <s:checkbox
				id="checkotro"
				name="requerimientos.bOtro"
				onclick="otro();"
				value="%{requerimientos.bOtro}" /> <s:label
				cssClass="etiquetaCaptura"
				value="otro" />&nbsp;&nbsp;&nbsp;</div>
			<s:label
				cssClass="etiquetaAyuda"
				value="Seleccione una opción." /><br />
			<br />
			<div
				id="otrasCondicionesPago"
				${requerimientos.bOtro==true?
				' style="display: block;"
				':' style="display: none;"' } ><s:label
				cssClass="etiquetaCaptura"
				value="Otras condiciones:" /> <s:textarea
				cols="30"
				id="idCampoOtrasCondiciones"
				name="requerimientos.otrasCondiciones"
				rows="2"></s:textarea><br />
			<s:label
				cssClass="etiquetaAyuda"
				value="Especifique si existen otras condiciones de pago." /><br />
			<br />
			</div>
			<s:label
				cssClass="etiquetaCaptura"
				value="Requisitos adicionales:" /> <s:textarea
				cols="30"
				id="idCampoRequisitosAdicionales"
				name="requerimientos.requisitosAdicionales"
				rows="2"></s:textarea><br />
			<s:label
				cssClass="etiquetaAyuda"
				value="Describa los requisitos adicionales; tales como certificaciones, criterios de calidad, condiciones de entrega." /><br />
			<br />
			<s:label
				cssClass="etiquetaCaptura"
				value="* Fecha en la que expira el requerimiento:" /> <s:date
				name="requerimientos.fechaExpira"
				id="fExpira"
				format="dd/MM/yyyy" /> <s:textfield
				class="calendario"
				id="ingreso2"
				name="requerimientos.fechaExpira"
				value="%{fExpira}"
				onchange="limpiaCheckExpira();"
				size="10"
				maxlength="10" /> <img
				src="${pageContext.request.contextPath}/img/calendario.png"
				width="16"
				height="16"
				title="Seleccione una fecha"
				id="lanzador2"
				style="cursor: hand"></img><br />
			<s:checkbox
				id="expiracontinuo"
				name="requerimientos.bContinuoExpira"
				onchange="limpiaFechaSuministro();"
				value="%{requerimientos.bContinuoExpira}" /> <s:label
				cssClass="etiquetaCaptura"
				value="Continuo" /> <br />
			<s:label
				cssClass="etiquetaAyuda"
				value="Indique la fecha en que expira el requerimiento o si el requerimiento es continuo." /><br />
			</td>
		</tr>
	</table>
	<table class="submit_tabla">
		<tr>
			<td style="width: 210px;"></td>
			<td><s:submit
				cssClass="botonenviar"
				value="Cancelar" /></td>
			<td><s:submit
				cssClass="botonenviar"
				value="Subir" /></td>
			<td><s:submit
				cssClass="botonenviar"
				value="Guardar" /></td>
			<td style="width: 210px;"></td>
		</tr>
	</table>
</s:form> <s:form
	name="frmBuscar"
	action="addReq"
	namespace="/tractora/requerimientos"
	theme="simple"
	method="get">
	<s:hidden
		id="idIdReqidIdReq"
		name="requerimientos.idRequerimiento"
		value="%{requerimientos.idRequerimiento}" />
	<s:hidden
		name="requerimientos.idTractora"
		id="idIdTraidIdTra"
		value="%{requerimientos.idTractora}" />
	<s:hidden
		name="requerimientos.producto"
		id="idProidPro"
		value="%{requerimientos.producto}" />
	<s:hidden
		id="idReqBusidReqBus"
		name="requerimientos.busqueda"
		value="%{requerimientos.busqueda}" />
	<s:hidden
		name="requerimientos.cveScian"
		id="idCveSciidCveSci"
		value="%{requerimientos.cveScian}" />
	<s:hidden
		name="requerimientos.descripcion"
		id="idDesidDes"
		value="%{requerimientos.descripcion}" />
	<s:hidden
		name="requerimientos.fechaSuministro"
		id="idFecSumidFecSum"
		value="%{requerimientos.fechaSuministro}" />
	<s:hidden
		name="requerimientos.bIndefinido"
		id="idBIndidBInd"
		value="%{requerimientos.bIndefinido}" />
	<s:hidden
		name="requerimientos.bVariasFechas"
		id="idBVaridBVar"
		value="%{requerimientos.bVariasFechas}" />
	<s:hidden
		name="requerimientos.bContinuoSuministro"
		id="idBConSumidBConSum"
		value="%{requerimientos.bContinuoSuministro}" />
	<s:hidden
		name="requerimientos.lugarSuministro"
		id="idBConSumidBConSum"
		value="%{requerimientos.lugarSuministro}" />
	<s:hidden
		name="requerimientos.bContado"
		id="idBConidBCon"
		value="%{requerimientos.bContado}" />
	<s:hidden
		name="requerimientos.bCredito"
		id="idBCreidBCre"
		value="%{requerimientos.bCredito}" />
	<s:hidden
		name="requerimientos.bQuince"
		id="idBQuiidBQui"
		value="%{requerimientos.bQuince}" />
	<s:hidden
		name="requerimientos.bTreinta"
		id="idBTreidBTre"
		value="%{requerimientos.bTreinta}" />
	<s:hidden
		name="requerimientos.bSesenta"
		id="idBSesidBSes"
		value="%{requerimientos.bSesenta}" />
	<s:hidden
		name="requerimientos.bNoventa"
		id="idBNovidBNov"
		value="%{requerimientos.bNoventa}" />
	<s:hidden
		name="requerimientos.bOtro"
		id="idBOtridBOtr"
		value="%{requerimientos.bOtro}" />
	<s:hidden
		name="requerimientos.otrasCondiciones"
		id="idOtrConidOtrCon"
		value="%{requerimientos.otrasCondiciones}" />
	<s:hidden
		name="requerimientos.requisitosAdicionales"
		id="idReqAdicidReqAdic"
		value="%{requerimientos.requisitosAdicionales}" />
	<s:hidden
		name="requerimientos.fechaExpira"
		id="idFecExpidFecExp"
		value="%{requerimientos.fechaExpira}" />
	<s:hidden
		name="requerimientos.bContinuoExpira"
		id="idBConExpidBConExp"
		value="%{requerimientos.bContinuoExpira}" />
	<s:hidden
		name="requerimientos.archivo"
		id="idArcidArc"
		value="%{requerimientos.archivo}" />
	<s:hidden
		id="idBus"
		name="busqueda"
		value="true" />
</s:form> <s:form
	name="frmGuarda"
	action="listReq"
	namespace="/tractora/requerimientos"
	theme="simple"
	method="get">
	<s:hidden
		name="requerimientos.idRequerimiento"
		id="idIdReqG"
		value="%{requerimientos.idRequerimiento}" />
	<s:hidden
		name="requerimientos.idTractora"
		id="idIdTraG"
		value="%{requerimientos.idTractora}" />
	<s:hidden
		name="requerimientos.cveScian"
		id="idCveSciG"
		value="%{requerimientos.cveScian}" />
	<s:hidden
		name="requerimientos.tipoProducto"
		id="idTipoProG"
		value="%{requerimientos.tipoProducto}" />
	<s:hidden
		name="requerimientos.fechaSuministro"
		id="idFecSumG"
		value="%{requerimientos.fechaSuministro}" />
	<s:hidden
		name="requerimientos.fechaSuministro"
		id="idFecExpG"
		value="%{requerimientos.fechaExpira}" />
</s:form></fieldset>
<script type="text/javascript">
	function guarda() {
		document.getElementById('idFecSumG').value = document
				.getElementById('ingreso').value;
		document.getElementById('idFecExpG').value = document
				.getElementById('ingreso2').value;
		document.frmGuarda.submit();
	}

	function contado() {
		document.getElementById('checkcredito').checked = false;
		document.getElementById('checkquince').checked = false;
		document.getElementById('checktreinta').checked = false;
		document.getElementById('checksesenta').checked = false;
		document.getElementById('checknoventa').checked = false;
		document.getElementById('checkotro').checked = false;
		document.getElementById('plazo').style.display = 'none';
		document.getElementById('otrasCondicionesPago').style.display = 'none';
	}

	function credito() {
		document.getElementById('checkcontado').checked = false;
		if (document.getElementById('checkcredito').checked == false) {
			document.getElementById('plazo').style.display = 'none';
			document.getElementById('otrasCondicionesPago').style.display = 'none';
		} else {
			if (document.getElementById('checkotro').checked == true) {
				document.getElementById('otrasCondicionesPago').style.display = 'block';
			}
			document.getElementById('plazo').style.display = 'block';
		}
	}

	function limpiaFechaSuministro() {
		if (document.getElementById('expiracontinuo').checked == true)
			document.getElementById('ingreso2').value = '';
	}

	function limpiaFechaExpira(check) {
		if (document.getElementById('indefinido').checked == true
				|| document.getElementById('variasfechas').checked == true
				|| document.getElementById('suministrocontinuo').checked == true)
			document.getElementById('ingreso').value = '';
		if (check == '1') {
			document.getElementById('variasfechas').checked = false;
			document.getElementById('suministrocontinuo').checked = false;
		}
		if (check == '2') {
			document.getElementById('indefinido').checked = false;
			document.getElementById('suministrocontinuo').checked = false;
		}
		if (check == '3') {
			document.getElementById('indefinido').checked = false;
			document.getElementById('variasfechas').checked = false;
		}
	}

	function limpiaCheckSuministro() {
		if (document.getElementById('ingreso').value != '') {
			document.getElementById('indefinido').checked = false;
			document.getElementById('variasfechas').checked = false;
			document.getElementById('suministrocontinuo').checked = false;
		}
	}

	function limpiaCheckExpira() {
		if (document.getElementById('ingreso2').value != '')
			document.getElementById('expiracontinuo').checked = false;
	}

	function limpiaCheckCredito(check) {
		document.getElementById('otrasCondicionesPago').style.display = 'none';
		if (check == '15') {
			document.getElementById('checktreinta').checked = false;
			document.getElementById('checksesenta').checked = false;
			document.getElementById('checknoventa').checked = false;
			document.getElementById('checkotro').checked = false;
		}
		if (check == '30') {
			document.getElementById('checkquince').checked = false;
			document.getElementById('checksesenta').checked = false;
			document.getElementById('checknoventa').checked = false;
			document.getElementById('checkotro').checked = false;
		}
		if (check == '60') {
			document.getElementById('checkquince').checked = false;
			document.getElementById('checktreinta').checked = false;
			document.getElementById('checknoventa').checked = false;
			document.getElementById('checkotro').checked = false;
		}
		if (check == '90') {
			document.getElementById('checkquince').checked = false;
			document.getElementById('checktreinta').checked = false;
			document.getElementById('checksesenta').checked = false;
			document.getElementById('checkotro').checked = false;
		}
	}

	function otro() {
		if (document.getElementById('checkotro').checked == false) {
			document.getElementById('otrasCondicionesPago').style.display = 'none';
		} else {
			document.getElementById('otrasCondicionesPago').style.display = 'block';
		}
		document.getElementById('checkquince').checked = false;
		document.getElementById('checktreinta').checked = false;
		document.getElementById('checksesenta').checked = false;
		document.getElementById('checknoventa').checked = false;
	}

	Calendar.setup({
		inputField : "ingreso", // id del campo de texto 
		ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el campo de texto 
		button : "lanzador" // el id del botón que lanzará el calendario 
	});
	Calendar.setup({
		inputField : "ingreso2", // id del campo de texto 
		ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el campo de texto 
		button : "lanzador2" // el id del botón que lanzará el calendario 
	});
</script>
<div class="overlay-container">
<div class="window-container zoomin">
<h3>Resultados de '<s:property value="requerimientos.busqueda" />'</h3>
<s:if test="%{busqueda!=null}">
	<iframe
		id="iframeDenoMua"
		name="denoMua"
		src='${pageContext.request.contextPath}/tractora/requerimientos/showPro.do?requerimientos.idRequerimiento=${requerimientos.idRequerimiento}&requerimientos.idTractora=${requerimientos.idTractora}&requerimientos.producto=${requerimientos.producto}&requerimientos.busqueda=${requerimientos.busqueda}&requerimientos.cveScian=${requerimientos.cveScian}&requerimientos.descripcion=${requerimientos.descripcion}&requerimientos.fechaSuministro=${requerimientos.fechaSuministro}&requerimientos.bIndefinido=${requerimientos.bIndefinido}&requerimientos.bVariasFechas=${requerimientos.bVariasFechas}&requerimientos.bContinuoSuministro=${requerimientos.bContinuoSuministro}&requerimientos.lugarSuministro=${requerimientos.lugarSuministro}&requerimientos.bContado=${requerimientos.bContado}&requerimientos.bCredito=${requerimientos.bCredito}&requerimientos.bQuince=${requerimientos.bQuince}&requerimientos.bTreinta=${requerimientos.bTreinta}&requerimientos.bSesenta=${requerimientos.bSesenta}&requerimientos.bNoventa=${requerimientos.bNoventa}&requerimientos.bOtro=${requerimientos.bOtro}&requerimientos.otrasCondiciones=${requerimientos.otrasCondiciones}&requerimientos.requisitosAdicionales=${requerimientos.requisitosAdicionales}&requerimientos.fechaExpira=${requerimientos.fechaExpira}&requerimientos.bContinuoExpira=${requerimientos.bContinuoExpira}&requerimientos.archivo=${requerimientos.archivo}&busqueda=true&resultados=false'
		width="700px"
		height="250px"
		frameborder="0"> </iframe>
</s:if> <span class="close">Cancelar</span> <s:url
	id="uri"
	action="addReq"
	encode="true"
	namespace="/tractora/requerimientos"
	includeContext="" /> <script type="text/javascript">
	function buscar() {
		document.getElementById('idBus').value = document
				.getElementById('idCampoBusqueda').value;
		document.getElementById('idReqBusidReqBus').value = document
				.getElementById('idCampoBusqueda').value;
		document.frmBuscar.submit();
	}
</script></div>
<div class="window-container zoomout">
<h3>Titulo 2</h3>
Texto de la ventana emergente<br />
<br />
<span class="close">Cerrar</span></div>
</div>
<script>
	!window.jQuery
			&& document
					.write(unescape('%3Cscript src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"%3E%3C/script%3E'))
</script>
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/demo.js"></script>
<s:if test="%{busqueda!=null}">
	<input
		type="button"
		id="idBtnBuscar"
		value=""
		class="button"
		style="position: absolute; margin-top: -500px; display: none;"
		data-type="zoomin" />
	<script type="text/javascript">
	setTimeout("breakOut()", 190);
	function breakOut() {
		document.getElementById('idBtnBuscar').click();
	}
</script>
</s:if>
</body>
</html>