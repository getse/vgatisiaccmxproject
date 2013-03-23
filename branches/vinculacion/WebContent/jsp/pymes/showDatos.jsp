<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
			<s:label value="Actualiza datos PyME" />
			<br />
			<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<br />
		<s:if test="personalidadJuridica == null">
			<br />
			<legend>
				<s:label cssClass="etiquetaCaptura" value="Estimado empresario por favor, ingrese y valide la información que se solicita en el presente formulario sobre su negocio. Esta información será utilizada para que otras empresas puedan ver los productos o servicios que ofrece. Recuerde seguir las instrucciones al pie de la letra para que las grandes empresas puedan encontrar su información." />
			</legend>
			<br />
		</s:if>
		<br />
		<s:form action="pymeInformacionSave" namespace="/pymes"
			theme="simple" onsubmit="return validacion()">
			<table>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Seleccione Persona Moral o Persona Fisica" /></td>
					<td>
						<select id="personalidadJuridica" name="pyMes.personalidadJuridica">
							<s:if test="personalidadJuridica == null">
								<option selected="selected" value="Seleccione el tipo de persona">Seleccione el tipo de persona</option>
							</s:if>
							<s:else>
								<option value=""></option>
								<option selected="selected"><s:text name="%{pyMes.personalidadJuridica}" /> </option>
							</s:else>
							<option value="Persona Moral">Persona Moral</option>
							<option value="Persona Fisica">Persona Fisica</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="RFC:" /></td>
					<td><s:textfield size="60" id="idRfc" name="pyMes.rfc" maxlength="20"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba su RFC con homoclave." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Correo electrónico:" /></td>
					<td><s:textfield size="60" id="idCorreoElectronico" name="pyMes.correoElectronico" maxlength="100"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba su correo electrónico." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Confimación Correo electrónico:" /></td>
					<td><s:textfield size="60" id="idComparaCorreo" maxlength="100"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Confirme su correo electrónico." /></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				<!-- Inicia Información de la empresa -->
				<tr>
					<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Información de la empresa" /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="Nombre comercial:" /></td>
					<td><s:textfield size="60" id="idNombreComercial" name="pyMes.nombreComercial" maxlength="150"></s:textfield>
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="Nombre fiscal:" /></td>
					<td><s:textfield size="60" id="idNombreFiscal" name="pyMes.nombreFiscal" maxlength="100"></s:textfield>
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"	value="Numero de empleados:" /></td>
					<td><s:textfield size="60" id="idNumeroEmpleados" name="pyMes.numeroEmpleados" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Mensaje de venta:" /></td>
					<td><s:textfield size="60" id="idMensajeVenta" name="pyMes.mensajeVentas" maxlength="150"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Incluya el mensaje principal que desea que vea la empresa tractora." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Calle:" /></td>
					<td><s:textfield size="60" id="idCalle" name="domicilios.calle" maxlength="50"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba la calle de su empresa." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Número Exterior:" /></td>
					<td><s:textfield size="20" id="idNumExt" name="domicilios.numExt" maxlength="20"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba el número exterior de su empresa." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="Número Interior:" /></td>
					<td><s:textfield size="20" id="idNumInt" name="domicilios.numInt" maxlength="20"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba el número interior de su empresa." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="Piso:" /></td>
					<td><s:textfield size="20" id="idPiso" name="domicilios.piso" maxlength="20"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba el piso en que se encuentra." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Colonia:" /></td>
					<td><s:textfield size="60" id="idColonia" name="domicilios.colonia" maxlength="50"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba la colonia en que se encuentra." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Delegación/Municipio:" /></td>
					<td><s:textfield size="60" id="idDelegacion" name="domicilios.delegacion" maxlength="50"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba Delegacion o Municipio en que se encuentra." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Estado:" /></td>
					<td>
						<select id="estado" name="domicilios.estado">
							<s:if test="estado == null">
								<option selected="selected" value="Seleccione el estado">Seleccione un estado</option>
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
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Seleccione el Estado ." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* C.P.:" /></td>
					<td><s:textfield size="20" id="idCodigoPostal" name="domicilios.codigoPostal" maxlength="5"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba el Código postal en que se encuentra." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="Página web:" /></td>
					<td><s:textfield size="60" id="idPagWeb" name="pyMes.paginaWeb" maxlength="100"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba la dirección web completa." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Productos principales:" /></td>
					<td><s:textfield size="60" id="idProdPrincipales" name="pyMes.productosPrincipales" maxlength="500"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Incluya sus productos principales en 3 palabras, (hasta 5)." /></td>
				</tr>

				<!-- Boton para agregar más productos -->

				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="Ventas anuales:" /></td>
					<td>$<s:textfield size="60" id="idVentasAnuales" name="pyMes.ventasAnuales" maxlength="150"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td> 
					<td><s:label cssClass="etiquetaAyuda" value="Escriba sus ventas anuales en pesos (ultimo año)." /></td>
				</tr>
				<tr>
					<td colspan="2">
						<table>
							<tr>
								<td style="width: 230px;">
									<s:label cssClass="etiquetaCaptura" value="* Sector (primer nivel):" />
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
								</td>
								<td style="width: 230px;">
									<s:label cssClass="etiquetaCaptura" value="Sector (segundo nivel):" />
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />		
								</td>
								<td>
									<s:label cssClass="etiquetaCaptura" value="Sector (Tercer nivel):" />
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Selecione la categoría a la que pertenece su empresa." /></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<!-- Inician CheckBox Estados -->
				
				<tr>
					<td colspan="2"><s:label cssClass="etiquetaCaptura" value="* Estados donde puede vender sus productos:" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<table>
							<tr>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Aguascalientes:" />
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Distrito Federal:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Morelos:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Sinaloa:" />	
								</td>
							</tr>
							<tr>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Baja California:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Durango:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Nayarit:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Sonora:" />	
								</td>
							</tr>
							<tr>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Baja California Sur:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Guanajuato:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Nuevo León:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Tabasco:" />	
								</td>
							</tr>
							<tr>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Campeche:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Guerrero:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Oaxaca:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Tamaulipas:" />	
								</td>
							</tr>
							<tr>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Chiapas:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Hidalgo:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Puebla:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Tlaxcala:" />	
								</td>
							</tr>
							<tr>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Chihuahua:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Jalisco:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Quertaro:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Veracruz:" />	
								</td>
							</tr>
							<tr>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Coahuila:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="México:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Quintana Roo:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Yucatán:" />	
								</td>
							</tr>
							<tr>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Colima:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Michoacán:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="San Luis Potosi:" />	
								</td>
								<td style="width: 180px;">
									<s:checkbox id="checkcontado" name="pyMes.XXXXX" onclick="contado();" value="%{pyMes.VVVVVV}" />
									<s:label cssClass="etiquetaCaptura" value="Zacatecas:" />	
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Puede elegir uno o más estados." /></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<!-- Terminan CheckBox Estados -->
				
				
				<!-- Inicia Información del contacto -->
				<tr>
					<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Información del contacto." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Nombre(s) :" /></td>
					<td><s:textfield size="60" id="idNombreContacto" name="pyMes.nombreContacto" maxlength="60"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba el nombre o nombres del contacto sin considerar acentos." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Apellido Paterno :" /></td>
					<td><s:textfield size="60" id="idAppPat" name="pyMes.appPaterno" maxlength="60"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba el apellido paterno del contacto sin considerar acentos." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Apellido Materno :" /></td>
					<td><s:textfield size="60" id="idAppMat" name="pyMes.appMaterno" maxlength="60"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba el apellido materno del contacto sin considerar acentos." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Correo electrónico :" /></td>
					<td><s:textfield size="60" id="idCorreoElectronico" name="pyMes.correoElectronicoContacto" maxlength="100"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Incluya su correo electrónico." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Confimación Correo electrónico :" /></td>
					<td><s:textfield size="60" id="idComparaCorreo" maxlength="100"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Confirme su correo electrónico." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Teléfono :" /></td>
					<td><s:textfield size="60" id="idTel" name="pyMes.telefonoContacto" maxlength="20"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Incluya su telefono con clave lada y Extensión." /></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				
				<!-- Inicia Registro de Clientes -->
				<tr>
					<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Registro de Clientes." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Cliente :" /></td>
					<td><s:textfield size="60" id="idCliente" name="clientes.cliente" maxlength="100"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Ingrese el nombre del cliente." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Productos que compra el cliente :" /></td>
					<td><s:textfield size="60" id="idProdCliente" name="clientes.productosCompra" maxlength="500"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Describa los principales productos que le vende a la tractora." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Años como proveedor del cliente :" /></td>
					<td><s:textfield size="60" id="idAniosProveCliente" name="clientes.aniosProveedor" maxlength="50"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba los años que lleva como proveedor de la tractora." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Meses como proveedor :" /></td>
					<td><s:textfield size="60" id="idMesesProveCliente" name="clientes.mesesProveedor" maxlength="50"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" value="Escriba los meses que lleva como proveedor de la tractora." /></td>
				</tr>
				<!-- "Botón" Otro proveedor -->
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				
				<!-- Inicia Certificaciones y capacitación. -->
				<tr>
					<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Certificaciones y capacitación." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="Certificación :" /></td>
					<td><s:textfield size="60" id="idCertificación" name="certificaciones.certificacion" maxlength="150"></s:textfield></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="Año de certificación :" /></td>
					<td><s:textfield size="60" id="idAnioCertificación" name="certificaciones.fechaCertificacion" maxlength="100"></s:textfield></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="Institución que certificó :" /></td>
					<td><s:textfield size="60" id="idInstCert" name="pyMes.institutoCertificador" maxlength="100"></s:textfield></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="Diplomado ccmx/1:" /></td>
					<td><s:checkbox id="checkcontado" name="pyMes.bDiplomadoCcmxUno" onclick="contado();" value="%{pyMes.VVVVVV}" /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="Diplomado ccmx/2:" /></td>
					<td><s:checkbox id="checkcontado" name="pyMes.bDiplomadoCcmxDos" onclick="contado();" value="%{pyMes.VVVVVV}" /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="Diplomado ccmx/3:" /></td>
					<td><s:checkbox id="checkcontado" name="pyMes.bDiplomadoCcmxTres" onclick="contado();" value="%{pyMes.VVVVVV}" /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="Diplomado ccmx/4:" /></td>
					<td><s:checkbox id="checkcontado" name="pyMes.bDiplomadoCcmxCuatro" onclick="contado();" value="%{pyMes.VVVVVV}" /></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				
				<!-- Inicia Requerimentos de compra -->
				<tr>
					<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Requerimientos de compra." /></td>
				</tr>
				<tr>
					<td colspan="2"><s:label cssClass="etiquetaCaptura" value="* ¿Desea recibir requerimientos de compra?" /></td>
				</tr>
				<tr>
					<td>
						<s:label cssClass="etiquetaCaptura" value="Si" />
						<s:checkbox id="checkcontado" name="pyMes.bRecibeRequerimientosCompra" onclick="contado();" value="%{pyMes.VVVVVV}" />
					</td>
					<td>
						<s:label cssClass="etiquetaCaptura" value="No" />
						<s:checkbox id="checkcontado" name="pyMes.bRecibeRequerimientosCompra" onclick="contado();" value="%{pyMes.VVVVVV}" />
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">
						<table style="display:none;">
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="Catálogo SCIAN 1er Nivel" /></td>
								<td><s:checkbox id="checkcontado" name="pyMes.bRecibeRequerimientosCompra" onclick="contado();" value="%{pyMes.VVVVVV}" /></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura" value="Catálogo SCIAN 2do Nivel" /></td>
								<td><s:checkbox id="checkcontado" name="pyMes.bRecibeRequerimientosCompra" onclick="contado();" value="%{pyMes.VVVVVV}" /></td>
							</tr>
							<tr>
								<td><s:label cssClass="etiquetaCaptura"	value="Catálogo SCIAN 3er Nivel" /></td>
								<td><s:checkbox id="checkcontado" name="pyMes.bRecibeRequerimientosCompra" onclick="contado();" value="%{pyMes.VVVVVV}" /></td>
							</tr>
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2"><s:submit cssClass="botonenviar" value="Actualizar PyME" /></td>
				</tr>
			</table>
			<s:hidden name="domicilios.idDomicilio" id="idDomicilio" value="%{domicilios.idDomicilio}" />
			<s:hidden name="clientes.idCliente" id="idCliente" value="%{clientes.idCliente}" />
			<s:hidden name="certificaciones.idCertificado" id="idCertificado" value="%{certificaciones.idCertificado}" />
		</s:form>
	</fieldset>
	<script type="text/javascript">
		function validacion() {
			
			valorPerJuridica = document.getElementById("idPersonalidadJuridica").selectedIndex;
			valorCorreo = document.getElementById("idCorreoElectronico").value;
			valorCompara = document.getElementById("idComparaCorreo").value;
			valorMsjVenta = document.getElementById("idMensajeVenta").value;
			valorCalle = document.getElementById("idCalle").value;
			valorNumExt = document.getElementById("idNumExt").value;
			valorColonia = document.getElementById("idColonia").value;
			valorDelegacion = document.getElementById("idDelegacion").value;
			valorEstado = document.getElementById("idEstado").selectedIndex;
			valorCodigoPostal = document.getElementById("idCodigoPostal").value;
			valorProdPrincipales = document.getElementById("idProdPrincipales").value;
			
			
			if( valorPerJuridica == " " || valorPerJuridica == 0 || valorPerJuridica == null) {
				alert("Seleccione un Estado");
				return false;
			}else if( !(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(valorCorreo)) ) {
				alert("Ingrese una dirección de correo electrónico válida");
				return false;
			}else if (valorCorreo != valorCompara){
				alert("El correo electrónico no coincide");
				return false;
			}else if( valorMsjVenta == null || valorMsjVenta.length == 0 || /^\s+$/.test(valorMsjVenta) ) {
				alert("Ingrese Mensaje de ventas");  
				return false;
			}else if( valorCalle == null || valorCalle.length == 0 || /^\s+$/.test(valorCalle) ) {
				alert("Ingrese la calle");  
				return false;
			}else if( valorNumExt == null || valorNumExt.length == 0 || /^\s+$/.test(valorNumExt) ) {
				alert("Ingrese el Número exterior");  
				return false;
			}else if( valorColonia == null || valorColonia.length == 0 || /^\s+$/.test(valorColonia) ) {
				alert("Ingrese la colonia");
				return false;
			}else if( valorDelegacion == null || valorDelegacion.length == 0 || /^\s+$/.test(valorDelegacion) ) {
				alert("Ingrese la delegación");
				return false;
			}else if( valorEstado == " " || valorEstado == 0 || valorEstado == null) {
				alert("Seleccione un Estado");
				return false;
			}else if( valorCodigoPostal == null || valorCodigoPostal.length == 0 || /^\s+$/.test(valorCodigoPostal) ) {
				alert("Ingrese el Código Postal");
				return false;
			}else if( valorProdPrincipales == null || valorProdPrincipales.length == 0 || /^\s+$/.test(valorProdPrincipales) ) {
				alert("Ingrese el producto principal");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>
