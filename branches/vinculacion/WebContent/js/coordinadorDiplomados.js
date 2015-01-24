var seleccion = false;
function solicitarFactura(){
	document.getElementById("menuSeleccionado").value=1;
	$(idProcesa)[0].style.display = 'block';
	document.frmConfirmacion.submit();
}
function frmAsistenciasDiplomas() {
	document.getElementById("menuSeleccionado2").value=2;
	$(idProcesa)[0].style.display = 'block';
	document.frmAsistencias.submit();
}
function frmAsistenciasInvitacion() {
	document.getElementById("menuSeleccionado2").value=3;
	$(idProcesa)[0].style.display = 'block';
	document.frmAsistencias.submit();
}
function frmAsistenciasGenerar() {
	if( document.getElementById("sesion1").checked ||  document.getElementById("sesion2").checked
			||  document.getElementById("sesion3").checked ||  document.getElementById("sesion4").checked){
		document.getElementById("menuSeleccionado2").value=4;
		$(idProcesa)[0].style.display = 'block';
		document.frmAsistencias.submit();
	} else{
		alert("Seleccione almenos una sesión.");
	}
}
function cancelaSolicitarFactura() {
	var totalChec=0;
	formulario = document.getElementById("frmFactura");
	for(var i=0; i<formulario.elements.length; i++) {
		var elemento = formulario.elements[i];
		if(elemento.type == "checkbox") {
		   totalChec = totalChec+1;
		}
	}
	for(var i=0; i<totalChec; i++) {
		if(document.getElementById("c"+i)!=null){
			document.getElementById("c"+i).disabled=false;
			document.getElementById("text"+i).disabled=false;
		}
	}
	document.getElementById("tlbSolFactura").style.display = 'block';
	document.getElementById("tlbSolFacturaSub").style.display = 'none';
}

function seleccionMenuPyme( val){
	if (val==1){
		document.getElementById("frmConfirmacion").style.display = 'block';
		document.getElementById("frmAsistencias").style.display = 'none';
		document.getElementById("frmFacturacion").style.display = 'none';
	} else if( val == 2){
		document.getElementById("frmConfirmacion").style.display = 'none';
		document.getElementById("frmAsistencias").style.display = 'block';
		document.getElementById("frmFacturacion").style.display = 'none';
	} else{
		document.getElementById("frmConfirmacion").style.display = 'none';
		document.getElementById("frmAsistencias").style.display = 'none';
		document.getElementById("frmFacturacion").style.display = 'block';
	}
}



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
	
	if ( nombre.length == 0 || /^\s+$/.test(nombre.value) ) {
		document.getElementById("nombre").focus();
		alert("Ingrese el nombre del asistente.");
	} else if ( paterno.length == 0 || /^\s+$/.test(paterno) ) {
		document.getElementById("apPaterno").focus();
		alert("Ingrese el apellido paterno del asistente.");
	} else if ( materno.length == 0 || /^\s+$/.test(materno) ) {
		document.getElementById("apMaterno").focus();
		alert("Ingrese el apellido materno del asistente.");
	}else if (lada.length < 2 || /^\s+$/.test(lada)) {
		document.getElementById("ladaTel").focus();
		alert("El campo lada debe contener dos o tres dígitos.");
		return false;
	} else if (lada.length == 2 && numTel.length != 8 || /^\s+$/.test(numTel)) {
		document.getElementById("numTel").focus();
		alert("El campo Teléfono debe contener ocho dígitos.");
		return false;
	}else if(lada.length == 3 && numTel.length != 7 || /^\s+$/.test(numTel)){
		document.getElementById("numTel").focus();
		alert("El campo Teléfono debe contener siete dígitos.");
		return false;
	}else if( !(/[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(correo)) ){
		document.getElementById("correo").focus();
		alert("Ingrese el Correo Electrónico del asistente, con un formato válido.");
	}else if( cargo.length == 0 || /^\s+$/.test(cargo) ){
		document.getElementById("cargo").focus();
		alert("Ingrese el cargo del asistente.");
	}else{
		
		var _ladaTel = document.getElementById('ladaTel').value;
		var _numeroTel = document.getElementById('numTel').value;
		var extTel = document.getElementById('extTel').value;

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

function editAsistente(pos){
	
	var telContacto = document.getElementById('telefonoHid' + pos).value;
	var ladaTel = document.getElementById('ladaTel');
	var numTel = document.getElementById('numTel');
	var extTel = document.getElementById('extTel');

	if (telContacto != 'null') {
		var separaCampos = telContacto.split(')('); 
		
		ladaTel.value = separaCampos[1];
		numTel.value = separaCampos[2];
		extTel.value = separaCampos[3].substring(0, (separaCampos[3].length - 1));
	}

	document.getElementById('posTabla').value = pos;

	document.getElementById('nombre').value = document.getElementById('nombreHid'+pos).value;
	document.getElementById('apPaterno').value = document.getElementById('apPaternoHid'+pos).value;
	document.getElementById('apMaterno').value = document.getElementById('apMaternoHid'+pos).value;
	document.getElementById('correo').value = document.getElementById('correoHid'+pos).value;
	document.getElementById('cargo').value = document.getElementById('cargoHid'+pos).value;
	

	document.getElementById("contFormA").style.display = 'block';
	document.getElementById("labShowForm").style.display = 'none';
	document.getElementById("labFinEdit").style.display = 'block';
	document.getElementById("labAddAsistente").style.display = 'none';
	document.getElementById("labCancelaAsistente").style.display = 'none';
	document.getElementById("AgregarAsistenteDiv").style.display = 'none';
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
	}else if (document.getElementById('ladaTel').value.length < 2 || /^\s+$/.test(document.getElementById('ladaTel').value)) {
		document.getElementById("ladaTel").focus();
		alert("El campo lada debe contener dos o tres dígitos.");
	} else if (document.getElementById('ladaTel').value.length == 2 && document.getElementById('numTel').value.length != 8 || /^\s+$/.test(document.getElementById('numTel').value)) {
		document.getElementById("numTel").focus();
		alert("El campo Teléfono debe contener ocho dígitos.");
	}else if(document.getElementById('ladaTel').value.length == 3 && document.getElementById('numTel').value.length != 7 || /^\s+$/.test(document.getElementById('numTel').value)){
		document.getElementById("numTel").focus();
		alert("El campo Teléfono debe contener siete dígitos.");
	}else if( !(/[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(document.getElementById('correo').value)) ){
		document.getElementById("correo").focus();
		alert("Ingrese el Correo Electrónico del asistente, con un formato válido.");
	}else if( document.getElementById('cargo').value.length == 0 || /^\s+$/.test(document.getElementById('cargo').value) ){
		document.getElementById("cargo").focus();
		alert("Ingrese el cargo del asistente.");
	}else{
		var pos = document.getElementById('posTabla').value;
		
		var _ladaTel = document.getElementById('ladaTel').value;
		var _numeroTel = document.getElementById('numTel').value;
		var extTel = document.getElementById('extTel').value;
		var _miTel = '(52)(' + _ladaTel + ')(' + _numeroTel + ')(' + extTel + ')';
	
		document.getElementById('nombreHid'+pos).value = document.getElementById('nombre').value;
		document.getElementById('apPaternoHid'+pos).value = document.getElementById('apPaterno').value;
		document.getElementById('apMaternoHid'+pos).value = document.getElementById('apMaterno').value;
		document.getElementById('telefonoHid'+pos).value = _miTel;
		document.getElementById('correoHid'+pos).value = document.getElementById('correo').value;
		document.getElementById('cargoHid'+pos).value = document.getElementById('cargo').value;
	
		document.getElementById('labNombre'+pos).innerText = document.getElementById('nombre').value;
		document.getElementById('labApPaterno'+pos).innerText = document.getElementById('apPaterno').value;
		document.getElementById('labApMaterno'+pos).innerText = document.getElementById('apMaterno').value;
		document.getElementById('labTelefono'+pos).innerText = _miTel;
		document.getElementById('labCorreo'+pos).innerText = document.getElementById('correo').value;
		document.getElementById('labCargo'+pos).innerText = document.getElementById('cargo').value;
	
		
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
		
	}
}

function cancelaRegAsistente(){
	
	if ( document.getElementById('nombre').value.length != 0 || document.getElementById('apPaterno').value.length != 0 
		|| document.getElementById('apMaterno').value.length != 0 || document.getElementById('ladaTel').value.length != 0
		|| document.getElementById('numTel').value.length != 0 || document.getElementById('extTel').value.length != 0
		|| document.getElementById('correo').value.length != 0 || document.getElementById('cargo').value.length != 0 
		 ) {
		
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
		}
	}else{
		document.getElementById("contFormA").style.display = 'none';
		document.getElementById("labShowForm").style.display = 'block';
		document.getElementById("labFinEdit").style.display = 'none';
		document.getElementById("labAddAsistente").style.display = 'block';
		document.getElementById("labCancelaAsistente").style.display = 'block';
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
function removeArchivo(val){

	var filaArch = document.getElementById('archivo' + val);
	var tabla = filaArch.parentNode;
	tabla.removeChild(filaArch);
	
	if(val == 1){
		document.getElementById("contNewArchivo").style.display = 'none';
	}

}
function validaAsistentesDip() {

	if(document.getElementById("contNewArchivo").style.display == 'block'){
		
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
			$(idProcesa)[0].style.display = 'block';
			return true;
		}
		
	}else{
		$(idProcesa)[0].style.display = 'block';
		return true;
	}	
}
function validaAsistentes() {

	if(document.getElementById("tablaReg").style.display == 'none'){
		alert('Registre al menos un Participante');
		return false;
	} else if(document.getElementById("contFormA").style.display == 'block'){
		alert('Registre el participante a capturar, mediante el botón correspondiente');
		return false;
	} else{
		$(idProcesa)[0].style.display = 'block';
		return true;
	}	
}

function validaNumero(evt) {
	var key = (document.all) ? evt.keyCode : evt.which;
	return (key <= 13 || (key >= 48 && key <= 57) || key == 46);
}
function finalizar(sesion){
	document.getElementById("numeroSesiones").value = sesion;
	if(validacion(sesion)){
		if(sesion<4){
			if(confirm("Se guardar solo los datos hasta la sesion "+ sesion
					+ "\n\n ¿Desea eliminar los datos de sesiones posteriores?")){
				$(idProcesa)[0].style.display = 'block';
				document.sesionest.submit();
			}
		} else {
			$(idProcesa)[0].style.display = 'block';
			document.sesionest.submit();
		}
	}
}
function siguiente(sesion){
	document.getElementById("numeroSesiones").value = sesion;
	if(validacion(document.getElementById("numeroSesiones").value)>0){
		document.getElementById("numeroSesiones").value = sesion + 1;
		document.getElementById("sesiont"+sesion).style.display='none';
		document.getElementById("sesiont"+(sesion+1)).style.display='block';
	}
}
function anterior(){
	sesionAct = document.getElementById("numeroSesiones").value;
	document.getElementById("sesiont"+sesionAct).style.display='none';
	document.getElementById("sesiont"+(sesionAct-1)).style.display='block';
	sesionAct = document.getElementById("numeroSesiones").value = sesionAct-1;
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
function validacion(sesion){
	idExpositor = document.getElementById("idExpositor"+sesion).value;
	idSala = document.getElementById("idSala"+sesion).value;
	ingreso = document.getElementById("ingreso"+sesion).value;
	hora = document.getElementById("hora"+sesion).value;
	minuto = document.getElementById("minuto"+sesion).value;
	horaFin = document.getElementById("horaFin"+sesion).value;
	minutoFin = document.getElementById("minutoFin"+sesion).value;
	idInstuctor = document.getElementById("idInstuctor"+sesion).value;
	calle = document.getElementById("calle"+sesion).value;
	numExt = document.getElementById("numExt"+sesion).value;
	numInt = document.getElementById("numInt"+sesion).value;
	piso = document.getElementById("piso"+sesion).value;
	colonia = document.getElementById("colonia"+sesion).value;
	delegacion = document.getElementById("delegacion"+sesion).value;
	estado = document.getElementById("estado"+sesion).value;
	codigoPostal = document.getElementById("codigoPostal"+sesion).value;
	idInfo = document.getElementById("idInfo"+sesion).value;
	if (idExpositor == null || idExpositor.length == 0
			|| /^\s+$/.test(idExpositor)) {
		document.getElementById("idExpositor"+sesion).focus();
		alert("Ingrese el expositor de la sesión");
		return false;
	} else if (idSala == null || idSala.length == 0
			|| /^\s+$/.test(idSala)) {
		document.getElementById("idSala"+sesion).focus();
		alert("Ingrese la sala de la sesión");
		return false;
	} else if (!isDate(ingreso)) {
		document.getElementById("ingreso"+sesion).focus();
		alert("Ingrese la fecha de la sesión");
		return false;
	} else if (hora == null || hora < 0) {
		document.getElementById("hora"+sesion).focus();
		alert("Ingrese la hora de la sesión");
		return false;
	} else if (minuto == null || minuto < 0) {
		document.getElementById("minuto"+sesion).focus();
		alert("Ingrese el minuto de la sesión");
		return false;
	} else if (horaFin == null || horaFin < 0) {
		document.getElementById("horaFin"+sesion).focus();
		alert("Ingrese la hora final de la sesión.");
		return false;
	} else if (minutoFin == null || minutoFin < 0) {
		document.getElementById("minutoFin"+sesion).focus();
		alert("Ingrese el minuto final de la sesión.");
		return false;
	} else if (idInstuctor == null || idInstuctor.length == 0
			|| /^\s+$/.test(idInstuctor)) {
		document.getElementById("idInstuctor"+sesion).focus();
		alert("Ingrese el instructor de la sesión");
		return false;
	} else if (calle == null || calle.length == 0
				|| /^\s+$/.test(calle)) {
			document.getElementById("calle"+sesion).focus();
			alert("Ingrese la calle");
			return false;
	} else if (numExt == null || numExt.length == 0
			|| /^\s+$/.test(numExt)) {
		document.getElementById("numExt"+sesion).focus();
		alert("Ingrese el Número exterior");
		return false;
	} else if (colonia == null || colonia.length == 0
			|| /^\s+$/.test(colonia)) {
		document.getElementById("colonia"+sesion).focus();
		alert("Ingrese la colonia");
		return false;
	} else if (delegacion == null || delegacion.length == 0
			|| /^\s+$/.test(delegacion)) {
		document.getElementById("delegacion"+sesion).focus();
		alert("Ingrese la delegación");
		return false;
	} else if (estado == 0) {
		document.getElementById("estado"+sesion).focus();
		alert("Seleccione un Estado");
		return false;
	} else if (codigoPostal == null || codigoPostal.length == 0
			|| /^\s+$/.test(codigoPostal)) {
		document.getElementById("codigoPostal"+sesion).focus();
		alert("Ingrese el Código Postal");
		return false;
	} else if (idInfo == null || idInfo.length == 0
			|| /^\s+$/.test(idInfo)) {
		document.getElementById("idInfo"+sesion).focus();
		alert("Ingrese alguna información importante");
		return false;
	} else{
		return true;
	}
}


	function addParticipantes(){
		document.getElementById("participantes").style.display="none";
		document.getElementById("addParticipantes").style.display="block";
	}
	function validaLetra(e) {
		tecla = (document.all) ? e.keyCode : e.which;
		if (tecla==0) return true;
		if (tecla==8) return true;
	    patron =/[A-ZÑña-z\s]/;
	    te = String.fromCharCode(tecla);
	    return patron.test(te); 
	}
	function MenuDiplomadoAnio(){
		document.getElementById("year").value = document.getElementById("menuAnios").value;
		$(idProcesa)[0].style.display = 'block';
		document.frmAnios.submit();
	}
	function validaChecInasistencia(){
		formulario = document.getElementById("inasistencias");
		for(var i=0; i<formulario.elements.length; i++) {
			var elemento = formulario.elements[i];
			if(elemento.type == "checkbox") {
			   if(elemento.checked) {
					$(idProcesa)[0].style.display = 'block';
					return true;
			   }
			 }
		}
		alert("No se ha seleccionado ningun Asistente");
		return false;
		
	}
	function todas() {
		var size = 0;
		try {
			size = document.frmSelecPymInscibirFinalizar.checkbox.length;
		} catch (e) {
		}
		size = size == undefined ? 1 : size;
		if (size == 1)
			document.frmSelecPymInscibirFinalizar.checkbox.checked = seleccion ? false : true;
		else if (size > 0)
			for ( var i = 0; i < size; i++)
				document.frmSelecPymInscibirFinalizar.checkbox[i].checked = seleccion ? false : true;
		seleccion = !seleccion;
		return false;
	}
	
	function saveInscribirPymes() {
		var size = 0;
		try {
			size = document.frmSelecPymInscibirFinalizar.checkbox.length == undefined ? 1
					: document.frmSelecPymInscibirFinalizar.checkbox.length;
		} catch (e) {
			alert('Seleccione por lo menos una PyME.');
			return false;
		}
		var pymes = '';
		var validacion = false;

		if (size == 1 && document.frmSelecPymInscibirFinalizar.checkbox.checked) {
			pymes = document.frmAsignacion.checkbox.id.substring(8);
			validacion = true;
		} else if (size > 1)
			for ( var i = 0; i < size; i++)
				if (document.frmSelecPymInscibirFinalizar.checkbox[i].checked) {
					pymes = pymes
							+ document.frmSelecPymInscibirFinalizar.checkbox[i].id.substring(8)
							+ ',';
					validacion = true;
				}
		if (!validacion){
			alert('Seleccione por lo menos una PyME.');
			return false;
		} else {
			if (pymes.length > 0 && pymes.substring(pymes.length - 1, pymes.length) == ',')
				pymes = pymes.substring(0, pymes.length - 1);
			document.getElementById("idHidIdPyMEs").value = pymes;
			$(idProcesa)[0].style.display = 'block';
			return true;
		}
	}