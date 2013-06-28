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
		<s:label id="tituloCord" value="Generar diplomas" />
		<br /><br />
		<s:label id="ayudaCord" cssClass="camposObligatorios" value="Seleccione la opción imprimir para generar la Diploma correspondiente a la PyME o marque las PyMEs que desea registrar." />
	</legend>
	<br />
	
	<s:form name="frmDiplomas" action="coordinadorConsultoriasPyMEsShow" namespace="/consultorias/coordinacion" theme="simple" onsubmit="return validacionDiplomas()">
		<s:hidden id="idHidIdPyMEs" name="idPyMEs" value="%{idPyMEs}" />
		<table width="99%" cellspacing="1" cellpadding="1">
			<thead>
				<tr>
					<td class="encabezado_tabla" align="center" style="width: 5%;"><b>No.</b></td>
					<td class="encabezado_tabla" align="center"><b>Nombre de le PyME</b></td>
					<td class="encabezado_tabla" align="center"><b>Fecha de término</b></td>
					<td class="encabezado_tabla" align="center"><b>Horas de consultoría</b></td>
					<td class="encabezado_tabla" align="center"><b>Empresa consultora</b></td>
					<td class="encabezado_tabla" align="center"><b>Ver diploma</b></td>
					<td class="encabezado_tabla" align="center"><b>Registrar diploma</b></td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="listDiplomasPyMEs" status="stat">
					<tr>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${nombreComercial}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${fecha}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${horas}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${tractora}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
							<a href="#">Ver</a>
						</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
							<s:checkbox id="idAsigna%{idServicioConsultoria}" name="checkbox" />
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<table class="submit_tabla">
		<tr >
			<td>
				<input class="botonenviar" type="button" value="Marcar/Desmarcar Todas" onkeypress="todasDiplomas();" onclick="todasDiplomas();" />
			</td>
			<td>
				<s:submit cssClass="botonenviar" value="Registrar Diplomas otorgadas" onclick="return regDiplomas()" />
			</td>
		</tr>
	</table>
	</s:form>
</fieldset>
</body>
</html>