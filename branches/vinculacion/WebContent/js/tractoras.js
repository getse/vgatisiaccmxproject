var peticion = false;
var seleccion = false;
var cveBusqueda = 0;
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

function pagina() {
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
	return pos;
}

function elegir(admin) {
	var pos = pagina();
	cveBusqueda = document.getElementById('idHidResCveScian' + pos).value;
	var des = document.getElementById('idHidResDescScian' + pos).value;
	document.getElementById('idInputCatScian').value = des;
	document.getElementById('idCveSci').value = cveBusqueda;
	document.getElementById('idInputCatScian').rows = des.length > 85 ? 2 : 1;
	setTimeout("nextCombo(" + cveBusqueda + ", 1, " + admin + ")", 1000);
	setTimeout("step(1, " + admin + ")", 1500);
}

function step(pos, admin) {
	var cve = cveBusqueda;
	if (document.getElementById('catProd' + pos).value == -1)
		setTimeout(("step(" + pos + ", " + admin + ");"), 1000);
	else {
		if (document.getElementById('catProd' + pos).options.length > 1) {
			setTimeout("showCombo(" + cve.substring(0, pos + 1) + ", " + admin
					+ ", " + (pos + 1) + ")", 200);
			setTimeout("nextCombo(" + cve + ", " + (pos + 1) + ", " + admin
					+ ")", 600);
		} else
			setTimeout(("step(" + pos + ", " + admin + ");"), 1000);
	}
}

function nextCombo(cve, pos, admin) {
	var _size = document.getElementById('catProd' + pos).length;
	if (pos > 1 && document.getElementById('catProd' + pos).options.length == 1)
		setTimeout(("nextCombo(" + cve + ", " + pos + ", " + admin + ");"),
				1000);
	else {
		for ( var i = 0; i < _size; i++) {
			if (document.getElementById('catProd' + pos).options[i].value == (cve + '')
					.substring(0, pos + 1)) {
				document.getElementById('catProd' + pos).options[i].selected = true;
				document.getElementById('catProd' + pos).options[i].click();
			}
		}
		if (pos > 1 && pos < 5) {
			setTimeout(("step(" + pos + ", " + admin + ");"), 1000);
		}
	}
}

function getTelefono(lada, telefono, extension) {

	if (lada.length < 2)
		lada = '00';

	if (extension.length == 0)
		extension = '0000';
	else if (extension.length == 1)
		extension = '000' + extension;
	else if (extension.length == 2)
		extension = '00' + extension;
	else if (extension.length == 3)
		extension = '0' + extension;

	return '(52)(' + lada + ')(' + telefono + ')(' + extension + ')';
}

function agregaTelefono() {
	_lada = document.getElementById("ladaTel").value;
	_telefono = document.getElementById("numTel").value;
	_extension = document.getElementById("extTel").value;
	var _telefonos = 0;

	if (_lada.length != 2 || /^\s+$/.test(_lada)) {
		document.getElementById("ladaTel").focus();
		alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
		return false;
	} else if (_telefono.length != 8 || /^\s+$/.test(_telefono)) {
		document.getElementById("numTel").focus();
		alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
		return false;
	} else {
		_tel = getTelefono(_lada, _telefono, _extension);
		for ( var i = 1; i <= 10; i++) {
			if (document.getElementById('idDivTel' + i).style.display == 'block')
				_telefonos++;
		}
		if (_telefonos < 10) {
			var _pos = _telefonos + 1;
			document.getElementById('idTelHid' + _pos).value = _tel;
			document.getElementById('labTel' + _pos).innerText = _tel;
			document.getElementById('idDivTel' + _pos).style.display = 'block';
		}
		document.getElementById("ladaTel").value = null;
		document.getElementById("numTel").value = null;
		document.getElementById("extTel").value = null;
	}
	document.getElementById('ladaTel').focus();
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

function cambiaCampo(e) {

	var code = (e.keyCode ? e.keyCode : e.which);

	if (code != 8 && code != 37 && code != 39) {
		if (document.getElementById('ladaTel').value.length == 2) {
			document.getElementById('numTel').focus();
		}
		if (document.getElementById('numTel').value.length == 8) {
			document.getElementById('extTel').focus();
		}
	}
}

function agregaEstado() {
	var size = document.getElementById("idCampoLugarSuministro").options.length;
	var _edo = '';
	var _hidDes = document.getElementById('idDesEdo').value;
	for ( var x = 0; x < size; x++) {
		if (document.getElementById("idCampoLugarSuministro").options[x].selected)
			_edo = document.getElementById("idCampoLugarSuministro").options[x].value;
	}
	var _edos = 0;
	for ( var i = 1; i <= 10; i++) {
		if (document.getElementById('idDivEdo' + i).style.display == 'block')
			_edos++;
	}
	if (_edos < 10) {
		var _pos = _edos + 1;
		document.getElementById('idEdoHid' + _pos).value = _edo;
		document.getElementById('idEdoDesHid' + _pos).value = _hidDes;
		document.getElementById('labEdo' + _pos).innerText = _edo
				+ (_hidDes.trim() != '' ? (', (' + _hidDes.trim() + ')') : '');
		document.getElementById('idDivEdo' + _pos).style.display = 'block';
	}
	document.getElementById("idCampoLugarSuministro").options[0].selected = true;
	document.getElementById('idCampoLugarSuministro').focus();
	document.getElementById('idDesEdo').value = null;
}

function quitarEstado(pos) {
	var _last = pos;
	if (pos == '10'
			|| document.getElementById('idDivEdo' + (pos + 1)).style.display == 'none') {
		document.getElementById('labEdo' + pos).innerText = null;
		document.getElementById('idEdoHid' + pos).value = null;
		document.getElementById('idEdoDesHid' + pos).value = null;
		document.getElementById('idDivEdo' + pos).style.display = 'none';
	} else {
		for ( var i = pos; i <= 10; i++) {
			if (document.getElementById('idDivEdo' + i).style.display == 'block')
				_last++;
			if (document.getElementById('labEdo' + (i + 1)) != null)
				_lab = document.getElementById('labEdo' + (i + 1)).innerText;
			if (document.getElementById('idEdoHid' + (i + 1)) != null)
				_hid = document.getElementById('idEdoHid' + (i + 1)).value;
			if (document.getElementById('idEdoDesHid' + (i + 1)) != null)
				_hidDes = document.getElementById('idEdoDesHid' + (i + 1)).value;
			document.getElementById('labEdo' + i).innerText = _lab;
			document.getElementById('idEdoHid' + i).value = _hid;
			document.getElementById('idEdoDesHid' + i).value = _hidDes;
		}
		document.getElementById('idDivEdo' + (_last - 1)).style.display = 'none';
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
	document.getElementById('plazo').style.display = 'none';
	document.getElementById('otrasCondicionesPago').style.display = 'none';
	if (document.getElementById('checkcontado').checked == true)
		document.getElementById('plazoC').style.display = 'block';
	else
		document.getElementById('plazoC').style.display = 'none';
	document.getElementById('checkcredito').checked = false;
	document.getElementById('checkquince').checked = false;
	document.getElementById('checktreinta').checked = false;
	document.getElementById('checksesenta').checked = false;
	document.getElementById('checknoventa').checked = false;
	document.getElementById('checkotro').checked = false;
}

function credito() {
	document.getElementById('plazoC').style.display = 'none';
	document.getElementById('otrasCondicionesPago').style.display = 'none';
	if (document.getElementById('checkcredito').checked == true)
		document.getElementById('plazo').style.display = 'block';
	else
		document.getElementById('plazo').style.display = 'none';
	document.getElementById('checkcontado').checked = false;
	document.getElementById('checkotroC').checked = false;
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
	document.getElementById('checkotro').checked = false;
	if (check == '15') {
		document.getElementById('checktreinta').checked = false;
		document.getElementById('checksesenta').checked = false;
		document.getElementById('checknoventa').checked = false;
	}
	if (check == '30') {
		document.getElementById('checkquince').checked = false;
		document.getElementById('checksesenta').checked = false;
		document.getElementById('checknoventa').checked = false;
	}
	if (check == '60') {
		document.getElementById('checkquince').checked = false;
		document.getElementById('checktreinta').checked = false;
		document.getElementById('checknoventa').checked = false;
	}
	if (check == '90') {
		document.getElementById('checkquince').checked = false;
		document.getElementById('checktreinta').checked = false;
		document.getElementById('checksesenta').checked = false;
	}
}

function otro(sec) {
	if (sec == 2) {
		document.getElementById('checkquince').checked = false;
		document.getElementById('checktreinta').checked = false;
		document.getElementById('checksesenta').checked = false;
		document.getElementById('checknoventa').checked = false;
		if (document.getElementById('checkotro').checked == true)
			document.getElementById('otrasCondicionesPago').style.display = 'block';
		else
			document.getElementById('otrasCondicionesPago').style.display = 'none';
	}
	if (sec == 1) {
		if (document.getElementById('checkotroC').checked == true)
			document.getElementById('otrasCondicionesPago').style.display = 'block';
		else
			document.getElementById('otrasCondicionesPago').style.display = 'none';
	}

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

function muestraAsignar() {
	if (document.frmAsignacion.checkbox == undefined)
		return;
	var size = document.frmAsignacion.checkbox.length == undefined ? 1
			: document.frmAsignacion.checkbox.length;
	var lista = false;
	if (size == 1 && document.frmAsignacion.checkbox.checked)
		lista = true;
	else if (size > 1)
		for ( var i = 0; i < size; i++)
			if (document.frmAsignacion.checkbox[i].checked)
				lista = true;
	if (lista)
		document.getElementById('idDivSelAsiCom').style.display = 'block';
	else
		alert('Seleccione por lo menos una PyME.');
}

function asignaComprador() {
	var size = document.frmAsignacion.checkbox.length == undefined ? 1
			: document.frmAsignacion.checkbox.length;
	var pymes = '';
	var validacion = false;
	var comprador = false;

	if (size == 1 && document.frmAsignacion.checkbox.checked) {
		pymes = document.frmAsignacion.checkbox.id.substring(8);
		validacion = true;
	} else if (size > 1)
		for ( var i = 0; i < size; i++)
			if (document.frmAsignacion.checkbox[i].checked) {
				pymes = pymes
						+ document.frmAsignacion.checkbox[i].id.substring(8)
						+ ',';
				validacion = true;
			}

	if (!validacion)
		alert('Seleccione por lo menos una PyME.');
	else {
		if (pymes.length > 0
				&& pymes.substring(pymes.length - 1, pymes.length) == ',')
			pymes = pymes.substring(0, pymes.length - 1);
		document.frmAsignacion.idHidIdPyMEs.value = pymes;
		size = document.getElementById('idCompradorSeleccionado').options.length == undefined ? 0
				: document.getElementById('idCompradorSeleccionado').options.length;
		for ( var j = 0; j < size; j++)
			if (document.getElementById('idCompradorSeleccionado').options[j].selected
					&& document.getElementById('idCompradorSeleccionado').options[j].value != '-1') {
				document.frmAsignacion.idHidIdComprador.value = document
						.getElementById('idCompradorSeleccionado').options[j].value;
				comprador = true;
			}

		if (!comprador)
			alert('Seleccione el Comprador al que serán asignadas las PyMEs.');
		else
			document.frmAsignacion.submit();
	}
}

function todas() {
	var size = 0;
	try {
		size = document.frmAsignacion.checkbox.length;
	} catch (e) {
	}
	size = size == undefined ? 1 : size;
	if (size == 1)
		document.frmAsignacion.checkbox.checked = seleccion ? false : true;
	else if (size > 0)
		for ( var i = 0; i < size; i++)
			document.frmAsignacion.checkbox[i].checked = seleccion ? false
					: true;
	seleccion = !seleccion;
	return false;
}

function validaNumero(evt) {
	var key = (document.all) ? evt.keyCode : evt.which;
	return (key <= 13 || (key >= 48 && key <= 57) || key == 46);
}

function validacionBusqueda() {
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
			alert("Seleccione o búsque la categoría del tipo de producto");
			document.getElementById("idCatCcmx1").focus();
			return false;
		} else {
			document.getElementById('sec1').style.display = 'none';
			document.getElementById('sec2').style.display = 'block';
			return true;
		}
	} else {
		if (document.getElementById("idDivEdo1").style.display == 'none') {
			document.getElementById("idCampoLugarSuministro").focus();
			alert("Agregue al menos un lugar de suministro mediante la opción '+agregarlo'");
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
				try {
					if (document.getElementById('idDivArc' + i).style.display == 'none') {
						// limpiando archivos inecesarios
						document.getElementById('filArc' + (i - 1)).name = null;
						document.getElementById('idArcHid' + (i - 1)).value = null;
					}
				} catch (e) {
				}
			}
			if (document.getElementById('checkquince').checked == true
					|| document.getElementById('checktreinta').checked == true
					|| document.getElementById('checksesenta').checked == true
					|| document.getElementById('checknoventa').checked == true) {
				try {
					document.getElementById('checkotro').value = false;
					document.getElementById('idCampoOtrasCondiciones').value = null;
					document.getElementById('checkotro').checked = false;
				} catch (e) {
				}
				try {
					document.getElementById('checkotroC').value = false;
					document.getElementById('idCampoOtrasCondiciones').value = null;
					document.getElementById('checkotroC').checked = false;
				} catch (e) {
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
	valorPuesto = document.getElementById("idPuesto").value;
	valorLada = document.getElementById("ladaTel").value;
	valorTelefono = document.getElementById("numTel").value;
	valorExtension = document.getElementById("extTel").value;
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
		} else if (valorPuesto == null || valorPuesto.length == 0
				|| /^\s+$/.test(valorPuesto)) {
			document.getElementById("idPuesto").focus();
			alert("Ingrese su puesto");
			return false;
		} else if (document.getElementById('idDivTel1').style.display == 'none'
				&& (valorLada.length != 2 || /^\s+$/.test(valorLada))) {
			document.getElementById("ladaTel").focus();
			alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
			return false;
		} else if (document.getElementById('idDivTel1').style.display == 'none'
				&& (valorTelefono.length != 8 || /^\s+$/.test(valorTelefono))) {
			document.getElementById("numTel").focus();
			alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
			return false;
		} else {
			if (!comprador) {
				document.getElementById('sec1').style.display = 'none';
				document.getElementById('sec2').style.display = 'block';
			}
			if (document.getElementById('idDivTel1').style.display == 'none') {
				document.getElementById('idTelHid1').value = getTelefono(
						valorLada, valorTelefono, valorExtension);
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
