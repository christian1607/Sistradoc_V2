package com.celmam.bean;

import com.celmam.dto.MaestroCodificadorDto;
import com.celmam.dto.VwConsultaTramiteDto;
import com.celmam.service.ServiceFactory;
import com.celmam.service.TramiteService;
import com.celmam.exception.TramiteServiceException;
import com.celmam.service.CodificadorService;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class ConsultaTramiteBean implements Serializable {

    final Logger logger = LoggerFactory.getLogger(ConsultaTramiteBean.class);
    private static final long serialVersionUID = 1L;

    private List<MaestroCodificadorDto> listaTipoDocumentos;
    private List<MaestroCodificadorDto> listaTipoEstadosTramite;
    private List<VwConsultaTramiteDto> resultadosConsultaTramite;
    private VwConsultaTramiteDto fitroBusqueda;

    private final TramiteService tramiteService = ServiceFactory.getTramiteService();
    private final CodificadorService codificadorService = ServiceFactory.getCodificadorService();

    @PostConstruct
    public void init() {

        try {
            listaTipoDocumentos = codificadorService.listarTipoDocumentos();
            listaTipoEstadosTramite = codificadorService.listarTipoEstadosTramite();
            fitroBusqueda=new VwConsultaTramiteDto();
            
            
        } catch (TramiteServiceException ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }

    public List<MaestroCodificadorDto> getListaTipoDocumentos() {
        return listaTipoDocumentos;
    }

    public void setListaTipoDocumentos(List<MaestroCodificadorDto> listaTipoDocumentos) {
        this.listaTipoDocumentos = listaTipoDocumentos;
    }

    public List<MaestroCodificadorDto> getListaTipoEstadosTramite() {
        return listaTipoEstadosTramite;
    }

    public void setListaTipoEstadosTramite(List<MaestroCodificadorDto> listaTipoEstadosTramite) {
        this.listaTipoEstadosTramite = listaTipoEstadosTramite;
    }

    public List<VwConsultaTramiteDto> getResultadosConsultaTramite() {
        return resultadosConsultaTramite;
    }

    public void setResultadosConsultaTramite(List<VwConsultaTramiteDto> resultadosConsultaTramite) {
        this.resultadosConsultaTramite = resultadosConsultaTramite;
    }

    public VwConsultaTramiteDto getFitroBusqueda() {
        return fitroBusqueda;
    }

    public void setFitroBusqueda(VwConsultaTramiteDto fitroBusqueda) {
        this.fitroBusqueda = fitroBusqueda;
    }

    public void consultarTramites() {

        try {
            listaTipoDocumentos = codificadorService.listarTipoDocumentos();
            listaTipoEstadosTramite = codificadorService.listarTipoEstadosTramite();

            resultadosConsultaTramite = tramiteService.consultarTramites(fitroBusqueda);
        } catch (TramiteServiceException ex) {
            Messages.addGlobalError(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Se produjo un error al consultar tramites", ex);
            Messages.addGlobalFatal("Se produjo un error al consultar tramites");
        }

    }

}
