/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.PassDao;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.hibernate.domain.Tpass;

//import pe.gob.mef.gescon.hibernate.dao.PoliticaDao;
import pe.gob.mef.gescon.service.PassService;

//import pe.gob.mef.gescon.hibernate.domain.Mtpolitica;

//import pe.gob.mef.gescon.service.PoliticaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Pass;
import pe.gob.mef.gescon.web.bean.User;

//import pe.gob.mef.gescon.web.bean.Politica;
/**
 *
 * @author JJacobo
 */
@Repository(value = "PassService")
public class PassServiceImpl implements PassService {

    @Override
    public BigDecimal getNextPK() throws Exception {
        PassDao passDao = (PassDao) ServiceFinder.findBean("PassDao");
        return passDao.getNextPK();
    }
    
    @Override
    public Pass getPassByUser(User user) throws Exception {
        Mtuser mtuser = new Mtuser();
        BeanUtils.copyProperties(mtuser, user);
        PassDao passDao = (PassDao) ServiceFinder.findBean("PassDao");
        Tpass tpass = passDao.getTpassByMtuser(mtuser);
        Pass pass = new Pass();
        if(tpass != null) {
            BeanUtils.copyProperties(pass, tpass);
        } else {
            pass = null;
        }
        return pass;
    }

    @Override
    public void saveOrUpdate(Pass pass) throws Exception {
        Tpass tpass = new Tpass();
        BeanUtils.copyProperties(tpass, pass);
        PassDao passDao = (PassDao) ServiceFinder.findBean("PassDao");
        passDao.saveOrUpdate(tpass);
    }

    

}
