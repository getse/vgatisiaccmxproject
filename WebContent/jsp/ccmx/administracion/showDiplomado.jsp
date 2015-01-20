<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/css/diploma.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ayudas.js"></script>
<script src="${pageContext.request.contextPath}/js/ccmx.js" type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">	
	$(window).ready(function() {
		var contArchivos = document.getElementById("contArchivosPago").rows.length;
	    if( contArchivos > 2 ){
			document.getElementById('contArchivosPago').style.display = 'block';
		}
	});
</script>
</head>

<body>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-325px auto 0 250px';
</script>
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
			<td class="contenidoNota"><s:property value="mensaje.mensaje" /></td>
		</tr>
	</table>
</s:if>

<fieldset id="requerimientos">
	<legend id="tituloPag">
		<s:if test=" opcion == null ">
			<s:label value="Imprimir diploma" />
			<br /><br />
		</s:if>
		<s:elseif test=" opcion == 'Participantes' ">
			<s:label value="Inscripción a diplomado." />
			<br /><br />
			<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
			<br/>
		</s:elseif>
		<s:elseif test=" opcion == 'Inasistencias' ">
			<s:label value="Lista de inasistentes." />
			<br /><br />
		</s:elseif>
		<s:elseif test=" opcion == 'Diplomas' ">
			<s:label value="Diplomas." />
			<br /><br/>
			<s:label cssClass="camposObligatorios" value="Seleccione el Diploma que quiera imprimir." />
			<br />
		</s:elseif>
		<s:else>
			<s:label value="Diplomado en " />${tituloDiplomado}
			<br /><br/>
			<s:label cssClass="camposObligatorios" value="Seleccione una PyME." />
			<br />
		</s:else>
		<br />
	</legend>
	
	<!-- ADMINISTRAR SESIONES -->
	<s:if test=" opcion == 'SesionesAdmin' ">
		<table width="99%">
			<tr>
				<td align="center" style="width: 99%;">
					<input class="botonenviar" value="Menú principal" type="button" onclick="javascript:document.frmPrincipal.submit()"/>
				</td>
			</tr>
		</table>
		<br />
		<s:form name="frmAsistencias" action="diplomadoShow" namespace="/ccmx/administracion/diplomados" theme="simple">
			<s:hidden name="idDiplomado" value="%{idDiplomado}" />
			<s:hidden name="idPyME"  value="%{idPyME}" />				
		
			<table>
				<tr>
					<td>
						<table width="99%" cellspacing="1" cellpadding="1">
							<thead>
								<tr>
									<td colspan="3">&nbsp;</td>
									<td class="encabezado_tabla" align="center" colspan="4"><b>Confirmación de asistencia por sesión</b></td>
									<td class="encabezado_tabla" align="center" colspan="4"><b>Lista de asistencias	 por sesión</b></td>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
									<td class="encabezado_tabla" align="center"><b>No.</b></td>
									<td class="encabezado_tabla" align="center"><b>Telefono</b></td>
									<td class="encabezado_tabla" align="center"><b>Nombre del<br />Participante</b></td>
									<td class="encabezado_tabla" align="center"><b>1</b></td>
									<td class="encabezado_tabla" align="center"><b>2</b></td>
									<td class="encabezado_tabla" align="center"><b>3</b></td>
									<td class="encabezado_tabla" align="center"><b>4</b></td>
									<td class="encabezado_tabla" align="center"><b>1</b></td>
									<td class="encabezado_tabla" align="center"><b>2</b></td>
									<td class="encabezado_tabla" align="center"><b>3</b></td>
									<td class="encabezado_tabla" align="center"><b>4</b></td>
									<td class="encabezado_tabla" align="center"><b>Pago Realizado</b></td>
									<td class="encabezado_tabla" align="center"><b>Factura</b></td>
									<td class="encabezado_tabla" align="center"><b>Diploma</b></td>
									<td class="encabezado_tabla" align="center"><b>Selección de Participante</b></td>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="listParticipantes" status="stat">
									<s:hidden name="listParticipantes[%{#stat.count -1}].id" value="%{id}" />
									<s:hidden name="listParticipantes[%{#stat.count -1}].nombre" value="%{nombre}" />
									<s:hidden name="listParticipantes[%{#stat.count -1}].correoElectronico" value="%{correoElectronico}" />
									<s:hidden name="listParticipantes[%{#stat.count -1}].idSesion1" value="%{idSesion1}" />
									<s:hidden name="listParticipantes[%{#stat.count -1}].idSesion2" value="%{idSesion2}" />
									<s:hidden name="listParticipantes[%{#stat.count -1}].idSesion3" value="%{idSesion3}" />
									<s:hidden name="listParticipantes[%{#stat.count -1}].idSesion4" value="%{idSesion4}" />
									<s:hidden name="listParticipantes[%{#stat.count -1}].resagado" value="%{resagado}" />
									<tr>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${telefono}</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${nombre}</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<s:if test="%{editable1}">
												<s:checkbox name="listParticipantes[%{#stat.count -1}].confirmado1" value="%{confirmado1}" />
											</s:if>
											<s:else>&nbsp;</s:else>
										</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<s:if test="%{editable2}">
												<s:checkbox name="listParticipantes[%{#stat.count -1}].confirmado2" value="%{confirmado2}" />
											</s:if>
											<s:else>&nbsp;</s:else>
										</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<s:if test="%{editable3}">
												<s:checkbox name="listParticipantes[%{#stat.count -1}].confirmado3" value="%{confirmado3}" />
											</s:if>
											<s:else>&nbsp;</s:else>
										</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<s:if test="%{editable4}">
												<s:checkbox name="listParticipantes[%{#stat.count -1}].confirmado4" value="%{confirmado4}" />
											</s:if>
											<s:else>&nbsp;</s:else>
										</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<s:if test="%{editable1}">
												<s:checkbox name="listParticipantes[%{#stat.count -1}].asistencia1" value="%{asistencia1}" />
											</s:if>
											<s:else>&nbsp;</s:else>
										</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<s:if test="%{editable2}">
												<s:checkbox name="listParticipantes[%{#stat.count -1}].asistencia2" value="%{asistencia2}" />
											</s:if>
											<s:else>&nbsp;</s:else>
										</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<s:if test="%{editable3}">
												<s:checkbox name="listParticipantes[%{#stat.count -1}].asistencia3" value="%{asistencia3}" />
											</s:if>
											<s:else>&nbsp;</s:else>
										</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<s:if test="%{editable4}">
												<s:checkbox name="listParticipantes[%{#stat.count -1}].asistencia4" value="%{asistencia4}" />
											</s:if>
											<s:else>&nbsp;</s:else>
										</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<s:if test="%{pago}">&bull;</s:if>
										</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<s:if test="%{factura}">&bull;</s:if>
										</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<s:if test="%{diploma}">Generado</s:if>
										</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
											<s:checkbox name="listParticipantes[%{#stat.count -1}].seleccion" />
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
			<s:hidden name="opcion" value="AdminSesiones" />
			<s:hidden name="menuSeleccionado" id="menuSeleccionado2" value="0" />
			<table width="100%">
				<tr>
					<td align="center" style="width: 25%;">
						<s:submit cssClass="botonenviar" align="left" value="Guardar cambios" />
					</td>	
					<td align="center" style="width: 25%;">
						<input class="botonenviar" value="Enviar invitación" type="button" onclick="javascript: sendInvitacion();" />
					</td>
					<td align="center" style="width: 25%;">
						<input class="botonenviar" value="Generar Diploma" type="button" onclick="javascript: showDiplomas();" />
					</td>
					<td align="center" style="width: 25%;">
						<input class="botonenviar" value="Importar inasistencias" type="button" onclick="javascript: document.frmInasistencias.submit()" />
					</td>
				</tr>
			</table>
			<br /><br />
			<s:label cssClass="resultado" cssStyle="font-size: 20px;" value="Generar Lista de Asistentes" />
			<br /><br />
			<table width="100%">
				<tr>
					<td colspan="4">
						<s:label cssClass="etiquetaCaptura" value="Seleccione la sesión:" />
					</td>
				</tr>
				<tr>
					<td>
						<s:checkbox name="sesion1" id="sesion1" style="align:center;" />
						<s:label cssClass="etiquetaCaptura" value="Sesion 1" />
					</td>
					<td>
						<s:checkbox name="sesion2" id="sesion2" />
						<s:label cssClass="etiquetaCaptura" value="Sesion 2" />
					</td>
					<td>
						<s:checkbox name="sesion3" id="sesion3" />
						<s:label cssClass="etiquetaCaptura" value="Sesion 3" />
					</td>
					<td>
						<s:checkbox name="sesion4" id="sesion4" />
						<s:label cssClass="etiquetaCaptura" value="Sesion 4" />
					</td>
				</tr>
			</table>
			<br /><br />
			<table width="99%">
				<tr>
					<td align="center">
						<s:if test="%{salida==null}">	
							<a class="resultado" href="${pageContext.request.contextPath}/downDocs.do" >Descargar Archivo</a>
						</s:if>
						<s:else>
							<input class="botonenviar" value="Generar Lista de asistentes" type="button" onclick="javascript: listAsistentes();" />
						</s:else>
					</td>
				</tr>
			</table>
		</s:form>

		<s:form name="frmInasistencias" action="diplomadoShow" namespace="/ccmx/administracion/diplomados" theme="simple">
			<s:hidden name="idDiplomado" value="%{idDiplomado}" />
			<s:hidden name="idPyme" value="%{idPyme}" />
			<s:hidden name="opcion" value="Inasistencias" />
		</s:form>
	</s:if>
	
	<!-- IMPRIME DIPLOMA -->
	<s:if test=" opcion == null ">
		<s:form action="" namespace="" theme="simple">
			<div id="contenedorDiploma">
				<div id="contNombre">
					<s:label value="%{participante}" />
				</div>
				<div id="contTema">
					<s:label value="%{tituloDiplomado}" />
				</div>
				<div id="contFirmas">
					<div id="firmaUno">
						<label class="nombreFirma">Iván Rivas Rodríguez</label>
						<br />
						<label class="puestoFirma">Director General del CCMX</label>
					</div>
					<div id="firmaDos">
						<label class="nombreFirma">Oscar Arturo Camarena</label>
						<br />
						<label class="puestoFirma">Capacitación Corporativo Ventas <br /> LALA</label>
					</div>
				</div>
			</div>
			<table id="botonesDiploma" width="99%">
				<tr>
					<td align="center" style="width: 50%;">
						<input class="botonenviar" value="Imprimir" type="button" onkeypress="javascript: printpage('contenedorDiploma');" onclick="javascript: printpage('contenedorDiploma');"/>
					</td>
					<td align="center" style="width: 50%;">
						<input class="botonenviar" value="Menú principal" type="button" onclick="javascript:document.frmPrincipal.submit()"/>
					</td>
				</tr>
			</table>
		</s:form>
	</s:if>

	<!-- DIPLOMAS -->
	<s:if test=" opcion == 'Diplomas' ">
		<table width="99%">
			<tr>
				<td align="center" style="width: 99%;">
					<input class="botonenviar" value="Menú principal" type="button" onclick="javascript:document.frmPrincipal.submit()"/>
				</td>
			</tr>
		</table>
		<table width="99%">
			<thead>
				<tr>
					<td class="encabezado_tabla" align="center"><b>No.</b></td>
					<td class="encabezado_tabla" align="center"><b>Nombre</b></td>
					<td class="encabezado_tabla" align="center"><b>Imprimir</b></td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="listDiplomas" status="stat">
					<s:hidden name="listInacistencias[%{#stat.count -1}].id" value="%{id}" />
					<tr>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${nombre}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
							<a href="${pageContext.request.contextPath}/ccmx/administracion/diplomados/diplomadoShow.do?tituloDiplomado=${tituloDiplomado}&participante=${nombre}">Imprimir</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	
	<!-- IMPORTAR INASISTENCIAS -->
	<s:if test=" opcion == 'Inasistencias' ">
		<s:form name="inasistencias" id="inasistencias" action="diplomadoShow" namespace="/ccmx/administracion/diplomados" theme="simple" onsubmit="return validaChecInasistencia()">
			<s:hidden name="idDiplomado" value = "%{idDiplomado}" />
			<s:hidden name="opcion" value="%{opcion}" />
			<table width="99%">
				<tr>
					<td align="center" style="width: 99%;">
						<input class="botonenviar" value="Menú principal" type="button" onclick="javascript:document.frmPrincipal.submit()"/>
					</td>
				</tr>
			</table>
			<table width="99%">
				<thead>
					<tr>
						<td class="encabezado_tabla" align="center"><b>No.</b></td>
						<td class="encabezado_tabla" align="center"><b>Nombre</b></td>
						<td class="encabezado_tabla" align="center"><b>Correo Electrónico</b></td>
						<td class="encabezado_tabla" align="center"><b>Teléfono</b></td>
						<td class="encabezado_tabla" align="center"><b>Tema</b></td>
						<td class="encabezado_tabla" align="center"><b>Pyme</b></td>
						<td class="encabezado_tabla" align="center"><b>Confirmado 1</b></td>
						<td class="encabezado_tabla" align="center"><b>Confirmado 2</b></td>
						<td class="encabezado_tabla" align="center"><b>Confirmado 3</b></td>
						<td class="encabezado_tabla" align="center"><b>Confirmado 4</b></td>
						<td class="encabezado_tabla" align="center"><b>Sesión</b></td>
						<td class="encabezado_tabla" align="center"><b>Invitación</b></td>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="listInacistencias" status="stat">
						<s:hidden name="listInacistencias[%{#stat.count -1}].id" value="%{id}" />
						<s:hidden name="listInacistencias[%{#stat.count -1}].sesion" value="%{sesion}" />
						<s:hidden name="listInacistencias[%{#stat.count -1}].idUsuario" value="%{idUsuario}" />
						<s:hidden name="listInacistencias[%{#stat.count -1}].tema" value="%{tema}" />
						<s:hidden name="listInacistencias[%{#stat.count -1}].correoElectronico" value="%{correoElectronico}" />
						<s:hidden name="listInacistencias[%{#stat.count -1}].asistencia1" value="%{asistencia1}" />
						<s:hidden name="listInacistencias[%{#stat.count -1}].asistencia2" value="%{asistencia2}" />
						<s:hidden name="listInacistencias[%{#stat.count -1}].asistencia3" value="%{asistencia3}" />
						<s:hidden name="listInacistencias[%{#stat.count -1}].asistencia4" value="%{asistencia4}" />
						<tr>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${nombre}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${correoElectronico}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${telefono}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${tema}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${pyme}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
								<s:if test="%{confirmado1}">&bull;</s:if>
							</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
								<s:if test="%{confirmado2}">&bull;</s:if>
							</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
								<s:if test="%{confirmado3}">&bull;</s:if>
							</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
								<s:if test="%{confirmado4}">&bull;</s:if>
							</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${sesion}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
								<s:checkbox name="listInacistencias[%{#stat.count -1}].invitacion"></s:checkbox>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<table width="100%">
				<tr>
					<td align="center"><s:submit cssClass="botonenviar" align="left" value="Enviar invitación" /></td>
				</tr>
			</table>
		</s:form>
	</s:if>

	<!-- ADMINISTRAR PAGOS Y FACTURACION -->
	<s:if test=" opcion == 'InPyme' ">
		<s:form name="selectPyME" action="diplomadoShow" namespace="/ccmx/administracion/diplomados" theme="simple">
			<s:hidden name="idDiplomado" value="%{idDiplomado}" />
			<s:hidden name="opcion" value="%{opcion}" />

			<table width="99%">
				<tr>
					<td align="center" style="width: 99%;">
						<s:label cssClass="etiquetaCaptura" value="* Seleccionar PyME: " />
						<select name="idPyME" onchange="javascript:document.selectPyME.submit();">
							<option value="-1">--Seleccione PyME--</option>
							<s:iterator value="pyMEsList" status="stat">
								<option value="${idUsuario}">${nombreComercial}</option>
							</s:iterator>
						</select>
					</td>
				</tr>
			</table>
		</s:form>
	</s:if>

	<s:if test=" opcion == 'Pagos' ">
		<br />
		<div id="frmConfirmacion" >
			<s:form name="frmConfirmacion" action="diplomadoShow" namespace="/ccmx/administracion/diplomados" theme="simple" enctype="multipart/form-data" method="post" onsubmit="javascript: return validaAsistentesDip();">
				<table width="99%">
					<tr>
						<td align="center" style="width: 99%;">
							<input class="botonenviar" value="Menú principal" type="button" onclick="javascript:document.frmPrincipal.submit()"/>
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="PyME: " /></td>
						<td ><s:label cssClass="resultado" value="%{listParticipantes[0].pyme}" /></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="Grandes Empresas: " /></td>
						<td><s:label cssClass="resultado" value="%{listParticipantes[0].tractora}" /></td>
					</tr>
				</table>
				<br />
				<s:hidden name="idDiplomado" value="%{idDiplomado}" />
				<s:hidden name="idPyME"  value="%{idPyME}" />
				<s:hidden id="labIdDiplomado" name="serviciosDiplomado.idServiciosDiplomado" value="%{serviciosDiplomado.idServiciosDiplomado}" />			
				<table>
					<tr>
						<td>
							<table width="99%" cellspacing="1" cellpadding="1">
								<thead>									
									<tr>
										<td colspan="4">&nbsp;</td>
										<td class="encabezado_tabla" align="center" colspan="4"><b>Confirmación de asistencia por sesión</b></td>
										<td class="encabezado_tabla" align="center" colspan="4"><b>Lista de asistencias	 por sesión</b></td>
									</tr>
									<tr>
										<td class="encabezado_tabla" align="center"><b>No.</b></td>
										<td class="encabezado_tabla" align="center"><b>Telefono</b></td>
										<td class="encabezado_tabla" align="center"><b>Nombre del<br />Participante</b></td>
										<td class="encabezado_tabla" align="center"><b>Correo<br />electrónico</b></td>
										<td class="encabezado_tabla" align="center"><b>1</b></td>
										<td class="encabezado_tabla" align="center"><b>2</b></td>
										<td class="encabezado_tabla" align="center"><b>3</b></td>
										<td class="encabezado_tabla" align="center"><b>4</b></td>
										<td class="encabezado_tabla" align="center"><b>1</b></td>
										<td class="encabezado_tabla" align="center"><b>2</b></td>
										<td class="encabezado_tabla" align="center"><b>3</b></td>
										<td class="encabezado_tabla" align="center"><b>4</b></td>
										<td class="encabezado_tabla" align="center"><b>Pago</b></td>
										<td class="encabezado_tabla" align="center"><b>Capturar factura</b></td>
										<td class="encabezado_tabla" align="center"><b>No. de archivo</b></td>
										<td class="encabezado_tabla" align="center"><b>Selección de Participante</b></td>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="listParticipantes" status="stat">
										<s:hidden name="listParticipantes[%{#stat.count -1}].id" value="%{id}" />
										<tr>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${telefono}</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${nombre}</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${correoElectronico}</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:if test="%{confirmado1}">&bull;</s:if>
												<s:else>&nbsp;</s:else>
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:if test="%{confirmado2}">&bull;</s:if>
												<s:else>&nbsp;</s:else>
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:if test="%{confirmado3}">&bull;</s:if>
												<s:else>&nbsp;</s:else>
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:if test="%{confirmado4}">&bull;</s:if>
												<s:else>&nbsp;</s:else>
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:if test="%{asistencia1}">&bull;</s:if>
												<s:else>&nbsp;</s:else>
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:if test="%{asistencia2}">&bull;</s:if>
												<s:else>&nbsp;</s:else>
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:if test="%{asistencia3}">&bull;</s:if>
												<s:else>&nbsp;</s:else>
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:if test="%{asistencia4}">&bull;</s:if>
												<s:else>&nbsp;</s:else>
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:checkbox name="listParticipantes[%{#stat.count -1}].pago" value="%{pago}" />
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:if test="%{numPago=='null'}">
													<s:textfield maxlength="30" name="listParticipantes[%{#stat.count -1}].numPago" value=""></s:textfield>
												</s:if>
												<s:else>
													<s:textfield maxlength="30" name="listParticipantes[%{#stat.count -1}].numPago" value="%{numPago}"></s:textfield>
												</s:else>
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:if test="%{numFile=='null'}">
													<s:textfield maxlength="15" name="listParticipantes[%{#stat.count -1}].numFile" value=""></s:textfield>
												</s:if>
												<s:else>
													<s:textfield maxlength="15" name="listParticipantes[%{#stat.count -1}].numFile" value="%{numFile}"></s:textfield>
												</s:else>
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:checkbox name="listParticipantes[%{#stat.count -1}].seleccion" />
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
				<br />
				<table id="contArchivosPago" width="99%" style="display: none;">
					<tr>
						<td class="encabezadoTablaResumen" colspan="4" align="center" style="width: 800px;">Descripción de los archivos adjuntos</td>
					</tr>
					<tr>
						<td class="cuerpo2TablaResumen" align="center" style="width: 10%;">&nbsp;No. archivo</td>
						<td class="cuerpo2TablaResumen" align="center" style="width: 35%;">&nbsp;Descripción del archivo</td>
						<td class="cuerpo2TablaResumen" align="center" style="width: 40%;">&nbsp;Descargar archivo adjunto</td>
						<td class="cuerpo2TablaResumen" align="center" style="width: 15%;">&nbsp;Eliminar archivo</td>
					</tr>
					<s:iterator value="listDocumentos" status="stat">
						<tr id="archPago${stat.count}">
							<td class="cuerpo1TablaResumen" align="center">${idArchivo}</td>
							<td class="cuerpo1TablaResumen" align="left">${descripcionArchivo}</td>
							<td class="cuerpo1TablaResumen" align="left">${nombre}</td>
							<td class="cuerpo1TablaResumen" align="center">
								<label class="quitar" onclick="javascript:supArchivoTabla(${idArchivo}, ${stat.count});">-eliminar</label>
							</td>
						</tr>
					</s:iterator>
				</table>
				<div id="contAyudaDelete" style="display: none; margin-left: 10px;" >
					<s:label cssClass="etiquetaAyuda" value="Recuerde que para concluir el proceso para eliminar archivos debe ejecutar el botón 'Registrar datos'." />
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
				<label id="labAddAsistente" class="agregar" onclick="javascript: addArchivo();">+adjuntar archivo</label>
				<br />
				<table width="99%">
					<tr>
						<s:hidden name="opcion" value="Facturacion"/>
						<td align="center" style="width: 50%;">
							<input class="botonenviar" value="Solicitar facturación" type="button" onclick="javascript:solicitarFactura()"/>
						</td>
						<td align="center" style="width: 50%;">
							<s:hidden id="menuSeleccionado" name="menuSeleccionado" value="2" />						
							<s:submit cssClass="botonenviar" align="left" value="Guardar cambios" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</s:if>

	<!-- AGREGAR PARTICIPANTES -->
	<s:if test=" opcion == 'InPyme2' ">
		<s:form name="frmSelectParticiapnte" action="diplomadoShow" namespace="/ccmx/administracion/diplomados" theme="simple">
			<s:hidden name="idDiplomado" value="%{idDiplomado}" />
			<s:hidden name="opcion" value="%{opcion}" />
			<table width="99%">
				<tr>
					<td align="center" style="width: 99%;">
						<s:label cssClass="etiquetaCaptura" value="* Seleccionar PyME: " />
						<select name="idPyME" onchange="javascript:document.frmSelectParticiapnte.submit()">
							<option value="-1">--Seleccione PyME--</option>
							<s:iterator value="pyMEsList" status="stat">
								<option value="${idUsuario}">${nombreComercial}</option>
							</s:iterator>
						</select>
					</td>
				</tr>
			</table>
		</s:form>
	</s:if>
	
	<s:if test=" opcion == 'Participantes' ">
		<table width="99%">
			<tr>
				<td align="center" style="width: 99%;">
					<input class="botonenviar" value="Menú principal" type="button" onclick="javascript:document.frmPrincipal.submit()"/>
				</td>
			</tr>
		</table>
		<s:form name="" action="diplomadosShow" namespace="/ccmx/administracion/diplomados" theme="simple" onsubmit="return validaAsistentes()">
			<s:hidden name="idPyME"  value="%{idPyME}" />
			<s:hidden name="idDiplomado" value="%{idDiplomado}" />
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
								<td class="cuerpo2TablaResumen" align="center" style="width: 10%"><b>Pago</b></td>
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
										<s:if test="serviciosDiplomado.asistentes[%{#stat.index}].pago == true">
											<s:label id="labPago%{#stat.count}" cssClass="etiquetaCaptura" value="Pagado" />
										</s:if>
										<s:else>
											<s:label id="labPago%{#stat.count}" cssClass="etiquetaCaptura" value="Pendiente" />
										</s:else>
											<s:hidden id="pagoHid%{#stat.count}" name="serviciosDiplomado.asistentes[%{#stat.index}].pago" value="%{serviciosDiplomado.asistentes[#stat.index].pago}" />
									</td>
									<td class="cuerpo1TablaResumen" align="center">
										<label class="quitar" onclick="editAsistente(${stat.count});">-editar</label>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<br />
					<label class="agregar" id="labShowForm" onclick="javascript: showFormAsistente();">+agregar otro asistente</label>
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
										<td colspan="2"><s:label cssClass="etiquetaCaptura" value="* Teléfono:" /></td>
									</tr>
									<tr>
										<td colspan="2">
											<table width="99%">
												<tr>
													<td>
														<s:label cssClass="etiquetaCaptura" value="Lada :" />
													</td>
													<td>
														<s:label id="intTel" cssClass="resultado" value="52" />&nbsp;&nbsp;
														<s:textfield size="2" id="ladaTel" name="ladaTel" maxlength="3" onkeypress="javascript: return validaNumero(event);" ></s:textfield>
													</td>
													<td>
														<s:label cssClass="etiquetaCaptura" value="Núm:" />
													</td>
													<td style="width: 28%;">
														<s:textfield size="13" id="numTel" name="numTel" maxlength="8" onkeypress="javascript: return validaNumero(event);" ></s:textfield>
													</td>
													<td style="width: 5%;">
														<s:label cssClass="etiquetaCaptura" value="Ext:" />
													</td>
													<td style="width: 15%;">
														<s:textfield size="4" id="extTel" name="extTel" maxlength="4" onkeypress="return validaNumero(event)"></s:textfield>
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
						<tr>
							<td colspan="2"></td>
						</tr>						
					</table>
					<s:hidden id="posTabla" name="posTabla" value="" />
					<br />
					<table width="99%">
						<tr>
							<td style="text-align: left;">
								<div id="AgregarAsistenteDiv">
								<label id="labAddAsistente" style="display: block;" class="agregar" onclick="javascript: addAsistente();">+registrar asistente</label>
								</div>
							</td>
							<td style="text-align: right;">
								<label id="labCancelaAsistente" class="quitar" style="display: none;" onclick="javascript: cancelaRegAsistente();">-cancelar registro</label>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<label id="labFinEdit" style="display: none;" class="agregar" onclick="javascript: finEditAsistente();">-finalizar edición</label>
							</td>
						</tr>
					</table>
				</div>
				<table width="99%">
					<tr>
						<td align="center" style="width: 100%;">
						<s:submit cssClass="botonenviar" align="left" value="Guardar Asistentes" /></td>
					</tr>
				</table>
			</div>
		</s:form>
	</s:if>
		
	<s:form name="frmPrincipal" action="diplomadosShow" namespace="/ccmx/administracion/diplomados" theme="simple">
		<s:hidden name="idDiplomado" value="%{idDiplomado}"></s:hidden>
	</s:form>
</fieldset>
<script>
	function printpage(){
		window.print();
	}	
</script>
</body>
</html>