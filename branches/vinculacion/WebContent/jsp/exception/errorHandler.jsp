<%@ taglib
	prefix="s"
	uri="/struts-tags"%>
<script type="text/javascript">
	document.getElementById('userContainer').style.display = 'none';
	document.getElementById('workingContainer').style.width = '100%';
	document.getElementById('workingContainer').style.margin = '0px 10px 0px 0%';
</script>
<br>
<br>
<table style="text-align: center;">
	<thead>
		<tr>
			<td>
				<h2>
					Por favor ingrese nuevamente seleccionando la opción 'Ingresar'.
				</h2>
			</td>
		</tr>
		<tr>
			<td><br />
				<fieldset id="requerimientos">
					<s:form name="frmSalir" action="logout.do" onsubmit="javascript: $(idProcesa)[0].style.display = 'block';" theme="simple" cssStyle="margin-left: 45%;">
						<s:submit cssClass="botonenviar" value="Ingresar" />
					</s:form>
				</fieldset>
			</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center"><br> <br>
				<table class="nota" style="width: 490px;">
					<tbody>
						<tr>
							<td class="imgNota"><img src="${pageContext.request.contextPath}/img/warning.png" /></td>
							<td>&nbsp;&nbsp;</td>
							<td class="contenidoNota"><s:property
									value="messageKey"
									escape="false" /></td>
						</tr>
					</tbody>
				</table></td>
		</tr>
		<tr>
			<td
				align="center"
				style="font-size: 12px !important"><br> <br> <a
				href="javascript: window.history.back()"><s:text name="http.error.back" /></a></td>
		</tr>
	</tbody>
</table>
