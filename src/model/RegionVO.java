package model;

import org.json.simple.JSONObject;

/**
 * Clase RegionVO para poder trabajar con la tabla regiones
 * 
 * @author
 *
 */
public class RegionVO {
	private int id;
	private int habitantes;
	private String nombre;
	private String comunidad;
	private float latitud;
	private float longitud;

	public RegionVO(int id, int habitantes, String nombre, String comunidad, float latitud, float longitud) {
		this.id = id;
		this.habitantes = habitantes;
		this.nombre = nombre;
		this.comunidad = comunidad;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	/**
	 * Constructor de RegionVO
	 * 
	 * @param nombre
	 * @param latitud
	 * @param longitud
	 */
	public RegionVO(String nombre, float latitud, float longitud) {
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public String getComunidad() {
		return comunidad;
	}

	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(int habitantes) {
		this.habitantes = habitantes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	@Override
	public String toString() {
		return "RegionVO [id=" + id + ", habitantes=" + habitantes + ", nombre=" + nombre + ", comunidad=" + comunidad
				+ ", latitud=" + latitud + ", longitud=" + longitud + "]";
	}

	/**
	 * Returns de JSON representation of the region
	 * 
	 * @return
	 */
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("habitantes", habitantes);
		obj.put("nombre", nombre);
		obj.put("comunidad", comunidad);
		obj.put("latitud", latitud);
		obj.put("longitud", longitud);
		return obj;
	}
}
