package model;

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
}
