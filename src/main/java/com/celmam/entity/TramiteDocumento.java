/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celmam.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "TRAMITE_DOCUMENTO")
@NamedQueries({
    @NamedQuery(name = "TramiteDocumento.findTramiteByCodTramite", query = "SELECT t FROM TramiteDocumento t")
})
public class TramiteDocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_DOCUMENTO", nullable = false)
    private String idDocumento;

    @Column(name = "COD_TRAMITE")
    private String codTramite;

    @Column(name = "COD_TIPO_DOCUMENTO")
    private String codTipoDocumento;
    
    @Column(name = "NOM_DOCUMENTO")
    private String nomDocumento;
    
    

    public TramiteDocumento() {
    }

    public TramiteDocumento(String idDocumento, String codTramite, String codTipoDocumento,String nombreDocumento) {
        this.idDocumento = idDocumento;
        this.codTramite = codTramite;
        this.codTipoDocumento = codTipoDocumento;
        this.nomDocumento=nombreDocumento;
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


}
