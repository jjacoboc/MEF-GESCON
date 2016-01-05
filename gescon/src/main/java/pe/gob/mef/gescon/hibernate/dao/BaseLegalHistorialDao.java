/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.TbaselegalHist;

/**
 *
 * @author JJacobo
 */
public interface BaseLegalHistorialDao {
    
    BigDecimal getNextPK() throws Exception;
    TbaselegalHist getThistorialById(BigDecimal idhistorial) throws Exception;
    TbaselegalHist getLastThistorialByTbaselegal(BigDecimal idbaselegal) throws Exception;
    List<TbaselegalHist> getThistoriales() throws Exception;
    List<TbaselegalHist> getThistorialesByTbaselegal(BigDecimal idbaselegal) throws Exception;
    void saveOrUpdate(TbaselegalHist thistorial) throws Exception;
}
