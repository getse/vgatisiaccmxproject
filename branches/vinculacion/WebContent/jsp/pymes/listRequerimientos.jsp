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
	<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/ayudas.js"></script>
</head>

<body>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-195px auto 0 250px';
</script>

<s:if test="mensaje!=null">
	<script type="text/javascript">
		document.getElementById("muestraReq").style.display = 'none';
	</script>
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
	<s:form action="pymeRequerimientosShow" namespace="/pyme" theme="simple" onsubmit="return validacion()">
		<s:if test="idRequerimiento == 0">
			<legend>
				<s:label value="Búsqueda de Requerimientos" />
				<br /> <br />
				<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
			</legend>	
			<script type="text/javascript">
				setTimeout("calendario()", 500);
			</script>
			<br />
			<s:label cssClass="etiquetaCaptura" value="* Busqueda por palabra clave" />
			<s:textfield size="60" id="busqueda" name="busqueda" maxlength="60"
				onfocus="javascript:ayudasHelp(0);" onblur="javascript:ayudasHelpBlo(0);"></s:textfield>
			<br />
			<s:label cssClass="etiquetaAyuda" value="Escriba en 3 palabras el producto" 
				id="ayudasDisplay0" style="display:none; margin-top:5px;"/>
			<br />
			<table>
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Filtro por fecha desde:" />
						<s:date name="fechaDesde" id="fDesde" format="dd/MM/yyyy" />
						<s:textfield class="calendario" id="ingreso" name="fechaDesde" value="%{fDesde}" size="10" maxlength="10" />
						<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador" style="cursor: hand"></img>
					</td>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Filtro por fecha hasta:" />
						<s:date name="fechaHasta" id="fHasta" format="dd/MM/yyyy" />
						<s:textfield class="calendario" id="ingreso2" name="fechaHasta" value="%{fHasta}" size="10" maxlength="10" />
						<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador2" style="cursor: hand"></img>
					</td>
				</tr>
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Filtro por tractora" />
						<select id="tractoraReq" name="tractoraReq">
							<option value="-1">TODAS</option>
							<s:iterator value="listTractoras">
								<option  value="${empresa}">${empresa}</option>
							</s:iterator>
						</select>
					</td>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Entidad Federativa:" />
						<select id="estado" name="estado" style="width: 200px;">
							<option value="Seleccione un estado">--Seleccione un estado--</option>
							<option ${estado == 'Aguascalientes' ? ' selected="selected" ' : ''} value="Aguascalientes">Aguascalientes</option>
							<option ${estado == 'Baja California Norte' ? ' selected="selected" ' : ''} value="Baja California Norte">Baja California Norte</option>
							<option ${estado == 'Baja California Sur' ? ' selected="selected" ' : ''} value="Baja California Sur">Baja California Sur</option>
							<option ${estado == 'Campeche' ? ' selected="selected" ' : ''} value="Campeche">Campeche</option>
							<option ${estado == 'Chiapas' ? ' selected="selected" ' : ''} value="Chiapas">Chiapas</option>
							<option ${estado == 'Chihuahua' ? ' selected="selected" ' : ''} value="Chihuahua">Chihuahua</option>
							<option ${estado == 'Coahuila' ? ' selected="selected" ' : ''} value="Coahuila">Coahuila</option>
							<option ${estado == 'Colima' ? ' selected="selected" ' : ''} value="Colima">Colima</option>
							<option ${estado == 'Distrito Federal' ? ' selected="selected" ' : ''} value="Distrito Federal">Distrito Federal</option>
							<option ${estado == 'Durango' ? ' selected="selected" ' : ''} value="Durango">Durango</option>
							<option ${estado == 'Estado de Mexico' ? ' selected="selected" ' : ''} value="Estado de Mexico">Estado de México</option>
							<option ${estado == 'Guanajuato' ? ' selected="selected" ' : ''} value="Guanajuato">Guanajuato</option>
							<option ${estado == 'Guerrero' ? ' selected="selected" ' : ''} value="Guerrero">Guerrero</option>
							<option ${estado == 'Hidalgo' ? ' selected="selected" ' : ''} value="Hidalgo">Hidalgo</option>
							<option ${estado == 'Jalisco' ? ' selected="selected" ' : ''} value="Jalisco">Jalisco</option>
							<option ${estado == 'Michoacan' ? ' selected="selected" ' : ''} value="Michoacan">Michoacán</option>
							<option ${estado == 'Morelos' ? ' selected="selected" ' : ''} value="Morelos">Morelos</option>
							<option ${estado == 'Nayarit' ? ' selected="selected" ' : ''} value="Nayarit">Nayarit</option>
							<option ${estado == 'Nuevo Leon' ? ' selected="selected" ' : ''} value="Nuevo Leon">Nuevo León</option>
							<option ${estado == 'Oaxaca' ? ' selected="selected" ' : ''} value="Oaxaca">Oaxaca</option>
							<option ${estado == 'Puebla' ? ' selected="selected" ' : ''} value="Puebla">Puebla</option>
							<option ${estado == 'Queretaro' ? ' selected="selected" ' : ''} value="Queretaro">Querétaro</option>
							<option ${estado == 'Quintana Roo' ? ' selected="selected" ' : ''} value="Quintana Roo">Quintana Roo</option>
							<option ${estado == 'San Luis Potosi' ? ' selected="selected" ' : ''} value="San Luis Potosi">San Luís Potosí</option>
							<option ${estado == 'Sinaloa' ? ' selected="selected" ' : ''} value="Sinaloa">Sinaloa</option>
							<option ${estado == 'Sonora' ? ' selected="selected" ' : ''} value="Sonora">Sonora</option>
							<option ${estado == 'Tabasco' ? ' selected="selected" ' : ''} value="Tabasco">Tabasco</option>
							<option ${estado == 'Tamaulipas' ? ' selected="selected" ' : ''} value="Tamaulipas">Tamaulipas</option>
							<option ${estado == 'Tlaxcala' ? ' selected="selected" ' : ''} value="Tlaxcala">Tlaxcala</option>
							<option ${estado == 'Veracruz' ? ' selected="selected" ' : ''} value="Veracruz">Veracruz</option>
							<option ${estado == 'Yucatan' ? ' selected="selected" ' : ''} value="Yucatan">Yucatán</option>
							<option ${estado == 'Zacatecas' ? ' selected="selected" ' : ''} value="Zacatecas">Zacatecas</option>
						</select>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
			<table width="100%">
				<tr>
					<td align="center" style="width: 50%">
						<input class="botonenviar" value="Mostrar requerimientos asociados" type="button" onclick="javascript:regresaNada();" />
			
					</td>
					<td>
						<s:submit cssClass="botonenviar" align="left" value="Buscar" />	
					</td>
				</tr>
			</table>
					
			<br /><br />
		</s:if>
		<!-- Lista busqueda -->
		<s:if test="busqueda != null || (requerimientos.idRequerimiento != 0 && busqueda != null)">
			<s:label cssClass="camposObligatorios" value="Seleccione el requerimiento que desea consultar." />
			<br />
			<table width="100%">
				<tr>
					<td>
						<table width="100%" cellspacing="1" cellpadding="1">
							<thead>
								<tr>
									<td class="encabezado_tabla" align="center"><b>No.</b></td>
									<td class="encabezado_tabla" align="center"><b>Nombre Tractora</b></td>
									<td class="encabezado_tabla" align="center"><b>Descripción del requerimiento</b></td>
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
												align="center">${tractora.empresa}</td>
											<td
												class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
												align="center">${producto}</td>
											<td
												class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
												align="center">
												<a href="${pageContext.request.contextPath}/pyme/pymeRequerimientosShow.do?idRequerimiento=${idRequerimiento}">Ver Requerimiento</a>
											</td>
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
				<s:label cssClass="camposObligatorios" value="Para responder al requerimieno seleccione la opción 'Responder' y después la opción 'Enviar respuesta'." />
			</legend>
			<br />	
			<div id="muestraReq">
				<s:hidden id="idShowReq" name="idRequerimiento" value="%{idRequerimiento}" />
				<table class="expediente_tabla" width="100%;">
					<tr>
						<td
							class="encabezadoTablaResumen"
							colspan="2"
							align="center" >Resumen del Requerimiento</td>
					</tr>
					<tr>
						<td class="cuerpo2TablaResumen">&nbsp;Nombre de la Tractora: </td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${requerimientos.tractora.empresa}</s:label></td>
					</tr>
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto solicitado:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${requerimientos.producto}</s:label></td>
					</tr>
					<tr>
						<td class="cuerpo2TablaResumen" align="left">&nbsp;Tipo de producto:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${requerimientos.tipoProducto}</s:label></td>
					</tr>
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Descripción del producto:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${requerimientos.descripcion}</s:label></td>
					</tr>
					<s:if test="requerimientos.idArchivo1!=0">
						<tr>
							<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 1:</td>
							<td class="cuerpo1TextoResumen">
								<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo1}&nameArchivo=${requerimientos.archivo1FileName}&mimeArchivo=${requerimientos.archivo1ContentType}">${requerimientos.archivo1FileName}</a>
							</td>
						</tr>
						<s:if test="requerimientos.descArchivo1!=null">
							<tr>
								<td class="cuerpo1TablaResumen" align="left">&nbsp;Descripción:</td>
								<td class="cuerpo1TextoResumen">${requerimientos.descArchivo1}</td>
							</tr>
						</s:if>
					</s:if>
					<s:if test="requerimientos.idArchivo2!=0">
						<tr>
							<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 2:</td>
							<td class="cuerpo1TextoResumen">
								<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo2}&nameArchivo=${requerimientos.archivo2FileName}&mimeArchivo=${requerimientos.archivo2ContentType}">${requerimientos.archivo2FileName}</a>
							</td>
						</tr>
						<s:if test="requerimientos.descArchivo2!=null">
							<tr>
								<td class="cuerpo1TablaResumen" align="left">&nbsp;Descripción:</td>
								<td class="cuerpo1TextoResumen">${requerimientos.descArchivo2}</td>
							</tr>
						</s:if>
					</s:if>
					<s:if test="requerimientos.idArchivo3!=0">
						<tr>
							<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 3:</td>
							<td class="cuerpo1TextoResumen">
								<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo3}&nameArchivo=${requerimientos.archivo3FileName}&mimeArchivo=${requerimientos.archivo3ContentType}">${requerimientos.archivo3FileName}</a>
							</td>
						</tr>
						<s:if test="requerimientos.descArchivo3!=null">
							<tr>
								<td class="cuerpo1TablaResumen" align="left">&nbsp;Descripción:</td>
								<td class="cuerpo1TextoResumen">${requerimientos.descArchivo3}</td>
							</tr>
						</s:if>
					</s:if>
					<s:if test="requerimientos.idArchivo4!=0">
						<tr>
							<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 4:</td>
							<td class="cuerpo1TextoResumen">
								<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo4}&nameArchivo=${requerimientos.archivo4FileName}&mimeArchivo=${requerimientos.archivo4ContentType}">${requerimientos.archivo4FileName}</a>
							</td>
						</tr>
						<s:if test="requerimientos.descArchivo4!=null">
							<tr>
								<td class="cuerpo1TablaResumen" align="left">&nbsp;Descripción:</td>
								<td class="cuerpo1TextoResumen">${requerimientos.descArchivo4}</td>
							</tr>
						</s:if>
					</s:if>
					<s:if test="requerimientos.idArchivo5!=0">
						<tr>
							<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 5:</td>
							<td class="cuerpo1TextoResumen">
								<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo5}&nameArchivo=${requerimientos.archivo5FileName}&mimeArchivo=${requerimientos.archivo5ContentType}">${requerimientos.archivo5FileName}</a>
							</td>
						</tr>
						<s:if test="requerimientos.descArchivo5!=null">
							<tr>
								<td class="cuerpo1TablaResumen" align="left">&nbsp;Descripción:</td>
								<td class="cuerpo1TextoResumen">${requerimientos.descArchivo5}</td>
							</tr>
						</s:if>
					</s:if>
					<s:if test="requerimientos.idArchivo6!=0">
						<tr>
							<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 6:</td>
							<td class="cuerpo1TextoResumen">
								<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo6}&nameArchivo=${requerimientos.archivo6FileName}&mimeArchivo=${requerimientos.archivo6ContentType}">${requerimientos.archivo6FileName}</a>
							</td>
						</tr>
						<s:if test="requerimientos.descArchivo6!=null">
							<tr>
								<td class="cuerpo1TablaResumen" align="left">&nbsp;Descripción:</td>
								<td class="cuerpo1TextoResumen">${requerimientos.descArchivo6}</td>
							</tr>
						</s:if>
					</s:if>
					<s:if test="requerimientos.idArchivo7!=0">
						<tr>
							<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 7:</td>
							<td class="cuerpo1TextoResumen">
								<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo7}&nameArchivo=${requerimientos.archivo7FileName}&mimeArchivo=${requerimientos.archivo7ContentType}">${requerimientos.archivo7FileName}</a>
							</td>
						</tr>
						<s:if test="requerimientos.descArchivo7!=null">
							<tr>
								<td class="cuerpo1TablaResumen" align="left">&nbsp;Descripción:</td>
								<td class="cuerpo1TextoResumen">${requerimientos.descArchivo7}</td>
							</tr>
						</s:if>
					</s:if>
					<s:if test="requerimientos.idArchivo8!=0">
						<tr>
							<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 8:</td>
							<td class="cuerpo1TextoResumen">
								<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo8}&nameArchivo=${requerimientos.archivo8FileName}&mimeArchivo=${requerimientos.archivo8ContentType}">${requerimientos.archivo8FileName}</a>
							</td>
						</tr>
						<s:if test="requerimientos.descArchivo8!=null">
							<tr>
								<td class="cuerpo1TablaResumen" align="left">&nbsp;Descripción:</td>
								<td class="cuerpo1TextoResumen">${requerimientos.descArchivo8}</td>
							</tr>
						</s:if>
					</s:if>
					<s:if test="requerimientos.idArchivo9!=0">
						<tr>
							<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 9:</td>
							<td class="cuerpo1TextoResumen">
								<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo9}&nameArchivo=${requerimientos.archivo9FileName}&mimeArchivo=${requerimientos.archivo9ContentType}">${requerimientos.archivo9FileName}</a>
							</td>
						</tr>
						<s:if test="requerimientos.descArchivo9!=null">
							<tr>
								<td class="cuerpo1TablaResumen" align="left">&nbsp;Descripción:</td>
								<td class="cuerpo1TextoResumen">${requerimientos.descArchivo9}</td>
							</tr>
						</s:if>
					</s:if>
					<s:if test="requerimientos.idArchivo10!=0">
						<tr>
							<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 10:</td>
							<td class="cuerpo1TextoResumen">
								<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${requerimientos.idArchivo10}&nameArchivo=${requerimientos.archivo10FileName}&mimeArchivo=${requerimientos.archivo10ContentType}">${requerimientos.archivo10FileName}</a>
							</td>
						</tr>
						<s:if test="requerimientos.descArchivo10!=null">
							<tr>
								<td class="cuerpo1TablaResumen" align="left">&nbsp;Descripción:</td>
								<td class="cuerpo1TextoResumen">${requerimientos.descArchivo10}</td>
							</tr>
						</s:if>
					</s:if>
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Fecha de suministro:</td>
						<td class="cuerpo1TextoResumen">
							<s:label cssClass="etiquetaResumen">
								<s:if test="%{requerimientos.fechaSuministro!=null}">
									<s:date name="requerimientos.fechaSuministro" format="dd/MM/yyyy" />
								</s:if>
								<s:else>
									${requerimientos.bIndefinido==true?'Indefinido':requerimientos.bVariasFechas==true?'Varias Fechas':requerimientos.bContinuoSuministro==true?'Continuo':''}
									<s:if test="%{requerimientos.bVariasFechas}">
										<tr>
											<td class="cuerpo2TablaResumen" align="left">&nbsp;Detalle fechas suministro:</td>
											<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${requerimientos.variasFechas}</s:label></td>
										</tr>
									</s:if>
								</s:else>
							</s:label>
						</td>
					</tr>
					<tr>
						<td
							class="cuerpo2TablaResumen"
							align="left">&nbsp;Lugar de suministro:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">
									<s:iterator value="requerimientos.lugarSuministro" status="stat">
										<s:if test="#stat.index!=0">, </s:if>${estadoVenta} ${descripcion!=null?'(':''}${descripcion!=null?descripcion:''}${descripcion!=null?')':''}
									</s:iterator>
									</s:label></td>
					</tr>
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Condiciones de pago:</td>
						<td class="cuerpo1TextoResumen">
							<s:label cssClass="etiquetaResumen">${requerimientos.bContado?'Contado':requerimientos.bCredito?'Crédito':requerimientos.bOtro?requerimientos.otrasCondiciones:''}</s:label>
							<s:if test="%{requerimientos.bCredito}">
								<s:label cssClass="etiquetaResumen">${requerimientos.bQuince?', con plazo a 15 días':requerimientos.bTreinta?', con plazo a 30 días':requerimientos.bSesenta?', con plazo a 60 días':requerimientos.bNoventa?', con plazo a 90 días':''}</s:label>
							</s:if>
						</td>
					</tr>
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Requisitos adicionales:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${requerimientos.requisitosAdicionales}</s:label></td>
					</tr>
					<tr>
						<td class="cuerpo2TablaResumen" align="left">&nbsp;Fecha en la que expira el requerimiento:</td>
						<td class="cuerpo1TextoResumen">
							<s:label cssClass="etiquetaResumen">
								<s:if test="%{requerimientos.fechaExpira!=null}">
									<s:date name="requerimientos.fechaExpira" format="dd/MM/yyyy" />
								</s:if>
								<s:else>
									${requerimientos.bContinuoExpira==true?'Continuo':''}
								</s:else>
							</s:label>
						</td>
					</tr>
					<s:if test="mensaje!=null">									
							<tr>
								<td style='text-align: center;' colspan="2"> 
									<input class="botonenviar" value="Regresar" type="button" onclick="javascript: document.frmCancela.submit();" />
								</td>
							</tr>
					</s:if>
					<s:else>
						<tr>
							<td align="center">
								<input class="botonenviar" value="Regresar" type="button" onclick="javascript: window.history.back();" />
							</td>
							<td align="center">
								<input class="botonenviar" value="Responder" type="button" onclick="responderReq();" />
							</td>
						</tr>
					</s:else>
				</table>
			</div>		
		</s:if>
	</s:form>
	<s:form name="sinNada" id="sinNada" action="pymeRequerimientosShow" namespace="/pyme" theme="simple" >
	</s:form>
	<div id="respuesta" style="display: none;">
		<s:form name="frmRespuesta" action="pymeRequerimientosSave" namespace="/pyme" enctype="multipart/form-data" onsubmit="return respuesta();" method="post" theme="simple">
			<s:hidden id="idShowReq" name="idRequerimiento" value="%{idRequerimiento}" />
				<table>
					<s:hidden id="idFrmRespId" name="respuesta.idRequerimiento" value="%{idRequerimiento}" />
					<s:hidden id="idFrmRespMsj" name="respuesta.mensajeEnvio" value="Envio exitoso de su cotizacion" />
					<tr>
						<td>
							<s:label cssClass="camposObligatorios" value="Recuerde que una vez enviada la cotización no se podrá modificar." />
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
							<s:textfield size="60" id="infoReq" name="respuesta.informacion" maxlength="60"
							onfocus="javascript:ayudasHelp(1);" onblur="javascript:ayudasHelpBlo(1);"></s:textfield>
						</td>
					</tr>
					<tr>
						<td>
							<s:label cssClass="etiquetaAyuda" value="Indique cualquier información que considere relevante sobre su cotización" 
									id="ayudasDisplay1" style="display:none; margin-top:5px;"/>							
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="Incluir archivo(s):" />
							<label class="agregar" onclick="javascript:otroArchivo();">+agregar otro</label>
							<br />
							<div id="idDivArchivo1Block" ${respuesta.archivo1FileName==null?' style="display: block;"':' style="display: none;"'}>
								<s:file  id="idCampoArchivo1" name="respuesta.archivo1"
									onclick="javascript:ayudasHelp(2);"></s:file>
								<br />
							</div>
							<div id="idDivArchivo2Block" ${respuesta.archivo2FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file id="idCampoArchivo2" name="respuesta.archivo2"
								onclick="javascript:ayudasHelp(2);"></s:file>
								<label class="agregar" onclick="javascript:supArchivo(2);">-eliminar</label>
								<br />
							</div>
							<div id="idDivArchivo3Block" ${respuesta.archivo3FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file id="idCampoArchivo3" name="respuesta.archivo3"
								onclick="javascript:ayudasHelp(2);"></s:file>
								<label class="agregar" onclick="javascript:supArchivo(3);">-eliminar</label>
								<br />
							</div>
							<div id="idDivArchivo4Block" ${respuesta.archivo4FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file id="idCampoArchivo4" name="respuesta.archivo4"
								onclick="javascript:ayudasHelp(2);"></s:file>
								<label class="agregar" onclick="javascript:supArchivo(4);">-eliminar</label>
								<br />
							</div>
							<div id="idDivArchivo5Block" ${respuesta.archivo5FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file id="idCampoArchivo5" name="respuesta.archivo5"
								onclick="javascript:ayudasHelp(2);"></s:file>
								<label class="agregar" onclick="javascript:supArchivo(5);">-eliminar</label>
								<br />
							</div>
							<div id="idDivArchivo6Block" ${respuesta.archivo6FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file id="idCampoArchivo6" name="respuesta.archivo6"
								onclick="javascript:ayudasHelp(2);"></s:file>
								<label class="agregar" onclick="javascript:supArchivo(6);">-eliminar</label>
								<br />
							</div>
							<div id="idDivArchivo7Block" ${respuesta.archivo7FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file id="idCampoArchivo7" name="respuesta.archivo7"
								onclick="javascript:ayudasHelp(2);"></s:file>
								<label class="agregar" onclick="javascript:supArchivo(7);">-eliminar</label>
								<br />
							</div>
							<div id="idDivArchivo8Block" ${respuesta.archivo8FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file id="idCampoArchivo8" name="respuesta.archivo8"
								onclick="javascript:ayudasHelp(2);"></s:file>
								<label class="agregar" onclick="javascript:supArchivo(8);">-eliminar</label>
								<br />
							</div>
							<div id="idDivArchivo9Block" ${respuesta.archivo9FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file id="idCampoArchivo9" name="respuesta.archivo9"
								onclick="javascript:ayudasHelp(2);"></s:file>
								<label class="agregar" onclick="javascript:supArchivo(9);">-eliminar</label>
								<br />
							</div>
							<div id="idDivArchivo10Block" ${respuesta.archivo10FileName==null?' style="display: none;"':' style="display: none;"'}>
								<s:file id="idCampoArchivo10" name="respuesta.archivo10"
								onclick="javascript:ayudasHelp(2);"></s:file>
								<label class="agregar" onclick="javascript:supArchivo(10);">-eliminar</label>
								<br />
							</div>
							<div id="idDivFil" style="display: block; margin-bottom: 0px; margin-top: -5px;">
								<s:label cssClass="etiquetaAyuda" id="ayudasDisplay2" style="display:none; margin-top:5px;"
									value="Indique el o los archivos que serán incluidos. Máximo 2MB (.pdf .doc .png)" />
								<br />
							</div>
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<td>
							<input class="botonenviar" value="Regresar" type="button" onclick="javascript: window.history.back();" />
						</td>
						<td>
							<s:submit cssClass="botonenviar" value="Enviar respuesta" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
</fieldset>
<s:form name="frmCancela" action="pymeRequerimientosShow" namespace="/pyme" theme="simple" method="post"></s:form>
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

	function supArchivo(obj) {
		document.getElementById('idCampoArchivo' + obj).value = '';
		document.getElementById('idDivArchivo' + obj + 'Block').style.display = 'none';
	}

	function responderReq() {
		divRespuesta = document.getElementById("respuesta");
		divRespuesta.style.display = "block";
		divRespuesta = document.getElementById("muestraReq");
		divRespuesta.style.display = "none";
	}

	function validacion() {
		valorBusq = document.getElementById("busqueda").value;
		if (valorBusq == null || valorBusq == 0 
				|| valorBusq.trim().equals("")) {
			document.getElementById("busqueda").focus();
			alert("Escriba la(s) palabra(s) que identifican el producto que busca");
			return false;
		}
		return true;
	}

	function respuesta() {
		valorRespReq = document.getElementById("infoReq").value;
		if (valorRespReq == null || valorRespReq.length == 0
				|| /^\s+$/.test(valorRespReq)) {
			document.getElementById('infoReq').focus();
			alert("El campo Informacion General es obligatorio");
			return false;
		} else {
			return true;
		}
	}
	function regresaNada(){
		document.sinNada.submit();
	}
</script>
</body>
</html>
