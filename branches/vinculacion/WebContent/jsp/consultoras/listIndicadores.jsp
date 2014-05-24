<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/serviciosPyMEs.js"></script>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-225px auto 0 250px';
</script>
<script
	src="${pageContext.request.contextPath}/js/ayudas.js"
	type="text/javascript"></script>
</head>
<body>
<fieldset id="requerimientos">
		<s:form 
			id="seguimientoCompleta"
			action="consultorIndicadoresShow" 
			namespace="/consultor" 
			enctype="multipart/form-data"  method="post" 
			theme="simple" >
			<s:hidden name="servConsultoria.idConsultoria" id="idConsultoria" value="%{servConsultoria.idConsultoria}" />
			<s:hidden name="servConsultoria.idUsuario" id="idUsuario" value="%{servConsultoria.idUsuario}" />
			<legend>
				<s:label value="Seguimiento de consultor." />
				<br /><br />
				<s:label cssClass="camposObligatorios"
					value="Los campos marcados con asterisco(*) son de caracter obligatorio." /><br/><br/>
			</legend>
			<div id="inicial" style="display: block;">
				<table>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="Fecha de inico :" /></td>
						<td>
								<s:date name="servConsultoria.inicio" id="fCert" format="dd/MM/yyyy" />
								<s:textfield class="calendario" id="ingreso" name="servConsultoria.inicio" value="%{fCert}" size="10" maxlength="10" />
						   		<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador" style="cursor: hand" />
						</td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="Fecha de termino:" /></td>
						<td>
								<s:date name="servConsultoria.termino" id="fCert2" format="dd/MM/yyyy" />
								<s:textfield class="calendario" id="ingreso2" name="servConsultoria.termino" value="%{fCert2}" size="10" maxlength="10" />
						   		<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador2" style="cursor: hand" />
						</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
					
					<td><s:label cssClass="etiquetaCaptura" value="Seguimiento:" /></td>
						<td>
							<select id="seguimiento" name="servConsultoria.estatus"
								onfocus="javascript:ayudasHelp(0);" 
								onblur="javascript:ayudasHelpBlo(0);">
								<option value="-1">--Seleccione--</option>
								<option value="DIAGNOSTICO" <s:if test="%{'DIAGNOSTICO'==servConsultoria.estatus}">selected="selected"</s:if>>
									Diagnóstico</option>
								<option value="PLAN DE MEJORA" <s:if test="%{'PLAN DE MEJORA'==servConsultoria.estatus}">selected="selected"</s:if>>
									Plan de mejora</option>
								<option value="IMPLEMENTACION" <s:if test="%{'IMPLEMENTACION'==servConsultoria.estatus}">selected="selected"</s:if>>
									Implementación</option>								
								<option value="EVALUACION" <s:if test="%{'EVALUACION'==servConsultoria.estatus}">selected="selected"</s:if>>
									Evaluación</option>
								<option value="CIERRE" <s:if test="%{'CIERRE'==servConsultoria.estatus}">selected="selected"</s:if>>
									Cierre</option>
								<option value="CANCELADA" <s:if test="%{'CANCELADA'==servConsultoria.estatus}">selected="selected"</s:if>>
									Cancelada</option>
								<option value="NO ACEPTO" <s:if test="%{'NO ACEPTO'==servConsultoria.estatus}">selected="selected"</s:if>>
									No Aceptó</option>
								<option value="PENDIENTE" <s:if test="%{'PENDIENTE'==servConsultoria.estatus}">selected="selected"</s:if>>
									Pendiente</option>
								<option value="DIFERIDA" <s:if test="%{'DIFERIDA'==servConsultoria.estatus}">selected="selected"</s:if>>
									Diferida</option>
								<option value="CONCLUIDA" <s:if test="%{'CONCLUIDA'==servConsultoria.estatus}">selected="selected"</s:if>>
									Concluida</option>
							</select></td>						
					</tr>
					<tr>
						<td>
							<s:label cssClass="etiquetaAyuda" id="ayudasDisplay0" style="display:none;margin-top:0px"
											value="Ingrese Estatus de la consultoria." />
						</td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="Comentarios:" /></td>
						<td><textarea name="servConsultoria.comentario"
								onfocus="javascript:ayudasHelp(01);" 
								onblur="javascript:ayudasHelpBlo(01);"
								maxlength="199"
								cols="80"
								rows="5">${servConsultoria.comentario}</textarea></td>
					</tr>
					<tr>
						<td>
							<s:label cssClass="etiquetaAyuda" id="ayudasDisplay01" style="display:none;margin-top:0px"
											value="Ingrese Estatus de la consultoria." />
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value='Documento:' />	
						</td>
						<td>
						<div ${documento.idArchivo!=null? ' style="display: block;" ':' style="display: none;"'}>
								<s:label cssClass="resultado" cssStyle="align: left;" value="%{documento.nombre}" />
								<input class="botonenviar" value="Reemplazar" id = "reemplazabtn" type="button" onclick="javascript:reemplazar();" />
						</div>
						<s:hidden id="idRFC" name="documento.idArchivo" value="%{documento.idArchivo}" />
						<div id= "reemplazar" ${documento.idArchivo==null? ' style="display: block;" ':' style="display: none;"'}>
							<div ${documento.idArchivo==null? ' style="display: block;" ':' style="display: block;"'}>
								<s:file id="aRfcPyME" name="servConsultoria.rfc" onclick="javascript:ayudasHelp(220);"/>
							</div>
						</div>	
						</td>			
					</tr>
					<tr>
						<td></td>
						<td>
							<s:label cssClass="etiquetaAyuda" id="ayudasDisplay220" style="display:none; margin-top:5px;" value="Indique el archivo que será incluido. Máximo 2MB (.pdf .doc .png)" />
						</td>
					</tr>
				</table>
				<table width="100%">
					<tr align="center">
						<td><input class="botonenviar" value="Siguiente" type="button" onclick="javascript:return validacion(1);" /></td>
					</tr>
				</table>				
			</div>
			<div id="final" style="display: none;">			
				<table width="99%">					
					<tr>
						<td style="width: 50%;">
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Capturar diagnóstico inicial." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Recursos Humanos (antes) :" /></td>
									<td><s:textfield id="recursosHumanosAntes" size="4" 
									name="servConsultoria.recursosHumanosAntes" maxlength="4"
									onfocus="javascript:ayudasHelp(30);" 
									onblur="javascript:ayudasHelpBlo(30);"
									onkeypress="javascript:return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2">
									<s:label cssClass="etiquetaAyuda" id="ayudasDisplay30" style="display:none;margin-top:0px"
											value="Ingrese número de Recursos Humanos (antes)." />
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Mercadeo (antes) :" /></td>
									<td><s:textfield id="mercadeoAntes" size="4" 
									name="servConsultoria.mercadeoAntes" maxlength="4"
									onfocus="javascript:ayudasHelp(1);" 
									onblur="javascript:ayudasHelpBlo(1);"
									onkeypress="javascript:return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2">
									<s:label cssClass="etiquetaAyuda" id="ayudasDisplay1" style="display:none;margin-top:0px"
											value="Ingrese número de Mercadeo (antes)." />
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Finanzas (antes) :" /></td>
									<td><s:textfield id="finanzasAntes" size="4" 
									name="servConsultoria.finanzasAntes" maxlength="4"
									onfocus="javascript:ayudasHelp(2);" 
									onblur="javascript:ayudasHelpBlo(2);"
									onkeypress="javascript:return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2">
									<s:label cssClass="etiquetaAyuda" id="ayudasDisplay2" style="display:none;margin-top:0px"
											value="Ingrese número de Finanzas (antes)." />
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Administración (antes) :" /></td>
									<td><s:textfield id="administracionAntes" size="4" 
									name="servConsultoria.administracionAntes" maxlength="4"
									onfocus="javascript:ayudasHelp(3);" 
									onblur="javascript:ayudasHelpBlo(3);"
									onkeypress="javascript:return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2">
									<s:label cssClass="etiquetaAyuda" id="ayudasDisplay3" style="display:none;margin-top:0px"
											value="Ingrese número de Administración (antes)." />
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Procesos (antes) :" /></td>
									<td><s:textfield id="procesosAntes" size="4" 
									name="servConsultoria.procesosAntes" maxlength="4"
									onfocus="javascript:ayudasHelp(4);" 
									onblur="javascript:ayudasHelpBlo(4);"
									onkeypress="javascript:return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2">
									<s:label cssClass="etiquetaAyuda" id="ayudasDisplay4" style="display:none;margin-top:0px"
											value="Ingrese número de Procesos (antes)." />
									</td>
								</tr>
							</table>
						</td>
						<td style="width: 50%;">
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Capturar diagnóstico final." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Recursos Humanos (despues) :" /></td>
									<td><s:textfield id="recursosHumanosDespues" size="4" 
									name="servConsultoria.recursosHumanosDespues" maxlength="4"
									onfocus="javascript:ayudasHelp(5);" 
									onblur="javascript:ayudasHelpBlo(5);"
									onkeypress="javascript:return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2">
									<s:label cssClass="etiquetaAyuda" id="ayudasDisplay5" style="display:none;margin-top:0px"
											value="Ingrese número de Recursos Humanos (despues) ." />
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Mercadeo (despues) :" /></td>
									<td><s:textfield id="mercadeoDespues" size="4" 
									name="servConsultoria.mercadeoDespues" maxlength="4"
									onfocus="javascript:ayudasHelp(6);" 
									onblur="javascript:ayudasHelpBlo(6);"
									onkeypress="javascript:return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2">
									<s:label cssClass="etiquetaAyuda" id="ayudasDisplay6" style="display:none;margin-top:0px"
											value="Ingrese número de Mercadeo (despues)." />
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Finanzas (despues) :" /></td>
									<td><s:textfield id="finanzasDespues" size="4" 
									name="servConsultoria.finanzasDespues" maxlength="4"
									onfocus="javascript:ayudasHelp(7);" 
									onblur="javascript:ayudasHelpBlo(7);"
									onkeypress="javascript:return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2">
									<s:label cssClass="etiquetaAyuda" id="ayudasDisplay7" style="display:none;margin-top:0px"
											value="Ingrese número de Finanzas (despues)." />
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Administración (despues) :" /></td>
									<td><s:textfield id="administracionDespues" size="4" 
									name="servConsultoria.administracionDespues" maxlength="4"
									onfocus="javascript:ayudasHelp(8);" 
									onblur="javascript:ayudasHelpBlo(8);"
									onkeypress="javascript:return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2">
									<s:label cssClass="etiquetaAyuda" id="ayudasDisplay8" style="display:none;margin-top:0px"
											value="Ingrese número de Administración (despues)." />
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Procesos (despues) :" /></td>
									<td><s:textfield id="procesosDespues" size="4" 
									name="servConsultoria.procesosDespues" maxlength="4"
									onfocus="javascript:ayudasHelp(9);" 
									onblur="javascript:ayudasHelpBlo(9);"
									onkeypress="javascript:return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2">
									<s:label cssClass="etiquetaAyuda" id="ayudasDisplay9" style="display:none;margin-top:0px"
											value="Ingrese número Procesos (despues)." />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="Diplomado recomendado 1 :" /></td>
						<td>
							<select name="servConsultoria.diplomadoRecomendado1" id="sug1"
									onfocus="javascript:ayudasHelp(10);" 
									onblur="javascript:ayudasHelpBlo(10);">
								<option value="-1" selected="selected">--Seleccione--</option>
								<s:iterator value="diplomados">
									<option value="${idDiplomado}" <s:if test="%{idDiplomado==servConsultoria.diplomadoRecomendado1}">selected="selected"</s:if>>
									${tema}</option>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td>
						<s:label cssClass="etiquetaAyuda" id="ayudasDisplay10" style="display:none;margin-top:0px"
							value="Ingrese diplomado recomendado 1." />
						</td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="Diplomado recomendado 2 :" /></td>
						<td>
							<select name="servConsultoria.diplomadoRecomendado2" id="sug2"
									onfocus="javascript:ayudasHelp(11);" 
									onblur="javascript:ayudasHelpBlo(11);">
								<option value="-1" selected="selected">--Seleccione--</option>
								<s:iterator value="diplomados">
									<option value="${idDiplomado}" <s:if test="%{idDiplomado==servConsultoria.diplomadoRecomendado2}">selected="selected"</s:if>>
									${tema}</option>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td>
						<s:label cssClass="etiquetaAyuda" id="ayudasDisplay11" style="display:none;margin-top:0px"
							value="Ingrese diplomado recomendado 2." />
						</td>
					</tr>
				</table>	
				<table width="100%">
					<tr style="width: 50%">
						<td align="center"><input class="botonenviar" value="Regresar" type="button" onclick="javascript:anterior();" /></td>
						<td align="left"><input class="botonenviar" value="Finalizar" type="button" onclick="javascript:completar();" /></td>
					</tr>
					<tr>
						<td>
						<s:label cssClass="etiquetaAyuda" id="ayudasDisplay0" style="display:none;margin-top:0px"
							value="Ingrese Estatus de la consultoria." />
						</td>
					</tr>
				</table>			
			</div>
		</s:form>
</fieldset>
<script type="text/javascript">
	function validaNumero(evt) {
		var key = (document.all) ? evt.keyCode : evt.which;
		return (key <= 46 || key <= 13 || (key >= 48 && key <= 57) || key == 46);
	}
	function reemplazar(){
		document.getElementById("reemplazabtn").style.display='none';
		document.getElementById("reemplazar").style.display='block';
		
	}
	function completar(){		
		if(validacion('2')){
			document.seguimientoCompleta.submit();
		}
	}
	function validacion(menu){
		var fechaInicio=document.getElementById("ingreso").value;
		var fechaTermino=document.getElementById("ingreso2").value;
		var seguimiento=document.getElementById("seguimiento").value;
		if(menu=='1'){
			document.getElementById("ingreso").value;
			if(!isDate(fechaInicio)){
				document.getElementById("ingreso").focus;
				alert("Ingrese la fecha correcta de inicio de la consultoría");
				return false;
			} else if(fechaTermino!=null && fechaTermino!="" && !isDate(fechaTermino)){
				document.getElementById("ingreso2").focus;
				alert("Ingrese la fecha correcta de termino de la consultoría");
				return false;
			} else if(seguimiento=='-1'){	
				document.getElementById("seguimiento").focus;
				alert("Seleccione el estatus de la consultoria ");
				return false;
			} else{
				document.getElementById("inicial").style.display='none';
				document.getElementById("final").style.display='block';
				return true;
			}			
		} else if(menu=='2'){
			var recursosHumanosAntes=document.getElementById("recursosHumanosAntes").value;
			var mercadeoAntes=document.getElementById("mercadeoAntes").value;
			var finanzasAntes=document.getElementById("finanzasAntes").value;
			var administracionAntes=document.getElementById("administracionAntes").value;
			var procesosAntes=document.getElementById("procesosAntes").value;			
			var recursosHumanosDespues=document.getElementById("recursosHumanosDespues").value;
			var mercadeoDespues=document.getElementById("mercadeoDespues").value;
			var finanzasDespues=document.getElementById("finanzasDespues").value;
			var administracionDespues=document.getElementById("administracionDespues").value;
			var procesosDespues=document.getElementById("procesosDespues").value;
			
			var sug1=document.getElementById("sug1").value;
			var sug2=document.getElementById("sug2").value;
			
			if (!/^([0-9])*[.]?[0-9]*$/.test(recursosHumanosAntes) || recursosHumanosAntes == null 
					|| recursosHumanosAntes.length == 0	|| /^\s+$/.test(recursosHumanosAntes) 
					|| recursosHumanosAntes>10.0){
				document.getElementById("recursosHumanosAntes").focus();
				alert("Los datos de recursos humanos(antes) deben de ser numéricos menores a 10.");
				return false;
				
			} else if(!/^([0-9])*[.]?[0-9]*$/.test(mercadeoAntes) || mercadeoAntes == null 
					|| mercadeoAntes.length == 0	|| /^\s+$/.test(mercadeoAntes) 
					|| mercadeoAntes>10.0){
				document.getElementById("mercadeoAntes").focus();
				alert("Los datos de mercadeo(antes) deben de ser numéricos menores a 10.");
				return false;
				
			} else if(!/^([0-9])*[.]?[0-9]*$/.test(finanzasAntes) || finanzasAntes == null 
					|| finanzasAntes.length == 0	|| /^\s+$/.test(finanzasAntes) 
					|| finanzasAntes>10.0){
				document.getElementById("finanzasAntes").focus();
				alert("Los datos de finanzas(antes) deben de ser numéricos menores a 10.");
				return false;
				
			} else if(!/^([0-9])*[.]?[0-9]*$/.test(administracionAntes) || administracionAntes == null 
					|| administracionAntes.length == 0	|| /^\s+$/.test(administracionAntes) 
					|| administracionAntes>10.0){
				document.getElementById("administracionAntes").focus();
				alert("Los datos de administración(antes) deben de ser numéricos menores a 10.");
				return false;
				
			} else if(!/^([0-9])*[.]?[0-9]*$/.test(procesosAntes) || procesosAntes == null 
					|| procesosAntes.length == 0	|| /^\s+$/.test(procesosAntes) 
					|| procesosAntes>10.0){
				document.getElementById("procesosAntes").focus();
				alert("Los datos de procesos(antes) deben de ser numéricos menores a 10.");
				return false;
				
			} else if(!/^([0-9])*[.]?[0-9]*$/.test(recursosHumanosDespues) || recursosHumanosDespues == null 
					|| recursosHumanosDespues.length == 0	|| /^\s+$/.test(recursosHumanosDespues) 
					|| recursosHumanosDespues>10.0){
				document.getElementById("recursosHumanosDespues").focus();
				alert("Los datos de recursos humanos(Despues) deben de ser numéricos menores a 10.");
				return false;
				
			} else if(!/^([0-9])*[.]?[0-9]*$/.test(mercadeoDespues) || mercadeoDespues == null 
					|| mercadeoDespues.length == 0	|| /^\s+$/.test(mercadeoDespues) 
					|| mercadeoDespues>10.0){
				document.getElementById("mercadeoDespues").focus();
				alert("Los datos de mercadeo(Despues) deben de ser numéricos menores a 10.");
				return false;
				
			} else if(!/^([0-9])*[.]?[0-9]*$/.test(finanzasDespues) || finanzasDespues == null 
					|| finanzasDespues.length == 0	|| /^\s+$/.test(finanzasDespues) 
					|| finanzasDespues>10.0){
				document.getElementById("finanzasDespues").focus();
				alert("Los datos de finanzas(Despues) deben de ser numéricos menores a 10.");
				return false;
				
			} else if(!/^([0-9])*[.]?[0-9]*$/.test(administracionDespues) || administracionDespues == null 
					|| administracionDespues.length == 0	|| /^\s+$/.test(administracionDespues) 
					|| administracionDespues>10.0){
				document.getElementById("administracionDespues").focus();
				alert("Los datos de administración(Despues) deben de ser numéricos menores a 10.");
				return false;
				
			} else if(!/^([0-9])*[.]?[0-9]*$/.test(procesosDespues) || procesosDespues == null 
					|| procesosDespues.length == 0	|| /^\s+$/.test(procesosDespues) 
					|| procesosDespues>10.0){
				document.getElementById("procesosDespues").focus();
				alert("Los datos de procesos(Despues) deben de ser numéricos menores a 10.");
				return false;
				
			} else if(sug1==null || sug1=='-1'){
				document.getElementById("sug1").focus();
				alert("Seleccione diplomado de recomendación 1.");
				return false;
				
			} else if(sug2==null || sug2=='-1'){
				document.getElementById("sug2").focus();
				alert("Seleccione diplomado de recomendación 2.");
				return false;
				
			} else {
				return true;
			}
		}
		return false;
	}
	function anterior(){
		document.getElementById("inicial").style.display='block';
		document.getElementById("final").style.display='none';
	}

	function isDate(fecha) {
		var separador = "/";
		function isInteger(s) {
			var i;
			for (i = 0; i < s.length; i++) {
				var c = s.charAt(i);
				if (((c < "0") || (c > "9")))
					return false;
			}
			return true;
		}
		function stripCharsInBag(s, bag) {
			var i;
			var returnString = "";
			for (i = 0; i < s.length; i++) {
				var c = s.charAt(i);
				if (bag.indexOf(c) == -1)
					returnString += c;
			}
			return returnString;
		}
		function posCharsInBags(s, bag) {
			var i;
			var s2 = false;
			var s5 = false;
			for (i = 0; i < s.length; i++) {
				var c = s.charAt(i);
				if (i == 2 && c == separador)
					s2 = true;
				;
				if (i == 5 && c == separador)
					s5 = true;
			}
			return s2 && s5;
		}
		var numeros = stripCharsInBag(fecha, separador);
		if (fecha.length != 10 || numeros.length != 8) {
			return false;
		}
		if (!posCharsInBags(fecha, separador)) {
			return false;
		}
		if (isInteger(numeros) == false) {
			return false;
		}
		return true;
	}
	Calendar.setup({
		inputField : "ingreso", // id del campo de texto
		ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
		// campo de texto
		button : "lanzador" // el id del botón que lanzará el calendario
	});
	Calendar.setup({
		inputField : "ingreso2", // id del campo de texto
		ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
		// campo de texto
		button : "lanzador2" // el id del botón que lanzará el calendario
	});
</script>
</body>
</html>