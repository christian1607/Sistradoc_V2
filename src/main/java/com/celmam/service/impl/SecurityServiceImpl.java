/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celmam.service.impl;

import com.celmam.dao.SecurityDao;
import com.celmam.dto.RolDto;
import com.celmam.dto.UsuarioDto;
import com.celmam.entity.Usuario;
import com.celmam.entity.UsuarioRol;
import com.celmam.exception.TramiteServiceException;
import com.celmam.service.SecurityService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Christian Altamirano <caltamirano at crossnet.ws>
 */
@Service("SecurityService")
@Transactional(propagation = Propagation.REQUIRED)
public class SecurityServiceImpl implements SecurityService {

    private final Logger LOG = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    SecurityDao securityDao;

    @Override
    public UsuarioDto login(String username, String password) throws TramiteServiceException {
        try {
            Usuario usuario = securityDao.findByUsernameAndPassword(username, password);

            if (null != usuario) {
                UsuarioDto usuarioDto = new UsuarioDto();
                usuarioDto.setUsuario(usuario.getUsuario());
                usuarioDto.setIsHabilitado((usuario.getIsHabilitado()) == HABILITADO);
                usuarioDto.setRoles(toListRoles(usuario.getRoles()));

                return usuarioDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            LOG.error("Se produjo un error al autenticar usuario", e);
            throw new TramiteServiceException("Se produjo un error al autenticar usuario");
        }

    }
    private static final int HABILITADO = 1;

    private List<RolDto> toListRoles(List<UsuarioRol> roles) {

        if (null != roles && !roles.isEmpty()) {
            return roles.stream().map(p -> {

                RolDto rolDto = new RolDto();
                rolDto.setRol(p.getRol());

                return rolDto;
            }).collect(Collectors.toList());
        }
        return null;
    }

}
