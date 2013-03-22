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
	<fieldset id="requerimientos">
		<legend>
			Captura para registro de Tractora
			<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<br />

		<s:form action="tractorasShow" namespace="/ccmx/administracion"
			theme="simple" onsubmit="return validacion()">
			<table>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" 
							value="* Empresa:" /></td>
					<td><s:textfield size="60" id="idEmpresa"
							name="tractoras.empresa" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Escriba el nombre comercial de la empresa que dará de alta." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Nombre(s) Contacto:" /></td>
					<td><s:textfield size="60" id="idNombre"
							name="tractoras.nombreContacto" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Escriba el nombre del adminitrador sin considerar acentos." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Apellido Paterno Contacto:" /></td>
					<td><s:textfield size="60" id="idAppPaterno"
							name="tractoras.appPaterno" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Escriba el apellido paterno del adminitrador sin considerar acentos." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Apellido Materno Contacto:" /></td>
					<td><s:textfield size="60" id="idAppMaterno"
							name="tractoras.appMaterno" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Escriba el apellido materno del adminitrador sin considerar acentos." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Correo Electrónico:" /></td>
					<td><s:textfield size="60" id="idCorreoElectronico"
							name="tractoras.correoElectronico" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Escriba el correo electrónico del administrador." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Confimación Correo electrónico:" /></td>
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
					<td colspan="2">
						<s:submit cssClass="botonenviar" value="Registrar" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

<script type="text/javascript">
		function validacion() {
			valorEmpresa = document.getElementById("idEmpresa").value;
			valorNombre = document.getElementById("idNombre").value;
			valorPaterno = document.getElementById("idAppPaterno").value;
			valorMaterno = document.getElementById("idAppMaterno").value;
			valorCorreo = document.getElementById("idCorreoElectronico").value;
			valorCompara = document.getElementById("idComparaCorreo").value;
			
			if( valorEmpresa == null || valorEmpresa.length == 0 || /^\s+$/.test(valorEmpresa) ) {
				alert("Ingrese el Nombre de la Empresa");
				return false;
			}else if( valorNombre == null || valorNombre.length == 0 || /^\s+$/.test(valorNombre) ) {
				alert("Ingrese el Nombre(s) del Contacto");
				return false;
			}else if( valorPaterno == null || valorPaterno.length == 0 || /^\s+$/.test(valorPaterno) ) {
				alert("Ingrese el Apellido Paterno del Contacto");
				return false;
			}else if( valorMaterno == null || valorMaterno.length == 0 || /^\s+$/.test(valorMaterno) ) {
				alert("Ingrese el Apellido Materno del Contacto");  
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