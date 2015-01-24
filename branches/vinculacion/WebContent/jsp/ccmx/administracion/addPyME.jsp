<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script
	src="${pageContext.request.contextPath}/js/ayudas.js"
	type="text/javascript"></script>
</head>
<body>
	<fieldset id="requerimientos">
		<legend>
			<s:label value="Captura para registro de PyME" />
			<br /> <br />
			<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<br />

		<s:form action="PyMEsShow" namespace="/ccmx/administracion"
			theme="simple" onsubmit="return validacion()">
			<table>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Nombre Comercial:" /></td>
					<td><s:textfield size="60" id="idNombreComercial" name="pyMEs.nombreComercial" maxlength="150"
						onfocus="javascript:ayudasHelp(0);" onblur="javascript:ayudasHelpBlo(0);"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay0" style="display:none;margin-top:0px;"
						value="Escriba el nombre comercial." /></td>
				</tr>			
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Correo Electrónico:" /></td>
					<td><s:textfield size="60" id="idCorreoElectronico" name="pyMEs.correoElectronico" maxlength="100"
						onfocus="javascript:ayudasHelp(1);" onblur="javascript:ayudasHelpBlo(1);"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay1" style="display:none;margin-top:0px;"
						value="Escriba su correo electrónico." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Confimar Correo Electrónico:" /></td>
					<td><s:textfield size="60" id="idComparaCorreo" maxlength="100"
						onfocus="javascript:ayudasHelp(2);" onblur="javascript:ayudasHelpBlo(2);"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay2" style="display:none;margin-top:0px;"
						value="Confirme el correo electrónico." /></td>
				</tr>
				
			<!-- CONTACTO TIPO VENTAS -->
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="Contacto de Ventas" /></td>
					<td>
						&nbsp;
						<s:hidden name="pyMEs.tipoContacto1" id="tipoContacto1" value="Ventas" />
						<s:hidden name="pyMEs.correoElectronicoContacto1" id="correoContacto1" value="" />
					</td>
				</tr>
				
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Nombre(s) :" /></td>
					<td><s:textfield size="60" id="idNombreContacto" name="pyMEs.nombreContacto1" maxlength="60" 
						onfocus="javascript:ayudasHelp(3);" onblur="javascript:ayudasHelpBlo(3);"
						onkeypress="return validaLetra(event)"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay3" style="display:none;margin-top:0px;"
						value="Escriba el nombre o nombres del contacto." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Apellido Paterno :" /></td>
					<td><s:textfield size="60" id="idAppPaterno" name="pyMEs.appPaterno1" maxlength="60" 
						onfocus="javascript:ayudasHelp(4);" onblur="javascript:ayudasHelpBlo(4);"
						onkeypress="return validaLetra(event)"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay4" style="display:none;margin-top:0px;"
						value="Escriba el apellido paterno del contacto." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Apellido Materno :" /></td>
					<td><s:textfield size="60" id="idAppMaterno" name="pyMEs.appMaterno1" maxlength="60" 
						onfocus="javascript:ayudasHelp(5);" onblur="javascript:ayudasHelpBlo(5);"
						onkeypress="return validaLetra(event)"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay5" style="display:none;margin-top:0px;"
						value="Escriba el apellido materno del contacto." /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay6" style="display:none;margin-top:0px;"
						value="Seleccione una de las Grandes Empresas que se asignará a la PyME." /></td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura" value="* Grandes Empresas :" /></td>
					<td>
						<select id="optTrac" name="pyMEs.idTractora" style="width: 200px;"
							onfocus="javascript:ayudasHelp(6);" onblur="javascript:ayudasHelpBlo(6);">
							<option selected="selected" value="0">--Seleccione una de las Grandes Empresas--</option>
							<s:iterator value="listTractoras" status="stat">
								<option value="${idUsuario}">${empresa}</option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:submit cssClass="botonenviar" value="Registrar PyME" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>
	<script type="text/javascript">
		function validacion() {
			valorEmpresa = document.getElementById("idNombreComercial").value;
			valorCorreo = document.getElementById("idCorreoElectronico").value;
			valorCompara = document.getElementById("idComparaCorreo").value;
			valorNombre = document.getElementById("idNombreContacto").value;
			valorPaterno = document.getElementById("idAppPaterno").value;
			valorMaterno = document.getElementById("idAppMaterno").value;
			valorTractora = document.getElementById('optTrac').selectedIndex;
			document.getElementById('correoContacto1').value = document.getElementById('idCorreoElectronico').value;		
			if( valorEmpresa == null || valorEmpresa.length == 0 || /^\s+$/.test(valorEmpresa) ) {
				alert("Ingrese el Nombre Comercial");
				return false;
			}else if( !(/[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(valorCorreo)) ) {
				alert("Ingrese una dirección de correo electrónico válida");
				return false;
			}else if (valorCorreo != valorCompara){
				alert("El correo electrónico no coincide");
				return false;
			}else if( valorNombre == null || valorNombre.length == 0 || /^\s+$/.test(valorNombre) ) {
				alert("Ingrese el Nombre(s) requerido");
				return false;
			}else if( valorPaterno == null || valorPaterno.length == 0 || /^\s+$/.test(valorPaterno) ) {
				alert("Ingrese Apellido Paterno");
				return false;
			}else if( valorMaterno == null || valorMaterno.length == 0 || /^\s+$/.test(valorMaterno) ) {
				alert("Ingrese Apellido Materno");  
				return false;
			}else if( valorTractora == 0 ){
				alert("Seleccione una de las Grandes Empresas para asignar a la PyME");  
				return false;
			}
			$(idProcesa)[0].style.display = 'block';
			return true;
		}
		
		function validaLetra(e) {
			tecla = (document.all) ? e.keyCode : e.which;
			if (tecla==0) return true;
			if (tecla==8) return true;
		    patron =/[A-ZÑña-z\s]/;
		    te = String.fromCharCode(tecla);
		    return patron.test(te); 
		}
	</script>
</body>
</html>