package com.telarg.security.data.vo;

import com.telarg.security.data.entities.MensajesDesconocidos;

public class DesconocidosResponse {

    private Iterable<MensajesDesconocidos> desconocidosList;

    public DesconocidosResponse(){ }

    public DesconocidosResponse(Iterable<MensajesDesconocidos> desconocidosList){
        this.desconocidosList = desconocidosList;
    }

    public Iterable<MensajesDesconocidos> getDesconocidosList() {
        return desconocidosList;
    }

    public void setDesconocidosList(Iterable<MensajesDesconocidos> desconocidosList) {
        this.desconocidosList = desconocidosList;
    }

}