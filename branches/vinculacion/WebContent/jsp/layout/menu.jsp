<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- MENU -->
<table>
	<tr>
		<td>
		<div id="menu1" class="menu1" style="display: block;"
			onclick="selectMenu(1);">${op[0]}</div>
		<div id="menu2" class="menu2" style="display: block;"
			onclick="selectMenu(2);">${op[1]}</div>
		<div id="menu3" class="menu3" style="display: block;"
			onclick="selectMenu(3);">${op[2]}</div>
		<div id="menu4" class="menu4" style="display: block;"
			onclick="selectMenu(4);">${op[3]}</div>
		<div id="menu5" class="menu5" style="display: block;"
			onclick="selectMenu(5);">${op[4]}</div>
		</td>
	</tr>
</table>

<s:form name="frmOpcionUno" action="%{fr[0]}" theme="simple">
	<s:hidden name="menu" value="1" />
</s:form>
<s:form name="frmOpcionDos" action="%{fr[1]}" theme="simple">
	<s:hidden name="menu" value="2" />
</s:form>
<s:form name="frmOpcionTres" action="%{fr[2]}" theme="simple">
	<s:hidden name="menu" value="3" />
</s:form>
<s:form name="frmOpcionCuatro" action="%{fr[3]}" them="simple">
	<s:hidden name="menu" value="4" />
</s:form>
<s:form name="frmOpcionCinco" action="%{fr[4]}" theme="simple">
	<s:hidden name="menu" value="5" />
</s:form>

<script type="text/javascript">
	document.getElementById('menu' + '${menu}').style.background = ' #B3B3B3 url(${pageContext.request.contextPath}/img/entradamenu.gif) no-repeat 220px -5px';
	document.getElementById('menu' + '${menu}').style.color = ' #4682B4';

	function selectMenu(menu) {
		switch (menu) {
		case 1:
			document.frmOpcionUno.submit();
			break;
		case 2:
			document.frmOpcionDos.submit();
			break;
		case 3:
			document.frmOpcionTres.submit();
			break;
		case 4:
			document.frmOpcionCuatro.submit();
			break;
		case 5:
			document.frmOpcionCinco.submit();
			break;
		}
	}
</script>