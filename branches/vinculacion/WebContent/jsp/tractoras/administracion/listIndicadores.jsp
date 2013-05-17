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
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script 
	type="text/javascript" 
	src="${pageContext.request.contextPath}/js/indicadores.js"></script>
	
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-270px auto 0 250px';
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
		<s:if test="indicador == 0">
			<s:label value="PyMEs vinculadas" />
			<br /><br />
		</s:if>
		<s:else>
			<s:label value="Indicadores" />
			<br /><br />
		</s:else>
	</legend>
	
	<div ${indicador==0?' style="display: block;"':' style="display: none;"'}>
		<table width="800px" cellspacing="1" cellpadding="1">
			<thead>
				<tr>
					<td class="encabezado_tabla" align="center"><b>No.</b></td>
					<td class="encabezado_tabla" align="center"><b>Nombre de la empresa</b></td>
					<td class="encabezado_tabla" align="center"><b>Nombre del contacto</b></td>
					<td class="encabezado_tabla" align="center"><b>Correo electrónico</b></td>
					<td class="encabezado_tabla" align="center"><b>Subir Indicadores</b></td>
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
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center"><a href="${pageContext.request.contextPath}/administracion/tractoraIndicadoresShow.do?indicador=${idUsuario}&empresa=${nombreComercial}">Subir</a></td>
						</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	
	<div ${indicador!=0?' style="display: block;"':' style="display: none;"'}>
		<s:textarea id="empresaIndi" rows="1" cols="40" disabled="true" cssClass="resultado" style="resize: none;" value="%{empresa}" />
	</div>
	<br />
	
	<div id="listIndi" ${indicador!=0?' style="display: block;"':' style="display: none;"'}>
		<s:label cssClass="etiquetaCaptura" value="Lista de Indicadores:" />
		<select id="indicadorPyME" style="width: 500px;" onchange="javascript:showForm(this.value);">
			<option selected="selected" value="0">Seleccione un indicador</option>
			<option value="1. Ahorros (respecto del promedio de otras cotizaciones)">1. Ahorros (respecto del promedio de otras cotizaciones)</option>
			<option value="2. Ahorros (respecto de la última cotización previo a tomar la consultoría)">2. Ahorros (respecto de la última cotización previo a tomar la consultoría)</option>
			<option value="3. Productos con defectos">3. Productos con defectos</option>
			<option value="4. Cumplimiento de servicios">4. Cumplimiento de servicios</option>
			<option value="5. Cumplimiento en el tiempo de entrega">5. Cumplimiento en el tiempo de entrega</option>
			<option value="6. Efectividad en el tiempo de respuesta sobre cotizaciones">6. Efectividad en el tiempo de respuesta sobre cotizaciones</option>
			<option value="7. Tiempo de respuesta para atender reclamaciones o defectos.">7. Tiempo de respuesta para atender reclamaciones o defectos.</option>
			<option value="8. Eficacia en la atención sobre reclamaciones">8. Eficacia en la atención sobre reclamaciones</option>
			<option value="9. Crecimiento en ventas anuales a la tractora (a nivel de producto)">9. Crecimiento en ventas anuales a la tractora (a nivel de producto)</option>
		</select>
	</div>
	
	<!-- SECCION DE CAPTURA DE ACUERDO AL INDICADOR SELECCIONADO -->
	
	<div id="contFormInd" style="display: none;">
		<s:form action="tractoraIndicadoresShow" namespace="/administracion" enctype="multipart/form-data" method="post" theme="simple">
			<s:hidden id="hidIdPyMETractora" name="indicadores.idPyMETractora" value="%{indicador}" />
			<table>
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Indicador:" />
					</td>
					<td>
						<s:textarea id="areaIndi" rows="1" cols="60" disabled="true" cssClass="resultado" style="resize: none;" value="" />
						<s:hidden id="hidAreaIndi" name="indicadores.indicador" value="%{indicadores.indicador}" />
					</td>
				</tr>
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Descripción:" />
					</td>
					<td>
						<s:textarea id="descIndi" rows="2" cols="80" disabled="true" cssClass="resultado" style="resize: none;" value="" />
						<s:hidden id="hidDescIndi" name="indicadores.descripcion" value="%{indicadores.descripcion}" />
					</td>
				</tr>
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Frecuencia de Medición:" />
					</td>
					<td>
						<s:textarea id="frecIndi" rows="1" cols="80" disabled="true" cssClass="resultado" style="resize: none;" value="" />
						<s:hidden id="hidFrecIndi" name="indicadores.frecuencia" value="%{indicadores.frecuencia}" />
					</td>
				</tr>
			</table>
			<br />
			
			<!-- Contenedor de formulas -->
			
			<div id="contFormula1" style="display: none; ">
				<table>
					<tr>
						<td colspan="2">
							<s:label cssClass="etiquetaCaptura" value="Calular Ahorro:" />
						</td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="cpg1" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese el costo de la propuesta ganadora" /></td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="cptp1" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese el costo promedio del total de propuestas que cumplieron los criterios de calidad." /></td>
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
						<td><s:textfield size="40" id="cac2" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese el costo de la propuesta antes de la consultoría." /></td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="cdc2" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese el costo de la propuesta después de la consultoría." /></td>
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
						<td><s:textfield size="40" id="tud3" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese el total de unidades defectuosas." /></td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="tu3" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese el total de unidades. Las unidades pueden ser en piezas o peso neto." /></td>
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
						<td><s:textfield size="40" id="scc4" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese Servicios contratados cumplidos." /></td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="sc4" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese Servicios contratados." /></td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="botonenviar" value="Calcular" type="button" onclick="javascript:calculaIndicador4();"/>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="contFormula5" style="display: none; ">
				<table>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="No aplica el calculo" /></td>
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
						<td><s:textfield size="40" id="tr6" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese Tiempo en días desde la publicación hasta recibir la respuesta." /></td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="tdrc6" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese tiempo destinado para recibir cotizaciones." /></td>
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
						<td><s:textfield size="40" id="fr7" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese fecha de la reclamación" /></td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="fs7" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese fecha de solución de la reclamación o atención de defectos." /></td>
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
						<td><s:textfield size="40" id="tr8" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese tiempo real en días en los que solucionó un reclamo." /></td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="tc8" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese tiempo en días acordados para solucionar reclamaciones." /></td>
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
						<td><s:textfield size="40" id="vt29" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ingrese Ventas del trimestre analizado en el año corriente." /></td>
					</tr>
					<tr>
						<td><s:textfield size="40" id="vt19" maxlength="100"></s:textfield></td>
						<td><s:label cssClass="etiquetaAyuda" value="Ventas del mismo trimestre para el año pasado." /></td>
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
							<option value="Abril - Mayo">Abril - Mayo</option>
							<option value="Junio - Julio">Junio - Julio</option>
							<option value="Agosto - Diciembre">Agosto - Diciembre</option>
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
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Campo de comentarios abierto" />
					</td>
					<td>
						<s:textarea id="areaComent" rows="3" cols="50" name="indicadores.comentario" value="%{indicadores.comentario}" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;
					</td>
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
						<!--s:textarea id="califCont" rows="1" cols="5" disabled="true" cssClass="resultado" style="resize: none;" name="indicadores.calificacion" value="%{indicadores.calificacion}" /-->
						<s:hidden id="califCont" name="indicadores.calificacion" value="%{indicadores.calificacion}" />
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td style="width: 220px;">
						<s:label cssClass="etiquetaCaptura" value="Recomendación:" />
					</td>
					<td>
						<s:file id="archivo1" name="indicadores.archivo1" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						<s:label cssClass="etiquetaAyuda" value="Indique el archivo que será incluido. Máximo 2MB (.png .jpg)" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:submit cssClass="botonenviar" value="Guardar" />
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</fieldset>
</body>
</html>