/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;

/**
 *
 * @author JJacobo
 */
public interface BaseLegalDao {
    
    public BigDecimal getNextPK() throws Exception;
    public List<Tbaselegal> getTbaselegales() throws Exception;
    public Tbaselegal getTbaselegalById(BigDecimal id) throws Exception;
    public void saveOrUpdate(Tbaselegal tbaselegal) throws Exception;
}
