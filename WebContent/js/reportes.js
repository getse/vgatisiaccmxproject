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
					case 7:
						if( ccmxserviciosConsult()){
							document.pymesReport.submit();
						}						
						break;
					case 8:
						if( ccmxfinancieroConsult()){
							document.pymesReport.submit();
						}						
						break;
					case 9:
						if( pymesConsult()){
							document.pymesReport.submit();
						}						
						break;
					case 10:
						if(pymesConsultRest()){
							document.pymesReport.submit();
						}						
						break;
					case 12:
						document.reporte4.submit();
						break;
					case 13:
						if(indicadores()){
							document.indicadoresReport.submit();
						}						
						break;
					case 14:
						if(ccmxserviciosRes()){
							document.serviciosReport.submit();
						}						
						break;
						
						
				}
			}
			function ccmxservicios(){
				if(document.getElementById("checTractoraServ").checked==true){
					if(document.getElementById("tractoraServ").value == -1){
						document.getElementById("tractoraServ").focus();
						alert('Seleccione tractora');
						return false;
					}
				}
				if(document.getElementById("checConsultoraServ").checked==true){
					if(document.getElementById("consultoraServ").value ==  -1){
						document.getElementById("consultoraServ").focus();
						alert('Seleccione consultora');
						return false;
					}
				}
				if(document.getElementById("checSesionServ").checked==true){
					if(document.getElementById("sesionServ").value == -1){
						document.getElementById("sesionServ").focus();
						alert('Seleccione sesion informativa');
						return false;
					}
				}
				if(document.getElementById("checAnticipoServ").checked==true){
					if(document.getElementById("anticipoServ").value == -1){
						document.getElementById("anticipoServ").focus();
						alert('Pago de anticipo');
						return false;
					}
				}
				if(document.getElementById("cAnticipofiniquitoServ").checked==true){
					if(document.getElementById("anticipofiniquitoServ").value ==  -1){
						document.getElementById("anticipofiniquitoServ").focus();
						alert('Seleccione pago de anticipo y finiquito');
						return false;
					}
				}
				document.getElementById("checTractoraServ").checked=false;
				document.getElementById("checConsultoraServ").checked=false;
				document.getElementById("checSesionServ").checked=false;
				document.getElementById("checAnticipoServ").checked=false;
				document.getElementById("cAnticipofiniquitoServ").checked=false;
				return true;
			}
			
			function ccmxfinanciero(){
				if(document.getElementById("checConsultoraFin").checked==true){
					if(document.getElementById("consultoraFin").value ==  -1){
						document.getElementById("consultoraFin").focus();
						alert('Seleccione consultora');
						return false;
					}
				}
				if(document.getElementById("checAnticipoFin").checked==true){
					if(document.getElementById("anticipoFin").value == -1){
						document.getElementById("anticipoFin").focus();
						alert('Seleccione Pago de anticipo');
						return false;
					}
				}
				if(document.getElementById("checFiniquitoFin").checked==true){
					if(document.getElementById("finiquitoFin").value == -1){
						document.getElementById("finiquitoFin").focus();
						alert('Seleccione pago finiquito');
						return false;
					}
				}				
				if(document.getElementById("cAnticipofiniquitoFin").checked==true){
					if(document.getElementById("anticipofiniquitoFin").value ==  -1){
						document.getElementById("anticipofiniquitoFin").focus();
						alert('Seleccione pago de anticipo y finiquito');
						return false;
					}
				}
				if(document.getElementById("checTipoFin").checked==true){
					if(document.getElementById("tipoFin").value ==  -1){
						document.getElementById("tipoFin").focus();
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
						document.getElementById("cedulaPy").focus();
						alert('Seleccione cedula');
						return false;
					}
				}
				if(document.getElementById("checConsultoraPy").checked==true){
					if(document.getElementById("consultoraPy").value ==  -1){
						document.getElementById("consultoraPy").focus();
						alert('Seleccione consultor');
						return false;
					}
				}
				if(document.getElementById("checEstatusPy").checked==true){
					if(document.getElementById("estatusPy").value == -1){
						document.getElementById("estatusPy").focus();
						alert('Seleccione estatus');
						return false;
					}
				}
				if(document.getElementById("checAnticipoPy").checked==true){
					if(document.getElementById("anticipoPy").value == -1){
						document.getElementById("anticipoPy").focus();
						alert('Seleccione Pago de anticipo');
						return false;
					}
				}
				if(document.getElementById("cAnticipofiniquitoPy").checked==true){
					if(document.getElementById("anticipofiniquitoPy").value ==  -1){
						document.getElementById("anticipofiniquitoPy").focus();
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
			function ccmxserviciosRes(){
				if(document.getElementById("checTractoraServ").checked==true){
					if(document.getElementById("tractoraServ").value == -1){
						document.getElementById("tractoraServ").focus();
						alert('Seleccione tractora');
						return false;
					}
				}
				if(document.getElementById("checConsultoraServ").checked==true){
					if(document.getElementById("consultoraServ").value ==  -1){
						document.getElementById("consultoraServ").focus();
						alert('Seleccione consultora');
						return false;
					}
				}
				if(document.getElementById("checSesionServ").checked==true){
					if(document.getElementById("sesionServ").value == -1){
						document.getElementById("sesionServ").focus();
						alert('Seleccione sesion informativa');
						return false;
					}
				}
				
				document.getElementById("checTractoraServ").checked=false;
				document.getElementById("checConsultoraServ").checked=false;
				document.getElementById("checSesionServ").checked=false;
				return true;
			}
			function indicadores(){
				if(document.getElementById("checCedulaIn").checked==true){
					if(document.getElementById("cedulaIn").value == -1){
						document.getElementById("cedulaIn").focus();
						alert('Seleccione cedula');
						return false;
					}
				}
				if(document.getElementById("checConsultoraIn").checked==true){
					if(document.getElementById("consultoraIn").value ==  -1){
						document.getElementById("consultoraIn").focus();
						alert('Seleccione consultor');
						return false;
					}
				}
				if(document.getElementById("checEstatusIn").checked==true){
					if(document.getElementById("estatusIn").value == -1){
						document.getElementById("estatusIn").focus();
						alert('Seleccione estatus');
						return false;
					}
				}
				if(document.getElementById("checAnticipoIn").checked==true){
					if(document.getElementById("anticipoIn").value == -1){
						document.getElementById("anticipoIn").focus();
						alert('Seleccione Pago de anticipo');
						return false;
					}
				}
				if(document.getElementById("cAnticipofiniquitoIn").checked==true){
					if(document.getElementById("anticipofiniquitoIn").value ==  -1){
						document.getElementById("anticipofiniquitoIn").focus();
						alert('Seleccione pago de anticipo y finiquito');
						return false;
					}
				}
				document.getElementById("checCedulaIn").checked=false;
				document.getElementById("checConsultoraIn").checked=false;
				document.getElementById("checEstatusIn").checked=false;
				document.getElementById("checAnticipoIn").checked=false;
				document.getElementById("cAnticipofiniquitoIn").checked=false;
				return true;
			}
			function ccmxserviciosConsult(){
				if(document.getElementById("checTractoraServ").checked==true){
					if(document.getElementById("tractoraServ").value == -1){
						document.getElementById("tractoraServ").focus();
						alert('Seleccione tractora');
						return false;
					}
				}
				if(document.getElementById("checSesionServ").checked==true){
					if(document.getElementById("sesionServ").value == -1){
						document.getElementById("sesionServ").focus();
						alert('Seleccione sesion informativa');
						return false;
					}
				}
				if(document.getElementById("checAnticipoServ").checked==true){
					if(document.getElementById("anticipoServ").value == -1){
						document.getElementById("anticipoServ").focus();
						alert('Seleccione Pago de anticipo');
						return false;
					}
				}
				if(document.getElementById("cAnticipofiniquitoServ").checked==true){
					if(document.getElementById("anticipofiniquitoServ").value ==  -1){
						document.getElementById("anticipofiniquitoServ").focus();
						alert('Seleccione pago de anticipo y finiquito');
						return false;
					}
				}
				document.getElementById("checTractoraServ").checked=false;
				document.getElementById("checSesionServ").checked=false;
				document.getElementById("checAnticipoServ").checked=false;
				document.getElementById("cAnticipofiniquitoServ").checked=false;
				return true;
			}
			
			function ccmxfinancieroConsult(){
				if(document.getElementById("checAnticipoFin").checked==true){
					if(document.getElementById("anticipoFin").value == -1){
						document.getElementById("anticipoFin").focus();
						alert('Seleccione Pago de anticipo');
						return false;
					}
				}
				if(document.getElementById("checFiniquitoFin").checked==true){
					if(document.getElementById("checFiniquitoFin").value == -1){
						alert('Seleccione pago finiquito');
						return false;
					}
				}				
				if(document.getElementById("cAnticipofiniquitoFin").checked==true){
					if(document.getElementById("anticipofiniquitoFin").value ==  -1){
						document.getElementById("anticipofiniquitoFin").focus();
						alert('Seleccione pago de anticipo y finiquito');
						return false;
					}
				}
				if(document.getElementById("checTipoFin").checked==true){
					if(document.getElementById("tipoFin").value ==  -1){
						document.getElementById("tipoFin").focus();
						alert('Seleccione tipo de consultoría');
						return false;
					}
				}
				document.getElementById("checAnticipoFin").checked=false;
				document.getElementById("checFiniquitoFin").checked=false;
				document.getElementById("cAnticipofiniquitoFin").checked=false;
				document.getElementById("checTipoFin").checked=false;
				return true;
			}
			function pymesConsult(){
				if(document.getElementById("checCedulaPy").checked==true){
					if(document.getElementById("cedulaPy").value == -1){
						document.getElementById("cedulaPy").focus();
						alert('Seleccione cedula');
						return false;
					}
				}
				if(document.getElementById("checConsultoraPy").checked==true){
					if(document.getElementById("consultoraPy").value ==  -1){
						document.getElementById("consultoraPy").focus();
						alert('Seleccione consultor');
						return false;
					}
				}
				if(document.getElementById("checEstatusPy").checked==true){
					if(document.getElementById("estatusPy").value == -1){
						document.getElementById("estatusPy").focus();
						alert('Seleccione estatus');
						return false;
					}
				}
				if(document.getElementById("checAnticipoPy").checked==true){
					if(document.getElementById("anticipoPy").value == -1){
						document.getElementById("anticipoPy").focus();
						alert('Seleccione Pago de anticipo');
						return false;
					}
				}
				if(document.getElementById("cAnticipofiniquitoPy").checked==true){
					if(document.getElementById("anticipofiniquitoPy").value ==  -1){
						document.getElementById("anticipofiniquitoPy").focus();
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
			function pymesConsultRest(){
				if(document.getElementById("checCedulaPy").checked==true){
					if(document.getElementById("cedulaPy").value == -1){
						document.getElementById("cedulaPy").focus();
						alert('Seleccione cedula');
						return false;
					}
				}
				if(document.getElementById("checConsultoraPy").checked==true){
					if(document.getElementById("consultoraPy").value ==  -1){
						document.getElementById("consultoraPy").focus();
						alert('Seleccione consultor');
						return false;
					}
				}
				if(document.getElementById("checEstatusPy").checked==true){
					if(document.getElementById("estatusPy").value == -1){
						document.getElementById("estatusPy").focus();
						alert('Seleccione estatus');
						return false;
					}
				}
				document.getElementById("checCedulaPy").checked=false;
				document.getElementById("checConsultoraPy").checked=false;
				document.getElementById("checEstatusPy").checked=false;
				return true;
			}