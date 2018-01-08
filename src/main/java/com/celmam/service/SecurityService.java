package com.celmam.service;

import com.celmam.dto.UsuarioDto;
import com.celmam.exception.TramiteServiceException;

/**
 *
 * @author Christian
 */
public interface SecurityService {

    public UsuarioDto login(String username, String password) throws TramiteServiceException;

  

}
