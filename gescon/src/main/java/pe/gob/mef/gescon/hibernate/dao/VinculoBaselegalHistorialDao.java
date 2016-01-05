/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.TvinculoBaselegalHist;

/**
 *
 * @author JJacobo
 */
public interface VinculoBaselegalHistorialDao {
    
    BigDecimal getNextPK() throws Exception;
    TvinculoBaselegalHist getTvinculoHistById(BigDecimal idvinculoh) throws Exception;
    List<TvinculoBaselegalHist> getTvinculoHists() throws Exception;
    List<TvinculoBaselegalHist> getTvinculoHistsByTbaselegalHist(BigDecimal idhistorial) throws Exception;
    void saveOrUpdate(TvinculoBaselegalHist tvinculoHist) throws Exception;
}
