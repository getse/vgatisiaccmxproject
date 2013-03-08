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
<s:label cssClass="camposObligatorios"
	value="Seleccione la opción que corresponda a su tipo de producto.'" />

<s:form action="addReq" name="frmProds" namespace="/tractora/requerimientos"
	theme="simple">
	<table>
		<tr>
			<td>
			<br />
			<table width="570px" cellspacing="1" cellpadding="1">
				<thead>
					<tr>
						<td class="encabezado_tabla" align="center"><b>ID</b></td>
						<td class="encabezado_tabla" align="center"><b>Descripción</b></td>
						<td class="encabezado_tabla" align="center" style="width: 30px;"><b>Elegir</b></td>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="listProductos" status="stat">
						<tr>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center"><a
								href='${pageContext.request.contextPath}/tractora/requerimientos/addReq.do?requerimientos.idRequerimiento=${idRequerimiento}'>
							${idProducto} </a></td>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}">
							<a
								href='${pageContext.request.contextPath}/tractora/requerimientos/addReq.do?requerimientos.idRequerimiento=${idRequerimiento}'>
							${producto} </a></td>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}">
							<input class="checkbox" id="idCheckProducto${idProducto}" name="checkProducto" type="checkbox" value="${idProducto}"  onchange="clearc(this);""/>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			</td>
		</tr>
	</table>
	<span class="close" onclick="regresa();">Aceptar</span>
</s:form>
<s:url id="uri" action="addReq" encode="true" namespace="/tractora/requerimientos" includeContext="" />
<s:form id="idFrmRegresar" name="frmRegresar" action="%{uri}" method="get" target="_top">
	<s:hidden id="idDom" name="requerimientos.idRequerimiento" />
</s:form>
<script type="text/javascript">
	function clearc(check) {
		for (i=0;ele=document.frmProds.elements[i];i++)
			  if (ele.type=='checkbox')
			    if (ele.checked)
			    	ele.checked=false;
			check.checked=true;
	}
	function regresa() {
		document.frmRegresar.submit();
	}
</script>
</body>
</html>