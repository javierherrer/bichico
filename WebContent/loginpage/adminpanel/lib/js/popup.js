$(document).on('ready', funcMain);


function funcMain()
{
	$("#btn-abrir-popup").on('click',activar);
	$("#salir").on('click',desactivar);
	listMensajes();
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

function listMensajes(){
	
	    $.get("listarmensajes", function(data, status){
	    	
	    	//TODO comprobar con un if si el obj tiene el parametro "error" y en caso de que lo tenga hacer un
			//Document.location para redirigir a la pagina de login (que te envian en el valor de error)
			//aceptado error
			console.log(data);
      		var obj = JSON.parse(data);
      		var all = Object.keys(obj.mensajes).length;
          	for (var i = 0; i < all; i++) {
			           newRowTableMWithValue(obj.mensajes[i].emisor,obj.mensajes[i].email,obj.mensajes[i].contenido);
			}
        });
}


function newRowTableMWithValue(nombre, email, contido)
{
	var name_table=document.getElementById("tablaMensajes");
    var row = name_table.insertRow(1);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);

    cell1.innerHTML = '<p id="mensaje" name="palabra" class="non-margin mensaje">'+nombre+'</p>';
    cell2.innerHTML = '<p name="email" class="non-margin mensaje">'+email+'</p>';
    cell3.innerHTML = '<p name="contenido" class="non-margin mensaje">'+contido+'</p>';
    cell4.innerHTML = '<span><i id ="'+name+'" class="fas fa-trash-alt mensaje"></i></span>';
    

}