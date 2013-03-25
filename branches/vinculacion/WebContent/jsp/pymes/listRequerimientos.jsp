<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>
<fieldset id="requerimientos">
	<legend>
		<s:label value="Búsqueda de Requerimientos" />
		<br /> 
		<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		<br /><br />
	</legend>
	
	<s:form action="pymeRequerimientosShow" namespace="/pyme" theme="simple" onsubmit="return validacion()">	
	
	<s:if test="idRequerimiento == 0">
		<s:label cssClass="etiquetaCaptura" value="* Busqueda por palabra" />
		<s:textfield size="60" id="busqueda" name="busqueda" maxlength="60"></s:textfield>
		<br />
		<s:label cssClass="etiquetaAyuda" value="Escriba en 3 palabras el producto" />
		<br />
		<br />
		<s:label cssClass="etiquetaCaptura" value="Filtro por tractora" />
		<select id="tractoraReq" name="tractoraReq"">
			<s:if test="tractoraReq == null">
				<option selected="selected" value="Seleccione una opción">Seleccione una opción</option>
			</s:if>
			<s:else>
				<option selected="selected" value="tractoraReq"><s:property value="tractoraReq" /></option>
			</s:else>
				<s:iterator value="listTractoras">
					<option value="${empresa}">${empresa}</option>
				</s:iterator>
		</select>
		<br />
		<br />
		<s:label cssClass="etiquetaCaptura" value="Filtro por fecha desde" />
		<select id="fechaDesde" name="fechaDesde">
			<s:if test="fechaDesde == null">
				<option selected="selected" value="Seleccione una opción">Seleccione una opción</option>
			</s:if>
			<s:else>
				<option selected="selected" value="fechaDesde"><s:property value="fechaDesde" /></option>
			</s:else>
				<s:iterator value="listFechas">
					<option value="${fechaSuministro}">${fechaSuministro}</option>
				</s:iterator>
		</select>
		<br />
		<br />
		<s:label cssClass="etiquetaCaptura" value="Filtro por fecha hasta" />
		<select id="fechaHasta" name="fechaHasta">
			<s:if test="fechaHasta == null">
				<option selected="selected" value="Seleccione una opción">Seleccione una opción</option>
			</s:if>
			<s:else>
				<option selected="selected" value="fechaHasta"><s:property value="fechaHasta" /></option>
			</s:else>
				<s:iterator value="listFechas">
					<option value="${fechaExpira}">${fechaExpira}</option>
				</s:iterator>
		</select>
		<br />
		<s:submit cssClass="botonenviar" align="left" value="Buscar" />
		<br /><br />
	</s:if>
		<!-- Lista busqueda -->
		<s:if test="busqueda != null || (requerimientos.idRequerimiento != 0 && busqueda != null)">
			<table>
				<tr>
					<td>
						<table width="800px" cellspacing="1" cellpadding="1">
							<thead>
								<tr>
									<td class="encabezado_tabla" align="center"><b>Nombre Tractora</b></td>
									<td class="encabezado_tabla" align="center"><b>Parte inicial requerimiento</b></td>
									<td class="encabezado_tabla" align="center"><b>Link en la descripción</b></td>
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
			<div id="muestraReq">
				<s:hidden id="idShowReq" name="idRequerimiento" value="%{idRequerimiento}" />
				<table>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="Nombre tractora" />
						</td>
					</tr>
					<tr>
						<td>
							<s:label cssClass="etiquetaAyuda" value="%{requerimientos.nombreTractora}" />
						</td>
					</tr>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="Descripción completa del requerimiento" />
						</td>
					</tr>
					<tr>
						<td>
							<s:label cssClass="etiquetaAyuda" value="%{requerimientos.descripcion}" />
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
		<div id="respuesta" style="display: none;">
			<s:form>
				<table>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="Recuerde que una vez enviada la cotización no se podrá modificar" />
						</td>
					</tr>
					<tr>
						<td>
							<br />
							<s:label cssClass="etiquetaCaptura" value="Informacion General" />
						</td>
					</tr>
					<tr>
						<td>
							<s:textfield size="60" id="" name="" maxlength="60"></s:textfield>
						</td>
					</tr>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="Adjuntar archivo" />
						</td>
					</tr>
					<tr>
						<td>
							<s:file name="" label="Adjuntar archivo" />
						</td>
					</tr>
					<tr>
						<td>
							<br />
							<input class="botonenviar" value="Enviar respuesta" type="button" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</s:form>
</fieldset>
<script type="text/javascript">

	function responderReq(){
		divRespuesta = document.getElementById("respuesta");
	    divRespuesta.style.display = "";
	    
	    divRespuesta = document.getElementById("muestraReq");
	    divRespuesta.style.display = "none";
	}

	function validacion() {
		valorBusq = document.getElementById("busqueda").value;
		
		
		if( valorBusq == null || valorBusq.length == 0 || /^\s+$/.test(valorBusq) ) {
			alert("Escriba la(s) palabra(s) que identifican el producto que busca");
			return false;
		}
		return true;
	}
	
</script>
</body>
</html>
