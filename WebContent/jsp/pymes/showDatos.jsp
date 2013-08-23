<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/css/calendario.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/pymes.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar-es.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar-setup.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ayudas.js"></script>
</head>

<body>
	<script type="text/javascript">
		document.getElementById('workingContainer').style.margin = '-195px auto 0 250px';
	</script>
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

	<fieldset id="requerimientos">
		<s:form action="pymeInformacionSave" namespace="/pymes" enctype="multipart/form-data" method="post" theme="simple" onsubmit="return validacion('7')">
			<s:hidden name="cveScian" id="idCveSci" value="%{cveScian}" />
			<s:hidden name="pyMEs.desactivar" id="idDesactivar" value="" />
			<s:hidden name="pyMEs.nombreAcepta" id="idNomAcepta" value="" />
			<s:hidden name="pyMEs.apellidoPaternoAcepta" id="idApPaAcepta" value="" />
			<s:hidden name="pyMEs.apellidoMaternoAcepta" id="idApMaAcepta" value="" />
			<!-- Inicia Seccion 1 -->
			<div id="sec1" ${pyMEs.personalidadJuridica==null? ' style="display: block;" ':' style="display: none;"' }>
				<legend>
					<s:label value="Actualizar datos PyME" />
					<br /><br />
					<s:label cssClass="camposObligatorios" value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
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
						<td>
							<s:label cssClass="etiquetaCaptura" value="* Seleccione Persona Moral o Persona Fisica" />
						</td>
						<td>
							<select id="personalidadJuridica" name="pyMEs.personalidadJuridica">
								<option value="-- Seleccione --">-- Seleccione --</option>
								<option ${pyMEs.personalidadJuridica == 'Persona Moral' ? ' selected="selected" ' : ''} value="Persona Moral">Persona Moral</option>
								<option ${pyMEs.personalidadJuridica == 'Persona Fisica' ? ' selected="selected" ' : ''} value="Persona Fisica">Persona Física</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="RFC:" />
						</td>
						<td>
							<s:textfield size="60" id="rfc" name="pyMEs.rfc" maxlength="20"
							onfocus="javascript:ayudasHelp(0);" onblur="javascript:ayudasHelpBlo(0);"></s:textfield>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
							<s:label cssClass="etiquetaAyuda" id="ayudasDisplay0" style="display:none; margin-top:5px;"
								value="Escriba su RFC con homoclave." />
						</td>
					</tr>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="* Correo electrónico:" />
						</td>
						<td>
							<s:textarea id="correoElectronico" rows="1" cols="40" disabled="true" cssClass="resultado" style="resize: none;" 
							value="%{pyMEs.correoElectronico}" onfocus="javascript:ayudasHelp(1);" onblur="javascript:ayudasHelpBlo(1);"/>
							<s:hidden name="pyMEs.correoElectronico" id="correoElectronico" value="%{pyMEs.correoElectronico}" />
						</td>
					</tr>
				</table>
				<div style="display: none;">
					<table>
						<tr>
							<td>&nbsp;</td>
							<td>
								<s:label cssClass="etiquetaAyuda" id="ayudasDisplay1" style="display:none; margin-top:5px;"
									value="Escriba su correo electrónico." />
							</td>
						</tr>
						<tr>
							<td>
								<s:label cssClass="etiquetaCaptura" value="* Confirmar Correo Electrónico:" />
							</td>
							<td>
								<s:textfield size="60" id="comparaCorreo" maxlength="100"
									onfocus="javascript:ayudasHelp(2);" onblur="javascript:ayudasHelpBlo(2);"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>
								<s:label cssClass="etiquetaAyuda" id="ayudasDisplay2" style="display:none; margin-top:5px;"
									value="Confirme su correo electrónico." />
							</td>
						</tr>
					</table>
				</div>

				<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td>
							<input class="botonenviar" value="Siguiente" type="button" onclick="javascript:return validacion('1');" />
						</td>
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
						<td colspan="2">
							<br />
							<s:label cssClass="titulosPyMEs" value="Información de la empresa" />
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* Nombre comercial:" />
									</td>
									<td>
										<s:textfield size="30" id="nombreComercial" name="pyMEs.nombreComercial" maxlength="150"></s:textfield>
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="Nombre fiscal:" />
									</td>
									<td>
										<s:textfield size="30" id="nombreFiscal" name="pyMEs.nombreFiscal" maxlength="100"></s:textfield>
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="Número de empleados:" />
									</td>
									<td>
										<s:textfield size="30" id="numeroEmpleados" name="pyMEs.numeroEmpleados" maxlength="25" onkeydown="return validaNumero(event)"></s:textfield>
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* Mensaje de venta:" />
									</td>
									<td>
										<s:textfield size="30" id="mensajeVenta" name="pyMEs.mensajeVentas" maxlength="150"
											onfocus="javascript:ayudasHelp(222);" onblur="javascript:ayudasHelpBlo(222);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay222" style="display:none; margin-top:5px;"
											value="Incluya el mensaje principal que desea que vea la empresa tractora." />
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* Calle:" />
									</td>
									<td>
										<s:textfield size="30" id="calle" name="domicilios.calle" maxlength="50"
											onfocus="javascript:ayudasHelp(3);" onblur="javascript:ayudasHelpBlo(3);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay3" style="display:none; margin-top:5px;"
											value="Escriba la calle de su empresa." />
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* Número Exterior:" />
									</td>
									<td>
										<s:textfield size="20" id="numExt" name="domicilios.numExt" maxlength="20"
											onfocus="javascript:ayudasHelp(4);" onblur="javascript:ayudasHelpBlo(4);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay4" style="display:none; margin-top:5px;"
											value="Escriba el número exterior de su empresa." />
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="Número Interior:" />
									</td>
									<td>
										<s:textfield size="20" id="numInt" name="domicilios.numInt" maxlength="20"
											onfocus="javascript:ayudasHelp(5);" onblur="javascript:ayudasHelpBlo(5);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay5" style="display:none; margin-top:5px;"
											value="Escriba el número interior de su empresa." />
									</td>
								</tr>
							</table>
						</td>
						<!-- Segunda Columna -->
						<td>
							<table>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="Piso:" />
									</td>
									<td>
										<s:textfield size="30" id="piso" name="domicilios.piso" maxlength="20"
											onfocus="javascript:ayudasHelp(6);" onblur="javascript:ayudasHelpBlo(6);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay6" style="display:none; margin-top:5px;"
											value="Escriba el piso en que se encuentra." />
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* Colonia:" />
									</td>
									<td>
										<s:textfield size="30" id="colonia" name="domicilios.colonia" maxlength="50"
											onfocus="javascript:ayudasHelp(7);" onblur="javascript:ayudasHelpBlo(7);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay7" style="display:none; margin-top:5px;"
											value="Escriba la colonia en que se encuentra." />
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* Delegación/Municipio:" />
									</td>
									<td><s:textfield size="30" id="delegacion"
											name="domicilios.delegacion" maxlength="50"
											onfocus="javascript:ayudasHelp(8);" onblur="javascript:ayudasHelpBlo(8);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay8" style="display:none; margin-top:5px;"
											value="Escriba Delegación o Municipio en que se encuentra." />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay9" style="display:none; margin-top:5px;" value="Seleccione el Estado ." />
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* Estado:" />
									</td>
									<td>
										<select id="estado" name="domicilios.estado" style="width: 230px;"
											onfocus="javascript:ayudasHelp(9);" onblur="javascript:ayudasHelpBlo(9);">
											<option value="Seleccione el estado">--Seleccione un estado--</option>
											<option ${domicilios.estado == 'Aguascalientes' ? ' selected="selected" ' : ''} value="Aguascalientes">Aguascalientes</option>
											<option ${domicilios.estado == 'Baja California Norte' ? ' selected="selected" ' : ''} value="Baja California Norte">Baja California Norte</option>
											<option ${domicilios.estado == 'Baja California Sur' ? ' selected="selected" ' : ''} value="Baja California Sur">Baja California Sur</option>
											<option ${domicilios.estado == 'Campeche' ? ' selected="selected" ' : ''} value="Campeche">Campeche</option>
											<option ${domicilios.estado == 'Chiapas' ? ' selected="selected" ' : ''} value="Chiapas">Chiapas</option>
											<option ${domicilios.estado == 'Chihuahua' ? ' selected="selected" ' : ''} value="Chihuahua">Chihuahua</option>
											<option ${domicilios.estado == 'Coahuila' ? ' selected="selected" ' : ''} value="Coahuila">Coahuila</option>
											<option ${domicilios.estado == 'Colima' ? ' selected="selected" ' : ''} value="Colima">Colima</option>
											<option ${domicilios.estado == 'Distrito Federal' ? ' selected="selected" ' : ''} value="Distrito Federal">Distrito Federal</option>
											<option ${domicilios.estado == 'Durango' ? ' selected="selected" ' : ''} value="Durango">Durango</option>
											<option ${domicilios.estado == 'Estado de Mexico' ? ' selected="selected" ' : ''} value="Estado de Mexico">Estado de México</option>
											<option ${domicilios.estado == 'Guanajuato' ? ' selected="selected" ' : ''} value="Guanajuato">Guanajuato</option>
											<option ${domicilios.estado == 'Guerrero' ? ' selected="selected" ' : ''} value="Guerrero">Guerrero</option>
											<option ${domicilios.estado == 'Hidalgo' ? ' selected="selected" ' : ''} value="Hidalgo">Hidalgo</option>
											<option ${domicilios.estado == 'Jalisco' ? ' selected="selected" ' : ''} value="Jalisco">Jalisco</option>
											<option ${domicilios.estado == 'Michoacan' ? ' selected="selected" ' : ''} value="Michoacan">Michoacán</option>
											<option ${domicilios.estado == 'Morelos' ? ' selected="selected" ' : ''} value="Morelos">Morelos</option>
											<option ${domicilios.estado == 'Nayarit' ? ' selected="selected" ' : ''} value="Nayarit">Nayarit</option>
											<option ${domicilios.estado == 'Nuevo Leon' ? ' selected="selected" ' : ''} value="Nuevo Leon">Nuevo León</option>
											<option ${domicilios.estado == 'Oaxaca' ? ' selected="selected" ' : ''} value="Oaxaca">Oaxaca</option>
											<option ${domicilios.estado == 'Puebla' ? ' selected="selected" ' : ''} value="Puebla">Puebla</option>
											<option ${domicilios.estado == 'Queretaro' ? ' selected="selected" ' : ''} value="Queretaro">Querétaro</option>
											<option ${domicilios.estado == 'Quintana Roo' ? ' selected="selected" ' : ''} value="Quintana Roo">Quintana Roo</option>
											<option ${domicilios.estado == 'San Luis Potosi' ? ' selected="selected" ' : ''} value="San Luis Potosi">San Luís Potosí</option>
											<option ${domicilios.estado == 'Sinaloa' ? ' selected="selected" ' : ''} value="Sinaloa">Sinaloa</option>
											<option ${domicilios.estado == 'Sonora' ? ' selected="selected" ' : ''} value="Sonora">Sonora</option>
											<option ${domicilios.estado == 'Tabasco' ? ' selected="selected" ' : ''} value="Tabasco">Tabasco</option>
											<option ${domicilios.estado == 'Tamaulipas' ? ' selected="selected" ' : ''} value="Tamaulipas">Tamaulipas</option>
											<option ${domicilios.estado == 'Tlaxcala' ? ' selected="selected" ' : ''} value="Tlaxcala">Tlaxcala</option>
											<option ${domicilios.estado == 'Veracruz' ? ' selected="selected" ' : ''} value="Veracruz">Veracruz</option>
											<option ${domicilios.estado == 'Yucatan' ? ' selected="selected" ' : ''} value="Yucatan">Yucatán</option>
											<option ${domicilios.estado == 'Zacatecas' ? ' selected="selected" ' : ''} value="Zacatecas">Zacatecas</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* C.P.:" />
									</td>
									<td>
										<s:textfield size="20" id="codigoPostal" name="domicilios.codigoPostal" maxlength="5" 
										onfocus="javascript:ayudasHelp(10);" onblur="javascript:ayudasHelpBlo(10);"
										onkeypress="return validaNumero(event)"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay10" style="display:none; margin-top:5px;"
											value="Escriba el Código postal en que se encuentra." />
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="Página web:" />
									</td>
									<td>
										<s:textfield size="30" id="pagWeb" name="pyMEs.paginaWeb" maxlength="100"
											onfocus="javascript:ayudasHelp(11);" onblur="javascript:ayudasHelpBlo(11);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay11" style="display:none; margin-top:5px;"
											value="Escriba la dirección web completa." />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br />
				<table>
					<tr>
						<td style="width: 400px;">
							<div onmouseover="javascript:ayudasHelp(12)" onmouseout="javascript:ayudasHelpBlo(12)">
							<table>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* Sector" />
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
								<tr>
									<td>
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay12" style="display:none; margin-top:5px;"
										value="Estimada PyME, seleccione el sector al que pertenece su empresa. Seleccione el sector manufacturero si usted produce o fabrica un producto o alguna parte del producto que vende; seleccione el sector comercial si solamente compra y vende bienes que no fabricó, ya sea total o parcialmente; y seleccione el sector de servicios si realiza cualquier actividades para resolver las necesidades de clientes, exceptuando los dos rubros anteriores" />
									</td>
								</tr>
							</table>
							</div>
						</td>
						<td style="width: 400px;">
							<table>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay13" style="display:none; margin-top:5px;"
											value="Seleccione el rango de sus ventas anuales en pesos (último año)." />
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="Ventas anuales:" />
									</td>
									<td>
										<select id="ventasAnuales" name="pyMEs.ventasAnuales" style="width: 230px;"											
											onfocus="javascript:ayudasHelp(13);" onblur="javascript:ayudasHelpBlo(13);">
											<option value="Seleccione un rango">Seleccione un rango de ventas</option>
											<option ${pyMEs.ventasAnuales == 'Menos de $1,000,000' ? ' selected="selected" ' : ''} value="Menos de $1,000,000">Menos de $1,000,000</option>
											<option ${pyMEs.ventasAnuales == '$1,000,000 a $5,000,000' ? ' selected="selected" ' : ''} value="$1,000,000 a $5,000,000">$1,000,000 a $5,000,000</option>
											<option ${pyMEs.ventasAnuales == '$5,000,000 a $20,000,000' ? ' selected="selected" ' : ''} value="$5,000,000 a $20,000,000">$5,000,000 a $20,000,000</option>
											<option ${pyMEs.ventasAnuales == '$20,000,000 a $50,000,000' ? ' selected="selected" ' : ''} value="$20,000,000 a $50,000,000">$20,000,000 a $50,000,000</option>
											<option ${pyMEs.ventasAnuales == '$50,000,000 a $100,000,000' ? ' selected="selected" ' : ''} value="$50,000,000 a $100,000,000">$50,000,000 a $100,000,000</option>
											<option ${pyMEs.ventasAnuales == '$100,000,000 a $250,000,000' ? ' selected="selected" ' : ''} value="$100,000,000 a $250,000,000">$100,000,000 a $250,000,000</option>
											<option ${pyMEs.ventasAnuales == 'Mas de $250,000,000' ? ' selected="selected" ' : ''} value="Mas de $250,000,000">Más de $250,000,000</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<!-- Inician campos productos-->
				<br />
				<div style="width: 100%">
					<div class="flotantes">
						<table>
							<tr>
								<td>
									<s:label cssClass="etiquetaCaptura" value="* Productos:" />
									<s:textfield size="30" id="idProducto" maxlength="24"
									onfocus="javascript:ayudasHelp(14);" onblur="javascript:ayudasHelpBlo(14);"></s:textfield>
									&nbsp;&nbsp;
									<label class="agregar" onclick="agregaProducto();">+agregar</label>
								</td>
							</tr>
							<tr>
								<td>
									<s:label cssClass="etiquetaAyuda" id="ayudasDisplay14" style="display:none; margin-top:5px;"
										value="Incluya sus productos principales en 3 palabras, mediante el botón '+agregar' (máximo 20 productos)." />
								</td>
							</tr>
						</table>
					</div>
					<div ${pyMEs.productos[0].producto!=null?' style="display: block;"':' style="display: none;"'} id="tablaProd" class="flotantes">
						<table width="100%" cellspacing="1" cellpadding="1">
							<tr>
								<td class="encabezadoTablaResumen" colspan="3" align="center"><b>Descripción de los productos registrados</b></td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;"><b>No.</b></td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 75%;"><b>Producto agregado</b></td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 15%;"><b>Eliminar producto</b></td>
							</tr>
						</table>
						<s:iterator status="stat" value="(20).{ #this }" >
							<div id="idDivProd${stat.count}" ${!(pyMEs.productos[stat.index]==null)?' style="display: block;"':' style="display: none;"'}>
								<table width="100%" cellspacing="1" cellpadding="1">
									<tr>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 10%;" align="center">
											${stat.count}
											<s:hidden id="idIdProdHid%{#stat.count}" name="pyMEs.productos[%{#stat.index}].idProducto" value="%{pyMEs.productos[#stat.index].idProducto}" />
											<s:hidden id="idProdHid%{#stat.count}" name="pyMEs.productos[%{#stat.index}].producto" value="%{pyMEs.productos[#stat.index].producto}" />
										</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 75%;">
											<s:label id="labProd%{#stat.count}" cssClass="etiquetaCaptura" value="%{pyMEs.productos[#stat.index].producto}" />
										</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 15%;" align="center">
											<label class="quitar" onclick="quitarProducto(${stat.count});">-quitar</label>
										</td>
									</tr>
								</table>
							</div>
						</s:iterator>
					</div>
				</div>
				<!-- Finaliza campos productos-->
	
				<!-- Botones -->
				
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td>
							<input class="botonenviar" value="Anterior" type="button" onclick="javascript:document.getElementById('sec2').style.display='none'; javascript:document.getElementById('sec1').style.display='block';" />
						</td>
						<td>
							<input class="botonenviar" value="Siguiente" type="button" onclick="javascript:return validacion('2');" />
						</td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
			<!-- Termina Seccion 2 -->

			<!-- Inicia Seccion 3 -->

			<div id="sec3" style="display: none;">
				<br />
				<table>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="* Estados donde puede vender sus productos:" />
						</td>
					</tr>
					<tr>
						<td>
							<div onmouseover="javascript:ayudasHelp('estados');document.getElementById('Destados').style.display='none';" onmouseout="javascript:ayudasHelpBlo('estados');document.getElementById('Destados').style.display='block';">
							<table>
								<tr>
									<td>
										<s:if test="estadosVentas.nacional == 'Nacional'">
											<s:checkbox id="checkNacional" name="pyMEsNacional" value="true" onclick="javascript: valueCheckNacional('Nacional');" />
										</s:if> 
										<s:else>
											<s:checkbox id="checkNacional" name="pyMEsNacional" value="" onclick="javascript: valueCheckNacional('Nacional');" />
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Nacional" />
										<s:hidden id="checkEstadoNacional" name="estadosVentas.nacional" value="%{estadosVentas.nacional}" />
										<s:hidden id="idCheckEstadoNacional" name="estadosVentas.idNacional" value="%{estadosVentas.idNacional}" />
									</td>
								</tr>
							</table>
							<table id="contCheckEstados">
								<tr>
									<td style="width: 180px;">
										<s:if test="estadosVentas.aguascalientes == 'Aguascalientes'">
											<s:checkbox id="check1" name="pyMEsAguascalientes" value="true" onclick="javascript: valueEstadoCheck(1, 'Aguascalientes');" />
										</s:if>
										<s:else>
											<s:checkbox id="check1" name="pyMEsAguascalientes" value="" onclick="javascript: valueEstadoCheck(1, 'Aguascalientes');" />
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Aguascalientes" />
										<s:hidden id="checkEstado1" name="estadosVentas.aguascalientes" value="%{estadosVentas.aguascalientes}" />
										<s:hidden id="idCheckEstado1" name="estadosVentas.idAguascalientes" value="%{estadosVentas.idAguascalientes}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.distritoFederal == 'Distrito Federal'">
											<s:checkbox id="check2" name="pyMEsDistritoFederal" value="true" onclick="javascript: valueEstadoCheck(2, 'Distrito Federal');" />
										</s:if>
										<s:else>
											<s:checkbox id="check2" name="pyMEsDistritoFederal" value="" onclick="javascript: valueEstadoCheck(2, 'Distrito Federal');" />
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Distrito Federal" />
										<s:hidden id="checkEstado2" name="estadosVentas.distritoFederal" value="%{estadosVentas.distritoFederal}" />
										<s:hidden id="idCheckEstado2" name="estadosVentas.idDistritoFederal" value="%{estadosVentas.idDistritoFederal}" />
									</td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.morelos == 'Morelos'">
											<s:checkbox id="check3" name="pyMEsMorelos" value="true"
												onclick="javascript: valueEstadoCheck(3, 'Morelos');" />
										</s:if> <s:else>
											<s:checkbox id="check3" name="pyMEsMorelos" value=""
												onclick="javascript: valueEstadoCheck(3, 'Morelos');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Morelos" /> <s:hidden
											id="checkEstado3" name="estadosVentas.morelos"
											value="%{estadosVentas.morelos}" /> <s:hidden
											id="idCheckEstado3" name="estadosVentas.idMorelos"
											value="%{estadosVentas.idMorelos}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.sinaloa == 'Sinaloa'">
											<s:checkbox id="check4" name="pyMEsSinaloa" value="true"
												onclick="javascript: valueEstadoCheck(4, 'Sinaloa');" />
										</s:if> <s:else>
											<s:checkbox id="check4" name="pyMEsSinaloa" value=""
												onclick="javascript: valueEstadoCheck(4, 'Sinaloa');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Sinaloa" /> <s:hidden
											id="checkEstado4" name="estadosVentas.sinaloa"
											value="%{estadosVentas.sinaloa}" /> <s:hidden
											id="idCheckEstado4" name="estadosVentas.idSinaloa"
											value="%{estadosVentas.idSinaloa}" /></td>
								</tr>
								<tr>
									<td style="width: 180px;"><s:if
											test="estadosVentas.bajaCaliforniaNorte == 'Baja California Norte'">
											<s:checkbox id="check5" name="pyMEsBajaCaliforniaNorte"
												value="true"
												onclick="javascript: valueEstadoCheck(5, 'Baja California Norte');" />
										</s:if> <s:else>
											<s:checkbox id="check5" name="pyMEsBajaCaliforniaNorte"
												value=""
												onclick="javascript: valueEstadoCheck(5, 'Baja California Norte');" />
										</s:else> <s:label cssClass="etiquetaCaptura"
											value="Baja California Norte" /> <s:hidden id="checkEstado5"
											name="estadosVentas.bajaCaliforniaNorte"
											value="%{estadosVentas.bajaCaliforniaNorte}" /> <s:hidden
											id="idCheckEstado5"
											name="estadosVentas.idBajaCaliforniaNorte"
											value="%{estadosVentas.idBajaCaliforniaNorte}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.durango == 'Durango'">
											<s:checkbox id="check6" name="pyMEsDurango" value="true"
												onclick="javascript: valueEstadoCheck(6, 'Durango');" />
										</s:if> <s:else>
											<s:checkbox id="check6" name="pyMEsDurango" value=""
												onclick="javascript: valueEstadoCheck(6, 'Durango');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Durango" /> <s:hidden
											id="checkEstado6" name="estadosVentas.durango"
											value="%{estadosVentas.durango}" /> <s:hidden
											id="idCheckEstado6" name="estadosVentas.idDurango"
											value="%{estadosVentas.idDurango}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.nayarit == 'Nayarit'">
											<s:checkbox id="check7" name="pyMEsNayarit" value="true"
												onclick="javascript: valueEstadoCheck(7, 'Nayarit');" />
										</s:if> <s:else>
											<s:checkbox id="check7" name="pyMEsNayarit" value=""
												onclick="javascript: valueEstadoCheck(7, 'Nayarit');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Nayarit" /> <s:hidden
											id="checkEstado7" name="estadosVentas.nayarit"
											value="%{estadosVentas.nayarit}" /> <s:hidden
											id="idCheckEstado7" name="estadosVentas.idNayarit"
											value="%{estadosVentas.idNayarit}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.sonora == 'Sonora'">
											<s:checkbox id="check8" name="pyMEsSonora" value="true"
												onclick="javascript: valueEstadoCheck(8, 'Sonora');" />
										</s:if> <s:else>
											<s:checkbox id="check8" name="pyMEsSonora" value=""
												onclick="javascript: valueEstadoCheck(8, 'Sonora');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Sonora" /> <s:hidden
											id="checkEstado8" name="estadosVentas.sonora"
											value="%{estadosVentas.sonora}" /> <s:hidden
											id="idCheckEstado8" name="estadosVentas.idSonora"
											value="%{estadosVentas.idSonora}" /></td>
								</tr>
								<tr>
									<td style="width: 180px;"><s:if
											test="estadosVentas.bajaCaliforniaSur == 'Baja California Sur'">
											<s:checkbox id="check9" name="pyMEsBajaCaliforniaSur"
												value="true"
												onclick="javascript: valueEstadoCheck(9, 'Baja California Sur');" />
										</s:if> <s:else>
											<s:checkbox id="check9" name="pyMEsBajaCaliforniaSur"
												value=""
												onclick="javascript: valueEstadoCheck(9, 'Baja California Sur');" />
										</s:else> <s:label cssClass="etiquetaCaptura"
											value="Baja California Sur" /> <s:hidden id="checkEstado9"
											name="estadosVentas.bajaCaliforniaSur"
											value="%{estadosVentas.bajaCaliforniaSur}" /> <s:hidden
											id="idCheckEstado9" name="estadosVentas.idBajaCaliforniaSur"
											value="%{estadosVentas.idBajaCaliforniaSur}" /></td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.guanajuato == 'Guanajuato'">
											<s:checkbox id="check10" name="pyMEsGuanajuato" value="true" onclick="javascript: valueEstadoCheck(10, 'Guanajuato');" />
										</s:if>
										<s:else>
											<s:checkbox id="check10" name="pyMEsGuanajuato" value="" onclick="javascript: valueEstadoCheck(10, 'Guanajuato');" />
										</s:else>
										<s:label cssClass="etiquetaCaptura" value="Guanajuato" />
										<s:hidden id="checkEstado10" name="estadosVentas.guanajuato" value="%{estadosVentas.guanajuato}" />
										<s:hidden id="idCheckEstado10" name="estadosVentas.idGuanajuato" value="%{estadosVentas.idGuanajuato}" />
									</td>
									<td style="width: 180px;">
										<s:if test="estadosVentas.nuevoLeon == 'Nuevo León'">
											<s:checkbox id="check11" name="pyMEsNuevoLeon" value="true"
												onclick="javascript: valueEstadoCheck(11, 'Nuevo León');" />
										</s:if> <s:else>
											<s:checkbox id="check11" name="pyMEsNuevoLeon" value=""
												onclick="javascript: valueEstadoCheck(11, 'Nuevo León');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Nuevo León" /> <s:hidden
											id="checkEstado11" name="estadosVentas.nuevoLeon"
											value="%{estadosVentas.nuevoLeon}" /> <s:hidden
											id="idCheckEstado11" name="estadosVentas.idNuevoLeon"
											value="%{estadosVentas.idNuevoLeon}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.tabasco == 'Tabasco'">
											<s:checkbox id="check12" name="pyMEsTabasco" value="true"
												onclick="javascript: valueEstadoCheck(12, 'Tabasco');" />
										</s:if> <s:else>
											<s:checkbox id="check12" name="pyMEsTabasco" value=""
												onclick="javascript: valueEstadoCheck(12, 'Tabasco');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Tabasco" /> <s:hidden
											id="checkEstado12" name="estadosVentas.tabasco"
											value="%{estadosVentas.tabasco}" /> <s:hidden
											id="idCheckEstado12" name="estadosVentas.idTabasco"
											value="%{estadosVentas.idTabasco}" /></td>
								</tr>
								<tr>
									<td style="width: 180px;"><s:if
											test="estadosVentas.campeche == 'Campeche'">
											<s:checkbox id="check13" name="pyMEsCampeche" value="true"
												onclick="javascript: valueEstadoCheck(13, 'Campeche');" />
										</s:if> <s:else>
											<s:checkbox id="check13" name="pyMEsCampeche" value=""
												onclick="javascript: valueEstadoCheck(13, 'Campeche');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Campeche" /> <s:hidden
											id="checkEstado13" name="estadosVentas.campeche"
											value="%{estadosVentas.campeche}" /> <s:hidden
											id="idCheckEstado13" name="estadosVentas.idCampeche"
											value="%{estadosVentas.idCampeche}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.guerrero == 'Guerrero'">
											<s:checkbox id="check14" name="pyMEsGuerrero" value="true"
												onclick="javascript: valueEstadoCheck(14, 'Guerrero');" />
										</s:if> <s:else>
											<s:checkbox id="check14" name="pyMEsGuerrero" value=""
												onclick="javascript: valueEstadoCheck(14, 'Guerrero');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Guerrero" /> <s:hidden
											id="checkEstado14" name="estadosVentas.guerrero"
											value="%{estadosVentas.guerrero}" /> <s:hidden
											id="idCheckEstado14" name="estadosVentas.idGuerrero"
											value="%{estadosVentas.idGuerrero}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.oaxaca == 'Oaxaca'">
											<s:checkbox id="check15" name="pyMEsOaxaca" value="true"
												onclick="javascript: valueEstadoCheck(15, 'Oaxaca');" />
										</s:if> <s:else>
											<s:checkbox id="check15" name="pyMEsOaxaca" value=""
												onclick="javascript: valueEstadoCheck(15, 'Oaxaca');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Oaxaca" /> <s:hidden
											id="checkEstado15" name="estadosVentas.oaxaca"
											value="%{estadosVentas.oaxaca}" /> <s:hidden
											id="idCheckEstado15" name="estadosVentas.idOaxaca"
											value="%{estadosVentas.idOaxaca}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.tamaulipas == 'Tamaulipas'">
											<s:checkbox id="check16" name="pyMEsTamaulipas" value="true"
												onclick="javascript: valueEstadoCheck(16, 'Tamaulipas');" />
										</s:if> <s:else>
											<s:checkbox id="check16" name="pyMEsTamaulipas" value=""
												onclick="javascript: valueEstadoCheck(16, 'Tamaulipas');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Tamaulipas" /> <s:hidden
											id="checkEstado16" name="estadosVentas.tamaulipas"
											value="%{estadosVentas.tamaulipas}" /> <s:hidden
											id="idCheckEstado16" name="estadosVentas.idTamaulipas"
											value="%{estadosVentas.idTamaulipas}" /></td>
								</tr>
								<tr>
									<td style="width: 180px;"><s:if
											test="estadosVentas.chiapas == 'Chiapas'">
											<s:checkbox id="check17" name="pyMEsChiapas" value="true"
												onclick="javascript: valueEstadoCheck(17, 'Chiapas');" />
										</s:if> <s:else>
											<s:checkbox id="check17" name="pyMEsChiapas" value=""
												onclick="javascript: valueEstadoCheck(17, 'Chiapas');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Chiapas" /> <s:hidden
											id="checkEstado17" name="estadosVentas.chiapas"
											value="%{estadosVentas.chiapas}" /> <s:hidden
											id="idCheckEstado17" name="estadosVentas.idChiapas"
											value="%{estadosVentas.idChiapas}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.hidalgo == 'Hidalgo'">
											<s:checkbox id="check18" name="pyMEsHidalgo" value="true"
												onclick="javascript: valueEstadoCheck(18, 'Hidalgo');" />
										</s:if> <s:else>
											<s:checkbox id="check18" name="pyMEsHidalgo" value=""
												onclick="javascript: valueEstadoCheck(18, 'Hidalgo');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Hidalgo" /> <s:hidden
											id="checkEstado18" name="estadosVentas.hidalgo"
											value="%{estadosVentas.hidalgo}" /> <s:hidden
											id="idCheckEstado18" name="estadosVentas.idHidalgo"
											value="%{estadosVentas.idHidalgo}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.puebla == 'Puebla'">
											<s:checkbox id="check19" name="pyMEsPuebla" value="true"
												onclick="javascript: valueEstadoCheck(19, 'Puebla');" />
										</s:if> <s:else>
											<s:checkbox id="check19" name="pyMEsPuebla" value=""
												onclick="javascript: valueEstadoCheck(19, 'Puebla');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Puebla" /> <s:hidden
											id="checkEstado19" name="estadosVentas.puebla"
											value="%{estadosVentas.puebla}" /> <s:hidden
											id="idCheckEstado19" name="estadosVentas.idPuebla"
											value="%{estadosVentas.idPuebla}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.tlaxcala == 'Tlaxcala'">
											<s:checkbox id="check20" name="pyMEsTlaxcala" value="true"
												onclick="javascript: valueEstadoCheck(20, 'Tlaxcala');" />
										</s:if> <s:else>
											<s:checkbox id="check20" name="pyMEsTlaxcala" value=""
												onclick="javascript: valueEstadoCheck(20, 'Tlaxcala');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Tlaxcala" /> <s:hidden
											id="checkEstado20" name="estadosVentas.tlaxcala"
											value="%{estadosVentas.tlaxcala}" /> <s:hidden
											id="idCheckEstado20" name="estadosVentas.idTlaxcala"
											value="%{estadosVentas.idTlaxcala}" /></td>
								</tr>
								<tr>
									<td style="width: 180px;"><s:if
											test="estadosVentas.chihuahua == 'Chihuahua'">
											<s:checkbox id="check21" name="pyMEsChihuahua" value="true"
												onclick="javascript: valueEstadoCheck(21, 'Chihuahua');" />
										</s:if> <s:else>
											<s:checkbox id="check21" name="pyMEsChihuahua" value=""
												onclick="javascript: valueEstadoCheck(21, 'Chihuahua');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Chihuahua" /> <s:hidden
											id="checkEstado21" name="estadosVentas.chihuahua"
											value="%{estadosVentas.chihuahua}" /> <s:hidden
											id="idCheckEstado21" name="estadosVentas.idChihuahua"
											value="%{estadosVentas.idChihuahua}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.jalisco == 'Jalisco'">
											<s:checkbox id="check22" name="pyMEsJalisco" value="true"
												onclick="javascript: valueEstadoCheck(22, 'Jalisco');" />
										</s:if> <s:else>
											<s:checkbox id="check22" name="pyMEsJalisco" value=""
												onclick="javascript: valueEstadoCheck(22, 'Jalisco');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Jalisco" /> <s:hidden
											id="checkEstado22" name="estadosVentas.jalisco"
											value="%{estadosVentas.jalisco}" /> <s:hidden
											id="idCheckEstado22" name="estadosVentas.idJalisco"
											value="%{estadosVentas.idJalisco}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.queretaro == 'Querétaro'">
											<s:checkbox id="check23" name="pyMEsQueretaro" value="true"
												onclick="javascript: valueEstadoCheck(23, 'Querétaro');" />
										</s:if> <s:else>
											<s:checkbox id="check23" name="pyMEsQueretaro" value=""
												onclick="javascript: valueEstadoCheck(23, 'Querétaro');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Querétaro" /> <s:hidden
											id="checkEstado23" name="estadosVentas.queretaro"
											value="%{estadosVentas.queretaro}" /> <s:hidden
											id="idCheckEstado23" name="estadosVentas.idQueretaro"
											value="%{estadosVentas.idQueretaro}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.veracruz == 'Veracruz'">
											<s:checkbox id="check24" name="pyMEsVeracruz" value="true"
												onclick="javascript: valueEstadoCheck(24, 'Veracruz');" />
										</s:if> <s:else>
											<s:checkbox id="check24" name="pyMEsVeracruz" value=""
												onclick="javascript: valueEstadoCheck(24, 'Veracruz');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Veracruz" /> <s:hidden
											id="checkEstado24" name="estadosVentas.veracruz"
											value="%{estadosVentas.veracruz}" /> <s:hidden
											id="idCheckEstado24" name="estadosVentas.idVeracruz"
											value="%{estadosVentas.idVeracruz}" /></td>
								</tr>
								<tr>
									<td style="width: 180px;"><s:if
											test="estadosVentas.coahuila == 'Coahuila'">
											<s:checkbox id="check25" name="pyMEsCoahuila" value="true"
												onclick="javascript: valueEstadoCheck(25, 'Coahuila');" />
										</s:if> <s:else>
											<s:checkbox id="check25" name="pyMEsCoahuila" value=""
												onclick="javascript: valueEstadoCheck(25, 'Coahuila');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Coahuila" /> <s:hidden
											id="checkEstado25" name="estadosVentas.coahuila"
											value="%{estadosVentas.coahuila}" /> <s:hidden
											id="idCheckEstado25" name="estadosVentas.idCoahuila"
											value="%{estadosVentas.idCoahuila}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.estadoDeMexico == 'Estado de México'">
											<s:checkbox id="check26" name="pyMEsEstadoDeMexico"
												value="true"
												onclick="javascript: valueEstadoCheck(26, 'Estado de México');" />
										</s:if> <s:else>
											<s:checkbox id="check26" name="pyMEsEstadoDeMexico" value=""
												onclick="javascript: valueEstadoCheck(26, 'Estado de México');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Estado de México" />
										<s:hidden id="checkEstado26"
											name="estadosVentas.estadoDeMexico"
											value="%{estadosVentas.estadoDeMexico}" /> <s:hidden
											id="idCheckEstado26" name="estadosVentas.idEstadoDeMexico"
											value="%{estadosVentas.idEstadoDeMexico}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.quintanaRoo == 'Quintana Roo'">
											<s:checkbox id="check27" name="pyMEsQuintanaRoo" value="true"
												onclick="javascript: valueEstadoCheck(27, 'Quintana Roo');" />
										</s:if> <s:else>
											<s:checkbox id="check27" name="pyMEsQuintanaRoo" value=""
												onclick="javascript: valueEstadoCheck(27, 'Quintana Roo');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Quintana Roo" />
										<s:hidden id="checkEstado27" name="estadosVentas.quintanaRoo"
											value="%{estadosVentas.quintanaRoo}" /> <s:hidden
											id="idCheckEstado27" name="estadosVentas.idQuintanaRoo"
											value="%{estadosVentas.idQuintanaRoo}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.yucatan == 'Yucatán'">
											<s:checkbox id="check28" name="pyMEsYucatan" value="true"
												onclick="javascript: valueEstadoCheck(28, 'Yucatán');" />
										</s:if> <s:else>
											<s:checkbox id="check28" name="pyMEsYucatan" value=""
												onclick="javascript: valueEstadoCheck(28, 'Yucatán');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Yucatán" /> <s:hidden
											id="checkEstado28" name="estadosVentas.yucatan"
											value="%{estadosVentas.yucatan}" /> <s:hidden
											id="idCheckEstado28" name="estadosVentas.idYucatan"
											value="%{estadosVentas.idYucatan}" /></td>
								</tr>
								<tr>
									<td style="width: 180px;"><s:if
											test="estadosVentas.colima == 'Colima'">
											<s:checkbox id="check29" name="pyMEsColima" value="true"
												onclick="javascript: valueEstadoCheck(29, 'Colima');" />
										</s:if> <s:else>
											<s:checkbox id="check29" name="pyMEsColima" value=""
												onclick="javascript: valueEstadoCheck(29, 'Colima');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Colima" /> <s:hidden
											id="checkEstado29" name="estadosVentas.colima"
											value="%{estadosVentas.colima}" /> <s:hidden
											id="idCheckEstado29" name="estadosVentas.idColima"
											value="%{estadosVentas.idColima}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.michoacan == 'Michoacán'">
											<s:checkbox id="check30" name="pyMEsMichoacan" value="true"
												onclick="javascript: valueEstadoCheck(30, 'Michoacán');" />
										</s:if> <s:else>
											<s:checkbox id="check30" name="pyMEsMichoacan" value=""
												onclick="javascript: valueEstadoCheck(30, 'Michoacán');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Michoacán" /> <s:hidden
											id="checkEstado30" name="estadosVentas.michoacan"
											value="%{estadosVentas.michoacan}" /> <s:hidden
											id="idCheckEstado30" name="estadosVentas.idMichoacan"
											value="%{estadosVentas.idMichoacan}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.sanLuisPotosi == 'San Luís Potosí'">
											<s:checkbox id="check31" name="pyMEsSanLuisPotosi"
												value="true"
												onclick="javascript: valueEstadoCheck(31, 'San Luís Potosí');" />
										</s:if> <s:else>
											<s:checkbox id="check31" name="pyMEsSanLuisPotosi" value=""
												onclick="javascript: valueEstadoCheck(31, 'San Luís Potosí');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="San Luís Potosí" />
										<s:hidden id="checkEstado31"
											name="estadosVentas.sanLuisPotosi"
											value="%{estadosVentas.sanLuisPotosi}" /> <s:hidden
											id="idCheckEstado31" name="estadosVentas.idSanLuisPotosi"
											value="%{estadosVentas.idSanLuisPotosi}" /></td>
									<td style="width: 180px;"><s:if
											test="estadosVentas.zacatecas == 'Zacatecas'">
											<s:checkbox id="check32" name="pyMEsZacatecas" value="true"
												onclick="javascript: valueEstadoCheck(32, 'Zacatecas');" />
										</s:if> <s:else>
											<s:checkbox id="check32" name="pyMEsZacatecas" value=""
												onclick="javascript: valueEstadoCheck(32, 'Zacatecas');" />
										</s:else> <s:label cssClass="etiquetaCaptura" value="Zacatecas" /> <s:hidden
											id="checkEstado32" name="estadosVentas.zacatecas"
											value="%{estadosVentas.zacatecas}" /> <s:hidden
											id="idCheckEstado32" name="estadosVentas.idZacatecas"
											value="%{estadosVentas.idZacatecas}" /></td>
								</tr>
							</table></div></td>
					</tr>
					<tr>
						<td>
							<s:label cssClass="etiquetaAyuda" id="ayudasDisplayestados" style="display:none; margin-top:5px;" value="Puede elegir uno o más estados." />
							<s:label cssClass="etiquetaAyuda" id="Destados" style="display:block; margin-top:5px; color: #FFFFFF;" value="." />
						</td>
					</tr>
				</table>
				<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td><input class="botonenviar" value="Anterior" type="button"
							onclick="javascript:document.getElementById('sec3').style.display='none'; javascript:document.getElementById('sec2').style.display='block';" />
						</td>
						<td><input class="botonenviar" value="Siguiente"
							type="button" onclick="javascript:return validacion('3');" />
						</td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
			<!-- Termina Seccion 3 -->

			<!-- Inicia Seccion 4 -->
			<div id="sec4" style="display: none;">
				<br />
				<!-- Inicia Información del contacto -->
				<s:label cssClass="titulosPyMEs" value="Información del contacto." />
				<br /><br />
				<div id="showFormContact" style="display: none;">
					<table width="99%">
						<tr>
							<td style="width: 50%;">
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Seleccione el tipo de contacto:" />
										</td>
										<td>
											<select id="tipoContacto" onchange="javascript:valorTipoCont(this.value);">
												<option value="">--Seleccione un tipo--</option>
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
												<table>
													<tr>
														<td style="width: 210px;">
															<s:label cssClass="etiquetaCaptura" value="Ingrese el tipo de contacto:" />
														</td>
														<td>
															<s:textfield size="30" id="tipoOtro" name="tipoOt" maxlength="30" onfocus="javascript:ayudasHelp(15);" onblur="javascript:ayudasHelpBlo(15);"></s:textfield>
															<s:label cssClass="etiquetaAyuda" id="ayudasDisplay15" style="display:none; margin-top:5px;" value="Indique otro tipo de contacto " />
														</td>
													</tr>
												</table>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Nombre(s) :" />
										</td>
										<td>
											<s:textfield size="30" id="nombreContacto" name="nombreC" maxlength="60" 
												onfocus="javascript:ayudasHelp(16);" onmouseout="javascript:ayudasHelpBlo(16);"
												onkeypress="return validaLetra(event)"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay16" style="display:none; margin-top:5px;"
											value="Escriba el nombre o nombres del contacto." />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Apellido Paterno :" />
										</td>
										<td>
											<s:textfield size="30" id="appPat" name="apPat" maxlength="60" 
												onfocus="javascript:ayudasHelp(17);" onmouseout="javascript:ayudasHelpBlo(17);"
												onkeypress="return validaLetra(event)"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay17" style="display:none; margin-top:5px;"
												value="Escriba el apellido paterno del contacto." />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Apellido Materno :" />
										</td>
										<td>
											<s:textfield size="30" id="appMat" name="apMat" maxlength="60" 
												onfocus="javascript:ayudasHelp(18);" onmouseout="javascript:ayudasHelpBlo(18);"
												onkeypress="return validaLetra(event)"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay18" style="display:none; margin-top:5px;"
												value="Escriba el apellido materno del contacto." />
										</td>
									</tr>
								</table>
							</td>
							<td style="width: 50%;">
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Correo electrónico :" />
										</td>
										<td>
											<s:textfield size="30" id="correoElectronicoContacto" name="correoElec" 
												onfocus="javascript:ayudasHelp(19);" onmouseout="javascript:ayudasHelpBlo(19);"
												maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay19" style="display:none; margin-top:5px;"
												value="Incluya su correo electrónico." />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Confime Correo electrónico :" />
										</td>
										<td>
											<s:textfield size="30" id="comparaCorreoContacto" maxlength="100"
												onfocus="javascript:ayudasHelp(20);" onmouseout="javascript:ayudasHelpBlo(20);"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay20" style="display:none; margin-top:5px;"
												value="Confirme su correo electrónico." />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<table width="100%">
												<tr>
													<td colspan="6">
														<s:label cssClass="etiquetaCaptura" value="* Teléfono :" />
													</td>
												</tr>
												
												<tr>
													<td style="width: 12%;">
														<s:label cssClass="etiquetaCaptura" value="Lada :" />
													</td>
													<td style="width: 20%;">
														<s:label id="intTel" cssClass="resultado" value="52" />&nbsp;&nbsp;
														<s:textfield size="2" id="ladaTel" name="ladaTel" maxlength="2" 
															onkeypress="javascript: cambiaCampo(event); return validaNumero(event)" 
															onfocus="javascript:ayudasHelp(21);" onmouseout="javascript:ayudasHelpBlo(21);"
															></s:textfield>
													</td>
													<td style="width: 10%;">
														<s:label cssClass="etiquetaCaptura" value="Núm:" />
													</td>
													<td style="width: 28%;">
														<s:textfield size="16" id="numTel" name="numTel" maxlength="8" 
															onkeypress="javascript: cambiaCampo(event); return validaNumero(event);" 
															onfocus="javascript:ayudasHelp(21);" onmouseout="javascript:ayudasHelpBlo(21);"
															></s:textfield>
													</td>
													<td style="width: 5%;">
														<s:label cssClass="etiquetaCaptura" value="Ext:" />
													</td>
													<td style="width: 15%;">
														<s:textfield size="4" id="extTel" name="extTel" maxlength="4" 
															onfocus="javascript:ayudasHelp(21);" onmouseout="javascript:ayudasHelpBlo(21);"
															onkeypress="return validaNumero(event)"></s:textfield>
													</td>
												</tr>
											</table>
											
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay21" style="display:none; margin-top:5px;"
												value="Incluya su teléfono con clave lada y extensión." />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<s:hidden id="tempPosContHid" name="tempPosCont" value="" />

					<br />
					<label id="regContact" class="agregar" onclick="javascript: addContacto();">+registrar contacto</label>
					<label id="linkActulizaContact" class="quitar" style="display: none; font-size: 15px;" onclick="javascript: actualizaContacto();">+Finalizar actualización</label>
					<br />
				</div>
				
				<div id="tablaContact">
					<table width="100%" cellspacing="1" cellpadding="1">
						<tr>
							<td class="encabezadoTablaResumen" colspan="7" align="center"><b>Contactos registrados</b></td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen" align="center" style="width: 5%;"><b>No.</b></td>
							<td class="cuerpo1TablaResumen" align="center" style="width: 15%;"><b>Tipo</b></td>
							<td class="cuerpo1TablaResumen" align="center" style="width: 20%;"><b>Nombre</b></td>
							<td class="cuerpo1TablaResumen" align="center" style="width: 20%;"><b>Correo elecrónico</b></td>
							<td class="cuerpo1TablaResumen" align="center" style="width: 20%;"><b>Teléfono</b></td>
							<td class="cuerpo1TablaResumen" align="center" style="width: 10%;"><b>Editar contacto</b></td>
							<td class="cuerpo1TablaResumen" align="center" style="width: 10%;"><b>Eliminar contacto</b></td>
						</tr>
					</table>
					
					<s:iterator status="stat" value="(5).{ #this }" >
						<div id="divContacto${stat.count}" ${!(pyMEs.contactos[stat.index]==null)?' style="display: block;"':' style="display: none;"'}>
							<table width="100%" cellspacing="1" cellpadding="1">
								<tr>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 5%;" align="center">
										${stat.count}
										<s:hidden id="idContactoHid%{#stat.count}" name="pyMEs.contactos[%{#stat.index}].idContacto" value="%{pyMEs.contactos[#stat.index].idContacto}" />
										<s:hidden id="tipoHid%{#stat.count}" name="pyMEs.contactos[%{#stat.index}].tipo" value="%{pyMEs.contactos[#stat.index].tipo}" />
										<s:hidden id="nombreHid%{#stat.count}" name="pyMEs.contactos[%{#stat.index}].nombre" value="%{pyMEs.contactos[#stat.index].nombre}" />
										<s:hidden id="apPatHid%{#stat.count}" name="pyMEs.contactos[%{#stat.index}].apellidoPaterno" value="%{pyMEs.contactos[#stat.index].apellidoPaterno}" />
										<s:hidden id="apMatHid%{#stat.count}" name="pyMEs.contactos[%{#stat.index}].apellidoMaterno" value="%{pyMEs.contactos[#stat.index].apellidoMaterno}" />
										<s:hidden id="correoHid%{#stat.count}" name="pyMEs.contactos[%{#stat.index}].correoElectronico" value="%{pyMEs.contactos[#stat.index].correoElectronico}" />
										<s:hidden id="telHid%{#stat.count}" name="pyMEs.contactos[%{#stat.index}].Telefono" value="%{pyMEs.contactos[#stat.index].Telefono}" />
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 15%;" align="center">
										<s:label id="labTipo%{#stat.count}" cssClass="etiquetaCaptura" value="%{pyMEs.contactos[#stat.index].tipo}" />
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 20%;" align="center">
										<s:label id="labNombre%{#stat.count}" cssClass="etiquetaCaptura" value="%{pyMEs.contactos[#stat.index].nombre}" />
										<s:label id="labApPat%{#stat.count}" cssClass="etiquetaCaptura" value="%{pyMEs.contactos[#stat.index].apellidoPaterno}" />
										<s:label id="labApMat%{#stat.count}" cssClass="etiquetaCaptura" value="%{pyMEs.contactos[#stat.index].apellidoMaterno}" />
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 20%;" align="center">
										<s:label id="labCorreo%{#stat.count}" cssClass="etiquetaCaptura" value="%{pyMEs.contactos[#stat.index].correoElectronico}" />
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 20%;" align="center">
										<div>
											<s:label id="labTel%{#stat.count}" cssClass="etiquetaCaptura" value="%{pyMEs.contactos[#stat.index].Telefono}" />
										</div>
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 10%;" align="center">
										<label class="agregar" onclick="editaContacto(${stat.count});">editar</label>
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 10%;" align="center">
										<s:if test="#stat.count == 1">
											<label class="etiquetaCaptura">-eliminar</label>
										</s:if>
										<s:else>
											<label class="quitar" onclick="supContacto(${stat.count});">-eliminar</label>
										</s:else>
										
									</td>
								</tr>
							</table>
						</div>
					</s:iterator>
				</div>
				<br />
				<label id="linkAddContacto" class="agregar" onclick="javascript:document.getElementById('linkAddContacto').style.display='none'; javascript:document.getElementById('regContact').style.display='block'; javascript:document.getElementById('showFormContact').style.display='block';">+Agregar otro contacto</label>

				<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td><input class="botonenviar" value="Anterior" type="button"
							onclick="javascript:document.getElementById('sec4').style.display='none'; javascript:document.getElementById('sec3').style.display='block';" />
						</td>
						<td><input class="botonenviar" value="Siguiente"
							type="button" onclick="javascript:return validacion('4');" />
						</td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
			<!-- Termina Seccion 4 -->

			<!-- Inicia Seccion 5 -->
			<div id="sec5" style="display: none;">
				<br />
				<s:label cssClass="titulosPyMEs" value="Clientes principales." />
				<br /><br />
				<!-- Inicia Registro de Clientes -->
				
				<div id="formCliente" style="display: none;">
					<s:label cssClass="etiquetaCaptura" value="Datos del Cliente" />
					<br /><br />
					<table width="99%">
						<tr>
							<td>
								<s:label cssClass="etiquetaCaptura" value="* Cliente:" />
								<s:textfield id="cliente" size="25" name="" maxlength="100"
								onfocus="javascript:ayudasHelp(288);" onmouseout="javascript:ayudasHelpBlo(288);"></s:textfield>
								
							</td>
							<td>
								<s:label cssClass="etiquetaCaptura" value="* Productos que compra:" />
								<s:textfield id="prodCliente" size="35" name="" maxlength="500" name="" 
										onfocus="javascript:ayudasHelp(29);" onmouseout="javascript:ayudasHelpBlo(29);"></s:textfield>
								<label class="agregar" onclick="addProdCliente();">+agregar producto</label>
							</td>
						</tr>
						<tr>
							<td>
								<s:label cssClass="etiquetaAyuda" id="ayudasDisplay288" style="display:none; margin-top:5px;" value="Ingrese el nombre del cliente." />
							</td>
							<td>
								<s:textarea id="showProdCliente" rows="1" cols="40" disabled="true" cssClass="resultado" style="resize: none;" value="" /><br />
								<br />
								<label id="labDeleteProdC" style="display: none;" class="quitar" onclick="deleteProdCliente();">-eliminar producto</label><br />
								<s:label id="ayudasDisplay29" style="display:none; margin-top:5px;" cssClass="etiquetaAyuda" value="Describa los principales productos que le vende a la tractora." />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<s:label cssClass="etiquetaCaptura" value="Tiempo como proveedor del cliente :" />
							</td>
						</tr>
						<tr>
							<td>
								<s:label cssClass="etiquetaCaptura" value="*Años:" />
								<s:textfield id="aniosProveCliente" size="5" name="" maxlength="4" onkeypress="return validaNumero(event)"
										onfocus="javascript:ayudasHelp(30);" onmouseout="javascript:ayudasHelpBlo(30);"></s:textfield>
							</td>
							<td>
								<s:label cssClass="etiquetaCaptura" value="*Meses:" />
								<s:textfield id="mesesProveCliente" size="5" name="" maxlength="2" onfocus="javascript:ayudasHelp(30);" 
										onmouseout="javascript:ayudasHelpBlo(30);" onkeypress="return validaNumero(event)"></s:textfield>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<s:label cssClass="etiquetaAyuda" id="ayudasDisplay30" style="display:none; margin-top:5px;"
											value="Escriba los años y meses que lleva como proveedor de la tractora." />
							</td>
						</tr>
					</table>
					<s:label id="posTempA" cssClass="etiquetaCaptura" value="" />
					<s:hidden id="posTempAHid" name="posTempAHid" value="" />
					
					<label id="regCliente" class="agregar" onclick="javascript: addCliente();">+registrar cliente</label>
					<label id="linkActulizaProve" class="quitar" style="display: none; font-size: 15px;" onclick="javascript: actualizaCliente();">+Finalizar actualización</label>
					<br /><br />
				</div>

				<div id="tablaClient">
					<table width="100%" cellspacing="1" cellpadding="1">
						<tr>
							<td class="encabezadoTablaResumen" colspan="6" align="center"><b>Clientes registrados</b></td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen" align="center" style="width: 5%;"><b>No.</b></td>
							<td class="cuerpo1TablaResumen" align="center" style="width: 25%;"><b>Cliente</b></td>
							<td class="cuerpo1TablaResumen" align="center" style="width: 30%;"><b>Producto(s) que compra</b></td>
							<td class="cuerpo1TablaResumen" align="center" style="width: 20%;"><b>Tiempo como proveedor</b></td>
							<td class="cuerpo1TablaResumen" align="center" style="width: 10%;"><b>Editar cliente</b></td>
							<td class="cuerpo1TablaResumen" align="center" style="width: 10%;"><b>Eliminar cliente</b></td>
						</tr>
					</table>
					
					<s:iterator status="stat" value="(6).{ #this }" >
						<div id="divCliente${stat.count}" ${!(pyMEs.clientes[stat.index]==null)?' style="display: block;"':' style="display: none;"'}>
							<table width="100%" cellspacing="1" cellpadding="1">
								<tr>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 5%;" align="center">
										${stat.count}
										<s:hidden id="idClienteHid%{#stat.count}" name="pyMEs.clientes[%{#stat.index}].idCliente" value="%{pyMEs.clientes[#stat.index].idCliente}" />
										<s:hidden id="clienteHid%{#stat.count}" name="pyMEs.clientes[%{#stat.index}].cliente" value="%{pyMEs.clientes[#stat.index].cliente}" />
										<s:hidden id="prodCompHid%{#stat.count}" name="pyMEs.clientes[%{#stat.index}].productosCompra" value="%{pyMEs.clientes[#stat.index].productosCompra}" />
										<s:hidden id="anioHid%{#stat.count}" name="pyMEs.clientes[%{#stat.index}].aniosProveedor" value="%{pyMEs.clientes[#stat.index].aniosProveedor}" />
										<s:hidden id="mesesHid%{#stat.count}" name="pyMEs.clientes[%{#stat.index}].mesesProveedor" value="%{pyMEs.clientes[#stat.index].mesesProveedor}" />
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 25%;" align="center">
										<s:label id="labCliente%{#stat.count}" cssClass="etiquetaCaptura" value="%{pyMEs.clientes[#stat.index].cliente}" />
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 30%;" align="center">
										<s:label id="labProdCliente%{#stat.count}" cssClass="etiquetaCaptura" value="%{pyMEs.clientes[#stat.index].productosCompra}" />
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 20%;" align="center">
										<s:label id="labAniosCliente%{#stat.count}" cssClass="etiquetaCaptura" value="%{pyMEs.clientes[#stat.index].aniosProveedor}" />
										<label id="labAnios${stat.count}" class="etiquetaCaptura" ${!(pyMEs.clientes[stat.index].aniosProveedor==null)?' style="display: block;"':' style="display: none;"'}>Años</label>
										<s:label id="labMesesCliente%{#stat.count}" cssClass="etiquetaCaptura" value="%{pyMEs.clientes[#stat.index].mesesProveedor}" />
										<label id="labMeses${stat.count}" class="etiquetaCaptura" ${!(pyMEs.clientes[stat.index].mesesProveedor==null)?' style="display: block;"':' style="display: none;"'}>Meses</label>
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 10%;" align="center">
										<label class="agregar" onclick="editaCliente(${stat.count});">editar</label>
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 10%;" align="center">
										<s:if test="#stat.count == 1">
											<label class="etiquetaCaptura">-eliminar</label>
										</s:if>
										<s:else>
											<label class="quitar" onclick="supCliente(${stat.count});">-eliminar</label>
										</s:else>
										
									</td>
								</tr>
							</table>
						</div>
					</s:iterator>
				</div>
				<br />
				<label id="linkAddProve" class="agregar" onclick="javascript:document.getElementById('linkAddProve').style.display='none'; javascript:document.getElementById('regCliente').style.display='block'; javascript:document.getElementById('formCliente').style.display='block';">+registrar otro cliente</label>				

				<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td>
							<input class="botonenviar" value="Anterior" type="button" onclick="javascript:document.getElementById('sec5').style.display='none'; javascript:document.getElementById('sec4').style.display='block';" />
						</td>
						<td>
							<input class="botonenviar" value="Siguiente" type="button" onclick="javascript:return validacion('5');" />
						</td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
			<!-- Termina Seccion 5 -->

			<!-- Inicia Seccion 6 -->
			<div id="sec6" style="display: none;">
				<!-- Inicia Certificaciones y capacitación. -->
				<br />
				<s:label cssClass="titulosPyMEs" value="Certificaciones y capacitación." />
				<table>
					<tr>
						<td>
							<div id="cert1">
								<s:hidden name="pyMEs.certificaciones[0].idCertificado" id="idCert1" value="%{pyMEs.certificaciones[0].idCertificado}" />
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Certificación :" />
										</td>
										<td>
											<s:textfield size="30" id="certificacion1" name="pyMEs.certificaciones[0].certificacion" maxlength="150"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Año de certificación :" />
										</td>
										<td>
											<s:date name="pyMEs.certificaciones[0].fechaCertificacion" id="fCert" format="dd/MM/yyyy" />
											<s:textfield class="calendario" id="ingreso" name="pyMEs.certificaciones[0].fechaCertificacion" value="%{fCert}" size="10" maxlength="10" />
											<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador" style="cursor: hand"/>
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Institución que certificó :" />
										</td>
										<td>
											<s:textfield size="30" id="instCert1" name="pyMEs.certificaciones[0].institutoCertificador" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">&nbsp;</td>
									</tr>
								</table>
							</div>
						</td>
						<td>
							<div id="cert2" ${pyMEs.certificaciones[1].idCertificado==null? ' style="display: none;" ':' style="display: block;"'}>
								<s:hidden name="pyMEs.certificaciones[1].idCertificado" id="idCert2" value="%{pyMEs.certificaciones[1].idCertificado}" />
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Certificación :" />
										</td>
										<td>
											<s:textfield size="30" id="certificacion2" name="pyMEs.certificaciones[1].certificacion" maxlength="150"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Año de certificación :" />
										</td>
										<td>
											<s:date name="pyMEs.certificaciones[1].fechaCertificacion" id="fCert2" format="dd/MM/yyyy" />
											<s:textfield class="calendario" id="ingreso2" name="pyMEs.certificaciones[1].fechaCertificacion" value="%{fCert2}" size="10" maxlength="10" />
											<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador2" style="cursor: hand" />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Institución que certificó :" />
										</td>
										<td>
											<s:textfield size="30" id="instCert2" name="pyMEs.certificaciones[1].institutoCertificador" maxlength="100"></s:textfield>
										</td>
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
							<div id="cert3" ${pyMEs.certificaciones[2].idCertificado==null? ' style="display: none;" ':' style="display: block;"'}>
								<s:hidden name="pyMEs.certificaciones[2].idCertificado" id="idCert3" value="%{pyMEs.certificaciones[2].idCertificado}" />
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Certificación :" />
										</td>
										<td>
											<s:textfield size="30" id="certificacion3" name="pyMEs.certificaciones[2].certificacion" maxlength="150"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Año de certificación :" />
										</td>
										<td>
											<s:date name="pyMEs.certificaciones[2].fechaCertificacion" id="fCert3" format="dd/MM/yyyy" />
											<s:textfield class="calendario" id="ingreso3" name="pyMEs.certificaciones[2].fechaCertificacion" value="%{fCert3}" size="10" maxlength="10" />
											<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador3" style="cursor: hand" />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Institución que certificó :" />
										</td>
										<td>
											<s:textfield size="30" id="instCert3" name="pyMEs.certificaciones[2].institutoCertificador" maxlength="100"></s:textfield>
										</td>
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
							<div id="cert4" ${pyMEs.certificaciones[3].idCertificado==null? ' style="display: none;" ':' style="display: block;"'}>
								<s:hidden name="pyMEs.certificaciones[3].idCertificado" id="idCert4" value="%{pyMEs.certificaciones[3].idCertificado}" />
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Certificación :" />
										</td>
										<td>
											<s:textfield size="30" id="certificacion4" name="pyMEs.certificaciones[3].certificacion" maxlength="150"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Año de certificación :" />
										</td>
										<td>
											<s:date name="pyMEs.certificaciones[3].fechaCertificacion" id="fCert4" format="dd/MM/yyyy" />
											<s:textfield class="calendario" id="ingreso4" name="pyMEs.certificaciones[3].fechaCertificacion" value="%{fCert4}" size="10" maxlength="10" />
											<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador4" style="cursor: hand" />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Institución que certificó :" />
										</td>
										<td>
											<s:textfield size="30" id="instCert4" name="pyMEs.certificaciones[3].institutoCertificador" maxlength="100"></s:textfield>
										</td>
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
							<div id="cert5" ${pyMEs.certificaciones[4].idCertificado==null? ' style="display: none;" ':' style="display: block;"'}>
								<s:hidden name="pyMEs.certificaciones[4].idCertificado" id="idCert5" value="%{pyMEs.certificaciones[4].idCertificado}" />
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Certificación :" />
										</td>
										<td>
											<s:textfield size="30" id="certificacion5" name="pyMEs.certificaciones[4].certificacion" maxlength="150"></s:textfield>
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Año de certificación :" />
										</td>
										<td>
											<s:date name="pyMEs.certificaciones[4].fechaCertificacion" id="fCert5" format="dd/MM/yyyy" />
											<s:textfield class="calendario" id="ingreso5" name="pyMEs.certificaciones[4].fechaCertificacion" value="%{fCert5}" size="10" maxlength="10" />
											<img src="${pageContext.request.contextPath}/img/calendario.png" width="16" height="16" title="Seleccione una fecha" id="lanzador5" style="cursor: hand" />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Institución que certificó :" />
										</td>
										<td>
											<s:textfield size="30" id="instCert5" name="pyMEs.certificaciones[4].institutoCertificador" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<label class="quitar" onclick="javascript:supCert( 5 );">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
						</td>
						<td></td>
					</tr>
				</table>
				<label id="showCert" class="agregar" onclick="javascript:addCert();">+agregar otra Certificación</label>

				<!-- Inicia Seleccionar Diplomado -->
				<table>
					<tr>
						<td>
							<br />
							<s:label cssClass="titulosPyMEs" value="Diplomados que se ha participado" />
						</td>
					</tr>
					<tr>
						<td>
							<table>
								<tr>
									<td>
										<s:checkbox id="dip1" name="pyMEs.bDiplomadoCcmxUno" value="%{pyMEs.bDiplomadoCcmxUno}" />
										<s:label cssClass="etiquetaCaptura" value="Cultura organizacional" />
									</td>
								</tr>
								<tr>
									<td>
										<s:checkbox id="dip2" name="pyMEs.bDiplomadoCcmxDos" value="%{pyMEs.bDiplomadoCcmxDos}" />
										<s:label cssClass="etiquetaCaptura" value="Estrategia Planeación e Innovación" />
									</td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td>
										<s:checkbox id="dip3" name="pyMEs.bDiplomadoCcmxTres" value="%{pyMEs.bDiplomadoCcmxTres}" />
										<s:label cssClass="etiquetaCaptura" value="Reducción de Costos con Manufactura Esbelta" />
									</td>
								</tr>
								<tr>
									<td>
										<s:checkbox id="dip4" name="pyMEs.bDiplomadoCcmxCuatro" value="%{pyMEs.bDiplomadoCcmxCuatro}" />
										<s:label cssClass="etiquetaCaptura" value="Inteligencia Comercial" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br />

				<!-- Inicia Files -->
				<s:label cssClass="etiquetaCaptura" value="Incluir Archivo(s) Adjunto(s)" />
				<br />
				<div ${pyMEs.descArchivo1==null? ' style="display: block;" ':' style="display: none;"'}>
					<div style="width: 850px;">
						<br />
						<s:label cssClass="etiquetaAyuda" value="Estimada PyME, si desea incluir más detalles de su empresa o de los productos o servicios que provee,  lo puede hacer en esta sección, adjuntando los archivos que considere necesarios. Recuerde incluir catálogos de productos, planos, especificaciones, fotos de productos, etc. No olvide indicar qué contiene cada archivo que adjunte en el recuadro correspondiente para una mayor identificación." />
						<br /><br />
					</div>
				</div>
				<table>
					<tr>
						<td>
							<div id="idDivArchivo1Block" ${pyMEs.descArchivo1==null? ' style="display: block;" ':' style="display: none;"'}>
								<table>
									<tr>
										<td>
											<div ${pyMEs.descArchivo1==null? ' style="display: block;" ':' style="display: none;"'}>
												<s:file id="idCampoArchivo1" name="pyMEs.archivo1"></s:file>
											</div>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
											<s:textfield id="arch1" size="40" name="pyMEs.descArchivo1" maxlength="100"></s:textfield>
											<label class="quitar" onclick="javascript:supArchivo(1);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo2Block" style="display: none;">
								<table>
									<tr>
										<td>
											<div ${pyMEs.descArchivo2==null? ' style="display: block;" ':' style="display: none;"'}>
												<s:file id="idCampoArchivo2" name="pyMEs.archivo2"></s:file>
											</div>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
											<s:textfield id="arch2" size="40" name="pyMEs.descArchivo2" maxlength="100"></s:textfield>
											<label class="quitar" onclick="javascript:supArchivo(2);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo3Block" style="display: none;">
								<table>
									<tr>
										<td>
											<div ${pyMEs.descArchivo3==null? ' style="display: block;" ':' style="display: none;"'}>
												<s:file id="idCampoArchivo3" name="pyMEs.archivo3"></s:file>
											</div>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
											<s:textfield id="arch3" size="40" name="pyMEs.descArchivo3" maxlength="100"></s:textfield>
											<label class="quitar" onclick="javascript:supArchivo(3);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo4Block" style="display: none;">
								<table>
									<tr>
										<td>
											<div ${pyMEs.descArchivo4==null? ' style="display: block;" ':' style="display: none;"'}>
												<s:file id="idCampoArchivo4" name="pyMEs.archivo4"></s:file>
											</div>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
											<s:textfield id="arch4" size="40" name="pyMEs.descArchivo4" maxlength="100"></s:textfield>
											<label class="quitar" onclick="javascript:supArchivo(4);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo5Block" style="display: none;">
								<table>
									<tr>
										<td>
											<div ${pyMEs.descArchivo5==null? ' style="display: block;" ':' style="display: none;"'}>
												<s:file id="idCampoArchivo5" name="pyMEs.archivo5"></s:file>
											</div>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
											<s:textfield id="arch5" size="40" name="pyMEs.descArchivo5" maxlength="100"></s:textfield>
											<label class="quitar" onclick="javascript:supArchivo(5);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo6Block" style="display: none;">
								<table>
									<tr>
										<td>
											<div ${pyMEs.descArchivo6==null? ' style="display: block;" ':' style="display: none;"'}>
												<s:file id="idCampoArchivo6" name="pyMEs.archivo6"></s:file>
											</div>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
											<s:textfield id="arch6" size="40" name="pyMEs.descArchivo6" maxlength="100"></s:textfield>
											<label class="quitar" onclick="javascript:supArchivo(6);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo7Block" style="display: none;">
								<table>
									<tr>
										<td>
											<div ${pyMEs.descArchivo7==null? ' style="display: block;" ':' style="display: none;"'}>
												<s:file id="idCampoArchivo7" name="pyMEs.archivo7"></s:file>
											</div>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
											<s:textfield id="arch7" size="40" name="pyMEs.descArchivo7" maxlength="100"></s:textfield>
											<label class="quitar" onclick="javascript:supArchivo(7);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo8Block" style="display: none;">
								<table>
									<tr>
										<td>
											<div ${pyMEs.descArchivo8==null? ' style="display: block;" ':' style="display: none;"'}>
												<s:file id="idCampoArchivo8" name="pyMEs.archivo8"></s:file>
											</div>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
											<s:textfield id="arch8" size="40" name="pyMEs.descArchivo8" maxlength="100"></s:textfield>
											<label class="quitar" onclick="javascript:supArchivo(8);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo9Block" style="display: none;">
								<table>
									<tr>
										<td>
											<div ${pyMEs.descArchivo9==null? ' style="display: block;"
												':' style="display: none;"'}>
												<s:file id="idCampoArchivo9" name="pyMEs.archivo9"></s:file>
											</div>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
											<s:textfield id="arch9" size="40" name="pyMEs.descArchivo9" maxlength="100"></s:textfield>
											<label class="quitar" onclick="javascript:supArchivo(9);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo10Block" style="display: none;">
								<table>
									<tr>
										<td>
											<div ${pyMEs.descArchivo10==null? ' style="display: block;" ':' style="display: none;"'}>
												<s:file id="idCampoArchivo10" name="pyMEs.archivo10"></s:file>
											</div>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
											<s:textfield id="arch10" size="40" name="pyMEs.descArchivo10" maxlength="100"></s:textfield>
											<label class="quitar" onclick="javascript:supArchivo(10);">-eliminar</label>
										</td>
									</tr>
								</table>
							</div>
							<div id="ayudaArchivos" ${pyMEs.descArchivo1!=null || pyMEs.descArchivo2!=null
								|| pyMEs.descArchivo3!=null || pyMEs.descArchivo4!=null
								|| pyMEs.descArchivo5!=null || pyMEs.descArchivo6!=null
								|| pyMEs.descArchivo7!=null || pyMEs.descArchivo8!=null
								|| pyMEs.descArchivo9!=null || pyMEs.descArchivo10!=null?
								' style="display: none;" ':' style="display: block;"'}>
								<s:label cssClass="etiquetaAyuda" value="Indique el o los archivos que serán incluidos. Máximo 2MB (.pdf .doc .png)" />
								<br />
							</div>
							<div>
								<label id="showArchivo" class="agregar" onclick="javascript:otroArchivo();">+agregar otro archivo</label>	
							</div>
							
							<div ${pyMEs.descArchivo1!=null || pyMEs.descArchivo2!=null
								|| pyMEs.descArchivo3!=null || pyMEs.descArchivo4!=null
								|| pyMEs.descArchivo5!=null || pyMEs.descArchivo6!=null
								|| pyMEs.descArchivo7!=null || pyMEs.descArchivo8!=null
								|| pyMEs.descArchivo9!=null || pyMEs.descArchivo10!=null?
								' style="display: block;" ':' style="display: none;"'}>
								<table>
									<tr>
										<td class="encabezadoTablaResumen" colspan="3" align="center"
											style="width: 800px;">Descripción de los archivos adjuntos</td>
									</tr>
									<tr>
										<td class="cuerpo2TablaResumen" align="left"
											style="width: 300px;">&nbsp;Descripción del archivo :</td>
										<td class="cuerpo2TablaResumen" align="left"
											style="width: 300px;">&nbsp;Descargar archivo adjunto:</td>
										<td class="cuerpo2TablaResumen" align="left"
											style="width: 150px;">&nbsp;Eliminar archivo :</td>
									</tr>
									<s:if test="pyMEs.archivo1FileName != null">
										<tr id="descArch1">
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 300px;">${pyMEs.descArchivo1}</td>
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 300px;"><a
												href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo1}&nameArchivo=${pyMEs.archivo1FileName}&mimeArchivo=${pyMEs.archivo1ContentType}">${pyMEs.archivo1FileName}</a>
											</td>
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 150px;"><label class="quitar"
												onclick="javascript:supArchivo(1);">-eliminar</label>
											</td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo2FileName != null">
										<tr id="descArch2">
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 300px;">${pyMEs.descArchivo2}</td>
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 300px;"><a
												href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo2}&nameArchivo=${pyMEs.archivo2FileName}&mimeArchivo=${pyMEs.archivo2ContentType}">${pyMEs.archivo2FileName}</a>
											</td>
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 150px;"><label class="quitar"
												onclick="javascript:supArchivo(2);">-eliminar</label>
											</td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo3FileName != null">
										<tr id="descArch3">
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 300px;">${pyMEs.descArchivo3}</td>
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 300px;"><a
												href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo3}&nameArchivo=${pyMEs.archivo3FileName}&mimeArchivo=${pyMEs.archivo3ContentType}">${pyMEs.archivo3FileName}</a>
											</td>
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 150px;"><label class="quitar"
												onclick="javascript:supArchivo(3);">-eliminar</label>
											</td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo4FileName != null">
										<tr id="descArch4">
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 300px;">${pyMEs.descArchivo4}</td>
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 300px;"><a
												href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo4}&nameArchivo=${pyMEs.archivo4FileName}&mimeArchivo=${pyMEs.archivo4ContentType}">${pyMEs.archivo4FileName}</a>
											</td>
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 150px;"><label class="quitar"
												onclick="javascript:supArchivo(4);">-eliminar</label>
											</td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo5FileName != null">
										<tr id="descArch5">
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 300px;">${pyMEs.descArchivo5}</td>
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 300px;"><a
												href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo5}&nameArchivo=${pyMEs.archivo5FileName}&mimeArchivo=${pyMEs.archivo5ContentType}">${pyMEs.archivo5FileName}</a>
											</td>
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 150px;"><label class="quitar"
												onclick="javascript:supArchivo(2);">-eliminar</label>
											</td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo6FileName != null">
										<tr id="descArch6">
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 300px;">${pyMEs.descArchivo6}</td>
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 300px;"><a
												href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo6}&nameArchivo=${pyMEs.archivo6FileName}&mimeArchivo=${pyMEs.archivo6ContentType}">${pyMEs.archivo6FileName}</a>
											</td>
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 150px;"><label class="quitar"
												onclick="javascript:supArchivo(6);">-eliminar</label>
											</td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo7FileName != null">
										<tr id="descArch7">
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 300px;">${pyMEs.descArchivo7}</td>
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 300px;"><a
												href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo7}&nameArchivo=${pyMEs.archivo7FileName}&mimeArchivo=${pyMEs.archivo7ContentType}">${pyMEs.archivo7FileName}</a>
											</td>
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 150px;"><label class="quitar"
												onclick="javascript:supArchivo(7);">-eliminar</label>
											</td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo8FileName != null">
										<tr id="descArch8">
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 300px;">${pyMEs.descArchivo8}</td>
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 300px;"><a
												href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo8}&nameArchivo=${pyMEs.archivo8FileName}&mimeArchivo=${pyMEs.archivo8ContentType}">${pyMEs.archivo8FileName}</a>
											</td>
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 150px;"><label class="quitar"
												onclick="javascript:supArchivo(8);">-eliminar</label>
											</td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo9FileName != null">
										<tr id="descArch9">
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 300px;">${pyMEs.descArchivo9}</td>
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 300px;"><a
												href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo9}&nameArchivo=${pyMEs.archivo9FileName}&mimeArchivo=${pyMEs.archivo9ContentType}">${pyMEs.archivo9FileName}</a>
											</td>
											<td class="cuerpo1TablaResumen" align="left"
												style="width: 150px;"><label class="quitar"
												onclick="javascript:supArchivo(9);">-eliminar</label>
											</td>
										</tr>
									</s:if>
									<s:if test="pyMEs.archivo10FileName != null">
										<tr id="descArch10">
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 300px;">${pyMEs.descArchivo10}</td>
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 300px;"><a
												href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo10}&nameArchivo=${pyMEs.archivo10FileName}&mimeArchivo=${pyMEs.archivo10ContentType}">${pyMEs.archivo10FileName}</a>
											</td>
											<td class="cuerpo2TablaResumen" align="left"
												style="width: 150px;"><label class="quitar"
												onclick="javascript:supArchivo(10);">-eliminar</label>
											</td>
										</tr>
									</s:if>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<br />

				<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td><input class="botonenviar" value="Anterior" type="button"
							onclick="javascript:document.getElementById('sec6').style.display='none'; javascript:document.getElementById('sec5').style.display='block';" />
						</td>
						<td><input class="botonenviar" value="Siguiente"
							type="button" onclick="javascript:return validacion('6');" />
						</td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
			<!-- Termina Seccion 6 -->


			<!-- Inicia Seccion 7 -->
			<div id="sec7" style="display: none;">
				<!-- Inicia Indicadores -->
				<br />
				<s:label cssClass="titulosPyMEs" value="Indicadores de resultados." />

				<table>
					<tr>
						<td style="width: 400px;">
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Ventas o ingresos  acumulados (antes) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="ingresosAnt" size="60" name="indicadores.ingresosAntes" maxlength="100" onkeypress="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaAyuda" value="Indique el valor en Millones" /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Número de clientes (antes) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="clientesAnt" size="60" name="indicadores.clientesAntes" maxlength="100" onkeypress="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Número de empleados (antes) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="empleadosAnt" size="60" name="indicadores.empleadosAntes" maxlength="100" onkeypress="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="% Egresos / Ventas (antes) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="egresosAnt" size="60" name="indicadores.egresosAntes" maxlength="100" onkeypress="return validaNumero(event)"></s:textfield></td>
								</tr>
							</table></td>
						<td style="width: 400px;">
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Ventas o ingresos  acumulados (después) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="ingresosDesp" size="60" name="indicadores.ingresosDespues" maxlength="100" onkeypress="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaAyuda" value="Indique el valor en Millones" /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Número de clientes (después) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="clientesDesp" size="60" name="indicadores.clientesDespues" maxlength="100" onkeypress="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Número de empleados (después) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="empleadosDesp" size="60" name="indicadores.empleadosDespues" maxlength="100" onkeypress="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="% Egresos / Ventas (después) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="egresosDesp" size="60" name="indicadores.egresosDespues" maxlength="100" onkeypress="return validaNumero(event)"></s:textfield></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

				<!-- Inicia Requerimentos de compra -->
				<br />
				<table>
					<tr>
						<td colspan="2"><s:label cssClass="titulosPyMEs" value="Requerimientos de compra." /></td>
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
								<s:checkbox id="reqNo" name="pyMEsReqNo" onclick="javascript:recibeReqNo();" value="true" />
							</s:if>
							<s:else>
								<s:checkbox id="reqNo" name="pyMEsReqNo" onclick="javascript:recibeReqNo();" value="" />
							</s:else>
						</td>
					</tr>
				</table>
				<br />
				<table id="showCatalogos" ${pyMEs.bRecibeRequerimientosCompra==false? ' style="display: none;" ':' style="display: block;"'}>
					<tr>
						<td colspan="2">
							<s:label cssClass="etiquetaCaptura" value="Seleccione la industria o industrias a las que se dedica su empresa:" />
						</td>
					</tr>
					<tr>
						<td>
							<div id="comboCat1" ${pyMEs.categorias[0].cveScian!=0? ' style="display: block;" ':' style="display: none;"'}>
								<select id="catProd1" name="cat1" style="width: 500px;" onchange="javascript: showCombo(this.value, 2);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
									<option selected="selected" value="-1">--Seleccione una opción--</option>
									<s:iterator value="listCatProductos" status="stat">
										<option value="${cveScian}">${descScian}</option>
									</s:iterator>
								</select>
							</div>
							
							<select id="catProd2" name="cat2" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, 3);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
								<option selected="selected" value="-1">--Seleccione una opción--</option>
								<s:iterator value="listCat2" status="stat">
									<option value="${cveScian}">${descScian}</option>
								</s:iterator>
							</select>
							<select id="catProd3" name="cat3" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, 4);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
								<option selected="selected" value="-1">--Seleccione una opción--</option>
								<s:iterator value="listCat3" status="stat">
									<option value="${cveScian}">${descScian}</option>
								</s:iterator>
							</select>
							<select id="catProd4" name="cat4" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, 5);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
								<option selected="selected" value="-1">--Seleccione una opción--</option>
								<s:iterator value="listCat4" status="stat">
									<option value="${cveScian}">${descScian}</option>
								</s:iterator>
							</select>
							<select id="catProd5" name="cat5" style="width: 500px; display: none;" onchange="javascript: showCombo(this.value, 6);" onfocus="javascript:focoAyuda('idDivTipPro');" onblur="javascript:blurAyuda('idDivTipPro');">
								<option selected="selected" value="-1">--Seleccione una opción--</option>
								<s:iterator value="listCat5" status="stat">
									<option value="${cveScian}">${descScian}</option>
								</s:iterator>
							</select>
						</td>
						<td>
							<div id="idDivTipPro" style="display: none; margin-bottom: 0px; margin-top: -10px;">
								<s:label cssClass="etiquetaAyuda" value="Seleccione la categoría en la cual se encuentra su producto para agregarla." />
								<br />
							</div>
							<div id="idDivTipPro2" style="display: block; margin-bottom: 0px; margin-top: -5px;">
								<s:label cssClass="etiquetaAyuda" value="" />
								<br />
							</div>
						</td>
					</tr>
				</table>
				<table id="showNombreCat" width="95%" ${pyMEs.bRecibeRequerimientosCompra==false? ' style="display: none;" ':' style="display: block;"'}>
					<tr>
						<td>
							<table width="100%" cellspacing="1" cellpadding="1">
								<tr>
									<td class="encabezadoTablaResumen" colspan="3" align="center"><b>Descripción de las catagorías de su industria</b></td>
								</tr>
								<tr>
									<td class="cuerpo1TablaResumen" align="center" style="width: 10%;"><b>No.</b></td>
									<td class="cuerpo1TablaResumen" align="center" style="width: 75%;"><b>Categoría del poducto</b></td>
									<td class="cuerpo1TablaResumen" align="center" style="width: 15%;"><b>Eliminar categoría</b></td>
								</tr>
							</table>
							<s:iterator status="stat" value="(10).{ #this }" >
								<div id="idDivCat${stat.count}" ${!(pyMEs.categorias[stat.index]==null)?' style="display: block;"':' style="display: none;"'}>
									<table width="100%" cellspacing="1" cellpadding="1">
										<tr>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 10%;" align="center">
												${stat.count}
												<s:hidden id="idCatHid%{#stat.count}" name="pyMEs.categorias[%{#stat.index}].cveScian" value="%{pyMEs.categorias[#stat.index].cveScian}" />
												<s:hidden id="idDesCatHid%{#stat.count}" name="pyMEs.categorias[%{#stat.index}].descScian" value="%{pyMEs.categorias[#stat.index].descScian}" />
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 75%;">
												<s:label id="labCat%{#stat.count}" cssClass="etiquetaCaptura" value="%{pyMEs.categorias[#stat.index].descScian}" />
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" style="width: 15%;" align="center">
												<label class="quitar" onclick="quitarCategoria(${stat.count});">-quitar</label>
											</td>
										</tr>
									</table>
								</div>
							</s:iterator>
						</td>
					</tr>
				</table>
				<table>
					
				</table>
				<!-- Botones -->
				<table class="submit_tabla">
					<tr>
						<td style="width: 250px;"></td>
						<td><input class="botonenviar" value="Anterior" type="button"
							onclick="javascript:document.getElementById('sec7').style.display='none'; javascript:document.getElementById('sec6').style.display='block';" />
						</td>
						<td>
							<s:submit id="idBotonEnviar" cssClass="botonenviar" value="Actualizar PyME" />
						</td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
			</div>
			<!-- Termina Seccion 7 -->

			<!-- Bloque Hidden's -->

			<s:hidden name="domicilios.idDomicilio" id="idDomicilio"
				value="%{domicilios.idDomicilio}" />
			<s:hidden name="indicadores.idIndicador" id="idIndicador"
				value="%{indicadores.idIndicador}" />

			<!-- Bloque Hidden's -->

		</s:form>
		<!-- EXPEDIENTE PYME -->
		<div id="resumenPyME" ${pyMEs.personalidadJuridica!=null? ' style="display: block;" ':' style="display: none;"' }>
			<br /><br />
			<table class="expediente_tabla" width="99%">
				<tr>
					<td class="encabezadoTablaResumen" colspan="2" align="center">Expediente PyME</td>
				</tr>
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2">
						<s:label cssClass="etiquetaResumen">${pyMEs.nombreComercial}</s:label>
					</td>
				</tr>
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2">
						<s:label cssClass="etiquetaResumen">${pyMEs.mensajeVentas}</s:label>
					</td>
				</tr>

				<!-- PRODUCTOS -->			
				<tr>
					<td colspan="2">
						<div class="flotantes">
							<table width="101%">
								<tr>
									<td class="encabezadoTablaResumen" align="center">Principales Productos o Servicios</td>
								</tr>
								<!-- PRODUCTOS -->
								<s:iterator status="stat" value="pyMEs.productos" >
									<tr>
										<td class="cuerpo1TextoResumen">
											<ul>
												<li>
													<s:label cssClass="etiquetaResumen">${pyMEs.productos[stat.index].producto}</s:label>
												</li>
											</ul>
										</td>
									</tr>
								</s:iterator>
							</table>
						</div>
						<div class="flotantes">
							<table width="103%">
								<tr>
									<td class="encabezadoTablaResumen" align="center">Principales Clientes</td>
								</tr>
								<!-- TRACTORA --><!-- CLIENTES -->
								<s:iterator status="stat" value="pyMEs.clientes" >
									<tr>
										<td class="cuerpo1TextoResumen">
											<ul>
												<li>
													<s:label cssClass="etiquetaResumen">${pyMEs.clientes[stat.index].cliente}</s:label>
												</li>
											</ul>
										</td>
									</tr>
								</s:iterator>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div>
							<table width="100%">
								<tr>
									<td class="encabezadoTablaResumen" colspan="3" align="center">Indicadores CCMX</td>
								</tr>
								<tr>
									<td class="cuerpo1TablaResumen" align="center" style="width: 100px;">&nbsp;</td>
									<td class="cuerpo1TablaResumen" align="center" style="width: 170px;">Antes</td>
									<td class="cuerpo1TablaResumen" align="center" style="width: 170px;">Después</td>
								</tr>
								<tr>
									<td class="cuerpo1TablaResumen" style="height: 29px;">Ventas o ingresos acumulados:</td>
									<td class="cuerpo1TextoResumen">${indicadores.ingresosAntes}</td>
									<td class="cuerpo1TextoResumen">${indicadores.ingresosDespues}</td>
								</tr>
								<tr>
									<td class="cuerpo1TablaResumen" style="height: 29px;">Numero de clientes:</td>
									<td class="cuerpo1TextoResumen">${indicadores.clientesAntes}</td>
									<td class="cuerpo1TextoResumen">${indicadores.clientesDespues}</td>
								</tr>
								<tr>
									<td class="cuerpo1TablaResumen" style="height: 29px;">Número de empleados:</td>
									<td class="cuerpo1TextoResumen">${indicadores.empleadosAntes}</td>
									<td class="cuerpo1TextoResumen">${indicadores.empleadosDespues}</td>
								</tr>
								<tr>
									<td class="cuerpo1TablaResumen" style="height: 29px;">% Egresos / Ventas:</td>
									<td class="cuerpo1TextoResumen">${indicadores.egresosAntes}</td>
									<td class="cuerpo1TextoResumen">${indicadores.egresosDespues}</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>

				<!-- ESTADOS -->
				<tr>
					<td class="encabezadoTablaResumen" colspan="2" align="center">Estados en los que puede suministrar su producto</td>
				</tr>
				<s:if test="estadosVentas.nacional != null">
					<tr>
						<td class="cuerpo1TextoResumen" colspan="2" align="center"><s:label cssClass="etiquetaResumen">${estadosVentas.nacional}</s:label></td>
					</tr>
				</s:if>
				<tr>
					<td colspan="2">
						<s:if test="estadosVentas.aguascalientes != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.aguascalientes}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.bajaCaliforniaNorte != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.bajaCaliforniaNorte}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.bajaCaliforniaSur != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.bajaCaliforniaSur}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.campeche != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.campeche}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.chiapas != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.chiapas}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.chihuahua != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.chihuahua}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.coahuila != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.coahuila}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.colima != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.colima}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.distritoFederal != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.distritoFederal}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.durango !=  null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.durango}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.guanajuato != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.guanajuato}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.guerrero != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.guerrero}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.hidalgo!= null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.hidalgo}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.jalisco != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.jalisco}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.estadoDeMexico != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.estadoDeMexico}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.michoacan != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.michoacan}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.morelos != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.morelos}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.nayarit != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.nayarit}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.nuevoLeon != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.nuevoLeon}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.oaxaca != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.oaxaca}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.puebla != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.puebla}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.queretaro != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.queretaro}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.quintanaRoo != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.quintanaRoo}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.sanLuisPotosi != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.sanLuisPotosi}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.sinaloa != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.sinaloa}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						
						<s:if test="estadosVentas.sonora != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.sonora}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.tabasco != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.tabasco}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.tamaulipas != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.tamaulipas}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.tlaxcala != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.tlaxcala}
										</li>
									</ul>
								</div>
							</div>			
						</s:if>
						<s:if test="estadosVentas.veracruz!= null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.veracruz}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.yucatan != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.yucatan}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
						<s:if test="estadosVentas.zacatecas != null">
							<div class="flotantes2">
								<div class="cuerpo1TextoResumen">
									<ul>
										<li>
											${estadosVentas.zacatecas}
										</li>
									</ul>
								</div>
							</div>
						</s:if>
					</td>
				</tr>				
				<tr>
					<td class="encabezadoTablaResumen" colspan="2" align="center">Datos de contacto</td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%">
							<tr>
								<td class="cuerpo1TablaResumen" align="center" colspan="2">Ventas</td>
								<td class="cuerpo1TablaResumen" align="center" colspan="2">Otro</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Nombre:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.contactos[0].nombre} ${pyMEs.contactos[0].apellidoPaterno} ${pyMEs.contactos[0].apellidoMaterno}</td>
								<td class="cuerpo1TablaResumen" style="width: 150px;">Nombre:</td>
								<td class="cuerpo1TextoResumen" style="width: 380px;">${pyMEs.contactos[1].nombre} ${pyMEs.contactos[1].apellidoPaterno} ${pyMEs.contactos[1].apellidoMaterno}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Teléfono:</td>
								<td class="cuerpo1TextoResumen">${pyMEs.contactos[0].telefono}</td>
								<td class="cuerpo1TablaResumen">Telefóno:</td>
								<td class="cuerpo1TextoResumen">${pyMEs.contactos[1].telefono}</td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen">Correo Electrónico:</td>
								<td class="cuerpo1TextoResumen">${pyMEs.contactos[0].correoElectronico}</td>
								<td class="cuerpo1TablaResumen">Correo Electrónico:</td>
								<td class="cuerpo1TextoResumen">${pyMEs.contactos[1].correoElectronico}</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="encabezadoTablaResumen" align="center" colspan="2">Certificaciones</td>
				</tr>
				<s:if test="pyMEs.certificaciones!=null">
					<s:iterator status="stat" value="pyMEs.certificaciones" >
						<tr>
							<td class="cuerpo1TablaResumen">Certificación:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.certificaciones[stat.index].certificacion}</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen">Año:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.certificaciones[stat.index].fechaCertificacion}</td>
						</tr>
						<tr>
							<td class="cuerpo1TablaResumen">Institución:</td>
							<td class="cuerpo1TextoResumen">${pyMEs.certificaciones[stat.index].institutoCertificador}</td>
						</tr>
					</s:iterator>
				</s:if>
				<tr>
					<td class="encabezadoTablaResumen" align="center" colspan="2">Diplomados</td>
				</tr>
				<tr>
					<td class="cuerpo1TablaResumen">Diplomado en:</td>
					<td class="cuerpo1TextoResumen">
						<s:if test="pyMEs.bDiplomadoCcmxUno==true">
							Cultura organizacional y la competitividad de las empresas
						</s:if>
					</td>
				</tr>
				<s:if test="pyMEs.bDiplomadoCcmxDos==true">
					<tr>
						<td class="cuerpo1TablaResumen">Diplomado en:</td>
						<td class="cuerpo1TextoResumen">
							Estrategia Comercial, Imagen y Cadena de Distribución
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.bDiplomadoCcmxTres==true">
					<tr>
						<td class="cuerpo1TablaResumen">Diplomado en:</td>
						<td class="cuerpo1TextoResumen">
								Desarrollo de Métodos de Producción Esbeltos y Cadena de Valor
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.bDiplomadoCcmxCuatro==true">
					<tr>
						<td class="cuerpo1TablaResumen">Diplomado en:</td>
						<td class="cuerpo1TextoResumen">
								Estrategia, Planeación e Innovación
						</td>
					</tr>
				</s:if>
				<tr>
					<td class="encabezadoTablaResumen" colspan="2" align="center">Conozca más sobre nuestros productos y sobre nuestra empresa</td>
				</tr>
				<!-- ARCHIVOS -->
				<s:if test="pyMEs.idArchivo1!=0">
					<tr>
						<td class="cuerpo1TablaResumen">${pyMEs.descArchivo1}</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo1}&nameArchivo=${pyMEs.archivo1FileName}&mimeArchivo=${pyMEs.archivo1ContentType}">${pyMEs.archivo1FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo2!=0">
					<tr>
						<td class="cuerpo1TablaResumen">${pyMEs.descArchivo2}</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo2}&nameArchivo=${pyMEs.archivo2FileName}&mimeArchivo=${pyMEs.archivo2ContentType}">${pyMEs.archivo2FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo3!=0">
					<tr>
						<td class="cuerpo1TablaResumen">${pyMEs.descArchivo3}</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo3}&nameArchivo=${pyMEs.archivo3FileName}&mimeArchivo=${pyMEs.archivo3ContentType}">${pyMEs.archivo3FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo4!=0">
					<tr>
						<td class="cuerpo1TablaResumen">${pyMEs.descArchivo4}</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo4}&nameArchivo=${pyMEs.archivo4FileName}&mimeArchivo=${pyMEs.archivo4ContentType}">${pyMEs.archivo4FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo5!=0">
					<tr>
						<td class="cuerpo1TablaResumen">${pyMEs.descArchivo5}</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo5}&nameArchivo=${pyMEs.archivo5FileName}&mimeArchivo=${pyMEs.archivo5ContentType}">${pyMEs.archivo5FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo6!=0">
					<tr>
						<td class="cuerpo1TablaResumen">${pyMEs.descArchivo6}</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo6}&nameArchivo=${pyMEs.archivo6FileName}&mimeArchivo=${pyMEs.archivo6ContentType}">${pyMEs.archivo6FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo7!=0">
					<tr>
						<td class="cuerpo1TablaResumen">${pyMEs.descArchivo7}</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo7}&nameArchivo=${pyMEs.archivo7FileName}&mimeArchivo=${pyMEs.archivo7ContentType}">${pyMEs.archivo7FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo8!=0">
					<tr>
						<td class="cuerpo1TablaResumen">${pyMEs.descArchivo8}</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo8}&nameArchivo=${pyMEs.archivo8FileName}&mimeArchivo=${pyMEs.archivo8ContentType}">${pyMEs.archivo8FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo9!=0">
					<tr>
						<td class="cuerpo1TablaResumen">${pyMEs.descArchivo9}</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo9}&nameArchivo=${pyMEs.archivo9FileName}&mimeArchivo=${pyMEs.archivo9ContentType}">${pyMEs.archivo9FileName}</a>
						</td>
					</tr>
				</s:if>
				<s:if test="pyMEs.idArchivo10!=0">
					<tr>
						<td class="cuerpo1TablaResumen">${pyMEs.descArchivo10}</td>
						<td class="cuerpo1TextoResumen">
							<a href="${pageContext.request.contextPath}/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo10}&nameArchivo=${pyMEs.archivo10FileName}&mimeArchivo=${pyMEs.archivo10ContentType}">${pyMEs.archivo10FileName}</a>
						</td>
					</tr>
				</s:if>
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2" align="center"><s:label cssClass="etiquetaResumen">${pyMEs.rfc}</s:label>
					</td>
				</tr>
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2" align="center">
						<s:label cssClass="etiquetaResumen">${pyMEs.paginaWeb}</s:label>
					</td>
				</tr>
				<tr>
					<td class="cuerpo1TextoResumen" colspan="2" align="center">
						<s:label cssClass="etiquetaResumen">
							<s:if test="domicilios.calle != null">${domicilios.calle} </s:if>
							<s:if test="domicilios.numExt != null">${domicilios.numExt} </s:if>
							<s:if test="domicilios.numInt != null">${domicilios.numInt} </s:if>
							<s:if test="domicilios.piso != null">${domicilios.piso} </s:if>
							<s:if test="domicilios.colonia != null">${domicilios.colonia} </s:if>
							<s:if test="domicilios.delegacion != null">${domicilios.delegacion} </s:if>
							<s:if test="domicilios.estado != null">${domicilios.estado} </s:if>
							<s:if test="domicilios.codigoPostal != null">${domicilios.codigoPostal}</s:if>
						</s:label>
					</td>
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
					<td>
						<input class="botonenviar" value="Modificar" type="button" onclick="javascript: modificar();" />
					</td>
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
	<div class="overlay-container">
		<div class="window-container zoomin" id="idDivWc">
			<fieldset id="requerimientos">
				<!-- inicia PF Aviso de Privacidad -->
				<div id="idDivAvisoPrivacidad" style="display: none; height: 460px; overflow-y: scroll; padding-right: 20px;">
					<p style='line-height: normal; text-align: center'>
						<span style='font-size: 18.0pt; font-family: "Calibri", "sans-serif"; color: #636363'><b>AVISO DE PRIVACIDAD</b></span>
					</p>
					<p style='line-height: normal'>
						<b><i><span style='font-size: 14.0pt; font-family: "Candara", "sans-serif"; color: #400080'>Responsable.</span></i></b>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>CENTRO DE COMPETITIVIDAD DE M&Eacute;XICO, A.C. </span></b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>(en lo sucesivo <b>CCMX</b>) hace de su conocimiento sus
							derechos y obligaciones previstos en la Ley Federal de Protecci&oacute;n de Datos Personales en Posesi&oacute;n de los Particulares, y que ser&aacute; responsabilidad de esta entidad el uso y tratamiento de los mismos.
						</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>CCMX</span></b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'> con domicilio ubicado en </span><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"'>Avenida
							Fundidora 501-95&ordf;, Colonia Obrera, Monterrey, Nuevo Le&oacute;n, C.P. 64010, M&eacute;xico</span><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>, y con direcci&oacute;n de correo electr&oacute;nico:</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<i><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: blue'></span></i><a href="mailto:avisodeprivacidad@ccmx.com.mx"><i><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: blue; text-decoration: underline'>avisodeprivacidad@ccmx.com.mx</span></i></a>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal; text-autospace: none'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span></b>
					</p>
					<p style='line-height: normal'>
						<b><i><span style='font-size: 14.0pt; font-family: "Candara", "sans-serif"; color: #400080'>Responsabilidad.</span></i></b>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>CCMX</span></b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'> se obliga y compromete a proteger aquellos Datos Personales que sean proporcionados y/o recabados del <b>TITULAR</b>;
							ya sea, a trav&eacute;s de cualquier medio electr&oacute;nico, formato en papel, formato electr&oacute;nico de la p&aacute;gina de internet de <b>CCMX, </b>as&iacute; como el Sistema de Vinculaci&oacute;n de <b>CCMX</b>, tel&eacute;fono u otro que tenga como finalidad la obtenci&oacute;n de
							Datos Personales; as&iacute; mismo, se compromete a solamente utilizar dichos datos por el periodo que sea requerido; o bien, tan pronto las legislaciones en materia laboral, fiscal y/o cualquier otra que regule la relaci&oacute;n entre <b>CCMX</b> y el <b>TITULAR</b> de los datos lo permita, de
							conformidad y en cumplimiento a las obligaciones contenidas en las Leyes correspondientes.
						</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal; text-autospace: none'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span></b>
					</p>
					<p style='line-height: normal'>
						<b><i><span style='font-size: 14.0pt; font-family: "Candara", "sans-serif"; color: #400080'>&iquest;Qu&eacute; Datos Personales se recaban?.</span></i></b>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Se consideran &quot;Datos Personales&quot; cualquier informaci&oacute;n que acredite la personalidad e identidad de su persona tal y como son: Nombre(s) y apellidos, domicilio, n&uacute;mero
							telef&oacute;nico (casa o celular), correo electr&oacute;nico, p&aacute;gina web, as&iacute; como los que permitan identificar de manera plena la personalidad del <b>TITULAR</b>.
						</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>As&iacute; mismo, <b>CCMX, </b>podr&aacute; recabar Datos Personales adicionales como son: Ventas anuales, ventas o ingresos acumulados (antes), % Egresos/Ventas (antes), Ventas o ingresos acumulados
							(despu&eacute;s), % Egresos/Ventas (despu&eacute;s).
						</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal; text-autospace: none'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span></b>
					</p>
					<p style='line-height: normal'>
						<b><i><span style='font-size: 14.0pt; font-family: "Candara", "sans-serif"; color: #400080'>Finalidades del Tratamiento.</span></i></b>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"'>De conformidad con los art&iacute;culos 16, fracci&oacute;n II de la </span><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"'>Ley Federal de Protecci&oacute;n de Datos Personales en Posesi&oacute;n de
							los Particulares y art&iacute;culos 14, 30, 40, 41 y 42 del Reglamento de la Ley Federal de Protecci&oacute;n de Datos Personales en Posesi&oacute;n de los Particulares, se deber&aacute; describir en el presente Aviso de Privacidad las finalidades para las cuales se tratar&aacute;n los datos
							personales, lo cual se hace en los siguientes t&eacute;rminos:</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"'>Las finalidades principales para las cuales se tratar&aacute;n los Datos Personales recabados, ser&aacute;n &uacute;nica y exclusivamente para </span><span
							style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>la puesta en contacto del <b>TITULAR</b> con empresas grandes, con
						</span><span style='font-family: "Candara", "sans-serif"; color: black'>PYMES, con otras empresas relacionadas o consultores externos que requieran de sus datos, </span><span style='font-family: "Candara", "sans-serif"; color: black'>af&iacute;n de apoyarlo en aumento en ventas, as&iacute; como
							la mejora de pr&aacute;cticas comerciales, administrativas y de negocios y recibir asesor&iacute;as personalizadas buscando el bien de su negocio.</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"'>Las finalidades accesorias para las cuales se tratar&aacute;n los Datos Personales recabados, ser&aacute;n &uacute;nica y exclusivamente para fines mercadot&eacute;cnicos, publicitarios o de prospecci&oacute;n comercial.</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal; text-autospace: none'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span></b>
					</p>
					<p style='line-height: normal'>
						<b><i><span style='font-size: 14.0pt; font-family: "Candara", "sans-serif"; color: #400080'>Datos personales patrimoniales y financieros.</span></i></b>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>CCMX</span></b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'> hace del conocimiento del <b>TITULAR </b>que tambi&eacute;n podr&aacute;n ser recabados los siguientes
							&quot;Datos Personales financieros o patrimoniales&quot;, los cuales son
						</span><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>aqu&eacute;llos que se refieren a la situaci&oacute;n patrimonial o financiera tales como </span><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Ventas anuales, ventas o
							ingresos acumulados (antes), % Egresos/Ventas (antes), Ventas o ingresos acumulados (despu&eacute;s), % Egresos/Ventas (despu&eacute;s).</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>CCMX</span></b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'> se compromete a que todos los Datos Personales que el <b>TITULAR</b> le proporcione, o <b>CCMX</b>
							obtenga de &eacute;l, ser&aacute;n tratados bajo las m&aacute;s estrictas medidas de seguridad que garanticen su confidencialidad y buen uso para las finalidades descritas en el presente Aviso de Privacidad.
						</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Siendo estos Datos Personales financieros o patrimoniales recabados &uacute;nica y exclusivamente por ser necesarios para el conocimiento de su negocio y con ello tener un perfil claro del mismo y poder
							lograr los objetivos de vinculaci&oacute;n acordados entre el </span><b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>TITULAR</span></b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'> y <b>CCMX</b>.
						</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal; text-autospace: none'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span></b>
					</p>
					<p style='line-height: normal'>
						<b><i><span style='font-size: 14.0pt; font-family: "Candara", "sans-serif"; color: #400080'>Tratamiento de la Informaci&oacute;n.</span></i></b>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph; line-height: normal; text-autospace: none'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>CCMX </span></b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>garantiza que los Datos Personales que recabe u obtenga del <b>TITULAR</b> ser&aacute;n utilizados
							&uacute;nica y exclusivamente para los fines mencionados en el presente aviso de privacidad, que dicha informaci&oacute;n no estar&aacute; al alcance ni se podr&aacute; acceder a ella por personas ajenas a los procesos mencionados, que estar&aacute;n protegidos con las medidas de seguridad
							f&iacute;sicas, administrativas y t&eacute;cnicas suficientes para su protecci&oacute;n y confidencialidad y que dichos datos ser&aacute;n tratados solamente por el per&iacute;odo de tiempo que sea requerido para el cumplimiento de los fines mencionados en el presente aviso de privacidad y una
							vez cumplidos estos fines se proceder&aacute; a la cancelaci&oacute;n, bloqueo y eliminaci&oacute;n de los mismos en los tiempos y formas que las Leyes respectivas lo indiquen.
						</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-family: "Candara", "sans-serif"; color: black'>De igual manera, y por ser necesario para el cumplimiento de los fines mencionados por este aviso de privacidad, <b>CCMX</b> podr&aacute; transferir sus Datos Personales a PYMES o a otras empresas relacionadas o consultores
							externos que requieran de sus datos para ponerlos en
						</span><span style='font-family: "Candara", "sans-serif"; color: black'>vinculaci&oacute;n con el <b>TITULAR</b>, af&iacute;n de apoyarlo en aumento en ventas, as&iacute; como la mejora de pr&aacute;cticas comerciales, administrativas y de negocios y recibir asesor&iacute;as personalizadas
							buscando el bien de su negocio;
						</span><span style='font-family: "Candara", "sans-serif"; color: black'>y </span><span style='font-family: "Candara", "sans-serif"; color: black'>en cuyo caso, la informaci&oacute;n que sea transferida ser&aacute; tratada con apego a lo estipulado por este aviso de privacidad.<b> </b></span>
					</p>
					<p style='margin-bottom: 12.0pt; text-align: justify; text-justify: inter-ideograph; line-height: normal; text-autospace: none'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>CCMX</span></b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'> se compromete a no transferir sus Datos Personales a ning&uacute;n tercero distinto a los referidos y/o
							ajeno a tales procesos sin el consentimiento del <b>TITULAR</b>, salvo las excepciones previstas en el art&iacute;culo 37 de la Ley Federal de Protecci&oacute;n de Datos Personales en Posesi&oacute;n de los Particulares que a la letra dice:
						</span>
					</p>
					<p style='margin-bottom: 12.0pt; text-align: justify; text-justify: inter-ideograph; line-height: normal; text-autospace: none'>
						<i><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&quot;</span></i><i><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Art&iacute;culo 37.- </span></i><i><span
							style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Las transferencias nacionales o internacionales de datos podr&aacute;n llevarse a cabo sin el consentimiento del titular cuando se d&eacute; alguno de los siguientes supuestos:</span></i><i><span
							style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'> I. </span></i><i><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Cuando la transferencia est&eacute; prevista en una Ley o Tratado en los que M&eacute;xico sea parte;
								II. Cuando la transferencia sea necesaria para la prevenci&oacute;n o el diagn&oacute;stico m&eacute;dico, la prestaci&oacute;n de asistencia sanitaria, tratamiento m&eacute;dico o la gesti&oacute;n de servicios sanitarios; </span></i><i><span
							style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>III. </span></i><i><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Cuando la transferencia sea efectuada a sociedades controladoras, subsidiarias o afiliadas bajo el
								control com&uacute;n del responsable, o a una sociedad matriz o a cualquier sociedad del mismo grupo del responsable que opere bajo los mismos procesos y pol&iacute;ticas internas; IV. Cuando la transferencia sea necesaria por virtud de un contrato celebrado o por celebrar en inter&eacute;s del
								titular, por el responsable y un tercero; </span></i><i><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>V. </span></i><i><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Cuando la transferencia sea necesaria
								o legalmente exigida para la salvaguarda de un inter&eacute;s p&uacute;blico, o para la procuraci&oacute;n o administraci&oacute;n de justicia; </span></i><i><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>VI. </span></i><i><span
							style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Cuando la transferencia sea precisa para el reconocimiento, ejercicio o defensa de un derecho en un proceso judicial, y </span></i><i><span
							style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>VII. </span></i><i><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Cuando la transferencia sea precisa para el mantenimiento o cumplimiento de una relaci&oacute;n
								jur&iacute;dica entre el responsable y el titular.&quot;</span></i><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'> </span>
					</p>
					<p style='margin-bottom: 12.0pt; text-align: justify; text-justify: inter-ideograph; line-height: normal; text-autospace: none'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>As&iacute; como a realizar esta transferencia con apego a los t&eacute;rminos que fija dicha Ley.</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal; text-autospace: none'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span></b>
					</p>
					<p style='line-height: normal'>
						<b><i><span style='font-size: 14.0pt; font-family: "Candara", "sans-serif"; color: #400080'>Derecho a limitar el uso y divulgaci&oacute;n de sus Datos Personales.</span></i></b>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-family: "Candara", "sans-serif"; color: black'>Si el <b>TITULAR</b> desea limitar el uso o divulgaci&oacute;n de sus Datos Personales podr&aacute; ejercer de manera gratuita y en cualquier momento dichos derechos estipulados en la Ley Federal de Protecci&oacute;n de Datos
							Personales en Posesi&oacute;n de los Particulares vigente, para lo cual podr&aacute; solicitar un formato para la delimitaci&oacute;n de uso y divulgaci&oacute;n, por el siguiente medio:
						</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-family: "Candara", "sans-serif"; color: black'>Para el ejercicio de dichos derechos el <b>TITULAR </b>podr&aacute; llenar el formato correspondiente que ser&aacute; proporcionado por <b>CCMX</b> en la siguiente direcci&oacute;n:
						</span>
					</p>
					<p style='margin-left: 36.0pt; text-align: justify; text-justify: inter-ideograph; text-indent: -18.0pt'>
						<span style='font-family: Symbol'>&deg;<span style='font: 7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style='font-family: "Candara", "sans-serif"'>Avenida Fundidora 501-95&ordf;, Colonia Obrera, Monterrey, Nuevo Le&oacute;n, C.P. 64010,
							M&eacute;xico.</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-family: "Candara", "sans-serif"'>El formato deber&aacute; ir acompa&ntilde;ado de una copia simple de un documento oficial que acredite la identidad del <b>TITULAR</b> e incluir una descripci&oacute;n clara y precisa de los Datos Personales respecto de los cuales
							ejercitar&aacute; cualquiera de los derechos que le confiere la Ley, as&iacute; mismo deber&aacute; proporcionar un correo electr&oacute;nico, n&uacute;mero telef&oacute;nico y direcci&oacute;n para oir y recibir notificaciones a fin de comunicarle la respuesta a su petici&oacute;n y cualquier
							otra informaci&oacute;n que facilite la b&uacute;squeda y localizaci&oacute;n de sus datos para los fines requeridos.
						</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-family: "Candara", "sans-serif"'>As&iacute; mismo con objeto de que el <b>TITULAR</b> pueda limitar el uso y divulgaci&oacute;n de su informaci&oacute;n personal, le ofrecemos los siguientes medios:
						</span>
					</p>
					<p style='margin-left: 36.0pt; text-align: justify; text-justify: inter-ideograph; text-indent: -18.0pt'>
						<span style='font-family: Symbol'>&deg;<span style='font: 7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style='font-family: "Candara", "sans-serif"'>Su registro en el Listado de Exclusi&oacute;n de <b>CAINTRA</b>, a fin de que sus datos
							personales no sean tratados para fines mercadot&eacute;cnicos, publicitarios o de prospecci&oacute;n comercial por nuestra parte.
						</span>
					</p>
					<p style='margin-left: 36.0pt; text-align: justify; text-justify: inter-ideograph; text-indent: -18.0pt'>
						<span style='font-family: Symbol'>&deg;<span style='font: 7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style='font-family: "Candara", "sans-serif"'>Su inscripci&oacute;n en el Registro P&uacute;blico para Evitar Publicidad, que est&aacute; a
							cargo de la Procuradur&iacute;a Federal del Consumidor, con la finalidad de que sus datos personales no sean utilizados para recibir publicidad o promociones de empresas de bienes o servicios. Para mayor informaci&oacute;n sobre este registro, usted puede consultar el portal de Internet de la
							PROFECO, o bien ponerse en contacto directo con &eacute;sta.</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-family: "Candara", "sans-serif"'>Para mayor informaci&oacute;n podr&aacute; dirigirse a los datos de contacto establecidos en el presente documento.</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal; text-autospace: none'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span></b>
					</p>
					<p style='line-height: normal'>
						<b><i><span style='font-size: 14.0pt; font-family: "Candara", "sans-serif"; color: #400080'>Derecho a la revocaci&oacute;n del consentimiento para el uso de sus datos personales.</span></i></b>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Usted puede revocar el consentimiento que, en su caso, nos haya otorgado para el tratamiento de sus datos personales. Sin embargo, es importante que tenga en cuenta que no en todos los casos podremos
							atender su solicitud o concluir el uso de forma inmediata, ya que es posible que por alguna obligaci&oacute;n legal requiramos seguir tratando sus datos personales. Asimismo, usted deber&aacute; considerar que para ciertos fines, la revocaci&oacute;n de su consentimiento implicar&aacute; que no
							le podamos seguir prestando el servicio que nos solicit&oacute;, o la conclusi&oacute;n de su relaci&oacute;n con nosotros.</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-family: "Candara", "sans-serif"; color: black'>Para el ejercicio de dicho derecho el <b>TITULAR </b>podr&aacute; llenar el formato correspondiente que ser&aacute; proporcionado por <b>CCMX</b> en la siguiente direcci&oacute;n:
						</span>
					</p>
					<p style='margin-left: 36.0pt; text-align: justify; text-justify: inter-ideograph; text-indent: -18.0pt'>
						<span style='font-family: Symbol'>&deg;<span style='font: 7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style='font-family: "Candara", "sans-serif"'>Avenida Fundidora 501-95&ordf;, Colonia Obrera, Monterrey, Nuevo Le&oacute;n, C.P. 64010,
							M&eacute;xico.</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-family: "Candara", "sans-serif"'>El formato deber&aacute; ir acompa&ntilde;ado de una copia simple de un documento oficial que acredite la identidad del <b>TITULAR</b> e incluir una descripci&oacute;n clara y precisa de los Datos Personales respecto de los cuales
							ejercitar&aacute; cualquiera de los derechos que le confiere la Ley, as&iacute; mismo deber&aacute; proporcionar un correo electr&oacute;nico, n&uacute;mero telef&oacute;nico y direcci&oacute;n para oir y recibir notificaciones a fin de comunicarle la respuesta a su petici&oacute;n y cualquier
							otra informaci&oacute;n que facilite la b&uacute;squeda y localizaci&oacute;n de sus datos para los fines requeridos.
						</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal; text-autospace: none'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span></b>
					</p>
					<p style='line-height: normal'>
						<b><i><span style='font-size: 14.0pt; font-family: "Candara", "sans-serif"; color: #400080'>Derecho al Acceso, Rectificaci&oacute;n, Cancelaci&oacute;n y Oposici&oacute;n de sus Datos Personales.</span></i></b>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-family: "Candara", "sans-serif"; color: black'>Si el <b>TITULAR</b> desea acceder, rectificar, cancelar, limitar o restringir el uso de sus Datos Personales podr&aacute; ejercer de manera gratuita y en cualquier momento su derecho de acceso, rectificaci&oacute;n,
							cancelaci&oacute;n u oposici&oacute;n de sus Datos Personales (Derechos ARCO estipulados en la Ley Federal de Protecci&oacute;n de Datos Personales en Posesi&oacute;n de los Particulares vigente), as&iacute; mismo podr&aacute; solicitar su delimitaci&oacute;n de uso, correcci&oacute;n o la
							revocaci&oacute;n del consentimiento de su uso, por ese mismo medio.
						</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-family: "Candara", "sans-serif"; color: black'>Para el ejercicio de dichos derechos el <b>TITULAR </b>podr&aacute; llenar el formato correspondiente que ser&aacute; proporcionado por <b>CCMX</b> en la siguiente direcci&oacute;n:
						</span>
					</p>
					<p style='margin-left: 36.0pt; text-align: justify; text-justify: inter-ideograph; text-indent: -18.0pt'>
						<span style='font-family: Symbol'>&deg;<span style='font: 7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style='font-family: "Candara", "sans-serif"'>Avenida Fundidora 501-95&ordf;, Colonia Obrera, Monterrey, Nuevo Le&oacute;n, C.P. 64010,
							M&eacute;xico.</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-family: "Candara", "sans-serif"'>El formato deber&aacute; ir acompa&ntilde;ado de una copia simple de un documento oficial que acredite la identidad del <b>TITULAR</b> e incluir una descripci&oacute;n clara y precisa de los Datos Personales respecto de los cuales
							ejercitar&aacute; cualquiera de los derechos que le confiere la Ley, as&iacute; mismo deber&aacute; proporcionar un correo electr&oacute;nico, n&uacute;mero telef&oacute;nico y direcci&oacute;n para oir y recibir notificaciones a fin de comunicarle la respuesta a su petici&oacute;n y cualquier
							otra informaci&oacute;n que facilite la b&uacute;squeda y localizaci&oacute;n de sus datos para los fines requeridos.
						</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<b><span style='font-family: "Candara", "sans-serif"; color: black'>CCMX</span></b><span style='font-family: "Candara", "sans-serif"; color: black'> tendr&aacute; un plazo de m&aacute;ximo veinte d&iacute;as h&aacute;biles contados a partir de la fecha en que se reciba la solicitud del <b>TITULAR</b>
							para informar al <b>TITULAR</b> sobre la procedencia de su solicitud, en caso afirmativo se atender&aacute; lo solicitado por el <b>TITULAR en </b>un plazo no mayor a quince d&iacute;as h&aacute;biles siguientes a la fecha de la que se acepte la procedencia de la solicitud llevada a cabo por el
							<b>TITULAR</b>.
						</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<b><span style='font-family: "Candara", "sans-serif"; color: black'>CCMX</span></b><span style='font-family: "Candara", "sans-serif"; color: black'> podr&aacute; negar el acceso total o parcial de los Datos Personales o a la realizaci&oacute;n de cualquier tipo de rectificaci&oacute;n,
							cancelaci&oacute;n u oposici&oacute;n al tratamiento de los mismos, cuando se encuentre de los supuestos enmarcados en la Ley.</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal; text-autospace: none'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span></b>
					</p>
					<p style='line-height: normal'>
						<b><i><span style='font-size: 14.0pt; font-family: "Candara", "sans-serif"; color: #400080'>El uso de las tecnologias de rastreo en nuestro portal de Internet.</span></i></b>
					</p>
					<p style='margin-top: 0cm; margin-right: 0cm; margin-bottom: 7.5pt; margin-left: 0cm; text-align: justify; text-justify: inter-ideograph'>
						<em><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black; font-style: normal'>Le informamos que en nuestra p&aacute;gina de Internet utilizamos cookies, web beacons y otras tecnolog&iacute;as a trav&eacute;s de las cuales es posible
								monitorear su comportamiento como usuario de Internet, brindarle un mejor servicio y experiencia de usuario al navegar en nuestra p&aacute;gina, as&iacute; como ofrecerle nuevos productos y servicios basados en sus preferencias.</span></em>
					</p>
					<p style='margin-top: 0cm; text-align: justify; text-justify: inter-ideograph'>
						<em><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black; font-style: normal'>Los datos personales que obtenemos de estas tecnolog&iacute;as de rastreo son los siguientes: horario de navegaci&oacute;n, tiempo de navegaci&oacute;n en nuestra
								p&aacute;gina de Internet, secciones consultadas, y p&aacute;ginas de Internet accedidas previo a la nuestra.</span></em>
					</p>
					<p style='margin-top: 0cm; text-align: justify; text-justify: inter-ideograph'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>As&iacute; mismo, le informamos que sus datos personales que se obtienen a trav&eacute;s de estas tecnolog&iacute;as no ser&aacute;n compartidos con personas, empresas, organizaciones o
							autoridades distintas a nosotros.</span>
					</p>
					<p style='margin-top: 0cm; text-align: justify; text-justify: inter-ideograph'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Estas tecnolog&iacute;as podr&aacute;n deshabilitarse siguiendo las instrucciones establecidas en el procedimiento de eliminaci&oacute;n de cookies y web beacons publicadas en la p&aacute;gina
						</span><span><a href="http://www.caintra.org.mx"><b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"'>http://www.caintra.org.mx</span></b></a></span><b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>.</span></b>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal; text-autospace: none'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span></b>
					</p>
					<p style='line-height: normal'>
						<b><i><span style='font-size: 14.0pt; font-family: "Candara", "sans-serif"; color: #400080'>Modificaciones al Aviso de Privacidad.</span></i></b>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>CCMX</span></b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'> se reserva el derecho de efectuar en cualquier momento modificaciones o actualizaciones al presente
							aviso de privacidad, en cumplimiento de novedades legislativas o jurisprudenciales, pol&iacute;ticas internas, nuevos requerimientos para la prestaci&oacute;n u ofrecimiento de nuestros servicios o productos y pr&aacute;cticas del mercado; de</span><span
							style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'> ser as&iacute;, </span><b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>CCMX</span></b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>
						</span><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>lo dar&aacute; a conocer a trav&eacute;s de su p&aacute;gina de Internet </span><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"'>en </span><a href="http://www.caintra.org.mx"><b><span
								style='font-size: 12.0pt; font-family: "Candara", "sans-serif"'>http://www.caintra.org.mx</span></b></a><b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'> </span></b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>a efecto
							de enterar al <b>TITULAR</b> de los Datos Personales.
						</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Fecha de la &uacute;ltima actualizaci&oacute;n al presente aviso de privacidad: 17/Julio/2013.</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal'>
						<b><span style='font-size: 10.0pt; font-family: "Candara", "sans-serif"'>HE LEIDO EL PRESENTE AVISO DE PRIVACIDAD INTEGRAL ESCRITO QUE FUE PUESTO A MI </span></b>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal'>
						<b><span style='font-size: 10.0pt; font-family: "Candara", "sans-serif"'>DISPOSICI&Oacute;N CON ANTERIORIDAD AL TRATAMIENTO DE MIS DATOS PERSONALES.</span></b>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal'>
						<input type="checkbox" id="idCheckAceptaAP"/>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span>
					</p>
					<table class="submit_tabla" style="margin-left: 0px; width: 100%;">
						<tr>
							<td style="width: 250px;"></td>
							<td style="width: 9%;"></td>
							<td><input class="close" value="Cancelar" type="button" style="color: rgb(125, 125, 125);" onclick="document.getElementById('sec1').style.display='block'; $('.overlay-container').fadeOut().end().find('.window-container').removeClass('window-container-visible');"/></td>
							<td style="width: 9%;"></td>
							<td><input class="botonenviar" style="color: rgb(125, 125, 125);" value="Continuar" type="button" onclick="javascript: aceptar();" /></td>
							<td style="width: 9%;"></td>
							<td style="width: 250px;"></td>
						</tr>
					</table>
				</div>
					<!-- inicia PF Aviso de Privacidad -->
				<div id="idDivAvisoPrivacidad2" style="display: none; height: 460px; overflow-y: scroll; padding-right: 20px;">
					<p style='line-height: normal; text-align: center'>
						<span style='font-size: 18.0pt; font-family: "Calibri", "sans-serif"; color: #636363'><b>CONSENTIMIENTO AVISO PERSONAS F&Iacute;SICAS</b></span>
					</p>
					<p style='line-height: normal'>
						<b><i><span style='font-size: 14.0pt; font-family: "Candara", "sans-serif"; color: #400080'>Conteste las siguientes preguntas por favor:</span></i></b>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Consiento que mis Datos Personales financieros y/o patrimoniales 
							se encuentren en posesi&oacute;n de CCMX conforme a los t&eacute;rminos y condiciones del Aviso de Privacidad Integral as&iacute; como a la 
							legislaci&oacute;n aplicable a la materia, el cual previamente me fue puesto a la vista.</span></b>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal; font-weight: bold;'>
						<input type="checkbox" id="idCheckAceptaP1" onchange="javascript: document.getElementById('idCheckNoAceptaP1').checked = false;" />S&Iacute; ACEPTO
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" id="idCheckNoAceptaP1" onchange="javascript: document.getElementById('idCheckAceptaP1').checked = false;" />NO ACEPTO
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Consiento que mis Datos Personales y/o Datos Personales financieros y/o 
							patrimoniales sean transferidos conforme a los t&eacute;rminos y condiciones del Aviso de Privacidad Integral as&iacute; como a la legislaci&oacute;n 
							aplicable a la materia, el cual previamente me fue puesto a la vista.</span></b>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal; font-weight: bold;'>
						<input type="checkbox" id="idCheckAceptaP2" onchange="javascript: document.getElementById('idCheckNoAceptaP2').checked = false;" />S&Iacute; ACEPTO
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" id="idCheckNoAceptaP2" onchange="javascript: document.getElementById('idCheckAceptaP2').checked = false;" />NO ACEPTO
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>En caso de que Usted no desee que sus datos personales sean tratados para 
							fines mercadot&eacute;cnicos, publicitarios o de prospecci&oacute;n comercial, desde este momento nos puede comunicar esta negativa o si acepta que los 
							mismos sean utilizados para dichos fines. La negativa para el uso de sus datos personales para estas finalidades, no podr&aacute; ser un motivo para que le 
							neguemos los servicios y productos que solicita o contrata con nosotros.</span></b>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal; font-weight: bold;'>
						<input type="checkbox" id="idCheckAceptaP3" onchange="javascript: document.getElementById('idCheckNoAceptaP3').checked = false;" />S&Iacute; ACEPTO
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" id="idCheckNoAceptaP3" onchange="javascript: document.getElementById('idCheckAceptaP3').checked = false;" />NO ACEPTO
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>* Introduzca sus datos por favor:</span></b>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Nombre(s):</span>
						<s:textfield size="20" id="idNombreAcepta" maxlength="60"></s:textfield>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Apellido paterno:</span>
						<s:textfield size="20" id="idApPaternoAcepta" maxlength="60"></s:textfield>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>Apellido materno:</span>
						<s:textfield size="20" id="idApMaternoAcepta" maxlength="60"></s:textfield>
					</p>
					<table class="submit_tabla" style="margin-left: 0px; width: 100%;">
						<tr>
							<td style="width: 250px;"></td>
							<td style="width: 9%;"></td>
							<td><input class="botonenviar" value="Atras" type="button" style="color: rgb(125, 125, 125);" onclick="document.getElementById('idDivAvisoPrivacidad').style.display='block';document.getElementById('idDivAvisoPrivacidad2').style.display='none';"/></td>
							<td style="width: 9%;"></td>
							<td><input class="botonenviar" style="color: rgb(125, 125, 125);" value="Continuar" type="button" onclick="javascript: cuestionario();" /></td>
							<td style="width: 9%;"></td>
							<td style="width: 250px;"></td>
						</tr>
					</table>
				</div>
				<!-- termina PF Aviso de Privacidad -->
				
				<!-- inicia PM Liberacion de Responsabilidad -->
				<div id="idDivLiberacionResponsabilidad" style="display: none; height: 460px; overflow-y: scroll; padding-right: 20px;">
					<p style='line-height: normal; text-align: center'>
						<span style='font-size: 18.0pt; font-family: "Calibri", "sans-serif"; color: #636363'><b>LIBERACI&Oacute;N DE RESPONSABILIDAD</b></span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph; line-height: normal'>
					<b><span style='font-family: "Candara", "sans-serif"; color: black'>CENTRO DE COMPETITIVIDAD DE M&Eacute;XICO, A.C. </span></b><span style='font-family: "Candara", "sans-serif"; color: black'>(en lo sucesivo <b>CCMX</b>) con domicilio ubicado en
					</span><span style='font-family: "Candara", "sans-serif"'>Avenida Fundidora 501-95&ordf;, Colonia Obrera, Monterrey, Nuevo Le&oacute;n, C.P. 64010, M&eacute;xico</span><span style='font-family: "Candara", "sans-serif"; color: black'>, y con direcci&oacute;n de correo electr&oacute;nico: </span>
						<a href="mailto:centrodedatos@ccmx.com.mx"><i><span style='font-family: "Candara", "sans-serif"; color: blue; text-decoration: underline'>centrodedatos@ccmx.com.mx</span></i></a>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<b><span style='font-family: "Candara", "sans-serif"; color: black'>CCMX</span></b><span style='font-family: "Candara", "sans-serif"; color: black'> se obliga y compromete a proteger la informaci&oacute;n de las personas morales que sea recabada mediante el Sistema Integral de
							Administraci&oacute;n <b>CCMX</b> o por cualquier otro medio electr&oacute;nico, formato en papel, formato electr&oacute;nico de la p&aacute;gina de internet de <b>CCMX, </b>as&iacute; como el Sistema de Vinculaci&oacute;n de <b>CCMX</b>, tel&eacute;fono u otro que tenga como finalidad la
							obtenci&oacute;n de informaci&oacute;n, tal como los datos de contacto de la empresa, ventas anuales, ventas o ingresos acumulados (antes), % Egresos/Ventas (antes), Ventas o ingresos acumulados (despu&eacute;s), % Egresos/Ventas (despu&eacute;s), entre otros.
						</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-family: "Candara", "sans-serif"'>Las finalidades principales para recabar esta informaci&oacute;n, ser&aacute;n para </span><span style='font-family: "Candara", "sans-serif"; color: black'>poner en contacto a su empresa con grandes empresas, con </span><span
							style='font-family: "Candara", "sans-serif"; color: black'>PYMES, y con otras empresas relacionadas o consultores externos que requieran de sus datos, </span><span style='font-family: "Candara", "sans-serif"; color: black'>af&iacute;n de apoyarla en aumento en ventas, as&iacute; como la
							mejora de pr&aacute;cticas comerciales, administrativas y de negocios y recibir asesor&iacute;as personalizadas buscando el bien de su negocio.</span><span style='font-family: "Candara", "sans-serif"; color: black'> As&iacute; como </span><span style='font-family: "Candara", "sans-serif"'>para
							fines mercadot&eacute;cnicos, publicitarios o de prospecci&oacute;n comercial.</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<b><span style='font-family: "Candara", "sans-serif"; color: black'>CCMX</span></b><span style='font-family: "Candara", "sans-serif"; color: black'> se compromete a que toda </span><span style='font-family: "Candara", "sans-serif"'>esta informaci&oacute;n</span><span
							style='font-family: "Candara", "sans-serif"; color: black'> ser&aacute; tratada bajo las m&aacute;s estrictas medidas de seguridad que garanticen su confidencialidad y buen uso para las finalidades descritas. As&iacute; como que </span><span
							style='font-family: "Candara", "sans-serif"; color: black'>no estar&aacute; al alcance ni se podr&aacute; acceder a ella por personas ajenas a los fines mencionados, que estar&aacute; protegida con las medidas de seguridad f&iacute;sicas, administrativas y t&eacute;cnicas suficientes para
							su protecci&oacute;n y confidencialidad.</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'>De igual manera, <b>CCMX</b> podr&aacute; transferir
						</span><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"'>esta informaci&oacute;n</span><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'> </span><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'>a PYMES o a
							otras empresas relacionadas o consultores externos que requieran de sus datos para ponerlos en </span><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'>vinculaci&oacute;n con su empresa; af&iacute;n de apoyarlo en aumento en ventas, as&iacute; como la mejora de
							pr&aacute;cticas comerciales, administrativas y de negocios y recibir asesor&iacute;as personalizadas buscando el bien de su negocio.<b> </b>
						</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-family: "Candara", "sans-serif"; color: black'>Si su empresa desea limitar el uso o divulgaci&oacute;n de </span><span style='font-family: "Candara", "sans-serif"'>esta informaci&oacute;n</span><span style='font-family: "Candara", "sans-serif"; color: black'>, </span><span
							style='font-family: "Candara", "sans-serif"; color: black'>podr&aacute; ejercer de manera gratuita y en cualquier momento dichos derechos enviando un correo electr&oacute;nico manifestando esto a la siguiente direcci&oacute;n: </span><i><span
							style='font-family: "Candara", "sans-serif"; color: blue'><a href="mailto:centrodedatos@ccmx.com.mx"><i><span style='font-family: "Candara", "sans-serif"; color: blue; text-decoration: underline'>centrodedatos@ccmx.com.mx</span></i></a></span></i><span style='font-family: "Candara", "sans-serif"; color: black'>, o acudir
							directamente a la siguiente direcci&oacute;n: </span><span style='font-family: "Candara", "sans-serif"'>Avenida Fundidora 501-95&ordf;, Colonia Obrera, Monterrey, Nuevo Le&oacute;n, C.P. 64010, M&eacute;xico.</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"'>Deber&aacute; presentar un escrito libre acompa&ntilde;ado de una copia simple de un documento oficial que acredite la representaci&oacute;n del compareciente e incluir una descripci&oacute;n clara y precisa de la
							informaci&oacute;n cuyo </span><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'>uso o divulgaci&oacute;n desee limitar</span><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"'>, as&iacute; mismo deber&aacute; proporcionar un correo
							electr&oacute;nico, n&uacute;mero telef&oacute;nico y direcci&oacute;n para o&iacute;r y recibir notificaciones a fin de comunicarle la respuesta a su petici&oacute;n y cualquier otra informaci&oacute;n que facilite la b&uacute;squeda y localizaci&oacute;n de su informaci&oacute;n para los fines
							requeridos.</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"'>As&iacute; mismo con objeto de que su empresa pueda limitar el uso y divulgaci&oacute;n de su informaci&oacute;n, le ofrecemos los siguientes medios:</span>
					</p>
					<p style='margin-left: 36.0pt; text-align: justify; text-justify: inter-ideograph; text-indent: -18.0pt'>
						<span style='font-family: Symbol'>&deg;<span style='font: 7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"'>Su inscripci&oacute;n en el Registro P&uacute;blico para Evitar
							Publicidad, que est&aacute; a cargo de la Procuradur&iacute;a Federal del Consumidor, con la finalidad de que sus datos personales no sean utilizados para recibir publicidad o promociones de empresas de bienes o servicios. Para mayor informaci&oacute;n sobre este registro, usted puede consultar
							el portal de Internet de la PROFECO, o bien ponerse en contacto directo con &eacute;sta.</span>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph'>
						<span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"'>Para mayor informaci&oacute;n podr&aacute; dirigirse a los datos de contacto establecidos en el presente documento.</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-family: "Candara", "sans-serif"; color: black'>Usted puede revocar el consentimiento que, en su caso, nos haya otorgado para el tratamiento de su informaci&oacute;n. Sin embargo, es importante que tenga en cuenta que no en todos los casos podremos atender su solicitud o
							concluir el uso de forma inmediata, ya que es posible que por alguna obligaci&oacute;n legal requiramos seguirla tratando. Asimismo, usted deber&aacute; considerar que para ciertos fines, la revocaci&oacute;n de su consentimiento implicar&aacute; que no le podamos seguir prestando el servicio
							que nos solicit&oacute;, o la conclusi&oacute;n de su relaci&oacute;n con nosotros.</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<em><span style='font-family: "Candara", "sans-serif"; color: black; font-style: normal'>Le informamos que en nuestra p&aacute;gina de Internet utilizamos cookies, web beacons y otras tecnolog&iacute;as a trav&eacute;s de las cuales es posible monitorear su comportamiento como usuario
								de Internet, brindarle un mejor servicio y experiencia de usuario al navegar en nuestra p&aacute;gina, as&iacute; como ofrecerle nuevos productos y servicios basados en sus preferencias.</span></em>
					</p>
					<p style='margin: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph'>
						<em><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black; font-style: normal'>&nbsp;</span></em>
					</p>
					<p style='margin: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph'>
						<em><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black; font-style: normal'>Los datos personales que obtenemos de estas tecnolog&iacute;as de rastreo son los siguientes: horario de navegaci&oacute;n, tiempo de navegaci&oacute;n en nuestra
								p&aacute;gina de Internet, secciones consultadas, y p&aacute;ginas de Internet accedidas previo a la nuestra.</span></em>
					</p>
					<p style='margin: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph'>
						<em><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black; font-style: normal'>&nbsp;</span></em>
					</p>
					<p style='margin: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph'>
						<span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'>As&iacute; mismo, le informamos que sus datos personales que se obtienen a trav&eacute;s de estas tecnolog&iacute;as no ser&aacute;n compartidos con personas, empresas, organizaciones o
							autoridades distintas a nosotros.</span>
					</p>
					<p style='margin: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph'>
						<span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span>
					</p>
					<p style='margin: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph'>
						<span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'>Estas tecnolog&iacute;as podr&aacute;n deshabilitarse siguiendo las instrucciones establecidas en el procedimiento de eliminaci&oacute;n de cookies y web beacons publicadas en la p&aacute;gina
						</span><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"'>en </span><span ><a href="http://www.caintra.org.mx"><b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"'>http://www.caintra.org.mx</span></b></a></span>
					</p>
					<p style='margin: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph'>
						<b><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span></b>
					</p>
					<p style='margin: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph'>
						<b><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'>CCMX</span></b><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'> se reserva el derecho de efectuar en cualquier momento modificaciones o
							actualizaciones a la presente Liberaci&oacute;n de Responsabilidad, en cumplimiento de novedades legislativas o jurisprudenciales, pol&iacute;ticas internas, nuevos requerimientos para la prestaci&oacute;n u ofrecimiento de nuestros servicios o productos y pr&aacute;cticas del mercado; de</span><span
							style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'> ser as&iacute;, </span><b><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'>CCMX</span></b><span 
							style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'> </span><span style='font-size: 11.0pt; font-family: "Candara", "sans-serif"; color: black'>lo dar&aacute; a conocer a trav&eacute;s de su p&aacute;gina de Internet </span><span 
							style='font-size: 12.0pt; font-family: "Candara", "sans-serif"'>en </span><span><a href="http://www.caintra.org.mx"><b><span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"'>http://www.caintra.org.mx</span></b></a></span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-family: "Candara", "sans-serif"; color: black'>Fecha de la &uacute;ltima actualizaci&oacute;n: 17/Julio/2013.</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<b><span style='font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span></b>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal'>
						<b><span style='font-size: 10.0pt; font-family: "Candara", "sans-serif"; color: black'>ESTOY DE ACUERDO CON LOS T&Eacute;RMINOS Y CONDICIONES CONTENIDOS EN ESTE DOCUMENTO, AS&Iacute; COMO CON LAS FINALIDADES DESCRITAS PARA EL USO DE INFORMACI&Oacute;N.</span></b>
					</p>
					<p style='margin-bottom: 0cm; margin-bottom: .0001pt; text-align: center; line-height: normal'>
						<input type="checkbox" id="idCheckAceptaLR"/>
					</p>
					<p style='text-align: justify; text-justify: inter-ideograph; line-height: normal'>
						<span style='font-size: 12.0pt; font-family: "Candara", "sans-serif"; color: black'>&nbsp;</span>
					</p>
					<table class="submit_tabla" style="margin-left: 0px; width: 60%;">
						<tr>
							<td style="width: 250px;"></td>
							<td style="width: 9%;"></td>
							<td><input class="close" value="Cancelar" type="button" style="color: rgb(125, 125, 125);" onclick="document.getElementById('sec1').style.display='block'; $('.overlay-container').fadeOut().end().find('.window-container').removeClass('window-container-visible');"/></td>
							<td style="width: 9%;"></td>
							<td><input class="botonenviar" style="color: rgb(125, 125, 125);" value="Continuar" type="button" onclick="javascript: aceptar();" /></td>
							<td style="width: 9%;"></td>
							<td style="width: 250px;"></td>
						</tr>
					</table>
				</div>
				<!-- termina PM Liberacion de Responsabilidad -->
			</fieldset>
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
		<input type="button" id="idBtnTerminosCondiciones" value="" class="button" style="position: absolute; margin-top: -500px; display: none;" data-type="zoomin" />
	</s:if>
</body>
</html>