<?xml version="1.0" encoding="UTF-8"?>
<%@taglib
	uri="/struts-tags"
	prefix="s"%>
<%@page
	pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html
	xmlns="http://www.w3.org/1999/xhtml"
	xml:lang="es">
<head>
<meta
	http-equiv="Content-Type"
	content="text/html; charset=UTF-8" />
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
			<s:label value="Administración de Diplomados" />
			<br /> <br />
			<s:label
				cssClass="camposObligatorios"
				value="Seleccione la opción 'Registrar Diplomado' para agregar un Diplomado, seleccione la opción 'Eliminar' para eliminarlo." />
		</legend>
		<br />
		<s:form
			action="diplomadoAdd"
			namespace="/ccmx/administracion/diplomados"
			theme="simple">
			<table
				width="800px"
				cellspacing="1"
				cellpadding="1">
				<thead>
					<tr>
						<td
							class="encabezado_tabla"
							align="center"><b><s:text name="No." /></b></td>
						<td
							class="encabezado_tabla"
							align="center"><b><s:text name="Título" /></b></td>
						<td
							class="encabezado_tabla"
							align="center"><b><s:text name="Generación" /></b></td>
						<td
							class="encabezado_tabla"
							align="center"><b><s:text name="Ubicación" /></b></td>
						<td
							class="encabezado_tabla"
							align="center"><b><s:text name="Fecha" /></b></td>
						<td
							class="encabezado_tabla"
							align="center"><b><s:text name="Información" /></b></td>
						<td
							class="encabezado_tabla"
							align="center"><b><s:text name="Acción" /></b></td>
					</tr>
				</thead>
				<tbody>
					<s:iterator
						value="listDiplomados"
						status="stat">
						<tr>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center">${stat.count}</td>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center">${tema}</td>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center">${generacion}</td>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center">${ubicacion}</td>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center">${fecha}</td>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center"><a href="${url}">Ver información</a></td>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center"><a
								href="${pageContext.request.contextPath}/ccmx/administracion/diplomados/diplomadoShow.do?idDiplomado=${idDiplomado}&tituloDiplomado=${tema}&fechaDip=${fecha}">Eliminar</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<table>
				<tr>
					<td><br /> <s:submit
							cssClass="botonenviar"
							value="Registrar Diplomado" /></td>
				</tr>
			</table>
		</s:form>
	</fieldset>
</body>
</html>