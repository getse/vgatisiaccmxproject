<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
document.getElementById('workingContainer').style.margin = '-150px auto 0 250px';
</script>
<script
	type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
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
<div ${idFactura==null ?' style="display: block;"':' style="display: none;"'}>
	<fieldset id="requerimientos">
		<legend>
			<s:label value="Facturación" />
			<br /> <br />
			<s:label cssClass="camposObligatorios"
				value="Seleccione 'Filtro de PYMES'. Active la factura solicitada y seleccione 'Registrar Factura(s)'" />
		</legend>
		<br/>
		<s:form
			id="addFact"
			name="addFact"
			action="addFacturacionShow"
			namespace="/consultor/administracion"
			theme="simple">
			
		</s:form>
		<s:form
			id="form"
			name="form"
			action="consultoraFacturacionShow"
			namespace="/consultor/administracion"
			theme="simple">
		<div style="size: 100%">
		<table width="100%">
			<tr ></tr><tr><td>&nbsp;</td></tr>
			<tr>
				<td style="width: 33%">&nbsp;</td>
				<td style="width: 20%">&nbsp;</td>
				<td class="encabezado_tabla" align="left"><b>Filtro de PYMES</b></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><input type="checkbox" name="opcion" value="null" onchange="javascript:enviar();">Todas</input>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><input type="checkbox" name="opcion" value="1" onchange="javascript:enviar();">En diagnóstico, plan de mejora, implementación o evaluación</input>
				</td>
			</tr>
			<tr>
				<td >&nbsp;</td>
				<td>&nbsp;</td>
				<td><input type="checkbox" name="opcion" value="2" onchange="javascript:enviar();"	>Concluidas</input>
				</td>
			</tr>
		</table>
		</div>
		</s:form>
		<br/>
		<s:form
			id="send"
			name="send"
			action="consultoraFacturacionShow"
			namespace="/consultor/administracion"
			theme="simple"
			onsubmit="javascript:return validaSend();">
		<table>		
			<tr>
				<td>
					<select name="idFactura" id="idFactura">
						<option value="-1" selected="selected">--Seleccionar--</option>
						<s:iterator value="facturasList" status="stat">
							<option value="${idFactura}">${idFactura}</option>
						</s:iterator>
					</select></td>
				<td colspan="2"><s:submit
					cssClass="botonenviar"
					value="Registrar Factura(s)." /></td>
				<td>
					<input class="botonenviar" type="button" value="Crear nueva Factura" 
						onclick="javascript:document.addFact.submit();"/></td>
			</tr>
		</table>
		<table>
				<tr>
					<td>
						<table
							width="920px"
							cellspacing="1"
							cellpadding="1">
							<thead>
								<tr>
									<td class="encabezado_tabla" align="center"><b>No.</b></td>
									<td class="encabezado_tabla" align="center"><b>PYME Nombre comercial</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Consultor</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Estatus Consultoria</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Factura Anticipo</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Factura Abono 1</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Factura Abono 2</b>
									</td>
									<td class="encabezado_tabla" align="center"><b>Factura Finiquito</b>
									</td>
								</tr>
							</thead>
							<tbody>
							<s:set var="contador" value="0" />
								<s:iterator value="pagosList" status="stat">
									<tr>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${stat.count}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${nombreComercial}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${nombreContacto}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">${estatus}</td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">
											<s:if test="%{anticipo==null}">
												<input type="checkbox" id="a${stat.count}" name="anticipoList" value="${idServicios}" onchange="javascript:valida(1,${stat.count});"></input></s:if>
											<s:else>${anticipo}</s:else></td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">
											<s:if test="%{abono1==null}">
												<input type="checkbox" id="b${stat.count}" name="abono1List" value="${idServicios}" onchange="javascript:valida(2,${stat.count});"></input></s:if>
											<s:else>${abono1}</s:else></td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">
											<s:if test="%{abono2==null}">
												<input type="checkbox" id="c${stat.count}" name="abono2List" value="${idServicios}" onchange="javascript:valida(3,${stat.count});"></input></s:if>
											<s:else>${abono2}</s:else></td>
										<td
											class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}"
											align="center">
											<s:if test="%{finiquito==null}">
												<input type="checkbox" id="d${stat.count}" name="finiquitoList" value="${idServicios}" onchange="javascript:valida(4,${stat.count});"></input></s:if>
											<s:else>${finiquito}</s:else></td>	
																
									</tr>
								</s:iterator>
							</tbody>
						</table></td>
				</tr>
			</table>
			</s:form>
	</fieldset>
</div>
<div ${idFactura!=null ?' style="display: block;"':' style="display: none;"'}>
	<fieldset id="requerimientos">
		<legend>
			<s:label value="Agregar factura" />
			<br />
		</legend>
		<br/>
		<s:label cssClass="camposObligatorios"
				value="Ingrese el número de factura(Sin espacios)." />
		<s:form
			id="factura"
			name="factura"
			action="consultoraFacturacionShow"
			namespace="/consultor/administracion"
			theme="simple"
			onsubmit="javascript:return validafactura();">
			<br /><br />
			<table>
				<tr>
					<td>Ingrese número de factura: </td>				
					<td><s:textfield name ="idFactura" id="addFactura" value=""></s:textfield></td>
				</tr>
			</table>
			<br />
			<table>
			<tr>
					<td colspan="2"><s:submit
							cssClass="botonenviar"
							value="Regstrar de factura" /></td>
			</tr>
			</table>
			
		</s:form>
	</fieldset>
</div>
<script type="text/javascript">
	
	function valida(s,id){
		if(s==1){
			document.getElementById("b"+id+"").checked=false;
			document.getElementById("c"+id+"").checked=false;
			document.getElementById("d"+id+"").checked=false;
		}
		if(s==2){
			document.getElementById("a"+id+"").checked=false;
			document.getElementById("c"+id+"").checked=false;
			document.getElementById("d"+id+"").checked=false;
			
		}
		if(s==3){
			document.getElementById("b"+id+"").checked=false;
			document.getElementById("a"+id+"").checked=false;
			document.getElementById("d"+id+"").checked=false;
			
		}
		if(s==4){
			document.getElementById("b"+id+"").checked=false;
			document.getElementById("c"+id+"").checked=false;
			document.getElementById("a"+id+"").checked=false;
			
		}
	}
	function enviar(){
		document.form.submit();
	}
	function validaSend(){
		valSelect = document.getElementById("idFactura").value;
		if(valSelect=="-1"){
			document.getElementById("idFactura").focus();
			alert("Seleccione factura deseada.");
			return false;
		}
		formulario = document.getElementById("send");
		for(var i=0; i<formulario.elements.length; i++) {
			var elemento = formulario.elements[i];
			if(elemento.type == "checkbox") {
			   if(elemento.checked) {
				   if(confirm("Se enviara un correo electrónico al coordinador de \n"
						   +"consultorias con las modificaciones realizadas\n\n"
						   +"¿Desea continuar?")){
					   return true;
				   }else{
					   return false;
				   }
				   
			   }
			 }
		}
		alert("No se ha seleccionado ninguna factura ha insertar.");
		return false;
	}
	function validafactura(){
		factura = document.getElementById("addFactura").value;
		if(factura!=null && factura.trim()==""){
			document.getElementById("addFactura").focus();
			alert("Ingrese el número de Factura.");
			return false;
		}
		if(confirm("Se dara de alta la factura correspondente a su usuario\n "
				+factura+"\n\n"+
				"¿Desea continuar?")){
			return true;
		}
		else{
			return false;
		}
		
	}
</script>
</body>
</html>