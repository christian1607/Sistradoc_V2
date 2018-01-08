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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "USUARIO_ROL")
@NamedQueries({
    @NamedQuery(name = "UsuarioRol.finByUsernameAndPassword",    query = "SELECT u FROM UsuarioRol u")
})
public class UsuarioRol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USUARIO_ROL_ID", updatable = false, nullable = false)
    private long id;

    @Column(name = "ROLE", nullable = false)
    @NotNull
    private String rol;

    @ManyToOne
    @JoinColumn(name = "USERNAME")
    private Usuario usuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

}
