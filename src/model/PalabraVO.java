/**
 * palabraVO.java
 *
 * Versi�n V.v 21-oct-2020
 * Javier Herrer Torres
 */
package model;


/**
 * palabraVO
 *
 */
public class PalabraVO {
    private String palabra;
    private float importancia;

    public PalabraVO(String palabra, float importancia) {
        this.palabra = palabra;
        this.importancia = importancia;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public float getImportancia() {
        return importancia;
    }

    public void setImportancia(float importancia) {
        this.importancia = importancia;
    }
}