<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="${pageContext.request.contextPath}/js/validaCampos.js" type="text/javascript"></script>
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
		<legend>
			Actualiza datos
			<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<s:form action="saveDat" 
			theme="simple" onsubmit="return validacion()">
			<table>
				<tr>
					<td>
						<table>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Empresa:" />
									<s:textfield size="50" id="idEmpresa" name="tractoras.empresa"
										maxlength="100"></s:textfield></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba el nombre de su empresa." /></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Nombre(s):" /> <s:textfield size="47" id="idNombre"
										name="tractoras.nombreContacto" maxlength="60"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba su Nombre(s) sin incluir acentos." /></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Apellido Paterno:" /> <s:textfield size="41"
										id="idAppPaterno" name="tractoras.appPaterno" maxlength="60"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba su Apellido Paterno sin incluir acentos." /></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Apellido Materno:" /> <s:textfield size="40"
										id="idAppMaterno" name="tractoras.appMaterno" maxlength="60"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba su Apellido Materno sin incluir acentos." /></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Correo Electrónico:" /> <s:textfield size="38"
										id="idCorreoElectronico" name="tractoras.correoElectronico"
										maxlength="100"></s:textfield></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba su correo electrónico." /></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Confimación Correo electrónico:" /> <s:textfield
										size="22" id="idComparaCorreo" maxlength="100"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Confirme su correo electrónico." /></td>
							</tr>
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
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Teléfono:" />
									<s:textfield size="25" id="idTelefono" name="tractoras.telefonos" maxlength="200"></s:textfield>
									<s:label cssClass="etiquetaCaptura" cssStyle="cursor: pointer; font-size: 12px;" value="Agregar otro Teléfono" onclick="" />
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Incluya su telefono con clave lada y Extensión." />
										
									<div id="oculto" style="display: none;">
										<s:label cssClass="etiquetaCaptura" value="Agregar otro Teléfono:" />
										<s:textfield size="25" id="idAddTelefono" name="" maxlength="200"></s:textfield>
										<br />
										<s:label cssClass="etiquetaAyuda" value="Incluya su telefono con clave lada y Extensión." />
									</div>
								</td>
							</tr>


						</table></td>
					<td>
						<table>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Calle:" />
									<s:textfield size="51" id="idCalle" name="domicilios.calle" maxlength="50"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba la calle de su empresa." /></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Número exterior:" /> <s:textfield size="36"
										id="idNumExt" name="domicilios.numExt" maxlength="20"></s:textfield></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba el número exterior de su empresa." /></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="  Número Interior:" /> <s:textfield size="39"
										id="idNumInt" name="domicilios.numInt" maxlength="20"></s:textfield></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba el número interior de su empresa." /></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="  Piso:" />
									<s:textfield size="54" id="idPiso" name="domicilios.piso" maxlength="20"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba el piso en que se encuentra." /></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="* Colonia:" />
									<s:textfield size="48" id="idColonia" name="domicilios.colonia" maxlength="50"></s:textfield>
								</td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba la colonia en la que se encuentra." /></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Delegación/Municipio:" /> <s:textfield size="31"
										id="idDelegacion" name="domicilios.delegacion" maxlength="50"></s:textfield></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba la delegacion o el municipio en el que se encuentra." />
								</td>
							</tr>
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
										<option value="Mexico">Mexico</option>
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
							<tr>
								<td><s:label cssClass="etiquetaCaptura"
										value="* Código Postal:" /> <s:textfield value="%{domicilios.codigoPostal}" size="40"
										id="idCodigoPostal" name="domicilios.codigoPostal" maxlength="5"></s:textfield></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaAyuda"
										value="Escriba su código postal." /></td>
							</tr>
						</table></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><s:submit cssClass="botonenviar"
							value="Actualizar" /></td>
				</tr>
			</table>
			<s:hidden name="domicilios.idDomicilio" id="idDomicilio" value="%{domicilios.idDomicilio}" />
		</s:form>
	</fieldset>
</body>
</html>