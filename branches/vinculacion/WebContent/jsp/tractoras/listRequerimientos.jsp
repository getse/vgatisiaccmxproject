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
<fieldset id="requerimientos"><legend>Listado de Requerimientos<br />
<br />
<s:label
	cssClass="camposObligatorios"
	value="Si desea agregar un requerimiento nuevo seleccione la opción 'Agregar Requerimiento'" /></legend><br />
<s:form
	action="addReq"
	namespace="/tractora/requerimientos"
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
						<td
							class="encabezado_tabla"
							align="center"><b>ID</b></td>
						<td
							class="encabezado_tabla"
							align="center"><b>Requerimiento</b></td>
						<td
							class="encabezado_tabla"
							align="center"><b>Fecha de suministro</b></td>
						<td
							class="encabezado_tabla"
							align="center"><b>Fecha en que expira</b></td>
						<td
							class="encabezado_tabla"
							align="center"><b>Acciones</b></td>
					</tr>
				</thead>
				<tbody>
					<s:iterator
						value="listRequerimientos"
						status="stat">
						<tr>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center"><a
								href='${pageContext.request.contextPath}/tractora/requerimientos/addReq.do?requerimientos.idRequerimiento=${idRequerimiento}'>
							${idRequerimiento} </a></td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}">
							<a
								href='${pageContext.request.contextPath}/tractora/requerimientos/addReq.do?requerimientos.idRequerimiento=${idRequerimiento}'>
							${producto} </a></td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}">
							${fechaSuministro}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}">
							${fechaExpira}</td>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center"><a href="javascript:del('${idRequerimiento}');"> Eliminar </a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td><br />
			<s:submit
				cssClass="botonenviar"
				value="Agregar Requerimiento" /></td>
		</tr>
	</table>
</s:form></fieldset>
<s:form
	name="frmBorrar"
	action="deleteReq"
	namespace="/tractora/requerimientos"
	theme="simple"
	method="get">
	<s:hidden
		id="idIdReq"
		name="requerimientos.idRequerimiento"
		value="%{requerimientos.idRequerimiento}" />
	<s:hidden
		id="idCve"
		name="cve"
		value="" />
</s:form>
<script type="text/javascript">
	function del(id) {
		var cve = prompt('Estimado usuario. Para eliminar un Requerimiento es necesario confirme su contraseña de acceso:');
		document.getElementById('idCve').value = cve;
		document.getElementById('idIdReq').value = id;
		document.frmBorrar.submit();
	}
</script>
</body>
</html>