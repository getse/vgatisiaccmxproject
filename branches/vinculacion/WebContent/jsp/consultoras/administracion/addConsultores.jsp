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
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-195px auto 0 250px';
</script>
<script
	src="${pageContext.request.contextPath}/js/ayudas.js"
	type="text/javascript"></script>
</head>
<body>
<div id="agregar" ${pymesList==null ? ' style="display: block;"':' style="display: none;"'}>
	<fieldset id="requerimientos">
		<legend>
			<s:label value="Captura registro de consultor " />
			<br /> <br />
			<s:label
				cssClass="camposObligatorios"
				value="Para registrar un consultor, ingrese los siguientes datos: " />
		</legend>
		<br />
		<s:form
			action="consultoraConsultoresShow"
			namespace="/consultor/administracion"
			theme="simple"
			onsubmit="return validacion()">
			<s:hidden name="consultoras.idUsuario" value="%{consultoras.idUsuario}" />
			<s:hidden name="credenciales" value="%{consultoras.correoElectronico}" />
			<table>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Nombre(s) Contacto:" /></td>
					<td><s:textfield
							size="60"
							id="idNombreContacto"
							name="consultoras.nombreContacto"
							maxlength="60"
							onfocus="javascript:ayudasHelp(0);" 
							onblur="javascript:ayudasHelpBlo(0);"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay0" style="display:none;margin-top:0px"
							value="Ingrese el nombre." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Apellido Paterno Contacto:" /></td>
					<td><s:textfield
							size="60"
							id="idAppPaternoContacto"
							name="consultoras.appPaternoContacto"
							maxlength="60"
							onfocus="javascript:ayudasHelp(1);" 
							onblur="javascript:ayudasHelpBlo(1);"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay1" style="display:none;margin-top:0px"
							value="Ingrese el apellido paterno." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Apellido Materno Contacto:" /></td>
					<td><s:textfield
							size="60"
							id="idAppMaternoContacto"
							name="consultoras.appMaternoContacto"
							maxlength="60"
							onfocus="javascript:ayudasHelp(2);" 
							onblur="javascript:ayudasHelpBlo(2);"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay2" style="display:none;margin-top:0px"
							value="Ingrese el apellido materno." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Correo electrónico:" /></td>
					<td><s:textfield
							size="60"
							id="idCorreoElectronico"
							name="consultoras.correoElectronico"
							maxlength="60"
							onfocus="javascript:ayudasHelp(3);" 
							onblur="javascript:ayudasHelpBlo(3);"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay3" style="display:none;margin-top:0px"
							value="Ingrese el correo electrónico." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Confirmar Correo Electrónico:" /></td>
					<td><s:textfield
							size="60"
							id="idConfirmacion"
							maxlength="60"
							onfocus="javascript:ayudasHelp(4);" 
							onblur="javascript:ayudasHelpBlo(4);"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay4" style="display:none;margin-top:0px"
							value="Ingrese nuevamente el correo electrónico." /></td>
				</tr>
				<tr>
					<td colspan="2"><s:submit
							cssClass="botonenviar"
							value="Registrar Consultor" /></td>
				</tr>
			</table>
		</s:form>
	</fieldset>
</div>
<div id="asignar" ${pymesList!=null ? ' style="display: block;"':' style="display: none;"'} >
	<fieldset id="requerimientos">	
		<s:form
			id="form"
			name="form"
			action="consultoraConsultoresShow"
			namespace="/consultor/administracion"
			theme="simple"
			onsubmit="return confirmacion()">
			<legend>
			<s:label value="Consultor " />${consultoras.nombreContacto}
				<br /> <br />
			</legend>
			<s:label
				cssClass="camposObligatorios"
				value="Seleccionar de la lista de PyMEs las que serán asignadas a el consultor." />			
			<s:hidden name="consultoras.idConsultora" value="%{consultoras.idConsultora}"/>
			<s:hidden name="consultoras.idUsuario" value="%{consultoras.idUsuario}" />
			<div id="checkboxes"><br/>
			<table style="width: 90%">
			<s:if test="%{pymesList!=null}">
					<s:set var="contador" value="0" />
					<tr>
						<td class="encabezado_tabla" align="center" style="width: 10%"><b>Asignar</b></td>
						<td class="encabezado_tabla" align="center" style="width: 30%"><b>PyME</b></td>
						<td class="encabezado_tabla" align="center" style="width: 30%"><b>Corre Electronico</b></td>
						<td class="encabezado_tabla" align="center" style="width: 30%"><b>Contacto</b></td>
					</tr>
					
					<s:iterator value="pymesList" status="stat">
						<s:if test="(#stat.index % 1) == 0"><tr></s:if>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center"><input type="checkbox" name="pymesSelected" value="${idUsuario}"></input></td>	
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">${nombreComercial}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">${correoElectronico}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">${nombreContacto1}</td>				
					</s:iterator>
					<tr>
						<td colspan="2"><s:submit
							cssClass="botonenviar"
							value="Asignar" /></td>
					</tr>
			</s:if>
			<s:else>
				<tr>
					<td><s:label value="No se han asigndo nuevas PyMEs a su administración"></s:label> </td>
				</tr>
			</s:else>
			</table>
			</div>
		</s:form>	
	</fieldset>
</div>
	<script type="text/javascript">
		function validacion() {
			valorNombre = document.getElementById("idNombreContacto").value;
			valorPaterno = document.getElementById("idAppPaternoContacto").value;
			valorMaterno = document.getElementById("idAppMaternoContacto").value;
			valorCorreo = document.getElementById("idCorreoElectronico").value;
			valorCompara = document.getElementById("idConfirmacion").value;

			if (valorNombre == null || valorNombre.length == 0
					|| /^\s+$/.test(valorNombre)) {
				document.getElementById("idNombreContacto").focus();
				alert("Ingrese el Nombre");
				return false;
			} else if (valorPaterno == null || valorPaterno.length == 0
					|| /^\s+$/.test(valorPaterno)) {
				document.getElementById("idAppPaternoContacto").focus();
				alert("Ingrese Apellido Paterno");
				return false;
			} else if (valorMaterno == null || valorMaterno.length == 0
					|| /^\s+$/.test(valorMaterno)) {
				document.getElementById("idAppMaternoContacto").focus();
				alert("Ingrese Apellido Materno");
				return false;
			} else if (!(/[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
					.test(valorCorreo))) {
				document.getElementById("idCorreoElectronico").focus();
				alert("Ingrese una dirección de correo electrónico válida");
				return false;
			} else if (valorCorreo != valorCompara) {
				document.getElementById("idConfirmacion").focus();
				alert("El correo electrónico no coincide");
				return false;
			}
			return true;
		}
		function confirmacion(){
			formulario = document.getElementById("form");
			for(var i=0; i<formulario.elements.length; i++) {
				var elemento = formulario.elements[i];
				if(elemento.type == "checkbox") {
				   if(elemento.checked) {
					   if(confirm("Las PyMEs seran asignadas y en seguida se enviara\n un correo al Consultor para notificarle.\n\n¿Desea continuar?")){
							return true;
						}
						else{
							return false;
						}
				   }
				 }
			}
			alert("No se ha seleccionado ninguna PyME");
			return false;
			
		}
	</script>
	
</body>
</html>