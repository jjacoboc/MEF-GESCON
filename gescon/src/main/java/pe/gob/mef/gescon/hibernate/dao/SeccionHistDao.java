/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.TseccionHist;

/**
 *
 * @author JJacobo
 */
public interface SeccionHistDao {
    
    BigDecimal getNextPK() throws Exception;
    TseccionHist getTseccionHistById(BigDecimal idseccionh) throws Exception;
    List<TseccionHist> getTseccionHists() throws Exception;
    List<TseccionHist> getTseccionHistsByThistorial(BigDecimal idhistorial) throws Exception;
    void saveOrUpdate(TseccionHist tseccionHist) throws Exception;
}
