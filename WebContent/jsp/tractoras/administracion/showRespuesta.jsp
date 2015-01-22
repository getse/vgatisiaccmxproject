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
<fieldset id="requerimientos">
	<legend>
		<s:label value="Resumen de Respuesta al Requerimiento" />
		<br />
		<br />
	</legend>
	<div id="idDivRespuesta" ${idRespuesta==0? ' style="display: none;" ' :' style="display: block;"' }>
		<table class="expediente_tabla">
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Resumen de la respuesta al Requerimiento</td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;ID Requerimiento:</td>
				<td class="cuerpo1TextoResumen">
					<s:label cssClass="etiquetaResumen">${respuesta.idRequerimiento}</s:label>
				</td>
			</tr>
			<tr>
				<td class="cuerpo2TablaResumen" align="left">&nbsp;ID Respuesta:</td>
				<td class="cuerpo1TextoResumen">
					<s:label cssClass="etiquetaResumen">${respuesta.idRespuesta}</s:label>
				</td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Información:</td>
				<td class="cuerpo1TextoResumen">
					<s:label cssClass="etiquetaResumen">${respuesta.informacion}</s:label>
				</td>
			</tr>
			<s:if test="respuesta.idArchivo1!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 1:</td>
					<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${respuesta.idArchivo1}&nameArchivo=${respuesta.archivo1FileName}&mimeArchivo=${respuesta.archivo1ContentType}">${respuesta.archivo1FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="respuesta.idArchivo2!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 2:</td>
					<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${respuesta.idArchivo2}&nameArchivo=${respuesta.archivo2FileName}&mimeArchivo=${respuesta.archivo2ContentType}">${respuesta.archivo2FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="respuesta.idArchivo3!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 3:</td>
					<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${respuesta.idArchivo3}&nameArchivo=${respuesta.archivo3FileName}&mimeArchivo=${respuesta.archivo3ContentType}">${respuesta.archivo3FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="respuesta.idArchivo4!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 4:</td>
					<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${respuesta.idArchivo4}&nameArchivo=${respuesta.archivo4FileName}&mimeArchivo=${respuesta.archivo4ContentType}">${respuesta.archivo4FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="respuesta.idArchivo5!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 5:</td>
					<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${respuesta.idArchivo5}&nameArchivo=${respuesta.archivo5FileName}&mimeArchivo=${respuesta.archivo5ContentType}">${respuesta.archivo5FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="respuesta.idArchivo6!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 6:</td>
					<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${respuesta.idArchivo6}&nameArchivo=${respuesta.archivo6FileName}&mimeArchivo=${respuesta.archivo6ContentType}">${respuesta.archivo6FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="respuesta.idArchivo7!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 7:</td>
					<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${respuesta.idArchivo7}&nameArchivo=${respuesta.archivo7FileName}&mimeArchivo=${respuesta.archivo7ContentType}">${respuesta.archivo7FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="respuesta.idArchivo8!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 8:</td>
					<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${respuesta.idArchivo8}&nameArchivo=${respuesta.archivo8FileName}&mimeArchivo=${respuesta.archivo8ContentType}">${respuesta.archivo8FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="respuesta.idArchivo9!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 9:</td>
					<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${respuesta.idArchivo9}&nameArchivo=${respuesta.archivo9FileName}&mimeArchivo=${respuesta.archivo9ContentType}">${respuesta.archivo9FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="respuesta.idArchivo10!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 10:</td>
					<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${respuesta.idArchivo10}&nameArchivo=${respuesta.archivo10FileName}&mimeArchivo=${respuesta.archivo10ContentType}">${respuesta.archivo10FileName}</a>
					</td>
				</tr>
			</s:if>
			<tr>
				<td><br /></td>
			</tr>
		</table>
		<table class="submit_tabla">
			<tr>
				<td style="width: 450px;"></td>
				<td>
					<input class="botonenviar" value="Regresar" type="button" onclick="javascript: window.history.back();" />
				</td>
				<td style="width: 450px;"></td>
			</tr>
		</table>
	</div>
</fieldset>
</body>
</html>
