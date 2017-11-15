
package com.celmam.util;

/**
 *
 * @author Christian
 */
public enum TipoCodificador {

    TIPO_DOCUMENTO("TDOC"),
    TIPO_ESTADO_TRAMITE("TETRA") ;

    private final String url;

    TipoCodificador(String url) {
        this.url = url;
    }

    public String value() {
        return url;
    }

}
