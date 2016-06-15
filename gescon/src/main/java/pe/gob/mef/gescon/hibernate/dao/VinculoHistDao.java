/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.TvinculoHist;

/**
 *
 * @author JJacobo
 */
public interface VinculoHistDao {
    
    BigDecimal getNextPK() throws Exception;
    TvinculoHist getTvinculoHistById(BigDecimal idvinculoh) throws Exception;
    List<TvinculoHist> getTvinculoHists() throws Exception;
    List<TvinculoHist> getTvinculoHistsByThistorial(BigDecimal idhistorial) throws Exception;
    void saveOrUpdate(TvinculoHist tvinculoHist) throws Exception;
    List<HashMap> getConcimientosVinculadosByHistorial(HashMap filters) throws Exception;
}
