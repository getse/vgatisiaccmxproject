<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="${pageContext.request.contextPath}/js/tractoras.js" type="text/javascript"></script>
<link
	href="${pageContext.request.contextPath}/css/calendario.css"
	rel="stylesheet"
	type="text/css" />
<script
	src="${pageContext.request.contextPath}/js/ayudas.js"
	type="text/javascript"></script>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-225px auto 0 250px';
</script>
</head>
<body>
<s:if test="mensaje!=null">
	<br />
	<table class="nota">
		<tr>
			<td class="imgNota"><s:if test="mensaje.respuesta==0">
				<img src="${pageContext.request.contextPath}/img/palomitaverde.gif" />
			</s:if> <s:else>
				<img src="${pageContext.request.contextPath}/img/warning.png" />
			</s:else></td>
			<td class="contenidoNota"><s:property value="mensaje.mensaje" /></td>
		</tr>
	</table>
</s:if>
<s:form action="consultorInformacionShow" namespace="/consultor" name="frmCancela" id="frmCancela"></s:form>
<div id="Editar" ${consultoras.idUsuario!=0 ?' style="display: block;"':' style="display: none;"'}>
	<fieldset id="requerimientos">
			<s:form action="consultorInformacionShow" namespace="/consultor"
				theme="simple" onsubmit="javascript:return validaDatoss();">
			<s:hidden name="consultoras.idUsuario" value="%{consultoras.idUsuario}"></s:hidden>	
			<s:hidden name="consultoras.correoElectronico" value="%{consultoras.correoElectronico}"></s:hidden>
			<legend>
				<s:label value="Confirmar Datos" />
				<br /> <br />
				<s:label cssClass="camposObligatorios"
					value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
			</legend>
			<br />
				<table>
					<tr>
						<td style="width: 470px;">
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura"
											value="* Nombre(s):" /> <s:textfield size="43" id="idNombre"
											name="consultoras.nombreContacto" maxlength="60" onfocus="javascript:ayudasHelp(0);" 
											onblur="javascript:ayudasHelpBlo(0);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay0" style="display:none;margin-top:0px"
											value="Escriba su Nombre(s)." /></td>
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" 
											value="* Apellido Paterno:" /> <s:textfield size="36"
											id="idAppPaterno" name="consultoras.appPaternoContacto" maxlength="60"
											onfocus="javascript:ayudasHelp(1);" 
											onblur="javascript:ayudasHelpBlo(1);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay1" style="display:none;margin-top:0px"
											value="Escriba su Apellido Paterno." /></td>
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura"
											value="* Apellido Materno:" /> <s:textfield size="35"
											id="idAppMaterno" name="consultoras.appMaternoContacto" maxlength="60"
											onfocus="javascript:ayudasHelp(2);" 
											onblur="javascript:ayudasHelpBlo(2);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay2" style="display:none;margin-top:0px"
											value="Escriba su Apellido Materno." /></td>
								</tr>
							</table>
						</td>
						<td style="width: 470px;">
							<table>
							<tr>
									<td><s:label cssClass="etiquetaCaptura"
											value="Correo Electrónico" /> 
											</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura"
											value="%{consultoras.correoElectronico}" /> 
											</td>
								</tr>
								<tr><td>&nbsp;</td></tr>							
							</table>
							<table>
								<tr>
									<td colspan="6"><s:label cssClass="etiquetaCaptura"
											value="* Teléfono fijo:" /></td>
								</tr>
								<tr>
									<td style="width: 12%;">
											<s:label cssClass="etiquetaCaptura" value="Lada :" />
									</td>
									<td style="width: 20%;">
										<s:label id="intTel" cssClass="resultado" value="52" />&nbsp;&nbsp;
										<s:textfield size="2" id="ladaTel" name="" maxlength="3" onkeypress="javascript: cambiaCampo(event);" 
											onfocus="javascript:ayudasHelp(5);" onblur="javascript:ayudasHelpBlo(5);"
											onkeypress="return validaNumero(event)"></s:textfield>
									</td>
									<td style="width: 10%;">
										<s:label cssClass="etiquetaCaptura" value="Núm:" />
										</td>
									<td style="width: 28%;">
										<s:textfield size="16" id="numTel" name="" maxlength="8" onkeypress="javascript: cambiaCampo(event);" 
										onfocus="javascript:ayudasHelp(5);" onblur="javascript:ayudasHelpBlo(5);"
										onkeypress="return validaNumero(event)"></s:textfield>
									</td>
									<td style="width: 5%;">
										<s:label cssClass="etiquetaCaptura" value="Ext:" />
									</td>
									<td style="width: 15%;">
										<s:textfield size="4" id="extTel" name="" maxlength="4" 
										onfocus="javascript:ayudasHelp(5);" onblur="javascript:ayudasHelpBlo(5);"
										onkeypress="return validaNumero(event)"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="6"><s:label cssClass="etiquetaCaptura"
											value="* Teléfono celular:" /></td>
								</tr>
								<tr>
									<td style="width: 12%;">
											<s:label cssClass="etiquetaCaptura" value="Lada :" />
									</td>
									<td style="width: 20%;">
										<s:label id="intTel2" cssClass="resultado" value="52" />&nbsp;&nbsp;
										<s:textfield size="2" id="ladaTel2" name="" maxlength="3" onkeypress="javascript: cambiaCampo(event);" 
											onfocus="javascript:ayudasHelp(5);" onblur="javascript:ayudasHelpBlo(5);"
											onkeypress="return validaNumero(event)"></s:textfield>
									</td>
									<td style="width: 10%;">
										<s:label cssClass="etiquetaCaptura" value="Núm:" />
										</td>
									<td style="width: 28%;">
										<s:textfield size="16" id="numTel2" name="" maxlength="8" onkeypress="javascript: cambiaCampo(event);" 
										onfocus="javascript:ayudasHelp(5);" onblur="javascript:ayudasHelpBlo(5);"
										onkeypress="return validaNumero(event)"></s:textfield>
									</td>
									<td style="width: 5%;">
										<s:label cssClass="etiquetaCaptura" value="Ext:" />
									</td>
									<td style="width: 15%;">
										<s:textfield size="4" id="extTel2" name="" maxlength="4" 
										onfocus="javascript:ayudasHelp(5);" onblur="javascript:ayudasHelpBlo(5);"
										onkeypress="return validaNumero(event)"></s:textfield>
									</td>
								</tr>
							</table>
							<s:hidden id="telCompHid2"  name="consultoras.telefonos[1].telefono" value="%{consultoras.telefonos[1].telefono}" />
							<s:hidden id="telCompHid"  name="consultoras.telefonos[0].telefono" value="%{consultoras.telefonos[0].telefono}" />
							<s:hidden name="consultoras.telefonos[0].idUsuario" value="%{consultoras.telefonos[0].idUsuario}" />
							<s:hidden name="consultoras.telefonos[1].idUsuario" value="%{consultoras.telefonos[1].idUsuario}" />
							<s:hidden name="consultoras.telefonos[0].idTelefono" value="%{consultoras.telefonos[0].idTelefono}" />
							<s:hidden name="consultoras.telefonos[1].idTelefono" value="%{consultoras.telefonos[1].idTelefono}" />
							<table>
								<tr>
									<td>
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay5" style="display:none;margin-top:0px"
											value="Incluya su teléfono con clave lada y extensión." /><br />
									</td>
								</tr>
							</table>
							
						</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</table>
			<table class="submit_tabla">
					<tr>
						<td style="width: 250px;">
						<input
						class="botonenviar"
						value="Cancelar"
						type="button"
						onclick="cancela();" /></td>
						<td><s:submit
								cssClass="botonenviar"
								value="Guardar" /></td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
	</s:form>
</fieldset>
</div>
<div id="Editar" ${consultoras.idUsuario==0 ? ' style="display: block;"':' style="display: none;"'}>
	<fieldset id="requerimientos">
			<legend>
				<s:label value="Mi Información" />
			</legend>
			<br />
			<s:form action="consultorInformacionAdd" namespace="/consultor"
				theme="simple">
			<br/><br/>
			<table class="expediente_tabla">
				<tr>
				<td	class="encabezadoTablaResumen"
						colspan="2"
						align="center">Expediente Electrónico del Consultor</td>
				</tr>
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Nombre del Contacto:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${consultoras.nombreContacto}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Apellido Paterno del Contacto:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${consultoras.appPaternoContacto}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Apellido Materno del Contacto:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${consultoras.appMaternoContacto}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Correo Electrónico:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${consultoras.correoElectronico}</s:label></td>
				</tr>
				<s:iterator value="consultoras.telefonos" status="stats">
					<tr>
						<td class="${((stats.index % 2) == 0) ? 'cuerpo2TablaResumen' : 'cuerpo1TablaResumen'}" align="left">&nbsp;Teléfono ${stat.count}:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${telefono}</s:label></td>
					</tr>
				</s:iterator>
			</table>
			<table class="submit_tabla">
				<tr>
					<td style="width: 450px;"></td>
					<td><s:submit
						class="botonenviar"
						value="Modificar"
						type="button"
						 /></td>
					<td style="width: 450px;"></td>
				</tr>
			</table>
		</s:form>
	</fieldset>		
</div>
<script type="text/javascript">
function validaDatoss() {
	valorNombre = document.getElementById("idNombre").value;
	valorPaterno = document.getElementById("idAppPaterno").value;
	valorMaterno = document.getElementById("idAppMaterno").value;

		if (valorNombre == null || valorNombre.length == 0
				|| /^\s+$/.test(valorNombre)) {
			document.getElementById("idNombre").focus();
			alert("Ingrese el Nombre(s) requerido");
			return false;
		} else if (valorPaterno == null || valorPaterno.length == 0
				|| /^\s+$/.test(valorPaterno)) {
			document.getElementById("idAppPaterno").focus();
			alert("Ingrese Apellido Paterno");
			return false;
		} else if (valorMaterno == null || valorMaterno.length == 0
				|| /^\s+$/.test(valorMaterno)) {
			document.getElementById("idAppMaterno").focus();
			alert("Ingrese Apellido Materno");
			return false;
		} 
		if(document.getElementById('ladaTel').value.length < 2 || /^\s+$/.test(document.getElementById('ladaTel').value)){
			document.getElementById("ladaTel").focus();
			alert("El campo de lada debe contener dos o tres dígitos.");
			return false;
		}
		valorTelefonoContacto = document.getElementById("numTel").value;
		if (document.getElementById('ladaTel').value.length == 2 && valorTelefonoContacto.length != 8 || /^\s+$/.test(valorTelefonoContacto)) {
			document.getElementById("numTel").focus();
			alert("El campo Teléfono debe contener ocho dígitos.");
			return false;
		}else if(document.getElementById('ladaTel').value.length == 3 && valorTelefonoContacto.length != 7 || /^\s+$/.test(valorTelefonoContacto)){
			document.getElementById("numTel").focus();
			alert("El campo Teléfono debe contener siete dígitos.");
			return false;
		}
		if(document.getElementById('ladaTel2').value.length < 2 || /^\s+$/.test(document.getElementById('ladaTel').value)){
			document.getElementById("ladaTel2").focus();
			alert("El campo de lada debe contener dos o tres dígitos.");
			return false;
		}
		valorTelefonoContacto = document.getElementById("numTel2").value;
		if (document.getElementById('ladaTel2').value.length == 2 && valorTelefonoContacto.length != 8 || /^\s+$/.test(valorTelefonoContacto)) {
			document.getElementById("numTel2").focus();
			alert("El campo Teléfono debe contener ocho dígitos.");
			return false;
		}else if(document.getElementById('ladaTel2').value.length == 3 && valorTelefonoContacto.length != 7 || /^\s+$/.test(valorTelefonoContacto)){
			document.getElementById("numTel2").focus();
			alert("El campo Teléfono debe contener siete dígitos.");
			return false;
		}
		var ladaTel1 = document.getElementById('ladaTel').value;
		var numTel1 = document.getElementById('numTel').value;
		var extTel1 = document.getElementById('extTel').value;

		var ladaTel2 = document.getElementById('ladaTel2').value;
		var numTel2 = document.getElementById('numTel2').value;
		var extTel2 = document.getElementById('extTel2').value;
		
		document.getElementById('telCompHid').value = '(52)('+ladaTel1+')('+numTel1+')('+extTel1+')';
		document.getElementById('telCompHid2').value = '(52)('+ladaTel2+')('+numTel2+')('+extTel2+')';
		return true;
}
function cambiaCampo(e){
	
	var code = (e.keyCode ? e.keyCode : e.which);
	
	if(code != 8 && code != 37 && code != 39) {
		if(document.getElementById('ladaTel').value.length == 2){
			document.getElementById('numTel').focus();
		}
		if(document.getElementById('numTel').value.length == 8){
			document.getElementById('extTel').focus();
		}
	}
}
function validaNumero(evt) {
	var key = (document.all) ? evt.keyCode : evt.which;
	return (key <= 13 || (key >= 48 && key <= 57) || key == 46);
}
window.onload = function() {
	var telContacto1 = document.getElementById('telCompHid').value;	
	var ladaTel = document.getElementById('ladaTel');
	var numTel = document.getElementById('numTel');
	var extTel = document.getElementById('extTel');
	if(telContacto1 != 'null'){
		var separaCampos = telContacto1.split(')(');
		if(separaCampos.length>1){
			ladaTel.value = separaCampos[1];
			numTel.value = separaCampos[2];
			extTel.value = separaCampos[3].substring(0, (separaCampos[3].length - 1));			
		}
	}
	
							
	var telContacto2 = document.getElementById('telCompHid2').value;
	var ladaTel2 = document.getElementById('ladaTel2');
	var numTel2 = document.getElementById('numTel2');
	var extTel2 = document.getElementById('extTel2');
							
	if(telContacto2 != 'null'){
		var separaCampos2 = telContacto2.split(')(');
		if(separaCampos.length>1){
			ladaTel2.value = separaCampos2[1];
			numTel2.value = separaCampos2[2];
			extTel2.value = separaCampos2[3].substring(0, (separaCampos2[3].length - 1));
		}
	}
};
</script>
</body>
</html>