function validacion(sec) {
	
	valorPerJuridica = document.getElementById("personalidadJuridica").selectedIndex;
	valorCorreo = document.getElementById("correoElectronico").value;
	valorCompara = document.getElementById("comparaCorreo").value;

	valorMsjVenta = document.getElementById("mensajeVenta").value;
	valorCalle = document.getElementById("calle").value;
	valorNumExt = document.getElementById("numExt").value;
	valorColonia = document.getElementById("colonia").value;
	valorDelegacion = document.getElementById("delegacion").value;
	valorEstado = document.getElementById("estado").selectedIndex;
	valorCodigoPostal = document.getElementById("codigoPostal").value;
	valorProdPrincipales = document.getElementById("prodPrincipales").value;
	valorSectorUno = document.getElementById("sector1");
	valorSectorDos = document.getElementById("sector2");
	valorSectorTres = document.getElementById("sector3");

	valorTipoContacto = document.getElementById("tipoContacto").selectedIndex;
	valorNombre = document.getElementById("nombreContacto").value;
	valorPaterno = document.getElementById("appPat").value;
	valorMaterno = document.getElementById("appMat").value;
	valorCorreoContacto = document.getElementById("correoElectronicoContacto").value;
	valorComparaContacto = document.getElementById("comparaCorreoContacto").value;
	valorTelefonoContacto = document.getElementById("telContacto").value;
	
	valorTipoContacto2 = document.getElementById("tipoContacto2").selectedIndex;
	valorNombre2 = document.getElementById("nombreContacto2").value;
	valorPaterno2 = document.getElementById("appPat2").value;
	valorMaterno2 = document.getElementById("appMat2").value;
	valorCorreoContacto2 = document.getElementById("correoElectronicoContacto2").value;
	valorComparaContacto2 = document.getElementById("comparaCorreoContacto2").value;
	valorTelefonoContacto2 = document.getElementById("telContacto2").value;
	
	valorCliente1 = document.getElementById("cliente1").value;
	valorProdCliente1 = document.getElementById("prodCliente1").value;
	valorAniosProveCliente1 = document.getElementById("aniosProveCliente1").value;
	valorMesesProveCliente1 = document.getElementById("mesesProveCliente1").value;
	
	valorCliente2 = document.getElementById("cliente2").value;
	valorProdCliente2 = document.getElementById("prodCliente2").value;
	valorAniosProveCliente2 = document.getElementById("aniosProveCliente2").value;
	valorMesesProveCliente2 = document.getElementById("mesesProveCliente2").value;
	
	valorCliente3 = document.getElementById("cliente3").value;
	valorProdCliente3 = document.getElementById("prodCliente3").value;
	valorAniosProveCliente3 = document.getElementById("aniosProveCliente3").value;
	valorMesesProveCliente3 = document.getElementById("mesesProveCliente3").value;
	
	valorCliente4 = document.getElementById("cliente4").value;
	valorProdCliente4 = document.getElementById("prodCliente4").value;
	valorAniosProveCliente4 = document.getElementById("aniosProveCliente4").value;
	valorMesesProveCliente4 = document.getElementById("mesesProveCliente4").value;
	
	valorCliente5 = document.getElementById("cliente5").value;
	valorProdCliente5 = document.getElementById("prodCliente5").value;
	valorAniosProveCliente5 = document.getElementById("aniosProveCliente5").value;
	valorMesesProveCliente5 = document.getElementById("mesesProveCliente5").value;



	if (sec == '1') {

		if( valorPerJuridica == " " || valorPerJuridica == 0 || valorPerJuridica == null || valorPerJuridica == "Seleccione el tipo de persona") {
			document.getElementById("personalidadJuridica").focus();
			alert("Seleccione el tipo de Personalidad Juridica");
			return false;
		}else if( !(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(valorCorreo)) ) {
			document.getElementById("correoElectronico").focus();
			alert("Ingrese una dirección de correo electrónico válida");
			return false;
		}else if (valorCorreo != valorCompara){
			document.getElementById("comparaCorreo").focus();
			alert("El correo electrónico no coincide");
			return false;
		}else{
			document.getElementById('sec1').style.display = 'none';
			document.getElementById('sec2').style.display = 'block';
			return true;
		}
	}else if( sec == '2'){
		if( valorMsjVenta == null || valorMsjVenta.length == 0 || /^\s+$/.test(valorMsjVenta) ) {
			document.getElementById("mensajeVenta").focus();
			alert("Ingrese Mensaje de ventas");  
			return false;
		}else if( valorCalle == null || valorCalle.length == 0 || /^\s+$/.test(valorCalle) ) {
			document.getElementById("calle").focus();
			alert("Ingrese la calle");  
			return false;
		}else if( valorNumExt == null || valorNumExt.length == 0 || /^\s+$/.test(valorNumExt) ) {
			document.getElementById("numExt").focus();
			alert("Ingrese el Número exterior");  
			return false;
		}else if( valorColonia == null || valorColonia.length == 0 || /^\s+$/.test(valorColonia) ) {
			document.getElementById("colonia").focus();
			alert("Ingrese la colonia");
			return false;
		}else if( valorDelegacion == null || valorDelegacion.length == 0 || /^\s+$/.test(valorDelegacion) ) {
			document.getElementById("delegacion").focus();
			alert("Ingrese la delegación");
			return false;
		}else if( document.getElementById("estado").value.length == 0 || valorEstado == 0 ) {
			document.getElementById("estado").focus();
			alert("Seleccione un Estado");
			return false;
		}else if( valorCodigoPostal == null || valorCodigoPostal.length == 0 || /^\s+$/.test(valorCodigoPostal) ) {
			document.getElementById("codigoPostal").focus();
			alert("Ingrese el Código Postal");
			return false;
		}
		//Valida productos
		if( valorProdPrincipales == null || valorProdPrincipales.length == 0 || /^\s+$/.test(valorProdPrincipales) ) {
			document.getElementById("prodPrincipales").focus();
			alert("Ingrese el producto principal");
			return false;
		}
		if(document.getElementById("idProd2").style.display == 'block'){
			if( document.getElementById("prodPrincipales2").value == null || document.getElementById("prodPrincipales2").value.length == 0 || document.getElementById("prodPrincipales2").value == " " ) {
				document.getElementById("prodPrincipales2").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd3").style.display == 'block'){
			if( document.getElementById("prodPrincipales3").value == null || document.getElementById("prodPrincipales3").value.length == 0 || document.getElementById("prodPrincipales3").value == " " ) {
				document.getElementById("prodPrincipales3").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd4").style.display == 'block'){
			if( document.getElementById("prodPrincipales4").value == null || document.getElementById("prodPrincipales4").value.length == 0 || document.getElementById("prodPrincipales4").value == " " ) {
				document.getElementById("prodPrincipales4").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd5").style.display == 'block'){
			if( document.getElementById("prodPrincipales5").value == null || document.getElementById("prodPrincipales5").value.length == 0 || document.getElementById("prodPrincipales5").value == " " ) {
				document.getElementById("prodPrincipales5").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd6").style.display == 'block'){
			if( document.getElementById("prodPrincipales6").value == null || document.getElementById("prodPrincipales6").value.length == 0 || document.getElementById("prodPrincipales6").value == " " ) {
				document.getElementById("prodPrincipales6").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd7").style.display == 'block'){
			if( document.getElementById("prodPrincipales7").value == null || document.getElementById("prodPrincipales7").value.length == 0 || document.getElementById("prodPrincipales7").value == " " ) {
				document.getElementById("prodPrincipales7").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd8").style.display == 'block'){
			if( document.getElementById("prodPrincipales8").value == null || document.getElementById("prodPrincipales8").value.length == 0 || document.getElementById("prodPrincipales8").value == " " ) {
				document.getElementById("prodPrincipales8").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd9").style.display == 'block'){
			if( document.getElementById("prodPrincipales9").value == null || document.getElementById("prodPrincipales9").value.length == 0 || document.getElementById("prodPrincipales9").value == " " ) {
				document.getElementById("prodPrincipales9").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd10").style.display == 'block'){
			if( document.getElementById("prodPrincipales10").value == null || document.getElementById("prodPrincipales10").value.length == 0 || document.getElementById("prodPrincipales10").value == " " ) {
				document.getElementById("prodPrincipales10").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd11").style.display == 'block'){
			if( document.getElementById("prodPrincipales11").value == null || document.getElementById("prodPrincipales11").value.length == 0 || document.getElementById("prodPrincipales11").value == " " ) {
				document.getElementById("prodPrincipales11").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd12").style.display == 'block'){
			if( document.getElementById("prodPrincipales12").value == null || document.getElementById("prodPrincipales12").value.length == 0 || document.getElementById("prodPrincipales12").value == " " ) {
				document.getElementById("prodPrincipales12").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd13").style.display == 'block'){
			if( document.getElementById("prodPrincipales13").value == null || document.getElementById("prodPrincipales13").value.length == 0 || document.getElementById("prodPrincipales13").value == " " ) {
				document.getElementById("prodPrincipales13").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd14").style.display == 'block'){
			if( document.getElementById("prodPrincipales14").value == null || document.getElementById("prodPrincipales14").value.length == 0 || document.getElementById("prodPrincipales14").value == " " ) {
				document.getElementById("prodPrincipales14").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd15").style.display == 'block'){
			if( document.getElementById("prodPrincipales15").value == null || document.getElementById("prodPrincipales15").value.length == 0 || document.getElementById("prodPrincipales15").value == " " ) {
				document.getElementById("prodPrincipales15").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd16").style.display == 'block'){
			if( document.getElementById("prodPrincipales16").value == null || document.getElementById("prodPrincipales16").value.length == 0 || document.getElementById("prodPrincipales16").value == " " ) {
				document.getElementById("prodPrincipales16").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd17").style.display == 'block'){
			if( document.getElementById("prodPrincipales17").value == null || document.getElementById("prodPrincipales17").value.length == 0 || document.getElementById("prodPrincipales17").value == " " ) {
				document.getElementById("prodPrincipales17").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd18").style.display == 'block'){
			if( document.getElementById("prodPrincipales18").value == null || document.getElementById("prodPrincipales18").value.length == 0 || document.getElementById("prodPrincipales18").value == " " ) {
				document.getElementById("prodPrincipales18").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd19").style.display == 'block'){
			if( document.getElementById("prodPrincipales19").value == null || document.getElementById("prodPrincipales19").value.length == 0 || document.getElementById("prodPrincipales19").value == " " ) {
				document.getElementById("prodPrincipales19").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		if(document.getElementById("idProd20").style.display == 'block'){
			if( document.getElementById("prodPrincipales20").value == null || document.getElementById("prodPrincipales20").value.length == 0 || document.getElementById("prodPrincipales20").value == " " ) {
				document.getElementById("prodPrincipales20").focus();
				alert("Ingrese el producto principal");
				return false;
			}
		}
		
		if( valorSectorUno.checked && (!valorSectorDos.checked && !valorSectorTres.checked) ){
			document.getElementById('sec2').style.display = 'none';
			document.getElementById('sec3').style.display = 'block';
			return true;
		}else if( valorSectorDos.checked && (!valorSectorUno.checked && !valorSectorTres.checked) ){
			document.getElementById('sec2').style.display = 'none';
			document.getElementById('sec3').style.display = 'block';
			return true;
		}else if( valorSectorTres.checked && (!valorSectorUno.checked && !valorSectorDos.checked) ){
			document.getElementById('sec2').style.display = 'none';
			document.getElementById('sec3').style.display = 'block';
			return true;
		}else{
			document.getElementById("sector1").focus();
			alert("Selecione una categoría a la que pertenece su empresa.");
			return false;
		}
	}else if( sec == '3' ){
		
		var estados = 0;
		
		for(var x = 1; x < 33; x++){
			check = document.getElementById('check'+x);
			if(check.checked){
				estados++;
				break;
			}
		}
		
		if (estados == 0){
			alert('Seleccione por lo menos 1 estado');
			return false;
		}else{
			document.getElementById('sec3').style.display = 'none';
			document.getElementById('sec4').style.display = 'block';
			return true;
		}

	}else if( sec == '4' ){

		if( valorTipoContacto == " " || valorTipoContacto == 0 || valorTipoContacto == null || valorTipoContacto == "Seleccione tipo de contacto" ){
			document.getElementById("tipoContacto").focus();
			alert("Seleccione un tipo de contacto");
			return false;
		}else if( document.getElementById("tipoContacto").value == 'Otro' ){
			if( document.getElementById("tipoOtro").value == null ||  document.getElementById("tipoOtro").value== " " || document.getElementById("tipoOtro").value.length == 0  ){
				document.getElementById("tipoOtro").focus();
				alert("Indique el tipo de contacto");
				return false;
			}
		}		
		
		if( valorNombre == null || valorNombre.length == 0 || /^\s+$/.test(valorNombre) ) {
			document.getElementById("nombreContacto").focus();
			alert("Ingrese el Nombre(s) requerido");
			return false;
		}else if( valorPaterno == null || valorPaterno.length == 0 || /^\s+$/.test(valorPaterno) ) {
			document.getElementById("appPat").focus();
			alert("Ingrese Apellido Paterno");
			return false;
		}else if( valorMaterno == null || valorMaterno.length == 0 || /^\s+$/.test(valorMaterno) ) {
			document.getElementById("appMat").focus();
			alert("Ingrese Apellido Materno");  
			return false;
		}else if( !(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(valorCorreoContacto)) ) {
			document.getElementById("correoElectronicoContacto").focus();
			alert("Ingrese una dirección de correo electrónico válida");
			return false;
		}else if (valorCorreoContacto != valorComparaContacto){
			document.getElementById("comparaCorreoContacto").focus();
			alert("El correo electrónico no coincide");
			return false;
		}else if( valorTelefonoContacto == null || valorTelefonoContacto.length == 0 || /^\s+$/.test(valorTelefonoContacto) ) {
			document.getElementById("telContacto").focus();
			alert("Ingrese su teléfono");
			return false;
		}
		
		//Validación segundo contacto
		if( document.getElementById("contacto2").style.display == 'block' ){
			
			if( valorTipoContacto2 == " " || valorTipoContacto2 == 0 || valorTipoContacto2 == null || valorTipoContacto2 == "Seleccione tipo de contacto" ){
				document.getElementById("tipoContacto2").focus();
				alert("Seleccione un tipo de contacto para el segundo contacto");
				return false;
			}else if( document.getElementById("tipoContacto2").value == 'Otro' ){
				if( document.getElementById("tipoOtro2").value == null ||  document.getElementById("tipoOtro2").value== " " || document.getElementById("tipoOtro2").value.length == 0  ){
					document.getElementById("tipoOtro2").focus();
					alert("Indique el tipo de contacto");
					return false;
				}
			}
			
			if( valorNombre2 == null || valorNombre2.length == 0 || /^\s+$/.test(valorNombre2) ) {
				document.getElementById("nombreContacto2").focus();
				alert("Ingrese el Nombre(s) requerido");
				return false;
			}else if( valorPaterno2 == null || valorPaterno2.length == 0 || /^\s+$/.test(valorPaterno2) ) {
				document.getElementById("appPat2").focus();
				alert("Ingrese Apellido Paterno");
				return false;
			}else if( valorMaterno2 == null || valorMaterno2.length == 0 || /^\s+$/.test(valorMaterno2) ) {
				document.getElementById("appMat2").focus();
				alert("Ingrese Apellido Materno");  
				return false;
			}else if( !(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(valorCorreoContacto2)) ) {
				document.getElementById("correoElectronicoContacto2").focus();
				alert("Ingrese una dirección de correo electrónico válida");
				return false;
			}else if (valorCorreoContacto2 != valorComparaContacto2){
				document.getElementById("comparaCorreoContacto2").focus();
				alert("El correo electrónico no coincide");
				return false;
			}else if( valorTelefonoContacto2 == null || valorTelefonoContacto2.length == 0 || /^\s+$/.test(valorTelefonoContacto2) ) {
				document.getElementById("telContacto2").focus();
				alert("Ingrese su teléfono");
				return false;
			}
		}
		
		document.getElementById('sec4').style.display = 'none';
		document.getElementById('sec5').style.display = 'block';
		return true;	

	}else if(sec == '5'){
		if( valorCliente1 == null || valorCliente1.length == 0 || /^\s+$/.test(valorCliente1) ) {
			document.getElementById("cliente1").focus();
			alert("Ingrese el nombre del cliente");
			return false;
		}else if( valorProdCliente1 == null || valorProdCliente1.length == 0 || /^\s+$/.test(valorProdCliente1) ) {
			document.getElementById("prodCliente1").focus();
			alert("El campo Productos que compra el cliente es requerido");
			return false;
		}else if( valorAniosProveCliente1.length == 0 || /^\s+$/.test(valorAniosProveCliente1) || isNaN(valorAniosProveCliente1) ) {
			document.getElementById("aniosProveCliente1").focus();
			alert("El campo Años como proveedor es requerido y debe ingresarlo con números");
			return false;
		}else if( valorMesesProveCliente1.length == 0 || /^\s+$/.test(valorMesesProveCliente1) || isNaN(valorMesesProveCliente1) ) {
			document.getElementById("mesesProveCliente1").focus();
			alert("El campo Meses como proveedor es requerido y debe ingresarlo con números");
			return false;
		}
		
		if( document.getElementById('prove2').style.display == 'block' ){
			if( valorCliente2 == null || valorCliente2.length == 0 || /^\s+$/.test(valorCliente2) ) {
				document.getElementById("cliente2").focus();
				alert("Ingrese el nombre del cliente");
				return false;
			}else if( valorProdCliente2 == null || valorProdCliente2.length == 0 || /^\s+$/.test(valorProdCliente2) ) {
				document.getElementById("prodCliente2").focus();
				alert("El campo Productos que compra el cliente es requerido");
				return false;
			}else if( valorAniosProveCliente2.length == 0 || /^\s+$/.test(valorAniosProveCliente2) || isNaN(valorAniosProveCliente2) ) {
				document.getElementById("aniosProveCliente2").focus();
				alert("El campo Años como proveedor es requerido y debe ingresarlo con números");
				return false;
			}else if( valorMesesProveCliente2.length == 0 || /^\s+$/.test(valorMesesProveCliente2) || isNaN(valorMesesProveCliente2) ) {
				document.getElementById("mesesProveCliente2").focus();
				alert("El campo Meses como proveedor es requerido y debe ingresarlo con números");
				return false;
			}
		}

		if( document.getElementById('prove3').style.display == 'block' ){
			if( valorCliente3 == null || valorCliente3.length == 0 || /^\s+$/.test(valorCliente3) ) {
				document.getElementById("cliente3").focus();
				alert("Ingrese el nombre del cliente");
				return false;
			}else if( valorProdCliente3 == null || valorProdCliente3.length == 0 || /^\s+$/.test(valorProdCliente3) ) {
				document.getElementById("prodCliente3").focus();
				alert("El campo Productos que compra el cliente es requerido");
				return false;
			}else if( valorAniosProveCliente3.length == 0 || /^\s+$/.test(valorAniosProveCliente3) || isNaN(valorAniosProveCliente3) ) {
				document.getElementById("aniosProveCliente3").focus();
				alert("El campo Años como proveedor es requerido y debe ingresarlo con números");
				return false;
			}else if( valorMesesProveCliente3.length == 0 || /^\s+$/.test(valorMesesProveCliente3) || isNaN(valorMesesProveCliente3) ) {
				document.getElementById("mesesProveCliente3").focus();
				alert("El campo Meses como proveedor es requerido y debe ingresarlo con números");
				return false;
			}
		}
		
		if( document.getElementById('prove4').style.display == 'block' ){
			if( valorCliente4 == null || valorCliente4.length == 0 || /^\s+$/.test(valorCliente4) ) {
				document.getElementById("cliente4").focus();
				alert("Ingrese el nombre del cliente");
				return false;
			}else if( valorProdCliente4 == null || valorProdCliente4.length == 0 || /^\s+$/.test(valorProdCliente4) ) {
				document.getElementById("prodCliente4").focus();
				alert("El campo Productos que compra el cliente es requerido");
				return false;
			}else if( valorAniosProveCliente4.length == 0 || /^\s+$/.test(valorAniosProveCliente4) || isNaN(valorAniosProveCliente4) ) {
				document.getElementById("aniosProveCliente4").focus();
				alert("El campo Años como proveedor es requerido y debe ingresarlo con números");
				return false;
			}else if( valorMesesProveCliente4.length == 0 || /^\s+$/.test(valorMesesProveCliente4) || isNaN(valorMesesProveCliente4) ) {
				document.getElementById("mesesProveCliente4").focus();
				alert("El campo Meses como proveedor es requerido y debe ingresarlo con números");
				return false;
			}
		}
		
		if( document.getElementById('prove5').style.display == 'block' ){
			if( valorCliente5 == null || valorCliente5.length == 0 || /^\s+$/.test(valorCliente5) ) {
				document.getElementById("cliente5").focus();
				alert("Ingrese el nombre del cliente");
				return false;
			}else if( valorProdCliente5 == null || valorProdCliente5.length == 0 || /^\s+$/.test(valorProdCliente5) ) {
				document.getElementById("prodCliente5").focus();
				alert("El campo Productos que compra el cliente es requerido");
				return false;
			}else if( valorAniosProveCliente5.length == 0 || /^\s+$/.test(valorAniosProveCliente5) || isNaN(valorAniosProveCliente5) ) {
				document.getElementById("aniosProveCliente5").focus();
				alert("El campo Años como proveedor es requerido y debe ingresarlo con números");
				return false;
			}else if( valorMesesProveCliente5.length == 0 || /^\s+$/.test(valorMesesProveCliente5) || isNaN(valorMesesProveCliente5) ) {
				document.getElementById("mesesProveCliente5").focus();
				alert("El campo Meses como proveedor es requerido y debe ingresarlo con números");
				return false;
			}
		}
		
		document.getElementById('sec5').style.display = 'none';
		document.getElementById('sec6').style.display = 'block';
		return true;
	}else{
		if( document.getElementById('reqSi').checked == true && document.getElementById('reqNo').checked == false ){
			if( document.getElementById('cat1').checked == false && document.getElementById('cat2').checked == false && document.getElementById('cat3').checked == false ){
				alert('Seleccione un catálogo');
				return false;
			}else{				
				return true;	
			}

		}else if( document.getElementById('reqSi').checked == false && document.getElementById('reqNo').checked == true ){			
			return true;	
		}else{
			alert('¿Desea recibir requerimientos de compra?');
			return false;
		}

	}

}
function showCat(){

	if ( document.getElementById('reqSi').checked ) {
		document.getElementById('reqNo').checked = false;
		document.getElementById('reqNo').disabled = true;
		document.getElementById('showCatalogos').style.display = 'block';
	}else if ( document.getElementById('reqSi').checked == false ){
		document.getElementById('reqNo').disabled = false;
		document.getElementById('showCatalogos').style.display = 'none';
	}
}

function recibeReqNo(){
	if ( document.getElementById('reqNo').checked ){
		document.getElementById('reqSi').checked = false;
		document.getElementById('reqSi').disabled = true;
	}else if ( document.getElementById('reqNo').checked == false ){
		document.getElementById('reqSi').disabled = false;
	}
}

function checkSector(){

	secUno = document.getElementById("sector1");
	secDos = document.getElementById("sector2");
	secTres = document.getElementById("sector3");
	
	if( secUno.checked ) {
		secDos.checked = false;
		secTres.checked = false;
	}
	if ( secDos.checked ){
		secUno.checked = false;
		secTres.checked = false;
	}
	if ( secTres.checked ){
		secUno.checked = false;
		secDos.checked = false;
	}
}


function addProd(){
	var sizeProd = 2;

	for ( var i = 1; i < 21; i++) {
		_block = document.getElementById('idProd' + i).style.display;
		if (_block == 'block') {
			sizeProd++;
		}
	}
	document.getElementById('idProd' + sizeProd).style.display = 'block';
}

function delProd(num){
	document.getElementById('prodPrincipales'+num).value = '';
	document.getElementById('idProd'+num).style.display = 'none';
}

function valorTipoCont(tipo){
	if(tipo != 'Otro'){
		document.getElementById("tipoOtro").value = tipo;
		document.getElementById("otroTipo").style.display = 'none';
		return true;
	}else{
		document.getElementById("otroTipo").style.display = 'block';
		document.getElementById("otroTipo").value = '';
	}
}

function valorTipoCont2(tipo){
	if(tipo != 'Otro'){
		document.getElementById("tipoOtro2").value = tipo;
		document.getElementById("otroTipo2").style.display = 'none';
		return true;
	}else{
		document.getElementById("otroTipo2").style.display = 'block';
		document.getElementById("otroTipo2").value = '';
	}
}

function supContacto(){
	document.getElementById("tipoContacto2").selectedIndex = 0;
	document.getElementById("nombreContacto2").value = '';
	document.getElementById("appPat2").value = '';
	document.getElementById("appMat2").value = '';
	document.getElementById("correoElectronicoContacto2").value = '';
	document.getElementById("telContacto2").value = '';
	document.getElementById("contacto2").style.display = 'none';
	document.getElementById("linkAddContacto").style.display = 'block';
	
}


function showContacto(){
	document.getElementById("contacto2").style.display = 'block';
	document.getElementById("linkAddContacto").style.display = 'none';
}

function showCliente(){
	
	var sizeCliente = 2;

	for ( var c = 2; c < 6; c++) {
		_block = document.getElementById('prove' + c).style.display;
		if (_block == 'block') {
			sizeCliente++;
		}
	}
	document.getElementById( 'prove' + sizeCliente ).style.display = 'block';
	
	if(sizeCliente == 5){
		document.getElementById("linkAddProve").style.display = 'none';
	}
}

function supCliente(v){
	document.getElementById("cliente" + v).value = '';
	document.getElementById("prodCliente" + v).value = '';
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

function supArchivo(obj){
	document.getElementById('idCampoArchivo' + obj ).value='';
	document.getElementById('idDivArchivo' + obj + 'Block').style.display = 'none';
}