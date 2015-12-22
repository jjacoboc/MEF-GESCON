/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.DiscusionHist;

/**
 *
 * @author JJacobo
 */
public interface DiscusionHistService {
    
    BigDecimal getNextPK() throws Exception;
    DiscusionHist getDiscusionHistById(BigDecimal ndiscusionhid) throws Exception;
    DiscusionHist getDiscusionHistByConocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(DiscusionHist discusionHist) throws Exception;
}
