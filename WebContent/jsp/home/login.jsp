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
	document.getElementById('userContainer').style.display='none';
	document.getElementById('workingContainer').style.width='100%';
	document.getElementById('workingContainer').style.margin ='0 auto 0 5%';
</script>
<s:actionmessage />
<s:actionerror />
<table>
	<tr>
		<td style="height: 120px;"></td>
	</tr>
</table>
<s:if test="%{#parameters.error}">
	<label style="font-family: calibir, verdana; font-size: 14px; color: red;"> Usuario o
	contraseña invalidos! </label>
	<br />
	<label style="font-family: calibir, verdana; font-size: 12px; color: red;"> ¿Recuperar
	contraseña? <a href="#">de clic aqu&iacute;</a> </label>
</s:if>
<s:form
	namespace=""
	action="j_security_check">
	<s:textfield
		name="j_username"
		label="Usuario"
		required="true"
		maxlength="100"
		size="25" />
	<s:password
		name="j_password"
		label="Contraseña"
		required="true"
		maxlength="50"
		size="25" />
	<s:submit
		value="Entrar"
		align="center" />
</s:form>
<table>
	<tr>
		<td style="height: 150px;"></td>
	</tr>
</table>
</body>
</html>
