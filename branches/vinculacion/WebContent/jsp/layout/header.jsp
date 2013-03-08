<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- ENCABEZADO -->
<table class="headertable" align="center">
	<tr class="headertr">
		<td class="headertd">
		<table>
			<tr>
				<td><img class="headerimg" style="cursor: pointer;"
					onclick="top.location='/vinculacion/inicio.do';"
					src="${pageContext.request.contextPath}/img/LogoCCMxazul2.png"></td>
			</tr>
		</table>
		</td>
		<td>
		<table>
			<tr class="headertr">
				<td colspan="2"><label class="headerlabeltitle">Sistema
				Integral de Administración CCMX</label></td>
			</tr>
			<tr class="headertr">
				<td width="95%"><label class="headerlabelusuario">Bienvenido:
				<s:property value="#session.Usuario.id" /> ~</label></td>
				<td width="5%" style="vertical-align: bottom !important;"><s:url
					id="uri" action="logout.do" encode="true" namespace="">
				</s:url><label class="headerlabelsalir"><s:a href="%{uri}">Salir</s:a></label></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
