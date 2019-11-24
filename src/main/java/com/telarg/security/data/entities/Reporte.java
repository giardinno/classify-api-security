package com.telarg.security.data.entities;

import com.telarg.security.utils.Classifications;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reporte {

    @Id
    private Classifications classifications;

    private long contador;

    private String expresiones;

    public Reporte(){ }

    public Reporte(Classifications classifications, String expresiones, int contador) {
        this.classifications = classifications;
        this.expresiones = expresiones;
        this.contador = contador;
    }

    public Reporte(Classifications classifications, int contador) {
        this.classifications = classifications;
        this.contador = contador;
    }

    public Classifications getClassifications() {
        return classifications;
    }

    public void setClassifications(Classifications classifications) {
        this.classifications = classifications;
    }

    public String getExpresiones() { return expresiones; }

    public long getContador() {
        return contador;
    }

    public void setContador(long contador) {
        this.contador = contador;
    }

    public void setExpresiones(String expresiones) { this.expresiones = expresiones; }

}