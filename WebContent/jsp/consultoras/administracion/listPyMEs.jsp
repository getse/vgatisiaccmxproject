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
	<div id="busqPyME" ${idUsuario!=0? ' style="display: none;" ' :' style="display: block;"' }>
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
				<s:label cssClass="camposObligatorios"
					value="Asignar cedula PYME en las que se encuentra disponible el campo de selección, asignando tambien el año." />
			</legend>
			<br />
			<table>
				<tr>
					<td>
						<table>
							<tr>
								<td colspan="2" style="width: 550px;">
									<s:label cssClass="etiquetaCaptura" value="* Busqueda por palabra clave: " />
									<s:textfield size="77" id="campoBusqueda" name="busqueda" maxlength="60"></s:textfield>
									<s:hidden id="idHiddNombreCom" name="nombreCom" value="%{busqueda}" />
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<s:label cssClass="etiquetaAyuda" value="Escriba la(s) palabra(s) que identifican el producto o nombre comercial que busca." />
								</td>
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
									<s:textarea id="idInputCatScian" rows="1" cols="53" disabled="true" cssClass="resultado" name="producto" />
									<br />
									<div id="idDivTipPro" style="display: block; margin-bottom: 0px; margin-top: -10px;">
										<s:label cssClass="etiquetaAyuda" value="Seleccione o búsque la categoría en la cual se encuentra su producto." />
										<br />
									</div>
									<div id="idDivTipPro2" style="display: block; margin-bottom: 0px; margin-top: -5px;">
										<s:label cssClass="etiquetaAyuda" value="" />
										<br />
									</div>
								</td>
							</tr>
						</table>
					</td>
					<td style="margin-top: 5px; margin-left: 5px; display: block;">
						<s:label cssClass="etiquetaCaptura" value="Entidad Federativa:" />
						<br />
						<select id="estado" name="estado" style="width: 200px;">
							<option ${estado == '-1' ? ' selected="selected" ' : ''} value="-1">--Seleccione un estado--</option>
							<option ${estado == 'Aguascalientes' ? ' selected="selected" ' : ''} value="Aguascalientes">Aguascalientes</option>
							<option ${estado == 'Baja California' ? ' selected="selected" ' : ''} value="Baja California">Baja California</option>
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
							<option ${estado == 'Estado de Mexico' ? ' selected="selected" ' : ''} value="Estado de Mexico">Estado de Mexico</option>
							<option ${estado == 'Michoacan' ? ' selected="selected" ' : ''} value="Michoacan">Michoacan</option>
							<option ${estado == 'Morelos' ? ' selected="selected" ' : ''} value="Morelos">Morelos</option>
							<option ${estado == 'Nayarit' ? ' selected="selected" ' : ''} value="Nayarit">Nayarit</option>
							<option ${estado == 'Nuevo Leon' ? ' selected="selected" ' : ''} value="Nuevo Leon">Nuevo Leon</option>
							<option ${estado == 'Oaxaca' ? ' selected="selected" ' : ''} value="Oaxaca">Oaxaca</option>
							<option ${estado == 'Puebla' ? ' selected="selected" ' : ''} value="Puebla">Puebla</option>
							<option ${estado == 'Quertaro' ? ' selected="selected" ' : ''} value="Quertaro">Quertaro</option>
							<option ${estado == 'Quintana Roo' ? ' selected="selected" ' : ''} value="Quintana Roo">Quintana Roo</option>
							<option ${estado == 'San Luis Potosi' ? ' selected="selected" ' : ''} value="San Luis Potosi">San Luis Potosi</option>
							<option ${estado == 'Sinaloa' ? ' selected="selected" ' : ''} value="Sinaloa">Sinaloa</option>
							<option ${estado == 'Sonora' ? ' selected="selected" ' : ''} value="Sonora">Sonora</option>
							<option ${estado == 'Tabasco' ? ' selected="selected" ' : ''} value="Tabasco">Tabasco</option>
							<option ${estado == 'Tamaulipas' ? ' selected="selected" ' : ''} value="Tamaulipas">Tamaulipas</option>
							<option ${estado == 'Tlaxcala' ? ' selected="selected" ' : ''} value="Tlaxcala">Tlaxcala</option>
							<option ${estado == 'Veracruz' ? ' selected="selected" ' : ''} value="Veracruz">Veracruz</option>
							<option ${estado == 'Yucatan' ? ' selected="selected" ' : ''} value="Yucatan">Yucatan</option>
							<option ${estado == 'Zacatecas' ? ' selected="selected" ' : ''} value="Zacatecas">Zacatecas</option>
						</select>
						<br /><s:label cssClass="etiquetaAyuda" value="Seleccione el estado que identifica el producto que busca." />
					</td>
				</tr>
			</table>
			<br />
			</s:form>
			<!-- Lista busqueda -->
			<s:form 
			id="cedula"
			action="consultoraPyMEsShow" 
			namespace="/consultor/administracion" 
			theme="simple" 
			onsubmit="return validaAsignaCedula()">
			<s:hidden name="cveScian" id="idCveSci" value="%{cveScian}" />
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
								<td class="encabezado_tabla" align="center"><b>Ver Expediente
										</b></td>
								<td class="encabezado_tabla" align="center"><b>Asignar
									cedula</b></td>
							</tr>
						</thead>
					<tbody>
						<s:set var="contador" value="0" />
						<s:iterator value="listPyMEs" status="stat">
							
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
													/consultor/administracion/consultoraPyMEsShow.do?idUsuario=${idUsuario}">Ver Expediente</a></td>
									<s:if test="cedulaModificable">
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center"><input type="checkbox" name="pymesSelected" value="${idUsuario}"></input></td>
									</s:if>
									<s:else>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">NA</td>
									</s:else>
								</tr>							
						</s:iterator>
					</tbody>
					<tr>
						<td colspan="4">
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
					
					<td colspan="2">
						<s:submit cssClass="botonenviar" align="left" value="Asignar cedula" />
					</td>
				</tr>
			</table>	
			</s:form>	
	</div>
	<script type="text/javascript">
		function validaAsignaCedula(){
			var bandera=false;
			formulario = document.getElementById("cedula");
			for(var i=0; i<formulario.elements.length; i++) {
				var elemento = formulario.elements[i];
				if(elemento.type == "checkbox") {
				    if(elemento.checked) {
				    	bandera=true;
					   if(document.getElementById("anho").value!='-1'){
						   if(confirm("Se asignaran las PYMES seleccionadas ")){
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
				alert("No se ha seleccionado ninguna PYME");
			}
			return false;
		}
	</script>
	<!-- EXPEDIENTE PYME -->
	<div id="resumenPyME" ${idUsuario==0? ' style="display: none;" ' :' style="display: block;"' }>
		<br />
		<br />
		<table class="expediente_tabla">
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Expediente PyME</td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Mensaje:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.mensajeVentas}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Nombre comercial de la empresa:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.nombreComercial}</s:label></td>
			</tr>
			
			<!-- PRODUCTOS -->
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto1}</s:label></td>
			</tr>
			<s:if test="pyMEs.producto2!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto2}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto3!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto3}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto4!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto4}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto5!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto5}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto6!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto6}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto7!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto7}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto8!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto8}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto9!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto9}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto10!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto10}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto11!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto11}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto12!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto12}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto13!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto13}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto14!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto14}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto15!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto15}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto16!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto16}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto17!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto17}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto18!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto18}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto19!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto19}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.producto20!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto que vende:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto20}</s:label></td>
				</tr>
			</s:if>
			
			<!-- CLIENTES -->
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Principales clientes:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.cliente1}</s:label></td>
			</tr>
			<s:if test="pyMEs.cliente2!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Principales clientes:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.cliente2}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.cliente3!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Principales clientes:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.cliente3}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.cliente4!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Principales clientes:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.cliente4}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.cliente5!=null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Principales clientes:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.cliente5}</s:label></td>
				</tr>
			</s:if>
			
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Página web:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.paginaWeb}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Nombres contacto de Ventas:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.nombreContacto1}</s:label></td>
			</tr>
			
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Apellido Paterno Contacto de Ventas:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.appPaterno1}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Apellido Materno Contacto de Ventas:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.appMaterno1}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Correo Electronico contacto de Ventas:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.correoElectronicoContacto1}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Telefono de Contacto de Ventas:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.telefonoContacto1}</s:label></td>
			</tr>
			
			<!-- ESTADOS -->
			<s:if test="estadosVentas.nacional != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.nacional}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.aguascalientes != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.aguascalientes}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.bajaCaliforniaNorte != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.bajaCaliforniaNorte}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.bajaCaliforniaSur != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.bajaCaliforniaSur}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.campeche != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.campeche}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.chiapas != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.chiapas}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.chihuahua != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.chihuahua}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.coahuila != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.coahuila}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.colima != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.colima}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.distritoFederal != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.distritoFederal}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.durango !=  null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.durango}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.guanajuato != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.guanajuato}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.guerrero != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.guerrero}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.hidalgo!= null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.hidalgo}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.jalisco != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.jalisco}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.estadoDeMexico != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.estadoDeMexico}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.michoacan != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.michoacan}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.morelos != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.morelos}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.nayarit != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.nayarit}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.nuevoLeon != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.nuevoLeon}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.oaxaca != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.oaxaca}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.puebla != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.puebla}</s:label></td>
				</tr>
			</s:if>
			
			<s:if test="estadosVentas.queretaro != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.queretaro}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.quintanaRoo != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.quintanaRoo}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.sanLuisPotosi != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.sanLuisPotosi}</s:label></td>
				</tr>
			</s:if>
			<s:if test="estadosVentas.sinaloa != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.sinaloa}</s:label></td>
				</tr>			
			</s:if>
			
			<s:if test="estadosVentas.sonora != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.sonora}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.tabasco != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.tabasco}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.tamaulipas != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.tamaulipas}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.tlaxcala != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.tlaxcala}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.veracruz!= null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.veracruz}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.yucatan != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.yucatan}</s:label></td>
				</tr>			
			</s:if>
			<s:if test="estadosVentas.zacatecas != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.zacatecas}</s:label></td>
				</tr>			
			</s:if>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Certificaciones:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.certificacion1}</s:label></td>
			</tr>
			<s:if test="certificaciones.certificacion2 != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Certificaciones:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.certificacion2}</s:label></td>
				</tr>
			</s:if>
			<s:if test="certificaciones.certificacion3 != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Certificaciones:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.certificacion3}</s:label></td>
				</tr>
			</s:if>
			<s:if test="certificaciones.certificacion4 != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Certificaciones:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.certificacion4}</s:label></td>
				</tr>
			</s:if>
			<s:if test="certificaciones.certificacion5 != null">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Certificaciones:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.certificacion5}</s:label></td>
				</tr>
			</s:if>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Consultoría:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.bServiciosCcmxConsultoria}</s:label></td>
			</tr>
			<s:if test="pyMEs.bDiplomadoCcmxUno==true">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Diplomado:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Cultura organizacional y la competitividad de las empresas</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bDiplomadoCcmxDos==true">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Diplomado:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Estrategia Comercial, Imagen y Cadena de Distribución</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bDiplomadoCcmxTres==true">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Diplomado:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Desarrollo de Métodos de Producción Esbeltos y Cadena de Valor</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bDiplomadoCcmxCuatro==true">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Diplomado:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Estrategia, Planeación e Innovación</s:label></td>
				</tr>
			</s:if>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Ventas o ingresos  acumulados (antes):</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${indicadores.ingresosAntes}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Ventas o ingresos  acumulados (después):</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${indicadores.ingresosDespues}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Numero de clientes (antes):</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${indicadores.clientesAntes}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Numero de clientes (después):</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${indicadores.clientesDespues}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Número de empleados (antes):</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${indicadores.empleadosAntes}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Número de empleados (después):</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${indicadores.empleadosDespues}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;% Egresos / Ventas (antes):</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${indicadores.egresosAntes}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;% Egresos / Ventas (después):</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${indicadores.egresosDespues}</s:label></td>
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
		<table>
		</table>
	</div>
</fieldset>
</body>
</html>
