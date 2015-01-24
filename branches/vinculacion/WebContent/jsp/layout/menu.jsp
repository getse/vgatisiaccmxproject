<%@ taglib
	prefix="s"
	uri="/struts-tags"%>
<!-- MENU -->
<table id="leftmenu" class="leftmenu">
	<tr>
		<td>
			<div
				id="menu1"
				class="menu1"
				style="display: block;"
				onclick="selectMenu(1);">${op[0]}</div>
			<div
				id="menu2"
				class="menu2"
				style="display: block;"
				onclick="selectMenu(2);">${op[1]}</div>
			<div
				id="menu3"
				class="menu3"
				style="display: block;"
				onclick="selectMenu(3);">${op[2]}</div>
			<div
				id="menu4"
				class="menu4"
				${op[3] == null ? 'style="display: none;"': 'style="display: block;"'}
				onclick="selectMenu(4);">${op[3]}</div>
				
			<div
				id="menu5"
				class="menu5"
				${op[4] == null ? 'style="display: none;"': 'style="display: block;"'}
				onclick="selectMenu(5);">${op[4]}</div>
			<div
				id="menu6"
				class="menu6"
				${op[5] == null ? 'style="display: none;"': 'style="display: block;"'}
				onclick="selectMenu(6);">${op[5]}</div>
			<div
				id="menu7"
				class="menu7"
				${op[6] == null ? 'style="display: none;"': 'style="display: block;"'}
				onclick="selectMenu(7);">${op[6]}</div>
		</td>
	</tr>
</table>
<s:form
	name="frmOpcionUno"
	action="%{fr[0]}"
	theme="simple">
	<s:hidden
		name="menu"
		value="1" />
</s:form>
<s:form
	name="frmOpcionDos"
	action="%{fr[1]}"
	theme="simple">
	<s:hidden
		name="menu"
		value="2" />
</s:form>
<s:form
	name="frmOpcionTres"
	action="%{fr[2]}"
	theme="simple">
	<s:hidden
		name="menu"
		value="3" />
</s:form>
<s:form
	name="frmOpcionCuatro"
	action="%{fr[3]}"
	them="simple">
	<s:hidden
		name="menu"
		value="4" />
</s:form>
<s:form
	name="frmOpcionCinco"
	action="%{fr[4]}"
	theme="simple">
	<s:hidden
		name="menu"
		value="5" />
</s:form>
<s:form
	name="frmOpcionSeis"
	action="%{fr[5]}"
	theme="simple">
	<s:hidden
		name="menu"
		value="6" />
</s:form>
<s:form
	name="frmOpcionSiete"
	action="%{fr[6]}"
	theme="simple">
	<s:hidden
		name="menu"
		value="7" />
</s:form>
<script type="text/javascript">
	document.getElementById('menu' + '${menu}').style.background = ' #6486AA url(${pageContext.request.contextPath}/img/entradamenu.gif) no-repeat 220px -5px';
	document.getElementById('menu' + '${menu}').style.color = ' #F5F5DC';

	function selectMenu(menu) {
		$(idProcesa)[0].style.display = 'block';
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
		case 6:
			document.frmOpcionSeis.submit();
			break;
		case 7:
			document.frmOpcionSiete.submit();
			break;
		}
	}
</script>
