package com.celmam.dao;

import com.celmam.dto.VwConsultaTramiteDto;
import com.celmam.entity.Tramite;
import com.celmam.entity.TramiteDocumento;
import com.celmam.entity.VwConsultaTramite;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Christian
 */
@Repository("TramiteDao")
public class TramiteDao {

    @Autowired
    BaseDaoImpl<String, Tramite> tramiteDao;

    @Autowired
    BaseDaoImpl<String, TramiteDocumento> tramiteDocumentoDao;

    @Autowired
    BaseDaoImpl<String, VwConsultaTramite> vwConsultaTramiteDao;

    public Tramite obtenerTramiteByCodigoTramite(String codTramite) {

        return tramiteDao.findByKey(codTramite, Tramite.class);
    }

    public void registrarTramite(Tramite tramite) {
        tramiteDao.insertTransaccion(tramite);
    }

    public void registrarTramiteDocumento(TramiteDocumento tramiteDocumento) {
        tramiteDocumentoDao.insertTransaccion(tramiteDocumento);
    }

    public String obtenerCodigoTramiteSeq() {
        return String.format("%04d", Integer.parseInt(tramiteDao.callFunction("SISTRADOC.TRAMITE_SEQ.NEXTVAL").toString()));

    }

    public List<VwConsultaTramite> consultarTramites(VwConsultaTramiteDto filtros) {

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("codTramite", filtros.getCodTramite());
        parametros.put("nomDestinatario", filtros.getNomDestinatario());
        parametros.put("nomRemitente", filtros.getNomRemitente());
        parametros.put("codEstado", filtros.getCodEstado());
        parametros.put("fechaDesde", filtros.getFechaDesde());
        parametros.put("fechaHasta", filtros.getFechaHasta());

        return vwConsultaTramiteDao.findByNamedQuery("VwConsultaTramite.findFiltro", parametros);
    }

    public void actualizarTramite(Tramite tramite) {
        tramiteDao.update(tramite);
    }

}
