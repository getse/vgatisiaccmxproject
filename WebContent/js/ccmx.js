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
		var url = '${pageContext.request.contextPath}/vinculacion/ccmx/administracion/PyMEsShow.do?cat'
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
										combo.options[0].selected = true;
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

function validacionAddTractora() {
	valorEmpresa = document.getElementById("idEmpresa").value;
	valorNombre = document.getElementById("idNombre").value;
	valorPaterno = document.getElementById("idAppPaterno").value;
	valorMaterno = document.getElementById("idAppMaterno").value;
	valorCorreo = document.getElementById("idCorreoElectronico").value;
	valorCompara = document.getElementById("idComparaCorreo").value;

	if (valorEmpresa == null || valorEmpresa.length == 0
			|| /^\s+$/.test(valorEmpresa)) {
		alert("Ingrese el Nombre de la Empresa");
		document.getElementById("idEmpresa").focus();
		return false;
	} else if (valorNombre == null || valorNombre.length == 0
			|| /^\s+$/.test(valorNombre)) {
		alert("Ingrese el Nombre(s) del Contacto");
		document.getElementById("idNombre").focus();
		return false;
	} else if (valorPaterno == null || valorPaterno.length == 0
			|| /^\s+$/.test(valorPaterno)) {
		alert("Ingrese el Apellido Paterno del Contacto");
		document.getElementById("idAppPaterno").focus();
		return false;
	} else if (valorMaterno == null || valorMaterno.length == 0
			|| /^\s+$/.test(valorMaterno)) {
		alert("Ingrese el Apellido Materno del Contacto");
		document.getElementById("idAppMaterno").focus();
		return false;
	} else if (!(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
			.test(valorCorreo))) {
		alert("Ingrese una dirección de correo electrónico válida");
		document.getElementById("idCorreoElectronico").focus();
		return false;
	} else if (valorCorreo != valorCompara) {
		alert("El correo electrónico no coincide");
		document.getElementById("idComparaCorreo").focus();
		return false;
	} else {
		return true;
	}
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

function desactivaPyME() {
	var del = confirm("¿Desea desactivar la PyME seleccionada?");
	if (del == true) {
		return true;
	} else {
		return false;
	}
}

function activaPyME() {
	var del = confirm("¿Desea Activar la PyME seleccionada?");
	if (del == true) {
		return true;
	} else {
		return false;
	}
}

function showDiplomados() {
	document.frmAnios.submit();
}

function registraDip() {
	var nombreDip = document.getElementById("nomDiplomado").value;
	var anioDip = document.getElementById("anio").selectedIndex;
	var generacionDip = document.getElementById("generacion").selectedIndex;

	if (nombreDip.length == 0 || /^\s+$/.test(nombreDip)) {
		document.getElementById("nomDiplomado").focus();
		alert("Ingrese el nombre del diplomado");
		return false;
	} else if (generacionDip == 0) {
		document.getElementById("generacion").focus();
		alert("Seleccione las generaciones en las que se registrará el diplomado");
		return false;
	} else if (anioDip == 0) {
		document.getElementById("anio").focus();
		alert("Seleccione el año del diplomado");
		return false;
	} else {
		return true;
	}
}

function finalizar(sesion) {
	document.getElementById("numeroSesiones").value = sesion;
	if (validacion(sesion)) {
		if (sesion < 4) {
			if (confirm("Se guardar solo los datos hasta la sesion " + sesion
					+ "\n\n ¿Desea eliminar los datos de sesiones posteriores?")) {
				document.sesionest.submit();
			}
		} else {
			document.sesionest.submit();
		}
	}
}

function siguiente(sesion) {
	document.getElementById("numeroSesiones").value = sesion;
	if (validacion(document.getElementById("numeroSesiones").value) > 0) {
		document.getElementById("numeroSesiones").value = sesion + 1;
		document.getElementById("sesiont" + sesion).style.display = 'none';
		document.getElementById("sesiont" + (sesion + 1)).style.display = 'block';
	}
}

function anterior() {
	sesionAct = document.getElementById("numeroSesiones").value;
	document.getElementById("sesiont" + sesionAct).style.display = 'none';
	document.getElementById("sesiont" + (sesionAct - 1)).style.display = 'block';
	sesionAct = document.getElementById("numeroSesiones").value = sesionAct - 1;
}

function isDate(fecha) {
	var separador = "/";
	function isInteger(s) {
		var i;
		for (i = 0; i < s.length; i++) {
			var c = s.charAt(i);
			if (((c < "0") || (c > "9")))
				return false;
		}
		return true;
	}
	function stripCharsInBag(s, bag) {
		var i;
		var returnString = "";
		for (i = 0; i < s.length; i++) {
			var c = s.charAt(i);
			if (bag.indexOf(c) == -1)
				returnString += c;
		}
		return returnString;
	}
	function posCharsInBags(s, bag) {
		var i;
		var s2 = false;
		var s5 = false;
		for (i = 0; i < s.length; i++) {
			var c = s.charAt(i);
			if (i == 2 && c == separador)
				s2 = true;
			;
			if (i == 5 && c == separador)
				s5 = true;
		}
		return s2 && s5;
	}
	var numeros = stripCharsInBag(fecha, separador);
	if (fecha.length != 10 || numeros.length != 8) {
		return false;
	}
	if (!posCharsInBags(fecha, separador)) {
		return false;
	}
	if (isInteger(numeros) == false) {
		return false;
	}
	return true;
}

function validacion(sesion) {

	var diplomado = document.getElementById("nombreDip").value;

	var idExpositor = document.getElementById("idExpositor" + sesion).value;
	var idSala = document.getElementById("idSala" + sesion).value;
	var ingreso = document.getElementById("ingreso" + sesion).value;
	var hora = document.getElementById("hora" + sesion).value;
	var minuto = document.getElementById("minuto" + sesion).value;
	var horaFin = document.getElementById("horaFin" + sesion).value;
	var minutoFin = document.getElementById("minutoFin" + sesion).value;
	var idInstuctor = document.getElementById("idInstuctor" + sesion).value;
	var calle = document.getElementById("calle" + sesion).value;
	var numExt = document.getElementById("numExt" + sesion).value;
	var numInt = document.getElementById("numInt" + sesion).value;
	var piso = document.getElementById("piso" + sesion).value;
	var colonia = document.getElementById("colonia" + sesion).value;
	var delegacion = document.getElementById("delegacion" + sesion).value;
	var estado = document.getElementById("estado" + sesion).value;
	var codigoPostal = document.getElementById("codigoPostal" + sesion).value;
	var idInfo = document.getElementById("idInfo" + sesion).value;

	if (diplomado.length == 0 || /^\s+$/.test(diplomado)) {
		document.getElementById("nombreDip").focus();
		alert("Ingrese el nombre del diplomado");
		return false;
	} else if (idExpositor == null || idExpositor.length == 0
			|| /^\s+$/.test(idExpositor)) {
		document.getElementById("idExpositor" + sesion).focus();
		alert("Ingrese el expositor de la sesión");
		return false;
	} else if (idSala == null || idSala.length == 0 || /^\s+$/.test(idSala)) {
		document.getElementById("idSala" + sesion).focus();
		alert("Ingrese la sala de la sesión");
		return false;
	} else if (!isDate(ingreso)) {
		document.getElementById("ingreso" + sesion).focus();
		alert("Ingrese la fecha de la sesión");
		return false;
	} else if (hora == null || hora < 0) {
		document.getElementById("hora" + sesion).focus();
		alert("Ingrese la hora de la sesión");
		return false;
	} else if (minuto == null || minuto < 0) {
		document.getElementById("minuto" + sesion).focus();
		alert("Ingrese el minuto de la sesión");
		return false;
	} else if (horaFin == null || horaFin < 0) {
		document.getElementById("horaFin" + sesion).focus();
		alert("Ingrese la hora final de la sesión.");
		return false;
	} else if (minutoFin == null || minutoFin < 0) {
		document.getElementById("minutoFin" + sesion).focus();
		alert("Ingrese el minuto final de la sesión.");
		return false;
	} else if (idInstuctor == null || idInstuctor.length == 0
			|| /^\s+$/.test(idInstuctor)) {
		document.getElementById("idInstuctor" + sesion).focus();
		alert("Ingrese el instructor de la sesión");
		return false;
	} else if (calle == null || calle.length == 0 || /^\s+$/.test(calle)) {
		document.getElementById("calle" + sesion).focus();
		alert("Ingrese la calle");
		return false;
	} else if (numExt == null || numExt.length == 0 || /^\s+$/.test(numExt)) {
		document.getElementById("numExt" + sesion).focus();
		alert("Ingrese el Número exterior");
		return false;
	} else if (colonia == null || colonia.length == 0 || /^\s+$/.test(colonia)) {
		document.getElementById("colonia" + sesion).focus();
		alert("Ingrese la colonia");
		return false;
	} else if (delegacion == null || delegacion.length == 0
			|| /^\s+$/.test(delegacion)) {
		document.getElementById("delegacion" + sesion).focus();
		alert("Ingrese la delegación");
		return false;
	} else if (estado == 0) {
		document.getElementById("estado" + sesion).focus();
		alert("Seleccione un Estado");
		return false;
	} else if (codigoPostal == null || codigoPostal.length == 0
			|| /^\s+$/.test(codigoPostal)) {
		document.getElementById("codigoPostal" + sesion).focus();
		alert("Ingrese el Código Postal");
		return false;
	} else if (idInfo == null || idInfo.length == 0 || /^\s+$/.test(idInfo)) {
		document.getElementById("idInfo" + sesion).focus();
		alert("Ingrese alguna información importante");
		return false;
	} else {
		return true;
	}
}

function deleteDiplomado() {
	if (confirm("¿Desea eliminar totalmente el diplomado?")) {
		document.deleteDip.submit();
	}
}

function solicitarFactura() {
	document.getElementById("menuSeleccionado").value = 1;
	document.frmConfirmacion.submit();
}

var ids = null;
function supArchivoTabla(id, pos) {

	var sup = document.getElementById("contArchivosPago").rows.length;
	var f = document.getElementById('archPago' + pos);
	var t = f.parentNode;
	t.removeChild(f);

	ids = ids + id + ',';
	var i = ids.substring(0, ids.length - 1);
	document.getElementById('eliminarArchivos').value = i;

	document.getElementById('contAyudaDelete').style.display = 'block';

	if (sup == 3) {
		document.getElementById("contArchivosPago").style.display = 'none';
	}
}

function addArchivo() {

	var id = document.getElementById("contArchivos").rows.length;
	var sec = id - 1;

	if (id > 1) {
		if (document.getElementById("pago" + sec).value == '') {
			alert('Seleccione un archivo');
		} else if (document.getElementById("descripcion" + sec).value.length == 0) {
			document.getElementById("descripcion" + sec).focus();
			alert('Ingrese la descripción del archivo seleccionado');
		} else {
			var tra = document.createElement('tr');
			tra.id = 'archivo' + id;

			var tda = document.createElement('td');

			var file = document.createElement('input');
			file.setAttribute('type', 'file');
			file.setAttribute('name', 'serviciosDiplomado.archivos.upload');
			file.id = 'pago' + id;

			var desc = document.createElement('input');
			desc.setAttribute('type', 'text');
			desc.setAttribute('name',
					'serviciosDiplomado.archivos.descripcionArchivos');
			desc.setAttribute('size', '40');
			desc.setAttribute('maxlength', '100');
			desc.id = 'descripcion' + id;

			var labDesc = document.createElement('label');
			labDesc.setAttribute('class', 'etiquetaCaptura');
			labDesc.innerText = 'Descripción del archivo:';

			var labDelete = document.createElement('label');
			labDelete.setAttribute('class', 'quitar');
			labDelete.onclick = new Function("removeArchivo('" + id + "')");
			labDelete.innerText = '-eliminar';

			var newArch = document.getElementById("contArchivos");

			tda.appendChild(file);
			tda.appendChild(labDesc);
			tda.appendChild(desc);
			tda.appendChild(labDelete);

			tra.appendChild(tda);
			newArch.appendChild(tra);
		}
	} else {
		var tra = document.createElement('tr');
		tra.id = 'archivo' + id;

		var tda = document.createElement('td');

		var file = document.createElement('input');
		file.setAttribute('type', 'file');
		file.setAttribute('name', 'serviciosDiplomado.archivos.upload');
		file.id = 'pago' + id;

		var desc = document.createElement('input');
		desc.setAttribute('type', 'text');
		desc.setAttribute('name',
				'serviciosDiplomado.archivos.descripcionArchivos');
		desc.setAttribute('size', '40');
		desc.setAttribute('maxlength', '100');
		desc.id = 'descripcion' + id;

		var labDesc = document.createElement('label');
		labDesc.setAttribute('class', 'etiquetaCaptura');
		labDesc.innerText = 'Descripción del archivo:';

		var labDelete = document.createElement('label');
		labDelete.setAttribute('class', 'quitar');
		labDelete.onclick = new Function("removeArchivo('" + id + "')");
		labDelete.innerText = '-eliminar';

		var newArch = document.getElementById("contArchivos");

		tda.appendChild(file);
		tda.appendChild(labDesc);
		tda.appendChild(desc);
		tda.appendChild(labDelete);

		tra.appendChild(tda);
		newArch.appendChild(tra);

		document.getElementById("contNewArchivo").style.display = 'block';
	}
}

function removeArchivo(val) {

	var filaArch = document.getElementById('archivo' + val);
	var tabla = filaArch.parentNode;
	tabla.removeChild(filaArch);

	if (val == 1) {
		document.getElementById("contNewArchivo").style.display = 'none';
	}
}

function sendInvitacion() {
	document.getElementById("menuSeleccionado2").value = 1;
	document.frmAsistencias.submit();
}

function showDiplomas() {
	document.getElementById("menuSeleccionado2").value = 2;
	document.frmAsistencias.submit();
}

function listAsistentes() {
	if (document.getElementById("sesion1").checked
			|| document.getElementById("sesion1").checked
			|| document.getElementById("sesion1").checked
			|| document.getElementById("sesion1").checked) {
		document.getElementById("menuSeleccionado2").value = 3;
		document.frmAsistencias.submit();
	} else {
		alert("Seleccione almenos una sesión.");
	}
}

function showFormAsistente() {
	document.getElementById("contFormA").style.display = 'block';
	document.getElementById("labCancelaAsistente").style.display = 'block';
	document.getElementById("labShowForm").style.display = 'none';

	document.getElementById("contFormA").style.display = 'block';
	document.getElementById("tablaReg").style.display = 'none';
}

function addAsistente() {

	var secuencia = document.getElementById("cuerpoTablaReg").rows.length;
	var idTotal = secuencia + 1;

	var nombre = document.getElementById('nombre').value;
	var paterno = document.getElementById('apPaterno').value;
	var materno = document.getElementById('apMaterno').value;
	var lada = document.getElementById('ladaTel').value;
	var numTel = document.getElementById('numTel').value;
	var correo = document.getElementById('correo').value;
	var cargo = document.getElementById('cargo').value;

	if (nombre.length == 0 || /^\s+$/.test(nombre.value)) {
		document.getElementById("nombre").focus();
		alert("Ingrese el nombre del asistente.");
	} else if (paterno.length == 0 || /^\s+$/.test(paterno)) {
		document.getElementById("apPaterno").focus();
		alert("Ingrese el apellido paterno del asistente.");
	} else if (materno.length == 0 || /^\s+$/.test(materno)) {
		document.getElementById("apMaterno").focus();
		alert("Ingrese el apellido materno del asistente.");
	} else if (lada.length < 2 || /^\s+$/.test(lada)) {
		document.getElementById("ladaTel").focus();
		alert("El campo lada debe contener dos o tres dígitos.");
		return false;
	} else if (lada.length == 2 && numTel.length != 8 || /^\s+$/.test(numTel)) {
		document.getElementById("numTel").focus();
		alert("El campo Teléfono debe contener ocho dígitos.");
		return false;
	} else if (lada.length == 3 && numTel.length != 7 || /^\s+$/.test(numTel)) {
		document.getElementById("numTel").focus();
		alert("El campo Teléfono debe contener siete dígitos.");
		return false;
	} else if (!(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
			.test(correo))) {
		document.getElementById("correo").focus();
		alert("Ingrese el Correo Electrónico del asistente, con un formato válido.");
	} else if (cargo.length == 0 || /^\s+$/.test(cargo)) {
		document.getElementById("cargo").focus();
		alert("Ingrese el cargo del asistente.");
	} else {

		var _ladaTel = document.getElementById('ladaTel').value;
		var _numeroTel = document.getElementById('numTel').value;
		var extTel = document.getElementById('extTel').value;

		var _miTel = '(52)(' + _ladaTel + ')(' + _numeroTel + ')(' + extTel
				+ ')';

		var tr = document.createElement('tr');
		tr.id = 'asistente' + idTotal;

		var td1 = document.createElement('td');
		td1.setAttribute('class', 'cuerpo1TablaResumen');
		td1.setAttribute('align', 'center');

		var td2 = document.createElement('td');
		td2.setAttribute('class', 'cuerpo1TablaResumen');
		td2.setAttribute('align', 'center');

		var td3 = document.createElement('td');
		td3.setAttribute('class', 'cuerpo1TablaResumen');
		td3.setAttribute('align', 'center');

		var td4 = document.createElement('td');
		td4.setAttribute('class', 'cuerpo1TablaResumen');
		td4.setAttribute('align', 'center');

		var td5 = document.createElement('td');
		td5.setAttribute('class', 'cuerpo1TablaResumen');
		td5.setAttribute('align', 'center');

		var td6 = document.createElement('td');
		td6.setAttribute('class', 'cuerpo1TablaResumen');
		td6.setAttribute('align', 'center');

		var td7 = document.createElement('td');
		td7.setAttribute('class', 'cuerpo1TablaResumen');
		td7.setAttribute('align', 'center');

		var td8 = document.createElement('td');
		td8.setAttribute('class', 'cuerpo1TablaResumen');
		td8.setAttribute('align', 'center');

		var td9 = document.createElement('td');
		td9.setAttribute('class', 'cuerpo1TablaResumen');
		td9.setAttribute('align', 'center');

		var labCont = document.createElement('label');
		labCont.setAttribute('class', 'etiquetaCaptura');
		labCont.id = 'labContador' + idTotal;
		labCont.innerText = secuencia + 1;

		var labNom = document.createElement('label');
		labNom.setAttribute('class', 'etiquetaCaptura');
		labNom.id = 'labNombre' + idTotal;
		labNom.innerText = nombre;

		var labPat = document.createElement('label');
		labPat.setAttribute('class', 'etiquetaCaptura');
		labPat.id = 'labApPaterno' + idTotal;
		labPat.innerText = paterno;

		var labMat = document.createElement('label');
		labMat.setAttribute('class', 'etiquetaCaptura');
		labMat.id = 'labApMaterno' + idTotal;
		labMat.innerText = materno;

		var labTel = document.createElement('label');
		labTel.setAttribute('class', 'etiquetaCaptura');
		labTel.id = 'labTelefono' + idTotal;
		labTel.innerText = _miTel;

		var labEmail = document.createElement('label');
		labEmail.setAttribute('class', 'etiquetaCaptura');
		labEmail.id = 'labCorreo' + idTotal;
		labEmail.innerText = correo;

		var labCargo = document.createElement('label');
		labCargo.setAttribute('class', 'etiquetaCaptura');
		labCargo.id = 'labCargo' + idTotal;
		labCargo.innerText = cargo;

		var labEdita = document.createElement('label');
		labEdita.setAttribute('class', 'quitar');
		labEdita.onclick = new Function("editAsistente('" + idTotal + "')");
		labEdita.innerText = '-editar';

		var idHid = document.createElement('input');
		idHid.setAttribute('type', 'hidden');
		idHid.setAttribute('name', 'serviciosDiplomado.asistentes[' + secuencia
				+ '].idAsistente');
		idHid.setAttribute('value', '0');
		idHid.id = 'idAsisHid' + idTotal;

		var nomHid = document.createElement('input');
		nomHid.setAttribute('type', 'hidden');
		nomHid.setAttribute('name', 'serviciosDiplomado.asistentes['
				+ secuencia + '].nombre');
		nomHid.setAttribute('value', nombre);
		nomHid.id = 'nombreHid' + idTotal;

		var patHid = document.createElement('input');
		patHid.setAttribute('type', 'hidden');
		patHid.setAttribute('name', 'serviciosDiplomado.asistentes['
				+ secuencia + '].appPaterno');
		patHid.setAttribute('value', paterno);
		patHid.id = 'apPaternoHid' + idTotal;

		var matHid = document.createElement('input');
		matHid.setAttribute('type', 'hidden');
		matHid.setAttribute('name', 'serviciosDiplomado.asistentes['
				+ secuencia + '].appMaterno');
		matHid.setAttribute('value', materno);
		matHid.id = 'apMaternoHid' + idTotal;

		var telHid = document.createElement('input');
		telHid.setAttribute('type', 'hidden');
		telHid.setAttribute('name', 'serviciosDiplomado.asistentes['
				+ secuencia + '].telefono');
		telHid.setAttribute('value', _miTel);
		telHid.id = 'telefonoHid' + idTotal;

		var correoHid = document.createElement('input');
		correoHid.setAttribute('type', 'hidden');
		correoHid.setAttribute('name', 'serviciosDiplomado.asistentes['
				+ secuencia + '].correoElectronico');
		correoHid.setAttribute('value', correo);
		correoHid.id = 'correoHid' + idTotal;

		var cargoHid = document.createElement('input');
		cargoHid.setAttribute('type', 'hidden');
		cargoHid.setAttribute('name', 'serviciosDiplomado.asistentes['
				+ secuencia + '].cargo');
		cargoHid.setAttribute('value', cargo);
		cargoHid.id = 'cargoHid' + idTotal;
		var asistente = document.getElementById("cuerpoTablaReg");

		td1.appendChild(labCont);
		td1.appendChild(idHid);
		td2.appendChild(labNom);
		td2.appendChild(nomHid);
		td3.appendChild(labPat);
		td3.appendChild(patHid);
		td4.appendChild(labMat);
		td4.appendChild(matHid);
		td5.appendChild(labTel);
		td5.appendChild(telHid);
		td6.appendChild(labEmail);
		td6.appendChild(correoHid);
		td7.appendChild(labCargo);
		td7.appendChild(cargoHid);
		td9.appendChild(labEdita);

		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		tr.appendChild(td6);
		tr.appendChild(td7);
		tr.appendChild(td8);
		tr.appendChild(td9);
		asistente.appendChild(tr);

		document.getElementById("contFormA").style.display = 'none';
		document.getElementById("labShowForm").style.display = 'block';

		document.getElementById('nombre').value = '';
		document.getElementById('apPaterno').value = '';
		document.getElementById('apMaterno').value = '';
		document.getElementById('ladaTel').value = '';
		document.getElementById('numTel').value = '';
		document.getElementById('extTel').value = '';
		document.getElementById('correo').value = '';
		document.getElementById('cargo').value = '';

		document.getElementById("tablaReg").style.display = 'block';
	}

}

function editAsistente(pos) {

	var telContacto = document.getElementById('telefonoHid' + pos).value;
	var ladaTel = document.getElementById('ladaTel');
	var numTel = document.getElementById('numTel');
	var extTel = document.getElementById('extTel');

	if (telContacto != 'null') {
		var separaCampos = telContacto.split(')(');

		ladaTel.value = separaCampos[1];
		numTel.value = separaCampos[2];
		extTel.value = separaCampos[3].substring(0,
				(separaCampos[3].length - 1));
	}

	document.getElementById('posTabla').value = pos;

	document.getElementById('nombre').value = document
			.getElementById('nombreHid' + pos).value;
	document.getElementById('apPaterno').value = document
			.getElementById('apPaternoHid' + pos).value;
	document.getElementById('apMaterno').value = document
			.getElementById('apMaternoHid' + pos).value;
	document.getElementById('correo').value = document
			.getElementById('correoHid' + pos).value;
	document.getElementById('cargo').value = document.getElementById('cargoHid'
			+ pos).value;

	document.getElementById("contFormA").style.display = 'block';
	document.getElementById("labShowForm").style.display = 'none';
	document.getElementById("labFinEdit").style.display = 'block';
	document.getElementById("labAddAsistente").style.display = 'none';
	document.getElementById("labCancelaAsistente").style.display = 'none';
	document.getElementById("AgregarAsistenteDiv").style.display = 'none';

	document.getElementById("contFormA").style.display = 'block';
	document.getElementById("tablaReg").style.display = 'none';
}

function finEditAsistente() {

	if (document.getElementById('nombre').value.length == 0
			|| /^\s+$/.test(document.getElementById('nombre').value)) {
		document.getElementById("nombre").focus();
		alert("Ingrese el nombre del asistente.");
	} else if (document.getElementById('apPaterno').value.length == 0
			|| /^\s+$/.test(document.getElementById('apPaterno').value)) {
		document.getElementById("apPaterno").focus();
		alert("Ingrese el apellido paterno del asistente.");
	} else if (document.getElementById('apMaterno').value.length == 0
			|| /^\s+$/.test(document.getElementById('apMaterno').value)) {
		document.getElementById("apMaterno").focus();
		alert("Ingrese el apellido materno del asistente.");
	} else if (document.getElementById('ladaTel').value.length < 2
			|| /^\s+$/.test(document.getElementById('ladaTel').value)) {
		document.getElementById("ladaTel").focus();
		alert("El campo de lada debe contener dos o tres dígitos.");
	} else if (document.getElementById('ladaTel').value.length == 2
			&& document.getElementById('numTel').value.length != 8
			|| /^\s+$/.test(document.getElementById('numTel').value)) {
		document.getElementById("numTel").focus();
		alert("El campo Teléfono debe contener ocho dígitos");
	} else if (document.getElementById('ladaTel').value.length == 3
			&& document.getElementById('numTel').value.length != 7
			|| /^\s+$/.test(document.getElementById('numTel').value)) {
		document.getElementById("numTel").focus();
		alert("El campo Teléfono debe contener siete dígitos");
	} else if (!(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
			.test(document.getElementById('correo').value))) {
		document.getElementById("correo").focus();
		alert("Ingrese el Correo Electrónico del asistente, con un formato válido.");
	} else if (document.getElementById('cargo').value.length == 0
			|| /^\s+$/.test(document.getElementById('cargo').value)) {
		document.getElementById("cargo").focus();
		alert("Ingrese el cargo del asistente.");
	} else {
		var pos = document.getElementById('posTabla').value;

		var _ladaTel = document.getElementById('ladaTel').value;
		var _numeroTel = document.getElementById('numTel').value;
		var extTel = document.getElementById('extTel').value;
		var _miTel = '(52)(' + _ladaTel + ')(' + _numeroTel + ')(' + extTel
				+ ')';

		document.getElementById('nombreHid' + pos).value = document
				.getElementById('nombre').value;
		document.getElementById('apPaternoHid' + pos).value = document
				.getElementById('apPaterno').value;
		document.getElementById('apMaternoHid' + pos).value = document
				.getElementById('apMaterno').value;
		document.getElementById('telefonoHid' + pos).value = _miTel;
		document.getElementById('correoHid' + pos).value = document
				.getElementById('correo').value;
		document.getElementById('cargoHid' + pos).value = document
				.getElementById('cargo').value;

		document.getElementById('labNombre' + pos).innerText = document
				.getElementById('nombre').value;
		document.getElementById('labApPaterno' + pos).innerText = document
				.getElementById('apPaterno').value;
		document.getElementById('labApMaterno' + pos).innerText = document
				.getElementById('apMaterno').value;
		document.getElementById('labTelefono' + pos).innerText = _miTel;
		document.getElementById('labCorreo' + pos).innerText = document
				.getElementById('correo').value;
		document.getElementById('labCargo' + pos).innerText = document
				.getElementById('cargo').value;

		document.getElementById("contFormA").style.display = 'none';
		document.getElementById("labShowForm").style.display = 'block';
		document.getElementById("labFinEdit").style.display = 'none';
		document.getElementById("labAddAsistente").style.display = 'block';
		document.getElementById("labCancelaAsistente").style.display = 'block';

		document.getElementById("contFormA").style.display = 'none';
		document.getElementById("tablaReg").style.display = 'block';

		document.getElementById('nombre').value = '';
		document.getElementById('apPaterno').value = '';
		document.getElementById('apMaterno').value = '';
		document.getElementById('ladaTel').value = '';
		document.getElementById('numTel').value = '';
		document.getElementById('extTel').value = '';
		document.getElementById('correo').value = '';
		document.getElementById('cargo').value = '';

	}
}

function cancelaRegAsistente() {

	if (document.getElementById('nombre').value.length != 0
			|| document.getElementById('apPaterno').value.length != 0
			|| document.getElementById('apMaterno').value.length != 0
			|| document.getElementById('ladaTel').value.length != 0
			|| document.getElementById('numTel').value.length != 0
			|| document.getElementById('extTel').value.length != 0
			|| document.getElementById('correo').value.length != 0
			|| document.getElementById('cargo').value.length != 0) {

		var del = confirm("¿Desea cancelar el registro del asistente?. Los datos capturados en el formulario no serán almacenados");
		if (del == true) {

			document.getElementById("contFormA").style.display = 'none';
			document.getElementById("labShowForm").style.display = 'block';
			document.getElementById("labFinEdit").style.display = 'none';
			document.getElementById("labAddAsistente").style.display = 'block';

			document.getElementById("contFormA").style.display = 'none';
			document.getElementById("tablaReg").style.display = 'block';

			document.getElementById('nombre').value = '';
			document.getElementById('apPaterno').value = '';
			document.getElementById('apMaterno').value = '';
			document.getElementById('ladaTel').value = '';
			document.getElementById('numTel').value = '';
			document.getElementById('extTel').value = '';
			document.getElementById('correo').value = '';
			document.getElementById('cargo').value = '';
		}
	} else {
		document.getElementById("contFormA").style.display = 'none';
		document.getElementById("labShowForm").style.display = 'block';
		document.getElementById("labFinEdit").style.display = 'none';
		document.getElementById("labAddAsistente").style.display = 'block';
		document.getElementById("labCancelaAsistente").style.display = 'block';

		document.getElementById("contFormA").style.display = 'none';
		document.getElementById("tablaReg").style.display = 'block';
	}
}

function validaChecInasistencia() {
	formulario = document.getElementById("inasistencias");
	for ( var i = 0; i < formulario.elements.length; i++) {
		var elemento = formulario.elements[i];
		if (elemento.type == "checkbox") {
			if (elemento.checked) {
				return true;
			}
		}
	}
	alert("No se ha seleccionado ningun Asistente");
	return false;
}

function validaAsistentes() {
	alert("Ya hay validación");
	return false;
}

function validaDocumento() {
	rol = document.getElementById('idRolDocumento');
	doc = document.getElementById('idDocumento');

	if (rol.value == -1) {
		alert('Seleccione el perfil al que desea subir un documento.');
		rol.focus();
		return false;
	}
	if (doc.value == '') {
		alert('Seleccione el documento que desea subir.');
		return false;
	}

	return true;
}

function cancelaDocumento() {
	document.frmCancelaDocumento.submit();
}

function cambiarCorreo(id, original) {
	var cve = prompt('Estimado usuario, introduzca el nuevo correo electrónico:');
	if (cve == null)
		return false;
	if (!(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(cve))) {
		alert("Ingrese una dirección de correo electrónico válida por favor.");
		return false;
	}

	document.getElementById('idCambiaCorreoId').value = id;
	document.getElementById('idCambiaCorreoOriginal').value = original;
	document.getElementById('idCambiaCorreoNuevo').value = cve;
	document.frmCambiarCorreo.submit();
}