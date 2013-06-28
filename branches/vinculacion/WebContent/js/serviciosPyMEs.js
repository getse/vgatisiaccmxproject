/* NUEVA IMPLEMENTACION SCRIPTS DIP */

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

function validaCheck1(pos){

	if (document.getElementById('checkUbicaDip1').checked) {
		document.getElementById('checkUbicaDip2').checked = false;
		document.getElementById('checkUbicaDip3').checked = false;
	}
	
	var _fechaActual = new Date();
	var _fechaDip = document.getElementById('fechaHid' + pos).value;
	var _splitDip = _fechaDip.split('/'); 
	
	if(_splitDip[0] <= _fechaActual.getDate()){
		if(_splitDip[1] <= (_fechaActual.getMonth() + 1)){
			if(_splitDip[2] <= _fechaActual.getFullYear()){
				document.getElementById('checkUbicaDip1').checked = false;
				alert( 'Lo sentimos, no puede inscribirse a diplomados pasados, seleccione otra fecha disponible.' );
				document.getElementById('checkUbicaDip1').disabled = true;
			}
		}
	}else if(_splitDip[1] <= (_fechaActual.getMonth() + 1)){
		if(_splitDip[2] <= _fechaActual.getFullYear()){
			document.getElementById('checkUbicaDip1').checked = false;
			alert( 'Lo sentimos, no puede inscribirse a diplomados pasados, seleccione otra fecha disponible.' );
			document.getElementById('checkUbicaDip1').disabled = true;
		}
	}else if(_splitDip[2] <= _fechaActual.getFullYear()){
		document.getElementById('checkUbicaDip1').checked = false;
		alert( 'Lo sentimos, no puede inscribirse a diplomados pasados, seleccione otra fecha disponible.' );
		document.getElementById('checkUbicaDip1').disabled = true;
		
	}
	
	if( document.getElementById('checkUbicaDip1').checked ){
		document.getElementById('frmAsistente').style.display = 'block';
		document.getElementById('labIdDiplomado').value = document.getElementById('idDiplomadoHid' + pos).value;
		document.getElementById('asistIdDiplomado').value = document.getElementById('idDiplomadoHid' + pos).value;
	}
}

function validaCheck2(pos){	
	if (document.getElementById('checkUbicaDip2').checked) {
		document.getElementById('checkUbicaDip1').checked = false;
		document.getElementById('checkUbicaDip3').checked = false;
	}
	
	var _fechaActual = new Date();
	var _fechaDip = document.getElementById('fechaHid' + pos).value;
	var _splitDip = _fechaDip.split('/'); 
	
	if(_splitDip[0] <= _fechaActual.getDate()){
		if(_splitDip[1] <= (_fechaActual.getMonth() + 1)){
			if(_splitDip[2] <= _fechaActual.getFullYear()){
				document.getElementById('checkUbicaDip2').checked = false;
				alert( 'Lo sentimos, no puede inscribirse a diplomados pasados, seleccione otra fecha disponible.' );
				document.getElementById('checkUbicaDip2').disabled = true;
			}
		}
	}else if(_splitDip[1] <= (_fechaActual.getMonth() + 1)){
		if(_splitDip[2] <= _fechaActual.getFullYear()){
			document.getElementById('checkUbicaDip2').checked = false;
			alert( 'Lo sentimos, no puede inscribirse a diplomados pasados, seleccione otra fecha disponible.' );
			document.getElementById('checkUbicaDip2').disabled = true;
		}
	}else if(_splitDip[2] <= _fechaActual.getFullYear()){
		document.getElementById('checkUbicaDip2').checked = false;
		alert( 'Lo sentimos, no puede inscribirse a diplomados pasados, seleccione otra fecha disponible.' );
		document.getElementById('checkUbicaDip2').disabled = true;
		
	}
	
	if( document.getElementById('checkUbicaDip2').checked ){
		document.getElementById('frmAsistente').style.display = 'block';
		document.getElementById('labIdDiplomado').value = document.getElementById('idDiplomadoHid' + pos).value;
		document.getElementById('asistIdDiplomado').value = document.getElementById('idDiplomadoHid' + pos).value;
	}
}

function validaCheck3(pos){	
	if (document.getElementById('checkUbicaDip3').checked) {
		document.getElementById('checkUbicaDip1').checked = false;
		document.getElementById('checkUbicaDip2').checked = false;
	}
	
	var _fechaActual = new Date();
	var _fechaDip = document.getElementById('fechaHid' + pos).value;
	var _splitDip = _fechaDip.split('/'); 
	
	if(_splitDip[0] <= _fechaActual.getDate()){
		if(_splitDip[1] <= (_fechaActual.getMonth() + 1)){
			if(_splitDip[2] <= _fechaActual.getFullYear()){
				document.getElementById('checkUbicaDip3').checked = false;
				alert( 'Lo sentimos, no puede inscribirse a diplomados pasados, seleccione otra fecha disponible.' );
				document.getElementById('checkUbicaDip3').disabled = true;
			}
		}
	}else if(_splitDip[1] <= (_fechaActual.getMonth() + 1)){
		if(_splitDip[2] <= _fechaActual.getFullYear()){
			document.getElementById('checkUbicaDip3').checked = false;
			alert( 'Lo sentimos, no puede inscribirse a diplomados pasados, seleccione otra fecha disponible.' );
			document.getElementById('checkUbicaDip3').disabled = true;
		}
	}else if(_splitDip[2] <= _fechaActual.getFullYear()){
		document.getElementById('checkUbicaDip3').checked = false;
		alert( 'Lo sentimos, no puede inscribirse a diplomados pasados, seleccione otra fecha disponible.' );
		document.getElementById('checkUbicaDip3').disabled = true;
		
	}
	
	if( document.getElementById('checkUbicaDip3').checked ){
		document.getElementById('frmAsistente').style.display = 'block';
		document.getElementById('labIdDiplomado').value = document.getElementById('idDiplomadoHid' + pos).value;
		document.getElementById('asistIdDiplomado').value = document.getElementById('idDiplomadoHid' + pos).value;
	}
}

var secuencia = 2;
function addAsistente(){

	var tr = document.createElement('tr');
	tr.id = 'asistente' + secuencia;

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

	var txNom = document.createElement('input');
	txNom.setAttribute('type', 'text');
	txNom.setAttribute('size', '20');
	txNom.setAttribute('name', 'nombresAsistentes');
	txNom.setAttribute('value', '');
	txNom.setAttribute('maxlength', '60');
	txNom.setAttribute('onkeypress', 'return validaLetra(event)');
	txNom.id = 'nombre' + secuencia;

	var txPat = document.createElement('input');
	txPat.setAttribute('type', 'text');
	txPat.setAttribute('size', '20');
	txPat.setAttribute('name', 'appPatAsistentes');
	txPat.setAttribute('value', '');
	txPat.setAttribute('maxlength', '60');
	txPat.setAttribute('onkeypress', 'return validaLetra(event)');
	txPat.id = 'appPat' + secuencia;

	var txMat = document.createElement('input');
	txMat.setAttribute('type', 'text');
	txMat.setAttribute('size', '20');
	txMat.setAttribute('name', 'appMatAsistentes');
	txMat.setAttribute('value', '');
	txMat.setAttribute('maxlength', '60');
	txMat.setAttribute('onkeypress', 'return validaLetra(event)');
	txMat.id = 'appMat' + secuencia;
	
	var txTel = document.createElement('input');
	txTel.setAttribute('type', 'text');
	txTel.setAttribute('size', '20');
	txTel.setAttribute('name', 'telAsistentes');
	txTel.setAttribute('value', '');
	txTel.setAttribute('maxlength', '60');
	txTel.id = 'tel' + secuencia;
	
	var txCorreo = document.createElement('input');
	txCorreo.setAttribute('type', 'text');
	txCorreo.setAttribute('size', '20');
	txCorreo.setAttribute('name', 'correoAsistentes');
	txCorreo.setAttribute('value', '');
	txCorreo.setAttribute('maxlength', '60');
	txCorreo.id = 'correo' + secuencia;
	
	secuencia++;

	asistente = document.getElementById("addAsistente");
	td1.appendChild(txNom);
	td2.appendChild(txPat);
	td3.appendChild(txMat);
	td4.appendChild(txTel);
	td5.appendChild(txCorreo);
	tr.appendChild(td1);
	tr.appendChild(td2);
	tr.appendChild(td3);
	tr.appendChild(td4);
	tr.appendChild(td5);
	asistente.appendChild(tr);
	
	document.getElementById('labDelAsistente').style.display = 'block';

}

function deleteAsistente(){
  var _delAsistente = document.getElementById("addAsistente");
  if (_delAsistente.childNodes.length > 2) {
	  _delAsistente.removeChild(_delAsistente.childNodes[_delAsistente.childNodes.length-1]);
  }
  
  if(_delAsistente.childNodes.length == 3){
	  document.getElementById('labDelAsistente').style.display = 'none';
  }
}

function validaAsistentesDip() {
	
	if (document.getElementById('checkUbicaDip3').checked == false 
			&& document.getElementById('checkUbicaDip1').checked == false 
			&& document.getElementById('checkUbicaDip2').checked == false) {
		alert("Seleccione una Sede.");
		return false;
	}
	
	for ( var i = 1; i < secuencia; i++) {
		if ( document.getElementById('nombre' + i).value.length == 0 || /^\s+$/.test(document.getElementById('nombre' + i).value) ) {
			document.getElementById("nombre" + i).focus();
			alert("Ingrese el nombre del asistente.");
			return false;
		} else if ( document.getElementById('appPat' + i).value.length == 0 || /^\s+$/.test(document.getElementById('appPat' + i).value) ) {
			document.getElementById("appPat" + i).focus();
			alert("Ingrese el apellido materno del asistente.");
			return false;
		} else if ( document.getElementById('appMat' + i).value.length == 0 || /^\s+$/.test(document.getElementById('appMat' + i).value) ) {
			document.getElementById("appMat" + i).focus();
			alert("Ingrese el apellido materno del asistente.");
			return false;
		}else if( document.getElementById('tel' + i).value.length == 0 || /^\s+$/.test(document.getElementById('tel' + i).value) ){
			document.getElementById("tel" + i).focus();
			alert("Ingrese el Teléfono del asistente.");
			return false;
		}else if( !(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(document.getElementById('correo' + i).value)) ){
			document.getElementById("correo" + i).focus();
			alert("Ingrese el Correo Electrónico del asistente, con un formato válido.");
			return false;
		}
	}
	return true;
}


/* NUEVA IMPLEMENTACION SCRIPTS CONSULT */

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