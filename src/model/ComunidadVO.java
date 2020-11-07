/**
 * comunidadaVO.java
 *
 * Versión V.v 21-oct-2020
 * Javier Herrer Torres
 */
package model;


import org.json.simple.JSONObject;

import java.util.List;

/**
 * comunidadVO
 *
 */
public class ComunidadVO {
    private String nombre;
    private List<RegionVO> regiones;
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

    public List<RegionVO> getRegiones(){
        return regiones;
    }

    public void setRegiones(List<RegionVO> regiones){
        this.regiones = regiones;
    }

    /**
     * Returns de JSON representation of the region
     * @return
     */
    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
//            obj.put();
        return obj;
    }
}
