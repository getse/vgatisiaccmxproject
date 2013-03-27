function validacion(sec) {

	document.getElementById('hidSector1').value = document.getElementById('sector1').value;
	document.getElementById('hidSector2').value = document.getElementById('sector2').value;
	document.getElementById('hidSector3').value = document.getElementById('sector3').value;
	
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


	valorNombre = document.getElementById("nombreContacto").value;
	valorPaterno = document.getElementById("appPat").value;
	valorMaterno = document.getElementById("appMat").value;
	valorCorreoContacto = document.getElementById("correoElectronicoContacto").value;
	valorComparaContacto = document.getElementById("comparaCorreoContacto").value;
	valorTelefonoContacto = document.getElementById("telContacto").value;
	valorCliente = document.getElementById("cliente").value;
	valorProdCliente = document.getElementById("prodCliente").value;
	valorAniosProveCliente = document.getElementById("aniosProveCliente").value;
	valorMesesProveCliente = document.getElementById("mesesProveCliente").value;



	if (sec == '1') {

		if( valorPerJuridica == " " || valorPerJuridica == 0 || valorPerJuridica == null || valorPerJuridica == "Seleccione el tipo de persona") {
			document.getElementById("personalidadJuridica").focus();
			alert("Seleccione un Estado");
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
		if( valorMsjVenta == null || valorMsjVenta.length == 0 || valorMsjVenta == " " ) {
			document.getElementById("mensajeVenta").focus();
			alert("Ingrese Mensaje de ventas");  
			return false;
		}else if( valorCalle == null || valorCalle.length == 0 || valorCalle == " " ) {
			document.getElementById("calle").focus();
			alert("Ingrese la calle");  
			return false;
		}else if( valorNumExt == null || valorNumExt.length == 0 || valorNumExt == " " ) {
			document.getElementById("numExt").focus();
			alert("Ingrese el Número exterior");  
			return false;
		}else if( valorColonia == null || valorColonia.length == 0 || valorColonia == " " ) {
			document.getElementById("colonia").focus();
			alert("Ingrese la colonia");
			return false;
		}else if( valorDelegacion == null || valorDelegacion.length == 0 || valorDelegacion == " " ) {
			document.getElementById("delegacion").focus();
			alert("Ingrese la delegación");
			return false;
		}else if( valorEstado == " " || valorEstado == 0 || valorEstado == null) {
			document.getElementById("estado").focus();
			alert("Seleccione un Estado");
			return false;
		}else if( valorCodigoPostal == null || valorCodigoPostal.length == 0 || valorCodigoPostal == " " ) {
			document.getElementById("codigoPostal").focus();
			alert("Ingrese el Código Postal");
			return false;
		}else if( valorProdPrincipales == null || valorProdPrincipales.length == 0 || valorProdPrincipales == " " ) {
			document.getElementById("prodPrincipales").focus();
			alert("Ingrese el producto principal");
			return false;
		}else if( valorSectorUno.checked && (!valorSectorDos.checked && !valorSectorTres.checked) ){
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
			alert("Selecione una categoría a la que pertenece su empresa.");
			return false;
		}
	}else if( sec == '3' ){

		document.getElementById('sec3').style.display = 'none';
		document.getElementById('sec4').style.display = 'block';
		return true;

	}else if( sec == '4' ){

		if( valorNombre == null || valorNombre.length == 0 || valorNombre == " " ) {
			document.getElementById("nombreContacto").focus();
			alert("Ingrese el Nombre(s) requerido");
			return false;
		}else if( valorPaterno == null || valorPaterno.length == 0 || valorPaterno == " " ) {
			document.getElementById("appPat").focus();
			alert("Ingrese Apellido Paterno");
			return false;
		}else if( valorMaterno == null || valorMaterno.length == 0 || valorMaterno == " " ) {
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
		}else if( valorTelefonoContacto == null || valorTelefonoContacto.length == 0 || valorTelefonoContacto == " " ) {
			document.getElementById("telContacto").focus();
			alert("Ingrese su teléfono");
			return false;
		}else if( valorCliente == null || valorCliente.length == 0 || valorCliente == " " ) {
			document.getElementById("cliente").focus();
			alert("Ingrese el nombre del cliente");
			return false;
		}else if( valorProdCliente == null || valorProdCliente.length == 0 || valorProdCliente == " " ) {
			document.getElementById("prodCliente").focus();
			alert("El campo Productos que compra el cliente es requerido");
			return false;
		}else if( isNaN(valorAniosProveCliente) ) {
			document.getElementById("aniosProveCliente").focus();
			alert("El campo Años como proveedor es requerido y debe ingresarlo con números");
			return false;
		}else if( isNaN(valorMesesProveCliente) ) {
			document.getElementById("mesesProveCliente").focus();
			alert("El campo Meses como proveedor es requerido y debe ingresarlo con números");
			return false;
		}else{
			document.getElementById('sec4').style.display = 'none';
			document.getElementById('sec5').style.display = 'block';
			return true;	
		}

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
		document.getElementById('showCatalogos').style.display = 'block';
	}else if ( document.getElementById('reqSi').checked == false ){
		document.getElementById('showCatalogos').style.display = 'none';
	}else if ( document.getElementById('reqNo').checked ){
		document.getElementById('reqSi').checked = false;
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

function calendario() {
	Calendar.setup({
		inputField : "ingreso", // id del campo de texto
		ifFormat : "%d/%m/%Y", // formato de la fecha que se escriba en el
		// campo de texto
		button : "lanzador" // el id del botón que lanzará el calendario
	});
}