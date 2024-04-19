package com.example.interntsci.Models;

public class Persona {
    private String id, nombre,domicilio, telefono, plan, adeuOOb, CEO;

    public Persona() {
    }


    public Persona(String id, String nombre, String domicilio, String telefono, String plan, String adeuOOb, String CEO) {
        this.id = id;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.plan = plan;
        this.adeuOOb = adeuOOb;
        this.CEO = CEO;


    }
    public String getCEO() {
        CEO=id+" "+nombre+" "+domicilio+" "+telefono+" "+plan+" "+adeuOOb;
        return CEO;
    }

    public void setCEO(String id, String nombre, String domicilio, String telefono, String plan, String adeuOOb) {
        this.CEO = id+" "+nombre+" "+domicilio+" "+telefono+" "+plan+adeuOOb;
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getAdeuOOb() {
        return adeuOOb;
    }

    public void setAdeuOOb(String adeuOOb) {
        this.adeuOOb = adeuOOb;
    }
}
