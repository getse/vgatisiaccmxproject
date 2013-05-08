var peticion = false;
try{    
    peticion = new XMLHttpRequest();
}catch( e ){
    try{
        peticion = new ActiveXObject( "Msxml2.XMLHTTP" );
    } catch ( E ){
        try{
            peticion = new ActiveXObject( "Microsoft.XMLHTTP" );
        } catch ( failed ){
            peticion = false;
        }
    }
}
if ( !peticion ){alert( "ERROR AL INICIALIZAR!" );}

function showCombo2( cat1 ){
	var combo2 = document.getElementById( 'catProd2' );
	$( "#catProd2" ).html( '<option selected="selected" value="0">Cargando...</option>' );
	combo2.style.display = 'block';
	combo2.disabled = true;
	
    var url = '${pageContext.request.contextPath}/vinculacion/comprador/compradorRequerimientoAdd.do?cat1='+cat1;
    peticion.open( "GET", url, true );
    peticion.onreadystatechange = function(){
        if ( peticion.readyState == 4 && peticion.status == 200 ){
        	var cont = peticion.responseText;
        	var divideCont = cont.split('\<');
        	var x = 1;
        	for( i = 1; i < divideCont.length; i++ ){
        		var sel = divideCont[i];
        		if( sel.substring(0, 6) == 'option' ){
        			var inicioCadena = sel.indexOf('>') + 1;
    				var finCadena = sel.length;
    				var _text = sel.substring( inicioCadena, finCadena );
    				var _valOpt = sel.split(' ');
        			for( j = 1; j < _valOpt.length; j++ ){
        				var _val = _valOpt[j];
        				if( _val.substring(0, 5) == 'value' ){
        					var _valorValue = _val.split('\"');
        					for( var k = 1; k < _valorValue.length; k++ ){
        						var _valNumero = _valorValue[k];
        						if( _valNumero.length == 3 && !isNaN(_valNumero) ){
        							combo2.options[0] = new Option( '--Seleccione una opción--', 0 );
        							combo2.options[x] = new Option( _text, _valNumero );
            						x++;
        						}
        					}
        				}
        			}
        		}
        	}
    		document.getElementById( "catProd2" ).disabled = false;			
        }
    }; 
   peticion.send( null );
}

function showCombo3( cat2 ){
	var combo3 = document.getElementById( 'catProd3' );
	$( "#catProd3" ).html( '<option selected="selected" value="0">Cargando...</option>' );
	combo3.style.display = 'block';
	combo3.disabled = true;
    var url = '${pageContext.request.contextPath}/vinculacion/comprador/compradorRequerimientoAdd.do?cat2='+cat2;
    peticion.open( "GET", url, true );
    peticion.onreadystatechange = function(){
        if ( peticion.readyState == 4 && peticion.status == 200 ){
        	var cont = peticion.responseText;
        	var divideCont = cont.split('\<');
        	var x = 1;
        	for( i = 1; i < divideCont.length; i++ ){
        		var sel = divideCont[i];
        		if( sel.substring(0, 6) == 'option' ){
        			var inicioCadena = sel.indexOf('>') + 1;
    				var finCadena = sel.length;
    				var _text = sel.substring( inicioCadena, finCadena );
    				var _valOpt = sel.split(' ');
        			for( j = 1; j < _valOpt.length; j++ ){
        				var _val = _valOpt[j];
        				if( _val.substring(0, 5) == 'value' ){
        					var _valorValue = _val.split('\"');
        					for( var k = 1; k < _valorValue.length; k++ ){
        						var _valNumero = _valorValue[k];
        						if( _valNumero.length == 5 && !isNaN(_valNumero) ){
        							combo3.options[0] = new Option( '--Seleccione una opción--', 0 );
        							combo3.options[x] = new Option( _text, _valNumero );
            						x++;
        						}
        					}
        				}
        			}
        		}
        	}
			document.getElementById( "catProd3" ).disabled = false;
        }
    }; 
   peticion.send( null );  
}


function fillDescripcionScian(id) {
	var combo = document.getElementById('catProd3');

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

function agregaTelefono() {
	var _tel = document.getElementById('idTelefono').value.length;
	var _telefonos = 0;
	if (_tel == 0) {
		alert('Ingrese un número telefónico para agregarlo.');
		document.getElementById('idTelefono').style.background = '#FEF5C9';
	} else {
		for ( var i = 1; i <= 10; i++) {
			if (document.getElementById('idDivTel' + i).style.display == 'block')
				_telefonos++;
		}
		if (_telefonos < 10) {
			var _pos = _telefonos + 1;
			document.getElementById('idTelHid' + _pos).value = document
					.getElementById('idTelefono').value;
			document.getElementById('labTel' + _pos).innerText = document
					.getElementById('idTelefono').value;
			document.getElementById('idDivTel' + _pos).style.display = 'block';
		}
	}
	document.getElementById('idTelefono').value = null;
	document.getElementById('idTelefono').focus();
}

function quitarTelefono(pos) {
	var _last = pos;
	if (pos == '10'
			|| document.getElementById('idDivTel' + (pos + 1)).style.display == 'none') {
		document.getElementById('labTel' + pos).innerText = null;
		document.getElementById('idTelHid' + pos).value = null;
		document.getElementById('idDivTel' + pos).style.display = 'none';
	} else {
		for ( var i = pos; i <= 10; i++) {
			if (document.getElementById('idDivTel' + i).style.display == 'block')
				_last++;
			if (document.getElementById('labTel' + (i + 1)) != null)
				_lab = document.getElementById('labTel' + (i + 1)).innerText;
			if (document.getElementById('idTelHid' + (i + 1)) != null)
				_hid = document.getElementById('idTelHid' + (i + 1)).value;
			document.getElementById('labTel' + i).innerText = _lab;
			document.getElementById('idTelHid' + i).value = _hid;
		}
		document.getElementById('idDivTel' + (_last - 1)).style.display = 'none';
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

function modificarDatos() {
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
		document.getElementById("idDivDetalleVariasFechas").style.display = 'none';
	}
	if (check == '2') {
		document.getElementById('indefinido').checked = false;
		document.getElementById('suministrocontinuo').checked = false;
		if (document.getElementById('variasfechas').checked == false) {
			document.getElementById("idDivDetalleVariasFechas").style.display = 'none';
		} else {
			document.getElementById("idDivDetalleVariasFechas").style.display = 'block';
		}
	}
	if (check == '3') {
		document.getElementById('indefinido').checked = false;
		document.getElementById('variasfechas').checked = false;
		document.getElementById("idDivDetalleVariasFechas").style.display = 'none';
	}
}

function limpiaCheckSuministro() {
	if (document.getElementById('ingreso').value != '') {
		document.getElementById('indefinido').checked = false;
		document.getElementById('variasfechas').checked = false;
		document.getElementById('suministrocontinuo').checked = false;
		document.getElementById("idDivDetalleVariasFechas").style.display = 'none';
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
}

function blurAyuda(id) {
	document.getElementById(id).style.display = 'none';
	document.getElementById(id + '2').style.display = 'block';
}

function otroArchivo() {
	var sizeF = 1;

	for ( var i = 1; i < 11; i++) {
		_block = document.getElementById('idDivArchivo' + i + 'Block').style.display;
		_none = document.getElementById('idDivArchivo' + i + 'None').style.display;
		if (_block == 'block' || _none == 'block') {
			sizeF++;
		}
	}
	document.getElementById('idDivArchivo' + sizeF + 'Block').style.display = 'block';
}

function supArchivo(pos){
	document.getElementById('idCampoArchivo' + pos ).value='';
	document.getElementById('idDivArchivo' + pos + 'Block').style.display = 'none';
}

// validacion de campos
function validacion(sec) {
	document.getElementById('idFecSum').value = document
			.getElementById('ingreso').value;
	document.getElementById('idFecExp').value = document
			.getElementById('ingreso2').value;
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
		} else if ((valorFechaS == null || valorFechaS == 0 || /^\s+$/
				.test(valorFechaS))
				&& (document.getElementById('indefinido').checked == false
						&& document.getElementById('variasfechas').checked == false && document
						.getElementById('suministrocontinuo').checked == false)) {
			document.getElementById('ingreso').focus();
			alert("Ingrese la fecha de suministro o seleccione una opción");
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
			for ( var i = 1; i <= 10; i++) {
				if (document.getElementById('idDivArc' + i).style.display == 'none') {
					// limpiando archivos inecesarios
					document.getElementById('filArc' + (i - 1)).name = null;
					document.getElementById('idArcHid' + (i - 1)).value = null;
				}
			}
			return true;
		}
	}
}
function validaDatosTractora(sec) {
	valorEmpresa = document.getElementById("idEmpresa").value;
	valorNombre = document.getElementById("idNombre").value;
	valorPaterno = document.getElementById("idAppPaterno").value;
	valorMaterno = document.getElementById("idAppMaterno").value;
	valorCorreo = document.getElementById("idCorreoElectronico").value;
	valorCompara = document.getElementById("idComparaCorreo").value;

	valorPuesto = document.getElementById("idPuesto").value;
	valorTelefono = document.getElementById("idTelefono").value;
	// valorAddTelefono = document.getElementById("idAddTelefono").value;

	valorCalle = document.getElementById("idCalle").value;
	valorNumExt = document.getElementById("idNumExt").value;
	valorColonia = document.getElementById("idColonia").value;
	valorDelegacion = document.getElementById("idDelegacion").value;
	// Select
	valorEstado = document.getElementById("idEstado").selectedIndex;
	// valorEstado = document.getElementById("idEst").value;
	valorCodigoPostal = document.getElementById("idCodigoPostal").value;

	if (sec == '1') {
		if (valorEmpresa == null || valorEmpresa.length == 0
				|| /^\s+$/.test(valorEmpresa)) {
			document.getElementById("idEmpresa").focus();
			alert("Ingrese en Nombre de la Empresa");
			return false;
		} else if (valorNombre == null || valorNombre.length == 0
				|| /^\s+$/.test(valorNombre)) {
			document.getElementById("idNombre").focus();
			alert("Ingrese el Nombre(s) requerido");
			return false;
		} else if (valorPaterno == null || valorPaterno.length == 0
				|| /^\s+$/.test(valorPaterno)) {
			document.getElementById("idAppPaterno").focus();
			alert("Ingrese Apellido Paterno");
			return false;
		} else if (valorMaterno == null || valorMaterno.length == 0
				|| /^\s+$/.test(valorMaterno)) {
			document.getElementById("idAppMaterno").focus();
			alert("Ingrese Apellido Materno");
			return false;
		} else if (!(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
				.test(valorCorreo))) {
			document.getElementById("idCorreoElectronico").focus();
			alert("Ingrese una dirección de correo electrónico válida");
			return false;
		} else if (valorCorreo != valorCompara) {
			document.getElementById("idComparaCorreo").focus();
			alert("El correo electrónico no coincide");
			return false;
		} else if (valorPuesto == null || valorPuesto.length == 0
				|| /^\s+$/.test(valorPuesto)) {
			document.getElementById("idPuesto").focus();
			alert("Ingrese su puesto");
			return false;
		} else if (document.getElementById('idDivTel1').style.display == 'none'
				&& (valorTelefono == null || valorTelefono.length == 0 || /^\s+$/
						.test(valorTelefono))) {
			document.getElementById("idTelefono").focus();
			alert("Ingrese el teléfono");
			return false;
		} else {
			document.getElementById('sec1').style.display = 'none';
			document.getElementById('sec2').style.display = 'block';
			if (document.getElementById('idDivTel1').style.display == 'none') {
				document.getElementById('idTelHid1').value = document
						.getElementById('idTelefono').value;
			}
			return true;
		}
	} else if (sec == '2') {
		if (valorCalle == null || valorCalle.length == 0
				|| /^\s+$/.test(valorCalle)) {
			document.getElementById("idCalle").focus();
			alert("Ingrese la calle");
			return false;
		} else if (valorNumExt == null || valorNumExt.length == 0
				|| /^\s+$/.test(valorNumExt)) {
			document.getElementById("idNumExt").focus();
			alert("Ingrese el número exterior");
			return false;
		} else if (valorColonia == null || valorColonia.length == 0
				|| /^\s+$/.test(valorColonia)) {
			document.getElementById("idColonia").focus();
			alert("Ingrese la colonia");
			return false;
		} else if (valorDelegacion == null || valorDelegacion.length == 0
				|| /^\s+$/.test(valorDelegacion)) {
			document.getElementById("idDelegacion").focus();
			alert("Ingrese la delegación");
			return false;
		} else if (valorEstado == " " || valorEstado == 0
				|| valorEstado == null) {
			document.getElementById("idEstado").focus();
			alert("Seleccione un Estado");
			return false;
		} else if (valorCodigoPostal == null || valorCodigoPostal.length == 0
				|| /^\s+$/.test(valorCodigoPostal)) {
			document.getElementById("idCodigoPostal").focus();
			alert("Ingrese el Código Postal");
			return false;
		} else {
			return true;
		}
	}

}
