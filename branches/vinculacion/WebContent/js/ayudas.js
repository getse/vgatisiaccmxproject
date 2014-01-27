function ayudasHelp(a) {
	document.getElementById("ayudasDisplay" + a).style.display = 'block'
}
function ayudasHelpBlo(a) {
	document.getElementById("ayudasDisplay" + a).style.display = 'none'
}
function ayudasHelpChec(a, b) {
	if (document.getElementById(b).checked) {
		document.getElementById("ayudasDisplay" + a).style.display = 'block'
	} else {
		document.getElementById("ayudasDisplay" + a).style.display = 'none'
	}
}