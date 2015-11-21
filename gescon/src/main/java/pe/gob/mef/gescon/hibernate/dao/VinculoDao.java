/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Tvinculo;

/**
 *
 * @author JJacobo
 */
public interface VinculoDao {
    
    BigDecimal getNextPK() throws Exception;
    List<Tvinculo> getTvinculos() throws Exception;
    List<Tvinculo> getTvinculosByTconocimiento(BigDecimal idconocimiento) throws Exception;
    List<Tvinculo> getTvinculosByTconocimientoAndTtipoconocimiento(BigDecimal idconocimiento, BigDecimal idtipoconocimiento) throws Exception;
    Tvinculo getTvinculoById(BigDecimal idvinculo) throws Exception;
    void saveOrUpdate(Tvinculo tvinculo) throws Exception;
}
