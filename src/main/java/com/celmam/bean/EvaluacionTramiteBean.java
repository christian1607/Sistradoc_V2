package com.celmam.bean;

import com.celmam.alfresco.AlfrescoUtils;
import com.celmam.dto.MaestroCodificadorDto;
import com.celmam.dto.TramiteDto;
import com.celmam.service.ServiceFactory;
import com.celmam.service.TramiteService;
import com.celmam.exception.TramiteServiceException;
import com.celmam.service.CodificadorService;
import com.celmam.util.TipoEstadoTramite;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.chemistry.opencmis.client.api.Document;
import org.omnifaces.util.Messages;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class EvaluacionTramiteBean implements Serializable {

    final Logger logger = LoggerFactory.getLogger(EvaluacionTramiteBean.class);
    private static final long serialVersionUID = 1L;

    private TramiteDto tramite = new TramiteDto();
    private List<MaestroCodificadorDto> listaTipoDocumentos;
    private StreamedContent documento;

    private final TramiteService tramiteService = ServiceFactory.getTramiteService();
    private final CodificadorService codificadorService = ServiceFactory.getCodificadorService();

    @PostConstruct
    public void init() {

        try {
            String codigoTramite = obtenerValorParametro("codigoTramite");
            tramite = tramiteService.obtenerTramitePorCodigoTramite(codigoTramite);

        } catch (TramiteServiceException ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }

    public TramiteDto getTramite() {
        return tramite;
    }

    public void setTramite(TramiteDto tramite) {
        this.tramite = tramite;
    }

    public List<MaestroCodificadorDto> getListaTipoDocumentos() {
        return listaTipoDocumentos;
    }

    public void setListaTipoDocumentos(List<MaestroCodificadorDto> listaTipoDocumentos) {
        this.listaTipoDocumentos = listaTipoDocumentos;
    }

    public StreamedContent getDocumento() {
        return documento;
    }

    private String obtenerValorParametro(String nameParameter) {

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        return params.get(nameParameter);
    }

    public void descargarAdjunto(String idAlfresco) {
        try {
            Document adjunto = AlfrescoUtils.obtenerFileById(idAlfresco);
            documento = new DefaultStreamedContent(adjunto.getContentStream().getStream(), "application/pdf", adjunto.getName());
        } catch (Exception e) {
            Messages.addGlobalFatal("Se produjo un error al descargar documento.");
        }

    }

    public void aceptarTramite() throws InterruptedException {
        try {
            cambiarEstadoTramite(TipoEstadoTramite.ACEPTADA.value(), tramite.getCodTramite());
            tramite = tramiteService.obtenerTramitePorCodigoTramite(tramite.getCodTramite());
            Messages.addGlobalInfo("Se aceptó trámite correctamente.");
        } catch (TramiteServiceException ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }

    public void rechazarTramite() {
        try {
            cambiarEstadoTramite(TipoEstadoTramite.RECHAZADA.value(), tramite.getCodTramite());
            tramite = tramiteService.obtenerTramitePorCodigoTramite(tramite.getCodTramite());
            Messages.addGlobalInfo("Se rechazó trámite correctamente.");
        } catch (TramiteServiceException ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }

    private void cambiarEstadoTramite(String codEstado, String codTramite) throws TramiteServiceException {

        tramiteService.actualizarEstadoTramite(codEstado, codTramite);

    }
}
