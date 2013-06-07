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
								<option value="Seleccione el tipo de persona">Seleccione el tipo de persona</option>
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
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay9" style="display:none; margin-top:5px;"
											value="Seleccione el Estado ." />
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* C.P.:" />
									</td>
									<td>
										<s:textfield size="20" id="codigoPostal" name="domicilios.codigoPostal" maxlength="5" 
										onfocus="javascript:ayudasHelp(10);" onblur="javascript:ayudasHelpBlo(10);"
										onkeydown="return validaNumero(event)"></s:textfield>
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
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay12" style="display:none; margin-top:5px;"
										value="*Elija el sector en el que se encuentra su empresa." />
									</td>
								</tr>
								<tr>
									<td>
										<s:checkbox id="sector1" name="pyMEs.bPrimerNivel" value="%{pyMEs.bPrimerNivel}" onclick="javascript: checkSectorUno();"
											
											/>
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
										value="Incluya sus productos principales en 3 palabras, (máximo 20 productos)." />
								</td>
							</tr>
						</table>
					</div>
					<div ${pyMEs.productos[0].producto!=null?' style="display: block;"':' style="display: none;"'} id="tablaProd" class="flotantes">
						<table width="100%" cellspacing="1" cellpadding="1">
							<tr>
								<td class="encabezadoTablaResumen" colspan="3" align="center"><b>Descripción del o de los productos registrados</b></td>
							</tr>
							<tr>
								<td class="cuerpo1TablaResumen" align="center" style="width: 10%;"><b>No.</b></td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 75%;"><b>Producto agregado</b></td>
								<td class="cuerpo1TablaResumen" align="center" style="width: 15%;"><b>Eliminar Producto</b></td>
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
							<div onmouseover="javascript:ayudasHelp('estados')" onmouseout="javascript:ayudasHelpBlo('estados')">
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
						<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplayestados" style="display:none; margin-top:5px;"
								value="Puede elegir uno o más estados." />
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
				<s:label cssClass="titulosPyMEs" value="Información del contacto." />
				<table>
					<tr>
						<td>
							<table>
								<!-- Inicia Información del contacto -->
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="Seleccione el tipo de contacto." />
										<s:hidden name="pyMEs.contactos[0].idContacto" id="idContacto1" value="%{pyMEs.contactos[0].idContacto}" />
									</td>
									<td>
										<select id="tipoContacto" onchange="javascript:valorTipoCont(this.value);"
											onfocus="javascript:ayudasHelp(15);" onblur="javascript:ayudasHelpBlo(15);">
											<option ${pyMEs.contactos[0].tipo == '' ? ' selected="selected" ' : ''} value="">--Seleccione un tipo--</option>
											<option ${pyMEs.contactos[0].tipo == 'Ventas' ? ' selected="selected" ' : ''} value="Ventas">Ventas</option>
											<option ${pyMEs.contactos[0].tipo == 'Director General' ? ' selected="selected" ' : ''} value="Director General">Director General</option>
											<option ${pyMEs.contactos[0].tipo == 'Propietario' ? ' selected="selected" ' : ''} value="Propietario">Propietario</option>
											<option ${pyMEs.contactos[0].tipo!=null && pyMEs.contactos[0].tipo != 'Ventas' && pyMEs.contactos[0].tipo != 'Director General' && pyMEs.contactos[0].tipo != 'Propietario' ? ' selected="selected" ' : ''} value="Otro">Otro</option>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div id="otroTipo" style="display: none;">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay15" style="display:none; margin-top:5px;"
											value="Indique otro tipo de contacto " />
										
											<s:label cssClass="etiquetaAyuda" value="Indique otro tipo de contacto " />
											<s:textfield size="23" id="tipoOtro" name="pyMEs.contactos[0].tipo" maxlength="30"></s:textfield>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* Nombre(s) :" />
									</td>
									<td>
										<s:textfield size="30" id="nombreContacto" name="pyMEs.contactos[0].nombre" maxlength="60" 
											onfocus="javascript:ayudasHelp(16);" onblur="javascript:ayudasHelpBlo(16);"
											onkeydown="return validaLetra(event)"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay16" style="display:none; margin-top:5px;"
										value="Escriba el nombre o nombres del contacto sin considerar acentos." />
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* Apellido Paterno :" />
									</td>
									<td>
										<s:textfield size="30" id="appPat" name="pyMEs.contactos[0].apellidoPaterno" maxlength="60" 
											onfocus="javascript:ayudasHelp(17);" onblur="javascript:ayudasHelpBlo(17);"
											onkeydown="return validaLetra(event)"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay17" style="display:none; margin-top:5px;"
											value="Escriba el apellido paterno del contacto sin considerar acentos." />
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* Apellido Materno :" />
									</td>
									<td>
										<s:textfield size="30" id="appMat" name="pyMEs.contactos[0].apellidoMaterno" maxlength="60" 
											onfocus="javascript:ayudasHelp(18);" onblur="javascript:ayudasHelpBlo(18);"
											onkeydown="return validaLetra(event)"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay18" style="display:none; margin-top:5px;"
											value="Escriba el apellido materno del contacto sin considerar acentos." />
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* Correo electrónico :" />
									</td>
									<td>
										<s:textfield size="30" id="correoElectronicoContacto" name="pyMEs.contactos[0].correoElectronico" 
											onfocus="javascript:ayudasHelp(19);" onblur="javascript:ayudasHelpBlo(19);" maxlength="100"></s:textfield>
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
											onfocus="javascript:ayudasHelp(20);" onblur="javascript:ayudasHelpBlo(20);"></s:textfield>
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
													<s:textfield size="2" id="ladaTel" name="" maxlength="2" onkeydown="javascript: cambiaCampo(event);" 
														onfocus="javascript:ayudasHelp(21);" onblur="javascript:ayudasHelpBlo(21);"
														onkeydown="return validaNumero(event)"></s:textfield>
												</td>
												<td style="width: 10%;">
													<s:label cssClass="etiquetaCaptura" value="Núm:" />
												</td>
												<td style="width: 28%;">
													<s:textfield size="16" id="numTel" name="" maxlength="8" onkeydown="javascript: cambiaCampo(event);" 
														onfocus="javascript:ayudasHelp(21);" onblur="javascript:ayudasHelpBlo(21);"
														onkeydown="return validaNumero(event)"></s:textfield>
												</td>
												<td style="width: 5%;">
													<s:label cssClass="etiquetaCaptura" value="Ext:" />
												</td>
												<td style="width: 15%;">
													<s:textfield size="4" id="extTel" name="" maxlength="4" 
														onfocus="javascript:ayudasHelp(21);" onblur="javascript:ayudasHelpBlo(21);"
														onkeydown="return validaNumero(event)"></s:textfield>
												</td>
											</tr>
										</table>
										<s:hidden id="telCompHid"  name="pyMEs.contactos[0].telefono" value="%{pyMEs.contactos[0].telefono}" />
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
						<td>
							<div id="contacto2" ${pyMEs.contactos[1].idContacto==null? ' style="display: none;" ':' style="display: block;"'}>
								<table>
									<!-- Inicia Información del SEGUNDO contacto -->
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Seleccione el tipo de contacto." />
											<s:hidden name="pyMEs.contactos[1].idContacto" id="idContacto2" value="%{pyMEs.contactos[1].idContacto}" />
										</td>
										<td>
											<select id="tipoContacto2" onchange="javascript:valorTipoCont2(this.value);"
												onfocus="javascript:ayudasHelp(15);" onblur="javascript:ayudasHelpBlo(22);">
												<option ${pyMEs.contactos[1].tipo == '' ? ' selected="selected" ' : ''} value="">--Seleccione un tipo--</option>
												<option ${pyMEs.contactos[1].tipo == 'Ventas' ? ' selected="selected" ' : ''} value="Ventas">Ventas</option>
												<option ${pyMEs.contactos[1].tipo == 'Director General' ? ' selected="selected" ' : ''} value="Director General">Director General</option>
												<option ${pyMEs.contactos[1].tipo == 'Propietario' ? ' selected="selected" ' : ''} value="Propietario">Propietario</option>
												<option ${pyMEs.contactos[1].tipo != '' && pyMEs.contactos[1].tipo != 'Ventas' && pyMEs.contactos[1].tipo != 'Director General' && pyMEs.contactos[1].tipo != 'Propietario' ? ' selected="selected" ' : ''} value="Otro">Otro</option>
											</select>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div id="otroTipo2" style="display: none;">
												<s:label cssClass="etiquetaAyuda" id="ayudasDisplay22" style="display:none; margin-top:5px;"
													value="Indique otro tipo de contacto " />
												<s:textfield size="23" id="tipoOtro2" name="pyMEs.contactos[1].tipo" maxlength="30"></s:textfield>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Nombre(s) :" />
										</td>
										<td>
											<s:textfield size="30" id="nombreContacto2" name="pyMEs.contactos[1].nombre" maxlength="60" 
												onfocus="javascript:ayudasHelp(23);" onblur="javascript:ayudasHelpBlo(23);"
												onkeydown="return validaLetra(event)"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay23" style="display:none; margin-top:5px;"
												value="Escriba el nombre o nombres del contacto sin considerar acentos." />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Apellido Paterno :" />
										</td>
										<td>
											<s:textfield size="30" id="appPat2" name="pyMEs.contactos[1].apellidoPaterno" maxlength="60" 
												onfocus="javascript:ayudasHelp(24);" onblur="javascript:ayudasHelpBlo(24);"
												onkeydown="return validaLetra(event)"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay24" style="display:none; margin-top:5px;"
												value="Escriba el apellido paterno del contacto sin considerar acentos." />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Apellido Materno :" />
										</td>
										<td>
											<s:textfield size="30" id="appMat2" name="pyMEs.contactos[1].apellidoMaterno" maxlength="60" 
												onfocus="javascript:ayudasHelp(25);" onblur="javascript:ayudasHelpBlo(25);"
												onkeydown="return validaLetra(event)"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay25" style="display:none; margin-top:5px;"
												value="Escriba el apellido materno del contacto sin considerar acentos." />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Correo electrónico :" />
										</td>
										<td>
											<s:textfield size="30" id="correoElectronicoContacto2" name="pyMEs.contactos[1].correoElectronico" maxlength="100"
												onfocus="javascript:ayudasHelp(26);" onblur="javascript:ayudasHelpBlo(26);"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay26" style="display:none; margin-top:5px;"
												value="Incluya su correo electrónico." />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Confime Correo electrónico :" />
										</td>
										<td>
											<s:textfield size="30" id="comparaCorreoContacto2" maxlength="100"
												onfocus="javascript:ayudasHelp(27);" onblur="javascript:ayudasHelpBlo(27);"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay27" style="display:none; margin-top:5px;"
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
														<s:label id="intTel2" cssClass="resultado" value="52" />&nbsp;&nbsp;
														<s:textfield size="2" id="ladaTel2" name="" maxlength="2" onkeydown="javascript: cambiaCampo2(event);" 
															onfocus="javascript:ayudasHelp(28);" onblur="javascript:ayudasHelpBlo(28);"
															onkeydown="return validaNumero(event)"></s:textfield>
													</td>
													<td style="width: 10%;">
														<s:label cssClass="etiquetaCaptura" value="Núm:" />
													</td>
													<td style="width: 28%;">
														<s:textfield size="16" id="numTel2" name="" maxlength="8" onkeydown="javascript: cambiaCampo2(event);" 
															onfocus="javascript:ayudasHelp(28);" onblur="javascript:ayudasHelpBlo(28);"
															onkeydown="return validaNumero(event)"></s:textfield>
													</td>
													<td style="width: 5%;">
														<s:label cssClass="etiquetaCaptura" value="Ext:" />
													</td>
													<td style="width: 15%;">
														<s:textfield size="4" id="extTel2" name="" maxlength="4" 
															onfocus="javascript:ayudasHelp(28);" onblur="javascript:ayudasHelpBlo(28);"
															onkeydown="return validaNumero(event)"></s:textfield>
													</td>
												</tr>
											</table>
											<s:hidden id="telCompHid2"  name="pyMEs.contactos[1].telefono" value="%{pyMEs.contactos[1].telefono}" />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay28" style="display:none; margin-top:5px;"
												value="Incluya su teléfono con clave lada y extensión." />
											<label class="quitar" onclick="javascript:supContacto();">-eliminar contacto</label>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>

				<label id="linkAddContacto" class="agregar" onclick="javascript:showContacto();">+Agregar otro contacto</label>
				<script>
					window.onload = function() {
						var telContacto1 = document.getElementById('telCompHid').value;
						var ladaTel = document.getElementById('ladaTel');
						var numTel = document.getElementById('numTel');
						var extTel = document.getElementById('extTel');
												
						if(telContacto1 != 'null'){
							ladaTel.value = telContacto1.substring(5, 7);
							numTel.value = telContacto1.substring(9, 17);
							extTel.value = telContacto1.substring(19, 23);
						}
						
												
						var telContacto2 = document.getElementById('telCompHid2').value;
						var ladaTel2 = document.getElementById('ladaTel2');
						var numTel2 = document.getElementById('numTel2');
						var extTel2 = document.getElementById('extTel2');
												
						if(telContacto2 != 'null'){
							ladaTel2.value = telContacto2.substring(5, 7);
							numTel2.value = telContacto2.substring(9, 17);
							extTel2.value = telContacto2.substring(19, 23);
						}
					};
				</script>

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

				<!-- Inicia Registro de Clientes -->
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" 
											value="Datos del Cliente Tractora" />
										<s:hidden name="pyMEs.clientes[0].idCliente" id="idCliente1" value="%{pyMEs.clientes[0].idCliente}" />
									</td>
								</tr>
								<tr>
									<td>
										<s:label cssClass="etiquetaCaptura" value="* Cliente:" />
									</td>
									<td>
										<s:textfield size="25" id="cliente1" name="pyMEs.clientes[0].cliente" maxlength="100"
											onfocus="javascript:ayudasHelp(288);" onblur="javascript:ayudasHelpBlo(288);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay288" style="display:none; margin-top:5px;"
											value="Ingrese el nombre del cliente." />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaCaptura" value="* Productos que compra el cliente :" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:textfield size="35" id="prodCliente1" name="prod1" maxlength="500"
										onfocus="javascript:ayudasHelp(29);" onblur="javascript:ayudasHelpBlo(29);"></s:textfield>
										<label class="agregar" onclick="addProdCliente(1);">+agregar</label>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:textarea id="showProdCliente1" rows="1" cols="40" disabled="true" cssClass="resultado" style="resize: none;" value="%{pyMEs.clientes[0].productosCompra}" 
											/>
										<s:hidden id="showProdCliente1Hid"  name="pyMEs.clientes[0].productosCompra" value="%{pyMEs.clientes[0].productosCompra}" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div id="labDeleteProdC1" ${pyMEs.clientes[0].productosCompra==null? ' style="display: none;" ':' style="display: block;"'}>
											<label class="quitar" onclick="deleteProdCliente(1);">+eliminar producto</label>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay29" style="display:none; margin-top:5px;"
											value="Describa los principales productos que le vende a la tractora." />
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
										<s:textfield size="5" id="aniosProveCliente1" name="pyMEs.clientes[0].aniosProveedor" maxlength="4" 
											onfocus="javascript:ayudasHelp(30);" onblur="javascript:ayudasHelpBlo(30);"
											onkeydown="return validaNumero(event)"></s:textfield>
									</td>
									<td>
										<s:label cssClass="etiquetaCaptura" value="*Meses:" />
										<s:textfield size="5" id="mesesProveCliente1" name="pyMEs.clientes[0].mesesProveedor" maxlength="2" 
											onfocus="javascript:ayudasHelp(30);" onblur="javascript:ayudasHelpBlo(30);"
											onkeydown="return validaNumero(event)"></s:textfield>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay30" style="display:none; margin-top:5px;"
											value="Escriba los años y meses que lleva como proveedor de la tractora." />
									</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
							</table>
						</td>
						<td>
							<div id="prove2" ${pyMEs.clientes[1].idCliente==null? ' style="display: none;" ':' style="display: block;"'}>
								<table>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" value="Ingresar datos del Cliente" />
										<s:hidden name="pyMEs.clientes[1].idCliente" id="idCliente2" value="%{pyMEs.clientes[1].idCliente}" /></td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Cliente:" />
										</td>
										<td>
											<s:textfield size="25" id="cliente2" name="pyMEs.clientes[1].cliente" maxlength="100"
												onfocus="javascript:ayudasHelp(31);" onblur="javascript:ayudasHelpBlo(31);"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2"><s:label cssClass="etiquetaAyuda" id="ayudasDisplay31" style="display:none; margin-top:5px;"
												value="Ingrese el nombre del cliente." /></td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaCaptura" value="* Productos que compra el cliente :" />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:textfield size="25" id="prodCliente2" name="prod2" maxlength="500"
											onfocus="javascript:ayudasHelp(32);" onblur="javascript:ayudasHelpBlo(32);"></s:textfield>
											<label class="agregar" onclick="addProdCliente(2);">+agregar</label>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:textarea id="showProdCliente2" rows="1" cols="40" disabled="true" cssClass="resultado" style="resize: none;" value="%{pyMEs.clientes[1].productosCompra}"/>
											<s:hidden id="showProdCliente2Hid"  name="pyMEs.clientes[1].productosCompra" value="%{pyMEs.clientes[1].productosCompra}" />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div id="labDeleteProdC2" ${pyMEs.clientes[1].productosCompra==null? ' style="display: none;" ':' style="display: block;"'}>
												<label class="quitar" onclick="deleteProdCliente(2);">+eliminar producto</label>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay32" style="display:none; margin-top:5px;"
												value="Describa los principales productos que le vende a la tractora." />
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
											<s:textfield size="5" id="aniosProveCliente2" name="pyMEs.clientes[1].aniosProveedor" maxlength="50" 
												onfocus="javascript:ayudasHelp(33);" onblur="javascript:ayudasHelpBlo(33);"
												onkeydown="return validaNumero(event)"></s:textfield>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="*Meses:" />
											<s:textfield size="5" id="mesesProveCliente2" name="pyMEs.clientes[1].mesesProveedor" maxlength="50" 
												onfocus="javascript:ayudasHelp(33);" onblur="javascript:ayudasHelpBlo(33);"
												onkeydown="return validaNumero(event)"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" id="ayudasDisplay33" style="display:none; margin-top:5px;"
												value="Escriba los años y meses que lleva como proveedor de la tractora." />
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
							<div id="prove3" ${pyMEs.clientes[2].idCliente==null? ' style="display: none;" ':' style="display: block;"'}>
								<br /> <br />
								<table>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" value="Ingresar datos del Cliente" />
											<s:hidden name="pyMEs.clientes[2].idCliente" id="idCliente3" value="%{pyMEs.clientes[2].idCliente}" />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Cliente:" />
										</td>
										<td>
											<s:textfield size="25" id="cliente3" name="pyMEs.clientes[2].cliente" maxlength="100"
												onfocus="javascript:ayudasHelp(34);" onblur="javascript:ayudasHelpBlo(34);"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda"  id="ayudasDisplay34" style="display:none; margin-top:5px;"
												value="Ingrese el nombre del cliente." />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaCaptura" value="* Productos que compra el cliente :" />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:textfield size="25" id="prodCliente3" name="prod3" maxlength="500"
												onfocus="javascript:ayudasHelp(35);" onblur="javascript:ayudasHelpBlo(35);"></s:textfield>
											<label class="agregar" onclick="addProdCliente(3);">+agregar</label>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:textarea id="showProdCliente3" rows="1" cols="40" disabled="true" cssClass="resultado" style="resize: none;" value="%{pyMEs.clientes[2].productosCompra}" />
											<s:hidden id="showProdCliente3Hid"  name="pyMEs.clientes[2].productosCompra" value="%{pyMEs.clientes[2].productosCompra}" />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div id="labDeleteProdC3" ${pyMEs.clientes[2].productosCompra==null? ' style="display: none;" ':' style="display: block;"'}>
												<label class="quitar" onclick="deleteProdCliente(3);">+eliminar producto</label>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda"  id="ayudasDisplay35" style="display:none; margin-top:5px;"
												value="Describa los principales productos que le vende a la tractora." />
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
											<s:textfield size="5" id="aniosProveCliente3" name="pyMEs.clientes[2].aniosProveedor" maxlength="50" 
												onfocus="javascript:ayudasHelp(36);" onblur="javascript:ayudasHelpBlo(36);"
												onkeydown="return validaNumero(event)"></s:textfield>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="*Meses:" />
											<s:textfield size="5" id="mesesProveCliente3" name="pyMEs.clientes[2].mesesProveedor" maxlength="50" 
												onfocus="javascript:ayudasHelp(36);" onblur="javascript:ayudasHelpBlo(36);"
												onkeydown="return validaNumero(event)"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda"  id="ayudasDisplay36" style="display:none; margin-top:5px;"
												value="Escriba los años y meses que lleva como proveedor de la tractora." />
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
							<div id="prove4" ${pyMEs.clientes[3].idCliente==null? ' style="display: none;" ':' style="display: block;"'}>
								<br /> <br />
								<table>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" value="Ingresar datos del Cliente" />
											<s:hidden name="pyMEs.idCliente4" id="idCliente4" value="%{pyMEs.clientes[3].idCliente}" />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Cliente:" />
										</td>
										<td>
											<s:textfield size="25" id="cliente4" name="pyMEs.clientes[3].cliente" maxlength="100"
												onfocus="javascript:ayudasHelp(37);" onblur="javascript:ayudasHelpBlo(37);"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda"  id="ayudasDisplay37" style="display:none; margin-top:5px;"
												value="Ingrese el nombre del cliente." />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaCaptura" value="* Productos que compra el cliente :" />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:textfield size="25" id="prodCliente4" name="prod4" maxlength="500"
												onfocus="javascript:ayudasHelp(38);" onblur="javascript:ayudasHelpBlo(38);"></s:textfield>
											<label class="agregar" onclick="addProdCliente(4);">+agregar</label>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:textarea id="showProdCliente4" rows="1" cols="40" disabled="true" cssClass="resultado" style="resize: none;" value="%{pyMEs.clientes[3].productosCompra}" />
											<s:hidden id="showProdCliente4Hid"  name="pyMEs.clientes[3].productosCompra" value="%{pyMEs.clientes[3].productosCompra}" />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div id="labDeleteProdC4" ${pyMEs.clientes[3].productosCompra==null? ' style="display: none;" ':' style="display: block;"'}>
												<label class="quitar" onclick="deleteProdCliente(4);">+eliminar producto</label>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda"  id="ayudasDisplay38" style="display:none; margin-top:5px;"
												value="Describa los principales productos que le vende a la tractora." />
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
											<s:textfield size="5" id="aniosProveCliente4" name="pyMEs.clientes[3].aniosProveedor" maxlength="50" 
												onfocus="javascript:ayudasHelp(39);" onblur="javascript:ayudasHelpBlo(39);"
												onkeydown="return validaNumero(event)"></s:textfield>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="*Meses:" />
											<s:textfield size="5" id="mesesProveCliente4" name="pyMEs.clientes[3].mesesProveedor" maxlength="50" 
												onfocus="javascript:ayudasHelp(39);" onblur="javascript:ayudasHelpBlo(39);"
												onkeydown="return validaNumero(event)"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda"  id="ayudasDisplay39" style="display:none; margin-top:5px;"
												value="Escriba los años y meses que lleva como proveedor de la tractora." />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<label class="quitar" onclick="javascript:supCliente(4);">-eliminar cliente</label>
										</td>
									</tr>
								</table>
							</div></td>
					</tr>
					<tr>
						<td>
							<div id="prove5" ${pyMEs.clientes[4].idCliente==null? ' style="display: none;" ':' style="display: block;"'}>
								<br /> <br />
								<table>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" value="Ingresar datos del Cliente" />
											<s:hidden name="pyMEs.clientes[4].idCliente" id="idCliente5" value="%{pyMEs.clientes[4].idCliente}" />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Cliente:" />
										</td>
										<td>
											<s:textfield size="25" id="cliente5" name="pyMEs.clientes[4].cliente" maxlength="100"
												onfocus="javascript:ayudasHelp(40);" onblur="javascript:ayudasHelpBlo(40);"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda"  id="ayudasDisplay40" style="display:none; margin-top:5px;"
												value="Ingrese el nombre del cliente." />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaCaptura" value="* Productos que compra el cliente :" />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:textfield size="25" id="prodCliente5" name="prod5" maxlength="500"
												onfocus="javascript:ayudasHelp(41);" onblur="javascript:ayudasHelpBlo(41);"></s:textfield>
											<label class="agregar" onclick="addProdCliente(5);">+agregar</label>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:textarea id="showProdCliente5" rows="1" cols="40" disabled="true" cssClass="resultado" style="resize: none;" value="%{pyMEs.clientes[4].productosCompra}" />
											<s:hidden id="showProdCliente5Hid"  name="pyMEs.clientes[4].productosCompra" value="%{pyMEs.clientes[4].productosCompra}" />
										</td>
									</tr>
										<tr>
										<td colspan="2">
											<div id="labDeleteProdC5" ${pyMEs.clientes[4].productosCompra==null? ' style="display: none;" ':' style="display: block;"'}>
												<label class="quitar" onclick="deleteProdCliente(5);">+eliminar producto</label>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda"  id="ayudasDisplay41" style="display:none; margin-top:5px;"
												value="Describa los principales productos que le vende a la tractora." />
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
											<s:textfield size="5" id="aniosProveCliente5" name="pyMEs.clientes[4].aniosProveedor" maxlength="50" 
												onfocus="javascript:ayudasHelp(42);" onblur="javascript:ayudasHelpBlo(42);"
												onkeydown="return validaNumero(event)"></s:textfield>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="*Meses:" />
											<s:textfield size="5" id="mesesProveCliente5" name="pyMEs.clientes[4].mesesProveedor" maxlength="50" 
												onfocus="javascript:ayudasHelp(42);" onblur="javascript:ayudasHelpBlo(42);"
												onkeydown="return validaNumero(event)"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda"  id="ayudasDisplay42" style="display:none; margin-top:5px;"
												value="Escriba los años y meses que lleva como proveedor de la tractora." />
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
							<div id="prove6" ${pyMEs.clientes[5].idCliente==null? ' style="display: none;" ':' style="display: block;"'}>
								<br /><br />
								<table>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda" value="Ingresar datos del Cliente" />
											<s:hidden name="pyMEs.clientes[5].idCliente" id="idCliente6" value="%{pyMEs.clientes[5].idCliente}" />
										</td>
									</tr>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="* Cliente:" />
										</td>
										<td>
											<s:textfield size="25" id="cliente6" name="pyMEs.clientes[5].cliente" maxlength="100"
												onfocus="javascript:ayudasHelp(43);" onblur="javascript:ayudasHelpBlo(43);"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda"  id="ayudasDisplay43" style="display:none; margin-top:5px;"
												value="Ingrese el nombre del cliente." />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaCaptura" value="* Productos que compra el cliente :" />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:textfield size="25" id="prodCliente6" name="prod6" maxlength="500"
												onfocus="javascript:ayudasHelp(44);" onblur="javascript:ayudasHelpBlo(44);"></s:textfield>
											<label class="agregar" onclick="addProdCliente(6);">+agregar</label>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:textarea id="showProdCliente6" rows="1" cols="40" disabled="true" cssClass="resultado" style="resize: none;" value="%{pyMEs.clientes[5].productosCompra}" />
											<s:hidden id="showProdCliente6Hid"  name="pyMEs.clientes[5].productosCompra" value="%{pyMEs.clientes[5].productosCompra}" />
										</td>
									</tr>
										<tr>
										<td colspan="2">
											<div id="labDeleteProdC6" ${pyMEs.clientes[5].productosCompra==null? ' style="display: none;" ':' style="display: block;"'}>
												<label class="quitar" onclick="deleteProdCliente(6);">+eliminar producto</label>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda"  id="ayudasDisplay44" style="display:none; margin-top:5px;"
												value="Describa los principales productos que le vende a la tractora." />
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
											<s:textfield size="5" id="aniosProveCliente6" name="pyMEs.clientes[5].aniosProveedor" maxlength="50" 
												onfocus="javascript:ayudasHelp(45);" onblur="javascript:ayudasHelpBlo(45);"
												onkeydown="return validaNumero(event)"></s:textfield>
										</td>
										<td>
											<s:label cssClass="etiquetaCaptura" value="*Meses:" />
											<s:textfield size="5" id="mesesProveCliente6" name="pyMEs.clientes[5].mesesProveedor" maxlength="50" 
												onfocus="javascript:ayudasHelp(45);" onblur="javascript:ayudasHelpBlo(45);"
												onkeydown="return validaNumero(event)"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<s:label cssClass="etiquetaAyuda"  id="ayudasDisplay45" style="display:none; margin-top:5px;"
												value="Escriba los años y meses que lleva como proveedor de la tractora." />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<label class="quitar" onclick="javascript:supCliente(6);">-eliminar cliente</label>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<br />
				<label id="linkAddProve" class="agregar" onclick="javascript:showCliente();">+Agregar otro cliente</label>
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
				<table>
					<tr>
						<td>
							<div id="idDivArchivo1Block" ${pyMEs.descArchivo1==null? ' style="display: block;" ':' style="display: none;"'}>
								<table>
									<tr>
										<td>
											<s:label cssClass="etiquetaCaptura" value="Descripción del archivo :" />
										</td>
										<td>
											<s:textfield id="arch1" size="40" name="pyMEs.descArchivo1" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo1==null? ' style="display: block;" ':' style="display: none;"'}>
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
											<div ${pyMEs.descArchivo2==null? ' style="display: block;" ':' style="display: none;"'}>
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
										<td><s:label cssClass="etiquetaCaptura"
												value="Descripción del archivo :" />
										</td>
										<td><s:textfield id="arch3" size="40"
												name="pyMEs.descArchivo3" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo3==null? ' style="display: block;"
												':' style="display: none;"'}>
												<s:file id="idCampoArchivo3" name="pyMEs.archivo3"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(3);">-eliminar</label>
											</div></td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo4Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="Descripción del archivo :" />
										</td>
										<td><s:textfield id="arch4" size="40"
												name="pyMEs.descArchivo4" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo4==null? ' style="display: block;"
												':' style="display: none;"'}>
												<s:file id="idCampoArchivo4" name="pyMEs.archivo4"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(4);">-eliminar</label>
											</div></td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo5Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="Descripción del archivo :" />
										</td>
										<td><s:textfield id="arch5" size="40"
												name="pyMEs.descArchivo5" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo5==null? ' style="display: block;"
												':' style="display: none;"'}>
												<s:file id="idCampoArchivo5" name="pyMEs.archivo5"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(5);">-eliminar</label>
											</div></td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo6Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="Descripción del archivo :" />
										</td>
										<td><s:textfield id="arch6" size="40"
												name="pyMEs.descArchivo6" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo6==null? ' style="display: block;"
												':' style="display: none;"'}>
												<s:file id="idCampoArchivo6" name="pyMEs.archivo6"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(6);">-eliminar</label>
											</div></td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo7Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="Descripción del archivo :" />
										</td>
										<td><s:textfield id="arch7" size="40"
												name="pyMEs.descArchivo7" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo7==null? ' style="display: block;"
												':' style="display: none;"'}>
												<s:file id="idCampoArchivo7" name="pyMEs.archivo7"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(7);">-eliminar</label>
											</div></td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo8Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="Descripción del archivo :" />
										</td>
										<td><s:textfield id="arch8" size="40"
												name="pyMEs.descArchivo8" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo8==null? ' style="display: block;"
												':' style="display: none;"'}>
												<s:file id="idCampoArchivo8" name="pyMEs.archivo8"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(8);">-eliminar</label>
											</div></td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo9Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="Descripción del archivo :" />
										</td>
										<td><s:textfield id="arch9" size="40"
												name="pyMEs.descArchivo9" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo9==null? ' style="display: block;"
												':' style="display: none;"'}>
												<s:file id="idCampoArchivo9" name="pyMEs.archivo9"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(9);">-eliminar</label>
											</div></td>
									</tr>
								</table>
							</div>

							<div id="idDivArchivo10Block" style="display: none;">
								<table>
									<tr>
										<td><s:label cssClass="etiquetaCaptura"
												value="Descripción del archivo :" />
										</td>
										<td><s:textfield id="arch10" size="40"
												name="pyMEs.descArchivo10" maxlength="100"></s:textfield>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div ${pyMEs.descArchivo10==null? ' style="display: block;"
												':' style="display: none;"'}>
												<s:file id="idCampoArchivo10" name="pyMEs.archivo10"></s:file>
												<label class="quitar" onclick="javascript:supArchivo(10);">-eliminar</label>
											</div></td>
									</tr>
								</table>
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
											style="width: 800px;">Descripción del o de los archivos
											adjuntos</td>
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
							
							<div>
								<s:label cssClass="etiquetaAyuda"
									value="Indique el o los archivos que serán incluidos. Máximo 2MB (.pdf .doc .png)" />
								<br />
							</div></td>
					</tr>
					<tr>
						<td>
							<label id="showArchivo" class="agregar" onclick="javascript:otroArchivo();">+agregar otro</label>	
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
									<td><s:textfield id="ingresosAnt" size="60" name="indicadores.ingresosAntes" maxlength="100" onkeydown="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaAyuda" value="Indique el valor en Millones" /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Número de clientes (antes) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="clientesAnt" size="60" name="indicadores.clientesAntes" maxlength="100" onkeydown="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Número de empleados (antes) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="empleadosAnt" size="60" name="indicadores.empleadosAntes" maxlength="100" onkeydown="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="% Egresos / Ventas (antes) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="egresosAnt" size="60" name="indicadores.egresosAntes" maxlength="100" onkeydown="return validaNumero(event)"></s:textfield></td>
								</tr>
							</table></td>
						<td style="width: 400px;">
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Ventas o ingresos  acumulados (después) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="ingresosDesp" size="60" name="indicadores.ingresosDespues" maxlength="100" onkeydown="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaAyuda" value="Indique el valor en Millones" /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Número de clientes (después) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="clientesDesp" size="60" name="indicadores.clientesDespues" maxlength="100" onkeydown="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="Número de empleados (después) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="empleadosDesp" size="60" name="indicadores.empleadosDespues" maxlength="100" onkeydown="return validaNumero(event)"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="% Egresos / Ventas (después) :" /></td>
								</tr>
								<tr>
									<td><s:textfield id="egresosDesp" size="60" name="indicadores.egresosDespues" maxlength="100" onkeydown="return validaNumero(event)"></s:textfield></td>
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
							<s:label cssClass="etiquetaAyuda" value="Seleccione la industria o industrias a las que se dedica su empresa" />
						</td>
					</tr>
					<tr>
						<td>
							<div id="comboCat1" ${pyMEs.cveScianRequerimientosCompra!=0? ' style="display: none;" ':' style="display: block;"'}>
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
					</tr>
					<tr>
						<td>
							<div id="showNombreCat" ${pyMEs.cveScianRequerimientosCompra==0? ' style="display: none;" ':' style="display: block;"'}>
								<s:textarea id="idInputCatScian" rows="1" cols="53" disabled="true" cssClass="resultado" name="producto" />
								<s:hidden name="pyMEs.cveScianRequerimientosCompra" id="cveScianReqComp" value="%{pyMEs.cveScianRequerimientosCompra}" />
								<br />
								<s:if test="pyMEs.cveScianRequerimientosCompra != 0">
									<label id="labEditCat" class="agregar" onclick="showCombosCat();">+Editar Industria</label>
								</s:if>
							</div>
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
</body>
</html>