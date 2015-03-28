<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${pageContext.request.contextPath}/css/rating.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/ayudas.js" type="text/javascript"></script>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/indicadores.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#calif').calif({maxvalue: 5});
				$('.star').children('a').css('width', '100%').end().slice(0,$('#califCont').val()).addClass('hover').end();
			});
		</script>
	</head>
	<body>
		<fieldset id="requerimientos">
			<s:hidden name="indicador" id="idIndi" value="%{indicador}" />
			<legend>
				<s:label value="Calificación de la PyME" />
				<br /><br />
				<s:label cssClass="camposObligatorios"
					value="Los campos marcados con asterisco(*) son de caracter obligatorio." /><br/><br/>
			</legend>
			
			<!-- CALIFICACIÓN DE LA PyME -->
			<div id="showCalif" ${calificaPyME!=0?' style="display: block;"':' style="display: none;"'}>
				<s:form name="frmCalifica" action="consultorIndicadoresShow" namespace="/consultor" method="post" theme="simple" onsubmit="return califica()">
					<s:hidden id="idPymeTractora" name="relPyMEsTractoras.idPyMETractora" value="%{relPyMEsTractoras.idPyMETractora}" />
					<s:hidden id="idUsuarioPyME" name="relPyMEsTractoras.idUsuarioPyME" value="%{relPyMEsTractoras.idUsuarioPyME}" />
					<s:hidden id="idTractora" name="calificaPyME" value="%{calificaPyME}" />
					<s:hidden id="rel" name="rel" value="%{rel}" />
					<table width="60%">
						<tr>
							<td>
								<s:label cssClass="etiquetaCaptura" value="* Comentarios:" />
							</td>
							<td colspan="2">
								<s:textarea id="areaComent" rows="3" cols="50" name="relPyMEsTractoras.comentario" value="%{relPyMEsTractoras.comentario}" />
							</td>
						</tr>
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td style="width: 220px;">
								<s:label cssClass="etiquetaCaptura" value="Calificar PyME:" />
								<img src="${pageContext.request.contextPath}/img/help.png" alt="Ayuda" style="cursor: pointer;" onclick="$('#idBtnBuscar').click();" />
							</td>
							<td style="width: 115px;">
								<div id="calif" class="rating">&nbsp;</div>
							</td>
							<td>
								<s:textarea id="califCont" rows="1" cols="5" disabled="true" cssClass="resultado" style="resize: none;" name="" value="%{relPyMEsTractoras.calificacion}" />
								<s:hidden id="hidCalifCont" name="relPyMEsTractoras.calificacion" value="%{relPyMEsTractoras.calificacion}" />
							</td>
						</tr>
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td style="width: 220px;">
								<s:label cssClass="etiquetaCaptura" value="Recomendar PyME:" />
							</td>
							<td colspan="2">
								<s:checkbox id="recSi" name="relPyMEsTractoras.recomendacion" value="%{relPyMEsTractoras.recomendacion}" />
							</td>
						</tr>
					</table>
		
					<br />
					<table>
						<tr>
							<td colspan="2">
								<input class="botonenviar" value="Guardar calificación" type="button" onclick="javascript: califica();" />
							</td>
						</tr>
					</table>
				</s:form>
			</div>
		</fieldset>
		<div class="overlay-container">
			<div class="window-container zoomin">
				<fieldset id="requerimientos">
					<legend>
						<s:label id="idBusResTit" value="" />
					</legend>
				</fieldset>
				<s:if test="%{true}">
					<div id="idDivResultados" style="overflow: auto; overflow-x: hidden;"></div>
				</s:if>
			</div>
			<div class="window-container zoomout">
				<h3>Cómo calificar una PyME:</h3>
					<ul style="list-style-type: none;">
						<li><img src="${pageContext.request.contextPath}/img/0_Estrellas.png" width="85px" height="17px" alt="0 Estrellas" /> 0 estrellas - PyME nada recomendable.</li>
						<li><img src="${pageContext.request.contextPath}/img/1_Estrellas.png" width="85px" height="17px" alt="1 Estrella" /> 1 estrella<label style="color:#FFFFFF">s</label> - PyME irresponsable e indisciplinada.</li>
						<li><img src="${pageContext.request.contextPath}/img/2_Estrellas.png" width="85px" height="17px" alt="2 Estrellas" /> 2 estrellas - Cumple con dificultades.</li>
						<li><img src="${pageContext.request.contextPath}/img/3_Estrellas.png" width="85px" height="17px" alt="3 Estrellas" /> 3 estrellas - Cumple a tiempo.</li>
						<li><img src="${pageContext.request.contextPath}/img/4_Estrellas.png" width="85px" height="17px" alt="4 Estrellas" /> 4 estrellas - PyME responsable y eficaz.</li>
						<li><img src="${pageContext.request.contextPath}/img/5_Estrellas.png" width="85px" height="17px" alt="5 Estrellas" /> 5 estrellas - PyME de excelencia, 100% recomendable.</li>
					</ul>
				<br />
				<br />
				<span class="close">Cerrar</span>
			</div>
		</div>
		<script>
			!window.jQuery && document.write(unescape('%3Cscript src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"%3E%3C/script%3E'))
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/demo.js"></script>
		<s:if test="%{true}">
			<input type="button" id="idBtnBuscar" value="" class="button" style="position: absolute; margin-top: -500px; display: none;" data-type="zoomout" />
		</s:if>
	</body>
</html>