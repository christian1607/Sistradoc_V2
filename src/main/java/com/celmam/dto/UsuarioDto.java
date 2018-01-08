/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celmam.dto;

import java.util.List;

/**
 *
 * @author Christian Altamirano <caltamirano at crossnet.ws>
 */
public class UsuarioDto {

    private String usuario;
    private boolean isHabilitado;
    private List<RolDto> roles;
    private AdministradorDto administrador;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<RolDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RolDto> roles) {
        this.roles = roles;
    }

    public AdministradorDto getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorDto administrador) {
        this.administrador = administrador;
    }

    public boolean isIsHabilitado() {
        return isHabilitado;
    }

    public void setIsHabilitado(boolean isHabilitado) {
        this.isHabilitado = isHabilitado;
    }

}
