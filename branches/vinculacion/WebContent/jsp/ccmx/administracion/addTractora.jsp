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
	<fieldset id="tractoras">
		<legend>
			Captura para registro de Tractoras
			<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<br />

		<s:form action="showLisTra" namespace="/ccmx/administracion/tractoras"
			theme="simple">
			<table>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Nombre:" />
					</td>
					<td><s:textfield size="50" id="idCampoEmpresa"
							name="tractoras.nombreContacto" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Ingrese su Nombre(s)." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Apellido Paterno:" /></td>
					<td><s:textfield size="50" id="idCampoEmpresa"
							name="tractoras.appPaterno" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Apellido Materno:" /></td>
					<td><s:textfield size="50" id="idCampoEmpresa"
							name="tractoras.appMaterno" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Correo Electrónico:" /></td>
					<td><s:textfield size="50" id="idCampoEmpresa"
							name="tractoras.correoElectronico" maxlength="250"></s:textfield>
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Puesto:" />
					</td>
					<td><s:textfield size="50" id="idCampoEmpresa"
							name="tractoras.puesto" maxlength="250"></s:textfield>
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Telefono:" />
					</td>
					<td><s:textfield size="50" id="idCampoEmpresa"
							name="tractoras.telefonos" maxlength="250"></s:textfield></td>
				</tr>

				<tr>
					<td colspan="2">
						<s:submit cssClass="botonenviar" value="Registrar" />
					</td>
				</tr>
			</table>
			<s:hidden name="tractoras.idUsuario" id="idUsuario" value="1" />
			<s:hidden name="tractoras.idUsuarioPadre" id="idUsuarioPadre" value="1" />
			<s:hidden name="tractoras.idTractoraPadre" id="idTractoraPadre" value="1" />
			<s:hidden name="tractoras.empresa" id="empresa" value="Mi Empresa" />
		</s:form>
	</fieldset>


</body>
</html>