package com.codigo.recplants.clases;

public class Cultivo {
    public int id;
    public String nombre_cultivo;
    public String guia_cultivo;
    public String imagen_cultivo;

    public Cultivo(int id, String nombre_cultivo, String guia_cultivo, String imagen_cultivo) {
        this.id = id;
        this.nombre_cultivo = nombre_cultivo;
        this.guia_cultivo = guia_cultivo;
        this.imagen_cultivo = imagen_cultivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_cultivo() {
        return nombre_cultivo;
    }

    public void setNombre_cultivo(String nombre_cultivo) {
        this.nombre_cultivo = nombre_cultivo;
    }

    public String getGuia_cultivo() {
        return guia_cultivo;
    }

    public void setGuia_cultivo(String guia_cultivo) {
        this.guia_cultivo = guia_cultivo;
    }

    public String getImagen_cultivo() {
        return imagen_cultivo;
    }

    public void setImagen_cultivo(String imagen_cultivo) {
        this.imagen_cultivo = imagen_cultivo;
    }


}
