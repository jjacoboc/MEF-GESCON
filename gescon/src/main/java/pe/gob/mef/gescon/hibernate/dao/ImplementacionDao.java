/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import pe.gob.mef.gescon.hibernate.domain.Timplementacion;

/**
 *
 * @author JJacobo
 */
public interface ImplementacionDao {
    
    BigDecimal getNextPK() throws Exception;
    Timplementacion getTimplementacionById(BigDecimal id) throws Exception;
    Timplementacion getTimplementacionByConocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(final Timplementacion timplementacion) throws Exception;
}
