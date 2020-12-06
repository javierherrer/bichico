package encriptar;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Clase para encriptar cadena con AES
 */
public class Encriptador {

	private final static String alg = "AES";
	private final static String Ci = "AES/CBC/PKCS5Padding";
	private final static String key = "96SD61A790ITB2L4";
	private static String iv = "0123456789ABCDEF";

	/**
	 * Recibe una cadena y es encriptada con AES
	 * @param cadena
	 * @return String de cadena encriptada
	 */
	public static String encriptar(String cadena) {

		byte[] encrypted = null;
		try {
			Cipher cipher = Cipher.getInstance(Ci);
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
			encrypted = cipher.doFinal(cadena.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(Base64.encodeBase64(encrypted));
	}
}