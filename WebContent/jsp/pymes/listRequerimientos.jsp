<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link
	href="${pageContext.request.contextPath}/css/calendario.css"
	rel="stylesheet"
	type="text/css" />
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar.js"></script>
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar-es.js"></script>
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar-setup.js"></script>
</head>

<body>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-195px auto 0 250px';
</script>

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
	
	<s:form action="pymeRequerimientosShow" namespace="/pyme" theme="simple" onsubmit="return validacion()">
	<s:if test="idRequerimiento == 0">
	<legend>
		<s:label value="Búsqueda de Requerimientos" />
		<br /> <br />
		<s:label cssClass="camposObligatorios"
			value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
	</legend>	
	<script type="text/javascript">
		setTimeout("calendario()", 500);
	</script>
		<s:label cssClass="etiquetaCaptura" value="* Busqueda por palabra" />
		<s:textfield size="60" id="busqueda" name="busqueda" maxlength="60"></s:textfield>
		<br />
		<s:label cssClass="etiquetaAyuda" value="Escriba en 3 palabras el producto" />
		<br />
		<br />
		<s:label cssClass="etiquetaCaptura" value="Filtro por tractora" />
		<select id="tractoraReq" name="tractoraReq">
			<option value="-1">TODAS</option>
			<s:iterator value="listTractoras">
				<option  value="${empresa}">${empresa}</option>
			</s:iterator>
		</select>
		<br />
		<br />
		<s:label cssClass="etiquetaCaptura" value="Filtro por fecha desde:" />
		<s:date name="fechaDesde" id="fDesde" format="dd/MM/yyyy" />
		<s:textfield class="calendario" id="ingreso" name="fechaDesde" value="%{fDesde}" size="10" maxlength="10" />
		<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador" style="cursor: hand"></img>
		<br />
		<br />
		<s:label cssClass="etiquetaCaptura" value="Filtro por fecha hasta:" />
		<s:date name="fechaHasta" id="fHasta" format="dd/MM/yyyy" />
		<s:textfield class="calendario" id="ingreso2" name="fechaHasta" value="%{fHasta}" size="10" maxlength="10" />
		<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador2" style="cursor: hand"></img>
		<br />
		<s:submit cssClass="botonenviar" align="left" value="Buscar" />
		<br /><br />
	</s:if>
		<!-- Lista busqueda -->
		<s:if test="busqueda != null || (requerimientos.idRequerimiento != 0 && busqueda != null)">
			<s:label cssClass="camposObligatorios"
					value="Seleccione el requerimiento que desea consultar." />
			<br />
			<table>
				<tr>
					<td>
						<table width="800px" cellspacing="1" cellpadding="1">
							<thead>
								<tr>
									<td class="encabezado_tabla" align="center"><b>Nombre Tractora</b></td>
									<td class="encabezado_tabla" align="center"><b>Parte inicial requerimiento</b></td>
									<td class="encabezado_tabla" align="center"><b>Acción</b></td>
								</tr>
							</thead>
							<tbody>
							
								<s:iterator value="listRequerimientos" status="stat">
										<tr>
											<td
												class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
												align="center">${nombreTractora}</td>
											<td
												class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
												align="center">${descripcion}</td>
											<td
												class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
												align="center">
												<a href="${pageContext.request.contextPath}/pyme/pymeRequerimientosShow.do?idRequerimiento=${idRequerimiento}">Ver Requerimiento</a></td>
										</tr>
								</s:iterator>
							
							</tbody>
						</table>
					</td>
				</tr>
			</table>
			<br />
			<br />
		</s:if>
		<s:if test="requerimientos.idRequerimiento != 0">
			<legend>
				<s:label value="Respuesta Requerimiento" />
				<br /> <br />
				<s:label cssClass="camposObligatorios"
					value="Para responder al requerimieno seleccione la opción 'Responder Requerimiento." />
			</legend>
			<br />	
			<div id="muestraReq">
				<s:hidden id="idShowReq" name="idRequerimiento" value="%{idRequerimiento}" />
				<table>
					<tr>
						<td
							class="encabezadoTablaResumen"
							colspan="2"
							align="center">Requerimiento</td>
					</tr>
					<tr>
						<td class="cuerpo1TablaResumen">
							&nbsp;Nombre tractora:
						</td>
					</tr>
					<tr>
						<td class="cuerpo1TextoResumen">
							<s:label cssClass="etiquetaResumen" value="%{requerimientos.nombreTractora}" />
						</td>
					</tr>
					<tr>
						<td class="cuerpo2TablaResumen">
							&nbsp;Descripción completa del requerimiento:
						</td>
					</tr>
					<tr>
						<td class="cuerpo1TextoResumen">
							<s:label cssClass="etiquetaResumen" value="%{requerimientos.descripcion}" />
						</td>
					</tr>
					
					
					<tr>
						<td colspan="2">
							<input class="botonenviar" value="Responder Requerimiento" type="button" onclick="responderReq();" />
						</td>
					</tr>
				</table>
			</div>		
		</s:if>
	</s:form>
		<div id="respuesta" style="display: none;">
				<s:form
					name="frmRespuesta"
					action="pymeRequerimientosSave"
					namespace="/pyme"
					enctype="multipart/form-data"
					onsubmit="return respuesta();"
					method="post"
					theme="simple">
				<table>
					<s:hidden
						id="idFrmRespId"
						name="respuesta.idRequerimiento"
						value="%{idRequerimiento}" />
					<s:hidden
						id="idFrmRespMsj"
						name="respuesta.mensajeEnvio"
						value="Envio exitoso de su cotizacion" />
					<tr>
						<td>
							<s:label cssClass="camposObligatorios"
									value="Recuerde que una vez enviada la cotización no se podrá modificar." />
						</td>
					</tr>
					<tr>
						<td>
							<br />
							<s:label cssClass="etiquetaCaptura" value="* Informacion General" />
						</td>
					</tr>
					<tr>
						<td>
							<s:textfield size="60" id="infoReq" name="respuesta.informacion" maxlength="60"></s:textfield>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<s:label
								cssClass="etiquetaCaptura"
								value="Incluir archivo(s):" /> <label
							class="agregar"
							onclick="javascript:otroArchivo();">+agregar otro</label> <br />
							<div
								id="idDivArchivo1Block"
								${respuesta.archivo1FileName==null?' style="display: block;"':' style="display: none;"'}>
								<s:file 
									id="idCampoArchivo1"
									name="respuesta.archivo1">
								</s:file>
								<br />
							</div>
							<div
								id="idDivArchivo2Block"
								${respuesta.archivo2FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo2"
									name="respuesta.archivo2">
								</s:file>
								<br />
							</div>
							<div
								id="idDivArchivo3Block"
								${respuesta.archivo3FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo3"
									name="respuesta.archivo3">
								</s:file>
								<br />
							</div>
							<div
								id="idDivArchivo4Block"
								${respuesta.archivo4FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo4"
									name="respuesta.archivo4">
								</s:file>
								<br />
							</div>
							<div
								id="idDivArchivo5Block"
								${respuesta.archivo5FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo5"
									name="respuesta.archivo5">
								</s:file>
								<br />
							</div>
							<div
								id="idDivArchivo6Block"
								${respuesta.archivo6FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo6"
									name="respuesta.archivo6">
								</s:file>
								<br />
							</div>
							<div
								id="idDivArchivo7Block"
								${respuesta.archivo7FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo7"
									name="respuesta.archivo7">
								</s:file>
								<br />
							</div>
							<div
								id="idDivArchivo8Block"
								${respuesta.archivo8FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo8"
									name="respuesta.archivo8">
								</s:file>
								<br />
							</div>
							<div
								id="idDivArchivo9Block"
								${respuesta.archivo9FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo9"
									name="respuesta.archivo9">
								</s:file>
								<br />
							</div>
							<div
								id="idDivArchivo10Block"
								${respuesta.archivo10FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo10"
									name="respuesta.archivo10">
								</s:file>
								<br />
							</div>
							<div
								id="idDivFil"
								style="display: block; margin-bottom: 0px; margin-top: -5px;">
								<s:label
									cssClass="etiquetaAyuda"
									value="Indique el o los archivos que serán incluidos. Máximo 2MB (.pdf .doc .png)" />
								<br />
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<br />
							<s:submit cssClass="botonenviar" value="Enviar respuesta" />
						</td>
					</tr>
				</table>
				</s:form>
		</div>
</fieldset>

<script type="text/javascript">
	function calendario() {
		Calendar.setup({
			inputField : "ingreso", // id del campo de texto
			ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
			// campo de texto
			button : "lanzador" // el id del botón que lanzará el calendario
		});
		Calendar.setup({
			inputField : "ingreso2", // id del campo de texto
			ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
			// campo de texto
			button : "lanzador2" // el id del botón que lanzará el calendario
			});
	}

	function otroArchivo() {
		var sizeF = 1;

		for ( var i = 1; i < 11; i++) {
			_block = document.getElementById('idDivArchivo' + i + 'Block').style.display;
			if (_block == 'block') {
				sizeF++;
			}
		}
		document.getElementById('idDivArchivo' + sizeF + 'Block').style.display = 'block';
	}

	function responderReq(){
		divRespuesta = document.getElementById("respuesta");
	    divRespuesta.style.display = "block";
	    
	    divRespuesta = document.getElementById("muestraReq");
	    divRespuesta.style.display = "none";
	}
	
	function validacion(){
		
		valorBusq = document.getElementById("busqueda").value.split(" ");
		
		
		if( valorBusq == null || valorBusq == 0 || valorBusq.length > 3 || valorBusq == " " ) {
			document.getElementById("busqueda").focus();
			alert("Escriba la(s) palabra(s) que identifican el producto que busca");
			return false;
		}
			
		return true;
	}
	
	function respuesta() {
		document.getElementById('idFrmRespInfo').value = document.getElementById('infoReq').value;
		
		valorRespReq = document.getElementById("infoReq").value;
		if( valorRespReq == null || valorRespReq == 0 || valorRespReq == " " ) {
			alert("El campo Informacion General es obligatorio");
			return false;
		}else{
			return true;
		}
	}
	
</script>
</body>
</html>
