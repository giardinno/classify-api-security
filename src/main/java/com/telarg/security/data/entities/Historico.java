package com.telarg.security.data.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Historico {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @OneToOne()
    private Clasificacion clasificacion;


    @Size(max = 150)
    private String message;

    public Historico(){}

    public Historico(Clasificacion clasificacion, @Size(max = 150) String message) {
        this.clasificacion = clasificacion;
        this.message = message;
    }

    public Clasificacion getClasificacion() { return clasificacion; }

    public void setClasificacion(Clasificacion clasificacion) { this.clasificacion = clasificacion; }

    public String getMessage() { return message; }

    public void setMessage(String message) {
        this.message = message;
    }

}