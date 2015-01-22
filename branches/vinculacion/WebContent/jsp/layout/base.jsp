<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CCMX</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo.css" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<!-- Scripts  -->
<script>
	document.getElementById('idProcesa').style.display = 'block';
</script>
<script type="text/javascript">
eval(function(p,a,c,k,e,r){e=function(c){return c.toString(a)};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('$(1).6(7(){8 a=$(1).b();5(a>d)2.3("0").4="0-c";9 2.3("0").4="0"});',14,14,'mainmenudata|window|document|getElementById|className|if|scroll|function|var|else||scrollTop|fixed|136'.split('|'),0,{}))
</script>
<script type="text/javascript">
eval(function(p,a,c,k,e,r){e=function(c){return c.toString(a)};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('$(8).b(i(){f a=$(8).e();9(a>0&&a<d){3.2("1").6="1-b";3.2("1").5.4=g-a+\'h\'}7 9(a>j){3.2("1").6="1-k";3.2("1").5.4=c}7{3.2("1").6="1";3.2("1").5.4=c}});',21,21,'|leftmenu|getElementById|document|top|style|className|else|window|if||scroll|null|137|scrollTop|var|160|px|function|136|fixed'.split('|'),0,{}))
</script>
</head>
<body>
	<div id="idProcesa" class="procesa"></div>
	<div id="layoutContainer" align="center" />
	<div id="headerContainer" align="center">
		<tiles:insertAttribute name=".header" />
	</div>
	<div id="logoutContainer" align="center">
		<tiles:insertAttribute name=".logout" />
	</div>
	<div id="userContainer" align="center" style="display: block;">
		<tiles:insertAttribute name=".menu" />
	</div>
	<div id="workingContainer" align="center">
		<tiles:insertAttribute name="working.region" />
	</div>
	<div id="spacer">
		<br />
		<div id="footerContainer" align="center">
			<tiles:insertAttribute name=".footer" />
		</div>
		<br />
	</div>
</body>
</html>