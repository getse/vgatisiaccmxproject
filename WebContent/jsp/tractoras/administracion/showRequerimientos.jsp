<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${pageContext.request.contextPath}/css/calendario.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/tractoras.js" type="text/javascript"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar-es.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar-setup.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
	</head>
	<body>
		<fieldset id="requerimientos">
			<s:form name="frmRequerimientos" action="tractoraRequerimientoSave" namespace="/tractora/administracion" enctype="multipart/form-data" onsubmit="return validacion('2');" method="post" theme="simple">
				<s:hidden name="requerimientos.idRequerimiento" id="idIdReq" value="%{requerimientos.idRequerimiento}" />
				<s:hidden name="requerimientos.idTractora" id="idIdTra" value="%{requerimientos.idTractora}" />
				<s:hidden name="requerimientos.cveScian" id="idCveSci" value="%{requerimientos.cveScian}" />
				<s:hidden name="requerimientos.tipoProducto" id="idTipoPro" value="%{requerimientos.tipoProducto}" />
				<s:hidden name="fechaSuministro" id="idFecSum" value="%{fechaSuministro}" />
				<s:hidden name="fechaExpira" id="idFecExp"value="%{fechaExpira}" />
				<div id="sec1" ${requerimientos.producto==null? ' style="display: block;"': ' style="display: none;"' }>
					<legend><s:label value="Datos del Requerimiento" />
						<br />
						<br />
						<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
					</legend>
					<br />
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<td style="width: 250px;">
											<s:label cssClass="etiquetaCaptura" value="* Producto o servicio solicitado:" />
										</td>
										<td>
											<s:textfield id="idCampoProducto" name="requerimientos.producto" onfocus="javascript:focoAyuda('idDivPro');" onblur="javascript:blurAyuda('idDivPro');" maxlength="100" size="97" />
										</td>
									</tr>
								</table>
								<table>
									<tr>
										<td>&nbsp;</td>
										<td>
											<div id="idDivPro" style="display: none; margin-bottom: 0px; margin-top: -15px;">
												<s:label cssStyle="margin-left: 230px;" cssClass="etiquetaAyuda" value="Defina el producto solicitado en cinco palabras." />
											</div>
											<div id="idDivPro2" style="display: block; margin-bottom: 0px; margin-top: -15px;">
												<s:label cssClass="etiquetaAyuda" value="" />
											</div>
										</td>
									</tr>
								</table>
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Tipo de producto:" />
										</td>
									</tr>
									<tr>
										<td>
											<div id="idDivTipPro" style="display: none; margin-bottom: 0px; margin-top: -5px;">
												<s:label cssClass="etiquetaAyuda" value="Seleccione la categoría en la cual se encuentra su producto." />
												<br />
											</div>
											<div id="idDivTipPro2" style="display: block; margin-bottom: 0px; margin-top: -5px;">
												<s:label cssClass="etiquetaAyuda" value="" />
												<br />
											</div>
										</td>
									</tr>
								</table>
								<table>
									<tr>
										<td>
											<select id="catProd1" name="cat1" style="width: 500px;" onchange="javascript: showCombo(this.value, true, 2);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
												<option selected="selected" value="-1">--Seleccione una opción--</option>
												<s:iterator value="listCatProductos" status="stat">
													<option value="${cveScian}">${descScian}</option>
												</s:iterator>
											</select>
											<select id="catProd2" name="cat2" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, true, 3);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
												<option selected="selected" value="-1">--Seleccione una opción--</option>
												<s:iterator value="listCat2" status="stat">
													<option value="${cveScian}">${descScian}</option>
												</s:iterator>
											</select>
											<select id="catProd3" name="cat3" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, true, 4);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
												<option selected="selected" value="-1">--Seleccione una opción--</option>
												<s:iterator value="listCat3" status="stat">
													<option value="${cveScian}">${descScian}</option>
												</s:iterator>
											</select>
											<select id="catProd4" name="cat4" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, true, 5);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
												<option selected="selected" value="-1">--Seleccione una opción--</option>
												<s:iterator value="listCat4" status="stat">
													<option value="${cveScian}">${descScian}</option>
												</s:iterator>
											</select>
											<select id="catProd5" name="cat5" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, true, 6);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
												<option selected="selected" value="-1">--Seleccione una opción--</option>
												<s:iterator value="listCat5" status="stat">
													<option value="${cveScian}">${descScian}</option>
												</s:iterator>
											</select>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value=" o realice una búsqueda:" />
											<s:textfield id="idCampoBusqueda" name="requerimientos.busqueda" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');" onkeypress="javascript: if(event.which == 13 || event.keyCode == 13) busqueda(true);" value="%{requerimientos.busqueda}" />
											<a href="#">
												<label class="agregar" onclick="javascript: busqueda(true);">&raquo;buscar</label>
											</a>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:textarea id="idInputCatScian" rows="1" cols="80" disabled="true" cssClass="resultado" cssStyle="resize: none; overflow-y: hidden" name="requerimientos.tipoProducto" value="%{requerimientos.tipoProducto}" />
										</td>
									</tr>
								</table>
								<table>
									<tr>
										<td style="width: 120px;"><s:label cssClass="etiquetaCaptura" value="Descripción:" />
										</td>
										<td>
											<s:textarea id="idCampoDescripcion" name="requerimientos.descripcion" onfocus="javascript:focoAyuda('idDivDes');" onblur="javascript:blurAyuda('idDivDes');" cols="90" rows="6"></s:textarea>
											<br />
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>
											<div id="idDivDes" style="display: none; margin-bottom: 0px; margin-top: -5px;">
												<s:label cssStyle="margin-left: 2px;" cssClass="etiquetaAyuda" value="Describa el producto con mayor detalle en caso de requerirlo." />
												<br />
												<s:label cssStyle="margin-left: 2px;" cssClass="etiquetaAyuda" value="Si requiere incluir información adicional puede hacer una descripción del mismo o adjuntar archivos como complemento." />
											</div>
											<div id="idDivDes2" style="display: block; margin-bottom: 0px; margin-top: -5px;">
												<s:label cssClass="etiquetaAyuda" cssStyle="color: #FFFFFF;" value="." />
												<br />
												<s:label cssClass="etiquetaAyuda" cssStyle="color: #FFFFFF;" value="." />
											</div>
										</td>
									</tr>
								</table>
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Incluir archivo(s):" />
											<div id="idDivArchivo1Block" ${requerimientos.archivo1FileName==null? ' style="display: block;"': ' style="display: none;"'}>
												<table>
													<tr>
														<td>
															<s:file id="idCampoArchivo1" name="requerimientos.archivo1" onclick="javascript:focoAyuda('idDivFil');" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:file>
														</td>
														<td>
															<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
														</td>
														<td>
															<s:textfield id="arch1" size="30" name="requerimientos.descArchivo1" maxlength="100" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:textfield>
														</td>
													</tr>
												</table>
											</div>
											<div id="idDivArchivo1None" ${requerimientos.archivo1FileName==null? ' style="display: none;"': ' style="display: block;"'}>
												<label class="resultado">
													${requerimientos.archivo1FileName}
													${requerimientos.descArchivo1}
												</label>
											</div>
											<div id="idDivArchivo2Block" ${requerimientos.archivo2FileName==null? ' style="display: none;"': ' style="display: none;"'}>
												<table>
													<tr>
														<td>
															<s:file id="idCampoArchivo2" name="requerimientos.archivo2" onclick="javascript:focoAyuda('idDivFil');" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:file>
														</td>
														<td>
															<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
														</td>
														<td>
															<s:textfield id="arch1" size="30" name="requerimientos.descArchivo2" maxlength="100" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:textfield>
															<label class="quitar" onclick="javascript:supArchivo(2);">-eliminar archivo</label>
														</td>
													</tr>
												</table>
											</div>
											<div id="idDivArchivo2None" ${requerimientos==null || requerimientos.archivo2FileName==null? ' style="display: none;"': ' style="display: block;"'}>
												<label class="resultado">
													${requerimientos.archivo2FileName}
													${requerimientos.descArchivo2}
												</label>
											</div>
											<div id="idDivArchivo3Block" ${requerimientos.archivo3FileName==null? ' style="display: none;"': ' style="display: none;"'}>
												<table>
													<tr>
														<td>
															<s:file id="idCampoArchivo3" name="requerimientos.archivo3" onclick="javascript:focoAyuda('idDivFil');" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:file>
														</td>
														<td>
															<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
														</td>
														<td>
															<s:textfield id="arch1" size="30" name="requerimientos.descArchivo3" maxlength="100" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:textfield>
															<label class="quitar" onclick="javascript:supArchivo(3);">-eliminar archivo</label>
														</td>
													</tr>
												</table>
											</div>
											<div id="idDivArchivo3None" ${requerimientos==null || requerimientos.archivo3FileName==null? ' style="display: none;"': ' style="display: block;"'}>
												<label class="resultado">
													${requerimientos.archivo3FileName}
													${requerimientos.descArchivo3}
												</label>
											</div>
											<div id="idDivArchivo4Block" ${requerimientos.archivo4FileName==null? ' style="display: none;"': ' style="display: none;"'}>
												<table>
													<tr>
														<td>
															<s:file id="idCampoArchivo4" name="requerimientos.archivo4" onclick="javascript:focoAyuda('idDivFil');" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:file>
														</td>
														<td>
															<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
														</td>
														<td>
															<s:textfield id="arch1" size="30" name="requerimientos.descArchivo4" maxlength="100" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:textfield>
															<label class="quitar" onclick="javascript:supArchivo(4);">-eliminar archivo</label>
														</td>
													</tr>
												</table>
											</div>
											<div id="idDivArchivo4None" ${requerimientos==null || requerimientos.archivo4FileName==null? ' style="display: none;"': ' style="display: block;"'}>
												<label class="resultado">
													${requerimientos.archivo4FileName}
													${requerimientos.descArchivo4}
												</label>
											</div>
											<div id="idDivArchivo5Block" ${requerimientos.archivo5FileName==null? ' style="display: none;"': ' style="display: none;"'}>
												<table>
													<tr>
														<td>
															<s:file id="idCampoArchivo5" name="requerimientos.archivo5" onclick="javascript:focoAyuda('idDivFil');" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:file>
														</td>
														<td>
															<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
														</td>
														<td>
															<s:textfield id="arch1" size="30" name="requerimientos.descArchivo5" maxlength="100" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:textfield>
															<label class="quitar" onclick="javascript:supArchivo(5);">-eliminar archivo</label>
														</td>
													</tr>
												</table>
											</div>
											<div id="idDivArchivo5None" ${requerimientos==null || requerimientos.archivo5FileName==null? ' style="display: none;"': ' style="display: block;"'}>
												<label class="resultado">
													${requerimientos.archivo5FileName}
													${requerimientos.descArchivo5}
												</label>
											</div>
											<div id="idDivArchivo6Block" ${requerimientos.archivo6FileName==null? ' style="display: none;"': ' style="display: none;"'}>
												<table>
													<tr>
														<td>
															<s:file id="idCampoArchivo6" name="requerimientos.archivo6" onclick="javascript:focoAyuda('idDivFil');" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:file>
														</td>
														<td>
															<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
														</td>
														<td>
															<s:textfield id="arch1" size="30" name="requerimientos.descArchivo6" maxlength="100" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:textfield>
															<label class="quitar" onclick="javascript:supArchivo(6);">-eliminar archivo</label>
														</td>
													</tr>
												</table>
											</div>
											<div id="idDivArchivo6None" ${requerimientos==null || requerimientos.archivo6FileName==null? ' style="display: none;"': ' style="display: block;"'}>
												<label class="resultado">
													${requerimientos.archivo6FileName}
													${requerimientos.descArchivo6}
												</label>
											</div>
											<div id="idDivArchivo7Block" ${requerimientos.archivo7FileName==null? ' style="display: none;"': ' style="display: none;"'}>
												<table>
													<tr>
														<td>
															<s:file id="idCampoArchivo7" name="requerimientos.archivo7" onclick="javascript:focoAyuda('idDivFil');" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:file>
														</td>
														<td>
															<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
														</td>
														<td>
															<s:textfield id="arch1" size="30" name="requerimientos.descArchivo7" maxlength="100" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:textfield>
															<label class="quitar" onclick="javascript:supArchivo(7);">-eliminar archivo</label>
														</td>
													</tr>
												</table>
											</div>
											<div id="idDivArchivo7None" ${requerimientos==null || requerimientos.archivo7FileName==null? ' style="display: none;"': ' style="display: block;"'}>
												<label class="resultado">
													${requerimientos.archivo7FileName}
													${requerimientos.descArchivo7}
												</label>
											</div>
											<div id="idDivArchivo8Block" ${requerimientos.archivo8FileName==null? ' style="display: none;"': ' style="display: none;"'}>
												<table>
													<tr>
														<td>
															<s:file id="idCampoArchivo8" name="requerimientos.archivo8" onclick="javascript:focoAyuda('idDivFil');" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:file>
														</td>
														<td>
															<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
														</td>
														<td>
															<s:textfield id="arch1" size="30" name="requerimientos.descArchivo8" maxlength="100" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:textfield>
															<label class="quitar" onclick="javascript:supArchivo(8);">-eliminar archivo</label>
														</td>
													</tr>
												</table>
											</div>
											<div id="idDivArchivo8None" ${requerimientos==null || requerimientos.archivo8FileName==null? ' style="display: none;"': ' style="display: block;"'}>
												<label class="resultado">
													${requerimientos.archivo8FileName}
													${requerimientos.descArchivo8}
												</label>
											</div>
											<div id="idDivArchivo9Block" ${requerimientos.archivo9FileName==null? ' style="display: none;"': ' style="display: none;"'}>
												<table>
													<tr>
														<td>
															<s:file id="idCampoArchivo9" name="requerimientos.archivo9" onclick="javascript:focoAyuda('idDivFil');" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:file>
														</td>
														<td>
															<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
														</td>
														<td>
															<s:textfield id="arch1" size="30" name="requerimientos.descArchivo9" maxlength="100" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:textfield>
															<label class="quitar" onclick="javascript:supArchivo(9);">-eliminar archivo</label>
														</td>
													</tr>
												</table>
											</div>
											<div id="idDivArchivo9None" ${requerimientos==null || requerimientos.archivo9FileName==null? ' style="display: none;"': ' style="display: block;"'}>
												<label class="resultado">
													${requerimientos.archivo9FileName}
													${requerimientos.descArchivo9}
												</label>
											</div>
											<div id="idDivArchivo10Block" ${requerimientos.archivo10FileName==null? ' style="display: none;"': ' style="display: none;"'}>
												<table>
													<tr>
														<td>
															<s:file id="idCampoArchivo10" name="requerimientos.archivo10" onclick="javascript:focoAyuda('idDivFil');" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:file>
														</td>
														<td>
															<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
														</td>
														<td>
															<s:textfield id="arch1" size="30" name="requerimientos.descArchivo10" maxlength="100" onfocus="javascript:focoAyuda('idDivFil');" onblur="javascript:blurAyuda('idDivFil');"></s:textfield>
															<label class="quitar" onclick="javascript:supArchivo(10);">-eliminar archivo</label>
														</td>
													</tr>
												</table>
											</div>
											<div id="idDivArchivo10None" ${requerimientos==null || requerimientos.archivo10FileName==null? ' style="display: none;"': ' style="display: block;"'}>
												<label class="resultado">
													${requerimientos.archivo10FileName}
													${requerimientos.descArchivo10}
												</label>
											</div>
											<div id="idDivFil" style="display: none; margin-bottom: 0px; margin-top: -5px;">
												<s:label cssStyle="margin-left: 5px;" cssClass="etiquetaAyuda" value="Indique el o los archivos que serán incluidos. Máximo 2MB (.pdf .doc .png)" />
												<br />
											</div>
											<div id="idDivFil2" style="display: block; margin-bottom: 0px; margin-top: -5px;">
												<s:label cssClass="etiquetaAyuda" value="" />
												<br />
											</div>
											<label class="agregar" onclick="javascript:otroArchivo(); javascript:focoAyuda('idDivFil');">+agregar otro</label>
											<br />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table class="submit_tabla">
						<tr>
							<td style="width: 250px;"></td>
							<td>
								<input class="botonenviar" value="Cancelar" type="button" onclick="cancela();" />
							</td>
							<td>
								<input class="botonenviar" value="Siguiente" type="button" onclick="javascript:return validacion('1');" />
							</td>
							<td style="width: 250px;"></td>
						</tr>
					</table>
				</div>
				<div id="sec2" style="display: none;">
					<legend><s:label value="Datos del Requerimiento" />
						<br />
						<br />
						<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
					</legend>
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<td style="width: 185px;">
											<s:label cssClass="etiquetaCaptura" value="* Lugar de suministro:" />
										</td>
										<td>
											<select id="idCampoLugarSuministro" name="lugares" onfocus="javascript:focoAyuda('idDivLug');" onblur="javascript:blurAyuda('idDivLug');">
												<option selected="selected" style="width: 200px;" value="Nacional">Nacional</option>
												<option value="Aguascalientes">Aguascalientes</option>
												<option value="Baja California Norte">Baja California Norte</option>
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
												<option value="Estado de Mexico">Estado de México</option>
												<option value="Michoacan">Michoacán</option>
												<option value="Morelos">Morelos</option>
												<option value="Nayarit">Nayarit</option>
												<option value="Nuevo Leon">Nuevo León</option>
												<option value="Oaxaca">Oaxaca</option>
												<option value="Puebla">Puebla</option>
												<option value="Queretaro">Querétaro</option>
												<option value="Quintana Roo">Quintana Roo</option>
												<option value="San Luis Potosi">San Luís Potosí</option>
												<option value="Sinaloa">Sinaloa</option>
												<option value="Sonora">Sonora</option>
												<option value="Tabasco">Tabasco</option>
												<option value="Tamaulipas">Tamaulipas</option>
												<option value="Tlaxcala">Tlaxcala</option>
												<option value="Veracruz">Veracruz</option>
												<option value="Yucatan">Yucatán</option>
												<option value="Zacatecas">Zacatecas</option>
											</select>&nbsp;&nbsp;
										</td>
										<td style="width: 155px;">
											<s:label cssClass="etiquetaCaptura" value="   Descripción opcional:" />
										</td>
										<td>
											<s:textfield id="idDesEdo" name="descLugar" onfocus="javascript:focoAyuda('idDivLug');" onblur="javascript:blurAyuda('idDivLug');" maxlength="250" size="32" />
											<label class="agregar" onblur="blurAyuda('idDivLug');" onclick="agregaEstado(); focoAyuda('idDivLug');">+agregarlo</label>
										</td>
									</tr>
								</table>
								<table>
									<tr>
										<td>
											<s:iterator status="stat" value="(10).{ #this }">
												<div id="idDivEdo${stat.count}" ${!(requerimientos.lugarSuministro[stat.index]==null)? ' style="display: block;"': ' style="display: none;"'}>
													<s:hidden id="idEdoHid%{#stat.count}" name="requerimientos.lugarSuministro[%{#stat.index}].estadoVenta" value="%{requerimientos.lugarSuministro[#stat.index].estadoVenta}" />
													<s:hidden id="idEdoDesHid%{#stat.count}" name="requerimientos.lugarSuministro[%{#stat.index}].descripcion" value="%{requerimientos.lugarSuministro[#stat.index].descripcion}" />
													<s:label id="labEdo%{#stat.count}" cssClass="etiquetaCaptura" value="%{requerimientos.lugarSuministro[#stat.index].estadoVenta}%{requerimientos.lugarSuministro[#stat.index].descripcion==null?'':', '.concat(requerimientos.lugarSuministro[#stat.index].descripcion)}" />
													<label class="quitar" onclick="quitarEstado(${stat.count}); focoAyuda('idDivLug');">-quitar</label>
												</div>
											</s:iterator>
											<div id="idDivLug" style="display: none; margin-bottom: 0px; margin-top: 0px;">
												<s:label cssClass="etiquetaAyuda" value="Seleccione un lugar de suministro agregando un estado a la vez con la opción '+agregarlo', puede incluir una descripción adicional." />
											</div>
											<div id="idDivLug2" style="display: block; margin-bottom: 0px; margin-top: 0px;">
												<s:label cssClass="etiquetaAyuda" cssStyle="color: #FFFFFF;" value="." />
											</div>
											<br />
										</td>
									</tr>
								</table>
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Condiciones de pago:" />
											<s:checkbox id="checkcontado" name="requerimientos.bContado" onfocus="javascript:focoAyuda('idDivConPag');" onblur="javascript:blurAyuda('idDivConPag');" onclick="contado();javascript:blurAyuda('idDivLug');javascript:focoAyuda('idDivConPag');" value="%{requerimientos.bContado}" />
											<s:label cssClass="etiquetaCaptura" value="Contado" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<s:checkbox id="checkcredito" name="requerimientos.bCredito" onfocus="javascript:focoAyuda('idDivConPag');" onblur="javascript:blurAyuda('idDivConPag');" onclick="credito();javascript:blurAyuda('idDivLug');javascript:focoAyuda('idDivConPag');" value="%{requerimientos.bCredito}" />
											<s:label cssClass="etiquetaCaptura" value="Crédito" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<div id="plazo" ${requerimientos.bCredito==true? ' style="display: block;"': ' style="display: none;"' }>
												<s:checkbox id="checkquince" name="requerimientos.bQuince" onfocus="javascript:focoAyuda('idDivConPag');" onblur="javascript:blurAyuda('idDivConPag');" onclick="limpiaCheckCredito(15);" value="%{requerimientos.bQuince}" />
												<s:label cssClass="etiquetaCaptura" value="15 días" />&nbsp;&nbsp;&nbsp;
												<s:checkbox id="checktreinta" name="requerimientos.bTreinta" onfocus="javascript:focoAyuda('idDivConPag');" onblur="javascript:blurAyuda('idDivConPag');" onclick="limpiaCheckCredito(30);" value="%{requerimientos.bTreinta}" />
												<s:label cssClass="etiquetaCaptura" value="30 días" />&nbsp;&nbsp;&nbsp;
												<s:checkbox id="checksesenta" name="requerimientos.bSesenta" onfocus="javascript:focoAyuda('idDivConPag');" onblur="javascript:blurAyuda('idDivConPag');" onclick="limpiaCheckCredito(60);" value="%{requerimientos.bSesenta}" />
												<s:label cssClass="etiquetaCaptura" value="60 días" />&nbsp;&nbsp;&nbsp;
												<s:checkbox id="checknoventa" name="requerimientos.bNoventa" onfocus="javascript:focoAyuda('idDivConPag');" onblur="javascript:blurAyuda('idDivConPag');" onclick="limpiaCheckCredito(90);" value="%{requerimientos.bNoventa}" />
												<s:label cssClass="etiquetaCaptura" value="90 días" />&nbsp;&nbsp;&nbsp;
											</div>
											<div id="idDivConPag" style="display: none; margin-bottom: 5px; margin-top: 0px;">
												<s:label cssClass="etiquetaAyuda" value="Seleccione una opción." />
											</div>
											<div id="idDivConPag2" style="display: block; margin-bottom: 5px; margin-top: 15px;">
												<s:label cssClass="etiquetaAyuda" style="margin-left: 20px;" value="" />
											</div>
											<br />
											<div id="otrasCondicionesPago" style="display: block;">
												<table>
													<tr>
														<td style="width: 190px;"><s:label cssClass="etiquetaCaptura" value="Otras condiciones de pago:" />
														</td>
														<td>
															<s:textarea cols="70" id="idCampoOtrasCondiciones" name="requerimientos.otrasCondiciones" onfocus="javascript:focoAyuda('idDivConPag');javascript:focoAyuda('idDivOtrCon');" onblur="javascript:blurAyuda('idDivOtrCon');" rows="2"></s:textarea>
															<br />
															<div id="idDivOtrCon" style="display: none; margin-bottom: 0px; margin-top: 0px;">
																<s:label cssClass="etiquetaAyuda" value="Especifique si existen otras condiciones de pago." />
															</div>
															<div id="idDivOtrCon2" style="display: block; margin-bottom: 0px; margin-top: 15px;">
																<s:label cssClass="etiquetaAyuda" value="" />
															</div>
														</td>
													</tr>
												</table>
												<br />
											</div>
											<br />
										</td>
									</tr>
								</table>
								<table>
									<tr>
										<td style="width: 190px; vertical-align: top;">
											<s:label cssClass="etiquetaCaptura" value="Requisitos adicionales:" />
										</td>
										<td>
											<s:textarea cols="70" id="idCampoRequisitosAdicionales" name="requerimientos.requisitosAdicionales" onfocus="javascript:focoAyuda('idDivReqAdi');" onblur="javascript:blurAyuda('idDivReqAdi');" rows="2"></s:textarea>
											<br />
											<div id="idDivReqAdi" style="display: none; margin-bottom: 0px; margin-top: 0px;">
												<s:label cssClass="etiquetaAyuda" value="Describa los requisitos adicionales; tales como certificaciones, criterios de calidad, condiciones de entrega." />
											</div>
											<div id="idDivReqAdi2" style="display: block; margin-bottom: 0px; margin-top: 0px;">
												<s:label cssClass="etiquetaAyuda" cssStyle="color: #FFFFFF;" value="." />
											</div>
										</td>
									</tr>
								</table>
								<table>
									<tr>
										<td style="width: 450px">
											<br />
											<s:label cssClass="etiquetaCaptura" value="* Fecha de suministro:" />
											<s:date name="requerimientos.fechaSuministro" id="fSuministro" format="dd/MM/yyyy" />
											<s:textfield class="calendario" id="ingreso" name="requerimientos.fechaSuministro" value="%{fSuministro}" onfocus="javascript:focoAyuda('idDivFecSum');" onblur="javascript:blurAyuda('idDivFecSum');" onchange="limpiaCheckSuministro();" size="10" maxlength="10" />
											<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador" style="cursor: hand"></img>
											<br />
											<s:checkbox id="indefinido" name="requerimientos.bIndefinido" onfocus="javascript:focoAyuda('idDivFecSum');" onblur="javascript:blurAyuda('idDivFecSum');" onchange="limpiaFechaExpira(1);" value="%{requerimientos.bIndefinido}" />
											<s:label cssClass="etiquetaCaptura" value="Indefinido" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<s:checkbox id="variasfechas" name="requerimientos.bVariasFechas" onfocus="javascript:focoAyuda('idDivFecSum');" onblur="javascript:blurAyuda('idDivFecSum');" onchange="limpiaFechaExpira(2);" value="%{requerimientos.bVariasFechas}" />
											<s:label cssClass="etiquetaCaptura" value="Varias fechas" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<s:checkbox id="suministrocontinuo" name="requerimientos.bContinuoSuministro" onfocus="javascript:focoAyuda('idDivFecSum');" onblur="javascript:blurAyuda('idDivFecSum');" onchange="limpiaFechaExpira(3);" value="%{requerimientos.bContinuoSuministro}" />
											<s:label cssClass="etiquetaCaptura" value="Continuo" />
											<br />
											<div id="idDivDetalleVariasFechas" ${requerimientos.bVariasFechas==true? ' style="display: block; margin-bottom: 0px; margin-top: 0px;"': ' style="display: none; margin-bottom: 0px; margin-top: 0px;"' }>
												<s:textfield size="25" id="idDetalleVariasFechas" name="requerimientos.variasFechas" onfocus="javascript:focoAyuda('idDivFecSum');" onblur="javascript:blurAyuda('idDivFecSum');" maxlength="100"></s:textfield>
											</div>
											<div id="idDivFecSum" style="display: none; margin-bottom: 0px; margin-top: 0px;">
												<s:label cssClass="etiquetaAyuda" value="Indique la fecha de suministro o seleccione una opción." />
											</div>
											<div id="idDivFecSum2" style="display: block; margin-bottom: 0px; margin-top: 15px;">
												<s:label cssClass="etiquetaAyuda" style="margin-left: 25px;" value="" />
											</div>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Fecha en la que expira el requerimiento:" />
											<s:date name="requerimientos.fechaExpira" id="fExpira" format="dd/MM/yyyy" />
											<s:textfield class="calendario" id="ingreso2" name="requerimientos.fechaExpira" value="%{fExpira}" onfocus="javascript:focoAyuda('idDivFecExp');" onblur="javascript:blurAyuda('idDivFecExp');" onchange="limpiaCheckExpira();" size="10" maxlength="10" />
											<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador2" style="cursor: hand"></img>
											<br />
											<s:checkbox id="expiracontinuo" name="requerimientos.bContinuoExpira" onclick="javascript:focoAyuda('idDivFecExp');" onfocus="javascript:focoAyuda('idDivFecExp');" onblur="javascript:blurAyuda('idDivFecExp');" onchange="limpiaFechaSuministro();" value="%{requerimientos.bContinuoExpira}" />
											<s:label cssClass="etiquetaCaptura" value="Continuo o no tiene expiración" />
											<br />
											<div id="idDivFecExp" style="display: none; margin-bottom: 0px; margin-top: -5px;">
												<s:label cssClass="etiquetaAyuda" value="Indique la fecha en que expira el requerimiento o si el requerimiento es continuo." />
											</div>
											<div id="idDivFecExp2" style="display: block; margin-bottom: 0px; margin-top: -5px;">
												<s:label cssClass="etiquetaAyuda" cssStyle="font-color: #FFFFFF;margin-left: 20px;" value="." />
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table class="submit_tabla">
						<tr>
							<td style="width: 250px;"></td>
							<td>
								<input class="botonenviar" value="Anterior" type="button" onclick="javascript:document.getElementById('sec2').style.display='none'; javascript:document.getElementById('sec1').style.display='block';" />
							</td>
							<td>
								<s:submit cssClass="botonenviar" value="Guardar" />
							</td>
							<td style="width: 250px;"></td>
						</tr>
					</table>
				</div>
			</s:form>
			<div id="secR" ${requerimientos.producto==null? ' style="display: none;"': ' style="display: block;"' }>
				<legend><s:label value="Expediente del Requerimiento" />
					<br />
				</legend>
				<br />
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
				<table class="expediente_tabla">
					<tr>
						<td class="encabezadoTablaResumen" colspan="2" align="center">Resumen, revise que su infomación es correcta</td>
					</tr>
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Producto solicitado:</td>
						<td class="cuerpo1TextoResumen">
							<s:label cssClass="etiquetaResumen">${requerimientos.producto}</s:label>
						</td>
					</tr>
					<tr>
						<td class="cuerpo2TablaResumen" align="left">&nbsp;Tipo de producto:</td>
						<td class="cuerpo1TextoResumen">
							<s:label cssClass="etiquetaResumen">${requerimientos.tipoProducto}</s:label>
						</td>
					</tr>
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Descripción del producto:</td>
						<td class="cuerpo1TextoResumen">
							<s:label cssClass="etiquetaResumen">${requerimientos.descripcion}</s:label>
						</td>
					</tr>
					<s:if test="requerimientos.idArchivo1!=0">
						<tr>
							<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo anexo 1:</td>
							<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo1}&nameArchivo=${requerimientos.archivo1FileName}&mimeArchivo=${requerimientos.archivo1ContentType}">${requerimientos.archivo1FileName}</a>
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
							<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo2}&nameArchivo=${requerimientos.archivo2FileName}&mimeArchivo=${requerimientos.archivo2ContentType}">${requerimientos.archivo2FileName}</a>
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
							<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo3}&nameArchivo=${requerimientos.archivo3FileName}&mimeArchivo=${requerimientos.archivo3ContentType}">${requerimientos.archivo3FileName}</a>
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
							<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo4}&nameArchivo=${requerimientos.archivo4FileName}&mimeArchivo=${requerimientos.archivo4ContentType}">${requerimientos.archivo4FileName}</a>
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
							<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo5}&nameArchivo=${requerimientos.archivo5FileName}&mimeArchivo=${requerimientos.archivo5ContentType}">${requerimientos.archivo5FileName}</a>
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
							<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo6}&nameArchivo=${requerimientos.archivo6FileName}&mimeArchivo=${requerimientos.archivo6ContentType}">${requerimientos.archivo6FileName}</a>
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
							<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo7}&nameArchivo=${requerimientos.archivo7FileName}&mimeArchivo=${requerimientos.archivo7ContentType}">${requerimientos.archivo7FileName}</a>
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
							<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo8}&nameArchivo=${requerimientos.archivo8FileName}&mimeArchivo=${requerimientos.archivo8ContentType}">${requerimientos.archivo8FileName}</a>
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
							<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo9}&nameArchivo=${requerimientos.archivo9FileName}&mimeArchivo=${requerimientos.archivo9ContentType}">${requerimientos.archivo9FileName}</a>
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
							<td class="cuerpo1TextoResumen"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo10}&nameArchivo=${requerimientos.archivo10FileName}&mimeArchivo=${requerimientos.archivo10ContentType}">${requerimientos.archivo10FileName}</a>
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
								<s:else>${requerimientos.bIndefinido==true?'Indefinido':requerimientos.bVariasFechas==true?'Varias Fechas':requerimientos.bContinuoSuministro==true?'Continuo':''}
									<s:if test="%{requerimientos.bVariasFechas}">
										<tr>
											<td class="cuerpo2TablaResumen" align="left">&nbsp;Detalle fechas suministro:</td>
											<td class="cuerpo1TextoResumen">
												<s:label cssClass="etiquetaResumen">${requerimientos.variasFechas}</s:label>
											</td>
										</tr>
									</s:if>
								</s:else>
							</s:label>
						</td>
					</tr>
					<s:iterator value="requerimientos.lugarSuministro" status="stat">
						<tr>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="left">&nbsp;Lugar de suministro ${stat.count}:</td>
							<td class="cuerpo1TextoResumen">
								<s:label cssClass="etiquetaResumen">${estadoVenta} ${descripcion==null?'':', '.concat(descripcion)}</s:label>
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Condiciones de pago:</td>
						<td class="cuerpo1TextoResumen">
							<s:label cssClass="etiquetaResumen">${requerimientos.bContado?'Contado':'Crédito'}${requerimientos.bQuince?', con plazo a 15 días':requerimientos.bTreinta?', con plazo a 30 días':requerimientos.bSesenta?', con plazo a 60 días':requerimientos.bNoventa?', con plazo a 90 días':''}${requerimientos.otrasCondiciones==''?'':', '}${requerimientos.otrasCondiciones}</s:label>
						</td>
					</tr>
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Requisitos adicionales:</td>
						<td class="cuerpo1TextoResumen">
							<s:label cssClass="etiquetaResumen">${requerimientos.requisitosAdicionales}</s:label>
						</td>
					</tr>
					<tr>
						<td class="cuerpo2TablaResumen" align="left">&nbsp;Fecha en la que expira el requerimiento:</td>
						<td class="cuerpo1TextoResumen">
							<s:label cssClass="etiquetaResumen">
								<s:if test="%{requerimientos.fechaExpira!=null}">
									<s:date name="requerimientos.fechaExpira" format="dd/MM/yyyy" />
								</s:if>
								<s:else>${requerimientos.bContinuoExpira==true?'Continuo':''}</s:else>
							</s:label>
						</td>
					</tr>
				</table>
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td>
							<input class="botonenviar" value="Modificar" type="button" onclick="javascript:modificar();" />
						</td>
						<td>
							<input class="botonenviar" value="Regresar" type="button" onclick="cancela();" />
						</td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
				<s:set var="vlistRespuestas" value="listRespuestas" />
				<div ${vlistRespuestas[0]!=null?'style="display: block;"':'style="display: none;"'}>
					<legend><s:label value="Respuestas al Requerimiento" />
						<br />
						<br />
						<s:label cssClass="camposObligatorios" value="Seleccione la opción 'Respuesta' para consultar la respuesta al requerimiento, seleccione 'Expediente' para consultar la PyME." />
					</legend>
					<br />
					<table>
						<tr>
							<td>
								<table width="99%" cellspacing="1" cellpadding="1">
									<thead>
										<tr>
											<td class="encabezado_tabla" align="center"><b>No.</b></td>
											<td class="encabezado_tabla" align="center"><b>ID Requerimiento</b></td>
											<td class="encabezado_tabla" align="center"><b>Información</b></td>
											<td class="encabezado_tabla" align="center"><b>Ver Respuesta</b></td>
											<td class="encabezado_tabla" align="center"><b>PyME</b></td>
											<td class="encabezado_tabla" align="center"><b>Ver Expediente</b></td>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="#vlistRespuestas" status="stat">
											<tr>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center">${stat.count}</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
												align="center">${idRequerimiento}</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
												align="center">${informacion}</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
													align="center"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraRequerimientoRespuesta.do?idRespuesta=${idRespuesta}" onclick="javascript: $(idProcesa)[0].style.display = 'block';">Respuesta</a></td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
												align="center">${nombrePyME}</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
														align="center"><a href="${pageContext.request.contextPath}/tractora/administracion/tractoraRequerimientoPyME.do?idUsuario=${idPyME}" onclick="javascript: $(idProcesa)[0].style.display = 'block';">Expediente</a></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<br />
			</div>
			<s:form name="frmCancela" action="tractoraRequerimientosShow" namespace="/tractora/administracion" theme="simple" method="post"></s:form>
		</fieldset>
		<s:if test="%{requerimientos==null}">
			<script type="text/javascript">
				calendario();
			</script>
		</s:if>
		<div class="overlay-container">
			<div class="window-container zoomin">
				<fieldset id="requerimientos">
					<legend>
						<s:label id="idBusResTit" value="" />
					</legend>
				</fieldset>
				<s:if test="%{true}">
					<div id="idDivResultados" style="overflow: auto; overflow-x: hidden;"></div>
				</s:if>
			</div>
			<div class="window-container zoomout">
				<h3>T2</h3>
				Texto de la ventana emergente
				<br />
				<br />
				<span class="close">Cerrar</span>
			</div>
		</div>
		<script>
			!window.jQuery && document.write(unescape('%3Cscript src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"%3E%3C/script%3E'))
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/demo.js">
		</script>
		<s:if test="%{true}">
			<input type="button" id="idBtnBuscar" value="" class="button" style="position: absolute; margin-top: -500px; display: none;" data-type="zoomin" />
		</s:if>
	</body>
</html>