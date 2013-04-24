<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-195px auto 0 250px';
	
	function validacion() {
		valorBusq = document.getElementById("campoBusqueda").value.split(" ");
		
		if( valorBusq == null || valorBusq == 0 || valorBusq.length > 3 || valorBusq == " " ) {
			document.getElementById("campoBusqueda").focus();
			alert("Para realizar una búsqueda Escriba en 3 palabras el producto");
			return false;
		}else{
			return true;	
		}
	}
</script>
</head>

<body>

<fieldset id="requerimientos">
	<legend>
		<s:label value="Búsqueda de PyMEs" />
		<br /> <br />
		<s:label cssClass="camposObligatorios"
			value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
	</legend>
	<br />
	<br />
	<s:form action="pymeBusquedaShow" namespace="/pyme" theme="simple" onsubmit="return validacion()">
		<table>
			<tr>
				<td>
					<s:label cssClass="etiquetaCaptura" value="* Busqueda por palabra" />	
				</td>
				<td>
					<s:textfield size="60" id="campoBusqueda" name="busqueda" maxlength="60"></s:textfield>	
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<s:label cssClass="etiquetaAyuda" value="Escriba la(s) palabra(s) que identifican el producto que busca." />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<br />
					<s:label cssClass="etiquetaCaptura" value="Filtro de busqueda" />
					<br />
				</td>
			</tr>
			<tr>
				<td>
					<s:label cssClass="etiquetaCaptura" value="Estado:" />
				</td>
				<td>
					<select id="estado" name="estado">
						<s:if test="estado == null">
							<option selected="selected" value="Seleccione un estado">Seleccione un estado</option>
						</s:if>
						<s:else>
							<option value=""></option>
							<option selected="selected" value="${estado}"><s:text name="estado" /></option>
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
				<td>
					<s:label cssClass="etiquetaCaptura" value="Sector" />	
				</td>
				<td>
					<s:textfield size="60" id="sector" name="sector" maxlength="60"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>
					<s:label cssClass="etiquetaCaptura" value="Sub sector" />
				</td>
				<td>
					<s:textfield size="60" id="subSector" name="subSector" maxlength="60"></s:textfield>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<s:submit cssClass="botonenviar" align="left" value="Buscar" />
				</td>
			</tr>
		</table>
		<br />
		<br />
		
		<!-- Lista busqueda -->
		<s:if test="estado != null">
			<table width="800px" cellspacing="1" cellpadding="1">
				<thead>
					<tr>
						<td class="encabezado_tabla" align="center"><b>Nombre comercial</b></td>
						<td class="encabezado_tabla" align="center"><b>Estado (Ent Federativa)</b></td>
						<td class="encabezado_tabla" align="center"><b>Teléfono</b></td>
						<td class="encabezado_tabla" align="center"><b>Nombre contacto</b></td>
						<td class="encabezado_tabla" align="center"><b>Apellido Pat Contacto</b></td>
						<td class="encabezado_tabla" align="center"><b>Apellido Mat Contacto</b></td>
						<td class="encabezado_tabla" align="center"><b>Correo electrónico contacto</b></td>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="listPyMEs" status="stat">
						<s:if test="estado != null">
							<tr>
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
							</tr>
						</s:if>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
	</s:form>
</fieldset>
</body>
</html>
