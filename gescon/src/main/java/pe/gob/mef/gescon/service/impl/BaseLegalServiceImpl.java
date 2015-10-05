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
import pe.gob.mef.gescon.hibernate.dao.BaseLegalDao;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.service.BaseLegalService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.BaseLegal;

/**
 *
 * @author JJacobo
 */
@Repository(value = "BaseLegalService")
public class BaseLegalServiceImpl implements BaseLegalService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        return baseLegalDao.getNextPK();
    }

    @Override
    public List<BaseLegal> getBaselegales() throws Exception {
        List<BaseLegal> baseLegales = new ArrayList<BaseLegal>();
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        List<Tbaselegal> lista = baseLegalDao.getTbaselegales();
        for(Tbaselegal tbaselegal : lista) {
            BaseLegal baseLegal = new BaseLegal();
            BeanUtils.copyProperties(baseLegal, tbaselegal);
            baseLegales.add(baseLegal);
        }
        return baseLegales;
    }

    @Override
    public void saveOrUpdate(BaseLegal baseLegal) throws Exception {
        Tbaselegal tbaselegal = new Tbaselegal();
        BeanUtils.copyProperties(tbaselegal, baseLegal);
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        baseLegalDao.saveOrUpdate(tbaselegal);
    }
    
}
