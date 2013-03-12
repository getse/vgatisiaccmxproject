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
			Listado de Tractoras<br /><br />
			<s:label cssClass="camposObligatorios"
				value="Si desea registrar una Tractora nueva seleccione la opción 'Registrar Tractora'" />
		</legend>
		<br />
		<s:form action="addTra" namespace="/ccmx/administracion/tractoras"
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
									<td class="encabezado_tabla" align="center"><b>Empresa</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Nombre(s) Contacto</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Apellido
											Paterno Contacto</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Apellido
											Materno Contacto</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Correo
											Electrónico</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Eliminar Tractora</b>
									</td>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="listTractoras" status="stat">
									<tr>
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
							value="Registrar Tractora" /></td>
				</tr>
			</table>
		</s:form>
	</fieldset>
</body>
</html>