<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script
	src="${pageContext.request.contextPath}/js/pymes.js"
	type="text/javascript"></script>
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/libraries/RGraph.common.core.js"></script>
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/libraries/RGraph.radar.js"></script>
	<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/ayudas.js"></script>
<!--[if lt IE 9]><script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/excanvas/excanvas.js"></script><![endif]-->
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
				<img src="${pageContext.request.contextPath}/img/palomitaverde.gif" />
			</s:if> <s:else>
				<img src="${pageContext.request.contextPath}/img/warning.png" />
			</s:else></td>
			<td class="contenidoNota"><s:property value="mensaje.mensaje" /></td>
		</tr>
	</table>
</s:if>
<fieldset id="requerimientos">
	<div id="busqPyME" ${pymesList==null && idUsuario==0 ? ' style="display: block;" ' :' style="display: none;"' }>
		<s:form 
			action="consultoraPyMEsShow" 
			namespace="/consultor/administracion" 
			theme="simple" 
			onsubmit="return validacionBusqueda()">
			<s:hidden name="cveScian" id="idCveSci" value="%{cveScian}" />
			<s:hidden name="producto" id="idProd" value="%{producto}" />
			<legend>
				<s:label value="Búsqueda de PyMEs" />
				<br /> <br />
				<s:label cssClass="camposObligatorios"
					value="Los campos marcados con asterisco(*) A son de caracter obligatorio." /><br/><br/>
			</legend>
			<br />
			<table>
				<tr>
					<td>
						<table>
							<tr>
								<td colspan="2" style="width: 550px;">
									<s:label cssClass="etiquetaCaptura" value="* Busqueda por palabra clave: " />
									<s:textfield size="77" id="campoBusqueda" name="busqueda" maxlength="60" onfocus="javascript: ayudasHelp('1');" onblur="javascript:ayudasHelpBlo('1');"
										onfocus="javascript: ayudasHelp('1');" onblur="javascript:ayudasHelpBlo('1');"></s:textfield>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div style="display: none; margin-bottom: 5px; " id="ayudasDisplay1">
									<s:label 
									 cssClass="etiquetaAyuda" value="Escriba la(s) palabra(s) que identifican el producto o nombre comercial que busca." />
								</div></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table>
							<tr>
								<td>
									<s:label cssClass="etiquetaCaptura" value="Tipo de producto:" />
								</td>
							</tr>
							<tr>
								<td>
									<div id="idDivTipPro" style="display: none; margin-bottom: 0px; margin-top: -5px;">
										<s:label cssClass="etiquetaAyuda" value="Seleccione la categoría en la cual se encuentra su producto." />
										<br />
									</div>
									<div id="idDivTipPro2" style="display: block; margin-bottom: 0px; margin-top: -5px;">
									</div>
								</td>
							</trs>
							<tr>
								<td>
								<select id="catProd1" name="cat1" style="width: 500px;" onchange="javascript: showCombo(this.value, 2);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
									<option selected="selected" value="-1">--Seleccione una opción--</option>
									<s:iterator value="listCatProductos" status="stat">
										<option value="${cveScian}">${descScian}</option>
									</s:iterator>
								</select>
								<select id="catProd2" name="cat2" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, 3);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
									<option selected="selected" value="-1">--Seleccione una opción--</option>
									<s:iterator value="listCat2" status="stat" >
										<option value="${cveScian}">${descScian}</option>
									</s:iterator>
								</select>
								<select id="catProd3" name="cat3" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, 4);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
									<option selected="selected" value="-1">--Seleccione una opción--</option>
									<s:iterator value="listCat3" status="stat" >
										<option value="${cveScian}">${descScian}</option>
									</s:iterator>
								</select>
								<select id="catProd4" name="cat4" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, 5);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
									<option selected="selected" value="-1">--Seleccione una opción--</option>
									<s:iterator value="listCat4" status="stat" >
										<option value="${cveScian}">${descScian}</option>
									</s:iterator>
								</select>
								<select id="catProd5" name="cat5" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, 6);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
									<option selected="selected" value="-1">--Seleccione una opción--</option>
									<s:iterator value="listCat5" status="stat" >
										<option value="${cveScian}">${descScian}</option>
									</s:iterator>
								</select>
								</td>
							</tr>
							<tr>
								<td>
									<s:textarea id="idInputCatScian" rows="1" cols="53" disabled="true" cssClass="resultado" name="producto" value="%{producto}" />
								</td>
							</tr>
						</table>
					</td>
					<td style="margin-top: 5px; margin-left: 5px; display: block;">
						<s:label cssClass="etiquetaCaptura" value="Entidad Federativa:" />
						<br />
						<select id="estado" name="estado" style="width: 200px;" 
							onfocus="javascript: ayudasHelp('2');" onblur="javascript:ayudasHelpBlo('2');">
							<option ${estado == '-1' ? ' selected="selected" ' : ''} value="-1">--Seleccione un estado--</option>
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
							<option ${estado == 'Guanajuato' ? ' selected="selected" ' : ''} value="Guanajuato">Guanajuato</option>
							<option ${estado == 'Guerrero' ? ' selected="selected" ' : ''} value="Guerrero">Guerrero</option>
							<option ${estado == 'Hidalgo' ? ' selected="selected" ' : ''} value="Hidalgo">Hidalgo</option>
							<option ${estado == 'Jalisco' ? ' selected="selected" ' : ''} value="Jalisco">Jalisco</option>
							<option ${estado == 'Estado de Mexico' ? ' selected="selected" ' : ''} value="Estado de Mexico">Estado de México</option>
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
						<br /><s:label id="ayudasDisplay2" style="display: none; margin-bottom: 0px; margin-top: -5px;"
								cssClass="etiquetaAyuda" value="Seleccione el estado que identifica el producto que busca." />
					</td>
				</tr>
			</table>
			<table class="submit_tabla" style="width: 40%;">
				<tr>
					<td colspan="2" >
						<input class="botonenviar" type="button" value="Asignar cedula" onclick="javascript:cedulaSub();"/>
					</td>
					<td colspan="2">
						<s:submit cssClass="botonenviar" align="right" value="Buscar" />
					</td>
				</tr>
			</table>
			<br />
			</s:form>
			<s:form 
			id="cedula"
			action="consultoraPyMEsShow" 
			namespace="/consultor/administracion" 
			theme="simple" >
				<s:hidden name="salida" value="asignar"></s:hidden>
			</s:form>	
			<!-- Lista busqueda -->
			
				<table width="99%" cellspacing="1" cellpadding="1">
					<thead>
							<tr>
								<td class="encabezado_tabla" align="center"><b>No.</b></td>
								<td class="encabezado_tabla" align="center"><b>Nombre
										Comercial</b></td>
								<td class="encabezado_tabla" align="center"><b>Correo
										electrónico</b></td>
								<td class="encabezado_tabla" align="center"><b>Nombre
										Contacto</b></td>
								<td class="encabezado_tabla" align="center"><b>Apellido
										Paterno Contacto</b></td>
								<td class="encabezado_tabla" align="center"><b>Apellido
										Materno Contacto</b></td>
								<td class="encabezado_tabla" align="center"><b>Ver Expediente
										</b></td>
							</tr>
						</thead>
					<tbody>
						<s:set var="contador" value="0" />
						<s:iterator value="listPyMEs" status="stat">		
							<s:if test="estatus == true">					
								<s:set var="cnt" value="#contador=#contador+1" />
								<tr>
									<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${stat.count}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${nombreComercial}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${correoElectronicoContacto1}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${nombreContacto1}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${appPaterno1}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${appMaterno1}</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center"><a href="${pageContext.request.contextPath}
													/consultor/administracion/consultoraPyMEsShow.do?idUsuario=${idUsuario}">Expediente</a></td>
								</tr>					
							</s:if>		
						</s:iterator>
					</tbody>
					<tr>
				</tr>
			</table>	
			
	</div>
	<div  ${pymesList!=null && idUsuario==0 ? ' style="display: block;" ' :' style="display: none;"' }>
		<s:form 
			id="asignar"
			action="consultoraPyMEsShow" 
			namespace="/consultor/administracion" 
			theme="simple" 
			onsubmit="return validaAsignaCedula()">
			<s:hidden name="cveScian" id="idCveSci" value="%{cveScian}" />
			<legend>
				<s:label value="Asignar cédula" />
				<br /> <br />
				<s:label cssClass="camposObligatorios"
					value="Asignar cedula PyME en las que se encuentra disponible el campo de selección, asignando tambien el año." />
			</legend>
			<br />
			<s:hidden name="producto" id="idProd" value="%{producto}" />
				<table width="99%" cellspacing="1" cellpadding="1">
					<thead>
							<tr>
								<td class="encabezado_tabla" align="center"><b>No.</b></td>
								<td class="encabezado_tabla" align="center"><b>Nombre
										Comercial</b></td>
								<td class="encabezado_tabla" align="center"><b>Correo
										electrónico</b></td>
								<td class="encabezado_tabla" align="center"><b>Nombre
										Contacto</b></td>
								<td class="encabezado_tabla" align="center"><b>Apellido
										Paterno Contacto</b></td>
								<td class="encabezado_tabla" align="center"><b>Apellido
										Materno Contacto</b></td>
								<td class="encabezado_tabla" align="center"><b>Cedula
										</b></td>
								<td class="encabezado_tabla" align="center"><b>Seleccionar
										</b></td>
							</tr>
						</thead>
					<tbody>
						<s:set var="contador" value="0" />
						<s:iterator value="pymesList" status="stat">
							
								<s:set var="cnt" value="#contador=#contador+1" />
								<tr>
									<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${stat.count}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${nombreComercial}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${correoElectronicoContacto1}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${nombreContacto1}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${appPaterno1}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${appMaterno1}</td>
									<td
										class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
										align="center">${cedula}</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center"><input type="checkbox" name="pymesSelected" value="${idUsuario}"></input></td>
								</tr>							
						</s:iterator>
					</tbody>
			</table>
			<div id="paso1" style="width: 45%;" class="submit_tabla">
				<table>
					<tr>
						<td >
							<input class="botonenviar" type="button" value="Marcar/Desmarcar todos" onclick="javascript:seleccionAll()"/>
						</td>
					</tr>
					<tr >
						<td align="center">
								<select name="ant1" id="anho" >
								<option value="-1">--Seleccione año--</option>
								<option value="2010">2010</option>
								<option value="2011">2011</option>
								<option value="2012">2012</option>
								<option value="2013">2013</option>
								<option value="2014">2014</option>
								<option value="Pendiente">Pendiente</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="center">
						<s:submit
							cssClass="botonenviar"
							value="Asignar" /></td>
					</tr>
				</table>
			</div>
			</s:form>	
	</div>
	<script type="text/javascript">
		function cedulaSub(){
			document.cedula.submit()
		}
		function seleccionAll(){
			bool=false;
			b=true;
			formulario = document.getElementById("asignar");
			for(var i=0; i<formulario.elements.length; i++) {
				var elemento = formulario.elements[i];
				if(elemento.type == "checkbox" && b) {
				    if(elemento.checked==true ){
				    	bool=true;
				    }
					if(bool){
						elemento.checked=false;
					}else{
						elemento.checked=true;
					}
					b=false;
				 }
				if(elemento.type == "checkbox" && b==false) {
					if(bool){
						elemento.checked=false;
					}else{
						elemento.checked=true;
					}
				}
			}
		}
		function validaAsignaCedula(){
			var bandera=false;
			formulario = document.getElementById("asignar");
			for(var i=0; i<formulario.elements.length; i++) {
				var elemento = formulario.elements[i];
				if(elemento.type == "checkbox") {
				    if(elemento.checked) {
				    	bandera=true;
					   if(document.getElementById("anho").value!='-1'){
						   if(confirm("Se asignaran las PyMEs seleccionadas ")){
								return true;
							}
							else{
								return false;
							}
					   }else{
						   alert("No se ha seleccionado año");
					   }
					   break;
				    }
				 }
			}
			if(!bandera){
				alert("No se ha seleccionado ninguna PyME");
			}
			return false;
		}
	</script>
	<!-- EXPEDIENTE PYME -->
	<div id="resumenPyME" ${idUsuario==0? ' style="display: none;" ' :' style="display: block;"' }>
		<br />
		<br />
		<table class="expediente_tabla" width="99%">
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Expediente PyME</td>
			</tr>
			
			<tr>
				<td class="cuerpo1TextoResumen" colspan="2">
					<s:label cssClass="etiquetaResumen">${pyMEs.nombreComercial}</s:label>
				</td>
			</tr>
			<tr>
				<td class="cuerpo1TextoResumen" colspan="2">
					<s:label cssClass="etiquetaResumen">${pyMEs.mensajeVentas}</s:label>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="flotantes">
						<table width="100%">
							<tr>
								<td class="encabezadoTablaResumen" align="center">Principales Productos o Servicios</td>
							</tr>
							<!-- PRODUCTOS -->
							<s:iterator status="stat" value="pyMEs.productos" >
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.productos[stat.index].producto}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="flotantes">
						<table width="103%">
							<tr>
								<td class="encabezadoTablaResumen" align="center">Principales Clientes</td>
							</tr>
							<!-- TRACTORA --><!-- CLIENTES -->
							<tr>
								<td class="cuerpo1TextoResumen">
									<ul>
										<li class="resaltaText">
											<s:label>${pyMEs.clientes[0].cliente}</s:label>
										</li>
									</ul>
								</td>
							</tr>
							<s:if test="pyMEs.clientes[1].cliente != null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.clientes[1].cliente}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.clientes[2].cliente != null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.clientes[2].cliente}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.clientes[3].cliente != null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.clientes[3].cliente}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.clientes[4].cliente != null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.clientes[4].cliente}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.clientes[5].cliente != null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.clientes[5].cliente}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Desempeño de la PyME</td>
			</tr>
			
			<tr>
				<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${relPymesTractoras.comentario}</s:label></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<table>
						<tr>
							<td class="encabezadoTablaResumen" style="width: 470px;" align="center">PyME Recomendada por:</td>
							<td class="encabezadoTablaResumen" style="width: 470px;" align="center">Desempeño de la PyME de acuerdo con la empresa tractora:</td>
						</tr>
						<tr>
							<td class="cuerpo1TextoResumen" align="center">
								<s:if test="relPymesTractoras.recomendacion == true">
									<img src="${pageContext.request.contextPath}/img/tractora_${relPymesTractoras.idUsuarioTractora}.png" alt="Logo Tractora" />
								</s:if>
							</td>
							<td class="cuerpo1TextoResumen" align="center">
								<s:if test="relPyMEsTractoras.calificacion == 1">
									<img src="${pageContext.request.contextPath}/img/1_Estrellas.png" width="85px" height="17px" alt="1 Estrellas" />
								</s:if>
								<s:elseif test="relPyMEsTractoras.calificacion == 2">
									<img src="${pageContext.request.contextPath}/img/2_Estrellas.png" width="85px" height="17px" alt="2 Estrellas" />
								</s:elseif>
								<s:elseif test="relPyMEsTractoras.calificacion == 3">
									<img src="${pageContext.request.contextPath}/img/3_Estrellas.png" width="85px" height="17px" alt="3 Estrellas" />
								</s:elseif>
								<s:elseif test="relPyMEsTractoras.calificacion == 4">
									<img src="${pageContext.request.contextPath}/img/4_Estrellas.png" width="85px" height="17px" alt="4 Estrellas" />
								</s:elseif>
								<s:elseif test="relPyMEsTractoras.calificacion == 5">
									<img src="${pageContext.request.contextPath}/img/5_Estrellas.png" width="85px" height="17px" alt="5 Estrellas" />
								</s:elseif>
								<s:else>
									<img src="${pageContext.request.contextPath}/img/0_Estrellas.png" width="85px" height="17px" alt="Sin calificación" />
								</s:else>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			
			<tr>
				<td colspan="2">
					<div>
						<!-- INDICADORES -->
						<table width="100%">
							<tr>
								<td class="encabezadoTablaResumen" colspan="9" align="center">Indicadores de experiencias de compra</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 20%;">Concepto</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T1-2012</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T2-2012</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T3-2012</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T4-2012</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T1-2013</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T2-2013</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T3-2013</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;">T4-2013</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Ahorros (respecto del promedio de otras cotizaciones):</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosUnoEnero2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosUnoAbril2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosUnoJulio2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosUnoOctubre2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosUnoEnero2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosUnoAbril2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosUnoJulio2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosUnoOctubre2013}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Ahorros (respecto de la última cotización previo a tomar la consultoría):</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosDosEnero2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosDosAbril2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosDosJulio2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosDosOctubre2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosDosEnero2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosDosAbril2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosDosJulio2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorrosMonetariosDosOctubre2013}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Productos libres de defectos:</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosUnoEnero2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosUnoAbril2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosUnoJulio2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosUnoOctubre2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosUnoEnero2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosUnoAbril2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosUnoJulio2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosUnoOctubre2013}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Cumplimiento de servicios:</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosDosEnero2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosDosAbril2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosDosJulio2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosDosOctubre2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosDosEnero2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosDosAbril2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosDosJulio2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.defectosDosOctubre2013}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Cumplimiento en el tiempo de entrega:</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoUnoEnero2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoUnoAbril2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoUnoJulio2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoUnoOctubre2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoUnoEnero2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoUnoAbril2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoUnoJulio2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoUnoOctubre2013}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Efectividad en el tiempo de respuesta sobre cotizaciones:</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoDosEnero2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoDosAbril2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoDosJulio2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoDosOctubre2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoDosEnero2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoDosAbril2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoDosJulio2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.ahorroTiempoDosOctubre2013}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Tiempo de respuesta para atender reclamaciones o defectos:</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioUnoEnero2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioUnoAbril2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioUnoJulio2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioUnoOctubre2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioUnoEnero2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioUnoAbril2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioUnoJulio2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioUnoOctubre2013}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Eficacia en la atención sobre reclamaciones:</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioDosEnero2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioDosAbril2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioDosJulio2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioDosOctubre2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioDosEnero2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioDosAbril2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioDosJulio2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.servicioDosOctubre2013}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Crecimiento en ventas anuales a la tractora (a nivel de producto):</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.capacidadEnero2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.capacidadAbril2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.capacidadJulio2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.capacidadOctubre2012}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.capacidadEnero2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.capacidadAbril2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.capacidadJulio2013}</td>
								<td class="cuerpo1TextoResumen" >${indicadoresMes.capacidadOctubre2013}</td>
							</tr>
						</table>
					</div>
					<div>
						<table width="100%">
							<tr>
								<td class="encabezadoTablaResumen" colspan="3" align="center">Indicadores CCMX</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" align="center" style="width: 100px;">&nbsp;</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 170px;">Antes de la Consultoría</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 170px;">Después de la Consultoría</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="height: 29px;">RH:</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.recursosHumanosAntes}</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.recursosHumanosDespues}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="height: 29px;">Mercadeo:</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.mercadeoAntes}</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.mercadeoDespues}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="height: 29px;">Finanzas:</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.finanzasAntes}</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.finanzasDespues}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="height: 29px;">Administración:</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.administracionAntes}</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.administracionDespues}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="height: 29px;">Procesos:</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.procesosAntes}</td>
								<td class="cuerpo1TextoResumen">${serviciosConsultoria.procesosDespues}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Avance Promedio:</td>
								<td class="cuerpo1TextoResumen" colspan="2" align="center">
									<label id="formulaRadar">
										<script type="text/javascript">
											$(document).ready(function(){
												var rhA = <s:property value="serviciosConsultoria.recursosHumanosAntes" />;
												var mA = <s:property value="serviciosConsultoria.mercadeoAntes" />;
												var fA = <s:property value="serviciosConsultoria.finanzasAntes" />;
												var aA = <s:property value="serviciosConsultoria.administracionAntes" />;
												var pA = <s:property value="serviciosConsultoria.procesosAntes" />;
												var vpi = (rhA + mA + fA + aA + pA) / 5;
												var rhD = <s:property value="serviciosConsultoria.recursosHumanosDespues" />;
												var mD = <s:property value="serviciosConsultoria.mercadeoDespues" />;
												var fD = <s:property value="serviciosConsultoria.finanzasDespues" />;
												var aD = <s:property value="serviciosConsultoria.administracionDespues" />;
												var pD = <s:property value="serviciosConsultoria.procesosDespues" />;
												var vpf = (rhD + mD + fD + aD + pD) / 5;
												var avance = (vpf - vpi) / vpi;
												var valAvance = parseFloat(avance).toFixed(2);
												if(isNaN(valAvance)){
													document.getElementById("formulaRadar").innerHTML = '0.0';
												}else{
													document.getElementById("formulaRadar").innerHTML = valAvance;
												}
											});
										</script>
									</label>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td class="encabezadoTablaResumen" colspan="3" align="center">Gráficas de Indicadores CCMX</td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="center">Antes de la Consultoría</td>
				<td class="cuerpo1TablaResumen" align="center">Después de la Consultoría</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td>
								<canvas id="cvsAntes" width="350" height="250">[No canvas support]</canvas>
								<script>
						
								</script>
							</td>
						</tr>
					</table>
				</td>
				
				<td>
					<table>
						<tr>
							<td>
								<canvas id="cvsDesp" width="350" height="250">[No canvas support]</canvas>
								<script>
									window.onload = function() {
										var radar = new RGraph.Radar('cvsAntes', [ <s:property value="serviciosConsultoria.recursosHumanosAntes" />, <s:property value="serviciosConsultoria.mercadeoAntes" />, <s:property value="serviciosConsultoria.finanzasAntes" />, <s:property value="serviciosConsultoria.administracionAntes" />, <s:property value="serviciosConsultoria.procesosAntes" /> ]);
										radar.Set('chart.labels', [ 'Recursos Humanos\n('+<s:property value="serviciosConsultoria.recursosHumanosAntes" />+')', 'Mercadeo\n('+<s:property value="serviciosConsultoria.mercadeoAntes" />+')', 'Finanzas\n('+<s:property value="serviciosConsultoria.finanzasAntes" />+')', 'Administración\n('+<s:property value="serviciosConsultoria.administracionAntes" />+')', 'Procesos\n('+<s:property value="serviciosConsultoria.procesosAntes" />+')']);
										radar.Set('chart.highlights', true);
										radar.Set('chart.linewidth', 1);
										radar.Set('chart.strokestyle', ['lightblue']);
										radar.Set('chart.labels.axes', 'n');
										radar.Set('chart.labels.axes.boxed', false);
										radar.Set('chart.labels.axes.boxed.zero', false);
										radar.Set('chart.labels.offset', 25);
										radar.Set('chart.text.size', 9);
										radar.Set('chart.gutter.left', 30);
										radar.Set('chart.gutter.right', 30);
										radar.Set('chart.gutter.top', 40);
										radar.Set('chart.gutter.bottom', 20);
							            radar.Draw();
							            
							            
							            var radarDesp = new RGraph.Radar('cvsDesp', [ <s:property value="serviciosConsultoria.recursosHumanosDespues" />, <s:property value="serviciosConsultoria.mercadeoDespues" />, <s:property value="serviciosConsultoria.finanzasDespues" />, <s:property value="serviciosConsultoria.administracionDespues" />, <s:property value="serviciosConsultoria.procesosDespues" /> ]);
										radarDesp.Set('chart.labels', [ 'Recursos Humanos\n('+<s:property value="serviciosConsultoria.recursosHumanosDespues" />+')', 'Mercadeo\n('+<s:property value="serviciosConsultoria.mercadeoDespues" />+')', 'Finanzas\n('+<s:property value="serviciosConsultoria.finanzasDespues" />+')', 'Administración\n('+<s:property value="serviciosConsultoria.administracionDespues" />+')', 'Procesos\n('+<s:property value="serviciosConsultoria.procesosDespues" />+')']);
										radarDesp.Set('chart.highlights', true);
										radarDesp.Set('chart.linewidth', 1);
										radarDesp.Set('chart.strokestyle', ['lightblue']);
										radarDesp.Set('chart.labels.axes', 'n');
										radarDesp.Set('chart.labels.axes.boxed', false);
										radarDesp.Set('chart.labels.axes.boxed.zero', false);
										radarDesp.Set('chart.labels.offset', 25);
										radarDesp.Set('chart.text.size', 9);
										radarDesp.Set('chart.gutter.left', 30);
										radarDesp.Set('chart.gutter.right', 30);
										radarDesp.Set('chart.gutter.top', 40);
										radarDesp.Set('chart.gutter.bottom', 20);
										radarDesp.Draw();
									};
								</script>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Estados en los que puede suministrar su producto</td>
			</tr>
			<!-- ESTADOS -->
			<s:if test="estadosVentas.nacional != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.nacional}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.aguascalientes != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.aguascalientes}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.bajaCaliforniaNorte != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.bajaCaliforniaNorte}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.bajaCaliforniaSur != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.bajaCaliforniaSur}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.campeche != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.campeche}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.chiapas != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.chiapas}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.chihuahua != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.chihuahua}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.coahuila != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.coahuila}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.colima != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.colima}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.distritoFederal != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.distritoFederal}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.durango !=  null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.durango}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.guanajuato != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.guanajuato}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.guerrero != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.guerrero}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.hidalgo!= null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.hidalgo}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.jalisco != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.jalisco}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.estadoDeMexico != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.estadoDeMexico}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.michoacan != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.michoacan}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.morelos != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.morelos}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.nayarit != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.nayarit}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.nuevoLeon != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.nuevoLeon}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.oaxaca != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.oaxaca}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.puebla != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.puebla}</s:label></td>
				</tr>
			</s:if>
			
			<s:if test="estadosVentas.queretaro != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.queretaro}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.quintanaRoo != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.quintanaRoo}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.sanLuisPotosi != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.sanLuisPotosi}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.sinaloa != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.sinaloa}</s:label></td>
				</tr>			
			</s:if>
			
			<s:if test="estadosVentas.sonora != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.sonora}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.tabasco != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.tabasco}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.tamaulipas != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.tamaulipas}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.tlaxcala != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.tlaxcala}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.veracruz!= null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.veracruz}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.yucatan != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.yucatan}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.zacatecas != null">
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2"><s:label cssClass="etiquetaResumen">${estadosVentas.zacatecas}</s:label></td>
				</tr>			
			</s:if>
			
			
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Datos de contacto</td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="100%">
						<tr>
							<td class="cuerpo1TablaResumen" align="center" colspan="2">Ventas</td>
							<td class="cuerpo1TablaResumen" align="center" colspan="2">Otro</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Nombre:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.contactos[0].nombre} ${pyMEs.contactos[0].apellidoPaterno} ${pyMEs.contactos[0].apellidoMaterno}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Nombre:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.contactos[1].nombre} ${pyMEs.contactos[1].apellidoPaterno} ${pyMEs.contactos[1].apellidoMaterno}</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen">Teléfono:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.contactos[0].telefono}</td>
							<td class="cuerpo1TablaResumen">Telefóno:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.contactos[1].telefono}</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen">Correo Electrónico:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.contactos[0].correoElectronico}</td>
							<td class="cuerpo1TablaResumen">Correo Electrónico:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.contactos[1].correoElectronico}</td>
						</tr>
					</table>
				</td>
			</tr>
			
			
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Certificaciones de la empresa y calificaciones de los empleados</td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="100%">
						<tr>
							<td class="cuerpo1TablaResumen" align="center" colspan="2">Certificaciones</td>
							<td class="cuerpo1TablaResumen" align="center" colspan="2">Consultoria CCMX</td>
							<td class="cuerpo1TablaResumen" align="center" colspan="2">Diplomados</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Certificación:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[0].certificacion}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Horas:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">
								<s:if test="serviciosConsultoria.bConsultoriaVeinte==true">
									20
								</s:if>
								<s:elseif test="serviciosConsultoria.bConsultoriaCuarenta==true">
									40
								</s:elseif>
								<s:elseif test="serviciosConsultoria.bConsultoriaSesenta==true">
									60
								</s:elseif>
								<s:elseif test="serviciosConsultoria.bConsultoriaOchenta==true">
									80
								</s:elseif>
								
							</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Diplomado en:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">
								<s:if test="pyMEs.bDiplomadoCcmxUno==true">
									Cultura organizacional y la competitividad de las empresas
								</s:if>
							</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Año:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[0].fechaCertificacion}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Fecha de inicio:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${serviciosConsultoria.inicio}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Diplomado en:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">
								<s:if test="pyMEs.bDiplomadoCcmxDos==true">
									Estrategia Comercial, Imagen y Cadena de Distribución
								</s:if>
							</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Institución:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[0].institutoCertificador}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Fecha de termino:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${serviciosConsultoria.termino}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Diplomado en:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">
								<s:if test="pyMEs.bDiplomadoCcmxTres==true">
									Desarrollo de Métodos de Producción Esbeltos y Cadena de Valor
								</s:if>
							</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen" colspan="2">&nbsp;</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Cédula:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.cedula}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Diplomado en:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">
								<s:if test="pyMEs.bDiplomadoCcmxCuatro==true">
									Estrategia, Planeación e Innovación
								</s:if>
							</td>
						</tr>
						<s:if test="pyMEs.certificaciones[1].certificacion != null">
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Certificación:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[1].certificacion}</td>
								<td colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Año:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[1].fechaCertificacion}</td>
								<td colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Institución:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[1].institutoCertificador}</td>
								<td colspan="4">&nbsp;</td>
							</tr>
						</s:if>
						<s:if test="pyMEs.certificaciones[2].certificacion != null">
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Certificación:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[2].certificacion}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Año:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[2].fechaCertificacion}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Institución:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[2].institutoCertificador}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
						</s:if>
						<s:if test="pyMEs.certificaciones[3].certificacion != null">
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Certificación:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[3].certificacion}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Año:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[3].fechaCertificacion}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Institución:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[3].institutoCertificador}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
						</s:if>
						<s:if test="pyMEs.certificaciones[4].certificacion != null">
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Certificación:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[4].certificacion}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Año:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[4].fechaCertificacion}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Institución:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificaciones[4].institutoCertificador}</td>
								<td  colspan="4">&nbsp;</td>
							</tr>
						</s:if>
					</table>
				</td>
			</tr>
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Indicadores</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<table width="100%">
						<tr>
							<td class="cuerpo1TablaResumen" align="center" style="width: 250px;">&nbsp;</td>
							<td class="cuerpo1TablaResumen" align="center" style="width: 250px;">Antes</td>
							<td class="cuerpo1TablaResumen" align="center" style="width: 250px;">Después</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen">Ventas o ingresos  acumulados:</td>
							<td class="cuerpo1TextoResumen">${indicadores.ingresosAntes}</td>
							<td class="cuerpo1TextoResumen">${indicadores.ingresosDespues}</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen">Número de clientes:</td>
							<td class="cuerpo1TextoResumen">${indicadores.clientesAntes}</td>
							<td class="cuerpo1TextoResumen">${indicadores.clientesDespues}</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen">Número de empleados:</td>
							<td class="cuerpo1TextoResumen">${indicadores.empleadosAntes}</td>
							<td class="cuerpo1TextoResumen">${indicadores.empleadosDespues}</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen">% Egresos / Ventas:</td>
							<td class="cuerpo1TextoResumen">${indicadores.egresosAntes}</td>
							<td class="cuerpo1TextoResumen">${indicadores.egresosDespues}</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Conozca más sobre nuestros productos y sobre nuestra empresa</td>
			</tr>
			<s:if test="pyMEs.idArchivo1!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo1}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/consultoraShowDoc.do?idArchivo=${pyMEs.idArchivo1}&nameArchivo=${pyMEs.archivo1FileName}&mimeArchivo=${pyMEs.archivo1ContentType}">${pyMEs.archivo1FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo2!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo2}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/consultoraShowDoc.do?idArchivo=${pyMEs.idArchivo2}&nameArchivo=${pyMEs.archivo2FileName}&mimeArchivo=${pyMEs.archivo2ContentType}">${pyMEs.archivo2FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo3!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo3}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/consultoraShowDoc.do?idArchivo=${pyMEs.idArchivo3}&nameArchivo=${pyMEs.archivo3FileName}&mimeArchivo=${pyMEs.archivo3ContentType}">${pyMEs.archivo3FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo4!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo4}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/consultoraShowDoc.do?idArchivo=${pyMEs.idArchivo4}&nameArchivo=${pyMEs.archivo4FileName}&mimeArchivo=${pyMEs.archivo4ContentType}">${pyMEs.archivo4FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo5!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo5}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/consultoraShowDoc.do?idArchivo=${pyMEs.idArchivo5}&nameArchivo=${pyMEs.archivo5FileName}&mimeArchivo=${pyMEs.archivo5ContentType}">${pyMEs.archivo5FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo6!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo6}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/consultoraShowDoc.do?idArchivo=${pyMEs.idArchivo6}&nameArchivo=${pyMEs.archivo6FileName}&mimeArchivo=${pyMEs.archivo6ContentType}">${pyMEs.archivo6FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo7!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo7}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/consultoraShowDoc.do?idArchivo=${pyMEs.idArchivo7}&nameArchivo=${pyMEs.archivo7FileName}&mimeArchivo=${pyMEs.archivo7ContentType}">${pyMEs.archivo7FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo8!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo8}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/consultoraShowDoc.do?idArchivo=${pyMEs.idArchivo8}&nameArchivo=${pyMEs.archivo8FileName}&mimeArchivo=${pyMEs.archivo8ContentType}">${pyMEs.archivo8FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo9!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo9}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/consultoraShowDoc.do?idArchivo=${pyMEs.idArchivo9}&nameArchivo=${pyMEs.archivo9FileName}&mimeArchivo=${pyMEs.archivo9ContentType}">${pyMEs.archivo9FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo10!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo10}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/consultoraShowDoc.do?idArchivo=${pyMEs.idArchivo10}&nameArchivo=${pyMEs.archivo10FileName}&mimeArchivo=${pyMEs.archivo10ContentType}">${pyMEs.archivo10FileName}</a>
					</td>
				</tr>
			</s:if>
			<tr>
				<td class="cuerpo1TextoResumen" colspan="2" align="center"><s:label cssClass="etiquetaResumen">${pyMEs.rfc}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TextoResumen" colspan="2" align="center">
					<s:label cssClass="etiquetaResumen">
						${domicilios.calle} 
						${domicilios.numExt} 
						${domicilios.numInt} 
						${domicilios.piso} 
						${domicilios.colonia} 
						${domicilios.delegacion} 
						${domicilios.estado} 
						${domicilios.codigoPostal}
					</s:label>
				</td>
			</tr>
			<tr>
				<td class="cuerpo1TextoResumen" colspan="2" align="center"><s:label cssClass="etiquetaResumen">${pyMEs.paginaWeb}</s:label></td>
			</tr>
					

			<!--<s:iterator value="tractoras.telefonos" status="stat">
				<tr>
					<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="left">&nbsp;Teléfono ${stat.count}:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${telefono}</s:label></td>
				</tr>
			</s:iterator>-->
			
		</table>
		<table class="submit_tabla">
			<tr>
				<td style="width: 450px;"></td>
				<td><input
					class="botonenviar"
					value="Regresar"
					type="button"
					onclick="javascript: window.history.back();" /></td>
				<td style="width: 450px;"></td>
			</tr>
		</table>
	</div>
	</fieldset>
</body>
</html>