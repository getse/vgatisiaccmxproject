function validaNumero(evt) {
	var key = (document.all) ? evt.keyCode : evt.which;
	return (key <= 13 || (key >= 48 && key <= 57) || key == 46);
}

function validaLetra(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla==0) return true;
	if (tecla==8) return true;
    patron =/[A-ZÑña-z\s]/;
    te = String.fromCharCode(tecla);
    return patron.test(te); 
}

function selectDiplomados() {
	document.getElementById("diplomado").style.display = 'block';
	document.getElementById("consultoria").style.display = 'none';
}

function showFormAsistente(){
	document.getElementById("contFormA").style.display = 'block';
	document.getElementById("labCancelaAsistente").style.display = 'block';
	document.getElementById("labShowForm").style.display = 'none';
}

function addAsistente(){
	
	var secuencia = document.getElementById("cuerpoTablaReg").rows.length;
	var idTotal = secuencia + 1;

	var nombre = document.getElementById('nombre').value;
	var paterno = document.getElementById('apPaterno').value;
	var materno = document.getElementById('apMaterno').value;
	var lada = document.getElementById('ladaTel').value;
	var numTel = document.getElementById('numTel').value;
	var correo = document.getElementById('correo').value;
	var cargo = document.getElementById('cargo').value;
	var pago = document.getElementById('pago').checked;
	
	if ( nombre.length == 0 || /^\s+$/.test(nombre.value) ) {
		document.getElementById("nombre").focus();
		alert("Ingrese el nombre del asistente.");
	} else if ( paterno.length == 0 || /^\s+$/.test(paterno) ) {
		document.getElementById("apPaterno").focus();
		alert("Ingrese el apellido paterno del asistente.");
	} else if ( materno.length == 0 || /^\s+$/.test(materno) ) {
		document.getElementById("apMaterno").focus();
		alert("Ingrese el apellido materno del asistente.");
	}else if (lada.length != 2 || /^\s+$/.test(lada)) {
		document.getElementById("ladaTel").focus();
		alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
		return false;
	} else if (numTel.length != 8 || /^\s+$/.test(numTel)) {
		document.getElementById("numTel").focus();
		alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
		return false;
	}else if( !(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(correo)) ){
		document.getElementById("correo").focus();
		alert("Ingrese el Correo Electrónico del asistente, con un formato válido.");
	}else if( cargo.length == 0 || /^\s+$/.test(cargo) ){
		document.getElementById("cargo").focus();
		alert("Ingrese el cargo del asistente.");
	}else{
		
		var _ladaTel = document.getElementById('ladaTel').value;
		var _numeroTel = document.getElementById('numTel').value;
		var extTel;

		if (document.getElementById('extTel').value.length == 1) {
			extTel = 0 + '' + 0 + '' + 0 + '' + document.getElementById('extTel').value;
		} else if (document.getElementById('extTel').value.length == 2) {
			extTel = 0 + '' + 0 + '' + document.getElementById('extTel').value;
		} else if (document.getElementById('extTel').value.length == 3) {
			extTel = 0 + '' + document.getElementById('extTel').value;
		} else if (document.getElementById('extTel').value.length == 4) {
			extTel = document.getElementById('extTel').value;
		} else {
			extTel = 0 + '' + 0 + '' + 0 + '' + 0;
		}

		var _miTel = '(52)(' + _ladaTel + ')(' + _numeroTel + ')(' + extTel + ')';
	
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
		labCont.innerText = secuencia+1;
	
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
	
		var labPago = document.createElement('label');
		labPago.setAttribute('class', 'etiquetaCaptura');
		labPago.id = 'labPago' + idTotal;
		labPago.innerText = pago == true ? 'Pagado' : 'Pendiente';
	
		var labEdita = document.createElement('label');
		labEdita.setAttribute('class', 'quitar');
		labEdita.onclick = new Function("editAsistente('" + idTotal + "')");
		labEdita.innerText = '-editar';
	
		var idHid = document.createElement('input');
		idHid.setAttribute('type', 'hidden');
		idHid.setAttribute('name', 'serviciosDiplomado.asistentes[' + secuencia + '].idAsistente');
		idHid.setAttribute('value', '0');
		idHid.id = 'idAsisHid' + idTotal;
	
		var nomHid = document.createElement('input');
		nomHid.setAttribute('type', 'hidden');
		nomHid.setAttribute('name', 'serviciosDiplomado.asistentes[' + secuencia + '].nombre');
		nomHid.setAttribute('value', nombre);
		nomHid.id = 'nombreHid' + idTotal;
	
		var patHid = document.createElement('input');
		patHid.setAttribute('type', 'hidden');
		patHid.setAttribute('name', 'serviciosDiplomado.asistentes[' + secuencia + '].appPaterno');
		patHid.setAttribute('value', paterno);
		patHid.id = 'apPaternoHid' + idTotal;
	
		var matHid = document.createElement('input');
		matHid.setAttribute('type', 'hidden');
		matHid.setAttribute('name', 'serviciosDiplomado.asistentes[' + secuencia + '].appMaterno');
		matHid.setAttribute('value', materno);
		matHid.id = 'apMaternoHid' + idTotal;
	
		var telHid = document.createElement('input');
		telHid.setAttribute('type', 'hidden');
		telHid.setAttribute('name', 'serviciosDiplomado.asistentes[' + secuencia + '].telefono');
		telHid.setAttribute('value', _miTel);
		telHid.id = 'telefonoHid' + idTotal;
	
		var correoHid = document.createElement('input');
		correoHid.setAttribute('type', 'hidden');
		correoHid.setAttribute('name', 'serviciosDiplomado.asistentes[' + secuencia + '].correoElectronico');
		correoHid.setAttribute('value', correo);
		correoHid.id = 'correoHid' + idTotal;
	
		var cargoHid = document.createElement('input');
		cargoHid.setAttribute('type', 'hidden');
		cargoHid.setAttribute('name', 'serviciosDiplomado.asistentes[' + secuencia + '].cargo');
		cargoHid.setAttribute('value', cargo);
		cargoHid.id = 'cargoHid' + idTotal;
	
		var pagoHid = document.createElement('input');
		pagoHid.setAttribute('type', 'hidden');
		pagoHid.setAttribute('name', 'serviciosDiplomado.asistentes[' + secuencia + '].pago');
		pagoHid.setAttribute('value', pago);
		pagoHid.id = 'pagoHid' + idTotal;
	
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
		td8.appendChild(labPago);
		td8.appendChild(pagoHid);
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
		document.getElementById('pago').checked = false;
		
		document.getElementById("tablaReg").style.display = 'block';
	}

}

function editAsistente(pos){
	
	var telContacto = document.getElementById('telefonoHid' + pos).value;
	var ladaTel = document.getElementById('ladaTel');
	var numTel = document.getElementById('numTel');
	var extTel = document.getElementById('extTel');

	if (telContacto != 'null') {
		ladaTel.value = telContacto.substring(5, 7);
		numTel.value = telContacto.substring(9, 17);
		extTel.value = telContacto.substring(19, 23);
	}

	document.getElementById('posTabla').value = pos;

	document.getElementById('nombre').value = document.getElementById('nombreHid'+pos).value;
	document.getElementById('apPaterno').value = document.getElementById('apPaternoHid'+pos).value;
	document.getElementById('apMaterno').value = document.getElementById('apMaternoHid'+pos).value;
	document.getElementById('telefono').value = document.getElementById('telefonoHid'+pos).value;
	document.getElementById('correo').value = document.getElementById('correoHid'+pos).value;
	document.getElementById('cargo').value = document.getElementById('cargoHid'+pos).value;
	var p = document.getElementById('pagoHid'+pos).value;
	if( p == 'true' ){
		document.getElementById('pago').checked = true;
	}else{
		document.getElementById('pago').checked = false;
	}

	document.getElementById("contFormA").style.display = 'block';
	document.getElementById("labShowForm").style.display = 'none';
	document.getElementById("labFinEdit").style.display = 'block';
	document.getElementById("labAddAsistente").style.display = 'none';
	document.getElementById("labCancelaAsistente").style.display = 'none';
}

function finEditAsistente(){
	
	if ( document.getElementById('nombre').value.length == 0 || /^\s+$/.test(document.getElementById('nombre').value) ) {
		document.getElementById("nombre").focus();
		alert("Ingrese el nombre del asistente.");
	} else if ( document.getElementById('apPaterno').value.length == 0 || /^\s+$/.test(document.getElementById('apPaterno').value) ) {
		document.getElementById("apPaterno").focus();
		alert("Ingrese el apellido paterno del asistente.");
	} else if ( document.getElementById('apMaterno').value.length == 0 || /^\s+$/.test(document.getElementById('apMaterno').value) ) {
		document.getElementById("apMaterno").focus();
		alert("Ingrese el apellido materno del asistente.");
	}else if( document.getElementById('telefono').value.length == 0 || /^\s+$/.test(document.getElementById('telefono').value) ){
		document.getElementById("telefono").focus();
		alert("Ingrese el teléfono del asistente.");
	}else if (document.getElementById('ladaTel').value.length != 2 || /^\s+$/.test(document.getElementById('ladaTel').value)) {
		document.getElementById("ladaTel").focus();
		alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
	} else if (document.getElementById('numTel').value.length != 8 || /^\s+$/.test(document.getElementById('numTel').value)) {
		document.getElementById("numTel").focus();
		alert("Ingrese un teléfono valido, ejemplo: (52)(55)(55555555)(5555)");
	}else if( !(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(document.getElementById('correo').value)) ){
		document.getElementById("correo").focus();
		alert("Ingrese el Correo Electrónico del asistente, con un formato válido.");
	}else if( document.getElementById('cargo').value.length == 0 || /^\s+$/.test(document.getElementById('cargo').value) ){
		document.getElementById("cargo").focus();
		alert("Ingrese el cargo del asistente.");
	}else{
		var pos = document.getElementById('posTabla').value;
		
		var _ladaTel = document.getElementById('ladaTel').value;
		var _numeroTel = document.getElementById('numTel').value;
		var extTel;

		if (document.getElementById('extTel').value.length == 1) {
			extTel = 0 + '' + 0 + '' + 0 + '' + document.getElementById('extTel').value;
		} else if (document.getElementById('extTel').value.length == 2) {
			extTel = 0 + '' + 0 + '' + document.getElementById('extTel').value;
		} else if (document.getElementById('extTel').value.length == 3) {
			extTel = 0 + '' + document.getElementById('extTel').value;
		} else if (document.getElementById('extTel').value.length == 4) {
			extTel = document.getElementById('extTel').value;
		} else {
			extTel = 0 + '' + 0 + '' + 0 + '' + 0;
		}

		var _miTel = '(52)(' + _ladaTel + ')(' + _numeroTel + ')(' + extTel + ')';
	
		document.getElementById('nombreHid'+pos).value = document.getElementById('nombre').value;
		document.getElementById('apPaternoHid'+pos).value = document.getElementById('apPaterno').value;
		document.getElementById('apMaternoHid'+pos).value = document.getElementById('apMaterno').value;
		document.getElementById('telefonoHid'+pos).value = _miTel;
		document.getElementById('correoHid'+pos).value = document.getElementById('correo').value;
		document.getElementById('cargoHid'+pos).value = document.getElementById('cargo').value;
		document.getElementById('pagoHid'+pos).value = document.getElementById('pago').checked;
	
		document.getElementById('labNombre'+pos).innerText = document.getElementById('nombre').value;
		document.getElementById('labApPaterno'+pos).innerText = document.getElementById('apPaterno').value;
		document.getElementById('labApMaterno'+pos).innerText = document.getElementById('apMaterno').value;
		document.getElementById('labTelefono'+pos).innerText = _miTel;
		document.getElementById('labCorreo'+pos).innerText = document.getElementById('correo').value;
		document.getElementById('labCargo'+pos).innerText = document.getElementById('cargo').value;
	
		var c = document.getElementById('pago').checked;
		document.getElementById('labPago' + pos).innerText = (c == true ? 'Pagado' : 'Pendiente');
	
		document.getElementById("contFormA").style.display = 'none';
		document.getElementById("labShowForm").style.display = 'block';
		document.getElementById("labFinEdit").style.display = 'none';
		document.getElementById("labAddAsistente").style.display = 'block';
		document.getElementById("labCancelaAsistente").style.display = 'block';
	
		document.getElementById('nombre').value = '';
		document.getElementById('apPaterno').value = '';
		document.getElementById('apMaterno').value = '';
		document.getElementById('ladaTel').value = '';
		document.getElementById('numTel').value = '';
		document.getElementById('extTel').value = '';
		document.getElementById('correo').value = '';
		document.getElementById('cargo').value = '';
		document.getElementById('pago').checked = false;
	}
}

function cancelaRegAsistente(){
	
	if ( document.getElementById('nombre').value.length != 0 || document.getElementById('apPaterno').value.length != 0 
		|| document.getElementById('apMaterno').value.length != 0 || document.getElementById('ladaTel').value.length != 0
		|| document.getElementById('numTel').value.length != 0 || document.getElementById('extTel').value.length != 0
		|| document.getElementById('correo').value.length != 0 || document.getElementById('cargo').value.length != 0 
		|| document.getElementById('pago').checked == true ) {
		
		var del = confirm("¿Desea cancelar el registro del asistente?. Los datos capturados en el formulario no serán almacenados");
		if(del == true){
			
			document.getElementById("contFormA").style.display = 'none';
			document.getElementById("labShowForm").style.display = 'block';
			document.getElementById("labFinEdit").style.display = 'none';
			document.getElementById("labAddAsistente").style.display = 'block';
			
			document.getElementById('nombre').value = '';
			document.getElementById('apPaterno').value = '';
			document.getElementById('apMaterno').value = '';
			document.getElementById('ladaTel').value = '';
			document.getElementById('numTel').value = '';
			document.getElementById('extTel').value = '';
			document.getElementById('correo').value = '';
			document.getElementById('cargo').value = '';
			document.getElementById('pago').checked = false;
		}
	}else{
		document.getElementById("contFormA").style.display = 'none';
		document.getElementById("labShowForm").style.display = 'block';
		document.getElementById("labFinEdit").style.display = 'none';
		document.getElementById("labAddAsistente").style.display = 'block';
		document.getElementById("labCancelaAsistente").style.display = 'block';
	}
}

	
function addArchivo(){

	var id = document.getElementById("contArchivos").rows.length;
	var sec = id - 1;
	
	if(id > 1){
		if(document.getElementById("pago" + sec).value == ''){
			alert('Seleccione un archivo');
		}else if(document.getElementById("descripcion" + sec).value.length == 0){
			document.getElementById("descripcion" + sec).focus();
			alert('Ingrese la descripción del archivo seleccionado');
		}else{
			var tra = document.createElement('tr');
			tra.id = 'archivo' + id;

			var tda = document.createElement('td');

			var file = document.createElement('input');
			file.setAttribute('type', 'file');
			file.setAttribute('name', 'serviciosDiplomado.archivos.upload');
			file.id = 'pago' + id;

			var desc = document.createElement('input');
			desc.setAttribute('type', 'text');
			desc.setAttribute('name', 'serviciosDiplomado.archivos.descripcionArchivos');
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
	}else{
		var tra = document.createElement('tr');
		tra.id = 'archivo' + id;

		var tda = document.createElement('td');

		var file = document.createElement('input');
		file.setAttribute('type', 'file');
		file.setAttribute('name', 'serviciosDiplomado.archivos.upload');
		file.id = 'pago' + id;

		var desc = document.createElement('input');
		desc.setAttribute('type', 'text');
		desc.setAttribute('name', 'serviciosDiplomado.archivos.descripcionArchivos');
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

var ids = null;
function supArchivoTabla(id, pos){	

	var sup = document.getElementById("contArchivosPago").rows.length;
	var f = document.getElementById('archPago' + pos);
	var t = f.parentNode;
	t.removeChild(f);

	ids = ids + id + ',';
	var i = ids.substring(0, ids.length - 1);
	document.getElementById('eliminarArchivos').value = i;
	
	document.getElementById('contAyudaDelete').style.display = 'block';
	
	if( sup == 3 ){
		document.getElementById("contArchivosPago").style.display = 'none';
		document.getElementById('contAyudaDelete').style.display = 'none';
	}
}

function removeArchivo(val){

	var filaArch = document.getElementById('archivo' + val);
	var tabla = filaArch.parentNode;
	tabla.removeChild(filaArch);
	
	if(val == 1){
		document.getElementById("contNewArchivo").style.display = 'none';
	}

}

function validaAsistentesDip() {

	if(document.getElementById("tablaReg").style.display == 'none'){
		alert('Registre al menos un Participante');
		return false;
	}else if(document.getElementById("contFormA").style.display == 'block'){
		alert('Registre el participante a capturar, mediante el botón correspondiente');
		return false;
	}else if(document.getElementById("contNewArchivo").style.display == 'block'){
		
		var contFile = document.getElementById("contArchivos").rows.length;
		var contador = contFile - 1;
		
		if(document.getElementById("pago" + contador).value == ''){
			alert('Seleccione un archivo');
			return false;
		}else if(document.getElementById("descripcion" + contador).value.length == 0 || /^\s+$/.test(document.getElementById("descripcion" + contador).value)){
			document.getElementById("descripcion" + contador).focus();
			alert('Ingrese la descripción del archivo seleccionado');
			return false;
		}else{
			return true;
		}
		
	}else{
		return true;
	}	
}



function selectConsultorias() {
	document.getElementById("diplomado").style.display = 'none';
	document.getElementById("consultoria").style.display = 'block';
}

function veinteCheck() {
	if (document.getElementById("check20").checked) {
		document.getElementById("check40").checked = false;
		document.getElementById("check60").checked = false;
		document.getElementById("check80").checked = false;
		document.getElementById('showArchPago').style.display = 'none';
	}
}

function cuarentaCheck() {
	if (document.getElementById("check40").checked) {
		document.getElementById("check20").checked = false;
		document.getElementById("check60").checked = false;
		document.getElementById("check80").checked = false;
		document.getElementById('showArchPago').style.display = 'block';
	}else{
		document.getElementById('showArchPago').style.display = 'none';
	}
}

function sesentaCheck() {
	if (document.getElementById("check60").checked) {
		document.getElementById("check20").checked = false;
		document.getElementById("check40").checked = false;
		document.getElementById("check80").checked = false;
		document.getElementById('showArchPago').style.display = 'block';
	}else{
		document.getElementById('showArchPago').style.display = 'none';
	}
}

function ochentaCheck() {
	if (document.getElementById("check80").checked) {
		document.getElementById("check20").checked = false;
		document.getElementById("check40").checked = false;
		document.getElementById("check60").checked = false;
		document.getElementById('showArchPago').style.display = 'block';
	}else{
		document.getElementById('showArchPago').style.display = 'none';
	}
}

function consultoria() {

	if (document.getElementById("check20").checked || document.getElementById("check40").checked 
			|| document.getElementById("check60").checked || document.getElementById("check80").checked) {
		document.frmConsultoria.submit();
		return true;
	} else {
		alert("Selecione un tipo de consultoria.");
		return false;
	}
}