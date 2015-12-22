/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import pe.gob.mef.gescon.hibernate.domain.TdiscusionHist;

/**
 *
 * @author JJacobo
 */
public interface DiscusionHistDao {
    
    BigDecimal getNextPK() throws Exception;
    TdiscusionHist getTdiscusionHistById(BigDecimal ndiscusionhid) throws Exception;
    TdiscusionHist getTdiscusionHistByTconocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(final TdiscusionHist tdiscusionHist) throws Exception;
}
