package com.telarg.security.data.entities;

import com.telarg.security.utils.Classifications;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reporte {

    @Id
    private Classifications classifications;

    private long contador;

    public Reporte(){ }

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

    public long getContador() {
        return contador;
    }

    public void setContador(long contador) {
        this.contador = contador;
    }

}