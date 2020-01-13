package com.codigo.recplants.clases;

public class Diagnostico {
    public int id;
    public afeccion afeccion;
    public String fecha_diagnostico;

    public Diagnostico(int id, afeccion afeccion, String fecha_diagnostico) {
        this.id = id;
        this.afeccion = afeccion;
        this.fecha_diagnostico = fecha_diagnostico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public afeccion getAfeccion() {
        return afeccion;
    }

    public void setAfeccion(afeccion afeccion) {
        this.afeccion = afeccion;
    }

    public String getFecha_diagnostico() {
        return fecha_diagnostico;
    }

    public void setFecha_diagnostico(String fecha_diagnostico) {
        this.fecha_diagnostico = fecha_diagnostico;
    }
}
