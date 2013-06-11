<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
	<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/libraries/RGraph.common.core.js"></script>
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/libraries/RGraph.radar.js"></script>
<!--[if lt IE 9]><script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/excanvas/excanvas.js"></script><![endif]-->
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-285px auto 0 250px';
</script>
</head>
<body>
<fieldset id="requerimientos">
	<legend>
		<s:label value="Expediente electrónico PyME" />
		<br />
	</legend>
	<!-- EXPEDIENTE PYME -->
	<div id="resumenPyME" ${idUsuario==0? ' style="display: none;" ' :' style="display: block;"' }>
		<br />
		<br />
		<table class="expediente_tabla" width="99%">
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Expediente PyME</td>
			</tr>
			<tr>
				<td class="cuerpo1TextoResumen" colspan="2">
					<s:label cssClass="etiquetaResumen">${pyMEs.nombreComercial}</s:label>
				</td>
			</tr>
			<tr>
				<td class="cuerpo1TextoResumen" colspan="2">
					<s:label cssClass="etiquetaResumen">${pyMEs.mensajeVentas}</s:label>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="flotantes">
						<table width="100%">
							<tr>
								<td class="encabezadoTablaResumen" align="center">Principales Productos o Servicios</td>
							</tr>
							<!-- PRODUCTOS -->
							<s:iterator status="stat" value="pyMEs.productos" >
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.productos[stat.index].producto}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="flotantes">
						<table width="103%">
							<tr>
								<td class="encabezadoTablaResumen" align="center">Principales Clientes</td>
							</tr>
							<!-- TRACTORA --><!-- CLIENTES -->
							<tr>
								<td class="cuerpo1TextoResumen">
									<ul>
										<li class="resaltaText">
											<s:label>${pyMEs.clientes[0].cliente}</s:label>
										</li>
									</ul>
								</td>
							</tr>
							<s:if test="pyMEs.clientes[1].cliente != null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.clientes[1].cliente}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.clientes[2].cliente != null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.clientes[2].cliente}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.clientes[3].cliente != null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.clientes[3].cliente}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.clientes[4].cliente != null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.clientes[4].cliente}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.clientes[5].cliente != null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.clientes[5].cliente}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Desempeño de la PyME</td>
			</tr>
			
			<tr>
				<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${relPymesTractoras.comentario}</s:label></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<table>
						<tr>
							<td class="encabezadoTablaResumen" style="width: 470px;" align="center">PyME Recomendada por:</td>
							<td class="encabezadoTablaResumen" style="width: 470px;" align="center">Desempeño de la PyME de acuerdo con la empresa tractora:</td>
						</tr>
						<tr>
							<td class="cuerpo1TextoResumen" align="center">Aquí va el logo</td>
							<td class="cuerpo1TextoResumen" align="center">
								<s:if test="relPyMEsTractoras.calificacion == 1">
									<img src="${pageContext.request.contextPath}/img/1_Estrellas.png" width="85px" height="17px" alt="1 Estrellas" />
								</s:if>
								<s:elseif test="relPyMEsTractoras.calificacion == 2">
									<img src="${pageContext.request.contextPath}/img/2_Estrellas.png" width="85px" height="17px" alt="2 Estrellas" />
								</s:elseif>
								<s:elseif test="relPyMEsTractoras.calificacion == 3">
									<img src="${pageContext.request.contextPath}/img/3_Estrellas.png" width="85px" height="17px" alt="3 Estrellas" />
								</s:elseif>
								<s:elseif test="relPyMEsTractoras.calificacion == 4">
									<img src="${pageContext.request.contextPath}/img/4_Estrellas.png" width="85px" height="17px" alt="4 Estrellas" />
								</s:elseif>
								<s:elseif test="relPyMEsTractoras.calificacion == 5">
									<img src="${pageContext.request.contextPath}/img/5_Estrellas.png" width="85px" height="17px" alt="5 Estrellas" />
								</s:elseif>
								<s:else>
									<img src="${pageContext.request.contextPath}/img/0_Estrellas.png" width="85px" height="17px" alt="Sin calificación" />
								</s:else>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td colspan="2">
					<div>
						<!-- INDICADORES -->
						<table width="100%">
							<tr>
								<td class="encabezadoTablaResumen" colspan="9" align="center">Indicadores de experiencias de compra</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 20%;">Concepto</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T1-2012</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T2-2012</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T3-2012</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T4-2012</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T1-2013</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T2-2013</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T3-2013</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T4-2013</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: ">Ahorros (respecto del promedio de otras cotizaciones):<br />%</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosEnero}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosAbril}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosJulio}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosOctubre}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Defectos en producto o servicios:</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosEnero}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosAbril}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosJulio}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosOctubre}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Ahorro en tiempo:</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoEnero}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoAbril}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoJulio}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoOctubre}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Servicio post-venta:</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioEnero}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioAbril}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioJulio}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioOctubre}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioOctubre}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Capacidad de la PYME:</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.capacidadEnero}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.capacidadAbril}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.capacidadJulio}</td>
								<td class="cuerpo1TextoResumen">${indicadoresMes.capacidadOctubre}</td>
								<td class="cuerpo1TextoResumen">${indicadoresMes.capacidadOctubre}</td>
								<td class="cuerpo1TextoResumen">${indicadoresMes.capacidadOctubre}</td>
								<td class="cuerpo1TextoResumen">${indicadoresMes.capacidadOctubre}</td>
								<td class="cuerpo1TextoResumen">${indicadoresMes.capacidadOctubre}</td>
							</tr>
						</table>
					</div>
					<div>
						<table width="100%">
							<tr>
								<td class="encabezadoTablaResumen" colspan="3" align="center">Indicadores CCMX</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" align="center" style="width: 100px;">&nbsp;</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 170px;">Antes de la Consultoría</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 170px;">Después de la Consultoría</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="height: 29px;">RH:</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.recursosHumanosAntes}</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.recursosHumanosDespues}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="height: 29px;">Mercadeo:</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.mercadeoAntes}</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.mercadeoDespues}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="height: 29px;">Finanzas:</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.finanzasAntes}</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.finanzasDespues}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="height: 29px;">Administración:</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.administracionAntes}</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.administracionDespues}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="height: 29px;">Procesos:</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.procesosAntes}</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.procesosDespues}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Avance Promedio:</td>
								<td class="cuerpo1TextoResumen" colspan="2" align="center">
									<label id="formulaRadar">
										<script type="text/javascript">
											$(document).ready(function(){
												var rhA = <s:property value="serviciosConsultoria.recursosHumanosAntes" />;
												var mA = <s:property value="serviciosConsultoria.mercadeoAntes" />;
												var fA = <s:property value="serviciosConsultoria.finanzasAntes" />;
												var aA = <s:property value="serviciosConsultoria.administracionAntes" />;
												var pA = <s:property value="serviciosConsultoria.procesosAntes" />;
												var vpi = (rhA + mA + fA + aA + pA) / 5;
												var rhD = <s:property value="serviciosConsultoria.recursosHumanosDespues" />;
												var mD = <s:property value="serviciosConsultoria.mercadeoDespues" />;
												var fD = <s:property value="serviciosConsultoria.finanzasDespues" />;
												var aD = <s:property value="serviciosConsultoria.administracionDespues" />;
												var pD = <s:property value="serviciosConsultoria.procesosDespues" />;
												var vpf = (rhD + mD + fD + aD + pD) / 5;
												var avance = (vpf - vpi) / vpi;
												var valAvance = parseFloat(avance).toFixed(2);
												document.getElementById("formulaRadar").innerHTML = valAvance;
											});
										</script>
									</label>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td class="encabezadoTablaResumen" colspan="3" align="center">Gráficas de Indicadores CCMX</td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="center">Antes de la Consultoría</td>
				<td class="cuerpo1TablaResumen" align="center">Después de la Consultoría</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td>
								<canvas id="cvsAntes" width="350" height="250">[No canvas support]</canvas>
								<script>
						
								</script>
							</td>
						</tr>
					</table>
				</td>
				
				<td>
					<table>
						<tr>
							<td>
								<canvas id="cvsDesp" width="350" height="250">[No canvas support]</canvas>
								<script>
									window.onload = function() {
										var radar = new RGraph.Radar('cvsAntes', [ <s:property value="serviciosConsultoria.recursosHumanosAntes" />, <s:property value="serviciosConsultoria.mercadeoAntes" />, <s:property value="serviciosConsultoria.finanzasAntes" />, <s:property value="serviciosConsultoria.administracionAntes" />, <s:property value="serviciosConsultoria.procesosAntes" /> ]);
										radar.Set('chart.labels', [ 'Recursos Humanos\n('+<s:property value="serviciosConsultoria.recursosHumanosAntes" />+')', 'Mercadeo\n('+<s:property value="serviciosConsultoria.mercadeoAntes" />+')', 'Finanzas\n('+<s:property value="serviciosConsultoria.finanzasAntes" />+')', 'Administración\n('+<s:property value="serviciosConsultoria.administracionAntes" />+')', 'Procesos\n('+<s:property value="serviciosConsultoria.procesosAntes" />+')']);
										radar.Set('chart.highlights', true);
										radar.Set('chart.linewidth', 1);
										radar.Set('chart.strokestyle', ['lightblue']);
										radar.Set('chart.labels.axes', 'n');
										radar.Set('chart.labels.axes.boxed', false);
										radar.Set('chart.labels.axes.boxed.zero', false);
										radar.Set('chart.labels.offset', 25);
										radar.Set('chart.text.size', 9);
										radar.Set('chart.gutter.left', 30);
										radar.Set('chart.gutter.right', 30);
										radar.Set('chart.gutter.top', 40);
										radar.Set('chart.gutter.bottom', 20);
							            radar.Draw();
							            
							            
							            var radarDesp = new RGraph.Radar('cvsDesp', [ <s:property value="serviciosConsultoria.recursosHumanosDespues" />, <s:property value="serviciosConsultoria.mercadeoDespues" />, <s:property value="serviciosConsultoria.finanzasDespues" />, <s:property value="serviciosConsultoria.administracionDespues" />, <s:property value="serviciosConsultoria.procesosDespues" /> ]);
										radarDesp.Set('chart.labels', [ 'Recursos Humanos\n('+<s:property value="serviciosConsultoria.recursosHumanosDespues" />+')', 'Mercadeo\n('+<s:property value="serviciosConsultoria.mercadeoDespues" />+')', 'Finanzas\n('+<s:property value="serviciosConsultoria.finanzasDespues" />+')', 'Administración\n('+<s:property value="serviciosConsultoria.administracionDespues" />+')', 'Procesos\n('+<s:property value="serviciosConsultoria.procesosDespues" />+')']);
										radarDesp.Set('chart.highlights', true);
										radarDesp.Set('chart.linewidth', 1);
										radarDesp.Set('chart.strokestyle', ['lightblue']);
										radarDesp.Set('chart.labels.axes', 'n');
										radarDesp.Set('chart.labels.axes.boxed', false);
										radarDesp.Set('chart.labels.axes.boxed.zero', false);
										radarDesp.Set('chart.labels.offset', 25);
										radarDesp.Set('chart.text.size', 9);
										radarDesp.Set('chart.gutter.left', 30);
										radarDesp.Set('chart.gutter.right', 30);
										radarDesp.Set('chart.gutter.top', 40);
										radarDesp.Set('chart.gutter.bottom', 20);
										radarDesp.Draw();
									};
								</script>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Estados en los que puede suministrar su producto</td>
			</tr>
			<!-- ESTADOS -->
			<s:if test="estadosVentas.nacional != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.nacional}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.aguascalientes != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.aguascalientes}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.bajaCaliforniaNorte != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.bajaCaliforniaNorte}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.bajaCaliforniaSur != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.bajaCaliforniaSur}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.campeche != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.campeche}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.chiapas != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.chiapas}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.chihuahua != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.chihuahua}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.coahuila != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.coahuila}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.colima != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.colima}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.distritoFederal != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.distritoFederal}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.durango !=  null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.durango}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.guanajuato != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.guanajuato}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.guerrero != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.guerrero}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.hidalgo!= null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.hidalgo}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.jalisco != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.jalisco}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.estadoDeMexico != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.estadoDeMexico}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.michoacan != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.michoacan}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.morelos != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.morelos}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.nayarit != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.nayarit}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.nuevoLeon != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.nuevoLeon}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.oaxaca != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.oaxaca}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.puebla != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.puebla}</s:label></td>
				</tr>
			</s:if>
			
			<s:if test="estadosVentas.queretaro != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.queretaro}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.quintanaRoo != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.quintanaRoo}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.sanLuisPotosi != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.sanLuisPotosi}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.sinaloa != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.sinaloa}</s:label></td>
				</tr>			
			</s:if>
			
			<s:if test="estadosVentas.sonora != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.sonora}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.tabasco != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.tabasco}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.tamaulipas != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.tamaulipas}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.tlaxcala != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.tlaxcala}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.veracruz!= null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.veracruz}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.yucatan != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.yucatan}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.zacatecas != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.zacatecas}</s:label></td>
				</tr>			
			</s:if>
			
			
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Datos de contacto</td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="100%">
						<tr>
							<td class="cuerpo1TablaResumen" align="center" colspan="2">Ventas</td>
							<td class="cuerpo1TablaResumen" align="center" colspan="2">Otro</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Nombre:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.contactos[0].nombre} ${pyMEs.contactos[0].apellidoPaterno} ${pyMEs.contactos[0].apellidoMaterno}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Nombre:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.contactos[1].nombre} ${pyMEs.contactos[1].apellidoPaterno} ${pyMEs.contactos[1].apellidoMaterno}</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen">Teléfono:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.contactos[0].telefono}</td>
							<td class="cuerpo1TablaResumen">Telefóno:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.contactos[1].telefono}</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen">Correo Electrónico:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.contactos[0].correoElectronico}</td>
							<td class="cuerpo1TablaResumen">Correo Electrónico:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.contactos[1].correoElectronico}</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Certificaciones de la empresa y calificaciones de los empleados</td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="100%">
						<tr>
							<td class="cuerpo1TablaResumen" align="center" colspan="2">Certificaciones</td>
							<td class="cuerpo1TablaResumen" align="center" colspan="2">Consultoria CCMX</td>
							<td class="cuerpo1TablaResumen" align="center" colspan="2">Diplomados</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Certificación:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[0].certificacion}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Horas:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">
								<s:if test="serviciosConsultoria.bConsultoriaVeinte==true">
									20
								</s:if>
								<s:elseif test="serviciosConsultoria.bConsultoriaCuarenta==true">
									40
								</s:elseif>
								<s:elseif test="serviciosConsultoria.bConsultoriaSesenta==true">
									60
								</s:elseif>
								<s:elseif test="serviciosConsultoria.bConsultoriaOchenta==true">
									80
								</s:elseif>
								
							</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Diplomado en:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">
								<s:if test="pyMEs.bDiplomadoCcmxUno==true">
									Cultura organizacional y la competitividad de las empresas
								</s:if>
							</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Año:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[0].fechaCertificacion}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Fecha de inicio:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${serviciosConsultoria.inicio}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Diplomado en:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">
								<s:if test="pyMEs.bDiplomadoCcmxDos==true">
									Estrategia Comercial, Imagen y Cadena de Distribución
								</s:if>
							</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Institución:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[0].institutoCertificador}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Fecha de termino:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${serviciosConsultoria.termino}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Diplomado en:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">
								<s:if test="pyMEs.bDiplomadoCcmxTres==true">
									Desarrollo de Métodos de Producción Esbeltos y Cadena de Valor
								</s:if>
							</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen" colspan="2">&nbsp;</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Cédula:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.cedula}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Diplomado en:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">
								<s:if test="pyMEs.bDiplomadoCcmxCuatro==true">
									Estrategia, Planeación e Innovación
								</s:if>
							</td>
						</tr>
						<s:if test="pyMEs.certificaciones[1].certificacion != null">
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Certificación:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[1].certificacion}</td>
								<td colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Año:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[1].fechaCertificacion}</td>
								<td colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Institución:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[1].institutoCertificador}</td>
								<td colspan="4">&nbsp;</td>
							</tr>
						</s:if>
						<s:if test="pyMEs.certificaciones[2].certificacion != null">
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Certificación:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[2].certificacion}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Año:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[2].fechaCertificacion}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Institución:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[2].institutoCertificador}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
						</s:if>
						<s:if test="pyMEs.certificaciones[3].certificacion != null">
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Certificación:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[3].certificacion}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Año:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[3].fechaCertificacion}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Institución:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[3].institutoCertificador}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
						</s:if>
						<s:if test="pyMEs.certificaciones[4].certificacion != null">
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Certificación:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[4].certificacion}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Año:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[4].fechaCertificacion}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Institución:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[4].institutoCertificador}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
						</s:if>
					</table>
				</td>
			</tr>			
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Conozca más sobre nuestros productos y sobre nuestra empresa</td>
			</tr>
			<s:if test="pyMEs.idArchivo1!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo1}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo1}&nameArchivo=${pyMEs.archivo1FileName}&mimeArchivo=${pyMEs.archivo1ContentType}">${pyMEs.archivo1FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo2!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo2}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo2}&nameArchivo=${pyMEs.archivo2FileName}&mimeArchivo=${pyMEs.archivo2ContentType}">${pyMEs.archivo2FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo3!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo3}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo3}&nameArchivo=${pyMEs.archivo3FileName}&mimeArchivo=${pyMEs.archivo3ContentType}">${pyMEs.archivo3FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo4!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo4}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo4}&nameArchivo=${pyMEs.archivo4FileName}&mimeArchivo=${pyMEs.archivo4ContentType}">${pyMEs.archivo4FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo5!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo5}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo5}&nameArchivo=${pyMEs.archivo5FileName}&mimeArchivo=${pyMEs.archivo5ContentType}">${pyMEs.archivo5FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo6!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo6}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo6}&nameArchivo=${pyMEs.archivo6FileName}&mimeArchivo=${pyMEs.archivo6ContentType}">${pyMEs.archivo6FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo7!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo7}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo7}&nameArchivo=${pyMEs.archivo7FileName}&mimeArchivo=${pyMEs.archivo7ContentType}">${pyMEs.archivo7FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo8!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo8}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo8}&nameArchivo=${pyMEs.archivo8FileName}&mimeArchivo=${pyMEs.archivo8ContentType}">${pyMEs.archivo8FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo9!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo9}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo9}&nameArchivo=${pyMEs.archivo9FileName}&mimeArchivo=${pyMEs.archivo9ContentType}">${pyMEs.archivo9FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo10!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo10}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo10}&nameArchivo=${pyMEs.archivo10FileName}&mimeArchivo=${pyMEs.archivo10ContentType}">${pyMEs.archivo10FileName}</a>
					</td>
				</tr>
			</s:if>
			<tr>
				<td class="cuerpo1TextoResumen" colspan="2" align="center"><s:label cssClass="etiquetaResumen">${pyMEs.paginaWeb}</s:label></td>
			</tr>

			<!--<s:iterator value="tractoras.telefonos" status="stat">
				<tr>
					<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="left">&nbsp;Teléfono ${stat.count}:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${telefono}</s:label></td>
				</tr>
			</s:iterator>-->
			
		</table>
		<table class="submit_tabla">
			<tr>
				<td style="width: 450px;"></td>
				<td><input
					class="botonenviar"
					value="Regresar"
					type="button"
					onclick="javascript: window.history.back();" /></td>
				<td style="width: 450px;"></td>
			</tr>
		</table>
		<table>
		</table>
	</div>
</fieldset>
</body>
</html>
