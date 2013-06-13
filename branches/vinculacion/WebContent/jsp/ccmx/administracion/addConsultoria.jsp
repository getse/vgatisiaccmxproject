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
			<table width="100%">
			<tr>
				<td style="50%">
					<table width="100%">
						<tr>
							<td><s:label
									cssClass="etiquetaCaptura"
									value="* Empresa:" /></td>
						</tr>
						<tr>
							<td><s:textfield
									size="60"
									id="idEmpresa"
									name="consultoras.empresa"
									maxlength="100"
									onfocus="javascript:ayudasHelp(1);" 
									onblur="javascript:ayudasHelpBlo(1);"></s:textfield></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaAyuda" id="ayudasDisplay1" style="display:none;margin-top:0px;"
									value="Incluya el nombre de la empresa consultora que se dará de alta." /></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaCaptura"
									value="* Nombre(s) Contacto:" /></td>
						</tr>
						<tr>
							<td><s:textfield
									size="60"
									id="idNombreContacto"
									name="consultoras.nombreContacto"
									maxlength="60" 
									onfocus="javascript:ayudasHelp(2);" 
									onblur="javascript:ayudasHelpBlo(2);"
									onkeydown="return validaLetra(event)"></s:textfield></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaAyuda" id="ayudasDisplay2" style="display:none;margin-top:0px;"
									value="Ingrese el nombre." /></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaCaptura"
									value="* Apellido Paterno Contacto:" /></td>
						</tr>
						<tr>
							<td><s:textfield
									size="60"
									id="idAppPaternoContacto"
									name="consultoras.appPaternoContacto"
									maxlength="60" 
									onfocus="javascript:ayudasHelp(3);" 
									onblur="javascript:ayudasHelpBlo(3);"
									onkeydown="return validaLetra(event)"></s:textfield></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaAyuda" id="ayudasDisplay3" style="display:none;margin-top:0px;"
									value="Ingrese el apellido paterno." /></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaCaptura"
									value="* Apellido Materno Contacto:" /></td>
						</tr>
						<tr>
							<td><s:textfield
									size="60"
									id="idAppMaternoContacto"
									name="consultoras.appMaternoContacto"
									maxlength="60" 
									onfocus="javascript:ayudasHelp(4);" 
									onblur="javascript:ayudasHelpBlo(4);"
									onkeydown="return validaLetra(event)"></s:textfield></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaAyuda" id="ayudasDisplay4" style="display:none;margin-top:0px;"
									value="Ingrese el apellido materno." /></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaCaptura"
									value="* Correo electrónico:" /></td>
						</tr>
						<tr>
							<td><s:textfield
									size="60"
									id="idCorreoElectronico"
									name="consultoras.correoElectronico"
									maxlength="60"
									onfocus="javascript:ayudasHelp(5);" 
									onblur="javascript:ayudasHelpBlo(5);"></s:textfield></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaAyuda" id="ayudasDisplay5" style="display:none;margin-top:0px;"
									value="Ingrese el correo electrónico." /></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaCaptura"
									value="* Confirmar Correo Electrónico:" /></td>
						</tr>
						<tr>
							<td><s:textfield
									size="60"
									id="idConfirmacion"
									maxlength="60"
									onfocus="javascript:ayudasHelp(6);" 
									onblur="javascript:ayudasHelpBlo(6);"></s:textfield></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaAyuda" id="ayudasDisplay6" style="display:none;margin-top:0px;"
									value="Ingrese nuevamente el correo electrónico." /></td>
						</tr>
					</table>				
				</td>
				<td style="50%">
					<table width="100%">
						<tr>
							<td style="width: 100%">
								<s:label
									cssClass="etiquetaCaptura"
									value="Administración de Pagos." /></td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td><s:label
									cssClass="etiquetaCaptura"
									value="* Pago de anticipo autorizado:" /></td>
						</tr>
						<tr>
							<td><s:textfield
									size="30"
									maxlength="9"
									id="costoAnticipo"
									onkeypress="javascript:return validaNumero(event)"
									onkeyup ="javascript: addComa(event,'costoAnticipo');"
									onfocus="javascript:ayudasHelp(7);" 
									onblur="javascript:ayudasHelpBlo(7);">
									</s:textfield>.<s:textfield
									size="2"
									maxlength="2"
									id="costoAnticipo2"
									onkeypress="javascript:return validaCentavos(event)"
									onfocus="javascript:ayudasHelp(7);" 
									onblur="javascript:ayudasHelpBlo(7);">
								</s:textfield>
								<s:hidden name="consultoras.costoAnticipo" 
								value="%{consultoras.costoAnticipo}"
								id="Anticipo"></s:hidden></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaAyuda" id="ayudasDisplay7" style="display:none;margin-top:0px;"
									value="Ingrese el monto autorizado para el pago por concepto de anticipo" /></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaCaptura"
									value="* Pago de abono 1 autorizado:" /></td>
						</tr>
						<tr>
							<td><s:textfield
									size="30"
									maxlength="9"
									id="costoAbono1"
									onkeypress="javascript:return validaNumero(event)"
									onkeyup ="javascript: addComa(event,'costoAbono1');"
									onfocus="javascript:ayudasHelp(8);" 
									onblur="javascript:ayudasHelpBlo(8);">
									</s:textfield> . 
									<s:textfield
									size="2"
									maxlength="2"
									id="costoAbono12"
									onkeypress="javascript:return validaCentavos(event)"
									onfocus="javascript:ayudasHelp(8);" 
									onblur="javascript:ayudasHelpBlo(8);">
									</s:textfield></td>
								<s:hidden name="consultoras.costoAbono1" 
								value="%{consultoras.costoAbono1}"
								id="Abono1"></s:hidden>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaAyuda" id="ayudasDisplay8" style="display:none;margin-top:0px;"
									value="Ingrese el monto autorizado para el pago por concepto de abono1." /></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaCaptura"
									value="* Pago de abono 2 autorizado:" /></td>
						</tr>
						<tr>
							<td><s:textfield
									size="30"
									maxlength="9"
									id="costoAbono2"
									onkeypress="javascript:return validaNumero(event)"
									onkeyup ="javascript: addComa(event,'costoAbono2');"
									onfocus="javascript:ayudasHelp(9);" 
									onblur="javascript:ayudasHelpBlo(9);">
									</s:textfield> . <s:textfield
									size="2"
									maxlength="2"
									id="costoAbono22"
									onkeypress="javascript:return validaCentavos(event)"
									onfocus="javascript:ayudasHelp(9);" 
									onblur="javascript:ayudasHelpBlo(9);">
									</s:textfield></td>
								<s:hidden name="consultoras.costoAbono2"
								value="%{consultoras.costoAbono2}"
								id="Abono2"></s:hidden>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaAyuda" id="ayudasDisplay9" style="display:none;margin-top:0px;"
									value="Ingrese el monto autorizado para el pago por concepto de abono2." /></td>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaCaptura"
									value="* Pago de finiquito autorizado:" /></td>
						</tr>
						<tr>
							<td><s:textfield
									size="30"
									maxlength="9"
									id="costoFiniquito"
									onkeypress="javascript:return validaNumero(event)"
									onkeyup ="javascript: addComa(event,'costoFiniquito');"
									onfocus="javascript:ayudasHelp(10);" 
									onblur="javascript:ayudasHelpBlo(10);">
									</s:textfield> . <s:textfield
									size="2"
									maxlength="2"
									id="costoFiniquito2"
									onkeypress="javascript:return validaCentavos(event)"
									onfocus="javascript:ayudasHelp(10);" 
									onblur="javascript:ayudasHelpBlo(10);">
									</s:textfield> </td>
								<s:hidden name="consultoras.costoFiniquito"
								value="%{consultoras.costoFiniquito}"
								id="Finiquito"></s:hidden>
						</tr>
						<tr>
							<td><s:label
									cssClass="etiquetaAyuda" id="ayudasDisplay10" style="display:none;margin-top:0px;"
									value="Ingrese el monto autorizado para el pago por concepto de finiquito." /></td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>&nbsp;</td></tr>
					</table>
				</td>		
			</tr>
			<tr>
				<td colspan="2" align="center"><s:submit
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
			res= numero.charAt(x+1)+"0";
		}
		else{
			res=numero.charAt(x+1)+numero.charAt(x+2)+res;
		}
		document.getElementById("costoAnticipo2").value=res;
		res="";
		var pi=x-1;
		var y=0;
		for(pi;pi>=0;pi--){
			if(y%3==0 && y>0){
				res=numero.charAt(pi)+","+res;
			}else{
				res=numero.charAt(pi)+res;
			}
			y=y+1;
		}
		document.getElementById("costoAnticipo").value=res;
		numero = document.getElementById("Abono1").value;
		var n=numero.toString();
		var x=numero.indexOf('.');
		var res="";
		if((x+2)==n.length){
			res= numero.charAt(x+1)+"0";
		}
		else{
			res=numero.charAt(x+1)+numero.charAt(x+2)+res;
		}
		document.getElementById("costoAbono12").value=res;
		res="";
		var pi=x-1;
		var y=0;
		for(pi;pi>=0;pi--){
			if(y%3==0 && y>0){
				res=numero.charAt(pi)+","+res;
			}else{
				res=numero.charAt(pi)+res;
			}
			y=y+1;
		}
		document.getElementById("costoAbono1").value=res;
		numero = document.getElementById("Abono2").value;
		var n=numero.toString();
		var x=numero.indexOf('.');
		var res="";
		if((x+2)==n.length){
			res= numero.charAt(x+1)+"0";
		}
		else{
			res=numero.charAt(x+1)+numero.charAt(x+2)+res;
		}
		document.getElementById("costoAbono22").value=res;
		res="";
		var pi=x-1;
		var y=0;
		for(pi;pi>=0;pi--){
			if(y%3==0 && y>0){
				res=numero.charAt(pi)+","+res;
			}else{
				res=numero.charAt(pi)+res;
			}
			y=y+1;
		}
		document.getElementById("costoAbono2").value=res;
		numero = document.getElementById("Finiquito").value;
		var n=numero.toString();
		var x=numero.indexOf('.');
		var res="";
		if((x+2)==n.length){
			res= numero.charAt(x+1)+"0";
		}
		else{
			res=numero.charAt(x+1)+numero.charAt(x+2)+res;
		}
		document.getElementById("costoFiniquito2").value=res;
		res="";
		var pi=x-1;
		var y=0;
		for(pi;pi>=0;pi--){
			if(y%3==0 && y>0){
				res=numero.charAt(pi)+","+res;
			}else{
				res=numero.charAt(pi)+res;
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
			valCentAnticipo = document.getElementById("costoAnticipo2").value;
			valCentAbono1 = document.getElementById("costoAbono12").value;
			valCentAbono2 = document.getElementById("costoAbono22").value;
			valCentFiniquito = document.getElementById("costoFiniquito2").value;
			if(valCentAnticipo==null || valCentAnticipo.trim().length==0){valCentAnticipo="00";}
			if(valCentAbono1==null || valCentAbono1.trim().length==0){valCentAbono1="00";}
			if(valCentAbono2==null || valCentAbono2.trim().length==0){valCentAbono2="00";}
			if(valCentFiniquito==null || valCentFiniquito.trim().length==0){valCentFiniquito="00";}
			if(valCentAnticipo.length==1){valCentAnticipo=valCentAnticipo+"0";}
			if(valCentAbono1.length==1){valCentAbono1=valCentAbono1+"0";}
			if(valCentAbono2.length==1){valCentAbono2=valCentAbono2+"0";}
			if(valCentFiniquito.length==1){valCentFiniquito=valCentFiniquito+"0";}
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
					|| ! /^(((\d{1,3},)(\d{3},)*\d{3})|(\d{1,3}))$/.test(valAnticipo)) {
				document.getElementById("costoAnticipo").focus();
				alert("Ingrese un valor númerico correcto a pago de anticipo autorizado.");
				return false;
			} else if (valCentAnticipo == null 
					|| !/^\d{2}$/.test(valCentAnticipo)) {
				document.getElementById("costoAnticipo2").focus();
				alert("Ingrese un valor númerico correcto a pago de anticipo autorizado..");
				return false;
			} else if (valAbono1 == null 
					|| ! /^(((\d{1,3},)(\d{3},)*\d{3})|(\d{1,3}))$/.test(valAbono1)) {
				document.getElementById("costoAbono1").focus();
				alert("Ingrese un valor númerico correcto a pago de abono1 autorizado.");
				return false;
			} else if (valCentAbono1 == null 
					|| ! /^\d{2}$/.test(valCentAbono1)) {
				document.getElementById("costoAbono12").focus();
				alert("Ingrese un valor númerico correcto a pago de abono1 autorizado.");
				return false;
			} else if (valAbono2 == null 
					|| ! /^(((\d{1,3},)(\d{3},)*\d{3})|(\d{1,3}))$/.test(valAbono2)) {
				document.getElementById("costoAbono2").focus();
				alert("Ingrese un valor númerico correcto a pago de abono2 autorizado.");
				return false;
			} else if (valCentAbono2 == null 
					|| ! /^\d{2}$/.test(valCentAbono2)) {
				document.getElementById("costoAbono22").focus();
				alert("Ingrese un valor númerico correcto a pago de abono2 autorizado.");
				return false;
			} else if (valCentFiniquito == null 
					|| ! /^(((\d{1,3},)(\d{3},)*\d{3})|(\d{1,3}))$/.test(valFiniquito)) {
				document.getElementById("costoFiniquito").focus();
				alert("Ingrese un valor númerico correcto a pago de finiquito autorizado.");
				return false;
			} else if (valCentFiniquito == null 
					|| ! /^\d{2}$/.test(valCentFiniquito)) {
				document.getElementById("costoFiniquito2").focus();
				alert("Ingrese un valor númerico correcto a pago de finiquito autorizado.");
				return false;
			}
			document.getElementById("Anticipo").value=replaceAll(valAnticipo,",","")+"."+valCentAnticipo;
			document.getElementById("Abono1").value=replaceAll(valAbono1,",","")+"."+valCentAbono1;
			document.getElementById("Abono2").value=replaceAll(valAbono2,",","")+"."+valCentAbono2;
			document.getElementById("Finiquito").value=replaceAll(valFiniquito,",","")+"."+valCentFiniquito;
			return true;
		}
		function replaceAll( text, busca, reemplaza ){
			  while (text.toString().indexOf(busca) != -1)
			      text = text.toString().replace(busca,reemplaza);
			  return text;
		}

		function validaNumero(e) {
			tecla = (document.all) ? e.keyCode : e.which;
			var regresa = false;
			switch (tecla){
			case 0: regresa = true; 
				break;
			case 8: regresa = true; 
			break;
			case 37: regresa = true; 
			break;
			case 39: regresa = true; 
			break;
			case 48: regresa = true; 
				break;
			case 49: regresa = true; 
				break;
			case 50: regresa = true; 
				break;
			case 51: regresa = true; 
				break;
			case 52: regresa = true; 
				break;
			case 53: regresa = true; 
				break;
			case 54: regresa = true; 
				break;
			case 55: regresa = true; 
				break;
			case 56: regresa = true; 
				break;
			case 57: regresa = true; 
			break;
			default: regresa = false;
			}
			if(regresa){
				return true;
			}			
			patron = /1/;
			te = String.fromCharCode(tecla);
			return patron.test(te);
		}
		function validaCentavos(e) {
			tecla = (document.all) ? e.keyCode : e.which;
			var regresa = false;
			switch (tecla){
			case 0: regresa = true; 
				break;
			case 8: regresa = true; 
			break;
			case 37: regresa = true; 
				break;
			case 39: regresa = true; 
			break;
			case 48: regresa = true; 
				break;
			case 49: regresa = true; 
				break;
			case 50: regresa = true; 
				break;
			case 51: regresa = true; 
				break;
			case 52: regresa = true; 
				break;
			case 53: regresa = true; 
				break;
			case 54: regresa = true; 
				break;
			case 55: regresa = true; 
				break;
			case 56: regresa = true; 
				break;
			case 57: regresa = true; 
			break;
			default: regresa = false;
			}
			if(regresa){
				return true;
			}else{
				patron = /1/;
				te = String.fromCharCode(tecla);
				return patron.test(te);
			}
		}
		function validaLetra(e) {
			tecla = (document.all) ? e.keyCode : e.which;
			if (tecla==0) return true;
			if (tecla==8) return true;
		    patron =/[A-ZñÑa-z\s]/;
		    te = String.fromCharCode(tecla);
		    return patron.test(te); 
		}
		
		function addComa(e,idd){
			tecla = (document.all) ? e.keyCode : e.which;
			if (tecla==0) return true;
			if (tecla==37) return true;
			if (tecla==39) return true;
			var i = document.getElementById(idd).value;
			
				if(i.length <= 8){
					i=replaceAll(i,",","");
					if(i!=null && i.length > 3 ){				
						temp1=i.substring(0,i.length-3);
						temp2=i.substring(i.length-3,i.length);
						i=temp1+","+temp2;
					}
					if(i!=null && i.length >7){
						temp1=i.substring(0,i.length-7);
						temp2=i.substring(i.length-7,i.length);
						i=temp1+","+temp2;
					}
					document.getElementById(idd).value=i;
				}
			
		}
	</script>
</body>
</html>