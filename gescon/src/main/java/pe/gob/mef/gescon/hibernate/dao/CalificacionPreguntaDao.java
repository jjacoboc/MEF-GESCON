/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.TcalificacionPregunta;

/**
 *
 * @author JJacobo
 */
public interface CalificacionPreguntaDao {
    
    BigDecimal getNextPK() throws Exception;
    TcalificacionPregunta getTcalificacionById(BigDecimal idcalificacion) throws Exception;
    List<TcalificacionPregunta> getTcalificaciones() throws Exception;
    List<TcalificacionPregunta> getTcalificacionesByTconocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(TcalificacionPregunta tcalificacion) throws Exception;
    void delete(BigDecimal idcalificacion) throws Exception;
}
