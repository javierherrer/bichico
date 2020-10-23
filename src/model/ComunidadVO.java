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
    private String nombre;
    private float latitud;
    private float longitud;

    public ComunidadVO(String nombre, float latitud, float longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
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
