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
						<img
							src="${pageContext.request.contextPath}/img/palomitaverde.gif" />
					</s:if> <s:else>
						<img src="${pageContext.request.contextPath}/img/warning.png" />
					</s:else>
				</td>
				<td class="contenidoNota"><s:property value="mensaje.mensaje" />
				</td>
			</tr>
		</table>
	</s:if>
	<fieldset id="requerimientos">
	<div id="busqPyME" ${idUsuario!=0? ' style="display: none;" ' :' style="display: block;"' }>
		
		<s:form action="PyMEAdd" namespace="/ccmx/administracion/pymes"
			theme="simple">
			
			<legend>
				<s:label value="Administración de PyMEs" />
				<br /> <br />
				<s:label cssClass="camposObligatorios"
					value="Seleccione la opción 'Registrar PyME' para agregar una nueva PyME, seleccione la opción 'Modificar' para modificarla." />
			</legend>
			<br />

			<table>
				<tr>
					<td>
						<table width="800px" cellspacing="1" cellpadding="1">
							<thead>
								<tr>
									<td class="encabezado_tabla" align="center"><b>No.</b></td>
									<td class="encabezado_tabla" align="center"><b>Nombre
											Comercial</b></td>
									<td class="encabezado_tabla" align="center"><b>Correo
											electrónico</b></td>
									<td class="encabezado_tabla" align="center"><b>Nombre
											Contacto</b></td>
									<td class="encabezado_tabla" align="center"><b>Apellido
											Paterno Contacto</b></td>
									<td class="encabezado_tabla" align="center"><b>Apellido
											Materno Contacto</b></td>
									<td class="encabezado_tabla" align="center"><b>Expediente
											</b></td>
									<td class="encabezado_tabla" align="center"><b>Desactivar
											PyME</b></td>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="listPyMEs" status="stat">
									<s:if test="%{idUsuarioPadre==1}">
										<tr>
										<td
												class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
												align="center">${stat.count}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${nombreComercial}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${correoElectronico}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${nombreContacto1}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${appPaterno1}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${appMaterno1}</td>
										<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
												align="center"><a href="${pageContext.request.contextPath}/ccmx/administracion/pymes/PyMEsShow.do?idUsuario=${idUsuario}">Ver Expediente</a></td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center"><a href="#">Desactivar</a></td>
										</tr>
									</s:if>
								</s:iterator>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td><br />
					<s:submit cssClass="botonenviar" value="Registrar PyME" />
					</td>
				</tr>
			</table>
		</s:form>
	</div>	
		<!-- EXPEDIENTE PYME -->
	<div id="resumenPyME" ${idUsuario==0? ' style="display: none;" ' :' style="display: block;"' }>
		<br />
		<br />
		<table>
			<tr>
				<td class="encabezadoTablaResumen" colspan="2" align="center">Expediente PyME</td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Mensaje:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.mensajeVentas}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo2TablaResumen" align="left">&nbsp;Nombre comercial de la empresa:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.nombreComercial}</s:label></td>
			</tr>
			
			<!-- PRODUCTOS -->
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto1}</s:label></td>
			</tr>
			<s:if test="pyMEs.producto2!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto2}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto3!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto3}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto4!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto4}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto5!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto5}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto6!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto6}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto7!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto7}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto8!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto8}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto9!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto9}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto10!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto10}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto11!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto11}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto12!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto12}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto13!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto13}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto14!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto14}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto15!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto15}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto16!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto16}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto17!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto17}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto18!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto18}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto19!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto19}</s:label></td>
					</tr>
			</s:if>
			<s:if test="pyMEs.producto20!=null">
					<tr>
						<td class="cuerpo1TablaResumen" align="left">&nbsp;Productos que vende:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.producto20}</s:label></td>
					</tr>
			</s:if>
			
			<!-- CLIENTES -->
			<tr>
				<td class="cuerpo2TablaResumen" align="left">&nbsp;Principales clientes:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.cliente1}</s:label></td>
			</tr>
			<s:if test="pyMEs.cliente2!=null">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Principales clientes:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.cliente2}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.cliente3!=null">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Principales clientes:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.cliente3}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.cliente4!=null">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Principales clientes:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.cliente4}</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.cliente5!=null">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Principales clientes:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.cliente5}</s:label></td>
				</tr>
			</s:if>
			
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Página web:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.paginaWeb}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo2TablaResumen" align="left">&nbsp;Nombres contacto de Ventas:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.nombreContacto1}</s:label></td>
			</tr>
			
			<tr>
				<td class="cuerpo2TablaResumen" align="left">&nbsp;Apellido Paterno Contacto de Ventas:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.appPaterno1}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Apellido Materno Contacto de Ventas:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.appMaterno1}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo2TablaResumen" align="left">&nbsp;Correo Electronico contacto de Ventas:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.correoElectronicoContacto1}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo1TablaResumen" align="left">&nbsp;Telefono de Contacto de Ventas:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.telefonoContacto1}</s:label></td>
			</tr>
			
			<!-- ESTADOS -->
			<s:if test="pyMEs.bAguascalientes!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Aguascalientes</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bBajaCaliforniaSur!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Baja California Sur</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bBajaCaliforniaNorte!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Baja California Norte</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bCampeche!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Campeche</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bChiapas!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Chiapas</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bChihuahua!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Chihuahua</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bCoahuila!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Coahuila</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bColima!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Colima</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bDistritoFederal!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Distrito Federal</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bDurango!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Durango</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bGuanajuato!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Guanajuato</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bGuerrero!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Guerrero</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bHidalgo!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Hidalgo</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bJalisco!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Jalisco</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bMexico!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">México</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bMichoacan!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Michoacán</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bMorelos!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Morelos</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bNayarit!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Nayarit</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bNuevoLeon!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Nuevo León</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bOaxaca!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Oaxaca</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bPuebla!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Puebla</s:label></td>
				</tr>
			</s:if>
			
			<s:if test="pyMEs.bQueretaro!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Querétaro</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bQuintanaRoo!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Quintana Roo</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bSanLuisPotosi!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">San Luis Potosí</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bSinaloa!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Sinaloa</s:label></td>
				</tr>			
			</s:if>
			
			<s:if test="pyMEs.bSonora!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Sonora</s:label></td>
				</tr>			
			</s:if>
			<s:if test="pyMEs.bTabasco!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Tabasco</s:label></td>
				</tr>			
			</s:if>
			<s:if test="pyMEs.bTamaulipas!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Tamaulipas</s:label></td>
				</tr>			
			</s:if>
			<s:if test="pyMEs.bTlaxcala!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Tlaxcala</s:label></td>
				</tr>			
			</s:if>
			<s:if test="pyMEs.bVeracruz!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Veracruz</s:label></td>
				</tr>			
			</s:if>
			<s:if test="pyMEs.bYucatan!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Yucatán</s:label></td>
				</tr>			
			</s:if>
			<s:if test="pyMEs.bZacatecas!=false">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Estados donde puede vender sus productos:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Zacatecas</s:label></td>
				</tr>			
			</s:if>
			<tr>
				<td class="cuerpo2TablaResumen" align="left">&nbsp;Dirección:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${domicilios.calle} ${domicilios.numExt} ${domicilios.numInt} ${domicilios.piso} ${domicilios.colonia} ${domicilios.delegacion} ${domicilios.estado} ${domicilios.codigoPostal}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo2TablaResumen" align="left">&nbsp;RFC:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.rfc}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo2TablaResumen" align="left">&nbsp;Certificaciones:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${certificaciones.certificacion}</s:label></td>
			</tr>
			<tr>
				<td class="cuerpo2TablaResumen" align="left">&nbsp;Consultoría:</td>
				<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${pyMEs.bServiciosCcmxConsultoria}</s:label></td>
			</tr>
			<s:if test="pyMEs.bDiplomadoCcmxUno==true">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Diplomados:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Cultura organizacional y la competitividad de las empresas</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bDiplomadoCcmxDos==true">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Diplomados:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Estrategia Comercial, Imagen y Cadena de Distribución</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bDiplomadoCcmxTres==true">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Diplomados:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Desarrollo de Métodos de Producción Esbeltos y Cadena de Valor</s:label></td>
				</tr>
			</s:if>
			<s:if test="pyMEs.bDiplomadoCcmxCuatro==true">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Diplomados:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">Estrategia, Planeación e Innovación</s:label></td>
				</tr>
			</s:if>
			
			<!-- ARCHIVOS -->
			<s:if test="pyMEs.idArchivo1!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo adjunto 1:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/ccmx/administracion/pymes/showDoc.do?idArchivo=${pyMEs.idArchivo1}&nameArchivo=${pyMEs.archivo1FileName}&mimeArchivo=${pyMEs.archivo1ContentType}">${pyMEs.archivo1FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo2!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo adjunto 2:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/ccmx/administracion/pymes/showDoc.do?idArchivo=${pyMEs.idArchivo2}&nameArchivo=${pyMEs.archivo2FileName}&mimeArchivo=${pyMEs.archivo2ContentType}">${pyMEs.archivo2FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo3!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo adjunto 3:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/ccmx/administracion/pymes/showDoc.do?idArchivo=${pyMEs.idArchivo3}&nameArchivo=${pyMEs.archivo3FileName}&mimeArchivo=${pyMEs.archivo3ContentType}">${pyMEs.archivo3FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo4!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo adjunto 4:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/ccmx/administracion/pymes/showDoc.do?idArchivo=${pyMEs.idArchivo4}&nameArchivo=${pyMEs.archivo4FileName}&mimeArchivo=${pyMEs.archivo4ContentType}">${pyMEs.archivo4FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo5!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo adjunto 5:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/ccmx/administracion/pymes/showDoc.do?idArchivo=${pyMEs.idArchivo5}&nameArchivo=${pyMEs.archivo5FileName}&mimeArchivo=${pyMEs.archivo5ContentType}">${pyMEs.archivo5FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo6!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo adjunto 6:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/ccmx/administracion/pymes/showDoc.do?idArchivo=${pyMEs.idArchivo6}&nameArchivo=${pyMEs.archivo6FileName}&mimeArchivo=${pyMEs.archivo6ContentType}">${pyMEs.archivo6FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo7!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo adjunto 7:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/ccmx/administracion/pymes/showDoc.do?idArchivo=${pyMEs.idArchivo7}&nameArchivo=${pyMEs.archivo7FileName}&mimeArchivo=${pyMEs.archivo7ContentType}">${pyMEs.archivo7FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo8!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo adjunto 8:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/ccmx/administracion/pymes/showDoc.do?idArchivo=${pyMEs.idArchivo8}&nameArchivo=${pyMEs.archivo8FileName}&mimeArchivo=${pyMEs.archivo8ContentType}">${pyMEs.archivo8FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo9!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo adjunto 9:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/ccmx/administracion/pymes/showDoc.do?idArchivo=${pyMEs.idArchivo9}&nameArchivo=${pyMEs.archivo9FileName}&mimeArchivo=${pyMEs.archivo9ContentType}">${pyMEs.archivo9FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo10!=0">
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Archivo adjunto 10:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/ccmx/administracion/pyme/showDoc.do?idArchivo=${pyMEs.idArchivo10}&nameArchivo=${pyMEs.archivo10FileName}&mimeArchivo=${pyMEs.archivo10ContentType}">${pyMEs.archivo10FileName}</a>
					</td>
				</tr>
			</s:if>


			<!--<s:iterator value="tractoras.telefonos" status="stat">
				<tr>
					<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="left">&nbsp;Teléfono ${stat.count}:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${telefono}</s:label></td>
				</tr>
			</s:iterator>-->
			
		</table>
	</div>
	</fieldset>

</body>
</html>