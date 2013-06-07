<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="${pageContext.request.contextPath}/js/tractoras.js" type="text/javascript"></script>
<link
	href="${pageContext.request.contextPath}/css/calendario.css"
	rel="stylesheet"
	type="text/css" />
<script
	src="${pageContext.request.contextPath}/js/ayudas.js"
	type="text/javascript"></script>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-190px auto 0 250px';
</script>
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
<s:form action="consultorInformacionShow" namespace="/consultor" name="frmCancela" id="frmCancela"></s:form>
<div id="Editar" ${consultoras.idUsuario!=0 ?' style="display: block;"':' style="display: none;"'}>
	<fieldset id="requerimientos">
			<s:form action="consultorInformacionShow" namespace="/consultor"
				theme="simple" onsubmit="javascript:return validaDatoss();">
			<s:hidden name="consultoras.idUsuario" value="%{consultoras.idUsuario}"></s:hidden>	
			<legend>
				<s:label value="Confirmar Datos" />
				<br /> <br />
				<s:label cssClass="camposObligatorios"
					value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
			</legend>
			<br />
				<table>
					<tr>
						<td style="width: 470px;">
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura"
											value="* Nombre(s):" /> <s:textfield size="43" id="idNombre"
											name="consultoras.nombreContacto" maxlength="60" onfocus="javascript:ayudasHelp(0);" 
											onblur="javascript:ayudasHelpBlo(0);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay0" style="display:none;margin-top:0px"
											value="Escriba su Nombre(s) sin incluir acentos." /></td>
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" 
											value="* Apellido Paterno:" /> <s:textfield size="36"
											id="idAppPaterno" name="consultoras.appPaternoContacto" maxlength="60"
											onfocus="javascript:ayudasHelp(1);" 
											onblur="javascript:ayudasHelpBlo(1);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay1" style="display:none;margin-top:0px"
											value="Escriba su Apellido Paterno sin incluir acentos." /></td>
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura"
											value="* Apellido Materno:" /> <s:textfield size="35"
											id="idAppMaterno" name="consultoras.appMaternoContacto" maxlength="60"
											onfocus="javascript:ayudasHelp(2);" 
											onblur="javascript:ayudasHelpBlo(2);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay2" style="display:none;margin-top:0px"
											value="Escriba su Apellido Materno sin incluir acentos." /></td>
								</tr>
							</table>
						</td>
						<td style="width: 470px;">
							<table>
								<tr>
									<td><s:label cssClass="etiquetaCaptura"
											value="* Correo Electrónico:" /> <s:textfield size="40"
											id="idCorreoElectronico" name="consultoras.correoElectronico"
											maxlength="100" onfocus="javascript:ayudasHelp(3);" 
											onblur="javascript:ayudasHelpBlo(3);"></s:textfield></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay3" style="display:none;margin-top:0px"
											value="Escriba su correo electrónico." /></td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura"
											value="* Confirmar Correo Electrónico:" /> <s:textfield
											size="28" id="idComparaCorreo" maxlength="100"
											onfocus="javascript:ayudasHelp(4);" 
											onblur="javascript:ayudasHelpBlo(4);"></s:textfield>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaAyuda" id="ayudasDisplay4" style="display:none;margin-top:0px"
											value="Confirme su correo electrónico." /></td>
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Teléfono fijo :" />
									<s:if test="consultoras.telefonos!=null && consultoras.telefonos[0].idTelefono!=0">
									<s:textfield size="30" id="tel0" name="consultoras.telefonos[0].telefono" value="%{consultoras.telefonos[0].telefono}" onkeypress="return tel(this, event);" maxlength="24"
									onfocus="javascript:ayudasHelp(5);" onblur="javascript:ayudasHelpBlo(5);"/>
									<s:hidden name="consultoras.telefonos[0].idTelefono" value="%{consultoras.telefonos[0].idTelefono}" />
									<s:hidden name="consultoras.telefonos[0].idUsuario" value="%{consultoras.telefonos[0].idUsuario}" />
									</s:if>
									<s:else>
										<s:textfield size="30" id="tel0" name="consultoras.telefonos[0].telefono" value="23" onkeypress="return tel(this, event);" maxlength="24"/>
									</s:else>
									</td>
								</tr>
								<tr>
									<td><s:label cssClass="etiquetaCaptura" value="* Teléfono celular :" />
									<s:if test="consultoras.telefonos!=null && consultoras.telefonos[1].idTelefono!=0">
									<s:textfield size="30" id="tel1" name="consultoras.telefonos[1].telefono" value="%{consultoras.telefonos[1].telefono}" 
										onkeypress="return tel(this, event);" maxlength="24" onfocus="javascript:ayudasHelp(5);" onblur="javascript:ayudasHelpBlo(5);"/>
									<s:hidden name="consultoras.telefonos[1].idTelefono" value="%{consultoras.telefonos[1].idTelefono}" />
									<s:hidden name="consultoras.telefonos[1].idUsuario" value="%{consultoras.telefonos[1].idUsuario}" />
									</s:if>
									<s:else>
										<s:textfield size="30" id="tel1" name="consultoras.telefonos[1].telefono" value="" onkeypress="return tel(this, event);" maxlength="24"/>
									</s:else>
									</td>
								</tr>
							</table>
							<table>
								<tr>
									<td>
										<s:label cssClass="etiquetaAyuda" id="ayudasDisplay5" style="display:none;margin-top:0px"
											value="Incluya su teléfono con clave lada y extensión." /><br />
									</td>
								</tr>
							</table>
							
						</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</table>
			<table class="submit_tabla">
					<tr>
						<td style="width: 250px;">
						<input
						class="botonenviar"
						value="Cancelar"
						type="button"
						onclick="cancela();" /></td>
						<td><s:submit
								cssClass="botonenviar"
								value="Guardar" /></td>
						<td style="width: 250px;"></td>
					</tr>
				</table>
	</s:form>
</fieldset>
</div>
<div id="Editar" ${consultoras.idUsuario==0 ? ' style="display: block;"':' style="display: none;"'}>
	<fieldset id="requerimientos">
			<s:form action="consultorInformacionAdd" namespace="/consultor"
				theme="simple">
			<br/><br/>
			<table class="expediente_tabla">
				<tr>
				<td	class="encabezadoTablaResumen"
						colspan="2"
						align="center">Expediente Electrónico del Consultor</td>
				</tr>
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Nombre del Contacto:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${consultoras.nombreContacto}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Apellido Paterno del Contacto:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${consultoras.appPaternoContacto}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo2TablaResumen" align="left">&nbsp;Apellido Materno del Contacto:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${consultoras.appMaternoContacto}</s:label></td>
				</tr>
				<tr>
					<td class="cuerpo1TablaResumen" align="left">&nbsp;Correo Electrónico:</td>
					<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${consultoras.correoElectronico}</s:label></td>
				</tr>
				<s:iterator value="consultoras.telefonos" status="stats">
					<tr>
						<td class="${((stats.index % 2) == 0) ? 'cuerpo2TablaResumen' : 'cuerpo1TablaResumen'}" align="left">&nbsp;Teléfono ${stat.count}:</td>
						<td class="cuerpo1TextoResumen"><s:label cssClass="etiquetaResumen">${telefono}</s:label></td>
					</tr>
				</s:iterator>
			</table>
			<table class="submit_tabla">
				<tr>
					<td style="width: 450px;"></td>
					<td><s:submit
						class="botonenviar"
						value="Modificar"
						type="button"
						 /></td>
					<td style="width: 450px;"></td>
				</tr>
			</table>
		</s:form>
	</fieldset>		
</div>
<script type="text/javascript">
function validaDatoss() {
	valorNombre = document.getElementById("idNombre").value;
	valorPaterno = document.getElementById("idAppPaterno").value;
	valorMaterno = document.getElementById("idAppMaterno").value;
	valorCorreo = document.getElementById("idCorreoElectronico").value;
	valorCompara = document.getElementById("idComparaCorreo").value;

	valorTelefono = document.getElementById("tel0").value;
	valorTelefono2 = document.getElementById("tel1").value;

	
		if (valorNombre == null || valorNombre.length == 0
				|| /^\s+$/.test(valorNombre)) {
			document.getElementById("idNombre").focus();
			alert("Ingrese el Nombre(s) requerido");
			return false;
		} else if (valorPaterno == null || valorPaterno.length == 0
				|| /^\s+$/.test(valorPaterno)) {
			document.getElementById("idAppPaterno").focus();
			alert("Ingrese Apellido Paterno");
			return false;
		} else if (valorMaterno == null || valorMaterno.length == 0
				|| /^\s+$/.test(valorMaterno)) {
			document.getElementById("idAppMaterno").focus();
			alert("Ingrese Apellido Materno");
			return false;
		} else if (!(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
				.test(valorCorreo))) {
			document.getElementById("idCorreoElectronico").focus();
			alert("Ingrese una dirección de correo electrónico válida");
			return false;
		} else if (valorCorreo != valorCompara) {
			document.getElementById("idComparaCorreo").focus();
			alert("El correo electrónico no coincide");
			return false;
		} else if (valorTelefono == null || valorTelefono.length == 0 || !/^\(\d{2}\)\(\d{2}\)\(\d{8}\)\(\d{4}\)$/
						.test(valorTelefono)) {
			document.getElementById("tel0").focus();
			alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
			return false;
		} else if (valorTelefono2 == null || valorTelefono2.length == 0 || !/^\(\d{2}\)\(\d{2}\)\(\d{8}\)\(\d{4}\)$/
					.test(valorTelefono2)) {
			document.getElementById("tel1").focus();
			alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
			return false;
		} else {
			return true;
		}
}
</script>
</body>
</html>