setTimeout("breakOut()", 2222);
// TODO hacer una funcion que revise cada determinado tiempo si el combo esta
// vacio para que entonces lo llene :/
function breakOut() {
	var combo = document.getElementById('idCatScianCcmx');
	var combo1 = document.getElementById('idCatCcmx1');
	// var sizeC1 = 1;

	// for ( var i = 0; i < combo.length; i++) {
	// _value = combo.options[i].value;
	// if (_value.length == 2) {
	// sizeC1++;
	// }
	// }

	combo1.length = 20;// sizeC1 - 1;

	for ( var i = 0, j = 0; i < 23; i++) {
		_value = combo.options[i].value;
		_text = combo.options[i].text;
		if (_value.length == 2) {
			if (_value != 32 && _value != 33 && _value != 49) {
				combo1.options[j].value = _value;
				combo1.options[j].text = _text;
				j++;
			}
		}
	}
}

function fillCombo2(cveScian) {
	var combo = document.getElementById('idCatScianCcmx');

	var combo2 = document.getElementById('idCatCcmx2');
	var sizeC2 = 1;
	var j = 0;

	for ( var i = 0; i < 120/* combo.length */; i++) {
		_value = combo.options[i].value;
		_text = combo.options[i].text;
		if (_value.length == 3
				&& (_value.substring(0, 2) == cveScian
						|| (cveScian == 31 && (_value.substring(0, 2) == 32 || _value
								.substring(0, 2) == 33)) || (cveScian == 48 && _value
						.substring(0, 2) == 49))) {
			sizeC2++;
		}
	}

	combo2.length = sizeC2 - 1;

	for ( var i = 0; i < 120/* combo.length */; i++) {
		_value = combo.options[i].value;
		_text = combo.options[i].text;
		if (_value.length == 3
				&& (_value.substring(0, 2) == cveScian
						|| (cveScian == 31 && (_value.substring(0, 2) == 32 || _value
								.substring(0, 2) == 33)) || (cveScian == 48 && _value
						.substring(0, 2) == 49))) {
			combo2.options[j].value = _value;
			combo2.options[j].text = _text;
			j++;
		}
	}

	document.getElementById('idCatCcmx3').length = 1;
	document.getElementById('idCatCcmx3').options[0].value = -1;
	document.getElementById('idCatCcmx3').options[0].text = '--Seleccione una opción--';
}

function fillCombo3(cveScian) {
	var combo = document.getElementById('idCatScianCcmx');

	var combo3 = document.getElementById('idCatCcmx3');
	var sizeC3 = 1;
	var k = 0;

	for ( var i = 110; i < combo.length; i++) {
		_value = combo.options[i].value;
		_text = combo.options[i].text;
		if (_value.length == 5 && _value.substring(0, 3) == cveScian) {
			sizeC3++;
		}
	}

	combo3.length = sizeC3 - 1;

	for ( var i = 110; i < combo.length; i++) {
		_value = combo.options[i].value;
		_text = combo.options[i].text;
		if (_value.length == 5 && _value.substring(0, 3) == cveScian) {
			combo3.options[k].value = _value;
			combo3.options[k].text = _text;
			k++;
		}
	}

}

function fillDescripcionScian(id) {
	var combo = document.getElementById('idCatCcmx3');

	for ( var i = 0; i < combo.length; i++) {
		_value = combo.options[i].value;
		_text = combo.options[i].text;
		if (_value == id) {
			document.getElementById('idInputCatScian').value = _text;
			document.getElementById('idCveSci').value = _value;
			document.getElementById('idInputCatScian').rows = _text.length > 85 ? 2
					: 1;
		}
	}

}

function agregarArchivo() {
	// var _arc = document.getElementById('idArchivo').value.length;
	var _archivos = 0;
	if (false) {// _arc == 0) {
		alert('Ingrese un número telefónico para agregarlo.');
		// document.getElementById('idTelefono').style.background = '#FEF5C9';
		// document.getElementById('idTelefono').focus();
	} else {
		for ( var i = 1; i <= 10; i++) {
			if (document.getElementById('idDivArc' + i).style.display == 'block')
				_archivos++;
		}
		if (_archivos < 10) {
			var _pos = _archivos + 1;
			// document.getElementById('idArcHid' + _pos).value = document
			// .getElementById('idTelefono').value;
			// document.getElementById('filTel' + _pos).innerText = document
			// .getElementById('idTelefono').value;
			document.getElementById('idDivArc' + _pos).style.display = 'block';
		}
	}
	// document.getElementById('idTelefono').value = null;
}

function quitarArchivo(pos) {
	document.getElementById('filArc' + pos).name = null;
	document.getElementById('idArcHid' + pos).value = null;
	document.getElementById('idDivArc' + pos).style.display = 'none';
}

function agregaTelefono() {
	var _tel = document.getElementById('idTelefono').value.length;
	var _telefonos = 0;
	if (_tel == 0) {
		alert('Ingrese un número telefónico para agregarlo.');
		document.getElementById('idTelefono').style.background = '#FEF5C9';
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
	}
	document.getElementById('idTelefono').value = null;
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
function validaDatosTractora(sec) {
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
				&& (valorTelefono == null || valorTelefono.length == 0 || /^\s+$/
						.test(valorTelefono))) {
			document.getElementById("idTelefono").focus();
			alert("Ingrese el teléfono");
			return false;
		} else {
			document.getElementById('sec1').style.display = 'none';
			document.getElementById('sec2').style.display = 'block';
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
