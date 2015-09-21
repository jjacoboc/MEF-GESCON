/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Mtmaestro;

/**
 *
 * @author JJacobo
 */
public interface MaestroDao {
    
    public BigDecimal getNextPK() throws Exception;
    public List<Mtmaestro> getMtmaestros() throws Exception;
    public void saveOrUpdate(Mtmaestro mtmaestro) throws Exception;
}
