$(document).on('ready', funcMain);


function funcMain()
{
	var modal = document.getElementById("myModal");
	modal.style.display = "none";
	$("#add_row").on('click',newRowTableWithValueButton);
	listWords();
	$("loans_table").on('click','.fa-times',deleteProduct);
	console.log($("loans_table"));
	$("body").on('click',".fa-times",deleteProduct);
	console.log($("body"));
	nombreUser();
}

function nombreUser(){
	var nombreUsuario = document.getElementById("nombreUsuario");
	var prueba = 'paco';
	console.log(prueba);
	nombreUsuario.innerHTML = '<span id= "nombreUsuario" style="color: black">'+prueba+'</span>';
}

function listWords(){


	    $.get("listarpalabras", function(data, status){
	    	
	    	//TODO comprobar con un if si el obj tiene el parametro "error" y en caso de que lo tenga hacer un
			//Document.location para redirigir a la pagina de login (que te envian en el valor de error)
			//aceptado error
			console.log(data);
      		var obj = JSON.parse(data);
      		var all = Object.keys(obj.palabras).length;
          	for (var i = 0; i < Object.keys(obj.palabras).length; i++) {
			           newRowTableWithValue(obj.palabras[i].nombre,obj.palabras[i].importancia);
			}
        });
}

function deleteProduct(){
	//Guardando la referencia del objeto presionado
  var _this = this;
  var palabra = _this.id;
   $.post("eliminarpalabra",
  {
    palabra: palabra
  },
  function(data, status){                                                 //detectar error
    
  });

	$(this).parent().parent().parent().fadeOut("slow",function(){$(this).remove();});
}



function newRowTable(){
	var name=document.getElementById("name").value;
	var importancia=document.getElementById("importancia").value;

	var name_table=document.getElementById("tabla_palabras");
    var row = name_table.insertRow(1);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);

    cell1.innerHTML = '<p id="palabra" name="palabra" class="non-margin">'+name+'</p>';
    cell2.innerHTML = '<p name="factor" class="non-margin">'+importancia+'</p>';
    cell3.innerHTML = '<span><i id ="'+name+'" class="fas fa-times"></i></span>';

 
	}

function newRowTableWithValue(name,importancia)
{
	var name_table=document.getElementById("tabla_palabras");
    var row = name_table.insertRow(1);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);

    cell1.innerHTML = '<p id="palabra" name="palabra" class="non-margin">'+name+'</p>';
    cell2.innerHTML = '<p name="importancia" class="non-margin">'+importancia+'</p>';
    cell3.innerHTML = '<span><i id ="'+name+'" class="fas fa-times"></i></span>';

  
}

function newRowTableWithValueButton(name,importancia)
{
	var nombrePalabra = document.getElementById("nombrePalabra").value;
	var importancia = document.getElementById("importancia").value;


  $.post("insertarpalabra",
  {
    nombre: nombrePalabra,
    importancia: importancia
  },
  function(data, status){                                                 //detectar error
    
  });

  newRowTableWithValue(nombrePalabra,importancia);
}


function format(input)
{
	var num = input.value.replace(/\,/g,'');
	if(!isNaN(num)){
		input.value = num;
	}
	else{ alert('Solo se permiten numeros');
		input.value = input.value.replace(/[^\d\.]*/g,'');
	}
}