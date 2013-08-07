<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ayudas.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/serviciosPyMEs.js"></script>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-195px auto 0 250px';
	
	$(window).ready(function() {
		var contArchivos = document.getElementById("contArchivosPago").rows.length;
	    if( contArchivos > 2 ){
			document.getElementById('contArchivosPago').style.display = 'block';
		}
	});
	
</script>
</head>

<body>
<s:if test="mensaje!=null">
	<br />
	<table class="nota">
		<tr>
			<td class="imgNota">
				<s:if test="mensaje.respuesta==0">
					<img src="${pageContext.request.contextPath}/img/palomitaverde.gif" />
				</s:if>
				<s:else>
					<img src="${pageContext.request.contextPath}/img/warning.png" />
				</s:else>
			</td>
			<td class="contenidoNota">
				<s:property value="mensaje.mensaje" />
			</td>
		</tr>
	</table>
</s:if>

<fieldset id="requerimientos">
	<legend>
		<s:label value="Inscripción a diplomados y consultorías" />
		<br />
		<br />
		<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
	</legend>

	<div ${idDiplomado==0? ' style="display: block;" ':' style="display: none;"'}>
		<br />
		<s:label cssClass="etiquetaCaptura" value="* Seleccione la opción 'Diplomados' o 'Consultorias' según requiera." />
		<br />
		<br />
		<table class="submit_tabla" style="width: 40%;">
			<tr>
				<td>
					<input class="botonenviar" value="Diplomados" type="button" onclick="selectDiplomados(); " />
				</td>
				<td>
					<input class="botonenviar" value="Consultorías" type="button" onclick="selectConsultorias(); " />
				</td>
			</tr>
		</table>
		<br />
	</div>

<!-- SERVICIOS DIPLOMADOS -->

	<!-- LISTA DIPLOMADOS -->
	<div id="diplomado" style="display: none;">
		<s:iterator value="listDiplomados" status="stat" var="recor">
			<table width="99%" cellspacing="1" cellpadding="1">
				<tr>
					<td class="encabezadoTablaResumen" align="center"><b>Generación ${stat.count}</b></td>
				</tr>
			</table>
			<s:iterator value="recor" status="cont">
				<div style="float: left; width: 49%; text-align: center;" class="cuerpo1TablaResumen">
					<a href="${pageContext.request.contextPath}/pyme/pymeServiciosShow.do?idDiplomado=${recor[cont.index].idDiplomado}&tituloDiplomado=${recor[cont.index].tema}">${recor[cont.index].tema}</a>
				</div>
			</s:iterator>
		</s:iterator>
	</div>

	<!-- REGISTRO SERVICIOS DIPLOMADOS -->
	<s:if test="idDiplomado != 0">
		<s:form id="idFrmDiplomado" name="frmDiplomado" action="pymeServiciosSave" namespace="/pyme" enctype="multipart/form-data" theme="simple" method="post" onsubmit="javascript: return validaAsistentesDip();">
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
					<td>
						<s:iterator value="listSesiones" status="stat">
							<div style="float: left; width: 25%;">
								<table>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaCaptura" value="Sesión:" /> <s:label cssClass="resultado" value="%{sesion}" />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Expositor:" />
											<s:label cssClass="resultado" value="%{expositor}" />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Fecha:" />
											<s:label cssClass="resultado" value="%{fecha}" />
										</td>
									</tr>
								</table>
							</div>
						</s:iterator>
					</td>
				</tr>
			</table>

			<s:hidden id="labIdDiplomado" name="serviciosDiplomado.idServiciosDiplomado" value="%{serviciosDiplomado.idServiciosDiplomado}" />
			<s:hidden id="idDiplomado" name="idDiplomado" value="%{idDiplomado}" />

			<div id="frmAsistente">
				<div id="tablaReg" ${serviciosDiplomado.asistentes[0].nombre!=null? ' style="display: block;" ':' style="display: none;"'}>
					<br />
					<table width="99%" cellspacing="1" cellpadding="1">
						<tr>
							<td class="encabezadoTablaResumen" align="center"><b>Asistentes Registrados</b></td>
						</tr>
					</table>
					<table width="99%" cellspacing="1" cellpadding="1">
						<thead>
							<tr>
								<td class="cuerpo2TablaResumen" align="center" style="width: 5%"><b>No.</b></td>
								<td class="cuerpo2TablaResumen" align="center" style="width: 15%"><b>Nombre</b></td>
								<td class="cuerpo2TablaResumen" align="center" style="width: 15%"><b>Apellido paterno</b></td>
								<td class="cuerpo2TablaResumen" align="center" style="width: 15%"><b>Apellido materno</b></td>
								<td class="cuerpo2TablaResumen" align="center" style="width: 10%"><b>Teléfono</b></td>
								<td class="cuerpo2TablaResumen" align="center" style="width: 10%"><b>Correo electrónico</b></td>
								<td class="cuerpo2TablaResumen" align="center" style="width: 10%"><b>Cargo</b></td>
								<td class="cuerpo2TablaResumen" align="center" style="width: 10%"><b>Editar asistente</b></td>
							</tr>
						</thead>
						<tbody id="cuerpoTablaReg">
							<s:iterator status="stat" value="serviciosDiplomado.asistentes">
								<tr id="asistente${stat.count}">
									<td class="cuerpo1TablaResumen" align="center">
										<s:label id="labContador%{#stat.count}" cssClass="etiquetaCaptura" value="%{#stat.count}" />
										<s:hidden id="idAsisHid%{#stat.count}" name="serviciosDiplomado.asistentes[%{#stat.index}].idAsistente" value="%{serviciosDiplomado.asistentes[#stat.index].idAsistente}" />
									</td>
									<td class="cuerpo1TablaResumen" align="center">
										<s:label id="labNombre%{#stat.count}" cssClass="etiquetaCaptura" value="%{serviciosDiplomado.asistentes[#stat.index].nombre}" />
										<s:hidden id="nombreHid%{#stat.count}" name="serviciosDiplomado.asistentes[%{#stat.index}].nombre" value="%{serviciosDiplomado.asistentes[#stat.index].nombre}" />
									</td>
									<td class="cuerpo1TablaResumen" align="center">
										<s:label id="labApPaterno%{#stat.count}" cssClass="etiquetaCaptura" value="%{serviciosDiplomado.asistentes[#stat.index].appPaterno}" />
										<s:hidden id="apPaternoHid%{#stat.count}" name="serviciosDiplomado.asistentes[%{#stat.index}].appPaterno" value="%{serviciosDiplomado.asistentes[#stat.index].appPaterno}" />
									</td>
									<td class="cuerpo1TablaResumen" align="center">
										<s:label id="labApMaterno%{#stat.count}" cssClass="etiquetaCaptura" value="%{serviciosDiplomado.asistentes[#stat.index].appMaterno}" />
										<s:hidden id="apMaternoHid%{#stat.count}" name="serviciosDiplomado.asistentes[%{#stat.index}].appMaterno" value="%{serviciosDiplomado.asistentes[#stat.index].appMaterno}" />
									</td>
									<td class="cuerpo1TablaResumen" align="center">
										<s:label id="labTelefono%{#stat.count}" cssClass="etiquetaCaptura" value="%{serviciosDiplomado.asistentes[#stat.index].telefono}" />
										<s:hidden id="telefonoHid%{#stat.count}" name="serviciosDiplomado.asistentes[%{#stat.index}].telefono" value="%{serviciosDiplomado.asistentes[#stat.index].telefono}" />
									</td>
									<td class="cuerpo1TablaResumen" align="center">
										<s:label id="labCorreo%{#stat.count}" cssClass="etiquetaCaptura" value="%{serviciosDiplomado.asistentes[#stat.index].correoElectronico}" />
										<s:hidden id="correoHid%{#stat.count}" name="serviciosDiplomado.asistentes[%{#stat.index}].correoElectronico" value="%{serviciosDiplomado.asistentes[#stat.index].correoElectronico}" />
									</td>
									<td class="cuerpo1TablaResumen" align="center">
										<s:label id="labCargo%{#stat.count}" cssClass="etiquetaCaptura" value="%{serviciosDiplomado.asistentes[#stat.index].cargo}" />
										<s:hidden id="cargoHid%{#stat.count}" name="serviciosDiplomado.asistentes[%{#stat.index}].cargo" value="%{serviciosDiplomado.asistentes[#stat.index].cargo}" />
									</td>
									<td class="cuerpo1TablaResumen" align="center">
										<label class="quitar" onclick="editAsistente(${stat.count});">-editar</label>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<br />
					<label style="font-size: 14px;" class="agregar" id="labShowForm" onclick="javascript: showFormAsistente();">+agregar otro asistente</label>
				</div>

				<div id="contFormA" ${serviciosDiplomado.asistentes[0].nombre==null? ' style="display: block;" ':' style="display: none;"'}>
					<br />
					<table>
						<tr>
							<td>
								<s:label cssClass="etiquetaCaptura" value="Datos del asistente" />
							</td>
						</tr>
					</table>

					<table width="99%" cellspacing="1" cellpadding="1">
						<tr>
							<td>
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Nombre:" /></td>
										<td><s:textfield size="30" id="nombre" name="nombre" value="" maxlength="60" onkeypress="return validaLetra(event)"></s:textfield></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Apellido paterno:" /></td>
										<td><s:textfield size="30" id="apPaterno" name="apPaterno" value="" maxlength="60" onkeypress="return validaLetra(event)"></s:textfield></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Apellido Materno:" /></td>
										<td><s:textfield size="30" id="apMaterno" name="apMaterno" value="" maxlength="60" onkeypress="return validaLetra(event)"></s:textfield></td>
									</tr>
								</table>
							</td>
							<td>
								<table>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaCaptura" value="Teléfono de contacto:" /></td>
									</tr>
									<tr>
										<td colspan="2">
											<table width="99%">
												<tr>
													<td>
														<s:label cssClass="etiquetaCaptura" value="* Lada :" />
													</td>
													<td>
														<s:label id="intTel" cssClass="resultado" value="52" />&nbsp;&nbsp;
														<s:textfield size="1" id="ladaTel" name="ladaTel" maxlength="2" onkeypress="javascript: return validaNumero(event);" ></s:textfield>
													</td>
													<td>
														<s:label cssClass="etiquetaCaptura" value="* Núm:" />
													</td>
													<td style="width: 20%;">
														<s:textfield size="10" id="numTel" name="numTel" maxlength="8" onkeypress="javascript: return validaNumero(event);" ></s:textfield>
													</td>
													<td style="width: 5%;">
														<s:label cssClass="etiquetaCaptura" value="Ext:" />
													</td>
													<td style="width: 15%;">
														<s:textfield size="2" id="extTel" name="extTel" maxlength="4" onkeypress="return validaNumero(event)"></s:textfield>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Correo electrónico:" /></td>
										<td><s:textfield size="30" id="correo" name="correo" value="" maxlength="60"></s:textfield></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Cargo en la PyME:" /></td>
										<td><s:textfield size="30" id="cargo" name="cargo" value="" maxlength="60"></s:textfield></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<s:hidden id="posTabla" name="posTabla" value="" />
					<br />
					<table width="99%">
						<tr>
							<td align="left">
								<label id="labAddAsistente" style="font-size: 16px;" class="agregar" onclick="javascript: addAsistente();">+registrar asistente</label>
							</td>
							<td align="right">
								<label id="labCancelaAsistente" style="display: none; font-size: 16px;" class="quitar" onclick="javascript: cancelaRegAsistente();">-cancelar registro</label>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<label id="labFinEdit" style="display: none; font-size: 16px;" class="agregar" onclick="javascript: finEditAsistente();">-finalizar edición</label>
							</td>
						</tr>
					</table>
				</div>
					
				<!-- ARCHIVO RFC PyME -->
				<br />
				<s:hidden id="idRFC" name="documentoRfc.idArchivo" value="%{documentoRfc.idArchivo}" />
				<table width="99%">
					<tr>
						<td style="width: 5%;">
							<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value='RFC:' />	
						</td>
						<td>
							<div ${documentoRfc.idArchivo==null? ' style="display: block;" ':' style="display: none;"'}>
								<s:file id="aRfcPyME" name="serviciosDiplomado.rfc" onclick="javascript:ayudasHelp(20);"/>
							</div>
							<div ${documentoRfc.idArchivo!=null? ' style="display: block;" ':' style="display: none;"'}>
								<s:label cssClass="resultado" cssStyle="align: left;" value="%{documentoRfc.nombre}" />
							</div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<s:label cssClass="etiquetaAyuda" id="ayudasDisplay20" style="display:none; margin-top:5px;" value="Indique el archivo que será incluido. Máximo 2MB (.pdf .doc .png)" />
						</td>
					</tr>
				</table>

				<!-- ARCHIVOS PAGO DIPLOMADO -->
				<br />
				<table width="99%">
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value='"Estimada PyME, le recordamos que si ya realizó el pago correspondiente a los diplomados que acaba de inscribir, puede adjuntarlo en esta sección".' />	
						</td>
					</tr>
				</table>
				<br />

				<table id="contArchivosPago" width="99%" style="display: none;">
					<tr>
						<td class="encabezadoTablaResumen" colspan="3" align="center" style="width: 800px;">Descripción de los archivos adjuntos</td>
					</tr>
					<tr>
						<td class="cuerpo2TablaResumen" align="center" style="width: 45%;">&nbsp;Descripción del archivo</td>
						<td class="cuerpo2TablaResumen" align="center" style="width: 40%;">&nbsp;Descargar archivo adjunto</td>
						<td class="cuerpo2TablaResumen" align="center" style="width: 15%;">&nbsp;Eliminar archivo</td>
					</tr>
					<s:iterator value="listDocumentos" status="stat">
						<tr id="archPago${stat.count}">
							<td class="cuerpo1TablaResumen" align="left">${descripcionArchivo}</td>
							<td class="cuerpo1TablaResumen" align="left">${nombre}</td>
							<td class="cuerpo1TablaResumen" align="center">
								<label class="quitar" onclick="javascript:supArchivoTabla(${idArchivo}, ${stat.count});">-eliminar</label>
							</td>
						</tr>
					</s:iterator>
				</table>

				<div id="contAyudaDelete" style="display: none; margin-left: 10px;">
					<s:label cssClass="etiquetaAyuda" value="Estimada PyME recuerde que para concluir el proceso para eliminar archivos debe ejecutar el botón 'Registrar datos'." />
				</div>
				<s:hidden id="eliminarArchivos" name="idArchivos" value="%{idArchivos}" />
				
				<div id="contNewArchivo" style="display: none;">
					<table id="contArchivos" width="99%">
						<tr>
							<td><s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="* Comprobante de pago del Diplomado:" /></td>
						</tr>
					</table>
					<table>
						<tr>
							<td>
								<s:label cssClass="etiquetaAyuda" style="margin-top:5px;" value="Indique el archivo que será incluido. Máximo 2MB (.pdf .doc .png)" />
							</td>
						</tr>
					</table>
				</div>
				<br />
				<label style="font-size: 14px;" id="labAddAsistente" class="agregar" onclick="javascript: addArchivo();">+adjuntar archivo</label>
			</div>
			<br />
			<table class="submit_tabla">
				<tr>
					<td style="width: 250px;"></td>
					<td><s:submit cssClass="botonenviar" align="left" value="Registrar datos" /></td>
					<td style="width: 250px;"></td>
				</tr>
			</table>
		</s:form>
	</s:if>

	<!-- SERVICIOS CONSULTORIA -->
	<div id="consultoria" style="display: none;">
		<s:form name="frmConsultoria" action="pymeServiciosSave" namespace="/pyme" enctype="multipart/form-data" theme="simple" method="post">
			<table>
				<tr>
					<td colspan="3"><s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="* Tipo de consultoria" /></td>
				</tr>
				<tr>
					<td style="width: 120px;">
						<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="20 Horas" />
						<s:checkbox id="check20" name="serviciosConsultoria.bConsultoriaVeinte" value="%{serviciosConsultoria.bConsultoriaVeinte}" onclick="javascript:veinteCheck();" />
					</td>
					<td style="width: 120px;">
						<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="40 Horas" />
						<s:checkbox id="check40" name="serviciosConsultoria.bConsultoriaCuarenta" value="%{serviciosConsultoria.bConsultoriaCuarenta}" onclick="javascript:cuarentaCheck();" />
					</td>
					<td style="width: 120px;">
						<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="60 Horas" />
						<s:checkbox id="check60" name="serviciosConsultoria.bConsultoriaSesenta" value="%{serviciosConsultoria.bConsultoriaSesenta}" onclick="javascript:sesentaCheck();" />
					</td>
					<td style="width: 120px;">
						<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="80 Horas" />
						<s:checkbox id="check80" name="serviciosConsultoria.bConsultoriaOchenta" value="%{serviciosConsultoria.bConsultoriaOchenta}" onclick="javascript:ochentaCheck();" />
					</td>
				</tr>
			</table>
			<br />
			<!-- ARCHIVO RFC PyME -->
			<s:hidden id="idRFC" name="documentoRfc.idArchivo" value="%{documentoRfc.idArchivo}" />
			<table width="99%">
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value='RFC:' />	
					</td>
					<td>
						<div ${documentoRfc.idArchivo==null? ' style="display: block;" ':' style="display: none;"'}>
							<s:file id="aRfcPyME" name="serviciosDiplomado.rfc" onclick="javascript:ayudasHelp(20);"/>
						</div>
						<div ${documentoRfc.idArchivo!=null? ' style="display: block;" ':' style="display: none;"'}>
							<s:label cssClass="resultado" cssStyle="align: left;" value="%{documentoRfc.nombre}" />
						</div>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<s:label cssClass="etiquetaAyuda" id="ayudasDisplay20" style="display:none; margin-top:5px;" value="Indique el archivo que será incluido. Máximo 2MB (.pdf .doc .png)" />
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
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="Comprobante de pago de consultoría:" /></td>
				</tr>
				<tr>
					<td><s:file id="aPagoConsult" name="serviciosConsultoria.archivo1" onclick="javascript:ayudasHelp(2);" /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay2" style="display:none; margin-top:5px;" value="Indique el archivo que será incluido. Máximo 2MB (.pdf .doc .png)" /></td>
				</tr>
			</table>
			<br />
			<input class="botonenviar" value="Confirmación Registro" type="button" onclick="consultoria();" />
		</s:form>
	</div>
</fieldset>
</body>
</html>
