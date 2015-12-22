/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.TdiscusionSeccionHist;

/**
 *
 * @author JJacobo
 */
public interface DiscusionSeccionHistDao {
    
    BigDecimal getNextPK() throws Exception;
    TdiscusionSeccionHist getTseccionHistById(BigDecimal idseccionhist) throws Exception;
    List<TdiscusionSeccionHist> getTseccionesHist() throws Exception;
    List<TdiscusionSeccionHist> getTseccionesHistByTdiscusionHist(BigDecimal iddiscusionhist) throws Exception;
    void saveOrUpdate(TdiscusionSeccionHist tdiscusionSeccionHist) throws Exception;
}
