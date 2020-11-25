//$(document).on('ready', funcMain);

/*function funcMain()
{
	$("#botonMensaje").on('click',enviarMensaje);
	
}
*/
function enviarMensaje(){
	var nombreUsuario = document.getElementById("nombreUsuario").value;
	var emailUsuario = document.getElementById("emailUsuario").value;
	var mensanjeUsuario = document.getElementById("mensajeUsuario").value;
  console.log("paso");
  $.post("enviarmensaje",
  {
    emisor: nombreUsuario,
    email: emailUsuario,
    contenido: mensanjeUsuario
  },null);

  document.getElementById("nombreUsuario").value = "";
  document.getElementById("emailUsuario").value = "";
  document.getElementById("mensajeUsuario").value = "";

  alert("mensaje enviado");

}