/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Discusion;

/**
 *
 * @author JJacobo
 */
public interface DiscusionService {
    
    BigDecimal getNextPK() throws Exception;
    Discusion getDiscusionById(BigDecimal iddiscusion) throws Exception;
    List<Discusion> getDiscusiones() throws Exception;
    List<Discusion> getDiscusionesByConocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(Discusion discusion) throws Exception;
}
