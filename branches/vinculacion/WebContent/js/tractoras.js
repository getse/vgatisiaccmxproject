var peticion = false;
var seleccion = false;
var cveBusqueda = 0;
try {
	peticion = new XMLHttpRequest()
} catch (e) {
	try {
		peticion = new ActiveXObject("Msxml2.XMLHTTP")
	} catch (E) {
		try {
			peticion = new ActiveXObject("Microsoft.XMLHTTP")
		} catch (failed) {
			peticion = false
		}
	}
}
if (!peticion) {
	alert("ERROR AL INICIALIZAR!")
}
function showCombo(n, o, p) {
	var q = document.getElementById('catProd' + p);
	if (p == 6) {
		var r = document.getElementById('catProd5');
		for ( var i = 0; i < r.length; i++) {
			_value = r.options[i].value;
			_text = r.options[i].text;
			if (_value == n) {
				document.getElementById('idInputCatScian').value = _text;
				document.getElementById('idCveSci').value = _value;
				document.getElementById('idInputCatScian').rows = _text.length > 85 ? 2
						: 1
			}
		}
	} else {
		$("#catProd" + p).html(
				'<option selected="selected" value="0">Cargando...</option>');
		q.style.display = 'block';
		q.disabled = true;
		var s = (p == 2 ? 3 : (p == 3 ? 4 : (p == 4 ? 5 : (p == 5 ? 6 : 1))));
		var t = '${pageContext.request.contextPath}/vinculacion/comprador/compradorRequerimientoAdd.do?cat'
				+ (p - 1) + '=' + n;
		if (o) {
			t = '${pageContext.request.contextPath}/vinculacion/tractora/administracion/tractoraRequerimientoAdd.do?cat'
					+ (p - 1) + '=' + n
		}
		peticion.open("GET", t, true);
		peticion.onreadystatechange = function() {
			if (peticion.readyState == 4 && peticion.status == 200) {
				var a = peticion.responseText;
				var b = a.split('\<');
				var x = 1;
				for ( var i = 1; i < b.length; i++) {
					var c = b[i];
					if (c.substring(0, 6) == 'option') {
						var d = c.indexOf('>') + 1;
						var e = c.length;
						var f = c.substring(d, e);
						var g = c.split(' ');
						for ( var j = 1; j < g.length; j++) {
							var h = g[j];
							if (h.substring(0, 5) == 'value') {
								var l = h.split('\"');
								for ( var k = 1; k < l.length; k++) {
									var m = l[k];
									if (m.length == s && !isNaN(m)) {
										q.options[0] = new Option(
												'--Seleccione una opción--', 0);
										q.options[x] = new Option(f, m);
										q.options[0].selected = true;
										x++
									}
								}
							}
						}
					}
				}
				document.getElementById('catProd' + p).disabled = false
			}
		};
		peticion.send(null)
	}
}
function busqueda(b) {
	var c = document.getElementById('idCampoBusqueda').value;
	if (c == '') {
		alert('Capture el texto para realizar una búsquda.');
		document.getElementById('idCampoBusqueda').focus()
	} else {
		document.getElementById('idCampoBusqueda').value = 'Buscando...';
		document.getElementById('idCampoBusqueda').disabled = true;
		var d = '${pageContext.request.contextPath}/vinculacion/comprador/compradorRequerimientoBusqueda.do?chain='
				+ c;
		if (b) {
			d = '${pageContext.request.contextPath}/vinculacion/tractora/administracion/tractoraRequerimientoBusqueda.do?chain='
					+ c
		}
		peticion.open("GET", d, true);
		peticion.onreadystatechange = function() {
			if (peticion.readyState == 4 && peticion.status == 200) {
				var a = peticion.responseText;
				a = a.substring(a.indexOf('ResBusCatSCIANCCMX...') + 26, a
						.lastIndexOf('ResBusCatSCIANCCMX...') - 4);
				document.getElementById('idCampoBusqueda').value = c;
				document.getElementById('idCampoBusqueda').disabled = false;
				document.getElementById('idBusResTit').innerText = 'Resultados del texto: [ '
						+ c + ' ]';
				document.getElementById('idBtnBuscar').click();
				$("#idDivResultados").html(a)
			}
		};
		peticion.send(null)
	}
}
function anterior() {
	if (document.getElementById('idDivRes1') != null) {
		document.getElementById('idBtnSiguiente').style.display = 'block';
		var a = 1;
		while (a > 0) {
			if (document.getElementById('idDivRes' + a) == null) {
				a = 0
			} else {
				if (document.getElementById('idDivRes' + (a - 1)) != null
						&& document.getElementById('idDivRes' + a).style.display == 'block') {
					document.getElementById('idDivRes' + a).style.display = 'none';
					document.getElementById('idDivRes' + (a - 1)).style.display = 'block'
				}
				a++
			}
		}
	}
}
function siguiente() {
	if (document.getElementById('idDivRes1') != null) {
		var a = 1;
		var b = 0;
		while (a > 0) {
			if (document.getElementById('idDivRes' + a) == null) {
				break
			} else {
				if (document.getElementById('idDivRes' + a).style.display == 'block')
					b = a;
				a++
			}
		}
		a = 1;
		if (document.getElementById('idDivRes' + (b + 1)) != null) {
			document.getElementById('idDivRes' + b).style.display = 'none';
			document.getElementById('idDivRes' + (b + 1)).style.display = 'block'
		}
	}
}
function pagina() {
	var a = 1;
	var b = 0;
	while (a > 0) {
		if (document.getElementById('idDivRes' + a) == null) {
			break
		} else {
			if (document.getElementById('idDivRes' + a).style.display == 'block')
				b = a;
			a++
		}
	}
	return b
}
function elegir(a) {
	var b = pagina();
	cveBusqueda = document.getElementById('idHidResCveScian' + b).value;
	var c = document.getElementById('idHidResDescScian' + b).value;
	document.getElementById('idInputCatScian').value = c;
	document.getElementById('idCveSci').value = cveBusqueda;
	document.getElementById('idInputCatScian').rows = c.length > 85 ? 2 : 1;
	setTimeout("nextCombo(" + cveBusqueda + ", 1, " + a + ")", 1000);
	setTimeout("step(1, " + a + ")", 1500)
}
function step(a, b) {
	var c = cveBusqueda;
	if (document.getElementById('catProd' + a).value == -1)
		setTimeout(("step(" + a + ", " + b + ");"), 1000);
	else {
		if (document.getElementById('catProd' + a).options.length > 1) {
			setTimeout("showCombo(" + c.substring(0, a + 1) + ", " + b + ", "
					+ (a + 1) + ")", 200);
			setTimeout("nextCombo(" + c + ", " + (a + 1) + ", " + b + ")", 600)
		} else
			setTimeout(("step(" + a + ", " + b + ");"), 1000)
	}
}
function nextCombo(a, b, c) {
	var d = document.getElementById('catProd' + b).length;
	if (b > 1 && document.getElementById('catProd' + b).options.length == 1)
		setTimeout(("nextCombo(" + a + ", " + b + ", " + c + ");"), 1000);
	else {
		for ( var i = 0; i < d; i++) {
			if (document.getElementById('catProd' + b).options[i].value == (a + '')
					.substring(0, b + 1)) {
				document.getElementById('catProd' + b).options[i].selected = true;
				document.getElementById('catProd' + b).options[i].click()
			}
		}
		if (b > 1 && b < 5) {
			setTimeout(("step(" + b + ", " + c + ");"), 1000)
		}
	}
}
function getTelefono(a, b, c) {
	return '(52)(' + a + ')(' + b + ')(' + c + ')'
}
function agregaTelefono() {
	_l = document.getElementById("ladaTel").value;
	_t = document.getElementById("numTel").value;
	_e = document.getElementById("extTel").value;
	var a = 0;
	if (_l.length < 2 || /^\s+$/.test(_l)) {
		document.getElementById("ladaTel").focus();
		alert("El campo lada debe contener dos o tres dígitos.");
		return false
	} else if (_l.length == 2 && _t.length != 8 || /^\s+$/.test(_t)) {
		document.getElementById("numTel").focus();
		alert("El campo Teléfono debe de contener ocho dígitos");
		return false
	} else if (_l.length == 3 && _t.length != 7 || /^\s+$/.test(_t)) {
		document.getElementById("numTel").focus();
		alert("El campo Teléfono debe de contener siete dígitos");
		return false
	} else {
		_tel = getTelefono(_l, _t, _e);
		for ( var i = 1; i <= 10; i++) {
			if (document.getElementById('idDivTel' + i).style.display == 'block')
				a++
		}
		if (a < 10) {
			var b = a + 1;
			document.getElementById('idTelHid' + b).value = _tel;
			document.getElementById('labTel' + b).innerText = _tel;
			document.getElementById('idDivTel' + b).style.display = 'block'
		}
		document.getElementById("ladaTel").value = null;
		document.getElementById("numTel").value = null;
		document.getElementById("extTel").value = null
	}
	document.getElementById('ladaTel').focus()
}
function quitarTelefono(a) {
	var b = a;
	if (a == '10'
			|| document.getElementById('idDivTel' + (a + 1)).style.display == 'none') {
		document.getElementById('labTel' + a).innerText = null;
		document.getElementById('idTelHid' + a).value = null;
		document.getElementById('idDivTel' + a).style.display = 'none'
	} else {
		for ( var i = a; i <= 10; i++) {
			if (document.getElementById('idDivTel' + i).style.display == 'block')
				b++;
			if (document.getElementById('labTel' + (i + 1)) != null)
				document.getElementById('labTel' + i).innerText = document
						.getElementById('labTel' + (i + 1)).innerText;
			if (document.getElementById('idTelHid' + (i + 1)) != null)
				document.getElementById('idTelHid' + i).value = document
						.getElementById('idTelHid' + (i + 1)).value
		}
		document.getElementById('idDivTel' + (b - 1)).style.display = 'none'
	}
}
function cambiaCampo(e) {
	var a = (e.keyCode ? e.keyCode : e.which);
	if (a != 8 && a != 37 && a != 39) {
		if (document.getElementById('ladaTel').value.length == 2) {
			document.getElementById('numTel').focus()
		}
		if (document.getElementById('numTel').value.length == 8) {
			document.getElementById('extTel').focus()
		}
	}
}
function agregaEstado() {
	var a = document.getElementById("idCampoLugarSuministro").options.length;
	var b = '';
	var c = document.getElementById('idDesEdo').value;
	for ( var x = 0; x < a; x++) {
		if (document.getElementById("idCampoLugarSuministro").options[x].selected)
			b = document.getElementById("idCampoLugarSuministro").options[x].value
	}
	var d = 0;
	for ( var i = 1; i <= 10; i++) {
		if (document.getElementById('idDivEdo' + i).style.display == 'block')
			d++
	}
	if (d < 10) {
		var e = d + 1;
		document.getElementById('idEdoHid' + e).value = b;
		document.getElementById('idEdoDesHid' + e).value = c;
		document.getElementById('labEdo' + e).innerText = b
				+ (c != '' ? (', (' + c + ')') : '');
		document.getElementById('idDivEdo' + e).style.display = 'block'
	}
	document.getElementById("idCampoLugarSuministro").options[0].selected = true;
	document.getElementById('idCampoLugarSuministro').focus();
	document.getElementById('idDesEdo').value = ''
}
function quitarEstado(a) {
	var b = a;
	if (a == '10'
			|| document.getElementById('idDivEdo' + (a + 1)).style.display == 'none') {
		document.getElementById('labEdo' + a).innerText = null;
		document.getElementById('idEdoHid' + a).value = null;
		document.getElementById('idEdoDesHid' + a).value = null;
		document.getElementById('idDivEdo' + a).style.display = 'none'
	} else {
		for ( var i = a; i <= 10; i++) {
			if (document.getElementById('idDivEdo' + i).style.display == 'block')
				b++;
			if (document.getElementById('labEdo' + (i + 1)) != null)
				document.getElementById('labEdo' + i).innerText = document
						.getElementById('labEdo' + (i + 1)).innerText;
			if (document.getElementById('idEdoHid' + (i + 1)) != null)
				document.getElementById('idEdoHid' + i).value = document
						.getElementById('idEdoHid' + (i + 1)).value;
			if (document.getElementById('idEdoDesHid' + (i + 1)) != null)
				document.getElementById('idEdoDesHid' + i).value = document
						.getElementById('idEdoDesHid' + (i + 1)).value
		}
		document.getElementById('idDivEdo' + (b - 1)).style.display = 'none'
	}
}
function calendario() {
	Calendar.setup({
		inputField : "ingreso",
		ifFormat : "%d/%m/%Y",
		button : "lanzador"
	});
	Calendar.setup({
		inputField : "ingreso2",
		ifFormat : "%d/%m/%Y",
		button : "lanzador2"
	})
}
function cancela() {
	document.frmCancela.submit()
}
function modificar() {
	calendario();
	document.getElementById('sec2').style.display = 'none';
	document.getElementById('secR').style.display = 'none';
	document.getElementById('sec1').style.display = 'block'
}
function modificarDatos() {
	document.getElementById('sec2').style.display = 'none';
	document.getElementById('secR').style.display = 'none';
	document.getElementById('sec1').style.display = 'block'
}
function lugarSuministro() {
	var a = document.getElementById('idInput').value;
	var b = ', ';
	if (a.indexOf(" ") != -1)
		document.getElementById('idInput').value = a + b
				+ document.getElementById('idCampoLugarSuministro').value;
	else
		document.getElementById('idInput').value = a + ' '
				+ document.getElementById('idCampoLugarSuministro').value;
	document.getElementById('idInput').size = document
			.getElementById('idInput').value.length;
	document.getElementById('lugarSum').style.display = 'block'
}
function contado() {
	document.getElementById('plazo').style.display = 'none';
	document.getElementById('checkcredito').checked = false;
	document.getElementById('checkquince').checked = false;
	document.getElementById('checktreinta').checked = false;
	document.getElementById('checksesenta').checked = false;
	document.getElementById('checknoventa').checked = false
}
function credito() {
	if (document.getElementById('checkcredito').checked == true)
		document.getElementById('plazo').style.display = 'block';
	else
		document.getElementById('plazo').style.display = 'none';
	document.getElementById('checkcontado').checked = false
}
function limpiaFechaSuministro() {
	if (document.getElementById('expiracontinuo').checked == true)
		document.getElementById('ingreso2').value = ''
}
function limpiaFechaExpira(a) {
	if (document.getElementById('indefinido').checked == true
			|| document.getElementById('variasfechas').checked == true
			|| document.getElementById('suministrocontinuo').checked == true)
		document.getElementById('ingreso').value = '';
	if (a == '1') {
		document.getElementById('variasfechas').checked = false;
		document.getElementById('suministrocontinuo').checked = false;
		document.getElementById("idDivDetalleVariasFechas").style.display = 'none'
	}
	if (a == '2') {
		document.getElementById('indefinido').checked = false;
		document.getElementById('suministrocontinuo').checked = false;
		if (document.getElementById('variasfechas').checked == false) {
			document.getElementById("idDivDetalleVariasFechas").style.display = 'none'
		} else {
			document.getElementById("idDivDetalleVariasFechas").style.display = 'block'
		}
	}
	if (a == '3') {
		document.getElementById('indefinido').checked = false;
		document.getElementById('variasfechas').checked = false;
		document.getElementById("idDivDetalleVariasFechas").style.display = 'none'
	}
}
function limpiaCheckSuministro() {
	if (document.getElementById('ingreso').value != '') {
		document.getElementById('indefinido').checked = false;
		document.getElementById('variasfechas').checked = false;
		document.getElementById('suministrocontinuo').checked = false;
		document.getElementById("idDivDetalleVariasFechas").style.display = 'none'
	}
}
function limpiaCheckExpira() {
	if (document.getElementById('ingreso2').value != '')
		document.getElementById('expiracontinuo').checked = false
}
function limpiaCheckCredito(a) {
	if (a == '15') {
		document.getElementById('checktreinta').checked = false;
		document.getElementById('checksesenta').checked = false;
		document.getElementById('checknoventa').checked = false
	}
	if (a == '30') {
		document.getElementById('checkquince').checked = false;
		document.getElementById('checksesenta').checked = false;
		document.getElementById('checknoventa').checked = false
	}
	if (a == '60') {
		document.getElementById('checkquince').checked = false;
		document.getElementById('checktreinta').checked = false;
		document.getElementById('checknoventa').checked = false
	}
	if (a == '90') {
		document.getElementById('checkquince').checked = false;
		document.getElementById('checktreinta').checked = false;
		document.getElementById('checksesenta').checked = false
	}
}
function focoAyudaBusqueda(a) {
	document.getElementById(a).style.display = 'block';
	document.getElementById(a + '2').style.display = 'none'
}
function focoAyuda(a) {
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
	document.getElementById(a).style.display = 'block';
	document.getElementById(a + '2').style.display = 'none'
}
function blurAyuda(a) {
	document.getElementById(a).style.display = 'none';
	document.getElementById(a + '2').style.display = 'block'
}
function otroArchivo() {
	var a = 1;
	for ( var i = 1; i < 11; i++) {
		_b = document.getElementById('idDivArchivo' + i + 'Block').style.display;
		_n = document.getElementById('idDivArchivo' + i + 'None').style.display;
		if (_b == 'block' || _n == 'block') {
			a++
		}
	}
	document.getElementById('idDivArchivo' + a + 'Block').style.display = 'block'
}
function supArchivo(a) {
	document.getElementById('idCampoArchivo' + a).value = '';
	document.getElementById('idDivArchivo' + a + 'Block').style.display = 'none'
}
function muestraAsignar() {
	if (document.frmAsignacion.checkbox == undefined)
		return;
	var a = document.frmAsignacion.checkbox.length == undefined ? 1
			: document.frmAsignacion.checkbox.length;
	var b = false;
	if (a == 1 && document.frmAsignacion.checkbox.checked)
		b = true;
	else if (a > 1)
		for ( var i = 0; i < a; i++)
			if (document.frmAsignacion.checkbox[i].checked)
				b = true;
	if (b)
		document.getElementById('idDivSelAsiCom').style.display = 'block';
	else
		alert('Seleccione por lo menos una PyME.')
}
function asignaComprador() {
	var a = document.frmAsignacion.checkbox.length == undefined ? 1
			: document.frmAsignacion.checkbox.length;
	var b = '';
	var c = false;
	var d = false;
	if (a == 1 && document.frmAsignacion.checkbox.checked) {
		b = document.frmAsignacion.checkbox.id.substring(8);
		c = true
	} else if (a > 1)
		for ( var i = 0; i < a; i++)
			if (document.frmAsignacion.checkbox[i].checked) {
				b = b + document.frmAsignacion.checkbox[i].id.substring(8)
						+ ',';
				c = true
			}
	if (!c)
		alert('Seleccione por lo menos una PyME.');
	else {
		if (b.length > 0 && b.substring(b.length - 1, b.length) == ',')
			b = b.substring(0, b.length - 1);
		document.frmAsignacion.idHidIdPyMEs.value = b;
		a = document.getElementById('idCompradorSeleccionado').options.length == undefined ? 0
				: document.getElementById('idCompradorSeleccionado').options.length;
		for ( var j = 0; j < a; j++)
			if (document.getElementById('idCompradorSeleccionado').options[j].selected
					&& document.getElementById('idCompradorSeleccionado').options[j].value != '-1') {
				document.frmAsignacion.idHidIdComprador.value = document
						.getElementById('idCompradorSeleccionado').options[j].value;
				d = true
			}
		if (!d) {
			alert('Seleccione el Comprador al que serán asignadas las PyMEs.')
		} else {
			document.getElementById('idProcesa').style.display = 'block';
			document.frmAsignacion.submit()
		}
	}
}
function todas() {
	var a = 0;
	try {
		a = document.frmAsignacion.checkbox.length
	} catch (e) {
	}
	a = a == undefined ? 1 : a;
	if (a == 1)
		document.frmAsignacion.checkbox.checked = seleccion ? false : true;
	else if (a > 0)
		for ( var i = 0; i < a; i++)
			document.frmAsignacion.checkbox[i].checked = seleccion ? false
					: true;
	seleccion = !seleccion;
	return false
}
function validaNumero(a) {
	var b = (document.all) ? a.keyCode : a.which;
	return (b <= 13 || (b >= 48 && b <= 57) || b == 46)
}
function validacionBusqueda() {
	valorBusq = document.getElementById("campoBusqueda").value.split(" ");
	document.getElementById('idProd').value = document
			.getElementById('idInputCatScian').value;
	if (valorBusq == null || valorBusq == 0 || valorBusq.length > 3
			|| valorBusq == " ") {
		document.getElementById("campoBusqueda").focus();
		alert("Para realizar una búsqueda escriba en 3 palabras el producto");
		return false
	} else {
		return true
	}
}
function validacion(a) {
	document.getElementById('idFecSum').value = document
			.getElementById('ingreso').value;
	document.getElementById('idFecExp').value = document
			.getElementById('ingreso2').value;
	b = document.getElementById("idCampoProducto").value;
	c = document.getElementById("idInputCatScian").value;
	d = document.getElementById('ingreso').value;
	e = document.getElementById("ingreso2").value;
	if (a == '1') {
		if (b == null || b.length == 0 || /^\s+$/.test(b)) {
			alert("Ingrese el producto solicitado");
			document.getElementById("idCampoProducto").focus();
			return false
		} else if (c == null || c.length == 0 || /^\s+$/.test(c)) {
			alert("Seleccione o búsque la categoría del tipo de producto");
			document.getElementById("idCatCcmx1").focus();
			return false
		} else {
			document.getElementById('sec1').style.display = 'none';
			document.getElementById('sec2').style.display = 'block';
			return true
		}
	} else {
		if (document.getElementById("idDivEdo1").style.display == 'none') {
			document.getElementById("idCampoLugarSuministro").focus();
			alert("Agregue al menos un lugar de suministro mediante la opción '+agregarlo'");
			return false
		} else if (!isDate(d)
				&& (document.getElementById('indefinido').checked == false
						&& document.getElementById('variasfechas').checked == false && document
						.getElementById('suministrocontinuo').checked == false)) {
			document.getElementById('ingreso').focus();
			alert("Ingrese la fecha de suministro o seleccione una opción");
			return false
		} else if (!isDate(e)
				&& (document.getElementById('expiracontinuo').checked == false)) {
			document.getElementById("ingreso2").focus();
			alert("Ingrese la fecha en que exira el requerimiento o seleccione una opción");
			return false
		} else {
			document.getElementById('idTipoPro').value = document
					.getElementById('idInputCatScian').value;
			for ( var i = 1; i <= 10; i++) {
				try {
					if (document.getElementById('idDivArc' + i).style.display == 'none') {
						document.getElementById('filArc' + (i - 1)).name = null;
						document.getElementById('idArcHid' + (i - 1)).value = null
					}
				} catch (e) {
				}
			}
			document.getElementById('idProcesa').style.display = 'block';
			return true
		}
	}
}
function validaDatosTractora(a, b) {
	c = document.getElementById("idEmpresa").value;
	d = document.getElementById("idNombre").value;
	e = document.getElementById("idAppPaterno").value;
	f = document.getElementById("idAppMaterno").value;
	g = document.getElementById("idPuesto").value;
	h = document.getElementById("ladaTel").value;
	i = document.getElementById("numTel").value;
	j = document.getElementById("extTel").value;
	k = document.getElementById("idCalle").value;
	l = document.getElementById("idNumExt").value;
	m = document.getElementById("idColonia").value;
	n = document.getElementById("idDelegacion").value;
	o = document.getElementById("idEstado").selectedIndex;
	p = document.getElementById("idCodigoPostal").value;
	if (a == '1') {
		if (c == null || c.length == 0 || /^\s+$/.test(c)) {
			document.getElementById("idEmpresa").focus();
			alert("Ingrese en Nombre de la Empresa");
			return false
		} else if (d == null || d.length == 0 || /^\s+$/.test(d)) {
			document.getElementById("idNombre").focus();
			alert("Ingrese el Nombre(s) requerido");
			return false
		} else if (e == null || e.length == 0 || /^\s+$/.test(e)) {
			document.getElementById("idAppPaterno").focus();
			alert("Ingrese Apellido Paterno");
			return false
		} else if (f == null || f.length == 0 || /^\s+$/.test(f)) {
			document.getElementById("idAppMaterno").focus();
			alert("Ingrese Apellido Materno");
			return false
		} else if (g == null || g.length == 0 || /^\s+$/.test(g)) {
			document.getElementById("idPuesto").focus();
			alert("Ingrese su puesto");
			return false
		} else if (document.getElementById('idDivTel1').style.display == 'none'
				&& (h.length < 2 || /^\s+$/.test(h))) {
			document.getElementById("ladaTel").focus();
			alert("El campo lada debe contener dos o tres dígitos.");
			return false
		} else if (document.getElementById('idDivTel1').style.display == 'none'
				&& (h.length == 2 && i.length != 8 || /^\s+$/.test(i))) {
			document.getElementById("numTel").focus();
			alert("El campo Teléfono debe contener ocho dígitos.");
			return false
		} else if (document.getElementById('idDivTel1').style.display == 'none'
				&& (h.length == 3 && i.length != 7 || /^\s+$/.test(i))) {
			document.getElementById("numTel").focus();
			alert("El campo Teléfono debe contener siete dígitos.");
			return false
		} else {
			if (!b) {
				document.getElementById('sec1').style.display = 'none';
				document.getElementById('sec2').style.display = 'block'
			} else {
				document.getElementById('idProcesa').style.display = 'block'
			}
			if (document.getElementById('idDivTel1').style.display == 'none') {
				document.getElementById('idTelHid1').value = getTelefono(h, i,
						j)
			}
			return true
		}
	} else if (a == '2') {
		if (k == null || k.length == 0 || /^\s+$/.test(k)) {
			document.getElementById("idCalle").focus();
			alert("Ingrese la calle");
			return false
		} else if (l == null || l.length == 0 || /^\s+$/.test(l)) {
			document.getElementById("idNumExt").focus();
			alert("Ingrese el número exterior");
			return false
		} else if (m == null || m.length == 0 || /^\s+$/.test(m)) {
			document.getElementById("idColonia").focus();
			alert("Ingrese la colonia");
			return false
		} else if (n == null || n.length == 0 || /^\s+$/.test(n)) {
			document.getElementById("idDelegacion").focus();
			alert("Ingrese la delegación");
			return false
		} else if (o == " " || o == 0 || o == null) {
			document.getElementById("idEstado").focus();
			alert("Seleccione un Estado");
			return false
		} else if (p == null || p.length == 0 || /^\s+$/.test(p)) {
			document.getElementById("idCodigoPostal").focus();
			alert("Ingrese el Código Postal");
			return false
		} else {
			document.getElementById('idProcesa').style.display = 'block';
			return true
		}
	}
}
function isDate(e) {
	var f = "/";
	function isInteger(s) {
		var i;
		for (i = 0; i < s.length; i++) {
			var c = s.charAt(i);
			if (((c < "0") || (c > "9")))
				return false
		}
		return true
	}
	function stripCharsInBag(s, a) {
		var i;
		var b = "";
		for (i = 0; i < s.length; i++) {
			var c = s.charAt(i);
			if (a.indexOf(c) == -1)
				b += c
		}
		return b
	}
	function posCharsInBags(s, a) {
		var i;
		var b = false;
		var d = false;
		for (i = 0; i < s.length; i++) {
			var c = s.charAt(i);
			if (i == 2 && c == f)
				b = true;
			if (i == 5 && c == f)
				d = true
		}
		return b && d
	}
	var g = stripCharsInBag(e, f);
	if (e.length != 10 || g.length != 8) {
		return false
	}
	if (!posCharsInBags(e, f)) {
		return false
	}
	if (isInteger(g) == false) {
		return false
	}
	return true
}