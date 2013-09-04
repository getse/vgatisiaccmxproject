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
	document.getElementById('workingContainer').style.margin = '-250px auto 0 250px';
</script>
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
						<td style="width:300 px" class="encabezado_tabla" colspan="2" align="center">Reportes</td>
					</tr>
					<tr >
						<td align="center" class="cuerpo2TablaResumen">	
						<s:form
								name="reporte1"
								action="compradorReportesShow"
								namespace="/"
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
					<tr>
						<td align="center" class="cuerpo1TablaResumen">
							<s:form
								name="reporte3"
								action="compradorReportesShow"
								namespace="/"
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
                                style="width: 250px;"
                                onclick="javascript:menuReporte(3);" /></td>
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
				action="compradorReportesShow"
				namespace="/"
				method="post"
				theme="simple">
			<table>
					<!-- tr >
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
					</tr-->
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
                                onclick="javascript:menuReporte(4);"/></td>
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
				action="compradorReportesShow"
				namespace="/"
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
					<!-- tr >
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
					</tr-->
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
                                value="Reporte"
                                type="button"
                                onclick="javascript:menuReporte(10);"/></td>
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