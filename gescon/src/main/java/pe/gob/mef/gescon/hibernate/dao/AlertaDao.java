/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Mtalerta;

/**
 *
 * @author JJacobo
 */
public interface AlertaDao {
    
    public BigDecimal getNextPK() throws Exception;
    public List<Mtalerta> getMtalertas() throws Exception;
    public void saveOrUpdate(Mtalerta mtalerta) throws Exception;
}
