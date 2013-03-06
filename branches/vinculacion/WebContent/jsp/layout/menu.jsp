<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- MENU -->
<table>
	<tr>
		<td>
		<div id="menu1" class="menu1" style="display: block;"
			onclick="selectMenu(1);">MI INFORMACI&Oacute;N</div>
		<div id="menu2" class="menu2" style="display: block;"
			onclick="selectMenu(2);">COMPRADORES</div>
		<div id="menu3" class="menu3" style="display: block;"
			onclick="selectMenu(3);">REQUERIMIENTOS</div>
		<div id="menu4" class="menu4" style="display: block;"
			onclick="selectMenu(4);">B&Uacute;SQUEDAS</div>
		<div id="menu5" class="menu5" style="display: block;"
			onclick="selectMenu(5);">REPORTES</div>
		</td>
	</tr>
</table>

<!-- Administracion Tractoras -->
<s:form name="frmAdmTraDat"
	action="showDatAdm.do" theme="simple">
	<s:hidden name="menu" value="1" />
</s:form>
<s:form name="frmAdmTraCom"
	action="showComAdm.do" theme="simple">
	<s:hidden name="menu" value="2" />
</s:form>
<s:form name="frmAdmTraReq"
	action="showReqAdm.do"
	theme="simple">
	<s:hidden name="menu" value="3" />
</s:form>
<s:form name="frmAdmTraBus"
	action="showBusAdm.do" them="simple">
	<s:hidden name="menu" value="4" />
</s:form>
<s:form name="frmAdmTraRep"
	action="showRepAdm.do" theme="simple">
	<s:hidden name="menu" value="5" />
</s:form>

<script type="text/javascript">
var x = '<s:property value="menu" />';

	document.getElementById('menu' + '${menu}').style.background=' #B3B3B3 url(${pageContext.request.contextPath}/img/entradamenu.gif) no-repeat 220px -5px';
	document.getElementById('menu' + '${menu}').style.color=' #4682B4';

	function selectMenu(menu) {
		switch (menu) {
		case 1:
			document.frmAdmTraDat.submit();
			break;
		case 2:
			document.frmAdmTraCom.submit();
			break;
		case 3:
			document.frmAdmTraReq.submit();
			break;
		case 4:
			document.frmAdmTraBus.submit();
			break;
		case 5:
			document.frmAdmTraRep.submit();
			break;
		}
	}
</script>