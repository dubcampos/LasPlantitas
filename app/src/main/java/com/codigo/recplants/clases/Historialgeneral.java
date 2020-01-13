package com.codigo.recplants.clases;




public class Historialgeneral {
    public int id;
    public Usuario Usuario;
    public Diagnostico Diagnostico;
    public Cultivo Cultivo;
    public String imagen_usuarioCultivo;


    public Historialgeneral(int id, com.codigo.recplants.clases.Usuario usuario, Diagnostico diagnostico, com.codigo.recplants.clases.Cultivo cultivo, String imagen_usuarioCultivo) {
        this.id = id;
        Usuario = usuario;
        this.Diagnostico = diagnostico;
        Cultivo = cultivo;
        this.imagen_usuarioCultivo = imagen_usuarioCultivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public com.codigo.recplants.clases.Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(com.codigo.recplants.clases.Usuario usuario) {
        Usuario = usuario;
    }

    public com.codigo.recplants.clases.Diagnostico getDiagnostico() {
        return Diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.Diagnostico = diagnostico;
    }

    public com.codigo.recplants.clases.Cultivo getCultivo() {
        return Cultivo;
    }

    public void setCultivo(com.codigo.recplants.clases.Cultivo cultivo) {
        Cultivo = cultivo;
    }

    public String getImagen_usuarioCultivo() {
        return imagen_usuarioCultivo;
    }

    public void setImagen_usuarioCultivo(String imagen_usuarioCultivo) {
        this.imagen_usuarioCultivo = imagen_usuarioCultivo;
    }
}
