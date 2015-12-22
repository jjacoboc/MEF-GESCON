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
import pe.gob.mef.gescon.hibernate.dao.DiscusionSeccionHistDao;
import pe.gob.mef.gescon.hibernate.domain.TdiscusionSeccionHist;
import pe.gob.mef.gescon.service.DiscusionSeccionHistService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.DiscusionSeccionHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "DiscusionSeccionHistService")
public class DiscusionSeccionHistServiceImpl implements DiscusionSeccionHistService {

    @Override
    public BigDecimal getNextPK() throws Exception {
        DiscusionSeccionHistDao discusionSeccionHistDao = (DiscusionSeccionHistDao) ServiceFinder.findBean("DiscusionSeccionHistDao");
        return discusionSeccionHistDao.getNextPK();
    }

    @Override
    public DiscusionSeccionHist getSeccionHistById(BigDecimal idseccion) throws Exception {
        DiscusionSeccionHistDao discusionSeccionHistDao = (DiscusionSeccionHistDao) ServiceFinder.findBean("DiscusionSeccionHistDao");
        TdiscusionSeccionHist tdiscusionSeccionHist = discusionSeccionHistDao.getTseccionHistById(idseccion);
        DiscusionSeccionHist discusionSeccionHist = new DiscusionSeccionHist();
        BeanUtils.copyProperties(discusionSeccionHist, tdiscusionSeccionHist);
        return discusionSeccionHist;
    }

    @Override
    public List<DiscusionSeccionHist> getSeccionesHist() throws Exception {
        List<DiscusionSeccionHist> seccionesHist = new ArrayList<DiscusionSeccionHist>();
        DiscusionSeccionHistDao discusionSeccionHistDao = (DiscusionSeccionHistDao) ServiceFinder.findBean("DiscusionSeccionHistDao");
        List<TdiscusionSeccionHist> lista = discusionSeccionHistDao.getTseccionesHist();
        for (TdiscusionSeccionHist tdiscusionSeccionHist : lista) {
            DiscusionSeccionHist discusionSeccionHist = new DiscusionSeccionHist();
            BeanUtils.copyProperties(discusionSeccionHist, tdiscusionSeccionHist);
            seccionesHist.add(discusionSeccionHist);
        }
        return seccionesHist;
    }

    @Override
    public List<DiscusionSeccionHist> getSeccionesHistByDiscusionHist(BigDecimal iddiscusion) throws Exception {
        List<DiscusionSeccionHist> seccionesHist = new ArrayList<DiscusionSeccionHist>();
        DiscusionSeccionHistDao discusionSeccionHistDao = (DiscusionSeccionHistDao) ServiceFinder.findBean("DiscusionSeccionHistDao");
        List<TdiscusionSeccionHist> lista = discusionSeccionHistDao.getTseccionesHistByTdiscusionHist(iddiscusion);
        for (TdiscusionSeccionHist tdiscusionSeccionHist : lista) {
            DiscusionSeccionHist discusionSeccionHist = new DiscusionSeccionHist();
            BeanUtils.copyProperties(discusionSeccionHist, tdiscusionSeccionHist);
            seccionesHist.add(discusionSeccionHist);
        }
        return seccionesHist;
    }

    @Override
    public void saveOrUpdate(DiscusionSeccionHist discusionSeccionHist) throws Exception {
        TdiscusionSeccionHist tdiscusionSeccionHist = new TdiscusionSeccionHist();
        BeanUtils.copyProperties(tdiscusionSeccionHist, discusionSeccionHist);
        DiscusionSeccionHistDao discusionSeccionHistDao = (DiscusionSeccionHistDao) ServiceFinder.findBean("DiscusionSeccionHistDao");
        discusionSeccionHistDao.saveOrUpdate(tdiscusionSeccionHist);
    }
    
}
