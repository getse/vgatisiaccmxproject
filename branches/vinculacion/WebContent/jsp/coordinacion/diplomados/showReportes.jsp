<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-149px auto 0 250px';</script>
</head>
<body >
	<div id="reportes" ${opcion==null?' style="display: block;"':' style="display: none;"'}>
		<fieldset id="requerimientos" >
			<legend>
				<s:label value="Generar reportes" />
				<br /> <br />
				<s:label
				cssClass="camposObligatorios"
				value="Clic sobre el reporte deseado." />
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
								action="coordinadorDiplomadosReportesShow"
								namespace="/diplomados/coordinacion"
								method="post"
								theme="simple">
						<s:hidden name="opcion" value="finanzas"></s:hidden>
						<input
						 		id="reporte1"
                                class="reporte1"
                                value="Estatus financiero de PYMES en diplomados"
                                type="button"
                                style="width: 240px;"
                                onclick="javascript:document.reporte1.submit();" />	
						</s:form></td>
					</tr>
					<tr></tr>
					<tr>
						<td align="center" class="cuerpo1TablaResumen">
						<s:form
								name="reporte2"
								action="coordinadorDiplomadosReportesShow"
								namespace="/diplomados/coordinacion"
								method="post"
								theme="simple">
						<s:hidden name="opcion" value="pymes"></s:hidden>
						</s:form>
						<input
						 		id="reporte2"
                                class="reporte2"
                                value="PYMES en Diplomados"
                                type="button"
                                style="width: 240px;"
                                onclick="javascript:document.reporte2.submit();" /></td>
					</tr>
				</table>		
		</fieldset>
	</div>
	<div id="pymes" ${opcion!=null && opcion=='pymes'?' style="display: block;"':' style="display: none;"'}>
		<fieldset id="requerimientos">
			<legend>
				<s:label value="PYMES en Diplomados" />
				<br /> <br />
			</legend>
			<s:form
				name="pymesReport"
				action="coordinadorDiplomadosReportesShow"
				namespace="/diplomados/coordinacion"
				method="post"
				theme="simple" >
				<table >
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checTractora" name=""
									onclick="javascript:showSelect('checTractora','tractora');"/>
									 <s:label
									cssClass="etiquetaCaptura" value="Tractora" /></td>
						<td style="width: 180px;">
									<select name="idTractora" id="tractora" style="display:none;">
										<option value="-1">--Seleccionar--</option>
										<s:iterator value="listTractoras" status="stat">
											<option value="${idUsuario}">${empresa}</option>
										</s:iterator>
									</select></td>
					</tr>
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checPyme" name="checPyme"
							onclick="javascript:showSelect('checPyme','pyme');"/>
							<s:label cssClass="etiquetaCaptura" value="PYME" /></td>
						<td style="width: 180px;">
									<select name="idUsuario" id="pyme" style="display:none;">
										<option value="-1">--Seleccionar--</option>
										<s:iterator value="listPymes" status="stat">
											<option value="${idUsuario}">${nombreComercial}</option>
										</s:iterator>
									</select></td>
					</tr>
					<tr >
						<td style="width: 280px;height:30px;"><s:checkbox id="checGeneracion" name=""
								onclick="javascript:showSelect('checGeneracion','generacion');"/>
								<s:label
									cssClass="etiquetaCaptura" value="Generación" /></td>
						<td style="width: 180px;">
									<select id="generacion" name="generacion" style="display:none;">
										<option value="0" selected="selected">--Seleccionar--</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
									</select></td>
					</tr>
					<tr>
					<td style="width: 250px;"></td>
						<td><s:hidden name="opcion" value="pyRepor"></s:hidden>
						<input
						 		id="pyReport"
                                class="pyReport"
                                value="Reporte"
                                type="button"
                                onclick="javascript:reportePymes();"/></td>
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
	<script type="text/javascript">
	function showSelect(val1,val2){
		if(document.getElementById(val1).checked==true){
			document.getElementById(val2).style.display='block';
		}
		else{
			document.getElementById(val2).value=-1;
			document.getElementById(val2).style.display='none';
		}
	}
	function reportePymes(){
		var temp = true;
		if(document.getElementById("checTractora").checked==true){
			if(document.getElementById("tractora").value==-1){
				document.getElementById("tractora").focus();
				alert("Seleccione empresa tractora.");
				temp=false;
			}
		}
		if(document.getElementById("checPyme").checked==true && temp){
			if(document.getElementById("pyme").value==-1){
				document.getElementById("pyme").focus();
				alert("Seleccione PYME.");
				temp=false;
			}
		}
		if(document.getElementById("checGeneracion").checked==true && temp){
			if(document.getElementById("generacion").value==0){
				document.getElementById("generacion").focus();
				alert("Seleccione una generación.");
				temp=false;
			}
		}
		if(temp){
			document.pymesReport.submit();
		}			
	}
	</script>
	</body>
</html>