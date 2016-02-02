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
    
    BigDecimal getNextPK() throws Exception;
    void saveOrUpdate(Mtuser mtuser) throws Exception;
    List<Mtuser> getMtusers() throws Exception;
    List<Mtuser> getMtusersInternal() throws Exception;
    List<Mtuser> getMtusersExternal() throws Exception;
    Mtuser getMtuserByDNI(String dni) throws Exception;
    Mtuser getMtuserByLogin(String login) throws Exception;
    Mtuser getMtuserById(BigDecimal nusuarioid) throws Exception;
}
