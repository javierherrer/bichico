$(document).on('ready', funcMain);


function funcMain()
{
	$("#btn-abrir-popup").on('click',activar);
	$("#salir").on('click',desactivar);
	
}

function activar(){
	var modal = document.getElementById("myModal");
	 modal.style.display = "block";
}

function desactivar(){
	console.log("paso");
	var modal = document.getElementById("myModal");
	modal.style.display = "none";
}