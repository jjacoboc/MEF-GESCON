/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.ImplementacionDao;
import pe.gob.mef.gescon.hibernate.domain.Timplementacion;
import pe.gob.mef.gescon.service.ImplementacionService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Implementacion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ImplementacionService")
public class ImplementacionServiceImpl implements ImplementacionService{
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        ImplementacionDao implementacionDao = (ImplementacionDao) ServiceFinder.findBean("ImplementacionDao");
        return implementacionDao.getNextPK();
    }
    
    @Override
    public Implementacion getImplementacionById(BigDecimal id) throws Exception {
        ImplementacionDao implementacionDao = (ImplementacionDao) ServiceFinder.findBean("ImplementacionDao");
        Timplementacion timplementacion = implementacionDao.getTimplementacionById(id);
        Implementacion implementacion = new Implementacion();        
        BeanUtils.copyProperties(implementacion, timplementacion);        
        return implementacion;
    }
    
    @Override
    public Implementacion getImplementacionByConocimiento(BigDecimal idconocimiento) throws Exception {
        ImplementacionDao implementacionDao = (ImplementacionDao) ServiceFinder.findBean("ImplementacionDao");
        Timplementacion timplementacion = implementacionDao.getTimplementacionByConocimiento(idconocimiento);
        Implementacion implementacion = new Implementacion();        
        BeanUtils.copyProperties(implementacion, timplementacion);        
        return implementacion;
    }
    
    @Override
    public void saveOrUpdate(Implementacion implementacion) throws Exception {
        Timplementacion timplementacion = new Timplementacion();
        BeanUtils.copyProperties(timplementacion, implementacion);
        ImplementacionDao implementacionDao = (ImplementacionDao) ServiceFinder.findBean("ImplementacionDao");
        implementacionDao.saveOrUpdate(timplementacion);
    }
}
