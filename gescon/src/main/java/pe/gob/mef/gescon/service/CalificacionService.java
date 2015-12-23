/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Calificacion;

/**
 *
 * @author JJacobo
 */
public interface CalificacionService {
    
    BigDecimal getNextPK() throws Exception;
    Calificacion getCalificacionById(BigDecimal idcalificacion) throws Exception;
    List<Calificacion> getCalificaciones() throws Exception;
    List<Calificacion> getCalificacionesByConocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(Calificacion calificacion) throws Exception;
    void delete(BigDecimal idcalificacion) throws Exception;
}
