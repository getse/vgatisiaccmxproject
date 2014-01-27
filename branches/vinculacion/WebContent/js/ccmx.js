var peticion = false;
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
function showCombo(n, o) {
	var p = document.getElementById('catProd' + o);
	if (o == 6) {
		var q = document.getElementById('catProd5');
		for ( var i = 0; i < q.length; i++) {
			_v = q.options[i].value;
			_t = q.options[i].text;
			if (_v == n) {
				document.getElementById('idInputCatScian').value = _t;
				document.getElementById('idCveSci').value = _v;
				document.getElementById('idInputCatScian').rows = _t.length > 85 ? 2
						: 1
			}
		}
	} else {
		$("#catProd" + o).html(
				'<option selected="selected" value="0">Cargando...</option>');
		p.style.display = 'block';
		p.disabled = true;
		var r = (o == 2 ? 3 : (o == 3 ? 4 : (o == 4 ? 5 : (o == 5 ? 6 : 1))));
		var s = '${pageContext.request.contextPath}/vinculacion/ccmx/administracion/PyMEsShow.do?cat'
				+ (o - 1) + '=' + n;
		peticion.open("GET", s, true);
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
									if (m.length == r && !isNaN(m)) {
										p.options[0] = new Option(
												'--Seleccione una opción--', 0);
										p.options[x] = new Option(f, m);
										p.options[0].selected = true;
										x++
									}
								}
							}
						}
					}
				}
				document.getElementById('catProd' + o).disabled = false
			}
		};
		peticion.send(null)
	}
}
function focoAyuda(a) {
	document.getElementById(a).style.display = 'block';
	document.getElementById(a + '2').style.display = 'none'
}
function blurAyuda(a) {
	document.getElementById(a).style.display = 'none';
	document.getElementById(a + '2').style.display = 'block'
}
function validacionBusqueda() {
	a = document.getElementById("campoBusqueda").value.split(" ");
	document.getElementById('idProd').value = document
			.getElementById('idInputCatScian').value;
	if (a == null || a == 0 || a.length > 3 || a == " ") {
		document.getElementById("campoBusqueda").focus();
		alert("Para realizar una búsqueda escriba en 3 palabras el producto");
		return false
	} else {
		return true
	}
}
function validacionAddTractora() {
	a = document.getElementById("idEmpresa").value;
	b = document.getElementById("idNombre").value;
	c = document.getElementById("idAppPaterno").value;
	d = document.getElementById("idAppMaterno").value;
	e = document.getElementById("idCorreoElectronico").value;
	f = document.getElementById("idComparaCorreo").value;
	if (a == null || a.length == 0 || /^\s+$/.test(a)) {
		alert("Ingrese el Nombre de la Empresa");
		document.getElementById("idEmpresa").focus();
		return false
	} else if (b == null || b.length == 0 || /^\s+$/.test(b)) {
		alert("Ingrese el Nombre(s) del Contacto");
		document.getElementById("idNombre").focus();
		return false
	} else if (c == null || c.length == 0 || /^\s+$/.test(c)) {
		alert("Ingrese el Apellido Paterno del Contacto");
		document.getElementById("idAppPaterno").focus();
		return false
	} else if (d == null || d.length == 0 || /^\s+$/.test(d)) {
		alert("Ingrese el Apellido Materno del Contacto");
		document.getElementById("idAppMaterno").focus();
		return false
	} else if (!(/[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(e))) {
		alert("Ingrese una dirección de correo electrónico válida");
		document.getElementById("idCorreoElectronico").focus();
		return false
	} else if (e != f) {
		alert("El correo electrónico no coincide");
		document.getElementById("idComparaCorreo").focus();
		return false
	} else {
		return true
	}
}
function validaLetra(e) {
	a = (document.all) ? e.keyCode : e.which;
	if (a == 0)
		return true;
	if (a == 8)
		return true;
	b = /[A-ZÑña-z\s]/;
	c = String.fromCharCode(a);
	return b.test(c)
}
function desactivaPyME() {
	var a = confirm("¿Desea desactivar la PyME seleccionada?");
	if (a == true) {
		return true
	} else {
		return false
	}
}
function activaPyME() {
	var a = confirm("¿Desea Activar la PyME seleccionada?");
	if (a == true) {
		return true
	} else {
		return false
	}
}
function showDiplomados() {
	document.frmAnios.submit()
}
function registraDip() {
	var a = document.getElementById("nomDiplomado").value;
	var b = document.getElementById("anio").selectedIndex;
	var c = document.getElementById("generacionInicio").selectedIndex;
	var d = document.getElementById("generacion").selectedIndex;
	if (a.length == 0 || /^\s+$/.test(a)) {
		document.getElementById("nomDiplomado").focus();
		alert("Ingrese el nombre del diplomado");
		return false
	} else if (c == 0) {
		document.getElementById("generacionInicio").focus();
		alert("Seleccione la generación inicial en la que se registrará el diplomado");
		return false
	} else if (d == 0) {
		document.getElementById("generacion").focus();
		alert("Seleccione la generación final en la que se registrará el diplomado");
		return false
	} else if (c > d) {
		document.getElementById("generacionInicio").focus();
		alert("Selección incorrecta de generaciones, la generación inicial debe ser menor a la generación final");
		return false
	} else if (b == 0) {
		document.getElementById("anio").focus();
		alert("Seleccione el año del diplomado");
		return false
	} else {
		return true
	}
}
function finalizar(a) {
	document.getElementById("numeroSesiones").value = a;
	if (validacion(a)) {
		if (a < 4) {
			if (confirm("Se guardar solo los datos hasta la sesion " + a
					+ "\n\n ¿Desea eliminar los datos de sesiones posteriores?")) {
				document.sesionest.submit()
			}
		} else {
			document.sesionest.submit()
		}
	}
}
function siguiente(a) {
	document.getElementById("numeroSesiones").value = a;
	if (validacion(document.getElementById("numeroSesiones").value) > 0) {
		document.getElementById("numeroSesiones").value = a + 1;
		document.getElementById("sesiont" + a).style.display = 'none';
		document.getElementById("sesiont" + (a + 1)).style.display = 'block'
	}
}
function anterior() {
	a = document.getElementById("numeroSesiones").value;
	document.getElementById("sesiont" + a).style.display = 'none';
	document.getElementById("sesiont" + (a - 1)).style.display = 'block';
	a = document.getElementById("numeroSesiones").value = a - 1
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
function validacion(a) {
	var b = document.getElementById("nombreDip").value;
	var c = document.getElementById("idExpositor" + a).value;
	var d = document.getElementById("idSala" + a).value;
	var e = document.getElementById("ingreso" + a).value;
	var f = document.getElementById("hora" + a).value;
	var g = document.getElementById("minuto" + a).value;
	var h = document.getElementById("horaFin" + a).value;
	var i = document.getElementById("minutoFin" + a).value;
	var j = document.getElementById("idInstuctor" + a).value;
	var k = document.getElementById("calle" + a).value;
	var l = document.getElementById("numExt" + a).value;
	var m = document.getElementById("colonia" + a).value;
	var n = document.getElementById("delegacion" + a).value;
	var o = document.getElementById("estado" + a).value;
	var p = document.getElementById("codigoPostal" + a).value;
	var q = document.getElementById("idInfo" + a).value;
	if (b.length == 0 || /^\s+$/.test(b)) {
		document.getElementById("nombreDip").focus();
		alert("Ingrese el nombre del diplomado");
		return false
	} else if (c == null || c.length == 0 || /^\s+$/.test(c)) {
		document.getElementById("idExpositor" + a).focus();
		alert("Ingrese el expositor de la sesión");
		return false
	} else if (d == null || d.length == 0 || /^\s+$/.test(d)) {
		document.getElementById("idSala" + a).focus();
		alert("Ingrese la sala de la sesión");
		return false
	} else if (!isDate(e)) {
		document.getElementById("ingreso" + a).focus();
		alert("Ingrese la fecha de la sesión");
		return false
	} else if (f == null || f < 0) {
		document.getElementById("hora" + a).focus();
		alert("Ingrese la hora de la sesión");
		return false
	} else if (g == null || g < 0) {
		document.getElementById("minuto" + a).focus();
		alert("Ingrese el minuto de la sesión");
		return false
	} else if (h == null || h < 0) {
		document.getElementById("horaFin" + a).focus();
		alert("Ingrese la hora final de la sesión.");
		return false
	} else if (i == null || i < 0) {
		document.getElementById("minutoFin" + a).focus();
		alert("Ingrese el minuto final de la sesión.");
		return false
	} else if (j == null || j.length == 0 || /^\s+$/.test(j)) {
		document.getElementById("idInstuctor" + a).focus();
		alert("Ingrese el instructor de la sesión");
		return false
	} else if (k == null || k.length == 0 || /^\s+$/.test(k)) {
		document.getElementById("calle" + a).focus();
		alert("Ingrese la calle");
		return false
	} else if (l == null || l.length == 0 || /^\s+$/.test(l)) {
		document.getElementById("numExt" + a).focus();
		alert("Ingrese el Número exterior");
		return false
	} else if (m == null || m.length == 0 || /^\s+$/.test(m)) {
		document.getElementById("colonia" + a).focus();
		alert("Ingrese la colonia");
		return false
	} else if (n == null || n.length == 0 || /^\s+$/.test(n)) {
		document.getElementById("delegacion" + a).focus();
		alert("Ingrese la delegación");
		return false
	} else if (o == 0) {
		document.getElementById("estado" + a).focus();
		alert("Seleccione un Estado");
		return false
	} else if (p == null || p.length == 0 || /^\s+$/.test(p)) {
		document.getElementById("codigoPostal" + a).focus();
		alert("Ingrese el Código Postal");
		return false
	} else if (q == null || q.length == 0 || /^\s+$/.test(q)) {
		document.getElementById("idInfo" + a).focus();
		alert("Ingrese alguna información importante");
		return false
	} else {
		return true
	}
}
function deleteDiplomado() {
	if (confirm("¿Desea eliminar totalmente el diplomado?")) {
		document.deleteDip.submit()
	}
}
function solicitarFactura() {
	document.getElementById("menuSeleccionado").value = 1;
	document.frmConfirmacion.submit()
}
var ids = null;
function supArchivoTabla(a, b) {
	var c = document.getElementById("contArchivosPago").rows.length;
	var f = document.getElementById('archPago' + b);
	var t = f.parentNode;
	t.removeChild(f);
	ids = ids + a + ',';
	var i = ids.substring(0, ids.length - 1);
	document.getElementById('eliminarArchivos').value = i;
	document.getElementById('contAyudaDelete').style.display = 'block';
	if (c == 3) {
		document.getElementById("contArchivosPago").style.display = 'none'
	}
}
function addArchivo() {
	var a = document.getElementById("contArchivos").rows.length;
	var b = a - 1;
	if (a > 1) {
		if (document.getElementById("pago" + b).value == '') {
			alert('Seleccione un archivo')
		} else if (document.getElementById("descripcion" + b).value.length == 0) {
			document.getElementById("descripcion" + b).focus();
			alert('Ingrese la descripción del archivo seleccionado')
		} else {
			var c = document.createElement('tr');
			c.id = 'archivo' + a;
			var d = document.createElement('td');
			var e = document.createElement('input');
			e.setAttribute('type', 'file');
			e.setAttribute('name', 'serviciosDiplomado.archivos.upload');
			e.id = 'pago' + a;
			var f = document.createElement('input');
			f.setAttribute('type', 'text');
			f.setAttribute('name',
					'serviciosDiplomado.archivos.descripcionArchivos');
			f.setAttribute('size', '40');
			f.setAttribute('maxlength', '100');
			f.id = 'descripcion' + a;
			var g = document.createElement('label');
			g.setAttribute('class', 'etiquetaCaptura');
			g.innerText = 'Descripción del archivo:';
			var h = document.createElement('label');
			h.setAttribute('class', 'quitar');
			h.onclick = new Function("removeArchivo('" + a + "')");
			h.innerText = '-eliminar';
			var i = document.getElementById("contArchivos");
			d.appendChild(e);
			d.appendChild(g);
			d.appendChild(f);
			d.appendChild(h);
			c.appendChild(d);
			i.appendChild(c)
		}
	} else {
		var c = document.createElement('tr');
		c.id = 'archivo' + a;
		var d = document.createElement('td');
		var e = document.createElement('input');
		e.setAttribute('type', 'file');
		e.setAttribute('name', 'serviciosDiplomado.archivos.upload');
		e.id = 'pago' + a;
		var f = document.createElement('input');
		f.setAttribute('type', 'text');
		f.setAttribute('name',
				'serviciosDiplomado.archivos.descripcionArchivos');
		f.setAttribute('size', '40');
		f.setAttribute('maxlength', '100');
		f.id = 'descripcion' + a;
		var g = document.createElement('label');
		g.setAttribute('class', 'etiquetaCaptura');
		g.innerText = 'Descripción del archivo:';
		var h = document.createElement('label');
		h.setAttribute('class', 'quitar');
		h.onclick = new Function("removeArchivo('" + a + "')");
		h.innerText = '-eliminar';
		var i = document.getElementById("contArchivos");
		d.appendChild(e);
		d.appendChild(g);
		d.appendChild(f);
		d.appendChild(h);
		c.appendChild(d);
		i.appendChild(c);
		document.getElementById("contNewArchivo").style.display = 'block'
	}
}
function removeArchivo(a) {
	var b = document.getElementById('archivo' + a);
	var c = b.parentNode;
	c.removeChild(b);
	if (a == 1) {
		document.getElementById("contNewArchivo").style.display = 'none'
	}
}
function sendInvitacion() {
	document.getElementById("menuSeleccionado2").value = 1;
	document.frmAsistencias.submit()
}
function showDiplomas() {
	document.getElementById("menuSeleccionado2").value = 2;
	document.frmAsistencias.submit()
}
function listAsistentes() {
	if (document.getElementById("sesion1").checked
			|| document.getElementById("sesion1").checked
			|| document.getElementById("sesion1").checked
			|| document.getElementById("sesion1").checked) {
		document.getElementById("menuSeleccionado2").value = 3;
		document.frmAsistencias.submit()
	} else {
		alert("Seleccione almenos una sesión.")
	}
}
function showFormAsistente() {
	document.getElementById("contFormA").style.display = 'block';
	document.getElementById("labCancelaAsistente").style.display = 'block';
	document.getElementById("labShowForm").style.display = 'none';
	document.getElementById("contFormA").style.display = 'block';
	document.getElementById("tablaReg").style.display = 'none'
}
function addAsistente() {
	var a = document.getElementById("cuerpoTablaReg").rows.length;
	var b = a + 1;
	var c = document.getElementById('nombre').value;
	var d = document.getElementById('apPaterno').value;
	var e = document.getElementById('apMaterno').value;
	var f = document.getElementById('ladaTel').value;
	var g = document.getElementById('numTel').value;
	var h = document.getElementById('correo').value;
	var i = document.getElementById('cargo').value;
	if (c.length == 0 || /^\s+$/.test(c.value)) {
		document.getElementById("nombre").focus();
		alert("Ingrese el nombre del asistente.")
	} else if (d.length == 0 || /^\s+$/.test(d)) {
		document.getElementById("apPaterno").focus();
		alert("Ingrese el apellido paterno del asistente.")
	} else if (e.length == 0 || /^\s+$/.test(e)) {
		document.getElementById("apMaterno").focus();
		alert("Ingrese el apellido materno del asistente.")
	} else if (f.length < 2 || /^\s+$/.test(f)) {
		document.getElementById("ladaTel").focus();
		alert("El campo lada debe contener dos o tres dígitos.");
		return false
	} else if (f.length == 2 && g.length != 8 || /^\s+$/.test(g)) {
		document.getElementById("numTel").focus();
		alert("El campo Teléfono debe contener ocho dígitos.");
		return false
	} else if (f.length == 3 && g.length != 7 || /^\s+$/.test(g)) {
		document.getElementById("numTel").focus();
		alert("El campo Teléfono debe contener siete dígitos.");
		return false
	} else if (!(/[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(h))) {
		document.getElementById("correo").focus();
		alert("Ingrese el Correo Electrónico del asistente, con un formato válido.")
	} else if (i.length == 0 || /^\s+$/.test(i)) {
		document.getElementById("cargo").focus();
		alert("Ingrese el cargo del asistente.")
	} else {
		var j = document.getElementById('ladaTel').value;
		var k = document.getElementById('numTel').value;
		var l = document.getElementById('extTel').value;
		var m = '(52)(' + j + ')(' + k + ')(' + l + ')';
		var n = document.createElement('tr');
		n.id = 'asistente' + b;
		var o = document.createElement('td');
		o.setAttribute('class', 'cuerpo1TablaResumen');
		o.setAttribute('align', 'center');
		var p = document.createElement('td');
		p.setAttribute('class', 'cuerpo1TablaResumen');
		p.setAttribute('align', 'center');
		var q = document.createElement('td');
		q.setAttribute('class', 'cuerpo1TablaResumen');
		q.setAttribute('align', 'center');
		var r = document.createElement('td');
		r.setAttribute('class', 'cuerpo1TablaResumen');
		r.setAttribute('align', 'center');
		var s = document.createElement('td');
		s.setAttribute('class', 'cuerpo1TablaResumen');
		s.setAttribute('align', 'center');
		var t = document.createElement('td');
		t.setAttribute('class', 'cuerpo1TablaResumen');
		t.setAttribute('align', 'center');
		var u = document.createElement('td');
		u.setAttribute('class', 'cuerpo1TablaResumen');
		u.setAttribute('align', 'center');
		var v = document.createElement('td');
		v.setAttribute('class', 'cuerpo1TablaResumen');
		v.setAttribute('align', 'center');
		var w = document.createElement('td');
		w.setAttribute('class', 'cuerpo1TablaResumen');
		w.setAttribute('align', 'center');
		var x = document.createElement('label');
		x.setAttribute('class', 'etiquetaCaptura');
		x.id = 'labContador' + b;
		x.innerText = a + 1;
		var y = document.createElement('label');
		y.setAttribute('class', 'etiquetaCaptura');
		y.id = 'labNombre' + b;
		y.innerText = c;
		var z = document.createElement('label');
		z.setAttribute('class', 'etiquetaCaptura');
		z.id = 'labApPaterno' + b;
		z.innerText = d;
		var A = document.createElement('label');
		A.setAttribute('class', 'etiquetaCaptura');
		A.id = 'labApMaterno' + b;
		A.innerText = e;
		var B = document.createElement('label');
		B.setAttribute('class', 'etiquetaCaptura');
		B.id = 'labTelefono' + b;
		B.innerText = m;
		var C = document.createElement('label');
		C.setAttribute('class', 'etiquetaCaptura');
		C.id = 'labCorreo' + b;
		C.innerText = h;
		var D = document.createElement('label');
		D.setAttribute('class', 'etiquetaCaptura');
		D.id = 'labCargo' + b;
		D.innerText = i;
		var E = document.createElement('label');
		E.setAttribute('class', 'quitar');
		E.onclick = new Function("editAsistente('" + b + "')");
		E.innerText = '-editar';
		var F = document.createElement('input');
		F.setAttribute('type', 'hidden');
		F.setAttribute('name', 'serviciosDiplomado.asistentes[' + a
				+ '].idAsistente');
		F.setAttribute('value', '0');
		F.id = 'idAsisHid' + b;
		var G = document.createElement('input');
		G.setAttribute('type', 'hidden');
		G.setAttribute('name', 'serviciosDiplomado.asistentes[' + a
				+ '].nombre');
		G.setAttribute('value', c);
		G.id = 'nombreHid' + b;
		var H = document.createElement('input');
		H.setAttribute('type', 'hidden');
		H.setAttribute('name', 'serviciosDiplomado.asistentes[' + a
				+ '].appPaterno');
		H.setAttribute('value', d);
		H.id = 'apPaternoHid' + b;
		var I = document.createElement('input');
		I.setAttribute('type', 'hidden');
		I.setAttribute('name', 'serviciosDiplomado.asistentes[' + a
				+ '].appMaterno');
		I.setAttribute('value', e);
		I.id = 'apMaternoHid' + b;
		var J = document.createElement('input');
		J.setAttribute('type', 'hidden');
		J.setAttribute('name', 'serviciosDiplomado.asistentes[' + a
				+ '].telefono');
		J.setAttribute('value', m);
		J.id = 'telefonoHid' + b;
		var K = document.createElement('input');
		K.setAttribute('type', 'hidden');
		K.setAttribute('name', 'serviciosDiplomado.asistentes[' + a
				+ '].correoElectronico');
		K.setAttribute('value', h);
		K.id = 'correoHid' + b;
		var L = document.createElement('input');
		L.setAttribute('type', 'hidden');
		L
				.setAttribute('name', 'serviciosDiplomado.asistentes[' + a
						+ '].cargo');
		L.setAttribute('value', i);
		L.id = 'cargoHid' + b;
		var M = document.getElementById("cuerpoTablaReg");
		o.appendChild(x);
		o.appendChild(F);
		p.appendChild(y);
		p.appendChild(G);
		q.appendChild(z);
		q.appendChild(H);
		r.appendChild(A);
		r.appendChild(I);
		s.appendChild(B);
		s.appendChild(J);
		t.appendChild(C);
		t.appendChild(K);
		u.appendChild(D);
		u.appendChild(L);
		w.appendChild(E);
		n.appendChild(o);
		n.appendChild(p);
		n.appendChild(q);
		n.appendChild(r);
		n.appendChild(s);
		n.appendChild(t);
		n.appendChild(u);
		n.appendChild(v);
		n.appendChild(w);
		M.appendChild(n);
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
		document.getElementById("tablaReg").style.display = 'block'
	}
}
function editAsistente(a) {
	var b = document.getElementById('telefonoHid' + a).value;
	var c = document.getElementById('ladaTel');
	var d = document.getElementById('numTel');
	var e = document.getElementById('extTel');
	if (b != 'null') {
		var f = b.split(')(');
		c.value = f[1];
		d.value = f[2];
		e.value = f[3].substring(0, (f[3].length - 1))
	}
	document.getElementById('posTabla').value = a;
	document.getElementById('nombre').value = document
			.getElementById('nombreHid' + a).value;
	document.getElementById('apPaterno').value = document
			.getElementById('apPaternoHid' + a).value;
	document.getElementById('apMaterno').value = document
			.getElementById('apMaternoHid' + a).value;
	document.getElementById('correo').value = document
			.getElementById('correoHid' + a).value;
	document.getElementById('cargo').value = document.getElementById('cargoHid'
			+ a).value;
	document.getElementById("contFormA").style.display = 'block';
	document.getElementById("labShowForm").style.display = 'none';
	document.getElementById("labFinEdit").style.display = 'block';
	document.getElementById("labAddAsistente").style.display = 'none';
	document.getElementById("labCancelaAsistente").style.display = 'none';
	document.getElementById("AgregarAsistenteDiv").style.display = 'none';
	document.getElementById("contFormA").style.display = 'block';
	document.getElementById("tablaReg").style.display = 'none'
}
function finEditAsistente() {
	if (document.getElementById('nombre').value.length == 0
			|| /^\s+$/.test(document.getElementById('nombre').value)) {
		document.getElementById("nombre").focus();
		alert("Ingrese el nombre del asistente.")
	} else if (document.getElementById('apPaterno').value.length == 0
			|| /^\s+$/.test(document.getElementById('apPaterno').value)) {
		document.getElementById("apPaterno").focus();
		alert("Ingrese el apellido paterno del asistente.")
	} else if (document.getElementById('apMaterno').value.length == 0
			|| /^\s+$/.test(document.getElementById('apMaterno').value)) {
		document.getElementById("apMaterno").focus();
		alert("Ingrese el apellido materno del asistente.")
	} else if (document.getElementById('ladaTel').value.length < 2
			|| /^\s+$/.test(document.getElementById('ladaTel').value)) {
		document.getElementById("ladaTel").focus();
		alert("El campo de lada debe contener dos o tres dígitos.")
	} else if (document.getElementById('ladaTel').value.length == 2
			&& document.getElementById('numTel').value.length != 8
			|| /^\s+$/.test(document.getElementById('numTel').value)) {
		document.getElementById("numTel").focus();
		alert("El campo Teléfono debe contener ocho dígitos")
	} else if (document.getElementById('ladaTel').value.length == 3
			&& document.getElementById('numTel').value.length != 7
			|| /^\s+$/.test(document.getElementById('numTel').value)) {
		document.getElementById("numTel").focus();
		alert("El campo Teléfono debe contener siete dígitos")
	} else if (!(/[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
			.test(document.getElementById('correo').value))) {
		document.getElementById("correo").focus();
		alert("Ingrese el Correo Electrónico del asistente, con un formato válido.")
	} else if (document.getElementById('cargo').value.length == 0
			|| /^\s+$/.test(document.getElementById('cargo').value)) {
		document.getElementById("cargo").focus();
		alert("Ingrese el cargo del asistente.")
	} else {
		var a = document.getElementById('posTabla').value;
		var b = document.getElementById('ladaTel').value;
		var c = document.getElementById('numTel').value;
		var d = document.getElementById('extTel').value;
		var e = '(52)(' + b + ')(' + c + ')(' + d + ')';
		document.getElementById('nombreHid' + a).value = document
				.getElementById('nombre').value;
		document.getElementById('apPaternoHid' + a).value = document
				.getElementById('apPaterno').value;
		document.getElementById('apMaternoHid' + a).value = document
				.getElementById('apMaterno').value;
		document.getElementById('telefonoHid' + a).value = e;
		document.getElementById('correoHid' + a).value = document
				.getElementById('correo').value;
		document.getElementById('cargoHid' + a).value = document
				.getElementById('cargo').value;
		document.getElementById('labNombre' + a).innerText = document
				.getElementById('nombre').value;
		document.getElementById('labApPaterno' + a).innerText = document
				.getElementById('apPaterno').value;
		document.getElementById('labApMaterno' + a).innerText = document
				.getElementById('apMaterno').value;
		document.getElementById('labTelefono' + a).innerText = e;
		document.getElementById('labCorreo' + a).innerText = document
				.getElementById('correo').value;
		document.getElementById('labCargo' + a).innerText = document
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
		document.getElementById('cargo').value = ''
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
		var a = confirm("¿Desea cancelar el registro del asistente?. Los datos capturados en el formulario no serán almacenados");
		if (a == true) {
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
			document.getElementById('cargo').value = ''
		}
	} else {
		document.getElementById("contFormA").style.display = 'none';
		document.getElementById("labShowForm").style.display = 'block';
		document.getElementById("labFinEdit").style.display = 'none';
		document.getElementById("labAddAsistente").style.display = 'block';
		document.getElementById("labCancelaAsistente").style.display = 'block';
		document.getElementById("contFormA").style.display = 'none';
		document.getElementById("tablaReg").style.display = 'block'
	}
}
function validaChecInasistencia() {
	f = document.getElementById("inasistencias");
	for ( var i = 0; i < f.elements.length; i++) {
		var a = f.elements[i];
		if (a.type == "checkbox") {
			if (a.checked) {
				return true
			}
		}
	}
	alert("No se ha seleccionado ningun Asistente");
	return false
}
function validaAsistentes() {
	alert("Ya hay validación");
	return false
}
function validaDocumento() {
	a = document.getElementById('idRolDocumento');
	b = document.getElementById('idDocumento');
	if (a.value == -1) {
		alert('Seleccione el perfil al que desea subir un documento.');
		a.focus();
		return false
	}
	if (b.value == '') {
		alert('Seleccione el documento que desea subir.');
		return false
	}
	return true
}
function cancelaDocumento() {
	document.frmCancelaDocumento.submit()
}
function cambiarCorreo(a, b) {
	var c = prompt('Estimado usuario, introduzca el nuevo correo electrónico:');
	if (c == null)
		return false;
	if (!(/[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(c))) {
		alert("Ingrese una dirección de correo electrónico válida por favor.");
		return false
	}
	document.getElementById('idCambiaCorreoId').value = a;
	document.getElementById('idCambiaCorreoOriginal').value = b;
	document.getElementById('idCambiaCorreoNuevo').value = c;
	document.frmCambiarCorreo.submit()
}
function filtrarRoles(a) {
	for ( var i = 0; i < document.getElementsByTagName('tr').length; i++) {
		var b = document.getElementsByTagName('tr')[i].id;
		if (b.split('.')[1] > 0)
			document.getElementById(b).style.display = a == null ? 'table-row'
					: a == b.split('.')[0] ? 'table-row' : 'none'
	}
}
function confirmAccess(a, b, c) {
	document.getElementById('idHidIdUsuario').value = a;
	document.getElementById('idHidCorreo').value = b;
	document.getElementById('idHidCredencial').value = c;
	if (confirm('Se generará una nueva sesión para acceder al sistema con el usuario y perfil seleccionados'))
		document.frmAccess.submit()
}