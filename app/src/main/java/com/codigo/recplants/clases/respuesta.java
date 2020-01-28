package com.codigo.recplants.clases;

public class respuesta {
    String message;
    String descripcion;
    String causa;
    String prevencion;

    public respuesta(String message, String descripcion, String causa, String prevencion) {
        this.message = message;
        this.descripcion = descripcion;
        this.causa = causa;
        this.prevencion = prevencion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getPrevencion() {
        return prevencion;
    }

    public void setPrevencion(String prevencion) {
        this.prevencion = prevencion;
    }
}
