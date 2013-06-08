<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="${pageContext.request.contextPath}/js/tractoras.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/ayudas.js" type="text/javascript"></script>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-330px auto 0 250px';
</script>
</head>
<body>
	<fieldset id="requerimientos">
		<s:form action="tractoraInformacionAdd" namespace="/tractora/administracion"
			theme="simple" onsubmit="return validaDatosTractora('2', false)">
		<s:hidden name="domicilios.idDomicilio" id="idDomicilio" value="%{domicilios.idDomicilio}" />
		<div id="sec1" ${tractoras.puesto==null||tractoras.puesto==' '?
		' style="display: block;" '
		:' style="display: none;"' }>
		<legend>
			<s:label value="Actualizar datos" />
			<br /> <br />
			<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<br />
			<table>
				<tr>
					<td style="width: 470px;">
						<table>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Empresa:" />
									<s:textfield size="46" id="idEmpresa" name="tractoras.empresa"
										maxlength="100" onfocus="javascript:ayudasHelp(0);" onblur="javascript:ayudasHelpBlo(0);"></s:textfield></td>
							</tr>
							<tr>
								<td>
								<div id="ayudasDisplay0" style="display: none">
									<s:label cssClass="etiquetaAyuda"
										value="Escriba el nombre de su empresa." />
								</div>
								</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Nombre(s):" /> <s:textfield size="43" id="idNombre"
										name="tractoras.nombreContacto" maxlength="60"
										 onfocus="javascript:ayudasHelp(1);" onblur="javascript:ayudasHelpBlo(1);"></s:textfield>
								</td>
							</tr>
							<tr>
								<td>
								<div id="ayudasDisplay1" style="display: none">
									<s:label cssClass="etiquetaAyuda"
										value="Escriba su Nombre(s) sin incluir acentos." />
								</div>
								</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Apellido Paterno:" /> <s:textfield size="36"
										id="idAppPaterno" name="tractoras.appPaterno" maxlength="60"
										onfocus="javascript:ayudasHelp(2);" onblur="javascript:ayudasHelpBlo(2);"></s:textfield>
								</td>
							</tr>
							<tr>
								<td>
								<div id="ayudasDisplay2" style="display: none">
									<s:label cssClass="etiquetaAyuda"
										value="Escriba su Apellido Paterno sin incluir acentos." />
								</div>
								</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Apellido Materno:" /> <s:textfield size="35"
										id="idAppMaterno" name="tractoras.appMaterno" maxlength="60"
										onfocus="javascript:ayudasHelp(3);" onblur="javascript:ayudasHelpBlo(3);"></s:textfield>
								</td>
							</tr>
							<tr>
								<td>
								<div id="ayudasDisplay3" style="display: none">
									<s:label cssClass="etiquetaAyuda"
										value="Escriba su Apellido Materno sin incluir acentos." />
								</div></td>
							</tr>
						</table>
					</td>
					<td style="width: 470px;">
						<table>
							<tr>
								<td>
									<s:label cssClass="etiquetaCaptura" value=" Correo Electrónico:" />
									<s:textarea id="idCorreoElectronico" rows="1" cols="40" disabled="true" cssClass="resultado" style="resize: none;" 
										value="%{tractoras.correoElectronico}" onfocus="javascript:ayudasHelp(4);" onblur="javascript:ayudasHelpBlo(4);"/>
									<s:hidden name="tractoras.correoElectronico" id="correoElectronico" value="%{tractoras.correoElectronico}" />
								</td>
							</tr>
							<tr>
								<td>
								<div id="ayudasDisplay4" style="display: none">
									<s:label cssClass="etiquetaAyuda"
										value="Escriba su correo electrónico." />
								</div></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Puesto:" />
									<s:textfield size="53" id="idPuesto" name="tractoras.puesto" maxlength="100"
									onfocus="javascript:ayudasHelp(6);" onblur="javascript:ayudasHelpBlo(6);"></s:textfield>
								</td>
							</tr>
							<tr>
								<td>
								<div id="ayudasDisplay6" style="display: none">
									<s:label cssClass="etiquetaAyuda"
										value="Escriba el título exacto de su puesto en la empresa." />
								</div>
								</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Teléfono:" />
								<s:textfield size="30" id="idTelefono" onkeypress="return tel(this, event);" maxlength="24"
								onfocus="javascript:ayudasHelp(7);" onblur="javascript:ayudasHelpBlo(7);"></s:textfield>
								&nbsp;&nbsp;<label class="agregar" onclick="agregaTelefono();">+agregar</label></td>
							</tr>
						</table>
						<table>
							<tr>
								<td>
									<div id="ayudasDisplay7" style="display: none">
										<s:label cssClass="etiquetaAyuda" value="Incluya su teléfono con clave lada y extensión." />
									</div><br />
								</td>
							</tr>
						</table>
						<s:iterator status="stat" value="(10).{ #this }" >
						   <div id="idDivTel${stat.count}" ${!(tractoras.telefonos[stat.index]==null)?' style="display: block;"':' style="display: none;"'}>
								<s:hidden id="idTelHid%{#stat.count}" name="tractoras.telefonos[%{#stat.index}].telefono" value="%{tractoras.telefonos[#stat.index].telefono}" />
								<s:label id="labTel%{#stat.count}" cssClass="etiquetaCaptura" value="%{tractoras.telefonos[#stat.index].telefono}" /><label class="quitar" onclick="quitarTelefono(${stat.count});">-quitar</label>
							</div>
						</s:iterator>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
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
						onclick="javascript:return validaDatosTractora('1', false);" /></td>
					<td style="width: 250px;"></td>
				</tr>
			</table>
		</div>
		<div id="sec2" style="display: none;">
		<legend>
			<s:label value="Actualizar datos" />
			<br /> <br />
			<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<br />
			<table>
				<tr>
					<td style="width: 470px;">
						<table>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Calle:" />
									<s:textfield size="45" id="idCalle" name="domicilios.calle" maxlength="50"
									onfocus="javascript:ayudasHelp(8);" onblur="javascript:ayudasHelpBlo(8);"></s:textfield>
								</td>
							</tr>
							<tr>
								<td>
								<div id="ayudasDisplay8" style="display: none">
									<s:label cssClass="etiquetaAyuda"
										value="Escriba la calle de su empresa." />
								</div></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Número exterior:" /> <s:textfield size="20"
										id="idNumExt" name="domicilios.numExt" maxlength="20"
										onfocus="javascript:ayudasHelp(9);" onblur="javascript:ayudasHelpBlo(9);"></s:textfield></td>
							</tr>
							<tr>
								<td><div id="ayudasDisplay9" style="display: none">
									<s:label cssClass="etiquetaAyuda"
										value="Escriba el número exterior de su empresa." />
								</div></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="  Número Interior:" /> <s:textfield size="22"
										id="idNumInt" name="domicilios.numInt" maxlength="20"
										onfocus="javascript:ayudasHelp(10);" onblur="javascript:ayudasHelpBlo(10);"></s:textfield></td>
							</tr>
							<tr>
								<td><div id="ayudasDisplay10" style="display: none">
									<s:label cssClass="etiquetaAyuda"
										value="Escriba el número interior de su empresa." />
								</div></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="  Piso:" />
									<s:textfield size="47" id="idPiso" name="domicilios.piso" maxlength="20"
									onfocus="javascript:ayudasHelp(11);" onblur="javascript:ayudasHelpBlo(11);"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><div id="ayudasDisplay11" style="display: none">
									<s:label cssClass="etiquetaAyuda"
										value="Escriba el piso en que se encuentra." />
								</div></td>
							</tr>
						</table>
					</td>
					<td style="width: 470px;">
						<table>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Colonia:" />
									<s:textfield size="42" id="idColonia" name="domicilios.colonia" maxlength="50"
									onfocus="javascript:ayudasHelp(12);" onblur="javascript:ayudasHelpBlo(12);"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><div id="ayudasDisplay12" style="display: none">
									<s:label cssClass="etiquetaAyuda"
										value="Escriba la colonia en la que se encuentra." />
								</div></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Delegación/Municipio:" /> <s:textfield size="25"
										id="idDelegacion" name="domicilios.delegacion" maxlength="50"
										onfocus="javascript:ayudasHelp(13);" onblur="javascript:ayudasHelpBlo(13);"></s:textfield></td>
							</tr>
							<tr>
								<td><div id="ayudasDisplay13" style="display: none">
									<s:label cssClass="etiquetaAyuda"
										value="Escriba la delegacion o el municipio en el que se encuentra." />
								</div>
								</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Estado:" />
									<select id="idEstado" name="domicilios.estado" style="width: 200px;" 
										onfocus="javascript:ayudasHelp(14);" onblur="javascript:ayudasHelpBlo(14);">
										<s:if test="%{domicilios.estado} == null">
											<option selected="selected" value="Seleccione un estado">--Seleccione un estado--</option>
										</s:if>
										<s:else>
											<option value=""></option>
											<option selected="selected"><s:text name="%{domicilios.estado}" /></option>
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
								</select></td>
							</tr>
							<tr>
								<td><div id="ayudasDisplay14" style="display: none">
									<s:label cssClass="etiquetaAyuda"
										value="Seleccione la Entidad Federativa." />
								</div></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Código Postal:" /> <s:textfield value="%{domicilios.codigoPostal}" size="5"
										id="idCodigoPostal" name="domicilios.codigoPostal" maxlength="5"
										onfocus="javascript:ayudasHelp(15);" onblur="javascript:ayudasHelpBlo(15);"></s:textfield></td>
							</tr>
							<tr>
								<td><div id="ayudasDisplay15" style="display: none">
									<s:label cssClass="etiquetaAyuda"
										value="Escriba su código postal." />
									</div></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
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
		<div id="secR"
			${tractoras.puesto==null||tractoras.puesto==' '?
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
						align="center">Expediente Electrónico de la Tractora</td>
				</tr>
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Nombre de la Empresa:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${tractoras.empresa}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Nombre del Contacto:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${tractoras.nombreContacto}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Apellido Paterno del Contacto:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${tractoras.appPaterno}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Apellido Materno del Contacto:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${tractoras.appMaterno}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Correo Electrónico:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${tractoras.correoElectronico}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Puesto:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${tractoras.puesto}</s:label></td>
				</tr>
				<s:iterator value="tractoras.telefonos" status="stat">
					<tr>
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="left">&nbsp;Teléfono ${stat.count}:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${telefono}</s:label></td>
					</tr>
				</s:iterator>
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Calle:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${domicilios.calle}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Numero Exterior:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${domicilios.numExt}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Numero Interior:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${domicilios.numInt}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Piso:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${domicilios.piso}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Colonia:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${domicilios.colonia}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Delegación o Municipio:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${domicilios.delegacion}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estado:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${domicilios.estado}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Código Postal:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${domicilios.codigoPostal}</s:label></td>
				</tr>
			</table>
			<table class="submit_tabla">
				<tr>
					<td style="width: 450px;"></td>
					<td><input
						class="botonenviar"
						value="Modificar"
						type="button"
						onclick="javascript:modificarDatos();" /></td>
					<td style="width: 450px;"></td>
				</tr>
			</table>
		</div>
		<s:form
			name="frmCancela"
			action="tractoraInformacionShow"
			namespace="/tractora/administracion"
			theme="simple"
			method="post">
		</s:form>
	</fieldset>
</body>
</html>