package model;
/**
 * 
 * Clase AdminVO para poder trabajar con la tabla admin
 * @author 
 *
 */
public class AdminVO {
	private int id;
	private String nombre;
	private String hashedPass;

	public AdminVO(String nombre, String hashedPass) {
		this.nombre = nombre;
		this.hashedPass = hashedPass;
	}

	public AdminVO(int id, String nombre, String hashedPass) {
		this.id = id;
		this.nombre = nombre;
		this.hashedPass = hashedPass;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getHashedPass() {
		return hashedPass;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setHashedPass(String hashedPass) {
		this.hashedPass = hashedPass;
	}
}