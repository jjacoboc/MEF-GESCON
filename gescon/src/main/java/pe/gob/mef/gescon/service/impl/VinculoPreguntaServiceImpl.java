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
import pe.gob.mef.gescon.hibernate.dao.VinculoPreguntaDao;
import pe.gob.mef.gescon.hibernate.domain.TvinculoPregunta;
import pe.gob.mef.gescon.service.VinculoPreguntaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.VinculoPregunta;

/**
 *
 * @author JJacobo
 */
@Repository(value = "VinculoPreguntaService")
public class VinculoPreguntaServiceImpl implements VinculoPreguntaService {

    @Override
    public BigDecimal getNextPK() throws Exception {
        VinculoPreguntaDao vinculopreguntaDao = (VinculoPreguntaDao) ServiceFinder.findBean("VinculoPreguntaDao");
        return vinculopreguntaDao.getNextPK();
    }

    @Override
    public List<VinculoPregunta> getVinculos() throws Exception {
        List<VinculoPregunta> vinculos = new ArrayList<VinculoPregunta>();
        VinculoPreguntaDao vinculopreguntaDao = (VinculoPreguntaDao) ServiceFinder.findBean("VinculoPreguntaDao");
        List<TvinculoPregunta> lista = vinculopreguntaDao.getTvinculos();
        for (TvinculoPregunta tvinculopregunta : lista) {
            VinculoPregunta vinculopregunta = new VinculoPregunta();
            BeanUtils.copyProperties(vinculopregunta, tvinculopregunta);
            vinculos.add(vinculopregunta);
        }
        return vinculos;
    }

    @Override
    public List<VinculoPregunta> getVinculosByConocimiento(BigDecimal idconocimiento) throws Exception {
        List<VinculoPregunta> vinculos = new ArrayList<VinculoPregunta>();
        VinculoPreguntaDao vinculopreguntaDao = (VinculoPreguntaDao) ServiceFinder.findBean("VinculoPreguntaDao");
        List<TvinculoPregunta> lista = vinculopreguntaDao.getTvinculosByTconocimiento(idconocimiento);
        for (TvinculoPregunta tvinculopregunta : lista) {
            VinculoPregunta vinculopregunta = new VinculoPregunta();
            BeanUtils.copyProperties(vinculopregunta, tvinculopregunta);
            vinculos.add(vinculopregunta);
        }
        return vinculos;
    }

    @Override
    public List<VinculoPregunta> getVinculosByConocimientoAndTipoconocimiento(BigDecimal idconocimiento, BigDecimal idtipoconocimiento) throws Exception {
        List<VinculoPregunta> vinculos = new ArrayList<VinculoPregunta>();
        VinculoPreguntaDao vinculopreguntaDao = (VinculoPreguntaDao) ServiceFinder.findBean("VinculoPreguntaDao");
        List<TvinculoPregunta> lista = vinculopreguntaDao.getTvinculosByTconocimientoAndTtipoconocimiento(idconocimiento, idtipoconocimiento);
        for (TvinculoPregunta tvinculopregunta : lista) {
            VinculoPregunta vinculopregunta = new VinculoPregunta();
            BeanUtils.copyProperties(vinculopregunta, tvinculopregunta);
            vinculos.add(vinculopregunta);
        }
        return vinculos;
    }

    @Override
    public VinculoPregunta getVinculoById(BigDecimal idvinculo) throws Exception {
        VinculoPreguntaDao vinculopreguntaDao = (VinculoPreguntaDao) ServiceFinder.findBean("VinculoPreguntaDao");
        TvinculoPregunta tvinculopregunta = vinculopreguntaDao.getTvinculoById(idvinculo);
        VinculoPregunta vinculopregunta = new VinculoPregunta();
        BeanUtils.copyProperties(vinculopregunta, tvinculopregunta);
        return vinculopregunta;
    }

    @Override
    public void saveOrUpdate(VinculoPregunta vinculopregunta) throws Exception {
        TvinculoPregunta tvinculopregunta = new TvinculoPregunta();
        BeanUtils.copyProperties(tvinculopregunta, vinculopregunta);
        VinculoPreguntaDao vinculopreguntaDao = (VinculoPreguntaDao) ServiceFinder.findBean("VinculoPreguntaDao");
        vinculopreguntaDao.saveOrUpdate(tvinculopregunta);
    }

    @Override
    public void deleteByConocimiento(BigDecimal idconocimiento) throws Exception {
        VinculoPreguntaDao vinculopreguntaDao = (VinculoPreguntaDao) ServiceFinder.findBean("VinculoPreguntaDao");
        vinculopreguntaDao.deleteByTconocimiento(idconocimiento);
    }
}
