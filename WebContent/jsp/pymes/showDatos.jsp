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
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-195px auto 0 250px';
</script>
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
			<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<br />
		<s:if test="%{personalidadJuridica} == null">
			<br />
			<legend>
				<s:label cssClass="etiquetaCaptura" value="Estimado empresario por favor, ingrese y valide la información que se solicita en el presente formulario sobre su negocio. Esta información será utilizada para que otras empresas puedan ver los productos o servicios que ofrece. Recuerde seguir las instrucciones al pie de la letra para que las grandes empresas puedan encontrar su información." />
			</legend>
			<br />
		</s:if>

		<s:form action="pymeInformacionSave" namespace="/pymes" theme="simple" onsubmit="return validacion('5')">
		
		<!-- Inicia Seccion 1 -->
			<div id="sec1">
				<table>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="* Seleccione Persona Moral o Persona Fisica" /></td>
						<td>
							<select id="personalidadJuridica" name="pyMes.personalidadJuridica">
								<s:if test="%{personalidadJuridica} == null">
									<option selected="selected" value="Seleccione el tipo de persona">Seleccione el tipo de persona</option>
								</s:if>
								<s:else>
									<option value=""></option>
									<option selected="selected" value="${pyMes.personalidadJuridica}"><s:text name="%{pyMes.personalidadJuridica}" /> </option>
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
						<td><s:textfield size="60" id="correoElectronico" name="pyMes.correoElectronico" maxlength="100"></s:textfield></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><s:label cssClass="etiquetaAyuda" value="Escriba su correo electrónico." /></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="* Confimación Correo electrónico:" /></td>
						<td><s:textfield size="60" id="comparaCorreo" maxlength="100"></s:textfield></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><s:label cssClass="etiquetaAyuda" value="Confirme su correo electrónico." /></td>
					</tr>
				</table>
				
			<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td><input class="botonenviar" value="Siguiente" type="button" onclick="javascript:return validacion('1');" /></td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
		<!-- Termina Seccion 1 -->
		
		
		<!-- Inicia Seccion 2 -->
			<div id="sec2" style="display: none;">
				<!-- Inicia Información de la empresa -->
				<table>
					<tr>
						<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Información de la empresa" /></td>
					</tr>
				</table>
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Nombre comercial:" /></td>
									<td><s:textfield size="30" id="nombreComercial" name="pyMes.nombreComercial" maxlength="150"></s:textfield>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Nombre fiscal:" /></td>
									<td><s:textfield size="30" id="nombreFiscal" name="pyMes.nombreFiscal" maxlength="100"></s:textfield>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura"	value="Numero de empleados:" /></td>
									<td><s:textfield size="30" id="numeroEmpleados" name="pyMes.numeroEmpleados" maxlength="250"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Mensaje de venta:" /></td>
									<td><s:textfield size="30" id="mensajeVenta" name="pyMes.mensajeVentas" maxlength="150"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Incluya el mensaje principal que desea que vea la empresa tractora." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Calle:" /></td>
									<td><s:textfield size="30" id="calle" name="domicilios.calle" maxlength="50"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba la calle de su empresa." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Número Exterior:" /></td>
									<td><s:textfield size="20" id="numExt" name="domicilios.numExt" maxlength="20"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba el número exterior de su empresa." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Número Interior:" /></td>
									<td><s:textfield size="20" id="numInt" name="domicilios.numInt" maxlength="20"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba el número interior de su empresa." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Piso:" /></td>
									<td><s:textfield size="20" id="piso" name="domicilios.piso" maxlength="20"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba el piso en que se encuentra." /></td>
								</tr>
							</table>
						</td>
					<!-- Segunda Columna -->
						<td>
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Colonia:" /></td>
									<td><s:textfield size="30" id="colonia" name="domicilios.colonia" maxlength="50"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba la colonia en que se encuentra." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Delegación/Municipio:" /></td>
									<td><s:textfield size="30" id="delegacion" name="domicilios.delegacion" maxlength="50"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba Delegacion o Municipio en que se encuentra." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Estado:" /></td>
									<td>
										<select id="estado" name="domicilios.estado">
											<s:if test="%{domicilios.estado} == null">
												<option selected="selected" value="Seleccione el estado">Seleccione un estado</option>
											</s:if>
											<s:else>
												<option value=""></option>
												<option selected="selected" value="${domicilios.estado}"><s:text name="%{domicilios.estado}" /></option>
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
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Seleccione el Estado ." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* C.P.:" /></td>
									<td><s:textfield size="20" id="codigoPostal" name="domicilios.codigoPostal" maxlength="5"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba el Código postal en que se encuentra." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Página web:" /></td>
									<td><s:textfield size="30" id="pagWeb" name="pyMes.paginaWeb" maxlength="100"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba la dirección web completa." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Productos principales:" /></td>
									<td><s:textfield size="30" id="prodPrincipales" name="pyMes.productosPrincipales" maxlength="500"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Incluya sus productos principales en 3 palabras, (hasta 5)." /></td>
								</tr>
				
								<!-- Boton para agregar más productos -->
				
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Ventas anuales:" /></td>
									<td>$<s:textfield size="30" id="ventasAnuales" name="pyMes.ventasAnuales" maxlength="150"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba sus ventas anuales en pesos (ultimo año)." /></td>
								</tr>								
							</table>
						</td>
					</tr>
				</table>
				<table id="">
					<tr>
						<td style="width: 250px; padding-left: 50px;">
							<s:label cssClass="etiquetaCaptura" value="* Sector (primer nivel):" />
							<s:checkbox id="sector1" name="pyMes.uno" onclick="checkSector();" value="%{pyMes.VVVVVV}" />
						</td>
						<td style="width: 250px;">
							<s:label cssClass="etiquetaCaptura" value="Sector (segundo nivel):" />
							<s:checkbox id="sector2" name="pyMes.dos" onclick="checkSector();" value="%{pyMes.VVVVVV}" />		
						</td>
						<td style="width: 250px;">
							<s:label cssClass="etiquetaCaptura" value="Sector (Tercer nivel):" />
							<s:checkbox id="sector3" name="pyMes.tres" onclick="checkSector();" value="%{pyMes.VVVVVV}" />
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center"><s:label cssClass="etiquetaAyuda" value="Selecione la categoría a la que pertenece su empresa." /></td>
					</tr>
				</table>

			<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td><input class="botonenviar" value="Anterior" type="button" onclick="javascript:document.getElementById('sec2').style.display='none'; javascript:document.getElementById('sec1').style.display='block';" /></td>
						<td><input class="botonenviar" value="Siguiente" type="button" onclick="javascript:return validacion('2');" /></td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
		<!-- Termina Seccion 2 -->
		
		
		<!-- Inicia Seccion 3 -->
			<div id="sec3" style="display: none;">
				<table>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="* Estados donde puede vender sus productos:" /></td>
					</tr>
					<tr>
						<td>
							<table id="contCheckEstados">
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes" value="Aguascalientes" />
										<s:label cssClass="etiquetaCaptura" value="Aguascalientes:" />
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Distrito Federal" />
										<s:label cssClass="etiquetaCaptura" value="Distrito Federal:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Morelos" />
										<s:label cssClass="etiquetaCaptura" value="Morelos:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Sinaloa" />
										<s:label cssClass="etiquetaCaptura" value="Sinaloa:" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Baja California" />
										<s:label cssClass="etiquetaCaptura" value="Baja California:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Durango" />
										<s:label cssClass="etiquetaCaptura" value="Durango:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Nayarit" />
										<s:label cssClass="etiquetaCaptura" value="Nayarit:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Sonora" />
										<s:label cssClass="etiquetaCaptura" value="Sonora:" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Baja California Sur" />
										<s:label cssClass="etiquetaCaptura" value="Baja California Sur:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Guanajuato" />
										<s:label cssClass="etiquetaCaptura" value="Guanajuato:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Nuevo León" />
										<s:label cssClass="etiquetaCaptura" value="Nuevo León:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Tabasco" />
										<s:label cssClass="etiquetaCaptura" value="Tabasco:" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Campeche" />
										<s:label cssClass="etiquetaCaptura" value="Campeche:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Guerrero" />
										<s:label cssClass="etiquetaCaptura" value="Guerrero:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Oaxaca" />
										<s:label cssClass="etiquetaCaptura" value="Oaxaca:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Tamaulipas" />
										<s:label cssClass="etiquetaCaptura" value="Tamaulipas:" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Chiapas" />
										<s:label cssClass="etiquetaCaptura" value="Chiapas:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Hidalgo" />
										<s:label cssClass="etiquetaCaptura" value="Hidalgo:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Puebla" />
										<s:label cssClass="etiquetaCaptura" value="Puebla:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Tlaxcala" />
										<s:label cssClass="etiquetaCaptura" value="Tlaxcala:" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Chihuahua" />
										<s:label cssClass="etiquetaCaptura" value="Chihuahua:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Jalisco" />
										<s:label cssClass="etiquetaCaptura" value="Jalisco:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Quertaro" />
										<s:label cssClass="etiquetaCaptura" value="Quertaro:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Veracruz" />
										<s:label cssClass="etiquetaCaptura" value="Veracruz:" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Coahuila" />
										<s:label cssClass="etiquetaCaptura" value="Coahuila:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="México" />
										<s:label cssClass="etiquetaCaptura" value="México:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Quintana Roo" />
										<s:label cssClass="etiquetaCaptura" value="Quintana Roo:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Yucatán" />
										<s:label cssClass="etiquetaCaptura" value="Yucatán:" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Colima" />
										<s:label cssClass="etiquetaCaptura" value="Colima:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Michoacán" />
										<s:label cssClass="etiquetaCaptura" value="Michoacán:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="San Luis Potosi" />
										<s:label cssClass="etiquetaCaptura" value="San Luis Potosi:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="checkEstados" name="pyMes"  value="Zacatecas" />
										<s:label cssClass="etiquetaCaptura" value="Zacatecas:" />	
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaAyuda" value="Puede elegir uno o más estados." /></td>
					</tr>
				</table>
				
			<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td><input class="botonenviar" value="Anterior" type="button" onclick="javascript:document.getElementById('sec3').style.display='none'; javascript:document.getElementById('sec2').style.display='block';" /></td>
						<td><input class="botonenviar" value="Siguiente" type="button" onclick="javascript:return validacion('3');" /></td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
		<!-- Termina Seccion 3 -->
		
		
		<!-- Inicia Seccion 4 -->
			<div id="sec4" style="display: none;">
				<table>
					<tr>
						<td>
							<table>
								<!-- Inicia Información del contacto -->
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Información del contacto." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Nombre(s) :" /></td>
									<td><s:textfield size="30" id="nombreContacto" name="pyMes.nombreContacto" maxlength="60"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba el nombre o nombres del contacto sin considerar acentos." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Apellido Paterno :" /></td>
									<td><s:textfield size="30" id="appPat" name="pyMes.appPaterno" maxlength="60"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba el apellido paterno del contacto sin considerar acentos." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Apellido Materno :" /></td>
									<td><s:textfield size="30" id="appMat" name="pyMes.appMaterno" maxlength="60"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba el apellido materno del contacto sin considerar acentos." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Correo electrónico :" /></td>
									<td><s:textfield size="30" id="correoElectronicoContacto" name="pyMes.correoElectronicoContacto" maxlength="100"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Incluya su correo electrónico." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Confime Correo electrónico :" /></td>
									<td><s:textfield size="30" id="comparaCorreoContacto" maxlength="100"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Confirme su correo electrónico." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Teléfono :" /></td>
									<td><s:textfield size="30" id="telContacto" name="pyMes.telefonoContacto" maxlength="20"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Incluya su telefono con clave lada y Extensión." /></td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<!-- Inicia Registro de Clientes -->
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Registro de Clientes." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Cliente :" /></td>
									<td><s:textfield size="30" id="cliente" name="clientes.cliente" maxlength="100"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Ingrese el nombre del cliente." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Productos que compra el cliente :" /></td>
									<td><s:textfield size="30" id="prodCliente" name="clientes.productosCompra" maxlength="500"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Describa los principales productos que le vende a la tractora." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Años como proveedor del cliente :" /></td>
									<td><s:textfield size="30" id="aniosProveCliente" name="clientes.aniosProveedor" maxlength="50"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba los años que lleva como proveedor de la tractora." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Meses como proveedor :" /></td>
									<td><s:textfield size="30" id="mesesProveCliente" name="clientes.mesesProveedor" maxlength="50"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba los meses que lleva como proveedor de la tractora." /></td>
								</tr>
								<!-- "Botón" Otro proveedor -->
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				
				
			<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td><input class="botonenviar" value="Anterior" type="button" onclick="javascript:document.getElementById('sec4').style.display='none'; javascript:document.getElementById('sec3').style.display='block';" /></td>
						<td><input class="botonenviar" value="Siguiente" type="button" onclick="javascript:return validacion('4');" /></td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
		<!-- Termina Seccion 4 -->
		
		
		<!-- Inicia Seccion 5 -->
			<div id="sec5" style="display: none;">
				<table>
					<!-- Inicia Certificaciones y capacitación. -->
					<tr>
						<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Certificaciones y capacitación." /></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="Certificación :" /></td>
						<td><s:textfield size="60" id="certificación" name="certificaciones.certificacion" maxlength="150"></s:textfield></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="Año de certificación :" /></td>
						<td><s:textfield size="60" id="anioCertificación" name="certificaciones.fechaCertificacion" maxlength="100"></s:textfield></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="Institución que certificó :" /></td>
						<td><s:textfield size="60" id="instCert" name="pyMes.institutoCertificador" maxlength="100"></s:textfield></td>
					</tr>
				</table>
				<br />
				<table>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="Diplomado ccmx/1:" />
							<s:checkbox id="dip1" name="pyMes.bDiplomadoCcmxUno" value="%{pyMes.bDiplomadoCcmxUno}" />
						</td>
						<td>
							<s:label cssClass="etiquetaCaptura" value="Diplomado ccmx/2:" />
							<s:checkbox id="dip2" name="pyMes.bDiplomadoCcmxDos" value="%{pyMes.bDiplomadoCcmxDos}" />
						</td>
						<td>
							<s:label cssClass="etiquetaCaptura" value="Diplomado ccmx/3:" />
							<s:checkbox id="dip3" name="pyMes.bDiplomadoCcmxTres" value="%{pyMes.bDiplomadoCcmxTres}" />
						</td>
						<td>
							<s:label cssClass="etiquetaCaptura" value="Diplomado ccmx/4:" />
							<s:checkbox id="dip4" name="pyMes.bDiplomadoCcmxCuatro" value="%{pyMes.bDiplomadoCcmxCuatro}" />
						</td>
					</tr>
				</table>
				<br />
				<table>
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
							<s:checkbox id="reqSi" name="pyMes.bRecibeRequerimientosCompra" onclick="javascript:showCat();" value="Si" />
						</td>
						<td>
							<s:label cssClass="etiquetaCaptura" value="No" />
							<s:checkbox id="reqNo" name="pyMes.bRecibeRequerimientosCompra" onclick="javascript:showCat();" value="No" />
						</td>
					</tr>
				</table>
				<br />
				<table id="showCatalogos" style="display:none;">
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="Catálogo SCIAN 1er Nivel" /></td>
						<td><s:checkbox id="cat1" name="pyMes.bRecibeRequerimientosCompra" value="%{pyMes.bRecibeRequerimientosCompra}" /></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="Catálogo SCIAN 2do Nivel" /></td>
						<td><s:checkbox id="cat2" name="pyMes.bRecibeRequerimientosCompra" value="%{pyMes.bRecibeRequerimientosCompra}" /></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura"	value="Catálogo SCIAN 3er Nivel" /></td>
						<td><s:checkbox id="cat3" name="pyMes.bRecibeRequerimientosCompra" value="%{pyMes.bRecibeRequerimientosCompra}" /></td>
					</tr>
				</table>
				
			<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td>
							<input class="botonenviar" value="Anterior" type="button" onclick="javascript:document.getElementById('sec5').style.display='none'; javascript:document.getElementById('sec4').style.display='block';" />
						</td>
						<td><s:submit cssClass="botonenviar" value="Actualizar PyME" /></td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
		<!-- Termina Seccion 5 -->	
		
		<!-- Bloque Hidden's -->
			<s:hidden name="domicilios.idDomicilio" id="idDomicilio" value="%{domicilios.idDomicilio}" />
			<s:hidden name="clientes.idCliente" id="idCliente" value="%{clientes.idCliente}" />
			<s:hidden name="certificaciones.idCertificado" id="idCertificado" value="%{certificaciones.idCertificado}" />	
		<!-- Bloque Hidden's -->
		
		</s:form>
	</fieldset>
	<script type="text/javascript">
		function validacion(sec) {
			
				valorPerJuridica = document.getElementById("personalidadJuridica").selectedIndex;
				valorCorreo = document.getElementById("correoElectronico").value;
				valorCompara = document.getElementById("comparaCorreo").value;

				valorMsjVenta = document.getElementById("mensajeVenta").value;
				valorCalle = document.getElementById("calle").value;
				valorNumExt = document.getElementById("numExt").value;
				valorColonia = document.getElementById("colonia").value;
				valorDelegacion = document.getElementById("delegacion").value;
				valorEstado = document.getElementById("estado").selectedIndex;
				valorCodigoPostal = document.getElementById("codigoPostal").value;
				valorProdPrincipales = document.getElementById("prodPrincipales").value;
				valorSectorUno = document.getElementById("sector1");
				valorSectorDos = document.getElementById("sector2");
				valorSectorTres = document.getElementById("sector3");

				
				valorNombre = document.getElementById("nombreContacto").value;
				valorPaterno = document.getElementById("appPat").value;
				valorMaterno = document.getElementById("appMat").value;
				valorCorreoContacto = document.getElementById("correoElectronicoContacto").value;
				valorComparaContacto = document.getElementById("comparaCorreoContacto").value;
				valorTelefonoContacto = document.getElementById("telContacto").value;
				valorCliente = document.getElementById("cliente").value;
				valorProdCliente = document.getElementById("prodCliente").value;
				valorAniosProveCliente = document.getElementById("aniosProveCliente").value;
				valorMesesProveCliente = document.getElementById("mesesProveCliente").value;
			

		
			if (sec == '1') {
				
				if( valorPerJuridica == " " || valorPerJuridica == 0 || valorPerJuridica == null || valorPerJuridica == "Seleccione el tipo de persona") {
					document.getElementById("personalidadJuridica").focus();
					alert("Seleccione un Estado");
					return false;
				}else if( !(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(valorCorreo)) ) {
					document.getElementById("correoElectronico").focus();
					alert("Ingrese una dirección de correo electrónico válida");
					return false;
				}else if (valorCorreo != valorCompara){
					document.getElementById("comparaCorreo").focus();
					alert("El correo electrónico no coincide");
					return false;
				}else{
					document.getElementById('sec1').style.display = 'none';
					document.getElementById('sec2').style.display = 'block';
					return true;
				}
			}else if( sec == '2'){
				if( valorMsjVenta == null || valorMsjVenta.length == 0 || /^\s+$/.test(valorMsjVenta) ) {
					document.getElementById("mensajeVenta").focus();
					alert("Ingrese Mensaje de ventas");  
					return false;
				}else if( valorCalle == null || valorCalle.length == 0 || /^\s+$/.test(valorCalle) ) {
					document.getElementById("calle").focus();
					alert("Ingrese la calle");  
					return false;
				}else if( valorNumExt == null || valorNumExt.length == 0 || /^\s+$/.test(valorNumExt) ) {
					document.getElementById("numExt").focus();
					alert("Ingrese el Número exterior");  
					return false;
				}else if( valorColonia == null || valorColonia.length == 0 || /^\s+$/.test(valorColonia) ) {
					document.getElementById("colonia").focus();
					alert("Ingrese la colonia");
					return false;
				}else if( valorDelegacion == null || valorDelegacion.length == 0 || /^\s+$/.test(valorDelegacion) ) {
					document.getElementById("delegacion").focus();
					alert("Ingrese la delegación");
					return false;
				}else if( valorEstado == " " || valorEstado == 0 || valorEstado == null) {
					document.getElementById("estado").focus();
					alert("Seleccione un Estado");
					return false;
				}else if( valorCodigoPostal == null || valorCodigoPostal.length == 0 || /^\s+$/.test(valorCodigoPostal) ) {
					document.getElementById("codigoPostal").focus();
					alert("Ingrese el Código Postal");
					return false;
				}else if( valorProdPrincipales == null || valorProdPrincipales.length == 0 || /^\s+$/.test(valorProdPrincipales) ) {
					document.getElementById("prodPrincipales").focus();
					alert("Ingrese el producto principal");
					return false;
				}else if( valorSectorUno.checked && (!valorSectorDos.checked && !valorSectorTres.checked) ){
					document.getElementById('sec2').style.display = 'none';
					document.getElementById('sec3').style.display = 'block';
					return true;
				}else if( valorSectorDos.checked && (!valorSectorUno.checked && !valorSectorTres.checked) ){
					document.getElementById('sec2').style.display = 'none';
					document.getElementById('sec3').style.display = 'block';
					return true;
				}else if( valorSectorTres.checked && (!valorSectorUno.checked && !valorSectorDos.checked) ){
					document.getElementById('sec2').style.display = 'none';
					document.getElementById('sec3').style.display = 'block';
					return true;
				}else{
					alert("Selecione una categoría a la que pertenece su empresa.");
					return false;
				}
			}else if( sec == '3' ){

				document.getElementById('sec3').style.display = 'none';
				document.getElementById('sec4').style.display = 'block';
				return true;
				
			}else if( sec == '4' ){
				
				if( valorNombre == null || valorNombre.length == 0 || /^\s+$/.test(valorNombre) ) {
					document.getElementById("nombreContacto").focus();
					alert("Ingrese el Nombre(s) requerido");
					return false;
				}else if( valorPaterno == null || valorPaterno.length == 0 || /^\s+$/.test(valorPaterno) ) {
					document.getElementById("appPat").focus();
					alert("Ingrese Apellido Paterno");
					return false;
				}else if( valorMaterno == null || valorMaterno.length == 0 || /^\s+$/.test(valorMaterno) ) {
					document.getElementById("appMat").focus();
					alert("Ingrese Apellido Materno");  
					return false;
				}else if( !(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(valorCorreoContacto)) ) {
					document.getElementById("correoElectronicoContacto").focus();
					alert("Ingrese una dirección de correo electrónico válida");
					return false;
				}else if (valorCorreoContacto != valorComparaContacto){
					document.getElementById("comparaCorreoContacto").focus();
					alert("El correo electrónico no coincide");
					return false;
				}else if( valorTelefonoContacto == null || valorTelefonoContacto.length == 0 || /^\s+$/.test(valorTelefonoContacto) ) {
					document.getElementById("telContacto").focus();
					alert("Ingrese su teléfono");
					return false;
				}else if( valorCliente == null || valorCliente.length == 0 || /^\s+$/.test(valorCliente) ) {
					document.getElementById("cliente").focus();
					alert("Ingrese el nombre del cliente");
					return false;
				}else if( valorProdCliente == null || valorProdCliente.length == 0 || /^\s+$/.test(valorProdCliente) ) {
					document.getElementById("prodCliente").focus();
					alert("El campo Productos que compra el cliente es requerido");
					return false;
				}else if( isNaN(valorAniosProveCliente) ) {
					document.getElementById("aniosProveCliente").focus();
					alert("El campo Años como proveedor es requerido y debe ingresarlo con números");
					return false;
				}else if( isNaN(valorMesesProveCliente) ) {
					document.getElementById("mesesProveCliente").focus();
					alert("El campo Meses como proveedor es requerido y debe ingresarlo con números");
					return false;
				}else{
					document.getElementById('sec4').style.display = 'none';
					document.getElementById('sec5').style.display = 'block';
					return true;	
				}
				
			}else{
				if( document.getElementById('reqSi').checked == true && document.getElementById('reqNo').checked == false ){
					if( document.getElementById('cat1').checked == false && document.getElementById('cat2').checked == false && document.getElementById('cat3').checked == false ){
						alert('Seleccione un catálogo');
						return false;
					}else{
						return true;	
					}
					
				}else if( document.getElementById('reqSi').checked == false && document.getElementById('reqNo').checked == true ){
					return true;	
				}else{
					alert('¿Desea recibir requerimientos de compra?');
					return false;
				}
				
			}
			
		}
		function showCat(){
			
			if ( document.getElementById('reqSi').checked ) {
				document.getElementById('reqNo').checked = false;
				document.getElementById('showCatalogos').style.display = 'block';
			}else if ( document.getElementById('reqSi').checked == false ){
				document.getElementById('showCatalogos').style.display = 'none';
			}else if ( document.getElementById('reqNo').checked ){
				document.getElementById('reqSi').checked = false;
			}
		}
		
		function checkSector(){
			
			secUno = document.getElementById("sector1");
			secDos = document.getElementById("sector2");
			secTres = document.getElementById("sector3");
			
			if( secUno.checked ) {
				secDos.checked = false;
				secTres.checked = false;
			}
			if ( secDos.checked ){
				secUno.checked = false;
				secTres.checked = false;
			}
			if ( secTres.checked ){
				secUno.checked = false;
				secDos.checked = false;
			}
		}
	</script>
</body>
</html>
