/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.SeccionHist;

/**
 *
 * @author JJacobo
 */
public interface SeccionHistService {
    
    BigDecimal getNextPK() throws Exception;
    SeccionHist getSeccionHistById(BigDecimal idseccionh) throws Exception;
    List<SeccionHist> getSeccionHists() throws Exception;
    List<SeccionHist> getSeccionHistsByHistorial(BigDecimal idhistorial) throws Exception;
    void saveOrUpdate(SeccionHist seccionHist) throws Exception;
}
