<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script
	src="${pageContext.request.contextPath}/js/ccmx.js"
	type="text/javascript"></script>
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
			<s:label value="Captura para registro de Tractora" />
			<br /> <br />
			<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<br />
		<s:form action="tractorasShow" namespace="/ccmx/administracion"
			theme="simple" onsubmit="return validacionAddTractora()">
			<s:hidden name="tractoras.idUsuario" value="%{tractoras.idUsuario}" />
			<s:hidden name="credenciales" value="%{tractoras.correoElectronico}" />
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
					<td><s:label cssClass="etiquetaCaptura" value="* Confirmar Correo Electrónico:" /></td>
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
</body>
</html>