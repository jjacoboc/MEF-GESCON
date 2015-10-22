/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import pe.gob.mef.gescon.web.bean.BaseLegal;
import pe.gob.mef.gescon.web.bean.VinculoBaselegal;

/**
 *
 * @author JJacobo
 */
public interface VinculoBaseLegalService {
    
    public BigDecimal getNextPK() throws Exception;
    public void saveOrUpdate(VinculoBaselegal vinculoBaselegal) throws Exception;
    public void deleteByBaseLegal(BaseLegal baselegal) throws Exception;
}
