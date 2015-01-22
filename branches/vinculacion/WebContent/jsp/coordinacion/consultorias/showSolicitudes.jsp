<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/css/calendario.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/coordinadorConsultorias.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar-es.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar-setup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
</head>
<body>
<s:if test="mensaje!=null">
	<br />
	<table class="nota">
		<tr>
			<td class="imgNota">
				<s:if test="mensaje.respuesta==0">
					<img src="${pageContext.request.contextPath}/img/palomitaverde.gif" />
				</s:if>
				<s:else>
					<img src="${pageContext.request.contextPath}/img/warning.png" />
				</s:else>
			</td>
			<td class="contenidoNota">
				<s:property value="mensaje.mensaje" />
			</td>
		</tr>
	</table>
</s:if>
<fieldset id="requerimientos" >
	<legend>
		<s:label id="tituloCord" value="Solicitudes de Pago" />
		<br /><br />
		<s:if test="idUsuario == 0">
			<s:label id="ayudaCord" cssClass="camposObligatorios" value="Seleccione una empresa consultora para registrar pagos." />
		</s:if>
		<s:else>
			<s:label id="ayudaCord" cssClass="camposObligatorios" value="Facturas solicitadas." />
		</s:else>
	</legend>
	<br />

	<s:if test="idUsuario == 0">
		<table width="70%" cellspacing="1" cellpadding="1" align="center">
			<thead>
				<tr>
					<td class="encabezado_tabla" align="center" style="width: 5%;"><b>No.</b></td>
					<td class="encabezado_tabla" align="center"><b>Empresa consultora</b></td>
					<td class="encabezado_tabla" align="center" style="width: 25%;"><b>Solicitudes de pagos</b></td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="consultorasList" status="stat">
					<tr>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${empresa}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
							<a href="${pageContext.request.contextPath}/consultorias/coordinacion/coordinadorConsultoriasSolicitudesShow.do?idUsuario=${idUsuario}">Registrar solicitud</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<s:if test="numeroFactura == null">
			<div id="solicitaPago">
				<s:form name="frmFactura" action="coordinadorConsultoriasSolicitudesShow" namespace="/consultorias/coordinacion" theme="simple" onsubmit="return validacionFacturas()">
					<s:hidden id="idHidIdFacturas" name="idFacturas" value="%{idFacturas}" />
					<s:hidden id="idHidMontoTotal" name="montoTotal" value="%{montoTotal}" />
					<table width="99%" cellspacing="1" cellpadding="1">
						<thead>
							<tr>
								<td class="encabezado_tabla" align="center" style="width: 5%;"><b>No.</b></td>
								<td class="encabezado_tabla" align="center"><b>No. de factura</b></td>
								<td class="encabezado_tabla" align="center"><b>Importe de factura</b></td>
								<td class="encabezado_tabla" align="center"><b>Ver detalle de la factura</b></td>
								<td class="encabezado_tabla" align="center"><b>Solicitar pago</b></td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="listFacturas" status="stat">
								<tr>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${idFactura}</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${importeTotal}</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										<a href="${pageContext.request.contextPath}/consultorias/coordinacion/coordinadorConsultoriasSolicitudesShow.do?idUsuario=${idUsuario}&numeroFactura=${idFactura}&importe=${importeTotal}">Ver Detalle</a>
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										<s:checkbox id="idAsigna%{idFactura}&%{importeTotal}" name="checkbox" />
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<br />
					<table class="submit_tabla">
						<tr >
							<td><input class="botonenviar" type="button" value="Registrar Pagos" onkeypress="javascript: showRegPagos();" onclick="javascript: showRegPagos();"/></td>
							<td><input class="botonenviar" type="button" value="Marcar/Desmarcar Todas" onkeypress="javascript: todasFacturas();" onclick="javascript: todasFacturas();"/></td>
							<td><input class="botonenviar" type="submit" value="Liberar pagos" onkeypress="javascript: return liberaPago();" onclick="javascript: return liberaPago();" /></td>
						</tr>
					</table>
				</s:form>
			</div>
			
			<div id="infoPagos" style="display: none;">
				<s:form name="frmInfoFactura" action="coordinadorConsultoriasSolicitudesShow" namespace="/consultorias/coordinacion" theme="simple" onsubmit="return validacionFacturas()">
					<s:hidden id="idHidIdFacturas" name="idFacturas" value="%{idFacturas}" />
					<table width="70%" cellspacing="1" cellpadding="1" align="center">
						<thead>
							<tr>
								<td class="encabezado_tabla" align="center" style="width: 5%;"><b>No.</b></td>
								<td class="encabezado_tabla" align="center"><b>No. de factura</b></td>
								<td class="encabezado_tabla" align="center" style="width: 25%;"><b>Seleccionar factura</b></td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="facturasList" status="stat">
								<tr>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${idFactura}</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										<s:checkbox id="idAsigna%{idFactura}" name="checkbox" />
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<br />
					<table width="60%" style="margin: 0 auto;">
						<tr >
							<td style="width: 33%;"><input class="botonenviar" type="button" value="Cancelar" onclick="javascript: window.history.back();" /></td>
							<td><input class="botonenviar" type="button" value="Marcar/Desmarcar Todas" onkeypress="javascript: todasInfoPagos();" onclick="javascript: todasInfoPagos();"/></td>
							<td style="width: 40%; text-align: right;"><input class="botonenviar" type="button" value="Seleccionar fecha de pago" onkeypress="javascript: return showFecha();" onclick="javascript: return showFecha();" /></td>
						</tr>
					</table>
					
					<div id="contFecha" style="display: none;">
						<br /><br /><br />
						<table>
							<tr>
								<td>
									<s:label cssClass="etiquetaCaptura" value="* Elija la Fecha que será asignada a las Facturas seleccionadas y de clic en el botón 'Registrar pago'." />
								</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td>
									<s:label cssClass="etiquetaCaptura" value="Fecha a registrar:" />
									<s:date name="fechaPago" id="fPago" format="dd/MM/yyyy" />
									<s:textfield class="calendario" id="ingreso" name="fechaPago" value="%{fPago}" size="20" maxlength="10" />
									<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador" style="cursor: hand"/>
									<s:if test="idUsuario != 0">
										<script type="text/javascript">
											calendario();
										</script>
									</s:if>
								</td>
							</tr>
							<tr>
								<td>
									<br />
									<input class="botonenviar" type="button" value="Registrar Pago" onkeypress="saveFechaPago();" onclick="saveFechaPago();" />
								</td>
							</tr>
						</table>
					</div>
					
				</s:form>
			</div>
			
		</s:if>
		<s:else>
			<s:form name="frmDetalleFactura" action="coordinadorConsultoriasSolicitudesShow" namespace="/consultorias/coordinacion" theme="simple" onsubmit="return validacionDetalleFacturas()">
				<s:hidden id="idHidIdPagos" name="idPagosFacturas" value="%{idPagosFacturas}" />
				<s:label cssClass="etiquetaCaptura" value="Detalle de la Factura #" />
				<s:textarea id="numFactura" rows="1" cols="40" disabled="true" cssClass="resultado" style="margin-bottom: -6px; resize: none;" value="%{numeroFactura}"/>
				<br /><br />
				<s:label cssClass="etiquetaCaptura" value="Importe total de la Factura:" />
				<s:textarea id="numFactura" rows="1" cols="40" disabled="true" cssClass="resultado" style="margin-bottom: -6px; resize: none;" value="$ %{importe}"/>
				<br />
				<br />
	
				<table width="99%" cellspacing="1" cellpadding="1">
					<thead>
						<tr>
							<td class="encabezado_tabla" align="center" style="width: 5%;"><b>No.</b></td>
							<td class="encabezado_tabla" align="center"><b>Nombre comercial</b></td>
							<td class="encabezado_tabla" align="center"><b>Horas de consultoria</b></td>
							<td class="encabezado_tabla" align="center"><b>Empresa Consultora</b></td>
							<td class="encabezado_tabla" align="center"><b>Estatus de consultoría</b></td>
							<td class="encabezado_tabla" align="center"><b>Pago solicitado</b></td>
							<td class="encabezado_tabla" align="center"><b>Monto</b></td>
							<td class="encabezado_tabla" align="center"><b>Eliminar PyME</b></td>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listPyMEs" status="stat">
							<tr>
								<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
								<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${nombreComercial}</td>
								<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${horas}</td>
								<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${tractora}</td>
								<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${estatusConsultoria}</td>
								<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${tipo}</td>
								<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${monto}</td>
								<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center"><s:checkbox id="idAsigna%{idPago}" name="checkbox" /></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<table class="submit_tabla">
					<tr >
						<td><input class="botonenviar" type="button" value="Regresar" onclick="javascript: window.history.back();" /></td>
						<td><input class="botonenviar" type="button" value="Marcar/Desmarcar Todas" onkeypress="javascript:todasDetalleFacturas();" onclick="javascript:todasDetalleFacturas();"/></td>
						<td><input class="botonenviar" type="submit" value="Rechazar pago" onkeypress="javascript: return delFactura();" onclick="javascript: return delFactura();" /></td>
					</tr>
				</table>
			</s:form>
		</s:else>
	</s:else>
</fieldset>
</body>
</html>