/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celmam.dto;

/**
 *
 * @author Christian Altamirano <caltamirano at crossnet.ws>
 */
public class RolDto {

    private String rol;
    private String username;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "RolDto{" + "rol=" + rol + ", username=" + username + '}';
    }

    
}
