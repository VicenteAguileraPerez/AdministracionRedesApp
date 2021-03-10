package com.administracionredes.administracionredesapp;

import java.io.Serializable;

public class Inventario implements Serializable {
    private String id, tipo, nombre_dispositivo, observaciones, status;

    public Inventario(String id, String tipo, String nombre_dispositivo, String observaciones, String status) {
        this.id = id;
        this.tipo = tipo;
        this.nombre_dispositivo = nombre_dispositivo;
        this.observaciones = observaciones;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre_dispositivo() {
        return nombre_dispositivo;
    }

    public void setNombre_dispositivo(String nombre_dispositivo) {
        this.nombre_dispositivo = nombre_dispositivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
