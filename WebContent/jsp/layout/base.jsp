<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib
	uri="http://tiles.apache.org/tags-tiles"
	prefix="tiles"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta
	http-equiv="Content-Type"
	content="text/html; charset=ISO-8859-1" />
<title>CCMX</title>
<link
	rel="stylesheet"
	href="${pageContext.request.contextPath}/css/layout.css"
	type="text/css" />
<link
	rel="stylesheet"
	href="${pageContext.request.contextPath}/css/demo.css"
	type="text/css" />
<!-- Scripts  -->
<script>
	document.getElementById('idProcesa').style.display = 'block';
</script>
</head>
<body>
<div id="idProcesa" class="procesa">
</div>
<div
	id="layoutContainer"
	align="center" />
<div
	id="headerContainer"
	align="center"><tiles:insertAttribute name=".header" /></div>
<div
	id="logoutContainer"
	align="center"><tiles:insertAttribute name=".logout" /></div>
<div
	id="userContainer"
	align="center"
	style="display: block;"><tiles:insertAttribute name=".menu" /></div>
<div
	id="workingContainer"
	align="center"><tiles:insertAttribute name="working.region" /></div>
<div id="spacer">
<br />
<div
	id="footerContainer"
	align="center"><tiles:insertAttribute name=".footer" /></div>
<br />
</div>
</body>
</html>