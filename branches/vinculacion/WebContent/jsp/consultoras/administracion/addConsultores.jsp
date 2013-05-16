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
</head>
<body>
<div id="agregar" ${pymesList==null ? ' style="display: block;"':' style="display: none;"'}>
	<fieldset id="requerimientos">
		<legend>
			<s:label value="Captura registro de consultor " />
			<br /> <br />
			<s:label
				cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
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
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda"
							value="Incluya el nombre de la empresa consultora que se dará de alta." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Nombre(s) Contacto:" /></td>
					<td><s:textfield
							size="60"
							id="idNombreContacto"
							name="consultoras.nombreContacto"
							maxlength="60"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda"
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
							maxlength="60"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda"
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
							maxlength="60"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda"
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
							maxlength="60"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda"
							value="Ingrese el correo electrónico." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Confirmar Correo Electrónico:" /></td>
					<td><s:textfield
							size="60"
							id="idConfirmacion"
							maxlength="60"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda"
							value="Ingrese nuevamente el correo electrónico." /></td>
				</tr>
				<tr>
					<td colspan="2"><s:submit
							cssClass="botonenviar"
							value="Registrar Consultora" /></td>
				</tr>
			</table>
		</s:form>
	</fieldset>
</div>
<div id="asignar" ${pymesList!=null ? ' style="display: block;"':' style="display: none;"'}>	
		<legend>
			<s:label value="Asignar PYME a consultor  " />${consultoras.nombreContacto}
			<br /> <br />
		</legend>
		<s:form
			id="form"
			name="form"
			action="consultoraConsultoresShow"
			namespace="/consultor/administracion"
			theme="simple"
			onsubmit="return confirmacion()">
			<s:label
				cssClass="camposObligatorios"
				value="Lista de PYMES sin consultor asignado, selecccionar PYME" />
			
			<s:hidden name="consultoras.idConsultora" value="%{consultoras.idConsultora}"/>
			<s:hidden name="consultoras.idUsuario" value="%{consultoras.idUsuario}" />
			<div id="checkboxes"><br/>
			<table>
					<s:set var="contador" value="0" />
					<tr>
						<td class="encabezado_tabla" align="center"><b>Asignar</b></td>
						<td class="encabezado_tabla" align="center"><b>PYME</b></td>
						<td class="encabezado_tabla" align="center"><b>Corre Electronico</b></td>
					</tr>
					<s:iterator value="pymesList" status="stat">
						<s:if test="(#stat.index % 1) == 0"><tr></s:if>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center"><input type="checkbox" name="pymesSelected" value="${idUsuario}"></input></td>	
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">${nombreComercial}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">${correoElectronico}</td>					
					</s:iterator>
					<tr>
						<td colspan="2"><s:submit
							cssClass="botonenviar"
							value="Asignar" /></td>
					</tr>
			</table>
			</div>
		</s:form>	
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
			} else if (!(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
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
					   if(confirm("Las PYMES seran asignadas y en seguida se enviara\n un correo a las PYMES seleccionadas.\n\n¿Desea continuar?")){
							return true;
						}
						else{
							return false;
						}
				   }
				 }
			}
			alert("No se ha seleccionado ninguna PYME");
			return false;
			
		}
	</script>
	
</body>
</html>