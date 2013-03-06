<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- ENCABEZADO -->
<table class="headertable" align="center">
	<tr class="headertr">
		<td class="headertd">
		<table>
			<tr>
				<td><img class="headerimg" style="cursor: pointer;" onclick="top.location='/vinculacion/inicios.do';"
					src="<%=request.getContextPath()%>/img/LogoCCMxazul.png"></td>
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
				<s:property value="#session.Usuario.id"/> ~</label></td>
				<td width="5%"><s:url id="uri" action="logout.do" encode="true"
					namespace="">
				</s:url> <s:a href="%{uri}">Salir</s:a></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
