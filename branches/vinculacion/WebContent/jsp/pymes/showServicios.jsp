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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ayudas.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/serviciosPyMEs.js"></script>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-195px auto 0 250px';
</script>
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
		<legend>
			<s:label value="Inscripción a diplomados y consultorías" />
			<br /><br />
			<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<div ${tituloDiplomado==null?' style="display: block;"':' style="display: none;"'}>
			<br />
			<s:label cssClass="etiquetaCaptura" value="* Seleccione la opción 'Diplomados' o 'Consultorias' según requiera." />
			<br /><br />
			<table class="submit_tabla" style="width: 40%;">
				<tr>
					<td><input class="botonenviar" value="Diplomados" type="button" onclick="selectDiplomados(); " /></td>
					<td><input class="botonenviar" value="Consultorías" type="button" onclick="selectConsultorias(); " /></td>
				</tr>
			</table>
			<br />
		</div>
		
		<!-- SERVICIOS DIPLOMADOS -->
		<div id="diplomado" style="display: none;">
			<s:iterator value="(generaciones).{ #this }" status="stat">
				<table width="99%" cellspacing="1" cellpadding="1">
					<tr>
						<td class="encabezadoTablaResumen" align="center"><b>Generación ${stat.count}</b></td>
					</tr>
				</table>
				<s:iterator value="listDiplomados" status="cont">
					<div style="float: left; width: 49%; text-align: center;" class="cuerpo1TablaResumen">
						<a href="${pageContext.request.contextPath}/pyme/pymeServiciosShow.do?generacion=${stat.count}&tituloDiplomado=${tema}">${tema}</a>
					</div>
				</s:iterator>
			</s:iterator>
		</div>
		<s:if test="generacion != 0">
			<s:if test="tituloDiplomado != null">
				<s:form id="idFrmDiplomado" name="frmDiplomado" action="pymeServiciosShow" namespace="/pyme" enctype="multipart/form-data" theme="simple" method="post" onsubmit="javascript: return validaAsistentesDip();">
					<br />
					<table>
						<tr>
							<td><s:label cssClass="etiquetaCaptura" value="Diplomado seleccionado:" /></td>
							<td><s:label cssClass="resultado" value="%{tituloDiplomado}" /></td>
						</tr>
					</table>
					<br />
					<table width="99%" cellspacing="1" cellpadding="1">
						<tr>
							<td colspan="2"><s:label cssClass="etiquetaCaptura" value="Seleccione una sede:" /></td>
						</tr>
						<tr>
							<td colspan="2">
								<s:iterator value="listUbicacion" status="stat">
									<div style="float: left; width: 33%;">
										<table>
											<tr>
												<td>
													<s:hidden id="idDiplomadoHid%{#stat.count}" name="" value="%{idDiplomado}" />
													<s:checkbox id="checkUbicaDip%{#stat.count}" name="" value="" onclick="javascript: validaCheck%{#stat.count}(%{#stat.count});"/>
												</td>
												<td><s:label cssClass="resultado" value="%{sede}" /></td>
												<td><s:label cssClass="resultado" value="%{fecha}" /></td>
											</tr>
										</table>
									</div>
								</s:iterator>
							</td>
						</tr>
					</table>
					<br />
					<s:hidden id="labIdDiplomado" name="serviciosDiplomado.idDiplomado" value="%{serviciosDiplomado.idDiplomado}" />
					<s:hidden id="asistIdDiplomado" name="idDiplomado" value="%{idDiplomado}" />
					
					<div id="frmAsistente">
						<table>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Registrar Asistentes" /></td>
							</tr>
						</table>
						<table width="99%" cellspacing="1" cellpadding="1">
							<thead>
								<tr>
									<td class="encabezadoTablaResumen" align="center"><b>Nombre</b></td>
									<td class="encabezadoTablaResumen" align="center"><b>Apellido Paterno</b></td>
									<td class="encabezadoTablaResumen" align="center"><b>Apellido Materno</b></td>
									<td class="encabezadoTablaResumen" align="center"><b>Teléfono</b></td>
									<td class="encabezadoTablaResumen" align="center"><b>Correo Electrónico</b></td>
								</tr>
							</thead>
							<tbody id="addAsistente">
									<tr id="asistente1">
										<td class="cuerpo1TablaResumen" align="center">
											<s:textfield size="20" id="nombre1" name="nombresAsistentes" value="" maxlength="60" onkeypress="return validaLetra(event)"></s:textfield>
										</td>
										<td class="cuerpo1TablaResumen" align="center">
											<s:textfield size="20" id="appPat1" name="appPatAsistentes" value="" maxlength="60" onkeypress="return validaLetra(event)"></s:textfield>
										</td>
										<td class="cuerpo1TablaResumen" align="center">
											<s:textfield size="20" id="appMat1" name="appMatAsistentes" value="" maxlength="60" onkeypress="return validaLetra(event)"></s:textfield>
										</td>
										<td class="cuerpo1TablaResumen" align="center">
											<s:textfield size="20" id="tel1" name="telAsistentes" value="" maxlength="60"></s:textfield>
										</td>
										<td class="cuerpo1TablaResumen" align="center">
											<s:textfield size="20" id="correo1" name="correoAsistentes" value="" maxlength="60"></s:textfield>
										</td>
									</tr>
								</tbody>
							</table>
							
							<table width="99%">
								<tr>
									<td>
										<label class="agregar" onclick="javascript: addAsistente();">+agregar otro asistente</label>
									</td>
									<td id="labDelAsistente" style="text-align: right; display: none;">
										<label class="quitar" onclick="javascript: deleteAsistente();">-eliminar asistente</label>
									</td>
								</tr>
							</table>

							<br />
							<br />
							<table style="width: 99%;">
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value='"Estimada PyME, le recordamos que si ya realizó el pago correspondiente a los diplomados que acaba de inscribir, puede adjuntarlo en esta sección".' />
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="Comprobante de pago del Diplomado:" />
									</td>
								</tr>
								<tr>
									<td>
										<s:file id="aPagoDip" name="serviciosDiplomado.archivo1" onclick="javascript:ayudasHelp(2);"/>
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay2" style="display:none; margin-top:5px;"
										value="Indique el archivo que será incluido. Máximo 2MB (.pdf .doc .png)" />
									</td>
								</tr>
						</table>
						<br />
					</div>
					<s:submit cssClass="botonenviar" align="left" value="Confirmación Registro" />
				</s:form>
			</s:if>
			<s:else>
				<br />
				<table>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="El diplomado que ha seleccionado no está disponoble por el momento" />
						</td>
					</tr>
				</table>
			</s:else>
		</s:if>

			<div id="consultoria" style="display: none;">
			<s:form name="frmConsultoria" action="pymeServiciosSave" namespace="/pyme" enctype="multipart/form-data" theme="simple" method="post">
					<table>
						<tr>
							<td colspan="3">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="* Tipo de consultoria" />
							</td>
						</tr>
						<tr>
							<td style="width: 120px;">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="20 Horas" />
								<s:checkbox id="check20" name="serviciosConsultoria.bConsultoriaVeinte" value="%{serviciosConsultoria.bConsultoriaVeinte}" onclick="javascript:veinteCheck();"/>
							</td>
							<td style="width: 120px;">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="40 Horas" />
								<s:checkbox id="check40" name="serviciosConsultoria.bConsultoriaCuarenta" value="%{serviciosConsultoria.bConsultoriaCuarenta}" onclick="javascript:cuarentaCheck();" />
							</td>
							<td style="width: 120px;">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="60 Horas" />
								<s:checkbox id="check60" name="serviciosConsultoria.bConsultoriaSesenta" value="%{serviciosConsultoria.bConsultoriaSesenta}" onclick="javascript:sesentaCheck();"/>
							</td>
							<td style="width: 120px;">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="80 Horas" />
								<s:checkbox id="check80" name="serviciosConsultoria.bConsultoriaOchenta" value="%{serviciosConsultoria.bConsultoriaOchenta}" onclick="javascript:ochentaCheck();" />
							</td>
						</tr>
					</table>
					
					<br />
					<table id="showArchPago" style="width: 700px; display: none;">
						<tr>
							<td>
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value='"Estimada PyME, le recordamos que si ya realizó el pago correspondiente a la consultoría de 20, 40, 60 u 80 horas que acaba de solicitar, puede adjuntarlo en esta sección".' />
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="Comprobante de pago de consultoría:" />
							</td>
						</tr>
						<tr>
							<td>
								<s:file id="aPagoConsult" name="serviciosConsultoria.archivo1" 
									onclick="javascript:ayudasHelp(2);"/>
							</td>
						</tr>
						<tr>
							<td>
								<s:label cssClass="etiquetaAyuda"  id="ayudasDisplay2" style="display:none; margin-top:5px;"
									value="Indique el archivo que será incluido. Máximo 2MB (.pdf .doc .png)" />
							</td>
						</tr>
					</table>
					<br />
					<s:hidden name="serviciosConsultoria.mensaje" id="msjConsult" value="Estimada PyME, en breve un consultor se pondrá en contacto con ustedes, a nombre del CCMX" />
					<input class="botonenviar" value="Confirmación Registro" type="button" onclick="consultoria(); " />
				</s:form>
			</div>
	</fieldset>
</body>
</html>
