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
import pe.gob.mef.gescon.hibernate.dao.BaseLegalHistorialDao;
import pe.gob.mef.gescon.hibernate.domain.TbaselegalHist;
import pe.gob.mef.gescon.service.BaseLegalHistorialService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.BaselegalHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "BaseLegalHistorialService")
public class BaseLegalHistorialServiceImpl implements BaseLegalHistorialService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        BaseLegalHistorialDao historialDao = (BaseLegalHistorialDao) ServiceFinder.findBean("BaseLegalHistorialDao");
        return historialDao.getNextPK();
    }

    @Override
    public BaselegalHist getHistorialById(BigDecimal idhistorial) throws Exception {
        BaseLegalHistorialDao historialDao = (BaseLegalHistorialDao) ServiceFinder.findBean("BaseLegalHistorialDao");
        TbaselegalHist thistorial = historialDao.getThistorialById(idhistorial);
        BaselegalHist historial = new BaselegalHist();
        BeanUtils.copyProperties(historial, thistorial);
        return historial;
    }

    @Override
    public BaselegalHist getLastHistorialByBaselegal(BigDecimal idbaselegal) throws Exception {
        BaseLegalHistorialDao historialDao = (BaseLegalHistorialDao) ServiceFinder.findBean("BaseLegalHistorialDao");
        TbaselegalHist thistorial = historialDao.getLastThistorialByTbaselegal(idbaselegal);
        BaselegalHist historial = new BaselegalHist();
        if(thistorial != null) {
            BeanUtils.copyProperties(historial, thistorial);
        } else {
            historial = null;
        }
        return historial;
    }

    @Override
    public List<BaselegalHist> getHistoriales() throws Exception {
        List<BaselegalHist> historiales = new ArrayList<BaselegalHist>();
        BaseLegalHistorialDao historialDao = (BaseLegalHistorialDao) ServiceFinder.findBean("BaseLegalHistorialDao");
        List<TbaselegalHist> lista = historialDao.getThistoriales();
        for (TbaselegalHist thistorial : lista) {
            BaselegalHist historial = new BaselegalHist();
            BeanUtils.copyProperties(historial, thistorial);
            historiales.add(historial);
        }
        return historiales;
    }

    @Override
    public List<BaselegalHist> getHistorialesByBaselegal(BigDecimal idbaselegal) throws Exception {
        List<BaselegalHist> historiales = new ArrayList<BaselegalHist>();
        BaseLegalHistorialDao historialDao = (BaseLegalHistorialDao) ServiceFinder.findBean("BaseLegalHistorialDao");
        List<TbaselegalHist> lista = historialDao.getThistorialesByTbaselegal(idbaselegal);
        for (TbaselegalHist thistorial : lista) {
            BaselegalHist historial = new BaselegalHist();
            BeanUtils.copyProperties(historial, thistorial);
            historiales.add(historial);
        }
        return historiales;
    }

    @Override
    public void saveOrUpdate(BaselegalHist historial) throws Exception {
        TbaselegalHist thistorial = new TbaselegalHist();
        BeanUtils.copyProperties(thistorial, historial);
        BaseLegalHistorialDao historialDao = (BaseLegalHistorialDao) ServiceFinder.findBean("BaseLegalHistorialDao");
        historialDao.saveOrUpdate(thistorial);
    }
}
