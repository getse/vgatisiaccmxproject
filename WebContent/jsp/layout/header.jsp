<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- ENCABEZADO -->
<table class="headertable" align="center">
	<tr class="headertr">
		<td class="headertd">
			<table>
				<tr>
					<td><img class="headerimg" style="cursor: pointer;" onclick="top.location='/vinculacion/inicio.do';" src="${pageContext.request.contextPath}/img/LogoCCMxazul2.png"></td>
				</tr>
			</table>
		</td>
		<td>
			<table>
				<tr class="headertr">
					<td colspan="2"><label class="headerlabeltitle">Sistema Integral de Administración CCMX</label></td>
				</tr>
				<tr class="headertr">
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
		</td>
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