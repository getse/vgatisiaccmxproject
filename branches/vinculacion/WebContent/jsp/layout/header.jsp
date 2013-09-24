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
					<td width="95%"><label class="headerlabelusuario"><s:if test="%{#session.Usuario.rol=='AdministradorCCMX'}">CCMX Administrador:</s:if> <s:elseif test="%{#session.Usuario.rol=='Tractora'}">Tractora Administrador:</s:elseif> <s:elseif test="%{#session.Usuario.rol=='Comprador'}">Comprador:</s:elseif>
							<s:elseif test="%{#session.Usuario.rol=='CoordinadorConsultoras'}">Coordinador Consultoras:</s:elseif> <s:elseif test="%{#session.Usuario.rol=='CoordinadorDiplomados'}">Coordinador Diplomados:</s:elseif> <s:elseif test="%{#session.Usuario.rol=='PyME'}">PyME:</s:elseif> <s:elseif
								test="%{#session.Usuario.rol=='AdministradorConsultores'}">Consultora Administrador:</s:elseif> <s:elseif test="%{#session.Usuario.rol=='Consultor'}">Consultor:</s:elseif> <s:property value="#session.Usuario.id" />&nbsp;</label></td>
					<s:if test="%{#session.Usuario.rol=='AdministradorCCMX_'}">
						<td><label class="headerlabelmanual" onclick="javascript:document.frmManual1.submit();">Manual de Usuario</label></td>
					</s:if>
					<s:if test="%{#session.Usuario.rol=='Tractora_'}">
						<td><label class="headerlabelmanual" onclick="javascript:document.frmManual3.submit();;">Manual de Usuario</label></td>
					</s:if>
					<s:if test="%{#session.Usuario.rol=='Comprador_'}">
						<td><label class="headerlabelmanual" onclick="javascript:document.frmManual4.submit();;">Manual de Usuario</label></td>
					</s:if>
					<s:if test="%{#session.Usuario.rol=='CoordinadorConsultoras_'}">
						<td><label class="headerlabelmanual" onclick="javascript:document.frmManual7.submit();">Manual de Usuario</label></td>
					</s:if>
					<s:if test="%{#session.Usuario.rol=='CoordinadorDiplomados_'}">
						<td><label class="headerlabelmanual" onclick="javascript:document.frmManual8.submit();">Manual de Usuario</label></td>
					</s:if>
					<s:if test="%{#session.Usuario.rol=='PyME_'}">
						<td><label class="headerlabelmanual" onclick="javascript:document.frmManual2.submit();">Manual de Usuario</label></td>
					</s:if>
					<s:if test="%{#session.Usuario.rol=='AdministradorConsultores_'}">
						<td><label class="headerlabelmanual" onclick="javascript:document.frmManual5.submit();">Manual de Usuario</label></td>
					</s:if>
					<s:if test="%{#session.Usuario.rol=='Consultor_'}">
						<td><label class="headerlabelmanual" onclick="javascript:document.frmManual6.submit();">Manual de Usuario</label></td>
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
<a href="${pageContext.request.contextPath}/comprador/showDoc.do?idArchivo=${requerimientos.idArchivo1}&nameArchivo=${requerimientos.archivo1FileName}&mimeArchivo=${requerimientos.archivo1ContentType}">${requerimientos.archivo1FileName}</a>
<s:form name="frmManual1" action="showMan.do" theme="simple">
	<s:hidden name="idArchivo" value="1" />
	<s:hidden name="nameArchivo" value="ManualDeUsuario.pdf" />
</s:form>
<s:form name="frmManual2" action="showMan.do" theme="simple">
	<s:hidden name="idArchivo" value="2" />
	<s:hidden name="nameArchivo" value="ManualDeUsuario.pdf" />
</s:form>
<s:form name="frmManual3" action="showMan.do" theme="simple">
	<s:hidden name="idArchivo" value="3" />
	<s:hidden name="nameArchivo" value="ManualDeUsuario.pdf" />
</s:form>
<s:form name="frmManual4" action="showMan.do" theme="simple">
	<s:hidden name="idArchivo" value="4" />
	<s:hidden name="nameArchivo" value="ManualDeUsuario.pdf" />
</s:form>
<s:form name="frmManual5" action="showMan.do" theme="simple">
	<s:hidden name="idArchivo" value="5" />
	<s:hidden name="nameArchivo" value="ManualDeUsuario.pdf" />
</s:form>
<s:form name="frmManual6" action="showMan.do" theme="simple">
	<s:hidden name="idArchivo" value="6" />
	<s:hidden name="nameArchivo" value="ManualDeUsuario.pdf" />
</s:form>
<s:form name="frmManual7" action="showMan.do" theme="simple">
	<s:hidden name="idArchivo" value="7" />
	<s:hidden name="nameArchivo" value="ManualDeUsuario.pdf" />
</s:form>
<s:form name="frmManual8" action="showMan.do" theme="simple">
	<s:hidden name="idArchivo" value="8" />
	<s:hidden name="nameArchivo" value="ManualDeUsuario.pdf" />
</s:form>

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