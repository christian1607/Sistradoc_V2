package com.celmam.service;

import com.celmam.dto.MaestroCodificadorDto;
import com.celmam.exception.TramiteServiceException;
import java.util.List;

/**
 *
 * @author Christian
 */
public interface CodificadorService {

    public List<MaestroCodificadorDto> listarTipoDocumentos() throws TramiteServiceException;

    public List<MaestroCodificadorDto> listarTipoEstadosTramite() throws TramiteServiceException;
    
    public MaestroCodificadorDto obtenerCodificador(String tipoCodificador,String codCodificador) throws TramiteServiceException;
}
