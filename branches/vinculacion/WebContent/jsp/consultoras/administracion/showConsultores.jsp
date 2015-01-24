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
			<s:label value="Administración de Consultores  " />
			<br /> 
		</legend>
		<br />
		<s:form action="consultoraConsultoresAdd" namespace="/consultor/administracion"
			onsubmit="javascript: $(idProcesa)[0].style.display = 'block';" theme="simple">
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
									<td class="encabezado_tabla" align="center"><b>Asignar PyME</b>
									</td>
								</tr>
							</thead>
							<tbody>
								<s:set var="contador" value="0" />
								<s:iterator value="consultorasList" status="stat">
									<tr>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${stat.count}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${nombreContacto}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${appPaternoContacto}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${appMaternoContacto}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${correoElectronico}</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">
											<a
												href='${pageContext.request.contextPath}/consultor/administracion/consultoraPymes.do?consultoras.idUsuario=${idUsuario}' onclick="javascript: $(idProcesa)[0].style.display = 'block';">Asignar</a>
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
							value="Registrar consultor" /></td>
				</tr>
			</table>
		</s:form>
	</fieldset>
	</body>
</html>