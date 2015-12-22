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
import pe.gob.mef.gescon.hibernate.dao.HistorialDao;
import pe.gob.mef.gescon.hibernate.domain.Thistorial;
import pe.gob.mef.gescon.service.HistorialService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Historial;

/**
 *
 * @author JJacobo
 */
@Repository(value = "HistorialService")
public class HistorialServiceImpl implements HistorialService {

    @Override
    public BigDecimal getNextPK() throws Exception {
        HistorialDao historialDao = (HistorialDao) ServiceFinder.findBean("HistorialDao");
        return historialDao.getNextPK();
    }

    @Override
    public Historial getHistorialById(BigDecimal idhistorial) throws Exception {
        HistorialDao historialDao = (HistorialDao) ServiceFinder.findBean("HistorialDao");
        Thistorial thistorial = historialDao.getThistorialById(idhistorial);
        Historial historial = new Historial();
        BeanUtils.copyProperties(historial, thistorial);
        return historial;
    }
    
    @Override
    public Historial getLastHistorialByConocimiento(BigDecimal idconocimiento) throws Exception {
        HistorialDao historialDao = (HistorialDao) ServiceFinder.findBean("HistorialDao");
        Thistorial thistorial = historialDao.getLastThistorialByTconocimiento(idconocimiento);
        Historial historial = new Historial();
        if(thistorial != null) {
            BeanUtils.copyProperties(historial, thistorial);
        } else {
            historial = null;
        }
        return historial;
    }

    @Override
    public List<Historial> getHistoriales() throws Exception {
        List<Historial> historiales = new ArrayList<Historial>();
        HistorialDao historialDao = (HistorialDao) ServiceFinder.findBean("HistorialDao");
        List<Thistorial> lista = historialDao.getThistoriales();
        for (Thistorial thistorial : lista) {
            Historial historial = new Historial();
            BeanUtils.copyProperties(historial, thistorial);
            historiales.add(historial);
        }
        return historiales;
    }

    @Override
    public List<Historial> getHistorialesByConocimiento(BigDecimal idconocimiento) throws Exception {
        List<Historial> historiales = new ArrayList<Historial>();
        HistorialDao historialDao = (HistorialDao) ServiceFinder.findBean("HistorialDao");
        List<Thistorial> lista = historialDao.getThistorialesByTconocimiento(idconocimiento);
        for (Thistorial thistorial : lista) {
            Historial historial = new Historial();
            BeanUtils.copyProperties(historial, thistorial);
            historiales.add(historial);
        }
        return historiales;
    }

    @Override
    public void saveOrUpdate(Historial historial) throws Exception {
        Thistorial thistorial = new Thistorial();
        BeanUtils.copyProperties(thistorial, historial);
        HistorialDao historialDao = (HistorialDao) ServiceFinder.findBean("HistorialDao");
        historialDao.saveOrUpdate(thistorial);
    }
    
}
