/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
public interface UserService {
    
    public BigDecimal getNextPK() throws Exception;
    public void saveOrUpdate(User user) throws Exception;
    public List<User> getUsers() throws Exception;
    public User getUserByLogin(String login) throws Exception;
    
    /*
       cnishimura create
    */
    /**
     * @since cnishimura 31/10/2015
     * @param nusuarioid
     * @return
     * @throws Exception 
     */
    User getMtuserById(BigDecimal nusuarioid) throws Exception;
}
