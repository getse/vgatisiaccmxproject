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
			<br />
			<s:label cssClass="camposObligatorios"
				value="Los campos marcados con asterisco(*) son de caracter obligatorio." />
		</legend>
		<br /><br />
		<s:if test="idDiplomado == 0">
			<s:label cssClass="etiquetaCaptura" value="* Seleccione Diplomado o Consultoria" />
			<select id="inscripcion" name="inscripcion" onchange="inscripcionOnChange(this)">
				<option selected="selected" value="Seleccione una opción">Seleccione una opción</option>
				<option value="Diplomado">Diplomado</option>
				<option value="Consultoría">Consultoría</option>
			</select>
			<br />
			<br />
		</s:if>
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
										<a href="${pageContext.request.contextPath}/pyme/pymeServiciosShow.do?idDiplomado=${idDiplomado}&tituloDiplomado=${titulo}">Seleccionar</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
			<s:if test="idDiplomado != 0">
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
					<s:form name="frmDiplomado" action="pymeServiciosShow" namespace="/pyme" theme="simple" >
						<s:hidden id="idDiplomado" name="asistentes.idDiplomado" value="%{idDiplomado}" />
						<s:hidden id="idDiplomado" name="asistentes.nombre" value="%{asistente.nombre}" />
						<s:hidden id="idDiplomado" name="asistentes.appPaterno" value="%{asistente.appPaterno}" />
						<s:hidden id="idDiplomado" name="asistentes.appMaterno" value="%{asistente.appMaterno}" />
						<s:hidden id="idDiplomado" name="idDiplomado" value="%{idDiplomado}" />
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
										<s:textfield size="30" id="nombre" name="nombresAsistentes" value="" maxlength="60"></s:textfield>
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										<s:textfield size="30" id="appPat" name="appPatAsistentes" value="" maxlength="60"></s:textfield>
									</td>
									<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">
										<s:textfield size="30" id="appMat" name="appMatAsistentes" value="" maxlength="60"></s:textfield>
									</td>
								</tr>

							</tbody>
						</table>
						<input class="botonenviar" value="Agregar otro asistente" type="button" onclick="Asistente()" />
						<br />
						<br />
						<s:submit cssClass="botonenviar" align="left" value="Confirmación Registro" />
					</s:form>
				</div>
			</s:if>

			<div id="consultoria" style="display: none;">
			<s:form name="frmConsultoria" action="pymeServiciosSave" namespace="/pyme" theme="simple" method="get" onsubmit="return validar()">
					<table>
						<tr>
							<td colspan="3">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="* Tipo de consultoria" />
							</td>
						</tr>
						<tr>
							<td style="width: 120px;">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="40 Horas" />
								<s:checkbox id="check40" name="serviciosConsultoria.bConsultoriaCuarenta" onclick="checarCheck();" />
							</td>
							<td style="width: 120px;">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="60 Horas" />
								<s:checkbox id="check60" name="serviciosConsultoria.bConsultoriaSesenta" onclick="checarCheck();" />
							</td>
							<td style="width: 120px;">
								<s:label cssClass="etiquetaCaptura" cssStyle="align: left;" value="80 Horas" />
								<s:checkbox id="check80" name="serviciosConsultoria.bConsultoriaOchenta" onclick="checarCheck();" />
							</td>
						</tr>
					</table>
					<input class="botonenviar" value="Confirmación Registro" type="button"
							onclick="consultoria();" />
				</s:form>
			</div>
	</fieldset>
	
<script type="text/javascript">

function verAsistente() {
	
	 divAsist = document.getElementById("showAsistente");
     divAsist.style.display = "";
     
     divListDip = document.getElementById("listDip");
     divListDip.style.display = "none";
     
}

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





var secuencia=1;
function Asistente(){
  var asistente = document.getElementById("asistente1");
  var add = asistente.cloneNode(true);
  add.style.id = 'asistente' + secuencia;
  secuencia++;
  asistente = document.getElementById("addAsistente");
  asistente.appendChild(add);
}


	function  checarCheck(){
	
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
	
	function consultoria() {
		if(document.getElementById('check40').checked){
			document.getElementById('check40').value = true;
		}else if (document.getElementById('check60').checked){
			document.getElementById('check60').value = true;	
		}else if (document.getElementById('check80').checked){
			document.getElementById('check80').value = true;	
		}

		document.frmConsultoria.submit();
	}
	
	function validar(){
		
		cuarenta = document.getElementById("check40");
		sesenta = document.getElementById("check60");
		ochenta = document.getElementById("check80");
		if( !cuarenta.checked ) {
		  return false;
		}else if(!sesenta.checked){
			return false;
		}else if(!ochenta.checked){
			alert("Seleccione un tipo de consultoria");
			return false;
	}		
	
}

</script>
</body>
</html>
