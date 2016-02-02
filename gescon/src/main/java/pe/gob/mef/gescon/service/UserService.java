/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
public interface UserService {
    
    BigDecimal getNextPK() throws Exception;
    void saveOrUpdate(User user) throws Exception;
    List<User> getUsers() throws Exception;
    List<User> getUsersInternal() throws Exception;
    List<User> getUsersExternal() throws Exception;
    User getUserByDNI(String dni) throws Exception;
    User getUserByLogin(String login) throws Exception;
    User getMtuserById(BigDecimal nusuarioid) throws Exception;
}
