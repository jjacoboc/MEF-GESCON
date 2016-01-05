/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Tarchivo;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;

/**
 *
 * @author JJacobo
 */
public interface ArchivoDao {
    
    BigDecimal getNextPK() throws Exception;
    List<Tarchivo> getTarchivosByTbaselegal(Tbaselegal tbaselegal) throws Exception;
    Tarchivo getTarchivoByTbaselegal(Tbaselegal tbaselegal) throws Exception;
    void saveOrUpdate(Tarchivo tarchivo) throws Exception;
}
