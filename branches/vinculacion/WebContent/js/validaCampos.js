function validacion() {
			valorEmpresa = document.getElementById("idEmpresa").value;
			valorNombre = document.getElementById("idNombre").value;
			valorPaterno = document.getElementById("idAppPaterno").value;
			valorMaterno = document.getElementById("idAppMaterno").value;
			valorCorreo = document.getElementById("idCorreoElectronico").value;
			valorCompara = document.getElementById("idComparaCorreo").value;
			
			valorPuesto = document.getElementById("idPuesto").value;
			valorTelefono = document.getElementById("idTelefono").value;
			//valorAddTelefono = document.getElementById("idAddTelefono").value;
			
			valorCalle = document.getElementById("idCalle").value;
			valorNumExt = document.getElementById("idNumExt").value;
			valorColonia = document.getElementById("idColonia").value;
			valorDelegacion = document.getElementById("idDelegacion").value;
			//Select
			valorEstado = document.getElementById("idEstado").selectedIndex;
			valorCodigoPostal = document.getElementById("idCodigoPostal").value;
			
			
			if( valorEmpresa == null || valorEmpresa.length == 0 || /^\s+$/.test(valorEmpresa) ) {
				alert("Ingrese en Nombre de la Empresa");
				return false;
			}else if( valorNombre == null || valorNombre.length == 0 || /^\s+$/.test(valorNombre) ) {
				alert("Ingrese el Nombre(s) requerido");
				return false;
			}else if( valorPaterno == null || valorPaterno.length == 0 || /^\s+$/.test(valorPaterno) ) {
				alert("Ingrese Apellido Paterno");
				return false;
			}else if( valorMaterno == null || valorMaterno.length == 0 || /^\s+$/.test(valorMaterno) ) {
				alert("Ingrese Apellido Materno");  
				return false;
			}else if( !(/[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/.test(valorCorreo)) ) {
				alert("Ingrese una dirección de correo electrónico válida");
				return false;
			}else if (valorCorreo != valorCompara){
				alert("El correo electrónico no coincide");
				return false;
			}else if( valorPuesto == null || valorPuesto.length == 0 || /^\s+$/.test(valorPuesto) ) {
				alert("Ingrese su puesto");
				return false;
			}else if( valorTelefono == null || valorTelefono.length == 0 || /^\s+$/.test(valorTelefono) ) {
				alert("Ingrese el teléfono");
				return false;
			}else if( valorCalle == null || valorCalle.length == 0 || /^\s+$/.test(valorCalle) ) {
				alert("Ingrese la calle");
				return false;
			}else if( valorNumExt == null || valorNumExt.length == 0 || /^\s+$/.test(valorNumExt) ) {
				alert("Ingrese el número exterior");
				return false;
			}else if( valorColonia == null || valorColonia.length == 0 || /^\s+$/.test(valorColonia) ) {
				alert("Ingrese la colonia");
				return false;
			}else if( valorDelegacion == null || valorDelegacion.length == 0 || /^\s+$/.test(valorDelegacion) ) {
				alert("Ingrese la delegación");
				return false;
			}else if( valorCodigoPostal == null || valorCodigoPostal.length == 0 || /^\s+$/.test(valorCodigoPostal) ) {
				alert("Ingrese el código postal");
				return false;
			}else if( valorEstado == null || valorEstado == "Seleccione un Estado" ) {
				alert("Seleccione un estado");
				return false;
			}
	return true;
}