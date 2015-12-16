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
    
    public BigDecimal getNextPK() throws Exception;
    public List<Mtparametro> getMtparametros() throws Exception;
    public List<Mtparametro> getMtparametrosActived() throws Exception;
    public void saveOrUpdate(Mtparametro mtparametro) throws Exception;
}
