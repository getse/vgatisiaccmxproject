<?xml version="1.0" encoding="UTF-8"?>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link
	rel="stylesheet"
	href="${pageContext.request.contextPath}/css/layout.css"
	type="text/css" />
</head>
<body style="width: 100%; height: 768px; background: #FFFFFF url(img/diploma.jpg) no-repeat center;">
	<table width="100%">
		<tr>
			<td>
				<table width="100%">
					<tr align="center">
						<td align="center" style="height: 250px;">
							<label class="diplomaNombre"></label>
						</td>
					</tr>
				</table>
				<table width="100%">
					<tr align="center">
						<td align="center">
							<label class="diplomaNombre">${nombresAsistentes }</label>
						</td>
					</tr>
				</table>
				<table width="100%">
					<tr align="center">
						<td align="center" style="height: 55px;">
							<label class="diplomaNombre"></label>
						</td>
					</tr>
				</table>
				<table width="100%">
					<tr align="center">
						<td align="center">
							<label class="diplomaTitulo">${tema}</label>
						</td>
					</tr>
				</table>
				<table width="100%">
					<tr align="center">
						<td align="center" style="height: 105px;">
							<label class="diplomaNombre"></label>
						</td>
					</tr>
				</table>
				<table width="80%" style="margin-left: 170px;">
					<tr align="center">
						<td align="center">
							<table width="40%">
								<tr align="center">
									<td align="center">
										<label class="diplomaBaseIzq">Iván Rivas Rodríguez</label>
									</td>
								</tr>
								<tr>
									<td align="center">
										<label class="diplomaBaseIzqPie">Director General del CCMX</label>
									</td>
								</tr>
							</table>
						</td>
						<td>
							<table width="40%">
								<tr>
									<td align="center">
										<label class="diplomaBaseDer">Oscar Arturo Camarena</label>
									</td>
								</tr>
								<tr>
									<td align="center">
										<label class="diplomaBaseDerPie">Capacitación Corporativo Ventas LALA</label>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>