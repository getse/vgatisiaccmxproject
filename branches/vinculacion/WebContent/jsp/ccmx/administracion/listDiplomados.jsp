<?xml version="1.0" encoding="UTF-8"?>
<%@taglib
	uri="/struts-tags"
	prefix="s"%>
<%@page
	pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html
	xmlns="http://www.w3.org/1999/xhtml"
	xml:lang="es">
<head>
<meta
	http-equiv="Content-Type"
	content="text/html; charset=UTF-8" />
<script src="${pageContext.request.contextPath}/js/ccmx.js" type="text/javascript"></script>
</head>
<body>
<s:if test="mensaje!=null">
	<br />
	<table class="nota">
		<tr>
			<td class="imgNota">
				<s:if test="mensaje.respuesta==0">
					<img src="${pageContext.request.contextPath}/img/palomitaverde.gif" />
				</s:if>
				<s:else>
					<img src="${pageContext.request.contextPath}/img/warning.png" />
				</s:else>
			</td>
			<td class="contenidoNota"><s:property value="mensaje.mensaje" /></td>
		</tr>
	</table>
</s:if>

<fieldset id="requerimientos">
	<legend>
		<s:if test="idDiplomado == 0">
			<s:label value="Administración de Diplomados" />
			<br /><br />
			<s:label cssClass="camposObligatorios" value="Seleccione la opción 'Registrar Diplomado' para agregar un Diplomado, seleccione un diplomado para ver los detalles y/o editar su información." />
		</s:if>
		<s:else>
			<legend>
				<s:label value="Diplomado en " />${tituloDiplomado}
				<br /><br />
			</legend>
		</s:else>
	</legend>
	<br />
	
	<s:if test="idDiplomado == 0">
		<s:form name="frmAnios" action="diplomadosShow" namespace="/ccmx/administracion/diplomados" onsubmit="javascript: $(idProcesa)[0].style.display = 'block';" theme="simple">
			<table width="99%">
				<tr>
					<td style="width: 100%'" align="center">
						<s:label cssClass="etiquetaCaptura" value="Año: " />
						<select id="year" name="year" onchange="javascript: showDiplomados()">
							<s:iterator value="menuAnios" status="stat">
								<option value="${menuAnios[stat.index]}" ${menuAnios[stat.index]== year ? ' selected="selected"' : ''}>${menuAnios[stat.index]}</option>
							</s:iterator>
						</select>			
					</td>
				</tr>
			</table>
			<s:iterator value="listDiplomados" status="stat" var="recor">
				<table width="99%" cellspacing="1" cellpadding="1">
					<tr>
						<td class="encabezadoTablaResumen" align="center"><b>Generación <s:property value="#stat.count" /></b></td>
					</tr>
				</table>
				<s:iterator value="recor" status="cont">
					<div style="float: left; width: 49%; text-align: center;" class="cuerpo1TablaResumen">
						<a href="${pageContext.request.contextPath}/ccmx/administracion/diplomados/diplomadosShow.do?idDiplomado=${recor[cont.index].idDiplomado}" onclick="javascript: $(idProcesa)[0].style.display = 'block';">${recor[cont.index].tema}</a>						
					</div>
				</s:iterator>
			</s:iterator>
		</s:form>

		<s:form action="diplomadoAdd" namespace="/ccmx/administracion/diplomados" onsubmit="javascript: $(idProcesa)[0].style.display = 'block';" theme="simple">
			<table width="99%">
				<tr>
					<td>
						<br />
						<s:submit cssClass="botonenviar" value="Registrar Diplomado" />
					</td>
				</tr>
			</table>
		</s:form>
	</s:if>
	<s:else>
		<div>
			<s:form action="diplomadoAdd" namespace="/ccmx/administracion/diplomados" onsubmit="javascript: $(idProcesa)[0].style.display = 'block';" theme="simple">
				<s:hidden name="idDiplomado" value="%{idDiplomado}" />
				<s:hidden name="opcion" value="editarDiplomado" />
				
				<table width="99%">
					<tr>
						<td align="center" style="width: 99%;"><s:submit cssClass="botonenviar" align="left" value="Administrar diplomado" /></td>
					</tr>
				</table>
				<table width="99%">
					<tr>
						<td align="center" style="width: 50%;"><input class="botonenviar" value="Administrar Sesiones" type="button" onclick="javascript: $(idProcesa)[0].style.display = 'block';document.frmSesiones.submit();"/></td>
						<td align="center" style="width: 50%;"><input class="botonenviar" value="Administrar pagos y facturación" type="button" onclick="javascript: $(idProcesa)[0].style.display = 'block';document.frmPagos.submit();"/></td>
					</tr>
				</table>
				<table width="99%">
					<tr>
						<td>
							<table width="99%" cellspacing="1" cellpadding="1">
								<thead>
									<tr>
										<td class="encabezado_tabla" align="center"><b>No.</b></td>
										<td class="encabezado_tabla" align="center"><b>PYME</b></td>
										<td class="encabezado_tabla" align="center"><b>Nombre del<br />Participante</b></td>
										<td class="encabezado_tabla" align="center"><b>Telefono</b></td>
										<td class="encabezado_tabla" align="center"><b>Correo<br />electrónico</b></td>
										<td class="encabezado_tabla" align="center"><b>Cargo</b></td>
										<td class="encabezado_tabla" align="center"><b>Grandes Empresas</b></td>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="listParticipantes" status="stat">
											<tr>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${stat.count}</td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${pyme}</td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${nombre}</td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${telefono}</td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${correoElectronico}</td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${cargo}</td>
												<td class="${((stat.index % 2) == 0) ? 'cuerpo1TablaResumen' : 'cuerpo2TablaResumen'}" align="center">${tractora}</td>
											</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
				<table width="99%">
					<tr>
						<td align="center" style="width: 99%;">
							<input class="botonenviar" value="Agregar paticipante" type="button" onclick="javascript: $(idProcesa)[0].style.display = 'block';document.frmParticipantes.submit();"/>
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		
		<s:form name="frmSesiones" action="diplomadoShow" namespace="/ccmx/administracion/diplomados" theme="simple">
			<s:hidden name="opcion" value="SesionesAdmin" />
			<s:hidden name="idDiplomado" value="%{idDiplomado}" />
		</s:form>
		<s:form name="frmPagos" action="diplomadoShow" namespace="/ccmx/administracion/diplomados" theme="simple">
			<s:hidden name="opcion" value="selecPyme" />
			<s:hidden name="idDiplomado" value="%{idDiplomado}" />
		</s:form>
		<s:form name="frmParticipantes" action="diplomadoShow" namespace="/ccmx/administracion/diplomados" theme="simple">
			<s:hidden name="opcion" value="selecPyme2" />
			<s:hidden name="idDiplomado" value="%{idDiplomado}" />
		</s:form>
	</s:else>

</fieldset>
</body>
</html>