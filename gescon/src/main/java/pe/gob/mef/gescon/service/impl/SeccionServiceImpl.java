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
import pe.gob.mef.gescon.hibernate.dao.SeccionDao;
import pe.gob.mef.gescon.hibernate.domain.Tseccion;
import pe.gob.mef.gescon.service.SeccionService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Seccion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "SeccionService")
public class SeccionServiceImpl implements SeccionService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        SeccionDao seccionDao = (SeccionDao) ServiceFinder.findBean("SeccionDao");
        return seccionDao.getNextPK();
    }

    @Override
    public Seccion getSeccionById(BigDecimal idseccion) throws Exception {
        SeccionDao seccionDao = (SeccionDao) ServiceFinder.findBean("SeccionDao");
        Tseccion tseccion = seccionDao.getTseccionById(idseccion);
        Seccion seccion = new Seccion();
        BeanUtils.copyProperties(seccion, tseccion);
        return seccion;
    }

    @Override
    public List<Seccion> getSecciones() throws Exception {
        List<Seccion> secciones = new ArrayList<Seccion>();
        SeccionDao seccionDao = (SeccionDao) ServiceFinder.findBean("SeccionDao");
        List<Tseccion> lista = seccionDao.getTsecciones();
        for (Tseccion tseccion : lista) {
            Seccion seccion = new Seccion();
            BeanUtils.copyProperties(seccion, tseccion);
            secciones.add(seccion);
        }
        return secciones;
    }

    @Override
    public List<Seccion> getSeccionesByConocimiento(BigDecimal idconocimiento) throws Exception {
        List<Seccion> secciones = new ArrayList<Seccion>();
        SeccionDao seccionDao = (SeccionDao) ServiceFinder.findBean("SeccionDao");
        List<Tseccion> lista = seccionDao.getTseccionesByTconocimiento(idconocimiento);
        for (Tseccion tseccion : lista) {
            Seccion seccion = new Seccion();
            BeanUtils.copyProperties(seccion, tseccion);
            secciones.add(seccion);
        }
        return secciones;
    }

    @Override
    public void saveOrUpdate(Seccion seccion) throws Exception {
        Tseccion tseccion = new Tseccion();
        BeanUtils.copyProperties(tseccion, seccion);
        SeccionDao seccionDao = (SeccionDao) ServiceFinder.findBean("SeccionDao");
        seccionDao.saveOrUpdate(tseccion);
    }
    
    @Override
    public void deleteSeccionesByConocimiento(BigDecimal idconocimiento) throws Exception {
        SeccionDao seccionDao = (SeccionDao) ServiceFinder.findBean("SeccionDao");
        seccionDao.deleteTseccionesByTconocimiento(idconocimiento);
    }
}
