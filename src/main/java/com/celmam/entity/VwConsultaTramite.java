/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celmam.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "VW_CONSULTA_TRAMITE")
@NamedQueries({
    @NamedQuery(name = "VwConsultaTramite.findFiltro", query = "SELECT vw FROM VwConsultaTramite vw WHERE "
            + "( :codTramite IS NULL OR (:codTramite IS NOT NULL AND vw.codTramite= :codTramite)) AND "
            + "( :nomDestinatario IS NULL OR (:nomDestinatario IS NOT NULL AND vw.nomDestinatario= :nomDestinatario)) AND "
            + "( :nomRemitente IS NULL OR (:nomRemitente IS NOT NULL AND vw.nomRemitente= :nomRemitente)) AND "
            + "( :codEstado IS NULL OR (:codEstado IS NOT NULL AND vw.codEstado= :codEstado)) AND "
            + "( :fechaDesde IS NULL OR (:fechaDesde IS NOT NULL AND vw.fecRegistro >= :fechaDesde)) AND "
            + "( :fechaHasta IS NULL OR (:fechaHasta IS NOT NULL AND vw.fecRegistro <= :fechaHasta))")
})
public class VwConsultaTramite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "COD_TRAMITE")
    private String codTramite;

    @Column(name = "DES_ASUNTO")
    private String desAsunto;

    @Column(name = "FEC_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRegistro;

    @Column(name = "NOM_DESTINATARIO")
    private String nomDestinatario;

    @Column(name = "NOM_REMITENTE")
    private String nomRemitente;

    @Column(name = "COD_ESTADO")
    private String codEstado;

    @Column(name = "NOM_ESTADO")
    private String nomEstado;

    public String getCodTramite() {
        return codTramite;
    }

    public void setCodTramite(String codTramite) {
        this.codTramite = codTramite;
    }

    public String getDesAsunto() {
        return desAsunto;
    }

    public void setDesAsunto(String desAsunto) {
        this.desAsunto = desAsunto;
    }

    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public String getNomDestinatario() {
        return nomDestinatario;
    }

    public void setNomDestinatario(String nomDestinatario) {
        this.nomDestinatario = nomDestinatario;
    }

    public String getNomRemitente() {
        return nomRemitente;
    }

    public void setNomRemitente(String nomRemitente) {
        this.nomRemitente = nomRemitente;
    }

    public String getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
    }

    public String getNomEstado() {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

}
