<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="footer">
	<tbody>
		<tr>
			<td><label class="footerlabel">CCMX Contacto: </label><s:a href="mailto:contacto@ccmx.org.mx">
				contacto@ccmx.org.mx
			</s:a> | <s:url id="uri" action="help" encode="true" namespace="/publico">
				<s:param name="llave">terminosUso</s:param>
			</s:url> <s:a href="%{uri}">
				Terminos de Uso
			</s:a> | <s:url id="uri" action="help" encode="true" namespace="/publico">
				<s:param name="llave">politicasPrivacidad</s:param>
			</s:url> <s:a href="%{uri}">
				Pol&iacute;ticas de Privacidad
			</s:a></td>
		</tr>
	</tbody>
</table>
