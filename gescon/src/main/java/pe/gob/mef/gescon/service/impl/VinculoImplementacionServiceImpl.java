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
import pe.gob.mef.gescon.hibernate.dao.VinculoDao;
import pe.gob.mef.gescon.hibernate.dao.VinculoImplementacionDao;
import pe.gob.mef.gescon.hibernate.domain.TvinculoImplementacion;
import pe.gob.mef.gescon.service.VinculoImplementacionService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.VinculoImplementacion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "VinculoImplementacionService")
public class VinculoImplementacionServiceImpl implements VinculoImplementacionService {

    @Override
    public BigDecimal getNextPK() throws Exception {
        VinculoDao vinculoDao = (VinculoDao) ServiceFinder.findBean("VinculoDao");
        return vinculoDao.getNextPK();
    }

    @Override
    public List<VinculoImplementacion> getVinculos() throws Exception {
        List<VinculoImplementacion> vinculos = new ArrayList<VinculoImplementacion>();
        VinculoImplementacionDao vinculoDao = (VinculoImplementacionDao) ServiceFinder.findBean("VinculoImplementacionDao");
        List<TvinculoImplementacion> lista = vinculoDao.getTvinculos();
        for (TvinculoImplementacion tvinculo : lista) {
            VinculoImplementacion vinculo = new VinculoImplementacion();
            BeanUtils.copyProperties(vinculo, tvinculo);
            vinculos.add(vinculo);
        }
        return vinculos;
    }

    @Override
    public List<VinculoImplementacion> getVinculosByConocimiento(BigDecimal idconocimiento) throws Exception {
        List<VinculoImplementacion> vinculos = new ArrayList<VinculoImplementacion>();
        VinculoImplementacionDao vinculoDao = (VinculoImplementacionDao) ServiceFinder.findBean("VinculoImplementacionDao");
        List<TvinculoImplementacion> lista = vinculoDao.getTvinculosByTconocimiento(idconocimiento);
        for (TvinculoImplementacion tvinculo : lista) {
            VinculoImplementacion vinculo = new VinculoImplementacion();
            BeanUtils.copyProperties(vinculo, tvinculo);
            vinculos.add(vinculo);
        }
        return vinculos;
    }

    @Override
    public VinculoImplementacion getVinculoById(BigDecimal idvinculo) throws Exception {
        VinculoImplementacionDao vinculoDao = (VinculoImplementacionDao) ServiceFinder.findBean("VinculoImplementacionDao");
        TvinculoImplementacion tvinculo = vinculoDao.getTvinculoById(idvinculo);
        VinculoImplementacion vinculo = new VinculoImplementacion();
        BeanUtils.copyProperties(vinculo, tvinculo);
        return vinculo;
    }

    @Override
    public void saveOrUpdate(VinculoImplementacion vinculoImplementacion) throws Exception {
        TvinculoImplementacion tvinculo = new TvinculoImplementacion();
        BeanUtils.copyProperties(tvinculo, vinculoImplementacion);
        VinculoImplementacionDao vinculoDao = (VinculoImplementacionDao) ServiceFinder.findBean("VinculoImplementacionDao");
        vinculoDao.saveOrUpdate(tvinculo);
    }

    @Override
    public void deleteByConocimiento(BigDecimal idconocimiento) throws Exception {
        VinculoImplementacionDao vinculoDao = (VinculoImplementacionDao) ServiceFinder.findBean("VinculoImplementacionDao");
        vinculoDao.deleteByTconocimiento(idconocimiento);
    }
}
