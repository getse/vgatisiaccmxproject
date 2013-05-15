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

function showCombo(cat, admin, next) {
	var combo = document.getElementById('catProd' + next);
	if (next == 6) {
		var _combo = document.getElementById('catProd5');
		for ( var i = 0; i < _combo.length; i++) {
			_value = _combo.options[i].value;
			_text = _combo.options[i].text;
			if (_value == cat) {
				document.getElementById('idInputCatScian').value = _text;
				document.getElementById('idCveSci').value = _value;
				document.getElementById('idInputCatScian').rows = _text.length > 85 ? 2
						: 1;
			}
		}
	} else {
		$("#catProd" + next).html(
				'<option selected="selected" value="0">Cargando...</option>');
		combo.style.display = 'block';
		combo.disabled = true;

		var size = (next == 2 ? 3 : (next == 3 ? 4 : (next == 4 ? 5
				: (next == 5 ? 6 : 1))));
		var url = '${pageContext.request.contextPath}/vinculacion/comprador/compradorRequerimientoAdd.do?cat'
				+ (next - 1) + '=' + cat;
		if (admin) {
			url = '${pageContext.request.contextPath}/vinculacion/tractora/administracion/tractoraRequerimientoAdd.do?cat'
					+ (next - 1) + '=' + cat;
		}
		peticion.open("GET", url, true);
		peticion.onreadystatechange = function() {
			if (peticion.readyState == 4 && peticion.status == 200) {
				var cont = peticion.responseText;
				var divideCont = cont.split('\<');
				var x = 1;
				for ( var i = 1; i < divideCont.length; i++) {
					var sel = divideCont[i];
					if (sel.substring(0, 6) == 'option') {
						var inicioCadena = sel.indexOf('>') + 1;
						var finCadena = sel.length;
						var _text = sel.substring(inicioCadena, finCadena);
						var _valOpt = sel.split(' ');
						for ( var j = 1; j < _valOpt.length; j++) {
							var _val = _valOpt[j];
							if (_val.substring(0, 5) == 'value') {
								var _valorValue = _val.split('\"');
								for ( var k = 1; k < _valorValue.length; k++) {
									var _valNumero = _valorValue[k];
									if (_valNumero.length == size
											&& !isNaN(_valNumero)) {
										combo.options[0] = new Option(
												'--Seleccione una opción--', 0);
										combo.options[x] = new Option(_text,
												_valNumero);
										x++;
									}
								}
							}
						}
					}
				}
				document.getElementById('catProd' + next).disabled = false;
			}
		};
		peticion.send(null);
	}
}

function busqueda(admin) {
	var chain = document.getElementById('idCampoBusqueda').value;
	if (chain.trim() == '') {
		alert('Capture el texto para realizar una búsquda.');
		document.getElementById('idCampoBusqueda').focus();
	} else {
		document.getElementById('idCampoBusqueda').value = 'Buscando...';
		document.getElementById('idCampoBusqueda').disabled = true;
		var url = '${pageContext.request.contextPath}/vinculacion/comprador/compradorRequerimientoBusqueda.do?chain='
				+ chain;
		if (admin) {
			url = '${pageContext.request.contextPath}/vinculacion/tractora/administracion/tractoraRequerimientoBusqueda.do?chain='
					+ chain;
		}
		peticion.open("GET", url, true);
		peticion.onreadystatechange = function() {
			if (peticion.readyState == 4 && peticion.status == 200) {
				var respuesta = peticion.responseText;
				respuesta = respuesta.substring(respuesta
						.indexOf('ResBusCatSCIANCCMX...') + 26, respuesta
						.lastIndexOf('ResBusCatSCIANCCMX...') - 4);
				document.getElementById('idCampoBusqueda').value = chain;
				document.getElementById('idCampoBusqueda').disabled = false;
				document.getElementById('idBusResTit').innerText = 'Resultados del texto: [ '
						+ chain + ' ]';
				document.getElementById('idBtnBuscar').click();
				$("#idDivResultados").html(respuesta);
			}
		};
		peticion.send(null);
	}
}

function anterior() {
	if (document.getElementById('idDivRes1') != null) {
		document.getElementById('idBtnSiguiente').style.display = 'block';
		var cnt = 1;
		while (cnt > 0) {
			if (document.getElementById('idDivRes' + cnt) == null) {
				cnt = 0;
			} else {
				if (document.getElementById('idDivRes' + (cnt - 1)) != null
						&& document.getElementById('idDivRes' + cnt).style.display == 'block') {
					document.getElementById('idDivRes' + cnt).style.display = 'none';
					document.getElementById('idDivRes' + (cnt - 1)).style.display = 'block';
				}
				cnt++;
			}
		}
	}
}

function siguiente() {
	if (document.getElementById('idDivRes1') != null) {
		document.getElementById('idBtnAnterior').style.display = 'block';
		var cnt = 1;
		var pos = 0;
		while (cnt > 0) {
			if (document.getElementById('idDivRes' + cnt) == null) {
				break;
			} else {
				if (document.getElementById('idDivRes' + cnt).style.display == 'block')
					pos = cnt;
				cnt++;
			}
		}
		cnt = 1;
		if (document.getElementById('idDivRes' + (pos + 1)) != null) {
			document.getElementById('idDivRes' + pos).style.display = 'none';
			document.getElementById('idDivRes' + (pos + 1)).style.display = 'block';
		}
	}

}

function elegir(admin) {
	var cnt = 1;
	var pos = 0;
	while (cnt > 0) {
		if (document.getElementById('idDivRes' + cnt) == null) {
			break;
		} else {
			if (document.getElementById('idDivRes' + cnt).style.display == 'block')
				pos = cnt;
			cnt++;
		}
	}
	var cve = document.getElementById('idHidResCveScian' + pos).value;
	var des = document.getElementById('idHidResDescScian' + pos).value;
	document.getElementById('idInputCatScian').value = des;
	document.getElementById('idCveSci').value = cve;
	document.getElementById('idInputCatScian').rows = des.length > 85 ? 2 : 1;
	setTimeout("nextCombo(" + cve + ", 1)", 100);
	setTimeout("showCombo(" + cve.substring(0, 2) + ", " + admin + ", 2)", 1000);
	setTimeout("nextCombo(" + cve + ", 2)", 4000);
	setTimeout("showCombo(" + cve.substring(0, 3) + ", " + admin + ", 3)", 5000);
	setTimeout("nextCombo(" + cve + ", 3)", 8000);
	setTimeout("showCombo(" + cve.substring(0, 4) + ", " + admin + ", 4)", 9000);
	setTimeout("nextCombo(" + cve + ", 4)", 12000);
	setTimeout("showCombo(" + cve.substring(0, 5) + ", " + admin + ", 5)",
			13000);
	setTimeout("nextCombo(" + cve + ", 5)", 16000);
}

function nextCombo(cve, pos) {
	var _size = document.getElementById('catProd' + pos).length;
	for ( var i = 0; i < _size; i++) {
		if (document.getElementById('catProd' + pos).options[i].value == (cve + '')
				.substring(0, pos + 1)) {
			document.getElementById('catProd' + pos).options[i].selected = true;
			document.getElementById('catProd' + pos).options[i].click();
		}
	}
}

function agregaTelefono() {
	var _tel = document.getElementById('idTelefono').value;
	var _telefonos = 0;
	if (!/^\(\d{2}\)\(\d{2}\)\(\d{8}\)\(\d{4}\)$/.test(_tel)) {
		alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
	} else {
		for ( var i = 1; i <= 10; i++) {
			if (document.getElementById('idDivTel' + i).style.display == 'block')
				_telefonos++;
		}
		if (_telefonos < 10) {
			var _pos = _telefonos + 1;
			document.getElementById('idTelHid' + _pos).value = document
					.getElementById('idTelefono').value;
			document.getElementById('labTel' + _pos).innerText = document
					.getElementById('idTelefono').value;
			document.getElementById('idDivTel' + _pos).style.display = 'block';
		}
		document.getElementById('idTelefono').value = null;
	}
	document.getElementById('idTelefono').focus();
}

function quitarTelefono(pos) {
	var _last = pos;
	if (pos == '10'
			|| document.getElementById('idDivTel' + (pos + 1)).style.display == 'none') {
		document.getElementById('labTel' + pos).innerText = null;
		document.getElementById('idTelHid' + pos).value = null;
		document.getElementById('idDivTel' + pos).style.display = 'none';
	} else {
		for ( var i = pos; i <= 10; i++) {
			if (document.getElementById('idDivTel' + i).style.display == 'block')
				_last++;
			if (document.getElementById('labTel' + (i + 1)) != null)
				_lab = document.getElementById('labTel' + (i + 1)).innerText;
			if (document.getElementById('idTelHid' + (i + 1)) != null)
				_hid = document.getElementById('idTelHid' + (i + 1)).value;
			document.getElementById('labTel' + i).innerText = _lab;
			document.getElementById('idTelHid' + i).value = _hid;
		}
		document.getElementById('idDivTel' + (_last - 1)).style.display = 'none';
	}
}

function calendario() {
	Calendar.setup({
		inputField : "ingreso", // id del campo de texto
		ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
		// campo de texto
		button : "lanzador" // el id del botón que lanzará el calendario
	});
	Calendar.setup({
		inputField : "ingreso2", // id del campo de texto
		ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
		// campo de texto
		button : "lanzador2" // el id del botón que lanzará el calendario
	});
}

function cancela() {
	document.frmCancela.submit();
}

function modificar() {
	calendario();
	document.getElementById('sec2').style.display = 'none';
	document.getElementById('secR').style.display = 'none';
	document.getElementById('sec1').style.display = 'block';
}

function modificarDatos() {
	document.getElementById('sec2').style.display = 'none';
	document.getElementById('secR').style.display = 'none';
	document.getElementById('sec1').style.display = 'block';
}

function lugarSuministro() {
	var pila = document.getElementById('idInput').value;
	var coma = ', ';
	if (pila.indexOf(" ") != -1)
		document.getElementById('idInput').value = pila + coma
				+ document.getElementById('idCampoLugarSuministro').value;
	else
		document.getElementById('idInput').value = pila + ' '
				+ document.getElementById('idCampoLugarSuministro').value;
	document.getElementById('idInput').size = document
			.getElementById('idInput').value.length;
	document.getElementById('lugarSum').style.display = 'block';
}

function contado() {
	document.getElementById('checkcredito').checked = false;
	document.getElementById('checkquince').checked = false;
	document.getElementById('checktreinta').checked = false;
	document.getElementById('checksesenta').checked = false;
	document.getElementById('checknoventa').checked = false;
	document.getElementById('checkotro').checked = false;
	document.getElementById('plazo').style.display = 'none';
	document.getElementById('otrasCondicionesPago').style.display = 'none';
}

function credito() {
	document.getElementById('checkcontado').checked = false;
	if (document.getElementById('checkcredito').checked == false) {
		document.getElementById('plazo').style.display = 'none';
		document.getElementById('otrasCondicionesPago').style.display = 'none';
	} else {
		if (document.getElementById('checkotro').checked == true) {
			document.getElementById('otrasCondicionesPago').style.display = 'block';
		}
		document.getElementById('plazo').style.display = 'block';
	}
}

function limpiaFechaSuministro() {
	if (document.getElementById('expiracontinuo').checked == true)
		document.getElementById('ingreso2').value = '';
}

function limpiaFechaExpira(check) {
	if (document.getElementById('indefinido').checked == true
			|| document.getElementById('variasfechas').checked == true
			|| document.getElementById('suministrocontinuo').checked == true)
		document.getElementById('ingreso').value = '';
	if (check == '1') {
		document.getElementById('variasfechas').checked = false;
		document.getElementById('suministrocontinuo').checked = false;
		document.getElementById("idDivDetalleVariasFechas").style.display = 'none';
	}
	if (check == '2') {
		document.getElementById('indefinido').checked = false;
		document.getElementById('suministrocontinuo').checked = false;
		if (document.getElementById('variasfechas').checked == false) {
			document.getElementById("idDivDetalleVariasFechas").style.display = 'none';
		} else {
			document.getElementById("idDivDetalleVariasFechas").style.display = 'block';
		}
	}
	if (check == '3') {
		document.getElementById('indefinido').checked = false;
		document.getElementById('variasfechas').checked = false;
		document.getElementById("idDivDetalleVariasFechas").style.display = 'none';
	}
}

function limpiaCheckSuministro() {
	if (document.getElementById('ingreso').value != '') {
		document.getElementById('indefinido').checked = false;
		document.getElementById('variasfechas').checked = false;
		document.getElementById('suministrocontinuo').checked = false;
		document.getElementById("idDivDetalleVariasFechas").style.display = 'none';
	}
}

function limpiaCheckExpira() {
	if (document.getElementById('ingreso2').value != '')
		document.getElementById('expiracontinuo').checked = false;
}

function limpiaCheckCredito(check) {
	document.getElementById('otrasCondicionesPago').style.display = 'none';
	if (check == '15') {
		document.getElementById('checktreinta').checked = false;
		document.getElementById('checksesenta').checked = false;
		document.getElementById('checknoventa').checked = false;
		document.getElementById('checkotro').checked = false;
	}
	if (check == '30') {
		document.getElementById('checkquince').checked = false;
		document.getElementById('checksesenta').checked = false;
		document.getElementById('checknoventa').checked = false;
		document.getElementById('checkotro').checked = false;
	}
	if (check == '60') {
		document.getElementById('checkquince').checked = false;
		document.getElementById('checktreinta').checked = false;
		document.getElementById('checknoventa').checked = false;
		document.getElementById('checkotro').checked = false;
	}
	if (check == '90') {
		document.getElementById('checkquince').checked = false;
		document.getElementById('checktreinta').checked = false;
		document.getElementById('checksesenta').checked = false;
		document.getElementById('checkotro').checked = false;
	}
}

function otro() {
	if (document.getElementById('checkotro').checked == false) {
		document.getElementById('otrasCondicionesPago').style.display = 'none';
	} else {
		document.getElementById('otrasCondicionesPago').style.display = 'block';
	}
	document.getElementById('checkquince').checked = false;
	document.getElementById('checktreinta').checked = false;
	document.getElementById('checksesenta').checked = false;
	document.getElementById('checknoventa').checked = false;
}

function focoAyudaBusqueda(id) {
	document.getElementById(id).style.display = 'block';
	document.getElementById(id + '2').style.display = 'none';
}

function focoAyuda(id) {
	document.getElementById('idDivPro').style.display = 'none';
	document.getElementById('idDivTipPro').style.display = 'none';
	document.getElementById('idDivDes').style.display = 'none';
	document.getElementById('idDivFil').style.display = 'none';
	document.getElementById('idDivFecSum').style.display = 'none';
	document.getElementById('idDivLug').style.display = 'none';
	document.getElementById('idDivConPag').style.display = 'none';
	document.getElementById('idDivOtrCon').style.display = 'none';
	document.getElementById('idDivReqAdi').style.display = 'none';
	document.getElementById('idDivFecExp').style.display = 'none';

	document.getElementById('idDivPro2').style.display = 'block';
	document.getElementById('idDivTipPro2').style.display = 'block';
	document.getElementById('idDivDes2').style.display = 'block';
	document.getElementById('idDivFil2').style.display = 'block';
	document.getElementById('idDivFecSum2').style.display = 'block';
	document.getElementById('idDivLug2').style.display = 'block';
	document.getElementById('idDivConPag2').style.display = 'block';
	document.getElementById('idDivOtrCon2').style.display = 'block';
	document.getElementById('idDivReqAdi2').style.display = 'block';
	document.getElementById('idDivFecExp2').style.display = 'block';

	document.getElementById(id).style.display = 'block';
	document.getElementById(id + '2').style.display = 'none';
}

function blurAyuda(id) {
	document.getElementById(id).style.display = 'none';
	document.getElementById(id + '2').style.display = 'block';
}

function otroArchivo() {
	var sizeF = 1;

	for ( var i = 1; i < 11; i++) {
		_block = document.getElementById('idDivArchivo' + i + 'Block').style.display;
		_none = document.getElementById('idDivArchivo' + i + 'None').style.display;
		if (_block == 'block' || _none == 'block') {
			sizeF++;
		}
	}
	document.getElementById('idDivArchivo' + sizeF + 'Block').style.display = 'block';
}

function supArchivo(pos) {
	document.getElementById('idCampoArchivo' + pos).value = '';
	document.getElementById('idDivArchivo' + pos + 'Block').style.display = 'none';
}

function seleccionaTodos() {
	var size = document.busqueda.checkAsigna.length;
	if (size > 0)
		for ( var i = 0; i < size; i++)
			document.busqueda.checkAsigna[i].checked = (document.busqueda.checkTodos.checked ? true
					: false);
}

function tel(fld, vnt) {
	var key = (document.all) ? vnt.keyCode : vnt.which;
	siz = fld.value.length;
	if (siz == 0)
		fld.value = '(';
	else if (siz == 3 || siz == 7 || siz == 17)
		fld.value = fld.value + ')(';
	else if (siz == 23)
		fld.value = fld.value + ')';
	if (key == 13)
		agregaTelefono();
	return (key <= 13 || (key >= 48 && key <= 57) || key == 46 || key == 40 || key == 41);
}

function validacionBusqueda() {
	document.getElementById('idHiddNombreCom').value = document.getElementById('campoBusqueda').value;
	valorBusq = document.getElementById("campoBusqueda").value.split(" ");
	document.getElementById('idProd').value = document
			.getElementById('idInputCatScian').value;

	if (valorBusq == null || valorBusq == 0 || valorBusq.length > 3
			|| valorBusq == " ") {
		document.getElementById("campoBusqueda").focus();
		alert("Para realizar una búsqueda Escriba en 3 palabras el producto");
		return false;
	} else {
		return true;
	}
}

// validacion de campos
function validacion(sec) {
	document.getElementById('idFecSum').value = document
			.getElementById('ingreso').value;
	document.getElementById('idFecExp').value = document
			.getElementById('ingreso2').value;
	valorProducto = document.getElementById("idCampoProducto").value;
	valorTipoProducto = document.getElementById("idInputCatScian").value;
	valorLugarSuministro = document.getElementById("idInput").value;
	valorFechaS = document.getElementById('ingreso').value;
	valorFechaE = document.getElementById("ingreso2").value;

	if (sec == '1') {

		if (valorProducto == null || valorProducto.length == 0
				|| /^\s+$/.test(valorProducto)) {
			alert("Ingrese el producto solicitado");
			document.getElementById("idCampoProducto").focus();
			return false;
		} else if (valorTipoProducto == null || valorTipoProducto.length == 0
				|| /^\s+$/.test(valorTipoProducto)) {
			alert("Seleccione la categoría del tipo de producto");
			document.getElementById("idCatCcmx1").focus();
			return false;
		} else {
			document.getElementById('sec1').style.display = 'none';
			document.getElementById('sec2').style.display = 'block';
			return true;
		}
	} else {
		if (valorLugarSuministro == null || valorLugarSuministro.length == 0
				|| /^\s+$/.test(valorLugarSuministro)) {
			document.getElementById("idInput").focus();
			alert("Agregue al menos un lugar de suministro");
			return false;
		} else if ((valorFechaS == null || valorFechaS == 0 || /^\s+$/
				.test(valorFechaS))
				&& (document.getElementById('indefinido').checked == false
						&& document.getElementById('variasfechas').checked == false && document
						.getElementById('suministrocontinuo').checked == false)) {
			document.getElementById('ingreso').focus();
			alert("Ingrese la fecha de suministro o seleccione una opción");
			return false;
		} else if ((valorFechaE == null || valorFechaE == 0 || /^\s+$/
				.test(valorFechaE))
				&& (document.getElementById('expiracontinuo').checked == false)) {
			document.getElementById("ingreso2").focus();
			alert("Ingrese la fecha en que exira el requerimiento o seleccione una opción");
			return false;
		} else {
			document.getElementById('idTipoPro').value = document
					.getElementById('idInputCatScian').value;
			for ( var i = 1; i <= 10; i++) {
				if (document.getElementById('idDivArc' + i).style.display == 'none') {
					// limpiando archivos inecesarios
					document.getElementById('filArc' + (i - 1)).name = null;
					document.getElementById('idArcHid' + (i - 1)).value = null;
				}
			}
			return true;
		}
	}
}
function validaDatosTractora(sec, comprador) {
	valorEmpresa = document.getElementById("idEmpresa").value;
	valorNombre = document.getElementById("idNombre").value;
	valorPaterno = document.getElementById("idAppPaterno").value;
	valorMaterno = document.getElementById("idAppMaterno").value;
	valorCorreo = document.getElementById("idCorreoElectronico").value;
	valorCompara = document.getElementById("idComparaCorreo").value;

	valorPuesto = document.getElementById("idPuesto").value;
	valorTelefono = document.getElementById("idTelefono").value;
	// valorAddTelefono = document.getElementById("idAddTelefono").value;

	valorCalle = document.getElementById("idCalle").value;
	valorNumExt = document.getElementById("idNumExt").value;
	valorColonia = document.getElementById("idColonia").value;
	valorDelegacion = document.getElementById("idDelegacion").value;
	// Select
	valorEstado = document.getElementById("idEstado").selectedIndex;
	// valorEstado = document.getElementById("idEst").value;
	valorCodigoPostal = document.getElementById("idCodigoPostal").value;

	if (sec == '1') {
		if (valorEmpresa == null || valorEmpresa.length == 0
				|| /^\s+$/.test(valorEmpresa)) {
			document.getElementById("idEmpresa").focus();
			alert("Ingrese en Nombre de la Empresa");
			return false;
		} else if (valorNombre == null || valorNombre.length == 0
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
		} else if (valorPuesto == null || valorPuesto.length == 0
				|| /^\s+$/.test(valorPuesto)) {
			document.getElementById("idPuesto").focus();
			alert("Ingrese su puesto");
			return false;
		} else if (document.getElementById('idDivTel1').style.display == 'none'
				&& (valorTelefono == null || valorTelefono.length == 0 || !/^\(\d{2}\)\(\d{2}\)\(\d{8}\)\(\d{4}\)$/
						.test(valorTelefono))) {
			document.getElementById("idTelefono").focus();
			alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
			return false;
		} else {
			if (!comprador) {
				document.getElementById('sec1').style.display = 'none';
				document.getElementById('sec2').style.display = 'block';
			}
			if (document.getElementById('idDivTel1').style.display == 'none') {
				document.getElementById('idTelHid1').value = document
						.getElementById('idTelefono').value;
			}
			return true;
		}
	} else if (sec == '2') {
		if (valorCalle == null || valorCalle.length == 0
				|| /^\s+$/.test(valorCalle)) {
			document.getElementById("idCalle").focus();
			alert("Ingrese la calle");
			return false;
		} else if (valorNumExt == null || valorNumExt.length == 0
				|| /^\s+$/.test(valorNumExt)) {
			document.getElementById("idNumExt").focus();
			alert("Ingrese el número exterior");
			return false;
		} else if (valorColonia == null || valorColonia.length == 0
				|| /^\s+$/.test(valorColonia)) {
			document.getElementById("idColonia").focus();
			alert("Ingrese la colonia");
			return false;
		} else if (valorDelegacion == null || valorDelegacion.length == 0
				|| /^\s+$/.test(valorDelegacion)) {
			document.getElementById("idDelegacion").focus();
			alert("Ingrese la delegación");
			return false;
		} else if (valorEstado == " " || valorEstado == 0
				|| valorEstado == null) {
			document.getElementById("idEstado").focus();
			alert("Seleccione un Estado");
			return false;
		} else if (valorCodigoPostal == null || valorCodigoPostal.length == 0
				|| /^\s+$/.test(valorCodigoPostal)) {
			document.getElementById("idCodigoPostal").focus();
			alert("Ingrese el Código Postal");
			return false;
		} else {
			return true;
		}
	}

}
