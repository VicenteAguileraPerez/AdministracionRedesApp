package com.administracionredes.administracionredesapp.models;

import java.io.Serializable;

public class Localizacion implements Serializable
{
    private String id, nombre, tipo, localizacion, estatus, observaciones;

    public Localizacion(String id, String nombre, String tipo, String localizacion, String estatus, String observaciones) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.localizacion = localizacion;
        this.estatus = estatus;
        this.observaciones = observaciones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
