/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.CalificacionBaselegal;

/**
 *
 * @author JJacobo
 */
public interface CalificacionBaseLegalService {
    
    BigDecimal getNextPK() throws Exception;
    CalificacionBaselegal getCalificacionById(BigDecimal idcalificacion) throws Exception;
    List<CalificacionBaselegal> getCalificaciones() throws Exception;
    List<CalificacionBaselegal> getCalificacionesByConocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(CalificacionBaselegal calificacion) throws Exception;
    void delete(BigDecimal idcalificacion) throws Exception;
}
