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
						<img
							src="${pageContext.request.contextPath}/img/palomitaverde.gif" />
					</s:if> <s:else>
						<img src="${pageContext.request.contextPath}/img/warning.png" />
					</s:else>
				</td>
				<td class="contenidoNota"><s:property value="mensaje.mensaje" />
				</td>
			</tr>
		</table>
	</s:if>
	<fieldset id="requerimientos">
		<legend>
			<s:label value="Administración de PyMEs" />
			<br /> <br />
			<s:label cssClass="camposObligatorios"
				value="Seleccione la opción 'Registrar PyME' para agregar una nueva PyME, seleccione la opción 'Modificar' para modificarla." />
		</legend>
		<br />
		<s:form action="PyMEAdd" namespace="/ccmx/administracion/pymes"
			theme="simple">

			<table>
				<tr>
					<td>
						<table width="800px" cellspacing="1" cellpadding="1">
							<thead>
								<tr>
									<td class="encabezado_tabla" align="center"><b>No.</b></td>
									<td class="encabezado_tabla" align="center"><b>Nombre
											Comercial</b></td>
									<td class="encabezado_tabla" align="center"><b>Correo
											electrónico</b></td>
									<td class="encabezado_tabla" align="center"><b>Nombre
											Contacto</b></td>
									<td class="encabezado_tabla" align="center"><b>Apellido
											Paterno Contacto</b></td>
									<td class="encabezado_tabla" align="center"><b>Apellido
											Materno Contacto</b></td>
									<td class="encabezado_tabla" align="center"><b>Desactivar
											PyME</b></td>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="listPyMEs" status="stat">
									<s:if test="%{idUsuarioPadre==1}">
										<tr>
										<td
												class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
												align="center">${stat.count}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${nombreComercial}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${correoElectronico}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${nombreContacto1}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${appPaterno1}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${appMaterno1}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center"><a href="#">Desactivar</a></td>
										</tr>
									</s:if>
								</s:iterator>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td><br />
					<s:submit cssClass="botonenviar" value="Registrar PyME" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

</body>
</html>