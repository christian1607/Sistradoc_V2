/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celmam.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Christian
 */
@Embeddable
public class MaestroCodificadorPK implements Serializable {

    @Column(name = "COD_TIPO_CODIFICADOR")
    private String codTipoCodificador;

    @Column(name = "COD_CODIFICADOR")
    private String codCodificador;

    public String getCodTipoCodificador() {
        return codTipoCodificador;
    }

    public void setCodTipoCodificador(String codTipoCodificador) {
        this.codTipoCodificador = codTipoCodificador;
    }

    public String getCodCodificador() {
        return codCodificador;
    }

    public void setCodCodificador(String codCodificador) {
        this.codCodificador = codCodificador;
    }

}
