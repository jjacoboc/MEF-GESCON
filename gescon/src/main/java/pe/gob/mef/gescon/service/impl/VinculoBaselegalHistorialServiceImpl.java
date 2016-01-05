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
import pe.gob.mef.gescon.hibernate.dao.VinculoBaselegalHistorialDao;
import pe.gob.mef.gescon.hibernate.domain.TvinculoBaselegalHist;
import pe.gob.mef.gescon.service.VinculoBaselegalHistorialService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.VinculoBaselegalHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "VinculoBaselegalHistorialService")
public class VinculoBaselegalHistorialServiceImpl implements VinculoBaselegalHistorialService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        VinculoBaselegalHistorialDao vinculoHistDao = (VinculoBaselegalHistorialDao) ServiceFinder.findBean("VinculoBaselegalHistorialDao");
        return vinculoHistDao.getNextPK();
    }

    @Override
    public VinculoBaselegalHist getVinculoHistById(BigDecimal idvinculoh) throws Exception {
        VinculoBaselegalHistorialDao vinculoHistDao = (VinculoBaselegalHistorialDao) ServiceFinder.findBean("VinculoBaselegalHistorialDao");
        TvinculoBaselegalHist tvinculoHist = vinculoHistDao.getTvinculoHistById(idvinculoh);
        VinculoBaselegalHist vinculoHist = new VinculoBaselegalHist();
        BeanUtils.copyProperties(vinculoHist, tvinculoHist);
        return vinculoHist;
    }

    @Override
    public List<VinculoBaselegalHist> getVinculoHists() throws Exception {
        List<VinculoBaselegalHist> vinculosHists = new ArrayList<VinculoBaselegalHist>();
        VinculoBaselegalHistorialDao vinculoHistDao = (VinculoBaselegalHistorialDao) ServiceFinder.findBean("VinculoBaselegalHistorialDao");
        List<TvinculoBaselegalHist> lista = vinculoHistDao.getTvinculoHists();
        for (TvinculoBaselegalHist tvinculoHist : lista) {
            VinculoBaselegalHist vinculoHist = new VinculoBaselegalHist();
            BeanUtils.copyProperties(vinculoHist, tvinculoHist);
            vinculosHists.add(vinculoHist);
        }
        return vinculosHists;
    }

    @Override
    public List<VinculoBaselegalHist> getVinculoHistsByBaselegalHist(BigDecimal idhistorial) throws Exception {
        List<VinculoBaselegalHist> vinculosHists = new ArrayList<VinculoBaselegalHist>();
        VinculoBaselegalHistorialDao vinculoHistDao = (VinculoBaselegalHistorialDao) ServiceFinder.findBean("VinculoBaselegalHistorialDao");
        List<TvinculoBaselegalHist> lista = vinculoHistDao.getTvinculoHistsByTbaselegalHist(idhistorial);
        for (TvinculoBaselegalHist tvinculoHist : lista) {
            VinculoBaselegalHist vinculoHist = new VinculoBaselegalHist();
            BeanUtils.copyProperties(vinculoHist, tvinculoHist);
            vinculosHists.add(vinculoHist);
        }
        return vinculosHists;
    }

    @Override
    public void saveOrUpdate(VinculoBaselegalHist vinculoHist) throws Exception {
        VinculoBaselegalHistorialDao vinculoHistDao = (VinculoBaselegalHistorialDao) ServiceFinder.findBean("VinculoBaselegalHistorialDao");
        TvinculoBaselegalHist tvinculoHist = new TvinculoBaselegalHist();
        BeanUtils.copyProperties(tvinculoHist, vinculoHist);
        vinculoHistDao.saveOrUpdate(tvinculoHist);
    }
    
}
