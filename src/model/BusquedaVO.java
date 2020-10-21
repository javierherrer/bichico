package model;

import java.util.Date;

public class BusquedaVO {
    private int interes;
    private Date fecha;
    private String palabra;
    private int idRegion;

    public BusquedaVO(int interes, Date fecha, String palabra, int idRegion) {
        this.interes = interes;
        this.fecha = fecha;
        this.palabra = palabra;
        this.idRegion = idRegion;
    }

    public int getInteres() {
        return interes;
    }

    public void setInteres(int interes) {
        this.interes = interes;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }
}
