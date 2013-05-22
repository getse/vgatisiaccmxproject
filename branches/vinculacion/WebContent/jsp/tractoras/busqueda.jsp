<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
</head>
<body>
<h5>ResBusCatSCIANCCMX...</h5>
<fieldset id="requerimientos">
	<s:set var="oProductos" value="productos" />
	<s:if test="%{#oProductos.isEmpty}">
		<table>
			<tr>
				<td class="encabezadoTablaResumen" align="center">
					Estimado usuario, no se encontraron coincidencias con este criterio de búsqueda.
					<br />Seleccione la opción: 'Cancelar' para intentar nuevamente por favor.
				</td>
			</tr>
		</table>
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<table class="submit_tabla">
				<tr>
					<td style="height: 40px;"><s:label cssClass="etiquetaCaptura" >Sin resultados, seleccione 'Cancelar'.</s:label></td>
				</tr>
			</table>
	</s:if>
	<s:iterator value="oProductos" status="row">
		<div id="idDivRes${row.count}" ${row.count == 1 ? ' style="display: block;"' : ' style="display: none;"'}>
			<table>
				<tr>
					<td class="encabezadoTablaResumen" align="center">
						<s:hidden id="idHidResCveScian%{#row.count}" value="%{cveScian}" />
						<s:hidden id="idHidResDescScian%{#row.count}" value="%{descScian}" />
						Resultado<font class="marcaResultado"> No. <b><s:property value="#row.count" /></b></font>, presione 'Siguiente' o 'Anterior' para navegar por todos los resultados.
						<br />Seleccione un resultado dando cilc en la opción 'Elegir este resultado'.
					</td>
				</tr>
			</table>
			<br />
			<div class="resultado">
			<table>
				<tr>
					<td>
						<s:label id="idLabelResNivel1%{#row.count}" cssClass="cuerpo1LabelResultado" value="%{nivel1}" />
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>
						<label style="position: absolute; margin-left: 45px; margin-top: 1px">&raquo;&nbsp;</label><s:label id="idLabelResNivel2%{#row.count}" cssClass="cuerpo2LabelResultado" value="%{nivel2}" />
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>
						<label style="position: absolute; margin-left: 95px; margin-top: 1px">&raquo;&nbsp;</label><s:label id="idLabelResNivel3%{#row.count}" cssClass="cuerpo3LabelResultado" value="%{nivel3}" />
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>
						<label style="position: absolute; margin-left: 145px; margin-top: 1px">&raquo;&nbsp;</label><s:label id="idLabelResNivel4%{#row.count}" cssClass="cuerpo4LabelResultado" value="%{nivel4}" />
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>
						<label style="position: absolute; margin-left: 195px; margin-top: 1px">&raquo;&nbsp;</label><s:label id="idLabelResDescScian%{#row.count}" cssClass="cuerpo5LabelResultado" value="%{descScian}" />
					</td>
				</tr>
			</table>
			<table class="submit_tabla">
				<tr>
					<td style="text-align: left; padding: 20px 0px 0px 170px;"><s:label cssClass="etiquetaCaptura" >página actual -<b><s:property value="#row.count" /></b>-</s:label></td>
				</tr>
			</table>
			</div>
		</div>
	</s:iterator>
	<table class="submit_tabla" style="margin-left: 0px; width: 48%;">
		<tr>
			<td style="width: 250px;"></td>
			<td><input class="botonenviar" id="idBtnAnterior" value="<< Anterior" type="button" onclick="javascript: anterior();" /></td>
			<td style="width: 9%;"></td>
			<td><input class="close" value="Cancelar" type="button" style="color: rgb(125, 125, 125);" onclick="$('.overlay-container').fadeOut().end().find('.window-container').removeClass('window-container-visible');"/></td>
			<td style="width: 9%;"></td>
			<td><input class="close" style="color: rgb(125, 125, 125); text-decoration: underline;" value="Elegir este resultado" type="button" onclick="javascript: elegir(false); $('.overlay-container').fadeOut().end().find('.window-container').removeClass('window-container-visible');" /></td>
			<td style="width: 9%;"></td>
			<td><input class="botonenviar" id="idBtnSiguiente" value="Siguiente >>" type="button" onclick="javascript: siguiente();" /></td>
			<td style="width: 250px;"></td>
		</tr>
	</table>
</fieldset>
<h5>ResBusCatSCIANCCMX...</h5>
</body>
</html>