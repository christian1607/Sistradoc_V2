/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celmam.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "MAESTRO_CODIFICADOR")
@NamedQueries({
    @NamedQuery(name = "MaestroCodificador.findTipoCodificador",
            query = "SELECT t FROM MaestroCodificador t WHERE t.pk.codTipoCodificador= :codificador")
})
public class MaestroCodificador implements Serializable {

    @EmbeddedId
    private MaestroCodificadorPK pk;

    @Column(name = "NOM_CODIFICADOR")
    private String nomCodificador;

    @Column(name = "IND_ACTIVO")
    private String indActivo;

    public MaestroCodificadorPK getPk() {
        return pk;
    }

    public void setPk(MaestroCodificadorPK pk) {
        this.pk = pk;
    }

    public String getNomCodificador() {
        return nomCodificador;
    }

    public void setNomCodificador(String nomCodificador) {
        this.nomCodificador = nomCodificador;
    }

    public String getIndActivo() {
        return indActivo;
    }

    public void setIndActivo(String indActivo) {
        this.indActivo = indActivo;
    }

}
