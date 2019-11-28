package com.codigo.recplants.clases;

public class Usuario {
    public int id;
    public String correo_usuario;
    public String contrasenia_usuario;
    public String nombre_usuario;
    public String telefono_usuario;

    public Usuario(int id, String correo_usuario, String contrasenia_usuario, String nombre_usuario, String telefono_usuario) {
        this.id = id;
        this.correo_usuario = correo_usuario;
        this.contrasenia_usuario = contrasenia_usuario;
        this.nombre_usuario = nombre_usuario;
        this.telefono_usuario = telefono_usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
    }

    public String getContrasenia_usuario() {
        return contrasenia_usuario;
    }

    public void setContrasenia_usuario(String contrasenia_usuario) {
        this.contrasenia_usuario = contrasenia_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getTelefono_usuario() {
        return telefono_usuario;
    }

    public void setTelefono_usuario(String telefono_usuario) {
        this.telefono_usuario = telefono_usuario;
    }
}