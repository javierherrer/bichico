$(document).on('ready', funcMain);

function funcMain()
{
	$("#botonMensaje").on('click',enviarMensaje);
	
}

function enviarMensaje(){
	var nombreUsuario = document.getElementById("nombreUsuario").value;
	var emailUsuario = document.getElementById("emailUsuario").value;
	var mensanjeUsuario = document.getElementById("mensajeUsuario").value;

  $.post("enviarMensajeServlet",
  {
    emisor: nombreUsuario,
    email: emailUsuario,
    contenido: mensanjeUsuario
  },null);

}