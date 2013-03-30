<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="${pageContext.request.contextPath}/js/tractoras.js" type="text/javascript"></script>
</head>
<body>
	<fieldset id="requerimientos">
		<br />
		<s:form action="tractoraInformacionAdd" namespace="/tractora/administracion"
			theme="simple" onsubmit="return validaDatosTractora('2')">
		<s:hidden name="domicilios.idDomicilio" id="idDomicilio" value="%{domicilios.idDomicilio}" />
		<div id="sec1" ${tractoras.puesto==null?
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
										maxlength="100"></s:textfield></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba el nombre de su empresa." /></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Nombre(s):" /> <s:textfield size="43" id="idNombre"
										name="tractoras.nombreContacto" maxlength="60"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba su Nombre(s) sin incluir acentos." /></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Apellido Paterno:" /> <s:textfield size="36"
										id="idAppPaterno" name="tractoras.appPaterno" maxlength="60"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba su Apellido Paterno sin incluir acentos." /></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Apellido Materno:" /> <s:textfield size="35"
										id="idAppMaterno" name="tractoras.appMaterno" maxlength="60"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba su Apellido Materno sin incluir acentos." /></td>
							</tr>
						</table>
					</td>
					<td style="width: 470px;">
						<table>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Correo Electrónico:" /> <s:textfield size="40"
										id="idCorreoElectronico" name="tractoras.correoElectronico"
										maxlength="100"></s:textfield></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba su correo electrónico." /></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Confirmar Correo Electrónico:" /> <s:textfield
										size="28" id="idComparaCorreo" maxlength="100"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Confirme su correo electrónico." /></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Puesto:" />
									<s:textfield size="53" id="idPuesto" name="tractoras.puesto" maxlength="100"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba el título exacto de su puesto en la empresa." />
								</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Teléfono:" />
								<s:textfield size="30" id="idTelefono" onchange="document.getElementById('idTelefono').style.background = '#FFFFFF';" maxlength="30"></s:textfield>
								&nbsp;&nbsp;<label class="agregar" onclick="agregaTelefono();">+agregar</label></td>
							</tr>
						</table>
						<table>
							<tr>
								<td>
									<s:label cssClass="etiquetaAyuda" value="Incluya su telefono con clave lada y Extensión." /><br />
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
						onclick="javascript:return validaDatosTractora('1');" /></td>
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
									<s:textfield size="45" id="idCalle" name="domicilios.calle" maxlength="50"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba la calle de su empresa." /></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Número exterior:" /> <s:textfield size="20"
										id="idNumExt" name="domicilios.numExt" maxlength="20"></s:textfield></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba el número exterior de su empresa." /></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="  Número Interior:" /> <s:textfield size="22"
										id="idNumInt" name="domicilios.numInt" maxlength="20"></s:textfield></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba el número interior de su empresa." /></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="  Piso:" />
									<s:textfield size="47" id="idPiso" name="domicilios.piso" maxlength="20"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba el piso en que se encuentra." /></td>
							</tr>
						</table>
					</td>
					<td style="width: 470px;">
						<table>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Colonia:" />
									<s:textfield size="42" id="idColonia" name="domicilios.colonia" maxlength="50"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba la colonia en la que se encuentra." /></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Delegación/Municipio:" /> <s:textfield size="25"
										id="idDelegacion" name="domicilios.delegacion" maxlength="50"></s:textfield></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba la delegacion o el municipio en el que se encuentra." />
								</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Estado:" />
									<select id="idEstado" name="domicilios.estado">
										<s:if test="%{domicilios.estado} == null">
											<option selected="selected" value="Seleccione un estado">Seleccione un estado</option>
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
								<td><s:label cssClass="etiquetaAyuda"
										value="Seleccione la Entidad Federativa." /></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Código Postal:" /> <s:textfield value="%{domicilios.codigoPostal}" size="5"
										id="idCodigoPostal" name="domicilios.codigoPostal" maxlength="5"></s:textfield></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba su código postal." /></td>
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
			${tractoras.puesto==null?
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
						<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="left">&nbsp;Teléfono:</td>
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