package model;

public class MensajeVO {
    private int id;
    private String emisor;
    private String contenido;
    private String email;

    public MensajeVO(int id, String emisor, String contenido, String email) {
        this.id = id;
        this.emisor = emisor;
        this.contenido = contenido;
        this.email = email;
    }
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
