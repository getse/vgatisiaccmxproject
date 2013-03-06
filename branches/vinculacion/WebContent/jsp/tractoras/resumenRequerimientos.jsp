<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Resumen</title>
</head>
<body>
<s:property value="mensaje" />
<hr />
<s:property value="#session.GTS" />
<hr />
<s:property value="#session['GTS']" />
<hr />
<s:property value="#parameters.gts" />
<hr />
<s:property value="#attr.GTS" />
<hr />
<s:property value="%{nombre}" />
<s:hidden name="nombre" value="%{nombre}" />
<hr />
</body>
</html>