package com.celmam.util;

/**
 *
 * @author Christian
 */
public enum TipoEstadoTramite {

    REGISTRADA("1"),
    ACEPTADA("2"),
    RECHAZADA("3"),
    OBSERVADA("4");

    private final String url;

    TipoEstadoTramite(String url) {
        this.url = url;
    }

    public String value() {
        return url;
    }

}
