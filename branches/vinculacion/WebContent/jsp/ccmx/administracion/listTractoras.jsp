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
			<s:label value="Administración de Empresas Tractoras" />
			<br /> <br />
			<s:label cssClass="camposObligatorios"
				value="Seleccione la opción 'Registrar Tractora' para agregar una nueva Empresa Tractora, seleccione la opción 'Modificar' para modificarla." />
		</legend>
		<br />
		<s:form action="tractoraAdd" namespace="/ccmx/administracion"
			theme="simple">
			<table>
				<tr>
					<td>
						<table
							width="800px"
							cellspacing="1"
							cellpadding="1">
							<thead>
								<tr>
									<td class="encabezado_tabla" align="center"><b>No.</b></td>
									<td class="encabezado_tabla" align="center"><b>Empresa Tractora</b> </td>
									<td class="encabezado_tabla" align="center"><b>Nombre(s) Contacto</b> </td>
									<td class="encabezado_tabla" align="center"><b>Apellido Paterno Contacto</b></td>
									<td class="encabezado_tabla" align="center"><b>Apellido Materno Contacto</b></td>
									<td class="encabezado_tabla" align="center"><b>Correo Electrónico</b></td>
									<td class="encabezado_tabla" align="center"><b>Modificar Tractora</b></td>
								</tr>
							</thead>
							<tbody>
								<s:set var="contador" value="0" />
								<s:iterator value="listTractoras" status="stat">
								<s:if test="%{idTractoraPadre==0}">
								<s:set var="cnt" value="#contador=#contador+1" />
									<tr>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${cnt}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${empresa}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${nombreContacto}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${appPaterno}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${appMaterno}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${correoElectronico}</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">
											<a
												href='${pageContext.request.contextPath}/ccmx/administracion/tractoraAdd.do?tractoras.idUsuario=${idUsuario}'>Modificar</a>
										</td>
									</tr>
								</s:if>
								</s:iterator>
							</tbody>
						</table></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><br /><s:submit cssClass="botonenviar"
							value="Registrar Tractora" /></td>
				</tr>
			</table>
		</s:form>
		<br /><br />
		<div id="showDetalles">
			<input class="botonenviar" value="Ver registros de Tractoras" type="button" onclick="javascript:document.getElementById('detallesTrac').style.display='block'; javascript:document.getElementById('showDetalles').style.display='none';" />
		</div>
		<div id="detallesTrac" style="display: none;">
			<table width="800px" cellspacing="1" cellpadding="1">
				<thead>
					<tr>
						<td class="encabezado_tabla" align="center" style="width: 5%;"><b>No.</b></td>
						<td class="encabezado_tabla" align="center"><b>Empresa Tractora</b> </td>
						<td class="encabezado_tabla" align="center"><b>Total de Compradores</b></td>
						<td class="encabezado_tabla" align="center"><b>Total de Requerimientos Registrados</b></td>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="listDetallesTractoras" status="stat">
						<tr>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${empresa}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${compradores}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${requerimientos}</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<br />
			<input class="botonenviar" value="Regresar" type="button" onclick="javascript:document.getElementById('detallesTrac').style.display='none'; javascript:document.getElementById('showDetalles').style.display='block';" />
		</div>		
	</fieldset>
</body>
</html>