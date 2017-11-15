package com.celmam.dao;

import com.celmam.entity.MaestroCodificador;
import com.celmam.entity.MaestroCodificadorPK;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Christian
 */
@Repository("MaestroCodificadorDao")
public class MaestroCodificadorDao {

    @Autowired
    BaseDaoImpl<MaestroCodificadorPK, MaestroCodificador> maestroCodificadorDao;

    public List<MaestroCodificador> listarTipoDocumentos(String codificador) {

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("codificador", codificador);

        return maestroCodificadorDao.findByNamedQuery("MaestroCodificador.findTipoCodificador", parametros);

    }

    public MaestroCodificador obtenerCodificador(String tipoCodificador, String codCodificador) {

        MaestroCodificadorPK pk = new MaestroCodificadorPK();
        pk.setCodCodificador(codCodificador);
        pk.setCodTipoCodificador(tipoCodificador);
        return maestroCodificadorDao.findByKey(pk, MaestroCodificador.class);
    }

}
