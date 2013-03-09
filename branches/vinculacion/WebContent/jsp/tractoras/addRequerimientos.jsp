<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/css/calendario.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar-es.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar-setup.js"></script>
</head>

<body>
<fieldset id="requerimientos"><legend>Captura de
Requerimientos<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio."  /></legend><br />

<s:form action="listReq" namespace="/tractora/requerimientos" theme="simple">
<s:hidden name="requerimientos.idRequerimiento" id="idReq" value="%{requerimientos.idRequerimiento}"/>
<s:hidden name="requerimientos.tipoProducto" id="idPro" value="%{requerimientos.tipoProducto}"/>

<table>
<tr>
<td style="width: 454px">
<s:label cssClass="etiquetaCaptura" value="* Producto solicitado:"  />
<s:textfield size="25" id="idCampoProducto" name="requerimientos.producto" /><br />
<s:label cssClass="etiquetaAyuda" value="Defina el producto solicitado en tres palabras."  /><br /><br />
<s:label cssClass="etiquetaCaptura" value="* Tipo de producto:" />
<s:textfield id="idCampoTipoProducto" name="requerimientos.tipoProducto" value="%{requerimientos.tipoProducto}" />
<a href="javascript:buscar();" >buscar</a><br />
<label class="agregar">${requerimientos.tipoProducto}</label><br />
<s:label cssClass="etiquetaAyuda" value="Seleccione la categoría en la cual se encuentra su producto." /><br />
<s:label cssClass="etiquetaAyuda" value="Si requiere incluir información adicional puede hacer una descripción " /><br />
<s:label cssClass="etiquetaAyuda" value="del mismo o adjuntar archivos como complemento." /><br /><br />
<s:label cssClass="etiquetaCaptura" value="Descripción:"  />
<s:textarea cols="30" id="idCampoDescripcion" name="requerimientos.descripcion" rows="2"></s:textarea><br />
<s:label cssClass="etiquetaAyuda" value="Describa el producto con mayor detalle en caso de requerirlo."  /><br /><br />
<s:label cssClass="etiquetaCaptura" value="Incluir archivo(s):"  />
<s:file id="idCampoArchivo" name="requerimientos.archivo"><label class="agregar">+ agregar otro</label></s:file> <br />
<s:label cssClass="etiquetaAyuda" value="Indique el o los archivos que serán incluidos. Máximo 2MB (.pdf .doc .png)"  /><br /><br />
<s:label cssClass="etiquetaCaptura" value="* Fecha de suministro:"  />
<input class="calendario" id="ingreso" name="igreso" onchange="limpiaCheckSuministro();" value="<s:property value='requerimientos==null||requerimientos.fechaSuministro==null?"dd-mm-yyyy":requerimientos.fechaSuministro' />" size="18"  />
<s:date id="idCampoFechaSuministro" name="requerimientos.fechaSuministro" />
<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador" style="cursor:hand" ></img>
<br />
<input class="checkbox" id="indefinido" name="persiste" onchange="limpiaFechaExpira(1);" type="checkbox" ${requerimientos.bIndefinido==true?' checked="checked"':' ' } ></input><s:label cssClass="etiquetaCaptura" value="Indefinido"  /></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="checkbox" id="variasfechas" name="persiste" onchange="limpiaFechaExpira(2);" type="checkbox" ><s:label cssClass="etiquetaCaptura" value="Varias fechas"  /></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="checkbox" id="suministrocontinuo" name="continuo" onchange="limpiaFechaExpira(3);" type="checkbox"  ${requerimientos.bContinuoSuministro==true?' checked="checked"':' ' } ><s:label cssClass="etiquetaCaptura" value="Continuo"  /></input>
<br />
<s:label cssClass="etiquetaAyuda" value="Indique la fecha de suministro o seleccione una opción."  /><br />
</td>
<td style="width: 454px">
<s:label cssClass="etiquetaCaptura" value="* Lugar de suministro:"  /><label class="agregar">+ agregar otro</label>
<select id="idCampoLugarSuministro" name="requerimientos.lugarSuministro"  >
				<option selected="selected" value="Nacional">Nacional</option>
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
<s:label cssClass="etiquetaAyuda" value="Seleccione el o los lugares de suministro."  /><br /><br />
<s:label cssClass="etiquetaCaptura" value="Condiciones de pago:"  />
<input class="checkbox" id="checkcontado" name="requerimientos.bContado" type="checkbox" onclick="contado();" ${requerimientos.bContado==true?' checked="checked"':' ' } ><s:label cssClass="etiquetaCaptura" value="Contado"  /></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="checkbox" id="checkcredito" name="checkcredito" type="checkbox" onclick="credito()"  ${requerimientos.bCredito==true?' checked="checked"':' ' } ><s:label cssClass="etiquetaCaptura" value="Crédito"  /></input><br />
<div id="plazo" ${requerimientos.bCredito==true?' style="display: block;"':' style="display: none;"' } >
	<input class="checkbox" id="checkquince" name="requerimientos.bQuince" type="checkbox" onclick="limpiaCheckCredito(15);" ${requerimientos.bQuince==true?' checked="checked"':' ' } ><s:label cssClass="etiquetaCaptura" value="15 días"  /></input>&nbsp;&nbsp;&nbsp;
	<input class="checkbox" id="checktreinta" name="requerimientos.bTreinta" type="checkbox" onclick="limpiaCheckCredito(30);" ${requerimientos.bTreinta==true?' checked="checked"':' ' } ><s:label cssClass="etiquetaCaptura" value="30 días"  /></input>&nbsp;&nbsp;&nbsp;
	<input class="checkbox" id="checksesenta" name="requerimientos.bSesenta" type="checkbox" onclick="limpiaCheckCredito(60);" ${requerimientos.bSesenta==true?' checked="checked"':' ' } ><s:label cssClass="etiquetaCaptura" value="60 días"  /></input>&nbsp;&nbsp;&nbsp;
	<input class="checkbox" id="checknoventa" name="requerimientos.bNoventa" type="checkbox" onclick="limpiaCheckCredito(90);" ${requerimientos.bNoventa==true?' checked="checked"':' ' } ><s:label cssClass="etiquetaCaptura" value="90 días"  /></input>&nbsp;&nbsp;&nbsp;
	<input class="checkbox" id="checkotro" name="requerimientos.bOtro" type="checkbox" onclick="otro();"  ${requerimientos.bOtro==true?' checked="checked"':' ' } ><s:label cssClass="etiquetaCaptura" value="otro"  /></input>&nbsp;&nbsp;&nbsp;
</div>
<s:label cssClass="etiquetaAyuda" value="Seleccione una opción."  /><br /><br />
<div id="otrasCondicionesPago" ${requerimientos.bOtro==true?' style="display: block;"':' style="display: none;"' } ><s:label cssClass="etiquetaCaptura" value="Otras condiciones:"  />
<s:textarea cols="30" id="idCampoOtrasCondiciones" name="requerimientos.otrasCondiciones" rows="2"></s:textarea><br />
<s:label cssClass="etiquetaAyuda" value="Especifique si existen otras condiciones de pago."  /><br /><br /></div>
<s:label cssClass="etiquetaCaptura" value="Requisitos adicionales:"  />
<s:textarea cols="30" id="idCampoRequisitosAdicionales" name="requerimientos.requisitosAdicionales" rows="2"></s:textarea><br />
<s:label cssClass="etiquetaAyuda" value="Describa los requisitos adicionales; tales como certificaciones, criterios de calidad, condiciones de entrega."  /><br /><br />
<s:label cssClass="etiquetaCaptura" value="* Fecha en la que expira el requerimiento:"  />
<input class="calendario" id="ingreso2" name="igreso" onchange="limpiaCheckExpira();" value="<s:property value='requerimientos==null||requerimientos.fechaExpira==null?"dd-mm-yyyy":requerimientos.fechaExpira' />" size="18"  />
<s:date id="idCampoFechaExpira" name="requerimientos.fechaExpira" />
<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16"  title="Seleccione una fecha" id="lanzador2" style="cursor:hand" ></img>
<br />
<input class="checkbox" id="expiracontinuo" name="requerimientos.bContinuoExpira" type="checkbox" onchange="limpiaFechaSuministro();"  ${requerimientos.bContinuoExpira==true?' checked="checked"':' ' }><s:label cssClass="etiquetaCaptura" value="Continuo"  /></input>
<br />
<s:label cssClass="etiquetaAyuda" value="Indique la fecha en que expira el requerimiento o si el requerimiento es continuo."  /><br />
</td>
</tr>
</table>
<table class="submit_tabla">
<tr>
<td style="width: 210px;"></td>
<td><s:submit cssClass="botonenviar" value="Cancelar" /></td>
<td><s:submit cssClass="botonenviar" value="Subir" /></td>
<td><s:submit cssClass="botonenviar" value="Guardar" /></td>
<td style="width: 210px;"></td>
</tr>
</table>

</s:form>

<s:form name="frmBuscar" action="addReq" namespace="/tractora/requerimientos" theme="simple" method="get">
	<s:hidden id="idReqIdReq" name="requerimientos.idRequerimiento"  value="%{requerimientos.idRequerimiento}"/>
	<s:hidden id="idReqTipPro" name="requerimientos.tipoProducto"  value="%{requerimientos.tipoProducto}"/>
	<s:hidden name="busqueda"  value="true"/>
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
	
	function limpiaFechaSuministro() {
		if (document.getElementById('expiracontinuo').checked==true)
			document.getElementById('ingreso2').value='dd-mm-yyyy';
	}
	
	function limpiaFechaExpira(check) {
		if (document.getElementById('indefinido').checked == true
					|| document.getElementById('variasfechas').checked == true
					|| document.getElementById('suministrocontinuo').checked == true)
				document.getElementById('ingreso').value = 'dd-mm-yyyy';
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
		if (document.getElementById('ingreso').value!='dd-mm-yyyy') {
			document.getElementById('indefinido').checked=false;
			document.getElementById('variasfechas').checked=false;
			document.getElementById('suministrocontinuo').checked=false;
		}
	}

	function limpiaCheckExpira() {
		if (document.getElementById('ingreso2').value!='dd-mm-yyyy')
			document.getElementById('expiracontinuo').checked=false;
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
		ifFormat : "%Y/%m/%d", // formato de la fecha que se escriba en el campo de texto 
		button : "lanzador" // el id del botón que lanzará el calendario 
	});
	Calendar.setup({
		inputField : "ingreso2", // id del campo de texto 
		ifFormat : "%Y/%m/%d", // formato de la fecha que se escriba en el campo de texto 
		button : "lanzador2" // el id del botón que lanzará el calendario 
	});
</script>

<div class="overlay-container">
	<div class="window-container zoomin">
		<h3>Resultados de '<s:property value="requerimientos.tipoProducto"/>'</h3> 

		<iframe id="iframeDenoMua" name="denoMua"
	src='${pageContext.request.contextPath}/tractora/requerimientos/showProd.do?requerimientos.idRequerimiento=${requerimientos.idRequerimiento}&requerimientos.tipoProducto=${requerimientos.tipoProducto}&busqueda=true'
	 width="700px" height="250px"
	frameborder="0"> </iframe>
		<span class="close">Cancelar</span>
		<s:url id="uri" action="addReq" encode="true" namespace="/tractora/requerimientos" includeContext="" />
		<s:form id="idFrmRegresar" name="frmRegresar" action="%{uri}" method="get">
			<s:hidden id="idDom" name="requerimientos.idRequerimiento" />
		</s:form>
		<script type="text/javascript">
			function regresa() {
				alert('-' + document.getElementById('idCampoTipoProducto').value);
				document.frmRegresar.submit();
			}
			function buscar() {
				document.getElementById("idReqTipPro").value=document.getElementById('idCampoTipoProducto').value;
				document.getElementById("idReqIdReq").value=document.getElementById('idReq').value;
				//document.getElementById("idBusq").value=true;
			    document.frmBuscar.submit();
			}
		</script>
	</div>
	<div class="window-container zoomout">
		<h3>Titulo 2</h3> 
		Texto de la ventana emergente<br/>
		<br/>
		<span class="close">Cerrar</span>
	</div>
</div>

<script>!window.jQuery && document.write(unescape('%3Cscript src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"%3E%3C/script%3E'))</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/demo.js"></script>
<s:if test="%{busqueda=='true'}">
	<input type="button" id="idBtnBuscar" value="" class="button" style="position: absolute; margin-top: -500px; display: none;" data-type="zoomin" />
	<script type="text/javascript">
		setTimeout ("breakOut()", 190);
		function breakOut() {
			document.getElementById('idBtnBuscar').click();
		}
	</script>
</s:if>
</body>

</html>