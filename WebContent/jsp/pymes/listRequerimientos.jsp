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
		<br />
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
		<s:label cssClass="etiquetaCaptura" value="Entidad Federativa:" />
			<select id="estado" name="estado" style="width: 200px;">
				<s:if test="estado == null">
					<option selected="selected" value="Seleccione un estado">--Seleccione un estado--</option>
				</s:if>
				<s:else>
					<option value=""></option>
					<option selected="selected" value="${estado}"><s:text name="estado" /></option>
				</s:else>
				<option value="Aguascalientes">Aguascalientes</option>
				<option value="Baja California">Baja California</option>
				<option value="Baja California Sur">Baja California Sur</option>
				<option value="Campeche">Campeche</option>
				<option value="Chiapas">Chiapas</option>
				<option value="Chihuahua">Chihuahua</option>
				<option value="Coahuila">Coahuila</option>
				<option value="Colima">Colima</option>
				<option value="Distrito Federal">Distrito Federal</option>
				<option value="Durango">Durango</option>
				<option value="Guanajuato">Guanajuato</option>
				<option value="Guerrero">Guerrero</option>
				<option value="Hidalgo">Hidalgo</option>
				<option value="Jalisco">Jalisco</option>
				<option value="Estado de Mexico">Estado de Mexico</option>
				<option value="Michoacan">Michoacan</option>
				<option value="Morelos">Morelos</option>
				<option value="Nayarit">Nayarit</option>
				<option value="Nuevo Leon">Nuevo Leon</option>
				<option value="Oaxaca">Oaxaca</option>
				<option value="Puebla">Puebla</option>
				<option value="Quertaro">Quertaro</option>
				<option value="Quintana Roo">Quintana Roo</option>
				<option value="San Luis Potosi">San Luis Potosi</option>
				<option value="Sinaloa">Sinaloa</option>
				<option value="Sonora">Sonora</option>
				<option value="Tabasco">Tabasco</option>
				<option value="Tamaulipas">Tamaulipas</option>
				<option value="Tlaxcala">Tlaxcala</option>
				<option value="Veracruz">Veracruz</option>
				<option value="Yucatan">Yucatan</option>
				<option value="Zacatecas">Zacatecas</option>
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
									<td class="encabezado_tabla" align="center"><b>No.</b></td>
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
												align="center">${stat.count}</td>
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
					<s:if test="requerimientos.idArchivo1!=0">
						<tr>
							<td
								class="cuerpo2TablaResumen"
								align="left">&nbsp;Archivo anexo 1:</td>
							<td class="cuerpo1TextoResumen"><a
								href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo1}&nameArchivo=${requerimientos.archivo1FileName}&mimeArchivo=${requerimientos.archivo1ContentType}">${requerimientos.archivo1FileName}</a>
							</td>
						</tr>
					</s:if>
					<s:if test="requerimientos.idArchivo2!=0">
						<tr>
							<td
								class="cuerpo1TablaResumen"
								align="left">&nbsp;Archivo anexo 2:</td>
							<td class="cuerpo1TextoResumen"><a
								href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo2}&nameArchivo=${requerimientos.archivo2FileName}&mimeArchivo=${requerimientos.archivo2ContentType}">${requerimientos.archivo2FileName}</a>
							</td>
						</tr>
					</s:if>
					<s:if test="requerimientos.idArchivo3!=0">
						<tr>
							<td
								class="cuerpo2TablaResumen"
								align="left">&nbsp;Archivo anexo 3:</td>
							<td class="cuerpo1TextoResumen"><a
								href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo3}&nameArchivo=${requerimientos.archivo3FileName}&mimeArchivo=${requerimientos.archivo3ContentType}">${requerimientos.archivo3FileName}</a>
							</td>
						</tr>
					</s:if>
					<s:if test="requerimientos.idArchivo4!=0">
						<tr>
							<td
								class="cuerpo1TablaResumen"
								align="left">&nbsp;Archivo anexo 4:</td>
							<td class="cuerpo1TextoResumen"><a
								href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo4}&nameArchivo=${requerimientos.archivo4FileName}&mimeArchivo=${requerimientos.archivo4ContentType}">${requerimientos.archivo4FileName}</a>
							</td>
						</tr>
					</s:if>
					<s:if test="requerimientos.idArchivo5!=0">
						<tr>
							<td
								class="cuerpo2TablaResumen"
								align="left">&nbsp;Archivo anexo 5:</td>
							<td class="cuerpo1TextoResumen"><a
								href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo5}&nameArchivo=${requerimientos.archivo5FileName}&mimeArchivo=${requerimientos.archivo5ContentType}">${requerimientos.archivo5FileName}</a>
							</td>
						</tr>
					</s:if>
					<s:if test="requerimientos.idArchivo6!=0">
						<tr>
							<td
								class="cuerpo1TablaResumen"
								align="left">&nbsp;Archivo anexo 6:</td>
							<td class="cuerpo1TextoResumen"><a
								href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo6}&nameArchivo=${requerimientos.archivo6FileName}&mimeArchivo=${requerimientos.archivo6ContentType}">${requerimientos.archivo6FileName}</a>
							</td>
						</tr>
					</s:if>
					<s:if test="requerimientos.idArchivo7!=0">
						<tr>
							<td
								class="cuerpo2TablaResumen"
								align="left">&nbsp;Archivo anexo 7:</td>
							<td class="cuerpo1TextoResumen"><a
								href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo7}&nameArchivo=${requerimientos.archivo7FileName}&mimeArchivo=${requerimientos.archivo7ContentType}">${requerimientos.archivo7FileName}</a>
							</td>
						</tr>
					</s:if>
					<s:if test="requerimientos.idArchivo8!=0">
						<tr>
							<td
								class="cuerpo1TablaResumen"
								align="left">&nbsp;Archivo anexo 8:</td>
							<td class="cuerpo1TextoResumen"><a
								href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo8}&nameArchivo=${requerimientos.archivo8FileName}&mimeArchivo=${requerimientos.archivo8ContentType}">${requerimientos.archivo8FileName}</a>
							</td>
						</tr>
					</s:if>
					<s:if test="requerimientos.idArchivo9!=0">
						<tr>
							<td
								class="cuerpo2TablaResumen"
								align="left">&nbsp;Archivo anexo 9:</td>
							<td class="cuerpo1TextoResumen"><a
								href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo9}&nameArchivo=${requerimientos.archivo9FileName}&mimeArchivo=${requerimientos.archivo9ContentType}">${requerimientos.archivo9FileName}</a>
							</td>
						</tr>
					</s:if>
					<s:if test="requerimientos.idArchivo10!=0">
						<tr>
							<td
								class="cuerpo1TablaResumen"
								align="left">&nbsp;Archivo anexo 10:</td>
							<td class="cuerpo1TextoResumen"><a
								href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo10}&nameArchivo=${requerimientos.archivo10FileName}&mimeArchivo=${requerimientos.archivo10ContentType}">${requerimientos.archivo10FileName}</a>
							</td>
						</tr>
					</s:if>
					
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
					
				<s:hidden 
					id="idShowReq" 
					name="idRequerimiento" 
					value="%{idRequerimiento}" />
					
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
							onclick="javascript:otroArchivo();">+agregar otro</label>
							<br />
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
								<label class="agregar" onclick="javascript:supArchivo(2);">-eliminar</label>
								<br />
							</div>
							<div
								id="idDivArchivo3Block"
								${respuesta.archivo3FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo3"
									name="respuesta.archivo3">
								</s:file>
								<label class="agregar" onclick="javascript:supArchivo(3);">-eliminar</label>
								<br />
							</div>
							<div
								id="idDivArchivo4Block"
								${respuesta.archivo4FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo4"
									name="respuesta.archivo4">
								</s:file>
								<label class="agregar" onclick="javascript:supArchivo(4);">-eliminar</label>
								<br />
							</div>
							<div
								id="idDivArchivo5Block"
								${respuesta.archivo5FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo5"
									name="respuesta.archivo5">
								</s:file>
								<label class="agregar" onclick="javascript:supArchivo(5);">-eliminar</label>
								<br />
							</div>
							<div
								id="idDivArchivo6Block"
								${respuesta.archivo6FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo6"
									name="respuesta.archivo6">
								</s:file>
								<label class="agregar" onclick="javascript:supArchivo(6);">-eliminar</label>
								<br />
							</div>
							<div
								id="idDivArchivo7Block"
								${respuesta.archivo7FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo7"
									name="respuesta.archivo7">
								</s:file>
								<label class="agregar" onclick="javascript:supArchivo(7);">-eliminar</label>
								<br />
							</div>
							<div
								id="idDivArchivo8Block"
								${respuesta.archivo8FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo8"
									name="respuesta.archivo8">
								</s:file>
								<label class="agregar" onclick="javascript:supArchivo(8);">-eliminar</label>
								<br />
							</div>
							<div
								id="idDivArchivo9Block"
								${respuesta.archivo9FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo9"
									name="respuesta.archivo9">
								</s:file>
								<label class="agregar" onclick="javascript:supArchivo(9);">-eliminar</label>
								<br />
							</div>
							<div
								id="idDivArchivo10Block"
								${respuesta.archivo10FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file
									id="idCampoArchivo10"
									name="respuesta.archivo10">
								</s:file>
								<label class="agregar" onclick="javascript:supArchivo(10);">-eliminar</label>
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
	function supArchivo(obj){
		document.getElementById('idCampoArchivo' + obj ).value='';
		document.getElementById('idDivArchivo' + obj + 'Block').style.display = 'none';
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
