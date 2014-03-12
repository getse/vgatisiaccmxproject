<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script
	src="${pageContext.request.contextPath}/js/ccmx.js"
	type="text/javascript"></script>
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
				value="Lista de credenciales de acceso de los usuarios del sistema, seleccione un perfil de usuario para filtrar los resultados." />
		</legend>
		<br />
		<table width="80%">
			<tr>
				<td align="center">
					<input class="botonenviar" id="reporte2" value="Todos" type="button" style="width: 232px;" onclick="javascript:filtrarRoles(null);" />
				</td>
				<td align="center">
					<input class="botonenviar" id="reporte2" value="Administrador Consultoras" type="button" style="width: 232px;" onclick="javascript:filtrarRoles('AdministradorConsultores');" />
				</td>
				<td align="center">
					<input class="botonenviar" id="reporte2" value="Consultores" type="button" style="width: 232px;" onclick="javascript:filtrarRoles('Consultor');" />
				</td>
				<td align="center">
					<input class="botonenviar" id="reporte2" value="Coordinador Consultoras" type="button" style="width: 232px;" onclick="javascript:filtrarRoles('CoordinadorConsultoras');" />
				</td>
			</tr>
			<tr>
				<td align="center">
					<input class="botonenviar" id="reporte2" value="PyMEs" type="button" style="width: 232px;" onclick="javascript:filtrarRoles('PyME');" />
				</td>
				<td align="center">
					<input class="botonenviar" id="reporte2" value="Administrador Empresas Eje" type="button" style="width: 232px;" onclick="javascript:filtrarRoles('Tractora');" />
				</td>
				<td align="center">
					<input class="botonenviar" id="reporte2" value="Compradores" type="button" style="width: 232px;" onclick="javascript:filtrarRoles('Comprador');" />
				</td>
				<td align="center">
					<input class="botonenviar" id="reporte2" value="Coordinador Diplomados" type="button" style="width: 232px;" onclick="javascript:filtrarRoles('CoordinadorDiplomados');" />
				</td>
			</tr>
		</table>
		<br />
		<s:form action="#" namespace="/ccmx/administracion" theme="simple">
			<table>
				<tr>
					<td>
						<table width="940px" cellspacing="1" cellpadding="1">
							<thead>
								<tr>
									<td class="encabezado_tabla" align="center" style="width: 34px !important"><b>No.</b></td>
									<td class="encabezado_tabla" align="center"><b>Perfil</b></td>
									<td class="encabezado_tabla" align="center"><b>Correo electr&oacute;nio</b></td>
									<td class="encabezado_tabla" align="center"><b>Contrase&ntilde;a</b></td>
									<td class="encabezado_tabla" align="center"><b>Acciones</b></td>
									<td class="encabezado_tabla" align="center"><b>Facultades</b></td>
								</tr>
							</thead>
							<tbody>
								<s:set var="contador" value="0" />
								<s:iterator value="usuarios" status="stat">
									<tr id="${rol}.${stat.count}" style="display: table-row;">
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center" style="width: 34px !important">${stat.count}</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${rol == 'AdministradorCCMX' ? 'Administrador CCMX' : rol == 'AdministradorConsultores' ? 'Administrador Consultoras' : rol == 'Comprador' ? 'Comprador' : rol == 'Consultor' ? 'Consultor' : rol == 'CoordinadorConsultoras' ? 'Coordinador Consultoras' : rol == 'CoordinadorDiplomados' ? 'Coordinador Diplomados' : rol == 'PyME' ? 'PyME' : rol == 'Tractora' ? 'Administrador Tractora' : 'Indefinido'}</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${id}</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${credenciales}</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<a href="${pageContext.request.contextPath}/ccmx/administracion/usuarioSend.do?correo=${id}&credencial=${credenciales}">Enviar correo</a>
										</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<a href="#" onclick="javascript:confirmAccess('${idUsuario}', '${id}', '${credenciales}');">Acceder con este usuario</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
		</s:form>
	</fieldset>
	<s:form name="frmAccess" action="usuarioAccess.do" namespace="/ccmx/administracion" theme="simple">
		<s:hidden id="idHidIdUsuario" name="idUsuario" value="" />
		<s:hidden id="idHidCorreo" name="correo" value="" />
		<s:hidden id="idHidCredencial" name="credencial" value="" />
	</s:form>
</body>
</html>