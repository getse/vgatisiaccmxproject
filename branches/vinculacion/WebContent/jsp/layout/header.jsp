<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- ENCABEZADO -->
<table class="mainheader">
	<tr>
		<td align="center" style="cursor: pointer;" onclick="top.location='/vinculacion/inicio.do';" width="372px"><img class="headerimgleft" src="${pageContext.request.contextPath}/img/LogoCCMxazul2.png" alt="ccmx" height="120px" width="362px"></td>
		<td align="center" onclick="top.location='/vinculacion/inicio.do';" style="cursor: pointer;" width="553px"><label class="headerlabeltitle" onclick="top.location='/vinculacion/inicio.do';">SISTEMA DE VINCULACI&Oacute;N</label></td>
		<td align="center" style="cursor: pointer;" onclick="top.location='/vinculacion/inicio.do';" width="290px"><img class="headerimgright" src="${pageContext.request.contextPath}/img/LogoCCMxazul1.png" alt="ccm" height="114px" width="280px"></td>
	</tr>
</table>
<table id="mainmenudata" class="mainmenudata">
	<thead></thead>
	<tbody>
		<tr>
			<s:if test="%{#session.Usuario.rol=='AdministradorCCMX'}">
				<td style="width: 85%;"><label class="headerlabelusuario">${session.super ? '<font color="orange"><b>SuperUsuario - </b></font>' : ''}Administrador CCMX:&nbsp;${session.Usuario.id}&nbsp;</label></td>
				<td><label class="headerlabelmanual" onclick="manual(1, 'Administrador');">Manual de Usuario</label></td>
			</s:if>
			<s:if test="%{#session.Usuario.rol=='Tractora'}">
				<td style="width: 85%;"><label class="headerlabelusuario">${session.super ? '<font color="orange"><b>SuperUsuario - </b></font>' : ''}Administrador Grandes Empresas:&nbsp;${session.Usuario.id}&nbsp;</label></td>
				<td><label class="headerlabelmanual" onclick="manual(3, 'Administrador-Tractora');">Manual de Usuario</label></td>
			</s:if>
			<s:if test="%{#session.Usuario.rol=='Comprador'}">
				<td style="width: 85%;"><label class="headerlabelusuario">${session.super ? '<font color="orange"><b>SuperUsuario - </b></font>' : ''}Comprador:&nbsp;${session.Usuario.id}&nbsp;</label></td>
				<td><label class="headerlabelmanual" onclick="manual(4, 'Comprador');">Manual de Usuario</label></td>
			</s:if>
			<s:if test="%{#session.Usuario.rol=='CoordinadorConsultoras'}">
				<td style="width: 85%;"><label class="headerlabelusuario">${session.super ? '<font color="orange"><b>SuperUsuario - </b></font>' : ''}Coordinador Consultoras:&nbsp;${session.Usuario.id}&nbsp;</label></td>
				<td><label class="headerlabelmanual" onclick="manual(7, 'Coordinador-Consultoras');">Manual de Usuario</label></td>
			</s:if>
			<s:if test="%{#session.Usuario.rol=='CoordinadorDiplomados'}">
				<td style="width: 85%;"><label class="headerlabelusuario">${session.super ? '<font color="orange"><b>SuperUsuario - </b></font>' : ''}Coordinador Diplomados:&nbsp;${session.Usuario.id}&nbsp;</label></td>
				<td><label class="headerlabelmanual" onclick="manual(8, 'Coordinador-Diplomados');">Manual de Usuario</label></td>
			</s:if>
			<s:if test="%{#session.Usuario.rol=='PyME'}">
				<td style="width: 85%;"><label class="headerlabelusuario">${session.super ? '<font color="orange"><b>SuperUsuario - </b></font>' : ''}PyME:&nbsp;${session.Usuario.id}&nbsp;</label></td>
				<td><label class="headerlabelmanual" onclick="manual(2, 'PyME');">Manual de Usuario</label></td>
			</s:if>
			<s:if test="%{#session.Usuario.rol=='AdministradorConsultores'}">
				<td style="width: 85%;"><label class="headerlabelusuario">${session.super ? '<font color="orange"><b>SuperUsuario - </b></font>' : ''}Administrador Consultor:&nbsp;${session.Usuario.id}&nbsp;</label></td>
				<td><label class="headerlabelmanual" onclick="manual(5, 'Administrador-Consultores');">Manual de Usuario</label></td>
			</s:if>
			<s:if test="%{#session.Usuario.rol=='Consultor'}">
				<td style="width: 85%;"><label class="headerlabelusuario">${session.super ? '<font color="orange"><b>SuperUsuario - </b></font>' : ''}Consultor:&nbsp;${session.Usuario.id}&nbsp;</label></td>
				<td><label class="headerlabelmanual" onclick="manual(6, 'Consultor');">Manual de Usuario</label></td>
			</s:if>
			<s:if test="%{#session.Usuario.rol!=''}">
				<td><label ${session.super ? ' class="headerlabelfinalizar"' : ' class="headerlabelsalir"'} onclick="javascript:salir(${session.super ? '1' : '0'});">${session.super ? 'Regresar a funciones del Administrador' : 'Salir'}</label></td>
			</s:if>
		</tr>
	</tbody>
</table>
<s:form name="frmSalir" action="logout.do" theme="simple">
</s:form>
<s:form name="frmManual" action="showMan.do" theme="simple">
	<s:hidden id="idHiddArchivo" name="idArchivo" value="" />
	<s:hidden id="idHiddName" name="nameArchivo" value="" />
	<s:hidden name="mimeArchivo" value="application/pdf" />
</s:form>
<script type="text/javascript">
eval(function(p,a,c,k,e,r){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('e o(a,b){1.4(\'p\').6=a;1.4(\'m\').6=\'l-k-j-\'+b+\'-i-d.v\';1.g.2()}e h(a){c(a==0){$(9)[0].8.7=\'3\';1.5.2()}q c(r(\'s tón u fá w x a y z A B d\')){$(9)[0].8.7=\'3\';1.5.2()}}C("D.E=\'${F.G.H}/I.J\'",K);',47,47,'|document|submit|block|getElementById|frmSalir|value|display|style|idProcesa|||if|CCMX|function|finalizar|frmManual|salir|SIA|Usuario|De|Manual|idHiddName||manual|idHiddArchivo|else|confirm|La|sesi|actual|pdf|para|regresar|su|cuenta|de|Administrador|setTimeout|top|location|pageContext|request|contextPath|logout|do|7195000'.split('|'),0,{}))
</script>
<%
	String _ccmxCookie = "ccmx";
	Cookie _cookies[] = request.getCookies();
	Cookie _cookie = null;
	if (_cookies != null) {
		for (int i = 0; i < _cookies.length; i++) {
			if (_cookies[i].getName().equals(_ccmxCookie)) {
				_cookie = _cookies[i];
				break;
			}
		}
	}
%>
<%
	if (_cookie != null) {
%>
<script type="text/javascript">
	var _topLocation = new String(top.location);
	var _lastIndex = _topLocation.lastIndexOf("/") + 1;
	var _size = _topLocation.length;
	if (_topLocation.substring(_lastIndex, _size) != 'inicio.do') {
	}
</script>
<%
	}
%>