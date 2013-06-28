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
			<td class="imgNota">
				<s:if test="mensaje.respuesta==0">
					<img src="${pageContext.request.contextPath}/img/palomitaverde.gif" />
				</s:if>
				<s:else>
					<img src="${pageContext.request.contextPath}/img/warning.png" />
				</s:else>
			</td>
			<td class="contenidoNota"><s:property value="mensaje.mensaje" /></td>
		</tr>
	</table>
</s:if>

<fieldset id="requerimientos">
	<legend>
		<s:label value="Administración de Diplomados" />
		<br /><br />
		<s:label cssClass="camposObligatorios" value="Seleccione la opción 'Registrar Diplomado' para agregar un Diplomado, seleccione un diplomado para ver los detalles y/o editar su información." />
	</legend>
	<br />
	<s:form action="diplomadoAdd" namespace="/ccmx/administracion/diplomados" theme="simple">			
		<div id="diplomado">
			<s:iterator value="(generaciones).{ #this }" status="stat">
				<table width="99%" cellspacing="1" cellpadding="1">
					<tr><td class="encabezadoTablaResumen" align="center"><b>Generación ${stat.count}</b></td></tr>
				</table>
				<s:iterator value="listDiplomados" status="cont">
					<div style="float: left; width: 49.2%; text-align: center;" class="cuerpo1TablaResumen">
						<a href="${pageContext.request.contextPath}/ccmx/administracion/diplomadoAdd.do?generacion=${stat.count}&tituloDiplomado=${tema}">${tema}</a>
					</div>
				</s:iterator>
			</s:iterator>
		</div>
		<s:hidden name="generacion" id="gen" value="%{generacion}" />
		<s:hidden name="tituloDiplomado" id="tituloDip" value="%{tituloDiplomado}" />
		<br />
		<table width="99%">
			<tr>
				<td>
					<br />
					<s:submit cssClass="botonenviar" value="Registrar Diplomado" />
				</td>
			</tr>
		</table>
	</s:form>
</fieldset>
</body>
</html>