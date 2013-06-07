function ayudasHelp(actual){
	document.getElementById("ayudasDisplay"+actual).style.display='block';
}
function ayudasHelpBlo(actual){	
	document.getElementById("ayudasDisplay"+actual).style.display='none';
}
function ayudasHelpChec(actual,cam){
	if(document.getElementById(cam).checked){
		document.getElementById("ayudasDisplay"+actual).style.display='block';
	}else{
		document.getElementById("ayudasDisplay"+actual).style.display='none';
	}
}