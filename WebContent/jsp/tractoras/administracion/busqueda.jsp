<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
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
					<td style="height: 40px;"><s:label cssClass="etiquetaCaptura" >Seleccione 'Cancelar'.</s:label></td>
				</tr>
			</table>
	</s:if>
	<s:iterator value="oProductos" status="row">
		<div id="idDivRes${row.count}" ${row.count == 1 ? ' style="width: 200px; height: 100px; overflow-y: scroll; display: block;"' : ' style="width: 200px; height: 100px; overflow-y: scroll; display: none;"'}>
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
			<table class="resultado">
				<tr>
					<td>
						<label style="position: absolute; margin-left: 95px; margin-top: 1px">&raquo;&nbsp;</label><s:label id="idLabelResNivel3%{#row.count}" cssClass="cuerpo3LabelResultado" value="%{nivel3}" />
					</td>
				</tr>
			</table>
			<table class="resultado">
				<tr>
					<td>
						<label style="position: absolute; margin-left: 145px; margin-top: 1px">&raquo;&nbsp;</label><s:label id="idLabelResNivel4%{#row.count}" cssClass="cuerpo4LabelResultado" value="%{nivel4}" />
					</td>
				</tr>
			</table>
			<table class="resultado">
				<tr>
					<td>
						<label style="position: absolute; margin-left: 195px; margin-top: 1px">&raquo;&nbsp;</label><s:label id="idLabelResDescScian%{#row.count}" cssClass="cuerpo5LabelResultado" value="%{descScian}" />
					</td>
				</tr>
			</table>
			<table class="submit_tabla">
				<tr>
					<td style="height: 40px;"><s:label cssClass="etiquetaCaptura" >página actual -<b><s:property value="#row.count" /></b>-</s:label></td>
				</tr>
			</table>
			</div>
		</div>
	</s:iterator>
	<table class="submit_tabla">
		<tr>
			<td style="width: 250px;"></td>
			<div id="idDivBack" style="display: block;">
			<td><input
				class="botonenviar"
				id="idBtnAnterior"
				value="<< Anterior"
				type="button"
				style="display: none;"
				onclick="javascript: anterior();" /></td>
			</div>
			<td style="width: 90%;"></td>
			<div id="idDivNext" style="display: block;">
			<td><input
				class="botonenviar"
				id="idBtnSiguiente"
				value="Siguiente >>"
				type="button"
				onclick="javascript: siguiente();" /></td>
			</div>
			<td style="width: 250px;"></td>
		</tr>
	</table>
</fieldset>
<h5>ResBusCatSCIANCCMX...</h5>
</body>
</html>