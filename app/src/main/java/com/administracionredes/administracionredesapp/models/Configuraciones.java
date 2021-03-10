package com.administracionredes.administracionredesapp.models;

import java.io.Serializable;

public class Configuraciones implements Serializable
{
    private String id,lugar,nodos,switches,topologia,red,cdir,rango_host,observaciones;

    public Configuraciones(String id, String lugar, String nodos, String switches, String topologia, String red, String cdir, String rango_host, String observaciones) {
        this.id = id;
        this.lugar = lugar;
        this.nodos = nodos;
        this.switches = switches;
        this.topologia = topologia;
        this.red = red;
        this.cdir = cdir;
        this.rango_host = rango_host;
        this.observaciones = observaciones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNodos() {
        return nodos;
    }

    public void setNodos(String nodos) {
        this.nodos = nodos;
    }

    public String getSwitches() {
        return switches;
    }

    public void setSwitches(String switches) {
        this.switches = switches;
    }

    public String getTopologia() {
        return topologia;
    }

    public void setTopologia(String topologia) {
        this.topologia = topologia;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getCdir() {
        return cdir;
    }

    public void setCdir(String cdir) {
        this.cdir = cdir;
    }

    public String getRango_host() {
        return rango_host;
    }

    public void setRango_host(String rango_host) {
        this.rango_host = rango_host;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
