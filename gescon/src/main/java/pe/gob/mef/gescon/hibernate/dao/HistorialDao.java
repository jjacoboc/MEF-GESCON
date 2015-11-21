/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Thistorial;

/**
 *
 * @author JJacobo
 */
public interface HistorialDao {
    
    BigDecimal getNextPK() throws Exception;
    Thistorial getThistorialById(BigDecimal idhistorial) throws Exception;
    List<Thistorial> getThistoriales() throws Exception;
    List<Thistorial> getThistorialesByTconocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(final Thistorial thistorial) throws Exception;
}
