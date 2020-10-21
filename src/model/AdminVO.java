package model;

public class AdminVO{
    int id;
    String nombre;
    String hashedPass;

    public AdminVO(int id, String nombre, String hashedPass){
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