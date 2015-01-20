<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="${pageContext.request.contextPath}/js/reportes.js" type="text/javascript"></script>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-135px auto 0 250px';</script>
</head>
<body >
	<div id="reportes" ${opcion==null?' style="display: block;"':' style="display: none;"'}>
		<fieldset id="requerimientos" >
			<legend>
				<s:label value="Generar reportes" />
				<br /> <br />
				<s:label
				cssClass="camposObligatorios"
				value="Seleccione el reporte que desea consultar." />
			</legend>
			<br />		
				<table align="center">
					<tr >
						<td  class="encabezado_tabla" align="center">Reportes</td>
					</tr>
					<tr >
						<td align="center" class="cuerpo2TablaResumen">	
						<s:form
								name="reporte1"
								action="coordinadorConsultoriasReportesShow"
								namespace="/consultorias/coordinacion"
								method="post"
								theme="simple">
						<s:hidden name="opcion" value="servicios"></s:hidden>
						<input
								class="botonenviar"
						 		id="reporte1"
                                class="reporte1"
                                value="Participación en los Servicios CCMX"
                                type="button"
                                style="width: 260px;"
                                onclick="javascript:menuReporte(1);" />	
						</s:form></td>
					</tr>
					<tr></tr>
					<tr>
						<td align="center" class="cuerpo1TablaResumen">
						<s:form
								name="reporte2"
								action="coordinadorConsultoriasReportesShow"
								namespace="/consultorias/coordinacion"
								method="post"
								theme="simple">
						<s:hidden name="opcion" value="finanzas"></s:hidden>
						</s:form>
						<input
								class="botonenviar"
						 		id="reporte2"
                                class="reporte2"
                                value="Estatus Financiero CCMX"
                                type="button"
                                style="width: 240px;"
                                onclick="javascript:menuReporte(2);" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td align="center" class="cuerpo2TablaResumen">
							<s:form
								name="reporte3"
								action="coordinadorConsultoriasReportesShow"
								namespace="/consultorias/coordinacion"
								method="post"
								theme="simple">
						<s:hidden name="opcion" value="pymes"></s:hidden>
						</s:form>
						<input
								class="botonenviar"
						 		id="reporte3"
                                class="reporte3"
                                value="Indicadores público"
                                type="button"
                                style="width: 240px;"
                                onclick="javascript:menuReporte(3);" /></td>
					</tr>
					<tr>
						<td align="center" class="cuerpo1TablaResumen" align="left">
						<s:form
								name="reporte4"
								action="coordinadorConsultoriasReportesShow"
								namespace="/consultorias/coordinacion"
								method="post"
								theme="simple">
						<s:hidden name="opcion" value="indicadores"></s:hidden>
						</s:form>
						<input
								class="botonenviar"
						 		id="reporte4"
                                class="reporte4"
                                value="Indicadores privado"
                                type="button"
                                style="width: 240px;"
                                onclick="javascript:menuReporte(12);" /></td>
					</tr>
				</table>		
		</fieldset>
	</div>
	<div id="ccmxservicos" ${opcion!=null && opcion=='servicios' ? ' style="display: block;"':' style="display: none;"'}>
		<fieldset id="requerimientos">
			<legend>
				<s:label value="Participación en los Servicios CCMX" />
				<br /> <br />
			</legend>
			<s:form
				name="serviciosReport"
				action="coordinadorConsultoriasReportesShow"
				namespace="/consultorias/coordinacion"
				method="post"
				theme="simple">
			<table>				
					<tr >
						<td style="width: 280px;height:30px;'"><s:checkbox id="checTractoraServ" name="checTractoraServ"
						onclick="javascript:showSelect('checTractoraServ','tractoraServ');"/> 
								<s:label cssClass="etiquetaCaptura" value="Grandes Empresas" /></td>
						<td style="width: 180px;">
									<select name="filtros.filtro1" id="tractoraServ" style="display:none;">
										<option value="-1">--Seleccionar--</option>
										<s:iterator value="tractorasList" status="stat">
											<option value="${idUsuario}">${empresa}</option>
										</s:iterator>
									</select></td>
					</tr>
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checConsultoraServ" name="checTractoraServ"
							onclick="javascript:showSelect('checConsultoraServ','consultoraServ');"/>
							<s:label cssClass="etiquetaCaptura" value="Empresa consultora" /></td>
						<td style="width: 180px;">
									<select name="filtros.filtro2" id="consultoraServ" style="display:none;">
										<option value="-1">--Seleccionar--</option>
										<s:iterator value="consultorasList" status="stat">
											<option value="${idUsuario}">${empresa}</option>
										</s:iterator>
									</select></td>
					</tr>
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checSesionServ" name=""
						onclick="javascript:showSelect('checSesionServ','sesionServ');"/>
							<s:label cssClass="etiquetaCaptura" value="Sesión informativa" /></td>
						<td style="width: 180px;">
									<select name="filtros.sesionInformativa" id="sesionServ" style="display:none;">
										<option value="">--Seleccionar--</option>
										<s:iterator value="sesionInformativa" status="stat">
											<s:if test="%{campoString!=null}">
												<option value="${campoString}">${campoString}</option>
											</s:if>
										</s:iterator>
									</select></td>
					</tr>
					<tr>
					<td style="width: 250px;"></td>
						<td><s:hidden name="opcion" value="servRepor"></s:hidden>
						<input
								class="botonenviar"
						 		id="servReport"
                                class="servReport"
                                value="Reporte de Servicios"
                                type="button"
                                onclick="javascript:menuReporte(14);"/></td>
					</tr>
				</table>
			</s:form>
		</fieldset>
	</div>
	<div id="ccmxfinanzas" ${opcion!=null && opcion=='finanzas'?' style="display: block;"':' style="display: none;"'}>
		<fieldset id="requerimientos">
			<legend>
				<s:label value="Estatus Financiero CCMX" />
				<br /> <br />
			</legend>
			<s:form
				name="finanzasReport"
				action="coordinadorConsultoriasReportesShow"
				namespace="/consultorias/coordinacion"
				method="post"
				theme="simple">
				<table >
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checConsultoraFin" name="checTractoraFin"
							onclick="javascript:showSelect('checConsultoraFin','consultoraFin');"/>
							<s:label cssClass="etiquetaCaptura" value="Empresa consultora" /></td>
						<td style="width: 180px;">
									<select name="filtros.filtro1" id="consultoraFin" style="display:none;">
										<option value="-1">--Seleccionar--</option>
										<s:iterator value="consultorasList" status="stat">
											<option value="${idConsultora}">${empresa}</option>
										</s:iterator>
									</select></td>
					</tr>
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checAnticipoFin" name="checTractoraFin"
						onclick="javascript:showSelect('checAnticipoFin','anticipoFin');"/> 
							<s:label cssClass="etiquetaCaptura" value="Pago de anticipo" /></td>
						<td style="width: 180px;">
								<select name="filtros.filtro2" id="anticipoFin" style="display:none;">
									<option value="-1" selected="selected">--Seleccionar--</option>
										<s:iterator value="menuAnticipo" status="stat">
											<option value="${id}">${campoString}</option>
										</s:iterator>
								</select></td>
					</tr>				
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checFiniquitoFin" name=""
									onclick="javascript:showSelect('checFiniquitoFin','finiquitoFin');"/> 
								<s:label
									cssClass="etiquetaCaptura" value="Pago de finiquito" /></td>
						<td style="width: 180px;">
									<select id="finiquitoFin" name="filtros.filtro3" style="display:none;">
										<option value="-1" selected="selected">--Seleccionar--</option>
										<s:iterator value="menuFiniquito" status="stat">
											<option value="${id}">${campoString}</option>
										</s:iterator>
									</select></td>
					</tr>
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="cAnticipofiniquitoFin" name=""
						onclick="javascript:showSelect('cAnticipofiniquitoFin','anticipofiniquitoFin');"/>  
							<s:label cssClass="etiquetaCaptura" value="Pago de anticipo y finiquito" /></td>
						<td style="width: 180px;">
								<select name="filtros.filtro4" id="anticipofiniquitoFin" style="display:none;">
									<option value="-1" selected="selected">--Seleccionar--</option>
									<s:iterator value="menuAnticipoFiniquito" status="stat">
										<option value="${id}">${campoString}</option>
									</s:iterator>
								</select></td>
					</tr>
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checTipoFin" name=""
							onclick="javascript:showSelect('checTipoFin','tipoFin');"/>  
									<s:label
									cssClass="etiquetaCaptura" value="Tipo de consultoría" /></td>
						<td style="width: 180px;">
									<select id="tipoFin" name="filtros.filtro5" style="display:none;">
										<option value="-1">--Seleccionar--</option>
										<option value="20">20 Horas</option>
										<option value="40">40 Horas</option>
										<option value="60">60 Horas</option>
										<option value="80">80 Horas</option>
									</select></td>
					</tr>
					<tr>
						<td style="width: 250px;"></td>
						<td><s:hidden name="opcion" value="finRepor"></s:hidden>
						<input
								class="botonenviar"
						 		id="finReport"
                                class="finReport"
                                value="Reporte de Finanzas"
                                type="button"
                                onclick="javascript:menuReporte(5);"/></td>
					</tr>
				</table>
			</s:form>
		</fieldset>
	</div>
	<div id="pymes" ${opcion!=null && opcion=='pymes'?' style="display: block;"':' style="display: none;"'}>
		<fieldset id="requerimientos">
			<legend>
				<s:label value="Reporte de indicadores público" />
				<br /> <br />
			</legend>
			<s:form
				name="pymesReport"
				action="coordinadorConsultoriasReportesShow"
				namespace="/consultorias/coordinacion"
				method="post"
				theme="simple" >
				<table >
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checCedulaPy" name=""
									onclick="javascript:showSelect('checCedulaPy','cedulaPy');"/>
									 <s:label
									cssClass="etiquetaCaptura" value="Cédula" /></td>
						<td style="width: 180px;">
									<select name="filtros.filtro1" id="cedulaPy" style="display:none;">
										<option value="-1">--Seleccionar--</option>
										<s:iterator value="menuCedula" status="stat">
											<option value="${campoString}">${campoString}</option>
										</s:iterator>
									</select></td>
					</tr>
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checConsultoraPy" name="checTractoraPy"
							onclick="javascript:showSelect('checConsultoraPy','consultoraPy');"/>
							<s:label cssClass="etiquetaCaptura" value="Consultor" /></td>
						<td style="width: 180px;">
									<select name="filtros.filtro2" id="consultoraPy" style="display:none;">
										<option value="-1">--Seleccionar--</option>
										<s:iterator value="consultorasList" status="stat">
											<option value="${idConsultora}">${nombreContacto} ${appPaternoContacto} ${appMaternoContacto}</option>
										</s:iterator>
									</select></td>
					</tr>
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checEstatusPy" name=""
								onclick="javascript:showSelect('checEstatusPy','estatusPy');"/>
								<s:label
									cssClass="etiquetaCaptura" value="Estatus de la consultoría" /></td>
						<td style="width: 180px;">
									<select id="estatusPy" name="filtros.estatus" style="display:none;">
										<option value="-1" selected="selected">--Seleccionar--</option>
										<s:iterator value="menuEstatus" status="stat">
											<option value="${campoString}">${campoString}</option>
										</s:iterator>
									</select></td>
					</tr>
					<tr>
					<td style="width: 250px;"></td>
						<td><s:hidden name="opcion" value="pyRepor"></s:hidden>
						<input
								class="botonenviar"
						 		id="pyReport"
                                class="pyReport"
                                value="Reporte"
                                type="button"
                                onclick="javascript:menuReporte(4);"/></td>
					</tr>
				</table>
			</s:form>
		</fieldset>
	</div>
	<div id="indicadores" ${opcion!=null && opcion=='indicadores'?' style="display: block;"':' style="display: none;"'}>
		<fieldset id="requerimientos">
			<legend>
				<s:label value="Reporte de Indicadores privado" />
				<br /> <br />
			<s:label
				cssClass="camposObligatorios"
				value="Seleccione el filtro y un valor correspondiente, de lo contrario deje vacías las opciones." />
			</legend>
			<s:form
				name="indicadoresReport"
				action="coordinadorConsultoriasReportesShow"
				namespace="/consultorias/coordinacion"
				method="post"
				theme="simple" >
				<table >
					
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checCedulaIn" name=""
									onclick="javascript:showSelect('checCedulaIn','cedulaIn');"/>
									 <s:label
									cssClass="etiquetaCaptura" value="Cédula" /></td>
						<td style="width: 180px;">
									<select name="filtros.cedula" id="cedulaIn" style="display:none;">
										<option value="" selected="selected">--Seleccionar--</option>
										<s:iterator value="menuCedula" status="stat">
											<option value="${campoString}">${campoString}</option>
										</s:iterator>
									</select></td>
					</tr>
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checConsultoraIn" name="checTractoraIn"
							onclick="javascript:showSelect('checConsultoraIn','consultoraIn');"/>
							<s:label cssClass="etiquetaCaptura" value="Consultor" /></td>
						<td style="width: 180px;">
									<select name="filtros.filtro2" id="consultoraIn" style="display:none;">
										<option value="-1">--Seleccionar--</option>
										<s:iterator value="consultorasList" status="stat">
											<option value="${idConsultora}">${nombreContacto} ${appPaternoContacto} ${appMaternoContacto}</option>
										</s:iterator>
									</select></td>
					</tr>
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checEstatusIn" name=""
								onclick="javascript:showSelect('checEstatusIn','estatusIn');"/>
								<s:label
									cssClass="etiquetaCaptura" value="Estatus de la consultoría" /></td>
						<td style="width: 180px;">
									<select id="estatusIn" name="filtros.estatus" style="display:none;">
										<option value="" selected="selected">--Seleccionar--</option>
										<s:iterator value="menuEstatus" status="stat">
											<option value="${campoString}">${campoString}</option>
										</s:iterator>
									</select></td>
					</tr>
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checAnticipoIn" name="checTractoraIn"
						onclick="javascript:showSelect('checAnticipoIn','anticipoIn');"/> 
							<s:label cssClass="etiquetaCaptura" value="Pago de anticipo" /></td>
						<td style="width: 180px;">
								<select name="filtros.filtro4" id="anticipoIn" style="display:none;">
									<option value="-1" selected="selected">--Seleccionar--</option>
										<s:iterator value="menuAnticipo" status="stat">
											<option value="${id}">${campoString}</option>
										</s:iterator>
								</select></td>
					</tr>
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="cAnticipofiniquitoIn" name=""
						onclick="javascript:showSelect('cAnticipofiniquitoIn','anticipofiniquitoIn');" />  
							<s:label cssClass="etiquetaCaptura" value="Pago de anticipo y finiquito" /></td>
						<td style="width: 180px;">
								<select name="filtros.filtro5" id="anticipofiniquitoIn" style="display:none;">
									<option value="-1" selected="selected">--Seleccionar--</option>
										<s:iterator value="menuAnticipoFiniquito" status="stat">
											<option value="${id}">${campoString}</option>
										</s:iterator>
								</select></td>
					</tr>
					<tr>
					<td style="width: 250px;"></td>
						<td><s:hidden name="opcion" value="inRepor"></s:hidden>
						<input
								class="botonenviar"
						 		id="InReport"
                                class="inReport"
                                value="Generar reporte"
                                type="button"
                                onclick="javascript:menuReporte(13);"/></td>
					</tr>
				</table>
			</s:form>
		</fieldset>
	</div>
	<div id="descargas" ${opcion!=null && opcion=='descarga'?' style="display: block;"':' style="display: none;"'}>
		<fieldset id="requerimientos">
		<legend>
			<s:label value="Reporte finalizado" />
			<br /> <br />
		</legend>
		
		<s:if test="salida==null">				
			<br/>
				<a href="${pageContext.request.contextPath}/downDoc.do" >Descargar Archivo</a>
		</s:if>
		<s:else>
				<s:property value="%{salida}"/>
		</s:else>
		</fieldset>
	</div>
	</body>
</html>