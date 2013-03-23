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
<fieldset id="requerimientos">
	<legend>
		<s:label value="Búsqueda de Requerimientos" />
		<br /> 
		<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		<br /><br />
	</legend>
	<s:form action="pymeRequerimientosShow" namespace="/pyme" theme="simple">	
		<s:label cssClass="etiquetaCaptura" value="* Busqueda por palabra" />
		<s:textfield size="60" id="busqueda" name="busqueda" maxlength="60"></s:textfield>
		<br />
		<s:label cssClass="etiquetaAyuda" value="Escriba en 3 palabras el producto" />
		<br />
		<br />
		<s:label cssClass="etiquetaCaptura" value="Filtro por tractora" />
		<select id="tractoraReq" name="tractoraReq"">
			<option selected="selected" value="Seleccione una opción">Seleccione una opción</option>
			<option value="Diplomado">Diplomado</option>
		</select>
		<br />
		<br />
		<s:label cssClass="etiquetaCaptura" value="Filtro por fecha desde" />
		<select id="inscripcion" name=""">
			<option selected="selected" value="Seleccione una opción">Seleccione una opción</option>
			<option value="Diplomado">Diplomado</option>
		</select>
		<br />
		<br />
		<s:label cssClass="etiquetaCaptura" value="Filtro por fecha hasta" />
		<select id="inscripcion" name=""">
			<option selected="selected" value="Seleccione una opción">Seleccione una opción</option>
			<option value="Diplomado">Diplomado</option>
		</select>
		<br />
		<s:submit cssClass="botonenviar" align="left" value="Buscar" />
		<br /><br />
		
		<!-- Lista busqueda -->
		<s:if test="busqueda != null || (requerimientos.idRequerimiento != 0 && busqueda != null)">
			<table>
				<tr>
					<td>
						<table width="800px" cellspacing="1" cellpadding="1">
							<thead>
								<tr>
									<td class="encabezado_tabla" align="center"><b>Nombre Tractora</b></td>
									<td class="encabezado_tabla" align="center"><b>Parte inicial requerimiento</b></td>
									<td class="encabezado_tabla" align="center"><b>Link en la descripción</b></td>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="listRequerimientos" status="stat">
										<tr>
											<td
												class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
												align="center">${nombreTractora}</td>
												<s:hidden id="nomTracInt" name="requerimientos.nombreTractora" value="" />
											<td
												class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
												align="center">${descripcion}</td>
												<s:hidden id="descReq" name="requerimientos.descripcion" value="" />
											<td
												class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
												align="center">
												<s:hidden id="idReq" name="requerimientos.idRequerimiento" /><a href="${pageContext.request.contextPath}/pyme/pymeRequerimientosShow.do?requerimientos.idRequerimiento=${idRequerimiento}">Ver Requerimiento</a></td>
										</tr>
								</s:iterator>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
			<br />
			<br />
		</s:if>
		<s:if test="requerimientos.idRequerimiento != 0">
			<div id="muestraReq">
				<s:hidden id="idShowReq" name="requerimientos.idRequerimiento" value="%{idRequerimiento}" />
				<table>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="Nombre tractora" />
						</td>
						<td>
							<s:text id="nomTrac" name="%{nombreTractora}" /> 
						</td>
					</tr>
					<tr>
						<td>
							<s:label cssClass="etiquetaCaptura" value="Descripción completa del requerimiento" />
						</td>
						<td>
							<s:property  value="%{descripción}" />
							<s:text id="descTrac" name="requerimientos.descripcion" /> 
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<s:submit cssClass="botonenviar" align="left" value="Responder Requerimiento" />
						</td>
					</tr>
				</table>
			</div>		
		</s:if>
	</s:form>
</fieldset>
<script type="text/javascript">

	function verReq(obj) {
		
		var a = document.getElementsByTagName('a')[0].id;
			//for (var i = 0; i < a .length; i++) {
  				//alert(a[i].id);
			//}
		
		alert("aqui van los atributos del object entre ellos el id " + a.value);
		
		
		 divReq = document.getElementById("muestraReq");
         divReq.style.display = "";
         
         document.getElementById('idShowReq').value = id;
	}
	
</script>
</body>
</html>
