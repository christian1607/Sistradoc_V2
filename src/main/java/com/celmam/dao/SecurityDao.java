/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celmam.dao;

import com.celmam.entity.Usuario;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Christian Altamirano <caltamirano at crossnet.ws>
 */
@Repository("securityDao")
public class SecurityDao {

    @Autowired
    BaseDaoImpl<String, Usuario> usuarioDao;

    public Usuario findByUsernameAndPassword(String username, String password) {

        HashMap<String, Object> parametros = new HashMap();
        parametros.put("usuario", username);
        parametros.put("password", password);

        return usuarioDao.findByObjectValues("Usuario.finByUsernameAndPassword", parametros);
    }

}
