/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.UserDao;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.hibernate.domain.TuserPerfil;
import pe.gob.mef.gescon.service.UserService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
@Repository(value = "UserService")
public class UserServiceImpl implements UserService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        UserDao userDao = (UserDao) ServiceFinder.findBean("UserDao");
        return userDao.getNextPK();
    }
    

    @Override
    public void saveOrUpdate(User user) throws Exception {
        Mtuser mtuser = new Mtuser();
        BeanUtils.copyProperties(mtuser, user);
        UserDao userDao = (UserDao) ServiceFinder.findBean("UserDao");
        userDao.saveOrUpdate(mtuser);
    }
    
    @Override
    public List<User> getUsers() throws Exception {
        List<User> users = new ArrayList<User>();
        UserDao userDao = (UserDao) ServiceFinder.findBean("UserDao");
        List<Mtuser> lista = userDao.getMtusers();
        for(Mtuser mtuser : lista) {
            User user = new User();
            BeanUtils.copyProperties(user, mtuser);
            users.add(user);
        }
        return users;
    }
    
    @Override
    public List<User> getUsersInternal() throws Exception {
        List<User> users = new ArrayList<User>();
        UserDao userDao = (UserDao) ServiceFinder.findBean("UserDao");
        List<Mtuser> lista = userDao.getMtusersInternal();
        for(Mtuser mtuser : lista) {
            User user = new User();
            BeanUtils.copyProperties(user, mtuser);
            users.add(user);
        }
        return users;
    }
    
    @Override
    public List<User> getUsersExternal() throws Exception {
        List<User> users = new ArrayList<User>();
        UserDao userDao = (UserDao) ServiceFinder.findBean("UserDao");
        List<Mtuser> lista = userDao.getMtusersExternal();
        for(Mtuser mtuser : lista) {
            User user = new User();
            BeanUtils.copyProperties(user, mtuser);
            users.add(user);
        }
        return users;
    }
    
    @Override
    public List<User> getUsersByPerfil(BigDecimal perfil) throws Exception {
        List<User> users = new ArrayList<User>();
        UserDao userDao = (UserDao) ServiceFinder.findBean("UserDao");
        List<Mtuser> lista = userDao.getMtuserByPerfil(perfil);
        for(Mtuser mtuser : lista) {
            User user = new User();
            BeanUtils.copyProperties(user, mtuser);
            users.add(user);
        }
        return users;
    }
    
    @Override
    public List<User> getUsersActivedByPerfil(BigDecimal perfil) throws Exception {
        List<User> users = new ArrayList<User>();
        UserDao userDao = (UserDao) ServiceFinder.findBean("UserDao");
        List<Mtuser> lista = userDao.getMtuserActivedByPerfil(perfil);
        for(Mtuser mtuser : lista) {
            User user = new User();
            BeanUtils.copyProperties(user, mtuser);
            users.add(user);
        }
        return users;
    }
    
    @Override
    public User getUserByDNI(String dni) throws Exception {
        UserDao userDao = (UserDao) ServiceFinder.findBean("UserDao");
        Mtuser mtuser = userDao.getMtuserByDNI(dni);
        User user = new User();
        if(mtuser != null) {
            BeanUtils.copyProperties(user, mtuser);
        } else {
            user = null;
        }        
        return user;
    }
    
    @Override
    public User getUserByEmail(String email) throws Exception {
        UserDao userDao = (UserDao) ServiceFinder.findBean("UserDao");
        Mtuser mtuser = userDao.getMtuserByEmail(email);
        User user = new User();
        if(mtuser != null) {
            BeanUtils.copyProperties(user, mtuser);
        } else {
            user = null;
        }        
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws Exception {
        UserDao userDao = (UserDao) ServiceFinder.findBean("UserDao");
        Mtuser mtuser = userDao.getMtuserByLogin(login);
        User user = new User();
        if(mtuser != null) {
            BeanUtils.copyProperties(user, mtuser);
        } else {
            user = null;
        }        
        return user;
    }

    @Override
    public User getMtuserById(BigDecimal nusuarioid) throws Exception {
        User user = new User();
        UserDao userDao = (UserDao) ServiceFinder.findBean("UserDao");
        Mtuser mtuser = userDao.getMtuserById(nusuarioid);
        BeanUtils.copyProperties(user, mtuser);
         return user;
    }
    
    @Override
    public void asignProfileToUser(TuserPerfil userperfil) throws Exception {
        UserDao userDao = (UserDao) ServiceFinder.findBean("UserDao");
        userDao.asignProfileToUser(userperfil);
    }
    
    @Override
    public BigDecimal getPerfilByUser(BigDecimal nusuarioid) throws Exception {
        UserDao userDao = (UserDao) ServiceFinder.findBean("UserDao");
        TuserPerfil tuserPerfil = userDao.getPerfilByUser(nusuarioid);
        return tuserPerfil.getNperfilid();
    }
    
    @Override
    public void deletePerfilByUser(BigDecimal idusuario) throws Exception {
        UserDao userDao = (UserDao) ServiceFinder.findBean("UserDao");
        userDao.deletePerfilByUser(idusuario);
    }
}
