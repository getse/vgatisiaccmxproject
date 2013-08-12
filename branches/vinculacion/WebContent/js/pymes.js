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

function showCombo(cat, next) {
	var combo = document.getElementById('catProd' + next);
	if (next == 6) {
		var _combo = document.getElementById('catProd5');
		for ( var i = 0; i < _combo.length; i++) {
			_value = _combo.options[i].value;
			_text = _combo.options[i].text;
			if (_value == cat) {
				var _cat = 0;
				for ( var i = 1; i <= 10; i++) {
					if (document.getElementById('idDivCat' + i).style.display == 'block')
						_cat = i + 1;
				}
				if (_cat == 0)
					_cat = 1;
				if (_cat < 11) {
					document.getElementById('idDivCat' + _cat).style.display = 'block';
					document.getElementById('labCat' + _cat).innerText = _text;
					document.getElementById('idCatHid' + _cat).value = _value;
					document.getElementById('idDesCatHid' + _cat).value = _text;
				}
			}
		}
		for ( var j = 1; j <= 5; j++)
			document.getElementById('catProd' + j).options[0].selected = 'selected';
	} else {
		$("#catProd" + next).html(
				'<option selected="selected" value="0">Cargando...</option>');
		combo.style.display = 'block';
		combo.disabled = true;

		var size = (next == 2 ? 3 : (next == 3 ? 4 : (next == 4 ? 5
				: (next == 5 ? 6 : 1))));
		var url = '${pageContext.request.contextPath}/vinculacion/pyme/pymeBusquedaShow.do?cat'
				+ (next - 1) + '=' + cat;
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

function cargaCategoria(_cat, _text, _value) {
	if (_value != 0) {
		document.getElementById('idDivCat' + _cat).style.display = 'block';
		document.getElementById('labCat' + _cat).innerText = _text;
		document.getElementById('idCatHid' + _cat).value = _value;
		document.getElementById('idDesCatHid' + _cat).value = _text;
	}
}

function quitarCategoria(pos) {
	var _last = pos;
	if (pos == '10'
			|| document.getElementById('idDivCat' + (pos + 1)).style.display == 'none') {
		document.getElementById('labCat' + pos).innerText = '';
		document.getElementById('idCatHid' + pos).value = null;
		document.getElementById('idDesCatHid' + pos).value = null;
		document.getElementById('idDivCat' + pos).style.display = 'none';
	} else {
		for ( var i = pos; i <= 10; i++) {
			if (document.getElementById('idDivCat' + i).style.display == 'block')
				_last++;
			if (document.getElementById('labCat' + (i + 1)) != null)
				_lab = document.getElementById('labCat' + (i + 1)).innerText;
			if (document.getElementById('idCatHid' + (i + 1)) != null)
				_hid = document.getElementById('idCatHid' + (i + 1)).value;
			if (document.getElementById('idDesCatHid' + (i + 1)) != null)
				_hidDes = document.getElementById('idDesCatHid' + (i + 1)).value;
			document.getElementById('labCat' + i).innerText = _lab;
			document.getElementById('idCatHid' + i).value = _hid;
			document.getElementById('idDesCatHid' + i).value = _hidDes;
		}
		document.getElementById('idDivCat' + (_last - 1)).style.display = 'none';
	}
}

function focoAyuda(id) {
	document.getElementById(id).style.display = 'block';
	document.getElementById(id + '2').style.display = 'none';
}

function blurAyuda(id) {
	document.getElementById(id).style.display = 'none';
	document.getElementById(id + '2').style.display = 'block';
}

function modificar() {
	document.getElementById('sec1').style.display = 'block';
	document.getElementById('resumenPyME').style.display = 'none';
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

function validacion(sec) {

	valorPerJuridica = document.getElementById("personalidadJuridica").selectedIndex;
	valorRFC = document.getElementById("rfc").value;
	valorCorreo = document.getElementById("correoElectronico").value;
	// valorCompara = document.getElementById("comparaCorreo").value;

	valorNomCom = document.getElementById("nombreComercial").value;
	valorMsjVenta = document.getElementById("mensajeVenta").value;
	valorCalle = document.getElementById("calle").value;
	valorNumExt = document.getElementById("numExt").value;
	valorColonia = document.getElementById("colonia").value;
	valorDelegacion = document.getElementById("delegacion").value;
	valorEstado = document.getElementById("estado").selectedIndex;
	valorCodigoPostal = document.getElementById("codigoPostal").value;
	valorSectorUno = document.getElementById("sector1");
	valorSectorDos = document.getElementById("sector2");
	valorSectorTres = document.getElementById("sector3");

	valorCliente = document.getElementById("cliente").value;
	valorProdCliente = document.getElementById("showProdCliente").value;
	valorAniosProveCliente = document.getElementById("aniosProveCliente").value;
	valorMesesProveCliente = document.getElementById("mesesProveCliente").value;

	var ingresosAntes = document.getElementById("ingresosAnt").value;
	var clientesAntes = document.getElementById("clientesAnt").value;
	var empleadosAntes = document.getElementById("empleadosAnt").value;
	var egresosAnt = document.getElementById("egresosAnt").value;
	var ingresosDespues = document.getElementById("ingresosDesp").value;
	var clientesDespues = document.getElementById("clientesDesp").value;
	var empleadosDespues = document.getElementById("empleadosDesp").value;
	var egresosDespues = document.getElementById("egresosDesp").value;

	if (sec == '1') {

		if (valorPerJuridica == 0 || valorPerJuridica == "-- Seleccione --") {
			document.getElementById("personalidadJuridica").focus();
			alert("Seleccione el tipo de Personalidad Juridica");
			return false;
		}

		if (valorRFC.length != 0) {
			if (document.getElementById("personalidadJuridica").value == "Persona Moral") {
				if (!(/^[a-zA-Z]{3}\d{6}\w{3}$/.test(valorRFC))) {
					document.getElementById("rfc").focus();
					alert("Ingrese un RFC válido");
					return false;
				}
			} else if (document.getElementById("personalidadJuridica").value == "Persona Fisica") {
				if (!(/^[a-zA-Z]{4}\d{6}\w{3}$/.test(valorRFC))) {
					alert("Ingrese un RFC válido");
					return false;
				}
			}
		}

		if (!(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
				.test(valorCorreo))) {
			document.getElementById("correoElectronico").focus();
			alert("Ingrese una dirección de correo electrónico válida");
			return false;
		} /*
			 * else if (valorCorreo != valorCompara) {
			 * document.getElementById("comparaCorreo").focus(); alert("El
			 * correo electrónico no coincide"); return false; }
			 */else {
			document.getElementById('sec1').style.display = 'none';
			// document.getElementById('sec2').style.display = 'block';
			document.getElementById('idBtnTerminosCondiciones').click();
			document.getElementById('idDivAvisoPrivacidad2').style.display = 'none';
			if (document.getElementById('personalidadJuridica')[1].selected)
				document.getElementById('idDivLiberacionResponsabilidad').style.display = 'block';
			if (document.getElementById('personalidadJuridica')[2].selected)
				document.getElementById('idDivAvisoPrivacidad').style.display = 'block';
			return true;
		}
	} else if (sec == '2') {

		if (valorNomCom == null || valorNomCom.length == 0
				|| /^\s+$/.test(valorNomCom)) {
			document.getElementById("nombreComercial").focus();
			alert("Ingrese el nombre comercial");
			return false;
		} else if (valorMsjVenta == null || valorMsjVenta.length == 0
				|| /^\s+$/.test(valorMsjVenta)) {
			document.getElementById("mensajeVenta").focus();
			alert("Ingrese Mensaje de ventas");
			return false;
		} else if (valorCalle == null || valorCalle.length == 0
				|| /^\s+$/.test(valorCalle)) {
			document.getElementById("calle").focus();
			alert("Ingrese la calle");
			return false;
		} else if (valorNumExt == null || valorNumExt.length == 0
				|| /^\s+$/.test(valorNumExt)) {
			document.getElementById("numExt").focus();
			alert("Ingrese el Número exterior");
			return false;
		} else if (valorColonia == null || valorColonia.length == 0
				|| /^\s+$/.test(valorColonia)) {
			document.getElementById("colonia").focus();
			alert("Ingrese la colonia");
			return false;
		} else if (valorDelegacion == null || valorDelegacion.length == 0
				|| /^\s+$/.test(valorDelegacion)) {
			document.getElementById("delegacion").focus();
			alert("Ingrese la delegación");
			return false;
		} else if (valorEstado == 0) {
			document.getElementById("estado").focus();
			alert("Seleccione un Estado");
			return false;
		} else if (valorCodigoPostal == null || valorCodigoPostal.length == 0
				|| /^\s+$/.test(valorCodigoPostal)) {
			document.getElementById("codigoPostal").focus();
			alert("Ingrese el Código Postal");
			return false;
		}
		// Valida productos
		if (document.getElementById('tablaProd').style.display == 'none') {
			document.getElementById("idProducto").focus();
			alert("Ingrese un producto mediante el botón de '+agregar'");
			return false;
		}

		if (valorSectorUno.checked
				&& (!valorSectorDos.checked && !valorSectorTres.checked)) {
			document.getElementById('sec2').style.display = 'none';
			document.getElementById('sec3').style.display = 'block';
			return true;
		} else if (valorSectorDos.checked
				&& (!valorSectorUno.checked && !valorSectorTres.checked)) {
			document.getElementById('sec2').style.display = 'none';
			document.getElementById('sec3').style.display = 'block';
			return true;
		} else if (valorSectorTres.checked
				&& (!valorSectorUno.checked && !valorSectorDos.checked)) {
			document.getElementById('sec2').style.display = 'none';
			document.getElementById('sec3').style.display = 'block';
			return true;
		} else {
			document.getElementById("sector1").focus();
			alert("Selecione una categoría a la que pertenece su empresa.");
			return false;
		}
	} else if (sec == '3') {

		var estados = 0;
		var valNal = document.getElementById('checkNacional');
		for ( var x = 1; x < 33; x++) {
			check = document.getElementById('check' + x);
			if (check.checked) {
				estados++;
				break;
			}
		}

		if (estados == 0 && !valNal.checked) {
			alert('Seleccione por lo menos una opción');
			return false;
		} else {
			document.getElementById('sec3').style.display = 'none';
			document.getElementById('sec4').style.display = 'block';
			return true;
		}

	} else if (sec == '4') {

		var tipo = document.getElementById("tipoHid1").value;
		var nom = document.getElementById("nombreHid1").value;
		var pat = document.getElementById("apPatHid1").value;
		var mat = document.getElementById("apMatHid1").value;
		var correo = document.getElementById("correoHid1").value;
		var telC = document.getElementById("telHid1").value;

		if (tipo.length == 0 || nom.length == 0 || pat.length == 0
				|| mat.length == 0 || correo.length == 0 || telC == 'null') {
			alert("Complemente los datos de su contacto utilizando el botón 'editar'");
			return false;
		}

		document.getElementById('sec4').style.display = 'none';
		document.getElementById('sec5').style.display = 'block';
		return true;

	} else if (sec == '5') {

		var c = document.getElementById('clienteHid1').value;
		var p = document.getElementById('prodCompHid1').value;
		var a = document.getElementById('anioHid1').value;
		var m = document.getElementById('mesesHid1').value;
		if (c.length == 0 || p.length == 0 || a.length == 0 || m.length == 0) {
			alert("Complemente los datos de su cliente Tractora utilizando el botón 'editar'");
			return false;
		}

		if (valorCliente.length > 0 || /^\s+$/.test(valorCliente)) {
			document.getElementById("cliente").focus();
			alert("Registre el cliente ingresado en los campos mediante el botón '+registrar cliente'");
			return false;
		} else if (document.getElementById("prodCliente").value.length > 0
				|| /^\s+$/.test(document.getElementById("prodCliente").value)) {
			document.getElementById("prodCliente").focus();
			alert("Agregue el Producto mediante el botón '+agregar producto'");
			return false;
		} else if (valorProdCliente.length > 0
				|| /^\s+$/.test(valorProdCliente)) {
			document.getElementById("prodCliente").focus();
			alert("Registre el cliente ingresado en los campos mediante el botón '+registrar cliente' o elimine el producto agregado");
			return false;
		} else if (valorAniosProveCliente.length > 0
				|| /^\s+$/.test(valorAniosProveCliente)
				|| isNaN(valorAniosProveCliente)) {
			document.getElementById("aniosProveCliente").focus();
			alert("Registre el cliente ingresado en los campos mediante el botón '+registrar cliente'");
			return false;
		} else if (valorMesesProveCliente.length > 0
				|| /^\s+$/.test(valorMesesProveCliente)
				|| isNaN(valorMesesProveCliente)) {
			document.getElementById("mesesProveCliente").focus();
			alert("Registre el cliente ingresado en los campos mediante el botón '+registrar cliente'");
			return false;
		}

		document.getElementById('sec5').style.display = 'none';
		document.getElementById('sec6').style.display = 'block';
		return true;
	} else if (sec == '6') {
		document.getElementById('sec6').style.display = 'none';
		document.getElementById('sec7').style.display = 'block';
		return true;
	} else {

		if (ingresosAntes.length > 0) {
			if (isNaN(ingresosAntes)) {
				document.getElementById('ingresosAnt').focus();
				alert("El valor de Ventas o ingresos acumulados (antes) debe ser ingresado con números");
				return false;
			}
		}
		if (clientesAntes.length > 0) {
			if (isNaN(clientesAntes)) {
				document.getElementById('clientesAnt').focus();
				alert("El valor de Número de clientes (antes) debe ser ingresado con números");
				return false;
			}
		}
		if (empleadosAntes.length > 0) {
			if (isNaN(empleadosAntes)) {
				document.getElementById('empleadosAnt').focus();
				alert("El valor de Número de empleados (antes) debe ser ingresado con números");
				return false;
			}
		}
		if (egresosAnt.length > 0) {
			if (isNaN(egresosAnt)) {
				document.getElementById('egresosAnt').focus();
				alert("El valor de % Egresos / Ventas (antes) debe ser ingresado con números");
				return false;
			}
		}

		if (ingresosDespues.length > 0) {
			if (isNaN(ingresosDespues)) {
				document.getElementById('ingresosDesp').focus();
				alert("El valor de Ventas o ingresos acumulados (después) debe ser ingresado con números");
				return false;
			}
		}
		if (clientesDespues.length > 0) {
			if (isNaN(clientesDespues)) {
				document.getElementById('clientesDesp').focus();
				alert("El valor de Número de clientes (después) debe ser ingresado con números");
				return false;
			}
		}
		if (empleadosDespues.length > 0) {
			if (isNaN(empleadosDespues)) {
				document.getElementById('empleadosDesp').focus();
				alert("El valor de Número de empleados (después) debe ser ingresado con números");
				return false;
			}
		}
		if (egresosDespues.length > 0) {
			if (isNaN(egresosDespues)) {
				document.getElementById('egresosDesp').focus();
				alert("El valor de % Egresos / Ventas (después) debe ser ingresado con números");
				return false;
			}
		}

		if (document.getElementById('reqSi').checked == true
				&& document.getElementById('reqNo').checked == false) {
			if (document.getElementById('idDivCat1').style.display == 'none') {
				document.getElementById('catProd1').focus();
				alert("Si desea recibir requerimientos de compra deberá seleccionar por lo menos un tipo de industria del catálogo.");
				return false;
			}
			document.getElementById("idBotonEnviar").value = "Actualizando PyME...";
			document.getElementById("idBotonEnviar").disabled = true;
			return true;
		} else if (document.getElementById('reqSi').checked == false
				&& document.getElementById('reqNo').checked == true) {
			document.getElementById("idBotonEnviar").value = "Actualizando PyME...";
			document.getElementById("idBotonEnviar").disabled = true;

			return true;
		} else {
			alert('¿Desea recibir requerimientos de compra?');
			return false;
		}

	}

}

function aceptar() {
	if (document.getElementById('personalidadJuridica')[1].selected) {
		if (document.getElementById('idCheckAceptaLR').checked) {
			document.getElementById('idDivLiberacionResponsabilidad').style.display = 'none';
			document.getElementById('sec2').style.display = 'block';
			$('.overlay-container').fadeOut().end().find('.window-container')
					.removeClass('window-container-visible');
			document.getElementById('idDesactivar').value = '';
		} else {
			document.getElementById('idDesactivar').value = '';
			window.location.assign(document.URL);
		}
	} else if (document.getElementById('personalidadJuridica')[2].selected) {
		if (document.getElementById('idCheckAceptaAP').checked) {
			document.getElementById('idDivAvisoPrivacidad').style.display = 'none';
			document.getElementById('idDivAvisoPrivacidad2').style.display = 'block';
		} else {
			document.getElementById('idDesactivar').value = '';
			window.location.assign(document.URL);
		}
	}
}

function cuestionario() {
	var nom = document.getElementById('idNombreAcepta').value;
	var app = document.getElementById('idApPaternoAcepta').value;
	var apm = document.getElementById('idApMaternoAcepta').value;
	var uno = document.getElementById('idCheckAceptaP1').checked
			|| document.getElementById('idCheckNoAceptaP1').checked;
	var dos = document.getElementById('idCheckAceptaP2').checked
			|| document.getElementById('idCheckNoAceptaP2').checked;
	var tres = document.getElementById('idCheckAceptaP3').checked
			|| document.getElementById('idCheckNoAceptaP3').checked;

	if (uno
			&& dos
			&& tres
			&& !((nom == null || nom.length == 0 || /^\s+$/.test(nom))
					|| (app == null || app.length == 0 || /^\s+$/.test(app)) || (apm == null
					|| apm.length == 0 || /^\s+$/.test(apm)))) {
		if (document.getElementById('idCheckAceptaP1').checked
				&& document.getElementById('idCheckAceptaP2').checked
				&& document.getElementById('idCheckAceptaP3').checked) {
			document.getElementById('sec2').style.display = 'block';
			$('.overlay-container').fadeOut().end().find('.window-container')
					.removeClass('window-container-visible');
			document.getElementById('idDesactivar').value = '';
		} else {
			document.getElementById('sec2').style.display = 'block';
			$('.overlay-container').fadeOut().end().find('.window-container')
					.removeClass('window-container-visible');
			document.getElementById('idDesactivar').value = 'true';
		}
		document.getElementById('idNomAcepta').value = nom;
		document.getElementById('idApPaAcepta').value = app;
		document.getElementById('idApMaAcepta').value = apm;
	} else {
		alert('Para continuar debe contestar todas las preguntas y llenar los campos con sus datos.');
		return false;
	}
}

function showCat() {

	if (document.getElementById('reqSi').checked) {
		document.getElementById('reqNo').checked = false;
		document.getElementById('showCatalogos').style.display = 'block';
		document.getElementById('showNombreCat').style.display = 'block';
		document.getElementById('comboCat1').style.display = 'block';
	} else if (document.getElementById('reqSi').checked == false) {
		document.getElementById('showCatalogos').style.display = 'none';
		document.getElementById('showNombreCat').style.display = 'none';
	}
}

function recibeReqNo() {
	if (document.getElementById('reqNo').checked) {
		document.getElementById('reqSi').checked = false;
		for ( var i = 1; i <= 10; i++) {
			document.getElementById('labCat' + i).innerText = '';
			document.getElementById('idCatHid' + i).value = null;
			document.getElementById('idDesCatHid' + i).value = null;
			document.getElementById('idDivCat' + i).style.display = 'none';
		}
		document.getElementById('showNombreCat').style.display = 'none';
		document.getElementById('showCatalogos').style.display = 'none';
	}
}

function calendario() {
	Calendar.setup({
		inputField : "ingreso", // id del campo de texto
		ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
		// campo de texto
		button : "lanzador" // el id del botón que lanzará el calendario
	});
}

function calendario2() {
	Calendar.setup({
		inputField : "ingreso2", // id del campo de texto
		ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
		// campo de texto
		button : "lanzador2" // el id del botón que lanzará el calendario
	});
}

function calendario3() {
	Calendar.setup({
		inputField : "ingreso3", // id del campo de texto
		ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
		// campo de texto
		button : "lanzador3" // el id del botón que lanzará el calendario
	});
}

function calendario4() {
	Calendar.setup({
		inputField : "ingreso4", // id del campo de texto
		ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
		// campo de texto
		button : "lanzador4" // el id del botón que lanzará el calendario
	});
}

function calendario5() {
	Calendar.setup({
		inputField : "ingreso5", // id del campo de texto
		ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
		// campo de texto
		button : "lanzador5" // el id del botón que lanzará el calendario
	});
}

function otroArchivo() {
	var sizeF = 1;

	for ( var i = 1; i < 11; i++) {
		_block = document.getElementById('idDivArchivo' + i + 'Block').style.display;
		if (_block == 'block') {
			sizeF++;
		}
	}

	document.getElementById('idDivArchivo' + sizeF + 'Block').style.display = 'block';
	document.getElementById('ayudaArchivos').style.display = 'block';
}

function supArchivo(obj) {
	document.getElementById('idCampoArchivo' + obj).value = '';
	document.getElementById('arch' + obj).value = '';
	document.getElementById('idDivArchivo' + obj + 'Block').style.display = 'none';
	document.getElementById('descArch' + obj).style.display = 'none';
}

function valueEstadoCheck(num, estado) {

	var cont = 0;

	if (document.getElementById('check' + num).checked) {
		document.getElementById('checkEstado' + num).value = estado;
		document.getElementById('checkNacional').disabled = true;
		document.getElementById('checkNacional').checked = false;
		document.getElementById('checkEstadoNacional').value = '';
	} else {
		document.getElementById('checkEstado' + num).value = '';
		for ( var x = 1; x < 33; x++) {
			var est = document.getElementById('check' + x);
			if (est.checked) {
				cont++;
				break;
			}
		}

		if (cont == 0) {
			document.getElementById('checkNacional').disabled = false;
		}
	}
}

function valueCheckNacional(estado) {
	if (document.getElementById('checkNacional').checked) {
		document.getElementById('checkEstadoNacional').value = estado;

		for ( var x = 1; x < 33; x++) {
			var estado = document.getElementById('check' + x);
			estado.disabled = true;
			estado.checked = false;
			document.getElementById('checkEstado' + x).value = '';
		}
	} else {
		document.getElementById('checkEstadoNacional').value = '';
		for ( var x = 1; x < 33; x++) {
			var estado = document.getElementById('check' + x);
			estado.disabled = false;
		}
	}
}

function checkSectorUno() {
	if (valorSectorUno.checked) {
		valorSectorDos.checked = false;
		valorSectorTres.checked = false;
	}
}
function checkSectorDos() {
	if (valorSectorDos.checked) {
		valorSectorUno.checked = false;
		valorSectorTres.checked = false;
	}
}
function checkSectorTres() {
	if (valorSectorTres.checked) {
		valorSectorUno.checked = false;
		valorSectorDos.checked = false;
	}
}

function addCert() {
	var sizeCert = 2;

	for ( var i = 2; i < 6; i++) {
		_block = document.getElementById('cert' + i).style.display;
		if (_block == 'block') {
			sizeCert++;
		}
	}
	document.getElementById('cert' + sizeCert).style.display = 'block';
	if (sizeCert == 5) {
		document.getElementById("showCert").style.display = 'none';
	}
}

function supCert(num) {
	document.getElementById('certificacion' + num).value = '';
	document.getElementById('instCert' + num).value = '';

	document.getElementById('cert' + num).style.display = 'none';
	document.getElementById('showCert').style.display = 'block';
}

function validaNumero(evt) {
	var key = (document.all) ? evt.keyCode : evt.which;
	return (key <= 13 || (key >= 48 && key <= 57) || key == 46);
}

function validaLetra(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 0)
		return true;
	if (tecla == 8)
		return true;
	patron = /[A-ZÑña-z\s]/;
	te = String.fromCharCode(tecla);
	return patron.test(te);
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

function cambiaCampo2(e) {

	var code = (e.keyCode ? e.keyCode : e.which);

	if (code != 8) {
		if (document.getElementById('ladaTel2').value.length == 2) {
			document.getElementById('numTel2').focus();
		}
		if (document.getElementById('numTel2').value.length == 8) {
			document.getElementById('extTel2').focus();
		}
	}
}

function agregaProducto() {
	var _prod = document.getElementById('idProducto').value;
	var _prodSplit = document.getElementById('idProducto').value.split(' ');
	var _productos = 0;

	if (_prod.length == 0 || /^\s+$/.test(_prod)) {
		alert("Ingrese un Producto mediante el botón de '+agregar'");
	} else if (_prodSplit.length > 3) {
		alert("El producto a ingresar solo puede estar conformado por tres palabras.");
	} else {
		for ( var i = 1; i <= 20; i++) {
			if (document.getElementById('idDivProd' + i).style.display == 'block') {
				_productos++;
			}
		}
		if (_productos < 20) {
			var _pos = _productos + 1;
			document.getElementById('idProdHid' + _pos).value = document
					.getElementById('idProducto').value;
			document.getElementById('labProd' + _pos).innerText = document
					.getElementById('idProducto').value;
			document.getElementById('idDivProd' + _pos).style.display = 'block';
		}
		document.getElementById('idProducto').value = '';
		document.getElementById('tablaProd').style.display = 'block';
	}
	document.getElementById('idProducto').focus();
}

function quitarProducto(pos) {
	var _last = pos;
	if (pos == '20'
			|| document.getElementById('idDivProd' + (pos + 1)).style.display == 'none') {
		document.getElementById('labProd' + pos).innerText = null;
		document.getElementById('idProdHid' + pos).value = null;
		document.getElementById('idDivProd' + pos).style.display = 'none';
	} else {
		for ( var i = pos; i <= 20; i++) {
			if (document.getElementById('idDivProd' + i).style.display == 'block') {
				_last++;
			}
			if (document.getElementById('labProd' + (i + 1)) != null) {
				_lab = document.getElementById('labProd' + (i + 1)).innerText;
			}
			if (document.getElementById('idProdHid' + (i + 1)) != null) {
				_hid = document.getElementById('idProdHid' + (i + 1)).value;
			}
			document.getElementById('labProd' + i).innerText = _lab;
			document.getElementById('idProdHid' + i).value = _hid;
		}
		document.getElementById('idDivProd' + (_last - 1)).style.display = 'none';
	}

	if (_last == 1) {
		document.getElementById('tablaProd').style.display = 'none';
	}
}

/* CONTACTOS */

function addContacto() {

	var _tipo = document.getElementById('tipoContacto').selectedIndex;
	var _tipOtro = document.getElementById('tipoOtro').value;
	var _nombre = document.getElementById('nombreContacto').value;
	var _apPat = document.getElementById('appPat').value;
	var _apMat = document.getElementById('appMat').value;
	var _correo = document.getElementById('correoElectronicoContacto').value;
	var _compCorreo = document.getElementById('comparaCorreoContacto').value;
	var _lada = document.getElementById('ladaTel').value;
	var _numTel = document.getElementById('numTel').value;

	var _totalContactos = 0;
	if (_tipo == 0) {
		document.getElementById('tipoContacto').focus();
		alert("Seleccione un tipo para el contacto");
	} else if (document.getElementById("tipoContacto").value == 'Otro'
			&& (_tipOtro == " " || _tipOtro.length == 0)) {
		document.getElementById("tipoOtro").focus();
		alert("Indique el tipo de contacto");
	} else if (_nombre.length == 0 || /^\s+$/.test(_nombre)) {
		document.getElementById('nombreContacto').focus();
		alert("Ingrese el nombre del contacto");
	} else if (_apPat.length == 0 || /^\s+$/.test(_apPat)) {
		document.getElementById('appPat').focus();
		alert("Ingrese el Apellido paterno del contacto");
	} else if (_apMat.length == 0 || /^\s+$/.test(_apMat)) {
		document.getElementById('appMat').focus();
		alert("Ingrese el Apellido materno del contacto");
	} else if (!(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
			.test(_correo))) {
		document.getElementById("correoElectronicoContacto").focus();
		alert("Ingrese una dirección de correo electrónico válida");
		return false;
	} else if (_correo != _compCorreo) {
		document.getElementById("comparaCorreoContacto").focus();
		alert("El correo electrónico no coincide");
		return false;
	} else if (_lada.length != 2 || /^\s+$/.test(_lada)) {
		document.getElementById("ladaTel").focus();
		alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
		return false;
	} else if (_numTel.length != 8 || /^\s+$/.test(_numTel)) {
		document.getElementById("numTel").focus();
		alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
		return false;
	} else {
		for ( var i = 1; i <= 5; i++) {
			if (document.getElementById('divContacto' + i).style.display == 'block') {
				_totalContactos++;
			}
		}

		if (_totalContactos < 5) {
			var _pos = _totalContactos + 1;

			var _ladaTel1 = document.getElementById('ladaTel').value;
			var _numeroTel1 = document.getElementById('numTel').value;
			var extTel1;

			if (document.getElementById('extTel').value.length == 1) {
				extTel1 = 0 + '' + 0 + '' + 0 + ''
						+ document.getElementById('extTel').value;
			} else if (document.getElementById('extTel').value.length == 2) {
				extTel1 = 0 + '' + 0 + ''
						+ document.getElementById('extTel').value;
			} else if (document.getElementById('extTel').value.length == 3) {
				extTel1 = 0 + '' + document.getElementById('extTel').value;
			} else if (document.getElementById('extTel').value.length == 4) {
				extTel1 = document.getElementById('extTel').value;
				;
			} else {
				extTel1 = 0 + '' + 0 + '' + 0 + '' + 0;
			}

			var _miTel = '(52)(' + _ladaTel1 + ')(' + _numeroTel1 + ')('
					+ extTel1 + ')';

			document.getElementById('tipoHid' + _pos).value = document
					.getElementById('tipoOtro').value;
			document.getElementById('nombreHid' + _pos).value = document
					.getElementById('nombreContacto').value;
			document.getElementById('apPatHid' + _pos).value = document
					.getElementById('appPat').value;
			document.getElementById('apMatHid' + _pos).value = document
					.getElementById('appMat').value;
			document.getElementById('correoHid' + _pos).value = document
					.getElementById('correoElectronicoContacto').value;
			document.getElementById('telHid' + _pos).value = _miTel;

			document.getElementById('labTipo' + _pos).innerText = document
					.getElementById('tipoOtro').value;
			document.getElementById('labNombre' + _pos).innerText = document
					.getElementById('nombreContacto').value;
			document.getElementById('labApPat' + _pos).innerText = document
					.getElementById('appPat').value;
			document.getElementById('labApMat' + _pos).innerText = document
					.getElementById('appMat').value;
			document.getElementById('labCorreo' + _pos).innerText = document
					.getElementById('correoElectronicoContacto').value;
			document.getElementById('labTel' + _pos).innerText = _miTel;
			document.getElementById('divContacto' + _pos).style.display = 'block';

		}
		document.getElementById('tipoContacto').selectedIndex = 0;
		document.getElementById('tipoOtro').value = '';
		document.getElementById('nombreContacto').value = '';
		document.getElementById('appPat').value = '';
		document.getElementById('appMat').value = '';
		document.getElementById('correoElectronicoContacto').value = '';
		document.getElementById('comparaCorreoContacto').value = '';
		document.getElementById('ladaTel').value = '';
		document.getElementById('numTel').value = '';
		document.getElementById('extTel').value = '';
		document.getElementById('linkAddContacto').style.display = 'block';
		document.getElementById('showFormContact').style.display = 'none';
	}

	if (_totalContactos == 4) {
		document.getElementById('linkAddContacto').style.display = 'none';
	}
}

function supContacto(pos) {

	var _last = pos;
	if (pos == '5'
			|| document.getElementById('divContacto' + (pos + 1)).style.display == 'none') {
		document.getElementById('labTipo' + pos).innerText = '';
		document.getElementById('labNombre' + pos).innerText = '';
		document.getElementById('labCorreo' + pos).innerText = '';
		document.getElementById('labTel' + pos).innerText = '';

		document.getElementById('tipoHid' + pos).value = null;
		document.getElementById('nombreHid' + pos).value = null;
		document.getElementById('apPatHid' + pos).value = null;
		document.getElementById('apMatHid' + pos).value = null;
		document.getElementById('correoHid' + pos).value = null;
		document.getElementById('telHid' + pos).value = null;

		document.getElementById('divContacto' + pos).style.display = 'none';
	} else {
		for ( var i = pos; i <= 5; i++) {
			if (document.getElementById('divContacto' + i).style.display == 'block') {
				_last++;
			}

			if (document.getElementById('labTipo' + (i + 1)) != null) {
				_labT = document.getElementById('labTipo' + (i + 1)).innerText;
			}
			if (document.getElementById('labNombre' + (i + 1)) != null) {
				_labN = document.getElementById('labNombre' + (i + 1)).innerText;
			}
			if (document.getElementById('labCorreo' + (i + 1)) != null) {
				_labC = document.getElementById('labCorreo' + (i + 1)).innerText;
			}
			if (document.getElementById('labTel' + (i + 1)) != null) {
				_labTele = document.getElementById('labTel' + (i + 1)).innerText;
			}

			if (document.getElementById('tipoHid' + (i + 1)) != null) {
				_hidT = document.getElementById('tipoHid' + (i + 1)).value;
			}
			if (document.getElementById('nombreHid' + (i + 1)) != null) {
				_hidN = document.getElementById('nombreHid' + (i + 1)).value;
			}
			if (document.getElementById('apPatHid' + (i + 1)) != null) {
				_hidAP = document.getElementById('apPatHid' + (i + 1)).value;
			}
			if (document.getElementById('apMatHid' + (i + 1)) != null) {
				_hidAM = document.getElementById('apMatHid' + (i + 1)).value;
			}
			if (document.getElementById('correoHid' + (i + 1)) != null) {
				_hidC = document.getElementById('correoHid' + (i + 1)).value;
			}
			if (document.getElementById('telHid' + (i + 1)) != null) {
				_hidTelef = document.getElementById('telHid' + (i + 1)).value;
			}

			document.getElementById('labTipo' + i).innerText = _labT;
			document.getElementById('labNombre' + i).innerText = _labN;
			document.getElementById('labCorreo' + i).innerText = _labC;
			document.getElementById('labTel' + i).innerText = _labTele;

			document.getElementById('tipoHid' + i).value = _hidT;
			document.getElementById('nombreHid' + i).value = _hidN;
			document.getElementById('apPatHid' + i).value = _hidAP;
			document.getElementById('apMatHid' + i).value = _hidAM;
			document.getElementById('correoHid' + i).value = _hidC;
			document.getElementById('telHid' + i).value = _hidTelef;
		}
		document.getElementById('divContacto' + (_last - 1)).style.display = 'none';
		document.getElementById('linkAddContacto').style.display = 'block';
	}
}

function editaContacto(pos) {

	var telContacto1 = document.getElementById('telHid' + pos).value;
	var ladaTel = document.getElementById('ladaTel');
	var numTel = document.getElementById('numTel');
	var extTel = document.getElementById('extTel');

	if (telContacto1 != 'null') {
		ladaTel.value = telContacto1.substring(5, 7);
		numTel.value = telContacto1.substring(9, 17);
		extTel.value = telContacto1.substring(19, 23);
	}

	document.getElementById('tipoContacto').value = document
			.getElementById('tipoHid' + pos).value;
	document.getElementById('tipoOtro').value = document
			.getElementById('tipoHid' + pos).value;

	document.getElementById('nombreContacto').value = document
			.getElementById('nombreHid' + pos).value;
	document.getElementById('appPat').value = document
			.getElementById('apPatHid' + pos).value;
	document.getElementById('appMat').value = document
			.getElementById('apMatHid' + pos).value;
	document.getElementById('correoElectronicoContacto').value = document
			.getElementById('correoHid' + pos).value;
	document.getElementById('comparaCorreoContacto').value = '';

	document.getElementById('regContact').style.display = 'none';
	document.getElementById('linkAddContacto').style.display = 'none';
	document.getElementById('linkActulizaContact').style.display = 'block';
	document.getElementById('showFormContact').style.display = 'block';

	document.getElementById('tempPosContHid').value = pos;
}

function actualizaContacto() {
	var pos = document.getElementById('tempPosContHid').value;

	var _tipo = document.getElementById('tipoContacto').selectedIndex;
	var _tipOtro = document.getElementById('tipoOtro').value;
	var _nombre = document.getElementById('nombreContacto').value;
	var _apPat = document.getElementById('appPat').value;
	var _apMat = document.getElementById('appMat').value;
	var _correo = document.getElementById('correoElectronicoContacto').value;
	var _compCorreo = document.getElementById('comparaCorreoContacto').value;
	var _lada = document.getElementById('ladaTel').value;
	var _numTel = document.getElementById('numTel').value;

	if (_tipo == 0) {
		document.getElementById('tipoContacto').focus();
		alert("Seleccione un tipo para el contacto");
	} else if (document.getElementById("tipoContacto").value == 'Otro'
			&& (_tipOtro == " " || _tipOtro.length == 0)) {
		document.getElementById("tipoOtro").focus();
		alert("Indique el tipo de contacto");
	} else if (_nombre.length == 0 || /^\s+$/.test(_nombre)) {
		document.getElementById('nombreContacto').focus();
		alert("Ingrese el nombre del contacto");
	} else if (_apPat.length == 0 || /^\s+$/.test(_apPat)) {
		document.getElementById('appPat').focus();
		alert("Ingrese el Apellido paterno del contacto");
	} else if (_apMat.length == 0 || /^\s+$/.test(_apMat)) {
		document.getElementById('appMat').focus();
		alert("Ingrese el Apellido materno del contacto");
	} else if (!(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
			.test(_correo))) {
		document.getElementById("correoElectronicoContacto").focus();
		alert("Ingrese una dirección de correo electrónico válida");
		return false;
	} else if (_correo != _compCorreo) {
		document.getElementById("comparaCorreoContacto").focus();
		alert("El correo electrónico no coincide");
		return false;
	} else if (_lada.length != 2 || /^\s+$/.test(_lada)) {
		document.getElementById("ladaTel").focus();
		alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
		return false;
	} else if (_numTel.length != 8 || /^\s+$/.test(_numTel)) {
		document.getElementById("numTel").focus();
		alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
		return false;
	} else {

		var _ladaTel1 = document.getElementById('ladaTel').value;
		var _numeroTel1 = document.getElementById('numTel').value;
		var extTel1;

		if (document.getElementById('extTel').value.length == 1) {
			extTel1 = 0 + '' + 0 + '' + 0 + ''
					+ document.getElementById('extTel').value;
		} else if (document.getElementById('extTel').value.length == 2) {
			extTel1 = 0 + '' + 0 + '' + document.getElementById('extTel').value;
		} else if (document.getElementById('extTel').value.length == 3) {
			extTel1 = 0 + '' + document.getElementById('extTel').value;
		} else if (document.getElementById('extTel').value.length == 4) {
			extTel1 = document.getElementById('extTel').value;
			;
		} else {
			extTel1 = 0 + '' + 0 + '' + 0 + '' + 0;
		}

		var _miTel = '(52)(' + _ladaTel1 + ')(' + _numeroTel1 + ')(' + extTel1
				+ ')';

		document.getElementById('tipoHid' + pos).value = document
				.getElementById('tipoOtro').value;
		document.getElementById('nombreHid' + pos).value = document
				.getElementById('nombreContacto').value;
		document.getElementById('apPatHid' + pos).value = document
				.getElementById('appPat').value;
		document.getElementById('apMatHid' + pos).value = document
				.getElementById('appMat').value;
		document.getElementById('correoHid' + pos).value = document
				.getElementById('correoElectronicoContacto').value;
		document.getElementById('telHid' + pos).value = _miTel;

		document.getElementById('labTipo' + pos).innerText = document
				.getElementById('tipoOtro').value;
		document.getElementById('labNombre' + pos).innerText = document
				.getElementById('nombreContacto').value;
		document.getElementById('labApPat' + pos).innerText = document
				.getElementById('appPat').value;
		document.getElementById('labApMat' + pos).innerText = document
				.getElementById('appMat').value;
		document.getElementById('labCorreo' + pos).innerText = document
				.getElementById('correoElectronicoContacto').value;
		document.getElementById('labTel' + pos).innerText = _miTel;

		document.getElementById('tipoContacto').selectedIndex = 0;
		document.getElementById('tipoOtro').value = '';
		document.getElementById('nombreContacto').value = '';
		document.getElementById('appPat').value = '';
		document.getElementById('appMat').value = '';
		document.getElementById('correoElectronicoContacto').value = '';
		document.getElementById('comparaCorreoContacto').value = '';
		document.getElementById('ladaTel').value = '';
		document.getElementById('numTel').value = '';
		document.getElementById('extTel').value = '';

		document.getElementById('linkActulizaContact').style.display = 'none';
		document.getElementById('regContact').style.display = 'block';
		document.getElementById('linkAddContacto').style.display = 'block';
		document.getElementById('showFormContact').style.display = 'none';
	}
}

function valorTipoCont(tipo) {
	if (tipo != 'Otro') {
		document.getElementById("tipoOtro").value = tipo;
		document.getElementById("otroTipo").style.display = 'none';
		return true;
	} else {
		document.getElementById("otroTipo").style.display = 'block';
		document.getElementById("tipoOtro").value = '';
	}
}

function valorTipoCont2(tipo) {
	if (tipo != 'Otro') {
		document.getElementById("tipoOtro2").value = tipo;
		document.getElementById("otroTipo2").style.display = 'none';
		return true;
	} else {
		document.getElementById("otroTipo2").style.display = 'block';
		document.getElementById("tipoOtro2").value = '';
	}
}

function showContacto() {
	document.getElementById("tipoContacto2").selectedIndex = 0;
	document.getElementById("contacto2").style.display = 'block';
	document.getElementById("linkAddContacto").style.display = 'none';
}

/* CLIENTES */

function addCliente() {
	var _client = document.getElementById('cliente').value;
	var _prodClient = document.getElementById('prodCliente').value;
	var _showProdClient = document.getElementById('showProdCliente').value;
	var _anio = document.getElementById('aniosProveCliente').value;
	var _mes = document.getElementById('mesesProveCliente').value;

	var _totalClientes = 0;

	if (_client.length == 0 || /^\s+$/.test(_client)) {
		document.getElementById('cliente').focus();
		alert("Ingrese el nombre del cliente");
	} else if (_prodClient.length > 0 && /^\s+$/.test(_prodClient)) {
		document.getElementById('prodCliente').focus();
		alert("Ingrese el producto que compra cliente mediante el botón '+agregar producto'");
	} else if (_showProdClient.length == 0) {
		document.getElementById('prodCliente').focus();
		alert("Ingrese el producto que compra cliente");
	} else if (_anio.length == 0 || /^\s+$/.test(_anio)) {
		document.getElementById('aniosProveCliente').focus();
		alert("El campo *Año es requerido");
	} else if (_mes.length == 0 || /^\s+$/.test(_mes)) {
		document.getElementById('mesesProveCliente').focus();
		alert("El campo *Mes es requerido");
	} else {
		for ( var i = 1; i <= 6; i++) {
			if (document.getElementById('divCliente' + i).style.display == 'block') {
				_totalClientes++;
			}
		}
		if (_totalClientes < 6) {
			var _pos = _totalClientes + 1;

			document.getElementById('clienteHid' + _pos).value = document
					.getElementById('cliente').value;
			document.getElementById('prodCompHid' + _pos).value = document
					.getElementById('showProdCliente').value;
			document.getElementById('anioHid' + _pos).value = document
					.getElementById('aniosProveCliente').value;
			document.getElementById('mesesHid' + _pos).value = document
					.getElementById('mesesProveCliente').value;

			document.getElementById('labCliente' + _pos).innerText = document
					.getElementById('cliente').value;
			document.getElementById('labProdCliente' + _pos).innerText = document
					.getElementById('showProdCliente').value;
			document.getElementById('labAniosCliente' + _pos).innerText = document
					.getElementById('aniosProveCliente').value;
			document.getElementById('labMesesCliente' + _pos).innerText = document
					.getElementById('mesesProveCliente').value;
			document.getElementById('divCliente' + _pos).style.display = 'block';

		}
		document.getElementById('cliente').value = '';
		document.getElementById('showProdCliente').value = '';
		document.getElementById('aniosProveCliente').value = '';
		document.getElementById('mesesProveCliente').value = '';

		document.getElementById('labAnios' + _pos).style.display = 'block';
		document.getElementById('labMeses' + _pos).style.display = 'block';
		document.getElementById('linkAddProve').style.display = 'block';
		document.getElementById('formCliente').style.display = 'none';
		document.getElementById('labDeleteProdC').style.display = 'none';
	}

	if (_totalClientes == 5) {
		document.getElementById('linkAddProve').style.display = 'none';
	}
}

function supCliente(pos) {

	var _last = pos;
	if (pos == '6'
			|| document.getElementById('divCliente' + (pos + 1)).style.display == 'none') {
		document.getElementById('labCliente' + pos).innerText = '';
		document.getElementById('labProdCliente' + pos).innerText = '';
		document.getElementById('labAniosCliente' + pos).innerText = '';
		document.getElementById('labMesesCliente' + pos).innerText = '';

		document.getElementById('clienteHid' + pos).value = null;
		document.getElementById('prodCompHid' + pos).value = null;
		document.getElementById('anioHid' + pos).value = null;
		document.getElementById('mesesHid' + pos).value = null;

		document.getElementById('divCliente' + pos).style.display = 'none';
	} else {
		for ( var i = pos; i <= 6; i++) {
			if (document.getElementById('divCliente' + i).style.display == 'block') {
				_last++;
			}

			if (document.getElementById('labCliente' + (i + 1)) != null) {
				_labC = document.getElementById('labCliente' + (i + 1)).innerText;
			}
			if (document.getElementById('labProdCliente' + (i + 1)) != null) {
				_labP = document.getElementById('labProdCliente' + (i + 1)).innerText;
			}
			if (document.getElementById('labAniosCliente' + (i + 1)) != null) {
				_labA = document.getElementById('labAniosCliente' + (i + 1)).innerText;
			}
			if (document.getElementById('labMesesCliente' + (i + 1)) != null) {
				_labM = document.getElementById('labMesesCliente' + (i + 1)).innerText;
			}

			if (document.getElementById('clienteHid' + (i + 1)) != null) {
				_hidC = document.getElementById('clienteHid' + (i + 1)).value;
			}
			if (document.getElementById('prodCompHid' + (i + 1)) != null) {
				_hidP = document.getElementById('prodCompHid' + (i + 1)).value;
			}
			if (document.getElementById('anioHid' + (i + 1)) != null) {
				_hidA = document.getElementById('anioHid' + (i + 1)).value;
			}
			if (document.getElementById('mesesHid' + (i + 1)) != null) {
				_hidM = document.getElementById('mesesHid' + (i + 1)).value;
			}

			document.getElementById('labCliente' + i).innerText = _labC;
			document.getElementById('labProdCliente' + i).innerText = _labP;
			document.getElementById('labAniosCliente' + i).innerText = _labA;
			document.getElementById('labMesesCliente' + i).innerText = _labM;

			document.getElementById('clienteHid' + i).value = _hidC;
			document.getElementById('prodCompHid' + i).value = _hidP;
			document.getElementById('anioHid' + i).value = _hidA;
			document.getElementById('mesesHid' + i).value = _hidM;
		}
		document.getElementById('divCliente' + (_last - 1)).style.display = 'none';
		document.getElementById('linkAddProve').style.display = 'block';
	}
}

function editaCliente(pos) {
	document.getElementById('cliente').value = document
			.getElementById('clienteHid' + pos).value;
	document.getElementById('showProdCliente').value = document
			.getElementById('prodCompHid' + pos).value;
	document.getElementById('aniosProveCliente').value = document
			.getElementById('anioHid' + pos).value;
	document.getElementById('mesesProveCliente').value = document
			.getElementById('mesesHid' + pos).value;
	document.getElementById('linkAddProve').style.display = 'none';
	document.getElementById('regCliente').style.display = 'none';
	document.getElementById('linkActulizaProve').style.display = 'block';
	document.getElementById('formCliente').style.display = 'block';
	document.getElementById('posTempAHid').value = pos;
}

function actualizaCliente() {
	var pos = document.getElementById('posTempAHid').value;

	var _client = document.getElementById('cliente').value;
	var _prodClient = document.getElementById('prodCliente').value;
	var _showProdClient = document.getElementById('showProdCliente').value;
	var _anio = document.getElementById('aniosProveCliente').value;
	var _mes = document.getElementById('mesesProveCliente').value;

	if (_client.length == 0 || /^\s+$/.test(_client)) {
		document.getElementById('cliente').focus();
		alert("Ingrese el nombre del cliente");
	} else if (_prodClient.length > 0 && /^\s+$/.test(_prodClient)) {
		document.getElementById('prodCliente').focus();
		alert("Ingrese el producto que compra cliente");
	} else if (_showProdClient.length == 0) {
		document.getElementById('prodCliente').focus();
		alert("Ingrese el producto que compra cliente");
	} else if (_anio.length == 0 || /^\s+$/.test(_anio)) {
		document.getElementById('aniosProveCliente').focus();
		alert("El campo *Año es requerido");
	} else if (_mes.length == 0 || /^\s+$/.test(_mes)) {
		document.getElementById('mesesProveCliente').focus();
		alert("El campo *Mes es requerido");
	} else {
		document.getElementById('clienteHid' + pos).value = document
				.getElementById('cliente').value;
		document.getElementById('prodCompHid' + pos).value = document
				.getElementById('showProdCliente').value;
		document.getElementById('anioHid' + pos).value = document
				.getElementById('aniosProveCliente').value;
		document.getElementById('mesesHid' + pos).value = document
				.getElementById('mesesProveCliente').value;

		document.getElementById('labCliente' + pos).innerText = document
				.getElementById('cliente').value;
		document.getElementById('labProdCliente' + pos).innerText = document
				.getElementById('showProdCliente').value;
		document.getElementById('labAniosCliente' + pos).innerText = document
				.getElementById('aniosProveCliente').value;
		document.getElementById('labMesesCliente' + pos).innerText = document
				.getElementById('mesesProveCliente').value;

		document.getElementById('cliente').value = '';
		document.getElementById('showProdCliente').value = '';
		document.getElementById('aniosProveCliente').value = '';
		document.getElementById('mesesProveCliente').value = '';

		document.getElementById('labAnios' + pos).style.display = 'block';
		document.getElementById('labMeses' + pos).style.display = 'block';
		document.getElementById('linkActulizaProve').style.display = 'none';
		document.getElementById('linkAddProve').style.display = 'block';
		document.getElementById('formCliente').style.display = 'none';
	}
}

function addProdCliente() {

	if (document.getElementById('showProdCliente').value.length == 0) {
		var cont = 0;
	} else {
		var cont = 1;
	}

	var _prodCliente = document.getElementById('prodCliente').value;
	var _prodTemp = document.getElementById('showProdCliente').value;

	if (_prodCliente.length == 0) {
		document.getElementById('prodCliente').focus();
		alert("Ingrese un Producto");
	} else {
		if (cont == 0) {
			document.getElementById('showProdCliente').value = _prodCliente;
			cont++;
		} else {
			document.getElementById('showProdCliente').value = _prodTemp + ', '
					+ _prodCliente;
		}
		document.getElementById('prodCliente').value = '';
		document.getElementById('labDeleteProdC').style.display = 'block';
	}
}

function deleteProdCliente() {
	var _producto = document.getElementById('showProdCliente').value;
	var _checkComa = _producto.lastIndexOf(',');
	var _prodMenos = _producto.substring(0, _checkComa);
	document.getElementById('showProdCliente').value = _prodMenos;
	if (_checkComa == -1) {
		document.getElementById('labDeleteProdC').style.display = 'none';
	}
}
