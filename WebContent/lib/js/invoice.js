$(document).on('ready', funcMain);

function funcMain()
{
 elementoClicado();
 newElement();                            //descomentar y crear bien
}


function elementoClicado() {
  var list = document.getElementById('myUL');
  list.addEventListener('click', function(ev) {
    var listItems = document.getElementById("myUL").getElementsByTagName('li');  //cojo li de la UL principal
    for (i = 0; i < listItems.length; i++) {
        listItems[i].className = '';          //les coloco las class a nula en caso de ponerle colocar una nueva
    }
  if (ev.target.tagName === 'LI') {           //asigno la clase cheeked a la seccionada
    ev.target.classList.toggle('checked');
    var comunidad = ev.srcElement.innerText;
    perdirRegiones(comunidad);
  
  }
  }, false);
}
function perdirRegiones(comunidad) {

  var mymap = document.getElementById("mapid");
  alert(comunidad);
   $.post("listarregiones",
                          {
                          comunidad: comunidad
                          
                          },
                          function(data, status){
                            //alert("Data: " + data + "\nStatus: " + status);
                            //alert(data);
                            var myJSON = JSON.stringify(data);
                            //alert(myJSON);
                          });
}
// Create a new list item when clicking on the "Add" button
function newElement() {
   $.get("listarcomunidades", function(data, status){
          alert(data);
          var myJSON = JSON.stringify(data);
          //alert(myJSON);
          var obj = JSON.parse(data);
                                                                      //introduci el codigo de debajo
  });
  var li = document.createElement("li");
  var inputValue = "ARAGON";
  var t = document.createTextNode(inputValue);
  li.appendChild(t);
  if (inputValue == '') {
   console.log("dato recibido vacio");
  } else {
    document.getElementById("myUL").appendChild(li);
  }
  
  for (i = 0; i < close.length; i++) {
    close[i].onclick = function() {
      var div = this.parentElement;
      div.style.display = "none";
    }
  }
}