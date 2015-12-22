/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.DiscusionHistDao;
import pe.gob.mef.gescon.hibernate.domain.TdiscusionHist;
import pe.gob.mef.gescon.service.DiscusionHistService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.DiscusionHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "DiscusionHistService")
public class DiscusionHistServiceImpl implements DiscusionHistService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        DiscusionHistDao discusionHistDao = (DiscusionHistDao) ServiceFinder.findBean("DiscusionHistDao");
        return discusionHistDao.getNextPK();
    }

    @Override
    public DiscusionHist getDiscusionHistById(BigDecimal ndiscusionhid) throws Exception {
        DiscusionHistDao discusionHistDao = (DiscusionHistDao) ServiceFinder.findBean("DiscusionHistDao");
        TdiscusionHist tdiscusionHist = discusionHistDao.getTdiscusionHistById(ndiscusionhid);
        DiscusionHist discusionHist = new DiscusionHist();
        BeanUtils.copyProperties(discusionHist, tdiscusionHist);
        return discusionHist;
    }

    @Override
    public DiscusionHist getDiscusionHistByConocimiento(BigDecimal idconocimiento) throws Exception {
        DiscusionHistDao discusionHistDao = (DiscusionHistDao) ServiceFinder.findBean("DiscusionHistDao");
        TdiscusionHist tdiscusionHist = discusionHistDao.getTdiscusionHistByTconocimiento(idconocimiento);
        DiscusionHist discusionHist = new DiscusionHist();
        BeanUtils.copyProperties(discusionHist, tdiscusionHist);
        return discusionHist;
    }

    @Override
    public void saveOrUpdate(DiscusionHist discusionHist) throws Exception {
        TdiscusionHist tdiscusionHist = new TdiscusionHist();
        BeanUtils.copyProperties(tdiscusionHist, discusionHist);
        DiscusionHistDao discusionHistDao = (DiscusionHistDao) ServiceFinder.findBean("DiscusionHistDao");
        discusionHistDao.saveOrUpdate(tdiscusionHist);
    }
    
}
