/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Tcalificacion;

/**
 *
 * @author JJacobo
 */
public interface CalificacionDao {
    
    BigDecimal getNextPK() throws Exception;
    Tcalificacion getTcalificacionById(BigDecimal idcalificacion) throws Exception;
    List<Tcalificacion> getTcalificaciones() throws Exception;
    List<Tcalificacion> getTcalificacionesByTconocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(Tcalificacion tcalificacion) throws Exception;
    void delete(BigDecimal idcalificacion) throws Exception;
}
