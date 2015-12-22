/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.TdiscusionSeccion;

/**
 *
 * @author JJacobo
 */
public interface DiscusionSeccionDao {
    
    BigDecimal getNextPK() throws Exception;
    TdiscusionSeccion getTseccionById(BigDecimal idseccion) throws Exception;
    List<TdiscusionSeccion> getTsecciones() throws Exception;
    List<TdiscusionSeccion> getTseccionesByTdiscusion(BigDecimal iddiscusion) throws Exception;
    void saveOrUpdate(TdiscusionSeccion tdiscusionSeccion) throws Exception;
}
