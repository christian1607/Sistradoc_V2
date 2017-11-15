/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celmam.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "TRAMITE")
@NamedQueries({
    @NamedQuery(name = "Tramite.findTramiteByCodTramite", query = "SELECT t FROM Tramite t")
})
public class Tramite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "COD_TRAMITE", updatable = false, nullable = false)
    private String codTramite;

    @Column(name = "NOM_REMITENTE")
    private String nomRemitente;

    @Column(name = "NOM_DESTINATARIO")
    private String nomDestinatario;

    @Column(name = "DES_COMENTARIO")
    private String desComentario;

    @Column(name = "DES_ASUNTO")
    private String desAsunto;

    @Column(name = "COD_ESTADO")
    private String codEstado;

    @Column(name = "FEC_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fecRegistro;

    @OneToMany(orphanRemoval = true,mappedBy = "codTramite",cascade = CascadeType.ALL)
    private List<TramiteDocumento> documentos;
    

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

    public List<TramiteDocumento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<TramiteDocumento> documentos) {
        this.documentos = documentos;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.codTramite);
        hash = 13 * hash + Objects.hashCode(this.nomRemitente);
        hash = 13 * hash + Objects.hashCode(this.nomDestinatario);
        hash = 13 * hash + Objects.hashCode(this.desComentario);
        hash = 13 * hash + Objects.hashCode(this.desAsunto);
        hash = 13 * hash + Objects.hashCode(this.codEstado);
        hash = 13 * hash + Objects.hashCode(this.fecRegistro);
      
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
        final Tramite other = (Tramite) obj;
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
        if (!Objects.equals(this.fecRegistro, other.fecRegistro)) {
            return false;
        }
       
        return true;
    }

   
}
