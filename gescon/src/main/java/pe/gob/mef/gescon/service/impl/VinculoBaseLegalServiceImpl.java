/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.VinculoBaseLegalDao;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.hibernate.domain.TvinculoBaselegal;
import pe.gob.mef.gescon.service.VinculoBaseLegalService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.BaseLegal;
import pe.gob.mef.gescon.web.bean.VinculoBaselegal;

/**
 *
 * @author JJacobo
 */
@Repository(value = "VinculoBaseLegalService")
public class VinculoBaseLegalServiceImpl implements VinculoBaseLegalService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        VinculoBaseLegalDao vinculoBaseLegalDao = (VinculoBaseLegalDao) ServiceFinder.findBean("VinculoBaseLegalDao");
        return vinculoBaseLegalDao.getNextPK();
    }

    @Override
    public void saveOrUpdate(VinculoBaselegal vinculoBaselegal) throws Exception {
        TvinculoBaselegal tvinculoBaselegal = new TvinculoBaselegal();
        BeanUtils.copyProperties(tvinculoBaselegal, vinculoBaselegal);
        VinculoBaseLegalDao vinculoBaseLegalDao = (VinculoBaseLegalDao) ServiceFinder.findBean("VinculoBaseLegalDao");
        vinculoBaseLegalDao.saveOrUpdate(tvinculoBaselegal);
    }
    
    @Override
    public void deleteByBaseLegal(BaseLegal baselegal) throws Exception {
        Tbaselegal tbaselegal = new Tbaselegal();
        BeanUtils.copyProperties(tbaselegal, baselegal);
        VinculoBaseLegalDao vinculoBaseLegalDao = (VinculoBaseLegalDao) ServiceFinder.findBean("VinculoBaseLegalDao");
        vinculoBaseLegalDao.deleteByBaseLegal(tbaselegal);
    }
}
