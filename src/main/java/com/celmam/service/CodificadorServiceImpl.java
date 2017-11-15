package com.celmam.service;

import com.celmam.dao.MaestroCodificadorDao;
import com.celmam.dto.MaestroCodificadorDto;
import com.celmam.entity.MaestroCodificador;
import com.celmam.exception.TramiteServiceException;
import com.celmam.util.Mensajes;
import com.celmam.util.TipoCodificador;
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
 * @author Christian
 */
@Service("CodificadorService")
@Transactional(propagation = Propagation.REQUIRED)
public class CodificadorServiceImpl implements CodificadorService {

    final Logger LOG = LoggerFactory.getLogger(CodificadorServiceImpl.class);

    @Autowired
    private MaestroCodificadorDao codificadorDao;

    @Override
    public List<MaestroCodificadorDto> listarTipoDocumentos() throws TramiteServiceException {
        try {
            return listarTipoCodificadorGenerico(TipoCodificador.TIPO_DOCUMENTO.value());
        } catch (Exception e) {
            LOG.error(Mensajes.ERROR_LISTAR_TIPO_DOCUMENTO, e);
            throw new TramiteServiceException(Mensajes.ERROR_LISTAR_TIPO_DOCUMENTO);
        }

    }

    private List<MaestroCodificadorDto> listarTipoCodificadorGenerico(String codificador) {
        List<MaestroCodificador> listaCodficadorTipo = codificadorDao.listarTipoDocumentos(codificador);

        List<MaestroCodificadorDto> listaCodificador = listaCodficadorTipo.stream().map(p -> new MaestroCodificadorDto(p.getPk().getCodTipoCodificador(),
                p.getPk().getCodCodificador(),
                p.getNomCodificador(),
                p.getIndActivo()))
                .collect(Collectors.toList());

        return listaCodificador;
    }

    @Override
    public List<MaestroCodificadorDto> listarTipoEstadosTramite() throws TramiteServiceException {
        try {
            return listarTipoCodificadorGenerico(TipoCodificador.TIPO_ESTADO_TRAMITE.value());
        } catch (Exception e) {
            LOG.error(Mensajes.ERROR_LISTAR_TIPO_DOCUMENTO, e);
            throw new TramiteServiceException(Mensajes.ERROR_LISTAR_TIPO_DOCUMENTO);
        }
    }

    @Override
    public MaestroCodificadorDto obtenerCodificador(String tipoCodificador, String codCodificador) throws TramiteServiceException {

        return fromEntity(codificadorDao.obtenerCodificador(tipoCodificador, codCodificador));
    }

    private MaestroCodificadorDto fromEntity(MaestroCodificador codificador) {

        MaestroCodificadorDto dto = new MaestroCodificadorDto();

        dto.setCodCodificador(codificador.getPk().getCodCodificador());
        dto.setCodTipoCodificador(codificador.getPk().getCodTipoCodificador());
        dto.setIndActivo(codificador.getIndActivo());
        dto.setNomCodificador(codificador.getNomCodificador());

        return dto;

    }

}
