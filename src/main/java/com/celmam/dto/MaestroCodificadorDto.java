package com.celmam.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Christian
 */
public class MaestroCodificadorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codTipoCodificador;

    private String codCodificador;

    private String nomCodificador;

    private String indActivo;

    public MaestroCodificadorDto(String codTipoCodificador, String codCodificador, String nomCodificador, String indActivo) {
        this.codTipoCodificador = codTipoCodificador;
        this.codCodificador = codCodificador;
        this.nomCodificador = nomCodificador;
        this.indActivo = indActivo;
    }

    public MaestroCodificadorDto(String nomCodificador) {
        this.nomCodificador = nomCodificador;
    }

    public MaestroCodificadorDto() {
    }

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

    @Override
    public String toString() {
        return "MaestroCodificadorDto{" + "codTipoCodificador=" + codTipoCodificador + ", codCodificador=" + codCodificador + ", nomCodificador=" + nomCodificador + ", indActivo=" + indActivo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.codTipoCodificador);
        hash = 41 * hash + Objects.hashCode(this.codCodificador);
        hash = 41 * hash + Objects.hashCode(this.nomCodificador);
        hash = 41 * hash + Objects.hashCode(this.indActivo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MaestroCodificadorDto other = (MaestroCodificadorDto) obj;
        if (!Objects.equals(this.codTipoCodificador, other.codTipoCodificador)) {
            return false;
        }
        if (!Objects.equals(this.codCodificador, other.codCodificador)) {
            return false;
        }
        if (!Objects.equals(this.indActivo, other.indActivo)) {
            return false;
        }
        return true;
    }

}
