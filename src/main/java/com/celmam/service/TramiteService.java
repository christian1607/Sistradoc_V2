package com.celmam.service;

import com.celmam.dto.TramiteDto;
import com.celmam.dto.VwConsultaTramiteDto;
import com.celmam.exception.TramiteServiceException;
import java.util.List;

/**
 *
 * @author Christian
 */
public interface TramiteService {

    public TramiteDto obtenerTramitePorCodigoTramite(String codTramite) throws TramiteServiceException;

    public void registrarTramite(TramiteDto tramite) throws TramiteServiceException;

    public String obtenerCodigoTramite() throws TramiteServiceException;
    
    public List<VwConsultaTramiteDto> consultarTramites(VwConsultaTramiteDto filtros) throws TramiteServiceException;
    
    public void actualizarEstadoTramite(String codEstado,String codTramite) throws TramiteServiceException;
    
}
