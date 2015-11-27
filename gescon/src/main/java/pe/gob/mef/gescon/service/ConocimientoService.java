/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Conocimiento;

/**
 *
 * @author JJacobo
 */
public interface ConocimientoService {
    
    BigDecimal getNextPK() throws Exception;
    List<Conocimiento> getConocimientos() throws Exception;
    Conocimiento getConocimientoById(BigDecimal id) throws Exception;
    void saveOrUpdate(Conocimiento conocimiento) throws Exception;
    void delete(Conocimiento conocimiento) throws Exception;
}
