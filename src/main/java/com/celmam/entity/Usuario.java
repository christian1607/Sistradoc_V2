/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celmam.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "USUARIO")
@NamedQueries({
    @NamedQuery(name = "Usuario.finByUsernameAndPassword",
            query = "SELECT u FROM Usuario u WHERE u.usuario=:usuario AND u.password=:password")
})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USERNAME", updatable = false, nullable = false)
    @Size(max = 100)
    private String usuario;

    @Column(name = "PASSWORD", nullable = false)
    @NotNull
    @Size(max = 50)
    private String password;

    @Column(name = "ENABLED", nullable = false)
    @NotNull
    private Integer isHabilitado;

    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    private List<UsuarioRol> roles;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsHabilitado() {
        return isHabilitado;
    }

    public void setIsHabilitado(Integer isHabilitado) {
        this.isHabilitado = isHabilitado;
    }

    public List<UsuarioRol> getRoles() {
        return roles;
    }

    public void setRoles(List<UsuarioRol> roles) {
        this.roles = roles;
    }
    
    

}
