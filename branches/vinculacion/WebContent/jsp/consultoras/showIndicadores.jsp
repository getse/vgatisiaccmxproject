<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script
	src="${pageContext.request.contextPath}/js/pymes.js"
	type="text/javascript"></script>
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
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
	<div id="busqPyME">
			<s:hidden name="cveScian" id="idCveSci" value="%{cveScian}" />
			<s:hidden name="producto" id="idProd" value="%{producto}" />
			<legend>
				<s:label value="Seguimiento Consultor" />
				<br /> <br />
			</legend>
			<br />
			<!-- Lista busqueda -->
			<s:form 
			id="cedula"
			action="consultoraPyMEsShow" 
			namespace="/consultor/administracion" 
			theme="simple" 
			onsubmit="return validaAsignaCedula()">
				<table width="99%" cellspacing="1" cellpadding="1">
					<thead>
							<tr>
								<td class="encabezado_tabla" align="center"><b>No.</b></td>
								<td class="encabezado_tabla" align="center"><b>Nombre
										Comercial</b></td>
								<td class="encabezado_tabla" align="center"><b>Correo
										electrónico</b></td>
								<td class="encabezado_tabla" align="center"><b>Nombre
										Contacto</b></td>
								<td class="encabezado_tabla" align="center"><b>Apellido
										Paterno Contacto</b></td>
								<td class="encabezado_tabla" align="center"><b>Apellido
										Materno Contacto</b></td>
								<td class="encabezado_tabla" align="center"><b>Seguimiento PyMEs
										</b></td>
								<td class="encabezado_tabla" align="center">
									<b>Calificar PyME</b>
								</td>
								
							</tr>
						</thead>
					<tbody>
						<s:set var="contador" value="0" />
						<s:iterator value="pymesList" status="stat">
								<s:set var="cnt" value="#contador=#contador+1" />
								<tr>
									<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${stat.count}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${nombreComercial}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${correoElectronicoContacto1}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${nombreContacto1}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${appPaterno1}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${appMaterno1}</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center"><a href="${pageContext.request.contextPath}
													/consultor/consultorIndicadorShow.do?seguimiento=${idServicioConsultoria}" onclick="javascript: $(idProcesa)[0].style.display = 'block';">Seguimiento</a></td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										<a href="${pageContext.request.contextPath}/consultor/consultorCalificaShow.do?idUsuario=${idUsuario}" onclick="javascript: $(idProcesa)[0].style.display = 'block';">
											Calificar
										</a>
									</td>
								</tr>
						</s:iterator>
					</tbody>
				</table>
			</s:form>
		</div>
	</fieldset>
</body>
</html>
