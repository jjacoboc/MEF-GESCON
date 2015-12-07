/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.TvinculoPregunta;

/**
 *
 * @author JJacobo
 */
public interface VinculoPreguntaDao {
    
    BigDecimal getNextPK() throws Exception;
    List<TvinculoPregunta> getTvinculos() throws Exception;
    List<TvinculoPregunta> getTvinculosByTconocimiento(BigDecimal idconocimiento) throws Exception;
    List<TvinculoPregunta> getTvinculosByTconocimientoAndTtipoconocimiento(BigDecimal idconocimiento, BigDecimal idtipoconocimiento) throws Exception;
    TvinculoPregunta getTvinculoById(BigDecimal idvinculo) throws Exception;
    void saveOrUpdate(TvinculoPregunta tvinculopregunta) throws Exception;
    void deleteByTconocimiento(BigDecimal idconocimiento) throws Exception;
}
