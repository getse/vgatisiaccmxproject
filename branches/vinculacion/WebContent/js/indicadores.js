//JavaScript Document

jQuery.fn.calif = function(){
	var settings = { maxvalue : 5, curvalue  : 0 };

	jQuery.extend(settings, {cancel: (settings.maxvalue > 1) ? true : false});

	var container = jQuery(this);

	for(var i= 0; i <= settings.maxvalue ; i++){
		var size = i;
		if(i == 0){
			if(settings.cancel == true){
				var div = '<div class="cancel"><a href="#0" title="Borrar">Borrar calificación</a></div>';
				container.append(div);
			}
		}else{
			var div = '<div class="star"><a href="#'+i+'" title="Calificación '+i+'">'+i+'</a></div>';
			container.append(div);
		}
	}

	var stars = jQuery(container).children('.star');
	var cancel = jQuery(container).children('.cancel');

	stars.mouseover(function(){
		event.drain();
		event.fill(this);
	}).mouseout(function(){
		event.drain();
		event.reset();
	});
	
	stars.click(function(){
		document.getElementById('califCont').value = stars.index(this) + 1; 
	});
	
	stars.click(function(){
		if(settings.cancel == true){
			settings.curvalue = stars.index(this) + 1;
		}
		return false;
	});

	if(cancel){
		cancel.mouseover(function(){
			event.drain();
			jQuery(this).addClass('on');
		}).mouseout(function(){
			event.reset();
			jQuery(this).removeClass('on');
		});

		cancel.click(function(){
			event.drain();
			settings.curvalue = 0;
			jQuery(container).children('.starRpta').html("");
			document.getElementById('califCont').value = '';
			return false;
		});
	}

	var event = {
			fill: function(el){
				var index = stars.index(el) + 1;
				stars.children('a').css('width', '100%').end().slice(0,index).addClass('hover').end();
			},
			drain: function() {
				stars.filter('.on').removeClass('on').end().filter('.hover').removeClass('hover').end();
			},
			reset: function(){		
				stars.slice(0,settings.curvalue).addClass('on').end();
			}
	};
	event.reset();
};


function showForm(val) {
	
	var combo = document.getElementById( 'indicadorPyME' ).selectedIndex; 
	
	var indi = document.getElementById("areaIndi");
	var descripcion = document.getElementById("descIndi");
	var frecuencia = document.getElementById("frecIndi");
	var valTrim = 'Trimestral';
	if(  combo != 0 ){
		
		document.getElementById( 'contFormInd' ).style.display = 'block';
		document.getElementById( 'listIndi' ).style.display = 'none';
		
		document.getElementById("hidAreaIndi").value = val;

		if(combo == 1){
			indi.value = 'Ahorros (respecto del promedio de otras cotizaciones)';
			descripcion.value = 'Ahorros generados a la tractora en comparación con otros proveedores en la misma cotización';
			frecuencia.value = 'Trimestral (la última cotización ganada dentro de los tres meses previos)';
			document.getElementById("contFormula1").style.display = 'block';
		}else if(combo == 2){
			indi.value = 'Ahorros (respecto de la última cotización previo a tomar la consultoría)';
			descripcion.value = 'Ahorros generados a la tractora en comparación con su desempeño antes de tomar los servicios del CCMX.';
			frecuencia.value = valTrim;
			document.getElementById("contFormula2").style.display = 'block';
		}else if(combo == 3){
			indi.value = 'Productos con defectos';
			descripcion.value = 'Productos rechazados o defectuosos en un pedido solicitado.';
			frecuencia.value = valTrim;
			document.getElementById("contFormula3").style.display = 'block';
		}else if(combo == 4){
			indi.value = 'Cumplimiento de servicios';
			descripcion.value = 'Apego del servicio proporcionado con las condiciones de contratación';
			frecuencia.value = 'Trimestral (el último contrato dentro de los tres meses previos)';
			document.getElementById("contFormula4").style.display = 'block';
		}else if(combo == 5){
			indi.value = 'Cumplimiento en el tiempo de entrega';
			descripcion.value = 'Indica  la eficacia de la PYME en la entrega..';
			frecuencia.value = valTrim;
		}else if(combo == 6){
			indi.value = 'Efectividad en el tiempo de respuesta sobre cotizaciones';
			descripcion.value = 'Indica el % de tiempo que tarda una PYME en responder a una cotización sobre sobre el tiempo planeado.';
			frecuencia.value = 'Trimestral (la última cotización ganada dentro de los tres meses previos)';
			document.getElementById("contFormula6").style.display = 'block';
		}else if(combo == 7){
			indi.value = 'Tiempo de respuesta para atender reclamaciones o defectos';
			descripcion.value = 'Indica el tiempo en días para resolver reclamaciones relacionadas con su servicios.';
			frecuencia.value = 'Trimestral (la última reclamación dentro de los 3 meses previos al reporte)';
			document.getElementById("contFormula7").style.display = 'block';
		}else if(combo == 8){
			indi.value = 'Eficacia en la atención sobre reclamaciones';
			descripcion.value = 'Indica el cumplimiento en tiempo de una PYME para atender reclamaciones o reponer productos defectuosos.';
			frecuencia.value = 'Trimestral (última reclamación sobre dentro de los tres meses previos al reporte)';
			document.getElementById("contFormula8").style.display = 'block';
		}else if(combo == 9){
			indi.value = 'Crecimiento en ventas anuales a la tractora (a nivel de producto)';
			descripcion.value = 'Indica el aumento en la producción que le vende a una tractora.';
			frecuencia.value = 'Trimestral (acumulando los 4 trimestres previos al reporte)';
			document.getElementById("contFormula9").style.display = 'block';
		}
	}
}

function calculaIndicador1(){
	var cpg1 = document.getElementById('cpg1').value;
	var cptp1 = document.getElementById('cptp1').value;
	var result1 = (1-cpg1/cptp1)*100;
	var valor1 = parseFloat(result1).toFixed(2);//Restricción 2digitos después del punto
	
	document.getElementById('resCalculo').value = valor1+'%';
	document.getElementById('hidResCalculo').value = valor1+'%';
	document.getElementById('resCalcGral').style.display = 'block';	
}

function calculaIndicador2(){
	var cac2 = document.getElementById('cac2').value;
	var cdc2 = document.getElementById('cdc2').value;	
	var result2 = (cac2/cdc2-1)*100;
	var valor2 = parseFloat(result2).toFixed(2);
	
	document.getElementById('resCalculo').value = valor2+'%';
	document.getElementById('hidResCalculo').value = valor2+'%';
	document.getElementById('resCalcGral').style.display = 'block';
}

function calculaIndicador3(){
	var tud3 = document.getElementById('tud3').value;
	var tu3 = document.getElementById('tu3').value;
	var result3 = (tud3/tu3)*100;
	var valor3 = parseFloat(result3).toFixed(2);
	
	document.getElementById('resCalculo').value = valor3+' %';
	document.getElementById('hidResCalculo').value = valor3+' %';
	document.getElementById('resCalcGral').style.display = 'block';
}

function calculaIndicador4(){
	var scc4 = document.getElementById('scc4').value;
	var sc4 = document.getElementById('sc4').value;
	var result4 = (scc4/sc4)*100;
	var valor4 = parseFloat(result4).toFixed(2);
	
	document.getElementById('resCalculo').value = valor4+'%';
	document.getElementById('hidResCalculo').value = valor4+'%';
	document.getElementById('resCalcGral').style.display = 'block';
}

function calculaIndicador6(){
	var tr6 = document.getElementById('tr6').value;
	var tdrc6 = document.getElementById('tdrc6').value;
	var result6 = (tr6/tdrc6)*100;
	var valor6 = parseFloat(result6).toFixed(2);
	
	document.getElementById('resCalculo').value = valor6+'%';
	document.getElementById('hidResCalculo').value = valor6+'%';
	document.getElementById('resCalcGral').style.display = 'block';
}

function calculaIndicador7(){
	var fr7 = document.getElementById('fr7').value;
	var fs7 = document.getElementById('fs7').value;
	var result7 = fs7-fr7;
	
	document.getElementById('resCalculo').value = result7+' Días';
	document.getElementById('hidResCalculo').value = result7+' Días';
	document.getElementById('resCalcGral').style.display = 'block';
}

function calculaIndicador8(){
	var tr8 = document.getElementById('tr8').value;
	var tc8 = document.getElementById('tc8').value;
	var result8 = (tr8/tc8)*100;
	var valor8 = parseFloat(result8).toFixed(2);
	
	document.getElementById('resCalculo').value = valor8+'%';
	document.getElementById('hidResCalculo').value = valor8+'%';
	document.getElementById('resCalcGral').style.display = 'block';
}

function calculaIndicador9(){
	var vt29 = document.getElementById('vt29').value;
	var vt19 = document.getElementById('vt19').value;
	var result9 = vt29-vt19/vt19;
	
	document.getElementById('resCalculo').value = result9+' Unidades de producto o servicio (piezas, toneladas, etc.)';
	document.getElementById('hidResCalculo').value = result9+' Unidades de producto o servicio (piezas, toneladas, etc.)';
	document.getElementById('resCalcGral').style.display = 'block';
}
