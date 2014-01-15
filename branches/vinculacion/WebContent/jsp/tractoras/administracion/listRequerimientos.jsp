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
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-330px auto 0 250px';
</script>
</head>
<body>
<s:if test="mensaje!=null">
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
		<s:label value="Listado de Requerimientos" />
		<br /> <br />
		<s:label cssClass="camposObligatorios"
			value="Si desea agregar un requerimiento nuevo seleccione la opción 'Agregar Requerimiento'. Seleccione 'Consultar' para consultar uno existente." />
	</legend>
	<br />
<s:form
	action="tractoraRequerimientoAdd"
	namespace="/tractora/administracion"
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
							align="center"><b>No.</b></td>
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
							align="center"><b>Consultar Requerimiento</b></td>
						<td
							class="encabezado_tabla"
							align="center"><b>Eliminar Requerimiento</b></td>
					</tr>
				</thead>
				<tbody>
					<s:iterator
						value="listRequerimientos"
						status="stat">
						<tr>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
								${stat.count}
							</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}">
								${producto}
							</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}">
								${fechaSuministro==null||fechaSuministro==''?(bIndefinido?'Indefinido':bVariasFechas?'Varias
							Fechas':bContinuoSuministro?'Continuo':''):fechaSuministro}
							</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}">
								${fechaExpira==null||fechaExpira==''?bContinuoExpira?'Continuo':'':fechaExpira}
							</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
								<a href='${pageContext.request.contextPath}/tractora/administracion/tractoraRequerimientoAdd.do?requerimientos.idRequerimiento=${idRequerimiento}'> Consultar </a>
							</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
								<a href="javascript:del('${idRequerimiento}');"> Eliminar </a>
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
			<td><br />
			<s:submit
				cssClass="botonenviar"
				value="Agregar Requerimiento" /></td>
		</tr>
	</table>
	<br /><br />
	<div id="detalle" style="display: block;">
		<table>
			<tr>
				<td>
					<table width="800px" cellspacing="1" cellpadding="1">
						<thead>
							<tr>
								<td class="encabezado_tabla" align="center" style="width: 5%;"><b>No.</b></td>
								<td class="encabezado_tabla" align="center"><b>Comprador</b> </td>
								<td class="encabezado_tabla" align="center"><b>Requerimientos Totales</b></td>
								<td class="encabezado_tabla" align="center"><b>Requerimientos Activos</b></td>
							</tr>
						</thead>
						<tbody>
							<s:set var="_r" value="0" />
							<s:set var="_a" value="0" />
							<s:iterator value="listDetalleRequerimientosTractora" status="stat">
								<tr>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${session.Usuario.idUsuario == idUsuario ? 'Administrador' : empresa}</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${requerimientos}</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${requerimientosActivos}</td>
									<s:set var="contadorR" value="#_r=#_r+requerimientos" />
									<s:set var="contadorA" value="#_a=#_a+requerimientosActivos" />
								</tr>
							</s:iterator>
							<tr>
								<td class="cuerpo3TablaResumen" align="center">Total</td>
								<td class="cuerpo3TablaResumen" align="center">&nbsp;</td>
								<td class="cuerpo3TablaResumen" align="center">${contadorR}</td>
								<td class="cuerpo3TablaResumen" align="center">${contadorA}</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
	</div>
</s:form></fieldset>
<s:form
	name="frmBorrar"
	action="tractoraRequerimientoDelete"
	namespace="/tractora/administracion"
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