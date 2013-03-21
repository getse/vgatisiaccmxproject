setTimeout("breakOut()", 1500);
// TODO hacer una funcion que revise cada determinado tiempo si el combo esta vacio para que entonces lo llene :/
function breakOut() {
	var combo = document.getElementById('idCatScianCcmx');
	var combo1 = document.getElementById('idCatCcmx1');
	var sizeC1 = 1;

	// for ( var i = 0; i < combo.length; i++) {
	// _value = combo.options[i].value;
	// if (_value.length == 2) {
	// sizeC1++;
	// }
	// }

	combo1.length = 22;// sizeC1 - 1;

	for ( var i = 0; i < combo1.length; i++) {
		_value = combo.options[i].value;
		_text = combo.options[i].text;
		if (_value.length == 2) {
			combo1.options[i].value = _value;
			combo1.options[i].text = _text;
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
		if (_value.length == 3 && _value.substring(0, 2) == cveScian) {
			sizeC2++;
		}
	}

	combo2.length = sizeC2 - 1;

	for ( var i = 0; i < 120/* combo.length */; i++) {
		_value = combo.options[i].value;
		_text = combo.options[i].text;
		if (_value.length == 3 && _value.substring(0, 2) == cveScian) {
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
	}
	if (check == '2') {
		document.getElementById('indefinido').checked = false;
		document.getElementById('suministrocontinuo').checked = false;
	}
	if (check == '3') {
		document.getElementById('indefinido').checked = false;
		document.getElementById('variasfechas').checked = false;
	}
}

function limpiaCheckSuministro() {
	if (document.getElementById('ingreso').value != '') {
		document.getElementById('indefinido').checked = false;
		document.getElementById('variasfechas').checked = false;
		document.getElementById('suministrocontinuo').checked = false;
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
	if (id = 'idDivOtrCon') {
		// document.getElementById('idDivConPag').style.display = 'block';
		// document.getElementById('idDivConPag2').style.display = 'none';
	}
}

function blurAyuda(id) {
	document.getElementById(id).style.display = 'none';
	document.getElementById(id + '2').style.display = 'block';
}

// validacion de campos
function validacion(sec) {
	document.getElementById('idFecSum').value = document
			.getElementById('ingreso').value;
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
		} else if ((valorFechaS == null || valorFechaS == 0 || /^\s+$/
				.test(valorFechaS))
				&& (document.getElementById('indefinido').checked == false
						&& document.getElementById('variasfechas').checked == false && document
						.getElementById('suministrocontinuo').checked == false)) {
			document.getElementById('ingreso').focus();
			alert("Ingrese la fecha de suministro o seleccione una opción");
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
		} else if ((valorFechaE == null || valorFechaE == 0 || /^\s+$/
				.test(valorFechaE))
				&& (document.getElementById('expiracontinuo').checked == false)) {
			document.getElementById("ingreso2").focus();
			alert("Ingrese la fecha en que exira el requerimiento o seleccione una opción");
			return false;
		} else {
			document.getElementById('idTipoPro').value = document
					.getElementById('idInputCatScian').value;
			return true;
		}
	}

}
