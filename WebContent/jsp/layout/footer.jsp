<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="footer">
	<tbody>
		<tr>
			<td><label class="footerlabel">CCMX Contacto: </label><s:a href="mailto:contacto@ccmx.org.mx">
				contacto@ccmx.org.mx
			</s:a> | <s:url id="uri" action="terminos" encode="true" namespace="/" />
			<s:a href="%{uri}">
				Terminos de Uso
			</s:a></td>
		</tr>
	</tbody>
</table>

<script>
	$(idProcesa)[0].style.display = 'none';
</script>