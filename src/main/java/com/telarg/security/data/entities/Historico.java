package com.telarg.security.data.entities;

import com.telarg.security.utils.Classifications;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Historico {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Classifications classifications;

    private String tag;

    @Size(max = 150)
    private String message;

    public Historico(){}

    public Historico(Classifications classifications, @Size(max = 150) String message, String tag) {
        this.classifications = classifications;
        this.message = message;
        this.tag = tag;
    }

    public Classifications getClassifications() {
        return classifications;
    }

    public void setClassifications(Classifications classifications) {
        this.classifications = classifications;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTag() { return tag; }

    public void setTag(String tag) { this.tag = tag; }
}