<%@ taglib
	uri="/struts-tags"
	prefix="s"%>
<script type="text/javascript">
	document.getElementById('userContainer').style.display = 'none';
	document.getElementById('workingContainer').style.width = '100%';
	document.getElementById('workingContainer').style.margin = '0px 10px 0px 0%';
</script>
<table>
	<tr>
		<td style="height: 50px;"></td>
	</tr>
</table>
<table>
	<tr>
		<td
			align="center"
			colspan="2"><label class="errorTitlelabel"><b>:/&nbsp;&nbsp;&nbsp;&nbsp;OCURRI&Oacute;
					UN ERROR EN EL SISTEMA.</b></label></td>
	</tr>
	<tr>
		<td style="height: 20px;"></td>
	</tr>
	<tr>
		<td align="right"><label class="errorTextlabel"><b>TICKET ID:</b></label></td>
		<td align="left"><label class="errorTextlabel"><s:property value="uuid" /></label></td>
	</tr>
	<tr>
		<td align="right"><label class="errorTextlabel"><b>CAUSA:</b></label></td>
		<td align="left">
			<label class="errorTextlabel">
				<s:if test="cause=='expired'">
					Su sesión ha expirado, por favor ingrese nuevamente seleccionando la opción 'Ingresar'.<br />
				</s:if><s:else>
					<s:property value="cause" />
				</s:else>
			</label>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<s:if test="cause=='expired'">
				<fieldset id="requerimientos">
					<s:form name="frmSalir" action="logout" onsubmit="javascript: $(idProcesa)[0].style.display = 'block';" theme="simple" cssStyle="margin-left: 45%;">
						<s:submit cssClass="botonenviar" value="Ingresar" />
					</s:form>
				</fieldset>
			</s:if><s:else>
				<br />
				<label class="errorTextlabel">Reporte esta pantalla al administrador por favor.</label>
			</s:else>
		</td>
	</tr>
	<tr>
		<td
			colspan="2"
			align="center"
			style="font-size: 12px !important"><br> <br> <a
			href="javascript: window.history.back()"><s:text name="http.error.back" /></a></td>
	</tr>
</table>
