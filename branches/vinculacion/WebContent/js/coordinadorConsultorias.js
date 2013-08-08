var seleccion = false;
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

function showCombo(cat, next, cadena) {
	var combo = document.getElementById('catProd' + next);
	if (next == 6) {
		var _combo = document.getElementById('catProd5');
		for ( var i = 0; i < _combo.length; i++) {
			_value = _combo.options[i].value;
			_text = _combo.options[i].text;
			if (_value == cat) {
				document.getElementById('idInputCatScian').value = _text;
				document.getElementById('idCveSci').value = _value;
				document.getElementById('idInputCatScian').rows = _text.length > 85 ? 2 : 1;
			}
		}
	} else {
		$("#catProd" + next).html(
		'<option selected="selected" value="0">Cargando...</option>');
		combo.style.display = 'block';
		combo.disabled = true;

		var size = (next == 2 ? 3 : (next == 3 ? 4 : (next == 4 ? 5
				: (next == 5 ? 6 : 1))));
		var url = '${pageContext.request.contextPath}/vinculacion/consultorias/coordinacion/'+cadena+'?cat'
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


function addPyME(){
	document.frmAddPyME.submit();
}

function showAsigna(){
	document.frmAssignPyME.submit();
}

function showReasigna(){
	document.frmReAssignPyME.submit();
}

function showDiplomas(){
	document.frmDiplomasPyME.submit();
}

function showBusqueda(){
	document.frmBusquedaPyME.submit();
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
			document.frmAsignacion.checkbox[i].checked = seleccion ? false : true;
	seleccion = !seleccion;
	return false;
}

function muestraAsignar() {
	if (document.frmAsignacion.checkbox == undefined)
		return;
	var size = document.frmAsignacion.checkbox.length == undefined ? 1 : document.frmAsignacion.checkbox.length;
	var lista = false;
	if (size == 1 && document.frmAsignacion.checkbox.checked)
		lista = true;
	else if (size > 1)
		for ( var i = 0; i < size; i++)
			if (document.frmAsignacion.checkbox[i].checked)
				lista = true;
	if (lista)
		document.getElementById('contConsultor').style.display = 'block';
	else
		alert('Seleccione por lo menos una PyME.');
}

function muestraReAsignar() {
	if (document.frmReasignaPyME.checkbox == undefined)
		return;
	var size = document.frmReasignaPyME.checkbox.length == undefined ? 1 : document.frmReasignaPyME.checkbox.length;
	var lista = false;
	if (size == 1 && document.frmReasignaPyME.checkbox.checked)
		lista = true;
	else if (size > 1)
		for ( var i = 0; i < size; i++)
			if (document.frmReasignaPyME.checkbox[i].checked)
				lista = true;
	if (lista)
		document.getElementById('contConsultor').style.display = 'block';
	else
		alert('Seleccione por lo menos una PyME.');
}

function asignaConsultora() {
	var size = document.frmAsignacion.checkbox.length == undefined ? 1
			: document.frmAsignacion.checkbox.length;
	var pymes = '';
	var validacion = false;
	var consultora = false;

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
		if (pymes.length > 0 && pymes.substring(pymes.length - 1, pymes.length) == ',')
			pymes = pymes.substring(0, pymes.length - 1);
		document.frmAsignacion.idHidIdPyMEs.value = pymes;
		size = document.getElementById('idConsultoraSeleccionada').options.length == undefined ? 0 : document.getElementById('idConsultoraSeleccionada').options.length;
		for ( var j = 0; j < size; j++)
			if (document.getElementById('idConsultoraSeleccionada').options[j].selected && document.getElementById('idConsultoraSeleccionada').options[j].value != '-1') {
				document.frmAsignacion.idHidIdConsultora.value = document.getElementById('idConsultoraSeleccionada').options[j].value;
				consultora = true;
			}

		if (!consultora)
			alert('Seleccione la Consultora a la que serán asignadas las PyMEs.');
		else
			document.frmAsignacion.submit();
	}
}

function reAsignaConsultora() {
	var size = document.frmReasignaPyME.checkbox.length == undefined ? 1
			: document.frmReasignaPyME.checkbox.length;
	var pymes = '';
	var validacion = false;
	var consultora = false;

	if (size == 1 && document.frmReasignaPyME.checkbox.checked) {
		pymes = document.frmReasignaPyME.checkbox.id.substring(8);
		validacion = true;
	} else if (size > 1)
		for ( var i = 0; i < size; i++)
			if (document.frmReasignaPyME.checkbox[i].checked) {
				pymes = pymes
						+ document.frmReasignaPyME.checkbox[i].id.substring(8)
						+ ',';
				validacion = true;
			}

	if (!validacion)
		alert('Seleccione por lo menos una PyME.');
	else {
		if (pymes.length > 0 && pymes.substring(pymes.length - 1, pymes.length) == ',')
			pymes = pymes.substring(0, pymes.length - 1);
		document.frmReasignaPyME.idHidIdPyMEs.value = pymes;
		size = document.getElementById('idConsultoraSeleccionada').options.length == undefined ? 0 : document.getElementById('idConsultoraSeleccionada').options.length;
		for ( var j = 0; j < size; j++)
			if (document.getElementById('idConsultoraSeleccionada').options[j].selected && document.getElementById('idConsultoraSeleccionada').options[j].value != '-1') {
				document.frmReasignaPyME.idHidIdConsultora.value = document.getElementById('idConsultoraSeleccionada').options[j].value;
				consultora = true;
			}

		if (!consultora)
			alert('Seleccione la Consultora a la que serán asignadas las PyMEs.');
		else
			document.frmReasignaPyME.submit();
	}
}

function reasignaTodas() {
	var size = 0;
	try {
		size = document.frmReasignaPyME.checkbox.length;
	} catch (e) {
	}
	size = size == undefined ? 1 : size;
	if (size == 1)
		document.frmReasignaPyME.checkbox.checked = seleccion ? false : true;
	else if (size > 0)
		for ( var i = 0; i < size; i++)
			document.frmReasignaPyME.checkbox[i].checked = seleccion ? false : true;
	seleccion = !seleccion;
	return false;
}

function todasDiplomas() {
	var size = 0;
	try {
		size = document.frmDiplomas.checkbox.length;
	} catch (e) {
	}
	size = size == undefined ? 1 : size;
	if (size == 1)
		document.frmDiplomas.checkbox.checked = seleccion ? false : true;
	else if (size > 0)
		for ( var i = 0; i < size; i++)
			document.frmDiplomas.checkbox[i].checked = seleccion ? false : true;
	seleccion = !seleccion;
	return false;
}

function todasFacturas() {
	var size = 0;
	try {
		size = document.frmFactura.checkbox.length;
	} catch (e) {
	}
	size = size == undefined ? 1 : size;
	if (size == 1)
		document.frmFactura.checkbox.checked = seleccion ? false : true;
	else if (size > 0)
		for ( var i = 0; i < size; i++)
			document.frmFactura.checkbox[i].checked = seleccion ? false : true;
	seleccion = !seleccion;
	return false;
}

function todasDetalleFacturas() {
	var size = 0;
	try {
		size = document.frmDetalleFactura.checkbox.length;
	} catch (e) {
	}
	size = size == undefined ? 1 : size;
	if (size == 1)
		document.frmDetalleFactura.checkbox.checked = seleccion ? false : true;
	else if (size > 0)
		for ( var i = 0; i < size; i++)
			document.frmDetalleFactura.checkbox[i].checked = seleccion ? false : true;
	seleccion = !seleccion;
	return false;
}

function todasInfoPagos() {
	var size = 0;
	try {
		size = document.frmInfoFactura.checkbox.length;
	} catch (e) {
	}
	size = size == undefined ? 1 : size;
	if (size == 1)
		document.frmInfoFactura.checkbox.checked = seleccion ? false : true;
	else if (size > 0)
		for ( var i = 0; i < size; i++)
			document.frmInfoFactura.checkbox[i].checked = seleccion ? false : true;
	seleccion = !seleccion;
	return false;
}

function validacionAgregar(){
	var empresa = document.getElementById('nombreComercial').value;
	var correo = document.getElementById('correoElectronico').value;
	var comparaCorreo = document.getElementById('comparaCorreo').value;
	var nombre = document.getElementById('nombreContacto').value;
	var paterno = document.getElementById('apPaterno').value;
	var materno = document.getElementById('apMaterno').value;
	var tractora = document.getElementById('optTrac').selectedIndex;
	
	document.getElementById('correoContacto1').value = correo;
	
	if( empresa == null || empresa.length == 0 || /^\s+$/.test(empresa) ) {
		document.getElementById('nombreComercial').focus();
		alert("Ingrese el Nombre Comercial");
		return false;
	}else if( !(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(correo)) ) {
		document.getElementById('correoElectronico').focus();
		alert("Ingrese una dirección de correo electrónico válida");
		return false;
	}else if (correo != comparaCorreo){
		document.getElementById('comparaCorreo').focus();
		alert("El correo electrónico no coincide");
		return false;
	}else if( nombre == null || nombre.length == 0 || /^\s+$/.test(nombre) ) {
		document.getElementById('nombreContacto').focus();
		alert("Ingrese el Nombre(s) requerido");
		return false;
	}else if( paterno == null || paterno.length == 0 || /^\s+$/.test(paterno) ) {
		document.getElementById('apPaterno').focus();
		alert("Ingrese Apellido Paterno");
		return false;
	}else if( materno == null || materno.length == 0 || /^\s+$/.test(materno) ) {
		document.getElementById('apMaterno').focus();
		alert("Ingrese Apellido Materno");  
		return false;
	}else if( tractora == 0 ){
		document.getElementById('optTrac').focus();
		alert("Seleccione una Tractora para asignar a la PyME");  
		return false;
	}
	return true;
}

function validacionDiplomas(){
	var size = document.frmDiplomas.checkbox.length == undefined ? 1 : document.frmDiplomas.checkbox.length;
	var pymes = '';
	var validacion = false;

	if (size == 1 && document.frmDiplomas.checkbox.checked) {
		pymes = document.frmDiplomas.checkbox.id.substring(8);
		validacion = true;
	} else if (size > 1){
		for ( var i = 0; i < size; i++){
			if (document.frmDiplomas.checkbox[i].checked) {
				pymes = pymes + document.frmDiplomas.checkbox[i].id.substring(8) + ',';
				validacion = true;
			}
		}
	}

	if (!validacion){
		alert('Seleccione por lo menos una PyME.');
		return false;
	} else {
		if (pymes.length > 0 && pymes.substring(pymes.length - 1, pymes.length) == ','){
			pymes = pymes.substring(0, pymes.length - 1);
			document.frmDiplomas.idHidIdPyMEs.value = pymes;
			return true;
		}
	}
}

function validacionFacturas(){
	var size = document.frmFactura.checkbox.length == undefined ? 1 : document.frmFactura.checkbox.length;
	var facturas = '';
	var montos = '';
	var validacion = false;

	if (size == 1 && document.frmFactura.checkbox.checked) {
		facturas = document.frmFactura.checkbox.id.substring(8);
		var divide = facturas.split('&');
		facturas = divide[0];
		montos = divide[1];
		validacion = true;
	} else if (size > 1){
		for ( var i = 0; i < size; i++){
			if (document.frmFactura.checkbox[i].checked) {
				var guion = document.frmFactura.checkbox[i].id.indexOf('&');				
				facturas = facturas + document.frmFactura.checkbox[i].id.substring(8,guion) + ',';
				montos = montos + document.frmFactura.checkbox[i].id.substring(guion+1) + ',';
				validacion = true;
			}
		}
	}

	if (!validacion){
		alert('Seleccione por lo menos una PyME.');
		return false;
	} else {
		if( facturas.length > 0 && facturas.substring(facturas.length - 1, facturas.length) == ','){
			facturas = facturas.substring(0, facturas.length - 1);
			montos = montos.substring(0, montos.length - 1);
			
			document.frmFactura.idHidIdFacturas.value = facturas;
			document.frmFactura.idHidMontoTotal.value = montos;

			return true;
			
		}else{
			document.frmFactura.idHidIdFacturas.value = facturas;
			document.frmFactura.idHidMontoTotal.value = montos;
			return true;
		}
	}
}

function validacionDetalleFacturas(){
	var size = document.frmDetalleFactura.checkbox.length == undefined ? 1 : document.frmDetalleFactura.checkbox.length;
	var pymes = '';
	var validacion = false;

	if (size == 1 && document.frmDetalleFactura.checkbox.checked) {
		pymes = document.frmDetalleFactura.checkbox.id.substring(8);
		validacion = true;
	} else if (size > 1){
		for ( var i = 0; i < size; i++){
			if (document.frmDetalleFactura.checkbox[i].checked) {
				pymes = pymes + document.frmDetalleFactura.checkbox[i].id.substring(8) + ',';
				validacion = true;
			}
		}
	}

	if (!validacion){
		alert('Seleccione por lo menos una PyME.');
		return false;
	} else {
		if(pymes.length = 1){
			document.frmDetalleFactura.idHidIdPagos.value = pymes;
			return true;
		}else if (pymes.length > 0 && pymes.substring(pymes.length - 1, pymes.length) == ','){
			pymes = pymes.substring(0, pymes.length - 1);
			document.frmDetalleFactura.idHidIdPagos.value = pymes;
			return true;
		}
	}
}

function validacionBusqueda(){
	var busqueda = document.getElementById('campoBusqueda').value;
	
	if( busqueda == null || busqueda.length == 0 || /^\s+$/.test(busqueda) ) {
		document.getElementById('campoBusqueda').focus();
		alert("Escriba la palabra que describe el producto que busca.");
		return false;
	}
	
	return true;
}

function validaLetra(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla==0) return true;
	if (tecla==8) return true;
    patron =/[A-ZÑña-z\s]/;
    te = String.fromCharCode(tecla);
    return patron.test(te); 
}

function regDiplomas(){
	var reg = confirm("¿Desea registrar los diplomas de las PyMEs seleccionadas?. Una vez realizada esta acción ya no podrá imprimir el Diploma.");
	if(reg == true){
		return true;
	}else{
		return false;
	}
}

function liberaPago(){
	var reg = confirm("¿Desea liberar los pagos de las PyMEs seleccionadas?. Una vez realizada esta acción se generará la factura correspondiente.");
	if(reg == true){
		return true;
	}else{
		return false;
	}
}

function delFactura(){
	var reg = confirm("¿Desea desvincular las PyMEs seleccionadas?. Una vez realizada esta acción las PyMEs no se incluirán en esta factura.");
	if(reg == true){
		return true;
	}else{
		return false;
	}
}

function showRegPagos(){
	document.getElementById('solicitaPago').style.display = 'none';
	document.getElementById('infoPagos').style.display = 'block';
}

function showFecha() {
	if (document.frmInfoFactura.checkbox == undefined)
		return;
	var size = document.frmInfoFactura.checkbox.length == undefined ? 1 : document.frmInfoFactura.checkbox.length;
	var lista = false;
	if (size == 1 && document.frmInfoFactura.checkbox.checked)
		lista = true;
	else if (size > 1)
		for ( var i = 0; i < size; i++)
			if (document.frmInfoFactura.checkbox[i].checked)
				lista = true;
	if (lista)
		document.getElementById('contFecha').style.display = 'block';
	else
		alert('Seleccione por lo menos una Factura.');
}

function saveFechaPago() {
	var size = document.frmInfoFactura.checkbox.length == undefined ? 1 : document.frmInfoFactura.checkbox.length;
	var facturas = '';
	var validacion = false;
	var consultora = false;

	if (size == 1 && document.frmInfoFactura.checkbox.checked) {
		facturas = document.frmInfoFactura.checkbox.id.substring(8);
		validacion = true;
	} else if (size > 1)
		for ( var i = 0; i < size; i++)
			if (document.frmInfoFactura.checkbox[i].checked) {
				facturas = facturas
						+ document.frmInfoFactura.checkbox[i].id.substring(8)
						+ ',';
				validacion = true;
			}

	if (!validacion){
		alert('Seleccione por lo menos una Factura.');
	}else {
		if(facturas.length = 1){
			document.frmInfoFactura.idHidIdFacturas.value = facturas;
		}else if (facturas.length > 0 && facturas.substring(facturas.length - 1, facturas.length) == ','){
			facturas = facturas.substring(0, facturas.length - 1);
			document.frmInfoFactura.idHidIdFacturas.value = facturas;
		}

		if (document.getElementById('ingreso').value.length == 0){
			alert('Seleccione la fecha que se asociará al pago de facturas.');
		}else{
			document.frmInfoFactura.submit();
		}
	}
}

function calendario() {
	Calendar.setup({
		inputField : "ingreso", // id del campo de texto
		ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el // campo de texto
		button : "lanzador" // el id del botón que lanzará el calendario
	});
}

function fechaDiploma(){
	var f = document.getElementById('fechaSalida').value;
	var separa = f.split('-');
	var mes = separa[1];
	switch (mes) {
	case '01':
		document.getElementById('mesDiploma').innerText = 'Enero'; 
		break;
	case '02':
		document.getElementById('mesDiploma').innerText = 'Febrero'; 
		break;
	case '03':
		document.getElementById('mesDiploma').innerText = 'Marzo'; 
		break;
	case '04':
		document.getElementById('mesDiploma').innerText = 'Abril'; 
		break;
	case '05':
		document.getElementById('mesDiploma').innerText = 'Mayo'; 
		break;
	case '06':
		document.getElementById('mesDiploma').innerText = 'Junio'; 
		break;
	case '07':
		document.getElementById('mesDiploma').innerText = 'Julio'; 
		break;
	case '08':
		document.getElementById('mesDiploma').innerText = 'Agosto'; 
		break;
	case '09':
		document.getElementById('mesDiploma').innerText = 'Septiembre'; 
		break;
	case '10':
		document.getElementById('mesDiploma').innerText = 'Octubre'; 
		break;
	case '11':
		document.getElementById('mesDiploma').innerText = 'Noviembre'; 
		break;
	case '12':
		document.getElementById('mesDiploma').innerText = 'Diciembre'; 
		break;
	}
	
	document.getElementById('anioDiploma').innerText = separa[0];
}