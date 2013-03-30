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
			<s:label value="Listado de Compradores" />
			<br /> <br />
			<s:label cssClass="camposObligatorios"
				value="Si desea registrar una Comprador nuevo seleccione la opción 'Registrar Comprador'." />
		</legend>
		<br />
		<s:form action="tractoraCompradoresAdd" namespace="/tractora/administracion"
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
									<td class="encabezado_tabla" align="center"><b>Nombre(s) del Comprador</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Apellido Paterno Comprador</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Apellido Materno Comprador</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Correo
											Electrónico</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Eliminar Comprador</b>
									</td>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="listCompradores" status="stat">
									<tr>
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
										
											<a href="#">Eliminar</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><br /><s:submit cssClass="botonenviar"
							value="Registrar Comprador" /></td>
				</tr>
			</table>
		</s:form>
	</fieldset>
</body>
</html>