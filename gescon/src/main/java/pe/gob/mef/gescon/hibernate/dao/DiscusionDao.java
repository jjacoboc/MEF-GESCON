/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Tdiscusion;

/**
 *
 * @author JJacobo
 */
public interface DiscusionDao {
    
    BigDecimal getNextPK() throws Exception;
    Tdiscusion getTdiscusionById(BigDecimal iddiscusion) throws Exception;
    List<Tdiscusion> getTdiscusiones() throws Exception;
    List<Tdiscusion> getTdiscusionesByTconocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(Tdiscusion tdiscusion) throws Exception;
}
