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
<!--[if lt IE 9]><script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/excanvas/excanvas.js"></script><![endif]-->
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-195px auto 0 250px';
</script>
</head>

<body>

<fieldset id="requerimientos">
	<div id="busqPyME" ${idUsuario!=0? ' style="display: none;" ' :' style="display: block;"' }>
		<s:form action="pymeBusquedaShow" namespace="/pyme" theme="simple" onsubmit="return validacionBusqueda()">
			<s:hidden name="cveScian" id="idCveSci" value="%{cveScian}" />
			<s:hidden name="producto" id="idProd" value="%{producto}" />
			<legend>
				<s:label value="Búsqueda de PyMEs" />
				<br /> <br />
				<s:label cssClass="camposObligatorios"
					value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
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
									<s:textarea id="idInputCatScian" rows="1" cols="53" disabled="true" cssClass="resultado" name="producto" value="%{producto}" />
									<br />
									<div id="idDivTipPro" style="display: none; margin-bottom: 0px; margin-top: -10px;">
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
						<br /><s:label cssClass="etiquetaAyuda" value="Seleccione el Estado en el cual desea que se provea el producto." />
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
						<td class="encabezado_tabla" align="center"><b>No.</b></td>
						<td class="encabezado_tabla" align="center"><b>Nombre comercial</b></td>
						<td class="encabezado_tabla" align="center"><b>Entidad Federativa</b></td>
						<td class="encabezado_tabla" align="center"><b>Teléfono</b></td>
						<td class="encabezado_tabla" align="center"><b>Nombre contacto</b></td>
						<td class="encabezado_tabla" align="center"><b>Apellido Paterno Contacto</b></td>
						<td class="encabezado_tabla" align="center"><b>Apellido Materno Contacto</b></td>
						<td class="encabezado_tabla" align="center"><b>Correo electrónico contacto</b></td>
						<td class="encabezado_tabla" align="center"><b>Ver Expediente</b></td>
					</tr>
				</thead>
				<tbody>
					<s:set var="contador" value="0" />
					<s:iterator value="listPyMEs" status="stat">
						<s:set var="cnt" value="#contador=#contador+1" />
						<tr>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${cnt}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${nombreComercial}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${estado}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${telefonoContacto1=='null'?'':telefonoContacto1}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${nombreContacto1}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${appPaterno1}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${appMaterno1}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${correoElectronicoContacto1}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center"><a href="${pageContext.request.contextPath}/pyme/pymeBusquedaShow.do?idUsuario=${idUsuario}">Expediente</a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:form>
	</div>
	
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
							<tr>
								<td class="cuerpo1TextoResumen">
									<ul>
										<li>
											<s:label cssClass="etiquetaResumen">${pyMEs.producto1}</s:label>
										</li>
									</ul>
									
								</td>
							</tr>
							
							<s:if test="pyMEs.producto2!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto2}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							
							<s:if test="pyMEs.producto3!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto3}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto4!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto4}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto5!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto5}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto6!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto6}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto7!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto7}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto8!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto8}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto9!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto9}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto10!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto10}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto11!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto11}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto12!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto12}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto13!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto13}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto14!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto14}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto15!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto15}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto16!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto16}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto17!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto17}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto18!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto18}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto19!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto19}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.producto20!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.producto20}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
						</table>
					</div>
					<div class="flotantes">
						<table width="100%">
							<tr>
								<td class="encabezadoTablaResumen" align="center">Principales Clientes</td>
							</tr>
							<!-- TRACTORA --><!-- CLIENTES -->
							<tr>
								<td class="cuerpo1TextoResumen">
									<ul>
										<li class="resaltaText">
											<s:label>${tractoras.empresa}</s:label>
										</li>
									</ul>
								</td>
							</tr>
							<s:if test="pyMEs.cliente1!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.cliente1}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.cliente2!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.cliente2}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.cliente3!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.cliente3}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.cliente4!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.cliente4}</s:label>
											</li>
										</ul>
									</td>
								</tr>
							</s:if>
							<s:if test="pyMEs.cliente5!=null">
								<tr>
									<td class="cuerpo1TextoResumen">
										<ul>
											<li>
												<s:label cssClass="etiquetaResumen">${pyMEs.cliente5}</s:label>
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
							<td class="cuerpo1TextoResumen" align="center">Aquí va el logo</td>
							<td class="cuerpo1TextoResumen" align="center">
								<s:if test="relPymesTractoras.calificacion == 1">
									<img src="${pageContext.request.contextPath}/img/1_Estrellas.png" width="85px" height="17px" alt="1 Estrellas" />
								</s:if>
								<s:elseif test="relPymesTractoras.calificacion == 2">
									<img src="${pageContext.request.contextPath}/img/2_Estrellas.png" width="85px" height="17px" alt="2 Estrellas" />
								</s:elseif>
								<s:elseif test="relPymesTractoras.calificacion == 3">
									<img src="${pageContext.request.contextPath}/img/3_Estrellas.png" width="85px" height="17px" alt="3 Estrellas" />
								</s:elseif>
								<s:elseif test="relPymesTractoras.calificacion == 4">
									<img src="${pageContext.request.contextPath}/img/4_Estrellas.png" width="85px" height="17px" alt="4 Estrellas" />
								</s:elseif>
								<s:elseif test="relPymesTractoras.calificacion == 5">
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
					<div class="flotantes">
					<table>
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
											document.getElementById("formulaRadar").innerHTML = valAvance;
										});
									</script>
								</label>
							</td>
						</tr>
					</table>
					</div>
					<div class="flotantes">
						<!-- INDICADORES -->
						<table>
							<tr>
								<td class="encabezadoTablaResumen" colspan="5" align="center">Indicadores de experiencias de compra</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Concepto</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 170px;">T1-2012</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 170px;">T2-2012</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 170px;">T3-2012</td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 170px;">T4-2012</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Ahorros monetarios:</td>
								<td class="cuerpo1TextoResumen" >${indicadores.ahorrosMonetariosEnero}</td>
								<td class="cuerpo1TextoResumen" >${indicadores.ahorrosMonetariosAbril}</td>
								<td class="cuerpo1TextoResumen" >${indicadores.ahorrosMonetariosJulio}</td>
								<td class="cuerpo1TextoResumen" >${indicadores.ahorrosMonetariosOctubre}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Defectos en producto o servicios:</td>
								<td class="cuerpo1TextoResumen" >${indicadores.defectosEnero}</td>
								<td class="cuerpo1TextoResumen" >${indicadores.defectosAbril}</td>
								<td class="cuerpo1TextoResumen" >${indicadores.defectosJulio}</td>
								<td class="cuerpo1TextoResumen" >${indicadores.defectosOctubre}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Ahorro en tiempo:</td>
								<td class="cuerpo1TextoResumen" >${indicadores.ahorroTiempoEnero}</td>
								<td class="cuerpo1TextoResumen" >${indicadores.ahorroTiempoAbril}</td>
								<td class="cuerpo1TextoResumen" >${indicadores.ahorroTiempoJulio}</td>
								<td class="cuerpo1TextoResumen" >${indicadores.ahorroTiempoOctubre}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Servicio post-venta:</td>
								<td class="cuerpo1TextoResumen" >${indicadores.servicioEnero}</td>
								<td class="cuerpo1TextoResumen" >${indicadores.servicioAbril}</td>
								<td class="cuerpo1TextoResumen" >${indicadores.servicioJulio}</td>
								<td class="cuerpo1TextoResumen" >${indicadores.servicioOctubre}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Capacidad de la PYME:</td>
								<td class="cuerpo1TextoResumen" >${indicadores.capacidadEnero}</td>
								<td class="cuerpo1TextoResumen" >${indicadores.capacidadAbril}</td>
								<td class="cuerpo1TextoResumen" >${indicadores.capacidadJulio}</td>
								<td class="cuerpo1TextoResumen">${indicadores.capacidadOctubre}</td>
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
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.nombreContacto1} ${pyMEs.appPaterno1} ${pyMEs.appMaterno1}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Nombre:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.nombreContacto2} ${pyMEs.appPaterno2} ${pyMEs.appMaterno2}</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen">Teléfono:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.telefonoContacto1}</td>
							<td class="cuerpo1TablaResumen">Telefóno:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.telefonoContacto2}</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen">Correo Electrónico:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.correoElectronicoContacto1}</td>
							<td class="cuerpo1TablaResumen">Correo Electrónico:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.correoElectronicoContacto2}</td>
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
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificacion1}</td>
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
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.fechaCertificacion1}</td>
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
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.institutoCertificador1}</td>
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
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Cédula:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.cedula}</td>
							<td class="cuerpo1TablaResumen" style="width: 150px;">Diplomado en:</td>
							<td class="cuerpo1TextoResumen" style="width: 380px;">
								<s:if test="pyMEs.bDiplomadoCcmxCuatro==true">
									Estrategia, Planeación e Innovación
								</s:if>
							</td>
						</tr>
						<s:if test="certificaciones.certificacion2 != null">
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Certificación:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificacion2}</td>
								<td colspan="4">nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Año:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.fechaCertificacion2}</td>
								<td colspan="4">nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Institución:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.institutoCertificador2}</td>
								<td colspan="4">nbsp;</td>
							</tr>
						</s:if>
						<s:if test="certificaciones.certificacion3 != null">
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Certificación:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificacion3}</td>
								<td  colspan="4">nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Año:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.fechaCertificacion3}</td>
								<td  colspan="4">nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Institución:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.institutoCertificador3}</td>
								<td  colspan="4">nbsp;</td>
							</tr>
						</s:if>
						<s:if test="certificaciones.certificacion4 != null">
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Certificación:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificacion4}</td>
								<td  colspan="4">nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Año:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.fechaCertificacion4}</td>
								<td  colspan="4">nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Institución:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.institutoCertificador4}</td>
								<td  colspan="4">nbsp;</td>
							</tr>
						</s:if>
						<s:if test="certificaciones.certificacion5 != null">
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Certificación:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.certificacion5}</td>
								<td  colspan="4">nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Año:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.fechaCertificacion5}</td>
								<td  colspan="4">nbsp;</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Institución:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.institutoCertificador5}</td>
								<td  colspan="4">nbsp;</td>
							</tr>
						</s:if>
						
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
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo1}&nameArchivo=${pyMEs.archivo1FileName}&mimeArchivo=${pyMEs.archivo1ContentType}">${pyMEs.archivo1FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo2!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo2}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo2}&nameArchivo=${pyMEs.archivo2FileName}&mimeArchivo=${pyMEs.archivo2ContentType}">${pyMEs.archivo2FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo3!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo3}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo3}&nameArchivo=${pyMEs.archivo3FileName}&mimeArchivo=${pyMEs.archivo3ContentType}">${pyMEs.archivo3FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo4!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo4}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo4}&nameArchivo=${pyMEs.archivo4FileName}&mimeArchivo=${pyMEs.archivo4ContentType}">${pyMEs.archivo4FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo5!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo5}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo5}&nameArchivo=${pyMEs.archivo5FileName}&mimeArchivo=${pyMEs.archivo5ContentType}">${pyMEs.archivo5FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo6!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo6}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo6}&nameArchivo=${pyMEs.archivo6FileName}&mimeArchivo=${pyMEs.archivo6ContentType}">${pyMEs.archivo6FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo7!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo7}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo7}&nameArchivo=${pyMEs.archivo7FileName}&mimeArchivo=${pyMEs.archivo7ContentType}">${pyMEs.archivo7FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo8!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo8}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo8}&nameArchivo=${pyMEs.archivo8FileName}&mimeArchivo=${pyMEs.archivo8ContentType}">${pyMEs.archivo8FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo9!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo9}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo9}&nameArchivo=${pyMEs.archivo9FileName}&mimeArchivo=${pyMEs.archivo9ContentType}">${pyMEs.archivo9FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo10!=0">
				<tr>
					<td class="cuerpo1TablaResumen">${pyMEs.descArchivo10}</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo10}&nameArchivo=${pyMEs.archivo10FileName}&mimeArchivo=${pyMEs.archivo10ContentType}">${pyMEs.archivo10FileName}</a>
					</td>
				</tr>
			</s:if>
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
		<table>
		</table>
	</div>
</fieldset>
</body>
</html>
