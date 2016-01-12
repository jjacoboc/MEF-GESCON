/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Tseccion;

/**
 *
 * @author JJacobo
 */
public interface SeccionDao {
    
    BigDecimal getNextPK() throws Exception;
    Tseccion getTseccionById(BigDecimal idseccion) throws Exception;
    List<Tseccion> getTsecciones() throws Exception;
    List<Tseccion> getTseccionesByTconocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(Tseccion tseccion) throws Exception;
    void deleteTseccionesByTconocimiento(final BigDecimal idconocimiento) throws Exception;
}
