<%@ taglib prefix="s" uri="/struts-tags"%>

<h2>
	<s:text name="http.error.title" />&nbsp;
	<s:property	value="code" />
</h2>

<br>
<b><s:text name="http.error.description" />:</b>
<s:text name="http.error.code.%{code}">
	<s:text name="http.error.code.default" />
</s:text>
<br>
<br>
<b><s:text name="http.error.solution" />:</b>
<s:text name="http.error.solution.%{code}">
	<s:text name="http.error.solution.default" />
</s:text>
<br>
<br>

<s:url id="uri" value="/" encode="true" />
<s:a href="%{uri}"><s:text name="http.error.back" /></s:a>

