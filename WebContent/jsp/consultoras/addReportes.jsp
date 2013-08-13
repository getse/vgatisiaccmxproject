<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="${pageContext.request.contextPath}/js/reportesRestricciones.js" type="text/javascript"></script>
<script type="text/javascript">
document.getElementById('workingContainer').style.margin = '-180px auto 0 250px';</script>
</head>
<body>
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
					<tr>
						<td align="center" class="cuerpo2TablaResumen">
							<s:form
								name="reporte3"
								action="consultorReportesShow"
								namespace="/consultor"
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
								action="consultorReportesShow"
								namespace="/consultor"
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
	<div id="pymes" ${opcion!=null && opcion=='pymes'?' style="display: block;"':' style="display: none;"'}>
		<fieldset id="requerimientos">
			<legend>
				<s:label value="Reporte de indicadores público" />
				<br /> <br />
			</legend>
			<s:form
				name="pymesReport"
				action="consultorReportesShow"
				namespace="/consultor"
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
						<td style="width: 280px;height:30px;"><s:checkbox id="checEstatusPy" name=""
								onclick="javascript:showSelect('checEstatusPy','estatusPy');"/>
								<s:label
									cssClass="etiquetaCaptura" value="Estatus de la consultoría" /></td>
						<td style="width: 180px;">
									<select id="estatusPy" name="filtros.estatus" style="display:none;">
										<option value="" selected="selected">--Seleccionar--</option>
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
                                value="Reporte de PyMEs"
                                type="button"
                                onclick="javascript:menuReporte(11);"/></td>
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
				action="consultorReportesShow"
				namespace="/consultor"
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
                                onclick="javascript:menuReporte(14);"/></td>
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
		<s:label cssClass="etiquetaCaptura" value="Reporte" />
		
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