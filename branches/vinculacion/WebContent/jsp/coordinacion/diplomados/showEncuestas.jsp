<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/css/calendario.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-185px auto 0 250px';
</script>
<script src="${pageContext.request.contextPath}/js/ayudas.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar-es.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar-setup.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
</head>
<body>
	<s:if test="mensaje!=null">
		<br />
		<table class="nota">
			<tr>
				<td class="imgNota"><s:if test="mensaje.respuesta==0">
						<img
							src="${pageContext.request.contextPath}/img/palomitaverde.gif" />
					</s:if> <s:else>
						<img src="${pageContext.request.contextPath}/img/warning.png" />
					</s:else>
				</td>
				<td class="contenidoNota"><s:property value="mensaje.mensaje" />
				</td>
			</tr>
		</table>
	</s:if>
	<fieldset id="requerimientos">
		<div ${listDiplomados!=null ? ' style="display: block;"
			' :' style="display: none;"' }>
			<legend>
				<s:label value="Encuestas" />
				<br /> <br />
			</legend>
			<br />
			<table width="99%">
					<tr>
						<td style="width: 100%'" align="center">
							<select id="menuAnios" name="menuAnios" onchange="javascript: MenuDiplomadoAnio()">
									<s:iterator value="menuAnios" status="stat">
										<option value="${menuAnios[stat.index]}">${menuAnios[stat.index]}</option>
									</s:iterator>
							</select>			
						</td>
					</tr>
				</table>
				<s:iterator value="listDiplomados" status="stat" var="recor">
				<table width="99%" cellspacing="1" cellpadding="1">
					<tr>
						<td class="encabezadoTablaResumen" align="center"><b>Generación
								${stat.count}</b>
						</td>
					</tr>
				</table>
					<s:iterator value="recor" status="cont">
						<div style="float: left; width: 49%; text-align: center;"
							class="cuerpo1TablaResumen">
							<a href="${pageContext.request.contextPath}/diplomados/coordinacion/coordinadorDiplomadosEncuestasShow.do?idDiplomado=${recor[cont.index].idDiplomado}">${recor[cont.index].tema}</a>						
						</div>
					</s:iterator>
				</s:iterator>
		</div>
		<div ${( idPyme== 0 && listParticipantes!=null
			)  ? ' style="display: block;" ' :' style="display: none;"' } >
			<s:hidden name="idDiplomado" value="%{idDiplomado}" />
			<s:hidden name="idPyme" id="idPyme" value="-1" />
			<legend>
				<s:label value="Encuestas." />
				<br /> <br />
			</legend>
			<br />
			<table width="900px" >
				<tr>
					<td>
						<table width="99%" cellspacing="1" cellpadding="1">
							<thead>
								<tr>
									<td class="encabezado_tabla" align="center"><b>No.</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>PYME </b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Nombre
											del<br />Participante </b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Telefono
									</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Correo<br />electrónico
									</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Cargo </b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Tractora
									</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Sesion1
									</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Sesion2
									</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Sesion3
									</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Sesion4
									</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Factura
									</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Pago </b>
									</td>
									<td class="encabezado_tabla" align="center"><b>No. de
											pago </b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Encuestas
									</b>
									</td>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="listParticipantes" status="stat">
									<tr>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${stat.count}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${pyme}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${nombre}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${telefono}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${correoElectronico}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${cargo}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${tractora}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center"><s:if test="%{asistencia1}">
														&bull;
													</s:if> <s:else>
														&nbsp;
													</s:else>
										</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center"><s:if test="%{asistencia2}">
														&bull;
													</s:if> <s:else>
														&nbsp;
													</s:else>
										</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center"><s:if test="%{asistencia3}">
														&bull;
													</s:if> <s:else>
														&nbsp;
													</s:else>
										</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center"><s:if test="%{asistencia4}">
														&bull;
													</s:if> <s:else>
														&nbsp;
													</s:else>
										</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center"><s:if test="%{pago}">
														&bull;
													</s:if> <s:else>
														&nbsp;
													</s:else>
										</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center"><s:if test="%{factura}">
														&bull;
													</s:if> <s:else>
														&nbsp;
													</s:else>
										</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${numPago}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center"><a
											href="${pageContext.request.contextPath}/diplomados/coordinacion/coordinadorDiplomadosEncuestasShow.do?idAsistente=${id}&idDiplomado=${idDiplomado}">Capturar</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table></td>
				</tr>
			</table>
		</div>
		<div ${listSesiones!=null ? ' style="display: block;"
			' :' style="display: none;"' }>
			<legend>
				<s:label value="Encuesta." />
				<br /> <br />
			</legend>
			<s:if test="listSesiones[0].idSesion>0">
				<s:form action="coordinadorDiplomadosEncuestasShow"
				namespace="/diplomados/coordinacion" theme="simple">
				<s:hidden name="idAsistente"  value="%{idAsistente}" />
				<s:hidden name="numeroSesiones" value="%{listSesiones[0].idSesion}" />
				<table id="tlbSolFactura" width="99%">
					<tbody style="width: 100%;"><tr>
						<td align="center" style="width: 100%;"><s:submit cssClass="botonenviar" align="left"
										value="Sesión 1" /></td>
					</tr></tbody>
				</table>
				</s:form>
			</s:if>
			<s:if test="listSesiones[1].idSesion>0">
				<s:form action="coordinadorDiplomadosEncuestasShow"
				namespace="/diplomados/coordinacion" theme="simple">
				<s:hidden name="idAsistente" value="%{idAsistente}" />
				<s:hidden name="numeroSesiones" value="%{listSesiones[1].idSesion}" />
				<table id="tlbSolFactura" width="99%">
					<tbody style="width: 100%;"><tr>
						<td align="center" style="width: 100%;"><s:submit cssClass="botonenviar" align="left"
										value="Sesión 2" /></td>
					</tr></tbody>
				</table>
				</s:form>		
			</s:if>
			<s:if test="listSesiones[2].idSesion>0">
				<s:form action="coordinadorDiplomadosEncuestasShow"
				namespace="/diplomados/coordinacion" theme="simple">
				<s:hidden name="idAsistente" value="%{idAsistente}" />
				<s:hidden name="numeroSesiones" value="%{listSesiones[2].idSesion}" />
				<table id="tlbSolFactura" width="99%">
					<tbody style="width: 100%;"><tr>
						<td align="center" style="width: 100%;"><s:submit cssClass="botonenviar" align="left"
										value="Sesión 3" /></td>
					</tr></tbody>
				</table>
				</s:form>			
			</s:if>
			<s:if test="listSesiones[3].idSesion>0">
				<s:form action="coordinadorDiplomadosEncuestasShow"
				namespace="/diplomados/coordinacion" theme="simple">
				<s:hidden name="idAsistente" value="%{idAsistente}" />
				<s:hidden name="numeroSesiones" value="%{listSesiones[3].idSesion}" />
				<table id="tlbSolFactura" width="99%">
					<tbody style="width: 100%;"><tr>
						<td align="center" style="width: 100%;"><s:submit cssClass="botonenviar" align="left"
										value="Sesión 4" /></td>
					</tr></tbody>
				</table>
				</s:form>
			</s:if>
		</div>
		<div ${encuesta!=null ? ' style="display: block;"
			' :' style="display: none;"' }>
			<s:form name="busqueda" action="coordinadorDiplomadosEncuestasShow"
				namespace="/diplomados/coordinacion" theme="simple"
				onsubmit="return validarEncuesta()">
				<s:hidden name="encuesta.idAsistente" value="%{encuesta.idAsistente}"></s:hidden>
				<s:hidden name="encuesta.idSesion" value="%{encuesta.idSesion}"></s:hidden>
			<legend>
				<s:label value="Encuesta." />
				<br /> <br />
			</legend>
			<table>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
						value="1.- ¿El diplomado cumplió con tus expectativas?" />
					</td>
				</tr>
				<tr>
					<td><s:textfield size="4" maxLength="4" id="respuesta1"
							name="encuesta.respuesta1" value="%{encuesta.respuesta1}"
							onfocus="javascript:ayudasHelp(1);"
							onblur="javascript:ayudasHelpBlo(1);" 
							onkeypress="javascript:return validaNumero(event)"/></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay1"
						style="display:none;margin-top:-1px;"
						value="Ingrese una calificación." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
						value="2.- ¿El diplomado te proporciono herramientas que te permitirán poner en práctica los conceptos aprendidos?" />
					</td>
				</tr>
				<tr>
					<td><s:textfield size="4" maxLength="4" id="respuesta2"
							name="encuesta.respuesta2" value="%{encuesta.respuesta2}"
							onfocus="javascript:ayudasHelp(2);"
							onblur="javascript:ayudasHelpBlo(2);" 
							onkeypress="javascript:return validaNumero(event)"/></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay2"
						style="display:none;margin-top:-1px;"
						value="Ingrese una calificación." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
						value="3.- ¿De lo visto en el diplomado que podrías aplicar de manera inmediata o 
							en el corto plazo en tu negocio?" />
					</td>
				</tr>
				<tr>
					<td><s:textarea cols="100" rows="2" maxLength="400" id="respuesta3"
							name="encuesta.respuesta3" value="%{encuesta.respuesta3}"
							onfocus="javascript:ayudasHelp(3);"
							onblur="javascript:ayudasHelpBlo(3);"/></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay3"
						style="display:none;margin-top:-1px;"
						value="Ingrese información sobre el diplomado." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
						value="4.- ¿Cuál es el principal aprendizaje que te llevas del diplomado?" />
					</td>
				</tr>
				<tr>
					<td><s:textarea cols="100" rows="2" maxLength="400" id="respuesta4"
							name="encuesta.respuesta4" value="%{encuesta.respuesta4}"
							onfocus="javascript:ayudasHelp(4);"
							onblur="javascript:ayudasHelpBlo(4);" /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay4"
						style="display:none;margin-top:-1px;"
						value="Ingrese información sobre el diplomado." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
						value="5.- ¿Los materiales proporcionados son adecuados?" />
					</td>
				</tr>
				<tr>
					<td><s:textfield size="4" maxLength="4" id="respuesta5"
							name="encuesta.respuesta5" value="%{encuesta.respuesta5}"
							onfocus="javascript:ayudasHelp(5);"
							onblur="javascript:ayudasHelpBlo(5);" 
							onkeypress="javascript:return validaNumero(event)"/></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay5"
						style="display:none;margin-top:-1px;"
						value="Ingrese calificación." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
						value="6.- ¿Cómo calificas los conocimientos y habilidad del expositor para transmitir el tema?" />
					</td>
				</tr>
				<tr>
					<td><s:textfield size="4" maxLength="4" id="respuesta6"
							name="encuesta.respuesta6" value="%{encuesta.respuesta6}"
							onfocus="javascript:ayudasHelp(6);"
							onblur="javascript:ayudasHelpBlo(6);"
							onkeypress="javascript:return validaNumero(event)" /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay6"
						style="display:none;margin-top:-1px;"
						value="Ingrese una calificación." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
						value="7.- ¿Qué fue lo que más te gusto del diplomado?" />
					</td>
				</tr>
				<tr>
					<td><s:textarea cols="100" rows="2" maxLength="400" id="respuesta7"
							name="encuesta.respuesta7" value="%{encuesta.respuesta7}"
							onfocus="javascript:ayudasHelp(7);"
							onblur="javascript:ayudasHelpBlo(7);" /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay7"
						style="display:none;margin-top:-1px;"
						value="Ingrese información sobre el diplomado." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
						value="8.- ¿Qué fue lo que menos te gusto de este diplomado?" />
					</td>
				</tr>
				<tr>
					<td><s:textarea cols="100" rows="2" maxLength="400" id="respuesta8"
							name="encuesta.respuesta8" value="%{encuesta.respuesta8}"
							onfocus="javascript:ayudasHelp(8);"
							onblur="javascript:ayudasHelpBlo(8);" /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay8"
						style="display:none;margin-top:-1px;"
						value="Ingrese información sobre el diplomado." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
						value="9.- ¿Qué sugerencias nos harías para mejorar el diplomado?" />
					</td>
				</tr>
				<tr>
					<td><s:textarea cols="100" rows="2" maxLength="400" id="respuesta9"
							name="encuesta.respuesta9" value="%{encuesta.respuesta9}"
							onfocus="javascript:ayudasHelp(9);"
							onblur="javascript:ayudasHelpBlo(9);" /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay9"
						style="display:none;margin-top:-1px;"
						value="Ingrese información sobre el diplomado." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
						value="Puntos de Retroalimentación:" />
					</td>
				</tr>
				<tr>
					<td><s:textarea cols="100" rows="2" maxLength="400" id="retroalimenacion"
							name="encuesta.retroalimenacion" value="%{encuesta.retroalimenacion}"
							onfocus="javascript:ayudasHelp(10);"
							onblur="javascript:ayudasHelpBlo(10);" /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay10"
						style="display:none;margin-top:-1px;"
						value="Ingrese información sobre el diplomado." />
					</td>
				</tr>
			</table>
			<table width="99%">
				<tr>
					<td align="center" style="width: 100%;">
							<s:submit cssClass="botonenviar" align="left"
								value="Guardar encuesta" /></td>
				</tr>
			</table>
			</s:form>
		</div>
	</fieldset>
<script type="text/javascript">
function validarEncuesta(){
	respuesta1 = document.getElementById("respuesta1").value; 
	respuesta2 = document.getElementById("respuesta2").value; 
	respuesta3 = document.getElementById("respuesta3").value; 
	respuesta4 = document.getElementById("respuesta4").value; 
	respuesta5 = document.getElementById("respuesta5").value; 
	respuesta6 = document.getElementById("respuesta6").value; 
	respuesta7 = document.getElementById("respuesta7").value; 
	respuesta8 = document.getElementById("respuesta8").value; 
	respuesta9 = document.getElementById("respuesta9").value; 
	retroalimenacion = document.getElementById("retroalimenacion").value; 
	if (!/^([0-9])*[.]?[0-9]*$/.test(respuesta1) || respuesta1 == null || respuesta1.length == 0
				|| /^\s+$/.test(respuesta1) || respuesta1>10.0){
		 document.getElementById("respuesta1").focus();
		 alert("Ingrese respuesta de pregunta 1 con calificación menor a 10.");
	} else if (!/^([0-9])*[.]?[0-9]*$/.test(respuesta2) || respuesta2 == null || respuesta2.length == 0
			|| /^\s+$/.test(respuesta2) || respuesta1>10.0){
		document.getElementById("respuesta2").focus();
		 alert("Ingrese respuesta de pregunta 2 con calificación menor a 10.");
	} else if (respuesta3 == null || respuesta3.length == 0
			|| /^\s+$/.test(respuesta3)){
		document.getElementById("respuesta3").focus();
		 alert("Ingrese respuesta de pregunta 3.");
	} else if (respuesta4 == null || respuesta4.length == 0
			|| /^\s+$/.test(respuesta4)){
		document.getElementById("respuesta4").focus();
		 alert("Ingrese respuesta de pregunta 4.");
	}  else if (!/^([0-9])*[.]?[0-9]*$/.test(respuesta6) || respuesta5 == null || respuesta5.length == 0
			|| /^\s+$/.test(respuesta5)){
		document.getElementById("respuesta5").focus();
		 alert("Ingrese respuesta de pregunta 5 con calificación menor a 10.");
	} else if (!/^([0-9])*[.]?[0-9]*$/.test(respuesta6) || respuesta6 == null || respuesta6.length == 0
			|| /^\s+$/.test(respuesta6) || respuesta6>10.0){
		document.getElementById("respuesta6").focus();
		 alert("Ingrese respuesta de pregunta 6 con calificación menor a 10.");
	} else if ( respuesta7 == null || respuesta7.length == 0
			|| /^\s+$/.test(respuesta7) || respuesta7>10.0){
		document.getElementById("respuesta7").focus();
		 alert("Ingrese respuesta de pregunta 7.");
	} else if (respuesta8 == null || respuesta8.length == 0
			|| /^\s+$/.test(respuesta8) || respuesta8>10.0){
		document.getElementById("respuesta8").focus();
		 alert("Ingrese respuesta de pregunta 8.");
	} else if (respuesta9 == null || respuesta9.length == 0
			|| /^\s+$/.test(respuesta9) || respuesta6>10.0){
		document.getElementById("respuesta9").focus();
		 alert("Ingrese respuesta de pregunta 9.");
	} else if (retroalimenacion == null || retroalimenacion.length == 0
			|| /^\s+$/.test(retroalimenacion) || respuesta6>10.0){
		document.getElementById("retroalimenacion").focus();
		alert("Ingrese algun punto de retralimentación.");
	}
	return true;
}
function validaNumero(evt) {
	var key = (document.all) ? evt.keyCode : evt.which;
	return (key <= 46 || key <= 13 || (key >= 48 && key <= 57) || key == 46);
}
</script>

</body>
</html>