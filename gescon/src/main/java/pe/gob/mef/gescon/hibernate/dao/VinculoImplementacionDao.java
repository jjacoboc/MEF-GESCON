/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.TvinculoImplementacion;

/**
 *
 * @author JJacobo
 */
public interface VinculoImplementacionDao {
    
    BigDecimal getNextPK() throws Exception;
    List<TvinculoImplementacion> getTvinculos() throws Exception;
    List<TvinculoImplementacion> getTvinculosByTconocimiento(BigDecimal idconocimiento) throws Exception;
    TvinculoImplementacion getTvinculoById(BigDecimal idvinculo) throws Exception;
    void saveOrUpdate(TvinculoImplementacion tvinculoImplementacion) throws Exception;
    void deleteByTconocimiento(BigDecimal idconocimiento) throws Exception;
}
