/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.AsignacionDao;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.hibernate.domain.Tasignacion;
import pe.gob.mef.gescon.service.AsignacionService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Asignacion;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
@Repository(value = "AsignacionService")
public class AsignacionServiceImpl implements AsignacionService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        AsignacionDao asignacionDao = (AsignacionDao) ServiceFinder.findBean("AsignacionDao");
        return asignacionDao.getNextPK();
    }
    
    @Override
    public BigDecimal getNumberNotificationsByUser(User user) throws Exception {
        Mtuser mtuser = new Mtuser();
        BeanUtils.copyProperties(mtuser, user);
        AsignacionDao asignacionDao = (AsignacionDao) ServiceFinder.findBean("AsignacionDao");
        return asignacionDao.getNumberNotificationsByMtuser(mtuser);
    }
    
    @Override
    public void saveOrUpdate(Asignacion asignacion) throws Exception {
        Tasignacion tasignacion = new Tasignacion();
        BeanUtils.copyProperties(tasignacion, asignacion);
        AsignacionDao asignacionDao = (AsignacionDao) ServiceFinder.findBean("AsignacionDao");
        asignacionDao.saveOrUpdate(tasignacion);
    }
}
