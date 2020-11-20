function acceso() {
	var name = document.getElementById("nombre").value;
	var pass = document.getElementById("pass").value;						//comprobar tambien al arranque

	console.log(name);
	console.log(pass);

	  $.post("login",
  {
    usuario: name,
    password: pass
  },
  function(data, status){                                                 //detectar error
    var error = data.error;
    if (error == "") {
    	document.location = "adminpanel";
    }else{
    	 alert(error);
    }
   
  });
}

