/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.BaselegalHist;

/**
 *
 * @author JJacobo
 */
public interface BaseLegalHistorialService {
    
    BigDecimal getNextPK() throws Exception;
    BaselegalHist getHistorialById(BigDecimal idhistorial) throws Exception;
    BaselegalHist getLastHistorialByBaselegal(BigDecimal idbaselegal) throws Exception;
    List<BaselegalHist> getHistoriales() throws Exception;
    List<BaselegalHist> getHistorialesByBaselegal(BigDecimal idbaselegal) throws Exception;
    void saveOrUpdate(BaselegalHist historial) throws Exception;
}
