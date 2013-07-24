<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ayudas.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ccmx.js"></script>
</head>

<body>
<fieldset id="requerimientos">
	<legend>
		<s:label value="Captura para registro de Diplomado" />
		<br /><br />
		<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
	</legend>
	<br />
	<s:form action="diplomadosShow" namespace="/ccmx/administracion" theme="simple" onsubmit="return registraDip()">
		<s:hidden name="diplomado.idDiplomado" id="idDip" value="%{diplomado.idDiplomado}" />
		<s:hidden name="tituloDiplomado" id="idTitDip" value="%{tituloDiplomado}" />
		<table>
			<tr>
				<td><s:label cssClass="etiquetaCaptura" value="* Nombre del Diplomado:" /></td>
				<td>
					<s:textfield size="80" id="nomDiplomado" name="diplomado.tema" maxlength="150" onfocus="javascript:ayudasHelp(0);" onblur="javascript:ayudasHelpBlo(0);"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay0" style="display:none;margin-top:0px;" value="Ingrese el nombre del diplomado." /></td>
			</tr>
		</table>
		<table width="86%">	
			<tr>
				<td><s:label id="ayudasDisplay1" style="display:none;margin-top:0px;" cssClass="etiquetaAyuda" value="* Seleccione el número de generaciones que tendrá el diplomado" /></td>
				<td><s:label id="ayudasDisplay2" style="display:none;margin-top:0px;" cssClass="etiquetaAyuda" value="* Seleccione el año en que se impartirá el diplomado" /></td>
			</tr>
			<tr>
				<td>
					<s:label cssClass="etiquetaCaptura" value="* Número de generaciones:" />
					<select id="generacion" name="generaciones" style="width: 180px;" onfocus="javascript:ayudasHelp(1);" onblur="javascript:ayudasHelpBlo(1);" >
						<option value="0">--Seleccione una opción--</option>
						<s:iterator value="(10).{ #this }" status="stat">
							<option value="${stat.count}">${stat.count}</option>
						</s:iterator>
					</select>
				</td>
				<td>
					<s:label cssClass="etiquetaCaptura" value="* Año del diplomado:" />
					<select id="anio" name="diplomado.year" style="width: 180px;" onfocus="javascript:ayudasHelp(2);" onblur="javascript:ayudasHelpBlo(2);">
						<option value="0">--Seleccione una opción--</option>
						<s:iterator value="menuAnios" status="stat">
							<option value="${menuAnios[stat.index]}">${menuAnios[stat.index]}</option>
						</s:iterator>
					</select>
				</td>
			</tr>
		</table>
		<br /><br />
		<table>
			<tr>
				<td>
					<s:if test="diplomado == null">
						<s:submit cssClass="botonenviar" value="Registrar Diplomado" />
					</s:if>
					<s:else>
						<s:submit cssClass="botonenviar" value="Actualizar Diplomado" />
					</s:else>
				</td>
			</tr>
		</table>
	</s:form>
</fieldset>
</body>
</html>