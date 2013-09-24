<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/css/calendario.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ayudas.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ccmx.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar-es.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar-setup.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
</head>

<body>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-325px auto 0 250px';
</script>
<fieldset id="requerimientos">
	<legend>
		<s:label value="Captura para registro de Diplomado" />
		<br /><br />
		<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
	</legend>
	<br />
	<s:if test="opcion == null">
		<s:form action="diplomadosShow" namespace="/ccmx/administracion/diplomados" theme="simple" onsubmit="return registraDip()">
			<table width="99%">
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Nombre del Diplomado:" /></td>
					<td>
						<s:textfield size="80" id="nomDiplomado" name="diplomado.tema" maxlength="150" onfocus="javascript:ayudasHelp(0);" onblur="javascript:ayudasHelpBlo(0);"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay0" style="display:none;margin-top:0px;" value="Ingrese el nombre del diplomado." /></td>
				</tr>
			</table>
			<table width="86%">	
				<tr>
					<td><s:label id="ayudasDisplay1" style="display:none;margin-top:0px;" cssClass="etiquetaAyuda" value="* Seleccione el número de generaciones que tendrá el diplomado" /></td>
					<td><s:label id="ayudasDisplay2" style="display:none;margin-top:0px;" cssClass="etiquetaAyuda" value="* Seleccione el año en que se impartirá el diplomado" /></td>
				</tr>
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" value="* Número de generaciones:" />
						<select id="generacion" name="generaciones" style="width: 180px;" onfocus="javascript:ayudasHelp(1);" onblur="javascript:ayudasHelpBlo(1);" >
							<option value="0">--Seleccione una opción--</option>
							<s:iterator value="(10).{ #this }" status="stat">
								<option value="${stat.count}">${stat.count}</option>
							</s:iterator>
						</select>
					</td>
					<td>
						<s:label cssClass="etiquetaCaptura" value="* Año del diplomado:" />
						<select id="anio" name="diplomado.year" style="width: 180px;" onfocus="javascript:ayudasHelp(2);" onblur="javascript:ayudasHelpBlo(2);">
							<option value="0">--Seleccione una opción--</option>
							<s:iterator value="menuAnios" status="stat">
								<option value="${menuAnios[stat.index]}">${menuAnios[stat.index]}</option>
							</s:iterator>
						</select>
					</td>
				</tr>
			</table>
			<br /><br />
			<table>
				<tr>
					<td>
						<s:submit cssClass="botonenviar" value="Registrar Diplomado" />
					</td>
				</tr>
			</table>
		</s:form>
	</s:if>

	<s:else>
		<s:form name="sesionest" action="diplomadosShow" namespace="/ccmx/administracion/diplomados" theme="simple">
			<table width="99%">
				<tr>
					<td>
						<table>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Nombre del diplomado:" /></td>
								<td>
									<s:textfield size="50" id="nombreDip" name="tituloDiplomado" maxlength="100" onfocus="javascript:ayudasHelp(10000);" onblur="javascript:ayudasHelpBlo(10000);"></s:textfield>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><s:label id="ayudasDisplay10000" cssClass="etiquetaAyuda" cssStyle="display: none;" value="Ingrese el nombre del diplomado." /></td>
							</tr>
						</table>
					</td>
					<td>
						<table>
							<tr>
								<td>
									<input <s:if test="servicios != 0">style="display: none;"</s:if> class="botonenviar" value="Eliminar Diplomado" type="button" onclick="javascript:deleteDiplomado();" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

			<s:hidden name="numeroSesiones" id="numeroSesiones" />
			<s:hidden name="idDiplomado" id="idDiplomado" value="%{idDiplomado}" />

			<div id="sesiont1" style="display: block;">
				<s:label cssClass="resultado" cssStyle="font-size: 20px;" value="Sesión 1" />
				<s:hidden name="listSesiones[0].idSesion" value="%{listSesiones[0].idSesion}" />
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Sede:" /></td>
									<td><s:textfield size="50" id="idExpositor1" name="listSesiones[0].expositor" maxlength="100" onfocus="javascript:ayudasHelp(1);" onblur="javascript:ayudasHelpBlo(1);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay1" style="display:none;margin-top:-1px;" value="Ingrese la sede de la sesión 1." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Sala:" /></td>
									<td><s:textfield size="40" id="idSala1" name="listSesiones[0].sala" maxlength="100" onfocus="javascript:ayudasHelp(2);" onblur="javascript:ayudasHelpBlo(2);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay2" style="display:none;margin-top:-1px;" value="Ingrese la sala de la sesión 1." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Fecha:" /></td>
									<td>
										<s:date name="listSesiones[0].fecha" id="fCert1" format="dd/MM/yyyy" /> <s:textfield class="calendario" id="ingreso1" name="listSesiones[0].fecha" value="%{fCert1}"size="10" maxlength="10" />
										<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador1" style="cursor: hand" />
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Horario:" /></td>
									<td>
										<select name="listSesiones[0].hora" style="width: 40px;" id="hora1">
											<option value="-1">--</option>
											<s:iterator value="(24).{ #this }" status="stat">
												<option <s:if test="listSesiones[0].hora == #stat.index ">selected="selected"</s:if> value="${stat.index}">${stat.index}</option>
											</s:iterator>
										</select> : 
										<select name="listSesiones[0].minuto" style="width: 40px;" id="minuto1">
											<option value="-1">--</option>
											<s:iterator  value="(6).{ #this }" status="stat">
												<option <s:if test="listSesiones[0].minuto == #stat.index + '0' ">selected="selected"</s:if> value="${stat.index}0">${stat.index}0</option>
											</s:iterator>
										</select> hrs.  &nbsp;
										<select name="listSesiones[0].horaFin" style="width: 40px;" id="horaFin1">
											<option value="-1">--</option>
											<s:iterator value="(24).{ #this }" status="stat">
												<option <s:if test="listSesiones[0].horaFin == #stat.index ">selected="selected"</s:if> value="${stat.index}">${stat.index}</option>
											</s:iterator>
										</select> : 
										<select name="listSesiones[0].minutoFin" style="width: 40px;" id="minutoFin1">
											<option value="-1">--</option>
											<s:iterator value="(6).{ #this }" status="stat">
												<option <s:if test="listSesiones[0].minutoFin == #stat.index + '0' ">selected="selected"</s:if> value="${stat.index}0">${stat.index}0</option>
											</s:iterator>
										</select> hrs.
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Instructor:" /></td>
									<td><s:textfield size="50" id="idInstuctor1" name="listSesiones[0].instructor" maxlength="80" onfocus="javascript:ayudasHelp(11);" onblur="javascript:ayudasHelpBlo(11);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay11" style="display:none;margin-top:-1px;" value="Ingrese el instructor de la sesión 1." /></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaCaptura" value="Dirección:" /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Calle:" /></td>
									<td><s:textfield size="30" id="calle1" name="listSesiones[0].domicilios.calle" maxlength="50" onfocus="javascript:ayudasHelp(3);" onblur="javascript:ayudasHelpBlo(3);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay3" style="display:none; margin-top:5px;" value="Escriba la calle de su empresa." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Número Exterior:" /></td>
									<td><s:textfield size="20" id="numExt1" name="listSesiones[0].domicilios.numExt" maxlength="20" onfocus="javascript:ayudasHelp(4);" onblur="javascript:ayudasHelpBlo(4);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay4" style="display:none; margin-top:5px;" value="Escriba el número exterior." /></td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Número Interior:" /></td>
									<td><s:textfield size="20" id="numInt1" name="listSesiones[0].domicilios.numInt" maxlength="20" onfocus="javascript:ayudasHelp(5);" onblur="javascript:ayudasHelpBlo(5);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay5" style="display:none; margin-top:5px;" value="Escriba el número interior." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Piso:" /></td>
									<td><s:textfield size="30" id="piso1" name="listSesiones[0].domicilios.piso" maxlength="20" onfocus="javascript:ayudasHelp(6);" onblur="javascript:ayudasHelpBlo(6);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay6" style="display:none; margin-top:5px;" value="Escriba el piso." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Colonia:" /></td>
									<td><s:textfield size="30" id="colonia1" name="listSesiones[0].domicilios.colonia" maxlength="50" onfocus="javascript:ayudasHelp(7);" onblur="javascript:ayudasHelpBlo(7);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay7" style="display:none; margin-top:5px;" value="Escriba la colonia." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Delegación/Municipio:" /></td>
									<td><s:textfield size="30" id="delegacion1" name="listSesiones[0].domicilios.delegacion" maxlength="50" onfocus="javascript:ayudasHelp(8);" onblur="javascript:ayudasHelpBlo(8);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay8" style="display:none; margin-top:5px;" value="Escriba Delegación o Municipio." /></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay9" style="display:none; margin-top:5px;" value="Seleccione el Estado ." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Estado:" /></td>
									<td>
										<select id="estado1" name="listSesiones[0].domicilios.estado" style="width: 230px;" onfocus="javascript:ayudasHelp(9);" onblur="javascript:ayudasHelpBlo(9);">
											<option value="0">--Seleccione un estado--</option>
											<option ${listSesiones[0].domicilios.estado== 'Aguascalientes' ? ' selected="selected" ' : ''} value="Aguascalientes">Aguascalientes</option>
											<option ${listSesiones[0].domicilios.estado== 'Baja California Norte' ? ' selected="selected" ' : ''} value="Baja California Norte">Baja California Norte</option>
											<option ${listSesiones[0].domicilios.estado== 'Baja California Sur' ? ' selected="selected" ' : ''} value="Baja California Sur">Baja California Sur</option>
											<option ${listSesiones[0].domicilios.estado== 'Campeche' ? ' selected="selected" ' : ''} value="Campeche">Campeche</option>
											<option ${listSesiones[0].domicilios.estado== 'Chiapas' ? ' selected="selected" ' : ''} value="Chiapas">Chiapas</option>
											<option ${listSesiones[0].domicilios.estado== 'Chihuahua' ? ' selected="selected" ' : ''} value="Chihuahua">Chihuahua</option>
											<option ${listSesiones[0].domicilios.estado== 'Coahuila' ? ' selected="selected" ' : ''} value="Coahuila">Coahuila</option>
											<option ${listSesiones[0].domicilios.estado== 'Colima' ? ' selected="selected" ' : ''} value="Colima">Colima</option>
											<option ${listSesiones[0].domicilios.estado== 'Distrito Federal' ? ' selected="selected" ' : ''} value="Distrito Federal">Distrito Federal</option>
											<option ${listSesiones[0].domicilios.estado== 'Durango' ? ' selected="selected" ' : ''} value="Durango">Durango</option>
											<option ${listSesiones[0].domicilios.estado == 'Estado de Mexico' ? ' selected="selected" ' : ''} value="Estado de Mexico">Estado de México</option>
											<option ${listSesiones[0].domicilios.estado== 'Guanajuato' ? ' selected="selected" ' : ''} value="Guanajuato">Guanajuato</option>
											<option ${listSesiones[0].domicilios.estado== 'Guerrero' ? ' selected="selected" ' : ''} value="Guerrero">Guerrero</option>
											<option ${listSesiones[0].domicilios.estado== 'Hidalgo' ? ' selected="selected" ' : ''} value="Hidalgo">Hidalgo</option>
											<option ${listSesiones[0].domicilios.estado== 'Jalisco' ? ' selected="selected" ' : ''} value="Jalisco">Jalisco</option>
											<option ${listSesiones[0].domicilios.estado== 'Michoacan' ? ' selected="selected" ' : ''} value="Michoacan">Michoacán</option>
											<option ${listSesiones[0].domicilios.estado== 'Morelos' ? ' selected="selected" ' : ''} value="Morelos">Morelos</option>
											<option ${listSesiones[0].domicilios.estado== 'Nayarit' ? ' selected="selected" ' : ''} value="Nayarit">Nayarit</option>
											<option ${listSesiones[0].domicilios.estado== 'Nuevo Leon' ? ' selected="selected" ' : ''} value="Nuevo Leon">Nuevo León</option>
											<option ${listSesiones[0].domicilios.estado== 'Oaxaca' ? ' selected="selected" ' : ''} value="Oaxaca">Oaxaca</option>
											<option ${listSesiones[0].domicilios.estado== 'Puebla' ? ' selected="selected" ' : ''} value="Puebla">Puebla</option>
											<option ${listSesiones[0].domicilios.estado== 'Queretaro' ? ' selected="selected" ' : ''} value="Queretaro">Querétaro</option>
											<option ${listSesiones[0].domicilios.estado== 'Quintana Roo' ? ' selected="selected" ' : ''} value="Quintana Roo">Quintana Roo</option>
											<option ${listSesiones[0].domicilios.estado== 'San Luis Potosi' ? ' selected="selected" ' : ''} value="San Luis Potosi">San Luís Potosí</option>
											<option ${listSesiones[0].domicilios.estado== 'Sinaloa' ? ' selected="selected" ' : ''} value="Sinaloa">Sinaloa</option>
											<option ${listSesiones[0].domicilios.estado=='Sonora' ? ' selected="selected" ' : ''} value="Sonora">Sonora</option>
											<option ${listSesiones[0].domicilios.estado== 'Tabasco' ? ' selected="selected" ' : ''} value="Tabasco">Tabasco</option>
											<option ${listSesiones[0].domicilios.estado== 'Tamaulipas' ? ' selected="selected" ' : ''} value="Tamaulipas">Tamaulipas</option>
											<option ${listSesiones[0].domicilios.estado== 'Tlaxcala' ? ' selected="selected" ' : ''} value="Tlaxcala">Tlaxcala</option>
											<option ${listSesiones[0].domicilios.estado== 'Veracruz' ? ' selected="selected" ' : ''} value="Veracruz">Veracruz</option>
											<option ${listSesiones[0].domicilios.estado== 'Yucatan' ? ' selected="selected" ' : ''} value="Yucatan">Yucatán</option>
											<option ${listSesiones[0].domicilios.estado== 'Zacatecas' ? ' selected="selected" ' : ''} value="Zacatecas">Zacatecas</option>
										</select>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* C.P.:" /></td>
									<td><s:textfield size="20" id="codigoPostal1" name="listSesiones[0].domicilios.codigoPostal" maxlength="5" onfocus="javascript:ayudasHelp(10);" onblur="javascript:ayudasHelpBlo(10);" onkeypress="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay10" style="display:none; margin-top:5px;" value="Escriba el Código postal en que se encuentra." /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br />
				<table>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="* Información importante:" /></td>
					</tr>
					<tr>
						<td><s:textarea cols="110" rows="9" id="idInfo1" name="listSesiones[0].info" value="%{listSesiones[0].info}" maxlength="1000" onfocus="javascript:ayudasHelp(13);" onblur="javascript:ayudasHelpBlo(13);" /></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay13" style="display:none;margin-top:-1px;" value="Ingrese la información importante" /></td>
					</tr>
				</table>
				<table width="99%; " class="submit_tabla">
					<tr>
						<s:hidden name="listSesiones[0].idDiplomado" id="idDiplomado" value="%{idDiplomado}" />
						<s:if test="%{listSesiones[0].domicilios.idDomicilio==0}" >
							<s:hidden name="listSesiones[0].domicilios.idDomicilio" value="0" />
						</s:if>
						<s:else>
							<s:hidden name="listSesiones[0].domicilios.idDomicilio" value="%{listSesiones[0].domicilios.idDomicilio}" />
						</s:else>
						<td align="center" style="width: 50%;">
							<input class="botonenviar" value="Siguiente sesión" type="button" onclick="javascript:siguiente(1);" />
						</td>
						<td align="center" style="width: 50%;">
							<input class="botonenviar" value="Guardar" type="button" onclick="javascript:finalizar(1);" />
						</td>
					</tr>
				</table>
			</div>

			<div id="sesiont2" style="display: none;">
				<s:label cssClass="resultado" cssStyle="font-size: 20px;" value="Sesión 2" />
				<s:hidden name="listSesiones[1].idSesion" value="%{listSesiones[1].idSesion}"></s:hidden>					
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Sede:" /></td>
									<td><s:textfield size="50" id="idExpositor2" name="listSesiones[1].expositor" maxlength="100" onfocus="javascript:ayudasHelp(14);" onblur="javascript:ayudasHelpBlo(14);"></s:textfield></td>
									</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay14" style="display:none;margin-top:-1px;" value="Ingrese la sede de la sesión 2." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Sala:" />
									</td><td><s:textfield size="40" id="idSala2" name="listSesiones[1].sala" maxlength="100" onfocus="javascript:ayudasHelp(15);" onblur="javascript:ayudasHelpBlo(15);"> </s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay15" style="display:none;margin-top:-1px;" value="Ingrese la sala de la sesión 2." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Fecha:" /></td>
									<td>
										<s:date name="listSesiones[1].fecha" id="fCert2" format="dd/MM/yyyy" /> <s:textfield class="calendario" id="ingreso2" name="listSesiones[1].fecha" value="%{fCert2}" size="10" maxlength="10" />
										<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador2" style="cursor: hand" />
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Horario:" /></td>
									<td>
										<select name="listSesiones[1].hora" style="width: 40px;" id="hora2">
											<option value="-1">--</option>
											<s:iterator value="(24).{ #this }" status="stat">
												<option <s:if test="listSesiones[1].hora == #stat.index ">selected="selected"</s:if> value="${stat.index}">${stat.index}</option>
											</s:iterator>
										</select> : 
										<select name="listSesiones[1].minuto" style="width: 40px;" id="minuto2">
											<option value="-1">--</option>
											<s:iterator value="(6).{ #this }" status="stat">
												<option <s:if test="listSesiones[1].minuto == #stat.index + '0' ">selected="selected"</s:if> value="${stat.index}0">${stat.index}0</option>
											</s:iterator>
										</select> hrs.  &nbsp;
										<select name="listSesiones[1].horaFin" style="width: 40px;" id="horaFin2">
											<option value="-1">--</option>
											<s:iterator value="(24).{ #this }" status="stat">
												<option <s:if test="listSesiones[1].horaFin == #stat.index ">selected="selected"</s:if> value="${stat.index}">${stat.index}</option>
											</s:iterator>
										</select> : 
										<select name="listSesiones[1].minutoFin" style="width: 40px;" id="minutoFin2">
											<option value="-1">--</option>
											<s:iterator value="(6).{ #this }" status="stat">
												<option <s:if test="listSesiones[1].minutoFin == #stat.index + '0' ">selected="selected"</s:if> value="${stat.index}0">${stat.index}0</option>
											</s:iterator>
										</select> hrs.
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Instructor:" />
									</td><td><s:textfield size="50" id="idInstuctor2" name="listSesiones[1].instructor" maxlength="80" onfocus="javascript:ayudasHelp(16);" onblur="javascript:ayudasHelpBlo(16);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay16" style="display:none;margin-top:-1px;" value="Ingrese el instructor de la sesión 2." /></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaCaptura" value="Dirección:" /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Calle:" /></td>
									<td><s:textfield size="30" id="calle2" name="listSesiones[1].domicilios.calle" maxlength="50" onfocus="javascript:ayudasHelp(17);" onblur="javascript:ayudasHelpBlo(17);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay17" style="display:none; margin-top:5px;" value="Escriba la calle de su empresa." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Número Exterior:" /></td>
									<td><s:textfield size="20" id="numExt2" name="listSesiones[1].domicilios.numExt" maxlength="20" onfocus="javascript:ayudasHelp(18);" onblur="javascript:ayudasHelpBlo(18);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay18" style="display:none; margin-top:5px;" value="Escriba el número exterior." /></td>	
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Número Interior:" /></td>
									<td><s:textfield size="20" id="numInt2" name="listSesiones[1].domicilios.numInt" maxlength="20" onfocus="javascript:ayudasHelp(19);" onblur="javascript:ayudasHelpBlo(19);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay19" style="display:none; margin-top:5px;" value="Escriba el número interior." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Piso:" /></td>
									<td><s:textfield size="30" id="piso2" name="listSesiones[1].domicilios.piso" maxlength="20" onfocus="javascript:ayudasHelp(20);" onblur="javascript:ayudasHelpBlo(20);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay20" style="display:none; margin-top:5px;" value="Escriba el piso." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Colonia:" /></td>
									<td><s:textfield size="30" id="colonia2" name="listSesiones[1].domicilios.colonia" maxlength="50" onfocus="javascript:ayudasHelp(21);" onblur="javascript:ayudasHelpBlo(21);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay21" style="display:none; margin-top:5px;" value="Escriba la colonia." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Delegación/Municipio:" /></td>
									<td><s:textfield size="30" id="delegacion2" name="listSesiones[1].domicilios.delegacion" maxlength="50" onfocus="javascript:ayudasHelp(22);" onblur="javascript:ayudasHelpBlo(22);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay22" style="display:none; margin-top:5px;" value="Escriba Delegación o Municipio." /></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay23" style="display:none; margin-top:5px;" value="Seleccione el Estado ." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Estado:" /></td>
									<td>
										<select id="estado2" name="listSesiones[1].domicilios.estado" style="width: 230px;" onfocus="javascript:ayudasHelp(23);" onblur="javascript:ayudasHelpBlo(23);">
											<option value="0">--Seleccione un estado--</option>
											<option ${listSesiones[1].domicilios.estado== 'Aguascalientes' ? ' selected="selected" ' : ''} value="Aguascalientes">Aguascalientes</option>
											<option ${listSesiones[1].domicilios.estado== 'Baja California Norte' ? ' selected="selected" ' : ''} value="Baja California Norte">Baja California Norte</option>
											<option ${listSesiones[1].domicilios.estado== 'Baja California Sur' ? ' selected="selected" ' : ''} value="Baja California Sur">Baja California Sur</option>
											<option ${listSesiones[1].domicilios.estado== 'Campeche' ? ' selected="selected" ' : ''} value="Campeche">Campeche</option>
											<option ${listSesiones[1].domicilios.estado== 'Chiapas' ? ' selected="selected" ' : ''} value="Chiapas">Chiapas</option>
											<option ${listSesiones[1].domicilios.estado== 'Chihuahua' ? ' selected="selected" ' : ''} value="Chihuahua">Chihuahua</option>
											<option ${listSesiones[1].domicilios.estado== 'Coahuila' ? ' selected="selected" ' : ''} value="Coahuila">Coahuila</option>
											<option ${listSesiones[1].domicilios.estado== 'Colima' ? ' selected="selected" ' : ''} value="Colima">Colima</option>
											<option ${listSesiones[1].domicilios.estado== 'Distrito Federal' ? ' selected="selected" ' : ''} value="Distrito Federal">Distrito Federal</option>
											<option ${listSesiones[1].domicilios.estado== 'Durango' ? ' selected="selected" ' : ''} value="Durango">Durango</option>
											<option ${listSesiones[1].domicilios.estado == 'Estado de Mexico' ? ' selected="selected" ' : ''} value="Estado de Mexico">Estado de México</option>
											<option ${listSesiones[1].domicilios.estado== 'Guanajuato' ? ' selected="selected" ' : ''} value="Guanajuato">Guanajuato</option>
											<option ${listSesiones[1].domicilios.estado== 'Guerrero' ? ' selected="selected" ' : ''} value="Guerrero">Guerrero</option>
											<option ${listSesiones[1].domicilios.estado== 'Hidalgo' ? ' selected="selected" ' : ''} value="Hidalgo">Hidalgo</option>
											<option ${listSesiones[1].domicilios.estado== 'Jalisco' ? ' selected="selected" ' : ''} value="Jalisco">Jalisco</option>
											<option ${listSesiones[1].domicilios.estado== 'Michoacan' ? ' selected="selected" ' : ''} value="Michoacan">Michoacán</option>
											<option ${listSesiones[1].domicilios.estado== 'Morelos' ? ' selected="selected" ' : ''} value="Morelos">Morelos</option>
											<option ${listSesiones[1].domicilios.estado== 'Nayarit' ? ' selected="selected" ' : ''} value="Nayarit">Nayarit</option>
											<option ${listSesiones[1].domicilios.estado== 'Nuevo Leon' ? ' selected="selected" ' : ''} value="Nuevo Leon">Nuevo León</option>
											<option ${listSesiones[1].domicilios.estado== 'Oaxaca' ? ' selected="selected" ' : ''} value="Oaxaca">Oaxaca</option>
											<option ${listSesiones[1].domicilios.estado== 'Puebla' ? ' selected="selected" ' : ''} value="Puebla">Puebla</option>
											<option ${listSesiones[1].domicilios.estado== 'Queretaro' ? ' selected="selected" ' : ''} value="Queretaro">Querétaro</option>
											<option ${listSesiones[1].domicilios.estado== 'Quintana Roo' ? ' selected="selected" ' : ''} value="Quintana Roo">Quintana Roo</option>
											<option ${listSesiones[1].domicilios.estado== 'San Luis Potosi' ? ' selected="selected" ' : ''} value="San Luis Potosi">San Luís Potosí</option>
											<option ${listSesiones[1].domicilios.estado== 'Sinaloa' ? ' selected="selected" ' : ''} value="Sinaloa">Sinaloa</option>
											<option ${listSesiones[1].domicilios.estado=='Sonora' ? ' selected="selected" ' : ''} value="Sonora">Sonora</option>
											<option ${listSesiones[1].domicilios.estado== 'Tabasco' ? ' selected="selected" ' : ''} value="Tabasco">Tabasco</option>
											<option ${listSesiones[1].domicilios.estado== 'Tamaulipas' ? ' selected="selected" ' : ''} value="Tamaulipas">Tamaulipas</option>
											<option ${listSesiones[1].domicilios.estado== 'Tlaxcala' ? ' selected="selected" ' : ''} value="Tlaxcala">Tlaxcala</option>
											<option ${listSesiones[1].domicilios.estado== 'Veracruz' ? ' selected="selected" ' : ''} value="Veracruz">Veracruz</option>
											<option ${listSesiones[1].domicilios.estado== 'Yucatan' ? ' selected="selected" ' : ''} value="Yucatan">Yucatán</option>
											<option ${listSesiones[1].domicilios.estado== 'Zacatecas' ? ' selected="selected" ' : ''} value="Zacatecas">Zacatecas</option>
										</select>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* C.P.:" /></td>
									<td><s:textfield size="20" id="codigoPostal2" name="listSesiones[1].domicilios.codigoPostal" maxlength="5" onfocus="javascript:ayudasHelp(24);" onblur="javascript:ayudasHelpBlo(24);" onkeypress="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay24" style="display:none; margin-top:5px;" value="Escriba el Código postal en que se encuentra." /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br />
				<table>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="* Información importante:" /></td>
					</tr>
					<tr>
						<td><s:textarea cols="110" rows="9" id="idInfo2" name="listSesiones[1].info" value="%{listSesiones[1].info}" maxlength="1000" onfocus="javascript:ayudasHelp(25);" onblur="javascript:ayudasHelpBlo(25);" /></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay25" style="display:none;margin-top:-1px;" value="Ingrese la información importante" /></td>
					</tr>
				</table>
				<table width="99%; " class="submit_tabla">
					<tr>
						<s:hidden name="listSesiones[1].idDiplomado" id="idDiplomado" value="%{idDiplomado}" />
						<td align="center" style="width: 33%;">
							<input class="botonenviar" value="Anterior sesión" type="button" onclick="javascript:anterior(2);" /> 
							<s:if test="%{listSesiones[1].domicilios.idDomicilio==0}" >
								<s:hidden name="listSesiones[1].domicilios.idDomicilio" value="0" />
							</s:if>
							<s:else>
								<s:hidden name="listSesiones[1].domicilios.idDomicilio" value="%{listSesiones[1].domicilios.idDomicilio}" />
							</s:else>
						</td>
						<td align="center" style="width: 33%;">
							<input class="botonenviar" value="Siguiente sesión" type="button" onclick="javascript:siguiente(2);" />
						</td>
						<td align="center" style="width: 33%;">
							<input class="botonenviar" value="Guardar" type="button" onclick="javascript:finalizar(2);" />
						</td>
					</tr>
				</table>
			</div>

			<div id="sesiont3" style="display: none;">
				<s:label cssClass="resultado" cssStyle="font-size: 20px;" value="Sesión 3" />
				<s:hidden name="listSesiones[2].idSesion" value="%{listSesiones[2].idSesion}" />
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Sede:" /></td>
									<td><s:textfield size="50" id="idExpositor3" name="listSesiones[2].expositor" maxlength="100" onfocus="javascript:ayudasHelp(26);" onblur="javascript:ayudasHelpBlo(26);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay26" style="display:none;margin-top:-1px;" value="Ingrese la sede de la sesión 3." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Sala:" /></td>
									<td><s:textfield size="40" id="idSala3" name="listSesiones[2].sala" maxlength="100" onfocus="javascript:ayudasHelp(27);" onblur="javascript:ayudasHelpBlo(27);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay27" style="display:none;margin-top:-1px;" value="Ingrese la sala de la sesión 3." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Fecha:" /></td>
									<td>
										<s:date name="listSesiones[2].fecha" id="fCert3" format="dd/MM/yyyy" /> <s:textfield class="calendario" id="ingreso3" name="listSesiones[2].fecha" value="%{fCert3}" size="10" maxlength="10" />
										<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador3" style="cursor: hand" />
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* Horario:" /> 
									</td>
									<td>
										<select name="listSesiones[2].hora" style="width: 40px;" id="hora3">
											<option value="-1">--</option>
											<s:iterator value="(24).{ #this }" status="stat">
												<option <s:if test="listSesiones[2].hora == #stat.index ">selected="selected"</s:if> value="${stat.index}">${stat.index}</option>
											</s:iterator>
										</select> : 
										<select name="listSesiones[2].minuto" style="width: 40px;" id="minuto3">
											<option value="-1">--</option>
											<s:iterator value="(6).{ #this }" status="stat">
												<option <s:if test="listSesiones[2].minuto == #stat.index + '0' ">selected="selected"</s:if> value="${stat.index}0">${stat.index}0</option>
											</s:iterator>
										</select> hrs.  &nbsp;
										<select name="listSesiones[2].horaFin" style="width: 40px;" id="horaFin3">
												<option value="-1">--</option>
												<s:iterator value="(24).{ #this }" status="stat">
													<option <s:if test="listSesiones[2].horaFin == #stat.index ">selected="selected"</s:if> value="${stat.index}">${stat.index}</option>
												</s:iterator>
										</select> : 
										<select name="listSesiones[2].minutoFin" style="width: 40px;" id="minutoFin3">
											<option value="-1">--</option>
											<s:iterator value="(6).{ #this }" status="stat">
												<option <s:if test="listSesiones[2].minutoFin == #stat.index + '0' ">selected="selected"</s:if> value="${stat.index}0">${stat.index}0</option>
											</s:iterator>
										</select> hrs.
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Instructor:" /></td>
									<td><s:textfield size="50" id="idInstuctor3" name="listSesiones[2].instructor" maxlength="80" onfocus="javascript:ayudasHelp(28);" onblur="javascript:ayudasHelpBlo(28);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay28" style="display:none;margin-top:-1px;" value="Ingrese el instructor de la sesión 3." /></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaCaptura" value="Dirección:" /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Calle:" /></td>
									<td><s:textfield size="30" id="calle3" name="listSesiones[2].domicilios.calle" maxlength="50" onfocus="javascript:ayudasHelp(29);" onblur="javascript:ayudasHelpBlo(29);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay29" style="display:none; margin-top:5px;" value="Escriba la calle de su empresa." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Número Exterior:" /></td>
									<td><s:textfield size="20" id="numExt3" name="listSesiones[2].domicilios.numExt" maxlength="20" onfocus="javascript:ayudasHelp(30);" onblur="javascript:ayudasHelpBlo(30);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay30" style="display:none; margin-top:5px;" value="Escriba el número exterior." /></td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Número Interior:" /></td>
									<td><s:textfield size="20" id="numInt3" name="listSesiones[2].domicilios.numInt" maxlength="20" onfocus="javascript:ayudasHelp(31);" onblur="javascript:ayudasHelpBlo(31);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay31" style="display:none; margin-top:5px;" value="Escriba el número interior." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Piso:" /></td>
									<td><s:textfield size="30" id="piso3" name="listSesiones[2].domicilios.piso" maxlength="20" onfocus="javascript:ayudasHelp(32);" onblur="javascript:ayudasHelpBlo(32);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay32" style="display:none; margin-top:5px;" value="Escriba el piso." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Colonia:" /></td>
									<td><s:textfield size="30" id="colonia3" name="listSesiones[2].domicilios.colonia" maxlength="50" onfocus="javascript:ayudasHelp(33);" onblur="javascript:ayudasHelpBlo(33);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay33" style="display:none; margin-top:5px;" value="Escriba la colonia." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Delegación/Municipio:" /></td>
									<td><s:textfield size="30" id="delegacion3" name="listSesiones[2].domicilios.delegacion" maxlength="50" onfocus="javascript:ayudasHelp(34);" onblur="javascript:ayudasHelpBlo(34);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay34" style="display:none; margin-top:5px;" value="Escriba Delegación o Municipio." /></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay35" style="display:none; margin-top:5px;" value="Seleccione el Estado ." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Estado:" /></td>
									<td>
										<select id="estado3" name="listSesiones[2].domicilios.estado" style="width: 230px;" onfocus="javascript:ayudasHelp(35);" onblur="javascript:ayudasHelpBlo(35);">
											<option value="0">--Seleccione un estado--</option>
											<option ${listSesiones[2].domicilios.estado== 'Aguascalientes' ? ' selected="selected" ' : ''} value="Aguascalientes">Aguascalientes</option>
											<option ${listSesiones[2].domicilios.estado== 'Baja California Norte' ? ' selected="selected" ' : ''} value="Baja California Norte">Baja California Norte</option>
											<option ${listSesiones[2].domicilios.estado== 'Baja California Sur' ? ' selected="selected" ' : ''} value="Baja California Sur">Baja California Sur</option>
											<option ${listSesiones[2].domicilios.estado== 'Campeche' ? ' selected="selected" ' : ''} value="Campeche">Campeche</option>
											<option ${listSesiones[2].domicilios.estado== 'Chiapas' ? ' selected="selected" ' : ''} value="Chiapas">Chiapas</option>
											<option ${listSesiones[2].domicilios.estado== 'Chihuahua' ? ' selected="selected" ' : ''} value="Chihuahua">Chihuahua</option>
											<option ${listSesiones[2].domicilios.estado== 'Coahuila' ? ' selected="selected" ' : ''} value="Coahuila">Coahuila</option>
											<option ${listSesiones[2].domicilios.estado== 'Colima' ? ' selected="selected" ' : ''} value="Colima">Colima</option>
											<option ${listSesiones[2].domicilios.estado== 'Distrito Federal' ? ' selected="selected" ' : ''} value="Distrito Federal">Distrito Federal</option>
											<option ${listSesiones[2].domicilios.estado== 'Durango' ? ' selected="selected" ' : ''} value="Durango">Durango</option>
											<option ${listSesiones[2].domicilios.estado == 'Estado de Mexico' ? ' selected="selected" ' : ''} value="Estado de Mexico">Estado de México</option>
											<option ${listSesiones[2].domicilios.estado== 'Guanajuato' ? ' selected="selected" ' : ''} value="Guanajuato">Guanajuato</option>
											<option ${listSesiones[2].domicilios.estado== 'Guerrero' ? ' selected="selected" ' : ''} value="Guerrero">Guerrero</option>
											<option ${listSesiones[2].domicilios.estado== 'Hidalgo' ? ' selected="selected" ' : ''} value="Hidalgo">Hidalgo</option>
											<option ${listSesiones[2].domicilios.estado== 'Jalisco' ? ' selected="selected" ' : ''} value="Jalisco">Jalisco</option>
											<option ${listSesiones[2].domicilios.estado== 'Michoacan' ? ' selected="selected" ' : ''} value="Michoacan">Michoacán</option>
											<option ${listSesiones[2].domicilios.estado== 'Morelos' ? ' selected="selected" ' : ''} value="Morelos">Morelos</option>
											<option ${listSesiones[2].domicilios.estado== 'Nayarit' ? ' selected="selected" ' : ''} value="Nayarit">Nayarit</option>
											<option ${listSesiones[2].domicilios.estado== 'Nuevo Leon' ? ' selected="selected" ' : ''} value="Nuevo Leon">Nuevo León</option>
											<option ${listSesiones[2].domicilios.estado== 'Oaxaca' ? ' selected="selected" ' : ''} value="Oaxaca">Oaxaca</option>
											<option ${listSesiones[2].domicilios.estado== 'Puebla' ? ' selected="selected" ' : ''} value="Puebla">Puebla</option>
											<option ${listSesiones[2].domicilios.estado== 'Queretaro' ? ' selected="selected" ' : ''} value="Queretaro">Querétaro</option>
											<option ${listSesiones[2].domicilios.estado== 'Quintana Roo' ? ' selected="selected" ' : ''} value="Quintana Roo">Quintana Roo</option>
											<option ${listSesiones[2].domicilios.estado== 'San Luis Potosi' ? ' selected="selected" ' : ''} value="San Luis Potosi">San Luís Potosí</option>
											<option ${listSesiones[2].domicilios.estado== 'Sinaloa' ? ' selected="selected" ' : ''} value="Sinaloa">Sinaloa</option>
											<option ${listSesiones[2].domicilios.estado=='Sonora' ? ' selected="selected" ' : ''} value="Sonora">Sonora</option>
											<option ${listSesiones[2].domicilios.estado== 'Tabasco' ? ' selected="selected" ' : ''} value="Tabasco">Tabasco</option>
											<option ${listSesiones[2].domicilios.estado== 'Tamaulipas' ? ' selected="selected" ' : ''} value="Tamaulipas">Tamaulipas</option>
											<option ${listSesiones[2].domicilios.estado== 'Tlaxcala' ? ' selected="selected" ' : ''} value="Tlaxcala">Tlaxcala</option>
											<option ${listSesiones[2].domicilios.estado== 'Veracruz' ? ' selected="selected" ' : ''} value="Veracruz">Veracruz</option>
											<option ${listSesiones[2].domicilios.estado== 'Yucatan' ? ' selected="selected" ' : ''} value="Yucatan">Yucatán</option>
											<option ${listSesiones[2].domicilios.estado== 'Zacatecas' ? ' selected="selected" ' : ''} value="Zacatecas">Zacatecas</option>
										</select>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* C.P.:" /></td>
									<td><s:textfield size="20" id="codigoPostal3" name="listSesiones[2].domicilios.codigoPostal" maxlength="5" onfocus="javascript:ayudasHelp(36);" onblur="javascript:ayudasHelpBlo(36);" onkeypress="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay36" style="display:none; margin-top:5px;" value="Escriba el Código postal en que se encuentra." /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br />
				<table>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="* Información importante:" /></td>
					</tr>
					<tr>
						<td><s:textarea cols="110" rows="9" id="idInfo3" name="listSesiones[2].info" value="%{listSesiones[2].info}" maxlength="1000" onfocus="javascript:ayudasHelp(37);" onblur="javascript:ayudasHelpBlo(37);" /></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay37" style="display:none;margin-top:-1px;" value="Ingrese la información importante" /></td>
					</tr>
				</table>
				<table width="99%; " class="submit_tabla">
					<tr>
						<s:if test="%{listSesiones[2].domicilios.idDomicilio==0}" >
							<s:hidden name="listSesiones[2].domicilios.idDomicilio" value="0" />
						</s:if>
						<s:else>
							<s:hidden name="listSesiones[2].domicilios.idDomicilio" value="%{listSesiones[2].domicilios.idDomicilio}" />
						</s:else>
						<s:hidden name="listSesiones[2].idDiplomado" id="idDiplomado" value="%{idDiplomado}" />
						<td align="center" style="width: 33%;">
							<input class="botonenviar" value="Anterior sesión" type="button" onclick="javascript:anterior();" />
						</td>
						<td align="center" style="width: 33%;">
							<input class="botonenviar" value="Siguiente sesión" type="button" onclick="javascript:siguiente(3);" />
						</td>
						<td align="center" style="width: 33%;">
							<input class="botonenviar" value="Guardar" type="button" onclick="javascript:finalizar(3);" />
						</td>
					</tr>
				</table>
			</div>

			<div id="sesiont4" style="display: none;">
				<s:label cssClass="resultado" cssStyle="font-size: 20px;" value="Sesión 4" />
				<s:hidden name="listSesiones[3].idSesion" value="%{listSesiones[3].idSesion}" />
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Sede:" /></td>
									<td><s:textfield size="50" id="idExpositor4" name="listSesiones[3].expositor" maxlength="100" onfocus="javascript:ayudasHelp(38);" onblur="javascript:ayudasHelpBlo(38);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay38" style="display:none;margin-top:-1px;" value="Ingrese la sede de la sesión 4." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Sala:" /></td>
									<td><s:textfield size="40" id="idSala4" name="listSesiones[3].sala" maxlength="100" onfocus="javascript:ayudasHelp(39);" onblur="javascript:ayudasHelpBlo(39);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay39" style="display:none;margin-top:-1px;" value="Ingrese la sala de la sesión 4." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Fecha:" /></td>
									<td>
										<s:date name="listSesiones[3].fecha" id="fCert4" format="dd/MM/yyyy" /> <s:textfield class="calendario" id="ingreso4" name="listSesiones[3].fecha" value="%{fCert4}"size="10" maxlength="10" />
										<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador4" style="cursor: hand" />
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Horario:" /></td>
									<td>
										<select name="listSesiones[3].hora" style="width: 40px;" id="hora4">
											<option value="-1">--</option>
											<s:iterator value="(24).{ #this }" status="stat">
												<option <s:if test="listSesiones[3].hora == #stat.index ">selected="selected"</s:if> value="${stat.index}">${stat.index}</option>
											</s:iterator>
										</select> : 
										<select name="listSesiones[3].minuto" style="width: 40px;" id="minuto4">
											<option value="-1">--</option>
											<s:iterator value="(6).{ #this }" status="stat">
												<option <s:if test="listSesiones[3].minuto == #stat.index + '0' ">selected="selected"</s:if> value="${stat.index}0">${stat.index}0</option>
											</s:iterator>
										</select> hrs.  &nbsp;
										<select name="listSesiones[3].horaFin" style="width: 40px;" id="horaFin4">
											<option value="-1">--</option>
											<s:iterator value="(24).{ #this }" status="stat">
												<option <s:if test="listSesiones[3].horaFin == #stat.index ">selected="selected"</s:if> value="${stat.index}">${stat.index}</option>
											</s:iterator>
										</select> : 
										<select name="listSesiones[3].minutoFin" style="width: 40px;" id="minutoFin4">
											<option value="-1">--</option>
											<s:iterator value="(6).{ #this }" status="stat">
												<option <s:if test="listSesiones[3].minutoFin == #stat.index + '0' ">selected="selected"</s:if> value="${stat.index}0">${stat.index}0</option>
											</s:iterator>
										</select> hrs.
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Instructor:" /></td>
									<td><s:textfield size="50" id="idInstuctor4" name="listSesiones[3].instructor" maxlength="80" onfocus="javascript:ayudasHelp(40);" onblur="javascript:ayudasHelpBlo(40);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay40" style="display:none;margin-top:-1px;" value="Ingrese el instructor de la sesión 4." /></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaCaptura" value="Dirección:" /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Calle:" /></td>
									<td><s:textfield size="30" id="calle4" name="listSesiones[3].domicilios.calle" maxlength="50" onfocus="javascript:ayudasHelp(41);" onblur="javascript:ayudasHelpBlo(41);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay41" style="display:none; margin-top:5px;" value="Escriba la calle de su empresa." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Número Exterior:" /></td>
									<td><s:textfield size="20" id="numExt4" name="listSesiones[3].domicilios.numExt" maxlength="20" onfocus="javascript:ayudasHelp(42);" onblur="javascript:ayudasHelpBlo(42);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay42" style="display:none; margin-top:5px;" value="Escriba el número exterior." /></td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Número Interior:" /></td>
									<td><s:textfield size="20" id="numInt4" name="listSesiones[3].domicilios.numInt" maxlength="20" onfocus="javascript:ayudasHelp(43);" onblur="javascript:ayudasHelpBlo(43);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay43" style="display:none; margin-top:5px;" value="Escriba el número interior." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Piso:" /></td>
									<td><s:textfield size="30" id="piso4" name="listSesiones[3].domicilios.piso" maxlength="20" onfocus="javascript:ayudasHelp(44);" onblur="javascript:ayudasHelpBlo(44);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay44" style="display:none; margin-top:5px;" value="Escriba el piso." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Colonia:" /></td>
									<td><s:textfield size="30" id="colonia4" name="listSesiones[3].domicilios.colonia" maxlength="50" onfocus="javascript:ayudasHelp(45);" onblur="javascript:ayudasHelpBlo(45);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay45" style="display:none; margin-top:5px;" value="Escriba la colonia." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Delegación/Municipio:" /></td>
									<td><s:textfield size="30" id="delegacion4" name="listSesiones[3].domicilios.delegacion" maxlength="50" onfocus="javascript:ayudasHelp(46);" onblur="javascript:ayudasHelpBlo(46);"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay46" style="display:none; margin-top:5px;" value="Escriba Delegación o Municipio." /></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay47" style="display:none; margin-top:5px;" value="Seleccione el Estado ." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Estado:" /></td>
									<td>
										<select id="estado4" name="listSesiones[3].domicilios.estado" style="width: 230px;" onfocus="javascript:ayudasHelp(47);" onblur="javascript:ayudasHelpBlo(47);">
											<option value="0">--Seleccione un estado--</option>
											<option ${listSesiones[2].domicilios.estado== 'Aguascalientes' ? ' selected="selected" ' : ''} value="Aguascalientes">Aguascalientes</option>
											<option ${listSesiones[2].domicilios.estado== 'Baja California Norte' ? ' selected="selected" ' : ''} value="Baja California Norte">Baja California Norte</option>
											<option ${listSesiones[2].domicilios.estado== 'Baja California Sur' ? ' selected="selected" ' : ''} value="Baja California Sur">Baja California Sur</option>
											<option ${listSesiones[2].domicilios.estado== 'Campeche' ? ' selected="selected" ' : ''} value="Campeche">Campeche</option>
											<option ${listSesiones[2].domicilios.estado== 'Chiapas' ? ' selected="selected" ' : ''} value="Chiapas">Chiapas</option>
											<option ${listSesiones[2].domicilios.estado== 'Chihuahua' ? ' selected="selected" ' : ''} value="Chihuahua">Chihuahua</option>
											<option ${listSesiones[2].domicilios.estado== 'Coahuila' ? ' selected="selected" ' : ''} value="Coahuila">Coahuila</option>
											<option ${listSesiones[2].domicilios.estado== 'Colima' ? ' selected="selected" ' : ''} value="Colima">Colima</option>
											<option ${listSesiones[2].domicilios.estado== 'Distrito Federal' ? ' selected="selected" ' : ''} value="Distrito Federal">Distrito Federal</option>
											<option ${listSesiones[2].domicilios.estado== 'Durango' ? ' selected="selected" ' : ''} value="Durango">Durango</option>
											<option ${listSesiones[2].domicilios.estado == 'Estado de Mexico' ? ' selected="selected" ' : ''} value="Estado de Mexico">Estado de México</option>
											<option ${listSesiones[2].domicilios.estado== 'Guanajuato' ? ' selected="selected" ' : ''} value="Guanajuato">Guanajuato</option>
											<option ${listSesiones[2].domicilios.estado== 'Guerrero' ? ' selected="selected" ' : ''} value="Guerrero">Guerrero</option>
											<option ${listSesiones[2].domicilios.estado== 'Hidalgo' ? ' selected="selected" ' : ''} value="Hidalgo">Hidalgo</option>
											<option ${listSesiones[2].domicilios.estado== 'Jalisco' ? ' selected="selected" ' : ''} value="Jalisco">Jalisco</option>
											<option ${listSesiones[2].domicilios.estado== 'Michoacan' ? ' selected="selected" ' : ''} value="Michoacan">Michoacán</option>
											<option ${listSesiones[2].domicilios.estado== 'Morelos' ? ' selected="selected" ' : ''} value="Morelos">Morelos</option>
											<option ${listSesiones[2].domicilios.estado== 'Nayarit' ? ' selected="selected" ' : ''} value="Nayarit">Nayarit</option>
											<option ${listSesiones[2].domicilios.estado== 'Nuevo Leon' ? ' selected="selected" ' : ''} value="Nuevo Leon">Nuevo León</option>
											<option ${listSesiones[2].domicilios.estado== 'Oaxaca' ? ' selected="selected" ' : ''} value="Oaxaca">Oaxaca</option>
											<option ${listSesiones[2].domicilios.estado== 'Puebla' ? ' selected="selected" ' : ''} value="Puebla">Puebla</option>
											<option ${listSesiones[2].domicilios.estado== 'Queretaro' ? ' selected="selected" ' : ''} value="Queretaro">Querétaro</option>
											<option ${listSesiones[2].domicilios.estado== 'Quintana Roo' ? ' selected="selected" ' : ''} value="Quintana Roo">Quintana Roo</option>
											<option ${listSesiones[2].domicilios.estado== 'San Luis Potosi' ? ' selected="selected" ' : ''} value="San Luis Potosi">San Luís Potosí</option>
											<option ${listSesiones[2].domicilios.estado== 'Sinaloa' ? ' selected="selected" ' : ''} value="Sinaloa">Sinaloa</option>
											<option ${listSesiones[2].domicilios.estado=='Sonora' ? ' selected="selected" ' : ''} value="Sonora">Sonora</option>
											<option ${listSesiones[2].domicilios.estado== 'Tabasco' ? ' selected="selected" ' : ''} value="Tabasco">Tabasco</option>
											<option ${listSesiones[2].domicilios.estado== 'Tamaulipas' ? ' selected="selected" ' : ''} value="Tamaulipas">Tamaulipas</option>
											<option ${listSesiones[2].domicilios.estado== 'Tlaxcala' ? ' selected="selected" ' : ''} value="Tlaxcala">Tlaxcala</option>
											<option ${listSesiones[2].domicilios.estado== 'Veracruz' ? ' selected="selected" ' : ''} value="Veracruz">Veracruz</option>
											<option ${listSesiones[2].domicilios.estado== 'Yucatan' ? ' selected="selected" ' : ''} value="Yucatan">Yucatán</option>
											<option ${listSesiones[2].domicilios.estado== 'Zacatecas' ? ' selected="selected" ' : ''} value="Zacatecas">Zacatecas</option>
										</select>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* C.P.:" /></td>
									<td><s:textfield size="20" id="codigoPostal4" name="listSesiones[3].domicilios.codigoPostal" maxlength="5" onfocus="javascript:ayudasHelp(48);" onblur="javascript:ayudasHelpBlo(48);" onkeypress="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay48" style="display:none; margin-top:5px;" value="Escriba el Código postal en que se encuentra." /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br />
				<table>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="* Información importante:" /></td>
					</tr>
						<tr><td><s:textarea cols="110" rows="9" id="idInfo4" name="listSesiones[3].info" value="%{listSesiones[3].info}" maxlength="1000" onfocus="javascript:ayudasHelp(49);" onblur="javascript:ayudasHelpBlo(49);" /></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay49" style="display:none;margin-top:-1px;" value="Ingrese la información importante" /></td>
					</tr>
				</table>
				<table width="99%; " class="submit_tabla">
					<tr>
						<s:hidden name="listSesiones[3].idDiplomado" id="idDiplomado" value="%{idDiplomado}" />
						<s:if test="%{listSesiones[3].domicilios.idDomicilio==0}" >
							<s:hidden name="listSesiones[3].domicilios.idDomicilio" value="0" />
						</s:if>
						<s:else>
							<s:hidden name="listSesiones[3].domicilios.idDomicilio" value="%{listSesiones[3].domicilios.idDomicilio}" />
						</s:else>
						<td align="center" style="width: 50%;"><input class="botonenviar" value="Anterior sesión" type="button" onclick="javascript:anterior(4);" /></td>
						<td align="center" style="width: 50%;"><input class="botonenviar" value="Guardar" type="button" onclick="javascript:finalizar(4);" /></td>
					</tr>
				</table>
			</div>
		</s:form>
		
		<s:form name="deleteDip" action="diplomadosShow" namespace="/ccmx/administracion/diplomados" theme="simple">
			<s:hidden id="idDeleteDiplomado" name="idDiplomado" value="%{idDiplomado}" />
			<s:hidden name="opcion" value="deleteDiplomado" />
		</s:form>
		<script type="text/javascript">
			Calendar.setup({
				inputField : "ingreso1", // id del campo de texto
				ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
				// campo de texto
				button : "lanzador1" // el id del botón que lanzará el calendario
			});
			Calendar.setup({
				inputField : "ingreso2", // id del campo de texto
				ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
				// campo de texto
				button : "lanzador2" // el id del botón que lanzará el calendario
			});
			Calendar.setup({
				inputField : "ingreso3", // id del campo de texto
				ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
				// campo de texto
				button : "lanzador3" // el id del botón que lanzará el calendario
			});
			Calendar.setup({
				inputField : "ingreso4", // id del campo de texto
				ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
				// campo de texto
				button : "lanzador4" // el id del botón que lanzará el calendario
			});
		</script>
	</s:else>
	
</fieldset>
</body>
</html>