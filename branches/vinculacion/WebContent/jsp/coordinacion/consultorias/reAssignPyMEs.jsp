<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="${pageContext.request.contextPath}/js/ayudas.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/coordinadorConsultorias.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>

<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-150px auto 0 250px';
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
<fieldset id="requerimientos" >
	<legend>
		<s:label id="tituloCord" value="Reasignación de PyMEs" />
		<br /><br />
		<s:label id="ayudaCord" cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
	</legend>
	<br />
	
	<s:form name="frmReasignaPyME" action="" namespace="/consultorias/coordinacion" theme="simple" onsubmit="return validacionBusqueda()">
		<s:hidden id="idHidIdConsultora" name="idConsultora" value="%{idConsultora}" />
		<s:hidden id="idHidIdPyMEs" name="idPyMEs" value="%{idPyMEs}" />
		<s:hidden name="cveScian" id="idCveSci" value="%{cveScian}" />
		<s:hidden name="producto" id="idProd" value="%{producto}" />
		<table width="99%">
			<tr>
				<td colspan="2">
					<s:label cssClass="etiquetaCaptura" value="* Busqueda por palabra clave: " />
					<s:textfield size="77" id="campoBusqueda" name="busqueda" maxlength="60" onfocus="javascript: ayudasHelp('10');" onblur="javascript:ayudasHelpBlo('10');"></s:textfield>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="ayudasDisplay10" style="display: none; padding-left: 220px;">
						<s:label cssClass="etiquetaAyuda" value="Escriba la(s) palabra(s) que identifican el producto o nombre comercial que busca." />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td style="width: 60%;">
								<s:label cssClass="etiquetaCaptura" value="Tipo de producto:" />
							</td>
							<td style="width: 40%;">
								<s:label cssClass="etiquetaCaptura" value="Tractora:" />
							</td>
						</tr>
						<tr>
							<td>
								<div id="ayudasDisplay11" style="display: none; margin-bottom: 0px; margin-top: -5px;">
									<s:label cssClass="etiquetaAyuda" value="Seleccione la categoría en la cual se encuentra su producto." />
									<br />
								</div>
							</td>
							<td>
								<div id="ayudasDisplay12" style="display: none; margin-bottom: 0px; margin-top: -5px;">
									<s:label cssClass="etiquetaAyuda" value="Seleccione una Tractora." />
									<br />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<select id="catProd1" name="cat1" style="width: 500px;" onchange="javascript: showCombo(this.value, 2, 'coordinadorConsultoriasPyMEsReAssign.do');" onfocus="javascript: ayudasHelp('11');" onblur="javascript:ayudasHelpBlo('11');">
									<option selected="selected" value="-1">--Seleccione una opción--</option>
									<s:iterator value="listCat1" status="stat">
										<option value="${cveScian}">${descScian}</option>
									</s:iterator>
								</select>
								<select id="catProd2" name="cat2" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, 3, 'coordinadorConsultoriasPyMEsReAssign.do');" onfocus="javascript: ayudasHelp('11');" onblur="javascript:ayudasHelpBlo('11');">
									<option selected="selected" value="-1">--Seleccione una opción--</option>
									<s:iterator value="listCat2" status="stat" >
										<option value="${cveScian}">${descScian}</option>
									</s:iterator>
								</select>
								<select id="catProd3" name="cat3" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, 4, 'coordinadorConsultoriasPyMEsReAssign.do');" onfocus="javascript: ayudasHelp('11');" onblur="javascript:ayudasHelpBlo('11');">
									<option selected="selected" value="-1">--Seleccione una opción--</option>
									<s:iterator value="listCat3" status="stat" >
										<option value="${cveScian}">${descScian}</option>
									</s:iterator>
								</select>
								<select id="catProd4" name="cat4" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, 5, 'coordinadorConsultoriasPyMEsReAssign.do');" onfocus="javascript: ayudasHelp('11');" onblur="javascript:ayudasHelpBlo('11');">
									<option selected="selected" value="-1">--Seleccione una opción--</option>
									<s:iterator value="listCat4" status="stat" >
										<option value="${cveScian}">${descScian}</option>
									</s:iterator>
								</select>
								<select id="catProd5" name="cat5" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, 6, 'coordinadorConsultoriasPyMEsReAssign.do');" onfocus="javascript: ayudasHelp('11');" onblur="javascript:ayudasHelpBlo('11');">
									<option selected="selected" value="-1">--Seleccione una opción--</option>
									<s:iterator value="listCat5" status="stat" >
										<option value="${cveScian}">${descScian}</option>
									</s:iterator>
								</select>
							</td>
							<td>
								<select id="tractora" name="tractora" style="width: 200px;" onfocus="javascript: ayudasHelp('12');" onblur="javascript:ayudasHelpBlo('12');">
									<option value="-1">--Seleccione un estado--</option>
									<s:iterator value="TractorasList" status="stat" >
										<option value="${empresa}">${empresa}</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<s:textarea id="idInputCatScian" rows="1" cols="53" disabled="true" cssClass="resultado" name="producto" value="%{producto}" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<s:submit cssClass="botonenviar" align="left" value="Buscar" />
				</td>
			</tr>
		</table>
		<br />
		<!-- Lista busqueda -->
		<table width="99%" cellspacing="1" cellpadding="1">
			<thead>
				<tr>
					<td class="encabezado_tabla" align="center" style="width: 5%;"><b>No.</b></td>
					<td class="encabezado_tabla" align="center"><b>Nombre comercial</b></td>
					<td class="encabezado_tabla" align="center"><b>Empresa Tractora</b></td>
					<td class="encabezado_tabla" align="center"><b>Estado</b></td>
					<td class="encabezado_tabla" align="center"><b>Consultora Vinculada</b></td>
					<td class="encabezado_tabla" align="center"><b>Seleccionar PyME</b></td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="listPyMEs" status="stat">
					<tr>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${nombreComercial}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${tractora}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${estado}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${empresa}</td>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center"><s:checkbox id="idAsigna%{idUsuario}" name="checkbox" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<table class="submit_tabla">
			<tr >
				<td>
					<input class="botonenviar" type="button" value="Marcar/Desmarcar Todas" onkeypress="reasignaTodas();" onclick="reasignaTodas();" />
				</td>
				<td>
					<input class="botonenviar" type="button" value="Elegir Consultora" onkeypress="muestraReAsignar();" onclick="muestraReAsignar();" />
				</td>
			</tr>
		</table>
		<div id="contConsultor" style="display: none;">
			<br />
			<br />
			<s:label cssClass="camposObligatorios" value="Elija la Consultora a la cual serán asignadas las PyMEs y seleccione la opción 'Asignar PyMEs'." />
			<br />
			<table class="submit_tabla">
				<tr>
					<td>
						<select name="listaConsultoras" id="idConsultoraSeleccionada" style="width: 600px;">
							<option value="-1">--Seleccione un Consultora--</option>
							<s:iterator value="%{consultorasList}" status="stat">
								<option value="${idUsuario}">${empresa}</option>
							</s:iterator>
						</select>
						<br />
						<input class="botonenviar" type="submit" value="Asignar PyMEs" onkeypress="asignaConsultora();" onclick="reAsignaConsultora();" />
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</fieldset>
</body>
</html>