<?xml version="1.0" encoding="UTF-8"?>
<%@taglib
	uri="/struts-tags"
	prefix="s"%>
<%@page
	pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html
	xmlns="http://www.w3.org/1999/xhtml"
	xml:lang="es">
<head>
<meta
	http-equiv="Content-Type"
	content="text/html; charset=UTF-8" />
<link
	href="${pageContext.request.contextPath}/css/calendario.css"
	rel="stylesheet"
	type="text/css" />
<script
	src="${pageContext.request.contextPath}/js/tractoras.js"
	type="text/javascript"></script>
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
	<div
		id="scian"
		style="display: none;">
		<s:select
			list="listCatProductos"
			listValue="descScian"
			listKey="cveScian"
			cssStyle="width: 0px;"
			id="idCatScianCcmx"></s:select>
	</div>
	<fieldset id="requerimientos">
		<legend>
			<s:label value="Captura del Requerimiento" />
			<br /> <br />
			<s:label
				cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<br />
		<s:form
			name="frmRequerimientos"
			action="tractoraRequerimientoSave"
			namespace="/tractora/administracion"
			enctype="multipart/form-data"
			onsubmit="return validacion('2');"
			method="post"
			theme="simple">
			<s:hidden
				name="requerimientos.idRequerimiento"
				id="idIdReq"
				value="%{requerimientos.idRequerimiento}" />
			<s:hidden
				name="requerimientos.idTractora"
				id="idIdTra"
				value="%{requerimientos.idTractora}" />
			<s:hidden
				name="requerimientos.cveScian"
				id="idCveSci"
				value="%{requerimientos.cveScian}" />
			<s:hidden
				name="requerimientos.tipoProducto"
				id="idTipoPro"
				value="%{requerimientos.tipoProducto}" />
			<s:hidden
				name="fechaSuministro"
				id="idFecSum"
				value="%{fechaSuministro}" />
			<s:hidden
				name="fechaExpira"
				id="idFecExp"
				value="%{fechaExpira}" />
			<div
				id="sec1"
				${requerimientos.producto==null?
		' style="display: block;"
		':' style="display: none;"' }>
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td style="width: 250px;"><s:label
											cssClass="etiquetaCaptura"
											value="* Producto o servicio solicitado:" /></td>
									<td><s:textfield
											id="idCampoProducto"
											name="requerimientos.producto"
											onfocus="javascript:focoAyuda('idDivPro');"
											onblur="javascript:blurAyuda('idDivPro');"
											maxlength="100"
											size="97" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td>&nbsp;</td>
									<td>
										<div
											id="idDivPro"
											style="display: none; margin-bottom: 0px; margin-top: -15px;">
											<s:label
												cssClass="etiquetaAyuda"
												value="Defina el producto solicitado en cinco palabras." />
										</div>
										<div
											id="idDivPro2"
											style="display: block; margin-bottom: 0px; margin-top: -15px;">
											<s:label
												cssClass="etiquetaAyuda"
												value="" />
										</div>
									</td>
								</tr>
								<tr>
									<td style="width: 180px;"><s:label
											cssClass="etiquetaCaptura"
											value="* Tipo de producto:" /></td>
									<td><select
										id="idCatCcmx1"
										name="catccmx1"
										style="width: 500px;"
										onchange="javascript:fillCombo2(this.value);"
										onfocus="javascript:focoAyuda('idDivTipPro');"
										onblur="javascript:blurAyuda('idDivTipPro');">
											<option
												selected="selected"
												value="-1">--Seleccione una opción--</option>
									</select><br /> <select
										id="idCatCcmx2"
										name="catccmx2"
										style="width: 500px;"
										onclick="javascript:fillCombo3(this.value);"
										onkeypress="javascript:fillCombo3(this.value);"
										onchange="javascript:fillCombo3(this.value);"
										onfocus="javascript:focoAyuda('idDivTipPro');"
										onblur="javascript:blurAyuda('idDivTipPro');">
											<option
												selected="selected"
												value="-1">--Seleccione una opción--</option>
									</select><br /> <select
										id="idCatCcmx3"
										name="catccmx3"
										style="width: 500px;"
										onclick="javascript:fillDescripcionScian(this.value);"
										onkeypress="javascript:fillDescripcionScian(this.value);"
										onchange="javascript:fillDescripcionScian(this.value);"
										onfocus="javascript:focoAyuda('idDivTipPro');"
										onblur="javascript:blurAyuda('idDivTipPro');">
											<option
												selected="selected"
												value="-1">--Seleccione una opción--</option>
									</select></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td><s:textarea
											id="idInputCatScian"
											rows="1"
											cols="80"
											disabled="true"
											cssClass="resultado"
											name="requerimientos.tipoProducto"
											value="%{requerimientos.tipoProducto}" /> <br />
										<div
											id="idDivTipPro"
											style="display: none; margin-bottom: 0px; margin-top: -10px;">
											<s:label
												cssClass="etiquetaAyuda"
												value="Seleccione la categoría en la cual se encuentra su producto." />
											<br />
											<s:label
												cssClass="etiquetaAyuda"
												value="Si requiere incluir información adicional puede hacer una " />
											<br />
											<s:label
												cssClass="etiquetaAyuda"
												value="descripción del mismo o adjuntar archivos como complemento." />
										</div>
										<div
											id="idDivTipPro2"
											style="display: block; margin-bottom: 0px; margin-top: -5px;">
											<s:label
												cssClass="etiquetaAyuda"
												value="" />
											<br />
											<s:label
												cssClass="etiquetaAyuda"
												value="" />
											<br />
											<s:label
												cssClass="etiquetaAyuda"
												value="" />
										</div></td>
								</tr>
							</table>
							<table>
								<tr>
									<td style="width: 120px;"><s:label
											cssClass="etiquetaCaptura"
											value="Descripción:" /></td>
									<td><s:textarea
											id="idCampoDescripcion"
											name="requerimientos.descripcion"
											onfocus="javascript:focoAyuda('idDivDes');"
											onblur="javascript:blurAyuda('idDivDes');"
											cols="90"
											rows="6"></s:textarea></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>
										<div
											id="idDivDes"
											style="display: none; margin-bottom: 0px; margin-top: -15px;">
											<s:label
												cssClass="etiquetaAyuda"
												value="Describa el producto con mayor detalle en caso de requerirlo." />
										</div>
										<div
											id="idDivDes2"
											style="display: block; margin-bottom: 0px; margin-top: -15px;">
											<s:label
												cssClass="etiquetaAyuda"
												value="" />
										</div>
									</td>
								</tr>
							</table> <s:label
								cssClass="etiquetaCaptura"
								value="Incluir archivo(s):" /> <label
							class="agregar"
							onclick="javascript:agregarArchivo();">+agregar otro</label> <br /> <s:iterator
								status="stat"
								value="(10).{ #this }">
								<div
									id="idDivArc${stat.count}"
									${!(stat.index!=0&&requerimientos.archivos[stat.index]==null)?' style="display: block;"':' style="display: none;"'}>
									<s:hidden
										id="idArcHid%{#stat.count}"
										name="requerimientos.archivosFileName[%{#stat.index}]"
										value="%{requerimientos.archivosFileName[#stat.index]}" />
									<s:file
										id="filArc%{#stat.count}"
										name="requerimientos.archivos[%{#stat.index}]"
										onfocus="javascript:focoAyuda('idDivFil');"
										onblur="javascript:blurAyuda('idDivFil');">
									</s:file>
									<s:label
										id="labArc%{#stat.count}"
										cssClass="etiquetaCaptura"
										value="%{requerimientos.archivosFileName[#stat.index]}" />
									<label
										class="quitar"
										onclick="quitarArchivo(${stat.count});">${stat.count==1 ? '' : '-quitar'}</label>
								</div>
							</s:iterator>
							<div
								id="idDivFil"
								style="display: none; margin-bottom: 0px; margin-top: -5px;">
								<s:label
									cssClass="etiquetaAyuda"
									value="Indique el o los archivos que serán incluidos. Máximo 2MB (.pdf .doc .png)" />
								<br />
							</div>
							<div
								id="idDivFil2"
								style="display: block; margin-bottom: 0px; margin-top: -5px;">
								<s:label
									cssClass="etiquetaAyuda"
									value="" />
								<br />
							</div> <br />
						</td>
					</tr>
				</table>
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td><input
							class="botonenviar"
							value="Cancelar"
							type="button"
							onclick="cancela();" /></td>
						<td><input
							class="botonenviar"
							value="Siguiente"
							type="button"
							onclick="javascript:return validacion('1');" /></td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
			<div
				id="sec2"
				style="display: none;">
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td style="width: 185px;"><s:label
											cssClass="etiquetaCaptura"
											value="* Lugar de suministro:" /></td>
									<td><select
										id="idCampoLugarSuministro"
										name="lugares"
										onfocus="javascript:focoAyuda('idDivLug');"
										onblur="javascript:blurAyuda('idDivLug');">
											<option
												selected="selected"
												style="width: 200px;"
												value="Nacional">Nacional</option>
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
									</select>&nbsp;&nbsp;<label
										class="agregar"
										onblur="javascript:blurAyuda('idDivLug');"
										onclick="lugarSuministro();javascript:focoAyuda('idDivLug');">+agregar</label></td>
								</tr>
							</table>
							<table>
								<tr>
									<td>
										<div
											id="lugarSum"
											${requerimientos.lugarSuministro==null?
						' style="display: none;"
						':' style="display: block;"' }>
											<s:textfield
												size="45"
												id="idInput"
												cssClass="resultado"
												name="requerimientos.lugarSuministro"
												value="%{requerimientos.lugarSuministro}" />
											<label
												class="quitar"
												onblur="javascript:blurAyuda('idDivLug');"
												onclick="javascript:focoAyuda('idDivLug');javascript:document.getElementById('idInput').value=''; document.getElementById('lugarSum').style.display='none'">-limpiar</label>
										</div>
										<div
											id="idDivLug"
											style="display: none; margin-bottom: 0px; margin-top: 0px;">
											<s:label
												cssClass="etiquetaAyuda"
												value="Seleccione el o los lugares de suministro." />
										</div>
										<div
											id="idDivLug2"
											style="display: block; margin-bottom: 0px; margin-top: 0px;">
											<s:label
												cssClass="etiquetaAyuda"
												cssStyle="color: #FFFFFF;"
												value="." />
										</div> <br />
									</td>
								</tr>
							</table>
							<table>
								<tr>
									<td><s:label
											cssClass="etiquetaCaptura"
											value="Condiciones de pago:" /> <s:checkbox
											id="checkcontado"
											name="requerimientos.bContado"
											onfocus="javascript:focoAyuda('idDivConPag');"
											onblur="javascript:blurAyuda('idDivConPag');"
											onclick="contado();javascript:blurAyuda('idDivLug');javascript:focoAyuda('idDivConPag');"
											value="%{requerimientos.bContado}" /> <s:label
											cssClass="etiquetaCaptura"
											value="Contado" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:checkbox
											id="checkcredito"
											name="requerimientos.bCredito"
											onfocus="javascript:focoAyuda('idDivConPag');"
											onblur="javascript:blurAyuda('idDivConPag');"
											onclick="credito();javascript:blurAyuda('idDivLug');javascript:focoAyuda('idDivConPag');"
											value="%{requerimientos.bCredito}" /> <s:label
											cssClass="etiquetaCaptura"
											value="Crédito" />
										<div
											id="plazo"
											${requerimientos.bCredito==true?
						' style="display: block;"
						':' style="display: none;"' }>
											<s:checkbox
												id="checkquince"
												name="requerimientos.bQuince"
												onfocus="javascript:focoAyuda('idDivConPag');"
												onblur="javascript:blurAyuda('idDivConPag');"
												onclick="limpiaCheckCredito(15);"
												value="%{requerimientos.bQuince}" />
											<s:label
												cssClass="etiquetaCaptura"
												value="15 días" />
											&nbsp;&nbsp;&nbsp;
											<s:checkbox
												id="checktreinta"
												name="requerimientos.bTreinta"
												onfocus="javascript:focoAyuda('idDivConPag');"
												onblur="javascript:blurAyuda('idDivConPag');"
												onclick="limpiaCheckCredito(30);"
												value="%{requerimientos.bTreinta}" />
											<s:label
												cssClass="etiquetaCaptura"
												value="30 días" />
											&nbsp;&nbsp;&nbsp;
											<s:checkbox
												id="checksesenta"
												name="requerimientos.bSesenta"
												onfocus="javascript:focoAyuda('idDivConPag');"
												onblur="javascript:blurAyuda('idDivConPag');"
												onclick="limpiaCheckCredito(60);"
												value="%{requerimientos.bSesenta}" />
											<s:label
												cssClass="etiquetaCaptura"
												value="60 días" />
											&nbsp;&nbsp;&nbsp;
											<s:checkbox
												id="checknoventa"
												name="requerimientos.bNoventa"
												onfocus="javascript:focoAyuda('idDivConPag');"
												onblur="javascript:blurAyuda('idDivConPag');"
												onclick="limpiaCheckCredito(90);"
												value="%{requerimientos.bNoventa}" />
											<s:label
												cssClass="etiquetaCaptura"
												value="90 días" />
											&nbsp;&nbsp;&nbsp;
											<s:checkbox
												id="checkotro"
												name="requerimientos.bOtro"
												onfocus="javascript:focoAyuda('idDivConPag');"
												onblur="javascript:blurAyuda('idDivConPag');"
												onclick="otro();"
												value="%{requerimientos.bOtro}" />
											<s:label
												cssClass="etiquetaCaptura"
												value="otro" />
											&nbsp;&nbsp;&nbsp;
										</div>
										<div
											id="idDivConPag"
											style="display: none; margin-bottom: 5px; margin-top: 0px;">
											<s:label
												cssClass="etiquetaAyuda"
												value="Seleccione una opción." />
										</div>
										<div
											id="idDivConPag2"
											style="display: block; margin-bottom: 5px; margin-top: 15px;">
											<s:label
												cssClass="etiquetaAyuda"
												style="margin-left: 20px;"
												value="" />
										</div></td>
								</tr>
							</table>
							<div
								id="otrasCondicionesPago"
								${requerimientos.bOtro==true?
				' style="display: block;"
				':' style="display: none;"' }>
								<table>
									<tr>
										<td style="width: 180px;"><s:label
												cssClass="etiquetaCaptura"
												value="Otras condiciones:" /></td>
										<td><s:textarea
												cols="70"
												id="idCampoOtrasCondiciones"
												name="requerimientos.otrasCondiciones"
												onfocus="javascript:focoAyuda('idDivConPag');javascript:focoAyuda('idDivOtrCon');"
												onblur="javascript:blurAyuda('idDivOtrCon');"
												rows="2"></s:textarea><br />
											<div
												id="idDivOtrCon"
												style="display: none; margin-bottom: 0px; margin-top: 0px;">
												<s:label
													cssClass="etiquetaAyuda"
													value="Especifique si existen otras condiciones de pago." />
											</div>
											<div
												id="idDivOtrCon2"
												style="display: block; margin-bottom: 0px; margin-top: 15px;">
												<s:label
													cssClass="etiquetaAyuda"
													value="" />
											</div></td>
									</tr>
								</table>
								<br />
							</div>
							<table>
								<tr>
									<td style="width: 180px;"><s:label
											cssClass="etiquetaCaptura"
											value="Requisitos adicionales:" /></td>
									<td><s:textarea
											cols="70"
											id="idCampoRequisitosAdicionales"
											name="requerimientos.requisitosAdicionales"
											onfocus="javascript:focoAyuda('idDivReqAdi');"
											onblur="javascript:blurAyuda('idDivReqAdi');"
											rows="2"></s:textarea> <br />
										<div
											id="idDivReqAdi"
											style="display: none; margin-bottom: 0px; margin-top: -5px;">
											<s:label
												cssClass="etiquetaAyuda"
												value="Describa los requisitos adicionales; tales como certificaciones, criterios de calidad, " />
											<br />
											<s:label
												cssClass="etiquetaAyuda"
												value="condiciones de entrega." />
										</div>
										<div
											id="idDivReqAdi2"
											style="display: block; margin-bottom: 0px; margin-top: 5px;">
											<s:label
												cssClass="etiquetaAyuda"
												cssStyle="color: #FFFFFF;"
												value="" />
											<br />
											<s:label
												cssClass="etiquetaAyuda"
												cssStyle="color: #FFFFFF;"
												value="" />
										</div></td>
								</tr>
							</table>
							<table>
								<tr>
									<td style="width: 450px"><s:label
											cssClass="etiquetaCaptura"
											value="* Fecha de suministro:" /> <s:date
											name="requerimientos.fechaSuministro"
											id="fSuministro"
											format="dd/MM/yyyy" /> <s:textfield
											class="calendario"
											id="ingreso"
											name="requerimientos.fechaSuministro"
											value="%{fSuministro}"
											onfocus="javascript:focoAyuda('idDivFecSum');"
											onblur="javascript:blurAyuda('idDivFecSum');"
											onchange="limpiaCheckSuministro();"
											size="10"
											maxlength="10" /> <img
										src="${pageContext.request.contextPath}/img/calendario.png"
										width="16"
										height="16"
										title="Seleccione una fecha"
										id="lanzador"
										style="cursor: hand"></img> <br /> <s:checkbox
											id="indefinido"
											name="requerimientos.bIndefinido"
											onfocus="javascript:focoAyuda('idDivFecSum');"
											onblur="javascript:blurAyuda('idDivFecSum');"
											onchange="limpiaFechaExpira(1);"
											value="%{requerimientos.bIndefinido}" /> <s:label
											cssClass="etiquetaCaptura"
											value="Indefinido" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:checkbox
											id="variasfechas"
											name="requerimientos.bVariasFechas"
											onfocus="javascript:focoAyuda('idDivFecSum');"
											onblur="javascript:blurAyuda('idDivFecSum');"
											onchange="limpiaFechaExpira(2);"
											value="%{requerimientos.bVariasFechas}" /> <s:label
											cssClass="etiquetaCaptura"
											value="Varias fechas" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:checkbox
											id="suministrocontinuo"
											name="requerimientos.bContinuoSuministro"
											onfocus="javascript:focoAyuda('idDivFecSum');"
											onblur="javascript:blurAyuda('idDivFecSum');"
											onchange="limpiaFechaExpira(3);"
											value="%{requerimientos.bContinuoSuministro}" /> <s:label
											cssClass="etiquetaCaptura"
											value="Continuo" /> <br />
										<div
											id="idDivDetalleVariasFechas"
											${requerimientos.bVariasFechas==true?
											' style="display: block; margin-bottom: 0px; margin-top: 0px;"
											':' style="display: none; margin-bottom: 0px; margin-top: 0px;"' }>
											<s:textfield
												size="25"
												id="idDetalleVariasFechas"
												name="requerimientos.variasFechas"
												maxlength="100"></s:textfield>
										</div>
										<div
											id="idDivFecSum"
											style="display: none; margin-bottom: 0px; margin-top: 0px;">
											<s:label
												cssClass="etiquetaAyuda"
												value="Indique la fecha de suministro o seleccione una opción." />
										</div>
										<div
											id="idDivFecSum2"
											style="display: block; margin-bottom: 0px; margin-top: 15px;">
											<s:label
												cssClass="etiquetaAyuda"
												style="margin-left: 25px;"
												value="" />
										</div></td>
									<td><s:label
											cssClass="etiquetaCaptura"
											value="* Fecha en la que expira el requerimiento:" /> <s:date
											name="requerimientos.fechaExpira"
											id="fExpira"
											format="dd/MM/yyyy" /> <s:textfield
											class="calendario"
											id="ingreso2"
											name="requerimientos.fechaExpira"
											value="%{fExpira}"
											onfocus="javascript:focoAyuda('idDivFecExp');"
											onblur="javascript:blurAyuda('idDivFecExp');"
											onchange="limpiaCheckExpira();"
											size="10"
											maxlength="10" /> <img
										src="${pageContext.request.contextPath}/img/calendario.png"
										width="16"
										height="16"
										title="Seleccione una fecha"
										id="lanzador2"
										style="cursor: hand"></img> <br /> <s:checkbox
											id="expiracontinuo"
											name="requerimientos.bContinuoExpira"
											onclick="javascript:focoAyuda('idDivFecExp');"
											onfocus="javascript:focoAyuda('idDivFecExp');"
											onblur="javascript:blurAyuda('idDivFecExp');"
											onchange="limpiaFechaSuministro();"
											value="%{requerimientos.bContinuoExpira}" /> <s:label
											cssClass="etiquetaCaptura"
											value="Continuo o no tiene expiración" /> <br />
										<div
											id="idDivFecExp"
											style="display: none; margin-bottom: 0px; margin-top: -5px;">
											<s:label
												cssClass="etiquetaAyuda"
												value="Indique la fecha en que expira el requerimiento o si el requerimiento es continuo." />
										</div>
										<div
											id="idDivFecExp2"
											style="display: block; margin-bottom: 0px; margin-top: -5px;">
											<s:label
												cssClass="etiquetaAyuda"
												cssStyle="font-color: #FFFFFF;margin-left: 20px;"
												value="." />
										</div></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td><input
							class="botonenviar"
							value="Anterior"
							type="button"
							onclick="javascript:document.getElementById('sec2').style.display='none'; javascript:document.getElementById('sec1').style.display='block';" />
						</td>
						<td><s:submit
								cssClass="botonenviar"
								value="Guardar" /></td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
		</s:form>
		<div
			id="secR"
			${requerimientos.producto==null?
	' style="display: none;"
	':' style="display: block;"' }>
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
			<table>
				<tr>
					<td
						class="encabezadoTablaResumen"
						colspan="2"
						align="center">Resumen, revise que su infomación es correcta</td>
				</tr>
				<tr>
					<td
						class="cuerpo1TablaResumen"
						align="left">&nbsp;Producto solicitado:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${requerimientos.producto}</s:label></td>
				</tr>
				<tr>
					<td
						class="cuerpo2TablaResumen"
						align="left">&nbsp;Tipo de producto:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${requerimientos.tipoProducto}</s:label></td>
				</tr>
				<tr>
					<td
						class="cuerpo1TablaResumen"
						align="left">&nbsp;Descripción del producto:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${requerimientos.descripcion}</s:label></td>
				</tr>
				<s:if test="requerimientos.idArchivo1!=0">
					<tr>
						<td
							class="cuerpo2TablaResumen"
							align="left">&nbsp;Archivo anexo 1:</td>
						<td class="cuerpo1TextoResumen"><a
							href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo1}&nameArchivo=${requerimientos.archivo1FileName}&mimeArchivo=${requerimientos.archivo1ContentType}">${requerimientos.archivo1FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="requerimientos.idArchivo2!=0">
					<tr>
						<td
							class="cuerpo1TablaResumen"
							align="left">&nbsp;Archivo anexo 2:</td>
						<td class="cuerpo1TextoResumen"><a
							href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo2}&nameArchivo=${requerimientos.archivo2FileName}&mimeArchivo=${requerimientos.archivo2ContentType}">${requerimientos.archivo2FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="requerimientos.idArchivo3!=0">
					<tr>
						<td
							class="cuerpo2TablaResumen"
							align="left">&nbsp;Archivo anexo 3:</td>
						<td class="cuerpo1TextoResumen"><a
							href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo3}&nameArchivo=${requerimientos.archivo3FileName}&mimeArchivo=${requerimientos.archivo3ContentType}">${requerimientos.archivo3FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="requerimientos.idArchivo4!=0">
					<tr>
						<td
							class="cuerpo1TablaResumen"
							align="left">&nbsp;Archivo anexo 4:</td>
						<td class="cuerpo1TextoResumen"><a
							href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo4}&nameArchivo=${requerimientos.archivo4FileName}&mimeArchivo=${requerimientos.archivo4ContentType}">${requerimientos.archivo4FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="requerimientos.idArchivo5!=0">
					<tr>
						<td
							class="cuerpo2TablaResumen"
							align="left">&nbsp;Archivo anexo 5:</td>
						<td class="cuerpo1TextoResumen"><a
							href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo5}&nameArchivo=${requerimientos.archivo5FileName}&mimeArchivo=${requerimientos.archivo5ContentType}">${requerimientos.archivo5FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="requerimientos.idArchivo6!=0">
					<tr>
						<td
							class="cuerpo1TablaResumen"
							align="left">&nbsp;Archivo anexo 6:</td>
						<td class="cuerpo1TextoResumen"><a
							href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo6}&nameArchivo=${requerimientos.archivo6FileName}&mimeArchivo=${requerimientos.archivo6ContentType}">${requerimientos.archivo6FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="requerimientos.idArchivo7!=0">
					<tr>
						<td
							class="cuerpo2TablaResumen"
							align="left">&nbsp;Archivo anexo 7:</td>
						<td class="cuerpo1TextoResumen"><a
							href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo7}&nameArchivo=${requerimientos.archivo7FileName}&mimeArchivo=${requerimientos.archivo7ContentType}">${requerimientos.archivo7FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="requerimientos.idArchivo8!=0">
					<tr>
						<td
							class="cuerpo1TablaResumen"
							align="left">&nbsp;Archivo anexo 8:</td>
						<td class="cuerpo1TextoResumen"><a
							href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo8}&nameArchivo=${requerimientos.archivo8FileName}&mimeArchivo=${requerimientos.archivo8ContentType}">${requerimientos.archivo8FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="requerimientos.idArchivo9!=0">
					<tr>
						<td
							class="cuerpo2TablaResumen"
							align="left">&nbsp;Archivo anexo 9:</td>
						<td class="cuerpo1TextoResumen"><a
							href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo9}&nameArchivo=${requerimientos.archivo9FileName}&mimeArchivo=${requerimientos.archivo9ContentType}">${requerimientos.archivo9FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="requerimientos.idArchivo10!=0">
					<tr>
						<td
							class="cuerpo1TablaResumen"
							align="left">&nbsp;Archivo anexo 10:</td>
						<td class="cuerpo1TextoResumen"><a
							href="${pageContext.request.contextPath}/tractora/administracion/tractoraShowDoc.do?idArchivo=${requerimientos.idArchivo10}&nameArchivo=${requerimientos.archivo10FileName}&mimeArchivo=${requerimientos.archivo10ContentType}">${requerimientos.archivo10FileName}</a>
						</td>
					</tr>
				</s:if>
				<tr>
					<td
						class="cuerpo1TablaResumen"
						align="left">&nbsp;Fecha de suministro:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">
							<s:if test="%{requerimientos.fechaSuministro!=null}">
								<s:date
									name="requerimientos.fechaSuministro"
									format="dd/MM/yyyy" />
							</s:if>
							<s:else>
		${requerimientos.bIndefinido==true?'Indefinido':requerimientos.bVariasFechas==true?'Varias Fechas':requerimientos.bContinuoSuministro==true?'Continuo':''}
		<s:if test="%{requerimientos.bVariasFechas}">
									<tr>
										<td
											class="cuerpo2TablaResumen"
											align="left">&nbsp;Detalle fechas suministro:</td>
										<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${requerimientos.variasFechas}</s:label></td>
									</tr>
								</s:if>
							</s:else>
						</s:label></td>
				</tr>
				<tr>
					<td
						class="cuerpo2TablaResumen"
						align="left">&nbsp;Lugar de suministro:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${requerimientos.lugarSuministro}</s:label></td>
				</tr>
				<tr>
					<td
						class="cuerpo1TablaResumen"
						align="left">&nbsp;Condiciones de pago:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${requerimientos.bContado?'Contado':requerimientos.bCredito?'Crédito':''}</s:label>
						<s:if test="%{requerimientos.bOtro}">
							<s:label cssClass="etiquetaResumen">${requerimientos.bQuince?', con plazo a 15 días':requerimientos.bTreinta?', con plazo a 30 días':requerimientos.bSesenta?', con plazo a 60 días':requerimientos.bNoventa?', con plazo a 90 días':''}</s:label>
						</s:if></td>
				</tr>
				<s:if test="%{requerimientos.bCredito}">
					<tr>
						<td
							class="cuerpo2TablaResumen"
							align="left">&nbsp;Otras condiciones:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${requerimientos.otrasCondiciones}</s:label>
							<br /></td>
					</tr>
				</s:if>
				<tr>
					<td
						class="cuerpo1TablaResumen"
						align="left">&nbsp;Requisitos adicionales:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${requerimientos.requisitosAdicionales}</s:label></td>
				</tr>
				<tr>
					<td
						class="cuerpo2TablaResumen"
						align="left">&nbsp;Fecha en la que expira el requerimiento:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">
							<s:if test="%{requerimientos.fechaExpira!=null}">
								<s:date
									name="requerimientos.fechaExpira"
									format="dd/MM/yyyy" />
							</s:if>
							<s:else>
		${requerimientos.bContinuoExpira==true?'Continuo':''}
		</s:else>
						</s:label></td>
				</tr>
			</table>
			<table class="submit_tabla">
				<tr>
					<td style="width: 250px;"></td>
					<td><input
						class="botonenviar"
						value="Modificar"
						type="button"
						onclick="javascript:modificar();" /></td>
					<td><input
						class="botonenviar"
						value="Requerimientos"
						type="button"
						onclick="cancela();" /></td>
					<td style="width: 250px;"></td>
				</tr>
			</table>
		</div>
		<s:form
			name="frmCancela"
			action="tractoraRequerimientosShow"
			namespace="/tractora/administracion"
			theme="simple"
			method="post">
		</s:form>
	</fieldset>
	<s:if test="%{requerimientos==null}">
		<script type="text/javascript">
			calendario();
		</script>
	</s:if>
</body>
</html>