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
	value="Seleccione la opción que corresponda a su tipo de producto." />

<s:form action="addReq" name="frmProds"
	namespace="/tractora/requerimientos" theme="simple">
	<table>
		<tr>
			<td><br />
			<table width="630px" cellspacing="1" cellpadding="1">
				<thead>
					<tr>
						<td class="encabezado_tabla" align="center"><b>Rama</b></td>
						<td class="encabezado_tabla" align="center"><b>Sector</b></td>
						<td class="encabezado_tabla" align="center"><b>Clase</b></td>
						<!--						<td class="encabezado_tabla" align="center" style="width: 30px;"><b>Elegir</b></td>-->
					</tr>
				</thead>
				<tbody>
					<s:iterator value="listProductos" status="stat">
						<tr>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center"><a
								href="javascript:regresa('${requerimientos.idRequerimiento}','${clase}');">
							${rama} </a></td>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}">
							<a href="javascript:regresa('${requerimientos.idRequerimiento}','${clase}');">
							${subsectorEconomico} </a></td>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center"><a
								href="javascript:regresa('${requerimientos.idRequerimiento}','${clase}');">
							${clase} </a></td>
							<!--							<td-->
							<!--								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}">-->
							<!--							<input class="checkbox" id="idCheckProducto${idClase}" name="checkProducto" type="checkbox" value="${idClase}"  onchange="clearc(this);""/>-->
							<!--							</td>-->
						</tr>
					</s:iterator>
				</tbody>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<s:url id="uri" action="addReq" encode="true"
	namespace="/tractora/requerimientos" includeContext="" />
<s:form id="idFrmRegresar" name="frmRegresar" action="%{uri}"
	method="get" target="_top">
	<s:hidden id="idReq" name="requerimientos.idRequerimiento" />
	<s:hidden id="idClas" name="requerimientos.tipoProducto" />
</s:form>
<script type="text/javascript">
	function regresa(idReq, idClas) {
		document.getElementById("idReq").value = idReq;
		document.getElementById("idClas").value = idClas;
		document.frmRegresar.submit();
	}
</script>
</body>
</html>