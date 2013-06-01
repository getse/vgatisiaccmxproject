<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script
	src="${pageContext.request.contextPath}/js/tractoras.js"
	type="text/javascript"></script>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-285px auto 0 250px';
</script>
</head>
<body>
<fieldset id="requerimientos">
	<div id="busqPyME" ${idUsuario!=0? ' style="display: none;" ' :' style="display: block;"' }>
		<s:form name="busqueda" action="compradorPyMEsShow" namespace="/comprador" theme="simple">
			<legend>
				<s:label value="Listado de PyMEs vinculadas" />
				<br /> <br />
				<s:label cssClass="camposObligatorios"
					value="Seleccione la opción 'Expediente' para ver la información de una PyME." />
			</legend>
			<br />
			<table width="99%" cellspacing="1" cellpadding="1">
				<thead>
					<tr>
						<td class="encabezado_tabla" align="center"><b>No.</b></td>
						<td class="encabezado_tabla" align="center"><b>Nombre comercial</b></td>
						<td class="encabezado_tabla" align="center"><b>Entidad Federativa</b></td>
						<td class="encabezado_tabla" align="center"><b>Teléfono</b></td>
						<td class="encabezado_tabla" align="center"><b>Nombre contacto</b></td>
						<td class="encabezado_tabla" align="center"><b>Apellido Paterno Contacto</b></td>
						<td class="	encabezado_tabla" align="center"><b>Apellido Materno Contacto</b></td>
						<td class="encabezado_tabla" align="center"><b>Correo electrónico contacto</b></td>
						<td class="encabezado_tabla" align="center"><b>Ver Expediente</b></td>
					</tr>
				</thead>
				<tbody>
					<s:set var="contador" value="0" />
					<s:iterator value="listPyMEs" status="stat">
						<s:set var="cnt" value="#contador=#contador+1" />
						<tr>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${cnt}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${nombreComercial}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${estado}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${telefonoContacto1}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${nombreContacto1}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${appPaterno1}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${appMaterno1}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${correoElectronicoContacto1}</td>
							<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center"><a href="${pageContext.request.contextPath}/comprador/compradorPyMEsShow.do?idUsuario=${idUsuario}">Expediente</a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:form>
	</div>
	
	<!-- EXPEDIENTE PYME -->
	<div id="resumenPyME" ${idUsuario==0? ' style="display: none;" ' :' style="display: block;"' }>
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
						<a href="${pageContext.request.contextPath}/comprador/showDoc.do?idArchivo=${pyMEs.idArchivo1}&nameArchivo=${pyMEs.archivo1FileName}&mimeArchivo=${pyMEs.archivo1ContentType}">${pyMEs.archivo1FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo2!=0">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 2:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/comprador/showDoc.do?idArchivo=${pyMEs.idArchivo2}&nameArchivo=${pyMEs.archivo2FileName}&mimeArchivo=${pyMEs.archivo2ContentType}">${pyMEs.archivo2FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo3!=0">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 3:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/comprador/showDoc.do?idArchivo=${pyMEs.idArchivo3}&nameArchivo=${pyMEs.archivo3FileName}&mimeArchivo=${pyMEs.archivo3ContentType}">${pyMEs.archivo3FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo4!=0">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 4:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/comprador/showDoc.do?idArchivo=${pyMEs.idArchivo4}&nameArchivo=${pyMEs.archivo4FileName}&mimeArchivo=${pyMEs.archivo4ContentType}">${pyMEs.archivo4FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo5!=0">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 5:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/comprador/showDoc.do?idArchivo=${pyMEs.idArchivo5}&nameArchivo=${pyMEs.archivo5FileName}&mimeArchivo=${pyMEs.archivo5ContentType}">${pyMEs.archivo5FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo6!=0">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 6:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/comprador/showDoc.do?idArchivo=${pyMEs.idArchivo6}&nameArchivo=${pyMEs.archivo6FileName}&mimeArchivo=${pyMEs.archivo6ContentType}">${pyMEs.archivo6FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo7!=0">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 7:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/comprador/showDoc.do?idArchivo=${pyMEs.idArchivo7}&nameArchivo=${pyMEs.archivo7FileName}&mimeArchivo=${pyMEs.archivo7ContentType}">${pyMEs.archivo7FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo8!=0">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 8:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/comprador/showDoc.do?idArchivo=${pyMEs.idArchivo8}&nameArchivo=${pyMEs.archivo8FileName}&mimeArchivo=${pyMEs.archivo8ContentType}">${pyMEs.archivo8FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo9!=0">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 9:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/comprador/showDoc.do?idArchivo=${pyMEs.idArchivo9}&nameArchivo=${pyMEs.archivo9FileName}&mimeArchivo=${pyMEs.archivo9ContentType}">${pyMEs.archivo9FileName}</a>
					</td>
				</tr>
			</s:if>
			<s:if test="pyMEs.idArchivo10!=0">
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Archivo adjunto 10:</td>
					<td class="cuerpo1TextoResumen">
						<a href="${pageContext.request.contextPath}/comprador/showDoc.do?idArchivo=${pyMEs.idArchivo10}&nameArchivo=${pyMEs.archivo10FileName}&mimeArchivo=${pyMEs.archivo10ContentType}">${pyMEs.archivo10FileName}</a>
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
		<table class="submit_tabla">
			<tr>
				<td style="width: 450px;"></td>
				<td><input
					class="botonenviar"
					value="Regresar"
					type="button"
					onclick="javascript: window.history.back();" /></td>
				<td style="width: 450px;"></td>
			</tr>
		</table>
		<table>
		</table>
	</div>
</fieldset>
</body>
</html>
	