/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.DiscusionSeccionHist;

/**
 *
 * @author JJacobo
 */
public interface DiscusionSeccionHistService {
    
    BigDecimal getNextPK() throws Exception;
    DiscusionSeccionHist getSeccionHistById(BigDecimal idseccion) throws Exception;
    List<DiscusionSeccionHist> getSeccionesHist() throws Exception;
    List<DiscusionSeccionHist> getSeccionesHistByDiscusionHist(BigDecimal iddiscusion) throws Exception;
    void saveOrUpdate(DiscusionSeccionHist discusionSeccionHist) throws Exception;
}
