<%@ taglib
	prefix="s"
	uri="/struts-tags"%>
<!-- ENCABEZADO -->
<table
	class="headertable"
	align="center">
	<tr class="headertr">
		<td class="headertd">
		<table>
			<tr>
				<td><img
					class="headerimg"
					style="cursor: pointer;"
					onclick="top.location='/vinculacion/inicio.do';"
					src="${pageContext.request.contextPath}/img/LogoCCMxazul2.png"></td>
			</tr>
		</table>
		</td>
		<td>
		<table>
			<tr class="headertr">
				<td colspan="2"><label class="headerlabeltitle">Sistema Integral de Administración
				CCMX</label></td>
			</tr>
			<tr class="headertr">
				<td width="95%"><label class="headerlabelusuario"><s:if
					test="%{#session.Usuario.rol=='AdministradorCCMX'}">Administrador CCMX:</s:if> <s:elseif
					test="%{#session.Usuario.rol=='AdministradorTractora'}">Tractora:</s:elseif> <s:elseif
					test="%{#session.Usuario.rol=='Tractora'}">Comprador:</s:elseif> <s:elseif
					test="%{#session.Usuario.rol=='CoordinadorConsultoras'}">Coordinador Consultoras:</s:elseif> <s:elseif
					test="%{#session.Usuario.rol=='CoordinadorDiplomados'}">Coordinador Diplomados:</s:elseif> <s:elseif
					test="%{#session.Usuario.rol=='PyME'}">PyME:</s:elseif> <s:elseif
					test="%{#session.Usuario.rol=='AdministradorConsultores'}">Administrador Consultores:</s:elseif>
				<s:elseif test="%{#session.Usuario.rol=='Consultor'}">Consultor:</s:elseif> <s:property
					value="#session.Usuario.id" />&nbsp;</label></td>
				<td
					width="5%"
					style="vertical-align: bottom !important;"><s:if test="%{#session.Usuario.rol!=''}">
					<s:url
						id="uri"
						action="logout.do"
						encode="true"
						namespace="">
					</s:url>
					<label class="headerlabelsalir"><s:a href="%{uri}">Salir</s:a></label>
				</s:if></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
