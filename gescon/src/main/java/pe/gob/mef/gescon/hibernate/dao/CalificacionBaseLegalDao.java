/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.TcalificacionBaselegal;

/**
 *
 * @author JJacobo
 */
public interface CalificacionBaseLegalDao {
    
    BigDecimal getNextPK() throws Exception;
    TcalificacionBaselegal getTcalificacionById(BigDecimal idcalificacion) throws Exception;
    List<TcalificacionBaselegal> getTcalificaciones() throws Exception;
    List<TcalificacionBaselegal> getTcalificacionesByTconocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(TcalificacionBaselegal tcalificacion) throws Exception;
    void delete(BigDecimal idcalificacion) throws Exception;
}
