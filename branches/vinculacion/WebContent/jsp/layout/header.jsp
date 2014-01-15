<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- ENCABEZADO -->
<table style="width: 1215px">
	<tr>
		<td align="center" style="cursor: pointer;" onclick="top.location='/vinculacion/inicio.do';" width="372px">
			<img  class="headerimgleft" src="${pageContext.request.contextPath}/img/LogoCCMxazul2.png" alt="ccmx" height="120px" width="362px">
		</td>
		<td align="center" onclick="top.location='/vinculacion/inicio.do';" style="cursor: pointer;" width="553px">
			<label class="headerlabeltitle" onclick="top.location='/vinculacion/inicio.do';">SISTEMA DE VINCULACI&Oacute;N</label>
		</td>
		<td align="center" style="cursor: pointer;" onclick="top.location='/vinculacion/inicio.do';" width="290px">
			<img class="headerimgright" src="${pageContext.request.contextPath}/img/LogoCCMxazul1.png" alt="ccm" height="114px" width="280px">
		</td>
	</tr>
</table>
<table style="width: 1215px; margin-top: -15px;">
	<tr>
		<s:if test="%{#session.Usuario.rol=='AdministradorCCMX'}">
			<td width="95%"><label class="headerlabelusuario">CCMX Administrador:&nbsp;<s:property value="#session.Usuario.id" />&nbsp;</label></td>
			<td><label class="headerlabelmanual" onclick="manual(1, 'Administrador');">Manual de Usuario</label></td>
		</s:if>
		<s:if test="%{#session.Usuario.rol=='Tractora'}">
			<td width="95%"><label class="headerlabelusuario">Tractora Administrador:&nbsp;<s:property value="#session.Usuario.id" />&nbsp;</label></td>
			<td><label class="headerlabelmanual" onclick="manual(3, 'Administrador-Tractora');">Manual de Usuario</label></td>
		</s:if>
		<s:if test="%{#session.Usuario.rol=='Comprador'}">
			<td width="95%"><label class="headerlabelusuario">Comprador:&nbsp;<s:property value="#session.Usuario.id" />&nbsp;</label></td>
			<td><label class="headerlabelmanual" onclick="manual(4, 'Comprador');">Manual de Usuario</label></td>
		</s:if>
		<s:if test="%{#session.Usuario.rol=='CoordinadorConsultoras'}">
			<td width="95%"><label class="headerlabelusuario">Coordinador Consultoras:&nbsp;<s:property value="#session.Usuario.id" />&nbsp;</label></td>
			<td><label class="headerlabelmanual" onclick="manual(7, 'Coordinador-Consultoras');">Manual de Usuario</label></td>
		</s:if>
		<s:if test="%{#session.Usuario.rol=='CoordinadorDiplomados'}">
			<td width="95%"><label class="headerlabelusuario">Coordinador Diplomados:&nbsp;<s:property value="#session.Usuario.id" />&nbsp;</label></td>
			<td><label class="headerlabelmanual" onclick="manual(8, 'Coordinador-Diplomados');">Manual de Usuario</label></td>
		</s:if>
		<s:if test="%{#session.Usuario.rol=='PyME'}">
			<td width="95%"><label class="headerlabelusuario">PyME:&nbsp;<s:property value="#session.Usuario.id" />&nbsp;</label></td>
			<td><label class="headerlabelmanual" onclick="manual(2, 'PyME');">Manual de Usuario</label></td>
		</s:if>
		<s:if test="%{#session.Usuario.rol=='AdministradorConsultores'}">
			<td width="95%"><label class="headerlabelusuario">Consultora Administrador:&nbsp;<s:property value="#session.Usuario.id" />&nbsp;</label></td>
			<td><label class="headerlabelmanual" onclick="manual(5, 'Administrador-Consultores');">Manual de Usuario</label></td>
		</s:if>
		<s:if test="%{#session.Usuario.rol=='Consultor'}">
			<td width="95%"><label class="headerlabelusuario">Consultor:&nbsp;<s:property value="#session.Usuario.id" />&nbsp;</label></td>
			<td><label class="headerlabelmanual" onclick="manual(6, 'Consultor');">Manual de Usuario</label></td>
		</s:if>
		<s:if test="%{#session.Usuario.rol!=''}">
			<td><label class="headerlabelsalir" onclick="javascript:document.frmSalir.submit();">Salir</label></td>
		</s:if>
	</tr>
</table>
<s:form name="frmSalir" action="logout.do" theme="simple">
</s:form>

<s:form name="frmManual" action="showMan.do" theme="simple">
	<s:hidden id="idHiddArchivo" name="idArchivo" value="" />
	<s:hidden id="idHiddName" name="nameArchivo" value="" />
	<s:hidden name="mimeArchivo" value="application/pdf" />
</s:form>

<script type="text/javascript">
setTimeout("top.location='${pageContext.request.contextPath}/logout.do'", 7195000);
function manual(id, nombre) {
	document.getElementById('idHiddArchivo').value = id;
	document.getElementById('idHiddName'). value = 'Manual-De-Usuario-' + nombre + '-SIA-CCMX.pdf';
	document.frmManual.submit();
}
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