<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib
	prefix="s"
	uri="/struts-tags"%>
<%@page import="java.util.Locale"%>
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
<title>Login</title>
</head>
<body>
	<script type="text/javascript">
		document.getElementById('userContainer').style.display = 'none';
		document.getElementById('workingContainer').style.width = '100%';
		document.getElementById('workingContainer').style.margin = '0 auto 0 5%';
		setTimeout("document.getElementById('pwd').focus();", 100);
	</script>
	<s:actionmessage />
	<s:actionerror />
	<table>
		<tr>
			<td style="height: 120px;"></td>
		</tr>
	</table>
	<s:if test="%{#parameters.error}">
		<label style="font-family: Calibir, Arial; font-size: 14px; color: red;"> Usuario o
			contraseña invalidos! </label>
		<br />
		<label style="font-family: Calibir, Arial; font-size: 12px; color: red;"> ¿Recuperar
			contraseña? <a href="#" onclick="recover();">de clic aqu&iacute;</a>
		</label>
	</s:if>
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
	<fieldset id="login">
		<s:form
			namespace=""
			theme="simple"
			action="j_security_check">
			<table>
				<tr>
					<td>
						<table>
							<tr>
								<td style="width: 100px; text-align: right;"><s:label
										cssClass="etiquetaCaptura"
										value="Usuario: " /></td>
								<td><s:textfield
										id="pwd"
										name="j_username"
										required="true"
										maxlength="100"
										size="25" /></td>
							</tr>
							<tr>
								<td style="width: 100px; text-align: right;"><s:label
										cssClass="etiquetaCaptura"
										value="Contraseña: " /></td>
								<td><s:password
										name="j_password"
										required="true"
										maxlength="50"
										size="25" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="recover();">Recuperar contraseña</a><br /><br />
			<s:submit
				value="Entrar"
				cssClass="botonenviar"
				align="center" />
		</s:form>
	</fieldset>
	<table>
		<tr>
			<td style="height: 150px;"></td>
		</tr>
	</table>
<s:form
	name="frmRecover"
	action="inicio"
	namespace="/"
	theme="simple"
	method="get">
	<s:hidden
		id="idRecover"
		name="recover"
		value="" />
</s:form>
<script type="text/javascript">
	function recover() {
		var cve = prompt('Estimado usuario, para recuperar su contraseña es necesario confirme su correo electrónico:');
		if (!(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
				.test(cve))) {
			alert("Ingrese una dirección de correo electrónico válida por favor.");
			return false;
		}
		document.getElementById('idRecover').value = cve;
		document.frmRecover.submit();
	}
</script>
</body>
</html>
