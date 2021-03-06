/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Mtparametro;

/**
 *
 * @author JJacobo
 */
public interface ParametroDao {
    
    BigDecimal getNextPK() throws Exception;
    List<Mtparametro> getMtparametros() throws Exception;
    List<Mtparametro> getMtparametrosActived() throws Exception;
    Mtparametro getMtparametroById(BigDecimal id) throws Exception;
    void saveOrUpdate(Mtparametro mtparametro) throws Exception;
}
