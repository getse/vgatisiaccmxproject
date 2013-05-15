<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link
	href="${pageContext.request.contextPath}/css/calendario.css"
	rel="stylesheet"
	type="text/css" />
<script
	src="${pageContext.request.contextPath}/js/pymes.js"
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
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
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
		<s:form action="pymeInformacionSave" namespace="/pymes" enctype="multipart/form-data" method="post" theme="simple" onsubmit="return validacion('6')">
			<s:hidden name="cveScian" id="idCveSci" value="%{cveScian}" />
			<!-- Inicia Seccion 1 -->
			<div id="sec1" ${pyMEs.personalidadJuridica==null?' style="display: block;"':' style="display: none;"' }>
				<legend>
					<s:label value="Actualizar datos PyME" />
					<br /> <br />
					<s:label cssClass="camposObligatorios"
						value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
				</legend>
				<br />
				<s:if test="pyMEs.personalidadJuridica == null">
					<legend>
						<s:label cssClass="etiquetaCaptura" value="Estimado empresario por favor, ingrese y valide la información que se solicita en el presente formulario sobre su negocio. Esta información será utilizada para que otras empresas puedan ver los productos o servicios que ofrece. Recuerde seguir las instrucciones al pie de la letra para que las grandes empresas puedan encontrar su información." />
					</legend>
					<br />
				</s:if>
				<table>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="* Seleccione Persona Moral o Persona Fisica" /></td>
						<td>
							<select id="personalidadJuridica" name="pyMEs.personalidadJuridica">
								<s:if test="pyMEs.personalidadJuridica == null">
									<option selected="selected" value="Seleccione el tipo de persona">Seleccione el tipo de persona</option>
								</s:if>
								<s:else>
									<option value=""></option>
									<option selected="selected" value="${pyMEs.personalidadJuridica}"><s:text name="%{pyMEs.personalidadJuridica}" /> </option>
								</s:else>
								<option value="Persona Moral">Persona Moral</option>
								<option value="Persona Fisica">Persona Fisica</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="RFC:" /></td>
						<td><s:textfield size="60" id="rfc" name="pyMEs.rfc" maxlength="20"></s:textfield></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><s:label cssClass="etiquetaAyuda" value="Escriba su RFC con homoclave." /></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="* Correo electrónico:" /></td>
						<td><s:textfield size="60" id="correoElectronico" name="pyMEs.correoElectronico" maxlength="100"></s:textfield></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><s:label cssClass="etiquetaAyuda" value="Escriba su correo electrónico." /></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="* Confirmar Correo Electrónico:" /></td>
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
									<td><s:label cssClass="etiquetaCaptura" value="* Nombre comercial:" /></td>
									<td><s:textfield size="30" id="nombreComercial" name="pyMEs.nombreComercial" maxlength="150"></s:textfield>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Nombre fiscal:" /></td>
									<td><s:textfield size="30" id="nombreFiscal" name="pyMEs.nombreFiscal" maxlength="100"></s:textfield>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura"	value="Numero de empleados:" /></td>
									<td><s:textfield size="30" id="numeroEmpleados" name="pyMEs.numeroEmpleados" maxlength="250"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Mensaje de venta:" /></td>
									<td><s:textfield size="30" id="mensajeVenta" name="pyMEs.mensajeVentas" maxlength="150"></s:textfield></td>
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
							</table>
						</td>
					<!-- Segunda Columna -->
						<td>
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Piso:" /></td>
									<td><s:textfield size="20" id="piso" name="domicilios.piso" maxlength="20"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba el piso en que se encuentra." /></td>
								</tr>
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
										<select id="estado" name="domicilios.estado" style="width: 200px;">
											<s:if test="domicilios == null">
												<option selected="selected" value="Seleccione el estado">--Seleccione un estado--</option>
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
									<td><s:textfield size="30" id="pagWeb" name="pyMEs.paginaWeb" maxlength="100"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba la dirección web completa." /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br />
				<table>
					<tr>
						<td style="width: 400px;">
							<table>
								<tr>
									<td>
										<s:label cssClass="etiquetaAyuda" value="*Elija el sector en el que se encuentra su empresa." />
									</td>
								</tr>
								<tr>
									<td>
										<s:checkbox id="sector1" name="pyMEs.bPrimerNivel" value="%{pyMEs.bPrimerNivel}" onclick="javascript: checkSectorUno();"/>
										<s:label cssClass="etiquetaCaptura" value="Sector de Servicios:" />
									</td>
								</tr>
								<tr>
									<td>
										<s:checkbox id="sector2" name="pyMEs.bSegundoNivel" value="%{pyMEs.bSegundoNivel}" onclick="javascript: checkSectorDos();"/>
										<s:label cssClass="etiquetaCaptura" value="Sector Comercial:" />
									</td>
								</tr>
								<tr>
									<td>
										<s:checkbox id="sector3" name="pyMEs.bTercerNivel" value="%{pyMEs.bTercerNivel}" onclick="javascript: checkSectorTres();"/>
										<s:label cssClass="etiquetaCaptura" value="Sector Manufacturero:" />
									</td>
								</tr>
							</table>
						</td>
						<td style="width: 400px;">
							<table>	
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" value="Seleccione el rango de sus ventas anuales en pesos (ultimo año)." />
									</td>
								</tr>		
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="Ventas anuales:" />
									</td>
									<td>
										<select id="ventasAnuales" name="pyMEs.ventasAnuales">
											<s:if test="pyMEs.ventasAnuales == null">
												<option selected="selected" value="Seleccione un rango">Seleccione un rango de ventas</option>
											</s:if>
											<s:else>
												<option value=""></option>
												<option selected="selected" value="${pyMEs.ventasAnuales}"><s:text name="%{pyMEs.ventasAnuales}" /></option>
											</s:else>
											<s:if test="pyMEs.ventasAnuales != 'menor a $500,000'">
												<option value="menor a $500,000">menor a $500,000</option>
											</s:if>
											<s:if test="pyMEs.ventasAnuales != 'de $500,000 a $5,000,000'">
												<option value="de $500,000 a $5,000,000">de $500,000 a $5,000,000</option>
											</s:if>
											<s:if test="pyMEs.ventasAnuales != 'mayor a $5,000,000'">
												<option value="mayor a $5,000,000">mayor a $5,000,000</option>
											</s:if>
										</select>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>						
							</table>
						</td>
					</tr>
				</table>
				<br />
				<table>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="* Productos principales:" />
							<label id="addDesactivado" class="agregar" onclick="javascript:addProd();">+agregar otro producto</label>		
						</td>
					</tr>
				</table>
			<!-- Inician campos productos-->
				<br />
				<div id="idProd1">
					<s:hidden name="pyMEs.idProducto1" id="idProd1" value="%{pyMEs.idProducto1}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales" name="pyMEs.producto1" maxlength="500"></s:textfield>
				</div>
				<div id="idProd2" ${pyMEs.idProducto2==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto2" id="idProd2" value="%{pyMEs.idProducto2}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales2" name="pyMEs.producto2" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(2);">-eliminar producto</label>
				</div>
				<div id="idProd3" ${pyMEs.idProducto3==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto3" id="idProd3" value="%{pyMEs.idProducto3}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales3" name="pyMEs.producto3" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(3);">-eliminar producto</label>
				</div>
				<div id="idProd4" ${pyMEs.idProducto4==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto4" id="idProd4" value="%{pyMEs.idProducto4}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales4" name="pyMEs.producto4" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(4);">-eliminar producto</label>
				</div>
				<div id="idProd5" ${pyMEs.idProducto5==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto5" id="idProducto5" value="%{pyMEs.idProducto5}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales5" name="pyMEs.producto5" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(5);">-eliminar producto</label>
				</div>
				<div id="idProd6" ${pyMEs.idProducto6==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto6" id="idProducto6" value="%{pyMEs.idProducto6}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales6" name="pyMEs.producto6" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(6);">-eliminar producto</label>
				</div>
				<div id="idProd7" ${pyMEs.idProducto7==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto7" id="idProducto7" value="%{pyMEs.idProducto7}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales7" name="pyMEs.producto7" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(7);">-eliminar producto</label>
				</div>
				<div id="idProd8" ${pyMEs.idProducto8==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto8" id="idProducto8" value="%{pyMEs.idProducto8}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales8" name="pyMEs.producto8" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(8);">-eliminar producto</label>
				</div>
				<div id="idProd9" ${pyMEs.idProducto9==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto9" id="idProducto9" value="%{pyMEs.idProducto9}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales9" name="pyMEs.producto9" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(9);">-eliminar producto</label>
				</div>
				<div id="idProd10" ${pyMEs.idProducto10==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto10" id="idProducto10" value="%{pyMEs.idProducto10}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales10" name="pyMEs.producto10" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(10);">-eliminar producto</label>
				</div>
				<div id="idProd11" ${pyMEs.idProducto11==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto11" id="idProducto11" value="%{pyMEs.idProducto11}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales11" name="pyMEs.producto11" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(11);">-eliminar producto</label>
				</div>
				<div id="idProd12" ${pyMEs.idProducto12==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto12" id="idProducto12" value="%{pyMEs.idProducto12}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales12" name="pyMEs.producto12" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(12);">-eliminar producto</label>
				</div>
				<div id="idProd13" ${pyMEs.idProducto13==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto13" id="idProducto13" value="%{pyMEs.idProducto13}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales13" name="pyMEs.producto13" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(13);">-eliminar producto</label>
				</div>
				<div id="idProd14" ${pyMEs.idProducto14==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto14" id="idProducto14" value="%{pyMEs.idProducto14}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales14" name="pyMEs.producto14" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(14);">-eliminar producto</label>
				</div>
				<div id="idProd15" ${pyMEs.idProducto15==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto15" id="idProducto15" value="%{pyMEs.idProducto15}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales15" name="pyMEs.producto15" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(15);">-eliminar producto</label>
				</div>
				<div id="idProd16" ${pyMEs.idProducto16==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto16" id="idProducto16" value="%{pyMEs.idProducto16}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales16" name="pyMEs.producto16" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(16);">-eliminar producto</label>
				</div>
				<div id="idProd17" ${pyMEs.idProducto17==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto17" id="idProducto17" value="%{pyMEs.idProducto17}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales17" name="pyMEs.producto17" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(17);">-eliminar producto</label>
				</div>
				<div id="idProd18" ${pyMEs.idProducto18==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto18" id="idProducto18" value="%{pyMEs.idProducto18}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales18" name="pyMEs.producto18" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(18);">-eliminar producto</label>
				</div>
				<div id="idProd19" ${pyMEs.idProducto19==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto19" id="idProducto19" value="%{pyMEs.idProducto19}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales19" name="pyMEs.producto19" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(19);">-eliminar producto</label>
				</div>
				<div id="idProd20" ${pyMEs.idProducto20==0?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto20" id="idProducto20" value="%{pyMEs.idProducto20}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales20" name="pyMEs.producto20" maxlength="500"></s:textfield>
					<label class="quitar" onclick="javascript: delProd(20);">-eliminar producto</label>
				</div>
			<!-- Finaliza campos productos-->
				<div>
					<s:label cssClass="etiquetaAyuda" value="Incluya sus productos principales en 3 palabras, (máximo 20 productos)." />
				</div>
				
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
						<td>
							<s:label cssClass="etiquetaCaptura" value="* Estados donde puede vender sus productos:" />
						</td>
					</tr>
					<tr>
						<td>
							<table id="contCheckEstados">
								<tr>
									<td style="width: 180px;">
										<s:if test="estadosVentas.aguascalientes == 'Aguascalientes'">
											<s:checkbox id="check1" name="pyMEsAguascalientes" value="true" onclick="javascript: valueEstadoCheck(1, 'Aguascalientes');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check1" name="pyMEsAguascalientes" value="" onclick="javascript: valueEstadoCheck(1, 'Aguascalientes');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Aguascalientes:" />
										<s:hidden id="checkEstado1" name="estadosVentas.aguascalientes" value="%{estadosVentas.aguascalientes}" />
										<s:hidden id="idCheckEstado1" name="estadosVentas.idAguascalientes" value="%{estadosVentas.idAguascalientes}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.distritoFederal == 'Distrito Federal'">
											<s:checkbox id="check2" name="pyMEsDistritoFederal" value="true" onclick="javascript: valueEstadoCheck(2, 'Distrito Federal');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check2" name="pyMEsDistritoFederal" value="" onclick="javascript: valueEstadoCheck(2, 'Distrito Federal');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Distrito Federal:" />
										<s:hidden id="checkEstado2" name="estadosVentas.distritoFederal" value="%{estadosVentas.distritoFederal}" />
										<s:hidden id="idCheckEstado2" name="estadosVentas.idDistritoFederal" value="%{estadosVentas.idDistritoFederal}" />	
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.morelos == 'Morelos'">
											<s:checkbox id="check3" name="pyMEsMorelos" value="true" onclick="javascript: valueEstadoCheck(3, 'Morelos');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check3" name="pyMEsMorelos" value="" onclick="javascript: valueEstadoCheck(3, 'Morelos');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Morelos:" />
										<s:hidden id="checkEstado3" name="estadosVentas.morelos" value="%{estadosVentas.morelos}" />
										<s:hidden id="idCheckEstado3" name="estadosVentas.idMorelos" value="%{estadosVentas.idMorelos}" />	
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.sinaloa == 'Sinaloa'">
											<s:checkbox id="check4" name="pyMEsSinaloa" value="true" onclick="javascript: valueEstadoCheck(4, 'Sinaloa');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check4" name="pyMEsSinaloa" value="" onclick="javascript: valueEstadoCheck(4, 'Sinaloa');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Sinaloa:" />
										<s:hidden id="checkEstado4" name="estadosVentas.sinaloa" value="%{estadosVentas.sinaloa}" />
										<s:hidden id="idCheckEstado4" name="estadosVentas.idSinaloa" value="%{estadosVentas.idSinaloa}" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:if test="estadosVentas.bajaCaliforniaNorte == 'Baja California Norte'">
											<s:checkbox id="check5" name="pyMEsBajaCaliforniaNorte" value="true" onclick="javascript: valueEstadoCheck(5, 'Baja California Norte');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check5" name="pyMEsBajaCaliforniaNorte" value="" onclick="javascript: valueEstadoCheck(5, 'Baja California Norte');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Baja California Norte:" />
										<s:hidden id="checkEstado5" name="estadosVentas.bajaCaliforniaNorte" value="%{estadosVentas.bajaCaliforniaNorte}" />
										<s:hidden id="idCheckEstado5" name="estadosVentas.idBajaCaliforniaNorte" value="%{estadosVentas.idBajaCaliforniaNorte}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.durango == 'Durango'">
											<s:checkbox id="check6" name="pyMEsDurango" value="true" onclick="javascript: valueEstadoCheck(6, 'Durango');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check6" name="pyMEsDurango" value="" onclick="javascript: valueEstadoCheck(6, 'Durango');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Durango:" />
										<s:hidden id="checkEstado6" name="estadosVentas.durango" value="%{estadosVentas.durango}" />
										<s:hidden id="idCheckEstado6" name="estadosVentas.idDurango" value="%{estadosVentas.idDurango}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.nayarit == 'Nayarit'">
											<s:checkbox id="check7" name="pyMEsNayarit" value="true" onclick="javascript: valueEstadoCheck(7, 'Nayarit');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check7" name="pyMEsNayarit" value="" onclick="javascript: valueEstadoCheck(7, 'Nayarit');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Nayarit:" />
										<s:hidden id="checkEstado7" name="estadosVentas.nayarit" value="%{estadosVentas.nayarit}" />
										<s:hidden id="idCheckEstado7" name="estadosVentas.idNayarit" value="%{estadosVentas.idNayarit}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.sonora == 'Sonora'">
											<s:checkbox id="check8" name="pyMEsSonora" value="true" onclick="javascript: valueEstadoCheck(8, 'Sonora');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check8" name="pyMEsSonora" value="" onclick="javascript: valueEstadoCheck(8, 'Sonora');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Sonora:" />
										<s:hidden id="checkEstado8" name="estadosVentas.sonora" value="%{estadosVentas.sonora}" />
										<s:hidden id="idCheckEstado8" name="estadosVentas.idSonora" value="%{estadosVentas.idSonora}" />
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:if test="estadosVentas.bajaCaliforniaSur == 'Baja California Sur'">
											<s:checkbox id="check9" name="pyMEsBajaCaliforniaSur" value="true" onclick="javascript: valueEstadoCheck(9, 'Baja California Sur');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check9" name="pyMEsBajaCaliforniaSur" value="" onclick="javascript: valueEstadoCheck(9, 'Baja California Sur');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Baja California Sur:" />	
										<s:hidden id="checkEstado9" name="estadosVentas.bajaCaliforniaSur" value="%{estadosVentas.bajaCaliforniaSur}" />
										<s:hidden id="idCheckEstado9" name="estadosVentas.idBajaCaliforniaSur" value="%{estadosVentas.idBajaCaliforniaSur}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.guanajuato == 'Guanajuato'">
											<s:checkbox id="check10" name="pyMEsGuanajuato" value="true" onclick="javascript: valueEstadoCheck(10, 'Guanajuato');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check10" name="pyMEsGuanajuato" value="" onclick="javascript: valueEstadoCheck(10, 'Guanajuato');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Guanajuato:" />
										<s:hidden id="checkEstado10" name="estadosVentas.guanajuato" value="%{estadosVentas.guanajuato}" />
										<s:hidden id="idCheckEstado10" name="estadosVentas.idGuanajuato" value="%{estadosVentas.idGuanajuato}" />	
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.nuevoLeon == 'Nuevo León'">
											<s:checkbox id="check11" name="pyMEsNuevoLeon" value="true" onclick="javascript: valueEstadoCheck(11, 'Nuevo León');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check11" name="pyMEsNuevoLeon" value="" onclick="javascript: valueEstadoCheck(11, 'Nuevo León');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Nuevo León:" />
										<s:hidden id="checkEstado11" name="estadosVentas.nuevoLeon" value="%{estadosVentas.nuevoLeon}" />
										<s:hidden id="idCheckEstado11" name="estadosVentas.idNuevoLeon" value="%{estadosVentas.idNuevoLeon}" />	
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.tabasco == 'Tabasco'">
											<s:checkbox id="check12" name="pyMEsTabasco" value="true" onclick="javascript: valueEstadoCheck(12, 'Tabasco');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check12" name="pyMEsTabasco" value="" onclick="javascript: valueEstadoCheck(12, 'Tabasco');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Tabasco:" />
										<s:hidden id="checkEstado12" name="estadosVentas.tabasco" value="%{estadosVentas.tabasco}" />
										<s:hidden id="idCheckEstado12" name="estadosVentas.idTabasco" value="%{estadosVentas.idTabasco}" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:if test="estadosVentas.campeche == 'Campeche'">
											<s:checkbox id="check13" name="pyMEsCampeche" value="true" onclick="javascript: valueEstadoCheck(13, 'Campeche');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check13" name="pyMEsCampeche" value="" onclick="javascript: valueEstadoCheck(13, 'Campeche');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Campeche:" />	
										<s:hidden id="checkEstado13" name="estadosVentas.campeche" value="%{estadosVentas.campeche}" />
										<s:hidden id="idCheckEstado13" name="estadosVentas.idCampeche" value="%{estadosVentas.idCampeche}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.guerrero == 'Guerrero'">
											<s:checkbox id="check14" name="pyMEsGuerrero" value="true" onclick="javascript: valueEstadoCheck(14, 'Guerrero');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check14" name="pyMEsGuerrero" value="" onclick="javascript: valueEstadoCheck(14, 'Guerrero');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Guerrero:" />
										<s:hidden id="checkEstado14" name="estadosVentas.guerrero" value="%{estadosVentas.guerrero}" />
										<s:hidden id="idCheckEstado14" name="estadosVentas.idGuerrero" value="%{estadosVentas.idGuerrero}" />	
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.oaxaca == 'Oaxaca'">
											<s:checkbox id="check15" name="pyMEsOaxaca" value="true" onclick="javascript: valueEstadoCheck(15, 'Oaxaca');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check15" name="pyMEsOaxaca" value="" onclick="javascript: valueEstadoCheck(15, 'Oaxaca');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Oaxaca:" />
										<s:hidden id="checkEstado15" name="estadosVentas.oaxaca" value="%{estadosVentas.oaxaca}" />
										<s:hidden id="idCheckEstado15" name="estadosVentas.idOaxaca" value="%{estadosVentas.idOaxaca}" />	
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.tamaulipas == 'Tamaulipas'">
											<s:checkbox id="check16" name="pyMEsTamaulipas" value="true" onclick="javascript: valueEstadoCheck(16, 'Tamaulipas');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check16" name="pyMEsTamaulipas" value="" onclick="javascript: valueEstadoCheck(16, 'Tamaulipas');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Tamaulipas:" />
										<s:hidden id="checkEstado16" name="estadosVentas.tamaulipas" value="%{estadosVentas.tamaulipas}" />
										<s:hidden id="idCheckEstado16" name="estadosVentas.idTamaulipas" value="%{estadosVentas.idTamaulipas}" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:if test="estadosVentas.chiapas == 'Chiapas'">
											<s:checkbox id="check17" name="pyMEsChiapas" value="true" onclick="javascript: valueEstadoCheck(17, 'Chiapas');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check17" name="pyMEsChiapas" value="" onclick="javascript: valueEstadoCheck(17, 'Chiapas');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Chiapas:" />
										<s:hidden id="checkEstado17" name="estadosVentas.chiapas" value="%{estadosVentas.chiapas}" />
										<s:hidden id="idCheckEstado17" name="estadosVentas.idChiapas" value="%{estadosVentas.idChiapas}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.hidalgo == 'Hidalgo'">
											<s:checkbox id="check18" name="pyMEsHidalgo" value="true" onclick="javascript: valueEstadoCheck(18, 'Hidalgo');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check18" name="pyMEsHidalgo" value="" onclick="javascript: valueEstadoCheck(18, 'Hidalgo');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Hidalgo:" />
										<s:hidden id="checkEstado18" name="estadosVentas.hidalgo" value="%{estadosVentas.hidalgo}" />
										<s:hidden id="idCheckEstado18" name="estadosVentas.idHidalgo" value="%{estadosVentas.idHidalgo}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.puebla == 'Puebla'">
											<s:checkbox id="check19" name="pyMEsPuebla" value="true" onclick="javascript: valueEstadoCheck(19, 'Puebla');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check19" name="pyMEsPuebla" value="" onclick="javascript: valueEstadoCheck(19, 'Puebla');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Puebla:" />
										<s:hidden id="checkEstado19" name="estadosVentas.puebla" value="%{estadosVentas.puebla}" />
										<s:hidden id="idCheckEstado19" name="estadosVentas.idPuebla" value="%{estadosVentas.idPuebla}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.tlaxcala == 'Tlaxcala'">
											<s:checkbox id="check20" name="pyMEsTlaxcala" value="true" onclick="javascript: valueEstadoCheck(20, 'Tlaxcala');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check20" name="pyMEsTlaxcala" value="" onclick="javascript: valueEstadoCheck(20, 'Tlaxcala');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Tlaxcala:" />
										<s:hidden id="checkEstado20" name="estadosVentas.tlaxcala" value="%{estadosVentas.tlaxcala}" />
										<s:hidden id="idCheckEstado20" name="estadosVentas.idTlaxcala" value="%{estadosVentas.idTlaxcala}" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:if test="estadosVentas.chihuahua == 'Chihuahua'">
											<s:checkbox id="check21" name="pyMEsChihuahua" value="true" onclick="javascript: valueEstadoCheck(21, 'Chihuahua');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check21" name="pyMEsChihuahua" value="" onclick="javascript: valueEstadoCheck(21, 'Chihuahua');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Chihuahua:" />
										<s:hidden id="checkEstado21" name="estadosVentas.chihuahua" value="%{estadosVentas.chihuahua}" />
										<s:hidden id="idCheckEstado21" name="estadosVentas.idChihuahua" value="%{estadosVentas.idChihuahua}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.jalisco == 'Jalisco'">
											<s:checkbox id="check22" name="pyMEsJalisco" value="true" onclick="javascript: valueEstadoCheck(22, 'Jalisco');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check22" name="pyMEsJalisco" value="" onclick="javascript: valueEstadoCheck(22, 'Jalisco');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Jalisco:" />
										<s:hidden id="checkEstado22" name="estadosVentas.jalisco" value="%{estadosVentas.jalisco}" />
										<s:hidden id="idCheckEstado22" name="estadosVentas.idJalisco" value="%{estadosVentas.idJalisco}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.queretaro == 'Querétaro'">
											<s:checkbox id="check23" name="pyMEsQueretaro" value="true" onclick="javascript: valueEstadoCheck(23, 'Querétaro');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check23" name="pyMEsQueretaro" value="" onclick="javascript: valueEstadoCheck(23, 'Querétaro');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Quertaro:" />
										<s:hidden id="checkEstado23" name="estadosVentas.queretaro" value="%{estadosVentas.queretaro}" />
										<s:hidden id="idCheckEstado23" name="estadosVentas.idQueretaro" value="%{estadosVentas.idQueretaro}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.veracruz == 'Veracruz'">
											<s:checkbox id="check24" name="pyMEsVeracruz" value="true" onclick="javascript: valueEstadoCheck(24, 'Veracruz');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check24" name="pyMEsVeracruz" value="" onclick="javascript: valueEstadoCheck(24, 'Veracruz');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Veracruz:" />
										<s:hidden id="checkEstado24" name="estadosVentas.veracruz" value="%{estadosVentas.veracruz}" />
										<s:hidden id="idCheckEstado24" name="estadosVentas.idVeracruz" value="%{estadosVentas.idVeracruz}" />
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:if test="estadosVentas.coahuila == 'Coahuila'">
											<s:checkbox id="check25" name="pyMEsCoahuila" value="true" onclick="javascript: valueEstadoCheck(25, 'Coahuila');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check25" name="pyMEsCoahuila" value="" onclick="javascript: valueEstadoCheck(25, 'Coahuila');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Coahuila:" />
										<s:hidden id="checkEstado25" name="estadosVentas.coahuila" value="%{estadosVentas.coahuila}" />
										<s:hidden id="idCheckEstado25" name="estadosVentas.idCoahuila" value="%{estadosVentas.idCoahuila}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.mexico == 'México'">
											<s:checkbox id="check26" name="pyMEsMexico" value="true" onclick="javascript: valueEstadoCheck(26, 'México');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check26" name="pyMEsMexico" value="" onclick="javascript: valueEstadoCheck(26, 'México');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Estado de México:" />
										<s:hidden id="checkEstado26" name="estadosVentas.mexico" value="%{estadosVentas.mexico}" />
										<s:hidden id="idCheckEstado26" name="estadosVentas.idMexico" value="%{estadosVentas.idMexico}" />	
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.quintanaRoo == 'Quintana Roo'">
											<s:checkbox id="check27" name="pyMEsQuintanaRoo" value="true" onclick="javascript: valueEstadoCheck(27, 'Quintana Roo');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check27" name="pyMEsQuintanaRoo" value="" onclick="javascript: valueEstadoCheck(27, 'Quintana Roo');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Quintana Roo:" />
										<s:hidden id="checkEstado27" name="estadosVentas.quintanaRoo" value="%{estadosVentas.quintanaRoo}" />
										<s:hidden id="idCheckEstado27" name="estadosVentas.idQuintanaRoo" value="%{estadosVentas.idQuintanaRoo}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.yucatan == 'Yucatán'">
											<s:checkbox id="check28" name="pyMEsYucatan" value="true" onclick="javascript: valueEstadoCheck(28, 'Yucatán');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check28" name="pyMEsYucatan" value="" onclick="javascript: valueEstadoCheck(28, 'Yucatán');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Yucatán:" />
										<s:hidden id="checkEstado28" name="estadosVentas.yucatan" value="%{estadosVentas.yucatan}" />
										<s:hidden id="idCheckEstado28" name="estadosVentas.idYucatan" value="%{estadosVentas.idYucatan}" />
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:if test="estadosVentas.colima == 'Colima'">
											<s:checkbox id="check29" name="pyMEsColima" value="true" onclick="javascript: valueEstadoCheck(29, 'Colima');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check29" name="pyMEsColima" value="" onclick="javascript: valueEstadoCheck(29, 'Colima');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Colima:" />
										<s:hidden id="checkEstado29" name="estadosVentas.colima" value="%{estadosVentas.colima}" />
										<s:hidden id="idCheckEstado29" name="estadosVentas.idColima" value="%{estadosVentas.idColima}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.michoacan == 'Michoacán'">
											<s:checkbox id="check30" name="pyMEsMichoacan" value="true" onclick="javascript: valueEstadoCheck(30, 'Michoacán');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check30" name="pyMEsMichoacan" value="" onclick="javascript: valueEstadoCheck(30, 'Michoacán');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Michoacán:" />
										<s:hidden id="checkEstado30" name="estadosVentas.michoacan" value="%{estadosVentas.michoacan}" />
										<s:hidden id="idCheckEstado30" name="estadosVentas.idMichoacan" value="%{estadosVentas.idMichoacan}" />	
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.sanLuisPotosi == 'San Luís Potosí'">
											<s:checkbox id="check31" name="pyMEsSanLuisPotosi" value="true" onclick="javascript: valueEstadoCheck(31, 'San Luís Potosí');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check31" name="pyMEsSanLuisPotosi" value="" onclick="javascript: valueEstadoCheck(31, 'San Luís Potosí');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="San Luis Potosi:" />
										<s:hidden id="checkEstado31" name="estadosVentas.sanLuisPotosi" value="%{estadosVentas.sanLuisPotosi}" />
										<s:hidden id="idCheckEstado31" name="estadosVentas.idSanLuisPotosi" value="%{estadosVentas.idSanLuisPotosi}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.zacatecas == 'Zacatecas'">
											<s:checkbox id="check32" name="pyMEsZacatecas" value="true" onclick="javascript: valueEstadoCheck(32, 'Zacatecas');"/>
										</s:if>
										<s:else>
											<s:checkbox id="check32" name="pyMEsZacatecas" value="" onclick="javascript: valueEstadoCheck(32, 'Zacatecas');"/>
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Zacatecas:" />
										<s:hidden id="checkEstado32" name="estadosVentas.zacatecas" value="%{estadosVentas.zacatecas}" />
										<s:hidden id="idCheckEstado32" name="estadosVentas.idZacatecas" value="%{estadosVentas.idZacatecas}" />
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
				<s:label cssClass="etiquetaAyuda" value="Información del contacto." />
				<table>
					<tr>
						<td>
							<table>
								<!-- Inicia Información del contacto -->
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="Seleccione el tipo de contacto." />
										<s:hidden name="pyMEs.idContacto1" id="idContacto1" value="%{pyMEs.idContacto1}" />
									</td>
									<td>
										<select id="tipoContacto" onchange="javascript:valorTipoCont(this.value);">
											<s:if test="pyMEs.tipoContacto1 == ''">
												<option selected="selected" value="* Seleccione tipo de contacto">Seleccione tipo de contacto</option>
											</s:if>
											<s:else>
												<option value=""></option>
												<option selected="selected" value="${pyMEs.tipoContacto1}"><s:text name="%{pyMEs.tipoContacto1}" /></option>
											</s:else>
											<option value="Ventas">Ventas</option>
											<option value="Director General">Director General</option>
											<option value="Propietario">Propietario</option>
											<option value="Otro">Otro</option>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div id="otroTipo" style="display: none;">
											<s:label cssClass="etiquetaAyuda" value="Indique otro tipo de contacto " />
											<s:textfield size="23" id="tipoOtro" name="pyMEs.tipoContacto1" maxlength="30"></s:textfield>
										</div>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Nombre(s) :" /></td>
									<td><s:textfield size="30" id="nombreContacto" name="pyMEs.nombreContacto1" maxlength="60"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba el nombre o nombres del contacto sin considerar acentos." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Apellido Paterno :" /></td>
									<td><s:textfield size="30" id="appPat" name="pyMEs.appPaterno1" maxlength="60"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba el apellido paterno del contacto sin considerar acentos." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Apellido Materno :" /></td>
									<td><s:textfield size="30" id="appMat" name="pyMEs.appMaterno1" maxlength="60"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba el apellido materno del contacto sin considerar acentos." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Correo electrónico :" /></td>
									<td><s:textfield size="30" id="correoElectronicoContacto" name="pyMEs.correoElectronicoContacto1" maxlength="100"></s:textfield></td>
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
									<td><s:textfield size="30" id="telContacto" name="pyMEs.telefonoContacto1" onkeypress="return tel(this, event);" maxlength="24"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Incluya su teléfono con clave lada y extensión." /></td>
								</tr>
							</table>
						</td>
						<td>
							<div id="contacto2" ${pyMEs.idContacto2==0?' style="display: none;"':' style="display: block;"'}>
								<table>
									<!-- Inicia Información del SEGUNDO contacto -->
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Seleccione el tipo de contacto." />
											<s:hidden name="pyMEs.idContacto2" id="idContacto2" value="%{pyMEs.idContacto2}" />
										</td>
										<td>
											<select id="tipoContacto2" onchange="javascript:valorTipoCont2(this.value);">
												<s:if test="pyMEs.tipoContacto2 == null || pyMEs.tipoContacto2 == ''">
													<option selected="selected" value="Seleccione tipo de contacto">Seleccione tipo de contacto</option>
												</s:if>
												<s:else>
													<option value=""></option>
													<option selected="selected" value="${pyMEs.tipoContacto2}"><s:text name="%{pyMEs.tipoContacto2}" /></option>
												</s:else>
												<option value="Ventas">Ventas</option>
												<option value="Director General">Director General</option>
												<option value="Propietario">Propietario</option>
												<option value="Otro">Otro</option>
											</select>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div id="otroTipo2" style="display: none;">
												<s:label cssClass="etiquetaAyuda" value="Indique otro tipo de contacto " />
												<s:textfield size="23" id="tipoOtro2" name="pyMEs.tipoContacto2" maxlength="30"></s:textfield>
											</div>
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Nombre(s) :" /></td>
										<td><s:textfield size="30" id="nombreContacto2" name="pyMEs.nombreContacto2" maxlength="60"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba el nombre o nombres del contacto sin considerar acentos." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Apellido Paterno :" /></td>
										<td><s:textfield size="30" id="appPat2" name="pyMEs.appPaterno2" maxlength="60"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba el apellido paterno del contacto sin considerar acentos." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Apellido Materno :" /></td>
										<td><s:textfield size="30" id="appMat2" name="pyMEs.appMaterno2" maxlength="60"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba el apellido materno del contacto sin considerar acentos." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Correo electrónico :" /></td>
										<td><s:textfield size="30" id="correoElectronicoContacto2" name="pyMEs.correoElectronicoContacto2" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Incluya su correo electrónico." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Confime Correo electrónico :" /></td>
										<td><s:textfield size="30" id="comparaCorreoContacto2" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Confirme su correo electrónico." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Teléfono :" /></td>
										<td><s:textfield size="30" id="telContacto2" name="pyMEs.telefonoContacto2" onkeypress="return tel(this, event);" maxlength="24"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" value="Incluya su teléfono con clave lada y extensión." />
											<label class="quitar" onclick="javascript:supContacto();">-eliminar contacto</label>
										</td>
										
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				
				<label id="linkAddContacto" class="agregar" onclick="javascript:showContacto();">+Agregar otro contacto</label>
				
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
					<tr>
						<td>
							<s:label cssClass="etiquetaAyuda" value="Clientes principales." />
						</td>
					</tr>
					<tr>
						<td>
							<label id="linkAddProve" class="agregar" onclick="javascript:showCliente();">+Agregar otro cliente</label>
						</td>
					</tr>
				</table>
				
				<!-- Inicia Registro de Clientes -->
				
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" value="Datos del Cliente Tractora" />
										<s:hidden name="pyMEs.idCliente1" id="idCliente1" value="%{pyMEs.idCliente1}" />
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Cliente:" /></td>
									<td><s:textfield size="25" id="cliente1" name="pyMEs.cliente1" maxlength="100"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Ingrese el nombre del cliente." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Productos que compra el cliente :" /></td>
									<td><s:textfield size="25" id="prodCliente1" name="pyMEs.productosCompra1" maxlength="500"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Describa los principales productos que le vende a la tractora." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Años como proveedor del cliente :" /></td>
									<td><s:textfield size="25" id="aniosProveCliente1" name="pyMEs.aniosProveedor1" maxlength="50"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba los años que lleva como proveedor de la tractora." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Meses como proveedor :" /></td>
									<td><s:textfield size="25" id="mesesProveCliente1" name="pyMEs.mesesProveedor1" maxlength="50"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" value="Escriba los meses que lleva como proveedor de la tractora." />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td>
							<div id="prove2" ${pyMEs.idCliente2==0?' style="display: none;"':' style="display: block;"'}>
								<table>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" value="Ingresar datos del Cliente" />
											<s:hidden name="pyMEs.idCliente2" id="idCliente2" value="%{pyMEs.idCliente2}" />
										</td>
										
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Cliente:" /></td>
										<td><s:textfield size="25" id="cliente2" name="pyMEs.cliente2" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Ingrese el nombre del cliente." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Productos que compra el cliente :" /></td>
										<td><s:textfield size="25" id="prodCliente2" name="pyMEs.productosCompra2" maxlength="500"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Describa los principales productos que le vende a la tractora." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Años como proveedor del cliente :" /></td>
										<td><s:textfield size="25" id="aniosProveCliente2" name="pyMEs.aniosProveedor2" maxlength="50"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba los años que lleva como proveedor de la tractora." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Meses como proveedor :" /></td>
										<td><s:textfield size="25" id="mesesProveCliente2" name="pyMEs.mesesProveedor2" maxlength="50"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" value="Escriba los meses que lleva como proveedor de la tractora." />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<label class="quitar" onclick="javascript:supCliente(2);">-eliminar cliente</label>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div id="prove3" ${pyMEs.idCliente3==0?' style="display: none;"':' style="display: block;"'}>
								<br />
								<br />
								<table>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" value="Ingresar datos del Cliente" />
											<s:hidden name="pyMEs.idCliente3" id="idCliente3" value="%{pyMEs.idCliente3}" />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Cliente:" /></td>
										<td><s:textfield size="25" id="cliente3" name="pyMEs.cliente3" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Ingrese el nombre del cliente." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Productos que compra el cliente :" /></td>
										<td><s:textfield size="25" id="prodCliente3" name="pyMEs.productosCompra3" maxlength="500"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Describa los principales productos que le vende a la tractora." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Años como proveedor del cliente :" /></td>
										<td><s:textfield size="25" id="aniosProveCliente3" name="pyMEs.aniosProveedor3" maxlength="50"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba los años que lleva como proveedor de la tractora." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Meses como proveedor :" /></td>
										<td><s:textfield size="25" id="mesesProveCliente3" name="pyMEs.mesesProveedor3" maxlength="50"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" value="Escriba los meses que lleva como proveedor de la tractora." />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<label class="quitar" onclick="javascript:supCliente(3);">-eliminar cliente</label>
										</td>
									</tr>
								</table>
							</div>
						</td>
						<td>
							<div id="prove4" ${pyMEs.idCliente4==0?' style="display: none;"':' style="display: block;"'}>
								<br />
								<br />
								<table>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" value="Ingresar datos del Cliente" />
											<s:hidden name="pyMEs.idCliente4" id="idCliente4" value="%{pyMEs.idCliente4}" />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Cliente:" /></td>
										<td><s:textfield size="25" id="cliente4" name="pyMEs.cliente4" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Ingrese el nombre del cliente." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Productos que compra el cliente :" /></td>
										<td><s:textfield size="25" id="prodCliente4" name="pyMEs.productosCompra4" maxlength="500"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Describa los principales productos que le vende a la tractora." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Años como proveedor del cliente :" /></td>
										<td><s:textfield size="25" id="aniosProveCliente4" name="pyMEs.aniosProveedor4" maxlength="50"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba los años que lleva como proveedor de la tractora." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Meses como proveedor :" /></td>
										<td><s:textfield size="25" id="mesesProveCliente4" name="pyMEs.mesesProveedor4" maxlength="50"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" value="Escriba los meses que lleva como proveedor de la tractora." />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<label class="quitar" onclick="javascript:supCliente(4);">-eliminar cliente</label>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div id="prove5" ${pyMEs.idCliente5==0?' style="display: none;"':' style="display: block;"'}>
								<br />
								<br />
								<table>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" value="Ingresar datos del Cliente" />
											<s:hidden name="pyMEs.idCliente5" id="idCliente5" value="%{pyMEs.idCliente5}" />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Cliente:" /></td>
										<td><s:textfield size="25" id="cliente5" name="pyMEs.cliente5" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Ingrese el nombre del cliente." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Productos que compra el cliente :" /></td>
										<td><s:textfield size="25" id="prodCliente5" name="pyMEs.productosCompra5" maxlength="500"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Describa los principales productos que le vende a la tractora." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Años como proveedor del cliente :" /></td>
										<td><s:textfield size="25" id="aniosProveCliente5" name="pyMEs.aniosProveedor5" maxlength="50"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Escriba los años que lleva como proveedor de la tractora." /></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="* Meses como proveedor :" /></td>
										<td><s:textfield size="25" id="mesesProveCliente5" name="pyMEs.mesesProveedor5" maxlength="50"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" value="Escriba los meses que lleva como proveedor de la tractora." />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<label class="quitar" onclick="javascript:supCliente(5);">-eliminar cliente</label>
										</td>
									</tr>
								</table>
							</div>
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
			<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td><input class="botonenviar" value="Anterior" type="button" onclick="javascript:document.getElementById('sec5').style.display='none'; javascript:document.getElementById('sec4').style.display='block';" /></td>
						<td><input class="botonenviar" value="Siguiente" type="button" onclick="javascript:return validacion('5');" /></td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
		<!-- Termina Seccion 5 -->
		
		<!-- Inicia Seccion 6 -->
			<div id="sec6" style="display: none;">
				<!-- Inicia Certificaciones y capacitación. -->
				<s:label cssClass="etiquetaAyuda" value="Certificaciones y capacitación." />
				<label id="showCert" class="agregar" onclick="javascript:addCert();">+agregar otra Certificación</label>
				<table>
					<tr>
						<td>
							<div id="cert1">
							<s:hidden name="pyMEs.idCertificacion1" id="idCert1" value="%{pyMEs.idCertificacion1}" />
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Certificación :" /></td>
										<td><s:textfield size="30" id="certificacion1" name="pyMEs.certificacion1" maxlength="150"></s:textfield></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Año de certificación :" /></td>
										<td>
											<s:date name="pyMEs.fechaCertificacion1" id="fCert" format="dd/MM/yyyy" />
											<s:textfield class="calendario" id="ingreso" name="pyMEs.fechaCertificacion1" value="%{fCert}" size="10" maxlength="10" />
									   		<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador" style="cursor: hand" />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Institución que certificó :" /></td>
										<td><s:textfield size="30" id="instCert1" name="pyMEs.institutoCertificador1" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											&nbsp;
										</td>
									</tr>
								</table>
							</div>
						</td>
						<td>
							<div id="cert2" ${pyMEs.idCertificacion2!=0?' style="display: block;"':' style="display: none;"'}>
							<s:hidden name="pyMEs.idCertificacion2" id="idCert2" value="%{pyMEs.idCertificacion2}" />
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Certificación :" /></td>
										<td><s:textfield size="30" id="certificacion2" name="pyMEs.certificacion2" maxlength="150"></s:textfield></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Año de certificación :" /></td>
										<td>
											<s:date name="pyMEs.fechaCertificacion2" id="fCert2" format="dd/MM/yyyy" />
											<s:textfield class="calendario" id="ingreso2" name="pyMEs.fechaCertificacion2" value="%{fCert2}" size="10" maxlength="10" />
									   		<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador2" style="cursor: hand" />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Institución que certificó :" /></td>
										<td><s:textfield size="30" id="instCert2" name="pyMEs.institutoCertificador2" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<label class="quitar" onclick="javascript:supCert( 2 );">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div id="cert3" ${pyMEs.idCertificacion3!=0?' style="display: block;"':' style="display: none;"'}>
							<s:hidden name="pyMEs.idCertificacion3" id="idCert3" value="%{pyMEs.idCertificacion3}" />
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Certificación :" /></td>
										<td><s:textfield size="30" id="certificacion3" name="pyMEs.certificacion3" maxlength="150"></s:textfield></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Año de certificación :" /></td>
										<td>
											<s:date name="pyMEs.fechaCertificacion3" id="fCert3" format="dd/MM/yyyy" />
											<s:textfield class="calendario" id="ingreso3" name="pyMEs.fechaCertificacion3" value="%{fCert3}" size="10" maxlength="10" />
									   		<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador3" style="cursor: hand" />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Institución que certificó :" /></td>
										<td><s:textfield size="30" id="instCert3" name="pyMEs.institutoCertificador3" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<label class="quitar" onclick="javascript:supCert( 3 );">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
						</td>
						<td>
							<div id="cert4" ${pyMEs.idCertificacion4!=0?' style="display: block;"':' style="display: none;"'}>
							<s:hidden name="pyMEs.idCertificacion4" id="idCert4" value="%{pyMEs.idCertificacion4}" />
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Certificación :" /></td>
										<td><s:textfield size="30" id="certificacion4" name="pyMEs.certificacion4" maxlength="150"></s:textfield></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Año de certificación :" /></td>
										<td>
											<s:date name="pyMEs.fechaCertificacion4" id="fCert4" format="dd/MM/yyyy" />
											<s:textfield class="calendario" id="ingreso4" name="pyMEs.fechaCertificacion4" value="%{fCert4}" size="10" maxlength="10" />
									   		<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador4" style="cursor: hand" />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Institución que certificó :" /></td>
										<td><s:textfield size="30" id="instCert4" name="pyMEs.institutoCertificador4" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<label class="quitar" onclick="javascript:supCert( 4 );">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div id="cert5" ${pyMEs.idCertificacion5!=0?' style="display: block;"':' style="display: none;"'}>
							<s:hidden name="pyMEs.idCertificacion5" id="idCert5" value="%{pyMEs.idCertificacion5}" />
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Certificación :" /></td>
										<td><s:textfield size="30" id="certificacion5" name="pyMEs.certificacion5" maxlength="150"></s:textfield></td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Año de certificación :" /></td>
										<td>
											<s:date name="pyMEs.fechaCertificacion5" id="fCert5" format="dd/MM/yyyy" />
											<s:textfield class="calendario" id="ingreso5" name="pyMEs.fechaCertificacion5" value="%{fCert5}" size="10" maxlength="10" />
									   		<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador5" style="cursor: hand" />
										</td>
									</tr>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Institución que certificó :" /></td>
										<td><s:textfield size="30" id="instCert5" name="pyMEs.institutoCertificador5" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<label class="quitar" onclick="javascript:supCert( 5 );">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				
				<!-- Inicia Seleccionar Diplomado -->
				<table>
					<tr>
						<td>
							<s:label cssClass="etiquetaAyuda" value="Seleccionar Diplomado" />
						</td>
					</tr>
					<tr>
						<td>
							<table>
								<tr>
									<td>
										<s:checkbox id="dip1" name="pyMEs.bDiplomadoCcmxUno" value="%{pyMEs.bDiplomadoCcmxUno}" />
										<s:label cssClass="etiquetaCaptura" value="Cultura organizacional y la competitividad de las empresas" />
									</td>
								</tr>
								<tr>
									<td>
										<s:checkbox id="dip2" name="pyMEs.bDiplomadoCcmxDos" value="%{pyMEs.bDiplomadoCcmxDos}" />
										<s:label cssClass="etiquetaCaptura" value="Estrategia Comercial, Imagen y Cadena de Distribución" />
									</td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td>
										<s:checkbox id="dip3" name="pyMEs.bDiplomadoCcmxTres" value="%{pyMEs.bDiplomadoCcmxTres}" />
										<s:label cssClass="etiquetaCaptura" value="Desarrollo de Métodos de Producción Esbeltos y Cadena de Valor" />
									</td>
								</tr>
								<tr>
									<td>
										<s:checkbox id="dip4" name="pyMEs.bDiplomadoCcmxCuatro" value="%{pyMEs.bDiplomadoCcmxCuatro}" />
										<s:label cssClass="etiquetaCaptura" value="Estrategia, Planeación e Innovación" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br />
				
				<!-- Inicia Files -->
				<s:label cssClass="etiquetaCaptura" value="Incluir Archivo(s) Adjunto(s)" />
				<label id="showArchivo" class="agregar" onclick="javascript:otroArchivo();">+agregar otro</label>
				<br />
				
				<table>
					<tr>
						<td>
							<div id="idDivArchivo1Block" ${pyMEs.descArchivo1==null?' style="display: block;"':' style="display: none;"'}>
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" /></td>
										<td><s:textfield id="arch1" size="40" name="pyMEs.descArchivo1" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo1==null?' style="display: block;"':' style="display: none;"'}>
												<s:file id="idCampoArchivo1" name="pyMEs.archivo1"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(1);">-eliminar</label>
											</div>
										</td>
									</tr>
								</table>
							</div>
							
							<div id="idDivArchivo2Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" /></td>
										<td><s:textfield id="arch2" size="40" name="pyMEs.descArchivo2" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo2==null?' style="display: block;"':' style="display: none;"'}>
												<s:file id="idCampoArchivo2" name="pyMEs.archivo2"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(2);">-eliminar</label>
											</div>
										</td>
									</tr>
								</table>
							</div>
							
							<div id="idDivArchivo3Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" /></td>
										<td><s:textfield id="arch3" size="40" name="pyMEs.descArchivo3" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo3==null?' style="display: block;"':' style="display: none;"'}>
												<s:file id="idCampoArchivo3" name="pyMEs.archivo3"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(3);">-eliminar</label>
											</div>
										</td>
									</tr>
								</table>
							</div>
							
							<div id="idDivArchivo4Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" /></td>
										<td><s:textfield id="arch4" size="40" name="pyMEs.descArchivo4" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo4==null?' style="display: block;"':' style="display: none;"'}>
												<s:file id="idCampoArchivo4" name="pyMEs.archivo4"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(4);">-eliminar</label>
											</div>
										</td>
									</tr>
								</table>
							</div>
							
							<div id="idDivArchivo5Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" /></td>
										<td><s:textfield id="arch5" size="40" name="pyMEs.descArchivo5" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo5==null?' style="display: block;"':' style="display: none;"'}>
												<s:file id="idCampoArchivo5" name="pyMEs.archivo5"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(5);">-eliminar</label>
											</div>
										</td>
									</tr>
								</table>
							</div>
							
							<div id="idDivArchivo6Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" /></td>
										<td><s:textfield id="arch6" size="40" name="pyMEs.descArchivo6" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo6==null?' style="display: block;"':' style="display: none;"'}>
												<s:file id="idCampoArchivo6" name="pyMEs.archivo6"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(6);">-eliminar</label>
											</div>
										</td>
									</tr>
								</table>
							</div>
							
							<div id="idDivArchivo7Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" /></td>
										<td><s:textfield id="arch7" size="40" name="pyMEs.descArchivo7" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo7==null?' style="display: block;"':' style="display: none;"'}>
												<s:file id="idCampoArchivo7" name="pyMEs.archivo7"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(7);">-eliminar</label>
											</div>
										</td>
									</tr>
								</table>
							</div>
							
							<div id="idDivArchivo8Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" /></td>
										<td><s:textfield id="arch8" size="40" name="pyMEs.descArchivo8" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo8==null?' style="display: block;"':' style="display: none;"'}>
												<s:file id="idCampoArchivo8" name="pyMEs.archivo8"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(8);">-eliminar</label>
											</div>
										</td>
									</tr>
								</table>
							</div>
							
							<div id="idDivArchivo9Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" /></td>
										<td><s:textfield id="arch9" size="40" name="pyMEs.descArchivo9" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo9==null?' style="display: block;"':' style="display: none;"'}>
												<s:file id="idCampoArchivo9" name="pyMEs.archivo9"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(9);">-eliminar</label>
											</div>
										</td>
									</tr>
								</table>
							</div>
							
							<div id="idDivArchivo10Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" /></td>
										<td><s:textfield id="arch10" size="40" name="pyMEs.descArchivo10" maxlength="100"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo10==null?' style="display: block;"':' style="display: none;"'}>
												<s:file id="idCampoArchivo10" name="pyMEs.archivo10"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(10);">-eliminar</label>
											</div>
										</td>
									</tr>
								</table>
							</div>

							<div ${pyMEs.descArchivo1!=null || pyMEs.descArchivo2!=null || pyMEs.descArchivo3!=null || pyMEs.descArchivo4!=null || pyMEs.descArchivo5!=null || pyMEs.descArchivo6!=null || pyMEs.descArchivo7!=null || pyMEs.descArchivo8!=null || pyMEs.descArchivo9!=null || pyMEs.descArchivo10!=null?' style="display: block;"':' style="display: none;"'}>
								<table>
									<tr>
										<td class="encabezadoTablaResumen" colspan="3" align="center" style="width: 800px;">
											Descripción del o de los archivos adjuntos
										</td>
									</tr>
									<tr>
										<td class="cuerpo2TablaResumen" align="left" style="width: 300px;">&nbsp;Descripción del archivo :</td>
										<td class="cuerpo2TablaResumen" align="left" style="width: 300px;">&nbsp;Descargar archivo adjunto:</td>
										<td class="cuerpo2TablaResumen" align="left" style="width: 150px;">&nbsp;Eliminar archivo :</td>
									</tr>
									<s:if test="pyMEs.archivo1FileName != null">
										<tr id="descArch1">
											<td class="cuerpo1TablaResumen" align="left" style="width: 300px;">${pyMEs.descArchivo1}</td>
											<td class="cuerpo1TablaResumen" align="left" style="width: 300px;"><a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo1}&nameArchivo=${pyMEs.archivo1FileName}&mimeArchivo=${pyMEs.archivo1ContentType}">${pyMEs.archivo1FileName}</a></td>
											<td class="cuerpo1TablaResumen" align="left" style="width: 150px;"><label class="quitar" onclick="javascript:supArchivo(1);">-eliminar</label></td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo2FileName != null">
										<tr id="descArch2">
											<td class="cuerpo2TablaResumen" align="left" style="width: 300px;">${pyMEs.descArchivo2}</td>
											<td class="cuerpo2TablaResumen" align="left" style="width: 300px;"><a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo2}&nameArchivo=${pyMEs.archivo2FileName}&mimeArchivo=${pyMEs.archivo2ContentType}">${pyMEs.archivo2FileName}</a></td>
											<td class="cuerpo2TablaResumen" align="left" style="width: 150px;"><label class="quitar" onclick="javascript:supArchivo(2);">-eliminar</label></td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo3FileName != null">
										<tr id="descArch3">
											<td class="cuerpo1TablaResumen" align="left" style="width: 300px;">${pyMEs.descArchivo3}</td>
											<td class="cuerpo1TablaResumen" align="left" style="width: 300px;"><a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo3}&nameArchivo=${pyMEs.archivo3FileName}&mimeArchivo=${pyMEs.archivo3ContentType}">${pyMEs.archivo3FileName}</a></td>
											<td class="cuerpo1TablaResumen" align="left" style="width: 150px;"><label class="quitar" onclick="javascript:supArchivo(3);">-eliminar</label></td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo4FileName != null">
										<tr id="descArch4">
											<td class="cuerpo2TablaResumen" align="left" style="width: 300px;">${pyMEs.descArchivo4}</td>
											<td class="cuerpo2TablaResumen" align="left" style="width: 300px;"><a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo4}&nameArchivo=${pyMEs.archivo4FileName}&mimeArchivo=${pyMEs.archivo4ContentType}">${pyMEs.archivo4FileName}</a></td>
											<td class="cuerpo2TablaResumen" align="left" style="width: 150px;"><label class="quitar" onclick="javascript:supArchivo(4);">-eliminar</label></td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo5FileName != null">
										<tr id="descArch5">
											<td class="cuerpo1TablaResumen" align="left" style="width: 300px;">${pyMEs.descArchivo5}</td>
											<td class="cuerpo1TablaResumen" align="left" style="width: 300px;"><a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo5}&nameArchivo=${pyMEs.archivo5FileName}&mimeArchivo=${pyMEs.archivo5ContentType}">${pyMEs.archivo5FileName}</a></td>
											<td class="cuerpo1TablaResumen" align="left" style="width: 150px;"><label class="quitar" onclick="javascript:supArchivo(2);">-eliminar</label></td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo6FileName != null">
										<tr id="descArch6">
											<td class="cuerpo2TablaResumen" align="left" style="width: 300px;">${pyMEs.descArchivo6}</td>
											<td class="cuerpo2TablaResumen" align="left" style="width: 300px;"><a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo6}&nameArchivo=${pyMEs.archivo6FileName}&mimeArchivo=${pyMEs.archivo6ContentType}">${pyMEs.archivo6FileName}</a></td>
											<td class="cuerpo2TablaResumen" align="left" style="width: 150px;"><label class="quitar" onclick="javascript:supArchivo(6);">-eliminar</label></td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo7FileName != null">
										<tr id="descArch7">
											<td class="cuerpo1TablaResumen" align="left" style="width: 300px;">${pyMEs.descArchivo7}</td>
											<td class="cuerpo1TablaResumen" align="left" style="width: 300px;"><a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo7}&nameArchivo=${pyMEs.archivo7FileName}&mimeArchivo=${pyMEs.archivo7ContentType}">${pyMEs.archivo7FileName}</a></td>
											<td class="cuerpo1TablaResumen" align="left" style="width: 150px;"><label class="quitar" onclick="javascript:supArchivo(7);">-eliminar</label></td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo8FileName != null">
										<tr id="descArch8">
											<td class="cuerpo2TablaResumen" align="left" style="width: 300px;">${pyMEs.descArchivo8}</td>
											<td class="cuerpo2TablaResumen" align="left" style="width: 300px;"><a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo8}&nameArchivo=${pyMEs.archivo8FileName}&mimeArchivo=${pyMEs.archivo8ContentType}">${pyMEs.archivo8FileName}</a></td>
											<td class="cuerpo2TablaResumen" align="left" style="width: 150px;"><label class="quitar" onclick="javascript:supArchivo(8);">-eliminar</label></td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo9FileName != null">
										<tr id="descArch9">
											<td class="cuerpo1TablaResumen" align="left" style="width: 300px;">${pyMEs.descArchivo9}</td>
											<td class="cuerpo1TablaResumen" align="left" style="width: 300px;"><a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo9}&nameArchivo=${pyMEs.archivo9FileName}&mimeArchivo=${pyMEs.archivo9ContentType}">${pyMEs.archivo9FileName}</a></td>
											<td class="cuerpo1TablaResumen" align="left" style="width: 150px;"><label class="quitar" onclick="javascript:supArchivo(9);">-eliminar</label></td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo10FileName != null">
										<tr id="descArch10">
											<td class="cuerpo2TablaResumen" align="left" style="width: 300px;">${pyMEs.descArchivo10}</td>
											<td class="cuerpo2TablaResumen" align="left" style="width: 300px;"><a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo10}&nameArchivo=${pyMEs.archivo10FileName}&mimeArchivo=${pyMEs.archivo10ContentType}">${pyMEs.archivo10FileName}</a></td>
											<td class="cuerpo2TablaResumen" align="left" style="width: 150px;"><label class="quitar" onclick="javascript:supArchivo(10);">-eliminar</label></td>
										</tr>
									</s:if>
								</table>
							</div>
							
							<div>
								<s:label cssClass="etiquetaAyuda" value="Indique el o los archivos que serán incluidos. Máximo 2MB (.pdf .doc .png)" />
								<br />
							</div>
						</td>
					</tr>
				</table>
				<br />

				<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td><input class="botonenviar" value="Anterior" type="button" onclick="javascript:document.getElementById('sec6').style.display='none'; javascript:document.getElementById('sec5').style.display='block';" /></td>
						<td><input class="botonenviar" value="Siguiente" type="button" onclick="javascript:return validacion('6');" /></td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
		<!-- Termina Seccion 6 -->
		
		
		<!-- Inicia Seccion 7 -->
			<div id="sec7" style="display: none;">
				<!-- Inicia Indicadores -->
				<s:label cssClass="etiquetaAyuda" value="Indicadores de resultados." />
				
				<table>
					<tr>
						<td style="width: 400px;">
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Ventas o ingresos  acumulados (antes) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="ingresosAnt" size="60" name="indicadores.ingresosAntes" maxlength="100"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Numero de clientes (antes) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="clientesAnt" size="60" name="indicadores.clientesAntes" maxlength="100"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Numero de empleados (antes) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="empleadosAnt" size="60" name="indicadores.empleadosAntes" maxlength="100"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="% Egresos / Ventas (antes) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="egresosAnt" size="60" name="indicadores.egresosAntes" maxlength="100"></s:textfield></td>
								</tr>
							</table>
						</td>
						<td style="width: 400px;">
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Ventas o ingresos  acumulados (después) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="ingresosDesp" size="60" name="indicadores.ingresosDespues" maxlength="100"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Numero de clientes (después) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="clientesDesp" size="60" name="indicadores.clientesDespues" maxlength="100"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Número de empleados (después) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="empleadosDesp" size="60" name="indicadores.empleadosDespues" maxlength="100"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="% Egresos / Ventas (después) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="egresosDesp" size="60" name="indicadores.egresosDespues" maxlength="100"></s:textfield></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				
				<!-- Inicia Requerimentos de compra -->
				<br />
				<table>
					<tr>
						<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Requerimientos de compra." /></td>
					</tr>
					<tr>
						<td colspan="2"><s:label cssClass="etiquetaCaptura" value="* ¿Desea recibir requerimientos de compra?" /></td>
					</tr>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="Si" />
							<s:checkbox id="reqSi" name="pyMEs.bRecibeRequerimientosCompra" onclick="javascript:showCat();" value="%{pyMEs.bRecibeRequerimientosCompra}" />
						</td>
						<td>
							<s:label cssClass="etiquetaCaptura" value="No" />
							<s:if test="pyMEs.bRecibeRequerimientosCompra == false">
								<s:checkbox id="reqNo" name="pyMEs.reqNo" onclick="javascript:recibeReqNo();" value="true" />
							</s:if>
							<s:else>
								<s:checkbox id="reqNo" name="pyMEs.reqNo" onclick="javascript:recibeReqNo();" value="" />
							</s:else>
							
						</td>
					</tr>
				</table>
				<br />
				<table id="showCatalogos" ${pyMEs.bRecibeRequerimientosCompra==false?' style="display: none;"':' style="display: block;"'}>
					<tr>
						<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Seleccione la industria o industrias a las que se dedica su empresa" /></td>
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
			<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td>
							<input class="botonenviar" value="Anterior" type="button" onclick="javascript:document.getElementById('sec7').style.display='none'; javascript:document.getElementById('sec6').style.display='block';" />
						</td>
						<td><s:submit cssClass="botonenviar" value="Actualizar PyME" /></td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
		<!-- Termina Seccion 7 -->		
		
		<!-- Bloque Hidden's -->			

			<s:hidden name="domicilios.idDomicilio" id="idDomicilio" value="%{domicilios.idDomicilio}" />
			<s:hidden name="indicadores.idIndicador" id="idIndicador" value="%{indicadores.idIndicador}" />

		<!-- Bloque Hidden's -->
		
		</s:form>
		
		<!-- EXPEDIENTE PYME -->
		<div id="resumenPyME" ${pyMEs.personalidadJuridica!=null?' style="display: block;"':' style="display: none;"' }>
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
				<s:if test="estadosVentas.mexico != null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Estado donde puede vender sus productos:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${estadosVentas.mexico}</s:label></td>
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
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Dirección:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${domicilios.calle} ${domicilios.numExt} ${domicilios.numInt} ${domicilios.piso} ${domicilios.colonia} ${domicilios.delegacion} ${domicilios.estado} ${domicilios.codigoPostal}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;RFC:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.rfc}</s:label></td>
				</tr>
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
				
				<!-- ARCHIVOS -->
				<s:if test="pyMEs.idArchivo1!=0">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 1:</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo1}&nameArchivo=${pyMEs.archivo1FileName}&mimeArchivo=${pyMEs.archivo1ContentType}">${pyMEs.archivo1FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo2!=0">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 2:</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo2}&nameArchivo=${pyMEs.archivo2FileName}&mimeArchivo=${pyMEs.archivo2ContentType}">${pyMEs.archivo2FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo3!=0">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 3:</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo3}&nameArchivo=${pyMEs.archivo3FileName}&mimeArchivo=${pyMEs.archivo3ContentType}">${pyMEs.archivo3FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo4!=0">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 4:</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo4}&nameArchivo=${pyMEs.archivo4FileName}&mimeArchivo=${pyMEs.archivo4ContentType}">${pyMEs.archivo4FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo5!=0">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 5:</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo5}&nameArchivo=${pyMEs.archivo5FileName}&mimeArchivo=${pyMEs.archivo5ContentType}">${pyMEs.archivo5FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo6!=0">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 6:</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo6}&nameArchivo=${pyMEs.archivo6FileName}&mimeArchivo=${pyMEs.archivo6ContentType}">${pyMEs.archivo6FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo7!=0">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 7:</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo7}&nameArchivo=${pyMEs.archivo7FileName}&mimeArchivo=${pyMEs.archivo7ContentType}">${pyMEs.archivo7FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo8!=0">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 8:</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo8}&nameArchivo=${pyMEs.archivo8FileName}&mimeArchivo=${pyMEs.archivo8ContentType}">${pyMEs.archivo8FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo9!=0">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 9:</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo9}&nameArchivo=${pyMEs.archivo9FileName}&mimeArchivo=${pyMEs.archivo9ContentType}">${pyMEs.archivo9FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo10!=0">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 10:</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo10}&nameArchivo=${pyMEs.archivo10FileName}&mimeArchivo=${pyMEs.archivo10ContentType}">${pyMEs.archivo10FileName}</a>
						</td>
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
						value="Modificar"
						type="button"
						onclick="javascript: modificar();" /></td>
					<td style="width: 450px;"></td>
				</tr>
			</table>
		</div>
	</fieldset>
		<script type="text/javascript">
			calendario();
			calendario2();
			calendario3();
			calendario4();
			calendario5();
		</script>
</body>
</html>