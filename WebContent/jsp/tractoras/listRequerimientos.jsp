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
<fieldset id="requerimientos"><legend>Listado de
Requerimientos<s:label cssClass="camposObligatorios"
	value="Si desea agregar un requerimiento nuevo seleccione la opción 'Agregar Requerimiento'" /></legend><br />

<s:form action="addReq" namespace="/tractora/requerimientos"
	theme="simple">
	<table>
		<tr>
			<td>
			<table bgcolor="#ffffff" width="100%" cellspacing="1" cellpadding="1">
					<thead>
						<tr>
							<td class="encabezado_tabla" align="center"><b>#</b></td>
							<td class="encabezado_tabla" align="center"><b>Requerimiento</b></td>
							<td class="encabezado_tabla" align="center"><b>Fecha de incio</b></td>
							<td class="encabezado_tabla" align="center"><b>Fecha de finalización</b></td>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listRequerimientos" status="stat">
								<tr>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center" >
										${idRequerimiento}
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" >
									<a href='${pageContext.request.contextPath}/tractora/requerimientos/addReq.do?requerimientos.idRequerimiento=${idRequerimiento}'>
										${requerimiento}
									</a>
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" >
										${fechaInicio}
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" >
										${fechaFin}
									</td>
								</tr>
						</s:iterator>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td><s:submit cssClass="botonenviar"
				value="Agregar Requerimiento" /></td>
		</tr>
	</table>
--<s:property value="#session.Usuario" />--
</s:form></fieldset>
<script type="text/javascript">
	
</script>
</body>

</html>