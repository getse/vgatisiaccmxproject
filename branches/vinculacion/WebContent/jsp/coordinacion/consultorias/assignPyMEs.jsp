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
</head>
<body>
<fieldset id="requerimientos" >
	<legend>
		<s:label id="tituloCord" value="Asignación de PyMEs" />
		<br /><br />
		<s:label id="ayudaCord" cssClass="camposObligatorios" value="Seleccione una o varias PyMEs para asignarlas a un Consultora mediante la opción 'Elegir Consultora'." />
	</legend>
	<br />
	
	<s:form name="frmAsignacion" action="coordinadorConsultoriasPyMEsShow" namespace="/consultorias/coordinacion" theme="simple">
		<s:hidden id="idHidIdConsultora" name="idConsultora" value="%{idConsultora}" />
		<s:hidden id="idHidIdPyMEs" name="idPyMEs" value="%{idPyMEs}" />
		<table width="99%" cellspacing="1" cellpadding="1">
			<thead>
				<tr>
					<td class="encabezado_tabla" align="center" style="width: 5%;"><b>No.</b></td>
					<td class="encabezado_tabla" align="center"><b>Nombre Grandes Empresas</b></td>
					<td class="encabezado_tabla" align="center"><b>Nombre comercial</b></td>
					<td class="encabezado_tabla" align="center"><b>Nombre contacto</b></td>
					<td class="encabezado_tabla" align="center"><b>Correo electrónico contacto</b></td>
					<td class="encabezado_tabla" align="center"><b>Seleccionar</b></td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="listPyMEs" status="stat">
					<tr>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${tractora}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${nombreComercial}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${nombreContacto1} ${appPaterno1} ${appMaterno1}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${correoElectronicoContacto1}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center"><s:checkbox id="idAsigna%{idUsuario}" name="checkbox" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:form>
	<br />
	<table class="submit_tabla">
		<tr >
			<td>
				<input class="botonenviar" type="submit" value="Marcar/Desmarcar Todas" onclick="todas();" />
			</td>
			<td>
				<input class="botonenviar" type="submit" value="Elegir Consultora" onclick="muestraAsignar();" />
			</td>
		</tr>
	</table>
	<div id="contConsultor" style="display: none;">
		<br />
		<br />
		<s:label cssClass="camposObligatorios" value="Elija la Consultora a la cual serán asignadas las PyMEs y seleccione la opción 'Asignar PyMEs'." />
		<br />
		<br />
		<table class="submit_tabla">
			<tr>
				<td>
					<select name="listaConsultoras" id="idConsultoraSeleccionada" style="width: 600px;">
						<option value="-1">--Seleccione un Consultora--</option>
						<s:iterator value="%{consultorasList}" status="stat">
							<option value="${idUsuario}">${empresa}</option>
						</s:iterator>
					</select>
					<br />
					<br />
					<input class="botonenviar" type="submit" value="Asignar PyMEs" onclick="asignaConsultora();" />
				</td>
			</tr>
		</table>
	</div>
</fieldset>
</body>
</html>