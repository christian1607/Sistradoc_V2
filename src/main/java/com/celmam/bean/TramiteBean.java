package com.celmam.bean;

import com.celmam.alfresco.AlfrescoUtils;
import com.celmam.dto.MaestroCodificadorDto;
import com.celmam.dto.TramiteDocumentoDto;
import com.celmam.dto.TramiteDto;
import com.celmam.service.ServiceFactory;
import com.celmam.service.TramiteService;
import com.celmam.exception.TramiteServiceException;
import com.celmam.service.CodificadorService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.commons.io.IOUtils;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class TramiteBean implements Serializable {

    final Logger logger = LoggerFactory.getLogger(TramiteBean.class);
    private static final long serialVersionUID = 1L;

    private TramiteDto tramite = new TramiteDto();
    private List<MaestroCodificadorDto> listaTipoDocumentos;

    private final TramiteService tramiteService = ServiceFactory.getTramiteService();
    private final CodificadorService codificadorService = ServiceFactory.getCodificadorService();

    @PostConstruct
    public void init() {

        try {
            listaTipoDocumentos = codificadorService.listarTipoDocumentos();
            tramite.setCodTramite(tramiteService.obtenerCodigoTramite());
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

    public void registrarTramite() {

        try {
            tramiteService.registrarTramite(tramite);

            Messages.addGlobalInfo("Se registró tramite correctamente", "sss");
        } catch (TramiteServiceException ex) {
            Messages.addGlobalError(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Se produjo un error al registrar tramite", ex);
            Messages.addGlobalFatal("Se produjo un error al registrar tramite");
        }

    }

    @SuppressWarnings("UseSpecificCatch")
    public void subirArchivo(FileUploadEvent archivoAdjunto) {

        try {

            File file = new File(archivoAdjunto.getFile().getFileName());
            InputStream input = archivoAdjunto.getFile().getInputstream();

            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(IOUtils.toByteArray(input));
                fos.flush();
            }

            if (Optional.ofNullable(tramite.getCodTipoDocumento()).isPresent()) {

                Document documentoSubido = AlfrescoUtils.subirFile(file, Integer.parseInt(tramite.getDocumentoAdjunto().getCodTipoDocumento()));
                tramite.getDocumentosAdjuntos().add(new TramiteDocumentoDto(documentoSubido.getId(), tramite.getCodTramite(), tramite.getCodTipoDocumento()));
                Messages.addGlobalInfo("Archivo subido correctamente");
            } else {
                Messages.addGlobalWarn("No se seleccionó el tipo de documento correctamente");
            }

        } catch (FileNotFoundException ex) {
            logger.error("Se produjo un error al subir archivo", ex);
            Messages.addGlobalError("Se produjo un error al subir archivo");
        } catch (IOException ex) {
            logger.error("Se produjo un error al subir archivo", ex);
            Messages.addGlobalFatal("Se produjo un error al subir archivo");
        } catch (Exception ex) {
            logger.error("Se produjo un error al subir archivo", ex);
            Messages.addGlobalFatal("Se produjo un error al subir archivo");
        }

    }

}
