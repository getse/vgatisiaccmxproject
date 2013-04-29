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
			<s:label value="Inscripción a Diplomados/consultorías" />
			<br /> <br />
			<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<div ${tituloDiplomado==null?' style="display: block;"':' style="display: none;"'}>
			<br /><br />
			<s:label cssClass="etiquetaCaptura" value="* Seleccione Diplomado o Consultoria" />
			<select id="inscripcion" name="inscripcion" onchange="inscripcionOnChange(this)">
				<option selected="selected" value="Seleccione una opción">Seleccione una opción</option>
				<option value="Diplomado">Diplomado</option>
				<option value="Consultoría">Consultoría</option>
			</select>
			<br />
			<br />
		</div>
			<div id="diplomado" style="display: none;">
				<div id="listDip">
					<table width="800px" cellspacing="1" cellpadding="1">
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
						<div>
							<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="Incluir archivo de pago" />
							<br />
							<s:file id="aPagoDip" name="serviciosDiplomado.archivo1" />
						</div>
						<div>
							<s:label cssClass="etiquetaAyuda" value="Indique el archivo que será incluido. Máximo 2MB (.pdf .doc .png)" />
							<br />
						</div>
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
					<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="Incluir archivo de pago" />
					<br />
					
					<div>
						<s:file id="aPagoConsult" name="serviciosConsultoria.archivo1" />
					</div>
					
					<div>
						<s:label cssClass="etiquetaAyuda" value="Indique el archivo que será incluido. Máximo 2MB (.pdf .doc .png)" />
					</div>
					
					<br />

					<s:hidden name="serviciosConsultoria.mensaje" id="msjConsult" value="Estimada PYME, en breve un consultor se pondrá en contacto con ustedes, a nombre del CCMX" />					
					
					<input class="botonenviar" value="Confirmación Registro" type="button" onclick="consultoria(); " />
				</s:form>
			</div>
	</fieldset>
	
<script type="text/javascript">

function inscripcionOnChange(sel) {
	if(sel.value == "Seleccione una opción"){
		 valDiplomado = document.getElementById("diplomado");
         valDiplomado.style.display = "none";

         valConsultoria = document.getElementById("consultoria");
         valConsultoria.style.display = "none";
	}else if(sel.value=="Diplomado"){
         valDiplomado = document.getElementById("diplomado");
         valDiplomado.style.display = "";

         valConsultoria = document.getElementById("consultoria");
         valConsultoria.style.display = "none";
    }else{
    	 valDiplomado = document.getElementById("diplomado");
         valDiplomado.style.display="none";

         valConsultoria = document.getElementById("consultoria");
         valConsultoria.style.display = "";
    }
}

function verAsistente() {
	
	divAsist = document.getElementById("showAsistente");
    divAsist.style.display = "";
    
    divListDip = document.getElementById("listDip");
    divListDip.style.display = "none";
    
}

var secuencia=2;
function Asistente(){
	
	 var tr = document.createElement('tr');
	 tr.id = 'asistente' + secuencia;
	 
	 var td1 = document.createElement('td');
	 td1.setAttribute('class', "${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}");
	 td1.setAttribute('align', 'center');
	 
	 var td2 = document.createElement('td');
	 td2.setAttribute('class', "${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}");
	 td2.setAttribute('align', 'center');
	 
	 var td3 = document.createElement('td');
	 td3.setAttribute('class', "${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}");
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
	 secuencia ++;
	 
	 asistente = document.getElementById("addAsistente");
	 td1.appendChild(txNom);
	 td2.appendChild(txPat);
	 td3.appendChild(txMat);
	 tr.appendChild(td1);
	 tr.appendChild(td2);
	 tr.appendChild(td3);
	 asistente.appendChild(tr);
	
}

function validaAsistentesDip(){

	for(var i = 1; i < secuencia; i++ ){
		if( document.getElementById('nombre' + i).value == null || document.getElementById('nombre' + i).value.length == 0 || /^\s+$/.test(document.getElementById('nombre' + i).value) ) {
			document.getElementById("nombre" + i).focus();
			alert("Ingrese el nombre del asistente");  
			return false;
		}else if( document.getElementById('appPat' + i).value == null || document.getElementById('appPat' + i).value.length == 0 || /^\s+$/.test(document.getElementById('appPat' + i).value) ) {
			document.getElementById("appPat" + i).focus();
			alert("Ingrese el apellido materno del asistente");  
			return false;
		}else if( document.getElementById('appMat' + i).value == null || document.getElementById('appMat' + i).value.length == 0 || /^\s+$/.test(document.getElementById('appMat' + i).value) ) {
			document.getElementById("appMat" + i).focus();
			alert("Ingrese el apellido materno del asistente");  
			return false;
		}
	}
	
	return true;
}

veinte = document.getElementById("check20");
cuarenta = document.getElementById("check40");
sesenta = document.getElementById("check60");
ochenta = document.getElementById("check80");

function  veinteCheck(){
	if( veinte.checked ){
		cuarenta.disabled = true;
		sesenta.disabled = true;
		ochenta.disabled = true;
	}else{
		cuarenta.disabled = false;
		sesenta.disabled = false;
		ochenta.disabled = false;
	}
}

function cuarentaCheck(){
	if( cuarenta.checked ) {
		veinte.disabled = true;
		sesenta.disabled = true;
		ochenta.disabled = true;
	}else{
		veinte.disabled = false;
		sesenta.disabled = false;
		ochenta.disabled = false;
	}	
}

function sesentaCheck(){
	if ( sesenta.checked ){
		veinte.disabled = true;
		cuarenta.disabled = true;
		ochenta.disabled = true;
	}else{
		veinte.disabled = false;
		cuarenta.disabled = false;
		ochenta.disabled = false;
	}
}

function ochentaCheck(){
	if ( ochenta.checked ){
		veinte.disabled = true;
		cuarenta.disabled = true;
		sesenta.disabled = true;
	}else{
		veinte.disabled = false;
		cuarenta.disabled = false;
		sesenta.disabled = false;
	}
}
	
function consultoria() {
	
	if( veinte.checked ||cuarenta.checked || sesenta.checked || ochenta.checked ){
		document.frmConsultoria.submit();
		return true;
	}else{
		alert("Selecione un tipo de consultoria.");
		return false;
	}
}

</script>
</body>
</html>
