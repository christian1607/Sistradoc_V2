/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celmam.dto;

import java.io.Serializable;

/**
 *
 * @author Christian
 */
public class TramiteDocumentoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idDocumento;

    private String nomDocumento;

    private String codTramite;

    private String codTipoDocumento;
    
    private String nomTipoDocumento;

    public TramiteDocumentoDto() {
    }

    public TramiteDocumentoDto(String idDocumento, String codTramite, String codTipoDocumento, String nomDocumento) {
        this.idDocumento = idDocumento;
        this.codTramite = codTramite;
        this.codTipoDocumento = codTipoDocumento;
        this.nomDocumento = nomDocumento;
    }

    public TramiteDocumentoDto(String idDocumento, String nomDocumento, String codTramite, String codTipoDocumento, String nomTipoDocumento) {
        this.idDocumento = idDocumento;
        this.nomDocumento = nomDocumento;
        this.codTramite = codTramite;
        this.codTipoDocumento = codTipoDocumento;
        this.nomTipoDocumento = nomTipoDocumento;
    }
    
    

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getCodTramite() {
        return codTramite;
    }

    public void setCodTramite(String codTramite) {
        this.codTramite = codTramite;
    }

    public String getCodTipoDocumento() {
        return codTipoDocumento;
    }

    public void setCodTipoDocumento(String codTipoDocumento) {
        this.codTipoDocumento = codTipoDocumento;
    }

    public String getNomDocumento() {
        return nomDocumento;
    }

    public void setNomDocumento(String nomDocumento) {
        this.nomDocumento = nomDocumento;
    }

    public String getNomTipoDocumento() {
        return nomTipoDocumento;
    }

    public void setNomTipoDocumento(String nomTipoDocumento) {
        this.nomTipoDocumento = nomTipoDocumento;
    }
    
    

}
