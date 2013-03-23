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
			<br />
			<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<br /><br />
		<s:label cssClass="etiquetaCaptura" value="* Seleccione Diplomado o Consultoria" />
		<select id="inscripcion" name="inscripcion" onchange="inscripcionOnChange(this)">
			<option selected="selected" value="Seleccione una opción">Seleccione una opción</option>
			<option value="Diplomado">Diplomado</option>
			<option value="Consultoría">Consultoría</option>
		</select>
		<br />
		<br />
			<div id="diplomado" style="display: none;">
				<div id="listDip">
					<table width="800px" cellspacing="1" cellpadding="1">
						<thead>
							<tr>
								<td class="encabezado_tabla" align="center"><b>Título Diplomado</b></td>
								<td class="encabezado_tabla" align="center"><b>Fecha</b></td>
								<td class="encabezado_tabla" align="center"><b>Seleccionar diplomado</b></td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="listServiciosDiplomado" status="stat">
								<tr>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${titulo}</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${fecha}</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										<a href="${pageContext.request.contextPath}/pyme/pymeServiciosShow.do?asistentes.idDiplomado=${idDiplomado}">Seleccionar</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<s:form name="frmDiplomado" action="pymeServiciosShow" namespace="/pyme" theme="simple" method="get">
					<table>
						<tr>
							<td>
								<br />
								<br />
								<s:label cssClass="etiquetaCaptura" value="Ingresar Asistentes" />
							</td>
						</tr>
						<tr>
							<td>
								<s:hidden id="idDiplomado" name="asistente.idDiplomado" value="%{idDiplomado}" />
								<table width="800px" cellspacing="1" cellpadding="1">
									<thead>
										<tr>
											<td class="encabezado_tabla" align="center"><b>Nombre</b></td>
											<td class="encabezado_tabla" align="center"><b>Apellido Paterno</b></td>
											<td class="encabezado_tabla" align="center"><b>Apellido Materno</b></td>
										</tr>
									</thead>
									<tbody>
										<tr id="asistente1">
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:textfield size="30" id="nombre" name="asistentes.nombre" value="%{asistente.nombre}" maxlength="60"></s:textfield>
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:textfield size="30" id="appPat" name="asistentes.appPaterno" value="%{asistente.appPaterno}" maxlength="60"></s:textfield>
											</td>
											<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<s:textfield size="30" id="appMat" name="asistentes.appMaterno" value="%{asistente.appMaterno}" maxlength="60"></s:textfield>
											</td>
										</tr>
										<s:div id="addForm">
										</s:div>
										<tr>
											<td colspan="3" class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
												<p style="cursor: pointer;" onclick="showForm();">Agregar otro asistente</p>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
					<s:submit cssClass="botonenviar" align="left" value="Confirmación Registro" />
					</s:form>
			</div>

			<div id="consultoria" style="display: none;">
					<table>
						<tr>
							<td colspan="3">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="* Tipo de consultoria" />
							</td>
						</tr>
						<tr>
							<td style="width: 120px;">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="40 Horas" />
								<s:checkbox id="check40" name="serviciosConsultoria.bConsultoriaCuarenta" onclick="checarCheck();" value="%{serviciosConsultoria.bConsultoriaCuarenta}" />
							</td>
							<td style="width: 120px;">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="60 Horas" />
								<s:checkbox id="check60" name="serviciosConsultoria.bConsultoriaSesenta" onclick="checarCheck();" value="%{serviciosConsultoria.bConsultoriaSesenta}" />
							</td>
							<td style="width: 120px;">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="80 Horas" />
								<s:checkbox id="check80" name="serviciosConsultoria.bConsultoriaOchenta" onclick="checarCheck();" value="%{serviciosConsultoria.bConsultoriaOchenta}" />
							</td>
						</tr>
					</table>
					<input class="botonenviar" value="Confirmación Registro" type="button"
							onclick="consultoria();" />
			</div>
	</fieldset>

<!-- formularios de envio Div1 -->
<!-- <s:form
	name="frmDiplomado"
	action="pymeServiciosShow"
	namespace="/pyme"
	theme="simple"
	method="get">
	<s:hidden
		id="idFrmDipId"
		name="asistentes.idDiplomado"
		value="" />
	<s:hidden
		id="idFrmDipNom"
		name="asistentes.nombre"
		value="" />
	<s:hidden
		id="idFrmDipApp"
		name="asistentes.appPaterno"
		value="" />
	<s:hidden
		id="idFrmDipApm"
		name="asistentes.appMaterno"
		value="" />
</s:form> -->

<!-- formularios de envio Div2 -->
<s:form
	name="frmConsultoria"
	action="pymeServiciosSave"
	namespace="/pyme"
	theme="simple"
	method="get">
	<s:hidden
		id="idFrmCon40"
		name="serviciosConsultoria.bConsultoriaCuarenta"
		value="" />
	<s:hidden
		id="idFrmCon60"
		name="serviciosConsultoria.bConsultoriaSesenta"
		value="" />
	<s:hidden
		id="idFrmCon80"
		name="serviciosConsultoria.bConsultoriaOchenta"
		value="" />
</s:form>
	
<script type="text/javascript">

function verAsistente() {
	
	 divAsist = document.getElementById("showAsistente");
     divAsist.style.display = "";
     
     divListDip = document.getElementById("listDip");
     divListDip.style.display = "none";
     
}


/*function diplomado() {
	
	document.getElementById('idFrmDipId').value = document.getElementById('idDiplomado').value;
	document.getElementById('idFrmDipNom').value = document.getElementById('idCampoNombreAsistente').value;
	document.getElementById('idFrmDipApp').value = document.getElementById('idCampoAppAsistente').value;
	document.getElementById('idFrmDipApm').value = document.getElementById('idCampoApmAsistente').value;
	
	document.frmDiplomado.submit();
}*/

function consultoria() {
	document.getElementById('idFrmCon40').value = document.getElementById('check40').checked;
	document.getElementById('idFrmCon60').value = document.getElementById('check60').checked;
	document.getElementById('idFrmCon80').value = document.getElementById('check80').checked;

	document.frmConsultoria.submit();
}

function inscripcionOnChange(sel) {
	if(sel.value == "Seleccione una opci�n"){
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


function  checarCheck(){
	
	diplomado = document.getElementById("diplomado");
	for(var i = 0; i < diplomado.elements.length; i++) {
	  var elemento = diplomado.elements[i];
	  if(elemento.type == "checkbox") {
	    if(!elemento.checked || elemento.ckeked > 1 ) {
	    	alert('Seleccione un solo dipomado');
	      return false;
	    }
	  }
	}
	
	cuarenta = document.getElementById("check40");
	sesenta = document.getElementById("check60");
	ochenta = document.getElementById("check80");
	
	if( cuarenta.checked ) {
		sesenta.checked = false;
		ochenta.checked = false;
	}
	if ( sesenta.checked ){
		cuarenta.checked = false;
		ochenta.checked = false;
	}
	if ( ochenta.checked ){
		cuarenta.checked = false;
		sesenta.checked = false;
	}
}

</script>
</body>
</html>
