<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script
	type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>

</head>

<body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ayudas.js"></script>
<script type="text/javascript">
	document.getElementById('workingContainer').style.margin = '-195px auto 0 250px';
</script>
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
		<legend>
			<s:label value="Inscripción a diplomados y consultorías" />
			<br /> <br />
			<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<div ${tituloDiplomado==null?' style="display: block;"':' style="display: none;"'}>
			<br />
			<s:label cssClass="etiquetaCaptura" value="* Seleccione la opción 'Diplomados' o 'Consultorias' según requiera." />
			<br /><br />
			<table class="submit_tabla" style="width: 40%;">
				<tr>
					<td><input class="botonenviar" value="Diplomados" type="button" onclick="selectDiplomados(); " /></td>
					<td><input class="botonenviar" value="Consultorías" type="button" onclick="selectConsultorias(); " /></td>
				</tr>
			</table>
			<br />
		</div>
			<div id="diplomado" style="display: none;">
				<div id="listDip">
					<table width="99%" cellspacing="1" cellpadding="1">
						<thead>
							<tr>
								<td class="encabezado_tabla" align="center"><b><s:text name="No." /></b></td>
								<td class="encabezado_tabla" align="center"><b><s:text name="Título" /></b></td>
								<td class="encabezado_tabla" align="center"><b><s:text name="Generación" /></b></td>
								<td class="encabezado_tabla" align="center"><b><s:text name="Ubicación" /></b></td>
								<td class="encabezado_tabla" align="center"><b><s:text name="Fecha" /></b></td>
								<td class="encabezado_tabla" align="center"><b><s:text name="Información" /></b></td>
								<td class="encabezado_tabla" align="center"><b><s:text name="Seleccionar diplomado" /></b></td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="listDiplomados" status="stat">
								<tr>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										${stat.count}
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										${tema}
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										${generacion}
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										${ubicacion}
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										${fecha}
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										<a href="${url}">Ver información</a>
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										<a href="${pageContext.request.contextPath}/pyme/pymeServiciosShow.do?idDiplomado=${idDiplomado}&tituloDiplomado=${tema}&fechaDip=${fecha}">Seleccionar</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
			<s:if test="idDiplomado != 0">
			<div ${tituloDiplomado==null?' style="display: none;"':' style="display: block;"'}>
				<table>
					<tr>
						<td>
							<br />
							<s:label cssClass="etiquetaCaptura" value="Diplomado seleccionado" />
						</td>
					</tr>
					<tr>
						<td>
							<s:label cssClass="etiquetaAyuda" value="%{tituloDiplomado}" />
						</td>
					</tr>
					<tr>
						<td>
							<br />
							<br />
							<s:label cssClass="etiquetaCaptura" value="* Registrar Asistentes" />
						</td>
					</tr>
				</table>
				<div id="frmAsistente">
					<s:form name="frmDiplomado" action="pymeServiciosShow" namespace="/pyme" enctype="multipart/form-data" theme="simple" method="post" onsubmit="javascript: return validaAsistentesDip();">
						<s:hidden name="fechaDip" value="%{fechaDip}" />
						<s:hidden name="idDiplomado" value="%{idDiplomado}" />
						<s:hidden name="serviciosDiplomado.idDiplomado" value="%{idDiplomado}" />
						<s:hidden name="serviciosDiplomado.titulo" value="%{tituloDiplomado}" />
						<s:hidden name="serviciosDiplomado.mensaje" value="Servicio registrado correctamente" />
						<table width="800px" cellspacing="1" cellpadding="1">
							<thead>
								<tr>
									<td class="encabezado_tabla" align="center"><b>Nombre</b></td>
									<td class="encabezado_tabla" align="center"><b>Apellido Paterno</b></td>
									<td class="encabezado_tabla" align="center"><b>Apellido Materno</b></td>
								</tr>
							</thead>
							<tbody id="addAsistente">
								
								<tr id="asistente1">
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										<s:textfield size="30" id="nombre1" name="nombresAsistentes" value="" maxlength="60"></s:textfield>
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										<s:textfield size="30" id="appPat1" name="appPatAsistentes" value="" maxlength="60"></s:textfield>
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										<s:textfield size="30" id="appMat1" name="appMatAsistentes" value="" maxlength="60"></s:textfield>
									</td>
								</tr>
							</tbody>
						</table>
						<label id="addAsist" class="agregar" onclick="javascript:Asistente();">+agregar otro asistente</label>
						<br />
						<br />
						<table style="width: 700px;">
							<tr>
								<td>
									<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value='"Estimada PYME, le recordamos que si ya realizó el pago correspondiente a los diplomados que acaba de inscribir, puede adjuntarlo en esta sección”.' />
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="Comprobante sde pago de Diplomado" />
								</td>
							</tr>
							<tr>
								<td>
									<s:file id="aPagoDip" name="serviciosDiplomado.archivo1" 
									onclick="javascript:ayudasHelp(2);"/>
								</td>
							</tr>
							<tr>
								<td>
									<s:label cssClass="etiquetaAyuda" id="ayudasDisplay2" style="display:none; margin-top:5px;"
									value="Indique el archivo que será incluido. Máximo 2MB (.pdf .doc .png)" />
								</td>
							</tr>
						</table>
						<br />
						<s:submit cssClass="botonenviar" align="left" value="Confirmación Registro" />
					</s:form>
				</div>
			</div>
			</s:if>

			<div id="consultoria" style="display: none;">
			<s:form name="frmConsultoria" action="pymeServiciosSave" namespace="/pyme" enctype="multipart/form-data" theme="simple" method="post">
					<table>
						<tr>
							<td colspan="3">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="* Tipo de consultoria" />
							</td>
						</tr>
						<tr>
							<td style="width: 120px;">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="20 Horas" />
								<s:checkbox id="check20" name="serviciosConsultoria.bConsultoriaVeinte" value="%{serviciosConsultoria.bConsultoriaVeinte}" onclick="javascript:veinteCheck();"/>
							</td>
							<td style="width: 120px;">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="40 Horas" />
								<s:checkbox id="check40" name="serviciosConsultoria.bConsultoriaCuarenta" value="%{serviciosConsultoria.bConsultoriaCuarenta}" onclick="javascript:cuarentaCheck();" />
							</td>
							<td style="width: 120px;">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="60 Horas" />
								<s:checkbox id="check60" name="serviciosConsultoria.bConsultoriaSesenta" value="%{serviciosConsultoria.bConsultoriaSesenta}" onclick="javascript:sesentaCheck();"/>
							</td>
							<td style="width: 120px;">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="80 Horas" />
								<s:checkbox id="check80" name="serviciosConsultoria.bConsultoriaOchenta" value="%{serviciosConsultoria.bConsultoriaOchenta}" onclick="javascript:ochentaCheck();" />
							</td>
						</tr>
					</table>
					
					<br />
					<table id="showArchPago" style="width: 700px; display: none;">
						<tr>
							<td>
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value='"Estimada PYME, le recordamos que si ya realizó el pago correspondiente a la consultoría de 20, 40, 60 u 80 horas que acaba de solicitar, puede adjuntarlo en esta sección".' />
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="Comprobante de pago de consultoría:" />
							</td>
						</tr>
						<tr>
							<td>
								<s:file id="aPagoConsult" name="serviciosConsultoria.archivo1" 
									onclick="javascript:ayudasHelp(2);"/>
							</td>
						</tr>
						<tr>
							<td>
								<s:label cssClass="etiquetaAyuda"  id="ayudasDisplay2" style="display:none; margin-top:5px;"
									value="Indique el archivo que será incluido. Máximo 2MB (.pdf .doc .png)" />
							</td>
						</tr>
					</table>
					<br />
					<s:hidden name="serviciosConsultoria.mensaje" id="msjConsult" value="Estimada PYME, en breve un consultor se pondrá en contacto con ustedes, a nombre del CCMX" />
					<input class="botonenviar" value="Confirmación Registro" type="button" onclick="consultoria(); " />
				</s:form>
			</div>
	</fieldset>
	
<script type="text/javascript">
	var peticion = false;
try {
	peticion = new XMLHttpRequest();
} catch (e) {
	try {
		peticion = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (E) {
		try {
			peticion = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (failed) {
			peticion = false;
		}
	}
}
if (!peticion) {
	alert("ERROR AL INICIALIZAR!");
}

function showDetalles(id, generacion, titulo) {
	var combo = document.getElementById('ubicacion');
	var contFecha = document.getElementById('idFecha');
	
	//$("#ubicacion").html('<option selected="selected" value="0">Cargando...</option>');
	combo.disabled = true;
	
	var url = 'http://localhost:8080/vinculacion/pyme/pymeServiciosShow.do?generacion='+generacion+'&tituloDiplomado='+titulo+'&idDiplomado='+id;
	
	peticion.open("GET", url, true);
	peticion.onreadystatechange = function() {
		if (peticion.readyState == 4 && peticion.status == 200) {
			var cont = peticion.responseText; 
			var divideCont = cont.split('\<');
			for ( var i = 1; i < divideCont.length; i++) {
				var ar = divideCont[i];
				if (ar.substring(0, 8) == 'textarea') {
					var inicioCadena = ar.indexOf('>') + 1;
					var finCadena = ar.length;
					contFecha.innerHTML = ar.substring(inicioCadena, finCadena);
				}
			}
			combo.disabled = false;
			document.getElementById('fechaDip').style.display = 'block';
		}
	};
		peticion.send(null);
}


function selectDiplomados() {
	document.getElementById("diplomado").style.display = 'block';
	document.getElementById("consultoria").style.display = 'none';
}

function selectConsultorias() {
	document.getElementById("diplomado").style.display = 'none';
	document.getElementById("consultoria").style.display = 'block';
}

/*function verAsistente() {

	divAsist = document.getElementById("showAsistente");
	divAsist.style.display = "";

	divListDip = document.getElementById("listDip");
	divListDip.style.display = "none";

}*/

var secuencia = 2;
function asistente() {

	var tr = document.createElement('tr');
	tr.id = 'asistente' + secuencia;

	var td1 = document.createElement('td');
	td1.setAttribute('class', "cuerpo2TablaResumen");
	td1.setAttribute('align', 'center');

	var td2 = document.createElement('td');
	td2.setAttribute('class', "cuerpo2TablaResumen");
	td2.setAttribute('align', 'center');

	var td3 = document.createElement('td');
	td3 .setAttribute('class', "cuerpo2TablaResumen");
	td3.setAttribute('align', 'center');

	var txNom = document.createElement('input');
	txNom.setAttribute('type', 'text');
	txNom.setAttribute('size', '30');
	txNom.setAttribute('name', 'nombresAsistentes');
	txNom.setAttribute('value', '');
	txNom.setAttribute('maxlength', '60');
	txNom.id = 'nombre' + secuencia;

	var txPat = document.createElement('input');
	txPat.setAttribute('type', 'text');
	txPat.setAttribute('size', '30');
	txPat.setAttribute('name', 'appPatAsistentes');
	txPat.setAttribute('value', '');
	txPat.setAttribute('maxlength', '60');
	txPat.id = 'appPat' + secuencia;

	var txMat = document.createElement('input');
	txMat.setAttribute('type', 'text');
	txMat.setAttribute('size', '30');
	txMat.setAttribute('name', 'appMatAsistentes');
	txMat.setAttribute('value', '');
	txMat.setAttribute('maxlength', '60');
	txMat.id = 'appMat' + secuencia;
	secuencia++;

	asistente = document.getElementById("addAsistente");
	td1.appendChild(txNom);
	td2.appendChild(txPat);
	td3.appendChild(txMat);
	tr.appendChild(td1);
	tr.appendChild(td2);
	tr.appendChild(td3);
	asistente.appendChild(tr);

}

function validaAsistentesDip() {
	for ( var i = 1; i < secuencia; i++) {
		if (document.getElementById('nombre' + i).value == null || document.getElementById('nombre' + i).value.length == 0 || /^\s+$/.test(document.getElementById('nombre' + i).value)) {
			document.getElementById("nombre" + i).focus();
			alert("Ingrese el nombre del asistente");
			return false;
		} else if (document.getElementById('appPat' + i).value == null || document.getElementById('appPat' + i).value.length == 0 || /^\s+$/.test(document.getElementById('appPat' + i).value)) {
			document.getElementById("appPat" + i).focus();
			alert("Ingrese el apellido materno del asistente");
			return false;
		} else if (document.getElementById('appMat' + i).value == null || document.getElementById('appMat' + i).value.length == 0 || /^\s+$/.test(document.getElementById('appMat' + i).value)) {
			document.getElementById("appMat" + i).focus();
			alert("Ingrese el apellido materno del asistente");
			return false;
		}
	}
	return true;
}


/*CONSULTORIAS*/

function veinteCheck() {
	if (document.getElementById("check20").checked) {
		document.getElementById("check40").checked = false;
		document.getElementById("check60").checked = false;
		document.getElementById("check80").checked = false;
		document.getElementById('showArchPago').style.display = 'none';
	}
}

function cuarentaCheck() {
	if (document.getElementById("check40").checked) {
		document.getElementById("check20").checked = false;
		document.getElementById("check60").checked = false;
		document.getElementById("check80").checked = false;
		document.getElementById('showArchPago').style.display = 'block';
	}else{
		document.getElementById('showArchPago').style.display = 'none';
	}
}

function sesentaCheck() {
	if (document.getElementById("check60").checked) {
		document.getElementById("check20").checked = false;
		document.getElementById("check40").checked = false;
		document.getElementById("check80").checked = false;
		document.getElementById('showArchPago').style.display = 'block';
	}else{
		document.getElementById('showArchPago').style.display = 'none';
	}
}

function ochentaCheck() {
	if (document.getElementById("check80").checked) {
		document.getElementById("check20").checked = false;
		document.getElementById("check40").checked = false;
		document.getElementById("check60").checked = false;
		document.getElementById('showArchPago').style.display = 'block';
	}else{
		document.getElementById('showArchPago').style.display = 'none';
	}
}

function consultoria() {

	if (document.getElementById("check20").checked || document.getElementById("check40").checked 
			|| document.getElementById("check60").checked || document.getElementById("check80").checked) {
		document.frmConsultoria.submit();
		return true;
	} else {
		alert("Selecione un tipo de consultoria.");
		return false;
	}
}
</script>
</body>
</html>
