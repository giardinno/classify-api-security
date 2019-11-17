package com.telarg.security.utils;

public enum Classifications {
    FALTA_DE_SERVICIO("Falta de Servicio"),
    COBROS_INDEBIDOS("Cobros indebidos"),
    PROBLEMAS_DE_FACTURACIÓN("Problemas de facturación"),
    QUEJAS("Quejas"),
    RECONOCIMIENTO("Reconocimiento"),
    DESCONOCIDO("Desconocido");

    private final String value;

    Classifications(String v){
        value = v;
    }

    public String value() {
        return value;
    }

    public static Classifications fromValue(String v) {
        for (Classifications c: Classifications.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return null;
    }

}