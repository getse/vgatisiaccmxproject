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

function tel(fld, vnt) {
	var key = (document.all) ? vnt.keyCode : vnt.which;
	siz = fld.value.length;

	if (key == 13)
		agregaTelefono();

	if (siz == 0 && key == 41) {
		fld.value = '(';
		return false;
	} else if (siz == 4 || siz == 8 || siz == 18) {
		fld.value = fld.value + '(';
		return false;
	}

	if (siz == 0 && key != 40
			&& (key <= 13 || (key >= 48 && key <= 57) || key == 46)) {
		fld.value = '(';
		return true;
	} else if ((siz == 3 || siz == 7 || siz == 17) && (key == 40 || key == 41)) {
		fld.value = fld.value + ')(';
		return false;
	} else if ((siz == 3 || siz == 7 || siz == 17)
			&& (key <= 13 || (key >= 48 && key <= 57) || key == 46)) {
		fld.value = fld.value + ')(';
		return true;
	} else if (siz == 23) {
		fld.value = fld.value + ')';
		return false;
	}

	if (siz == 0 || siz == 3 || siz == 4 || siz == 7 || siz == 8 || siz == 17
			|| siz == 18 || siz == 23)
		return (key == 40 || key == 41);
	else if (siz == 1 || siz == 2 || siz == 5 || siz == 6
			|| (siz > 8 && siz < 17) || (siz > 18 && siz < 23))
		return (key <= 13 || (key >= 48 && key <= 57) || key == 46);
	else
		return false;
	return true;
}

function validacion(sec) {

	valorPerJuridica = document.getElementById("personalidadJuridica").selectedIndex;
	valorRFC = document.getElementById("rfc").value;
	valorCorreo = document.getElementById("correoElectronico").value;
	//valorCompara = document.getElementById("comparaCorreo").value;

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

	valorTipoContacto = document.getElementById("tipoContacto").selectedIndex;
	valorNombre = document.getElementById("nombreContacto").value;
	valorPaterno = document.getElementById("appPat").value;
	valorMaterno = document.getElementById("appMat").value;
	valorCorreoContacto = document.getElementById("correoElectronicoContacto").value;
	valorComparaContacto = document.getElementById("comparaCorreoContacto").value;
	valorTelefonoContacto = document.getElementById("numTel").value;

	valorTipoContacto2 = document.getElementById("tipoContacto2").selectedIndex;
	valorNombre2 = document.getElementById("nombreContacto2").value;
	valorPaterno2 = document.getElementById("appPat2").value;
	valorMaterno2 = document.getElementById("appMat2").value;
	valorCorreoContacto2 = document
			.getElementById("correoElectronicoContacto2").value;
	valorComparaContacto2 = document.getElementById("comparaCorreoContacto2").value;
	valorTelefonoContacto2 = document.getElementById("numTel2").value;

	valorCliente1 = document.getElementById("cliente1").value;
	valorProdCliente1 = document.getElementById("showProdCliente1").value;
	valorAniosProveCliente1 = document.getElementById("aniosProveCliente1").value;
	valorMesesProveCliente1 = document.getElementById("mesesProveCliente1").value;

	valorCliente2 = document.getElementById("cliente2").value;
	valorProdCliente2 = document.getElementById("showProdCliente2").value;
	valorAniosProveCliente2 = document.getElementById("aniosProveCliente2").value;
	valorMesesProveCliente2 = document.getElementById("mesesProveCliente2").value;

	valorCliente3 = document.getElementById("cliente3").value;
	valorProdCliente3 = document.getElementById("showProdCliente3").value;
	valorAniosProveCliente3 = document.getElementById("aniosProveCliente3").value;
	valorMesesProveCliente3 = document.getElementById("mesesProveCliente3").value;

	valorCliente4 = document.getElementById("cliente4").value;
	valorProdCliente4 = document.getElementById("showProdCliente4").value;
	valorAniosProveCliente4 = document.getElementById("aniosProveCliente4").value;
	valorMesesProveCliente4 = document.getElementById("mesesProveCliente4").value;

	valorCliente5 = document.getElementById("cliente5").value;
	valorProdCliente5 = document.getElementById("showProdCliente5").value;
	valorAniosProveCliente5 = document.getElementById("aniosProveCliente5").value;
	valorMesesProveCliente5 = document.getElementById("mesesProveCliente5").value;
	
	valorCliente6 = document.getElementById("cliente6").value;
	valorProdCliente6 = document.getElementById("showProdCliente6").value;
	valorAniosProveCliente6 = document.getElementById("aniosProveCliente6").value;
	valorMesesProveCliente6 = document.getElementById("mesesProveCliente6").value;
	
	var ingresosAntes = document.getElementById("ingresosAnt").value;
	var clientesAntes = document.getElementById("clientesAnt").value;
	var empleadosAntes = document.getElementById("empleadosAnt").value;
	var egresosAnt = document.getElementById("egresosAnt").value;
	var ingresosDespues = document.getElementById("ingresosDesp").value;
	var clientesDespues = document.getElementById("clientesDesp").value;
	var empleadosDespues = document.getElementById("empleadosDesp").value;
	var egresosDespues = document.getElementById("egresosDesp").value;

	if (sec == '1') {

		if ( valorPerJuridica == 0 || valorPerJuridica == "Seleccione el tipo de persona") {
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
		} /*else if (valorCorreo != valorCompara) {
			document.getElementById("comparaCorreo").focus();
			alert("El correo electrónico no coincide");
			return false;
		}*/ else {
			document.getElementById('sec1').style.display = 'none';
			document.getElementById('sec2').style.display = 'block';
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
		if(document.getElementById('tablaProd').style.display == 'none'){
			document.getElementById("idProducto").focus();
			alert("Ingrese un producto");
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

		if (valorTipoContacto == " " || valorTipoContacto == 0
				|| valorTipoContacto == null
				|| valorTipoContacto == "Seleccione tipo de contacto") {
			document.getElementById("tipoContacto").focus();
			alert("Seleccione un tipo de contacto");
			return false;
		} else if (document.getElementById("tipoContacto").value == 'Otro') {
			if (document.getElementById("tipoOtro").value == null
					|| document.getElementById("tipoOtro").value == " "
					|| document.getElementById("tipoOtro").value.length == 0) {
				document.getElementById("tipoOtro").focus();
				alert("Indique el tipo de contacto");
				return false;
			}
		}

		if (valorNombre == null || valorNombre.length == 0
				|| /^\s+$/.test(valorNombre)) {
			document.getElementById("nombreContacto").focus();
			alert("Ingrese el Nombre(s) requerido");
			return false;
		} else if (valorPaterno == null || valorPaterno.length == 0
				|| /^\s+$/.test(valorPaterno)) {
			document.getElementById("appPat").focus();
			alert("Ingrese Apellido Paterno");
			return false;
		} else if (valorMaterno == null || valorMaterno.length == 0
				|| /^\s+$/.test(valorMaterno)) {
			document.getElementById("appMat").focus();
			alert("Ingrese Apellido Materno");
			return false;
		} else if (!(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
				.test(valorCorreoContacto))) {
			document.getElementById("correoElectronicoContacto").focus();
			alert("Ingrese una dirección de correo electrónico válida");
			return false;
		} else if (valorCorreoContacto != valorComparaContacto) {
			document.getElementById("comparaCorreoContacto").focus();
			alert("El correo electrónico no coincide");
			return false;
		}
		
		if(document.getElementById('intTel').value.length != 2 || /^\s+$/.test(document.getElementById('intTel').value)){
			document.getElementById("intTel").focus();
			alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
			return false;					
		}
		if(document.getElementById('ladaTel').value.length != 2 || /^\s+$/.test(document.getElementById('ladaTel').value)){
			document.getElementById("ladaTel").focus();
			alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
			return false;
		}
		if (valorTelefonoContacto.length != 8 || /^\s+$/.test(valorTelefonoContacto)) {
			document.getElementById("numTel").focus();
			alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
			return false;
		}

		// Validación segundo contacto
		if (document.getElementById("contacto2").style.display == 'block') {

			if (valorTipoContacto2 == " " || valorTipoContacto2 == 0
					|| valorTipoContacto2 == null
					|| valorTipoContacto2 == "Seleccione tipo de contacto") {
				document.getElementById("tipoContacto2").focus();
				alert("Seleccione un tipo de contacto para el segundo contacto");
				return false;
			} else if (document.getElementById("tipoContacto2").value == 'Otro') {
				if (document.getElementById("tipoOtro2").value == null
						|| document.getElementById("tipoOtro2").value == " "
						|| document.getElementById("tipoOtro2").value.length == 0) {
					document.getElementById("tipoOtro2").focus();
					alert("Indique el tipo de contacto");
					return false;
				}
			}

			if (valorNombre2 == null || valorNombre2.length == 0
					|| /^\s+$/.test(valorNombre2)) {
				document.getElementById("nombreContacto2").focus();
				alert("Ingrese el Nombre(s) requerido");
				return false;
			} else if (valorPaterno2 == null || valorPaterno2.length == 0
					|| /^\s+$/.test(valorPaterno2)) {
				document.getElementById("appPat2").focus();
				alert("Ingrese Apellido Paterno");
				return false;
			} else if (valorMaterno2 == null || valorMaterno2.length == 0
					|| /^\s+$/.test(valorMaterno2)) {
				document.getElementById("appMat2").focus();
				alert("Ingrese Apellido Materno");
				return false;
			} else if (!(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
					.test(valorCorreoContacto2))) {
				document.getElementById("correoElectronicoContacto2").focus();
				alert("Ingrese una dirección de correo electrónico válida");
				return false;
			} else if (valorCorreoContacto2 != valorComparaContacto2) {
				document.getElementById("comparaCorreoContacto2").focus();
				alert("El correo electrónico no coincide");
				return false;
			}
			
			if(document.getElementById('intTel2').value.length != 2 || /^\s+$/.test(document.getElementById('intTel2').value)){
				document.getElementById("intTel2").focus();
				alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
				return false;					
			}
			
			if(document.getElementById('ladaTel2').value.length != 2 || /^\s+$/.test(document.getElementById('ladaTel2').value)){
				document.getElementById("ladaTel2").focus();
				alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
				return false;
			}
			
			if (valorTelefonoContacto2.length != 8 || /^\s+$/.test(valorTelefonoContacto2)) {
				document.getElementById("numTel2").focus();
				alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
				return false;
			}
		}
		
		var intTel1;
		var ladaTel1;
		var numTel1 = document.getElementById('numTel').value;
		var extTel1;
		var intTel2;
		var ladaTel2;
		var numTel2 = document.getElementById('numTel2').value;
		var extTel2;
		
		if(document.getElementById('intTel').value.length != 2){
			intTel1 = 0+''+0;
		}else{
			intTel1 = document.getElementById('intTel').value;
		}
		if(document.getElementById('ladaTel').value.length != 2){
			ladaTel1 = 0+''+0;
		}else{
			ladaTel1 = document.getElementById('ladaTel').value;
		}
		if(document.getElementById('extTel').value.length == 1){
			extTel1 = 0+''+0+''+0+''+document.getElementById('extTel').value;
		}else if(document.getElementById('extTel').value.length == 2){
			extTel1 = 0+''+0+''+document.getElementById('extTel').value;
		}else if(document.getElementById('extTel').value.length == 3){
			extTel1 = 0+''+document.getElementById('extTel').value;
		}else if(document.getElementById('extTel').value.length == 4){
			extTel1 = document.getElementById('extTel').value;;
		}else{
			extTel1 = 0+''+0+''+0+''+0;
		}
		document.getElementById('telCompHid').value = '('+intTel1+')('+ladaTel1+')('+numTel1+')('+extTel1+')';
		
		if(document.getElementById('intTel2').value.length != 2){
			intTel2 = 0+''+0;
		}else{
			intTel2 = document.getElementById('intTel2').value;
		}
		if(document.getElementById('ladaTel2').value.length != 2){
			ladaTel2 = 0+''+0;
		}else{
			ladaTel2 = document.getElementById('ladaTel2').value;
		}
		if(document.getElementById('extTel2').value.length == 1){
			extTel2 = 0+''+0+''+0+''+document.getElementById('extTel2').value;
		}else if(document.getElementById('extTel2').value.length == 2){
			extTel2 = 0+''+0+''+document.getElementById('extTel2').value;
		}else if(document.getElementById('extTel2').value.length == 3){
			extTel2 = 0+''+document.getElementById('extTel2').value;
		}else if(document.getElementById('extTel2').value.length == 4){
			extTel2 = document.getElementById('extTel2').value;;
		}else{
			extTel2 = 0+''+0+''+0+''+0;
		}
		document.getElementById('telCompHid2').value = '('+intTel2+')('+ladaTel2+')('+numTel2+')('+extTel2+')';
		
		document.getElementById('sec4').style.display = 'none';
		document.getElementById('sec5').style.display = 'block';
		return true;

	} else if (sec == '5') {
		if (valorCliente1 == null || valorCliente1.length == 0 || /^\s+$/.test(valorCliente1)) {
			document.getElementById("cliente1").focus();
			alert("Ingrese el nombre del cliente");
			return false;
		} else if (valorProdCliente1 == null || valorProdCliente1.length == 0 || /^\s+$/.test(valorProdCliente1)) {
			document.getElementById("prodCliente1").focus();
			alert("El campo Productos que compra el cliente es requerido");
			return false;
		} else if (valorAniosProveCliente1.length == 0 || /^\s+$/.test(valorAniosProveCliente1) || isNaN(valorAniosProveCliente1)) {
			document.getElementById("aniosProveCliente1").focus();
			alert("El campo Años como proveedor es requerido y debe ingresarlo con números");
			return false;
		} else if (valorMesesProveCliente1.length == 0 || /^\s+$/.test(valorMesesProveCliente1) || isNaN(valorMesesProveCliente1)) {
			document.getElementById("mesesProveCliente1").focus();
			alert("El campo Meses como proveedor es requerido y debe ingresarlo con números");
			return false;
		}

		if (document.getElementById('prove2').style.display == 'block') {
			if (valorCliente2 == null || valorCliente2.length == 0 || /^\s+$/.test(valorCliente2)) {
				document.getElementById("cliente2").focus();
				alert("Ingrese el nombre del cliente");
				return false;
			} else if (valorProdCliente2 == null || valorProdCliente2.length == 0 || /^\s+$/.test(valorProdCliente2)) {
				document.getElementById("prodCliente2").focus();
				alert("El campo Productos que compra el cliente es requerido");
				return false;
			} else if (valorAniosProveCliente2.length == 0
					|| /^\s+$/.test(valorAniosProveCliente2)
					|| isNaN(valorAniosProveCliente2)) {
				document.getElementById("aniosProveCliente2").focus();
				alert("El campo Años como proveedor es requerido y debe ingresarlo con números");
				return false;
			} else if (valorMesesProveCliente2.length == 0
					|| /^\s+$/.test(valorMesesProveCliente2)
					|| isNaN(valorMesesProveCliente2)) {
				document.getElementById("mesesProveCliente2").focus();
				alert("El campo Meses como proveedor es requerido y debe ingresarlo con números");
				return false;
			}
		}

		if (document.getElementById('prove3').style.display == 'block') {
			if (valorCliente3 == null || valorCliente3.length == 0
					|| /^\s+$/.test(valorCliente3)) {
				document.getElementById("cliente3").focus();
				alert("Ingrese el nombre del cliente");
				return false;
			} else if (valorProdCliente3 == null
					|| valorProdCliente3.length == 0
					|| /^\s+$/.test(valorProdCliente3)) {
				document.getElementById("prodCliente3").focus();
				alert("El campo Productos que compra el cliente es requerido");
				return false;
			} else if (valorAniosProveCliente3.length == 0
					|| /^\s+$/.test(valorAniosProveCliente3)
					|| isNaN(valorAniosProveCliente3)) {
				document.getElementById("aniosProveCliente3").focus();
				alert("El campo Años como proveedor es requerido y debe ingresarlo con números");
				return false;
			} else if (valorMesesProveCliente3.length == 0
					|| /^\s+$/.test(valorMesesProveCliente3)
					|| isNaN(valorMesesProveCliente3)) {
				document.getElementById("mesesProveCliente3").focus();
				alert("El campo Meses como proveedor es requerido y debe ingresarlo con números");
				return false;
			}
		}

		if (document.getElementById('prove4').style.display == 'block') {
			if (valorCliente4 == null || valorCliente4.length == 0
					|| /^\s+$/.test(valorCliente4)) {
				document.getElementById("cliente4").focus();
				alert("Ingrese el nombre del cliente");
				return false;
			} else if (valorProdCliente4 == null
					|| valorProdCliente4.length == 0
					|| /^\s+$/.test(valorProdCliente4)) {
				document.getElementById("prodCliente4").focus();
				alert("El campo Productos que compra el cliente es requerido");
				return false;
			} else if (valorAniosProveCliente4.length == 0
					|| /^\s+$/.test(valorAniosProveCliente4)
					|| isNaN(valorAniosProveCliente4)) {
				document.getElementById("aniosProveCliente4").focus();
				alert("El campo Años como proveedor es requerido y debe ingresarlo con números");
				return false;
			} else if (valorMesesProveCliente4.length == 0
					|| /^\s+$/.test(valorMesesProveCliente4)
					|| isNaN(valorMesesProveCliente4)) {
				document.getElementById("mesesProveCliente4").focus();
				alert("El campo Meses como proveedor es requerido y debe ingresarlo con números");
				return false;
			}
		}

		if (document.getElementById('prove5').style.display == 'block') {
			if (valorCliente5 == null || valorCliente5.length == 0
					|| /^\s+$/.test(valorCliente5)) {
				document.getElementById("cliente5").focus();
				alert("Ingrese el nombre del cliente");
				return false;
			} else if (valorProdCliente5 == null
					|| valorProdCliente5.length == 0
					|| /^\s+$/.test(valorProdCliente5)) {
				document.getElementById("prodCliente5").focus();
				alert("El campo Productos que compra el cliente es requerido");
				return false;
			} else if (valorAniosProveCliente5.length == 0
					|| /^\s+$/.test(valorAniosProveCliente5)
					|| isNaN(valorAniosProveCliente5)) {
				document.getElementById("aniosProveCliente5").focus();
				alert("El campo Años como proveedor es requerido y debe ingresarlo con números");
				return false;
			} else if (valorMesesProveCliente5.length == 0
					|| /^\s+$/.test(valorMesesProveCliente5)
					|| isNaN(valorMesesProveCliente5)) {
				document.getElementById("mesesProveCliente5").focus();
				alert("El campo Meses como proveedor es requerido y debe ingresarlo con números");
				return false;
			}
		}
		
		if (document.getElementById('prove6').style.display == 'block') {
			if (valorCliente6 == null || valorCliente6.length == 0 || /^\s+$/.test(valorCliente6)) {
				document.getElementById("cliente6").focus();
				alert("Ingrese el nombre del cliente");
				return false;
			} else if (valorProdCliente6 == null || valorProdCliente6.length == 0 || /^\s+$/.test(valorProdCliente6)) {
				document.getElementById("prodCliente6").focus();
				alert("El campo Productos que compra el cliente es requerido");
				return false;
			} else if (valorAniosProveCliente6.length == 0 || /^\s+$/.test(valorAniosProveCliente6) || isNaN(valorAniosProveCliente6)) {
				document.getElementById("aniosProveCliente6").focus();
				alert("El campo Años como proveedor es requerido y debe ingresarlo con números");
				return false;
			} else if (valorMesesProveCliente6.length == 0 || /^\s+$/.test(valorMesesProveCliente6) || isNaN(valorMesesProveCliente6)) {
				document.getElementById("mesesProveCliente6").focus();
				alert("El campo Meses como proveedor es requerido y debe ingresarlo con números");
				return false;
			}
		}

		document.getElementById("showProdCliente1Hid").value = document.getElementById("showProdCliente1").value;
		document.getElementById("showProdCliente2Hid").value = document.getElementById("showProdCliente2").value;
		document.getElementById("showProdCliente3Hid").value = document.getElementById("showProdCliente3").value;
		document.getElementById("showProdCliente4Hid").value = document.getElementById("showProdCliente4").value;
		document.getElementById("showProdCliente5Hid").value = document.getElementById("showProdCliente5").value;
		document.getElementById("showProdCliente6Hid").value = document.getElementById("showProdCliente6").value;
		
		document.getElementById('sec5').style.display = 'none';
		document.getElementById('sec6').style.display = 'block';
		return true;
	} else if (sec == '6') {
		document.getElementById('sec6').style.display = 'none';
		document.getElementById('sec7').style.display = 'block';
		return true;
	} else {

		if( ingresosAntes.length > 0 ){
			if( isNaN(ingresosAntes) ){
				document.getElementById('ingresosAnt').focus();
				alert("El valor de Ventas o ingresos acumulados (antes) debe ser ingresado con números");
				return false;
			}
		}
		if( clientesAntes.length > 0 ){
			if( isNaN(clientesAntes) ){
				document.getElementById('clientesAnt').focus();
				alert("El valor de Número de clientes (antes) debe ser ingresado con números");
				return false;
			}	
		}
		if( empleadosAntes.length > 0 ){
			if( isNaN(empleadosAntes) ){
				document.getElementById('empleadosAnt').focus();
				alert("El valor de Número de empleados (antes) debe ser ingresado con números");
				return false;
			}
		}
		if( egresosAnt.length > 0 ){
			if( isNaN(egresosAnt) ){
				document.getElementById('egresosAnt').focus();
				alert("El valor de % Egresos / Ventas (antes) debe ser ingresado con números");
				return false;
			}
		}
		
		if( ingresosDespues.length > 0 ){
			if( isNaN(ingresosDespues) ){
				document.getElementById('ingresosDesp').focus();
				alert("El valor de Ventas o ingresos acumulados (después) debe ser ingresado con números");
				return false;
			}
		}
		if( clientesDespues.length > 0 ){
			if( isNaN(clientesDespues) ){
				document.getElementById('clientesDesp').focus();
				alert("El valor de Número de clientes (después) debe ser ingresado con números");
				return false;
			}
		}
		if( empleadosDespues.length > 0 ){
			if( isNaN(empleadosDespues) ){
				document.getElementById('empleadosDesp').focus();
				alert("El valor de Número de empleados (después) debe ser ingresado con números");
				return false;
			}
		}
		if( egresosDespues.length > 0 ){
			if( isNaN(egresosDespues) ){
				document.getElementById('egresosDesp').focus();
				alert("El valor de % Egresos / Ventas (después) debe ser ingresado con números");
				return false;
			}
		}
		
		if (document.getElementById('reqSi').checked == true && document.getElementById('reqNo').checked == false) {
				document.getElementById('cveScianReqComp').value = document.getElementById('idCveSci').value;
				document.getElementById("idBotonEnviar").value = "Actualizando PyME...";
	    		document.getElementById("idBotonEnviar").disabled = true;
				return true;
		} else if (document.getElementById('reqSi').checked == false && document.getElementById('reqNo').checked == true) {
			//document.getElementById('cveScianReqComp').value = document.getElementById('idInputCatScian').value;
			document.getElementById("idBotonEnviar").value = "Actualizando PyME...";
    		document.getElementById("idBotonEnviar").disabled = true;

			return true;
		} else {
			alert('¿Desea recibir requerimientos de compra?');
			return false;
		}

	}

}
function showCat() {

	if (document.getElementById('reqSi').checked) {
		document.getElementById('reqNo').checked = false;
		document.getElementById('reqNo').disabled = true;
		document.getElementById('showCatalogos').style.display = 'block';
	} else if (document.getElementById('reqSi').checked == false) {
		document.getElementById('reqNo').disabled = false;
		document.getElementById('showCatalogos').style.display = 'none';
	}
}

function recibeReqNo() {
	if (document.getElementById('reqNo').checked) {
		document.getElementById('reqSi').checked = false;
		document.getElementById('reqSi').disabled = true;
	} else if (document.getElementById('reqNo').checked == false) {
		document.getElementById('reqSi').disabled = false;
	}
}

function valorTipoCont(tipo) {
	if (tipo != 'Otro') {
		document.getElementById("tipoOtro").value = tipo;
		document.getElementById("otroTipo").style.display = 'none';
		return true;
	} else {
		document.getElementById("otroTipo").style.display = 'block';
		document.getElementById("otroTipo").value = '';
	}
}

function valorTipoCont2(tipo) {
	if (tipo != 'Otro') {
		document.getElementById("tipoOtro2").value = tipo;
		document.getElementById("otroTipo2").style.display = 'none';
		return true;
	} else {
		document.getElementById("otroTipo2").style.display = 'block';
		document.getElementById("otroTipo2").value = '';
	}
}

function supContacto() {
	document.getElementById("tipoContacto2").selectedIndex = 0;
	document.getElementById("nombreContacto2").value = '';
	document.getElementById("appPat2").value = '';
	document.getElementById("appMat2").value = '';
	document.getElementById("correoElectronicoContacto2").value = '';
	//document.getElementById("telContacto2").value = '';
	document.getElementById("contacto2").style.display = 'none';
	document.getElementById("linkAddContacto").style.display = 'block';

}

function showContacto() {
	document.getElementById("contacto2").style.display = 'block';
	document.getElementById("linkAddContacto").style.display = 'none';
}

function showCliente() {

	var sizeCliente = 2;

	for ( var c = 2; c < 7; c++) {
		_block = document.getElementById('prove' + c).style.display;
		if (_block == 'block') {
			sizeCliente++;
		}
	}
	document.getElementById('prove' + sizeCliente).style.display = 'block';

	if (sizeCliente == 6) {
		document.getElementById("linkAddProve").style.display = 'none';
	}
}

function supCliente(v) {
	document.getElementById("cliente" + v).value = '';
	document.getElementById("prodCliente" + v).value = '';
	document.getElementById('showProdCliente'+v).value = '';
	document.getElementById('showProdCliente'+v+'Hid').value = '';
	document.getElementById("aniosProveCliente" + v).value = '';
	document.getElementById("mesesProveCliente" + v).value = '';

	document.getElementById('prove' + v).style.display = 'none';

	document.getElementById('linkAddProve').style.display = 'block';
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

function valueCheckNacional(estado){
	if (document.getElementById('checkNacional').checked) {
		document.getElementById('checkEstadoNacional').value = estado;

		for ( var x = 1; x < 33; x++) {
			var estado = document.getElementById('check' + x);
			estado.disabled = true;
			estado.checked = false;
			document.getElementById('checkEstado' + x).value = '';
		}
	}else{
		document.getElementById('checkEstadoNacional').value = '';
		for ( var x = 1; x < 33; x++) {
			var estado = document.getElementById('check' + x);
			estado.disabled = false;
		}
	}
}

function checkSectorUno() {
	if (valorSectorUno.checked) {
		valorSectorDos.disabled = true;
		valorSectorDos.checked = false;
		valorSectorTres.disabled = true;
		valorSectorTres.checked = false;
	} else {
		valorSectorDos.disabled = false;
		valorSectorTres.disabled = false;
	}
}
function checkSectorDos() {
	if (valorSectorDos.checked) {
		valorSectorUno.disabled = true;
		valorSectorUno.checked = false;
		valorSectorTres.disabled = true;
		valorSectorTres.checked = false;
	} else {
		valorSectorUno.disabled = false;
		valorSectorTres.disabled = false;
	}
}
function checkSectorTres() {
	if (valorSectorTres.checked) {
		valorSectorUno.disabled = true;
		valorSectorUno.checked = false;
		valorSectorDos.disabled = true;
		valorSectorDos.checked = false;
	} else {
		valorSectorUno.disabled = false;
		valorSectorDos.disabled = false;
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

function validaNumero(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla==0) return true;		
	if (tecla==8) return true;
	if (tecla==48) return true;
	if (tecla==49) return true;
	if (tecla==50) return true;
	if (tecla==51) return true;
	if (tecla==52) return true;
	if (tecla==53) return true;
	if (tecla==54) return true;
	if (tecla==55) return true;
	if (tecla==56) return true;
	if (tecla==57) return true;
	patron = /1/;
	te = String.fromCharCode(tecla);
	return patron.test(te);	
}

function cambiaCampo(e){
	
	var code = (e.keyCode ? e.keyCode : e.which);
	
	if(code != 8) {
		if(document.getElementById('intTel').value.length == 2){
			document.getElementById('ladaTel').focus();
		}
		if(document.getElementById('ladaTel').value.length == 2){
			document.getElementById('numTel').focus();
		}
		if(document.getElementById('numTel').value.length == 8){
			document.getElementById('extTel').focus();
		}
	}
}

function cambiaCampo2(e){
	
	var code = (e.keyCode ? e.keyCode : e.which);
	
	if(code != 8) {
		if(document.getElementById('intTel2').value.length == 2){
			document.getElementById('ladaTel2').focus();
		}
		if(document.getElementById('ladaTel2').value.length == 2){
			document.getElementById('numTel2').focus();
		}
		if(document.getElementById('numTel2').value.length == 8){
			document.getElementById('extTel2').focus();
		}
	}
}

function agregaProducto() {
	var _prod = document.getElementById('idProducto').value;
	var _productos = 0;
	if (_prod.length == 0) {
		alert("Ingrese un Producto");
	} else {
		for ( var i = 1; i <= 20; i++) {
			if (document.getElementById('idDivProd' + i).style.display == 'block'){
				_productos++;
			}
		}
		if (_productos < 20) {
			var _pos = _productos + 1;
			document.getElementById('idProdHid' + _pos).value = document.getElementById('idProducto').value;
			document.getElementById('labProd' + _pos).innerText = document.getElementById('idProducto').value;
			document.getElementById('idDivProd' + _pos).style.display = 'block';
		}
		document.getElementById('idProducto').value = '';
		document.getElementById('tablaProd').style.display = 'block';
	}
	document.getElementById('idProducto').focus();
}

function quitarProducto(pos) {
	var _last = pos;
	if (pos == '20' || document.getElementById('idDivProd' + (pos + 1)).style.display == 'none') {
		document.getElementById('labProd' + pos).innerText = null;
		document.getElementById('idProdHid' + pos).value = null;
		document.getElementById('idDivProd' + pos).style.display = 'none';
	} else {
		for ( var i = pos; i <= 20; i++) {
			if (document.getElementById('idDivProd' + i).style.display == 'block'){
				_last++;
			}
			if (document.getElementById('labProd' + (i + 1)) != null){
				_lab = document.getElementById('labProd' + (i + 1)).innerText;
			}
			if (document.getElementById('idProdHid' + (i + 1)) != null){
				_hid = document.getElementById('idProdHid' + (i + 1)).value;
			}
			document.getElementById('labProd' + i).innerText = _lab;
			document.getElementById('idProdHid' + i).value = _hid;
		}
		document.getElementById('idDivProd' + (_last - 1)).style.display = 'none';
	}
	
	if(_last == 1){
		document.getElementById('tablaProd').style.display = 'none';
	}
}

function addProdCliente(num){
	
	if(document.getElementById('showProdCliente' + num).value.length == 0){
		var cont = 0;
	}else{
		var cont = 1;
	}

	var _prodCliente = document.getElementById('prodCliente' + num).value;
	var _prodTemp = document.getElementById('showProdCliente' + num).value;
	
	if (_prodCliente.length == 0) {
		alert("Ingrese un Producto");
	} else {
		if(cont == 0){
			document.getElementById('showProdCliente'+ num).innerText = _prodCliente;
			cont++;
		}else{
			document.getElementById('showProdCliente'+ num).innerText = _prodTemp + ', ' + _prodCliente;
		}
		document.getElementById('prodCliente'+ num).value = '';
	}
}