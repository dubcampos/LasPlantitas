package com.codigo.recplants.clases;

public class Historialgeneral {
    public int id;
    //@JsonProperty("Usuario")
    public Usuario usuario;
    //@JsonProperty("Diagnostico")
    //public object diagnostico;
    //@JsonProperty("Cultivo")
    public Cultivo cultivo;
    public String imagen_usuarioCultivo;


    public Historialgeneral(int id, Usuario usuario, Cultivo cultivo, String imagen_usuarioCultivo) {
        this.id = id;
        this.usuario = usuario;
        this.cultivo = cultivo;
        this.imagen_usuarioCultivo = imagen_usuarioCultivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public void setCultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
    }

    public String getImagen_usuarioCultivo() {
        return imagen_usuarioCultivo;
    }

    public void setImagen_usuarioCultivo(String imagen_usuarioCultivo) {
        this.imagen_usuarioCultivo = imagen_usuarioCultivo;
    }
}
