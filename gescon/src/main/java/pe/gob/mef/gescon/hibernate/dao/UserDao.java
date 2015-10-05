/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;

/**
 *
 * @author JJacobo
 */
public interface UserDao {
    
    public BigDecimal getNextPK() throws Exception;
    public void saveOrUpdate(Mtuser mtuser) throws Exception;
    public List<Mtuser> getMtusers() throws Exception;
}
