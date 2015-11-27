/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Historial;

/**
 *
 * @author JJacobo
 */
public interface HistorialService {
    
    BigDecimal getNextPK() throws Exception;
    Historial getHistorialById(BigDecimal idhistorial) throws Exception;
    Historial getLastHistorialByConocimiento(BigDecimal idconocimiento) throws Exception;
    List<Historial> getHistoriales() throws Exception;
    List<Historial> getHistorialesByConocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(final Historial historial) throws Exception;
}
