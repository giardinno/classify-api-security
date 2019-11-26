package com.telarg.security.data.entities;

import com.telarg.security.utils.Classifications;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Clasificacion {

    @Id
    private Classifications classifications;

    private String tag;

    @OneToMany(mappedBy="clasificacion")
    private Set<Historico> historico;

    public Clasificacion(){}

    public Clasificacion(Classifications classifications) {
        this.classifications = classifications;
        this.tag = classifications.value();
    }

    public Classifications getClassifications() {
        return classifications;
    }

    public void setClassifications(Classifications classifications) {
        this.classifications = classifications;
    }

    public String getTag() { return tag; }

    public void setTag(String tag) { this.tag = tag; }

}