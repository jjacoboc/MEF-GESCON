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
import pe.gob.mef.gescon.hibernate.dao.VinculoHistDao;
import pe.gob.mef.gescon.hibernate.domain.TvinculoHist;
import pe.gob.mef.gescon.service.VinculoHistService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.VinculoHist;

/**
 *
 * @author JJacobo
 */
public class VinculoHistServiceImpl implements VinculoHistService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        VinculoHistDao vinculoHistDao = (VinculoHistDao) ServiceFinder.findBean("VinculoHistDao");
        return vinculoHistDao.getNextPK();
    }

    @Override
    public VinculoHist getVinculoHistById(BigDecimal idvinculoh) throws Exception {
        VinculoHistDao vinculoHistDao = (VinculoHistDao) ServiceFinder.findBean("VinculoHistDao");
        TvinculoHist tvinculoHist = vinculoHistDao.getTvinculoHistById(idvinculoh);
        VinculoHist vinculoHist = new VinculoHist();
        BeanUtils.copyProperties(vinculoHist, tvinculoHist);
        return vinculoHist;
    }

    @Override
    public List<VinculoHist> getVinculoHists() throws Exception {
        List<VinculoHist> vinculosHists = new ArrayList<VinculoHist>();
        VinculoHistDao vinculoHistDao = (VinculoHistDao) ServiceFinder.findBean("VinculoHistDao");
        List<TvinculoHist> lista = vinculoHistDao.getTvinculoHists();
        for (TvinculoHist tvinculoHist : lista) {
            VinculoHist vinculoHist = new VinculoHist();
            BeanUtils.copyProperties(vinculoHist, tvinculoHist);
            vinculosHists.add(vinculoHist);
        }
        return vinculosHists;
    }

    @Override
    public List<VinculoHist> getVinculoHistsByHistorial(BigDecimal idhistorial) throws Exception {
        List<VinculoHist> vinculosHists = new ArrayList<VinculoHist>();
        VinculoHistDao vinculoHistDao = (VinculoHistDao) ServiceFinder.findBean("VinculoHistDao");
        List<TvinculoHist> lista = vinculoHistDao.getTvinculoHistsByThistorial(idhistorial);
        for (TvinculoHist tvinculoHist : lista) {
            VinculoHist vinculoHist = new VinculoHist();
            BeanUtils.copyProperties(vinculoHist, tvinculoHist);
            vinculosHists.add(vinculoHist);
        }
        return vinculosHists;
    }

    @Override
    public void saveOrUpdate(VinculoHist vinculoHist) throws Exception {
        VinculoHistDao vinculoHistDao = (VinculoHistDao) ServiceFinder.findBean("VinculoHistDao");
        TvinculoHist tvinculoHist = new TvinculoHist();
        BeanUtils.copyProperties(tvinculoHist, vinculoHist);
        vinculoHistDao.saveOrUpdate(tvinculoHist);
    }
    
}
