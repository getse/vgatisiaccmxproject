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
<s:if test="mensaje!=null">
	<br />
	<table class="nota">
		<tr>
			<td class="imgNota">
				<s:if test="mensaje.respuesta==0">
					<img src="${pageContext.request.contextPath}/img/palomitaverde.gif" />
				</s:if>
				<s:else>
					<img src="${pageContext.request.contextPath}/img/warning.png" />
				</s:else>
			</td>
			<td class="contenidoNota">
				<s:property value="mensaje.mensaje" />
			</td>
		</tr>
	</table>
</s:if>
<fieldset id="requerimientos" >
	<legend>
		<s:label id="tituloCord" value="PyMEs" />
		<br /><br />
		<s:label id="ayudaCord" cssClass="camposObligatorios" value="Seleccione una opción." />
	</legend>
	<br />

	<table id="menuPyMEs" style="margin: 0px;" width="99%">
		<tr>
			<td align="center" style="width: 20%" class="cuerpo1TablaResumen">
				<s:form name="frmAddPyME" action="coordinadorConsultoriasPyMEsAdd" namespace="/consultorias/coordinacion" theme="simple">
					<input class="botonenviar" value="Agregar PyMEs" type="button" style="width: 100%" onclick="javascript:addPyME();" />
				</s:form>
			</td>
			<td align="center" style="width: 20%" class="cuerpo2TablaResumen">
				<s:form name="frmAssignPyME" action="coordinadorConsultoriasPyMEsAssign" namespace="/consultorias/coordinacion" theme="simple">
					<input class="botonenviar" value="Asignar PyME" type="button" style="width: 100%" onclick="javascript:showAsigna();" />
				</s:form>
			</td>
			<td align="center" style="width: 20%" class="cuerpo1TablaResumen">
				<s:form name="frmReAssignPyME" action="coordinadorConsultoriasPyMEsReAssign" namespace="/consultorias/coordinacion" theme="simple">
					<input class="botonenviar" value="Reasignar PyME" type="button" style="width: 100%" onclick="javascript: showReasigna();" />
				</s:form>
			</td>
			<td align="center" style="width: 20%" class="cuerpo2TablaResumen">
				<s:form name="frmDiplomasPyME" action="coordinadorConsultoriasDiplomasPyMEs" namespace="/consultorias/coordinacion" theme="simple">
					<input class="botonenviar" value="Solicitar diplomas" type="button" style="width: 100%" onclick="javascript: showDiplomas();" />
				</s:form>
			</td>
			<td align="center" style="width: 20%" class="cuerpo1TablaResumen">
				<s:form name="frmBusquedaPyME" action="coordinadorConsultoriasBusquedaPyMEs" namespace="/consultorias/coordinacion" theme="simple">
					<input class="botonenviar" value="Búsqueda de PyMEs" type="button" style="width: 100%" onclick="javascript:showBusqueda();" />
				</s:form>
			</td>
		</tr>
	</table>
</fieldset>
</body>
</html>