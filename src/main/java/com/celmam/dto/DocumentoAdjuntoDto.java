
package com.celmam.dto;

import java.io.Serializable;

/**
 *
 * @author Christian
 */
public class DocumentoAdjuntoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nomDocumento;

    private String codTipoDocumento;

    public DocumentoAdjuntoDto() {
    }

    public DocumentoAdjuntoDto(String nomDocumento, String codTipoDocumento) {
        this.nomDocumento = nomDocumento;
        this.codTipoDocumento = codTipoDocumento;
    }

    public String getNomDocumento() {
        return nomDocumento;
    }

    public void setNomDocumento(String nomDocumento) {
        this.nomDocumento = nomDocumento;
    }

    public String getCodTipoDocumento() {
        return codTipoDocumento;
    }

    public void setCodTipoDocumento(String codTipoDocumento) {
        this.codTipoDocumento = codTipoDocumento;
    }

}
