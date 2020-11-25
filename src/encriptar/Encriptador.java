package encriptar;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * Ejemplo de encriptado y desencriptado con algoritmo AES.
 * Se apoya en RSAAsymetricCrypto.java para salvar en fichero
 * o recuperar la clave de encriptación.
 * 
 * @author Chuidiang
 *
 */
public class Encriptador {
		
 
   public static String encriptar(String cadena) {
	   byte[] encriptado = null;
	   try {
		// Generamos una clave de 128 bits adecuada para AES
		      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		      keyGenerator.init(128);
		      Key key = keyGenerator.generateKey();
		      // Alternativamente, una clave que queramos que tenga al menos 16 bytes
		      // y nos quedamos con los bytes 0 a 15
		      key = new SecretKeySpec("asdf2assdsaaadf3rdsaa".getBytes(),  0, 16, "AES");
		      // Se obtiene un cifrador AES
		      Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
	
		// Se inicializa para encriptacion y se encripta el texto,
		      // que debemos pasar como bytes.
		      aes.init(Cipher.ENCRYPT_MODE, key);
		      encriptado = aes.doFinal(cadena.getBytes());
	   }catch (Exception e) {
		e.printStackTrace();
	}
	      // Se escribe byte a byte en hexadecimal el texto
	      // encriptado para ver su pinta.
	   	String textoEncriptado = "";
	   	  for (byte b : encriptado) {
	   		textoEncriptado = textoEncriptado + Integer.toHexString(0xFF & b);
	      }
	   return textoEncriptado;
	}
}