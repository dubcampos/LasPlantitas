package com.codigo.recplants.clases;

public class HistorialGeneralRegistro {
    public String usuario;
    public String cultivo;
    public String imagen_usuarioCultivo;

    public HistorialGeneralRegistro(String usuario, String cultivo, String imagen_usuarioCultivo) {
        this.usuario = usuario;
        this.cultivo = cultivo;
        this.imagen_usuarioCultivo = imagen_usuarioCultivo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
    }

    public String getImagen_usuarioCultivo() {
        return imagen_usuarioCultivo;
    }

    public void setImagen_usuarioCultivo(String imagen_usuarioCultivo) {
        this.imagen_usuarioCultivo = imagen_usuarioCultivo;
    }
}
