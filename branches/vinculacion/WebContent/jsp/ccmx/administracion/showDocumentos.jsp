<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ccmx.js"></script>
</head>
<body>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-325px auto 0 250px';
</script>
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
		<legend>
			<s:label value="Administración de Documentos" />
			<br /> <br />
			<s:label cssClass="camposObligatorios"
				value="Seleccione el tipo de perfil y cargue el documento correspondiente." />
		</legend>
		<br />
		<s:form action="documentosShow" namespace="/ccmx/administracion" enctype="multipart/form-data" method="post" theme="simple">
			<table>
				<tr>
					<td>
						<select id="idRolDocumento" name="rolManual" style="width: 200px;" >
							<option selected="selected" value="-1">--Seleccione una opción--</option>
								<option value="1">Administrador CCMX</option>
								<option value="2">PyME</option>
								<option value="3">Administrador Tractora</option>
								<option value="4">Comprador</option>
								<option value="5">Administrador Consultores</option>
								<option value="6">Consultor</option>
								<option value="7">Coordinador Consultoras</option>
								<option value="8">Coordinador Diplomados</option>
						</select>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>
						<s:file id="idDocumento" name="archivoManual" ></s:file>
					</td>
				</tr>
			</table>
			<table class="submit_tabla">
				<tr>
					<td style="width: 250px;"></td>
					<td>
						<input class="botonenviar" value="Cancelar" type="button" onclick="cancelaDocumento();" />
					</td>
					<td>
						<input class="botonenviar" value="Guardar" type="submit" onclick="javascript:return validaDocumento();" />
					</td>
					<td style="width: 250px;"></td>
				</tr>
			</table>
		</s:form>
		<s:form name="frmCancelaDocumento" action="documentosShow" namespace="/ccmx/administracion" theme="simple" method="post"></s:form>
	</fieldset>
</body>
</html>