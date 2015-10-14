/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.hibernate.domain.TvinculoBaselegal;

/**
 *
 * @author JJacobo
 */
public interface VinculoBaseLegalDao {
    
    public BigDecimal getNextPK() throws Exception;
    public void saveOrUpdate(TvinculoBaselegal tvinculoBaselegal) throws Exception;
    public void deleteByBaseLegal(final Tbaselegal tbaselegal) throws Exception;
}
