package com.telarg.security.data.entities;

import com.telarg.security.utils.Classifications;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class MensajesDesconocidos {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @OneToOne()
    private Clasificaciones clasificaciones;

    @Size(max = 150)
    private String message;

    public MensajesDesconocidos(){}

    public MensajesDesconocidos(@Size(max = 150) String message) {
        this.clasificaciones = new Clasificaciones(Classifications.DESCONOCIDO);
        this.message = message;
    }

    public Clasificaciones getClasificaciones() { return clasificaciones; }

    public void setClasificaciones(Clasificaciones clasificaciones) { this.clasificaciones = clasificaciones; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}