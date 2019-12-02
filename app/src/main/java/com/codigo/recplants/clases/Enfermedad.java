package com.codigo.recplants.clases;

public class Enfermedad {
    int id;
    public String nombre_afeccion;
    String causa_afeccion;

    public Enfermedad(int id, String nombre_afeccion, String causa_afeccion) {
        this.id = id;
        this.nombre_afeccion = nombre_afeccion;
        this.causa_afeccion = causa_afeccion;
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

    @Override
    public String toString() {
        return "Enfermedad{" +
                "id=" + id +
                ", nombre_afeccion='" + nombre_afeccion + '\'' +
                ", causa_afeccion='" + causa_afeccion + '\'' +
                '}';
    }
}
