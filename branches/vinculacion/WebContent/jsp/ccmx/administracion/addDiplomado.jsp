<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ayudas.js"></script>
</head>

<body>
<fieldset id="requerimientos">
	<legend>
		<s:label value="Captura para registro de Diplomado" />
		<br /><br />
		<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
	</legend>
	<br />
	<s:form action="diplomadosShow" namespace="/ccmx/administracion" theme="simple" onsubmit="return validacion()">
		<s:hidden name="diplomado.idDiplomado" id="idDip" value="%{diplomado.idDiplomado}" />
		<s:hidden name="tituloDiplomado" id="idTitDip" value="%{tituloDiplomado}" />
		<table>
			<tr>
				<td><s:label cssClass="etiquetaCaptura" value="* Nombre del Diplomado:" /></td>
				<td>
					<s:textfield size="60" id="nomDiplomado" name="diplomado.tema" maxlength="150" onfocus="javascript:ayudasHelp(0);" onblur="javascript:ayudasHelpBlo(0);"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay0" style="display:none;margin-top:0px;" value="Ingrese el nombre del diplomado." /></td>
			</tr>				
			<tr>
				<td colspan="2">
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
<script type="text/javascript">

function validacion(){
	
	nombreDip = document.getElementById("nomDiplomado").value;
	
	if ( nombreDip.length == 0 || /^\s+$/.test( nombreDip ) ) {
		document.getElementById("nomDiplomado").focus();
		alert("Ingrese el nombre del diplomado");
		return false;
	} else {
		return true;
	}
}

</script>
</body>
</html>