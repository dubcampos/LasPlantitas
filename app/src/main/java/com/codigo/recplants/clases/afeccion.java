package com.codigo.recplants.clases;

public class afeccion {
    int id;
    String imagen_afeccion;
    public String nombre_afeccion;
    String descripcion_efeccion;
    String causa_afeccion;
    String prevencion_afeccion;

    public afeccion(int id, String imagen_afeccion, String nombre_afeccion, String descripcion_efeccion, String causa_afeccion, String prevencion_afeccion) {
        this.id = id;
        this.imagen_afeccion = imagen_afeccion;
        this.nombre_afeccion = nombre_afeccion;
        this.descripcion_efeccion = descripcion_efeccion;
        this.causa_afeccion = causa_afeccion;
        this.prevencion_afeccion = prevencion_afeccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_afeccion() {
        return nombre_afeccion;
    }

    public void setNombre_afeccion(String nombre_afeccion) {
        this.nombre_afeccion = nombre_afeccion;
    }

    public String getCausa_afeccion() {
        return causa_afeccion;
    }

    public void setCausa_afeccion(String causa_afeccion) {
        this.causa_afeccion = causa_afeccion;
    }

    public String getImagen_afeccion() {
        return imagen_afeccion;
    }

    public void setImagen_afeccion(String imagen_afeccion) {
        this.imagen_afeccion = imagen_afeccion;
    }

    public String getPrevencion_afeccion() {
        return prevencion_afeccion;
    }

    public void setPrevencion_afeccion(String prevencion_afeccion) {
        this.prevencion_afeccion = prevencion_afeccion;
    }

    public String getDescripcion_efeccion() {
        return descripcion_efeccion;
    }

    public void setDescripcion_efeccion(String descripcion_efeccion) {
        this.descripcion_efeccion = descripcion_efeccion;
    }


}
