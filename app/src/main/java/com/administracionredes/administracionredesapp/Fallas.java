package com.administracionredes.administracionredesapp;

import java.io.Serializable;

public class Fallas implements Serializable {
    private String id, guia, dispositivo, tipo_de_falla, observaciones;

    public Fallas(String id, String guia, String dispositivo, String tipo_de_falla, String observaciones) {
        this.id = id;
        this.guia = guia;
        this.dispositivo = dispositivo;
        this.tipo_de_falla = tipo_de_falla;
        this.observaciones = observaciones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuia() {
        return guia;
    }

    public void setGuia(String guia) {
        this.guia = guia;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getTipo_de_falla() {
        return tipo_de_falla;
    }

    public void setTipo_de_falla(String tipo_de_falla) {
        this.tipo_de_falla = tipo_de_falla;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
