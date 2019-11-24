package com.telarg.security.data.entities;

import com.telarg.security.utils.Classifications;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Reporte {

    @Id
    private Classifications id;

    @OneToOne
    private Clasificacion clasificacion;

    private long contador;

    private String expresiones;

    public Reporte( Clasificacion clasificacion) {
        this.id = clasificacion.getClassifications();
        this.clasificacion = clasificacion;
        this.contador = 0;
    }

    public Reporte(Clasificacion clasificacion, int contador) {
        this.clasificacion = clasificacion;
        this.contador = contador;
    }

    public Clasificacion getClasificacion() { return clasificacion; }

    public void setClasificacion(Clasificacion clasificacion) { this.clasificacion = clasificacion; }

    public long getContador() {
        return contador;
    }

    public void setContador(long contador) {
        this.contador = contador;
    }

    public String getExpresiones() { return expresiones; }

    public void setExpresiones(String expresiones) { this.expresiones = expresiones; }

}