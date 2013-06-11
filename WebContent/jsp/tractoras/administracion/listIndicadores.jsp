<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link
	href="${pageContext.request.contextPath}/css/rating.css" 
	rel="stylesheet" 
	type="text/css" />
<script src="${pageContext.request.contextPath}/js/ayudas.js" type="text/javascript"></script>	
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script 
	type="text/javascript" 
	src="${pageContext.request.contextPath}/js/indicadores.js"></script>
	
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-330px auto 0 250px';
	$(document).ready(function() {
		$('#calif').calif({maxvalue: 5});
	});
</script>
</head>

<body>
<s:if test="mensaje!=null">
	<br />
	<table class="nota">
		<tr>
			<td class="imgNota"><s:if test="mensaje.respuesta==0">
				<img src="${pageContext.request.contextPath}/img/palomitaverde.gif" />
			</s:if> <s:else>
				<img src="${pageContext.request.contextPath}/img/warning.png" />
			</s:else></td>
			<td class="contenidoNota"><s:property value="mensaje.mensaje" /></td>
		</tr>
	</table>
</s:if>

<fieldset id="requerimientos">
	<s:hidden name="indicador" id="idIndi" value="%{indicador}" />
	<legend>
		<s:if test="indicador == 0 && calificaPyME == 0">
			<s:label value="PyMEs vinculadas" />
			<br /><br />
		</s:if>
		<s:elseif test="indicador!=0">
			<s:label value="Indicadores" />
			<br /><br />
		</s:elseif>
		<s:elseif test="calificaPyME!=0">
			<s:label value="Calicación de la PyME" />
			<br /><br />
		</s:elseif>
	</legend>
	
	<div ${indicador==0?' style="display: block;"':' style="display: none;"'}>
		<div ${calificaPyME==0?' style="display: block;"':' style="display: none;"'}>
			<table width="99%" cellspacing="1" cellpadding="1">
				<thead>
					<tr>
						<td class="encabezado_tabla" align="center"><b>No.</b></td>
						<td class="encabezado_tabla" align="center"><b>Nombre de la empresa</b></td>
						<td class="encabezado_tabla" align="center"><b>Nombre del contacto</b></td>
						<td class="encabezado_tabla" align="center"><b>Correo electrónico</b></td>
						<td class="encabezado_tabla" align="center"><b>Subir Indicadores</b></td>
						<td class="encabezado_tabla" align="center"><b>Calificar PyME</b></td>
					</tr>
				</thead>
				<tbody>
					<s:set var="contador" value="0" />
					<s:iterator value="listPyMEsIndicadores" status="stat">
							<s:set var="cnt" value="#contador=#contador+1" />
							<tr>
								<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${cnt}</td>
								<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${nombreComercial}</td>
								<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${nombreContacto1}</td>
								<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${correoElectronicoContacto1}</td>
								<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
									<a href="${pageContext.request.contextPath}/administracion/tractoraIndicadoresShow.do?indicador=${idTractora}&rel=${idUsuario}&empresa=${nombreComercial}">Subir</a>
								</td>
								<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
									<a href="${pageContext.request.contextPath}/administracion/tractoraIndicadoresShow.do?calificaPyME=${idTractora}&rel=${idUsuario}">Calificar</a>
								</td>
							</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
	
	<div ${indicador!=0?' style="display: block;"':' style="display: none;"'}>
		<s:textarea id="empresaIndi" rows="1" cols="40" disabled="true" cssClass="resultado" style="resize: none;" value="%{empresa}" />
		<br />
	</div>
	
	<div id="listIndi" ${indicador!=0?' style="display: block;"':' style="display: none;"'}>
		<s:label cssClass="etiquetaCaptura" value="Lista de Indicadores:" />
		<select id="indicadorPyME" style="width: 500px;" 
			onchange="javascript:showForm(this.value);"
			onfocus="javascript:ayudasHelp(0);" onblur="javascript:ayudasHelpBlo(0);">
			<option selected="selected" value="0">Seleccione un indicador</option>
			<s:iterator value="listCatIndicadoresTractora" status="stat">
				<option value="${idIndicador }">${indicador}</option>
			</s:iterator>
		</select>
		<br />
		<div id="ayudasDisplay0" style="display: none">
			<s:label cssClass="etiquetaAyuda" value="Seleccione el indicador para ver la descripción del indicador, el método de cálculo y la frecuencia de medición." />
		</div>
	</div>
	
	<!-- SECCION DE CAPTURA DE ACUERDO AL INDICADOR SELECCIONADO -->
	
	<div id="contFormInd" style="display: none;">
		<s:form action="tractoraIndicadoresShow" namespace="/" method="post" theme="simple">
			<s:hidden id="hidIdPyMETractora" name="indicadores.idPyMETractora" value="%{indicador}" />
			<s:hidden name="indicador" id="idIndi" value="%{indicador}" />
			<s:hidden name="indicadores.idPyME" id="idPyME" value="%{rel}" />
			<table>
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Indicador:" />
					</td>
					<td>
						<s:hidden id="hidAreaIndi" name="indicadores.idIndicadorTractora" value="%{indicadores.idIndicadorTractora}" />
						<s:textarea id="areaIndi" rows="1" cols="60" disabled="true" cssClass="resultado" style="resize: none;" value="" />
					</td>
				</tr>
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Unidad de Medida:" />
					</td>
					<td>
						<s:textarea id="areaUnidadMed" rows="1" cols="60" disabled="true" cssClass="resultado" style="resize: none;" value="" />
					</td>
				</tr>
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Descripción:" />
					</td>
					<td>
						<s:textarea id="descIndi" rows="2" cols="80" disabled="true" cssClass="resultado" style="resize: none;" value="" />
					</td>
				</tr>
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Frecuencia de Medición:" />
					</td>
					<td>
						<s:textarea id="frecIndi" rows="1" cols="80" disabled="true" cssClass="resultado" style="resize: none;" value="" />
					</td>
				</tr>
			</table>
			<br />
			
			<!-- Contenedor de formulas -->
			
			<div id="contFormula1" style="display: none; ">
				<table>
					<tr>
						<td colspan="2">
							<s:label cssClass="etiquetaCaptura" value="Calcular Ahorro:" />
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="cpg1" maxlength="100"
							onfocus="javascript:ayudasHelp(2);" onblur="javascript:ayudasHelpBlo(2);" onkeydown="return validaNumero(event)">
							</s:textfield></td>
						<td>
						<div id="ayudasDisplay2" style="display: none">
							<s:label cssClass="etiquetaAyuda" value="Ingrese el costo de la propuesta ganadora" />
						</div>
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="cptp1" maxlength="100"
							onfocus="javascript:ayudasHelp(3);" onblur="javascript:ayudasHelpBlo(3);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay3" style="display: none">
							<s:label cssClass="etiquetaAyuda" value="Ingrese el costo promedio del total de propuestas que cumplieron los criterios de calidad." />
						</div>	
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="botonenviar" value="Calcular" type="button" onclick="javascript:calculaIndicador1();"/>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="contFormula2" style="display: none; ">
				<table>
					<tr>
						<td colspan="2">
							<s:label cssClass="etiquetaCaptura" value="Calular Ahorro" />
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="cac2" maxlength="100"
						onfocus="javascript:ayudasHelp(4);" onblur="javascript:ayudasHelpBlo(4);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay4" style="display: none">
							<s:label cssClass="etiquetaAyuda" value="Ingrese el costo de la propuesta antes de la consultoría." />
						</div>
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="cdc2" maxlength="100"
						onfocus="javascript:ayudasHelp(5);" onblur="javascript:ayudasHelpBlo(5);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay5" style="display: none">
							<s:label cssClass="etiquetaAyuda" value="Ingrese el costo de la propuesta después de la consultoría." />
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="botonenviar" value="Calcular" type="button" onclick="javascript:calculaIndicador2();"/>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="contFormula3" style="display: none; ">
				<table>
					<tr>
						<td colspan="2">
							<s:label cssClass="etiquetaCaptura" value="Calular porcentaje de productos con defectos" />
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="tud3" maxlength="100"
							onfocus="javascript:ayudasHelp(6);" onblur="javascript:ayudasHelpBlo(6);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay6" style="display: none">
							<s:label cssClass="etiquetaAyuda" value="Ingrese el total de unidades defectuosas." />
						</div>	
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="tu3" maxlength="100"
							onfocus="javascript:ayudasHelp(7);" onblur="javascript:ayudasHelpBlo(7);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay7" style="display: none">
							<s:label cssClass="etiquetaAyuda" value="Ingrese el total de unidades. Las unidades pueden ser en piezas o peso neto." />
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="botonenviar" value="Calcular" type="button" onclick="javascript:calculaIndicador3();"/>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="contFormula4" style="display: none; ">
				<table>
					<tr>
						<td colspan="2">
							<s:label cssClass="etiquetaCaptura" value="Calcular porcentaje de apego al requerimiento" />
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="scc4" maxlength="100"
							onfocus="javascript:ayudasHelp(8);" onblur="javascript:ayudasHelpBlo(8);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay8" style="display: none">
							<s:label cssClass="etiquetaAyuda" value="Ingrese Servicios contratados cumplidos." />
						</div>
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="sc4" maxlength="100"
							onfocus="javascript:ayudasHelp(9);" onblur="javascript:ayudasHelpBlo(9);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay9" style="display: none">
							<s:label cssClass="etiquetaAyuda" value="Ingrese Servicios contratados." />
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="botonenviar" value="Calcular" type="button" onclick="javascript:calculaIndicador4();"/>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="contFormula6" style="display: none; ">
				<table>
					<tr>
						<td colspan="2">
							<s:label cssClass="etiquetaCaptura" value="Calcular ahorro en el tiempo de respuesta." />
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="tr6" maxlength="100"
							onfocus="javascript:ayudasHelp(10);" onblur="javascript:ayudasHelpBlo(10);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay10" style="display: none">
							<s:label cssClass="etiquetaAyuda" 
							value="Ingrese Tiempo en días desde la publicación hasta recibir la respuesta." />
						</div>
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="tdrc6" maxlength="100"
							onfocus="javascript:ayudasHelp(11);" onblur="javascript:ayudasHelpBlo(11);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay11" style="display: none">
							<s:label cssClass="etiquetaAyuda" 
							value="Ingrese tiempo destinado para recibir cotizaciones." />
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="botonenviar" value="Calcular" type="button" onclick="javascript:calculaIndicador6();"/>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="contFormula7" style="display: none; ">
				<table>
					<tr>
						<td colspan="2">
							<s:label cssClass="etiquetaCaptura" value="Calcular días para solventar reclamaciones o reponer productos defectuosos." />
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="fr7" maxlength="100"
							onfocus="javascript:ayudasHelp(12);" onblur="javascript:ayudasHelpBlo(12);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay12" style="display: none">
							<s:label cssClass="etiquetaAyuda" 
							value="Ingrese fecha de la reclamación" />
						</div>
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="fs7" maxlength="100"
							onfocus="javascript:ayudasHelp(13);" onblur="javascript:ayudasHelpBlo(13);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay13" style="display: none">
							<s:label cssClass="etiquetaAyuda" 
							value="Ingrese fecha de solución de la reclamación o atención de defectos." />
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="botonenviar" value="Calcular" type="button" onclick="javascript:calculaIndicador7();"/>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="contFormula8" style="display: none; ">
				<table>
					<tr>
						<td colspan="2">
							<s:label cssClass="etiquetaCaptura" value="Calcular eficacia en el tiempo de respuesta sobre reclamaciones" />
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="tr8" maxlength="100"
							onfocus="javascript:ayudasHelp(14);" onblur="javascript:ayudasHelpBlo(14);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay14" style="display: none">
							<s:label cssClass="etiquetaAyuda" 
							value="Ingrese tiempo real en días en los que solucionó un reclamo." />
						</div>
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="tc8" maxlength="100"
							onfocus="javascript:ayudasHelp(15);" onblur="javascript:ayudasHelpBlo(15);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay15" style="display: none">
							<s:label cssClass="etiquetaAyuda" value="Ingrese tiempo en días acordados para solucionar reclamaciones." />
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="botonenviar" value="Calcular" type="button" onclick="javascript:calculaIndicador8();"/>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="contFormula9" style="display: none; ">
				<table>
					<tr>
						<td colspan="2">
							<s:label cssClass="etiquetaCaptura" value="Calcular crecimiento en ventas" />
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="vt29" maxlength="100"
							onfocus="javascript:ayudasHelp(16);" onblur="javascript:ayudasHelpBlo(16);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay16" style="display: none">
							<s:label cssClass="etiquetaAyuda" value="Ingrese Ventas del trimestre analizado en el año corriente." />
						</div>
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="vt19" maxlength="100"
							onfocus="javascript:ayudasHelp(17);" onblur="javascript:ayudasHelpBlo(17);" onkeydown="return validaNumero(event)"></s:textfield></td>
						<td>
						<div id="ayudasDisplay17" style="display: none">
							<s:label cssClass="etiquetaAyuda" value="Ventas del mismo trimestre para el año pasado." />
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="botonenviar" value="Calcular" type="button" onclick="javascript:calculaIndicador9();"/>
						</td>
					</tr>
				</table>
			</div>
			
			<!-- Finaliza Contenedor de formulas -->
			<div id="resCalcGral" style="display: none;">
				<table>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="Resultado del cálculo: " />
						</td>
						<td>
							<s:textarea id="resCalculo" rows="1" cols="80" disabled="true" cssClass="resultado" style="resize: none;" value="" />
							<s:hidden id="hidResCalculo" name="indicadores.resultadoCalculo" value="%{indicadores.resultadoCalculo}" />
						</td>
					</tr>
				</table>
			</div>
			
			<br />
			<table>
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Periodo de Referencia:" />
					</td>
					<td>
						<select id="periodoRef" name="indicadores.periodoRefMes" style="width: 200px;">
							<option selected="selected" value="0">Seleccione un rango</option>
							<option value="Enero - Marzo">Enero - Marzo</option>
							<option value="Abril - Junio">Abril - Junio</option>
							<option value="Julio - Septiembre">Julio - Septiembre</option>
							<option value="Octubre - Diciembre">Octubre - Diciembre</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						<select id="periodoRefAnio" name="indicadores.periodoRefAnio" style="width: 200px;">
							<option selected="selected" value="0">Seleccione un año</option>
							<option value="2013">2013</option>
							<option value="2012">2012</option>
							<option value="2011">2011</option>
							<option value="2010">2010</option>
						</select>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td colspan="2">
						<s:submit cssClass="botonenviar" value="Guardar" />
					</td>
				</tr>
			</table>
		</s:form>
	</div>
	
	<!-- SEGUNDO FORM "CALIFICA PYME" -->
	
	<div id="showCalif" ${calificaPyME!=0?' style="display: block;"':' style="display: none;"'}>
		<s:form name="frmCalifica" action="tractoraIndicadoresShow" namespace="/administracion" enctype="multipart/form-data" method="post" theme="simple">
			<s:hidden id="idPymeTractora" name="relPyMEsTractoras.idPyMETractora" value="%{relPyMEsTractoras.idPyMETractora}" />
			<table>
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Campo de comentarios abierto" />
					</td>
					<td>
						<s:textarea id="areaComent" rows="3" cols="50" name="relPyMEsTractoras.comentario" value="%{relPyMEsTractoras.comentario}" />
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
			<table>
				<tr>
					<td style="width: 220px;">
						<s:label cssClass="etiquetaCaptura" value="Califica a la PYME:" />
					</td>
					<td style="width: 115px;">
						<div id="calif" class="rating">&nbsp;</div>
					</td>
					<td>
						<s:textarea id="califCont" rows="1" cols="5" disabled="true" cssClass="resultado" style="resize: none;" name="" value="%{relPyMEsTractoras.calificacion}" />
						<s:hidden id="hidCalifCont" name="relPyMEsTractoras.calificacion" value="%{relPyMEsTractoras.calificacion}" />
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td style="width: 220px;">
						<s:label cssClass="etiquetaCaptura" value="Recomendación:" />
					</td>
					<td>
						<s:file id="archivo1" name="relPyMEsTractoras.archivo1" onfocus="javascript:ayudasHelp(17);" onblur="javascript:ayudasHelpBlo(17);"/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<div id="ayudasDisplay18" style="display: none">
						<s:label cssClass="etiquetaAyuda" value="Indique el archivo que será incluido. Máximo 2MB (.png .jpg)" />
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input class="botonenviar" value="Enviar Calificación" type="button" onclick="javascript: califica();" />
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</fieldset>
<script type="text/javascript">

function califica() {
	document.frmCalifica.submit();
}

</script>
</body>
</html>