$(document).on('ready', funcMain);

function funcMain()
{
 rellenarListaComunidades();
}

function rellenarListaComunidades() {
   $.get("listarcomunidades", function(data, status){                                         //no parseo el JSOnN
        for (i = 0; i < data.length; i++) {
              var li = document.createElement("li");
              var t = document.createTextNode(data[i].nombre);
              t.Id=data[i].Id;
              li.appendChild(t);
              if (data[i] == '') {
                console.log("dato recibido vacio");
              } else {
                document.getElementById("myUL").appendChild(li);
              }
        }
                                                            
  });
  
}