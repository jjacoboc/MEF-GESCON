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
import pe.gob.mef.gescon.hibernate.dao.DiscusionDao;
import pe.gob.mef.gescon.hibernate.domain.Tdiscusion;
import pe.gob.mef.gescon.service.DiscusionService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Discusion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "DiscusionService")
public class DiscusionServiceImpl implements DiscusionService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        DiscusionDao discusionDao = (DiscusionDao) ServiceFinder.findBean("DiscusionDao");
        return discusionDao.getNextPK();
    }

    @Override
    public Discusion getDiscusionById(BigDecimal iddiscusion) throws Exception {
        DiscusionDao discusionDao = (DiscusionDao) ServiceFinder.findBean("DiscusionDao");
        Tdiscusion tdiscusion = discusionDao.getTdiscusionById(iddiscusion);
        Discusion discusion = new Discusion();
        BeanUtils.copyProperties(discusion, tdiscusion);
        return discusion;
    }

    @Override
    public List<Discusion> getDiscusiones() throws Exception {
        List<Discusion> discusiones = new ArrayList<Discusion>();
        DiscusionDao discusionDao = (DiscusionDao) ServiceFinder.findBean("DiscusionDao");
        List<Tdiscusion> lista = discusionDao.getTdiscusiones();
        for (Tdiscusion tdiscusion : lista) {
            Discusion discusion = new Discusion();
            BeanUtils.copyProperties(discusion, tdiscusion);
            discusiones.add(discusion);
        }
        return discusiones;
    }

    @Override
    public List<Discusion> getDiscusionesByConocimiento(BigDecimal idconocimiento) throws Exception {
        List<Discusion> discusiones = new ArrayList<Discusion>();
        DiscusionDao discusionDao = (DiscusionDao) ServiceFinder.findBean("DiscusionDao");
        List<Tdiscusion> lista = discusionDao.getTdiscusionesByTconocimiento(idconocimiento);
        for (Tdiscusion tdiscusion : lista) {
            Discusion discusion = new Discusion();
            BeanUtils.copyProperties(discusion, tdiscusion);
            discusiones.add(discusion);
        }
        return discusiones;
    }

    @Override
    public void saveOrUpdate(Discusion discusion) throws Exception {
        Tdiscusion tdiscusion = new Tdiscusion();
        BeanUtils.copyProperties(tdiscusion, discusion);
        DiscusionDao discusionDao = (DiscusionDao) ServiceFinder.findBean("DiscusionDao");
        discusionDao.saveOrUpdate(tdiscusion);
    }
    
}
