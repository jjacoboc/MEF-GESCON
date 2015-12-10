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
import pe.gob.mef.gescon.hibernate.domain.Tvinculo;
import pe.gob.mef.gescon.service.VinculoService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Vinculo;

/**
 *
 * @author JJacobo
 */
@Repository(value = "VinculoService")
public class VinculoServiceImpl implements VinculoService {

    @Override
    public BigDecimal getNextPK() throws Exception {
        VinculoDao vinculoDao = (VinculoDao) ServiceFinder.findBean("VinculoDao");
        return vinculoDao.getNextPK();
    }

    @Override
    public List<Vinculo> getVinculos() throws Exception {
        List<Vinculo> vinculos = new ArrayList<Vinculo>();
        VinculoDao vinculoDao = (VinculoDao) ServiceFinder.findBean("VinculoDao");
        List<Tvinculo> lista = vinculoDao.getTvinculos();
        for (Tvinculo tvinculo : lista) {
            Vinculo vinculo = new Vinculo();
            BeanUtils.copyProperties(vinculo, tvinculo);
            vinculos.add(vinculo);
        }
        return vinculos;
    }

    @Override
    public List<Vinculo> getVinculosByConocimiento(BigDecimal idconocimiento) throws Exception {
        List<Vinculo> vinculos = new ArrayList<Vinculo>();
        VinculoDao vinculoDao = (VinculoDao) ServiceFinder.findBean("VinculoDao");
        List<Tvinculo> lista = vinculoDao.getTvinculosByTconocimiento(idconocimiento);
        for (Tvinculo tvinculo : lista) {
            Vinculo vinculo = new Vinculo();
            BeanUtils.copyProperties(vinculo, tvinculo);
            vinculos.add(vinculo);
        }
        return vinculos;
    }

    @Override
    public List<Vinculo> getVinculosByConocimientoAndTipoconocimiento(BigDecimal idconocimiento, BigDecimal idtipoconocimiento) throws Exception {
        List<Vinculo> vinculos = new ArrayList<Vinculo>();
        VinculoDao vinculoDao = (VinculoDao) ServiceFinder.findBean("VinculoDao");
        List<Tvinculo> lista = vinculoDao.getTvinculosByTconocimientoAndTtipoconocimiento(idconocimiento, idtipoconocimiento);
        for (Tvinculo tvinculo : lista) {
            Vinculo vinculo = new Vinculo();
            BeanUtils.copyProperties(vinculo, tvinculo);
            vinculos.add(vinculo);
        }
        return vinculos;
    }

    @Override
    public Vinculo getVinculoById(BigDecimal idvinculo) throws Exception {
        VinculoDao vinculoDao = (VinculoDao) ServiceFinder.findBean("VinculoDao");
        Tvinculo tvinculo = vinculoDao.getTvinculoById(idvinculo);
        Vinculo vinculo = new Vinculo();
        BeanUtils.copyProperties(vinculo, tvinculo);
        return vinculo;
    }

    @Override
    public void saveOrUpdate(Vinculo vinculo) throws Exception {
        Tvinculo tvinculo = new Tvinculo();
        BeanUtils.copyProperties(tvinculo, vinculo);
        VinculoDao vinculoDao = (VinculoDao) ServiceFinder.findBean("VinculoDao");
        vinculoDao.saveOrUpdate(tvinculo);
    }

    @Override
    public void deleteByConocimiento(BigDecimal idconocimiento) throws Exception {
        VinculoDao vinculoDao = (VinculoDao) ServiceFinder.findBean("VinculoDao");
        vinculoDao.deleteByTconocimiento(idconocimiento);
    }
}
