/**
 * comunidadaVO.java
 *
 * Versión V.v 21-oct-2020
 * Javier Herrer Torres
 */
package model;


/**
 * comunidadVO
 *
 */
public class ComunidadVO {
    private int id;
    private String nombre;
    private float latitud;
    private float longitud;

    public ComunidadVO(int id, String nombre, float latitud, float longitud) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
}
