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
		<s:label value="Actualizar datos PyME" />
		<br /> <br />
		<s:label cssClass="camposObligatorios"
			value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
	</legend>
		<br />

		<s:form action="pymeInformacionSave" namespace="/pymes" enctype="multipart/form-data" method="post" theme="simple" onsubmit="return validacion('6')">
		
		<!-- Inicia Seccion 1 -->
			<div id="sec1">
				<s:if test="pyMEs.personalidadJuridica == 'null'">
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
								<s:if test="pyMEs.personalidadJuridica == 'null'">
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
									<td><s:label cssClass="etiquetaCaptura" value="Nombre comercial:" /></td>
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
										<select id="estado" name="domicilios.estado">
											<s:if test="domicilios.estado == null">
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
										<s:checkbox id="sector1" name="pyMEs.bPrimerNivel" value="%{pyMEs.bPrimerNivel}" />
										<s:label cssClass="etiquetaCaptura" value="Sector de Servicios:" />
									</td>
								</tr>
								<tr>
									<td>
										<s:checkbox id="sector2" name="pyMEs.bSegundoNivel" value="%{pyMEs.bSegundoNivel}" />
										<s:label cssClass="etiquetaCaptura" value="Sector Comercial:" />
									</td>
								</tr>
								<tr>
									<td>
										<s:checkbox id="sector3" name="pyMEs.bTercerNivel" value="%{pyMEs.bTercerNivel}" />
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
											<option value="menor a $500,000">menor a $500,000</option>
											<option value="de $500,000 a $5,000,000">de $500,000 a $5,000,000</option>
											<option value="mayor a $5,000,000">mayor a $5,000,000</option>
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
				<div id="idProd2" ${pyMEs.producto2==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto2" id="idProd2" value="%{pyMEs.idProducto2}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales2" name="pyMEs.producto2" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(2);">-eliminar producto</label>
				</div>
				<div id="idProd3" ${pyMEs==null || pyMEs.producto3==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto3" id="idProd3" value="%{pyMEs.idProducto3}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales3" name="pyMEs.producto3" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(3);">-eliminar producto</label>
				</div>
				<div id="idProd4" ${pyMEs==null || pyMEs.producto4==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto4" id="idProd4" value="%{pyMEs.idProducto4}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales4" name="pyMEs.producto4" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(4);">-eliminar producto</label>
				</div>
				<div id="idProd5" ${pyMEs==null || pyMEs.producto5==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto5" id="idProducto5" value="%{pyMEs.idProducto5}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales5" name="pyMEs.producto5" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(5);">-eliminar producto</label>
				</div>
				<div id="idProd6" ${pyMEs==null || pyMEs.producto6==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto6" id="idProducto6" value="%{pyMEs.idProducto6}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales6" name="pyMEs.producto6" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(6);">-eliminar producto</label>
				</div>
				<div id="idProd7" ${pyMEs==null || pyMEs.producto7==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto7" id="idProducto7" value="%{pyMEs.idProducto7}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales7" name="pyMEs.producto7" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(7);">-eliminar producto</label>
				</div>
				<div id="idProd8" ${pyMEs==null || pyMEs.producto8==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto8" id="idProducto8" value="%{pyMEs.idProducto8}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales8" name="pyMEs.producto8" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(8);">-eliminar producto</label>
				</div>
				<div id="idProd9" ${pyMEs==null || pyMEs.producto9==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto9" id="idProducto9" value="%{pyMEs.idProducto9}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales9" name="pyMEs.producto9" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(9);">-eliminar producto</label>
				</div>
				<div id="idProd10" ${pyMEs==null || pyMEs.producto10==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto10" id="idProducto10" value="%{pyMEs.idProducto10}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales10" name="pyMEs.producto10" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(10);">-eliminar producto</label>
				</div>
				<div id="idProd11" ${pyMEs==null || pyMEs.producto11==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto11" id="idProducto11" value="%{pyMEs.idProducto11}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales11" name="pyMEs.producto11" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(11);">-eliminar producto</label>
				</div>
				<div id="idProd12" ${pyMEs==null || pyMEs.producto12==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto12" id="idProducto12" value="%{pyMEs.idProducto12}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales12" name="pyMEs.producto12" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(12);">-eliminar producto</label>
				</div>
				<div id="idProd13" ${pyMEs==null || pyMEs.producto13==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto13" id="idProducto13" value="%{pyMEs.idProducto13}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales13" name="pyMEs.producto13" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(13);">-eliminar producto</label>
				</div>
				<div id="idProd14" ${pyMEs==null || pyMEs.producto14==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto14" id="idProducto14" value="%{pyMEs.idProducto14}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales14" name="pyMEs.producto14" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(14);">-eliminar producto</label>
				</div>
				<div id="idProd15" ${pyMEs==null || pyMEs.producto15==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto15" id="idProducto15" value="%{pyMEs.idProducto15}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales15" name="pyMEs.producto15" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(15);">-eliminar producto</label>
				</div>
				<div id="idProd16" ${pyMEs==null || pyMEs.producto16==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto16" id="idProducto16" value="%{pyMEs.idProducto16}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales16" name="pyMEs.producto16" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(16);">-eliminar producto</label>
				</div>
				<div id="idProd17" ${pyMEs==null || pyMEs.producto17==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto17" id="idProducto17" value="%{pyMEs.idProducto17}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales17" name="pyMEs.producto17" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(17);">-eliminar producto</label>
				</div>
				<div id="idProd18" ${pyMEs==null || pyMEs.producto18==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto18" id="idProducto18" value="%{pyMEs.idProducto18}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales18" name="pyMEs.producto18" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(18);">-eliminar producto</label>
				</div>
				<div id="idProd19" ${pyMEs==null || pyMEs.producto19==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto19" id="idProducto19" value="%{pyMEs.idProducto19}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales19" name="pyMEs.producto19" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(19);">-eliminar producto</label>
				</div>
				<div id="idProd20" ${pyMEs==null || pyMEs.producto20==null?' style="display: none;"':' style="display: block;"'}>
					<s:hidden name="pyMEs.idProducto20" id="idProducto20" value="%{pyMEs.idProducto20}" />
					<s:label cssClass="etiquetaCaptura" value="Producto:" />
					<s:textfield size="60" id="prodPrincipales20" name="pyMEs.producto20" maxlength="500"></s:textfield>
					<label class="agregar" onclick="javascript: delProd(20);">-eliminar producto</label>
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
										<s:checkbox id="check1" name="pyMEs.bAguascalientes" value="%{pyMEs.bAguascalientes}" />
										<s:label cssClass="etiquetaCaptura" value="Aguascalientes:" />
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check2" name="pyMEs.bDistritoFederal" value="%{pyMEs.bDistritoFederal}" />
										<s:label cssClass="etiquetaCaptura" value="Distrito Federal:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check3" name="pyMEs.bMorelos" value="%{pyMEs.bMorelos}" />
										<s:label cssClass="etiquetaCaptura" value="Morelos:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check4" name="pyMEs.bSinaloa" value="%{pyMEs.bSinaloa}" />
										<s:label cssClass="etiquetaCaptura" value="Sinaloa:" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="check5" name="pyMEs.bBajaCaliforniaNorte" value="%{pyMEs.bBajaCaliforniaNorte}" />
										<s:label cssClass="etiquetaCaptura" value="Baja California Norte:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check6" name="pyMEs.bDurango" value="%{pyMEs.bDurango}" />
										<s:label cssClass="etiquetaCaptura" value="Durango:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check7" name="pyMEs.bNayarit" value="%{pyMEs.bNayarit}" />
										<s:label cssClass="etiquetaCaptura" value="Nayarit:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check8" name="pyMEs.bSonora" value="%{pyMEs.bSonora}" />
										<s:label cssClass="etiquetaCaptura" value="Sonora:" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="check9" name="pyMEs.bBajaCaliforniaSur" value="%{pyMEs.bBajaCaliforniaSur}" />
										<s:label cssClass="etiquetaCaptura" value="Baja California Sur:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check10" name="pyMEs.bGuanajuato" value="%{pyMEs.bGuanajuato}" />
										<s:label cssClass="etiquetaCaptura" value="Guanajuato:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check11" name="pyMEs.bNuevoLeon" value="%{pyMEs.bNuevoLeon}" />
										<s:label cssClass="etiquetaCaptura" value="Nuevo León:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check12" name="pyMEs.bTabasco" value="%{pyMEs.bTabasco}" />
										<s:label cssClass="etiquetaCaptura" value="Tabasco:" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="check13" name="pyMEs.bCampeche" value="%{pyMEs.bCampeche}" />
										<s:label cssClass="etiquetaCaptura" value="Campeche:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check14" name="pyMEs.bGuerrero" value="%{pyMEs.bGuerrero}" />
										<s:label cssClass="etiquetaCaptura" value="Guerrero:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check15" name="pyMEs.bOaxaca" value="%{pyMEs.bOaxaca}" />
										<s:label cssClass="etiquetaCaptura" value="Oaxaca:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check16" name="pyMEs.bTamaulipas" value="%{pyMEs.bTamaulipas}" />
										<s:label cssClass="etiquetaCaptura" value="Tamaulipas:" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="check17" name="pyMEs.bChiapas" value="%{pyMEs.bChiapas}" />
										<s:label cssClass="etiquetaCaptura" value="Chiapas:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check18" name="pyMEs.bHidalgo" value="%{pyMEs.bHidalgo}" />
										<s:label cssClass="etiquetaCaptura" value="Hidalgo:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check19" name="pyMEs.bPuebla" value="%{pyMEs.bPuebla}" />
										<s:label cssClass="etiquetaCaptura" value="Puebla:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check20" name="pyMEs.bTlaxcala" value="%{pyMEs.bTlaxcala}" />
										<s:label cssClass="etiquetaCaptura" value="Tlaxcala:" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="check21" name="pyMEs.bChihuahua" value="%{pyMEs.bChihuahua}" />
										<s:label cssClass="etiquetaCaptura" value="Chihuahua:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check22" name="pyMEs.bJalisco" value="%{pyMEs.bJalisco}" />
										<s:label cssClass="etiquetaCaptura" value="Jalisco:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check23" name="pyMEs.bQueretaro" value="%{pyMEs.bQueretaro}" />
										<s:label cssClass="etiquetaCaptura" value="Quertaro:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check24" name="pyMEs.bVeracruz" value="%{pyMEs.bVeracruz}" />
										<s:label cssClass="etiquetaCaptura" value="Veracruz:" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="check25" name="pyMEs.bCoahuila" value="%{pyMEs.bCoahuila}" />
										<s:label cssClass="etiquetaCaptura" value="Coahuila:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check26" name="pyMEs.bMexico" value="%{pyMEs.bMexico}" />
										<s:label cssClass="etiquetaCaptura" value="México:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check27" name="pyMEs.bQuintanaRoo" value="%{pyMEs.bQuintanaRoo}" />
										<s:label cssClass="etiquetaCaptura" value="Quintana Roo:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check28" name="pyMEs.bYucatan" value="%{pyMEs.bYucatan}" />
										<s:label cssClass="etiquetaCaptura" value="Yucatán:" />	
									</td>
								</tr>
								<tr>
									<td style="width: 180px;">
										<s:checkbox id="check29" name="pyMEs.bColima" value="%{pyMEs.bColima}" />
										<s:label cssClass="etiquetaCaptura" value="Colima:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check30" name="pyMEs.bMichoacan" value="%{pyMEs.bMichoacan}" />
										<s:label cssClass="etiquetaCaptura" value="Michoacán:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check31" name="pyMEs.bSanLuisPotosi" value="%{pyMEs.bSanLuisPotosi}" />
										<s:label cssClass="etiquetaCaptura" value="San Luis Potosi:" />	
									</td>
									<td style="width: 180px;">
										<s:checkbox id="check32" name="pyMEs.bZacatecas" value="%{pyMEs.bZacatecas}" />
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
											<s:if test="pyMEs.tipoContacto1 == null">
												<option selected="selected" value="Seleccione tipo de contacto">Seleccione tipo de contacto</option>
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
									<td><s:textfield size="30" id="telContacto" name="pyMEs.telefonoContacto1" maxlength="20"></s:textfield></td>
								</tr>
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Incluya su telefono con clave lada y Extensión." /></td>
								</tr>
							</table>
						</td>
						<td>
							<div id="contacto2" style="display: none;">
								<table>
									<!-- Inicia Información del SEGUNDO contacto -->
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Seleccione el tipo de contacto." />
											<s:hidden name="pyMEs.idContacto2" id="idContacto2" value="%{pyMEs.idContacto2}" />
										</td>
										<td>
											<select id="tipoContacto2" onchange="javascript:valorTipoCont2(this.value);">
												<s:if test="pyMEs.tipoContacto2 == null">
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
										<td><s:textfield size="30" id="telContacto2" name="pyMEs.telefonoContacto2" maxlength="20"></s:textfield></td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" value="Incluya su telefono con clave lada y Extensión." />
											<label class="agregar" onclick="javascript:supContacto();">-eliminar contacto</label>
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
										<s:label cssClass="etiquetaAyuda" value="Ingresar datos del Cliente" />
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
							</table>
						</td>
						<td>
							<div id="prove2" ${pyMEs.cliente2==null?' style="display: none;"':' style="display: block;"'}>
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
											<label class="agregar" onclick="javascript:supCliente(2);">-eliminar cliente</label>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div id="prove3" ${pyMEs.cliente3==null?' style="display: none;"':' style="display: block;"'}>
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
											<label class="agregar" onclick="javascript:supCliente(3);">-eliminar cliente</label>
										</td>
									</tr>
								</table>
							</div>
						</td>
						<td>
							<div id="prove4" ${pyMEs.cliente4==null?' style="display: none;"':' style="display: block;"'}>
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
											<label class="agregar" onclick="javascript:supCliente(4);">-eliminar cliente</label>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div id="prove5" ${pyMEs.cliente5==null?' style="display: none;"':' style="display: block;"'}>
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
											<label class="agregar" onclick="javascript:supCliente(5);">-eliminar cliente</label>
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
				<table>
					<tr>
						<td>
							<table>
								<!-- Inicia Certificaciones y capacitación. -->
								<tr>
									<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Certificaciones y capacitación." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Certificación :" /></td>
									<td><s:textfield size="30" id="certificación" name="certificaciones.certificacion" maxlength="150"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Año de certificación :" /></td>
									<td>
										<s:date name="certificaciones.fechaCertificacion" id="fCert" format="dd/MM/yyyy" />
										<s:textfield class="calendario" id="ingreso" name="certificaciones.fechaCertificacion" value="%{fCert}" size="10" maxlength="10" />
								   		<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador" style="cursor: hand" />
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Institución que certificó :" /></td>
									<td><s:textfield size="30" id="instCert" name="pyMEs.institutoCertificador" maxlength="100"></s:textfield></td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td><s:label cssClass="etiquetaAyuda" value="Seleccionar Diplomado" /></td>
								</tr>
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
				<br />
				
				<table>
					<tr>
						<td>
							<div id="idDivArchivo1Block" ${pyMEs.archivo1FileName==null?' style="display: block;"':' style="display: none;"'}>
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
										</td>
										<td>
											<s:textfield id="" size="40" name="pyMEs.descArchivo1" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:file id="idCampoArchivo1" name="pyMEs.archivo1"></s:file>
										</td>
									</tr>
								</table>
							</div>
							<div id="idDivArchivo1None" ${pyMEs.archivo1FileName==null?' style="display: none;"':' style="display: block;"'}>
								<label class="resultado">${pyMEs.archivo1FileName}<br /></label>
							</div>
							
							<div id="idDivArchivo2Block" ${pyMEs.archivo2FileName==null?' style="display: none;"':' style="display: none;"'}>
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
										</td>
										<td>
											<s:textfield id="" size="40" name="pyMEs.descArchivo2" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:file id="idCampoArchivo2" name="pyMEs.archivo2"></s:file>
											<label class="agregar" onclick="javascript:supArchivo(2);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
							<div id="idDivArchivo2None" ${pyMEs==null || pyMEs.archivo2FileName==null?' style="display: none;"':' style="display: block;"'}>
								<label class="resultado">${pyMEs.archivo2FileName}<br /></label>
							</div>
							
							<div id="idDivArchivo3Block" ${pyMEs.archivo3FileName==null?' style="display: none;"':' style="display: none;"'}>
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
										</td>
										<td>
											<s:textfield id="" size="40" name="pyMEs.descArchivo3" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:file id="idCampoArchivo3" name="pyMEs.archivo3"></s:file>
											<label class="agregar" onclick="javascript:supArchivo(3);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
							<div id="idDivArchivo3None" ${pyMEs==null || pyMEs.archivo3FileName==null?' style="display: none;"':' style="display: block;"'}>
								<label class="resultado"> ${pyMEs.archivo3FileName}<br /></label>
							</div>
							
							<div id="idDivArchivo4Block" ${pyMEs.archivo4FileName==null?' style="display: none;"':' style="display: none;"'}>
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
										</td>
										<td>
											<s:textfield id="" size="40" name="pyMEs.descArchivo4" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:file id="idCampoArchivo4" name="requerimientos.archivo4"></s:file>
											<label class="agregar" onclick="javascript:supArchivo(4);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
							<div id="idDivArchivo4None" ${pyMEs==null || pyMEs.archivo4FileName==null?' style="display: none;"':' style="display: block;"'}>
								<label class="resultado"> ${pyMEs.archivo4FileName}<br /></label>
							</div>
							
							<div id="idDivArchivo5Block" ${pyMEs.archivo5FileName==null?' style="display: none;"':' style="display: none;"'}>
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
										</td>
										<td>
											<s:textfield id="" size="40" name="pyMEs.descArchivo5" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:file id="idCampoArchivo5" name="pyMEs.archivo5"></s:file>
											<label class="agregar" onclick="javascript:supArchivo(5);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
							<div id="idDivArchivo5None" ${pyMEs==null || pyMEs.archivo5FileName==null?' style="display: none;"':' style="display: block;"'}>
								<label class="resultado"> ${pyMEs.archivo5FileName}<br /></label>
							</div>
							
							<div id="idDivArchivo6Block" ${pyMEs.archivo6FileName==null?' style="display: none;"':' style="display: none;"'}>
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
										</td>
										<td>
											<s:textfield id="" size="40" name="pyMEs.descArchivo6" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:file id="idCampoArchivo6" name="pyMEs.archivo6"></s:file>
											<label class="agregar" onclick="javascript:supArchivo(6);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
							<div id="idDivArchivo6None" ${pyMEs==null || pyMEs.archivo6FileName==null?' style="display: none;"':' style="display: block;"'}>
								<label class="resultado"> ${pyMEs.archivo6FileName}<br /></label>
							</div>
							
							<div id="idDivArchivo7Block" ${pyMEs.archivo7FileName==null?' style="display: none;"':' style="display: none;"'}>
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
										</td>
										<td>
											<s:textfield id="" size="40" name="pyMEs.descArchivo7" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:file id="idCampoArchivo7" name="pyMEs.archivo7"></s:file>
											<label class="agregar" onclick="javascript:supArchivo(7);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
							<div id="idDivArchivo7None" ${pyMEs==null || pyMEs.archivo7FileName==null?' style="display: none;"':' style="display: block;"'}>
								<label class="resultado"> ${pyMEs.archivo7FileName}<br /></label>
							</div>
							
							<div id="idDivArchivo8Block" ${pyMEs.archivo8FileName==null?' style="display: none;"':' style="display: none;"'}>
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
										</td>
										<td>
											<s:textfield id="" size="40" name="pyMEs.descArchivo8" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:file id="idCampoArchivo8" name="pyMEs.archivo8"></s:file>
											<label class="agregar" onclick="javascript:supArchivo(8);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
							<div id="idDivArchivo8None" ${pyMEs==null || pyMEs.archivo8FileName==null?' style="display: none;"':' style="display: block;"'}>
								<label class="resultado"> ${pyMEs.archivo8FileName}<br /></label>
							</div>
							
							<div id="idDivArchivo9Block" ${pyMEs.archivo9FileName==null?' style="display: none;"':' style="display: none;"'}>
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
										</td>
										<td>
											<s:textfield id="" size="40" name="pyMEs.descArchivo9" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:file id="idCampoArchivo9" name="pyMEs.archivo9"></s:file>
											<label class="agregar" onclick="javascript:supArchivo(9);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
							<div id="idDivArchivo9None" ${pyMEs==null || pyMEs.archivo9FileName==null?' style="display: none;"':' style="display: block;"'}>
								<label class="resultado"> ${pyMEs.archivo9FileName}<br /></label>
							</div>
							
							<div id="idDivArchivo10Block" ${pyMEs.archivo10FileName==null?' style="display: none;"':' style="display: none;"'}>
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
										</td>
										<td>
											<s:textfield id="" size="40" name="pyMEs.descArchivo10" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:file id="idCampoArchivo10" name="pyMEs.archivo10"></s:file>
											<label class="agregar" onclick="javascript:supArchivo(10);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
							<div id="idDivArchivo10None" ${pyMEs==null || pyMEs.archivo10FileName==null?' style="display: none;"':' style="display: block;"'}>
								<label class="resultado"> ${pyMEs.archivo10FileName}<br /></label>
							</div>
							<div>
								<s:label cssClass="etiquetaAyuda" value="Indique el o los archivos que serán incluidos. Máximo 2MB (.pdf .doc .png)" />
								<br />
							</div>
						</td>
					</tr>
				</table>
				<br />
				
				<!-- Inicia Requerimentos de compra -->
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
							<s:checkbox id="reqNo" name="pyMEs.reqNo" onclick="javascript:showCat();" value="" />
						</td>
					</tr>
				</table>
				<br />
				<table id="showCatalogos" style="display:none;">
					<tr>
						<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Seleccione la industria o industrias a las que se dedica su empresa" /></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="Catálogo SCIAN 1er Nivel" /></td>
						<td><s:checkbox id="cat1" name="pyMEs.bCat1" value="%{pyMEs.bCat1}" /></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura" value="Catálogo SCIAN 2do Nivel" /></td>
						<td><s:checkbox id="cat2" name="pyMEs.bCat2" value="%{pyMEs.bCat2}" /></td>
					</tr>
					<tr>
						<td><s:label cssClass="etiquetaCaptura"	value="Catálogo SCIAN 3er Nivel" /></td>
						<td><s:checkbox id="cat3" name="pyMEs.bCat3" value="%{pyMEs.bCat3}" /></td>
					</tr>
				</table>
				
			<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td>
							<input class="botonenviar" value="Anterior" type="button" onclick="javascript:document.getElementById('sec6').style.display='none'; javascript:document.getElementById('sec5').style.display='block';" />
						</td>
						<td><s:submit cssClass="botonenviar" value="Actualizar PyME" /></td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
		<!-- Termina Seccion 6 -->	
		
		<!-- Bloque Hidden's -->			
			<s:hidden name="domicilios.idDomicilio" id="idDomicilio" value="%{domicilios.idDomicilio}" />
			<s:hidden name="certificaciones.idCertificado" id="idCertificado" value="%{certificaciones.idCertificado}" />
				
			
		<!-- Bloque Hidden's -->
		
		</s:form>
	</fieldset>
		<script type="text/javascript">
			calendario();
		</script>
</body>
</html>