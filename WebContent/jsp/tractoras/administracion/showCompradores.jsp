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
		<s:label value="Datos del Comprador" />
		<br /> <br />
		<s:label cssClass="camposObligatorios"
			value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
	</legend>
	<br />
		<s:form action="tractoraCompradoresShow" namespace="/tractora/administracion"
			theme="simple" onsubmit="return validacion()">
			<s:hidden name="tractoras.idUsuario" value="%{tractoras.idUsuario}" />
			<s:hidden name="credenciales" value="%{tractoras.correoElectronico}" /> 	
			<table>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Nombre(s) del Comprador:" /></td>
					<td><s:textfield size="60" id="idNombre"
							name="tractoras.nombreContacto" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Escriba el nombre del comprador." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Apellido Paterno Comprador:" /></td>
					<td><s:textfield size="60" id="idAppPaterno"
							name="tractoras.appPaterno" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Escriba el apellido paterno del comprador." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Apellido Materno Comprador:" /></td>
					<td><s:textfield size="60" id="idAppMaterno"
							name="tractoras.appMaterno" maxlength="250"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Escriba el apellido materno del comprador." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Correo Electrónico:" /></td>
					<td><s:textfield size="60" id="idCorreoElectronico"
							name="tractoras.correoElectronico" maxlength="250"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Escriba el correo electrónico del comprador." />
					</td>
				</tr>
				<tr>
					<td><s:label cssClass="etiquetaCaptura"
							value="* Confirmar Correo Electrónico:" /></td>
					<td><s:textfield size="60" id="idComparaCorreo"
							maxlength="250"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label cssClass="etiquetaAyuda"
							value="Confirme el correo electrónico." />
					</td>
				</tr>
			</table>
			<table class="submit_tabla">
				<tr>
					<td style="width: 250px;"></td>
					<td>
						<input class="botonenviar" value="Cancelar" type="button" onclick="cancela();" />
					</td>
					<td>
						<s:submit cssClass="botonenviar" value="Guardar" />
					</td>
					<td style="width: 250px;"></td>
				</tr>
			</table>
		</s:form>
		<s:form name="frmCancela" action="tractoraCompradoresShow" namespace="/tractora/administracion" theme="simple" method="post"></s:form>
	</fieldset>

	<script type="text/javascript">
		function cancela() {
			$(idProcesa)[0].style.display = 'block';
			document.frmCancela.submit();
		}
		
		function validacion() {
			valorNombre = document.getElementById("idNombre").value;
			valorPaterno = document.getElementById("idAppPaterno").value;
			valorMaterno = document.getElementById("idAppMaterno").value;
			valorCorreo = document.getElementById("idCorreoElectronico").value;
			valorCompara = document.getElementById("idComparaCorreo").value;
			
			if( valorNombre == null || valorNombre.length == 0 || /^\s+$/.test(valorNombre) ) {
				alert("Ingrese el Nombre(s) del Comprador");
				document.getElementById('idNombre').focus();
				return false;
			}else if( valorPaterno == null || valorPaterno.length == 0 || /^\s+$/.test(valorPaterno) ) {
				alert("Ingrese el Apellido Paterno del Comprador");
				document.getElementById('idAppPaterno').focus();
				return false;
			}else if( valorMaterno == null || valorMaterno.length == 0 || /^\s+$/.test(valorMaterno) ) {
				alert("Ingrese el Apellido Materno del Comprador");
				document.getElementById('idAppMaterno').focus();
				return false;
			}else if( !(/[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(valorCorreo)) ) {
				alert("Ingrese una dirección de correo electrónico válida");
				document.getElementById('idCorreoElectronico').focus();
				return false;
			}else if (valorCorreo != valorCompara){
				alert("El correo electrónico no coincide");
				document.getElementById('idComparaCorreo').focus();
				return false;
			}else{
				$(idProcesa)[0].style.display = 'block';
				return true;
			}
		}
</script>
</body>
</html>