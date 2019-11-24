package com.telarg.security.data.entities;

import com.telarg.security.utils.Classifications;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Clasificaciones {

    @Id
    private Classifications classifications;

    private String tag;

    @OneToOne(mappedBy = "clasificaciones")
    private Historico historico;

    public Clasificaciones(){}

    public Clasificaciones(Classifications classifications) {
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