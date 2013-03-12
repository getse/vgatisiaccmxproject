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
			Alta de Compradores<br /><br />
			<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<br />

		<s:form action="showComAdm" namespace="/tractoras/administracion/compradores"
			theme="simple" onsubmit="return validacion()">
			<table>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Nombre(s) del Comprador:" /></td>
					<td><s:textfield size="60" id="idNombre"
							name="tractoras.nombreContacto" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Escriba el nombre del comprador sin considerar acentos." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Apellido Paterno Comprador:" /></td>
					<td><s:textfield size="60" id="idAppPaterno"
							name="tractoras.appPaterno" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Escriba el apellido paterno del comprador sin considerar acentos." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Apellido Materno Comprador:" /></td>
					<td><s:textfield size="60" id="idAppMaterno"
							name="tractoras.appMaterno" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Escriba el apellido materno del comprador sin considerar acentos." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Correo Electrónico:" /></td>
					<td><s:textfield size="60" id="idCorreoElectronico"
							name="tractoras.correoElectronico" maxlength="250"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Escriba el correo electrónico del comprador." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Confimación Correo Electrónico:" /></td>
					<td><s:textfield size="60" id="idComparaCorreo"
							maxlength="250"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Confirme el correo electrónico." />
					</td>
				</tr>
				<tr>
					<td colspan="2"><s:submit cssClass="botonenviar"
							value="Agregar Comprador" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<script type="text/javascript">
		function validacion() {
			valorNombre = document.getElementById("idNombre").value;
			valorPaterno = document.getElementById("idAppPaterno").value;
			valorMaterno = document.getElementById("idAppMaterno").value;
			valorCorreo = document.getElementById("idCorreoElectronico").value;
			valorCompara = document.getElementById("idComparaCorreo").value;
			
			if( valorNombre == null || valorNombre.length == 0 || /^\s+$/.test(valorNombre) ) {
				alert("Ingrese el Nombre(s) del Comprador");
				return false;
			}else if( valorPaterno == null || valorPaterno.length == 0 || /^\s+$/.test(valorPaterno) ) {
				alert("Ingrese en Apellido Paterno del Comprador");
				return false;
			}else if( valorMaterno == null || valorMaterno.length == 0 || /^\s+$/.test(valorMaterno) ) {
				alert("Ingrese en Apellido Materno del Comprador");  
				return false;
			}else if( !(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(valorCorreo)) ) {
				alert("Ingrese una dirección de correo electrónico válida");
				return false;
			}else if (valorCorreo != valorCompara){
				alert("El correo electrónico no coincide");
				return false;
			}else{
				return true;
			}
		}
</script>
</body>
</html>