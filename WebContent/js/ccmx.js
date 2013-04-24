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