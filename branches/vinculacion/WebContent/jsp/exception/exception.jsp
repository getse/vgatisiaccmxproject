<%@ taglib uri="/struts-tags" prefix="s"%>
<table border="0" cellpadding="0" cellspacing="10">
	<tr>
		<td align="center" class="subtitulo_seccioninterior" colspan="2"><b>OCURRI&Oacute;
		UN ERROR EN EL PORTAL:</b></td>
	</tr>
	<tr>
		<td align="right" class="txt_gral"><b>TICKET ID:</b></td>
		<td align="left" class="txt_gral"><s:property value="uuid" /></td>
	</tr>
	<tr>
		<td align="right" class="txt_gral"><b>CAUSA:</b></td>
		<td align="left" class="txt_gral"><s:property value="cause" /></td>
	</tr>
	<tr>
		<td align="right" class="txt_gral"><b>SOLUCI&Oacute;N:</b></td>
		<td align="left" class="txt_gral"><s:property value="solution" /></td>
	</tr>
</table>
