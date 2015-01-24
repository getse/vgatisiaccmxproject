<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CCMX</title>
<link href="${pageContext.request.contextPath}/css/layout.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/demo.css"
	rel="stylesheet" type="text/css" />
</head>
<body class="bodyModal">
<div id="layoutContainerModal">
<div id="workingContainerModal"><tiles:insertAttribute
	name="working.region" /></div>
<div id="spacer"><br />
</div>
</div>
<script>
	$(idProcesa)[0].style.display = 'none'
</script>
</body>
</html>