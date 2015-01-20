<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="${pageContext.request.contextPath}/js/ayudas.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/coordinadorConsultorias.js" type="text/javascript"></script>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-150px auto 0 250px';
</script>
</head>
<body>
<fieldset id="requerimientos" >
	<legend>
		<s:label id="tituloCord" value="Captura para registro de PyME" />
		<br /><br />
		<s:label id="ayudaCord" cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
	</legend>
	<br />
	
	<s:form action="coordinadorConsultoriasPyMEsShow" namespace="/consultorias/coordinacion" theme="simple" onsubmit="return validacionAgregar()">
		<table>
			<tr>
				<td><s:label cssClass="etiquetaCaptura" value="* Nombre Comercial:" /></td>
				<td><s:textfield size="60" id="nombreComercial" name="pyMEs.nombreComercial" maxlength="150" onfocus="javascript:ayudasHelp(0);" onblur="javascript:ayudasHelpBlo(0);"></s:textfield></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay0" style="display:none;margin-top:0px;" value="Escriba el nombre comercial." /></td>
			</tr>
			<tr>
				<td><s:label cssClass="etiquetaCaptura" value="* Correo Electrónico:" /></td>
				<td><s:textfield size="60" id="correoElectronico" name="pyMEs.correoElectronico" maxlength="100" onfocus="javascript:ayudasHelp(1);" onblur="javascript:ayudasHelpBlo(1);"></s:textfield></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay1" style="display:none;margin-top:0px;" value="Escriba su correo electrónico." /></td>
			</tr>
			<tr>
				<td><s:label cssClass="etiquetaCaptura" value="* Confimar Correo Electrónico:" /></td>
				<td><s:textfield size="60" id="comparaCorreo" maxlength="100" onfocus="javascript:ayudasHelp(2);" onblur="javascript:ayudasHelpBlo(2);"></s:textfield></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay2" style="display:none;margin-top:0px;" value="Confirme el correo electrónico." /></td>
			</tr>
			
		<!-- CONTACTO TIPO VENTAS -->
			<tr>
				<td><s:label cssClass="etiquetaCaptura" value="Contacto de Ventas" /></td>
				<td>
					&nbsp;
					<s:hidden name="pyMEs.tipoContacto1" id="tipoContacto1" value="Ventas" />
					<s:hidden name="pyMEs.correoElectronicoContacto1" id="correoContacto1" value="" />
				</td>
			</tr>
			
			<tr>
				<td><s:label cssClass="etiquetaCaptura" value="* Nombre(s) :" /></td>
				<td><s:textfield size="60" id="nombreContacto" name="pyMEs.nombreContacto1" maxlength="60" onfocus="javascript:ayudasHelp(3);" onblur="javascript:ayudasHelpBlo(3);" onkeypress="return validaLetra(event)"></s:textfield></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay3" style="display:none;margin-top:0px;" value="Escriba el nombre o nombres del contacto." /></td>
			</tr>
			<tr>
				<td><s:label cssClass="etiquetaCaptura" value="* Apellido Paterno :" /></td>
				<td><s:textfield size="60" id="apPaterno" name="pyMEs.appPaterno1" maxlength="60" onfocus="javascript:ayudasHelp(4);" onblur="javascript:ayudasHelpBlo(4);" onkeypress="return validaLetra(event)"></s:textfield></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay4" style="display:none;margin-top:0px;" value="Escriba el apellido paterno del contacto." /></td>
			</tr>
			<tr>
				<td><s:label cssClass="etiquetaCaptura" value="* Apellido Materno :" /></td>
				<td><s:textfield size="60" id="apMaterno" name="pyMEs.appMaterno1" maxlength="60" onfocus="javascript:ayudasHelp(5);" onblur="javascript:ayudasHelpBlo(5);" onkeypress="return validaLetra(event)"></s:textfield></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay5" style="display:none;margin-top:0px;" value="Escriba el apellido materno del contacto." /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay6" style="display:none;margin-top:0px;" value="Seleccione una de las Grandes Empresas que se asignará a la PyME." /></td>
			</tr>
			<tr>
				<td><s:label cssClass="etiquetaCaptura" value="* Grandes Empresas :" /></td>
				<td>
					<select id="optTrac" name="pyMEs.idTractora" style="width: 200px;" onfocus="javascript:ayudasHelp(6);" onblur="javascript:ayudasHelpBlo(6);">
						<option selected="selected" value="0">--Seleccione una de las Grandes Empresas--</option>
						<s:iterator value="tractorasList" status="stat">
							<option value="${idUsuario}">${empresa}</option>
						</s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<br />
					<s:submit cssClass="botonenviar" value="Registrar PyME" />
				</td>
			</tr>
		</table>
	</s:form>
</fieldset>
</body>
</html>