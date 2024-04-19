package com.example.interntsci.Models;

import java.util.Calendar;
import java.util.PrimitiveIterator;
import java.text.SimpleDateFormat;
import java.util.Date;

public class pendiente {
    private String id;
    private String nompendiente;
    private String descripcion, monto;

    public pendiente() {
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNompendiente() {
        return nompendiente;
    }

    public void setNompendiente(String nompendiente) {
        this.nompendiente = nompendiente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}


