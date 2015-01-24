<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	document.getElementById('userContainer').style.display = 'none';
	document.getElementById('workingContainer').style.width = '100%';
	document.getElementById('workingContainer').style.margin = '0 auto 0 5%';
</script>

<s:url id="uri" action="logout.do" encode="true" namespace="">
</s:url>
<s:a href="%{uri}"></s:a>&nbsp;
