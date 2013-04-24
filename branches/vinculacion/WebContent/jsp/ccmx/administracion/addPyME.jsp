<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>
	<fieldset id="requerimientos">
		<legend>
			<s:label value="Captura para registro de usuarios PyMEs" />
			<br /> <br />
			<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<br />

		<s:form action="PyMEsShow" namespace="/ccmx/administracion/pymes"
			theme="simple" onsubmit="return validacion()">
			<table>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Nombre Comercial:" /></td>
					<td><s:textfield size="60" id="idNombreComercial" name="pyMes.nombreComercial" maxlength="150"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba el nombre comercial." /></td>
				</tr>			
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Correo electrónico:" /></td>
					<td><s:textfield size="60" id="idCorreoElectronico" name="pyMes.correoElectronico" maxlength="100"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba su correo electrónico." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Confimación de Correo electrónico:" /></td>
					<td><s:textfield size="60" id="idComparaCorreo" maxlength="100"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Confirme el correo electrónico." /></td>
				</tr>
				
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Nombre(s) :" /></td>
					<td><s:textfield size="60" id="idNombreContacto" name="pyMes.nombreContacto1" maxlength="60"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba el nombre o nombres del contacto sin considerar acentos." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Apellido Paterno :" /></td>
					<td><s:textfield size="60" id="idAppPaterno" name="pyMes.appPaterno1" maxlength="60"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba el apellido paterno del contacto sin considerar acentos." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Apellido Materno :" /></td>
					<td><s:textfield size="60" id="idAppMaterno" name="pyMes.appMaterno1" maxlength="60"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba el apellido materno del contacto sin considerar acentos." /></td>
				</tr>
				<tr>
					<td colspan="2">
						<s:submit cssClass="botonenviar" value="Registrar PyME" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>
	<script type="text/javascript">
		function validacion() {
			valorEmpresa = document.getElementById("idNombreComercial").value;
			valorCorreo = document.getElementById("idCorreoElectronico").value;
			valorCompara = document.getElementById("idComparaCorreo").value;
			valorNombre = document.getElementById("idNombreContacto").value;
			valorPaterno = document.getElementById("idAppPaterno").value;
			valorMaterno = document.getElementById("idAppMaterno").value;
					
			if( valorEmpresa == null || valorEmpresa.length == 0 || /^\s+$/.test(valorEmpresa) ) {
				alert("Ingrese el Nombre Comercial");
				return false;
			}else if( !(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(valorCorreo)) ) {
				alert("Ingrese una dirección de correo electrónico válida");
				return false;
			}else if (valorCorreo != valorCompara){
				alert("El correo electrónico no coincide");
				return false;
			}else if( valorNombre == null || valorNombre.length == 0 || /^\s+$/.test(valorNombre) ) {
				alert("Ingrese el Nombre(s) requerido");
				return false;
			}else if( valorPaterno == null || valorPaterno.length == 0 || /^\s+$/.test(valorPaterno) ) {
				alert("Ingrese Apellido Paterno");
				return false;
			}else if( valorMaterno == null || valorMaterno.length == 0 || /^\s+$/.test(valorMaterno) ) {
				alert("Ingrese Apellido Materno");  
				return false;
			}
			return true;
		}
	</script>
</body>
</html>