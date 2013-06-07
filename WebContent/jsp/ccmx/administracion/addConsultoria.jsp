<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta
	http-equiv="Content-Type"
	content="text/html; charset=UTF-8" />
<script
	src="${pageContext.request.contextPath}/js/ayudas.js"
	type="text/javascript"></script>
</head>
<body onload="javascript:completar(${consultoras.costoAnticipo})">
	<fieldset id="requerimientos">
		<legend>
			<s:label value="Captura para registro de Empresa Consultora" />
			<br /> <br />
			<s:label
				cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<br />
		<s:form
			action="consultorasShow"
			namespace="/ccmx/administracion/consultoras"
			theme="simple"
			onsubmit="return validacion()">
			<s:if test="%{consultoras.idUsuario>0}">
			<s:hidden name="consultoras.idUsuario" value="%{consultoras.idUsuario}" /></s:if>
			<s:hidden name="credenciales" value="%{consultoras.correoElectronico}" />
			<table>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Empresa:" /></td>
					<td><s:textfield
							size="60"
							id="idEmpresa"
							name="consultoras.empresa"
							maxlength="100"
							onfocus="javascript:ayudasHelp(1);" 
							onblur="javascript:ayudasHelpBlo(1);"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay1" style="display:none;margin-top:0px;"
							value="Incluya el nombre de la empresa consultora que se dará de alta." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Nombre(s) Contacto:" /></td>
					<td><s:textfield
							size="60"
							id="idNombreContacto"
							name="consultoras.nombreContacto"
							maxlength="60"
							onfocus="javascript:ayudasHelp(2);" 
							onblur="javascript:ayudasHelpBlo(2);"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay2" style="display:none;margin-top:0px;"
							value="Ingrese el nombre." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Apellido Paterno Contacto:" /></td>
					<td><s:textfield
							size="60"
							id="idAppPaternoContacto"
							name="consultoras.appPaternoContacto"
							maxlength="60"
							onfocus="javascript:ayudasHelp(3);" 
							onblur="javascript:ayudasHelpBlo(3);"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay3" style="display:none;margin-top:0px;"
							value="Ingrese el apellido paterno." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Apellido Materno Contacto:" /></td>
					<td><s:textfield
							size="60"
							id="idAppMaternoContacto"
							name="consultoras.appMaternoContacto"
							maxlength="60"
							onfocus="javascript:ayudasHelp(4);" 
							onblur="javascript:ayudasHelpBlo(4);"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay4" style="display:none;margin-top:0px;"
							value="Ingrese el apellido materno." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Correo electrónico:" /></td>
					<td><s:textfield
							size="60"
							id="idCorreoElectronico"
							name="consultoras.correoElectronico"
							maxlength="60"
							onfocus="javascript:ayudasHelp(5);" 
							onblur="javascript:ayudasHelpBlo(5);"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay5" style="display:none;margin-top:0px;"
							value="Ingrese el correo electrónico." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Confirmar Correo Electrónico:" /></td>
					<td><s:textfield
							size="60"
							id="idConfirmacion"
							maxlength="60"
							onfocus="javascript:ayudasHelp(6);" 
							onblur="javascript:ayudasHelpBlo(6);"></s:textfield></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay6" style="display:none;margin-top:0px;"
							value="Ingrese nuevamente el correo electrónico." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Costo Anticipo:" /></td>
					<td><s:textfield
							size="60"
							maxlength="12"
							id="costoAnticipo"
							onkeypress="javascript:return validaNumero(event)"
							onfocus="javascript:ayudasHelp(7);" 
							onblur="javascript:ayudasHelpBlo(7);">
							</s:textfield>
						<s:hidden name="consultoras.costoAnticipo" 
						value="%{consultoras.costoAnticipo}"
						id="Anticipo"></s:hidden></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay7" style="display:none;margin-top:0px;"
							value="Ingrese el costo de pago de anticipo con formato 123,123,123.00." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Costo Abono1:" /></td>
					<td><s:textfield
							size="60"
							maxlength="12"
							id="costoAbono1"
							onkeypress="javascript:return validaNumero(event)"
							onfocus="javascript:ayudasHelp(8);" 
							onblur="javascript:ayudasHelpBlo(8);">
							</s:textfield></td>
						<s:hidden name="consultoras.costoAbono1" 
						value="%{consultoras.costoAbono1}"
						id="Abono1"></s:hidden>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay8" style="display:none;margin-top:0px;"
							value="Ingrese el costo de pago de abono1 con formato 123,123,123.00." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Costo Abono2:" /></td>
					<td><s:textfield
							size="60"
							maxlength="12"
							id="costoAbono2"
							onkeypress="javascript:return validaNumero(event)"
							onfocus="javascript:ayudasHelp(9);" 
							onblur="javascript:ayudasHelpBlo(9);">
							</s:textfield></td>
						<s:hidden name="consultoras.costoAbono2"
						value="%{consultoras.costoAbono2}"
						id="Abono2"></s:hidden>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay9" style="display:none;margin-top:0px;"
							value="Ingrese el costo de pago de abono2 con formato 123,123,123.00." /></td>
				</tr>
				<tr>
					<td><s:label
							cssClass="etiquetaCaptura"
							value="* Costo Finiquito:" /></td>
					<td><s:textfield
							size="60"
							maxlength="12"
							id="costoFiniquito"
							onkeypress="javascript:return validaNumero(event)"
							onfocus="javascript:ayudasHelp(10);" 
							onblur="javascript:ayudasHelpBlo(10);">
							</s:textfield></td>
						<s:hidden name="consultoras.costoFiniquito"
						value="%{consultoras.costoFiniquito}"
						id="Finiquito"></s:hidden>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:label
							cssClass="etiquetaAyuda" id="ayudasDisplay10" style="display:none;margin-top:0px;"
							value="Ingrese el costo de pago de finiquito con formato 123,123,123.00." /></td>
				</tr>
				<tr>
					<td colspan="2"><s:submit
							cssClass="botonenviar"
							value="Registrar Consultora" /></td>
				</tr>
			</table>
		</s:form>
	</fieldset>
	<script type="text/javascript">
	window.onload = function(){
		numero = document.getElementById("Anticipo").value;
		var n=numero.toString();
		var x=numero.indexOf('.');
		var res="";
		if((x+2)==n.length){
			res= n+"0";
		}
		else{
			res=numero.charAt(x+1)+numero.charAt(x+2)+res;
		}
		var pi=x;
		var y=0;
		for(pi;pi>=0;pi--){		
			res=numero.charAt(pi)+res;
			if(y%3==0&&y>0){
				res=","+res;
			}	
			y=y+1;			
		}
		document.getElementById("costoAnticipo").value=res;
		numero = document.getElementById("Abono1").value;
		var n=numero.toString();
		var x=numero.indexOf('.');
		var res="";
		if((x+2)==n.length){
			res= n+"0";
		}
		else{
			res=numero.charAt(x+1)+numero.charAt(x+2)+res;
		}
		var pi=x;
		var y=0;
		for(pi;pi>=0;pi--){		
			res=numero.charAt(pi)+res;
			if(y%3==0&&y>0){
				res=","+res;
			}	
			y=y+1;			
		}
		document.getElementById("costoAbono1").value=res;
		numero = document.getElementById("Abono2").value;
		var n=numero.toString();
		var x=numero.indexOf('.');
		var res="";
		if((x+2)==n.length){
			res= n+"0";
		}
		else{
			res=numero.charAt(x+1)+numero.charAt(x+2)+res;
		}
		var pi=x;
		var y=0;
		for(pi;pi>=0;pi--){		
			res=numero.charAt(pi)+res;
			if(y%3==0&&y>0){
				res=","+res;
			}	
			y=y+1;			
		}
		document.getElementById("costoAbono2").value=res;
		numero = document.getElementById("Finiquito").value;
		var n=numero.toString();
		var x=numero.indexOf('.');
		var res="";
		if((x+2)==n.length){
			res= n+"0";
		}
		else{
			res=numero.charAt(x+1)+numero.charAt(x+2)+res;
		}
		var pi=x;
		var y=0;
		for(pi;pi>=0;pi--){		
			res=numero.charAt(pi)+res;
			if(y%3==0&&y>0){
				res=","+res;
			}	
			y=y+1;			
		}
		document.getElementById("costoFiniquito").value=res;
	}
		function validacion() {
			valorEmpresa = document.getElementById("idEmpresa").value;
			valorNombre = document.getElementById("idNombreContacto").value;
			valorPaterno = document.getElementById("idAppPaternoContacto").value;
			valorMaterno = document.getElementById("idAppMaternoContacto").value;
			valorCorreo = document.getElementById("idCorreoElectronico").value;
			valorCompara = document.getElementById("idConfirmacion").value;
			valAnticipo = document.getElementById("costoAnticipo").value;
			valAbono1 = document.getElementById("costoAbono1").value;
			valAbono2 = document.getElementById("costoAbono2").value;
			valFiniquito = document.getElementById("costoFiniquito").value;
			if (valorEmpresa == null || valorEmpresa.length == 0
					|| /^\s+$/.test(valorEmpresa)) {
				document.getElementById("idEmpresa").focus();
				alert("Ingrese el nombre de la Empresa");
				return false;
			} else if (valorNombre == null || valorNombre.length == 0
					|| /^\s+$/.test(valorNombre)) {
				document.getElementById("idNombreContacto").focus();
				alert("Ingrese el Nombre");
				return false;
			} else if (valorPaterno == null || valorPaterno.length == 0
					|| /^\s+$/.test(valorPaterno)) {
				document.getElementById("idAppPaternoContacto").focus();
				alert("Ingrese Apellido Paterno");
				return false;
			} else if (valorMaterno == null || valorMaterno.length == 0
					|| /^\s+$/.test(valorMaterno)) {
				document.getElementById("idAppMaternoContacto").focus();
				alert("Ingrese Apellido Materno");
				return false;
			} else if (!(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
					.test(valorCorreo))) {
				document.getElementById("idCorreoElectronico").focus();
				alert("Ingrese una dirección de correo electrónico válida");
				return false;
			} else if (valorCorreo != valorCompara) {
				document.getElementById("idConfirmacion").focus();
				alert("El correo electrónico no coincide");
				return false;
			} else if (valAnticipo == null 
					|| ! /^(((\d{1,3},)(\d{3},)*\d{3})|(\d{1,3})).\d{2}$/.test(valAnticipo)) {
				document.getElementById("costoAnticipo").focus();
				alert("Ingrese el costo de Anticipo\nFormato: 1,123,123.00 \nparcialemte según la cantidad deseada.");
				return false;
			} else if (valAbono1 == null 
					|| ! /^(((\d{1,3},)(\d{3},)*\d{3})|(\d{1,3})).\d{2}$/.test(valAbono1)) {
				document.getElementById("costoAbono1").focus();
				alert("Ingrese el costo de abono1\nFormato: 1,123,123.00 \nparcialemte según la cantidad deseada.");
				return false;
			} else if (valAbono2 == null 
					|| ! /^(((\d{1,3},)(\d{3},)*\d{3})|(\d{1,3})).\d{2}$/.test(valAbono2)) {
				document.getElementById("costoAbono2").focus();
				alert("Ingrese el costo de abono2\nFormato: 1,123,123.00 \nparcialemte según la cantidad deseada.");
				return false;
			} else if (valFiniquito == null 
					|| ! /^(((\d{1,3},)(\d{3},)*\d{3})|(\d{1,3})).\d{2}$/.test(valFiniquito)) {
				document.getElementById("costoFiniquito").focus();
				alert("Ingrese el costo de finiquito\nFormato: 1,123,123.00 \nparcialemte según la cantidad deseada.");
				return false;
			}
			document.getElementById("Anticipo").value=replaceAll(valAnticipo,",","");
			document.getElementById("Abono1").value=replaceAll(valAbono1,",","");
			document.getElementById("Abono2").value=replaceAll(valAbono2,",","");
			document.getElementById("Finiquito").value=replaceAll(valFiniquito,",","");
			return true;
		}
		function replaceAll( text, busca, reemplaza ){
			  while (text.toString().indexOf(busca) != -1)
			      text = text.toString().replace(busca,reemplaza);
			  return text;
		}

		function validaNumero(e) {
			tecla = (document.all) ? e.keyCode : e.which;
			if (tecla==0) return true;		
			if (tecla==8) return true;
			if (tecla==48) return true;
			if (tecla==49) return true;
			if (tecla==50) return true;
			if (tecla==51) return true;
			if (tecla==52) return true;
			if (tecla==53) return true;
			if (tecla==54) return true;
			if (tecla==55) return true;
			if (tecla==56) return true;
			if (tecla==57) return true;
			if (tecla==46) return true;
			if (tecla==44) return true;
			patron = /1/;
			te = String.fromCharCode(tecla);
			return patron.test(te);	
		}
	</script>
</body>
</html>