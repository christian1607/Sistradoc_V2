
package com.celmam.util;

/**
 *
 * @author Christian
 */
public enum TipoDocumento {

    SOLICITUD("1"),
    OFICIO("2"),
    MEMORANDUM("3");

    private final String url;

    TipoDocumento(String url) {
        this.url = url;
    }

    public String value() {
        return url;
    }

}
