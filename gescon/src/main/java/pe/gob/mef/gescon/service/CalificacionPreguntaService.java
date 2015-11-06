/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.CalificacionPregunta;

/**
 *
 * @author JJacobo
 */
public interface CalificacionPreguntaService {
    
    BigDecimal getNextPK() throws Exception;
    CalificacionPregunta getCalificacionById(BigDecimal idcalificacion) throws Exception;
    List<CalificacionPregunta> getCalificaciones() throws Exception;
    List<CalificacionPregunta> getCalificacionesByConocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(CalificacionPregunta calificacion) throws Exception;
    void delete(BigDecimal idcalificacion) throws Exception;
}
