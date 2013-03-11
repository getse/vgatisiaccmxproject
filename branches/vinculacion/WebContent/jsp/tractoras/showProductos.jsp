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
<s:form
	action="showReq"
	name="frmProds"
	namespace="/tractora/requerimientos"
	theme="simple">
	<table>
		<tr>
			<td><br />
			<table
				width="140%"
				cellspacing="1"
				cellpadding="1">
				<s:label
					cssClass="camposObligatorios"
					value="Seleccione la opción que corresponda a su tipo de producto." />
				<br />
				<thead>
					<tr>
						<td
							class="encabezado_tabla"
							align="center"><b>Rama</b></td>
						<td
							class="encabezado_tabla"
							align="center"><b>Sector</b></td>
						<td
							class="encabezado_tabla"
							align="center"><b>Clase</b></td>
					</tr>
				</thead>
				<tbody>
					<s:iterator
						value="listProductos"
						status="stat">
						<tr>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center"><a
								href="javascript:regresa('${requerimientos.idRequerimiento}','${idClase}','${requerimientos.busqueda}','${clase}');">
							${rama} </a></td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}">
							<a
								href="javascript:regresa('${requerimientos.idRequerimiento}','${idClase}','${requerimientos.busqueda}','${clase}');">
							${subsectorEconomico} </a></td>
							<td
								class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center"><a
								href="javascript:regresa('${requerimientos.idRequerimiento}','${idClase}','${requerimientos.busqueda}','${clase}');">
							${clase} </a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</td>
		</tr>
	</table>
</s:form>
<s:url
	id="uri"
	action="showReq"
	encode="true"
	namespace="/tractora/requerimientos"
	includeContext="" />
<s:form
	id="idFrmRegresar"
	name="frmRegresar"
	action="%{uri}"
	method="get"
	target="_top">
	<s:hidden
		id="idReq"
		name="requerimientos.idRequerimiento" />
	<s:hidden
		id="idCla"
		name="requerimientos.cveScian" />
	<s:hidden
		id="idBus"
		name="requerimientos.busqueda" />
	<s:hidden
		id="idRes"
		name="resultados"
		value="true" />
	<s:hidden
		name="requerimientos.idTractora"
		id="idIdTra"
		value="%{requerimientos.idTractora}" />
	<s:hidden
		name="requerimientos.producto"
		id="idPro"
		value="%{requerimientos.producto}" />
	<s:hidden
		name="requerimientos.tipoProducto"
		id="idTipPro"
		value="%{requerimientos.tipoProducto}" />
	<s:hidden
		name="requerimientos.descripcion"
		id="idDes"
		value="%{requerimientos.descripcion}" />
	<s:hidden
		name="requerimientos.fechaSuministroTODO"
		id="idFecSum"
		value="%{requerimientos.fechaSuministro}" />
	<s:hidden
		name="requerimientos.bIndefinido"
		id="idBInd"
		value="%{requerimientos.bIndefinido}" />
	<s:hidden
		name="requerimientos.bVariasFechas"
		id="idBVar"
		value="%{requerimientos.bVariasFechas}" />
	<s:hidden
		name="requerimientos.bContinuoSuministro"
		id="idBConSum"
		value="%{requerimientos.bContinuoSuministro}" />
	<s:hidden
		name="requerimientos.lugarSuministro"
		id="idLugSum"
		value="%{requerimientos.lugarSuministro}" />
	<s:hidden
		name="requerimientos.bContado"
		id="idBCon"
		value="%{requerimientos.bContado}" />
	<s:hidden
		name="requerimientos.bCredito"
		id="idBCre"
		value="%{requerimientos.bCredito}" />
	<s:hidden
		name="requerimientos.bQuince"
		id="idBQui"
		value="%{requerimientos.bQuince}" />
	<s:hidden
		name="requerimientos.bTreinta"
		id="idBTre"
		value="%{requerimientos.bTreinta}" />
	<s:hidden
		name="requerimientos.bSesenta"
		id="idBSes"
		value="%{requerimientos.bSesenta}" />
	<s:hidden
		name="requerimientos.bNoventa"
		id="idBNov"
		value="%{requerimientos.bNoventa}" />
	<s:hidden
		name="requerimientos.bOtro"
		id="idBOtr"
		value="%{requerimientos.bOtro}" />
	<s:hidden
		name="requerimientos.otrasCondiciones"
		id="idOtrCon"
		value="%{requerimientos.otrasCondiciones}" />
	<s:hidden
		name="requerimientos.requisitosAdicionales"
		id="idReqAdic"
		value="%{requerimientos.requisitosAdicionales}" />
	<s:hidden
		name="requerimientos.fechaExpiraTODO"
		id="idFecExp"
		value="%{requerimientos.fechaExpira}" />
	<s:hidden
		name="requerimientos.bContinuoExpira"
		id="idBConExp"
		value="%{requerimientos.bContinuoExpira}" />
	<s:hidden
		name="requerimientos.archivo"
		id="idArc"
		value="%{requerimientos.archivo}" />
</s:form>
<script type="text/javascript">
	function regresa(idReq, idCla, idBus, clase) {
		document.getElementById("idReq").value = idReq;
		document.getElementById("idTipPro").value = clase;
		document.getElementById("idCla").value = idCla;
		document.getElementById("idBus").value = idBus;
		document.frmRegresar.submit();
	}
</script>
</body>
</html>