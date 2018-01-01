/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celmam.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Christian
 */
public class TramiteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codTramite;

    private String nomRemitente;

    private String nomDestinatario;

    private String desComentario;

    private String desAsunto;

    private String codEstado;

    private String nomEstado;
    
    private Date fecRegistro;

    private String numDocumento;

    private List<TramiteDocumentoDto> documentosAdjuntos;

    private DocumentoAdjuntoDto documentoAdjunto;

    public TramiteDto() {

        documentosAdjuntos = new ArrayList<>();
        documentoAdjunto=new DocumentoAdjuntoDto();
    }

    public TramiteDto(String codTramite, String nomRemitente, String nomDestinatario, String desComentario, String desAsunto, String codEstado, Date fecRegistro, List<TramiteDocumentoDto> documentosAdjuntos,String nomEstado) {
        this.codTramite = codTramite;
        this.nomRemitente = nomRemitente;
        this.nomDestinatario = nomDestinatario;
        this.desComentario = desComentario;
        this.desAsunto = desAsunto;
        this.codEstado = codEstado;
        this.fecRegistro = fecRegistro;
        this.documentosAdjuntos = documentosAdjuntos;
        this.nomEstado=nomEstado;
    }
    
    
    
    

    public String getCodTramite() {
        return codTramite;
    }

    public void setCodTramite(String codTramite) {
        this.codTramite = codTramite;
    }

    public String getNomRemitente() {
        return nomRemitente;
    }

    public void setNomRemitente(String nomRemitente) {
        this.nomRemitente = nomRemitente;
    }

    public String getNomDestinatario() {
        return nomDestinatario;
    }

    public void setNomDestinatario(String nomDestinatario) {
        this.nomDestinatario = nomDestinatario;
    }

    public String getDesComentario() {
        return desComentario;
    }

    public void setDesComentario(String desComentario) {
        this.desComentario = desComentario;
    }

    public String getDesAsunto() {
        return desAsunto;
    }

    public void setDesAsunto(String desAsunto) {
        this.desAsunto = desAsunto;
    }

    public String getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
    }

    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

  
    public List<TramiteDocumentoDto> getDocumentosAdjuntos() {
        return documentosAdjuntos;
    }

    public void setDocumentosAdjuntos(List<TramiteDocumentoDto> documentosAdjuntos) {
        this.documentosAdjuntos = documentosAdjuntos;
    }

    public DocumentoAdjuntoDto getDocumentoAdjunto() {
        return documentoAdjunto;
    }

    public void setDocumentoAdjunto(DocumentoAdjuntoDto documentoAdjunto) {
        this.documentoAdjunto = documentoAdjunto;
    }

    public String getNomEstado() {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.codTramite);
        hash = 53 * hash + Objects.hashCode(this.nomRemitente);
        hash = 53 * hash + Objects.hashCode(this.nomDestinatario);
        hash = 53 * hash + Objects.hashCode(this.desComentario);
        hash = 53 * hash + Objects.hashCode(this.desAsunto);
        hash = 53 * hash + Objects.hashCode(this.codEstado);
        hash = 53 * hash + Objects.hashCode(this.fecRegistro);
        hash = 53 * hash + Objects.hashCode(this.numDocumento);
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
        final TramiteDto other = (TramiteDto) obj;
        if (!Objects.equals(this.codTramite, other.codTramite)) {
            return false;
        }
        if (!Objects.equals(this.nomRemitente, other.nomRemitente)) {
            return false;
        }
        if (!Objects.equals(this.nomDestinatario, other.nomDestinatario)) {
            return false;
        }
        if (!Objects.equals(this.desComentario, other.desComentario)) {
            return false;
        }
        if (!Objects.equals(this.desAsunto, other.desAsunto)) {
            return false;
        }
        if (!Objects.equals(this.codEstado, other.codEstado)) {
            return false;
        }
        if (!Objects.equals(this.numDocumento, other.numDocumento)) {
            return false;
        }
        if (!Objects.equals(this.fecRegistro, other.fecRegistro)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TramiteDto{" + "codTramite=" + codTramite + ", nomRemitente=" + nomRemitente + ", nomDestinatario=" + nomDestinatario + ", desComentario=" + desComentario + ", desAsunto=" + desAsunto + ", codEstado=" + codEstado + ", fecRegistro=" + fecRegistro + ", numDocumento=" + numDocumento + '}';
    }

}
