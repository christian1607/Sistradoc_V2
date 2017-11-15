package com.celmam.dto;

import java.io.Serializable;
import java.util.Date;

public class VwConsultaTramiteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codTramite;

    private String desAsunto;

    private Date fecRegistro;

    private String nomDestinatario;

    private String nomRemitente;

    private String codEstado;

    private String nomEstado;

    private Date fechaDesde;

    private Date fechaHasta;

    public VwConsultaTramiteDto() {
    }

    public VwConsultaTramiteDto(String codTramite, String desAsunto, Date fecRegistro, String nomDestinatario, String nomRemitente, String codEstado, String nomEstado) {
        this.codTramite = codTramite;
        this.desAsunto = desAsunto;
        this.fecRegistro = fecRegistro;
        this.nomDestinatario = nomDestinatario;
        this.nomRemitente = nomRemitente;
        this.codEstado = codEstado;
        this.nomEstado = nomEstado;
    }

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

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

}
