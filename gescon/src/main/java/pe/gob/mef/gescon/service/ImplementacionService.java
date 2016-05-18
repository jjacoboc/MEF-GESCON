/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import pe.gob.mef.gescon.web.bean.Implementacion;

/**
 *
 * @author JJacobo
 */
public interface ImplementacionService {
    
    BigDecimal getNextPK() throws Exception;
    Implementacion getImplementacionById(BigDecimal id) throws Exception;
    Implementacion getImplementacionByConocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(final Implementacion implementacion) throws Exception;
}
