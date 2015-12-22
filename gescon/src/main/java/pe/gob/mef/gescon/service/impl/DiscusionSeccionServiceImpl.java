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
import pe.gob.mef.gescon.hibernate.dao.DiscusionSeccionDao;
import pe.gob.mef.gescon.hibernate.domain.TdiscusionSeccion;
import pe.gob.mef.gescon.service.DiscusionSeccionService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.DiscusionSeccion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "DiscusionSeccionService")
public class DiscusionSeccionServiceImpl implements DiscusionSeccionService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        DiscusionSeccionDao discusionSeccionDao = (DiscusionSeccionDao) ServiceFinder.findBean("DiscusionSeccionDao");
        return discusionSeccionDao.getNextPK();
    }

    @Override
    public DiscusionSeccion getSeccionById(BigDecimal idseccion) throws Exception {
        DiscusionSeccionDao discusionSeccionDao = (DiscusionSeccionDao) ServiceFinder.findBean("DiscusionSeccionDao");
        TdiscusionSeccion tdiscusionSeccion = discusionSeccionDao.getTseccionById(idseccion);
        DiscusionSeccion discusionSeccion = new DiscusionSeccion();
        BeanUtils.copyProperties(discusionSeccion, tdiscusionSeccion);
        return discusionSeccion;
    }

    @Override
    public List<DiscusionSeccion> getSecciones() throws Exception {
        List<DiscusionSeccion> secciones = new ArrayList<DiscusionSeccion>();
        DiscusionSeccionDao discusionSeccionDao = (DiscusionSeccionDao) ServiceFinder.findBean("DiscusionSeccionDao");
        List<TdiscusionSeccion> lista = discusionSeccionDao.getTsecciones();
        for (TdiscusionSeccion tdiscusionSeccion : lista) {
            DiscusionSeccion discusionSeccion = new DiscusionSeccion();
            BeanUtils.copyProperties(discusionSeccion, tdiscusionSeccion);
            secciones.add(discusionSeccion);
        }
        return secciones;
    }

    @Override
    public List<DiscusionSeccion> getSeccionesByDiscusion(BigDecimal iddiscusion) throws Exception {
        List<DiscusionSeccion> secciones = new ArrayList<DiscusionSeccion>();
        DiscusionSeccionDao discusionSeccionDao = (DiscusionSeccionDao) ServiceFinder.findBean("DiscusionSeccionDao");
        List<TdiscusionSeccion> lista = discusionSeccionDao.getTseccionesByTdiscusion(iddiscusion);
        for (TdiscusionSeccion tdiscusionSeccion : lista) {
            DiscusionSeccion discusionSeccion = new DiscusionSeccion();
            BeanUtils.copyProperties(discusionSeccion, tdiscusionSeccion);
            secciones.add(discusionSeccion);
        }
        return secciones;
    }

    @Override
    public void saveOrUpdate(DiscusionSeccion discusionSeccion) throws Exception {
        TdiscusionSeccion tdiscusionSeccion = new TdiscusionSeccion();
        BeanUtils.copyProperties(tdiscusionSeccion, discusionSeccion);
        DiscusionSeccionDao discusionSeccionDao = (DiscusionSeccionDao) ServiceFinder.findBean("DiscusionSeccionDao");
        discusionSeccionDao.saveOrUpdate(tdiscusionSeccion);
    }
    
}
