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
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-325px auto 0 250px';
</script>
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
			<s:label value="Administración de Usuarios" />
			<br /> <br />
			<s:label cssClass="camposObligatorios"
				value="Lista de credenciales de acceso de los usuarios del sistema." />
		</legend>
		<br />
		<s:form action="#" namespace="/ccmx/administracion" theme="simple">
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
									<td class="encabezado_tabla" align="center"><b>Perfil</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Correo electr&oacute;nio</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Contrase&ntilde;a</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Acciones</b></td>
								</tr>
							</thead>
							<tbody>
								<s:set var="contador" value="0" />
								<s:iterator value="usuarios" status="stat">
									<tr>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${rol}</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${id}</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${credenciales}</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<a href="${pageContext.request.contextPath}/ccmx/administracion/usuariosSend.do?correo=${id}&credencial=${credenciales}">Enviar correo</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table></td>
				</tr>
			</table>
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
		</s:form>
	</fieldset>
</body>
</html>