			function showSelect(val1,val2){
				if(document.getElementById(val1).checked==true){
					document.getElementById(val2).style.display='block';
				}
				else{
					document.getElementById(val2).value=-1;
					document.getElementById(val2).style.display='none';
				}
			}
			function menuReporte(f){				
				switch (f) {
					case 1:
						document.reporte1.submit();
						break;
					case 2:
						document.reporte2.submit();
						break;
					case 3:
						document.reporte3.submit();
						break;
					case 4:
						if(ccmxservicios()){
							document.serviciosReport.submit();
						}						
						break;
					case 5:
						if(ccmxfinanciero()){
							document.finanzasReport.submit();
						}						
						break;
					case 6:
						if( pymes()){
							document.pymesReport.submit();
						}						
						break;
				}
			}
			function ccmxservicios(){
				if(document.getElementById("checTractoraServ").checked==true){
					if(document.getElementById("tractoraServ").value == -1){
						alert('Seleccione tractora');
						return false;
					}
				}
				if(document.getElementById("checConsultoraServ").checked==true){
					if(document.getElementById("consultoraServ").value ==  -1){
						alert('Seleccione consultora');
						return false;
					}
				}
				if(document.getElementById("checSesionServ").checked==true){
					if(document.getElementById("sesionServ").value == -1){
						alert('Seleccione sesion informativa');
						return false;
					}
				}
				document.getElementById("checTractoraServ").checked=false;
				document.getElementById("checConsultoraServ").checked=false;
				document.getElementById("checSesionServ").checked=false;
				return true;
			}
			
			function ccmxfinanciero(){
				if(document.getElementById("checConsultoraFin").checked==true){
					if(document.getElementById("consultoraFin").value ==  -1){
						alert('Seleccione consultora');
						return false;
					}
				}
				if(document.getElementById("checAnticipoFin").checked==true){
					if(document.getElementById("anticipoFin").value == -1){
						alert('Seleccione Pago de anticipo');
						return false;
					}
				}
				if(document.getElementById("checFiniquitoFin").checked==true){
					if(document.getElementById("finiquitoFin").value == -1){
						alert('Seleccione pago finiquito');
						return false;
					}
				}				
				if(document.getElementById("cAnticipofiniquitoFin").checked==true){
					if(document.getElementById("anticipofiniquitoFin").value ==  -1){
						alert('Seleccione pago de anticipo y finiquito');
						return false;
					}
				}
				if(document.getElementById("checTipoFin").checked==true){
					if(document.getElementById("tipoFin").value ==  -1){
						alert('Seleccione tipo de consultoría');
						return false;
					}
				}
				document.getElementById("checConsultoraFin").checked=false;
				document.getElementById("checAnticipoFin").checked=false;
				document.getElementById("checFiniquitoFin").checked=false;
				document.getElementById("cAnticipofiniquitoFin").checked=false;
				document.getElementById("checTipoFin").checked=false;
				return true;
			}
			function pymes(){
				if(document.getElementById("checCedulaPy").checked==true){
					if(document.getElementById("cedulaPy").value == -1){
						alert('Seleccione cedula');
						return false;
					}
				}
				if(document.getElementById("checConsultoraPy").checked==true){
					if(document.getElementById("consultoraPy").value ==  -1){
						alert('Seleccione consultora');
						return false;
					}
				}
				if(document.getElementById("checEstatusPy").checked==true){
					if(document.getElementById("estatusPy").value == -1){
						alert('Seleccione estatus');
						return false;
					}
				}
				if(document.getElementById("checAnticipoPy").checked==true){
					if(document.getElementById("anticipoPy").value == -1){
						alert('Pago de anticipo');
						return false;
					}
				}
				if(document.getElementById("cAnticipofiniquitoPy").checked==true){
					if(document.getElementById("anticipofiniquitoPy").value ==  -1){
						alert('Seleccione pago de anticipo y finiquito');
						return false;
					}
				}
				document.getElementById("checCedulaPy").checked=false;
				document.getElementById("checConsultoraPy").checked=false;
				document.getElementById("checEstatusPy").checked=false;
				document.getElementById("checAnticipoPy").checked=false;
				document.getElementById("cAnticipofiniquitoPy").checked=false;
				return true;
			}