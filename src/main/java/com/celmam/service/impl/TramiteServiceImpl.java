package com.celmam.service.impl;

import com.celmam.dao.TramiteDao;
import com.celmam.dto.MaestroCodificadorDto;
import com.celmam.dto.TramiteDocumentoDto;
import com.celmam.dto.TramiteDto;
import com.celmam.dto.VwConsultaTramiteDto;
import com.celmam.entity.Tramite;
import com.celmam.entity.TramiteDocumento;
import com.celmam.entity.VwConsultaTramite;
import com.celmam.exception.TramiteServiceException;
import com.celmam.service.CodificadorService;
import com.celmam.service.TramiteService;
import com.celmam.util.Mensajes;
import com.celmam.util.TipoCodificador;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Christian
 */
@Service("TramiteService")
@Transactional(propagation = Propagation.REQUIRED)
public class TramiteServiceImpl implements TramiteService {

    final Logger LOG = LoggerFactory.getLogger(TramiteServiceImpl.class);

    @Autowired
    private TramiteDao tramiteDao;

    @Autowired
    private CodificadorService codififcadorService;

    @Override
    public TramiteDto obtenerTramitePorCodigoTramite(String codTramite) throws TramiteServiceException {

        TramiteDto datosTramite;
        try {

            Tramite tramite = tramiteDao.obtenerTramiteByCodigoTramite(codTramite);

            if (Optional.ofNullable(tramite).isPresent()) {
                datosTramite = new TramiteDto(tramite.getCodTramite(),
                        tramite.getNomRemitente(),
                        tramite.getNomDestinatario(),
                        tramite.getDesComentario(),
                        tramite.getDesAsunto(),
                        tramite.getCodEstado(),
                        tramite.getFecRegistro(),
                        fromEntityToDto(tramite.getDocumentos()),
                        obtenerNombreEstado(tramite.getCodEstado()));
                return datosTramite;
            } else {
                throw new TramiteServiceException(Mensajes.ERROR_OBTENER_CODIGO_TRAMITE);
            }

        } catch (Exception e) {
            LOG.error(Mensajes.ERROR_OBTENER_CODIGO_TRAMITE, codTramite, e);
            throw new TramiteServiceException(Mensajes.ERROR_OBTENER_CODIGO_TRAMITE);

        }

    }

    @Override
    public void registrarTramite(TramiteDto tramiteDto) throws TramiteServiceException {

        try {
            tramiteDto.setCodEstado(ESTADO_PENDIENTE);
            tramiteDto.setFecRegistro(new Date());

            Tramite tramite = fromDtoToTramite(tramiteDto);
            tramiteDao.registrarTramite(tramite);

        } catch (TramiteServiceException ex) {
            throw new TramiteServiceException(ex.getMessage());
        } catch (Exception ex) {
            LOG.error(Mensajes.ERROR_REGISTRO_TRAMITE, ex);
            throw new TramiteServiceException(Mensajes.ERROR_REGISTRO_TRAMITE);
        }
    }
    private static final String ESTADO_PENDIENTE = "1";

    public void registrarTramiteDocumento(TramiteDocumentoDto documentoDto) {

        TramiteDocumento documento = new TramiteDocumento();
        documento.setIdDocumento(documentoDto.getIdDocumento());
        documento.setCodTramite(documentoDto.getCodTramite());
        documento.setCodTipoDocumento(documentoDto.getCodTipoDocumento());

        tramiteDao.registrarTramiteDocumento(documento);
    }

    private Tramite fromDtoToTramite(TramiteDto tramiteDto) throws TramiteServiceException {
        try {
            Tramite tramite = new Tramite();

            tramite.setCodEstado(tramiteDto.getCodEstado());
            tramite.setCodTramite(tramiteDto.getCodTramite());
            tramite.setDesAsunto(tramiteDto.getDesAsunto());
            tramite.setDesComentario(tramiteDto.getDesComentario());
            tramite.setFecRegistro(tramiteDto.getFecRegistro());
            tramite.setNomDestinatario(tramiteDto.getNomDestinatario());
            tramite.setNomRemitente(tramiteDto.getNomRemitente());

            tramite.setDocumentos(fromDtoToTramiteDocumento(tramiteDto.getDocumentosAdjuntos()));

            return tramite;

        } catch (Exception e) {
            LOG.error(Mensajes.ERROR_REGISTRO_TRAMITE, e);
            throw new TramiteServiceException(Mensajes.ERROR_REGISTRO_TRAMITE);
        }

    }

    private List<TramiteDocumento> fromDtoToTramiteDocumento(List<TramiteDocumentoDto> documentos) {

        return documentos.stream()
                .map(p -> new TramiteDocumento(p.getIdDocumento(), p.getCodTramite(), p.getCodTipoDocumento(), p.getNomDocumento()))
                .collect(Collectors.toList());

    }

    @Override
    public String obtenerCodigoTramite() throws TramiteServiceException {
        try {
            String seq = tramiteDao.obtenerCodigoTramiteSeq();
            String prefijo = "TRA";

            return prefijo.concat(seq);

        } catch (Exception e) {
            LOG.error(Mensajes.ERROR_OBTENER_CODIGO_TRAMITE, e);
            throw new TramiteServiceException(Mensajes.ERROR_OBTENER_CODIGO_TRAMITE);
        }

    }

    @Override
    public List<VwConsultaTramiteDto> consultarTramites(VwConsultaTramiteDto filtros) throws TramiteServiceException {
        try {

            List<VwConsultaTramite> tramitesEncontrados = tramiteDao.consultarTramites(filtros);

            return tramitesEncontrados.stream()
                    .map(p -> new VwConsultaTramiteDto(p.getCodTramite(), p.getDesAsunto(), p.getFecRegistro(), p.getNomDestinatario(), p.getNomRemitente(), p.getCodEstado(), p.getNomEstado()))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            LOG.error(Mensajes.ERROR_CONSULTAR_TRAMITE, e);
            throw new TramiteServiceException(Mensajes.ERROR_CONSULTAR_TRAMITE);
        }

    }

    private List<TramiteDocumentoDto> fromEntityToDto(List<TramiteDocumento> documentos) {

        if (Optional.ofNullable(documentos).isPresent()) {
            return documentos.stream()
                    .map(p -> new TramiteDocumentoDto(p.getIdDocumento(), p.getNomDocumento(),
                    p.getCodTramite(), p.getCodTipoDocumento(), obtenerNombreTipoDocumento(p.getCodTipoDocumento())))
                    .collect(Collectors.toList());

        } else {
            return null;
        }

    }

    private String obtenerNombreTipoDocumento(String codTipoDocumento) {
        String nombreDocumento = null;
        List<MaestroCodificadorDto> listaTipoDocumentos;
        try {
            listaTipoDocumentos = codififcadorService.listarTipoDocumentos();
            MaestroCodificadorDto tipoDocuemnto = listaTipoDocumentos.stream().filter(p -> p.getCodCodificador().equals(codTipoDocumento))
                    .findAny()
                    .orElse(null);

            if (Optional.ofNullable(tipoDocuemnto).isPresent()) {
                nombreDocumento = tipoDocuemnto.getNomCodificador();
            }

        } catch (TramiteServiceException ex) {
            return null;

        } catch (Exception ex) {
            LOG.error(Mensajes.ERROR_OBTENER_TIPO_DOCUEMNTOS, ex);
            return null;
        }

        return nombreDocumento;

    }

    @Override
    public void actualizarEstadoTramite(String codEstado, String codTramite) throws TramiteServiceException {

        try {
            Tramite tramite = tramiteDao.obtenerTramiteByCodigoTramite(codTramite);
            tramite.setCodEstado(codEstado);
            tramiteDao.actualizarTramite(tramite);
        } catch (Exception e) {
            LOG.error(Mensajes.ERROR_ACTUALIZAR_TRAMITE, e);
            throw new TramiteServiceException(Mensajes.ERROR_ACTUALIZAR_TRAMITE);

        }

    }

    private String obtenerNombreEstado(String codEstado) {

        try {
            return Optional.ofNullable(codififcadorService.obtenerCodificador(TipoCodificador.TIPO_ESTADO_TRAMITE.value(), codEstado))
                    .orElse(new MaestroCodificadorDto("<NO DATA>")).getNomCodificador();
        } catch (TramiteServiceException ex) {
            LOG.error(Mensajes.ERROR_ACTUALIZAR_TRAMITE, ex);
            return null;
        }
    }
}
