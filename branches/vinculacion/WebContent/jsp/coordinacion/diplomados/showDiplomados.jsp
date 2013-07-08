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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/serviciosPyMEs.js"></script>
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
<s:form name="frmAnios" action="coordinadorDiplomadosDiplomadosShow"
				namespace="/diplomados/coordinacion" theme="simple">
			<s:hidden name="year" id="year"></s:hidden>
				
</s:form>
	<fieldset id="requerimientos">
		<div ${listDiplomados!=null ? ' style="display: block;"
			' :' style="display: none;"' }>
			<s:form name="busqueda" action="coordinadorDiplomadosDiplomadosShow"
				namespace="/diplomados/coordinacion" theme="simple"
				onsubmit="return validacionBusqueda()">
				<legend>
					<s:label value="Diplomados" />
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
							<a href="${pageContext.request.contextPath}/diplomados/coordinacion/coordinadorDiplomadosDiplomadosShow.do?idDiplomado=${recor[cont.index].idDiplomado}">${recor[cont.index].tema}</a>						
						</div>
					</s:iterator>
				</s:iterator>
			</s:form>
		</div>
		<div ${( idPyme == 0 && listParticipantes!= null )  ? ' style="display: block;"
			' :' style="display: none;"' } >
			<s:form name="busqueda" action="coordinadorDiplomadosDiplomadosShow"
				namespace="/diplomados/coordinacion" theme="simple"
				onsubmit="return validacionBusqueda()">
				<s:hidden name="idDiplomado" value="%{idDiplomado}" />
				<s:hidden name="idPyme" id="idPyme" value="-1" />
				<legend>
					<s:label value="Diplomado de " />${tema }
					<br /> <br />
				</legend>
				<br />
				<table width="99%">
					<tr>
						<td align="center" style="width: 50%;"><s:submit cssClass="botonenviar" align="left"
								value="Administrar Sesiones" /></td>
					</tr>
				</table>
				<table>
					<tr>
						<td>
							<table width="99%" cellspacing="1" cellpadding="1">
								<thead>
									<tr>
										<td class="encabezado_tabla" align="center"><b>No.</b></td>
										<td class="encabezado_tabla" align="center"><b>PYME
												</b></td>
										<td class="encabezado_tabla" align="center"><b>Nombre del<br />Participante
												</b></td>
										<td class="encabezado_tabla" align="center"><b>Telefono
												</b></td>
										<td class="encabezado_tabla" align="center"><b>Correo<br />electrónico
												 </b></td>
										<td class="encabezado_tabla" align="center"><b>Cargo
												 </b></td>
										<td class="encabezado_tabla" align="center"><b>Tractora
												 </b></td>
										<td class="encabezado_tabla" align="center"><b>Sesion1
												</b></td>
										<td class="encabezado_tabla" align="center"><b>Sesion2
												</b></td>
										<td class="encabezado_tabla" align="center"><b>Sesion3
												</b></td>
										<td class="encabezado_tabla" align="center"><b>Sesion4
												</b></td>
										<td class="encabezado_tabla" align="center"><b>Factura
												</b></td>
										<td class="encabezado_tabla" align="center"><b>Pago
												</b></td>
										<td class="encabezado_tabla" align="center"><b>No. de pago
												</b></td>
										<td class="encabezado_tabla" align="center"><b>Editar
												</b></td>
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
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{asistencia1}">
														&bull;
													</s:if> <s:else>
														&nbsp;
													</s:else></td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{asistencia2}">
														&bull;
													</s:if> <s:else>
														&nbsp;
													</s:else></td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{asistencia3}">
														&bull;
													</s:if> <s:else>
														&nbsp;
													</s:else></td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{asistencia4}">
														&bull;
													</s:if> <s:else>
														&nbsp;
													</s:else></td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{pago}">
														&bull;
													</s:if> <s:else>
														&nbsp;
													</s:else></td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{factura}">
														&bull;
													</s:if> <s:else>
														&nbsp;
													</s:else></td>
												<td
													class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">${numPago}</td>
												<td
													class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center"><a
															href="${pageContext.request.contextPath}/diplomados/coordinacion/coordinadorDiplomadosDiplomadosShow.do?idPyme=${idUsuario}&idDiplomado=${idDiplomado}">Editar</a>
												</td>
											</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<div id="participantes" ${idPyme != 0 && listParticipantes!= null ? ' style="display: block;"
			' :' style="display: none;"' } >
			<s:form name="busqueda" action="coordinadorDiplomadosDiplomadosShow"
				namespace="/diplomados/coordinacion" theme="simple">
				<s:hidden name="idDiplomado" value="%{idDiplomado}" />
				<s:hidden name="idPyme"  value="%{idPyme}" />
				<legend>
					<s:label value="Diplomado de " />${tema }
					<br /> <br />
				</legend>
				<br />
				<table>
					<tr>
						<td><s:label
									cssClass="etiquetaCaptura"
									value="Datos de la PYME:" /></td>
						<td >
							<b>${listParticipantes[0].pyme}</b></td>
					</tr>
				</table>
				<table width="100%">
					<tr>
						<td align="center" style="width: 25%;">
							<input class="botonenviar" value="Agregar asistentes"
								type="button" onclick="javascript:addParticipantes();" /></td>						
						<td align="center" style="width: 25%;"><input class="botonenviar" value="Importar inasistencias" 
								type="button" onclick="javascript: document.frmAsistencias.submit();"></input></td>
						<td align="center" style="width: 25%;"><input class="botonenviar" value="Solicitud de diploma." 
							type="button" onclick="javascript: document.frmDiplomas.submit();"/></td>
					</tr>
				</table>
				<table>
					<tr>
						<td>
							<table width="99%" cellspacing="1" cellpadding="1">
								<thead>
									<tr>
										<td class="encabezado_tabla" align="center"><b>No.</b></td>
										<td class="encabezado_tabla" align="center"><b>Telefono
												</b></td>
										<td class="encabezado_tabla" align="center"><b>Nombre del<br />Participante
												</b></td>
										<td class="encabezado_tabla" align="center"><b>Correo<br />electrónico
												 </b></td>
										<td class="encabezado_tabla" align="center"><b>Cargo
												 </b></td>
										<td class="encabezado_tabla" align="center"><b>Tractora
												 </b></td>
										<td class="encabezado_tabla" align="center"><b>C1
												</b></td>
										<td class="encabezado_tabla" align="center"><b>C2
												</b></td>
										<td class="encabezado_tabla" align="center"><b>C3
												</b></td>
										<td class="encabezado_tabla" align="center"><b>C4
												</b></td>
										<td class="encabezado_tabla" align="center"><b>Pago
												</b></td>
										<td class="encabezado_tabla" align="center"><b>S1
												</b></td>
										<td class="encabezado_tabla" align="center"><b>S2
												</b></td>
										<td class="encabezado_tabla" align="center"><b>S3
												</b></td>
										<td class="encabezado_tabla" align="center"><b>S4
												</b></td>
										<td class="encabezado_tabla" align="center"><b>Factura
												</b></td>
										<td class="encabezado_tabla" align="center"><b>No. de factura
												</b></td>
										<td class="encabezado_tabla" align="center"><b>Encuaestas
												</b></td>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="listParticipantes" status="stat">
									<s:hidden name="listParticipantes[%{#stat.count -1}].id" value="%{id}"></s:hidden>
											<tr>
												<td
													class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">${stat.count}</td>
												<td
													class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">${telefono}</td>
												<td
													class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">${nombre}</td>
												<td
													class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">${correoElectronico}</td>
												<td
													class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">${cargo}</td>
												<td
													class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">${tractora}</td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{editable1}">
														<s:checkbox name="listParticipantes[%{#stat.count -1}].confirmado1" value="%{confirmado1}"></s:checkbox>
													</s:if> <s:else>
														&nbsp;
													</s:else></td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{editable2}">
														<s:checkbox name="listParticipantes[%{#stat.count -1}].confirmado2" value="%{confirmado2}"></s:checkbox>
													</s:if> <s:else>
														&nbsp;
													</s:else></td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{editable3}">
														<s:checkbox name="listParticipantes[%{#stat.count -1}].confirmado3" value="%{confirmado3}"></s:checkbox>
													</s:if> <s:else>
														&nbsp;
													</s:else></td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{editable4}">
														<s:checkbox name="listParticipantes[%{#stat.count -1}].confirmado4" value="%{confirmado4}"></s:checkbox>
													</s:if> <s:else>
														&nbsp;
													</s:else></td>
													
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{pago}">
														<s:hidden name="listParticipantes[%{#stat.count -1}].pago" value="true"></s:hidden>
														<s:checkbox name="" value="%{pago}" disabled="true"></s:checkbox>
													</s:if> <s:else>
														<s:checkbox name="listParticipantes[%{#stat.count -1}].pago" value="%{pago}"></s:checkbox>
													</s:else></td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{editable1}">
														<s:checkbox name="listParticipantes[%{#stat.count -1}].asistencia1" value="%{asistencia1}"></s:checkbox>
													</s:if> <s:else>
														&nbsp;
													</s:else></td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{editable2}">
														<s:checkbox name="listParticipantes[%{#stat.count -1}].asistencia2" value="%{asistencia2}"></s:checkbox>
													</s:if> <s:else>
														&nbsp;
													</s:else></td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{editable3}">
														<s:checkbox name="listParticipantes[%{#stat.count -1}].asistencia3" value="%{asistencia3}"></s:checkbox>
													</s:if> <s:else>
														&nbsp;
													</s:else></td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{editable4}">
														<s:checkbox name="listParticipantes[%{#stat.count -1}].asistencia4" value="%{asistencia4}"></s:checkbox>
													</s:if> <s:else>
														&nbsp;
													</s:else></td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:if test="%{factura}">
														<s:hidden name="listParticipantes[%{#stat.count -1}].factura" value="true"></s:hidden>
														<s:checkbox name="" value="%{factura}" disabled="true"></s:checkbox>
													</s:if> <s:else>
														<s:checkbox name="listParticipantes[%{#stat.count -1}].factura" value="%{factura}"></s:checkbox>
													</s:else></td>
												<td
													class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">
													<s:textfield value="%{numPago}" name="listParticipantes[%{#stat.count -1}].numPago" 
															maxlength="20" size="19"></s:textfield> </td>
													
												<td
													class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center"><a
															href="${pageContext.request.contextPath}/diplomados/coordinacion/coordinadorDiplomadosEncuestasShow.do?idAsistente=${id}">Enviar</a>
												</td>
											</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
				<table width="100%">
					<tr>
						<td align="center" style="width: 25%;">
							<s:submit cssClass="botonenviar" align="left"
								value="Guardar cambios" /></td>		
						<td align="center" style="width: 25%;"><input class="botonenviar" value="Solicitar factura." 
							type="button" onclick=""/></td>
					</tr>
				</table>
					</tr>
				</table>
			</s:form>
			<s:form name="frmAsistencias" action="coordinadorDiplomadosInasistencias"
				namespace="/diplomados/coordinacion" theme="simple">
				<s:hidden name="idDiplomado" value="%{idDiplomado}"></s:hidden>
				<s:hidden name="idPyme" value="%{idPyme}"></s:hidden>
			</s:form>
			<s:form name="frmDiplomas" action="coordinadorDiplomadosDiplomasShow"
				namespace="/diplomados/coordinacion" theme="simple">
				<s:hidden name="idDiplomado" value="%{idDiplomado}"></s:hidden>
				<s:hidden name="idPyme" value="%{idPyme}"></s:hidden>
				
			</s:form>
		</div>
		<div id="addParticipantes" style="display: none;">
		<fieldset id="requerimientos">
				<legend>
					<s:label value="Inscripción a diplomados." />
					<br /><br />
					<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
				</legend>
		<s:form name="" action="coordinadorDiplomadosDiplomadosShow"
				namespace="/diplomados/coordinacion" theme="simple"
				onsubmit="return validaAsistentesDip()">
				<s:hidden name="idPyme"  value="%{idPyme}" />
				<s:hidden name="idDiplomado" value="%{idDiplomado}" />
				
				
				
				
				<div id="frmAsistente">
					
					<div id="tablaReg" ${serviciosDiplomado.asistentes[0].nombre!=null?' style="display: block;"':' style="display: none;"'}>
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
									<td class="cuerpo2TablaResumen" align="center" style="width: 10%"><b>Pago</b></td>
									<td class="cuerpo2TablaResumen" align="center" style="width: 10%"><b>Editar asistente</b></td>
								</tr>
							</thead>
							<tbody id="cuerpoTablaReg">
								<s:iterator status="stat" value="serviciosDiplomado.asistentes" >
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
											<s:if test="serviciosDiplomado.asistentes[%{#stat.index}].pago == true">
												<s:label id="labPago%{#stat.count}" cssClass="etiquetaCaptura" value="Pagado" />
											</s:if>
											<s:else>
												<s:label id="labPago%{#stat.count}" cssClass="etiquetaCaptura" value="Pendiente" />
											</s:else>
											<s:hidden id="pagoHid%{#stat.count}" name="serviciosDiplomado.asistentes[%{#stat.index}].pago" value="%{serviciosDiplomado.asistentes[#stat.index].pago}" />
										</td>
										<td class="cuerpo1TablaResumen"align="center">
											<s:if test="%{#stat.count} == 1">
												<label>-editar</label>
											</s:if>
											<s:else>
												<label class="quitar" onclick="editAsistente(${stat.count});">-editar</label>
											</s:else>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<br />
						<label class="agregar" id="labShowForm" onclick="javascript: showFormAsistente();">+agregar otro asistente</label>
					</div>

					<div id="contFormA" ${serviciosDiplomado.asistentes[0].nombre==null?' style="display: block;"':' style="display: none;"'}>
						<br />
						<table>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="Datos del asistente" /></td>
							</tr>
						</table>
						
						<table width="99%" cellspacing="1" cellpadding="1">
							<tr>
								<td>
									<table>
										<tr>
											<td>
												<s:label cssClass="etiquetaCaptura" value="* Nombre:" />
											</td>
											<td>
												<s:textfield size="30" id="nombre" name="nombre" value="" maxlength="60" onkeypress="return validaLetra(event)"></s:textfield>
											</td>
										</tr>
										<tr>
											<td>
												<s:label cssClass="etiquetaCaptura" value="* Apellido paterno:" />
											</td>
											<td>
												<s:textfield size="30" id="apPaterno" name="apPaterno" value="" maxlength="60" onkeypress="return validaLetra(event)"></s:textfield>
											</td>
										</tr>
										<tr>
											<td>
												<s:label cssClass="etiquetaCaptura" value="* Apellido Materno:" />
											</td>
											<td>
												<s:textfield size="30" id="apMaterno" name="apMaterno" value="" maxlength="60" onkeypress="return validaLetra(event)"></s:textfield>
											</td>
										</tr>
									</table>	
								</td>
								<td>
									<table>
										<tr>
											<td>
												<s:label cssClass="etiquetaCaptura" value="* Teléfono:" />
											</td>
											<td>
												<s:textfield size="30" id="telefono" name="telefono" value="" maxlength="60"></s:textfield>
											</td>
										</tr>
										<tr>
											<td>
												<s:label cssClass="etiquetaCaptura" value="* Correo electrónico:" />
											</td>
											<td>
												<s:textfield size="30" id="correo" name="correo" value="" maxlength="60"></s:textfield>
											</td>
										</tr>
										<tr>
											<td>
												<s:label cssClass="etiquetaCaptura" value="* Cargo en la PyME:" />
											</td>
											<td>
												<s:textfield size="30" id="cargo" name="cargo" value="" maxlength="60"></s:textfield>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="2"></td>
							</tr>
							<tr>
								<td colspan="2">
									<s:label cssClass="etiquetaCaptura" value="Registrar pago:" />
									<s:checkbox id="pago" name="pago" />
								</td>
							</tr>
						</table>
						<s:hidden id="posTabla" name="posTabla" value="" />
						<br />
						<table width="99%">
							<tr>
								<td>
									<label id="labAddAsistente" class="agregar" onclick="javascript: addAsistente();">+registrar asistente</label>
									<label id="labFinEdit" style="display: none;" class="agregar" onclick="javascript: finEditAsistente();">-finalizar edición</label>
								</td>
								<td >
									
								</td>
							</tr>
						</table>
						</div>
				
				
					<s:submit cssClass="botonenviar" align="left" value="Guardar Asistentes" />
				
				</s:form>
			</fieldset>
		</div>
		<div ${listInacistencias!=null ? ' style="display: block;"
			' :' style="display: none;"' }>
			<s:form name="inasistencias" id="inasistencias" action="coordinadorDiplomadosInasistencias"
				namespace="/diplomados/coordinacion" theme="simple"
				onsubmit="return validaChecInasistencia()">
				<s:hidden name="idDiplomado" value = "%{idDiplomado}"></s:hidden>
				<s:hidden name="idPyme" value = "%{idPyme}"></s:hidden>
					<legend>
						<s:label value="Lista de inasistentes." />
						<br /> <br />
					</legend>
					<table width="99%">
					<thead>
						<tr>
							<td class="encabezado_tabla" align="center"><b>No.</b></td>
							<td class="encabezado_tabla" align="center"><b>Nombre</b></td>
							<td class="encabezado_tabla" align="center"><b>Correo Electrónico</b></td>
							<td class="encabezado_tabla" align="center"><b>Teléfono</b></td>
							<td class="encabezado_tabla" align="center"><b>Tema</b></td>
							<td class="encabezado_tabla" align="center"><b>Pyme</b></td>
							<td class="encabezado_tabla" align="center"><b>Sesión</b></td>
							<td class="encabezado_tabla" align="center"><b>Invitación</b></td>
						</tr>
					</thead>
				<tbody>
				<s:iterator value="listInacistencias" status="stat">
					<s:hidden name="listInacistencias[%{#stat.count -1}].id" value="%{id}"></s:hidden>
					<s:hidden name="listInacistencias[%{#stat.count -1}].sesion" value="%{sesion}"></s:hidden>
					<s:hidden name="listInacistencias[%{#stat.count -1}].idUsuario" value="%{idUsuario}"></s:hidden>
					<s:hidden name="listInacistencias[%{#stat.count -1}].tema" value="%{tema}"></s:hidden>
					<s:hidden name="listInacistencias[%{#stat.count -1}].correoElectronico" value="%{correoElectronico}"></s:hidden>
					<tr>
						<td
							class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
							align="center">${stat.count}</td>
						<td
							class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
							align="center">${nombre}</td>
						<td
							class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
							align="center">${correoElectronico}</td>
						<td
							class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
							align="center">${telefono}</td>
						<td
							class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
							align="center">${tema}</td>
						<td
							class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
							align="center">${pyme}</td>
						<td
							class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
							align="center">${sesion}</td>
						<td
							class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
							align="center"><s:checkbox name="listInacistencias[%{#stat.count -1}].invitacion"></s:checkbox></td>
						
					</tr>
				</s:iterator>
				</tbody>
			</table>
			<table width="100%">
					<tr>
						<td align="center"><s:submit cssClass="botonenviar" align="left"
								value="Enviar invitación" /></td>
					</tr>
				</table>
			</s:form>
		</div>
		<div ${listDiplomas!=null ? ' style="display: block;"
			' :' style="display: none;"' }>
			<legend>
				<s:label value="Diplomas." />
			<br /> <br />
			</legend>
			<table width="99%">
				<thead>
					<tr>
						<td class="encabezado_tabla" align="center"><b>No.</b></td>
						<td class="encabezado_tabla" align="center"><b>Nombre</b></td>
						<td class="encabezado_tabla" align="center"><b>Tema</b></td>
						<td class="encabezado_tabla" align="center"><b>Imprimir</b></td>
					</tr>
				</thead>
			<tbody>
			<s:iterator value="listDiplomas" status="stat">
				<s:hidden name="listInacistencias[%{#stat.count -1}].id" value="%{id}"></s:hidden>
					<tr>
						<td
							class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
							align="center">${stat.count}</td>
						<td
							class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
							align="center">${nombre}</td>
						<td
							class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
							align="center">${tema }</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
								align="center"><a
								href="#">Imprimir</a>
							<!-- ${pageContext.request.contextPath}/diplomados/coordinacion/coordinadorDiplomadosDiplomaShow.do?tema=${tema}&NombresAsistentes=${nombre}"  -->
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</div>
		<div ${listSesiones!=null ? ' style="display: block;"
			' :' style="display: none;"' }>
			<s:form name="sesiones" action="coordinadorDiplomadosDiplomadosShow"
				namespace="/diplomados/coordinacion" theme="simple"
				onsubmit="javascript: return validaAsistentesDip();">
				<s:hidden name="numeroSesiones" id="numeroSesiones" />
				<s:hidden name="idDiplomado" id="idDiplomado" value="%{idDiplomado}" />
				<div id="sesion1" style="display: block;">
					<legend>
						<s:label value="Sesión 1" />
						<br /> <br />
					</legend>
					<s:hidden name="listSesiones[0].idSesion"
						value="%{listSesiones[0].idSesion}"></s:hidden>
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Sede:" />
										</td>
										<td><s:textfield size="50" id="idExpositor1"
												name="listSesiones[0].expositor" maxlength="100"
												onfocus="javascript:ayudasHelp(1);"
												onblur="javascript:ayudasHelpBlo(1);">
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay1" style="display:none;margin-top:-1px;"
												value="Ingrese la sede de la sesión 1." />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Sala:" />
										</td>
										<td><s:textfield size="40" id="idSala1"
												name="listSesiones[0].sala" maxlength="100"
												onfocus="javascript:ayudasHelp(2);"
												onblur="javascript:ayudasHelpBlo(2);">
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay2" style="display:none;margin-top:-1px;"
												value="Ingrese la sala de la sesión 1." />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Fecha:" />
										</td>
										<td><s:date name="listSesiones[0].fecha" id="fCert1"
												format="dd/MM/yyyy" /> <s:textfield class="calendario"
												id="ingreso1" name="listSesiones[0].fecha" value="%{fCert1}"
												size="10" maxlength="10" /> <img
											src="${pageContext.request.contextPath}/img/calendario.png"
											width="16" height="16" title="Seleccione una fecha"
											id="lanzador1" style="cursor: hand" />
										</td>
									</tr>
									<tr>
										<td>
											<s:label
												cssClass="etiquetaCaptura" value="Horario:" /> 
										</td>
										<td><select
											name="listSesiones[0].hora" style="width: 40px;" id="hora1">
												<option value="-1">--</option>
												<option ${listSesiones[0].hora== '0' ? ' selected="selected"
													' : ''} value="0">0</option>
												<option ${listSesiones[0].hora== '1' ? ' selected="selected"
													' : ''} value="1">1</option>
												<option ${listSesiones[0].hora== '2' ? ' selected="selected"
													' : ''} value="2">2</option>
												<option ${listSesiones[0].hora== '3' ? ' selected="selected"
													' : ''} value="3">3</option>
												<option ${listSesiones[0].hora== '4' ? ' selected="selected"
													' : ''} value="4">4</option>
												<option ${listSesiones[0].hora== '5' ? ' selected="selected"
													' : ''} value="5">5</option>
												<option ${listSesiones[0].hora== '6' ? ' selected="selected"
													' : ''} value="6">6</option>
												<option ${listSesiones[0].hora== '7' ? ' selected="selected"
													' : ''} value="7">7</option>
												<option ${listSesiones[0].hora== '8' ? ' selected="selected"
													' : ''} value="8">8</option>
												<option ${listSesiones[0].hora== '9' ? ' selected="selected"
													' : ''} value="9">9</option>
												<option ${listSesiones[0].hora==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[0].hora==
													'11' ? ' selected="selected" ' : ''} value="11">11</option>
												<option ${listSesiones[0].hora==
													'12' ? ' selected="selected" ' : ''} value="12">12</option>
												<option ${listSesiones[0].hora==
													'13' ? ' selected="selected" ' : ''} value="13">13</option>
												<option ${listSesiones[0].hora==
													'14' ? ' selected="selected" ' : ''} value="14">14</option>
												<option ${listSesiones[0].hora==
													'15' ? ' selected="selected" ' : ''} value="15">15</option>
												<option ${listSesiones[0].hora==
													'16' ? ' selected="selected" ' : ''} value="16">16</option>
												<option ${listSesiones[0].hora==
													'17' ? ' selected="selected" ' : ''} value="17">17</option>
												<option ${listSesiones[0].hora==
													'18' ? ' selected="selected" ' : ''} value="18">18</option>
												<option ${listSesiones[0].hora==
													'19' ? ' selected="selected" ' : ''} value="19">19</option>
												<option ${listSesiones[0].hora==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[0].hora==
													'21' ? ' selected="selected" ' : ''} value="21">21</option>
												<option ${listSesiones[0].hora==
													'22' ? ' selected="selected" ' : ''} value="22">22</option>
												<option ${listSesiones[0].hora==
													'23' ? ' selected="selected" ' : ''} value="23">23</option>
										</select> : <select name="listSesiones[0].minuto" style="width: 40px;"
											id="minuto1">
												<option value="-1">--</option>
												<option ${listSesiones[0].minuto==
													'0' ? ' selected="selected" ' : ''} value="0">00</option>
												<option ${listSesiones[0].minuto==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[0].minuto==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[0].minuto==
													'30' ? ' selected="selected" ' : ''} value="30">30</option>
												<option ${listSesiones[0].minuto==
													'40' ? ' selected="selected" ' : ''} value="40">40</option>
												<option ${listSesiones[0].minuto==
													'50' ? ' selected="selected" ' : ''} value="50">50</option>
										</select> hrs.  &nbsp;
										<select
											name="listSesiones[0].horaFin" style="width: 40px;" id="horaFin1">
												<option value="-1">--</option>
												<option ${listSesiones[0].horaFin== '0' ? ' selected="selected"
													' : ''} value="0">0</option>
												<option ${listSesiones[0].horaFin== '1' ? ' selected="selected"
													' : ''} value="1">1</option>
												<option ${listSesiones[0].horaFin== '2' ? ' selected="selected"
													' : ''} value="2">2</option>
												<option ${listSesiones[0].horaFin== '3' ? ' selected="selected"
													' : ''} value="3">3</option>
												<option ${listSesiones[0].horaFin== '4' ? ' selected="selected"
													' : ''} value="4">4</option>
												<option ${listSesiones[0].horaFin== '5' ? ' selected="selected"
													' : ''} value="5">5</option>
												<option ${listSesiones[0].horaFin== '6' ? ' selected="selected"
													' : ''} value="6">6</option>
												<option ${listSesiones[0].horaFin== '7' ? ' selected="selected"
													' : ''} value="7">7</option>
												<option ${listSesiones[0].horaFin== '8' ? ' selected="selected"
													' : ''} value="8">8</option>
												<option ${listSesiones[0].horaFin== '9' ? ' selected="selected"
													' : ''} value="9">9</option>
												<option ${listSesiones[0].horaFin==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[0].horaFin==
													'11' ? ' selected="selected" ' : ''} value="11">11</option>
												<option ${listSesiones[0].horaFin==
													'12' ? ' selected="selected" ' : ''} value="12">12</option>
												<option ${listSesiones[0].horaFin==
													'13' ? ' selected="selected" ' : ''} value="13">13</option>
												<option ${listSesiones[0].horaFin==
													'14' ? ' selected="selected" ' : ''} value="14">14</option>
												<option ${listSesiones[0].horaFin==
													'15' ? ' selected="selected" ' : ''} value="15">15</option>
												<option ${listSesiones[0].horaFin==
													'16' ? ' selected="selected" ' : ''} value="16">16</option>
												<option ${listSesiones[0].horaFin==
													'17' ? ' selected="selected" ' : ''} value="17">17</option>
												<option ${listSesiones[0].horaFin==
													'18' ? ' selected="selected" ' : ''} value="18">18</option>
												<option ${listSesiones[0].horaFin==
													'19' ? ' selected="selected" ' : ''} value="19">19</option>
												<option ${listSesiones[0].horaFin==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[0].horaFin==
													'21' ? ' selected="selected" ' : ''} value="21">21</option>
												<option ${listSesiones[0].horaFin==
													'22' ? ' selected="selected" ' : ''} value="22">22</option>
												<option ${listSesiones[0].horaFin==
													'23' ? ' selected="selected" ' : ''} value="23">23</option>
										</select> : <select name="listSesiones[0].minutoFin" style="width: 40px;"
											id="minutoFin1">
												<option value="-1">--</option>
												<option ${listSesiones[0].minutoFin==
													'0' ? ' selected="selected" ' : ''} value="0">00</option>
												<option ${listSesiones[0].minutoFin==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[0].minutoFin==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[0].minutoFin==
													'30' ? ' selected="selected" ' : ''} value="30">30</option>
												<option ${listSesiones[0].minutoFin==
													'40' ? ' selected="selected" ' : ''} value="40">40</option>
												<option ${listSesiones[0].minutoFin==
													'50' ? ' selected="selected" ' : ''} value="50">50</option>
										</select> hrs.</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Instructor:" />
										</td>
										<td><s:textfield size="50" id="idInstuctor1"
												name="listSesiones[0].instructor" maxlength="80"
												onfocus="javascript:ayudasHelp(11);"
												onblur="javascript:ayudasHelpBlo(11);">
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay11" style="display:none;margin-top:-1px;"
												value="Ingrese el instructor de la sesión 1." />
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaCaptura"
												value="Dirección:" /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Calle:" />
										</td>
										<td><s:textfield size="30" id="calle1"
												name="listSesiones[0].domicilios.calle" maxlength="50"
												onfocus="javascript:ayudasHelp(3);"
												onblur="javascript:ayudasHelpBlo(3);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay3" style="display:none; margin-top:5px;"
												value="Escriba la calle de su empresa." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Número Exterior:" /></td>
										<td><s:textfield size="20" id="numExt1"
												name="listSesiones[0].domicilios.numExt" maxlength="20"
												onfocus="javascript:ayudasHelp(4);"
												onblur="javascript:ayudasHelpBlo(4);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay4" style="display:none; margin-top:5px;"
												value="Escriba el número exterior." /></td>
									</tr>
								</table></td>
							<td>
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="Número Interior:" /></td>
										<td><s:textfield size="20" id="numInt1"
												name="listSesiones[0].domicilios.numInt" maxlength="20"
												onfocus="javascript:ayudasHelp(5);"
												onblur="javascript:ayudasHelpBlo(5);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay5" style="display:none; margin-top:5px;"
												value="Escriba el número interior." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Piso:" />
										</td>
										<td><s:textfield size="30" id="piso1"
												name="listSesiones[0].domicilios.piso" maxlength="20"
												onfocus="javascript:ayudasHelp(6);"
												onblur="javascript:ayudasHelpBlo(6);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay6" style="display:none; margin-top:5px;"
												value="Escriba el piso." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Colonia:" /></td>
										<td><s:textfield size="30" id="colonia1"
												name="listSesiones[0].domicilios.colonia" maxlength="50"
												onfocus="javascript:ayudasHelp(7);"
												onblur="javascript:ayudasHelpBlo(7);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay7" style="display:none; margin-top:5px;"
												value="Escriba la colonia." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Delegación/Municipio:" /></td>
										<td><s:textfield size="30" id="delegacion1"
												name="listSesiones[0].domicilios.delegacion" maxlength="50"
												onfocus="javascript:ayudasHelp(8);"
												onblur="javascript:ayudasHelpBlo(8);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay8" style="display:none; margin-top:5px;"
												value="Escriba Delegación o Municipio." /></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay9" style="display:none; margin-top:5px;"
												value="Seleccione el Estado ." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Estado:" />
										</td>
										<td><select id="estado1"
											name="listSesiones[0].domicilios.estado"
											style="width: 230px;" onfocus="javascript:ayudasHelp(9);"
											onblur="javascript:ayudasHelpBlo(9);">
												<option value="0">--Seleccione un estado--</option>
												<option ${listSesiones[0].domicilios.estado==
													'Aguascalientes' ? ' selected="selected"
													' : ''} value="Aguascalientes">Aguascalientes</option>
												<option ${listSesiones[0].domicilios.estado== 
													'Baja California Norte' ? ' selected="selected"
													' : ''} value="Baja California Norte">Baja
													California Norte</option>
												<option ${listSesiones[0].domicilios.estado== 
													'Baja California Sur' ? ' selected="selected"
													' : ''} value="Baja California Sur">Baja
													California Sur</option>
												<option ${listSesiones[0].domicilios.estado==
													'Campeche' ? ' selected="selected" ' : ''} value="Campeche">Campeche</option>
												<option ${listSesiones[0].domicilios.estado==
													'Chiapas' ? ' selected="selected" ' : ''} value="Chiapas">Chiapas</option>
												<option ${listSesiones[0].domicilios.estado==
													'Chihuahua' ? ' selected="selected"
													' : ''} value="Chihuahua">Chihuahua</option>
												<option ${listSesiones[0].domicilios.estado==
													'Coahuila' ? ' selected="selected" ' : ''} value="Coahuila">Coahuila</option>
												<option ${listSesiones[0].domicilios.estado==
													'Colima' ? ' selected="selected" ' : ''} value="Colima">Colima</option>
												<option ${listSesiones[0].domicilios.estado== 'Distrito Federal' ? ' selected="selected"
													' : ''} value="Distrito Federal">Distrito Federal</option>
												<option ${listSesiones[0].domicilios.estado==
													'Durango' ? ' selected="selected" ' : ''} value="Durango">Durango</option>
												<option ${listSesiones[0].domicilios.estado == 'Estado de Mexico' ? ' selected="selected"
													' : ''} value="Estado de Mexico">Estado de México</option>
												<option ${listSesiones[0].domicilios.estado==
													'Guanajuato' ? ' selected="selected"
													' : ''} value="Guanajuato">Guanajuato</option>
												<option ${listSesiones[0].domicilios.estado==
													'Guerrero' ? ' selected="selected" ' : ''} value="Guerrero">Guerrero</option>
												<option ${listSesiones[0].domicilios.estado==
													'Hidalgo' ? ' selected="selected" ' : ''} value="Hidalgo">Hidalgo</option>
												<option ${listSesiones[0].domicilios.estado==
													'Jalisco' ? ' selected="selected" ' : ''} value="Jalisco">Jalisco</option>
												<option ${listSesiones[0].domicilios.estado==
													'Michoacan' ? ' selected="selected"
													' : ''} value="Michoacan">Michoacán</option>
												<option ${listSesiones[0].domicilios.estado==
													'Morelos' ? ' selected="selected" ' : ''} value="Morelos">Morelos</option>
												<option ${listSesiones[0].domicilios.estado==
													'Nayarit' ? ' selected="selected" ' : ''} value="Nayarit">Nayarit</option>
												<option ${listSesiones[0].domicilios.estado== 
													'Nuevo Leon' ? ' selected="selected" ' : ''} value="Nuevo Leon">Nuevo
													León</option>
												<option ${listSesiones[0].domicilios.estado==
													'Oaxaca' ? ' selected="selected" ' : ''} value="Oaxaca">Oaxaca</option>
												<option ${listSesiones[0].domicilios.estado==
													'Puebla' ? ' selected="selected" ' : ''} value="Puebla">Puebla</option>
												<option ${listSesiones[0].domicilios.estado==
													'Queretaro' ? ' selected="selected"
													' : ''} value="Queretaro">Querétaro</option>
												<option ${listSesiones[0].domicilios.estado== 
													'Quintana Roo' ? ' selected="selected" ' : ''} value="Quintana Roo">Quintana
													Roo</option>
												<option ${listSesiones[0].domicilios.estado== 'San Luis Potosi'
													 ? ' selected="selected"
													' : ''} value="San Luis Potosi">San Luís Potosí</option>
												<option ${listSesiones[0].domicilios.estado==
													'Sinaloa' ? ' selected="selected" ' : ''} value="Sinaloa">Sinaloa</option>
												<option ${listSesiones[0].domicilios.estado==
													'Sonora' ? ' selected="selected" ' : ''} value="Sonora">Sonora</option>
												<option ${listSesiones[0].domicilios.estado==
													'Tabasco' ? ' selected="selected" ' : ''} value="Tabasco">Tabasco</option>
												<option ${listSesiones[0].domicilios.estado==
													'Tamaulipas' ? ' selected="selected"
													' : ''} value="Tamaulipas">Tamaulipas</option>
												<option ${listSesiones[0].domicilios.estado==
													'Tlaxcala' ? ' selected="selected" ' : ''} value="Tlaxcala">Tlaxcala</option>
												<option ${listSesiones[0].domicilios.estado==
													'Veracruz' ? ' selected="selected" ' : ''} value="Veracruz">Veracruz</option>
												<option ${listSesiones[0].domicilios.estado==
													'Yucatan' ? ' selected="selected" ' : ''} value="Yucatan">Yucatán</option>
												<option ${listSesiones[0].domicilios.estado==
													'Zacatecas' ? ' selected="selected"
													' : ''} value="Zacatecas">Zacatecas</option>
										</select></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* C.P.:" />
										</td>
										<td><s:textfield size="20" id="codigoPostal1"
												name="listSesiones[0].domicilios.codigoPostal" maxlength="5"
												onfocus="javascript:ayudasHelp(10);"
												onblur="javascript:ayudasHelpBlo(10);"
												onkeypress="return validaNumero(event)"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay10" style="display:none; margin-top:5px;"
												value="Escriba el Código postal en que se encuentra." /></td>
									</tr>
								</table></td>
						</tr>
					</table>
					<br />
					<table>
						<tr>
							<td><s:label cssClass="etiquetaCaptura"
									value="* Información importante:" />
							</td>
						</tr>
						<tr>
							<td><s:textarea cols="110" rows="9" id="idInfo1"
									name="listSesiones[0].info" value="%{listSesiones[0].info}" maxlength="1000"
									onfocus="javascript:ayudasHelp(13);"
									onblur="javascript:ayudasHelpBlo(13);" /></td>
						</tr>
						<tr>
							<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay13"
									style="display:none;margin-top:-1px;"
									value="Ingrese la información importante" />
							</td>
						</tr>
					</table>
					<table width="99%; " class="submit_tabla">
						<tr>
							<s:hidden name="listSesiones[0].idDiplomado" id="idDiplomado" value="%{idDiplomado}" />
							<s:hidden name="listSesiones[0].domicilios.idDomicilio"
								value="%{listSesiones[0].domicilios.idDomicilio}"></s:hidden>
							<td align="center" style="width: 50%;"><input class="botonenviar" value="Siguiente sesión"
								type="button" onclick="javascript:siguiente(1);" /></td>
							<td align="center" style="width: 50%;"><input class="botonenviar" value="Guardar" type="button"
								onclick="javascript:finalizar(1);" /></td>
						</tr>
					</table>
				</div>
				<div id="sesion2" style="display: none;">
					<legend>
						<s:label value="Sesión 2" />
						<br /> <br />
					</legend>
					<s:hidden name="listSesiones[1].idSesion"
							value="%{listSesiones[1].idSesion}"></s:hidden>					
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Sede:" />
										</td>
										<td><s:textfield size="50" id="idExpositor2"
												name="listSesiones[1].expositor" maxlength="100"
												onfocus="javascript:ayudasHelp(14);"
												onblur="javascript:ayudasHelpBlo(14);">
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay14" style="display:none;margin-top:-1px;"
												value="Ingrese la sede de la sesión 2." />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Sala:" />
										</td>
										<td><s:textfield size="40" id="idSala2"
												name="listSesiones[1].sala" maxlength="100"
												onfocus="javascript:ayudasHelp(15);"
												onblur="javascript:ayudasHelpBlo(15);">
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay15" style="display:none;margin-top:-1px;"
												value="Ingrese la sala de la sesión 2." />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Fecha:" />
										</td>
										<td><s:date name="listSesiones[1].fecha" id="fCert2"
												format="dd/MM/yyyy" /> <s:textfield class="calendario"
												id="ingreso2" name="listSesiones[1].fecha" value="%{fCert2}"
												size="10" maxlength="10" /> <img
											src="${pageContext.request.contextPath}/img/calendario.png"
											width="16" height="16" title="Seleccione una fecha"
											id="lanzador2" style="cursor: hand" />
										</td>
									</tr>
									<tr>
										<td>
											<s:label
												cssClass="etiquetaCaptura" value="Horario:" /> 
										</td>
										<td><select
											name="listSesiones[1].hora" style="width: 40px;" id="hora2">
												<option value="-1">--</option>
												<option ${listSesiones[1].hora== '0' ? ' selected="selected"
													' : ''} value="0">00</option>
												<option ${listSesiones[1].hora== '1' ? ' selected="selected"
													' : ''} value="1">1</option>
												<option ${listSesiones[1].hora== '2' ? ' selected="selected"
													' : ''} value="2">2</option>
												<option ${listSesiones[1].hora== '3' ? ' selected="selected"
													' : ''} value="3">3</option>
												<option ${listSesiones[1].hora== '4' ? ' selected="selected"
													' : ''} value="4">4</option>
												<option ${listSesiones[1].hora== '5' ? ' selected="selected"
													' : ''} value="5">5</option>
												<option ${listSesiones[1].hora== '6' ? ' selected="selected"
													' : ''} value="6">6</option>
												<option ${listSesiones[1].hora== '7' ? ' selected="selected"
													' : ''} value="7">7</option>
												<option ${listSesiones[1].hora== '8' ? ' selected="selected"
													' : ''} value="8">8</option>
												<option ${listSesiones[1].hora== '9' ? ' selected="selected"
													' : ''} value="9">9</option>
												<option ${listSesiones[1].hora==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[1].hora==
													'11' ? ' selected="selected" ' : ''} value="11">11</option>
												<option ${listSesiones[1].hora==
													'12' ? ' selected="selected" ' : ''} value="12">12</option>
												<option ${listSesiones[1].hora==
													'13' ? ' selected="selected" ' : ''} value="13">13</option>
												<option ${listSesiones[1].hora==
													'14' ? ' selected="selected" ' : ''} value="14">14</option>
												<option ${listSesiones[1].hora==
													'15' ? ' selected="selected" ' : ''} value="15">15</option>
												<option ${listSesiones[1].hora==
													'16' ? ' selected="selected" ' : ''} value="16">16</option>
												<option ${listSesiones[1].hora==
													'17' ? ' selected="selected" ' : ''} value="17">17</option>
												<option ${listSesiones[1].hora==
													'18' ? ' selected="selected" ' : ''} value="18">18</option>
												<option ${listSesiones[1].hora==
													'19' ? ' selected="selected" ' : ''} value="19">19</option>
												<option ${listSesiones[1].hora==
													'2[1]' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[1].hora==
													'21' ? ' selected="selected" ' : ''} value="21">21</option>
												<option ${listSesiones[1].hora==
													'22' ? ' selected="selected" ' : ''} value="22">22</option>
												<option ${listSesiones[1].hora==
													'23' ? ' selected="selected" ' : ''} value="23">23</option>
										</select> : <select name="listSesiones[1].minuto" style="width: 40px;"
											id="minuto2">
												<option value="-1">--</option>
												<option ${listSesiones[1].minuto==
													'0' ? ' selected="selected" ' : ''} value="0">00</option>
												<option ${listSesiones[1].minuto==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[1].minuto==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[1].minuto==
													'30' ? ' selected="selected" ' : ''} value="30">30</option>
												<option ${listSesiones[1].minuto==
													'40' ? ' selected="selected" ' : ''} value="40">40</option>
												<option ${listSesiones[1].minuto==
													'50' ? ' selected="selected" ' : ''} value="50">50</option>
										</select> hrs.  &nbsp;
										<select
											name="listSesiones[1].horaFin" style="width: 40px;" id="horaFin2">
												<option value="-1">--</option>
												<option ${listSesiones[1].horaFin== '0' ? ' selected="selected"
													' : ''} value="0">0</option>
												<option ${listSesiones[1].horaFin== '1' ? ' selected="selected"
													' : ''} value="1">1</option>
												<option ${listSesiones[1].horaFin== '2' ? ' selected="selected"
													' : ''} value="2">2</option>
												<option ${listSesiones[1].horaFin== '3' ? ' selected="selected"
													' : ''} value="3">3</option>
												<option ${listSesiones[1].horaFin== '4' ? ' selected="selected"
													' : ''} value="4">4</option>
												<option ${listSesiones[1].horaFin== '5' ? ' selected="selected"
													' : ''} value="5">5</option>
												<option ${listSesiones[1].horaFin== '6' ? ' selected="selected"
													' : ''} value="6">6</option>
												<option ${listSesiones[1].horaFin== '7' ? ' selected="selected"
													' : ''} value="7">7</option>
												<option ${listSesiones[1].horaFin== '8' ? ' selected="selected"
													' : ''} value="8">8</option>
												<option ${listSesiones[1].horaFin== '9' ? ' selected="selected"
													' : ''} value="9">9</option>
												<option ${listSesiones[1].horaFin==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[1].horaFin==
													'11' ? ' selected="selected" ' : ''} value="11">11</option>
												<option ${listSesiones[1].horaFin==
													'12' ? ' selected="selected" ' : ''} value="12">12</option>
												<option ${listSesiones[1].horaFin==
													'13' ? ' selected="selected" ' : ''} value="13">13</option>
												<option ${listSesiones[1].horaFin==
													'14' ? ' selected="selected" ' : ''} value="14">14</option>
												<option ${listSesiones[1].horaFin==
													'15' ? ' selected="selected" ' : ''} value="15">15</option>
												<option ${listSesiones[1].horaFin==
													'16' ? ' selected="selected" ' : ''} value="16">16</option>
												<option ${listSesiones[1].horaFin==
													'17' ? ' selected="selected" ' : ''} value="17">17</option>
												<option ${listSesiones[1].horaFin==
													'18' ? ' selected="selected" ' : ''} value="18">18</option>
												<option ${listSesiones[1].horaFin==
													'19' ? ' selected="selected" ' : ''} value="19">19</option>
												<option ${listSesiones[1].horaFin==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[1].horaFin==
													'21' ? ' selected="selected" ' : ''} value="21">21</option>
												<option ${listSesiones[1].horaFin==
													'22' ? ' selected="selected" ' : ''} value="22">22</option>
												<option ${listSesiones[1].horaFin==
													'23' ? ' selected="selected" ' : ''} value="23">23</option>
										</select> : <select name="listSesiones[1].minutoFin" style="width: 40px;"
											id="minutoFin2">
												<option value="-1">--</option>
												<option ${listSesiones[1].minutoFin==
													'0' ? ' selected="selected" ' : ''} value="0">00</option>
												<option ${listSesiones[1].minutoFin==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[1].minutoFin==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[1].minutoFin==
													'30' ? ' selected="selected" ' : ''} value="30">30</option>
												<option ${listSesiones[1].minutoFin==
													'40' ? ' selected="selected" ' : ''} value="40">40</option>
												<option ${listSesiones[1].minutoFin==
													'50' ? ' selected="selected" ' : ''} value="50">50</option>
										</select> hrs.</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Instructor:" />
										</td>
										<td><s:textfield size="50" id="idInstuctor2"
												name="listSesiones[1].instructor" maxlength="80"
												onfocus="javascript:ayudasHelp(16);"
												onblur="javascript:ayudasHelpBlo(16);">
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay16" style="display:none;margin-top:-1px;"
												value="Ingrese el instructor de la sesión 2." />
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaCaptura"
												value="Dirección:" /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Calle:" />
										</td>
										<td><s:textfield size="30" id="calle2"
												name="listSesiones[1].domicilios.calle" maxlength="50"
												onfocus="javascript:ayudasHelp(17);"
												onblur="javascript:ayudasHelpBlo(17);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay17" style="display:none; margin-top:5px;"
												value="Escriba la calle de su empresa." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Número Exterior:" /></td>
										<td><s:textfield size="20" id="numExt2"
												name="listSesiones[1].domicilios.numExt" maxlength="20"
												onfocus="javascript:ayudasHelp(18);"
												onblur="javascript:ayudasHelpBlo(18);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay18" style="display:none; margin-top:5px;"
												value="Escriba el número exterior." /></td>
									</tr>
								</table></td>
							<td>
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="Número Interior:" /></td>
										<td><s:textfield size="20" id="numInt2"
												name="listSesiones[1].domicilios.numInt" maxlength="20"
												onfocus="javascript:ayudasHelp(19);"
												onblur="javascript:ayudasHelpBlo(19);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay19" style="display:none; margin-top:5px;"
												value="Escriba el número interior." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Piso:" />
										</td>
										<td><s:textfield size="30" id="piso2"
												name="listSesiones[1].domicilios.piso" maxlength="20"
												onfocus="javascript:ayudasHelp(20);"
												onblur="javascript:ayudasHelpBlo(20);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay20" style="display:none; margin-top:5px;"
												value="Escriba el piso." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Colonia:" /></td>
										<td><s:textfield size="30" id="colonia2"
												name="listSesiones[1].domicilios.colonia" maxlength="50"
												onfocus="javascript:ayudasHelp(21);"
												onblur="javascript:ayudasHelpBlo(21);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay21" style="display:none; margin-top:5px;"
												value="Escriba la colonia." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Delegación/Municipio:" /></td>
										<td><s:textfield size="30" id="delegacion2"
												name="listSesiones[1].domicilios.delegacion" maxlength="50"
												onfocus="javascript:ayudasHelp(22);"
												onblur="javascript:ayudasHelpBlo(22);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay22" style="display:none; margin-top:5px;"
												value="Escriba Delegación o Municipio." /></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay23" style="display:none; margin-top:5px;"
												value="Seleccione el Estado ." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Estado:" />
										</td>
										<td><select id="estado2"
											name="listSesiones[1].domicilios.estado"
											style="width: 230px;" onfocus="javascript:ayudasHelp(23);"
											onblur="javascript:ayudasHelpBlo(23);">
												<option value="0">--Seleccione un estado--</option>
												<option ${listSesiones[1].domicilios.estado==
													'Aguascalientes' ? ' selected="selected"
													' : ''} value="Aguascalientes">Aguascalientes</option>
												<option ${listSesiones[1].domicilios.estado== 
													'Baja California Norte' ? ' selected="selected"
													' : ''} value="Baja California Norte">Baja
													California Norte</option>
												<option ${listSesiones[1].domicilios.estado== 
													'Baja California Sur' ? ' selected="selected"
													' : ''} value="Baja California Sur">Baja
													California Sur</option>
												<option ${listSesiones[1].domicilios.estado==
													'Campeche' ? ' selected="selected" ' : ''} value="Campeche">Campeche</option>
												<option ${listSesiones[1].domicilios.estado==
													'Chiapas' ? ' selected="selected" ' : ''} value="Chiapas">Chiapas</option>
												<option ${listSesiones[1].domicilios.estado==
													'Chihuahua' ? ' selected="selected"
													' : ''} value="Chihuahua">Chihuahua</option>
												<option ${listSesiones[1].domicilios.estado==
													'Coahuila' ? ' selected="selected" ' : ''} value="Coahuila">Coahuila</option>
												<option ${listSesiones[1].domicilios.estado==
													'Colima' ? ' selected="selected" ' : ''} value="Colima">Colima</option>
												<option ${listSesiones[1].domicilios.estado== 'Distrito Federal' ? ' selected="selected"
													' : ''} value="Distrito Federal">Distrito Federal</option>
												<option ${listSesiones[1].domicilios.estado==
													'Durango' ? ' selected="selected" ' : ''} value="Durango">Durango</option>
												<option ${listSesiones[1].domicilios.estado== 'Estado de Mexico' ? ' selected="selected"
													' : ''} value="Estado de Mexico">Estado de México</option>
												<option ${listSesiones[1].domicilios.estado==
													'Guanajuato' ? ' selected="selected"
													' : ''} value="Guanajuato">Guanajuato</option>
												<option ${listSesiones[1].domicilios.estado==
													'Guerrero' ? ' selected="selected" ' : ''} value="Guerrero">Guerrero</option>
												<option ${listSesiones[1].domicilios.estado==
													'Hidalgo' ? ' selected="selected" ' : ''} value="Hidalgo">Hidalgo</option>
												<option ${listSesiones[1].domicilios.estado==
													'Jalisco' ? ' selected="selected" ' : ''} value="Jalisco">Jalisco</option>
												<option ${listSesiones[1].domicilios.estado==
													'Michoacan' ? ' selected="selected"
													' : ''} value="Michoacan">Michoacán</option>
												<option ${listSesiones[1].domicilios.estado==
													'Morelos' ? ' selected="selected" ' : ''} value="Morelos">Morelos</option>
												<option ${listSesiones[1].domicilios.estado==
													'Nayarit' ? ' selected="selected" ' : ''} value="Nayarit">Nayarit</option>
												<option ${listSesiones[1].domicilios.estado== 'Nuevo
													Leon' ? ' selected="selected" ' : ''} value="Nuevo Leon">Nuevo
													León</option>
												<option ${listSesiones[1].domicilios.estado==
													'Oaxaca' ? ' selected="selected" ' : ''} value="Oaxaca">Oaxaca</option>
												<option ${listSesiones[1].domicilios.estado==
													'Puebla' ? ' selected="selected" ' : ''} value="Puebla">Puebla</option>
												<option ${listSesiones[1].domicilios.estado==
													'Queretaro' ? ' selected="selected"
													' : ''} value="Queretaro">Querétaro</option>
												<option ${listSesiones[1].domicilios.estado== 'Quintana
													Roo' ? ' selected="selected" ' : ''} value="Quintana Roo">Quintana
													Roo</option>
												<option ${listSesiones[1].domicilios.estado== 'San Luis Potosi' ? ' selected="selected"
													' : ''} value="San Luis Potosi">San Luís Potosí</option>
												<option ${listSesiones[1].domicilios.estado==
													'Sinaloa' ? ' selected="selected" ' : ''} value="Sinaloa">Sinaloa</option>
												<option ${listSesiones[1].domicilios.estado==
													'Sonora' ? ' selected="selected" ' : ''} value="Sonora">Sonora</option>
												<option ${listSesiones[1].domicilios.estado==
													'Tabasco' ? ' selected="selected" ' : ''} value="Tabasco">Tabasco</option>
												<option ${listSesiones[1].domicilios.estado==
													'Tamaulipas' ? ' selected="selected"
													' : ''} value="Tamaulipas">Tamaulipas</option>
												<option ${listSesiones[1].domicilios.estado==
													'Tlaxcala' ? ' selected="selected" ' : ''} value="Tlaxcala">Tlaxcala</option>
												<option ${listSesiones[1].domicilios.estado==
													'Veracruz' ? ' selected="selected" ' : ''} value="Veracruz">Veracruz</option>
												<option ${listSesiones[1].domicilios.estado==
													'Yucatan' ? ' selected="selected" ' : ''} value="Yucatan">Yucatán</option>
												<option ${listSesiones[1].domicilios.estado==
													'Zacatecas' ? ' selected="selected"
													' : ''} value="Zacatecas">Zacatecas</option>
										</select></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* C.P.:" />
										</td>
										<td><s:textfield size="20" id="codigoPostal2"
												name="listSesiones[1].domicilios.codigoPostal" maxlength="5"
												onfocus="javascript:ayudasHelp(24);"
												onblur="javascript:ayudasHelpBlo(24);"
												onkeypress="return validaNumero(event)"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay24" style="display:none; margin-top:5px;"
												value="Escriba el Código postal en que se encuentra." /></td>
									</tr>
								</table></td>
						</tr>
					</table>
					<br />
					<table>
						<tr>
							<td><s:label cssClass="etiquetaCaptura"
									value="* Información importante:" />
							</td>
						</tr>
						<tr>
							<td><s:textarea cols="110" rows="9" id="idInfo2"
									name="listSesiones[1].info" value="%{listSesiones[1].info}" maxlength="1000"
									onfocus="javascript:ayudasHelp(25);"
									onblur="javascript:ayudasHelpBlo(25);" /></td>
						</tr>
						<tr>
							<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay25"
									style="display:none;margin-top:-1px;"
									value="Ingrese la información importante" />
							</td>
						</tr>
					</table>
					<table width="99%; " class="submit_tabla">
						<tr>
							<s:hidden name="listSesiones[1].idDiplomado" id="idDiplomado" value="%{idDiplomado}" />
							<td align="center" style="width: 33%;"><input class="botonenviar" value="Anterior sesión"
								type="button" onclick="javascript:anterior(2);" /> <s:hidden
									name="listSesiones[1].domicilios.idDomicilio"
									value="%{listSesiones[1].domicilios.idDomicilio}"></s:hidden>
							</td>
							<td align="center" style="width: 33%;"><input class="botonenviar" value="Siguiente sesión"
								type="button" onclick="javascript:siguiente(2);" /></td>
							<td align="center" style="width: 33%;"><input class="botonenviar" value="Guardar" type="button"
								onclick="javascript:finalizar(2);" /></td>
						</tr>
					</table>
				</div>
				<div id="sesion3" style="display: none;">
					<legend>
						<s:label value="Sesión 3" />
						<br /> <br />
					</legend>
					<s:hidden name="listSesiones[2].idSesion"
							value="%{listSesiones[2].idSesion}"></s:hidden>
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Sede:" />
										</td>
										<td><s:textfield size="50" id="idExpositor3"
												name="listSesiones[2].expositor" maxlength="100"
												onfocus="javascript:ayudasHelp(26);"
												onblur="javascript:ayudasHelpBlo(26);">
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay26" style="display:none;margin-top:-1px;"
												value="Ingrese la sede de la sesión 3." />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Sala:" />
										</td>
										<td><s:textfield size="40" id="idSala3"
												name="listSesiones[2].sala" maxlength="100"
												onfocus="javascript:ayudasHelp(27);"
												onblur="javascript:ayudasHelpBlo(27);">
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay27" style="display:none;margin-top:-1px;"
												value="Ingrese la sala de la sesión 3." />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Fecha:" />
										</td>
										<td><s:date name="listSesiones[2].fecha" id="fCert3"
												format="dd/MM/yyyy" /> <s:textfield class="calendario"
												id="ingreso3" name="listSesiones[2].fecha" value="%{fCert3}"
												size="10" maxlength="10" /> <img
											src="${pageContext.request.contextPath}/img/calendario.png"
											width="16" height="16" title="Seleccione una fecha"
											id="lanzador3" style="cursor: hand" />
										</td>
									</tr>
									<tr>
										<td>
											<s:label
												cssClass="etiquetaCaptura" value="Horario:" /> 
										</td>
										<td><select
											name="listSesiones[2].hora" style="width: 40px;" id="hora3">
												<option value="-1">--</option>
												<option ${listSesiones[2].hora== '0' ? ' selected="selected"
													' : ''} value="0">0</option>
												<option ${listSesiones[2].hora== '1' ? ' selected="selected"
													' : ''} value="1">1</option>
												<option ${listSesiones[2].hora== '2' ? ' selected="selected"
													' : ''} value="2">2</option>
												<option ${listSesiones[2].hora== '3' ? ' selected="selected"
													' : ''} value="3">3</option>
												<option ${listSesiones[2].hora== '4' ? ' selected="selected"
													' : ''} value="4">4</option>
												<option ${listSesiones[2].hora== '5' ? ' selected="selected"
													' : ''} value="5">5</option>
												<option ${listSesiones[2].hora== '6' ? ' selected="selected"
													' : ''} value="6">6</option>
												<option ${listSesiones[2].hora== '7' ? ' selected="selected"
													' : ''} value="7">7</option>
												<option ${listSesiones[2].hora== '8' ? ' selected="selected"
													' : ''} value="8">8</option>
												<option ${listSesiones[2].hora== '9' ? ' selected="selected"
													' : ''} value="9">9</option>
												<option ${listSesiones[2].hora==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[2].hora==
													'11' ? ' selected="selected" ' : ''} value="11">11</option>
												<option ${listSesiones[2].hora==
													'12' ? ' selected="selected" ' : ''} value="12">12</option>
												<option ${listSesiones[2].hora==
													'13' ? ' selected="selected" ' : ''} value="13">13</option>
												<option ${listSesiones[2].hora==
													'14' ? ' selected="selected" ' : ''} value="14">14</option>
												<option ${listSesiones[2].hora==
													'15' ? ' selected="selected" ' : ''} value="15">15</option>
												<option ${listSesiones[2].hora==
													'16' ? ' selected="selected" ' : ''} value="16">16</option>
												<option ${listSesiones[2].hora==
													'17' ? ' selected="selected" ' : ''} value="17">17</option>
												<option ${listSesiones[2].hora==
													'18' ? ' selected="selected" ' : ''} value="18">18</option>
												<option ${listSesiones[2].hora==
													'19' ? ' selected="selected" ' : ''} value="19">19</option>
												<option ${listSesiones[2].hora==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[2].hora==
													'21' ? ' selected="selected" ' : ''} value="21">21</option>
												<option ${listSesiones[2].hora==
													'22' ? ' selected="selected" ' : ''} value="22">22</option>
												<option ${listSesiones[2].hora==
													'23' ? ' selected="selected" ' : ''} value="23">23</option>
										</select> : <select name="listSesiones[2].minuto" style="width: 40px;"
											id="minuto3">
												<option value="-1">--</option>
												<option ${listSesiones[2].minuto==
													'0' ? ' selected="selected" ' : ''} value="0">00</option>
												<option ${listSesiones[2].minuto==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[2].minuto==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[2].minuto==
													'30' ? ' selected="selected" ' : ''} value="30">30</option>
												<option ${listSesiones[2].minuto==
													'40' ? ' selected="selected" ' : ''} value="40">40</option>
												<option ${listSesiones[2].minuto==
													'50' ? ' selected="selected" ' : ''} value="50">50</option>
										</select> hrs.  &nbsp;
										<select
											name="listSesiones[2].horaFin" style="width: 40px;" id="horaFin3">
												<option value="-1">--</option>
												<option ${listSesiones[2].horaFin== '0' ? ' selected="selected"
													' : ''} value="0">0</option>
												<option ${listSesiones[2].horaFin== '1' ? ' selected="selected"
													' : ''} value="1">1</option>
												<option ${listSesiones[2].horaFin== '2' ? ' selected="selected"
													' : ''} value="2">2</option>
												<option ${listSesiones[2].horaFin== '3' ? ' selected="selected"
													' : ''} value="3">3</option>
												<option ${listSesiones[2].horaFin== '4' ? ' selected="selected"
													' : ''} value="4">4</option>
												<option ${listSesiones[2].horaFin== '5' ? ' selected="selected"
													' : ''} value="5">5</option>
												<option ${listSesiones[2].horaFin== '6' ? ' selected="selected"
													' : ''} value="6">6</option>
												<option ${listSesiones[2].horaFin== '7' ? ' selected="selected"
													' : ''} value="7">7</option>
												<option ${listSesiones[2].horaFin== '8' ? ' selected="selected"
													' : ''} value="8">8</option>
												<option ${listSesiones[2].horaFin== '9' ? ' selected="selected"
													' : ''} value="9">9</option>
												<option ${listSesiones[2].horaFin==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[2].horaFin==
													'11' ? ' selected="selected" ' : ''} value="11">11</option>
												<option ${listSesiones[2].horaFin==
													'12' ? ' selected="selected" ' : ''} value="12">12</option>
												<option ${listSesiones[2].horaFin==
													'13' ? ' selected="selected" ' : ''} value="13">13</option>
												<option ${listSesiones[2].horaFin==
													'14' ? ' selected="selected" ' : ''} value="14">14</option>
												<option ${listSesiones[2].horaFin==
													'15' ? ' selected="selected" ' : ''} value="15">15</option>
												<option ${listSesiones[2].horaFin==
													'16' ? ' selected="selected" ' : ''} value="16">16</option>
												<option ${listSesiones[2].horaFin==
													'17' ? ' selected="selected" ' : ''} value="17">17</option>
												<option ${listSesiones[2].horaFin==
													'18' ? ' selected="selected" ' : ''} value="18">18</option>
												<option ${listSesiones[2].horaFin==
													'19' ? ' selected="selected" ' : ''} value="19">19</option>
												<option ${listSesiones[2].horaFin==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[2].horaFin==
													'21' ? ' selected="selected" ' : ''} value="21">21</option>
												<option ${listSesiones[2].horaFin==
													'22' ? ' selected="selected" ' : ''} value="22">22</option>
												<option ${listSesiones[2].horaFin==
													'23' ? ' selected="selected" ' : ''} value="23">23</option>
										</select> : <select name="listSesiones[2].minutoFin" style="width: 40px;"
											id="minutoFin3">
												<option value="-1">--</option>
												<option ${listSesiones[2].minutoFin==
													'0' ? ' selected="selected" ' : ''} value="0">00</option>
												<option ${listSesiones[2].minutoFin==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[2].minutoFin==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[2].minutoFin==
													'30' ? ' selected="selected" ' : ''} value="30">30</option>
												<option ${listSesiones[2].minutoFin==
													'40' ? ' selected="selected" ' : ''} value="40">40</option>
												<option ${listSesiones[2].minutoFin==
													'50' ? ' selected="selected" ' : ''} value="50">50</option>
										</select> hrs.</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Instructor:" />
										</td>
										<td><s:textfield size="50" id="idInstuctor3"
												name="listSesiones[2].instructor" maxlength="80"
												onfocus="javascript:ayudasHelp(28);"
												onblur="javascript:ayudasHelpBlo(28);">
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay28" style="display:none;margin-top:-1px;"
												value="Ingrese el instructor de la sesión 3." />
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaCaptura"
												value="Dirección:" /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Calle:" />
										</td>
										<td><s:textfield size="30" id="calle3"
												name="listSesiones[2].domicilios.calle" maxlength="50"
												onfocus="javascript:ayudasHelp(29);"
												onblur="javascript:ayudasHelpBlo(29);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay29" style="display:none; margin-top:5px;"
												value="Escriba la calle de su empresa." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Número Exterior:" /></td>
										<td><s:textfield size="20" id="numExt3"
												name="listSesiones[2].domicilios.numExt" maxlength="20"
												onfocus="javascript:ayudasHelp(30);"
												onblur="javascript:ayudasHelpBlo(30);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay30" style="display:none; margin-top:5px;"
												value="Escriba el número exterior." /></td>
									</tr>
								</table></td>
							<td>
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="Número Interior:" /></td>
										<td><s:textfield size="20" id="numInt3"
												name="listSesiones[2].domicilios.numInt" maxlength="20"
												onfocus="javascript:ayudasHelp(31);"
												onblur="javascript:ayudasHelpBlo(31);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay31" style="display:none; margin-top:5px;"
												value="Escriba el número interior." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Piso:" />
										</td>
										<td><s:textfield size="30" id="piso3"
												name="listSesiones[2].domicilios.piso" maxlength="20"
												onfocus="javascript:ayudasHelp(32);"
												onblur="javascript:ayudasHelpBlo(32);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay32" style="display:none; margin-top:5px;"
												value="Escriba el piso." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Colonia:" /></td>
										<td><s:textfield size="30" id="colonia3"
												name="listSesiones[2].domicilios.colonia" maxlength="50"
												onfocus="javascript:ayudasHelp(33);"
												onblur="javascript:ayudasHelpBlo(33);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay33" style="display:none; margin-top:5px;"
												value="Escriba la colonia." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Delegación/Municipio:" /></td>
										<td><s:textfield size="30" id="delegacion3"
												name="listSesiones[2].domicilios.delegacion" maxlength="50"
												onfocus="javascript:ayudasHelp(34);"
												onblur="javascript:ayudasHelpBlo(34);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay34" style="display:none; margin-top:5px;"
												value="Escriba Delegación o Municipio." /></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay35" style="display:none; margin-top:5px;"
												value="Seleccione el Estado ." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Estado:" />
										</td>
										<td><select id="estado3"
											name="listSesiones[2].domicilios.estado"
											style="width: 230px;" onfocus="javascript:ayudasHelp(35);"
											onblur="javascript:ayudasHelpBlo(35);">
												<option value="0">--Seleccione un estado--</option>
												<option ${listSesiones[2].domicilios.estado==
													'Aguascalientes' ? ' selected="selected"
													' : ''} value="Aguascalientes">Aguascalientes</option>
												<option ${listSesiones[2].domicilios.estado== 'Baja California Norte' ? ' selected="selected"
													' : ''} value="Baja California Norte">Baja California Norte</option>
												<option ${listSesiones[2].domicilios.estado== 'Baja California Sur' ? ' selected="selected"
													' : ''} value="Baja California Sur">Baja
													California Sur</option>
												<option ${listSesiones[2].domicilios.estado==
													'Campeche' ? ' selected="selected" ' : ''} value="Campeche">Campeche</option>
												<option ${listSesiones[2].domicilios.estado==
													'Chiapas' ? ' selected="selected" ' : ''} value="Chiapas">Chiapas</option>
												<option ${listSesiones[2].domicilios.estado==
													'Chihuahua' ? ' selected="selected"
													' : ''} value="Chihuahua">Chihuahua</option>
												<option ${listSesiones[2].domicilios.estado==
													'Coahuila' ? ' selected="selected" ' : ''} value="Coahuila">Coahuila</option>
												<option ${listSesiones[2].domicilios.estado==
													'Colima' ? ' selected="selected" ' : ''} value="Colima">Colima</option>
												<option ${listSesiones[2].domicilios.estado== 'Distrito Federal' ? ' selected="selected"
													' : ''} value="Distrito Federal">Distrito Federal</option>
												<option ${listSesiones[2].domicilios.estado==
													'Durango' ? ' selected="selected" ' : ''} value="Durango">Durango</option>
												<option ${listSesiones[2].domicilios.estado== 'Estado de Mexico' ? ' selected="selected"
													' : ''} value="Estado de Mexico">Estado de México</option>
												<option ${listSesiones[2].domicilios.estado==
													'Guanajuato' ? ' selected="selected"
													' : ''} value="Guanajuato">Guanajuato</option>
												<option ${listSesiones[2].domicilios.estado==
													'Guerrero' ? ' selected="selected" ' : ''} value="Guerrero">Guerrero</option>
												<option ${listSesiones[2].domicilios.estado==
													'Hidalgo' ? ' selected="selected" ' : ''} value="Hidalgo">Hidalgo</option>
												<option ${listSesiones[2].domicilios.estado==
													'Jalisco' ? ' selected="selected" ' : ''} value="Jalisco">Jalisco</option>
												<option ${listSesiones[2].domicilios.estado==
													'Michoacan' ? ' selected="selected"
													' : ''} value="Michoacan">Michoacán</option>
												<option ${listSesiones[2].domicilios.estado==
													'Morelos' ? ' selected="selected" ' : ''} value="Morelos">Morelos</option>
												<option ${listSesiones[2].domicilios.estado==
													'Nayarit' ? ' selected="selected" ' : ''} value="Nayarit">Nayarit</option>
												<option ${listSesiones[2].domicilios.estado== 'Nuevo Leon' 
													? ' selected="selected" ' : ''} value="Nuevo Leon">Nuevo
													León</option>
												<option ${listSesiones[2].domicilios.estado==
													'Oaxaca' ? ' selected="selected" ' : ''} value="Oaxaca">Oaxaca</option>
												<option ${listSesiones[2].domicilios.estado==
													'Puebla' ? ' selected="selected" ' : ''} value="Puebla">Puebla</option>
												<option ${listSesiones[2].domicilios.estado==
													'Queretaro' ? ' selected="selected"
													' : ''} value="Queretaro">Querétaro</option>
												<option ${listSesiones[2].domicilios.estado== 'Quintana Roo' ? ' selected="selected" ' : ''} value="Quintana Roo">Quintana
													Roo</option>
												<option ${listSesiones[2].domicilios.estado== 'San Luis Potosi' ? ' selected="selected"
													' : ''} value="San Luis Potosi">San Luís Potosí</option>
												<option ${listSesiones[2].domicilios.estado==
													'Sinaloa' ? ' selected="selected" ' : ''} value="Sinaloa">Sinaloa</option>
												<option ${listSesiones[2].domicilios.estado==
													'Sonora' ? ' selected="selected" ' : ''} value="Sonora">Sonora</option>
												<option ${listSesiones[2].domicilios.estado==
													'Tabasco' ? ' selected="selected" ' : ''} value="Tabasco">Tabasco</option>
												<option ${listSesiones[2].domicilios.estado==
													'Tamaulipas' ? ' selected="selected"
													' : ''} value="Tamaulipas">Tamaulipas</option>
												<option ${listSesiones[2].domicilios.estado==
													'Tlaxcala' ? ' selected="selected" ' : ''} value="Tlaxcala">Tlaxcala</option>
												<option ${listSesiones[2].domicilios.estado==
													'Veracruz' ? ' selected="selected" ' : ''} value="Veracruz">Veracruz</option>
												<option ${listSesiones[2].domicilios.estado==
													'Yucatan' ? ' selected="selected" ' : ''} value="Yucatan">Yucatán</option>
												<option ${listSesiones[2].domicilios.estado==
													'Zacatecas' ? ' selected="selected"
													' : ''} value="Zacatecas">Zacatecas</option>
										</select></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* C.P.:" />
										</td>
										<td><s:textfield size="20" id="codigoPostal3"
												name="listSesiones[2].domicilios.codigoPostal" maxlength="5"
												onfocus="javascript:ayudasHelp(36);"
												onblur="javascript:ayudasHelpBlo(36);"
												onkeypress="return validaNumero(event)"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay36" style="display:none; margin-top:5px;"
												value="Escriba el Código postal en que se encuentra." /></td>
									</tr>
								</table></td>
						</tr>
					</table>
					<br />
					<table>
						<tr>
							<td><s:label cssClass="etiquetaCaptura"
									value="* Información importante:" />
							</td>
						</tr>
						<tr>
							<td><s:textarea cols="110" rows="9" id="idInfo3"
									name="listSesiones[2].info" value="%{listSesiones[2].info}" maxlength="1000"
									onfocus="javascript:ayudasHelp(37);"
									onblur="javascript:ayudasHelpBlo(37);" /></td>
						</tr>
						<tr>
							<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay37"
									style="display:none;margin-top:-1px;"
									value="Ingrese la información importante" />
							</td>
						</tr>
					</table>
					<table width="99%; " class="submit_tabla">
						<tr>
							<s:hidden name="listSesiones[2].domicilios.idDomicilio"
								value="%{listSesiones[2].domicilios.idDomicilio}"></s:hidden>
							<s:hidden name="listSesiones[2].idDiplomado" id="idDiplomado" value="%{idDiplomado}" />
							<td align="center" style="width: 33%;"><input class="botonenviar" value="Anterior sesión"
								type="button" onclick="javascript:anterior();" /></td>
							<td align="center" style="width: 33%;"><input class="botonenviar" value="Siguiente sesión"
								type="button" onclick="javascript:siguiente(3);" /></td>
							<td align="center" style="width: 33%;"><input class="botonenviar" value="Guardar" type="button"
								onclick="javascript:finalizar(3);" /></td>
						</tr>
					</table>
				</div>
				<div id="sesion4" style="display: none;">
					<legend>
						<s:label value="Sesión 4" />
						<br /> <br />
					</legend>
					<s:hidden name="listSesiones[3].idSesion"
							value="%{listSesiones[3].idSesion}"></s:hidden>
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Sede:" />
										</td>
										<td><s:textfield size="50" id="idExpositor4"
												name="listSesiones[3].expositor" maxlength="100"
												onfocus="javascript:ayudasHelp(38);"
												onblur="javascript:ayudasHelpBlo(38);">
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay38" style="display:none;margin-top:-1px;"
												value="Ingrese la sede de la sesión 4." />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Sala:" />
										</td>
										<td><s:textfield size="40" id="idSala4"
												name="listSesiones[3].sala" maxlength="100"
												onfocus="javascript:ayudasHelp(39);"
												onblur="javascript:ayudasHelpBlo(39);">
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay39" style="display:none;margin-top:-1px;"
												value="Ingrese la sala de la sesión 4." />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Fecha:" />
										</td>
										<td><s:date name="listSesiones[3].fecha" id="fCert4"
												format="dd/MM/yyyy" /> <s:textfield class="calendario"
												id="ingreso4" name="listSesiones[3].fecha" value="%{fCert4}"
												size="10" maxlength="10" /> <img
											src="${pageContext.request.contextPath}/img/calendario.png"
											width="16" height="16" title="Seleccione una fecha"
											id="lanzador4" style="cursor: hand" />
										</td>
									</tr>
									<tr>
										<td>
											<s:label
												cssClass="etiquetaCaptura" value="Horario:" /> 
										</td>
										<td><select
											name="listSesiones[3].hora" style="width: 40px;" id="hora4">
												<option value="-1">--</option>
												<option ${listSesiones[3].hora== '0' ? ' selected="selected"
													' : ''} value="0">0</option>
												<option ${listSesiones[3].hora== '1' ? ' selected="selected"
													' : ''} value="1">1</option>
												<option ${listSesiones[3].hora== '2' ? ' selected="selected"
													' : ''} value="2">2</option>
												<option ${listSesiones[3].hora== '3' ? ' selected="selected"
													' : ''} value="3">3</option>
												<option ${listSesiones[3].hora== '4' ? ' selected="selected"
													' : ''} value="4">4</option>
												<option ${listSesiones[3].hora== '5' ? ' selected="selected"
													' : ''} value="5">5</option>
												<option ${listSesiones[3].hora== '6' ? ' selected="selected"
													' : ''} value="6">6</option>
												<option ${listSesiones[3].hora== '7' ? ' selected="selected"
													' : ''} value="7">7</option>
												<option ${listSesiones[3].hora== '8' ? ' selected="selected"
													' : ''} value="8">8</option>
												<option ${listSesiones[3].hora== '9' ? ' selected="selected"
													' : ''} value="9">9</option>
												<option ${listSesiones[3].hora==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[3].hora==
													'11' ? ' selected="selected" ' : ''} value="11">11</option>
												<option ${listSesiones[3].hora==
													'12' ? ' selected="selected" ' : ''} value="12">12</option>
												<option ${listSesiones[3].hora==
													'13' ? ' selected="selected" ' : ''} value="13">13</option>
												<option ${listSesiones[3].hora==
													'14' ? ' selected="selected" ' : ''} value="14">14</option>
												<option ${listSesiones[3].hora==
													'15' ? ' selected="selected" ' : ''} value="15">15</option>
												<option ${listSesiones[3].hora==
													'16' ? ' selected="selected" ' : ''} value="16">16</option>
												<option ${listSesiones[3].hora==
													'17' ? ' selected="selected" ' : ''} value="17">17</option>
												<option ${listSesiones[3].hora==
													'18' ? ' selected="selected" ' : ''} value="18">18</option>
												<option ${listSesiones[3].hora==
													'19' ? ' selected="selected" ' : ''} value="19">19</option>
												<option ${listSesiones[3].hora==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[3].hora==
													'21' ? ' selected="selected" ' : ''} value="21">21</option>
												<option ${listSesiones[3].hora==
													'22' ? ' selected="selected" ' : ''} value="22">22</option>
												<option ${listSesiones[3].hora==
													'23' ? ' selected="selected" ' : ''} value="23">23</option>
										</select> : <select name="listSesiones[3].minuto" style="width: 40px;"
											id="minuto4">
												<option value="-1">--</option>
												<option ${listSesiones[3].minuto==
													'0' ? ' selected="selected" ' : ''} value="0">00</option>
												<option ${listSesiones[3].minuto==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[3].minuto==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[3].minuto==
													'30' ? ' selected="selected" ' : ''} value="30">30</option>
												<option ${listSesiones[3].minuto==
													'40' ? ' selected="selected" ' : ''} value="40">40</option>
												<option ${listSesiones[3].minuto==
													'50' ? ' selected="selected" ' : ''} value="50">50</option>
										</select> hrs.  &nbsp;
										<select
											name="listSesiones[3].horaFin" style="width: 40px;" id="horaFin4">
												<option value="-1">--</option>
												<option ${listSesiones[3].horaFin== '0' ? ' selected="selected"
													' : ''} value="0">0</option>
												<option ${listSesiones[3].horaFin== '1' ? ' selected="selected"
													' : ''} value="1">1</option>
												<option ${listSesiones[3].horaFin== '2' ? ' selected="selected"
													' : ''} value="2">2</option>
												<option ${listSesiones[3].horaFin== '3' ? ' selected="selected"
													' : ''} value="3">3</option>
												<option ${listSesiones[3].horaFin== '4' ? ' selected="selected"
													' : ''} value="4">4</option>
												<option ${listSesiones[3].horaFin== '5' ? ' selected="selected"
													' : ''} value="5">5</option>
												<option ${listSesiones[3].horaFin== '6' ? ' selected="selected"
													' : ''} value="6">6</option>
												<option ${listSesiones[3].horaFin== '7' ? ' selected="selected"
													' : ''} value="7">7</option>
												<option ${listSesiones[3].horaFin== '8' ? ' selected="selected"
													' : ''} value="8">8</option>
												<option ${listSesiones[3].horaFin== '9' ? ' selected="selected"
													' : ''} value="9">9</option>
												<option ${listSesiones[3].horaFin==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[3].horaFin==
													'11' ? ' selected="selected" ' : ''} value="11">11</option>
												<option ${listSesiones[3].horaFin==
													'12' ? ' selected="selected" ' : ''} value="12">12</option>
												<option ${listSesiones[3].horaFin==
													'13' ? ' selected="selected" ' : ''} value="13">13</option>
												<option ${listSesiones[3].horaFin==
													'14' ? ' selected="selected" ' : ''} value="14">14</option>
												<option ${listSesiones[3].horaFin==
													'15' ? ' selected="selected" ' : ''} value="15">15</option>
												<option ${listSesiones[3].horaFin==
													'16' ? ' selected="selected" ' : ''} value="16">16</option>
												<option ${listSesiones[3].horaFin==
													'17' ? ' selected="selected" ' : ''} value="17">17</option>
												<option ${listSesiones[3].horaFin==
													'18' ? ' selected="selected" ' : ''} value="18">18</option>
												<option ${listSesiones[3].horaFin==
													'19' ? ' selected="selected" ' : ''} value="19">19</option>
												<option ${listSesiones[3].horaFin==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[3].horaFin==
													'21' ? ' selected="selected" ' : ''} value="21">21</option>
												<option ${listSesiones[3].horaFin==
													'22' ? ' selected="selected" ' : ''} value="22">22</option>
												<option ${listSesiones[3].horaFin==
													'23' ? ' selected="selected" ' : ''} value="23">23</option>
										</select> : <select name="listSesiones[3].minutoFin" style="width: 40px;"
											id="minutoFin4">
												<option value="-1">--</option>
												<option ${listSesiones[3].minutoFin==
													'0' ? ' selected="selected" ' : ''} value="0">00</option>
												<option ${listSesiones[3].minutoFin==
													'10' ? ' selected="selected" ' : ''} value="10">10</option>
												<option ${listSesiones[3].minutoFin==
													'20' ? ' selected="selected" ' : ''} value="20">20</option>
												<option ${listSesiones[3].minutoFin==
													'30' ? ' selected="selected" ' : ''} value="30">30</option>
												<option ${listSesiones[3].minutoFin==
													'40' ? ' selected="selected" ' : ''} value="40">40</option>
												<option ${listSesiones[3].minutoFin==
													'50' ? ' selected="selected" ' : ''} value="50">50</option>
										</select> hrs.</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Instructor:" />
										</td>
										<td><s:textfield size="50" id="idInstuctor4"
												name="listSesiones[3].instructor" maxlength="80"
												onfocus="javascript:ayudasHelp(40);"
												onblur="javascript:ayudasHelpBlo(40);">
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay40" style="display:none;margin-top:-1px;"
												value="Ingrese el instructor de la sesión 4." />
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaCaptura"
												value="Dirección:" /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Calle:" />
										</td>
										<td><s:textfield size="30" id="calle4"
												name="listSesiones[3].domicilios.calle" maxlength="50"
												onfocus="javascript:ayudasHelp(41);"
												onblur="javascript:ayudasHelpBlo(41);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay41" style="display:none; margin-top:5px;"
												value="Escriba la calle de su empresa." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Número Exterior:" /></td>
										<td><s:textfield size="20" id="numExt4"
												name="listSesiones[3].domicilios.numExt" maxlength="20"
												onfocus="javascript:ayudasHelp(42);"
												onblur="javascript:ayudasHelpBlo(42);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay42" style="display:none; margin-top:5px;"
												value="Escriba el número exterior." /></td>
									</tr>
								</table></td>
							<td>
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="Número Interior:" /></td>
										<td><s:textfield size="20" id="numInt4"
												name="listSesiones[3].domicilios.numInt" maxlength="20"
												onfocus="javascript:ayudasHelp(43);"
												onblur="javascript:ayudasHelpBlo(43);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay43" style="display:none; margin-top:5px;"
												value="Escriba el número interior." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Piso:" />
										</td>
										<td><s:textfield size="30" id="piso4"
												name="listSesiones[3].domicilios.piso" maxlength="20"
												onfocus="javascript:ayudasHelp(44);"
												onblur="javascript:ayudasHelpBlo(44);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay44" style="display:none; margin-top:5px;"
												value="Escriba el piso." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Colonia:" /></td>
										<td><s:textfield size="30" id="colonia4"
												name="listSesiones[3].domicilios.colonia" maxlength="50"
												onfocus="javascript:ayudasHelp(45);"
												onblur="javascript:ayudasHelpBlo(45);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay45" style="display:none; margin-top:5px;"
												value="Escriba la colonia." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="* Delegación/Municipio:" /></td>
										<td><s:textfield size="30" id="delegacion4"
												name="listSesiones[3].domicilios.delegacion" maxlength="50"
												onfocus="javascript:ayudasHelp(46);"
												onblur="javascript:ayudasHelpBlo(46);"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay46" style="display:none; margin-top:5px;"
												value="Escriba Delegación o Municipio." /></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay47" style="display:none; margin-top:5px;"
												value="Seleccione el Estado ." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Estado:" />
										</td>
										<td><select id="estado4"
											name="listSesiones[3].domicilios.estado"
											style="width: 230px;" onfocus="javascript:ayudasHelp(47);"
											onblur="javascript:ayudasHelpBlo(47);">
												<option value="0">--Seleccione un estado--</option>
												<option ${listSesiones[3].domicilios.estado==
													'Aguascalientes' ? ' selected="selected"
													' : ''} value="Aguascalientes">Aguascalientes</option>
												<option ${listSesiones[3].domicilios.estado== 'Baja California Norte' ? ' selected="selected"
													' : ''} value="Baja California Norte">Baja California Norte</option>
												<option ${listSesiones[3].domicilios.estado== 'Baja California Sur' ? ' selected="selected"
													' : ''} value="Baja California Sur">Baja California Sur</option>
												<option ${listSesiones[3].domicilios.estado==
													'Campeche' ? ' selected="selected" ' : ''} value="Campeche">Campeche</option>
												<option ${listSesiones[3].domicilios.estado==
													'Chiapas' ? ' selected="selected" ' : ''} value="Chiapas">Chiapas</option>
												<option ${listSesiones[3].domicilios.estado==
													'Chihuahua' ? ' selected="selected"
													' : ''} value="Chihuahua">Chihuahua</option>
												<option ${listSesiones[3].domicilios.estado==
													'Coahuila' ? ' selected="selected" ' : ''} value="Coahuila">Coahuila</option>
												<option ${listSesiones[3].domicilios.estado==
													'Colima' ? ' selected="selected" ' : ''} value="Colima">Colima</option>
												<option ${listSesiones[3].domicilios.estado== 'Distrito Federal' ? ' selected="selected"
													' : ''} value="Distrito Federal">Distrito Federal</option>
												<option ${listSesiones[3].domicilios.estado==
													'Durango' ? ' selected="selected" ' : ''} value="Durango">Durango</option>
												<option ${listSesiones[3].domicilios.estado== 'Estado de Mexico' ? ' selected="selected"
													' : ''} value="Estado de Mexico">Estado de México</option>
												<option ${listSesiones[3].domicilios.estado==
													'Guanajuato' ? ' selected="selected"
													' : ''} value="Guanajuato">Guanajuato</option>
												<option ${listSesiones[3].domicilios.estado==
													'Guerrero' ? ' selected="selected" ' : ''} value="Guerrero">Guerrero</option>
												<option ${listSesiones[3].domicilios.estado==
													'Hidalgo' ? ' selected="selected" ' : ''} value="Hidalgo">Hidalgo</option>
												<option ${listSesiones[3].domicilios.estado==
													'Jalisco' ? ' selected="selected" ' : ''} value="Jalisco">Jalisco</option>
												<option ${listSesiones[3].domicilios.estado==
													'Michoacan' ? ' selected="selected"
													' : ''} value="Michoacan">Michoacán</option>
												<option ${listSesiones[3].domicilios.estado==
													'Morelos' ? ' selected="selected" ' : ''} value="Morelos">Morelos</option>
												<option ${listSesiones[3].domicilios.estado==
													'Nayarit' ? ' selected="selected" ' : ''} value="Nayarit">Nayarit</option>
												<option ${listSesiones[3].domicilios.estado== 'Nuevo
													Leon' ? ' selected="selected" ' : ''} value="Nuevo Leon">Nuevo
													León</option>
												<option ${listSesiones[3].domicilios.estado==
													'Oaxaca' ? ' selected="selected" ' : ''} value="Oaxaca">Oaxaca</option>
												<option ${listSesiones[3].domicilios.estado==
													'Puebla' ? ' selected="selected" ' : ''} value="Puebla">Puebla</option>
												<option ${listSesiones[3].domicilios.estado==
													'Queretaro' ? ' selected="selected"
													' : ''} value="Queretaro">Querétaro</option>
												<option ${listSesiones[3].domicilios.estado== 'Quintana Roo' ? ' selected="selected" ' : ''} 
													value="Quintana Roo">Quintana Roo</option>
												<option ${listSesiones[3].domicilios.estado== 'San Luis Potosi' ? ' selected="selected"
													' : ''} value="San Luis Potosi">San Luís Potosí</option>
												<option ${listSesiones[3].domicilios.estado==
													'Sinaloa' ? ' selected="selected" ' : ''} value="Sinaloa">Sinaloa</option>
												<option ${listSesiones[3].domicilios.estado==
													'Sonora' ? ' selected="selected" ' : ''} value="Sonora">Sonora</option>
												<option ${listSesiones[3].domicilios.estado==
													'Tabasco' ? ' selected="selected" ' : ''} value="Tabasco">Tabasco</option>
												<option ${listSesiones[3].domicilios.estado==
													'Tamaulipas' ? ' selected="selected"
													' : ''} value="Tamaulipas">Tamaulipas</option>
												<option ${listSesiones[3].domicilios.estado==
													'Tlaxcala' ? ' selected="selected" ' : ''} value="Tlaxcala">Tlaxcala</option>
												<option ${listSesiones[3].domicilios.estado==
													'Veracruz' ? ' selected="selected" ' : ''} value="Veracruz">Veracruz</option>
												<option ${listSesiones[3].domicilios.estado==
													'Yucatan' ? ' selected="selected" ' : ''} value="Yucatan">Yucatán</option>
												<option ${listSesiones[3].domicilios.estado==
													'Zacatecas' ? ' selected="selected"
													' : ''} value="Zacatecas">Zacatecas</option>
										</select></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* C.P.:" />
										</td>
										<td><s:textfield size="20" id="codigoPostal4"
												name="listSesiones[3].domicilios.codigoPostal" maxlength="5"
												onfocus="javascript:ayudasHelp(48);"
												onblur="javascript:ayudasHelpBlo(48);"
												onkeypress="return validaNumero(event)"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda"
												id="ayudasDisplay48" style="display:none; margin-top:5px;"
												value="Escriba el Código postal en que se encuentra." /></td>
									</tr>
								</table></td>
						</tr>
					</table>
					<br />
					<table>
						<tr>
							<td><s:label cssClass="etiquetaCaptura"
									value="* Información importante:" />
							</td>
						</tr>
						<tr>
							<td><s:textarea cols="110" rows="9" id="idInfo4"
									name="listSesiones[3].info" value="%{listSesiones[3].info}" maxlength="1000"
									onfocus="javascript:ayudasHelp(49);"
									onblur="javascript:ayudasHelpBlo(49);" /></td>
						</tr>
						<tr>
							<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay49"
									style="display:none;margin-top:-1px;"
									value="Ingrese la información importante" />
							</td>
						</tr>
					</table>
					<table width="99%; " class="submit_tabla">
						<tr>
							<s:hidden name="listSesiones[3].idDiplomado" id="idDiplomado" value="%{idDiplomado}" />
							<s:hidden name="listSesiones[3].domicilios.idDomicilio"
								value="%{listSesiones[3].domicilios.idDomicilio}"></s:hidden>
							<td align="center" style="width: 50%;"><input class="botonenviar" value="Anterior sesión"
								type="button" onclick="javascript:anterior(4);" /></td>
							<td align="center" style="width: 50%;"><input class="botonenviar" value="Guardar" type="button"
								onclick="javascript:finalizar(4);" /></td>
						</tr>
					</table>
				</div>
			</s:form>
		</div>
	</fieldset>
	<script type="text/javascript">
function validaNumero(evt) {
	var key = (document.all) ? evt.keyCode : evt.which;
	return (key <= 13 || (key >= 48 && key <= 57) || key == 46);
}
function finalizar(sesion){
	document.getElementById("numeroSesiones").value = sesion;
	if(validacion(sesion)){
		if(sesion<4){
			if(confirm("Se guardar solo los datos hasta la sesion "+ sesion
					+ "\n\n ¿Desea eliminar los datos de sesiones posteriores?")){
				document.sesiones.submit();
			}
		} else {
			document.sesiones.submit();
		}
	}
}
function siguiente(sesion){
	document.getElementById("numeroSesiones").value = sesion;
	if(validacion(document.getElementById("numeroSesiones").value)>0){
		document.getElementById("numeroSesiones").value = sesion + 1;
		document.getElementById("sesion"+sesion).style.display='none';
		document.getElementById("sesion"+(sesion+1)).style.display='block';
	}
}
function anterior(){
	sesionAct = document.getElementById("numeroSesiones").value;
	document.getElementById("sesion"+sesionAct).style.display='none';
	document.getElementById("sesion"+(sesionAct-1)).style.display='block';
	sesionAct = document.getElementById("numeroSesiones").value = sesionAct-1;
}
function validacion(sesion){
	idExpositor = document.getElementById("idExpositor"+sesion).value;
	idSala = document.getElementById("idSala"+sesion).value;
	ingreso = document.getElementById("ingreso"+sesion).value;
	hora = document.getElementById("hora"+sesion).value;
	minuto = document.getElementById("minuto"+sesion).value;
	horaFin = document.getElementById("horaFin"+sesion).value;
	minutoFin = document.getElementById("minutoFin"+sesion).value;
	idInstuctor = document.getElementById("idInstuctor"+sesion).value;
	calle = document.getElementById("calle"+sesion).value;
	numExt = document.getElementById("numExt"+sesion).value;
	numInt = document.getElementById("numInt"+sesion).value;
	piso = document.getElementById("piso"+sesion).value;
	colonia = document.getElementById("colonia"+sesion).value;
	delegacion = document.getElementById("delegacion"+sesion).value;
	estado = document.getElementById("estado"+sesion).value;
	codigoPostal = document.getElementById("codigoPostal"+sesion).value;
	idInfo = document.getElementById("idInfo"+sesion).value;
	if (idExpositor == null || idExpositor.length == 0
			|| /^\s+$/.test(idExpositor)) {
		document.getElementById("idExpositor"+sesion).focus();
		alert("Ingrese el expositor de la sesión");
		return false;
	} else if (idSala == null || idSala.length == 0
			|| /^\s+$/.test(idSala)) {
		document.getElementById("idSala"+sesion).focus();
		alert("Ingrese la sala de la sesión");
		return false;
	} else if (ingreso == null || ingreso.length == 0
			|| /^\s+$/.test(ingreso)) {
		document.getElementById("ingreso"+sesion).focus();
		alert("Ingrese la fecha de la sesión");
		return false;
	} else if (hora == null || hora < 0) {
		document.getElementById("hora"+sesion).focus();
		alert("Ingrese la hora de la sesión");
		return false;
	} else if (minuto == null || minuto < 0) {
		document.getElementById("minuto"+sesion).focus();
		alert("Ingrese el minuto de la sesión");
		return false;
	} else if (horaFin == null || horaFin < 0) {
		document.getElementById("horaFin"+sesion).focus();
		alert("Ingrese la hora final de la sesión.");
		return false;
	} else if (minutoFin == null || minutoFin < 0) {
		document.getElementById("minutoFin"+sesion).focus();
		alert("Ingrese el minuto final de la sesión.");
		return false;
	} else if (idInstuctor == null || idInstuctor.length == 0
			|| /^\s+$/.test(idInstuctor)) {
		document.getElementById("idInstuctor"+sesion).focus();
		alert("Ingrese el instructor de la sesión");
		return false;
	} else if (calle == null || calle.length == 0
				|| /^\s+$/.test(calle)) {
			document.getElementById("calle"+sesion).focus();
			alert("Ingrese la calle");
			return false;
	} else if (numExt == null || numExt.length == 0
			|| /^\s+$/.test(numExt)) {
		document.getElementById("numExt"+sesion).focus();
		alert("Ingrese el Número exterior");
		return false;
	} else if (colonia == null || colonia.length == 0
			|| /^\s+$/.test(colonia)) {
		document.getElementById("colonia"+sesion).focus();
		alert("Ingrese la colonia");
		return false;
	} else if (delegacion == null || delegacion.length == 0
			|| /^\s+$/.test(delegacion)) {
		document.getElementById("delegacion"+sesion).focus();
		alert("Ingrese la delegación");
		return false;
	} else if (estado == 0) {
		document.getElementById("estado"+sesion).focus();
		alert("Seleccione un Estado");
		return false;
	} else if (codigoPostal == null || codigoPostal.length == 0
			|| /^\s+$/.test(codigoPostal)) {
		document.getElementById("codigoPostal"+sesion).focus();
		alert("Ingrese el Código Postal");
		return false;
	} else if (idInfo == null || idInfo.length == 0
			|| /^\s+$/.test(idInfo)) {
		document.getElementById("idInfo"+sesion).focus();
		alert("Ingrese alguna información importante");
		return false;
	} else{
		return true;
	}
}


	function addParticipantes(){
		document.getElementById("participantes").style.display="none";
		document.getElementById("addParticipantes").style.display="block";
	}
	function validaLetra(e) {
		tecla = (document.all) ? e.keyCode : e.which;
		if (tecla==0) return true;
		if (tecla==8) return true;
	    patron =/[A-ZÑña-z\s]/;
	    te = String.fromCharCode(tecla);
	    return patron.test(te); 
	}
	function MenuDiplomadoAnio(){
		document.getElementById("year").value = document.getElementById("menuAnios").value;
		document.frmAnios.submit();
	}
	function validaChecInasistencia(){
		formulario = document.getElementById("inasistencias");
		for(var i=0; i<formulario.elements.length; i++) {
			var elemento = formulario.elements[i];
			if(elemento.type == "checkbox") {
			   if(elemento.checked) {
					return true;
			   }
			 }
		}
		alert("No se ha seleccionado ningun Asistente");
		return false;
		
	}
Calendar.setup({
	inputField : "ingreso1", // id del campo de texto
	ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
	// campo de texto
	button : "lanzador1" // el id del botón que lanzará el calendario
});
Calendar.setup({
	inputField : "ingreso2", // id del campo de texto
	ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
	// campo de texto
	button : "lanzador2" // el id del botón que lanzará el calendario
});
Calendar.setup({
	inputField : "ingreso3", // id del campo de texto
	ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
	// campo de texto
	button : "lanzador3" // el id del botón que lanzará el calendario
});
Calendar.setup({
	inputField : "ingreso4", // id del campo de texto
	ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
	// campo de texto
	button : "lanzador4" // el id del botón que lanzará el calendario
});
</script>
</body>
</html>